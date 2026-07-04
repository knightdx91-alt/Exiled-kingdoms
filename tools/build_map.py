import os, re, json, collections
SRC="decompiled/sources"
GAME=os.path.join(SRC,"net/fdgames")
hint_re=re.compile(r'compiled from:\s*([A-Za-z0-9_$]+)\.java')

# 1) Build obfuscated->real for every class file that has a top-level hint
#    Key by fully-qualified obfuscated name (pkg.Class from path).
def fqn_from_path(p):
    rel=os.path.relpath(p,SRC)[:-5]           # strip .java
    return rel.replace('/', '.')

mapping={}          # obf_fqn -> real_simple
pkg_preserved={}    # obf_fqn -> real_fqn  (only where package is real: com.badlogic.gdx.*)
for root,_,files in os.walk(SRC):
    for fn in files:
        if not fn.endswith('.java'): continue
        p=os.path.join(root,fn)
        stem=fn[:-5]
        with open(p,encoding='utf-8',errors='ignore') as f:
            head=f.read(4000)
        m=hint_re.search(head)
        real=m.group(1) if m else stem
        if real==stem: continue               # already real name
        obf=fqn_from_path(p)
        mapping[obf]=real
        pkg='.'.join(obf.split('.')[:-1])
        # package is trustworthy for the gdx tree (verified real subpackages)
        if pkg.startswith('com.badlogic.gdx') or pkg.startswith('com.badlogic'):
            pkg_preserved[obf]=pkg+'.'+real

# 2) What obfuscated gdx symbols does the GAME actually import?
imp_re=re.compile(r'^\s*import\s+([\w.]+);',re.M)
game_imports=collections.Counter()
for root,_,files in os.walk(GAME):
    for fn in files:
        if not fn.endswith('.java'): continue
        with open(os.path.join(root,fn),encoding='utf-8',errors='ignore') as f:
            for imp in imp_re.findall(f.read()):
                game_imports[imp]+=1

# obfuscated imports = last segment is single/short lower-case token
def looks_obf(fqn):
    last=fqn.split('.')[-1]
    return bool(re.fullmatch(r'[a-z][0-9]?',last))
game_obf=[i for i in game_imports if looks_obf(i)]
resolved=[i for i in game_obf if i in mapping]

print("Total obf->real hints           :",len(mapping))
print("  of which com.badlogic.* (FQN) :",len(pkg_preserved))
print("Distinct game imports           :",len(game_imports))
print("Game imports that are obfuscated:",len(game_obf))
print("  ...resolvable via hint map    :",len(resolved))
# breakdown of game obf imports by top package
byp=collections.Counter(i.split('.')[0] for i in game_obf)
print("Game obf imports by top-package :",dict(byp.most_common()))

os.makedirs("map_out",exist_ok=True)
json.dump({k:pkg_preserved.get(k,mapping[k]) for k in sorted(mapping)},
          open("map_out/deobf_map.json","w"),indent=0)
# game-usage-focused report
rep=[]
for i in sorted(game_obf):
    rep.append({"obf":i,"real":pkg_preserved.get(i,mapping.get(i)),"uses":game_imports[i]})
json.dump(rep,open("map_out/game_obf_imports.json","w"),indent=1)
print("wrote map_out/deobf_map.json and map_out/game_obf_imports.json")

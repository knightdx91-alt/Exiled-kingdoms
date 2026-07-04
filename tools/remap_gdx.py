import os,re,json,sys
ROOT=sys.argv[1]            # source tree to rewrite in place
MAP=json.load(open(sys.argv[2]))
# real-libGDX target FQNs we can safely swap to (stock libGDX provides them)
def is_real_gdx(real):
    return real.startswith('com.badlogic.gdx.')
# obf simple-name relocated libgdx math/reflect too (real names known)
RELOCATED={  # obf_fqn -> real gdx fqn (from method map investigation)
 'a0.p':'com.badlogic.gdx.math.Rectangle','a0.q':'com.badlogic.gdx.math.Vector2',
 'h0.b':'com.badlogic.gdx.utils.reflect.Constructor','h0.c':'com.badlogic.gdx.utils.reflect.Field',
 'h0.e':'com.badlogic.gdx.utils.reflect.ReflectionException',
 't.c':'com.badlogic.gdx.audio.Music','t.d':'com.badlogic.gdx.audio.Sound',
 'u.h':'com.badlogic.gdx.maps.MapProperties','r.d':'com.badlogic.gdx.assets.AssetManager',
}
lookup={}
for obf,real in MAP.items():
    if is_real_gdx(real): lookup[obf]=real
lookup.update(RELOCATED)

def rewrite(text):
    imp_re=re.compile(r'^import\s+([\w.]+);\s*$',re.M)
    # map of simpleObf -> realSimple for this file
    local={}
    newlines=[]
    seen_real=set()
    for line in text.split('\n'):
        m=re.match(r'\s*import\s+([\w.]+);\s*$',line)
        if m and m.group(1) in lookup:
            real=lookup[m.group(1)]
            simpleObf=m.group(1).split('.')[-1]
            realSimple=real.split('.')[-1]
            local[simpleObf]=realSimple
            if real not in seen_real:
                seen_real.add(real); newlines.append(f'import {real};')
            continue
        newlines.append(line)
    text='\n'.join(newlines)
    # body type-position replacements per mapping
    for obf,real in sorted(local.items(),key=lambda x:-len(x[0])):
        o=re.escape(obf)
        text=re.sub(rf'\bnew\s+{o}\s*(?=[(<])','new '+real+' ',text)
        text=re.sub(rf'\b{o}\s*(<[^;=]*?>)?\s+([A-Za-z_]\w*\s*[=;,)])',lambda m:real+(m.group(1) or '')+' '+m.group(2),text)
        text=re.sub(rf'\({o}\)',f'({real})',text)
        text=re.sub(rf'\b{o}\.',real+'.',text)
        text=re.sub(rf'\b{o}\[\]',real+'[]',text)
    return text

n=0
for root,_,files in os.walk(ROOT):
    for fn in files:
        if not fn.endswith('.java'): continue
        p=os.path.join(root,fn)
        t=open(p,encoding='utf-8',errors='ignore').read()
        nt=rewrite(t)
        if nt!=t: open(p,'w',encoding='utf-8').write(nt); n+=1
print("rewrote",n,"files")

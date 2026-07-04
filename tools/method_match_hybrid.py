import os,re,json,collections
DEC="decompiled/sources"; REAL="gdxsrc/real"
targets=[x for x in json.load(open('map_out/game_obf_imports.json'))
         if x['obf'].startswith('com.badlogic.gdx') and x['real']]
def read(p): return open(p,encoding='utf-8',errors='ignore').read() if os.path.exists(p) else None
sig=re.compile(r'((?:public|protected|private|static|final|synchronized|native|\s)+)'
               r'([\w\[\]<>?.,\s]+?)\s+([A-Za-z_$][\w$]*)\s*\(([^;{}]*)\)\s*(?:throws [\w., ]+)?\{')
PRIM=set('boolean byte char short int long float double void'.split())
def kept(t):  # is a type token preserved under obfuscation?
    base=re.sub(r'<.*>','',t).replace('[]','').strip()
    return base in PRIM or base[:1].isupper() and len(base)>2  # java./gdx kept names
def normtype(t):
    t=re.sub(r'\s+','',t)
    base=re.sub(r'<.*>','',t)
    return base if kept(base.replace('[]','')) else '?'
def methods(text):
    out=[]
    for m in sig.finditer(text):
        rr=m.group(2).strip().split(); ret=rr[-1] if rr else 'void'; name=m.group(3); params=m.group(4)
        ps=[]; d=0;cur=''
        for c in params:
            if c in '<([':d+=1;cur+=c
            elif c in '>)]':d-=1;cur+=c
            elif c==',' and d==0: ps.append(cur);cur=''
            else:cur+=c
        if cur.strip():ps.append(cur)
        # param type = first token of each
        pts=tuple(normtype(p.strip().split()[0]) if p.strip() else '' for p in ps)
        i=m.end()-1;depth=0;j=i
        while j<len(text):
            if text[j]=='{':depth+=1
            elif text[j]=='}':
                depth-=1
                if depth==0:break
            j+=1
        if name in ('if','for','while','switch','catch','return','new','synchronized'): continue
        out.append({"name":name,"ret":normtype(ret),"pts":pts,"body":text[i+1:j]})
    return out

report={}; total=certain=0
for t in targets:
    ot,rt=read(os.path.join(DEC,t['obf'].replace('.','/')+'.java')),read(os.path.join(REAL,t['real'].replace('.','/')+'.java'))
    if not ot or not rt: continue
    om=methods(ot); rm=methods(rt)
    # index real methods by (ret,pts)
    ridx=collections.defaultdict(list)
    for r in rm: ridx[(r['ret'],r['pts'])].append(r['name'])
    renamed=[m for m in om if re.fullmatch(r'[a-z][0-9]?',m['name'])]
    pairs=[]
    # group obf by signature; match positionally within unique/equal-size groups
    obf_by_sig=collections.defaultdict(list)
    for m in renamed: obf_by_sig[(m['ret'],m['pts'])].append(m)
    for key,oms in obf_by_sig.items():
        reals=ridx.get(key,[])
        for i,m in enumerate(oms):
            guess=None;conf=False
            if len(reals)==1 and len(oms)==1:
                guess=reals[0];conf=True                 # unique signature -> certain
            elif len(reals)==len(oms) and len(reals)>0:
                guess=reals[i];conf=True                 # equal-size group, positional -> confident
            elif reals:
                guess=reals[min(i,len(reals)-1)];conf=False
            pairs.append({"obf":m['name'],"sig":f"{key[0]}({','.join(key[1])})",
                          "real":guess,"certain":conf})
            total+=1; certain+= 1 if conf else 0
    report[t['real']]={"obf":t['obf'],"certain":sum(1 for p in pairs if p['certain']),
                       "total":len(pairs),"methods":pairs}
json.dump(report,open('map_out/method_map_hybrid.json','w'),indent=1)
print(f"renamed methods : {total}")
print(f"CERTAIN/confident: {certain}  ({100*certain//total}%)")
print("\n=== Array ===")
for p in report['com.badlogic.gdx.utils.Array']['methods'][:16]:
    print(f"  .{p['obf']} : {p['sig']:22s} -> {p['real']}{'  ✓' if p['certain'] else '  ?'}")

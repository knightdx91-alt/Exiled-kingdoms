import os,re,collections,sys
DEC='decompiled/sources'; REAL='gdxsrc/real'
# (obf decompiled path) -> real FQN  for classes appearing in unmapped_gdx_calls
PAIRS={
 'com/badlogic/gdx/e':'com.badlogic.gdx.Game',
 'com/badlogic/gdx/i':'com.badlogic.gdx.InputMultiplexer',
 'com/badlogic/gdx/utils/a':'com.badlogic.gdx.utils.Array',
 'com/badlogic/gdx/math/Matrix4':'com.badlogic.gdx.math.Matrix4',
 'r/d':'com.badlogic.gdx.assets.AssetManager',
 'u/h':'com.badlogic.gdx.maps.MapProperties',
 'a0/q':'com.badlogic.gdx.math.Vector2',
 'a0/p':'com.badlogic.gdx.math.Rectangle',
}
PAIRS.update({
 'com/badlogic/gdx/math/a':'com.badlogic.gdx.math.Vector3',
 'com/badlogic/gdx/utils/l':'com.badlogic.gdx.utils.GdxNativesLoader',
 'i0/b':'com.badlogic.gdx.utils.viewport.Viewport',
})

sig=re.compile(r'((?:public|protected|private|static|final|synchronized|native|\s)+)([\w\[\]<>?.,\s]+?)\s+([A-Za-z_$][\w$]*)\s*\(([^;{}]*)\)\s*(?:throws [\w., ]+)?\{')
PRIM=set('boolean byte char short int long float double void'.split())
def normret(t):
    t=re.sub(r'\s','',t); base=re.sub(r'<.*>','',t).replace('[]','')
    return base if (base in PRIM or (base[:1].isupper() and len(base)>2)) else '?'
def argc(p):
    p=p.strip()
    if not p:return 0
    d=0;n=1
    for c in p:
        if c in '<([':d+=1
        elif c in '>)]':d-=1
        elif c==',' and d==0:n+=1
    return n
def methods(txt):
    out=[]
    for m in sig.finditer(txt):
        rr=m.group(2).strip().split(); ret=normret(rr[-1] if rr else 'void')
        name=m.group(3)
        if name in ('if','for','while','switch','catch','return','new','synchronized','instanceof','assert','throw','class','void','super','this','final','static','public','private','protected'):continue
        i=m.end()-1;d=0;j=i
        while j<len(txt):
            if txt[j]=='{':d+=1
            elif txt[j]=='}':
                d-=1
                if d==0:break
            j+=1
        out.append((name,ret,argc(m.group(4)),txt[i+1:j]))
    return out
def norm(b):
    t=[]
    for s in re.findall(r'"(?:\\.|[^"\\])*"',b):t.append('S'+s)
    for n in re.findall(r'\b\d+\b',re.sub(r'"[^"]*"',' ',b)):t.append('N'+n)
    for w in re.findall(r'[A-Za-z_]\w{2,}',b):t.append('I'+w)
    return collections.Counter(t)
def sim(a,b):
    if not a and not b:return 1.0
    return sum((a&b).values())/max(1,sum((a|b).values()))
rows=[]; stats=collections.Counter()
for opath,real in PAIRS.items():
    of=os.path.join(DEC,opath+'.java'); rf=os.path.join(REAL,real.replace('.','/')+'.java')
    if not os.path.exists(of) or not os.path.exists(rf):
        stats['skip']+=1; continue
    om=methods(open(of,errors='ignore').read()); rm=methods(open(rf,errors='ignore').read())
    # index real by (ret,argc)
    ridx=collections.defaultdict(list)
    for rn,rr,rac,rb in rm: ridx[(rr,rac)].append((rn,rb))
    used=set()
    obf_ren=[(n,r,a,b) for n,r,a,b in om if re.fullmatch(r'[a-z][0-9]?',n)]
    obf_by_sig=collections.defaultdict(list)
    for n,r,a,b in obf_ren: obf_by_sig[(r,a)].append((n,b))
    for key,oms in obf_by_sig.items():
        reals=ridx.get(key,[])
        if len(reals)==1 and len(oms)==1:                 # unique signature -> certain
            rows.append(f"{real}\t{oms[0][0]}\t{key[1]}\t{reals[0][0]}\t1"); stats['unique']+=1
        elif reals:                                       # disambiguate by body-sim
            avail=[r for r in reals if r[0] not in used]
            for on,ob in oms:
                nb=norm(ob); best=sorted(((sim(nb,norm(rb)),rn) for rn,rb in avail),reverse=True)
                if best and (len(best)==1 or best[0][0]-best[1][0]>=0.08):
                    used.add(best[0][1]); rows.append(f"{real}\t{on}\t{key[1]}\t{best[0][1]}\t1"); stats['body']+=1
                elif best:
                    rows.append(f"{real}\t{on}\t{key[1]}\t{best[0][1]}\t0"); stats['guess']+=1
print("stats:",dict(stats),"rows:",len(rows))
open('method_lookup_more.tsv','w').write('\n'.join(rows)+'\n')
import collections as C
bycls=C.Counter(r.split('\t')[0].split('.')[-1] for r in rows)
print("rows per class:",dict(bycls))

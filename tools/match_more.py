import os,re,json,collections,sys
DEC='decompiled/sources'; REAL='gdxsrc/real'
# obf-package-class (path) -> real FQN, for the top unmapped classes
TARGETS={
 'r/d':'com.badlogic.gdx.assets.AssetManager',
 'u/h':'com.badlogic.gdx.maps.MapProperties',
 'a0/q':'com.badlogic.gdx.math.Vector2',
 'a0/p':'com.badlogic.gdx.math.Rectangle',
}
# ensure real sources extracted (Matrix4, Viewport, InputMultiplexer, etc.)
import zipfile
sig=re.compile(r'((?:public|protected|private|static|final|synchronized|native|\s)+)([\w\[\]<>?.,\s]+?)\s+([A-Za-z_$][\w$]*)\s*\(([^;{}]*)\)\s*(?:throws [\w., ]+)?\{')
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
        rr=m.group(2).strip().split(); ret=rr[-1] if rr else 'void'
        name=m.group(3)
        if name in ('if','for','while','switch','catch','return','new'):continue
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
    inter=sum((a&b).values());uni=sum((a|b).values());return inter/uni if uni else 0
rows=[]
for opath,real in TARGETS.items():
    of=os.path.join(DEC,opath+'.java'); rf=os.path.join(REAL,real.replace('.','/')+'.java')
    if not os.path.exists(of) or not os.path.exists(rf):
        print("skip",opath,os.path.exists(of),os.path.exists(rf));continue
    om=methods(open(of,errors='ignore').read()); rm=methods(open(rf,errors='ignore').read())
    used=set()
    for nm,ret,ac,body in om:
        if not re.fullmatch(r'[a-z][0-9]?',nm):continue
        nb=norm(body)
        cands=[(sim(nb,norm(rb)),rn) for rn,rr,rac,rb in rm if rac==ac and rn not in used]
        cands.sort(reverse=True)
        if cands and cands[0][0]>=0.4 and (len(cands)==1 or cands[0][0]-cands[1][0]>=0.1):
            used.add(cands[0][1]); rows.append(f"{real}\t{nm}\t{ac}\t{cands[0][1]}\t1")
print(f"new lookup rows: {len(rows)}")
open('method_lookup_more.tsv','w').write('\n'.join(rows)+'\n')
for r in rows[:12]:print("  ",r.replace('\t',' '))

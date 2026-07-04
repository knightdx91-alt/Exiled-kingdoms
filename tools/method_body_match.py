import os,re,json,difflib,collections
DEC="decompiled/sources"; REAL="gdxsrc/real"
targets=[x for x in json.load(open('map_out/game_obf_imports.json'))
         if x['obf'].startswith('com.badlogic.gdx') and x['real']]

def read(path):
    return open(path,encoding='utf-8',errors='ignore').read() if os.path.exists(path) else None

# crude method extractor: (name, argcount, body-text). Handles nested braces.
sig=re.compile(r'(?:public|protected|private|static|final|synchronized|native|\s)+'
               r'([\w\[\]<>?.,\s]+?)\s+([A-Za-z_$][\w$]*)\s*\(([^;{}]*)\)\s*(?:throws [\w., ]+)?\{')
def argcount(p):
    p=p.strip()
    if not p: return 0
    d=0;n=1
    for c in p:
        if c in '<([':d+=1
        elif c in '>)]':d-=1
        elif c==',' and d==0:n+=1
    return n
def methods(text):
    out=[]
    for m in sig.finditer(text):
        name=m.group(2); ac=argcount(m.group(3)); i=m.end()-1
        depth=0;j=i
        while j<len(text):
            if text[j]=='{':depth+=1
            elif text[j]=='}':
                depth-=1
                if depth==0:break
            j+=1
        out.append((name,ac,text[i+1:j]))
    return out

# normalize a body into a bag of "stable" tokens: string/number literals,
# multi-char (kept-name) identifiers/method calls, and control keywords.
KEEP_KW=set('if else for while return new throw try catch instanceof switch case break continue null true false this super'.split())
def norm(body):
    toks=[]
    # string & char literals (very strong signal)
    for s in re.findall(r'"(?:\\.|[^"\\])*"',body): toks.append('S:'+s)
    for s in re.findall(r"'(?:\\.|[^'\\])'",body): toks.append('C:'+s)
    b=re.sub(r'"(?:\\.|[^"\\])*"',' ',body)
    for n in re.findall(r'\b\d+\.?\d*[fFlL]?\b',b): toks.append('N:'+n.rstrip('fFlL'))
    for w in re.findall(r'[A-Za-z_$][\w$]*',b):
        if w in KEEP_KW: toks.append('K:'+w)
        elif len(w)>2: toks.append('I:'+w)   # kept-name identifiers/calls
    return toks

def sim(a,b):
    if not a and not b: return 1.0
    ca,cb=collections.Counter(a),collections.Counter(b)
    inter=sum((ca&cb).values()); union=sum((ca|cb).values())
    return inter/union if union else 0.0

report={}; total=rec=hi=0
for t in targets:
    of=os.path.join(DEC,t['obf'].replace('.','/')+'.java')
    rf=os.path.join(REAL,t['real'].replace('.','/')+'.java')
    ot,rt=read(of),read(rf)
    if not ot or not rt: continue
    om=methods(ot); rm=methods(rt)
    renamed=[m for m in om if re.fullmatch(r'[a-z][0-9]?',m[0])]
    used_real=set(); pairs=[]
    for nm,ac,body in renamed:
        cands=[r for r in rm if r[1]==ac]
        nb=norm(body)
        scored=sorted(((sim(nb,norm(rb)),rn) for rn,rac,rb in cands if rn not in used_real),
                      reverse=True)
        best=scored[0] if scored else (0,None)
        second=scored[1][0] if len(scored)>1 else 0
        conf = best[0]>=0.5 and (best[0]-second)>=0.15
        if conf and best[1]: used_real.add(best[1])
        pairs.append({"obf":nm,"args":ac,"real":best[1],"score":round(best[0],2),
                      "margin":round(best[0]-second,2),"confident":bool(conf)})
        total+=1; rec+= 1 if best[1] else 0; hi+= 1 if conf else 0
    report[t['real']]={"obf":t['obf'],"methods":pairs}
json.dump(report,open('map_out/method_map_body.json','w'),indent=1)
print(f"renamed methods       : {total}")
print(f"matched (any)         : {rec}")
print(f"HIGH-CONFIDENCE       : {hi}")
print("\n=== Array (high-confidence only) ===")
for p in report['com.badlogic.gdx.utils.Array']['methods']:
    if p['confident']: print(f"  .{p['obf']}({p['args']}) -> {p['real']}  score={p['score']} margin={p['margin']}")

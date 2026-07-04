import os,re,json
DEC="decompiled/sources"; REAL="gdxsrc/real"
usedmap=json.load(open('map_out/game_obf_imports.json'))
# only com.badlogic.gdx.* classes with resolved real names
targets=[u for u in usedmap if u['obf'].startswith('com.badlogic.gdx') and u['real']]

sig_re=re.compile(r'(?:public|protected|private|final|\s)+\s+([A-Za-z0-9_\[\]<>?., ]+?)\s+([A-Za-z_$][\w$]*)\s*\(([^)]*)\)\s*\{')
def norm_params(p):
    p=p.strip()
    if not p: return 0
    # count top-level commas +1, ignoring generics commas
    depth=0;n=1
    for c in p:
        if c=='<':depth+=1
        elif c=='>':depth-=1
        elif c==','and depth==0:n+=1
    return n
def methods(path):
    out=[]
    if not os.path.exists(path): return out
    for m in sig_re.finditer(open(path,encoding='utf-8',errors='ignore').read()):
        ret,name,params=m.group(1).strip(),m.group(2),m.group(3)
        rt=ret.split()[-1] if ret.split() else ret
        out.append((rt,name,norm_params(params)))
    return out

report={}
for t in targets:
    obf=t['obf']; real=t['real']
    of=os.path.join(DEC,obf.replace('.','/')+'.java')
    rf=os.path.join(REAL,real.replace('.','/')+'.java')
    om=methods(of); rm=methods(rf)
    # obfuscated methods whose name is single-letter (renamed)
    renamed=[m for m in om if re.fullmatch(r'[a-z][0-9]?',m[1]) or re.fullmatch(r'[a-z]\d+',m[1])]
    pairs=[]
    used_real=[]
    for ret,nm,ac in renamed:
        cands=[r for r in rm if r[2]==ac and r[0].split('<')[0]==ret.split('<')[0]]
        # positional: pick first unused candidate
        pick=None
        for c in cands:
            if c not in used_real: pick=c; used_real.append(c); break
        pairs.append({"obf":nm,"args":ac,"guess":pick[1] if pick else None,
                      "ambiguous":len(cands)>1})
    report[real]={"obf_class":obf,"renamed_methods":len(renamed),
                  "recovered":sum(1 for p in pairs if p['guess']),
                  "ambiguous":sum(1 for p in pairs if p['ambiguous']),
                  "methods":pairs}
tot=sum(r['renamed_methods'] for r in report.values())
rec=sum(r['recovered'] for r in report.values())
amb=sum(r['ambiguous'] for r in report.values())
print(f"libGDX classes matched      : {len(report)}")
print(f"renamed methods total       : {tot}")
print(f"  recovered (best-effort)   : {rec}")
print(f"  flagged ambiguous         : {amb}")
json.dump(report,open('map_out/method_map.json','w'),indent=1)
# show Array as sample
print("\n=== Array sample ===")
for p in report.get('com.badlogic.gdx.utils.Array',{}).get('methods',[])[:12]:
    print(f"  .{p['obf']}({p['args']} args) -> {p['guess']}{'  [AMBIG]' if p['ambiguous'] else ''}")

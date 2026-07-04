import os,re,sys,json
ROOT=sys.argv[1]
def find_block(text,start):
    d=0
    for j in range(start,len(text)):
        if text[j]=='{':d+=1
        elif text[j]=='}':
            d-=1
            if d==0:return j
    return -1
hdr=re.compile(r'(public\s+)?static\s+final\s+class\s+([A-Za-z_]\w*)\s*\{')
def is_failed_enum(body,name):
    return ('Failed to restore enum' in body or f'Enum.valueOf({name}.class' in body)
def cname(c): return c if re.fullmatch(r'[A-Za-z_]\w*',c) else '_'+re.sub(r'\W','_',c)

field_rename={}   # global: obfField -> constName  (enum constant fields only)
def process(text):
    changed=False; i=0
    while True:
        m=hdr.search(text,i)
        if not m: break
        ob=m.end()-1; close=find_block(text,ob)
        if close<0: break
        body=text[ob+1:close]; name=m.group(2)
        if is_failed_enum(body,name):
            ord2name={int(o):n for n,o in re.findall(rf'new\s+{name}\s*\(\s*"([^"]*)"\s*,\s*(\d+)\)',body)}
            if ord2name:
                consts=[ord2name[k] for k in sorted(ord2name)]
                # trace public constant fields -> name
                # local = new X("NAME",ORD);  FIELD = local;   OR  FIELD = new X("NAME",ORD);
                loc2name={}
                for lv,nm in re.findall(rf'(\w+)\s*=\s*new\s+{name}\s*\(\s*"([^"]*)"',body):
                    loc2name[lv]=nm
                for fld,rhs in re.findall(r'(\w+)\s*=\s*(\w+)\s*;',body):
                    if rhs in loc2name: field_rename[fld]=cname(loc2name[rhs])
                for fld,nm in re.findall(rf'(\w+)\s*=\s*new\s+{name}\s*\(\s*"([^"]*)"',body):
                    field_rename[fld]=cname(nm)
                # preserved real methods
                meths=[]
                for mm in re.finditer(r'(public|protected|private)[^\n;{}]*\b([A-Za-z_]\w*)\s*\([^)]*\)\s*\{',body):
                    if mm.group(2) in ('valueOf','values',name): continue
                    e=find_block(body,mm.end()-1); meths.append(body[mm.start():e+1])
                vis='public ' if m.group(1) else ''
                enum=f'{vis}enum {name} {{\n        '+', '.join(cname(c) for c in consts)+';\n'
                for x in meths: enum+='\n'+x+'\n'
                enum+='    }'
                text=text[:m.start()]+enum+text[close+1:]; changed=True
                i=m.start()+len(enum); continue
        i=close+1
    return text,changed

files=[]
for root,_,fs in os.walk(ROOT):
    for fn in fs:
        if fn.endswith('.java'): files.append(os.path.join(root,fn))
# pass 1: rebuild enums, collect field_rename
for p in files:
    t=open(p,encoding='utf-8',errors='ignore').read()
    if 'Failed to restore enum' not in t and 'Enum.valueOf' not in t: continue
    nt,ch=process(t)
    if ch: open(p,'w').write(nt)
# pass 2: apply field_rename globally (only exact enum-constant field tokens, qualified by '.')
print("enum-constant field renames:",len(field_rename))
collisions=[k for k in field_rename if re.fullmatch(r'f\d{3,4}[a-z]',k) is None]
for p in files:
    t=open(p,encoding='utf-8',errors='ignore').read(); o=t
    for fld,nm in field_rename.items():
        if not re.fullmatch(r"f\d{3,4}[a-z]",fld): continue
        t=re.sub(rf'\.{fld}\b','.'+nm,t)
    if t!=o: open(p,'w').write(t)
json.dump(field_rename,open('/home/user/Exiled-kingdoms/deobf/enum_field_rename.json','w'),indent=1)
print("sample:",dict(list(field_rename.items())[:8]))

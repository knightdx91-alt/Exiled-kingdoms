import os,re,json,collections
SRC="decompiled/sources"
hint_re=re.compile(r'compiled from:\s*([A-Za-z0-9_$]+)\.java')

# Known third-party library class-name signatures (real names -> library)
LIBS={
 'libgdx':set('''Game Screen Input InputAdapter InputProcessor InputMultiplexer Files Audio Graphics
   Application ApplicationListener Gdx Net Preferences'''.split()),
}
# Heuristic: classify by real simple-name and obf package
def classify(realname, obfpkg):
    r=realname
    if obfpkg.startswith('com.badlogic.gdx'): return 'libgdx'
    if r.startswith('PurchaseManager') or r in ('Transaction','OfferType','GdxPayException','PurchaseObserver'): return 'gdx-pay'
    if r in ('RayHandler','PointLight','Light','ConeLight','DirectionalLight','PositionalLight','ChainLight'): return 'box2dlights'
    if r in ('Rectangle','Vector2','Vector3','Matrix4','Polygon','Circle','Affine2','GridPoint2'): return 'libgdx-math(relocated)'
    if r in ('Music','Sound','AssetManager','MapProperties','FileHandle','Array','ObjectMap'): return 'libgdx(relocated)'
    if r in ('Constructor','Field','Method','ReflectionException','ClassReflection','ArrayReflection'): return 'libgdx-reflect(relocated)'
    if r in ('SafeIterableMap','FastSafeIterableMap','Lifecycle','LiveData'): return 'androidx'
    # game-ish: contains Game/Screen/Window/Dialog/HUD/Map/NPC/Menu/Button/Renderer/Text/Analytics/Resolver/Backup
    if re.search(r'(Screen|Window|Dialog|HUD|Renderer|Menu|Button|FloatingText|Backup|Analytics|Resolver|Conversation|SpawnData|Train)',r) or r.startswith('Game'):
        return 'GAME-CODE'
    return 'other'

rows=[]
for root,_,files in os.walk(SRC):
    for fn in files:
        if not fn.endswith('.java'): continue
        p=os.path.join(root,fn); stem=fn[:-5]
        rel=os.path.relpath(p,SRC)[:-5].replace('/','.')
        pkg='.'.join(rel.split('.')[:-1])
        with open(p,encoding='utf-8',errors='ignore') as f: head=f.read(3000)
        m=hint_re.search(head)
        real=m.group(1) if m else stem
        if real==stem: continue
        rows.append((rel,real,pkg,classify(real,pkg)))

cnt=collections.Counter(r[3] for r in rows)
print("=== hinted obfuscated classes by category ===")
for k,v in cnt.most_common(): print(f"  {k:28s} {v}")
game=[r for r in rows if r[3]=='GAME-CODE']
print(f"\n=== recoverable GAME classes hidden in obf packages: {len(game)} ===")
for rel,real,pkg,_ in sorted(game,key=lambda x:x[1])[:60]:
    print(f"  {rel:12s} -> {real}")
json.dump([{'obf':r[0],'real':r[1],'category':r[3]} for r in rows],
          open('map_out/full_classified_map.json','w'),indent=1)

#!/usr/bin/env python3
"""
Multiplayer port: the MP mod's engine (net.fdgames.ek.android.lan) onto our 4.2.2 base.
Spec: deobf/MULTIPLAYER_PORT_SPEC.md. Run from inside the decoded tree (build_mod_4_2_2.sh).

Part A (this file, first section): import the engine smali vendored in
recovered_mods/multiplayer/lan_smali, rewriting every reference from the mod's 1218
names to our 1207 names (spec §2). Part B: hooks into our game classes (spec §3).
"""
import os, re, shutil, sys

w = "."
REPO = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(REPO, 'recovered_mods/multiplayer/lan_smali')
DST = f'{w}/smali'

# --- spec §2: their member ref -> our member ref (full "Lclass;->name(sig)ret") -------------
REMAP = {
    'Lcom/badlogic/gdx/utils/a;->a(Ljava/lang/Object;)V': 'Lcom/badlogic/gdx/utils/a;->add(Ljava/lang/Object;)V',
    'Lnet/fdgames/GameEntities/Character;->r1(Ljava/lang/String;)V': 'Lnet/fdgames/GameEntities/Character;->b(Ljava/lang/String;)V',
    'Lnet/fdgames/GameEntities/Character;->v(Ljava/lang/String;ILjava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V':
        'Lnet/fdgames/GameEntities/Character;->a(Ljava/lang/String;ILjava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V',
    'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->s()V': 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterInventory;->u()V',
    'Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->e()V': 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->d()V',
    'Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->f(I)V': 'Lnet/fdgames/GameEntities/CharacterSheet/CharacterResistances;->a(I)V',
    'Lnet/fdgames/GameEntities/Final/NPC;->B1()V': 'Lnet/fdgames/GameEntities/Final/NPC;->y0()V',
    'Lnet/fdgames/GameEntities/Final/NPC;->L1()I': 'Lnet/fdgames/GameEntities/Final/NPC;->H0()I',
    'Lnet/fdgames/GameEntities/Final/NPC;->M1()Z': 'Lnet/fdgames/GameEntities/Final/NPC;->I0()Z',
    'Lnet/fdgames/GameEntities/Final/NPC;->U1(I)V': 'Lnet/fdgames/GameEntities/Final/NPC;->v(I)V',
    'Lnet/fdgames/GameEntities/Final/NPC;->k0()Z': 'Lnet/fdgames/GameEntities/Final/NPC;->P()Z',
    'Lnet/fdgames/GameEntities/Final/NPC;->v0()V': 'Lnet/fdgames/GameEntities/Final/NPC;->W()V',
    'Lnet/fdgames/GameEntities/GameObject;->q()I': 'Lnet/fdgames/GameEntities/GameObject;->m()I',
    'Lnet/fdgames/GameEntities/GameObject;->s()Z': 'Lnet/fdgames/GameEntities/GameObject;->i()Z',
    'Lnet/fdgames/GameEntities/Helpers/Damage;->b(Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;':
        'Lnet/fdgames/GameEntities/Helpers/Damage;->a(Ljava/lang/String;)Lnet/fdgames/GameEntities/Helpers/Damage$DamageType;',
    'Lnet/fdgames/GameEntities/Helpers/SkillSet;->n()V': 'Lnet/fdgames/GameEntities/Helpers/SkillSet;->f()V',
    'Lnet/fdgames/GameEntities/MapActor;->d0()Lnet/fdgames/GameEntities/MapActor$ActorState;':
        'Lnet/fdgames/GameEntities/MapActor;->J()Lnet/fdgames/GameEntities/MapActor$ActorState;',
    'Lnet/fdgames/GameEntities/MapActor;->q0(Lnet/fdgames/GameEntities/MapActor$ActorState;)V':
        'Lnet/fdgames/GameEntities/MapActor;->a(Lnet/fdgames/GameEntities/MapActor$ActorState;)V',
    'Lnet/fdgames/GameEntities/MapActor;->v0()V': 'Lnet/fdgames/GameEntities/MapActor;->W()V',
    'Lnet/fdgames/GameLevel/GameLevel;->b()F': 'Lnet/fdgames/GameLevel/GameLevel;->c()F',
    'Lnet/fdgames/GameLevel/GameLevel;->g(I)Lnet/fdgames/GameEntities/MapActor;': 'Lnet/fdgames/GameLevel/GameLevel;->b(I)Lnet/fdgames/GameEntities/MapActor;',
    'Lnet/fdgames/GameLevel/GameLevel;->h(I)Lnet/fdgames/GameEntities/MapObject;': 'Lnet/fdgames/GameLevel/GameLevel;->c(I)Lnet/fdgames/GameEntities/MapObject;',
    'Lnet/fdgames/GameLevel/GameLevel;->j(Ljava/lang/String;)Lnet/fdgames/GameEntities/Final/NPC;':
        'Lnet/fdgames/GameLevel/GameLevel;->b(Ljava/lang/String;)Lnet/fdgames/GameEntities/Final/NPC;',
    'Lnet/fdgames/GameLevel/GameLevelData;->e(Lnet/fdgames/GameEntities/Final/MapEffectEntity;)V':
        'Lnet/fdgames/GameLevel/GameLevelData;->a(Lnet/fdgames/GameEntities/Final/MapEffectEntity;)V',
    'Lnet/fdgames/GameLevel/GameLevelData;->o()Lnet/fdgames/GameLevel/GameLevelData;': 'Lnet/fdgames/GameLevel/GameLevelData;->s()Lnet/fdgames/GameLevel/GameLevelData;',
    'Lnet/fdgames/GameWorld/GameData;->v()Lnet/fdgames/GameWorld/GameData;': 'Lnet/fdgames/GameWorld/GameData;->O()Lnet/fdgames/GameWorld/GameData;',
    'Lnet/fdgames/GameWorld/Party;->f()Lnet/fdgames/GameEntities/Final/NPC;': 'Lnet/fdgames/GameWorld/Party;->c()Lnet/fdgames/GameEntities/Final/NPC;',
    'Lnet/fdgames/GameWorld/Party;->g()Ljava/lang/String;': 'Lnet/fdgames/GameWorld/Party;->d()Ljava/lang/String;',
    'Lnet/fdgames/GameWorld/Party;->i()Lnet/fdgames/GameEntities/Final/NPC;': 'Lnet/fdgames/GameWorld/Party;->g()Lnet/fdgames/GameEntities/Final/NPC;',
    'Lnet/fdgames/GameWorld/WorldFactions;->i(Ljava/lang/String;)[I': 'Lnet/fdgames/GameWorld/WorldFactions;->c(Ljava/lang/String;)[I',
    'Lnet/fdgames/Helpers/GameString;->b(Ljava/lang/String;Z)Ljava/lang/String;': 'Lnet/fdgames/Helpers/GameString;->a(Ljava/lang/String;Z)Ljava/lang/String;',
    'Lnet/fdgames/Rules/Rules;->f(I)Lnet/fdgames/Rules/Item;': 'Lnet/fdgames/Rules/Rules;->c(I)Lnet/fdgames/Rules/Item;',
    'Lnet/fdgames/Rules/Rules;->i(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;': 'Lnet/fdgames/Rules/Rules;->d(Ljava/lang/String;)Lnet/fdgames/Rules/Spawn;',
    'Lnet/fdgames/Rules/Spawn;->b(Ljava/lang/String;)V': 'Lnet/fdgames/Rules/Spawn;->a(Ljava/lang/String;)V',
    'Lnet/fdgames/assets/GameAssets;->o(Ljava/lang/String;)V': 'Lnet/fdgames/assets/GameAssets;->i(Ljava/lang/String;)V',
    'Lnet/fdgames/ek/ExiledKingdoms;->f()Lnet/fdgames/ek/IPlatformResolver;': 'Lnet/fdgames/ek/ExiledKingdoms;->e()Lnet/fdgames/ek/IPlatformResolver;',
    'Lnet/fdgames/ek/android/MainActivity;->u()V': 'Lnet/fdgames/ek/android/MainActivity;->ekOpenLobby()V',
    'Ly0/b;->r(IIII)I': 'Le/a/c/b;->c(IIII)I',
    # enum constants are letters too: every enum the engine or hooks touch (ActorState, Facing,
    # Gender, DamageType, CharacterClass, CharacterRace, EffectType, NPCState) was compared name
    # by name in <clinit> -- identical in both versions, so no enum remap is needed.
    'Ly0/b;->A(ILnet/fdgames/TiledMap/Objects/Coords;)Lnet/fdgames/TiledMap/Objects/Coords;':
        'Lnet/fdgames/ek/android/lan/LanGameBridge;->ekMapA(ILnet/fdgames/TiledMap/Objects/Coords;)Lnet/fdgames/TiledMap/Objects/Coords;',
}
# Forwarders added to OUR classes so the engine's call sites stay as they are (spec §2).
#   GameVariables.e(I,String)V -> b(String,I)Z   (argument order swapped; no e() in ours)
#   MapEffectEntity(F,I,I,I,String) -> (I,I,I,String,Z,String,F) = (x,y,caster,"",true,id,delay)

# --- import ---------------------------------------------------------------------------------
n_files = n_refs = 0
for dp, _, fs in os.walk(SRC):
    for fn in fs:
        if not fn.endswith('.smali'):
            continue
        sp = os.path.join(dp, fn)
        rel = os.path.relpath(sp, SRC)
        s = open(sp, encoding='utf-8').read()
        for old, new in REMAP.items():
            c = s.count(old)
            if c:
                s = s.replace(old, new)
                n_refs += c
        out = os.path.join(DST, rel)
        assert not os.path.exists(out), f"engine class already present: {rel}"
        os.makedirs(os.path.dirname(out), exist_ok=True)
        open(out, 'w', encoding='utf-8').write(s)
        n_files += 1
assert n_files == 29, n_files
left = []
for dp, _, fs in os.walk(os.path.join(DST, 'net/fdgames/ek/android/lan')):
    for fn in fs:
        t = open(os.path.join(dp, fn), encoding='utf-8').read()
        left += [x for x in REMAP if x in t]
assert not left, f"unmapped refs remain: {left}"
print(f"imported LAN engine: {n_files} classes, {n_refs} references remapped to 1207 names")

# --- reflection: the engine also calls game methods BY NAME (strings), which the REMAP above
# can't see. Rewrite the const-string that feeds the method-name argument of each reflective
# helper call, keyed by (helper, arity, their name) -> our name (spec §2b). Register-local:
# only the literal actually passed to that call is changed, so crossing names (resistances
# c->b while d->c) can't chain.
REFL = {
    ('invokeStatic', 2, 'v'): 'O',      # GameData.v()  -> O()       (instance getter)
    ('invokeStatic', 4, 'h'): 'g',      # Areas.h(String)Coords -> g(String)
    ('invokeStatic', 4, 'j'): 'j',      # Areas.j(String)Z (isCity) -- same name
    ('invokeObject', 2, 'd0'): 'J',     # MapActor.d0() state   -> J()
    ('invokeObject', 2, 'v0'): 'W',     # NPC.v0() sprite build  -> W()
    ('invokeObject', 2, 'c'): 'b',      # CharacterResistances.c()Z -> b()Z
    ('invokeObject', 2, 'd'): 'c',      # CharacterResistances.d()Z -> c()Z
    ('invokeInt', 2, 'f'): 'e',         # CharacterStats.f() level -> e()
    ('invokeInt', 3, 'g'): 'c',         # SkillSet.g(String) rank -> c(String)
    ('invokeInt', 3, 'q'): 'i',         # SkillSet.q(String)     -> i(String)
    ('invokeInt', 3, 'j'): 'd',         # SkillSet.j(String) +1  -> d(String)
    ('invokeInt', 3, 'b'): 'b',         # GameVariables.b(String) -- same name
    ('invokeString', 2, 'getName'): 'getName',
    ('invokeString', 2, 'toString'): 'toString',
}
HELPERS = {h for h, _, _ in REFL}


def _args(spec):
    spec = spec.strip()
    if '..' in spec:
        a, b = [x.strip() for x in spec.split('..')]
        return [f'{a[0]}{i}' for i in range(int(a[1:]), int(b[1:]) + 1)]
    return [x.strip() for x in spec.split(',') if x.strip()]


def remap_reflection(path):
    t = open(path, encoding='utf-8').read()
    lines = t.split('\n')
    last = {}          # register -> index of the const-string line currently in it
    used = {}          # const-string line index -> new value
    n = 0
    for i, ln in enumerate(lines):
        st = ln.strip()
        if st.startswith('.method'):
            last = {}
            continue
        m = re.match(r'const-string(?:/jumbo)? ([vp]\d+), "(.*)"$', st)
        if m:
            last[m.group(1)] = i
            continue
        m = re.match(r'invoke-static(?:/range)? \{([^}]*)\}, Lnet/fdgames/ek/android/lan/LanGameBridge;->(\w+)\(', st)
        if m and m.group(2) in HELPERS:
            args = _args(m.group(1))
            if len(args) >= 2 and args[1] in last:
                j = last[args[1]]
                old = re.search(r'"(.*)"$', lines[j].strip()).group(1)
                key = (m.group(2), len(args), old)
                assert key in REFL, f"unmapped reflective call {key} at line {i}"
                new = REFL[key]
                assert used.get(j, new) == new, f"const-string at {j} feeds conflicting calls"
                used[j] = new
            elif len(args) >= 2:
                raise AssertionError(f"reflective call without a literal name at line {i}: {st}")
            continue
        m = re.match(r'(?:move-result(?:-object|-wide)?|const\S*|new-instance|[is]get\S*|move\S*|check-cast|new-array) ([vp]\d+)\b', st)
        if m:
            last.pop(m.group(1), None)
    for j, new in used.items():
        old = re.search(r'"(.*)"$', lines[j].strip()).group(1)
        if old != new:
            lines[j] = lines[j][:lines[j].rindex('"' + old + '"')] + '"' + new + '"'
            n += 1
    # the mod's string-container constant (city-group prefix) read reflectively: inline it
    t2 = '\n'.join(lines)
    t2, k = re.subn(r'invoke-static \{[vp]\d+, [vp]\d+\}, Lnet/fdgames/ek/android/lan/LanGameBridge;->getStaticFieldValue\('
                    r'Ljava/lang/String;Ljava/lang/String;\)Ljava/lang/Object;\n(\s*\n)*(\s*)move-result-object ([vp]\d+)',
                    lambda a: f'const-string {a.group(3)}, "IM"', t2)
    open(path, 'w', encoding='utf-8').write(t2)
    return n, len(used), k


rn, ru, rk = remap_reflection(f'{DST}/net/fdgames/ek/android/lan/LanGameBridge.smali')
assert rk == 2, rk
print(f"reflection: {ru} reflective call names checked, {rn} renamed to 1207 names; string-container 'IM' inlined x{rk}")

# English: the lobby and session texts already fall back to English (lanString()/pt()), but
# the in-chat location list and one battle-log line are Portuguese-only in the MP mod.
EN = {
    'LanGameBridgeLocationButtonListener.smali': [
        ('"\\n[Localizacao]\\n"', '"\\n[Locations]\\n"'), ('"Jogador"', '"Player"'), ('" Nv."', '" Lv."'),
        ('"  Mapa: "', '"  Map: "'), ('"  Estado: "', '"  State: "'), ('"Em Combate"', '"In combat"'),
        ('"Livre"', '"Free"'), ('"  Companheiro: "', '"  Companion: "'),
        ('"Nenhum jogador conectado.\\n"', '"No players connected.\\n"')],
    # peers are NPCs built from a bestiary row (class/race/gender/level/weapon then come from the
    # peer's state). The MP mod used "janod" for wizards; in our build that row is the Janod
    # companion (companionSpawn, homecoming, gear hooks key on it), so use a plain human caster.
    'LanGameBridge.smali': [('" foi derrotado!"', '" was defeated!"'), ('"janod"', '"blue_wizard"')],
}
for fn, pairs in EN.items():
    p2 = f'{DST}/net/fdgames/ek/android/lan/{fn}'
    t = open(p2, encoding='utf-8').read()
    for a, b in pairs:
        assert a in t, f"{fn}: {a} not found"
        t = re.sub(r'(const-string(?:/jumbo)? [vp]\d+, )' + re.escape(a), lambda m: m.group(1) + b, t)
    open(p2, 'w', encoding='utf-8').write(t)
print("translated the Portuguese-only chat/location strings to English")

# GameMap.A(I,Coords) is not in our base: give the engine its own copy.
p = f'{DST}/net/fdgames/ek/android/lan/LanGameBridge.smali'
s = open(p, encoding='utf-8').read()
tail = s.rindex('.end method') + len('.end method')
s = s[:tail] + '''

.method public static ekMapA(ILnet/fdgames/TiledMap/Objects/Coords;)Lnet/fdgames/TiledMap/Objects/Coords;
    .locals 2

    iget v0, p1, Lnet/fdgames/TiledMap/Objects/Coords;->x:I

    iget p1, p1, Lnet/fdgames/TiledMap/Objects/Coords;->y:I

    add-int v1, v0, p1

    sub-int/2addr p1, v0

    div-int/lit8 p1, p1, 0x2

    new-instance v0, Lnet/fdgames/TiledMap/Objects/Coords;

    div-int/lit8 p0, p0, 0x2

    sub-int/2addr v1, p0

    invoke-direct {v0, v1, p1}, Lnet/fdgames/TiledMap/Objects/Coords;-><init>(II)V

    return-object v0
.end method
''' + s[tail:]
open(p, 'w', encoding='utf-8').write(s)


def add_method(path, text, what):
    t = open(path, encoding='utf-8').read()
    tail = t.rindex('.end method') + len('.end method')
    open(path, 'w', encoding='utf-8').write(t[:tail] + '\n\n' + text.strip('\n') + '\n' + t[tail:])
    print(f"added {what}")


# The mod's own engine calls LanSessionManager.logLanError(String) on one error path, but only the
# (String,Throwable) overload exists -- a NoSuchMethodError waiting to happen (in the mod too).
add_method(f'{DST}/net/fdgames/ek/android/lan/LanSessionManager.smali', '''
.method private logLanError(Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lnet/fdgames/ek/android/lan/LanSessionManager;->logLanError(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method''', "LanSessionManager.logLanError(String) (missing in the mod) -> (String,null)")

add_method(f'{DST}/net/fdgames/GameWorld/GameVariables.smali', '''
.method public e(ILjava/lang/String;)V
    .locals 0

    invoke-virtual {p0, p2, p1}, Lnet/fdgames/GameWorld/GameVariables;->b(Ljava/lang/String;I)Z

    return-void
.end method''', "GameVariables.e(I,String) -> b(String,I) forwarder")

add_method(f'{DST}/net/fdgames/GameEntities/Final/MapEffectEntity.smali', '''
.method public constructor <init>(FIIILjava/lang/String;)V
    .locals 8

    move-object v0, p0

    move v1, p2

    move v2, p3

    move v3, p4

    const-string v4, ""

    const/4 v5, 0x1

    move-object v6, p5

    move v7, p1

    invoke-direct/range {v0 .. v7}, Lnet/fdgames/GameEntities/Final/MapEffectEntity;-><init>(IIILjava/lang/String;ZLjava/lang/String;F)V

    return-void
.end method''', "MapEffectEntity(delay,x,y,caster,id) constructor adapter")

# MainActivity.ekOpenLobby(): their MainActivity.u() -- start the lobby screen.
add_method(f'{DST}/net/fdgames/ek/android/MainActivity.smali', '''
.method public final ekOpenLobby()V
    .locals 2

    new-instance v0, Landroid/content/Intent;

    const-class v1, Lnet/fdgames/ek/android/lan/LanLobbyActivity;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V

    return-void
.end method''', "MainActivity.ekOpenLobby()")

# NPC.lanPeerVisual (their field; hooks in part B read/write it)
p = f'{DST}/net/fdgames/GameEntities/Final/NPC.smali'
s = open(p, encoding='utf-8').read()
m = re.search(r'^\.field [^\n]*\bcompanionSpawn:Z\n', s, re.M)
assert m, "NPC.companionSpawn field not found"
s = s[:m.end()] + '\n.field public lanPeerVisual:Z\n' + s[m.end():]
open(p, 'w', encoding='utf-8').write(s)
print("added NPC.lanPeerVisual")

# The engine reads these directly; in our base they are private/protected (checked with an
# access scan over every member the engine touches). Widening to public only relaxes access.
WIDEN = [('net/fdgames/GameEntities/Final/NPC', 'ai_disabled:Z'),
         ('net/fdgames/GameEntities/GameObject', 'worldfactions:[I'),
         ('net/fdgames/GameEntities/MapActor', 'actionStartTime:F'),
         ('net/fdgames/GameEntities/MapActor', 'destination:Lnet/fdgames/TiledMap/Objects/Coords;')]
for cls, fld in WIDEN:
    p = f'{DST}/{cls}.smali'
    s = open(p, encoding='utf-8').read()
    m = re.search(r'^\.field (private|protected) ((?:[a-z]+ )*)' + re.escape(fld) + r'$', s, re.M)
    assert m, f"{cls}.{fld} not found as private/protected"
    s = s[:m.start()] + f'.field public {m.group(2)}{fld}' + s[m.end():]
    open(p, 'w', encoding='utf-8').write(s)
print("widened 4 fields to public for the engine")
print("part A done")

# =============================================================================================
# Part B -- glue class + hooks into our game classes (spec §3)
# =============================================================================================
import subprocess, tempfile

LAN = 'Lnet/fdgames/ek/android/lan/LanGameBridge;'
EK = 'Lnet/fdgames/ek/android/lan/EkMp;'
NPC_ = 'Lnet/fdgames/GameEntities/Final/NPC;'
CHAR = 'Lnet/fdgames/GameEntities/Character;'
MA = 'Lnet/fdgames/GameEntities/MapActor;'

# ---- B0: compile the Java glue (tools/mp_java) against our-name stubs; splice only EkMp* ----
LIB = os.environ.get('EK_LIB', '/tmp')
R8 = os.path.join(LIB, 'r8.jar')
BAK = os.path.join(LIB, 'baksmali-2.5.2.jar')
assert os.path.exists(R8), f"{R8} missing: D8 is needed to build the multiplayer glue (see CONTINUE_HERE)"
JROOT = os.path.join(REPO, 'tools/mp_java')
tmp = tempfile.mkdtemp()
def run(cmd):
    r = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    out = '\n'.join(l for l in r.stdout.split('\n') if 'JAVA_TOOL_OPTIONS' not in l)
    assert r.returncode == 0, f"{cmd[0]} failed:\n{out}"
stubs = [os.path.join(dp, f) for dp, _, fs in os.walk(os.path.join(JROOT, 'stubs')) for f in fs if f.endswith('.java')]
srcs = [os.path.join(dp, f) for dp, _, fs in os.walk(os.path.join(JROOT, 'src')) for f in fs if f.endswith('.java')]
os.makedirs(f'{tmp}/stubs'); os.makedirs(f'{tmp}/out'); os.makedirs(f'{tmp}/dex')
run(['javac', '--release', '8', '-nowarn', '-d', f'{tmp}/stubs'] + stubs)
run(['javac', '--release', '8', '-nowarn', '-cp', f'{tmp}/stubs', '-d', f'{tmp}/out'] + srcs)
classes = [os.path.join(dp, f) for dp, _, fs in os.walk(f'{tmp}/out') for f in fs if f.endswith('.class')]
run(['java', '-cp', R8, 'com.android.tools.r8.D8', '--min-api', '15', '--output', f'{tmp}/dex',
     '--classpath', f'{tmp}/stubs'] + classes)
run(['java', '-jar', BAK, 'd', '-l', f'{tmp}/dex/classes.dex', '-o', f'{tmp}/smali'])
n = 0
for dp, _, fs in os.walk(f'{tmp}/smali'):
    for f in fs:
        rel = os.path.relpath(os.path.join(dp, f), f'{tmp}/smali')
        assert rel.startswith('net/fdgames/ek/android/lan/'), f"glue produced a class outside the lan package: {rel}"
        out = os.path.join(DST, rel)
        assert not os.path.exists(out), rel
        shutil.copy(os.path.join(dp, f), out)
        n += 1
print(f"B0: compiled glue EkMp ({n} classes)")


def method_span(t, header_tail):
    """(start, end) of the method whose .method line ends with header_tail (name+sig)."""
    ms = [m for m in re.finditer(r'^\.method [^\n]*$', t, re.M) if m.group(0).endswith(' ' + header_tail)]
    assert len(ms) == 1, f"method {header_tail}: {len(ms)} matches"
    st = ms[0].start()
    return st, t.index('.end method', st) + len('.end method')


def edit_method(cls, header_tail, fn, what):
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    a, b = method_span(t, header_tail)
    body = fn(t[a:b])
    assert body != t[a:b], f"{what}: no change"
    open(path, 'w', encoding='utf-8').write(t[:a] + body + t[b:])
    print(f"B: {what}")


def sub1(pattern, repl, text, what, flags=0):
    new, k = re.subn(pattern, repl, text, count=0, flags=flags)
    assert k == 1, f"{what}: anchor matched {k}x"
    return new


def wrap_method(cls, header_tail, new_name, private, wrapper, what):
    """Rename the original method and add `wrapper` (full .method text) under the old name."""
    path = f'{DST}/{cls}.smali'
    t = open(path, encoding='utf-8').read()
    a, b = method_span(t, header_tail)
    hdr_end = t.index('\n', a)
    hdr = t[a:hdr_end]
    name = header_tail[:header_tail.index('(')]
    sig = header_tail[len(name):]
    mods = hdr.split()[1:-1]
    if private:
        mods = ['private' if x in ('public', 'protected') else x for x in mods]
    t = t[:a] + '.method ' + ' '.join(mods + [new_name + sig]) + t[hdr_end:]
    b = t.index('.end method', a) + len('.end method')
    t = t[:b] + '\n\n' + wrapper.strip('\n') + '\n' + t[b:]
    open(path, 'w', encoding='utf-8').write(t)
    print(f"B: {what}")


# ---- B1: GameScreen (e/a/b/b.a(F)) -> LanGameBridge.tick() every frame, right after the
#          level-transition check and before the player fetch (where the MP mod ticks). ----
edit_method('e/a/b/b', 'a(F)V', lambda m: sub1(
    r'(    :cond_\w+\n)(    invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevel;->h\(\)Lnet/fdgames/GameEntities/Final/Player;\n\n    move-result-object v1\n\n    const/4 v3, 0x0\n)',
    r'\1    invoke-static {}, ' + LAN + r'->tick()V' + '\n\n' + r'\2', m, 'tick anchor'),
    "GameScreen.a(F): LanGameBridge.tick() each frame")

# ---- B2: MessageRouter.a(String,I,I,String,F,DamageData) -> mirrorCombatAction at exit ----
MR = 'Lnet/fdgames/GameWorld/MessageRouter;'
MRSIG = '(Ljava/lang/String;IILjava/lang/String;FLnet/fdgames/GameEntities/Helpers/DamageData;)V'
wrap_method('net/fdgames/GameWorld/MessageRouter', 'a' + MRSIG, 'ekMpRoute', True, f"""
.method public static a{MRSIG}
    .locals 0

    invoke-static/range {{p0 .. p5}}, {MR}->ekMpRoute{MRSIG}

    invoke-static/range {{p0 .. p4}}, {LAN}->mirrorCombatAction(Ljava/lang/String;IILjava/lang/String;F)V

    return-void
.end method""", "MessageRouter.a(...): mirrorCombatAction after routing")

# ---- B3: Character.a(String,I,String,DamageData) (their v) -> publishPeerDamageIfNeeded ----
CSIG = '(Ljava/lang/String;ILjava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V'
wrap_method('net/fdgames/GameEntities/Character', 'a' + CSIG, 'ekMpMsg', True, f"""
.method public a{CSIG}
    .locals 0

    invoke-direct {{p0, p1, p2, p3, p4}}, {CHAR}->ekMpMsg{CSIG}

    invoke-static {{p0, p2, p1, p3, p4}}, {LAN}->publishPeerDamageIfNeeded({CHAR}ILjava/lang/String;Ljava/lang/String;Lnet/fdgames/GameEntities/Helpers/DamageData;)V

    return-void
.end method""", "Character.a(msg): publishPeerDamageIfNeeded")

# ---- B4: Character.i(I) attack start (their E0) -> publishLocalAttackStart(this, lastTargetHit_id)
edit_method('net/fdgames/GameEntities/Character', 'i(I)V', lambda m: sub1(
    r'(    iput p1, p0, Lnet/fdgames/GameEntities/MapActor;->stateRelativeTime:F\n\n)(    :cond_\w+\n    return-void\n)',
    r'\1    iget p1, p0, Lnet/fdgames/GameEntities/Character;->lastTargetHit_id:I' + '\n\n'
    r'    invoke-static {p0, p1}, ' + LAN + r'->publishLocalAttackStart(' + CHAR + r'I)V' + '\n\n' + r'\2', m, 'attack start'),
    "Character.i(I): publishLocalAttackStart")

# ---- B5: damage apply (their Character.u) -> recordAppliedPeerProc per applied proc ----------
edit_method('net/fdgames/GameEntities/Character', 'a(Ljava/lang/String;ILjava/lang/String;)V', lambda m: sub1(
    r'(    invoke-virtual \{(v\d+)\}, Lnet/fdgames/GameEntities/Helpers/DamageEffect;->a\(\)Z\n\n    move-result (v\d+)\n\n    if-eqz \3, :cond_\w+\n)',
    r'\1\n    invoke-static {v0, \2}, ' + LAN + r'->recordAppliedPeerProc(' + CHAR + r'Lnet/fdgames/GameEntities/Helpers/DamageEffect;)V' + '\n', m, 'proc loop'),
    "Character damage apply: recordAppliedPeerProc")

# ---- B6: PatrollerAI.a(I) (their c) -> patrol aggro distance uses the hostile target ----------
edit_method('net/fdgames/GameEntities/AI/PatrollerAI', 'a(I)V', lambda m: sub1(
    r'    invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevel;->h\(\)Lnet/fdgames/GameEntities/Final/Player;\n\n    move-result-object v2\n\n    iget v2, v2, Lnet/fdgames/GameEntities/MapObject;->x:I\n\n    invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevel;->h\(\)Lnet/fdgames/GameEntities/Final/Player;\n\n    move-result-object v3\n',
    '    invoke-static {p1}, ' + EK + '->hostileChar(' + MA + ')' + CHAR + '\n\n    move-result-object v2\n\n    iget v2, v2, Lnet/fdgames/GameEntities/MapObject;->x:I\n\n'
    '    invoke-static {p1}, ' + EK + '->hostileChar(' + MA + ')' + CHAR + '\n\n    move-result-object v3\n', m, 'patrol distance'),
    "PatrollerAI.a(I): patrol aggro targets the hostile actor")

# ---- B7: NPC.E0() enemy detection (their H1): every player read -> hostile target; a
#          successful spot stores the target's id (both sites return right after) -------------
def _b7(m):
    m, k = re.subn(r'    invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevel;->h\(\)Lnet/fdgames/GameEntities/Final/Player;\n',
                   '    invoke-static {p0}, ' + EK + '->hostileChar(' + MA + ')' + CHAR + '\n', m)
    assert k == 12, f"NPC.E0 player reads: {k}"
    m, k = re.subn(r'(    invoke-virtual \{v0, v9\}, Le/a/a/a;->a\(Le/a/d/x;\)V\n\n)(    iput v1, p0, Lnet/fdgames/GameEntities/Final/NPC;->detectedEnemyID:I\n\n    return-void\n)',
                   r'\1    invoke-static {p0}, ' + EK + r'->hostileId(' + MA + r')I' + '\n\n    move-result v1\n\n' + r'\2', m)
    assert k == 2, f"NPC.E0 spot sites: {k}"
    return m
edit_method('net/fdgames/GameEntities/Final/NPC', 'E0()V', _b7, "NPC.E0: detection targets the hostile actor (12 reads, 2 spot ids)")

# ---- B8: CompanionAI.a(I) (their c) -> follow the anchor (the player unless a peer owns it) --
edit_method('net/fdgames/GameEntities/AI/CompanionAI', 'a(I)V', lambda m: (lambda r: (r[0] if r[1] == 4 else (_ for _ in ()).throw(AssertionError(f"companion player reads: {r[1]}"))))(
    re.subn(r'    iget-object (v\d+), \1, Lnet/fdgames/GameWorld/GameData;->player:Lnet/fdgames/GameEntities/Final/Player;\n',
            r'    invoke-static {p1}, ' + EK + r'->companionAnchor(' + NPC_ + r')' + MA + '\n\n    move-result-object \\1\n', m)),
    "CompanionAI.a(I): follow anchor (4 reads)")

# ---- B9: MonsterSpawn.y() (their Q) -> spawn level +2 per extra player in a session --------
edit_method('net/fdgames/GameEntities/Final/MonsterSpawn', 'y()V', lambda m: sub1(
    r'(    iget v6, p0, Lnet/fdgames/GameEntities/Final/MonsterSpawn;->spawnLevel:I\n\n)(    if-lez v6, :cond_\w+\n\n    iget-object v7, v5, Lnet/fdgames/GameEntities/Character;->sheet)',
    r'\1    invoke-static {v6, v5}, ' + EK + r'->scaledSpawnLevel(I' + NPC_ + r')I' + '\n\n    move-result v6\n\n' + r'\2', m, 'spawn level'),
    "MonsterSpawn.y(): party-size level scaling")

# ---- B10: NPC.W() sprite build (their v0) -> peers build a composite player sprite. Exactly the
#          MP condition: !companionSpawn && lanPeerVisual (a peer's companion keeps the companion path).
edit_method('net/fdgames/GameEntities/Final/NPC', 'W()V', lambda m: sub1(
    r'^(\.method public W\(\)V\n    \.locals \d+\n)',
    r'\1' + '\n    iget-boolean v0, p0, ' + NPC_ + '->companionSpawn:Z\n\n    if-nez v0, :ekmp_nopeer\n\n'
    '    iget-boolean v0, p0, ' + NPC_ + '->lanPeerVisual:Z\n\n    if-eqz v0, :ekmp_nopeer\n\n'
    '    invoke-static {p0}, ' + EK + '->buildPeerSprite(' + NPC_ + ')V\n\n    return-void\n\n    :ekmp_nopeer\n', m, 'W prepend', re.M),
    "NPC.W(): peer composite sprite")

# ---- B11: NPC.a(F) update (their M) -> a peer only moves/animates (no AI, no regen) --------
wrap_method('net/fdgames/GameEntities/Final/NPC', 'a(F)V', 'ekMpUpdate', True, f"""
.method public a(F)V
    .locals 1

    iget-boolean v0, p0, {NPC_}->lanPeerVisual:Z

    if-eqz v0, :ekmp_normal

    invoke-super {{p0, p1}}, {CHAR}->a(F)V

    return-void

    :ekmp_normal
    invoke-direct {{p0, p1}}, {NPC_}->ekMpUpdate(F)V

    return-void
.end method""", "NPC.a(F): peer update = movement/animation only")

# ---- B12: NPC.z() frames (their Q) -> melee slash overlay for attacking peers -------------
edit_method('net/fdgames/GameEntities/Final/NPC', 'z()Lcom/badlogic/gdx/utils/a;', lambda m: sub1(
    r'(    :cond_\w+\n)(    invoke-virtual \{p0\}, Lnet/fdgames/GameEntities/Character;->b0\(\)V\n)',
    r'\1' + '    iget-boolean v0, p0, ' + NPC_ + '->lanPeerVisual:Z\n\n    if-eqz v0, :ekmp_noslash\n\n'
    '    invoke-virtual {p0}, ' + MA + '->J()Lnet/fdgames/GameEntities/MapActor$ActorState;\n\n    move-result-object v0\n\n'
    '    sget-object v1, Lnet/fdgames/GameEntities/MapActor$ActorState;->d:Lnet/fdgames/GameEntities/MapActor$ActorState;\n\n'
    '    if-eq v0, v1, :ekmp_slash\n\n'
    '    sget-object v1, Lnet/fdgames/GameEntities/MapActor$ActorState;->i:Lnet/fdgames/GameEntities/MapActor$ActorState;\n\n'
    '    if-ne v0, v1, :ekmp_noslash\n\n'
    '    :ekmp_slash\n'
    '    sget-object v0, Lnet/fdgames/assets/GameAssets;->a:Lcom/badlogic/gdx/utils/a;\n\n'
    '    sget-object v1, Lnet/fdgames/assets/GameAssets;->v:Lnet/fdgames/assets/SlashAnimation;\n\n'
    '    iget-object v2, p0, ' + MA + '->facing:Lnet/fdgames/GameEntities/MapActor$Facing;\n\n'
    '    invoke-virtual {v1, v2}, Lnet/fdgames/assets/SlashAnimation;->a(Lnet/fdgames/GameEntities/MapActor$Facing;)Lcom/badlogic/gdx/graphics/g2d/Animation;\n\n'
    '    move-result-object v1\n\n    invoke-virtual {p0}, ' + MA + '->B()F\n\n    move-result v2\n\n'
    '    invoke-virtual {v1, v2}, Lcom/badlogic/gdx/graphics/g2d/Animation;->getKeyFrame(F)Ljava/lang/Object;\n\n'
    '    move-result-object v1\n\n    check-cast v1, Lcom/badlogic/gdx/graphics/g2d/TextureRegion;\n\n'
    '    invoke-virtual {v0, v1}, Lcom/badlogic/gdx/utils/a;->add(Ljava/lang/Object;)V\n\n'
    '    :ekmp_noslash\n' + r'\2', m, 'z slash'),
    "NPC.z(): peer melee slash frames")

# ---- B13: never save or reload peers ----
edit_method('net/fdgames/Helpers/Serializer', 'd(II)V', lambda m: sub1(
    r'^(\.method public static d\(II\)V\n    \.locals \d+\n)', r'\1' + '\n    invoke-static {}, ' + EK + '->stripPeersCurrent()V\n', m, 'save', re.M),
    "Serializer.d(II) SaveGame: strip peers first")
edit_method('net/fdgames/GameLevel/GameLevelData', 'a(Lnet/fdgames/GameLevel/GameLevelData;)V', lambda m: sub1(
    r'^(\.method public static a\(Lnet/fdgames/GameLevel/GameLevelData;\)V\n    \.locals \d+\n)',
    r'\1' + '\n    invoke-static {p0}, ' + EK + '->stripPeers(Lnet/fdgames/GameLevel/GameLevelData;)V\n', m, 'load', re.M),
    "GameLevelData.a(level) load fix-up: strip peers first")

# ---- B14: HUD (e/a/d/y.j() = stage draw, their z0/z.q()) -> CHAT button ----
edit_method('e/a/d/y', 'j()V', lambda m: sub1(
    r'(    iget-object v0, p0, Le/a/d/y;->b:Lcom/badlogic/gdx/scenes/scene2d/Stage;\n\n)(    invoke-virtual \{v0\}, Lcom/badlogic/gdx/scenes/scene2d/Stage;->draw\(\)V\n)',
    r'\1    invoke-static {v0}, ' + EK + r'->hudChat(Lcom/badlogic/gdx/scenes/scene2d/Stage;)V' + '\n\n' + r'\2', m, 'hud'),
    "GameHUD.j(): CHAT button while in a session")

# ---- B15: renderer (e/a/a/a.a(F)) -> player-name labels over peers, before the batch ends --
edit_method('e/a/a/a', 'a(F)V', lambda m: sub1(
    r'(    :cond_\w+\n)(    iget-object v1, v0, Le/a/a/a;->l:Le/a/c/a;\n\n    invoke-virtual \{v1\}, Lcom/badlogic/gdx/s/j/k/a;->c\(\)Lcom/badlogic/gdx/graphics/g2d/Batch;\n\n    move-result-object v1\n\n    invoke-interface \{v1\}, Lcom/badlogic/gdx/graphics/g2d/Batch;->end\(\)V\n\n    :cond_\w+\n    invoke-static \{\}, Le/a/d/y;->J\(\)Le/a/d/y;\n)',
    r'\1    iget-object v1, v0, Le/a/a/a;->l:Le/a/c/a;' + '\n\n    invoke-virtual {v1}, Lcom/badlogic/gdx/s/j/k/a;->c()Lcom/badlogic/gdx/graphics/g2d/Batch;\n\n    move-result-object v1\n\n'
    '    iget-object v2, v0, Le/a/a/a;->e:Lcom/badlogic/gdx/graphics/g2d/GlyphLayout;\n\n'
    '    invoke-static {v1, v2}, ' + EK + '->drawPeerNames(Lcom/badlogic/gdx/graphics/g2d/Batch;Lcom/badlogic/gdx/graphics/g2d/GlyphLayout;)V\n\n' + r'\2', m, 'names'),
    "GameLevelRenderer.a(F): peer name labels")

# ---- B16: minimap (e/a/c/a.e(), their ADTIsometricTiledMapRenderer.h) -> peer pins after the player pin
edit_method('e/a/c/a', 'e()V', lambda m: sub1(
    r'(    sget-object (v\d+), Lnet/fdgames/assets/GameAssets;->v0:Lcom/badlogic/gdx/graphics/Texture;\n(?:.*\n){1,14}?    invoke-interface \{(v\d+), \2, v\d+, v\d+\}, Lcom/badlogic/gdx/graphics/g2d/Batch;->draw\(Lcom/badlogic/gdx/graphics/Texture;FF\)V\n)',
    r'\1\n    invoke-static {\3, \2}, ' + LAN + r'->drawPeerMapPins(Lcom/badlogic/gdx/graphics/g2d/Batch;Lcom/badlogic/gdx/graphics/Texture;)V' + '\n', m, 'minimap'),
    "minimap: peer pins")

# ---- B17: main menu (e/a/b/e) -> a MULTIPLAYER button right after CREDITS, in both builders --
path = f'{DST}/e/a/b/e.smali'
t = open(path, encoding='utf-8').read()
t = t.replace('.field static z:Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;',
              '.field static z:Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;\n\n.field static ekLan:Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;', 1)
blk = re.compile(r'(    new-instance (v\d+), L[^;]+;\n\n    const-string (v\d+), "CREDITS"\n(?:(?!    new-instance ).*\n)*?'
                 r'    invoke-virtual \{v\d+\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->row\(\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n'
                 r'(?:\n    move-result-object (v\d+)\n\n    invoke-virtual \{\4, v\d+\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;->colspan\(I\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n)?)')
made = 0
def _menu(m):
    global made
    b, btn, txt = m.group(1), m.group(2), m.group(3)
    if 'Le/a/b/e;->z:' not in b:
        return b
    c = b.replace(f'    const-string {txt}, "CREDITS"\n\n    invoke-static {{{txt}}}, Lnet/fdgames/Helpers/GameString;->a(Ljava/lang/String;)Ljava/lang/String;\n\n    move-result-object {txt}\n',
                  f'    const-string {txt}, "MULTIPLAYER"\n')
    assert c != b, "menu copy: CREDITS label not found"
    c = c.replace('Le/a/b/e;->z:', 'Le/a/b/e;->ekLan:')
    sp = re.search(r'    sput-object (v\d+), Le/a/b/e;->ekLan:Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;\n', c)
    c = c[:sp.end()] + (f'\n    sget-object {btn}, Le/a/b/e;->ekLan:Lcom/badlogic/gdx/scenes/scene2d/ui/TextButton;\n\n'
                        f'    invoke-static {{}}, {EK}->lobbyListener()Lcom/badlogic/gdx/scenes/scene2d/utils/ClickListener;\n\n'
                        f'    move-result-object {txt}\n\n'
                        f'    invoke-virtual {{{btn}, {txt}}}, Lcom/badlogic/gdx/scenes/scene2d/Actor;->addListener(Lcom/badlogic/gdx/scenes/scene2d/EventListener;)Z\n') + c[sp.end():]
    made += 1
    return b + '\n' + c
t = blk.sub(_menu, t)
assert made == 2, f"main menu builders patched: {made}"
open(path, 'w', encoding='utf-8').write(t)
print("B: main menu: MULTIPLAYER button (both layouts)")

# ---- B18: Player.E() death (their X) -> in the PvP arena you are eliminated, not killed --------
edit_method('net/fdgames/GameEntities/Final/Player', 'E()V', lambda m: sub1(
    r'^(\.method protected E\(\)V\n    \.locals \d+\n)',
    r'\1' + '\n    invoke-static {p0}, ' + EK + '->arenaPlayerDeath(Lnet/fdgames/GameEntities/Final/Player;)Z\n\n'
    '    move-result v0\n\n    if-eqz v0, :ekmp_die\n\n    return-void\n\n    :ekmp_die\n', m, 'player death', re.M),
    "Player.E(): arena elimination instead of game over")

# ---- B19: NPC.E() death (their X) -> a peer puppet dying in the arena is removed; last one = won
edit_method('net/fdgames/GameEntities/Final/NPC', 'E()V', lambda m: sub1(
    r'(    invoke-super/range \{p0 \.\. p0\}, Lnet/fdgames/GameEntities/MapActor;->E\(\)V\n)',
    r'\1' + '\n    invoke-static/range {p0 .. p0}, ' + EK + '->arenaPeerDeath(' + NPC_ + ')V\n', m, 'npc death'),
    "NPC.E(): arena peer elimination")

# ---- B20: world map (e/a/d/r1.draw, their z0/q1.draw) -> peer markers + names after the red corners
edit_method('e/a/d/r1', 'draw(Lcom/badlogic/gdx/graphics/g2d/Batch;F)V', lambda m: sub1(
    r'(    invoke-interface/range \{v2 \.\. v7\}, Lcom/badlogic/gdx/graphics/g2d/Batch;->draw\(Lcom/badlogic/gdx/graphics/g2d/TextureRegion;FFFF\)V\n\n)'
    r'(    iget-object p2, p0, Le/a/d/r1;->b:Ljava/lang/String;\n\n    invoke-static \{p2\}, Lnet/fdgames/GameWorld/Areas;->j\(Ljava/lang/String;\)Z\n)',
    r'\1    iget-object p2, p0, Le/a/d/r1;->b:Ljava/lang/String;' + '\n\n    iget-object v1, p0, Le/a/d/r1;->c:Lcom/badlogic/gdx/graphics/g2d/TextureRegion;\n\n'
    '    iget v0, p0, Le/a/d/r1;->g:F\n\n'
    '    invoke-static {p0, p1, p2, v1, v0}, ' + EK + '->drawWorldPeers(Lcom/badlogic/gdx/scenes/scene2d/Actor;Lcom/badlogic/gdx/graphics/g2d/Batch;Ljava/lang/String;Lcom/badlogic/gdx/graphics/g2d/TextureRegion;F)V\n\n' + r'\2', m, 'world map'),
    "WorldMapImage.draw: peer markers")

# ---- B21: GameData.f() new dynamic event (their Z(F)) -> game log + chat broadcast -------------
edit_method('net/fdgames/GameWorld/GameData', 'f()V', lambda m: sub1(
    r'(    invoke-virtual \{p0\}, Lnet/fdgames/GameWorld/GameData;->r\(\)Ljava/util/ArrayList;\n\n    move-result-object v0\n\n    invoke-virtual \{v0, (v\d+)\}, Ljava/util/ArrayList;->add\(Ljava/lang/Object;\)Z\n)',
    r'\1' + '\n    invoke-static {\\2}, ' + EK + '->worldEvent(Lnet/fdgames/GameWorld/DynamicEvent;)V\n', m, 'world event'),
    "GameData.f(): world-event log + broadcast")

# ---- B22-B24: friends list in the lobby (owner request, not in the mod; PORT_SPEC §8) ----------
LOBBY = 'net/fdgames/ek/android/lan/LanLobbyActivity'
LL = 'L' + LOBBY + ';'
FR = 'Lnet/fdgames/ek/android/lan/EkFriends;'
add_method(f'{DST}/{LOBBY}.smali', f"""
.method ekAddButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V
    .locals 0

    invoke-direct {{p0, p1, p2, p3}}, {LL}->addControlButton(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method ekJoin(Ljava/lang/String;I)V
    .locals 1

    invoke-direct {{p0}}, {LL}->savePlayerName()V

    iget-object v0, p0, {LL}->playerNameInput:Landroid/widget/EditText;

    invoke-virtual {{v0}}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {{v0}}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {{v0}}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-direct {{p0, v0, p1, p2}}, {LL}->joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method""", "LanLobbyActivity.ekAddButton / ekJoin (friends bridge)")
edit_method(LOBBY, 'joinHostAsync(Ljava/lang/String;Ljava/lang/String;I)V', lambda m: sub1(
    r'^(\.method private joinHostAsync\(Ljava/lang/String;Ljava/lang/String;I\)V\n    \.registers \d+\n)',
    r'\1' + '\n    invoke-static {p0, p2, p3}, ' + FR + '->remember(' + LL + 'Ljava/lang/String;I)V\n', m, 'remember', re.M),
    "LanLobbyActivity.joinHostAsync: remember the host as a friend")
edit_method(LOBBY, 'buildContentView()Landroid/view/View;', lambda m: sub1(
    r'(    new-instance (v\d+), ' + re.escape(LL[:-1]) + r'\$4;\n\n    invoke-direct \{\2, p0\}, ' + re.escape(LL[:-1]) + r'\$4;-><init>\(' + re.escape(LL) + r'\)V\n\n    invoke-direct \{p0, v3, v\d+, \2\}, ' + re.escape(LL) + r'->addControlButton\(Landroid/widget/LinearLayout;Ljava/lang/String;Landroid/view/View\$OnClickListener;\)V\n)',
    r'\1\n    invoke-static {p0, v1}, ' + FR + '->addLobbyRow(' + LL + r'Landroid/widget/LinearLayout;)V' + '\n', m, 'friends row'),
    "LanLobbyActivity.buildContentView: Friends / Add friend row")

# ---- B24b: lobby texts: the mod's placeholder title/subtitle predate sync (it now exists) --------
def _lobby_texts(m):
    m = sub1(r'const-string (v\d+), "LAN / CHAT BETA"', r'const-string \1, "MULTIPLAYER"', m, 'lobby title')
    return sub1(r'const-string (v\d+), "Host, join, discover LAN sessions and chat\. Map/combat sync will arrive in phases\."',
                r'const-string \1, "Play together in the host\'s world with your own character. Host, join by address or a friend, chat and trade."',
                m, 'lobby subtitle')
edit_method(LOBBY, 'buildContentView()Landroid/view/View;', _lobby_texts, "LanLobbyActivity: current title/subtitle")

# ---- B25-B29: shared world phase A (auto-host, join approval, solo != session) SHARED_WORLD_SPEC §5
LSM = 'net/fdgames/ek/android/lan/LanSessionManager'
LS = 'L' + LSM + ';'
AU = 'Lnet/fdgames/ek/android/lan/EkAuto;'
SESSION = f"""
    iget-boolean v0, p0, {LS}->connected:Z

    if-nez v0, :yes

    iget-boolean v0, p0, {LS}->hosting:Z

    if-eqz v0, :no

    iget-object v0, p0, {LS}->players:Ljava/util/ArrayList;

    invoke-virtual {{v0}}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x2

    if-lt v0, v1, :no

    :yes
    const/4 v0, 0x1

    return v0

    :no
    const/4 v0, 0x0

    return v0
.end method"""
for sig in ('isSessionRunning()Z', 'isInSession()Z'):
    edit_method(LSM, sig, lambda m, sig=sig: f'.method public {sig}\n    .registers 3\n' + SESSION,
                f"LanSessionManager.{sig}: hosting alone is not a session (>=2 players or connected)")
add_method(f'{DST}/{LSM}.smali', f"""
.method public ekConnected()Z
    .registers 2

    iget-boolean v0, p0, {LS}->connected:Z

    return v0
.end method""", "LanSessionManager.ekConnected()")
edit_method(LSM, 'joinHost(Ljava/lang/String;Ljava/lang/String;I)V', lambda m: sub1(
    r'^(\.method public joinHost\(Ljava/lang/String;Ljava/lang/String;I\)V\n    \.registers \d+\n)',
    r'\1' + '\n    invoke-static {}, ' + AU + '->noteJoin()V\n', m, 'noteJoin', re.M),
    "LanSessionManager.joinHost: pause auto-host while joining")
edit_method(LSM, 'handleIncomingClient(Ljava/net/Socket;)V', lambda m: sub1(
    r'(    invoke-direct \{p0, v4\}, ' + re.escape(LS) + r'->decode\(Ljava/lang/String;\)Ljava/lang/String;\n\n    move-result-object v4\n)(\n    iget-object v5, p0, ' + re.escape(LS) + r'->lock:Ljava/lang/Object;\n)',
    r'\1' + '\n    invoke-static {p1, v4}, ' + AU + r'->approveJoin(Ljava/net/Socket;Ljava/lang/String;)Z' + '\n\n    move-result v5\n\n    if-nez v5, :ekauto_ok\n\n'
    '    const-string v5, "CLOSE\\tThe host declined the join request."\n\n'
    '    invoke-direct {p0, v3, v5}, ' + LS + '->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V\n\n'
    '    invoke-direct {p0, v3}, ' + LS + '->closeQuietly(Ljava/io/PrintWriter;)V\n\n'
    '    invoke-direct {p0, v2}, ' + LS + '->closeQuietly(Ljava/io/BufferedReader;)V\n\n'
    '    invoke-direct {p0, p1}, ' + LS + '->closeQuietly(Ljava/net/Socket;)V\n\n    return-void\n\n    :ekauto_ok\n' + r'\2', m, 'approve'),
    "LanSessionManager.handleIncomingClient: host approves strangers (friends skip)")
edit_method('e/a/b/b', 'a(F)V', lambda m: sub1(
    r'(    invoke-static \{\}, ' + re.escape(LAN) + r'->tick\(\)V\n)',
    '    invoke-static {}, ' + AU + r'->tick()V' + '\n\n' + r'\1', m, 'autohost tick'),
    "GameScreen.a(F): auto-host keeper")

# ---- B30-B40: shared world phases B+C (character block, guest slot 42, world sync) SHARED_WORLD_SPEC §7
SH = 'Lnet/fdgames/ek/android/lan/EkShare;'
SER = 'net/fdgames/Helpers/Serializer'
SE = 'L' + SER + ';'
GD_ = 'Lnet/fdgames/GameWorld/GameData;'
PL_ = 'Lnet/fdgames/GameEntities/Final/Player;'
SGD = 'Lnet/fdgames/Helpers/SaveGameData;'
JS = 'Lnet/fdgames/Helpers/Json;'
add_method(f'{DST}/{SER}.smali', f"""
.method public static ekJson(){JS}
    .locals 1

    sget-object v0, {SE}->d:{JS}

    return-object v0
.end method

.method public static ekSnapshot()Ljava/lang/String;
    .locals 4

    invoke-static {{}}, {EK}->stripPeersCurrent()V

    invoke-static {{}}, {GD_}->O(){GD_}

    move-result-object v0

    if-nez v0, :ok

    const/4 v0, 0x0

    return-object v0

    :ok
    iget-object v1, v0, {GD_}->player:{PL_}

    const/4 v2, 0x0

    iput-object v2, v1, {PL_}->conversations:Ljava/util/ArrayList;

    new-instance v1, {SGD}

    invoke-direct {{v1}}, {SGD}-><init>()V

    iput-object v0, v1, {SGD}->gamedata:{GD_}

    invoke-static {{}}, Lnet/fdgames/GameLevel/GameLevelData;->s()Lnet/fdgames/GameLevel/GameLevelData;

    move-result-object v2

    iput-object v2, v1, {SGD}->leveldata:Lnet/fdgames/GameLevel/GameLevelData;

    invoke-static {{}}, Lnet/fdgames/GameWorld/MessageRouter;->a()Ljava/util/ArrayList;

    move-result-object v2

    iput-object v2, v1, {SGD}->queue:Ljava/util/ArrayList;

    const-string v2, "1.3.1207"

    iput-object v2, v1, {SGD}->version:Ljava/lang/String;

    iget-object v2, v0, {GD_}->player:{PL_}

    const/4 v3, 0x0

    iput-object v3, v2, {PL_}->activables:[Lnet/fdgames/GameEntities/Helpers/Activable;

    iput v3, v2, {PL_}->numActivables:I

    sget-object v2, {SE}->d:{JS}

    const/4 v3, 0x1

    invoke-virtual {{v2, v3}}, {JS}->setIgnoreUnknownFields(Z)V

    invoke-virtual {{v2, v1}}, {JS}->prettyPrint(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {{v1}}, {SE}->b(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method""", "Serializer.ekJson / ekSnapshot (the save, as a string)")
edit_method(SER, 'a(II)V', lambda m: sub1(
    r'^(\.method public static a\(II\)V\n    \.locals \d+\n)', r'\1' + '\n    invoke-static {p0, p1}, ' + SH + '->onLoadStart(II)V\n', m, 'load start', re.M),
    "Serializer.a(II) LoadGame: remember the slot")
edit_method(SER, 'a(II)V', lambda m: sub1(
    r'(    iget-object p0, v5, ' + re.escape(SGD) + r'->gamedata:' + re.escape(GD_) + r'\n\n    invoke-static \{p0\}, ' + re.escape(GD_) + r'->a\(' + re.escape(GD_) + r'\)V\n)',
    '    invoke-static {v5}, ' + SH + '->onLoaded(' + SGD + r')V' + '\n\n' + r'\1', m, 'graft'),
    "Serializer.a(II) LoadGame: graft a pending character block before the save goes live")
wrap_method(SER, 'd(II)V', 'ekSaveOrig', True, f"""
.method public static d(II)V
    .locals 0

    invoke-static {{p0, p1}}, {SE}->ekSaveOrig(II)V

    invoke-static {{p0}}, {SH}->onSaveAfter(I)V

    return-void
.end method""", "Serializer.d(II) SaveGame: mirror the character into the home save while joined")
GV = 'net/fdgames/GameWorld/GameVariables'
add_method(f'{DST}/{GV}.smali', """
.method public ekVars()Ljava/util/ArrayList;
    .locals 1

    iget-object v0, p0, Lnet/fdgames/GameWorld/GameVariables;->variables:Ljava/util/ArrayList;

    return-object v0
.end method""", "GameVariables.ekVars()")
wrap_method(GV, 'b(Ljava/lang/String;I)Z', 'ekSetOrig', True, f"""
.method public b(Ljava/lang/String;I)Z
    .locals 1

    invoke-direct {{p0, p1, p2}}, L{GV};->ekSetOrig(Ljava/lang/String;I)Z

    move-result v0

    invoke-static {{p1, p2}}, {SH}->onVar(Ljava/lang/String;I)V

    return v0
.end method""", "GameVariables.b(String,I): world variables sync")
for sig, orig, fn in (('l(Ljava/lang/String;)V', 'ekDeadOrig', 'onDead'), ('i(Ljava/lang/String;)V', 'ekLootOrig', 'onLooted')):
    wrap_method('net/fdgames/GameWorld/GameData', sig, orig, True, f"""
.method public {sig}
    .locals 0

    invoke-direct {{p0, p1}}, {GD_}->{orig}(Ljava/lang/String;)V

    invoke-static {{p1}}, {SH}->{fn}(Ljava/lang/String;)V

    return-void
.end method""", f"GameData.{sig}: {fn} sync")
CP = 'Lnet/fdgames/ek/android/lan/LanSessionManager$ClientPeer;'
add_method(f'{DST}/{LSM}.smali', f"""
.method public ekSendToHost(Ljava/lang/String;)V
    .registers 3

    iget-boolean v0, p0, {LS}->connected:Z

    if-eqz v0, :done

    iget-object v0, p0, {LS}->clientWriter:Ljava/io/PrintWriter;

    if-eqz v0, :done

    invoke-direct {{p0, v0, p1}}, {LS}->sendLine(Ljava/io/PrintWriter;Ljava/lang/String;)V

    :done
    return-void
.end method

.method public ekSendTo(Ljava/lang/Object;Ljava/lang/String;)V
    .registers 4

    instance-of v0, p1, {CP}

    if-eqz v0, :done

    check-cast p1, {CP}

    invoke-static {{p1, p2}}, {CP}->access$900({CP}Ljava/lang/String;)V

    :done
    return-void
.end method

.method public ekBroadcast(Ljava/lang/String;)V
    .registers 2

    invoke-direct {{p0, p1}}, {LS}->broadcastToClients(Ljava/lang/String;)V

    return-void
.end method""", "LanSessionManager.ekSendToHost / ekSendTo / ekBroadcast")
edit_method(LSM, 'handleIncomingClient(Ljava/net/Socket;)V', lambda m: sub1(
    r'(    :(goto_\w+)\n    invoke-virtual \{v2\}, Ljava/io/BufferedReader;->readLine\(\)Ljava/lang/String;\n\n    move-result-object v1\n\n    if-eqz v1, :cond_\w+\n)',
    r'\1' + '\n    invoke-static {p0, v6, v1}, ' + SH + '->hostLine(' + LS + r'Ljava/lang/Object;Ljava/lang/String;)Z' + '\n\n    move-result v4\n\n    if-nez v4, :\\2\n', m, 'host line'),
    "LanSessionManager host read loop: shared-world messages first")
edit_method(LSM, 'handleServerMessage(Ljava/lang/String;)V', lambda m: sub1(
    r'^(\.method private handleServerMessage\(Ljava/lang/String;\)V\n    \.registers \d+\n)',
    r'\1' + '\n    invoke-static {p0, p1}, ' + SH + '->clientLine(' + LS + 'Ljava/lang/String;)Z\n\n    move-result v0\n\n    if-eqz v0, :ekshare_no\n\n    return-void\n\n    :ekshare_no\n', m, 'client line', re.M),
    "LanSessionManager client dispatch: shared-world messages first")

# ---- B41-B48: shared world phase D (shared drops, PvP everywhere + loot bag, trade) SHARED_WORLD_SPEC §8
IT = 'Lnet/fdgames/ek/android/lan/EkItems;'
LOOT = 'net/fdgames/GameEntities/Final/Loot'
LT = 'L' + LOOT + ';'
edit_method('net/fdgames/GameLevel/GameLevel', 'a(III)V', lambda m: f""".method public static a(III)V
    .locals 1

    new-instance v0, {LT}

    invoke-direct {{v0, p0, p1, p2}}, {LT}-><init>(III)V

    invoke-static {{v0}}, Lnet/fdgames/GameLevel/GameLevelData;->a({LT})V

    invoke-static {{v0}}, {IT}->onNewDrop({LT})V

    return-void
.end method""", "GameLevel.a(III) drop: shared drop in a session")
add_method(f'{DST}/{LOOT}.smali', f"""
.method public ekSetGold(I)V
    .locals 0

    iput p1, p0, {LT}->gold:I

    return-void
.end method""", "Loot.ekSetGold")
wrap_method(LOOT, 'removeItem(I)V', 'ekRemoveOrig', True, f"""
.method public removeItem(I)V
    .locals 1

    invoke-virtual {{p0, p1}}, {LT}->getItem(I)I

    move-result v0

    invoke-direct {{p0, p1}}, {LT}->ekRemoveOrig(I)V

    invoke-static {{p0, v0}}, {IT}->onTake({LT}I)V

    return-void
.end method""", "Loot.removeItem: shared-drop pick")
wrap_method(LOOT, 'd()V', 'ekGoldOrig', True, f"""
.method public d()V
    .locals 1

    invoke-virtual {{p0}}, {LT}->g()I

    move-result v0

    invoke-direct {{p0}}, {LT}->ekGoldOrig()V

    invoke-static {{p0, v0}}, {IT}->onTakeGold({LT}I)V

    return-void
.end method""", "Loot.d: shared-drop gold pick")
wrap_method(LOOT, 'b()V', 'ekAllOrig', True, f"""
.method public b()V
    .locals 1

    invoke-static {{p0}}, {IT}->snapshot({LT})Ljava/lang/String;

    move-result-object v0

    invoke-direct {{p0}}, {LT}->ekAllOrig()V

    invoke-static {{p0, v0}}, {IT}->onTakeAll({LT}Ljava/lang/String;)V

    return-void
.end method""", "Loot.b take-all: shared-drop pick")
LGB = 'net/fdgames/ek/android/lan/LanGameBridge'
RRC = 'receiveRemoteCombat(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;II)V'
edit_method(LGB, RRC, lambda m: sub1(
    r'(    iget-object v1, v0, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;\n\n    if-eqz v1, :cond_\w+\n\n)(    const-string v2, "H10_pvp_arena"\n(?:.*\n)*?    if-lt v2, v3, :cond_\w+\n\n    :(cond_\w+)\n)',
    r'\1    invoke-static {}, ' + IT + r'->pvpAnywhere()Z' + '\n\n    move-result v2\n\n    if-nez v2, :\\3\n\n' + r'\2', m, 'pvp damage'),
    "LanGameBridge.receiveRemoteCombat: peer damage outside the arena when PvP everywhere is on")
edit_method(LGB, RRC, lambda m: sub1(
    r'(    if-lt v4, v5, :cond_\w+\n\n    const/4 v4, 0x0\n\n    iput v4, v3, Lnet/fdgames/GameEntities/CharacterSheet/CharacterStats;->missingHP:I\n)',
    r'\1\n    invoke-static {}, ' + IT + r'->onPvpDefeat()V' + '\n', m, 'pvp defeat'),
    "LanGameBridge.receiveRemoteCombat: PvP defeat outside the arena drops a loot bag")
def _hostile(m):
    new, k = re.subn(r'(    iget-object (v\d+), \2, Lnet/fdgames/GameWorld/GameData;->CurrentLevel:Ljava/lang/String;\n\n    if-eqz \2, :cond_\w+\n\n)(    const-string (v\d+), "H10_pvp_arena"\n(?:(?!\.end method).*\n)*?    if-lt v\d+, v\d+, :cond_\w+\n\n    :(cond_\w+)\n)',
                     lambda g: g.group(1) + '    invoke-static {}, ' + IT + '->pvpAnywhere()Z\n\n    move-result ' + g.group(4) + '\n\n    if-nez ' + g.group(4) + ', :' + g.group(5) + '\n\n' + g.group(3), m, count=1)
    assert k == 1, "hostile anchor"
    return new
for sig in ('createPeerActor(Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;',
            'getOrCreatePeerActor(Ljava/lang/String;Lnet/fdgames/ek/android/lan/LanSessionManager$PlayerState;)Lnet/fdgames/GameEntities/Final/NPC;'):
    edit_method(LGB, sig, _hostile, f"LanGameBridge.{sig.split('(')[0]}: peers hostile everywhere when PvP everywhere is on")

# ---- B49-B50: closing the shared-world gaps (SHARED_WORLD_SPEC §9) -------------------------------
add_method(f'{DST}/{SER}.smali', f"""
.method public static ekDecode(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    invoke-static {{p0}}, {SE}->a(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method""", "Serializer.ekDecode (a save file's text -> Json, for joining from the menu)")
wrap_method(SER, 'f()V', 'ekSaveLevelOrig', True, f"""
.method public static f()V
    .locals 1

    invoke-static {{}}, {GD_}->O(){GD_}

    move-result-object v0

    if-nez v0, :hasgd

    const/4 v0, 0x0

    goto :lvl

    :hasgd
    iget-object v0, v0, {GD_}->CurrentLevel:Ljava/lang/String;

    :lvl
    invoke-static {{}}, {SE}->ekSaveLevelOrig()V

    invoke-static {{v0}}, {SH}->onLevelSaved(Ljava/lang/String;)V

    return-void
.end method""", "Serializer.f() saveLevel: share the area you just left (level cache)")

# ---- B51: checkboxes drawn at 20x20 screen px on high-res screens (deobf/UI_SCALING_SPEC.md) --------
edit_method('net/fdgames/assets/GameAssets', 'b()V', lambda m: sub1(
    r'(    iput-object v1, v0, Lcom/badlogic/gdx/scenes/scene2d/ui/CheckBox\$CheckBoxStyle;->checkboxOn:Lcom/badlogic/gdx/scenes/scene2d/utils/Drawable;\n)',
    r'\1\n    sget-object v0, Lnet/fdgames/assets/GameAssets;->r0:Lcom/badlogic/gdx/scenes/scene2d/ui/CheckBox$CheckBoxStyle;\n\n'
    r'    invoke-static {v0}, Lnet/fdgames/ek/android/lan/EkUi;->scaleCheckboxes(Lcom/badlogic/gdx/scenes/scene2d/ui/CheckBox$CheckBoxStyle;)V\n',
    m, 'checkbox style'), "GameAssets.b(): checkbox images follow the UI scale (screen height / 720)")

# ---- B52: the skin's menu-button-font was scaled (w/1280, h/720) -> stretched text on non-16:9
#          screens (Fold inner ~1.2:1 = tall/narrow, outer 21:9 = wide). One uniform scale, the
#          min(w/1280, h/720) most windows already use for their layout. deobf/UI_SCALING_SPEC.md
edit_method('net/fdgames/assets/Assets', 'a()V', lambda m: sub1(
    r'(    div-float/2addr v4, v5\n\n)(    invoke-virtual \{v2, v3, v4\}, Lcom/badlogic/gdx/graphics/g2d/BitmapFont\$BitmapFontData;->setScale\(FF\)V\n)',
    r'\1    invoke-static {v3, v4}, Ljava/lang/Math;->min(FF)F\n\n    move-result v3\n\n    move v4, v3\n\n\2',
    m, 'menu font scale'), "Assets.a(): menu-button-font keeps its proportions (uniform min scale)")

# ---- B53: world-map peer markers never showed outside the big cities: the engine calls Areas.g(String)
#          (their static Areas.h) by reflection as a STATIC method, but in 4.2.2 it is an instance
#          method on GameWorld.f -> Method.invoke(null, ..) throws, caught, marker skipped. Call it the
#          way WorldMapImage.a(String) does: GameWorld.f.g(level). MULTIPLAYER_PORT_SPEC §7.
edit_method(LGB, 'resolveMarker(Ljava/lang/String;F)[F', lambda m: sub1(
    r'    const-string (v\d+), "net\.fdgames\.GameWorld\.Areas"\n\n    const-string (v\d+), "g"\n\n    const-class (v\d+), Ljava/lang/String;\n\n'
    r'    invoke-static \{\1, \2, \3, p0\}, Lnet/fdgames/ek/android/lan/LanGameBridge;->invokeStatic\(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;\)Ljava/lang/Object;\n\n'
    r'    move-result-object p0\n',
    r'    sget-object \1, Lnet/fdgames/GameWorld/GameWorld;->f:Lnet/fdgames/GameWorld/Areas;\n\n'
    r'    invoke-virtual {\1, p0}, Lnet/fdgames/GameWorld/Areas;->g(Ljava/lang/String;)Lnet/fdgames/TiledMap/Objects/Coords;\n\n'
    r'    move-result-object p0\n',
    m, 'areas coords'), "LanGameBridge.resolveMarker: area position via GameWorld.f.g(level) (was a failing static reflection)")

# ---- B54-B57: kill rewards split by damage share (deobf/KILL_REWARDS_SPEC.md) --------------------------
KL = 'Lnet/fdgames/ek/android/lan/EkKill;'
NPCD = 'Lnet/fdgames/GameEntities/Final/NPC;'
add_method(f'{DST}/{LGB}.smali', """
.method public static ekPeerActors()Ljava/util/LinkedHashMap;
    .locals 1

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerActors:Ljava/util/LinkedHashMap;

    return-object v0
.end method

.method public static ekPeerSummonOwners()Ljava/util/LinkedHashMap;
    .locals 1

    sget-object v0, Lnet/fdgames/ek/android/lan/LanGameBridge;->peerSummonOwners:Ljava/util/LinkedHashMap;

    return-object v0
.end method""", "LanGameBridge.ekPeerActors / ekPeerSummonOwners (who a hit belongs to)")
DSIG = '(Lnet/fdgames/GameEntities/Helpers/Damage;IZI)V'
wrap_method('net/fdgames/GameEntities/Character', 'a' + DSIG, 'ekDmgOrig', True, f"""
.method public a{DSIG}
    .locals 1

    invoke-static {{p0}}, {KL}->before({CHAR})I

    move-result v0

    invoke-direct {{p0, p1, p2, p3, p4}}, {CHAR}->ekDmgOrig{DSIG}

    invoke-static {{p0, p2, v0}}, {KL}->after({CHAR}II)V

    return-void
.end method""", "Character.a(Damage,I,Z,I): damage ledger (HP removed per attacker side)")
def _kill(m):
    m = sub1(r'^(\.method public E\(\)V\n    \.locals \d+\n)', r'\1' + '\n    invoke-static/range {p0 .. p0}, ' + KL + '->onDeath(' + NPCD + ')V\n', m, 'death start', re.M)
    m = sub1(r'(    iget (v\d+), (v\d+), Lnet/fdgames/GameEntities/MapObject;->x:I\n\s*add-int/2addr \2, (v\d+)\n\s*'
             r'iget (v\d+), \3, Lnet/fdgames/GameEntities/MapObject;->y:I\n\s*add-int/2addr \5, \4\n\s*)'
             r'invoke-static \{\2, \5, (v\d+), (v\d+)\}, Lnet/fdgames/GameLevel/GameLevel;->a\(IILjava/util/ArrayList;I\)V\n',
             lambda g: g.group(1) + 'invoke-static {' + g.group(3) + ', ' + g.group(2) + ', ' + g.group(5) + ', ' + g.group(6) + ', ' + g.group(7)
             + '}, ' + KL + '->loot(' + NPCD + 'IILjava/util/ArrayList;I)V\n', m, 'loot bag')
    m = sub1(r'(    iget-boolean (v\d+), (v\d+), Lnet/fdgames/GameEntities/Final/NPC;->respawned:Z\n\s*if-eqz \2, :cond_\w+\n\s*div-int/lit8 (v\d+), \4, 0xa\n\s*:cond_\w+\n\s*'
             r'invoke-static \{\}, Lnet/fdgames/GameLevel/GameLevel;->h\(\)Lnet/fdgames/GameEntities/Final/Player;\n\s*move-result-object (v\d+)\n\s*)'
             r'invoke-virtual \{\5, \4\}, Lnet/fdgames/GameEntities/Final/Player;->k\(I\)V\n',
             lambda g: g.group(1) + 'invoke-static {' + g.group(3) + ', ' + g.group(5) + ', ' + g.group(4) + '}, ' + KL
             + '->xp(' + NPCD + 'Lnet/fdgames/GameEntities/Final/Player;I)V\n', m, 'kill xp')
    return m
edit_method('net/fdgames/GameEntities/Final/NPC', 'E()V', _kill, "NPC.E() death: own-side damage report; shared kills' loot + XP go to the host")

# ---- B58: save slots: name and "class (level)" drawn on top of each other (deobf/UI_SCALING_SPEC.md) ----
SLOT = 'Le/a/d/e1/w;'
LBL = 'Lcom/badlogic/gdx/scenes/scene2d/ui/Label;'
edit_method('e/a/d/e1/w', 'a(Lnet/fdgames/GameWorld/BasicGameData;)V', lambda m: sub1(
    r'(    :goto_\w+\n\s*invoke-virtual \{p0\}, Lcom/badlogic/gdx/scenes/scene2d/ui/WidgetGroup;->pack\(\)V\n)',
    lambda g: g.group(1).replace('    invoke-virtual {p0}', f'    iget-object v0, p0, {SLOT}->g:{LBL}\n\n    iget-object v1, p0, {SLOT}->h:{LBL}\n\n'
        f'    sget v2, {SLOT}->l:F\n\n    invoke-static {{v0, v1, v2}}, Lnet/fdgames/ek/android/lan/EkUi;->fixSlot({LBL}{LBL}F)V\n\n    invoke-virtual {{p0}}', 1),
    m, 'slot fit'), "SlotDescriptionTable.a(): name + class/level each one line, fitted to the slot")

# ---- B59: Details window (StatsDetailWindow e/a/d/e/h0): long stat names ran into the value column --------
def _h0(m):
    new, k = re.subn(r'(    invoke-virtual \{(\w+), (\w+)\}, Lcom/badlogic/gdx/scenes/scene2d/ui/Table;->add\(Lcom/badlogic/gdx/scenes/scene2d/Actor;\)Lcom/badlogic/gdx/scenes/scene2d/ui/Cell;\n\s*move-result-object \w+\n\s*(?:sget \w+, [^\n]+\n\s*)?const/high16 \w+, 0x43700000    # 240\.0f\n)',
                     lambda g: '    invoke-static/range {' + g.group(3) + ' .. ' + g.group(3) + '}, Lnet/fdgames/ek/android/lan/EkUi;->wrapLabel(Ljava/lang/Object;)V\n\n' + g.group(1), m)
    assert k == 1, f"h0 name column: {k}"
    return new
edit_method('e/a/d/e/h0', 'a(Ljava/lang/String;Ljava/lang/String;Z)Lcom/badlogic/gdx/scenes/scene2d/ui/Table;', _h0, "StatsDetailWindow row: name column wraps in its own width")
edit_method('e/a/d/e/h0', 'a(Lnet/fdgames/GameEntities/CharacterSheet/CharacterSheet;)V', _h0, "StatsDetailWindow sheet rows: name column wraps in its own width")

print("DONE")

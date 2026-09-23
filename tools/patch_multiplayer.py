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

# ---- B10: NPC.W() sprite build (their v0) -> peers build a composite player sprite --------
edit_method('net/fdgames/GameEntities/Final/NPC', 'W()V', lambda m: sub1(
    r'^(\.method public W\(\)V\n    \.locals \d+\n)',
    r'\1' + '\n    iget-boolean v0, p0, ' + NPC_ + '->lanPeerVisual:Z\n\n    if-eqz v0, :ekmp_nopeer\n\n'
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
print("DONE")

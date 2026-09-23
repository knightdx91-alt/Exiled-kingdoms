# Multiplayer port — full audit (2026-09-23, before phase 2)

Owner: "make sure everything matches and we know what everything is before going on."
Everything below was checked against three disassemblies: the MP mod (1218, string containers
resolved), vanilla 2025 (1217) and our clean 4.2.2 base (1207). Tools used live in the session
scratch (`/tmp/mp/work/*.py`); the method is recorded here so it can be re-run.

## A. Name translations (REMAP, spec §2) — body-by-body
Comparer: registers/labels/scrambled names normalised, then an exact instruction diff.

| their → our | result | why any difference is benign |
|---|---|---|
| `GameData.v→O`, `Character.r1→b`, `CharacterResistances.e→d`, `.f→a`, `NPC.L1→H0`, `M1→I0`, `U1→v`, `GameObject.q→m`, `Damage.b→a`, `MapActor.d0→J`, `q0→a`, `v0→W`, `GameLevel.b→c`, `j→b`, `GameLevelData.e→a`, `o→s`, `Party.f→c`, `g→d`, `i→g`, `Spawn.b→a`, `GameMap.r→b.c` | **exact** (0 diffs) | — |
| `Array.a→add` | 0.90 | growth factor arithmetic compiled differently |
| `Character.v→a(msg)` | 0.67 | their body = ours + their hook; both forward to the damage-apply method → confirms `u(I,S,S)` ≡ our `a(S,I,S)` |
| `CharacterInventory.s→u` | 0.93, 176 diffs | **identical footprint** (same fields/methods touched); both are the method the equip path and the sheet refresh call |
| `NPC.B1→y0` | 0.90 | AI (re)build; 2025 refactored CivilianAI/IdleAI constructors |
| `NPC.k0→P` | 0.88 | "is party companion": theirs `instanceof CompanionAI`, ours `AI.b()` (overridden only by CompanionAI) |
| `NPC.v0→W` | 0.77 | sprite build; 2025 computes gender/body names dynamically, ours hardcodes the male fallback; plus their peer branch |
| `GameObject.s→i` | 0.84 | "is hostile to the player"; `WorldFactions` API took an extra faction arg in ours |
| `SkillSet.n→f` | 0.95 | bonus-set rebuild |
| `GameLevel.g→b(I)`, `h→c(I)` | 0.95/0.96 | player fetched via `GameData.player` vs `GameLevel.h()` |
| `WorldFactions.i→c`, `GameString.b→a`, `Rules.f→c`, `Rules.i→d`, `GameAssets.o→i`, `ExiledKingdoms.f→e` | 0.86–0.97 | field renames, lazy table load in 2025, obfuscated gdx sound class, field declared as MainActivity vs the interface |
| `GameVariables.e(I,S)` → our `b(S,I)` | — | argument order swapped; forwarder added. Plain variables are just set (console/quest side effects only fire for `REP_/GL_/know_/DQ_`/quest names) |
| `MapEffectEntity(F,I,I,I,S)` | — | adapter onto `(I,I,I,S,Z,S,F)` = (x,y,caster,"",true,id,delay) |

## B. Enums
ActorState, Facing, Gender, DamageType, CharacterClass, CharacterRace, EffectType, NPCState:
constants compared by name in `<clinit>` → **identical**. (An earlier parser suggested shifts; it
was wrong and its one remap was removed.)

## C. Reflection (names passed as strings)
* **Methods — 26 call sites** rewritten per call site (19 renamed): GameData `v→O`; Areas `h→g`,
  `j→j`; MapActor `d0→J`; NPC `v0→W`; CharacterResistances `c→b`, `d→c`; CharacterStats `f→e`;
  SkillSet `g→c` (rank), `q→i` (remove, confirmed body), `j→d` (+1); GameVariables `b→b`. Plus
  `GameVariables.g(String)` (absent in ours) → the engine's own fallback `GameData.log.a(String)`.
* **Fields — 77 names**: 75 exist on the same class with the same type in both; `b`/`c` differ on
  other classes but the engine reads them only on libGDX `Array` (items/size), identical in both.
* String-container read → inlined `"IM"`.

## D. Hook sites — each of ours is the same routine as theirs
Footprint + sequence comparison of theirs-hooked vs ours: GameScreen render (0.83), MessageRouter
(0.94), attack start (their extras: cheat-menu `mod_infinite_mana`, arcane mana), damage apply
(their extras: resist/spot log lines), PatrollerAI (0.94), CompanionAI, MonsterSpawn (their extras:
shops/events), NPC detection (their extra: SPOT_CHECK log), NPC update, NPC frames, level-load
fix-up (0.95), LoadGame, HUD draw (ours = bare `stage.draw`, theirs = draw + chat button),
renderer, minimap. **Fix found & applied:** the sprite hook now uses their exact condition
`!companionSpawn && lanPeerVisual` (a peer's companion keeps the companion sprite path).

## E. Runtime-safety checks on the built dex
* Every member the engine, glue and hooks reference resolves in our tree (hierarchy-aware).
* 15,279 invoke/field sites: static/instance/interface kind matches the declaration.
* D8 clean (min-api 21) on both builds; update gate OK.

## F. What the engine is (inventory)
* **Transport**: TCP **32124** (session), UDP **32123** (LAN discovery, `EK_DISCOVER`/`EK_HOST`),
  host-authoritative, **max 6 players**, plaintext and unauthenticated (anyone who can reach the
  host's IP can join — fine on a private ZeroTier/Tailscale network, not on open Wi-Fi).
  VPN interfaces (ZeroTier/WireGuard/Tailscale) are recognised for internet play.
* **Messages**: `JOIN`→`WELCOME`, `PLAYERS`, `STATE`, `PSTATE` (player snapshot), `PACT`
  (combat action), `PDMG2` (player damage), `NPCSTATE`/`NPCSTATE2` (world monsters), `CHAT`,
  `SYSTEM`, `CLOSE`, `ERROR`.
* **Player snapshot** (`PSTATE`, every **50 ms**): name, level/map ids, save slot, x/y, level,
  class/race/gender/portrait, sprite layers + name, facing/state/state time, action/spell +
  origin, speed, equipment ids (7 slots), visual-FX mask, stealth rank, skill list, missing HP/mana,
  combat-effect flags, gold, 5 reputations, and the same visual set for summon, companion and
  followers.
* **World monsters**: the host publishes each NPC's auth id, spawn row, tag/unique tag, x/y,
  facing, state and missing HP; on clients the matching NPC is a puppet (`ai_disabled=true`).
* **Per frame** (`tick`): rebuild peer actors from snapshots, apply host monster state, publish
  own state (≤20/s), host publishes monsters; writes `lan_pvp_active=1` and `pvp_fight_active`
  (1 while a peer is in the arena) into the **save's** game variables (used by arena content).
* **Peers** are NPCs built from a class base row (warrior `varannari_warrior`, rogue
  `human_archer`, cleric `varannari_druid`, wizard **`blue_wizard`** — theirs used `janod`), then
  class/race/gender/level/weapon from the snapshot; name labels, minimap pins, CHAT.
* **Lobby** (`LanLobbyActivity`, built in code): name (saved in `ek_lan_prefs`), Host, Join by IP,
  LAN search, chat; takes a Wi-Fi multicast lock for discovery.

## G. Known open items (by design, for phase 2)
Arena death rules (`Player`/`NPC` death hooks), world-map peer markers, world-event broadcast.

# Multiplayer port — the MP mod's engine onto our 4.2.2 base (Track C)

Owner's goal: full internet multiplayer + PvP, item drop/pickup between players and
trading, with the MP mod's menus. Recovered from the owner's
`Exiled-Kingdoms-Multiplayer-PVP-v1-3-1218-mod.apk` (Drive), compared against vanilla
`Exiled Kingdoms.apk` 1.3.1217 (Drive) and our base (4.2.2, versionCode 1207).

## 0. Why port onto our base (not re-sign their APK)
* Their APK is the 2025 Play build with Google **PairIP** (`com.pairip.application.Application`,
  `SignatureCheck` pinned to one cert). Re-signing it means defeating that — not done.
* Package is `net.fdgames.ek.android.multiplayer`: it could never update the owner's app.
* Our base has no PairIP; porting keeps `net.fdgames.ek.android` + our key → installs as
  an update, saves carry over (`tools/check_update_compat.sh`).

## 1. What the engine is
Second dex `classes2.dex`, package `net.fdgames.ek.android.lan`, 29 smali classes
(728 method refs — fits in our single dex: 32,936 + 728 < 65,536). The mod author's own code,
unobfuscated, no PairIP, no string-container indirection.

| Class | Role |
|---|---|
| `LanSessionManager` (+`$ClientPeer/$PlayerState/$FollowerState/$DiscoveryResult/$UiListener`) | sessions, TCP transport, UDP LAN discovery, chat, per-player state |
| `LanGameBridge` | snapshot sync: local player/companion/summon/NPC state out, peer state in; peers drawn as `NPC` with `lanPeerVisual=true` |
| `LanLobbyActivity` (+`$1..$9`) | host/join screen, built in code (no layout resources) |
| `LanGameBridgeChat*`, `…LocationButtonListener`, `LanSessionManagerChatSendRunnable` | chat + map-location UI |

Wire protocol (tab-separated lines over TCP, host-authoritative): `JOIN`, `WELCOME`,
`STATE`, `PLAYERS`, `CHAT`, `PSTATE` (player state), `PACT` (actions), `PDMG2` (player
damage), `NPCSTATE`/`NPCSTATE2`, `SYSTEM`, `CLOSE`, `ERROR`; discovery `EK_HOST` over UDP.
**Internet play:** no relay server; the code recognises ZeroTier/WireGuard/VPN interfaces,
so two phones on the same ZeroTier/Tailscale network behave as one LAN.

## 2. Symbol remap (their 1218 names → our 1207 names)
`tools/patch_multiplayer.py` rewrites every reference in the engine's smali. 128 of 178
external refs (all readable fields) match our base unchanged. Methods (matched by body
similarity, `/tmp/mp/work/mm.py`; ≥0.9 unless noted, low scores hand-verified):

| theirs | ours | note |
|---|---|---|
| `utils/a.a(Object)` | `utils/a.add(Object)` | libGDX Array |
| `Character.r1(String)` / `.v(String,I,String,DamageData)` | `b(String)` / `a(String,I,String,DamageData)` | |
| `CharacterInventory.s()` | `u()` | 0.73 — both "recompute from slots" |
| `CharacterResistances.e()` / `.f(I)` | `d()` / `a(I)` | |
| `NPC.B1()` | `y0()` | AI (re)build — read both |
| `NPC.L1()I` `M1()Z` `U1(I)` `k0()Z` `v0()` | `H0()` `I0()` `v(I)` `P()` `W()` | `v0`/`W` = sprite build (0.63, read) |
| `GameObject.q()I` `s()Z` | `m()` `i()` | |
| `Damage.b(String)` | `a(String)` | |
| `SkillSet.n()` | `f()` | bonus-set rebuild (0.84) |
| `MapActor.d0()` `q0(State)` `v0()` | `J()` `a(State)` `W()` | |
| `GameLevel.b()F` `g(I)` `h(I)` `j(String)` | `c()` `b(I)` `c(I)` `b(String)` | |
| `GameLevelData.e(MapEffectEntity)` `o()` | `a(MapEffectEntity)` `s()` | |
| `GameData.v()` | `O()` | |
| `GameVariables.e(I,String)V` | `b(String,I)Z` | **argument order swapped** |
| `Party.f()` `g()` `i()` | `c()` `d()` `g()` | |
| `WorldFactions.i(String)` | `c(String)` | |
| `GameString.b(String,Z)` | `a(String,Z)` | |
| `Rules.f(I)` `i(String)` | `c(I)` `d(String)` | |
| `Spawn.b(String)` | `a(String)` | |
| `GameAssets.o(String)` | `i(String)` | play sound |
| `ExiledKingdoms.f()` | `e()` | platform resolver |
| `GameMap(y0/b).r(IIII)` | `e/a/c/b.c(IIII)` | manhattan distance |
| `GameMap.A(I,Coords)` | — | not in our base → copied into the engine as a private static |
| `MapEffectEntity(F,I,I,I,String)` | `(I,I,I,String,Z,String,F)` | adapter: `(x,y,caster,"",true,id,delay)` |
| `MainActivity.u()` | — | their addition (opens the lobby) → added as `ekOpenLobby()` |
| `NPC.lanPeerVisual:Z`, `NPC.v0LanPeer()` | — | their additions → added |

## 3. Hooks into game classes (their method → ours)
| Where (theirs) | Ours | Hook |
|---|---|---|
| `GameScreen.c(F)` | `e/a/b/b.a(F)` | `LanGameBridge.tick()` each frame |
| `MainMenuScreen.c(F)` + LAN button (`x0/e.x`, listener → `MainActivity.u()`) | `e/a/b/e` | LAN ON/OFF button → lobby |
| `MessageRouter.a(String,I,I,String,F,DamageData)` | same | `mirrorCombatAction` at exit |
| `Character.v(String,I,String,DamageData)` | `Character.a(…)` | `publishPeerDamageIfNeeded` |
| `Character.E0(I)` | `Character.i(I)` | `publishLocalAttackStart` after attack state set |
| `Character.u(I,String,String)` (damage apply) | to find | `recordAppliedPeerProc` per applied proc |
| `PatrollerAI.c(I)` / `NPC.H1()` | `a(I)` / `E0()` | `resolveHostileTargetActor` — monsters also target peers |
| `CompanionAI.c(I)` | `a(I)` | `resolveCompanionAnchor` — follow the owner |
| `MonsterSpawn.Q()` | `y()` | spawn level += 2 × (players − 1) in a session |
| `NPC.<init>` ×2, `M(F)`, `Q()`, `v0()` | `<init>`, `a(F)`, `z()`, `W()` | peer flag init; peer update without AI; peer animation frames; peer sprite (`v0LanPeer`) |
| `NPC.X()` / `Player.X()` (death) | `E()` / to verify | arena elimination, respawn, "eliminated" chat |
| `GameLevelData.E(…)`, `Serializer.a(II)` | `a(GameLevelData)`, `a(II)` | never save peer NPCs |
| `GameHUD` (`z0/z.q()`, `z0/x.touchDown`) | `e/a/d/y` | CHAT button (+unread "CHAT!") |
| `GameLevelRenderer.q(F)` | `e/a/a/a.a(F)` | peer name labels |
| `ADTIsometricTiledMapRenderer.h()` | `e/a/c/a.e()` | peer pins on the minimap |
| `WorldMapImage.draw` | `e/a/d/r1.draw` | peer markers on the world map |
| `GameData.Z(F)` | `a(F)` | world-event chat broadcast (their feature, optional) |

Manifest: `+ <activity net.fdgames.ek.android.lan.LanLobbyActivity>` (new
`tools/axml_add_activity.py`, same clone technique as `axml_add_perms.py`) and
`+ ACCESS_WIFI_STATE, CHANGE_WIFI_MULTICAST_STATE` (INTERNET / ACCESS_NETWORK_STATE already present).

## 4. Phases
1. **Engine** (this spec): lobby, sync, chat, pins, monster scaling; arena death logic.
2. **Content + menus**: arena maps/conversations, redesigned UI art, mod menu/vault/upgrade.
3. **New features**: PvP anywhere (toggle), host-authoritative item drop/pickup, two-sided trade.

## 5. Deliberate deviations / APPROX
* Their redesigned menus are obfuscated 2025 UI classes; phase 2 rebuilds them on our UI
  classes from their art/behaviour rather than transplanting.
* Their difficulty slider, weapon-upgrade (`cdxUpgrade*`), SAF backup, `isPlayerModEnabled`
  mod-menu features are not part of the engine; considered in phase 2.
* Nothing here is device-tested; each phase needs two devices on one ZeroTier/Tailscale network.

## 6. Status — phase 1 shipped as v26 (2026-09-23)
Built by `tools/patch_multiplayer.py` (after all other patches) + manifest step in
`build_mod_4_2_2.sh`. `EK_SKIP_MP=1` builds without it. Needs `r8.jar` (D8) in `EK_LIB`.

* **Engine import:** 29 classes, 155 refs remapped; **reflection** (26 name-by-string calls:
  `invokeStatic/invokeObject/invokeInt/...`) rewritten per call site (19 renamed); the
  mod's string-container read inlined (`"IM"`); Portuguese-only chat/location strings → English;
  wizard peers built from `blue_wizard` (the MP mod used `janod`, which is our Janod
  companion row and would trigger his companion/homecoming hooks).
* **Enums:** all 8 enums touched were compared by name in `<clinit>` — identical, no remap.
* **Access:** 4 fields widened to public (`NPC.ai_disabled`, `GameObject.worldfactions`,
  `MapActor.actionStartTime`, `MapActor.destination`); every member the engine/glue/hooks touch
  resolves in our tree (hierarchy-aware scan), and all 15,279 invoke/field sites have the right
  static/instance/interface kind.
* **Glue** `EkMp` is Java (`tools/mp_java/src`), compiled against our-name stubs
  (`tools/mp_java/stubs`) and D8'd at build time; only `EkMp*` classes are spliced in.
* **Hooks B1–B17** as §3, installed: tick, combat mirror, peer damage, attack start, procs, patrol
  + detection targeting (12 reads, spot ids), companion anchor, spawn scaling, peer sprite /
  update / slash frames, save+load peer strip, HUD CHAT button, peer name labels, minimap pins,
  main-menu MULTIPLAYER button (both layouts).
* **Deferred to phase 2** (arena content): `Player`/`NPC` death arena rules, world-map peer
  markers, the world-event chat broadcast.
* **Manifest:** `LanLobbyActivity` (clone of MainActivity, exported=false) +
  ACCESS_WIFI_STATE, CHANGE_WIFI_MULTICAST_STATE.

APPROX (logged in DEOBFUSCATION_STATUS §3 C15): spawn scaling does not stack across respawns;
peer sprite keeps a peer visible when a weapon layer is missing; peer update runs movement/animation
only (no AI/regen — the engine syncs HP).

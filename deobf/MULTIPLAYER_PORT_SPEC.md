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

## 7. Phase 2a — arena rules, world-map peers, world events (v29)
Recovered from the MP mod (their → ours), installed by `patch_multiplayer.py` B18–B21; glue in `EkMp`.

| # | Theirs | Ours | Behaviour |
|---|---|---|---|
| B18 | `Player.X()` arena branch | `Player.E()V` prepend → `EkMp.arenaPlayerDeath` | On `H10_pvp_arena`: `pvp_arena_won=0`, `pvp_fight_active=0`, publish state, chat "[PVP] <name> has been eliminated!", `missingHP=0`, state `ActorState.b` (idle), publish; **no game over**. Elsewhere: normal death. |
| B19 | `NPC.X()` after `super.X()` | `NPC.E()V` after `MapActor.E()` → `EkMp.arenaPeerDeath` | A peer puppet (`lanPeerVisual`) dying in the arena is destroyed; if no other live peer (state ≠ `j`, not destroyed) remains → `pvp_arena_won=1`. APPROX: tests `CurrentLevel` (theirs `currentMapName`, never assigned, so their branch never ran). |
| B20 | `WorldMapImage.draw` (`z0/q1`) | `e/a/d/r1.draw` after the 4 red corners | `LanGameBridge.getPeerMarkerPairs(area, g)` → marker `c` at (x+px, y+py) size g, colours BLUE/GREEN/YELLOW/CYAN/MAGENTA by peer index; name from `getPeerMarkerNames` in `GameAssets.d0` at scale 0.5, white, above the marker; batch colour reset to RED. |
| B21 | `GameData.Z(F)` new dynamic event | `GameData.f()V` after `r().add(ev)` → `EkMp.worldEvent` | Event text `ev.e()` → game log (not in vanilla), and in a session chat ">>> [World Event] <text>" with `[BLUE]`/`[BLACK]`/`[]` stripped. |

Arena content (merged, `MP_CONTENT_SPEC.md`): `pvp_arena_master` (entry: needs `lan_pvp_active=1`, refuses while
`pvp_fight_active=1`; sets fight on, `pvp_arena_won=2`, **HideParty + LoseInventory** (stashed, not
destroyed), issues arena kit 523/525/380/601 + 2×5000, travels to `H10_pvp_arena`), `pvp_arena_exit`
(won → 200 gold; every outcome: kit removed, `RecoverInventory`, ShowParty, Teleport Scroll 6011),
`pvp_arena_chest` (manual `RecoverInventory`). All script actions are vanilla. The engine's
`tick` writes `lan_pvp_active`/`pvp_fight_active`; peers count as `enemy` while `pvp_arena_won ≥ 1`.

Also fixed: the engine calls `LanSessionManager.logLanError(String)` on one error path, but only the
`(String,Throwable)` overload exists (in the mod too) → forwarder added. Found by re-running the
member-resolution scan over the whole dex, intra-engine calls included.

## 8. Friends list (owner request — not in the MP mod)
Lobby row under Host / Join IP / Scan LAN / Leave: **Friends** and **Add friend** (`EkFriends`,
hooks B22–B24 in `patch_multiplayer.py`).
* **Remembered automatically**: every host you join (`joinHostAsync` → `EkFriends.remember`), most
  recent first, max 30. **Add friend**: name + IP (`ip` or `ip:port`, default port 32124).
* **Status**: opening the list sends the engine's own `EK_DISCOVER` UDP packet **straight to each
  friend's IP** on 32123 (1.2 s timeout, all friends in parallel, off the UI thread). A host answers
  `EK_HOST⇥name⇥port⇥players⇥max`; the list shows ● "hosting 2/6" or ○ "not hosting", and learns the
  friend's session name (decoded with the engine's `%09/%0A/%0D/%25` rules) for auto-added IPs.
  Unicast works over ZeroTier/Tailscale, where the broadcast "Scan LAN" does not.
* **Tap a friend** → Join (uses the name typed in the lobby, same path as Join IP) / Remove / Back.
* Stored in the lobby's prefs file `ek_lan_prefs`, key `ek_friends` (`name⇥ip⇥port` per line).
* Limits: a friend who is not hosting cannot be seen as "online" (the engine has no presence
  server); after a friend's VPN address changes, re-add them.

### §7 addendum (2026-09-23): world-map markers only worked in the big cities — fixed (B53)
The engine's `resolveMarker` gets an area's map cell via reflection `invokeStatic("…Areas", "g", level)`.
Our audit renamed their `h` → our `g` but missed that 4.2.2's `Areas.g(String)Coords` is an **instance**
method on `GameWorld.f` (the static-only `Areas.j` is fine). `Method.invoke(null, …)` threw, the catch
returned null, so a peer was drawn only in the four hard-coded cities (NG/FT/NI/IM). Now called exactly
as `WorldMapImage.a(String)` does: `GameWorld.f.g(level)`. Checked offline with the game's own code
(tools/init_harness + a direct `resolveMarker` call): v35 → null for H10/C11/G9/E10_cave/F6_temple;
v36 → their cells. All other reflective calls in the engine were re-checked for static/instance: OK.
Marker look (ours, APPROX): the marker texture is a 1x1 white pixel, which drawn at the area-cell size
covered the whole area; now a dot 40% of the cell, black outline, side by side for players sharing an
area, name scaled with the screen. There is no separate in-area minimap in 4.2.2 (the HUD map button
opens this world map; maps flagged `nominimap` disable it).

## v48 — "My address": Tailscale label was wrong on mobile data
Owner log (Fold host): `localIps=rmnet_data8=100.82.180.215 | wlan0=192.168.1.151` with no tun0.
`EkAuto.myAddresses` labelled any 100.64/10 address "Tailscale", but carriers use that CGNAT range on
the cellular interface, so the lobby offered an unreachable "Tailscale" address. Now: Android's
ConnectivityManager (by reflection; ACCESS_NETWORK_STATE is already granted) marks VPN (TRANSPORT_VPN)
and cellular interfaces; only VPN addresses are called Tailscale/ZeroTier, cellular ones are hidden
with a note, and when no VPN is visible the lobby says so (VPN off, or the game excluded in the VPN
app's split tunnelling). Name-based detection (tun*/zt*/wg*/rmnet*/ccmni*) stays as the fallback.

## v51 — "when I sent a message the other device stopped until it came through"
Reversed (LanSessionManager, MP mod): every packet write is synchronous on the calling thread.
`sendLine(PrintWriter,String)` and `ClientPeer.send(String)` do `print(line)`, `print('\n')`, `flush()`.
The GL thread calls them every frame (`publishLiveState`, `ekBroadcast`, `broadcastNpcState`,
`ekSendToHost`, EKWORLD/level snapshots), and `sendChat` holds `lock` while broadcasting. A socket write
blocks once the peer's receive window is full (Wi-Fi power-save, a busy peer, an internet hop), so the
game froze until the other side drained. The three calls were also not atomic across threads, so two
threads could interleave halves of lines.
Separately, a received CHAT runs `postChatAlert` → `LanGameBridge.postGameLog` on the reader thread. 4.2.2
has no `GameVariables.g(String)`, so it falls through to `GameData.O().log.a(String)`, which edits the
log's ArrayList and rebuilds the text the HUD is drawing at that moment: a cross-thread race with the
renderer.
Fix (B66): both writers call `EkNet.send`. From the GL thread (name `GLThread…`) the line goes into a
per-connection queue drained in order by a daemon sender; from other threads it's written directly. Every
write holds the PrintWriter's lock around print+newline+flush. `postGameLog` is wrapped so the log line is
added via `Gdx.app.postRunnable` on the game thread. Test: a GL-named thread queued 20,000 lines (~4 MB)
to a non-reading peer in 35 ms; all arrived in order once it read; mixed GL/reader sends → 0 corrupted lines.

## v55 — join request on the lobby; friends shown "not hosting" while hosting
1. `EkAuto.approveJoin` (hooked in `handleIncomingClient` after the `JOIN` line) showed its AlertDialog on
   MainActivity. While LanLobbyActivity is open it covers MainActivity, so the host only saw the request after
   leaving the lobby, and the joiner (waiting up to 45 s) thought joining failed. Now the dialog goes to the
   lobby when it's open (`EkFriends.lobbyIfOpen()`, registered in `relayoutLobby` and `remember`). The joiner
   gets a "the host has to accept you" toast.
2. Friends status: `EkFriends.probe` only sent one UDP `EK_DISCOVER` to 32123 with a 1.2 s wait. Over the
   internet only TCP 32124 is forwarded (UPnP maps TCP only) and mobile networks often drop the UDP, so hosting
   friends showed "not hosting". Also since v49 a typed `ip:port` was saved whole as the friend's address, which
   the probe then tried to resolve as a host name. Now: `remember` splits host/port (old entries are repaired
   on probe), two UDP asks of 1.5 s, then a TCP connect to the game port (3 s). A connection that sends no
   `JOIN` line is dropped by `handleIncomingClient` (readLine → null) without any prompt.

## v56 — players couldn't attack each other outside the arena
Reversed: peer puppets get `worldfactions` "enemy" (attackable) in `getOrCreatePeerActor` only when
`EkItems.pvpAnywhere()` is true (or inside `H10_pvp_arena` with `pvp_arena_won` ≥ 1); `receiveRemoteCombat`
applies peer damage outside the arena under the same check. `pvpAnywhere()` was `sessionPvp && inSession()`,
and `sessionPvp` was only set when the host flipped the toggle *while hosting*, or by `onClientJoined`, which
was never hooked. So the host's saved setting was ignored in new sessions, and guests never received EKPVP:
nobody could attack anybody outside the arena.
Fix: on the host `pvpAnywhere()` reads the saved setting directly; `EkItems.hostTick()` (from `EkAuto.tick`,
every 3 s) resends EKPVP to guests. Default is now ON (owner: "I can't attack the host, which is wrong");
"PvP everywhere" in My address still turns it off (an explicit OFF saved earlier is kept).

## v57 — PvP is each player's own choice (owner request)
"It should automatically make everyone PvP enabled but give a notification that PvP is enabled and give them
an option to turn it off. If the player turns it off it should stay off until they change it."
- Saved per phone (`ek_pvp_me`, default ON). The host-only "PvP everywhere" switch is replaced by **My PvP** in
  My address.
- Two players can hurt each other only when **both** have it on. Peer puppet hostility
  (`createPeerActor` / `getOrCreatePeerActor`) now asks `EkItems.pvpWithState/pvpWith(peer name)`; incoming
  player damage outside the arena (`receiveRemoteCombat`) asks `pvpAnywhere()` = in session and my PvP on.
  The arena works as before either way.
- Clients send `EKPVPME\t0|1` to the host every 3 s and on change. The host keys it by the name it knows that
  connection by (`ClientPeer.playerName`, which handles duplicate names like two "Player"s) and broadcasts
  `EKPVPT\t<name>=0|1…` every 3 s and on change. Unknown players count as off until the table arrives.
- On entering a session (host or guest) a dialog says "PvP is ON…" with **Keep PvP on / Turn PvP off**. If the
  player's choice is off, a short note says so instead.

## v59 — PvP opponent hostile to me only; world NPC jitter and slow health
Owner: "when PvP is on, if I join someone else's game every NPC and enemy counts me as a mob, even the player's
summons attack me" and "the enemy health isn't updated fast enough, NPCs and enemies are twitching trying to move
back and forth".

**Factions (reversed from `WorldFactions` / `data/world/factions.txt`).** An NPC aggroes on actors whose faction
its own `hostileto` list contains (`m0.b.d(Coords,int[],int)` → `WorldFactions.g(mine, theirs)`); projectiles and
skills use `g` in either order; "hostile to the player" is `f(arr)` = arr's list contains 100 (tap-to-attack, red
name, reputation loss on kill only when false, and only for codes 1..99). `enemy` (101) is listed by every town,
kingdom and guild faction (1..23) and by the player (100), so a puppet with faction `enemy` was attacked by
guards, townsfolk, companions and summons, while monsters (also 101) ignored it.
Now outside the arena the peer puppet keeps faction `player` and, when both players have PvP on, gets `neutral`
(105) as its **second** faction: `[100, 105]`, a pair no game data uses (`player,<x>` appears nowhere). With the
game's own rules that pair behaves exactly like `player` (105 lists nobody). Two wrappers add the PvP rule:
- `WorldFactions.a([I,Integer)` (4.2.2 `f`): a marked puppet is hostile to faction 100 (tap to attack, red name).
- `WorldFactions.a([I,[I)` (4.2.2 `g`): true only for the local player's own `worldfactions` array (identity; every
  actor gets its own array from `WorldFactions.c`) against a marked puppet, either order. Summons and companions
  are faction 100 too but have their own arrays, so they stay out of it; guards and townsfolk never list 100.
  Everything else falls through to the original check, so monsters still hunt the puppet like any player.
Setting it: `createPeerActor` passes the spawn faction through `EkItems.pvpFactionState` ("player" →
"player,neutral"); `getOrCreatePeerActor` calls `EkItems.pvpMarkActor` on return (sets/clears the 105 on a
`[100,x]` array, leaves the arena's `enemy` alone). The v57 hooks that jumped to the `enemy` branch are removed;
the arena (`H10_pvp_arena`) behaves as the MP mod made it.

**World NPC sync (host → joiner, `NPCSTATE2`).** The host sends every authoritative NPC (position, state, facing,
HP %) at 20 Hz (`LAN_PUBLISH_INTERVAL_MS` 50, from `tick`); the joiner applies the latest snapshot every frame
(`applyReceivedWorldNpcStatesV2`: 40 % step toward the host position, snap under 3 px or over 96 px, AI off,
`missingHP` from the %). Two causes of the reported behaviour:
1. Queue lag: since v51 every send from the GL thread goes through `EkNet`'s FIFO (up to 20 000 lines). The host
   queues a whole-level `NPCSTATE2` plus a `PSTATE` 20×/s; on a link slower than that (relay, mobile data) the
   queue grew and every update arrived later and later. `EkNet` now replaces a still-queued snapshot line with
   the newer one in place (`NPCSTATE2` per connection, `PSTATE` per player name); other lines keep their order.
2. Local drift: turning the AI off does not clear `MapActor.speedX/speedY`, so `MapActor.M` kept moving the NPC
   the way its local AI last chose while the snapshot pulled it back. `EkSync.remoteNpc` zeroes the velocity
   where the apply sets `ai_disabled`; the host position is the only thing that moves it.
Health: `EkSync.npcHp` replaces the `missingHP` store. More damage from the host applies at once; less damage
(regen/heal, or the host not having processed my hit yet) only after the host has said so for 1.2 s, so bars
no longer bounce back up after a hit. APPROX (the MP mod applied the host % directly).
Tested offline: 2 000 snapshots to a stalled link → 13 sent, newest last, all 20 chat lines in order, GL thread
never blocked; faction pair: me↔marked puppet hostile both orders, summon/guard/monster-hook false, unmarked
false, marked hostile to 100 but not to a town faction.

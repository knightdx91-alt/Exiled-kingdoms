# Shared world: host's world, your own character (owner decision 2026-09-23)

Owner: "Everyone plays in the host's world … you get to keep any items, levels etc that have changed
your character. That also falls into … items people have traded you or that you've killed other
players for." One save per player; no separate "multiplayer save". The 4.2.2 tablet is retired:
builds target the Fold only (still installs as an update, same key/package/versionCode rule).

Today (v28) each device runs its own save/world; a session only shares visible actors, monsters on the
same map, damage and chat. This spec replaces that with a host-owned world.

## 1. What is "character" and what is "world" (from GameData / Player fields)
| Goes home with you (character) | Stays with the host (world) |
|---|---|
| `player` (sheet: level/XP/attributes/skills/effects, `gold`, quick slots, `areasVisited`, recover/endurance use) | `CurrentLevel`/`NewArea`, map/level data, `deadNPCs`, `lootedContainers`, containers, plants, shops, beds |
| `backpack`, equipment (in the sheet) | `quests`, dynamic quests/events, `gameTime`, day/hour checks |
| `party` (your companions and their gear) | vaults & bags of holding (they are world containers) |
| character game variables: `REP_*` reputation, `item_upg_*` upgrades, `know_*`, other per-character flags (list recovered in phase B, rule in §4) | every other game variable (quest/world state) |

## 2. Flow
* **Solo start**: the game hosts in the background (auto-host) — see §5 safeguards.
* **Join** (Settings → Multiplayer → address, or tap a friend): (1) save your own game normally —
  this is your *home save*; (2) stop your host; (3) receive the host's world snapshot (the host
  serialises its GameData with the game's own save serializer, minus its character block); (4) load
  it, graft your character block in, appear next to the host.
* **While joined**: the host is the authority for the world. World changes a joiner causes (chest
  looted, NPC killed, quest variable set, door/toggle) are sent to the host and re-broadcast; the
  joiner applies the host's updates. **Saving on the joiner never writes the host's world**: a save
  (manual, inn, auto) only refreshes the character block of the home save.
* **Leave / disconnect / host quits**: take your character block, reload your home save, graft it
  in, save. Everything you gained — XP, levels, gold, loot, traded items, PvP loot — is kept; the
  home world is exactly as you left it.
* **Host** saves the world as normal (its own character included). Joiners' characters are never
  stored in the host's save.

## 3. Items between players (phase D)
Trade window (both confirm), drop on the ground → any player picks it up, PvP kill → loot the
loser's drop. All go through the host so an item can't be duplicated or lost; the receiver's
backpack is part of their character block, so it goes home with them.

## 4. Character-variable rule (to verify in phase B)
Classify every variable name the game and its content set (`SetVariable`, script actions, engine
constants): character = reputation, upgrade levels, skill/trait unlock flags, per-character counters;
world = quest stages, NPC/area/door state, world events. Unknown → world (safe default: at worst a
flag is re-earned; never corrupts the home world).

## 5. Auto-host safeguards
1. **Join approval**: "<name> wants to join — Allow / Deny"; friends skip the prompt.
2. **Solo is not a session**: `lan_pvp_active`, per-frame sync and spawn scaling only when ≥2 players.
3. **Switch** in Settings → Multiplayer to turn auto-host off.

## 6. Phases
A. Auto-host + in-game Multiplayer settings (own address, join by address, friends) + safeguards.
B. Character block: extract/graft/serialise; variable classification; home-save protection.
C. World sync: snapshot on join, joiner→host world ops, host broadcast, join/leave/disconnect.
D. Items: trade, drop/pick up, PvP loot.
Each phase is reversed from the real classes first (Serializer, GameData, Party, WorldContainer…)
and its numbers/flow pinned here before coding.

## Status
**Phase A — done (code).** `EkAuto` + hooks B25–B29 in `patch_multiplayer.py`:
* keeper in the game-screen tick (every 3 s): hosts with the lobby name (else the character's) when
  auto-host is on, you're in game, not hosting/connected, and no join started in the last 20 s;
  `joinHost` already stops the local host; after leaving, the keeper re-hosts.
* `isSessionRunning()`/`isInSession()` = connected, or hosting with ≥2 players — so the arena gate
  (`lan_pvp_active`), per-frame sync, spawn scaling, CHAT button all stay off while alone.
* `handleIncomingClient`: after reading `JOIN⇥name`, `EkAuto.approveJoin` — a friend's IP passes;
  otherwise the host gets "Allow + add friend / Allow once / Deny" (45 s timeout = deny); denied →
  `CLOSE⇥The host declined the join request.` and the socket is closed.
* Lobby row: Friends / Add friend / **My address** (VPN addresses first: ZeroTier `zt*`, Tailscale
  100.64/10, `tun`/`wg`; plus the auto-host ON/OFF switch, pref `ek_autohost`).
* In game: Options → **MULTIPLAYER** opens the lobby.

## 7. Phases B + C — technical design (reversed 2026-09-23)
**Save format.** `Serializer.d(slot,sub)` (Save; Options "Save & Exit" uses sub 0) builds
`SaveGameData{gamedata=GameData.O(), leveldata=GameLevelData.s(), queue=MessageRouter.a(),
version}`, clears `player.conversations`/`activables` first (as every save does), `Json.prettyPrint`s
it, Base64-encodes it (`Serializer.b`) and writes `data/saves/<slot>/game*.sav`. `Serializer.a(slot,sub)`
(LoadGame) reads, Base64-decodes (`Serializer.a(String)`), `fromJson(SaveGameData)`, then
`GameData.a(gamedata)` and starts the game screen at `CurrentLevel` with `leveldata`. `GameData`'s
constructor has no static side effects, so extra `SaveGameData` objects can be parsed on the side.
The load screen only lists slots 0–9 (`Serializer.a[]` latest-sub table, `e(I)`), so **slot 42 =
guest world**, invisible in the UI and never indexed in that table.

**Character block** (`EkShare.Block`, the game's own Json): `player`, `backpack`, `party`, the
companion NPC actors (level `npcs` whose tag is in `party.companions`), and the character variables
(`REP_*`, `know_*`, `item_upg_*`; `GameVariables.variables` via a new accessor). Captured with
`activables`/`conversations` cleared and then restored, as the save does.

**One graft path.** `LoadGame` is hooked right after the save is parsed and before
`GameData.a(gd)`: if a pending block file exists for the slot being loaded
(`data/saves/ek_block_<slot>.json`), it replaces `player` (keeping the position of the one it replaces),
`backpack`, `party` and the character variables, swaps the old party's companion NPCs in the level for
the block's, and for slot 42 sets `gamedata.slot = 42` so every save while joined goes to the guest
slot. The home block file is deleted only after a successful save to the home slot (hook around
`Serializer.d`), so a crash anywhere just re-applies it on the next load of home.
* **Join** (only from inside a game): Save & Exit–style save of home (sub 0), capture block, remember
  home slot, `joinHost`. On `WELCOME` the client sends `EKWREQ`; the host builds a snapshot on the game
  thread (`Serializer.ekSnapshot()`, a clone of the save minus the file write) and sends
  `EKWORLD⇥<base64>`; the client writes it to slot 42, writes the block for 42, loads slot 42.
* **While joined**: each save to 42 refreshes `ek_block_<home>.json` with the live character.
* **Leave / disconnect / host quits** (keeper sees guest mode + not connected): capture block → write
  `ek_block_<home>` → load home latest (`Serializer.f(home)`) → graft → save home (sub 0) next tick.

**World sync (C).** Host = authority; lines on the engine's TCP link:
`EKVAR⇥name⇥value` (world variables only: set through `GameVariables.b(String,I)`),
`EKDEAD⇥tag` (`GameData.l`), `EKLOOT⇥id` (`GameData.i`). A joiner's change goes to the host, the host
applies it through the game (so quest side effects run) and its own hooks broadcast to every client;
applying a received change is done with forwarding suppressed. Vault button / bag tabs are disabled
for guests (vaults are the host's world storage; a guest taking from a copy would duplicate items).

**Phases B + C — done (code).** `EkShare` + hooks B30–B40 (`patch_multiplayer.py`) and the guest
bag block (`patch_mp_features.py`); design as §7. Checked: `Player`'s `containers/shops/toggles/loots/
plants/beds/restpoints/mapCastles` are per-frame proximity caches rebuilt in `Player.a(F)`, so a
grafted player carries no level state. Variables `lan_*`/`pvp_*` (written by the engine every frame,
per player) are never synced; a variable is only sent when its value changed. Known limits: areas
other than the host's current one start from the game's defaults for the guest (their per-level
cache lives in the host's `data/saves/<slot>/cache/`), except looted chests and killed uniques,
which are synced lists; joining from the main menu (no character loaded) only chats.

## 8. Phase D — items between players (reversed 2026-09-23)
**Drops.** Vanilla drop = `CharacterWindow.t()` → `GameLevel.a(x,y,item)` → `new Loot(x,y,item)` →
`GameLevelData.a(Loot)`. `Loot` (a `MapSprite`, so it has the public `tag`) holds `Items` + `gold`;
taking goes through `removeItem(slot)` (one unit), `d()` (gold) and `b()` (take all: `d()` + clear).
In a session every new drop gets `tag = ekdrop_<random>` and is announced
`EKDROP⇥id⇥level⇥x⇥y⇥gold⇥itemId:count,…`; players on that level spawn the same bag
(`new Loot(x,y,ArrayList<Integer>,gold)`). The **host keeps the registry** of what is still in each
shared bag. Taking is optimistic, then arbitrated: `EKPICK⇥id⇥item⇥player` (or `EKPICKG⇥id⇥gold⇥player`)
→ host: still there → `EKGONE…` to everyone (others remove it from their copy); already taken →
`EKDENY…` to that player, whose game takes the unit back out of the backpack (gold back out of the
purse). So an item can't be duplicated by two players grabbing it at once.

**PvP anywhere.** Host setting "PvP everywhere" (lobby → My address; default **off**), announced as
`EKPVP⇥0/1` (on WELCOME and on change). When on, peers are hostile everywhere (the engine's arena
branches in `createPeerActor`/`getOrCreatePeerActor` take the "enemy" path), and a lethal peer hit
outside the arena goes through the engine's elimination branch of `receiveRemoteCombat` (you get up
with full HP, "[PVP] name has been eliminated!") and then **you drop a loot bag at your feet: one random
item stack from your backpack (never equipped gear) and 10 % of your gold** — a shared drop anyone
can pick up, so the winner keeps it and it goes home with them. Deaths to monsters stay vanilla.

**Trade.** Lobby → **Trade** → pick a player. Messages `EKTRADE⇥to⇥from⇥kind⇥payload` go through the host
(broadcast; each device acts on its own name): REQ → accept/decline → each side picks backpack items
(+ gold) and taps Ready (OFFER) → both see "You give / You get" and Confirm (CONFIRM with the pair of
offers) → when a device holds both confirmations for the same offers it verifies its own items are
still there, removes them and adds what it receives (full backpack → dropped at your feet). Cancel
at any point.

**Phase D — done (code).** `EkItems` (shared drops, host registry/arbitration, PvP-anywhere and the
PvP loot drop) + `EkTrade` (trade dialogs, execute on the game thread) and hooks B41–B48
(`patch_multiplayer.py`): `GameLevel.a(III)` tags/announces new drops, `Loot.ekSetGold`, wrappers on
`Loot.removeItem`/`d()`/`b()`, `receiveRemoteCombat` (PvP anywhere → elimination branch → loot drop),
`createPeerActor`/`getOrCreatePeerActor` hostility. Lobby: **Trade** button; My address: "PvP
everywhere" switch. Static checks pass (access, invoke kinds, D8, update gate); not device-tested.
Known limit: a trade whose items change between Ready and Confirm is cancelled on that device only
(the other side may already have executed) — rare, logged as a race risk.

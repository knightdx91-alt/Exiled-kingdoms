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

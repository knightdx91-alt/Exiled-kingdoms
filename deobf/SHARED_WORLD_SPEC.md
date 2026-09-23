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

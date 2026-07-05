# Mod analysis — Exiled Kingdoms Multiplayer (v2.0 / PVP v1.3)

Decompiled the mod APK (`Exiled-Kingdoms-Multiplayer-PVP-v1-3-1218-mod.apk`,
381 MB) and diffed it against our decompiled base (130 MB). Summary of every
meaningful change. The APK itself is NOT in git (third-party, large); it lives in
the owner's Google Drive.

> Ownership: the multiplayer engine, PVP, and new quests are **winlatorbrasil's**;
> the added sprites are from the **Sorrow Mod** (tatanaandatun). Both are separate
> creators from the base-game author. Analysis is fine; shipping any of it needs
> their permission.

## Code additions

New **unobfuscated** package `net.fdgames.ek.android.lan` (2nd dex, 7 classes, ~7.1k lines):

| Class | Lines | Role |
|---|---|---|
| `LanGameBridge` | 3983 | State-snapshot sync engine: captures local player/companion/summon/world-NPC state and applies peer snapshots (motion, combat, equipment, skills, visual FX). Peers rendered by reusing the base `NPC` class. |
| `LanSessionManager` | 2484 | Sessions, transport, chat, `PlayerState`. |
| `LanLobbyActivity` | 430 | Native Android LAN lobby screen (host/join, IP, LAN search). |
| `LanGameBridgeChatRunnable`, `…ChatButtonListener`, `…LocationButtonListener`, `LanSessionManagerChatSendRunnable` | ~240 | Chat + map-location marker UI/threads. |

- **Transport:** LAN discovery via **UDP broadcast** (`DatagramSocket`) + reliable
  data over **TCP** (`ServerSocket`). Host-authoritative.
- **Base classes patched to call the bridge** (8): `Player`, `NPC`, `Character`,
  `GameData`, `MessageRouter`, `CompanionAI`, `PatrollerAI`, `MonsterSpawn`; plus
  `ScriptedAction` (quest VM) for PVP/upgrade logic.
- `net/fdgames/Rules/zrcH/pgtXvpMCFu` — **not a feature**; a string-obfuscation
  container (garbage-named `String` fields) from the mod's build tooling.

## Screens / UI added
- **LAN Lobby** (native `LanLobbyActivity`).
- **LAN chat overlay** + **map location button** (see peers on the map).
- **PVP Arena UI, mod menu, vault/safe shortcut, difficulty slider, weapon/item
  upgrade UI** — built from the redesigned "medieval" UI graphics + modified
  (obfuscated) UI classes.

## Content additions (bulk of the 3× size, all standard formats)
| Asset | Base → Mod |
|---|---|
| sprites | 321 → 684 (+363, Sorrow Mod) |
| graphics | 209 → 610 (+401) |
| conversations | 3,765 → 12,268 (+8,503) |
| tmx maps/tilesets | 343 → 591 (+248, incl. PVP Arena) |
| quests | 799 → 891 (+92) |
| sounds / music | +311 / +31 |
| world / rules | +13 / +8 |
| languages | +1 (French) → CZ/DE/FR/IT/PL/PT/RU/TR |

- **PVP Arena** is implemented as **content** (new maps + conversations
  `FT_arena_*`, `pvp_arena_*`, Arena Master NPC, tribune) driven by patched
  `ScriptedAction`, with the LAN layer syncing the duel.

## Implication for the web port
- The **multiplayer engine will not port as-is** — native Android (`Activity`,
  Java UDP/TCP, `WifiManager`). A browser version needs **WebRTC / WebSockets**.
  The *design* (host-authoritative snapshot sync, peers-as-NPCs) is a good
  reference.
- The **content** (maps, quests, dialogue, arena) is the same `.tmx`/`.txt` data
  our tooling already handles — reusable with the creators' permission.

---

# Sorrow Mod & ENB — diff vs the base game

Both decompiled + asset-diffed against our base extraction of `Exiled Kingdoms.apk`
(130 MB, 5,808 asset files, 185 game classes). Reproduce with
`tools/extract_mod.sh <apk> recovered_mods/<name>`, then compare `assets/assets/data`.

**Headline: neither is a code mod.** Unlike the Multiplayer mod (which adds the
`net.fdgames.ek.android.lan` package + native UDP/TCP), **Sorrow and ENB add zero
new game classes** (only the auto-generated `BuildConfig`) and **no new native
libs**. Every base class shows text differences, but that is R8/jadx obfuscation
noise (member names are reshuffled on each build), not logic changes. Both are
**pure content / asset mods**.

## Sorrow Mod (`EK_SM_7.5_English_text.apk`, 366 MB)
A large **content expansion** — adds content everywhere, removes almost nothing
(1 conversation file). Ships both `arm64-v8a` and `armeabi-v7a` native libs.

| Category | Added | Modified |
|---|---|---|
| conversations | +752 | 212 |
| graphics | +397 | 100 |
| sprites | +363 | 7 |
| sounds | +311 | — |
| tmx (maps/tilesets) | +245 | 108 |
| quests | +52 | 12 |
| music | +31 | — |
| world | +13 | 22 |
| rules | — | 15 |
| ui | +1 | 14 |

**2,165 files added, 490 base files modified, 1 removed.** The added sprites match
what the Multiplayer mod pulled in from Sorrow (that mod credits Sorrow for its
sprite set).

## ENB (`EK_ENB_eng.apk`, 397 MB)
A **graphics/asset overhaul packaged as an English-only build**. The big "removed"
count is entirely the **non-English language folders** (CZ/DE/FR/IT/PL/PT/RU/TR
under `conversations/`, plus their `quests`/`rules`) being stripped — not lost
content. Ships **only `armeabi-v7a`** (32-bit); drops the base's `arm64-v8a`.

| Category | Added | Modified | Removed |
|---|---|---|---|
| sprites | +459 | 282 | 8 |
| sounds | +335 | 34 | — |
| tmx | +287 | 83 | 9 |
| conversations | +225 | 299 | 2,696 (non-EN langs) |
| graphics | +121 | 195 | — |
| quests | +24 | 15 | 579 (non-EN langs) |
| music | +22 | 22 | — |
| world | +16 | 24 | — |
| ui | +8 | 42 | — |
| rules | — | 21 | 30 (non-EN langs) |
| particle | +1 | 8 | — |

**1,498 files added, 1,025 base files modified, 3,322 removed** (the removals are
localization, not gameplay). The heavy `sprites`/`graphics`/`tmx` modification
counts are the actual ENB visual overhaul.

## Implication for the web port
Both are **directly reusable data** for Track B (same `.tmx`/`.txt`/PNG formats our
tooling handles) — no native code to reimplement, unlike Multiplayer. ENB's English
-only layout is actually convenient for a first web build. **Shipping any of it
still needs the creators' permission** (Sorrow Mod = tatanaandatun; ENB authors).

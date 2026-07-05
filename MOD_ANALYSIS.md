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

## Not yet analyzed
Two more mod APKs are in the owner's Drive, not yet diffed:
`EK_SM_7.5_English_text.apk` (Sorrow Mod) and `EK_ENB_eng.apk` (ENB graphics).

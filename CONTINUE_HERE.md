# Exiled Kingdoms — project handoff / continue here

> **See `README.md` first for the quick orientation + the standing findings**
> (locked doors are conversations; `Summon#` is native and NPC summons stack;
> mod diffs; Elisse has no quest). This file remains the detailed handoff.

Everything done so far, how to reproduce it, and what's planned. Start here.

The goal: **a browser-playable version of Exiled Kingdoms** (Chrome on Android),
offline-capable, with player-chosen orientation and browser-local saves — rebuilt
from the recovered game (the original project files were lost in a drive crash).

There are **two tracks** in this repo. Track B is the recommended path to a
shippable web game; Track A is the source-recovery that feeds it.

---

## ⚠️ OPEN WORK — the mod APK (read this first, updated 2026-09-22)

> **2026-09-23 owner decisions:** the Android 4.2.2 tablet is retired — ship the **Fold build only**
> (still must install as an update). Multiplayer moves to a **shared world** (host's world, each
> player keeps their own character): `deobf/SHARED_WORLD_SPEC.md`.

Track C (not A/B): modding the owner's game APK. It started as a 4.2.2-tablet build; the
owner has since moved to a **Galaxy Z Fold 8**, so there are now **two artifacts** with
the same feature set and the **same signing key**:

| Device | APK | Built by |
|---|---|---|
| **Galaxy Z Fold 8** (Android 16, 64-bit-only, foldable) | `ExiledKingdoms-hero-v49.apk` | `EK_MP_APK=<mod apk> build_mod_4_2_2.sh`, then `build_modern_compat.sh` |

Direct downloads from Pages (the repo stores each as 25 MB split parts because of
GitHub's 100 MB file limit; `.github/workflows/deploy.yml` reassembles them):

```
https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v49.apk
```

### v49 (2026-09-23) — internet play without extra apps (router port opening)

Owner: friends in other states on home Wi-Fi, no Tailscale, no manual port forwarding, free. New
`EkNat`: while hosting, UPnP-IGD asks the home router to forward TCP 32124 (falls back to 32125..32134
on conflict, lease 3600 renewed / permanent if the router insists), shows "Internet (friends
anywhere): <public IP>[:port]" in My address, removes the mapping when hosting stops. Detects
provider-shared addresses (CGNAT) and says to use Tailscale. Join by IP accepts host:port. Router
HTTP goes over a raw socket (SDK 29 blocks cleartext HttpURLConnection). Tested against a fake
router offline; not yet on a real router. `deobf/NAT_UPNP_SPEC.md`. B64. dist: base + v49 + v48.

### v48 (2026-09-23) — NPC portrait crash + Tailscale address label

Owner EK_crash.txt: talking to some NPCs crashed (portrait index 116, cache of 116). B63 sizes the
portrait caches like the MP mod (358 male / 222 female) and clamps bad indices. Lobby "My address"
labelled the mobile-data CGNAT address (rmnet, 100.x) as Tailscale; now uses ConnectivityManager to
tell VPN from cellular and says when no VPN is visible. `deobf/MP_CONTENT_SPEC.md`,
`deobf/MULTIPLAYER_PORT_SPEC.md` v48. dist: base + v48 + v47.

### v47 (2026-09-23) — Details overlap + summon route chooser, fixed from measurements

Owner screenshots showed both v44/v45 fixes failing. Checked this time in an offline layout sim (the
game's own libGDX classes + real tahoma25 metrics). Details: value sentences are broken into explicit
lines at the cell width (EkUi.prewrap, B61) and libGDX wrapping is off, so row height = drawn lines.
Route chooser: l1 hard-codes its pref size and show() packs to it; eksp now overrides
getPrefWidth/Height (EkUi.dialogPrefWidth/Height), text + 3 buttons fitted inside.
`deobf/UI_SCALING_SPEC.md` v47. dist: base + v47 + v46.

### v46 (2026-09-23) — Summon text shows only your chosen route

Owner: after picking a route the Summon skill still described all three. The skills2.txt cells are
now `@@generic~~undead~~arcane~~beast`; the two description getters (`Skill.a()`,
`SkillLevel.a()`, the only readers) pick the entry for `summon_path` (generic until chosen).
Also: `summon_path` is now a per-character variable in shared world (`EkShare.isCharVar`), so a
guest keeps their own route. `deobf/SUMMON_ROUTES_SPEC.md` §8. dist: base + v46 + v45.

### v45 (2026-09-23) — summon screens: no cut-off text

Beta tester: text cut off in the summon skill screens (by the back button / screen edge). The route
chooser (a fixed 700x240 SimpleDialog) now grows to its 5-line prompt + buttons (B62); the skill
description panel scrolls and resets to the top per skill. `deobf/UI_SCALING_SPEC.md`. dist: base + v45 + v44.

### v44 (2026-09-23) — Details: each row's height pinned to its wrapped sentence

Owner's screenshot (Character Stats): rows still one line tall, wrapped lines ("attacks.", "secrets.",
"rumours.") on the next row. B61 `EkUi.fitCell`: after the value cell gets its 480xS width, the label is
measured at that width and the cell height set to it. dist: base + v44 + v43.

### v43 (2026-09-23) — Details: description sentences no longer run over the rows below

Owner: the overlapping thing in Details is a sentence. The value column (wrapped sentence, 480xS) was
measured while its width was 0 → row too short → extra lines over the next rows. B60 sizes it to its
column first. `deobf/UI_SCALING_SPEC.md`. dist: base + v43 + v42. (Disk note: clean /tmp/ek old builds.)

### v42 (2026-09-23) — Details: stat names stay on one line (no overlap)

Owner: Details → character stats still wraps and overlaps the rows below. v40's wrap was the cause (the
row is sized before the label knows its wrapped height). Names now stay one line, shrink to fit the
240xS column (≥60%), "..." beyond. `EkUi.wrapLabel`, `deobf/UI_SCALING_SPEC.md`. dist: base + v42 + v41.

### v41 (2026-09-23) — Hero: all skills on one scrollable page

Owner: one long scrollable skill page instead of the HERO/ROGUE/CLERIC/MAGE pager. `patch_hero_class.py`
§7: SkillWindow's button array 20→44, ROGUE/CLERIC/MAGE sections added after the Hero's own (same
widgets/filter/listener/sizes), list in a vertical ScrollPane, pager button removed, highlight loop over
all buttons. Spec: `deobf/HERO_CLASS_MOD_SPEC.md`. dist: base + v41 + v40.

### v40 (2026-09-23) — character sheet: Hero page button sized, Details rows don't overlap

Owner: the skill-page switch button is tiny; words overlap in the Details screen. Hero pager button now
scaled/sized like the window's Details button (`patch_hero_class.py` §3d v4); Details window name column
wraps in its own width instead of running into the value (B59). `deobf/UI_SCALING_SPEC.md`. dist: base + v40 + v39.

### v39 (2026-09-23) — save slots: name and class/level no longer overlap

Owner: on the save list the character name is sometimes too big / not above class+level / on top of it.
Vanilla layout bug (wrapped labels without a width); `EkUi.fixSlot` (B58), `deobf/UI_SCALING_SPEC.md`.
dist: base + v39 + v38.

### v38 (2026-09-23) — shared kills: only other players get a share

Owner: split only between actual players, never for your companions/summons. Verified in the game code
(summons are tagged `player_summon` only when cast by you or a party companion) and hardened: other
players' summons are no longer tracked as monsters either. `deobf/KILL_REWARDS_SPEC.md`. dist: base + v38 + v37.

### v37 (2026-09-23) — shared kills: XP and loot split by damage

Owner: both players fighting a monster must both get XP/loot, split by damage dealt, companions and
summons included. Before: the last hitter got everything (a peer's replayed hit counts as an NPC kill on
the other device). Now (`deobf/KILL_REWARDS_SPEC.md`, `EkKill`, B54–B57): per-monster damage ledger by
side; the killer's device sends the rolled loot + XP to the host, other devices send their own side's
damage; host splits XP/gold by share and gives each item to one side weighted by share; each player gets
a personal bag where it died (or into the backpack if they left). Solo = vanilla. dist: base + v37 + v36.

### v36 (2026-09-23) — other players now show on the map everywhere

Owner asked to check the minimap. 4.2.2 has no in-area minimap; the HUD map button opens the world
map, where peers are drawn (B20). Bug: outside the 4 hard-coded cities their marker never appeared —
the engine called 4.2.2's instance method `Areas.g` as static via reflection (B53 fixes it; verified
offline by calling the real `resolveMarker`). Markers are now a dot with the name, not a block covering
the area. Details: `deobf/MULTIPLAYER_PORT_SPEC.md` §7 addendum. dist: base + v36 + v35.

### v35 (2026-09-23) — UI scaling audit: button text keeps its proportions

Owner asked what else doesn't scale. Audit in `deobf/UI_SCALING_SPEC.md`. One more real bug (vanilla):
the skin's `menu-button-font` was scaled (w/1280, h/720) separately, so button text was stretched on the
Fold (inner ~1.2:1 → tall/narrow; outer 21:9 → wide). B52 makes it uniform `min(w/1280, h/720)`, the
scale most windows use. Everything else checked out. dist: base + v35 + v34.

### v34 (2026-09-23) — checkboxes sized for the screen

Owner: checkboxes in the menus are "SUPER small". Vanilla bug on high-res screens: libGDX `CheckBox`
draws its image with `Scaling.none`, i.e. at the 20x20 png size, ignoring the `20 x (height/720)` cell
size the Settings/Options windows set (journal filters don't size it at all). `EkUi.scaleCheckboxes`
(B51) wraps the shared style's drawables so they report 20 x height/720 (live; fold/unfold safe).
Sliders were already scaled by the game. Spec: `deobf/UI_SCALING_SPEC.md`. dist: base + v34 + v33.

### v33 (2026-09-23) — lobby: Back button + current texts

Owner: the Multiplayer screen needs a Back button, and asked why it said sync "will arrive in phases".
That subtitle (and the "LAN / CHAT BETA" title) were the MP mod author's placeholders from before
sync existed. Now "MULTIPLAYER" + a current description (B24b), and a "< Back" row at the top
(`EkFriends.addLobbyRow`, closes the lobby). `dist/`: base + hero-v33 + hero-v32 (fallback).

### v32 (2026-09-23) — fixes the startup crash of v29–v31

Owner's `EK_crash.txt`: NPE `Quests.b` at the loading screen. Real cause, earlier in the log:
`Error in ExiledKingdoms.initialize: For input string: "m_118"` — the MP content's bestiary uses
gendered portrait ids our 1207 parser can't read, so game data init aborted before quests loaded.
Every build since v29 (the first with the content pack) crashed at launch.
* New `tools/init_harness/run.sh <apk>`: runs the game's own data loaders + every conversation offline
  (dex2jar on the desktop JVM). Hard gate in `build_mod_4_2_2.sh` — a build that would crash like
  this now fails instead. Details: `deobf/MP_CONTENT_SPEC.md` "Format drift fix-ups".
* `merge_mp_content.py` repairs the bestiary rows and 19 broken MP conversations (editing damage +
  names the 1207 engine lacks). v32: init OK, 1284/1284 conversations parse.
* `dist/` = base + `hero-v32` only (v29–v31 all crash at launch; no working fallback to keep).
  This release shares no split parts with v31 (715 conversation files normalised); later ones will.
* Offline check can't cover textures/sounds/map rendering. Still not device-tested.

### v31 (2026-09-23) — shared-world gaps closed; dist cleaned

Spec: `deobf/SHARED_WORLD_SPEC.md` §9. Hooks B49–B50.
* **Other areas**: the host's level cache (`data/saves/<slot>/cache/`) arrives with the world; the guest
  world loads as sub 0 (`auto.sav`) so LoadGame keeps it; every area exit shares that area's cache
  (host → all; guest → host, which keeps it unless it's standing there → all).
* **Join from the main menu**: your newest save's character comes along (home = that slot/sub).
* **Vaults and bags of holding** are yours: they travel in the character block (guest blocks removed).
* **Trade** is settled by the host: both sides escrow, host says COMMIT/ABORT; 60 s no answer = refund.
* `dist/` now holds only: `ExiledKingdoms-base-4.2.2.apk` (the clean build input, sha `5fc7c866…`),
  `hero-v31` (current) and `hero-v30` (fallback). Old cheat/tablet/hero builds removed.
  14 of 15 parts are shared between v30 and v31.
* Static checks only. **Nothing device-tested yet.**

### v30 (2026-09-23) — shared world: host's world, your own character (phases A–D)

Spec + status: `deobf/SHARED_WORLD_SPEC.md`. Java in `tools/mp_java/src/.../lan/Ek{Auto,Share,Items,Trade}.java`,
hooks B25–B48 in `patch_multiplayer.py`.
* **A** auto-host while you play (Options → MULTIPLAYER → My address has the ON/OFF switch); strangers
  need the host's Allow (friends skip it); solo never counts as a session.
* **B+C** one save per player: joining saves home, receives the host's world (slot 42, hidden), grafts
  your character (player, backpack, party, companions, REP_/know_/item_upg_ vars); leaving/disconnect
  takes your character home and saves it. Host is authority for world vars, kills, looted chests.
* **D** shared ground drops (host arbitrates pickups — no dupes), "PvP everywhere" host switch (loser
  drops one random backpack stack + 10 % gold), Trade window (lobby → Trade).
* Dedupe confirmed on the real release: 14 of 15 parts identical to v29.
* Static checks only (access, invoke kinds, D8, update gate). **Nothing device-tested yet.**

### v29 (2026-09-23) — Fold only, WITH the MP content pack; releases now dedupe in git

Same code as v28 plus the mod's content (maps incl. the PvP arena, quests, sprites, menu art, music):
built with `EK_MP_APK=<Exiled-Kingdoms-Multiplayer-PVP-v1-3-1218-mod.apk>` (owner's Drive). ~374 MB.
`build_modern_compat.sh` now runs `tools/zip_stable_order.py`: unchanged entries first (sorted,
fixed timestamps), the files a release edits last (`tools/apk_volatile_entries.txt`). Measured: after
editing one text file, 14 of 15 split parts were byte-identical, so a new release adds ~6 MB to git.
Keep that list up to date when a patch starts editing another asset.

### v28 (2026-09-23) — MP mod items 1–12 (no cheats) + friends list

Everything the MP mod had beyond the engine, except its cheat menu:
* **Arena**: dying in `H10_pvp_arena` eliminates you (full HP, "[PVP] name has been eliminated!"), no
  game over; the last peer standing wins. **World map** shows other players as coloured markers with
  names. New **world events** go to the game log and the room chat. (PORT_SPEC §7)
* **Options → Raise Difficulty** (Story → Casual → Normal → Hard → Ironman).
* **Equipment upgrades +1…+10** (select an equipped item → UPGRADE; gold + gems): main-hand damage and
  elemental, plus armor/HP/mana for every upgraded piece; preview shows the next cost, slots a "+N".
* **Vault button** in your inventory when you own a vault; **bag of holding tabs 1–5**.
* Recover/rest sync HP to other players at once; **SAF backup** (Android's file picker) on 4.4+.
* **Friends list** in the lobby: hosts you join are remembered, add by name + IP, shows who is
  hosting (direct UDP probe, works over ZeroTier/Tailscale), one tap joins. (PORT_SPEC §8)
* Forward shot without a target was already in 4.2.2 — nothing to add.
Specs: `deobf/MP_FEATURES_SPEC.md`, `MULTIPLAYER_PORT_SPEC.md` §7–8. Not device-tested.

**Content pack (item 12) is NOT in these APKs.** `EK_MP_APK=<mod apk> build_mod_4_2_2.sh …` merges the
mod's maps/quests/sprites/arena/UI art (`deobf/MP_CONTENT_SPEC.md`); the result is ~374 MB, too big for
the repo's split-part releases, so it is built on request. The arena map comes with it.

### v27 (2026-09-23) — multiplayer audit: everything matched and documented

Full audit before phase 2: `deobf/MULTIPLAYER_AUDIT.md` (every name translation diffed body-by-body,
all enums/reflection/fields verified, every hook site proven the same routine, engine inventory).
One fix: the peer-sprite hook now uses the mod's exact `!companionSpawn && lanPeerVisual`.

### v26 (2026-09-23) — MULTIPLAYER, phase 1: the MP mod's engine ported onto our base

Main menu → **MULTIPLAYER** opens the lobby (host / join by IP / LAN search, chat). In game: peers
appear as their characters with name labels and minimap pins, monsters engage every player and
scale +2 levels per extra player, combat and damage are mirrored, a CHAT button shows while in a
session. Internet play = both devices on one **ZeroTier or Tailscale** network, then join by the
host's VPN IP. Not device-tested. Spec + everything checked: `deobf/MULTIPLAYER_PORT_SPEC.md` §6.
Build now needs `r8.jar` (D8) in `EK_LIB` for the Java glue (`tools/mp_java`); `EK_SKIP_MP=1` skips it.
Next: phase 2 (arena + their menus/content), phase 3 (PvP anywhere, item drop/pickup, trading).

### v25 (2026-09-22) — Grissenda's off-class gear goes back to the player's backpack

`CharacterInventory.ekStripOffClass(inv, sheet)` (called from `SkillSet.ekPurgeSheet`, so same
hooks and same non-player-WARRIOR guard): each of the 12 slots whose item a vanilla warrior may
not use (`ClassRestriction.ekAllowed`) is added to `GameData.backpack` via the game's own
`Items.a(I)Z`, and only unequipped if that succeeded — a full backpack leaves it on her for a
later pass, so nothing is lost. Then `u()` recomputes bonuses, as the game's unequip does.
`patch_hero_class.py` §7b. D8 clean (15/24), update gate OK.

### v24 (2026-09-22) — Grissenda's off-class skills are cleaned up, points refunded

`SkillSet.ekPurgeSheet(sheet)`: for a WARRIOR sheet that is not the player's, removes every
learned skill whose class restriction rejects a vanilla warrior (via `ClassRestriction.ekAllowed`).
Points refund automatically (NPC free points = `J()` = level − `SkillSet.d()` + bonus). Runs on
recruit (`Party.a(NPC)`), on opening any skill screen (`c0.a(sheet,stage)`), and every 256th
trigger scan over `GameData.party.companions`, so an existing save heals by itself. Her
scripted grants are all warrior-legal (checked). `patch_hero_class.py` §7. D8 clean, gate OK.

### v23 (2026-09-22) — Hero perks are the player's only; Grissenda is a straight warrior again

The Hero reuses the WARRIOR enum, so every warrior-class NPC — above all **Grissenda** — had
silently become a Hero: any gear, any skill, a mana pool + bar, the class pager. Every Hero
hook now asks `CharacterSheet.ekIsHero()` (WARRIOR **and** the player's sheet; null-safe,
branch-join-free) instead of `class == WARRIOR`: `V()`, `C()`, `s0()`, the pager row/page, and
the three sheet-aware restriction sites (equip, learn/trainer, `Rules.a(I,sheet)`) via
`ClassRestriction.ekAllowed`. Skills/gear she already has stay (learned/equipped state is in
the save); she just can't get new non-warrior ones. `patch_hero_class.py` §6. D8 clean (15/24),
update gate OK.

### v22 (2026-09-22) — Arcane summon = the elemental line; builds hard-gated for update installs

Owner rejected v21's Wyvern/Manticore ("arcane shouldn't be creatures"). Arcane is the
elemental line: Lesser Sparkling → Sparkling → **Acid Elemental** (`elemental_acid`, cap 11)
→ **Animated Waste** (`elemental_acid_epic`, the greater acid elemental, cap 14) — no Fire,
Ice or Earth/Iron (those belong to the trainer Masteries). Everything else is as v21.
Every build now runs `tools/check_update_compat.sh` (pinned key + package + versionCode ≥ 1207)
and the random-key fallback is gone, so a build that would force a reinstall fails.

### v21 (2026-09-22) — four owner requests. Full reversing: `deobf/STACK_TRAITS_SPEC.md`

| # | Request | Root cause / anchor | Fix |
|---|---|---|---|
| 1 | Hero's INT/PER don't raise mana (only Mana Surge does) | `CharacterSheet.C()` trait-mana term is WIZARD/CLERIC only | Hero gets `level*(max(INT,PER)+2+min/2)` — `patch_hero_class.py` §2d |
| 2 | Rename Lesser Summoning → Summon | English name column is also the id source (`lesser_summoning`) | display-only swap in `Skill.<init>` — `patch_summon_routes.py` §6 |
| 3 | Arcane ranks 3-4 not Earth/Iron/Fire/Ice | were `golem_iron_lesser` / `elemental_acid` (golem sprites) | `wyvern` / `manticore` — **superseded in v22** by `elemental_acid` / `elemental_acid_epic` |
| 4 | Trait items stack (+1,+2,+2 STR = +5) | `CharacterInventory.u()` keeps the max per trait | sum of positive bonuses; "won't stack" popup off — new `patch_stack_traits.py` |

**Installs as an update** over v20 (same committed key, cert `53:8B:43:22…`; manifest byte-identical → same package + versionCode). D8 clean at min-api 15 and 24. Not playtested on device. v20 is dropped from `dist/` (still in git history); v21 likewise dropped when v22 shipped.
Base APK: the LFS object is fetchable again via the LFS batch API (curl POST to `…/info/lfs/objects/batch`); jars: smali/baksmali 2.5.2 fat jars from bitbucket, apksig from dl.google.com. If Maven Central returns 429, prefill `EK_LIB` from `https://maven-central.storage-download.googleapis.com/maven2`.

### v20 (2026-09-15) — skill icons render at their true colour (were all green on the Z Fold 8)

Owner saw every skill-icon background as bright green on the Fold 8. The bg is
`skill_bg<rank>` from `ui_icons.pack` (bg0 grey / bg1 yellow / bg2 green / bg3 blue /
bg4 red / bg5 purple), indexed by trained rank; untrained = grey. The game selects grey
correctly (`SkillSet.c(id)` = rank = 0), but `SkillImage.draw` (`e/a/d/e/z`) only ever
calls `setColor` for its ORANGE highlight and never resets to white otherwise, so the
neutral grey background inherits whatever tint the previously-drawn actor left on the batch
(a green UI element → green backgrounds). Draw order/tint differs by GPU, so it was fine on
the old device and green on the new one. Fix: `SkillImage.draw` sets the batch colour to
WHITE at the top when not highlighted. `tools/patch_skillicon_tint_fix.py` (a base-build
dex fix, so it is in **both** `hero-v20` and `hero-v20-fold`). Build-verified; round-trips
clean. Applies to every device, not just the Fold.

### v19 (2026-09-15) — install on 64-bit-only devices (Galaxy Z Fold 8) + stable signing

**Won't-install diagnosis:** the base APK ships **only `lib/armeabi-v7a/`** (32-bit) natives
(`libgdx.so`, `libgdx-box2d.so`). Modern Snapdragon flagships are 64-bit-only, so a
32-bit-only APK is rejected with `INSTALL_FAILED_NO_MATCHING_ABIS` → "App not installed."
(Not a Play-Protect/signature issue; `targetSdk=29` is fine.)

**Fix — universal APK.** Added `lib/arm64-v8a/libgdx.so` + `libgdx-box2d.so` (official
libGDX **1.9.12** Android natives from Maven Central) alongside the existing armeabi-v7a,
so one APK installs on both the owner's 4.2.2 phone and modern 64-bit devices. Version
1.9.12 was pinned by **exact JNI symbol-set match** against the game's own 32-bit libs
(58/58 gdx + 266/266 box2d, 0 diff); LOAD-segment alignment is 64 KB (16 KB-page safe).
`extractNativeLibs` defaults true (libs compressed, extracted at install). Libs live in
`tools/natives/arm64-v8a/`, wired into build step 6b. See `tools/natives/README.md`.

**Stable signing key.** The build now reuses a committed keystore
(`tools/ek-release.keystore`, cert SHA-256 `53:8B:43:22…`) instead of generating a fresh
random key per build, so future versions **update in place** without uninstalling. (Moving
to v19 from an earlier mod build is a one-time uninstall, because those used random keys.)
### v19-fold (2026-09-15) — the foldable + modern-storage layer on top of v19

`v19` (above) clears the wall that stopped the install: the missing 64-bit natives. Three
more walls are still in the way of actually *living on* a Galaxy Z Fold 8, all measured off
the shipped APK rather than assumed. Full write-up: **`deobf/MODERN_DEVICE_COMPAT.md`**.

```
https://knightdx91-alt.github.io/Exiled-kingdoms/dist/ExiledKingdoms-hero-v19-fold.apk
```

| Wall | Measured fact | Fix |
|---|---|---|
| **ABI** *(already fixed in v19)* | `lib/` held `armeabi-v7a` only | v19's arm64-v8a natives. `build_modern_compat.sh` re-checks them every build: JNI symbol sets must match the 32-bit libs exactly (58 core + 266 box2d, `nm -D` diff 0) and every `LOAD p_align` must be a multiple of 16 KB (they are 64 KB, so 16 KB-page devices are safe) — a mismatch **fails the build** |
| **Foldable** | `MainActivity configChanges=0x4a0` = `keyboardHidden\|orientation\|screenSize` — **no `screenLayout`, no `smallestScreenSize`** → the activity is destroyed on every fold/unfold and libGDX restarts the game at the title screen, losing unsaved progress. Same on every move between the cover and inner displays | widen to `0x40003ffc` — a **4-byte in-place** binary-manifest edit (`tools/axml_set_config.py`); `resources.arsc` and every other entry stay byte-identical |
| **Storage** | `patch_export_fix.py` repointed libGDX's external root to `/sdcard/`, right on 4.2.2 (install-time perms) and wrong on Android 11+, where it needs a **runtime** grant this 2023 build never asks for → save export, save *import* and the crash log all fail into a silent `catch`. That also blocks carrying the tablet's `EK.bak` across | `tools/patch_modern_device.py`: new `EkStorage` asks for the grant once at startup, probe-tests `<sdcard>/Download` (write + delete, cached), and falls back to the app-private external dir when refused. `AndroidFiles.getExternalStoragePath()` and `EkCrashLog` route through it |
| **Signature** | v19 signs v1+v2 with minSdk 16 | v19-fold adds **v3**, and signs with the **same committed keystore**, so it installs straight over v19 with no uninstall. `ApkVerifier`: verified at API 16, 24, 29, 34 **and** 36 |

`targetSdkVersion` deliberately **stays 29**: ≥ 24 clears Android 14/15's
minimum-installable-target block, and ≤ 29 keeps `requestLegacyExternalStorage` working
(raising it would switch the game to scoped storage and kill save export outright).

Build (post-processor over a finished mod APK, so it never needs the clean base):
```
EK_LIB=/tmp/eklib tools/build_modern_compat.sh ExiledKingdoms-hero-v19.apk \
                                               ExiledKingdoms-hero-v19-fold.apk
```
`EK_KEEP_CONFIGCHANGES=1` reverts to restart-on-fold; `EK_SKIP_STORAGE=1` skips the dex patch.

**Verified off-device** (nothing here is device-confirmed): the patched dex round-trips
through baksmali **and** passes a clean **D8 `--min-api 24`** pass over the whole dex;
`ApkVerifier` verified for API 16–36 with the same cert fingerprint as v19
(`53:8B:43:22…`); entry-by-entry CRC diff vs `hero-v19` is **0 added, 0 removed, 3 changed**
(`classes.dex`, `AndroidManifest.xml`, signature) — 6089 entries byte-identical.

**Which APK does the owner install?**
`hero-v19-fold` on the Fold 8 (it is v19 plus the three fixes above, same signing key);
`hero-v19` on the 4.2.2 tablet. The storage patch deliberately stays out of the tablet
build: it calls API-23 methods behind an `SDK_INT` guard, and this project has burned two
builds on Dalvik verification surprises — no reason to risk the known-good build.

**Three install gotchas on a 2026 Samsung, none of them the APK's fault:**
1. **Auto Blocker** (Settings → Security and privacy) blocks sideloading outright on
   One UI 6.1+ — turn it off before installing.
2. Any **Play-Store copy of Exiled Kingdoms must be uninstalled first** — same package
   name (`net.fdgames.ek.android`), different signing key, so the install is refused with
   "App not installed". **Export its save first**; that copy's saves go with it.
3. To get `EK.bak` at `/sdcard/Download` (so the tablet's exported save imports straight
   away), allow **Files/media** when the game asks on first launch, or in
   Settings → Apps → Exiled Kingdoms → Permissions. If it is refused, export still works —
   it lands in `Android/data/net.fdgames.ek.android/files/Download/` instead.

### v18 (2026-08-05) — wizard companion uses the full mage kit

`ekWizardAI`'s `WIZARD_SPELLS` widened from 4 to 9: added `disintegrate` (next-hit buff)
and the summons `lesser_summoning` + `fire_mastery` / `ice_mastery` / `earth_mastery`.
Offence stays first (the loop returns on the first spell that fires); summons sit mid-list
at a low roll chance and are further throttled by their 12-80 mana cost; Mage Armor is the
fallback. Every entry still fires only if the companion actually learned it and can afford
it, so purchases drive behaviour. `gate` is deliberately excluded (teleport, not combat).
`tools/patch_party_ai.py`.

### v17 (2026-08-05) — three owner requests

1. **Completely remove the orphaned cheat items** (they were still crashing on a save that
   held them). The backpack is `GameData.backpack` (`Helpers/Items`, 20-slot `itemList[I`).
   New `Items.ekPurge()` zeroes any slot whose id no longer resolves (`Rules.c(id) == null`),
   called from `CharacterSheet.l()` so the bag self-cleans on every access. Keys on
   `Rules.c()` (not `Rules.f()`, which is the *stackable* flag). `tools/patch_purge_orphan_items.py`.
2. **Show the Hero's mana bar.** `Character.s0()` is the "draw the mana bar" gate used by
   both the HUD (`e/a/d/y`) and the character screen (`e/a/d/e/h`), and it was WIZARD/CLERIC
   only. Added WARRIOR(Hero), same as the `V()` fix. `tools/patch_hero_class.py` §2c.
3. **Familiar rank 1 costs 1 skill point** (was 2) — `skills2.txt` Lesser Summoning rank 1
   cost column, in `tools/patch_summon_routes.py`.

### v16 (2026-08-05) — second Details crash, on-device. Spec appended to `deobf/V15_FIXES_SPEC.md` §6.

After v15 fixed the `InventorySlotImage` draw NPE, the owner's next `EK_crash.txt` showed a
*different* crash on the Details button: `CharacterWindow` → `StatsDetailWindow` fires an
analytics / Play-Games update that calls `MainActivity.f()`, which reads the secure setting
`"android_ld"` and calls `.length()` on it. On the 4.2.2 device that row is **null** → NPE.
`f()` already has a `"99999"` fallback for a non-hex id; the fix routes a null id down that
same fallback (`tools/patch_gpgs_deviceid_fix.py`). Build-verified; round-trips clean.

### v15 (2026-08-04) — five owner-reported fixes. Full reversing: `deobf/V15_FIXES_SPEC.md`.

| # | Report | Root cause (read from the base dex) | Fix |
|---|---|---|---|
| 1 | Wizard companion ignores bought spells (Mage Barrier) | `ekWizardAI` only rolled the 3 offensive spells; **Mage Barrier is a passive that only fires while Mage Armor is active**, and the AI never cast Mage Armor | add `mage_armor` self-buff to `WIZARD_SPELLS` |
| 2 | Buying a mage skill on the Hero grants no usable mana | `CharacterSheet.C()` (max mana) returns 0 unless `V()` is true, and `V()` is WIZARD/CLERIC-only → the Hero's pool is 0, so the v3 `g()` grant was dead code | `V()` also true for WARRIOR(Hero); Hero `g()` pool scales `level*2+12` |
| 3 | Not all skills show for a companion (incl. Grissenda) | `SkillWindow` lists a skill on an NPC sheet only when its `NPC` column is `Y`; most rows were `N` | flip every skill row to `NPC=Y` across the six base skill files (four newly wired into the build) |
| 4 | Familiar route dialog never pops; summons vanilla familiars | **`GameVariables.b()` returns `-255` (not 0) for an unset variable** → the "first purchase" gate (`if-nez`) always skipped, and the route lookup fell through to the Arcane default | prompt when route `<= 0` (`if-gtz`); drop the `rank==0` gate so pre-mod saves can still choose |
| 5 | Viewing character Details → NullPointerException | **(confirmed from `EK_crash.txt`)** `InventorySlotImage.a(I)` builds `new TextureRegionDrawable(null)` when an item id isn't in `Rules.a[]` (or its icon is missing) → `draw()` NPEs in `SpriteBatch`. Bites saves holding the v13-removed cheat items (ids 9990-9992) | leave the slot icon null (draw already skips null) instead of wrapping null (`tools/patch_inventory_icon_fix.py`); plus a harmless null-guard on the skill-window Details button |

> ⚠️ All five are now root-caused (issue 5 confirmed from the device crash log, not
> guessed). v15 was built + signed + statically verified (every edited method round-trips through
> the reassembled Dalvik dex with consistent register types, and none introduce the
> branch-join reference-type conflict that caused the earlier Hero VerifyErrors). The
> on-host `tools/dalvik_verify.sh` dexopt oracle could **not** be re-established this
> session (the pure-Python yaffs extractor mis-detects the 4.2 system image geometry);
> re-run it from a clean container before treating v15 as device-verified.

The previous build stays up next to it as a fallback; keep exactly two in `dist/` and drop
older ones (they are ~120 MB each of git objects).

Base: owner-supplied clean APK, sha256 `5fc7c866…` (Drive link; the repo's LFS budget is
spent, so it is NOT in git — re-fetch it from the owner when starting fresh).
Build: `EK_LIB=/tmp tools/build_mod_4_2_2.sh <base.apk> out.apk`
Verify: `tools/dalvik_verify.sh out.apk` → must print `dexopt: OK` with the same complaint
list as the untouched base.

### Status

| Feature | Status |
|---|---|
| Install on 4.2.2 | ✅ **working** (needed SHA1withRSA cert **and** the zip-swap pipeline) |
| **Export save** | ⚠️ **shipped, unconfirmed** — write side repointed to `/sdcard/Download/EK.bak` + `mkdirs()`, and the silent `catch` now prints to `GameConsole`. Never confirmed working on device; ask the owner what the console says when export is pressed |
| Janod mage companion | ✅ **working since v2** (v1 froze on spawn: skipped stat init + sprite dead end — `COMPANION_SPEC.md` §5). Stats, gear, dismissal and casting all fixed later; see the rows below |
| Summons gain XP | ✅ **v13 (2026-07-30)** — `NPC.ekSummonXP()` off `Player.k(I)V`: every live `player_summon` earns the companion's 0.56 share, additive. Levels persist across maps via the existing `follower.lastLevel`, and reset when the summon dies or expires. **v14** adds the feedback: floating "+N xp", and on level-up a blue LEVEL UP + the levelup sound + a battle-log line. See `deobf/COMPANION_SPEC.md` §10.1 |
| Dismiss 1 of 2 companions | ✅ **v13 (2026-07-30)** — the dismissal path cleared `activeCompanion` and returned, leaving the second companion following but unselectable. `NPC.ekPromoteFollower()` removes its follower record and re-registers it through `Party.a(NPC)`. See `deobf/COMPANION_SPEC.md` §10.2 |
| Cheat items + no-clip | 🚫 **removed from the default build (v13)** — opt back in with `EK_CHEATS=1`. Verified absent: no `noclip` in the dex, no 9990-9992 rows in `items.txt` |
| Companion XP tax | ✅ **v12 (2026-07-30)** — vanilla `Player.k(I)V` gave the player only 80% of ALL xp whenever a companion was in the party (the companion's 56% is generated separately, so it was a pure penalty). Player multiplier 0.8→1.0, companion 0.7→0.56 so its share is bit-for-bit unchanged. See `deobf/COMPANION_SPEC.md` §9 |
| Wizard/mage AI | ✅ **v8 (2026-07-30)** — `AISkillUsage` only ever had warrior/rogue/cleric branches, so a mage companion could never cast. `ekWizardAI()` adds the WIZARD (ordinal 3) case; `CharacterStats.g()` gives race NPC a real mana pool. See `deobf/PARTY_AI_SPEC.md` §1 |
| Player summon army | ✅ **v8 (2026-07-30)** — the one-at-a-time rule was the single `Party.b()` call on the player branch of `SkillActions.a(Character,String,II)`; dropped, duration ×5. NPC summons already stacked. See `deobf/PARTY_AI_SPEC.md` §2 |
| Second companion at once | ✅ **v8 (2026-07-30)** — `NPC.z0()`'s `companionSpawn` fork now routes a companion recruited while the slot is taken into the (unlimited, persisted) follower list; recruit refusals re-gated to "party is full". See `deobf/PARTY_AI_SPEC.md` §3 |
| Mage skills on a companion sheet | ✅ **v8 (2026-07-30)** — `SkillWindow` shows an NPC only skills flagged `NPC=Y`, and 4 of the 8 mage skills were `N` (the owner's "only the top row"). Flagged `Y` in `skills2.txt` / `skills_advanced2.txt` |
| Summon Familiar → 3 routes | ✅ **v9 (2026-07-30)** — one skill, no new tree entries (the mage basic page is capped at 8 slots and holds 8). First purchase pops a route dialog (`e/a/d/e/eksp`, extends the game's own SimpleDialog); the route is a saved game variable and `SkillActions.ekSummonId/ekSummonCap` turn route+rank into a bestiary id. Ranks extended to 4: skeleton→hero / familiar→golem→acid elemental / wolf→bear→werewolf. Every rung is checked against what trainers already sell (v10: arcane is constructs, not elementals; v11: beast drops dire/spirit wolf, which are the cleric Guardian Wolf). See `deobf/SUMMON_ROUTES_SPEC.md` |
| Summon Familiar → skeletons | ⤴️ **superseded by the 3-route feature above (v9-v11)** — the original single-ladder sketch | is kept for its reversing notes only. See `deobf/SUMMON_SKELETON_SPEC.md` |
| Hero class + per-class skill pager | ✅ **root-caused & fixed 2026-07-27 (v3)** — owner's `EK_crash.txt` logcat showed the VFY detail: the v2 pager patch left `v0` holding the **CharacterSheet** where the rest of `c0.c()` expects the **CharacterClass** (`CharacterClass.a(v0)` header labels) → Dalvik rejected the class. v3 restores `v0` via new `ekPageClass()` helper (+`ekSkillsSuppressed()`); verified in the assembled dex. Owner has been playing on it since |
| Janod recruit after wolf-cloak quest | ✅ **2026-07-27**: recruit offer now ALSO unlocks after `fair_deal>99` (Cloak of the Wolf), guarded by `mad_wizard<10` + Adaon-not-in-party (so the "damn thief!" scene still fires). Previously only `mad_wizard>99` (A Mad Wizard resolved) unlocked him |
| Companion dismissal → goes home | ✅ **v6 mechanism + v7 cache-proof fallback (2026-07-30)** — `NPCSpawn#` → `MonsterSpawn.Q()` re-drops the stored companion on their TMX spawn point; G9.tmx triggers cover a fresh map load, and `NPC.ekHomecomingTick()` (hooked into the trigger scan) covers the ~1080-game-second level cache that made the v6 triggers invisible to an existing save. See `deobf/COMPANION_SPEC.md` §8 |
| Janod companion-grade stats | ✅ **v7 (2026-07-30)** — `NPC.ekJanodGear()` at `Party.a(NPC)` equips the sorcerer kit, `CharacterSheet.ekDropHardcoded()` kills the miniboss weapon/armour/resist overrides, and the sheet's stored level is reset to companion grade (`CharacterStats.e(7)`) before `Party.r()` applies the standard XP catch-up. Self-heals a Janod already in a save. See `deobf/COMPANION_SPEC.md` §7–8 |

**2026-07-26 build notes:** the base APK is no longer fetchable from Git LFS (repo
exceeded its LFS budget) — it was re-obtained from the owner's Drive link (sha256
matches the LFS pointer: `5fc7c866…`). Jars for `EK_LIB`: baksmali/smali 2.5.2
(bitbucket), `apksig8.jar` = apksig 8.3.1 (dl.google.com/dl/android/maven2).
`EK_SKIP_HERO=1 tools/build_mod_4_2_2.sh …` builds the safe no-Hero APK; every build
now includes `tools/patch_crashlog.py` (uncaught exceptions → `/sdcard/EK_crash.txt`).

### 1. Export save — NEXT STEP IS ALREADY SCOPED

Two things were tried and were **not** the fix: adding `WRITE/READ_EXTERNAL_STORAGE`
(shipped, harmless, keep it), and redirecting the external root to `/sdcard/`
(shipped — `tools/patch_export_fix.py`).

**Owner's suggestion is the right next move, and it is nearly free**: write the backup
into the **Downloads** folder. The engine's *import* side **already probes** these paths,
relative to the external root (`Serializer.a()Z`, verified in the dex):

```
EK.bak · download/EK.bak · Download/EK.bak · downloads/EK.bak
Downloads/EK.bak · sdcard/download/EK.bak · sdcard/Download/EK.bak
```

Since `patch_export_fix.py` already repointed the external root at `/sdcard/`, those now
resolve to the **real** `/sdcard/Download/EK.bak`. So **only the write side needs changing**:
in `Serializer.a(Z)V` change the filename constant `"EK.bak"` → `"Download/EK.bak"`
(and `"EK_GPGS.bak"` likewise if wanted). Import then finds it with no further change.
⚠️ `new FileOutputStream(...)` does **not** create parent dirs — `/sdcard/Download` usually
exists, but add an explicit `mkdirs()` on the parent to be safe.

**Diagnostic already shipped:** the export's silent `catch` now pushes the exception text to
`GameConsole` (`tools/patch_export_fix.py`). Ask the owner what message appears in-game when
export is pressed — that names the real failure instead of another guess.

### 2. No-clip and the cheat items — REMOVED from the build (v13, owner's request)

`tools/patch_cheats_v2.py` (Tome of Renown, Phase/Anchor Stone, and the `e/a/c/b.c(II)Z`
collision patch) is now **opt-in behind `EK_CHEATS=1`**. The default build ships none of
it — verified in the APK: no `noclip` reference anywhere in the dex, no 9990/9991/9992
rows in `items.txt`.

Do not resurrect no-clip without reading the door finding in `README.md` first: a "locked
door" in EK is a **TMX conversation object** whose exit is a `Travel#map,entry` action, not
adjacent geometry, so no amount of collision patching can open one. If the goal is
"go through locked doors", the mechanism to patch is the conversation row picker (take the
branch that leads to a `Travel#`), not the movement predicate. Two collision patches were
already burned learning this.

### 3. 2026-07-30 session — v6 → v14 (all built, dexopt-clean, all deployed)

Every one of these was reversed against the base dex first and written up before coding;
each row names the spec section that holds the evidence.

| build | change | spec |
|---|---|---|
| v7 | Dismissed companions actually come home — `NPCSpawn#`→`MonsterSpawn.Q()`, plus `ekHomecomingTick()` because level data (triggers included) is cached ~1080 game-seconds | `COMPANION_SPEC.md` §8 |
| v7 | Janod's stored sheet reset to companion grade (the miniboss overrides survived in old saves) | `COMPANION_SPEC.md` §7-8 |
| v8 | `ekWizardAI()` — `AISkillUsage` had no WIZARD branch at all, so mage companions could never cast; race NPC also had a 0 mana pool | `PARTY_AI_SPEC.md` §1 |
| v8 | Player summons stack (one `Party.b()` call was the whole limit) + ×5 duration | `PARTY_AI_SPEC.md` §2 |
| v8 | Second companion travels as a follower (`ekTakesCompanionSlot`) | `PARTY_AI_SPEC.md` §3 |
| v8 | Mage skills visible on a companion sheet (`SkillWindow` filters on the `NPC=Y` column) | `PARTY_AI_SPEC.md` §1.1 |
| v9-v11 | Summon Familiar → three routes chosen on first purchase, 4 ranks each, no new tree entries; ladders checked against every trainer-bought skill | `SUMMON_ROUTES_SPEC.md` |
| v12 | Companions stopped taxing the player 20% of ALL xp | `COMPANION_SPEC.md` §9 |
| v13 | Cheats/no-clip out of the build; summons gain XP; dismissal promotes the other companion | `COMPANION_SPEC.md` §10 |
| v14 | Summon XP feedback: floating "+N xp", LEVEL UP, sound, battle-log line | `COMPANION_SPEC.md` §10.1 |

**Nothing in v6→v14 has been confirmed on the owner's device yet.** Everything is verified
two ways only: the reassembled dex was read back, and `tools/dalvik_verify.sh` (real
Android 4.2 `dexopt`) returns OK with output identical to the untouched base. That proves
classes load and control flow is what was intended; it proves nothing about behaviour.

**Three traps this session, all worth remembering:**
1. `dexopt: OK` does **not** mean the edit is right. A rank-test anchor matched the wrong
   `if-ne`, assembled clean, verified clean, and would have double-summoned at rank 2 and
   capped rank-4 creatures at level 3. Read the disassembled branch structure back for any
   edit that moves a comparison (`SUMMON_ROUTES_SPEC.md` §4).
2. **Base method letters do not match the readable decompile.** `CharacterStats.a(I)V` is
   `missingHP` in this APK, not XP (XP is `c(I)V` via `CharacterSheet.b(I)V`). Always map
   the letter in the base dex before using it.
3. **Register width.** `Player.k(I)V` is `.locals 19`, so `p1` is above v15 and plain
   `invoke-static` will not assemble; and any constructor with >5 args needs
   `invoke-direct/range` over contiguous registers.

### 4. Hero class — untested, UI is the risk

`tools/patch_hero_class.py`, spec `deobf/HERO_CLASS_MOD_SPEC.md` §v3. Logic side should be
sound (single choke point `ClassRestriction.a(CharacterClass)`, gated by the new
`ekSuppress` flag). **Unverified part is the layout**: the pager `TextButton` added to
Table `l` in `c()`. If it renders badly, adjust placement — it is a standalone
`add()`+`row()` at the top of the table, easy to move.

### Build pipeline — DO NOT reintroduce `apktool b`

`apktool b` re-encodes `AndroidManifest.xml`/`resources.arsc` and rewrites the zip
(META-INF at the front) → **`INSTALL_PARSE_FAILED_NO_CERTIFICATES` on 4.2.2**, even though
the signature verifies on desktop. `tools/build_mod_4_2_2.sh` instead zip-swaps only changed
entries into a copy of the base APK (`resources.arsc` stays byte-identical) and edits the
binary manifest via `tools/axml_add_perms.py`.

Signing must be: cert **SHA1withRSA** (`keytool -genkeypair -sigalg SHA1withRSA` — keytool
defaults to SHA384 and old Android then reports *no certificate at all*), digests SHA1,
signed with Google `apksig` `setMinSdkVersion(16)`. Pre-ship check:
`keytool -printcert -jarfile <apk> | grep -i "signature algorithm"` must say SHA1withRSA.

Patchers must stay **disassembler-agnostic**: apktool emits sequential labels (`:cond_1`),
baksmali offset-based (`:cond_17`). Anchor on instruction sequences and capture labels by
regex — never hardcode a label name.

### Specs
`deobf/CHEAT_MOD_SPEC.md` (cheats, signing, export) · `deobf/COMPANION_SPEC.md` (companion
system + Janod) · `deobf/HERO_CLASS_MOD_SPEC.md` (Hero, §v3 current).

Key reversing result worth remembering: **`world/companions.txt` is a dead file** — nothing
loads it. Companion identity is hardcoded by `spawn_id` in `NPC.<init>` (the `companionSpawn`
whitelist) and in `ScriptedAction`'s `UpgradeCompanion` branch.

---

## What's NOT in git (and how to get it)

Kept out of the repo on purpose (size / third-party copyright). All re-derivable:

| Item | Where | How to restore |
|---|---|---|
| Base game APK (`Exiled Kingdoms.apk`, 130 MB) | owner's Google Drive | download, then `tools/extract.sh <apk>` |
| Recovered assets (~150 MB) + native libs | — | `tools/extract.sh <apk>` regenerates `recovered/assets` + `recovered/native` |
| `node_modules/` (web) | — | `cd web && npm install` |
| `.cache/` (gdx/JavaParser jars) | — | auto-fetched by the scripts |
| 3 mod APKs (Multiplayer, Sorrow, ENB) | owner's Google Drive | third-party — see `MOD_ANALYSIS.md` |

---

## Track B — Web rebuild (Phaser 3)   ← recommended, this is the product

**Location:** `web/`. **Status:** a playable vertical slice — start a character, walk
a seamless world, meet NPCs, and hold conversations. All verified in headless Chrome
(`cd web && node verify.mjs` → `VERIFY: PASS`, screenshots in `web/shots/`).

### DONE (proven by verify.mjs)
- **Foundation**: Phaser 3 (`web/src/main.js`, Phaser vendored in `web/vendor/`);
  player-owned **4-way orientation** (engine-rotated `world` container, input
  hit-tests through the rotation); **offline PWA** (`sw.js` + `manifest.webmanifest`,
  precaches the whole game from `asset-manifest.json`); **IndexedDB saves**
  (`src/saves.js`, export/import + an `auto` save of the created character).
- **Isometric maps**: `tools/tmx2json.mjs` converts every `.tmx` (iso tiles, external
  `.tsx`, base64+gzip/zlib layers, **object layer → transitions / entries / npcs /
  containers / triggers**) to JSON; `src/map.js` renders three depth planes with
  recovered ambient/day-night (`deobf/ENGINE_SPEC.md`, `CAMERA.md`). All **151 maps**
  converted.
- **Seamless streaming overworld** (`src/world.js`): the 53 `[outdoor][worldmap]`
  tiles live in one global cell space and stream in a small window around the hero —
  **no load screen between outdoor tiles**. Viewport **tile culling** via a sprite
  pool bounds draw cost to the camera (~600 tiles, not ~90k); residency is lean for
  mobile (1 chunk mid-map, up to 4 at a corner) with tileset-texture dedup. Towns/
  buildings/caves stay discrete, entered by walking onto a transition cell (an arch).
- **Movement**: `src/move.js` — collision grid (`GameMap.v()`), 8-way A*, iso
  cell↔pixel (+ global variants). Two control schemes via a toggle: **tap-to-move**
  and a **free-floating joystick** (`src/joystick.js`, reskinned to the game's real
  `touchpad_base/knob`). Facing/anim from `src/sprite.js` (9×11×140 sheets).
- **Game start** (`src/char-create.js`): title → character creation using EK's own
  assets (logo, intro backdrop, portrait arrows, real class/difficulty text) →
  begins in `I10_tutorial` (Adaon's road) with the gender-correct sprite.
- **Entities** (`src/entity.js`): NPCs/monsters from each map's spawns render as
  animated, depth-sorted actors (interior + streamed world), sprites resolved via a
  **bestiary index** (`tools/gen-bestiary.mjs` → `assets/data/bestiary.json`);
  composite paper-doll sprites fall back to their base layer.
- **Dialogue reader** (`src/dialogue.js`): parses EK conversation trees, shows NPC
  lines (portrait + Continue) and player choices, evaluates **conditions** and runs
  **actions** against a shared world state (global variables + party/followers) — the
  same store quests/triggers use. Tap an NPC to walk up and talk. Verified end-to-end
  in the tutorial (Adaon greets you, the tree branches, `NPCFollow#` adds him).
- **Deobfuscated stat model** (`deobf/CHARACTER_STATS_SPEC.md`): real numbers behind
  creation/level-up — 6 attributes (0–12, triangular cost), trait pool `2L+2`, skill
  pool `2L−1`, tier-1 skill cost 1 (+mana), per-class HP/mana/damage. Baked into
  `assets/data/creation.json` + `skills.json` (`tools/gen-creation.mjs`,
  `gen-skills.mjs`).

### TODO (roughly in order)
1. ~~**Creation: traits + abilities pages**~~ ✅ DONE (`src/char-create.js`). After
   name/gender/class/difficulty, an **Attributes** page allocates 4 points across the
   6 attributes (0–12, triangular cost from `creation.json` `traitLadder`, pool
   enforced) and a **Starting Ability** page picks 1 skill from the class list +
   GENERAL (`skills.json`, Active/Passive + mana tags, optional). The created PC now
   carries `attributes` + `startingSkill`; both persist into the auto-save. Styled to
   the EK gold-on-stone look.
2. ~~**Player model + HUD**~~ ✅ DONE (`src/player.js`, `src/hud.js`; spec
   `deobf/HUD_SPEC.md`). `PlayerModel` derives level/maxHP/maxMana/damage from the
   created character via the recovered formulas + XP table (max level 25) + start gold
   18; the HUD shows portrait, HP/mana(caster)/XP bars + level + gold, action buttons,
   and a Character panel (attributes + derived stats); persisted in the save.
   *(Styling is placeholder EK-theme; exact GameHUD art is a later fidelity pass.
   Inventory/quickslots come with combat.)*
3. ~~**Combat + followers**~~ ✅ DONE (`src/combat.js`; spec `deobf/COMBAT_SPEC.md`).
   Real-time-with-pause: weapon-roll attacks + crits, the exact `g0`/armor mitigation,
   PatrollerAI enemies (aggro/chase/attack), CompanionAI followers (fight the hero's
   target / trail him), loot (gold+items) + XP on death, floating combat numbers, space
   to pause. Data: `weapons.json`, `loot.json`, combat-enriched `bestiary.json`.
   *(Skill/proc effects, status effects, and the inventory system are the next layer —
   see DEOBFUSCATION_STATUS.md A8–A11.)*
4. ~~**Quests + world state**~~ ✅ DONE (`src/hud.js` Journal; spec `deobf/QUEST_SPEC.md`).
   Key finding: a quest's progress IS the game variable named after its id (`>99` =
   complete), so world state was already the `gameState.vars` store. Journal (📖) lists
   active quests with the current-stage text + completed flag; `GainGold/LoseGold` wired;
   world state (all vars + followers) persists in the save. `quests.json` = 105 quests.
5. ~~**Hero class + trainers**~~ ✅ DONE (spec `deobf/TRAINERS_SPEC.md`). Reversed the
   trainer mechanics (`TrainSkill#`, advanced-skill discipline lists, `items.txt` Classes
   col) and added the owner's **HERO** class: selectable 5th class that learns advanced
   skills from ANY discipline via trainer NPCs and unlocks that discipline's
   class-restricted equipment (`PlayerModel.canUseItemClass`). Non-HERO classes are gated
   to their own discipline. `trainers.json` = 40 advanced skills; trained skills +
   disciplines show in the Character panel and persist. *(Skill effects + `skill_req`
   gates + custom HERO art are the follow-ups; equipment gating awaits the inventory
   system.)*
6. ~~**Render polish**~~ ✅ DONE (`src/render_fx.js`; spec `deobf/RENDER_POLISH_SPEC.md`).
   Interiors now get **roof/object fade** (alpha 0.42 when the hero is in the 4-tile band
   behind a tile), **fog-of-war** in dark dungeons (`maxlight>0`: explored grid, unexplored
   hidden, explored-not-visible dimmed to 1/3, enemies hidden in fog), and **dynamic
   lights** (additive torch glows from `type="light"` TMX objects, now extracted by
   `tmx2json` → `map.lights`, + a player torch on dark maps). Inert in the open world.
   Remaining APPROX: true LOS raycast, ray-cast shadow occlusion, secret-door reveal,
   particles (A4) — see DEOBFUSCATION_STATUS.md.
7. ~~**Items / inventory / equipment**~~ ✅ DONE (spec `deobf/INVENTORY_SPEC.md`). Reversed
   the `Item`/`CharacterInventory` cluster (items.txt 25 cols, 12 equip slots, type→slot
   map). `items.json` = 724 items (534 equippable). `PlayerModel` gains equipment/backpack/
   quickslots + equip/unequip (class-gated) and folds worn armor/HP/mana/resist/shield into
   its derived stats and combat. **Loot drops are now real items**; `GiveItem/RemoveItem`
   dialogue actions; consumable `OnUse` (potions heal). **Character/inventory screen**
   (portrait or 🎒 → paper-doll of 12 slots + Attack/Armor/Resist blocks + backpack grid,
   tap to equip/unequip/use). *(Next: item procs + trait/attribute mods + `Requisites`;
   icon art from the atlas; match the screen to the real InventoryScreen — DEOBF A14.)*

8. ~~**Skill / spell execution**~~ ✅ DONE (spec `deobf/SKILLS_EXEC_SPEC.md`; `src/skills.js`
   + combat/HUD wiring). Learned skills now DO things: per-rank effects with mana-spend +
   cooldowns — heals, the 4 caster spells (fireball/lightning/ice-storm/sacred-fire, AoE +
   stun + undead bonus), warrior/rogue melee actives (whirlwind/charge/bash/kick/stab),
   self-buffs (resilience/holy-shield/mage-armor/arbenos/evasion), and passives (fury,
   mana_surge). A HUD **skill bar** casts them (greyed on cooldown/no-mana); `PlayerModel`
   gains per-skill ranks. Numbers reversed from `SkillActions` + skill texts. *(Long tail —
   summons/stealth/traps/most masteries/procs + projectile animation — is APPROX; skills
   outside the effect table are known-but-inert. DEOBF A15.)*

### The remaining backlog (post-#1, post-skills)
Combat **layer 2 tail**: summons, stealth, traps, the mastery passives, item/skill procs,
status effects beyond stun/dodge (slow/bleed/fury are partial), projectile travel + spell
FX/animation. Then: **faithful UI pass** (match every
screen to the real game), **audio/music** (`music` map prop parsed, nothing plays yet),
**save/load menu + Continue** (state serializes; no slots UI), minimap/automap, eased
camera + shake, particles (A4), shops/merchants, reputation UI. See DEOBFUSCATION_STATUS.md
§3–§4 for the full APPROX/known-missing list.

### UI fidelity note
Per the owner: every UI surface (creation screens, dialogue box, menus) must
eventually match the real game **exactly**. Current styling is an agreed placeholder;
match to a screenshot when building each screen.

---

## Session log — 2026-07-05 (mobile UI + tutorial exit) — READ THIS FIRST

Everything below is **live on `main`** and deployed to GitHub Pages
(`https://knightdx91-alt.github.io/Exiled-kingdoms/`). All tested with
`cd web && node verify.mjs` (PASS) unless noted.

### ⚠️ Versioning / cache — HOW TO KNOW A DEPLOY LANDED
- **A visible build tag `#ek-build` shows in the bottom-left corner** (currently
  **v9**). It is the owner's ground truth that a fresh copy loaded.
- **On EVERY web change bump BOTH:** `web/index.html` `#ek-build` text AND
  `web/sw.js` `const VERSION` (keep them in sync, e.g. v8 ↔ ek-v8). Tell the owner
  the new number when you push.
- The service worker (`web/sw.js`) is now **network-first for app code**
  (html/js/css/webmanifest via `SHELL_RE`) so new deploys show up online; the big
  game assets stay cache-first for offline. `main.js` adds a `controllerchange`
  **auto-reload** (only when a controller already existed) + `registration.update()`
  so future updates self-apply. The owner was repeatedly stuck on an old
  cache-first SW — if it happens again, tell them to load in a **private tab** (no
  SW) or clear site data; it self-heals from v7+.
- **Deploy flakiness:** GitHub Pages sometimes fails the deploy at "syncing_files"
  with `Deployment failed, try again later` — this is GitHub-side, not the code.
  Re-run the workflow (or push again). Verify live with
  `curl -s .../index.html | grep ek-build`.

### Mobile UI — orientation, Settings, HUD (all in `web/`)
- **Orientation is device-lock first.** The 4 orientation buttons call the
  **Screen Orientation API** (`screen.orientation.lock('landscape-primary'|…)`),
  requesting fullscreen first on non-standalone browsers (Android Chrome tabs need
  it; installed PWAs don't). On success the whole page (game + HUD + Settings +
  creation) is really that orientation and fills the screen with correct input.
  Where the API is unavailable it **falls back** to engine rotation (`this.orient`
  rotates the Phaser `world`) + a CSS class `ek-rot-90/180/270` that rotates the
  DOM overlay layer. See `setOrient()` / `_lockOrientation()` in `src/main.js`.
  The **Auto** button was removed (it wrapped the row and hid reverse-portrait);
  device-follow is still the default until a lock is chosen. 4 buttons:
  portrait/landscape/reverse-portrait/reverse-landscape.
- **`#overlay-root`** (new, in `index.html`) wraps ALL DOM game overlays (HUD,
  dialogue, joystick, character-creation). It's `position:fixed; inset:0;
  pointer-events:none` over the canvas; interactive children (`#dlg`, `.cc-overlay`,
  `#stick-layer`) re-enable pointer-events. The engine-rotation fallback CSS-rotates
  this wrapper as a unit so the **HUD rotates with the game** (the canvas stays
  axis-aligned so Phaser input works). HUD/dialogue/joystick/creation are attached
  to `#overlay-root` (was `#game-root`).
- **Settings panel** (gear `#settings-btn` → `#settings-panel`): holds the
  Orientation buttons (`#orient-bar`) and the Movement scheme (`#control-bar`:
  Tap / Joystick). Replaces the old always-visible orient bar + control toggle.
  `_wireSettings()` in `main.js`; movement choice persists in `localStorage.ek_control`.
- **HUD action buttons** (character/inventory/menu, `.hud-btns`) moved to the
  **right** side.

### ▶ Tutorial exit / "can't walk to Lannager" — DESIGN DECISION (owner-confirmed)
**Symptom:** new game starts in `I10_tutorial` (Adaon's road); the player couldn't
get anywhere and got stuck. **Findings (verified):**
- `I10_tutorial` is loaded as a **standalone interior** (NOT in `world-grid.json`,
  which has chunks E9…I10 etc.; the tutorial is a disconnected copy of I10).
- Its `transitions: []` and `edgeExits: {}` are **empty** — and `edgeExits` isn't
  even handled in code. So the map has **no walk-out exit**. Collision data is
  fine (flood-fill from the start entry `0001`=c85,r41 reaches the whole map incl.
  the west edge; ~8817 cells).
- The **only intended exit** is the camp: two `triggers` in the map start Adaon's
  conversations; walking into the west camp zone (`c0-13,r29-45`, gated by
  `VariableLower#want_letter_back,10`) starts `adaon_tutorial_camp`, whose node 50
  ("Good night, friend") runs:
  `NPCStopFollowing#adaon_tutorial; StopRender#; Travel#H10,1,14; Sleep#;
   SetVariable#want_letter_back,10; PlayerRobbed#`.
- The dialogue engine (`src/dialogue.js`) implements `Travel#map,entry`
  (`goArea`) and a few verbs, but **does NOT implement `Sleep#`, `StopRender#`,
  `PlayerRobbed#`** (they're silently ignored). `Travel#H10,1,14` → `goArea('H10','1')`
  but H10 has no entry `'1'` (only `2/3/999/0001`), so it falls back to map-centre.

**DECISION (owner):** keep the tutorial **instanced / one-time** exactly like the
real game — you camp, **sleep, screen fades to black, you're robbed, and wake up in
the world (H10) near Lannager**, and never return (the `want_letter_back=10` set is
the gate). A tried west-edge-transition hack was **reverted** (it let you wander back
and skipped the sleep beat — wrong).

**TODO to finish this (next):**
1. ~~Implement the sleep/wake actions in `dialogue.js` `runActions`~~ ✅ **DONE**
   (`deobf/TUTORIAL_EXIT_SPEC.md`). `runActions` now runs the whole action list and
   **defers `Travel#`** to the end, so `SetVariable#want_letter_back,10` +
   `PlayerRobbed#` still fire. `StopRender#`/`Sleep#` → a **fade-to-black overlay**
   (`scene.fadeBlack`) wrapping the area swap; `PlayerRobbed#` resets gold to 18
   (matches `Player.C1`); `NPCStopFollowing#` now drops Adaon (was an unhandled
   no-op). Also fixed a latent parser bug: EK wraps comma-bearing action fields in
   `"…"` and the old parser left the quotes on, so the FIRST verb of every quoted
   list silently failed — `parseConversation` now unquotes. Entry fix: scripted
   `Travel#` prefers the `0001` marker (`entryOf(..., preferDefault=true)`), so
   `Travel#H10,1,14` lands at H10's east edge. Covered by `verify.mjs` **tutorial-exit**.
2. Make the camp reliably reachable/triggering (it is reachable; verify the trigger
   fires when walking the road, and that the conversation can't be closed early
   leaving the player trapped with no exit).
3. Then confirm the seamless world path H10 → … → Lannager is walkable.

### ▶ In-game character / inventory screen — TODO (owner wants it, has a reference)
- Owner: **remove the bottom HUD buttons**; clicking the **portrait** should open a
  full character/inventory screen like the real game (they sent a screenshot: Petra
  Devini, Cleric — Traits/attributes, Attack Stats, Armor, Resistances, equipment
  paper-doll, Backpack grid, Quick Slots, Journal/Skills/Reputation/Details,
  Unequip/Back, gold).
- **Faithfulness:** trace the real screen first (`InventoryScreen`/`CharacterSheet`
  cluster in the decompiled sources; `python3 tools/trace_calls.py`; `deobf/`).
  Write the spec to `deobf/` before building.
- **What's buildable now vs blocked:** attributes/derived-stats/resistances/skills/
  gold exist (`PlayerModel`, `deobf/CHARACTER_STATS_SPEC.md`) — the stats side of the
  screen can be built. The **equipment paper-doll + backpack + quick-slots need the
  item/inventory system**, which doesn't exist yet (comes with Combat, TODO #3).
  So: wire portrait → open the panel, build the stats half faithfully, stub the
  item half until the inventory system lands. The current `src/hud.js`
  `renderCharacter()` panel is the seed.

Verify anytime: `cd web && node verify.mjs` (drives real Chrome, screenshots to
`web/shots/`, asserts start/creation, movement (tap + joystick), seamless world +
transitions, NPCs + dialogue, day/night, zoom, 4 orientations, offline, saves).

---

## Track A — Source recovery (de-obfuscation of the APK)

**Location:** `recovered/` (raw decompiled source), `port/` (buildable subset),
`deobf/` (maps + analysis), `tools/` (the toolchain). **Status:** 154 of 499
classes compile against stock libGDX; the whole game logic/data/entity model is
recovered and readable.

Reproduce the whole thing from the APK:
```
tools/extract.sh <ExiledKingdoms.apk>     # decompile + extract assets
tools/rebuild_core.sh <decompiled/sources> # apply all de-obfuscation transforms
tools/verify_core.sh                       # compile the known-good set -> GREEN: 154/499
```

The pipeline (`tools/rebuild_core.sh`) in order: select game packages → stub the
platform (`port/stubs/`) → reconstruct broken enums → remap obfuscated libGDX
classes → jadx-artifact hand-fixes → widen `private` → JavaParser symbol solver
(type-aware method-call de-obfuscation).

Key tools:
- `tools/triage.py` — **run this first each session.** Dependency-aware: ranks
  failing classes by unblock impact + groups by error type. `python3 tools/triage.py port/core/src/main/java <errs.txt>`.
- `tools/remap_gdx.py` — obfuscated libGDX class/import/header remapper.
- `tools/javaparser/Remap.java` + `deobf/method_lookup.tsv` — symbol-solver
  method-call de-obfuscation; logs unmapped calls to extend the map.
- `tools/match_more.py`, `tools/fix_enums.py`, `tools/widen_private.py`.

Docs in `deobf/`: `METHOD_RECOVERY.md`, `R8_OPTIMIZATION.md`,
`REMAINING_BLOCKERS.md`, `ROUTE1_SYMBOL_SOLVER.md`, `TRIAGE.md` (via `tools/`).

**Honest state:** the remaining ~345 classes are the render/asset/UI tier, where
R8 optimization merged classes and hints mislead (e.g. `l` claimed
`GdxNativesLoader` but was a logger; `r/d` claimed `AssetManager` but is a custom
wrapper). It's slow, correctness-critical, per-class reconciliation — and it's the
layer you'd rewrite for web anyway. **This is why Track B (rebuild) is
recommended over finishing Track A.** Track A's value is as a complete, readable
*specification* of the game's behavior.

---

## The mods
Three third-party mods were analyzed/collected — see `MOD_ANALYSIS.md` (Multiplayer
mod fully diffed; Sorrow Mod + ENB collected, not yet diffed). The base game is the
owner's; the mods' original additions belong to their creators (winlatorbrasil,
Sorrow Mod, ENB authors) — permission needed to ship any of it.

---

## Cheat mod on the owner's 4.2.2 base APK — DONE & working
Owner-requested cheats (no-clip toggle + max reputation) on the owner's 2023 32-bit
`Exiled Kingdoms.apk` (installs on Android 4.2.2). Delivered as three usable inventory
items (Tome of Renown = max rep for 23 factions; Phase Stone = no-clip ON; Anchor Stone
= no-clip OFF), seeded into every new character. Full spec + **the debugging log of how
it was made to work** are in `deobf/CHEAT_MOD_SPEC.md` ("How we got it working"). Short
version — three bugs, each found by disassembling the 2023 dex and cross-reading the
decompiled sources:
1. **Boot crash (loading screen, before main menu):** cheat items missing from
   `items_text.txt` → name lookup NPE. Fix: add matching name/desc rows there too.
2. **New-Game crash:** grant used `CharacterInventory.a(I)Z` (= equip backpack slot #i,
   AIOOBE), not add-by-id. Fix: grant via `CharacterSheet.a(I)Z`.
3. **No Use button (Drop only):** items were type `general` (never usable). Fix: type
   `wand` — usable, not equippable (button reads USE), not consumed (reusable toggles).
Reproduce: `tools/build_cheat_mod.sh` pipeline + `tools/patch_cheats_2023base.py` (the
2023-anchor patcher); **sign v1 with a SHA1withRSA cert** (old Android rejects SHA-256/384
certs). Output: `dist/ExiledKingdoms-cheats-4.2.2.apk` (Git LFS).

---

## Repo map
```
web/                Track B — Phaser 3 web rebuild (the product)
  web/src/          main.js (scene), world.js (seamless streamer), map.js, move.js,
                    sprite.js, entity.js (NPCs), dialogue.js, char-create.js,
                    joystick.js, saves.js
  web/tools/        tmx2json, gen-manifest, gen-bestiary, gen-skills, gen-creation,
                    gen-world-grid, gen-icons (item/object icons), extract-skin (atlas slicer)
  web/assets/       tmx/ (151 maps), sprites/, portraits/, conversations/, ui/,
                    data/ (bestiary, skills, creation, world-grid)
  web/verify.mjs    end-to-end headless-Chrome check (keep green)
recovered/src/      Track A — decompiled game source (readable spec)
port/core/          Track A — buildable subset (154 green) + port/stubs
deobf/              de-obfuscation specs (ENGINE, UI, CAMERA, CHARACTER_STATS, …)
tools/              Track A toolchain (extract, rebuild, triage, remappers, verify,
                    trace_calls — obfuscated call-graph tracer)
MOD_ANALYSIS.md     what the Multiplayer mod changed
REVERSE_ENGINEERING.md  original recovery writeup
CONTINUE_HERE.md    this file
```

## Continuing on another account
Repo: `knightdx91-alt/Exiled-Kingdoms`, branch `main` (main-only, no branches/PRs —
see CLAUDE.md). Clone it, put the base APK somewhere, run the `tools/*.sh` scripts to
rebuild Track A, and `cd web && npm install && node verify.mjs` for Track B.
Recommended focus: Track B TODO #1 (creation traits + abilities pages — data is
already in `web/assets/data/creation.json` + `skills.json`).

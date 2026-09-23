# MP mod gameplay features 6–11 (owner: "No cheats at all. Do all 1-12")

Recovered from the MP mod (1218, `/tmp/mp/x/s_norm`) and mapped onto our 4.2.2 base. Their UI
classes live in `a1/` + `z0/`; ours in `e/a/d/`. Installed by `tools/patch_mp_features.py`
(runs after `patch_multiplayer.py`); Java glue `EkFeat` in `tools/mp_java/src`.

**Excluded — cheats.** The CharacterWindow "MODS" button opens `ModMenuDialog` (`a1/p0`):
infinite mana, fast cooldown, XP×3, infinite gold, +1 skill/ability point, no damage, teleport.
The PDALIFE floating menu (`com/android/support/Menu`, `libPDALIFE.so`) is the repacker's cheat
overlay. The `mod_*` variable reads in Character/CharacterSheet/CharacterStats/Player only serve
those toggles. None of it is ported.

## 6. Raise difficulty (their `z0/d0` GameOptionsWindow → ours `e/a/d/b0`)
Vanilla has **Lower Difficulty** only; the mod adds **Raise Difficulty** under it.
* Ladder (same ints in both): Story 4 → Casual 3 → Normal 0 → Hard 1 → Ironman 2.
  `raiseDifficulty()`: 4→3, 3→0, 0→1, 1→2 (2 stays). Our `GameData.b()V` is the mirror (lower).
* Button `RAISE_DIFFICULTY` (0.9 font scale like Lower), own row under Lower, disabled while
  difficulty == 2 (Ironman). Listener → confirm dialog `RAISE_DIFFICULTY_CONFIRM` → on yes:
  raise, hide the options window, show `DIF_RAISED`, whose OK calls `GameLevel.b(false)` exactly
  like `DIF_LOWERED`.
* Implementation: clone our lower classes `b0$a`, `b0$a$a`, `b0$a$a$a` → `b0$ekR*` with the key
  strings and the GameData call swapped; new `GameData.ekRaise()V`; field `b0.ekRaise`.
* Strings (EN/ES from the mod; added to our tables when absent): `RAISE_DIFFICULTY`,
  `RAISE_DIFFICULTY_CONFIRM`, `DIF_RAISED`.
* Note: raising into Ironman mid-game is allowed by the mod (confirm dialog guards it).

## 7. Equipment upgrades (their `Rules.cdxUpgrade*`, CharacterWindow/Sheet/Inventory hooks)
Level **L = 0..10 per item id** (not per copy), stored in the save as game variable
`item_upg_<itemId>` (so it survives save/load with no format change).
* **Subtype** of an item: weapon (`ItemType.c`): staff/wand 4, ranged 5, two-handed 3, else 2;
  `d` (armor) 0; `e,f,g,h,i` 1; `m,n` 6; `o,p` 7; other 0.
* **Cost of reaching level n** (n = L+1): gold = max(item.value, 750) × (3n² + 15 + [n≥6]·2n +
  [n≥9]·10 + sub-term), sub-term: subtypes 0,3 → +n; 1,7 → +n/2; 4,5 → +1; others 0.
  Material 1 qty: n≤3 → 2n+2; ≤6 → 3n−1; ≤8 → 4n−7; else 5n−16.
  Material 2 qty: n≤3 → n+1; ≤8 → 2n−2; else 3n−10.
  Material ids (vanilla gems 2001–2015, present in our base) by subtype and n — transcribed
  verbatim into `EkFeat.primaryId/secondaryId` (checked branch by branch) from `cdxUpgradePrimaryResourceId` /
  `cdxUpgradeSecondaryResourceId`.
* **Effects** (all recomputed from the variables, nothing stored on items):
  * main-hand weapon: damage bonus (`CharacterSheet.m()`, added to min and max) **+L**; min
    damage **+⌊4L/10⌋**, max **+⌊7L/10⌋** (`B(Z)`); secondary (elemental) damage **+⌊L/3⌋**.
  * every equipped slot (body, head, hands, legs, feet, main hand, off hand unless two-handed,
    ring ×2, belt, cloak, necklace): S = ΣL → armor (`DefenseBonus`) **+S**, HP **+2S**, mana
    **+2S**, added at the end of the inventory recompute.
* **UI**: CharacterWindow, an equipped item selected (`X0 == 2`): the second action button
  (`e0`, hidden in vanilla there) shows **UPGRADE**, disabled at +10. Tap → if gold ≥ cost and
  backpack has both material stacks: pay, remove materials, L+1, refresh sheet, sound `item`,
  log "Upgrade complete: <name> +n (…)" and refresh the window; else log what is required.
  Item preview text gets "Upgrade: +L/10" and the next-level cost; equipped/backpack slot icons
  draw a "+L" badge.
* APPROX: the mod's messages are Portuguese → English (as before for the chat strings).

## 8. Storage shortcuts (their CharacterWindow `U(…)` + listener `a1/e` ids 10–15)
* **Vault button**: in the plain inventory (no container open) of the **player** who owns at
  least one vault (`hasVault..hasVault4`): an orange `vault` icon button before the bottom-right
  buttons. Tap → open the first owned vault (`vault`, `vault2`, `vault3`, `vault4`) exactly as a
  vault chest does (`CharacterWindow.a(1, container)`), sound `item`. The vanilla vault tabs
  then switch between vaults.
* **Bag of holding tabs**: vanilla has one portable bag (`bag_of_holding`). When a bag is open,
  a row of five buttons **1–5** opens `bag_of_holding`, `bag_of_holding2..5`; the open one is
  tinted orange. (Container names read back as "bag of holding…" — `getName()` swaps `_`→` `.)

## 9. Recover (their `Player.Q1/V1` + `applyRecoverToActor`, `refreshRecoverUiAndLan`)
Their change over vanilla: after Recover and after resting, refresh the HUD and
`LanGameBridge.forcePublishLocalState()` so other players see the restored HP at once (plus a
Fury refresh that exists only in the 2025 skill code). Ours: `Player.I0()` (Recover) and
`Player.b(Z)` (rest) → `EkFeat.afterRecover()` before return. Our HUD redraws bars every frame,
so only the publish is needed.

## 10. Forward shot without target — already in our base
Their `spawnForwardRangedProjectile()` (shoot 100 px ahead along movement, or facing when still,
if no target) is **our vanilla `Character.v0()`**, already called by the player shot path
`a(String,I,String)`: `w0()` target → `x0()` nearest → `v0()` forward. The 2025 build had dropped
it; the mod restored it. Nothing to port.

## 11. SAF backup (their `MainActivity.launchExportPicker/launchImportPicker/onActivityResult`)
Android's Storage Access Framework picker instead of a fixed `Download/EK.bak` path (needed on
Android 10+ scoped storage). Request codes 0xBACA (export, `CREATE_DOCUMENT`, type
`application/octet-stream`, title `EK.bak`) and 0xBACC (import, `OPEN_DOCUMENT`, `*/*`).
* Export writes the same zip as our `Serializer.a(Z)` (zip of `data/saves`, entry prefix "").
* Import: (theirs moves saves to `data/saves_backup/`) — ours keeps our own backup rule: saves →
  `data/saves.bak`, then extract with our `Serializer.a(File zip, File dataDir)`, then
  `e/a/b/e.j()` and `Serializer.i()` as our restore does, on the GL thread.
* Our BackupWindows (`e/a/d/e1/a` main menu, `e/a/d/e1/n` in game): the confirm handlers for
  local export/import (`Serializer.a(false)` / `b(false)`) go through `EkFeat.safExport/
  safImport`, which launch the picker on **API ≥ 19** and return true (the success message then
  comes from the result); on older Android (the owner's 4.2.2 device) they return false and the
  original path runs unchanged. GPGS cloud backup (`a(true)`) is untouched.

## Status — built as v29 (2026-09-23)
`tools/patch_mp_features.py` (after `patch_multiplayer.py`, skipped with `EK_SKIP_MP=1`); glue
`EkFeat` (+`$Open`, 2 runnables). Checks on the built dex: every member EkFeat touches outside
itself is public or Android framework; 6,843 call sites in the changed classes have the right
invoke kind; D8 clean. Not device-tested.

APPROX (DEOBFUSCATION_STATUS C17): upgrade messages in English (mod: Portuguese); the vault/bag
buttons open through our own `CharacterWindow.a(1, container)`; SAF import keeps our
`data/saves.bak` backup rule (mod: `data/saves_backup/`) and runs our restore steps; the SAF
path is used only on Android 4.4+ (the picker does not exist below); Recover publishes state only
(our HUD redraws every frame; Fury refresh is 2025-only code).

## Item 4 (code side) — every UI code change in the mod, accounted for
Method: all string constants per `.source` file, MP 1218 vs vanilla 1217 (the `.line`-stripped
sizes differ only by debug info). The "redesigned menus" are the art in `data/ui` (merged by
`merge_mp_content.py`); the code changes are:

| Source | Change | Disposition |
|---|---|---|
| MainMenuScreen | LAN ON/OFF button; DONATE→STORE | LAN = our MULTIPLAYER button (B17); STORE is the purchase screen — not ported |
| MainMenuScreen | new logo 512×341 | laid out in the same 360×128 cell in theirs and ours — identical, nothing to do |
| GameHUD | CHAT button (+ "CHAT!"), WARNING_STORE | CHAT = B14; store warning not ported |
| GameOptionsWindow | Raise Difficulty | §6 |
| CharacterWindow / ItemPreviewTable / InventorySlotImage | upgrades, vault button, bag tabs 1–5, MODS | §7, §8; MODS (cheats) not ported |
| BackupWindow / MainActivity | SAF pickers; `…multiplayer/files/EK.bak` path | §11; the path is their package name, ours stays |
| WorldMapImage, GameData | peer markers, world-event chat | PORT_SPEC §7 |
| Player / NPC | arena elimination; composite sprite by gender | PORT_SPEC §7; peers use `EkMp.buildPeerSprite` |
| ModMenuDialog, SkillWindow, TraitsWindow, CharacterStats/Sheet `mod_*` | cheats (+1 SP/AP, XP×3, no damage…) | not ported |
| LicenseUtils/LicenseTable/LockedDialog/StoreWindow/Settings/Patching | trial-area list and license/store handling | not ported (licensing, not gameplay) |
| TeleportWindow | one button relabelled with hard-coded Portuguese "Torre de Tremadan" | not ported (a localisation regression) |
| Rules (EK_LOOT), GameWorld.ensureLoaded | debug logging; reload guard of their 2025 engine | not ported |

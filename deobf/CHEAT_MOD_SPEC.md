# Cheat Mod Spec — base-game APK (no-clip + max reputation)

A minimal, faithful mod of the **base** `Exiled Kingdoms.apk` that adds two owner-requested
cheats, delivered as **activatable inventory items** plus one small engine hook. Reversed
from the decompiled sources; every change is anchored to a real game mechanic.

## What the mod does
> **Item type = `wand` (not `general`).** `Item.g()` only reports an item as *usable* when
> its type is `potion`, `scroll`, or `wand`; a `general` item shows **only a Drop button**,
> no **Use**. `wand` is the right pick: it is usable, it is **not** equippable (`Rules.l()`
> excludes it, so the action button reads **USE**, not EQUIP), and — unlike potion/scroll —
> the loader does not append a hidden "remove self" action, so the item is **reusable** (the
> no-clip on/off toggles must survive repeated use). weaponStats stays null (empty col 3),
> which is safe because the item-preview UI only reads weaponStats for `type == weapon`.

Three new items are granted to every **newly created** character (they also survive the
tutorial robbery, since they are seeded in the same reset the robbery uses):

| Item id | Name | `OnUse` effect |
|--------:|------|----------------|
| 9990 | Tome of Renown | Sets `REP_<faction>` = 100 for all 23 player-facing factions (max, "Legendary Hero") |
| 9991 | Phase Stone   | `SetVariable#noclip,1` → turns no-clip ON |
| 9992 | Anchor Stone  | `SetVariable#noclip,0` → turns no-clip OFF |

Use an item from the inventory to fire its `OnUse`. No-clip lets you walk through any
collision (reach any door / cross any wall). It cannot enter a "building" that has no
interior map defined — only genuinely enterable structures.

## Reversed mechanics (why this works)

### Reputation
- `WorldFaction.e()` → `gameVariables.b("REP_" + id)`; `WorldFaction.d(int)` tiers cap at
  100 = `LEGENDARY_HERO`. Faction ids come from `assets/data/world/factions.txt`.
- `ScriptedAction` **case 1 `SetVariable`** raw-sets a variable (no faction clamp — clamp
  is only on `IncVariable`/`DecVariable` for `REP_`). So `SetVariable#REP_varsilia,100`
  writes exactly 100.
- Item `OnUse` is an `ActionsSet`; `ActionsSet(String)` → `FDUtils.s()` splits on `;` and
  keeps `#`-bearing verbs. A single `OnUse` cell therefore runs all 23 sets.
- The 23 player-facing factions (have a `flag`, excludes enemy/bandits/beasts/player/
  neutral/angrynpc): varsilia, mercia, ilmara, thuram, the_three, town_lannegar,
  town_kingsbridge, town_rhoneis, town_jabal, town_sydarun, town_new_anthur, town_fogas,
  town_whitetower, varannari, loreseekers, goldenhand, seventhhouse, wizardsguild,
  warriorsguild, friguld_governor, town_lamis, town_port_malan, town_solliga.

### No-clip  ⚠️ POLARITY CORRECTED (2026-07 build)
- The tile-collision method in the **2023/4.2.2 base** is `e/a/c/b->c(II)Z`
  (`.source "GameMap.java"`). **It is a SOLID/BLOCKED predicate, not a walkable one.**
  Reading the real smali, it returns:
  - `true` (blocked) for: out-of-bounds tiles, tiles where `p[x][y] > 0`, and **closed doors**;
  - `false` (passable) for open passage.
- **The previous build had this inverted.** It prepended `if noclip==1 return true`, which
  made *every* tile report as SOLID → the player and all NPCs/monsters froze in place
  (attacks still landed because combat never consults tile collision; ranged still fired
  from a distance). That is exactly the "I can't move at all, enemies can't move, but I can
  still attack" symptom the owner reported.
- **Corrected patch:** insert the noclip test at the in-bounds entry (`:cond_1`, reached
  only *after* the bounds check). If `GameData.O().gameVariables.b("noclip") == 1`, return
  **`false`** (passable) so every in-bounds tile can be walked through. Out-of-bounds stays
  SOLID (the bounds branch is untouched), so pathfinding never indexes `p[x][y]` out of
  range. `.locals` is widened `3 → 4` to get a scratch register without disturbing the
  original body.
- Engine variables default to **-255** for unknown names, so testing `== 1` keeps no-clip
  **off** on a fresh game until the Phase Stone sets `noclip = 1`.
- No-clip is **global** (NPCs/monsters also ignore tile collision while it is on) because
  `c()` has no per-actor context. Toggle it off with the Anchor Stone.

### Export-save storage permission  ⚠️ NEW (2026-07 build)
- Export writes `EK.bak` via `Gdx.files.external(...)`, which in this build resolves to the
  app's **external files dir** (`getExternalFilesDir(null)` →
  `Android/data/net.fdgames.ek.android/files/`). On **Android 4.2.2 (API 17)** writing there
  still requires `WRITE_EXTERNAL_STORAGE` (the permission only became implicit for the
  app-specific dir on API 19+). The clean base declares **no** storage permission, so the
  export silently failed on the owner's 4.2.2 device.
- Fix: add `WRITE_EXTERNAL_STORAGE` + `READ_EXTERNAL_STORAGE` (both `maxSdkVersion=29`) to
  the manifest. The app already declares `requestLegacyExternalStorage="true"` for
  API-29 scoped-storage compatibility, so no further change is needed.

### Delivery
- `Player.C1()` (obf `Final/Player->C1()V`) is the base-inventory reset (gold=18, fresh
  `CharacterInventory`, `inventory.c(0)`), reused by `PlayerRobbed#`. Three
  `inventory.c(9990/9991/9992)` calls are appended before `return-void`, so the items are
  part of the starting kit and reappear after the tutorial robbery.

## Files changed (2 smali methods + 2 assets)
- `assets/data/rules/items.txt` — 3 appended rows (25 cols each, ids 9990-9992).
- `assets/data/rules/items_text.txt` — 3 appended rows (19 cols each, ids 9990-9992):
  the item name/description. **Required:** `Rules.Load()` resolves every item's on-screen
  name by looking its `item_ID` up in `items_text.txt`; an item present in `items.txt` but
  missing here NPEs during "loading items" (subtask 6/12) → **crash on the loading screen,
  before the main menu**. All 130 stock `general` items have an `items_text.txt` entry.
- `m0/b.smali` — `C(II)Z`: no-clip check.
- `net/fdgames/GameEntities/Final/Player.smali` — `C1()V`: grant the 3 items.

## Build / repackage
See `tools/build_cheat_mod.sh <base.apk> <out.apk>`. Pipeline: baksmali `classes.dex` →
apply the two smali edits + append item rows → smali reassemble → swap `classes.dex` and
`items.txt` into a copy of the APK (leaves `resources.arsc` untouched) → strip old
`META-INF` → sign v1+v2 with `apksig` (self-signed debug key). Verified: `jarsigner
-verify` (v1) + `apksig ApkVerifier` (v2), and both patched methods confirmed present in
the final dex.

## Applied to the 2023 / Android-4.2.2 base (owner-supplied APK)
The owner's `Exiled Kingdoms.apk` (build dated 2023-01-06, single `classes.dex`,
`armeabi-v7a` libs, low `minSdkVersion` — installs natively on Android 4.2.2) uses a
**different method-name obfuscation** than the 2025 base, so the smali anchors were
re-reversed. Same three changes, remapped:

| Purpose | 2025 base | 2023 / 4.2.2 base |
|---------|-----------|-------------------|
| `GameData` self-accessor | `->v()` | `->O()` |
| GameMap class / walkability method | `m0/b` `->C(II)Z` | `e/a/c/b` (`.source "GameMap.java"`) `->c(II)Z` (reads `p:[[I`) |
| Base-inventory reset (grants + robbery reset) | `Player->C1()V` | `Player->y0()V` (gold `0x12`, fresh `CharacterInventory`, `inventory.a(0)`) |
| Add-item-by-id | `CharacterInventory->c(I)Z` | `CharacterSheet->a(I)Z` (backpack.a(id) → Rules.f(id)) |

> **Load-crash fix (corrected anchor).** The add-item anchor was originally
> mis-mapped to `CharacterInventory->a(I)Z`. In this 2023 base that method is **not**
> add-by-id — it treats its argument as a **backpack slot index** (`Items.e(p1)` →
> `array[p1]`), so `inventory.a(9990)` threw `ArrayIndexOutOfBoundsException` during the
> new-game inventory reset (`y0()`), which is the "loads for a moment, then crashes"
> symptom. The correct add-by-id here is `CharacterSheet->a(I)Z` — exactly what the
> unmodified `y0()` already uses for the starting weapon (`sheet.a(0x1f5)`). The three
> grants now call `sheet.a(9990/9991/9992)`.
| Variable getter | `GameVariables->b(String)I` | `GameVariables->b(String)I` (unchanged) |

`items.txt` (25 cols), the 23 factions, and the `book4`/`ring1` icons all exist unchanged
in this base. Built with `tools/build_cheat_mod.sh`'s pipeline using the remapped anchors;
signed v1 (**SHA1withRSA** — required by 4.2.2) + v2/v3. Output:
`dist/ExiledKingdoms-cheats-4.2.2.apk`.

## How we got it working (debugging log, 4.2.2 base)

The first cheat build installed and ran but **crashed on the loading screen before the main
menu**; once that was fixed it crashed **on New Game**; once that was fixed the items
appeared but **couldn't be used** (Drop only). Three distinct bugs, each verified against the
disassembled 2023 `classes.dex` (`baksmali`) and the decompiled 2025 sources (same game
logic, different obfuscation), then fixed in `tools/patch_cheats_2023base.py` and rebuilt:

1. **Startup crash — missing `items_text.txt` rows.**
   `Rules.Load()` (subtask 6/12, "loading items") resolves each item's on-screen
   name/description by matching its `item_ID` in `data/rules/items_text.txt`; on no match it
   sets `gameText = null` then calls `gameText.get()` → **NullPointerException during boot**.
   All 130 stock `general` items have an `items_text.txt` entry; our cheat items (9990-9992)
   were only in `items.txt`. **Fix:** also append 3 rows (19 cols) to `items_text.txt`.

2. **New-Game crash — wrong add-item anchor.**
   The grant in `Player.y0()` called `CharacterInventory.a(I)Z`, assuming add-by-id. In this
   2023 base that method is **"equip backpack slot #i"** (`Items.e(p1)` → `array[p1]`), so
   `a(9990)` indexed far out of range → **ArrayIndexOutOfBoundsException** during the
   new-game inventory reset. The real add-by-id is `CharacterSheet.a(I)Z` — what the stock
   `y0()` already uses for the starting weapon (`sheet.a(0x1f5)`). **Fix:** grant via
   `sheet.a(9990/9991/9992)`.

3. **No Use button — wrong item type.**
   `Item.g()` reports an item usable only if its type is `potion`/`scroll`/`wand`; a
   `general` item shows **Drop only**. **Fix:** set the three items to `wand` — usable, and
   **not** equippable (`Rules.l()` excludes wand, so the button reads **USE** and fires
   `OnUse`), and **not** consumed (only potion/scroll get a loader-appended "remove self"
   action), so the no-clip on/off stones stay reusable. Null `weaponStats` (empty col 3) is
   safe: the preview UI only reads `weaponStats` for `type == weapon`.

Every build: `baksmali classes.dex` → apply the 2 smali edits → append rows to `items.txt`
+ `items_text.txt` → `smali a --api 15` (dex 035, for Dalvik) → swap `classes.dex` + the two
data files into a copy of the base APK → strip `META-INF` → **sign v1 with a SHA1withRSA
cert** (older Android can't parse SHA-256/384 cert signatures) + v2. Verified each time:
packaged dex is byte-identical to the patched dex, cert sig alg is SHA1withRSA, and every
`item_ID` parses as an integer with correct column counts.

## Deliberate deviations / APPROX
- No-clip is global, not player-only (choke point limitation). Documented above.
- Cheat delivery is **new-characters-only** (owner's choice); existing saves would need an
  early merchant stocking the items — not included.
- Signed with a throwaway self-signed key → sideload install (not a Play update of the
  original). Uninstall the Play version first (different signer).

## 2026-07 rebuild (owner's new base — current)
The owner supplied a fresh, **unmodified** `Exiled Kingdoms.apk` (Drive) as the base to use
going forward; it is committed as `dist/ExiledKingdoms-base-4.2.2.apk` (Git LFS, 126 MB,
`classes.dex` dated 2023-01-06, `armeabi-v7a`, `minSdk 16` / `target 29`). Two owner-reported
regressions were fixed on top of it:

1. **No-clip froze everyone (polarity bug).** `e/a/c/b->c(II)Z` is a *solid* predicate, not
   *walkable* — the old `return true` made all tiles solid. Corrected to `return false`
   (passable) for in-bounds tiles when `noclip==1`. See the No-clip section above.
2. **Export save did nothing (missing permission).** Added `WRITE/READ_EXTERNAL_STORAGE`
   to the manifest so `EK.bak` can be written on Android 4.2.2. See the Export-save section.

Build tooling for this base is `tools/patch_cheats_v2.py`, applied to an **apktool**-decoded
tree (`apktool d` → run patcher + manifest edit → `apktool b`), then **v1-signed with
SHA1withRSA** (`jarsigner -digestalg SHA1 -sigalg SHA1withRSA`, with a
`jdk.jar.disabledAlgorithms=` security override since modern JDKs disable SHA1). Output:
`dist/ExiledKingdoms-cheats-4.2.2.apk`. Reputation grant + wand-type usable items are
unchanged from the prior build (they worked); only the two fixes above and the single-
`assets/` path layout differ.

### `INSTALL_PARSE_FAILED_NO_CERTIFICATES` — signing gotcha (hit 2026-07)
The first 2026-07 build would not install (Lucky Patcher reported
`INSTALL_PARSE_FAILED_NO_CERTIFICATES`). Cause: **the signing certificate itself** was
`SHA384withRSA`. `jarsigner -digestalg SHA1 -sigalg SHA1withRSA` only controls the manifest
digests and the signature block — it does **not** control how the self-signed cert in the
keystore was signed, and `keytool -genkeypair` silently defaults to SHA-384 on modern JDKs.
Android ≤ 4.2.2 (API 17) cannot parse SHA-256/384 certificate signatures, so it rejects the
APK as having *no* certificate at all.

**`-sigalg SHA1withRSA` must be passed to `keytool -genkeypair` as well:**
```
keytool -genkeypair -keystore ek.keystore -alias ek -keyalg RSA -keysize 2048 \
  -sigalg SHA1withRSA -validity 10000 -storepass … -keypass … -dname "…" \
  -J-Djava.security.properties=relax.security
```
Verify before shipping — this must print `SHA1withRSA`:
```
keytool -printcert -jarfile <out.apk> | grep -i "signature algorithm"
```
Full 4.2.2-safe checklist: cert alg = SHA1withRSA, manifest digests = `SHA1-Digest`,
`SHA1-Digest-Manifest` in the `.SF`, and every content entry signed (`jarsigner -verify`
reports `jar verified`; entry count should exceed the manifest `Name:` count by exactly 3).

### Export-save, take 2 — the permission was not the cause
Adding WRITE/READ_EXTERNAL_STORAGE was necessary but did **not** make export work. The
actual reason, reversed rather than assumed:

`Serializer.a(Z)V` writes the backup with
`new FileOutputStream(Gdx.files.getExternalStoragePath() + "EK.bak")`, and
`AndroidFiles.getExternalStoragePath()` returns the `externalFilesPath` field, set in the
constructor from `getExternalFilesDir(null)` -> `/sdcard/Android/data/net.fdgames.ek.android/files/`.
The entire export body sits inside `catch (Exception) { printStackTrace(); }`, so **every
failure is silent** — precisely the "nothing happens" symptom.

On Android 4.2.2 that app-external dir is unreliable: `getExternalFilesDir()` returns null
when the volume is not mounted at construction (AndroidFiles then stores **null**, making
the path the literal `"nullEK.bak"`), and the directory is not guaranteed to exist —
`new FileOutputStream(path)` does not create parent directories, so it throws
`FileNotFoundException`/ENOENT straight into the silent catch.

**Fix (`tools/patch_export_fix.py`, 2 smali edits):**
1. `AndroidFiles.getExternalStoragePath()` now returns
   `Environment.getExternalStorageDirectory().getAbsolutePath() + "/"` (i.e. `/sdcard/`),
   falling back to the original field if unavailable. `/sdcard` always exists (no mkdirs
   needed) and `WRITE_EXTERNAL_STORAGE` covers it. This also matches the game's own
   help text for this era — `BACKUP_INFO_FILE_TEXT`: *"exported to a file called EK.bak,
   on the root of your phone storage"* — and makes the import fallbacks the engine already
   probes (`download/EK.bak`, `Download/EK.bak`, `sdcard/Download/EK.bak`) resolve to real
   folders. Export/import stay symmetric: `FileHandle.file()` resolves `FileType.External`
   through this same method. Scope is safe — the only consumers of this root are
   `Serializer` (backup/restore) and the two backup-window classes that display the path.
2. The swallowed exception in `Serializer.a(Z)V` is now also pushed to `GameConsole`, so a
   future failure is diagnosable instead of invisible.

**Result:** the backup lands at `/sdcard/EK.bak`, which is also far easier to retrieve on
a 4.2.2 device than `Android/data/...`.


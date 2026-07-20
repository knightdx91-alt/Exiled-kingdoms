# Cheat Mod Spec — base-game APK (no-clip + max reputation)

A minimal, faithful mod of the **base** `Exiled Kingdoms.apk` that adds two owner-requested
cheats, delivered as **activatable inventory items** plus one small engine hook. Reversed
from the decompiled sources; every change is anchored to a real game mechanic.

## What the mod does
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

### No-clip
- Movement/pathfinding walkability is `GameMap.C(int x, int y)` (obf `m0/b->C(II)Z`),
  called by `Player`/`Character`. Returns `f2431p[x][y] == 1` (plus door/bounds handling).
- Engine variables default to **-255** for unknown names (`GameVariables` line 38), so an
  "odd = on" scheme would read ON by default. The patch instead tests `noclip == 1`, which
  a fresh game (default -255) never satisfies → off until the Phase Stone sets it to 1.
- Patch prepends to `C(II)Z`: read `GameData.v().gameVariables.b("noclip")`; if `== 1`
  return `true` (walkable); else fall through to the original body.
- No-clip is **global** (NPCs/monsters also ignore collision while it is on) because `C()`
  has no per-actor context. Toggle it off with the Anchor Stone.

### Delivery
- `Player.C1()` (obf `Final/Player->C1()V`) is the base-inventory reset (gold=18, fresh
  `CharacterInventory`, `inventory.c(0)`), reused by `PlayerRobbed#`. Three
  `inventory.c(9990/9991/9992)` calls are appended before `return-void`, so the items are
  part of the starting kit and reappear after the tutorial robbery.

## Files changed (2 smali methods + 1 asset)
- `assets/data/rules/items.txt` — 3 appended rows (25 cols each, ids 9990-9992).
- `m0/b.smali` — `C(II)Z`: no-clip check.
- `net/fdgames/GameEntities/Final/Player.smali` — `C1()V`: grant the 3 items.

## Build / repackage
See `tools/build_cheat_mod.sh <base.apk> <out.apk>`. Pipeline: baksmali `classes.dex` →
apply the two smali edits + append item rows → smali reassemble → swap `classes.dex` and
`items.txt` into a copy of the APK (leaves `resources.arsc` untouched) → strip old
`META-INF` → sign v1+v2 with `apksig` (self-signed debug key). Verified: `jarsigner
-verify` (v1) + `apksig ApkVerifier` (v2), and both patched methods confirmed present in
the final dex.

## Deliberate deviations / APPROX
- No-clip is global, not player-only (choke point limitation). Documented above.
- Cheat delivery is **new-characters-only** (owner's choice); existing saves would need an
  early merchant stocking the items — not included.
- Signed with a throwaway self-signed key → sideload install (not a Play update of the
  original). Uninstall the Play version first (different signer).

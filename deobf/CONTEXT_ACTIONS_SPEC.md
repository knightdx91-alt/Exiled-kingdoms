# Context / interact buttons — recovered spec + web mapping

Source of truth: `deobf/GAMEHUD_LAYOUT_SPEC.md` §3 (`GameHUD.E(int)` + the `f2965z`
button set). This doc pins how the web build reproduces it.

## What the real game does
- `player.activables[]` holds the world interactions **currently in reach** (max
  `numActivables`). Up to **4** are shown as icon buttons in the **top-right**
  (`f2965z[4]`, `v` FlashingImageButton). `GameHUD.S()` refreshes their icons each
  frame; the whole set is **hidden when nothing is in reach**.
- Tapping button `i` calls `GameHUD.E(i)`, dispatched by the activable **type**
  (`activables[i].i()`):

  | type | action | web build |
  |------|--------|-----------|
  | 1 | pick up loot on the ground (loot window) | **loot bag** (`pickup`) |
  | 2 | open a known loot pile | folded into `pickup` |
  | 3 | talk to NPC (conversation) | `talk` |
  | 4 | talk to a StaticNPC | `talk` |
  | 5 | enter shop / castle area | `enter` (arch transition) |
  | 6 | harvest plant | — (no harvest nodes exported yet) |
  | 7 | open a container (crate/chest) | `open` |
  | 8 | transition / use door | `enter` |
  | 9 | activate a trigger | — (interior triggers auto-fire on entry) |
  | 10 | sleep at inn | — (no inn nodes yet) |
  | 11 | recall / teleport | — |

## Web mapping (`src/main.js` `refreshContext()` / `doContext()`, `src/hud.js` `setContext()`)
Each frame (throttled ~6/s) the scene scans, within a reach radius of the hero's
cell, for:
- **talk** — `entities` whose `npc.conversation` is set (type 3/4). Icon = the NPC's
  portrait when known, else a speech glyph.
- **open** — unopened `containers` that are NOT loot bags (type 7). Icon = the object's
  own atlas art (`assets/ui/objects/<icon>.png`, e.g. `crate1`), else `loot.png`.
- **pickup** — unopened loot bags dropped by dead enemies (type 1). Icon = `loot.png`.
- **enter** — `currentTransitions()` doors/arches (type 5/8). Icon = an arch glyph.

The nearest few (capped at 4, sorted by distance, exactly like the real cap) are sent
to `HUD.setContext(list, onPick)`, which renders the top-right button column and hides
it when the list is empty. Tapping a button routes to `doContext(action)`, which walks
the hero up to the target if not already adjacent and then talks / opens / picks up /
travels — reusing the same `_pendingTalk` / `_pendingContainer` / `goArea` paths the
tap-to-move interaction already used, so behaviour is identical whether you tap the
world object or its context button.

## Dropped loot bags (deliberate change, was auto-collect)
Previously an enemy's loot was rolled and **added straight to the backpack** on death
(a floater, no ground object) — so there was nothing to interact with, contradicting
the real game's type-1 "pick up loot on the ground". Now `combat._rollLoot` collects
the rolled gold + items into a **loot bag** placed at the corpse's cell
(`scene.dropLootBag`), rendered with `sprites/loot.png` and tracked as a container. It
shows a `pickup` context button (and is tap-openable) like any other container; opening
it transfers gold + items into the backpack. This is APPROX vs. the real loot *window*
(items still auto-transfer on open with floaters, no per-item TAKE/TAKE_ALL window yet)
— logged as A18 in `deobf/DEOBFUSCATION_STATUS.md`.

## APPROX / not-yet
- Button **art** is the object/portrait/`loot.png` stand-in, not the real `f2965z`
  FlashingImageButton skin (unavailable without the APK atlas). Placeholder EK theme.
- Types 6 (harvest), 9 (manual trigger), 10 (sleep at inn), 11 (recall) have no exported
  world nodes in the web data yet, so they never appear.
- No separate loot/harvest **window**; results auto-transfer with floaters.

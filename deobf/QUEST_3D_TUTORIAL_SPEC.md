# Quest 3S 3D build: the tutorial area (spec, written before code)

Owner request (2026-10-25): make EK playable in 3D on a **Meta Quest 3S**, the way the
fan 3D Pokémon remakes rebuilt the original games, starting with **the whole tutorial
area**. Source of truth is **the owner's mod APK, hero-v78** (`dist/hero-v78/`), not the
stock 4.2.2 data. The deliverable is an **installable Quest APK** (sideload).

Track: new **Track D** (`quest/`, a Godot 4.6 + OpenXR project). Track B (`web/`) is not
touched; the game rules here are ported from it and from the recovered specs.

## 1. What "the tutorial area" is (traced from the source)

Orchestrator: `MainMenuScreen` (`l0/e.java`) `s()`:
`new l0.c(screen, new Transition("I10_tutorial", 1))`. A new game loads the map
`I10_tutorial` at entry `1` (marker `0001`). The tutorial is that one map plus the two
conversations its triggers start. Nothing else in the code names it.

### Map `assets/data/tmx/I10_tutorial.tmx` (from hero-v78)
- 96×96 isometric cells, 64×32 base tile, outdoor, music `decline`, no transitions and
  no edge exits (you can't walk out; see TUTORIAL_EXIT_SPEC.md).
- Layers: `nonwalk` (hidden collision mask), `water`, `ground`, `ground2`, `scenery`,
  `objects`, `roofs`.
- Tilesets: grassland, grassland_trees, natural_grounds, rocks_small, stoneroad1,
  stoneroad1-grass, town_objects, spawns.
- Entry `0001` = cell (85, 41): the player starts at the east end of the road.
- **v78 differs from stock 4.2.2 in only two places**, both container contents (it is
  the multiplayer-mod copy of the map):

  | crate | cell | stock 4.2.2 | **hero-v78** |
  |---|---|---|---|
  | Crate | (72, 40) | 501 Iron Dagger | **5000 Potion of Light Healing** (`GainHP#30`) |
  | Crate | (56, 30) | 120 Buckler | **3010 Lesser Ring of Endurance** (Toxic +10) |

- Spawns (13):
  - Adaon `adaon_tutorial` at (38, 41): faction player, conversation `adaon_tutorial`,
    boss/strong lv 3, steel longsword, armor 20, portrait male 21.
  - 10 goblins (`goblin`: weak warrior lv 1, goblin spear 1–4, Fire res 10, loot
    `goblins_lannegar`, aggro sounds goblin-7/11/13) at (56,50) (56,31) (27,40) (55,38)
    (26,41) (46,39) (65,57) (55,30) (28,38) (18,39). Some have wander 1–2.
  - 2 grey wolves (`grey_wolf`: lv 2–3, wolf bite 2–5, Fire −25 / Cold +25, loot
    `grey_wolf`, sound beast1) at (27, 41) and (46, 31).
- Triggers (2):
  1. Road zone c34–35, r36–48: `StartConversation#adaon_tutorial,adaon_tutorial`, only
     if `NPCisNotInParty#adaon_tutorial`.
  2. West camp zone c0–13, r29–45: `StartConversation#adaon_tutorial,adaon_tutorial_camp`,
     only if `VariableLower#want_letter_back,10`.

### Conversations (identical in v78 and stock)
- `adaon_tutorial`: nodes 1/2/3/4/5/11. Node 5 runs `NPCFollow#adaon_tutorial`, so
  Adaon joins and fights beside you. Node 1 has an "already a follower" branch.
- `adaon_tutorial_camp`: nodes 1–11 → 50. Node 50 ("Good night, friend.") runs the
  whole exit:
  `NPCStopFollowing#adaon_tutorial; StopRender#; Travel#H10,1,14; Sleep#;
   SetVariable#want_letter_back,10; PlayerRobbed#`.

### Loot tables (v78 `loot.txt`, UTF-16)
- `goblins_lannegar`: 102 leather bracers 8 %, 201 Goblin flesh-cutter 15 %, gold 50 %.
- `grey_wolf`: gold 50 %, 1002 damaged pelt 38 %, 1003 perfect pelt 23 %, 2001 small
  ruby 2 %, 2002 small emerald 2 %.

### Checklist (each item is implemented and ticked in the Quest build)
- [x] New game → hero placed at `0001` (85, 41), facing down the road (west).
- [x] The full map rendered: all 6 visible layers, collision from GameMap.v().
- [x] Adaon standing at (38, 41); talk to him by pointing at him, or walk into trigger 1.
- [x] `adaon_tutorial` tree with choices; node 5 makes Adaon follow and fight.
- [x] 10 goblins + 2 wolves with aggro / chase / leash and melee, using the recovered
      damage rolls and armor mitigation (COMBAT_SPEC.md, as ported in web/src/combat.js).
- [x] Adaon as a companion: attacks your target or the nearest enemy, otherwise trails you.
- [x] Kill XP, levels from the creation.json XP table, loot bags (gold + item names).
- [x] Both crates open with their **v78** contents; the potion is usable (heals 30).
- [x] Trigger 2 starts the camp talk; node 50 ends the area: fade to black, sleep,
      `want_letter_back=10`, robbed (gold 18, items wiped), Adaon gone.
- [x] Wake-up card: "robbed, Adaon gone, head for Lannegar". This is where the tutorial
      area ends (`Travel#H10` is outside the area).
- [x] Music `decline`; goblin/wolf aggro sounds; hit/swing sounds.

## 2. 3D presentation (the deliberate deviation)

EK is a 2D isometric game. The art is kept pixel for pixel and placed in 3D as a
**tabletop diorama** in mixed reality (passthrough), the same idea as "HD-2D".

- **World space.** Cell (c, r) sits at 3D (x = c, z = r) with 1 unit per cell. In EK's
  2:1 dimetric view the camera looks down 30°, so one art pixel is √2/64 units across
  and (√2/64)/cos 30° units tall.
- **Floors** (`water`, `ground`, `ground2`, plus flat `objects`/`scenery` tiles whose
  art stays inside their diamond) are baked offline into one iso image. The image is
  mapped onto a single ground quad with *affine* UVs: the iso projection is linear,
  so the quad shows exactly the original floor art.
- **Upright things** (trees, rocks, crates, tall objects, roofs) are vertical cards
  anchored at their cell, facing the viewer around the vertical axis
  (Y-billboard). They are drawn from one MultiMesh per tileset (a handful of draw calls).
- **Actors** use the real 140×140 9×11 sheets. The facing row comes from the actor's
  heading *relative to the viewer*, so walking away from you shows the back
  (U row), as in the 2D game when the camera looks along the road.
- **Visible disc.** The board shows a radius around the hero and fades out at the
  rim; the board scrolls under the hero, the way the 2D camera follows them.
- Unshaded materials. The art carries its own lighting, so there are no 3D lights. The
  day ambient (0.93) is from map.js `ambientColor`; the camp end card is night.

## 3. Controls (Quest Touch controllers)

| Input | Action |
|---|---|
| Left stick | Walk (relative to where you face) |
| Right trigger (laser) | Point and click: walk to a cell, attack an enemy, talk, open a crate, pick up loot, press UI buttons |
| A | Attack (the HUD attack button: nearest enemy in reach, else engage the nearest within 6) |
| B (or X) | Use a Potion of Light Healing |
| Right stick left/right | Turn the board 30° |
| Right stick up/down | Zoom the board (tabletop scale) |
| Left grip (hold) | Move the board with your hand |
| Menu (left) | Pause / resume (EK's real-time with pause) |

Desktop fallback (no headset, used for testing): WASD walk, mouse click = laser,
Space attack, Q/E turn, wheel zoom, H potion, P pause.

## 4. Deviations (logged in DEOBFUSCATION_STATUS.md §3, tagged `APPROX` in code)

- **Q1** 3D diorama presentation instead of the 2D iso camera (whole of §2).
- **Q2** Character creation is cut to name-less class + gender pick on a floating panel
  (no attribute or skill pages). Attributes start at 0 and there is no starting ability.
- **Q3** Hero body = `male_knight` / `female_knight` sheet (same as web/), not the
  paper-doll composite.
- **Q4** Combat numbers are web/src/combat.js's: the RACE_HP table, killXP = 8 + 6·lvl
  and the dmgMul bonus are already APPROX there (A-entries), and carried over as-is.
- **Q5** Loot is shown by item name. Gold drops for table row −1, using the web/
  formula. Items go to a simple bag; the ring is worn on pickup.
- **Q6** Hero death is non-punishing (full heal, enemies disengage), as in web/.
- **Q7** The area ends on a wake-up card instead of loading H10 (H10 is outside the
  area).

## 5. Build and verification

- `tools/quest_extract.py` rebuilds `quest/data/` from `dist/hero-v78` (sha256-checked).
  Tree shadows (dark, translucent pixels in the tile art) are moved from the upright
  card onto the floor bake, so they lie on the ground instead of hanging in the air.
- `tools/build_quest.sh [N]` extracts, imports, **plays the whole tutorial on desktop**
  (`quest/scripts/autotest.gd`: both crates, the road fight, Adaon joins, the goblin pack,
  camp, sleep, wake), and fails the build unless it prints `AUTOTEST PASS` with no script
  errors. Then it exports a release APK signed with `tools/ek-release.keystore`, checks the
  certificate (`538b4322…`), and splits it into `dist/quest-vN/`.
- Package `net.fdgames.ek.quest`. It is a separate app from the mod APK
  (`net.fdgames.ek.android`), so it installs alongside it and never replaces it.
- **Not verifiable here:** on-headset behaviour (controller bindings, passthrough,
  frame rate). Everything above was checked on desktop through the same code paths with
  the OpenXR runtime absent (the game falls back to a desktop camera).

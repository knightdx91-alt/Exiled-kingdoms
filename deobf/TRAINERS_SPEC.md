# Hero class + trainers — spec (reversed mechanics + owner's new design)

TODO #5. The **skill/trainer mechanics** are reversed from the base game; the **HERO
class** and its **trained-discipline equipment gating** are the owner's design-in-flight
additions (NOT in base EK), built on top of those mechanics.

## Reversed: how trainers + advanced skills work
- **Advanced ("specialist") skills** live in `data/rules/skills_advanced*.txt`:
  `name · type(P/A) · NPC · class(csv, e.g. "W,C") · melee · desc · cost · cooldown ·
  mana · skill_req`. The `class` column lists the disciplines that can learn the skill;
  `skill_req` is a prerequisite like `sacred_fire#3`.
- **Trainer NPCs teach them** via the dialogue action **`TrainSkill#<id>`**
  (`ActionsSet` → `ScriptedAction.ActionType.A0`), seen throughout the conversation data
  (`TrainSkill#flames_of_faith`, `#fire_mastery`, …). The `<id>` is the normalised skill
  name (matching the `skills.pack` icon region). Related actions: `GainSkillPoint#`,
  `ResetPlayerSkills`, `ForgetPlayerAdvancedSkills`.
- **Equipment class restriction**: `data/rules/items.txt` column 24 **`Classes`** holds a
  csv of `W`/`R`/`C`/`M` (Warrior/Rogue/Cleric/Mage); blank = usable by anyone. Base EK
  gates an item by the player's single class.

## New design (owner): the HERO class
- A 5th selectable class (`creation.json` `classes.HERO`, `learnsAll: true`; balanced
  base stats hp 40/+5, mana 6/+1, dmgMul 0.6). Not in base EK — placeholder creation art
  reuses the warrior tile; blurb authored in `gen-class-info.mjs`.
- **Learns every discipline's advanced skills** via trainers: `TrainSkill#id` succeeds for
  HERO regardless of the skill's `class` list. Other classes may only train skills whose
  `class` list includes their own discipline (faithful to base EK).
- **Trained-discipline equipment gating**: each advanced skill HERO trains unlocks that
  skill's discipline(s); HERO may then equip that discipline's class-restricted gear.
  `PlayerModel.canUseItemClass(col)` returns true when `col` is blank or shares a letter
  with the character's usable disciplines (base class's own letter + any trained). Ready
  for the inventory/equip system (TODO, arrives with items).

## Web implementation
- **`web/tools/gen-trainers.mjs`** → `assets/data/trainers.json`: `id → { name,
  disciplines[], type, cost, mana, req, desc }` (40 advanced skills).
- **`PlayerModel`** (`src/player.js`): `trained` Set, `skillPoints`, `disciplines` Set;
  `learnsAll()`, `baseDiscipline()`, `usableClasses()`, `canUseItemClass(col)`,
  `learnSkill(id, catalog)`. All persisted in `toJSON`.
- **Dialogue** (`src/dialogue.js`): `TrainSkill#` → `learnSkill`; `ResetPlayerSkills` →
  clear; `GainSkillPoint#` → `skillPoints`.
- **HUD** Character panel lists trained advanced skills, and for HERO its unlocked
  disciplines (+ skill points when held).
- **Creation**: HERO added to the class picker (`char-create.js CLASSES`) + class-info blurb.

## Deviations (DEOBFUSCATION_STATUS.md §3)
- HERO class, its `learnsAll` training rule, and discipline-based equipment gating are
  **new design**, not reversed from EK — documented here, tagged in code.
- Advanced-skill **effects** (the actual combat behaviour of trained skills) and
  `skill_req` prerequisite enforcement are not applied yet — training records the skill as
  known and unlocks disciplines; effects land with the skill-execution pass. APPROX.
- HERO creation art is a warrior-tile placeholder pending custom assets.

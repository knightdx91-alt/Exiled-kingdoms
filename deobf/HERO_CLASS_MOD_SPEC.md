# Hero-class mod — base-game APK (separate from the cheat mod)

A second, independent mod of the **base** `Exiled Kingdoms.apk`. It turns the game's
**Warrior** class slot into a **"Hero"** that can **learn every skill** (basic + advanced,
any discipline) and **use every weapon and armor**, with caster-grade mana so spells are
actually usable. The other three classes (Rogue/Cleric/Mage) are untouched.

## Why "replace a slot" instead of a true 5th class
The playable classes are hardcoded: 4 `ClassImage` tiles in `NewGameWindow` (`q0/v`), the
`CharacterClass` enum, and per-class stat switches keyed by `ordinal()`. Adding a genuine
5th tile means enum + creation-UI layout surgery in smali that can't be playtested in this
environment. Repurposing the first tile (the default-selected **Warrior**) into **Hero**
gives a real, in-menu class choice at essentially zero UI risk. The starting-skill screen
Just Works because it is built from `Skills.b(class)` (see below).

## The single choke point
Both gates route through one method:
`ClassRestriction.c(CharacterClass)` → true if the restriction set is empty **or** contains
the class. It is called by:
- **Skills**: `Skills.b(class)` keeps a basic skill iff `skill.skillClass.c(class)` — this is
  what fills the creation **SkillWindow** and the level-up skill list.
- **Advanced skills / trainers**: `TrainSkill#` checks the advanced skill's `skillClass.c()`.
- **Equipment**: `Item` usability checks `item.classes.c(sheet.stats.c())`.

So making `c()` return TRUE for the Hero class unlocks **all three** at once, and the
creation/level-up skill screens automatically list **every** skill for the Hero to pick.

## Patches (2 smali methods + 1 asset)
1. `net/fdgames/Rules/ClassRestriction->c(...)`: prepend
   `if (arg == CharacterClass.a /*WARRIOR slot = Hero*/) return TRUE;`.
   → Hero passes every skill filter, trainer gate, and equipment restriction.
2. `net/fdgames/GameEntities/CharacterSheet/CharacterStats->h()` (max mana): the vanilla
   method grants mana only to Wizard (`d`) and Cleric (`c`). Add a branch so the Hero slot
   (`a`) also gets `level*2 + 12` mana. Hero therefore has **Warrior HP (45 base, +6/level),
   solid melee (0.5 dmg factor), and caster mana** — a balanced hybrid.
3. `assets/data/ui/strings/strings.txt` (`WARRIOR` → "Hero") and
   `assets/data/ui/strings/texts.txt` (`CLASS_DESC_WARRIOR` → Hero blurb), English column.
   CRLF + BOM preserved.

Enum field map (smali): `a`=WARRIOR(→Hero), `b`=ROGUE, `c`=CLERIC, `d`=WIZARD, `e`=MONSTER,
`f`=GENERAL, `g`=NONE.

## Build
`tools/build_hero_mod.sh <base.apk> [out.apk]` — same baksmali→patch→smali→repackage→sign
(v1+v2, apksig) pipeline as the cheat mod. Verified: `jarsigner -verify` passes and all
three edits confirmed present in the final dex/assets.

## Deliberate deviations / APPROX
- Hero **replaces** the Warrior slot (owner-approved trade to avoid risky UI surgery). A
  true 5th class is possible but higher-risk without on-device playtest.
- "Learn every skill" = the Hero is **eligible** to learn any skill (no class gate), spending
  normal skill points — not that all skills are auto-known.
- Advanced-skill combat effects and `skill_req` prerequisites are the base game's own; this
  mod only removes the class restriction, it does not alter skill behavior.
- Not playtested on-device here (no emulator). Static verification only: patches present in
  final dex, signature valid, label present.

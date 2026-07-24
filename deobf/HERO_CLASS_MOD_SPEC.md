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

## v2 — scrollable, all-skills-pickable skill screen (EXPERIMENTAL)
The base-game skill window (`o0/t.l()`) is a fixed grid: 8 class + 4 general + 8
specialist slots (array `o0/t.k` size 20), no scroll. So v1's Hero was *eligible* for all
37 basic skills but the screen only rendered the first 8. v2 fixes that additively:
1. Icon array `o0/t.k` enlarged 20 → 80.
2. The skill Table is wrapped in a **ScrollPane** in the constructor (vertical scroll,
   ~420×520 cell) so overflow rows are reachable.
3. A new **"All Skills"** section is appended at the end of `l()`: a loop over
   `Skills.b(class)` (all 37 for the Hero) that builds an `o0/q` icon per skill with the
   real `o0/v` click-listener, laid out in rows of 4 (array indices 20+).
The three original sections are untouched (low risk). **EXPERIMENTAL / not playtested on
device** — hand-written scene2d smali; scroll sizing and register reuse are unverified.
v1 (`dist/ExiledKingdoms-hero.apk`) remains the safe fallback.

---

# v3 — rebuilt on the owner's 2023 / 4.2.2 base (CURRENT)

v1/v2 were written against the **2025** base and their anchors do not exist here. Re-reversed
against the owner's base; `tools/patch_hero_class.py` is the current implementation, applied
by `tools/build_mod_4_2_2.sh`.

## Remapped anchors (2025 base -> 2023/4.2.2 base)

| Purpose | 2025 base | 2023 / 4.2.2 base |
|---|---|---|
| `CharacterClass` enum fields | `a`=WARRIOR … | **shifted by one**: `b`=WARRIOR(Hero), `c`=ROGUE, `d`=CLERIC, `e`=WIZARD, `f`=MONSTER, `g`=GENERAL, `h`=NONE |
| Restriction choke point | `ClassRestriction->c(...)` | `ClassRestriction->a(CharacterClass)Ljava/lang/Boolean;` |
| Max mana | `CharacterStats->h()` | `CharacterStats->g()I` |
| Skill screen | `o0/t`, array `k` size 20 | `e/a/d/e/c0` (`.source "SkillWindow.java"`), array `n:[Le/a/d/e/z;` size `0x14` |

Verified callers of the choke point — one method gates **all** of it:
`Item` (equipment), `Skill` x2 (basic skills + advanced/trainers), `Rules`.

## The suppress flag (why a plain bypass is not enough)

Making `a(WARRIOR)` unconditionally true means `Skills.a(WARRIOR)` returns **all 37** basic
skills — so there is then no way to ask for "just the warrior list", which the per-class
pager needs. The bypass is therefore gated on a new
`public static ClassRestriction.ekSuppress:Z` (default `false` = bypass **live**):

```
a(CharacterClass p1):
    if (!ekSuppress && p1 == CharacterClass.b) return TRUE;   // Hero: unrestricted
    <original: empty set, or set contains p1>
```

The pager sets `ekSuppress = true` *only* while building one page's list, and clears it
immediately after. Everywhere else — equipping, learning, trainers — the bypass is live, so
the Hero is unrestricted, which is the owner's requirement ("shouldn't be restricted from
anything").

## Per-class pager (owner's preferred design over a ScrollPane)

The screen's class-skill section drew from `Skills.a(sheet.n())` into a fixed 8-slot grid, so
v1's Hero was *eligible* for 37 skills but only the first 8 ever rendered. v2 tried to fix
that with a hand-written ScrollPane and was never playtested. v3 pages instead, which reuses
the existing layout code untouched:

- new fields `c0.ekPage:I`, `c0.ekPageBtn:TextButton`
- `c()` sources the list via new helper `ekClassSkills(CharacterSheet, page)`, replacing the
  `sheet.n()` → `Skills.a()` sequence **in place, with identical register usage (v0/v1)**
- `ekClassSkills` maps page `0..3` → `b/c/d/e` (HERO/ROGUE/CLERIC/MAGE), wraps the
  `Skills.a()` call in `ekSuppress = true/false`, and returns the **real 8-skill list** for
  that class. Page 0 (`b` + suppress) yields the genuine warrior list.
- a `TextButton` labelled with the current page is added on **its own row** at the top of the
  table (a standalone `add()` + `row()`, so no existing cell/colspan is disturbed), and only
  when the character actually is the Hero — the other three classes render exactly as before
- `ekNextPage()` advances `(page+1) % 4` and re-runs `c()`
- listener `e/a/d/e/c0$ekp` mirrors the existing `c0$d` `InputListener` verbatim

Because the *learn* path runs with `ekSuppress` clear, a skill listed on any page is
learnable by the Hero.

## Other changes
- `CharacterStats.g()I`: the non-caster exit now also grants `WARRIOR` the caster branch
  (`e()`), so the Hero has mana and spells are actually usable.
- `strings.txt`: `WARRIOR` → `Hero` (English column; BOM + CRLF preserved).

## Deliberate deviations / APPROX
- Hero **replaces** the Warrior slot (owner-approved) rather than adding a 5th enum value.
- The class-section **header** still reads "Hero skills" on every page; the pager button
  itself shows which class list is displayed. Cosmetic only.
- Page 0 shows the warrior list, so the Hero's own skills are page 1 of 4, not a merged list.
- **Not playtested on device** (no emulator here). Static verification only: the dex
  assembles (which validates registers and label targets), and every edit was confirmed
  present in the shipped dex by re-disassembling it.

## ⚠️ STATUS 2026-07-24 — shipped, NOT yet verified on device
Owner has not playtested the Hero build. Open questions, in likely order of risk:
1. **Pager button layout** — does it render sensibly at the top of Table `l`? It is a
   standalone `add()` + `row()`, so it can be relocated without touching other cells.
2. Does cycling HERO → ROGUE → CLERIC → MAGE repopulate the 8-slot grid correctly?
3. Are skills from a non-native page actually learnable (they should be — the learn path
   runs with `ekSuppress` clear, so the bypass is live)?
4. Does the Hero show mana, and are spells castable?
5. Do Rogue/Cleric/Mage characters still behave exactly as before (no pager button)?


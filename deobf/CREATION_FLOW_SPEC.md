# Title + character-creation flow — full recovered spec

The complete new-game flow, traced end-to-end through the decompiled classes (label
keys are the exact on-screen `GameString` ids). **This is the authoritative scope for
the web port's creation UI — build every page/field here, in this order.**

Orchestrated by **MainMenuScreen (`l0/e`)** via static windows and the `q()/r()/s()`
handoffs. Confirmed transitions in `( )`.

```
IntroScreen (l0/c)                studio "fdgames" intro / loading
      ↓
TITLE = MainMenuScreen (l0/e)     START_NEW_GAME · CONTINUE_GAME · SETTINGS ·
                                  LIBRARY · CREDITS · STORE/DONATE · EXIT
      ↓ START_NEW_GAME
PAGE 1  NewGameWindow (q0/v,w)    name · gender · portrait · class · difficulty
      ↓ START_GAME  →  l0.e.q(PlayerCreation, difficulty)
        (q(): GameData.b(pc) builds player + CharacterSheet, then shows TraitsWindow)
PAGE 2  TraitsWindow (o0/a0)      allocate attribute (trait) points
      ↓ NEXT  →  l0.e.r()  →  shows SkillWindow
PAGE 3  SkillWindow (o0/t,u,v,w)  pick starting skill/ability
      ↓ START_GAME  →  l0.e.s()  →  IntroScreen → Transition("I10_tutorial", 1)
GAME starts in I10_tutorial (Adaon's road)
```

## PAGE 1 — NewGameWindow (`q0/v`, inner `q0/w`)
Produces `Rules.PlayerCreation` = **{name, gender, portraitIndex, charClass, difficulty}**.
- **Name** — `TextField`; a random-name button uses `WorldRandomNames.d(gender)`.
  Validated via `NAME_LENGHT` (min length).
- **Gender** — two `GenderImage` (`q0/q`); default random name per gender.
- **Portrait** — `Image` cycled by arrows; `portraitIndex` into `Assets.n(gender, idx)`.
- **Class** — four `ClassImage` (`q0/f`): WARRIOR/ROGUE/CLERIC/MAGE. Selecting a class
  brings up **ClassesHelpWindow (`p0/a`)** with `CLASS_DESC_WARRIOR/ROGUE/CLERIC/MAGE`.
- **Difficulty** — five `DifficultyImage` (`q0/o`): `DL_CASUAL/NORMAL/HARD/IRONMAN/STORY`.
- Buttons: `CANCEL`, `START_GAME` → `l0.e.q(pc, difficulty)`.
- Labels: `CREATE_YOUR_HERO, NAME, GENDER, CHOOSE_PORTRAIT, NEXT, START_GAME, CANCEL`.

## PAGE 2 — TraitsWindow (`o0/a0`)  ← attribute allocation
Shown by `q()` as `f2363z.e(player.sheet, stage)`; has a creation-mode flag (`f3459l`).
Spend the level-1 **trait points (4)** across the six attributes (STR/END/AGI/INT/AWA/PER),
0–12, triangular cost — see `CHARACTER_STATS_SPEC.md`. Uses **TraitsTable (`o0/z`)** (the
+/- rows, keys `T_STR/T_END/T_AGI/T_INT/T_AWA/T_PER`) and **TraitDescriptionTable (`o0/y`)**
(per-attribute blurb `DESC_STR…`).
- Labels: `AVAILABLE_TRAIT_POINTS`, `SPEND_ALL_TRAITS` (confirm dialog if unspent),
  `RESET`, `BACK`, **`NEXT`** → `l0.e.r()`.

## PAGE 3 — SkillWindow (`o0/t`, inners `o0/u,v,w`)  ← ability pick
Shown by `r()` as `A.p(player.sheet, stage)`. Spend the level-1 **skill point (1)** on a
starting ability from the class list (+ GENERAL); tier-1 costs 1 (+mana for actives).
Uses **SkillDetailTable (`o0/o,p`)** (`LEVEL/SECONDS/COST/ACTIVE_SKILL/PASSIVE`, `SLOT_NONE`),
**SkillImage (`o0/q`)**, **SkillInfoWindow (`o0/r`)** for details, **ChooseSkillSlotDialog
(`o0/h`)** to bind an active to a quick-slot.
- Labels: `SKILLS`, `SKILLS_LIST`, `SPECIALIST_SKILLS`, `POINTS`, `SPEND_ALL_SKILLS`,
  `SKILL_REQUISITES_ERROR`, `RESET`, `STAT_DETAILS`, **`START_GAME`** → `l0.e.s()`.

## Supporting classes / data
| Class | Obf. | Role |
|---|---|---|
| MainMenuScreen | `l0/e` | title menu + creation orchestrator (`q`→Traits, `r`→Skill, `s`→game) |
| NewGameWindow | `q0/v,w` | page 1 form |
| ClassImage / GenderImage / DifficultyImage | `q0/f,q,o` | selectable images on page 1 |
| ClassesHelpWindow | `p0/a` | class descriptions popup |
| TraitsWindow / TraitsTable / TraitDescriptionTable | `o0/a0,z,y` | page 2 attribute allocation |
| SkillWindow / SkillDetailTable / SkillImage / SkillInfoWindow / ChooseSkillSlotDialog | `o0/t.. ,o,p,q,r,h` | page 3 ability pick |
| IntroScreen | `l0/c` | studio intro → tutorial transition |
| PlayerCreation | `net.fdgames.Rules.PlayerCreation` | {name, gender, portraitIndex, charClass, difficulty} |
| CharacterSheet / CharacterTraits / CharacterStats / SkillSet | `net.fdgames.GameEntities.CharacterSheet.*` | the model built by `GameData.b(pc)` |

## Web-port status (as of this spec)
`web/src/char-create.js` implements the flow in the **right order** (title → identity/
class/difficulty → attributes → ability), but pages 2 & 3 are **custom UIs, not yet
matched to the real TraitsWindow/SkillWindow** (missing `SPEND_ALL_TRAITS`/`RESET`
controls, the specialist-skills grouping, per-skill detail table, quick-slot binding).
**TODO:** reskin pages 2 & 3 to the exact TraitsWindow/SkillWindow layout + labels above.

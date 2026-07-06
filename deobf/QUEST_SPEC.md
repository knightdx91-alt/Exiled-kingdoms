# Quests + world state — recovered spec

## Source of truth
- `GameWorld/Quest.java`, `QuestState.java`, `Quests.java`, `GameData.java`.
- Data: `data/quests/list.txt` (104 quest ids) + `data/quests/<id>.txt` (one file per quest).

## The key finding: a quest's progress **is a game variable**
`GameData` stores all world state in `gameVariables` — a flat name→int map. **A quest's
current progress is the variable named exactly `quest.ID`** (`GameData.java:608`
iterates quests and reads `gameVariables.b(quest.ID)`). So:
- Advancing a quest = `SetVariable#<quest_id>,<value>` (or `IncVariable#`), the **same**
  action the tutorial already uses (`SetVariable#want_letter_back,10` — `want_letter_back`
  is quest #4 in `list.txt`).
- A quest is **active** when its variable `> 0`, **complete** when `>= 100` (the `>99`
  test in `GameData`), and hidden when `0`/absent.
- Reputation, faction standing and event flags are likewise just variables — there is no
  separate reputation action in `ScriptedAction`. The web already has the shared
  `gameState.vars` store (dialogue `SetVariable/IncVariable/DecVariable`), so world state
  is already modelled; quests are a **reader** over it plus the quest text.

## quest file format (`data/quests/<id>.txt`, tab-separated)
`progress · description · description_ES · actions` (`Quest.java` parse loop).
- **Row with `progress == 0`** carries the quest **Name** (col 1) and an optional
  **type** letter in col 4: `B`=bounty, `I`=?, `M`=?, `C`=? (`questType` 1/2/3/4).
- Every other row is a **QuestState**: at progress value `N`, the journal shows that row's
  `description` (via `Quest.c(N)`), and its `actions` fire when the quest reaches `N`.
- Descriptions are wrapped `[BLACK]…[]` for the journal (`Quest.c`).

## Journal (what the player sees)
For every quest whose variable `> 0`: show its Name and the description for the current
progress value; mark **Completed** when `>= 100`. This mirrors EK's Journal tab.

## Web implementation
- **`web/tools/gen-quests.mjs`** → `assets/data/quests.json`:
  `{ id: { name, type, states: { "10": "desc", … } } }` from `list.txt` + each `<id>.txt`.
- **`web/src/journal.js`** — reads `quests.json` + `scene.gameState.vars`; lists active
  quests (var > 0) with the current-progress description; flags completed (>= 100). Opened
  from the HUD.
- **Dialogue actions**: `SetVariable/IncVariable/DecVariable` already move quest vars.
  Wire `GainGold#n`/`LoseGold#n` (were no-ops) to `PlayerModel.gold`. `TrainSkill#` /
  `GainSkillPoint#` land with the trainer system (`deobf/TRAINERS_SPEC.md`).
- **Persistence**: `gameState.vars` (all world state incl. quest progress) is saved with
  the character so quests survive reload.

## Deviations (DEOBFUSCATION_STATUS.md §3)
- Quest **actions** per state (the col-3 action list that fires when a state is reached)
  are not auto-run yet — progress is driven by dialogue/trigger `SetVariable`, which is how
  most quests advance anyway. Auto-firing state actions is a later pass. Tagged APPROX.
- Quest `type` letters (B/I/M/C) and dynamic quests (`DynamicQuest`) are parsed/ignored;
  the static journal is faithful.

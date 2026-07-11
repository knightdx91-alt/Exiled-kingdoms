# Tutorial skip → "wake up robbed" opening — recovered flag audit + design

Owner decision (supersedes the earlier "keep the instanced tutorial" note in
CONTINUE_HERE.md): **cut the playable tutorial**. A new game now begins with the
player waking on the road already robbed, realising the friendly traveler from last
night (Adaon) stole everything, and being pointed to the next town (Lannegar) for
leads. This is the same story beat the old tutorial ended on — we just start there.

## The whole tutorial cluster, traced (so we set the EXACT same state)
Reversed from the actual assets, not memory:

- **Map** `assets/tmx/I10_tutorial.json` — two triggers, both only `StartConversation`
  (no variable/item/xp side effects):
  - road trigger → `adaon_tutorial` (meet Adaon), gated `NPCisNotInParty#adaon_tutorial`
  - west camp zone → `adaon_tutorial_camp`, gated `VariableLower#want_letter_back,10`
- **`adaon_tutorial.txt`** — only scripted effect: node 5 `NPCFollow#adaon_tutorial`
  (Adaon joins the party for the goblin fight).
- **`adaon_tutorial_camp.txt`** — node 50 is the ENTIRE exit payload:
  `NPCStopFollowing#adaon_tutorial; StopRender#; Travel#H10,1,14; Sleep#;
   SetVariable#want_letter_back,10; PlayerRobbed#`

### Complete list of persistent state the tutorial produces
| effect | source | value after tutorial |
|--------|--------|----------------------|
| `want_letter_back` | camp node 50 `SetVariable` | **10** |
| gold / inventory | camp node 50 `PlayerRobbed#` (`Player.C1`) | gold **18**, items wiped |
| Adaon in party | `NPCFollow` then `NPCStopFollowing` | **not in party** |
| player location | `Travel#H10,1,14` → H10 entry `0001` | H10 (Lannegar Valley), east-edge |

**That is the complete set.** Verified there is nothing else:
- `want_letter_back` is **never** set to `5` by any script (state 5 = the pre-robbery
  "letter received" flavour text only); gameplay effectively begins the quest at 10.
- No `GiveItem`/`GainXP`/`GainSkillPoint`/other `SetVariable` anywhere in
  `I10_tutorial` or `adaon_tutorial*` (grepped).
- No stolen **letter item** to remove — the guard line is "stole all my money, and an
  important letter"; the letter is a quest abstraction (`want_letter_back`), not an
  inventory item.

### Why state 10 is exactly right (downstream gates)
`want_letter_back` is the staged quest **"A mysterious letter"** (`quests.json`), state
10 = *"…a thief called Adaon stole the letter from me."* Downstream entry points key off
it, e.g.:
- `lannegar_guard.txt` node 2: `VariableEqual#want_letter_back,10` (+`robbed_guard<1`,
  +`PlayerIsntLevel#2`) → "Help! I was robbed by a thief called Adaon!" → sets
  `robbed_guard,1` and opens the next chain.
- `lannegar_chea.txt`: `VariableLower#want_letter_back,20` → "Have you seen Adaon?"
- New Garand chain (`NG_*`, `ng_*`) reads it at 20/22/30/…/100.

`robbed_guard` stays **0** — it is set later by the guard, gated on `want_letter_back==10`.
So starting at exactly `want_letter_back=10` (and nothing else) drops the player cleanly
into the existing quest graph with no stage skipped or stranded.

## Implementation (src/main.js)
- New-game path (`boot → startNewGame(pc)`) starts at **H10 / `0001`** (was
  `I10_tutorial`), `mode: intro`.
- Before handing control over, the scene sets the traced state **in code** (not only via
  dialogue actions, so it holds even if the player dismisses the monologue early):
  `gameState.vars.want_letter_back = 10`, `PlayerRobbed` (gold 18), followers cleared.
- Then it plays the narration conversation **`intro_wakeup`** (player's own portrait as
  speaker), which re-asserts the same flags in its final node's `actions` as belt-and-
  braces. The old `I10_tutorial` map + `adaon_tutorial*` conversations are LEFT IN PLACE
  (unused by new games; still covered by verify's `tutorial-exit` check) so nothing that
  references them breaks.

## APPROX / notes
- `PlayerRobbed#` mirrors `Player.C1`'s gold reset (→18); it does not strip worn
  equipment (the guard dialogue robs "money … and the letter", not your gear), matching
  the existing `_robPlayer` behaviour used by the old tutorial exit.
- The monologue is new narration written to fit EK's tone; it is not a decompiled EK
  conversation (there is no wake-up conversation in the base game — the base game plays
  the camp/sleep beat instead). Tagged as new content, faithful to the state it sets.

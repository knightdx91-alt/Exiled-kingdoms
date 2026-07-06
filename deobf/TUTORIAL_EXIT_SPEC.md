# Tutorial exit — camp → sleep → robbed → wake near Lannager (recovered spec)

The tutorial (`I10_tutorial`, Adaon's road) is an **instanced, one-time** map with no
walk-out transition. Its only intended exit is the west **camp**: walking into the camp
zone starts `adaon_tutorial_camp`, and its terminal node performs the sleep/robbery/travel
that drops you into the seamless world near Lannager, never to return.

## Source of truth
- **Conversation:** `assets/conversations/adaon_tutorial_camp.txt`, node `50`
  (`recovered/assets/assets/data/conversations/adaon_tutorial_camp.txt`):
  ```
  50  Q  Good night, friend.  …  0    "NPCStopFollowing#adaon_tutorial;StopRender#;Travel#H10,1,14;Sleep#;SetVariable#want_letter_back,10;PlayerRobbed#;"
  ```
- **Action semantics** — `recovered/src/net/fdgames/GameLogic/ScriptedAction.java`:
  | Action | ordinal | Real effect |
  |---|---|---|
  | `NPCStopFollowing#id` | — | remove `id` from the party/followers |
  | `StopRender#` | 72 | `a.l().f2288o = true` — renderer "screen off" flag (goes black); `StartRender#`=73 clears it |
  | `Sleep#` | 53 | `GameLevel.k().q0(ActorState.Sleep)` — hero enters the Sleep actor state |
  | `Travel#map,entry,facing` | (Travel) | move the hero to `map` at numbered entry `entry`, facing `facing` |
  | `SetVariable#name,n` | — | set global variable `name = n` |
  | `PlayerRobbed#` | 71 | `Player.C1()` → `inventory = new CharacterInventory()`; `gold = 18`; then `a2()` shows the robbed message |
- **`Player.C1()`** — `recovered/src/net/fdgames/GameEntities/Final/Player.java:232` wipes
  the inventory and sets `gold = 18` (the starting purse). Equipment is moved to
  `lostEquipment` for a later recovery quest.

The real engine runs **every** action in the list in order; the whole list executes even
though `Travel#` changes the current level mid-list.

## Web implementation (`web/`)
- **`src/dialogue.js` `runActions`** now runs the full list, **defers `Travel#`** to the
  end (so `SetVariable#`/`PlayerRobbed#` after it still take effect), and treats
  `StopRender#`/`Sleep#` as a request to **fade to black** around the travel. Adds
  `NPCStopFollowing#` (was an unhandled no-op → Adaon never left) and `PlayerRobbed#`
  (`_robPlayer`: clears tracked items, sets `playerModel.gold = 18`, refreshes the HUD).
- **`src/dialogue.js` `parseConversation`** now **unquotes** the TSV condition/action
  fields. EK wraps a field in `"…"` when it contains a comma; the old parser left the
  quotes on, so the **first** verb of a quoted list parsed as `"NPCStopFollowing` and
  silently failed. (Latent bug; this beat surfaced it.)
- **`src/main.js` `fadeBlack(down)`** — a full-viewport fixed black overlay with a CSS
  opacity transition; returns a promise so the area swaps while the screen is dark.
- **`src/main.js` `entryOf(map, entryId, preferDefault)`** — resolves the spawn point.
  Scripted `Travel#` passes `preferDefault=true`, so a missing entry id falls back to the
  conventional **`0001`** marker (H10 `0001` = c86,r41, the east edge — you leave I10 west
  → arrive H10 east → walk west to Lannager). Arch transitions pass `false` and keep the
  map-centre fallback so they never bounce off a map's `0001` portal.

## The `want_letter_back` gate
`SetVariable#want_letter_back,10` is the flag that makes the tutorial one-time: the camp
trigger and Adaon's conversations are gated `VariableLower#want_letter_back,10`, so once
it's 10 you can never re-enter the beat.

## Verification
`web/verify.mjs` includes a **tutorial-exit** section: start on `I10_tutorial`, seed Adaon
as a follower, open `adaon_tutorial_camp` node 50, and assert Adaon dropped, `gold==18`,
`want_letter_back==10`, arrival in **world** `H10`, and the fade cleared. `VERIFY: PASS`.

## Deviations (logged in DEOBFUSCATION_STATUS.md §3, A6/A7)
- `facing` is ignored; the `0001` default stands in for un-exported numbered entries.
- No hero Sleep animation; the fade is a DOM overlay, not the engine renderer flag.
- `PlayerRobbed#` only resets gold (no inventory/lostEquipment system yet — arrives with
  Combat, TODO #3).

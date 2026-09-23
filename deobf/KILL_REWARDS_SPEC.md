# Shared kill rewards: XP and loot split by damage (owner request 2026-09-23)

Owner: "if both people are attacking an enemy both need to gain experience / loot. The amount needs to
be split depending on percentage of damage dealt by each party, including companions or summons."

## Reversed (4.2.2 names; 1.3.1217 names in brackets)
* **Damage**: every hit on a character goes through `Character.a(Damage, int attackerId, boolean, int)`
  (NPC/Player override it and call super). Lethal branch sets `killedByNPC = false` when the attacker
  is the player (id 1) or an NPC with `P()` (companion in the party: `ai.b()` && `party.a(spawn,tag)`),
  else `true`.
* **Death**: `NPC.E()` [`X()`]. Only `if (!killedByNPC || alwaysReward)`:
  - loot: once (`droppedloot`), each `;`-entry of `lootTable` → `Rules.c(entry)` items + `Rules.b(entry)`
    gold → `GameLevel.a(x+32, y+32, items, gold)` (= `new Loot` + `GameLevelData.a`), a bag on the ground;
  - XP (hostile faction only): level table (+race bonus above 10), /10 if `respawned`,
    `GameLevel.h().k(xp)` [`player.O0`] — `Player.k` then gives companions their 0.56 share (xp_share patch).
* **Multiplayer engine today**: each device runs its own monster; a peer's hit is replayed on the others
  by `receiveRemoteCombat` → `MessageRouter.a(msg, peerPuppet.m(), target, …)`, i.e. attacker = the
  peer's puppet NPC (not in party) → on that device a kill by the peer is `killedByNPC` → no reward.
  **Result: whoever lands the last hit gets all XP and all loot; the other gets nothing.**
* Summons: player summons are NPCs with `summoned` and tag `player_summon` (SkillActions); peer summons
  on this device are in `LanGameBridge.peerSummonOwners` (summonId → owner puppet id); peer puppets
  in `LanGameBridge.peerActors` (name → NPC).

## Rules (ours)
* **Side** = a player plus their companions and summons. Damage is the HP actually removed (missingHP
  before/after the hit, capped at max HP, so overkill doesn't count). Damage by other NPCs, traps and
  environment counts for nobody.
* **XP**: `xp × side share`, rounded (min 1 for any side that dealt damage); each player's companions
  then get their usual share of it (Player.k).
* **Gold**: `gold × share`, floored; the remainder goes to the biggest share.
* **Items** can't be split: each item unit goes to one side, chosen at random weighted by share.
* Each player gets their items + gold as a **personal bag** at the monster's spot (only on their
  device; not a shared drop); if they are in another area by then, straight into the backpack
  (overflow at their feet).
* Solo, or nobody else dealt damage: vanilla, unchanged.

## Flow (host decides, like trades/drops)
* Ledger (every device): `Character.a(Damage,I,Z,I)` wrapped → per monster (`level:uniqueID:spawn_id`)
  per side name: damage. Attribution: id 1 / `P()` / summoned `player_summon` → me; peer puppet or its
  summon → that peer's name.
* The device whose reward block runs (the last hitter's) intercepts `GameLevel.a(x,y,items,gold)` and
  `Player.k(xp)` inside `NPC.E()` and, right after the death, sends
  `EKKILL⇥key⇥reporter⇥level⇥x⇥y⇥xp⇥gold⇥items⇥ledger` to the host.
* Every device on which the monster dies sends `EKDMG⇥key⇥name⇥ownSideDamage` (its own side is
  measured locally and exactly, e.g. its companions' hits, which other devices never see).
* Host: on EKKILL waits 1.5 s for EKDMG, merges (per name: max of reports), splits, sends
  `EKREWARD⇥name⇥key⇥level⇥x⇥y⇥xp⇥gold⇥items` to each side (itself locally). Settled keys are
  remembered (no double pay).

## Status — done (code), v37
`EkKill` + B54–B57 (`patch_multiplayer.py`): `LanGameBridge.ekPeerActors/ekPeerSummonOwners`,
`Character.a(Damage,I,Z,I)` wrapped (before/after missingHP), `NPC.E()`: `onDeath` at the start, the
loot call `GameLevel.a(IIList I)` → `EkKill.loot`, the kill XP `Player.k` → `EkKill.xp`; routing in
`EkShare.hostLine/clientLine`. Static checks pass (access, invoke kinds, D8, update gate, startup data
check). Not device-tested.
Known limits: damage over time (poison/burn effects ticking without an attacker id) counts for nobody;
a peer's companions' hits are only known through that peer's own EKDMG report, so if the monster never
dies on that peer's device (its copy desynced) their companions' part is missing; the unique-boss bonus
XP (`sheet.z()*100`, second `Player.k` in `NPC.E`) stays with the killer (vanilla).

### Who counts as a separate party (owner: "only other actual players", 2026-09-23)
Only other players (names in `LanGameBridge.peerActors`) are separate shares. Companions (`NPC.P()`)
and summons cast by you or your companions (`SkillActions.a(Character,…)` tags a summon `player_summon`
only when the caster is the player, id 1, or `P()`; enemy summons skip that branch) are credited to
you and never get a share of their own; a kill involving only you and your companions/summons is
vanilla. Never split/tracked as monsters: companions, your summons, other players' stand-ins
(`lanPeerVisual`) and other players' summons (`peerSummonOwners`, v38).

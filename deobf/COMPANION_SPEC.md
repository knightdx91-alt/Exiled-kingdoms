# Companion system — reversed spec (2023 / 4.2.2 base) + Janod mage companion

Reversed from the owner's base APK (`dist/ExiledKingdoms-base-4.2.2.apk`, apktool tree).
Written **before** coding, per the project faithfulness rule.

## 1. `companions.txt` is DEAD — do not use it

`assets/data/world/companions.txt` contains only `grissenda`, yet the game has three
companions (Adaon, Hirge, Grissenda). **Nothing in the engine reads this file.** Every
`data/world/*.txt` loader was enumerated from the dex:

| File | Loader |
|---|---|
| `regions.txt`, `areas.txt` | `GameWorld/Areas` |
| `event_locations.txt`, `events.txt` | `GameWorld/WorldEvents` |
| `random_names.txt` | `GameWorld/WorldRandomNames` |
| `factions.txt`, `factions_text.txt` | `GameWorld/WorldFactions` |
| `castles.txt` | `GameWorld/Castles` |
| `rumors.txt` | `GameWorld/Rumors` |
| `spawntables/…` | `GameWorld/SpawnTable(s)` |
| **`companions.txt`** | **— none —** |

It is a vestigial file. Companion identity is **hardcoded in smali** (§2).

## 2. What actually makes an NPC a companion

Three separate mechanisms, two of which are hardcoded by `spawn_id`:

### (a) `NPC.<init>(Spawn)` — the companion whitelist  ← **HARDCODED**
`net/fdgames/GameEntities/Final/NPC;-><init>(Lnet/fdgames/Rules/Spawn;)V`

```
v2="grissenda"  v3="hirge"  v4="adaon"   v5=5  v6=1
if (spawn_id.toLowerCase(ENGLISH).equals("grissenda")) goto :cond_6   // companion path
if (spawn_id.toLowerCase(ENGLISH).equals("adaon"))     goto :cond_6
if (spawn_id.toLowerCase(ENGLISH).equals("hirge"))     goto :goto_2   // == :cond_6
:cond_0   → ordinary NPC setup … :cond_a/:goto_3 → return

:cond_6 / :goto_2   // COMPANION PATH
    if spawn_id=="grissenda" → grissenda starting gear/skills (shield_expert, …)
    if spawn_id=="hirge"     → hirge   starting gear/skills (heal_wounds, crusader,
                                        nivarias_barrier)
    if spawn_id=="adaon"     → adaon   starting gear/skills (stab, gossip)
    :cond_9
    companionSpawn = true            // ← the flag that matters
```

### (b) `NPC.z0()` → `Party.a(NPC)` — registration
```
if (this.companionSpawn) GameData.O().party.addCompanion(this);
```
`Party.a(NPC)` (`GameWorld/Party`) is **fully generic — no name check**: it sets
`activeCompanion = npc.spawn_id` and appends to the `companions` ArrayList.
`Party.h()` = `hasCompanion`, `Party.c()` = `getCompanion()` (matches `activeCompanion`
against the list). So *anything* with `companionSpawn=true` is a first-class companion.

**Therefore the only gate is the hardcoded whitelist in (a).** A new companion must be
added there — this cannot be done from data alone.

### (c) `UpgradeCompanion#<spawn_id>` — the advancement  ← **HARDCODED**
`GameLogic/ScriptedAction;->a()V`, enum `ActionType.T0`, **ordinal 0x60 = 96**, dispatched
by `packed-switch` on `type.ordinal()` (table covers 0x0–0x60; case 96 = `:pswitch_0`).
Note: the `ScriptedAction$1` ordinal-remap array is *not* used by this executor — the
switch reads `Enum.ordinal()` directly.

```
if (!party.hasCompanion()) return;
NPC c = party.getCompanion(); if (c == null) return;
c.gainXP(40000);                       // NPC.k(0x9c40)
GameAssets.playSound("levelup");
if (data.trim().toLowerCase().equals("hirge"))
      grant retribution, duel, death_ward, spiritual_ward, heavyhand
if (… "grissenda")
      grant body_development, massive_criticals, infantry_training,
            precission_strikes, heavyhand
if (… "adaon")
      grant flurry, rapid_fire, massive_criticals, precission_strikes,
            precission_shots, assassinate
:cond_4                                 // common tail
sheet.d(); sheet.d();                   // recalc x2
c.g0(); c.g0(); c.g0();                 // level-up x3
log/console GameString("COMPANION_ADVANCEMENT")
```
Grants use `CharacterSheet.e(String skillId)`. **Every skill the vanilla upgrades grant is
`type=P` (passive) with `npc=N`** — so the `NPC` column in `skills*.txt` does *not* gate
grants; it only governs whether the AI actively fires an **active** skill. Passives are
therefore the correct choice for a companion upgrade kit.

> **Register hazard.** In the `adaon` block the engine reuses **v0** for the sheet, which
> clobbers the action-data string held in v0. Any new name test must therefore be inserted
> **before** the adaon block (v0 still valid), and must scratch only v2/v3 while preserving
> v1 (the companion NPC).

### Skill IDs
`Rules/Skill` normalises the display name: `toLowerCase()`, `" " → "_"`, `"'" → ""`.
So `Mage Barrier → mage_barrier`, `Mana Surge → mana_surge`.

## 3. Data layer (no code needed)

- **`rules/bestiary.txt`** — the NPC definition. Companion-relevant columns:
  `class` (warrior/rogue/cleric/wizard), `weapon_id`, `Skillset`, `AI`, `faction=player`,
  `sprite`, `portrait`, `gender`. Existing companions: `grissenda` warrior L2,
  `hirge` cleric L6-7, `adaon` rogue L3 — all `race=npc`.
- **NPC "spells" are just ranged weapons** in `rules/weapons.txt` (`ranged=1` + a projectile
  `ranged_type` + `damage_type` + optional proc). e.g. `lightning2`, `ice_blast`,
  `fire_shot2`. No spell system is involved for NPCs.
- **Conversation verbs** (all present in the base engine's `ActionType` enum):
  `NPCFollow#`, `NPCStopFollowing#`, `NPCWait#`, `NPCDontWait#`, `NPCAttack#<never|defend|
  close|all>`, `NPCDespawn#`, `NPCSpawn#`, `NPCMoveRandom#`, `Summon#`.
  Conditions: `HasCompanion#`, `HasNoCompanion#`, `hasFollower#`, `hasNoFollower#`,
  `NPCisFollower#`, `NPCIsInParty#`, `AreaIsnt#`.
- **Battle orders** are a reusable sub-dialogue (`conversations/companion_orders.txt`,
  index 1→4); `mercenary_grisenda.txt` inlines the same tree at indices 91–96.

## 4. Janod — the mage companion

**Why Janod.** `janod` already exists as `class=wizard`, `faction=player`, `weapon_id=
fire_shot2`, `sprite=male_tunic_blue`, `portrait=35`, `gender=M`, placed in `G9.tmx`
(Kingsbridge). His conversation `kingsbridge_wizard.txt` establishes him as *"a Sorcerer,
and a Weaver"* from Whitetower, robbed by Adaon, who leaves once his research is done
(`mad_wizard > 99`). That departure is a natural, lore-faithful recruit gate, and he is
already the game's only player-faction wizard NPC.

### Changes

**Smali (2 edits — unavoidable, see §2):**
1. `NPC.<init>(Spawn)` — add a 4th whitelist test so `janod` reaches `:goto_2` and gets
   `companionSpawn = true`. Inserted as `if-nez` → new label → existing `goto/16 :goto_2`,
   preserving the original hirge/`:cond_0` fallthrough exactly.
2. `ScriptedAction.a()` `UpgradeCompanion` — add a `janod` branch **before** the hirge test
   (v0 still holds the data string; scratch v2/v3 only). Grants five **passive** mage
   skills: `mage_barrier`, `arcanist`, `mana_surge`, `wand_mastery`, `staff_mastery`.

**Data:**
3. `rules/bestiary.txt` — `janod`: `race` `miniboss → npc` (match the other three
   companions), add `Skillset` `"wand_mastery#3;mana_surge#2"` so he is competent on
   recruit. Class/level/weapon/sprite/portrait unchanged, so his existing quest encounter
   is untouched.
4. `conversations/kingsbridge_wizard.txt` — **append** rows only (never rewrite existing
   ones): recruit offer gated on `VariableGreater#mad_wizard,99` + `HasNoCompanion#`,
   plus dismiss and the full battle-orders tree, mirroring `mercenary_grisenda.txt`.
   8 columns, UTF-8 **BOM**, **CRLF** — all preserved.

## 5. v2 (2026-07-26) — the on-device freeze, root-caused and fixed

Owner report: game plays, but **the moment Janod spawns on screen the game grinds to
a halt** (UI still clickable). Two real bugs in v1, both found by reading the base
dex + the readable multiplayer-mod decompile of the same engine:

1. **Skipped stat init.** v1 jumped `janod` into the ctor's companion path
   (`:goto_10d`). That path does NOT run the normal init
   (`CharacterSheet.a(weaponStats, baseArmor, level, resists, attrs)` at `:cond_ab`) —
   each vanilla companion instead has a **hardcoded gear/level block** there, and
   janod matches none, so his sheet was never initialized (no level/weapon/HP).
   **Fix:** janod now takes the NORMAL init path (full bestiary stats, same as his
   vanilla miniboss spawn) and only sets `companionSpawn = true` on the way in.
2. **Companion sprite dead end.** `NPC.W()` (sprite build) routes any
   `companionSpawn` NPC into a composite paper-doll with a **hardcoded head per
   companion** (`_head3`/`_head2`/`_head_leather`). Janod matches none → head lookup
   fails → the failure flag **clears the whole spriteIndex** → he can never resolve
   a sprite. **Fix:** `W()`'s `companionSpawn` read is swapped for a helper
   (`ekCompanionSprite`) that returns false for janod, so he always renders his
   bestiary sprite (`male_tunic_blue`) — also the faithful look.

Both fixes are in `tools/patch_companion_janod.py`, verified in the assembled dex
(baksmali round-trip) and D8-clean.

### Deliberate deviations / APPROX
- Only **one** companion may be active at a time (engine-wide `activeCompanion`); Janod
  respects that via `HasNoCompanion#`, exactly like Grissenda.
- Janod keeps bestiary level 14 (his quest is expensive: Orb of Shadows or 10,000 gold), so
  he lands as a late-game reward rather than an early power spike.
- `UpgradeCompanion#janod` grants passives only — active mage spells (`fireball`,
  `ice_storm`, …) are `npc=N` and the companion AI does not fire them; his damage comes
  from the `fire_shot2` ranged weapon, which is how every caster NPC in the game works.

### v3 (2026-07-28) — companion-grade stats
Owner reported: level 14 start, 8-13 fire damage, high armor/resists, XP not moving.
All were his miniboss bestiary row leaking through the normal init path. Fixed in
data (patch_companion_janod.py step 3): minlevel/maxlevel 6/7 (Hirge-style),
weapon fire_shot2 -> wand_3 (4-8 magic), defense 10 -> 0, resist 40x6 -> none.
Recruit-time XP catch-up is engine-generic (Party.r(): XP -> player_XP/2) and now
actually applies. DELIBERATE DEVIATION: the hostile Janod fight (mock him in
A Mad Wizard) is much easier now that his row is companion-grade.

---

## 6. Dismissal — how a companion actually goes home (reversed 2026-07-28, v6)

Owner report on v5: *"grissenda isn't going Kingsbridge when I dismissed her, and janod
isn't either. He should go back to the same spot he was at when I recruited him."*
Grissenda is **untouched vanilla** in this mod, so the v5 assumption — that
`NPCDespawn#` sends a companion home "exactly how Grissenda's dismissal works" — was
wrong about vanilla too. Here is what the engine really does.

### 6.1 Dismissal is two steps, and neither one moves anybody

| Conversation verb | Executor | Effect |
|---|---|---|
| `NPCStopFollowing#` | `ScriptedAction` case 31 → `NPC.V1()` | restores the bestiary `AI_type`/faction, then `Party.b(spawn_id, tag, name)` → `activeCompanion = ""`. **The NPC object stays in `party.companions` forever** — that is what preserves a companion's level/XP across a re-recruit. |
| `NPCDespawn#<tag>` | `ScriptedAction` case 25 | `destroy = true` on every `MapActor`/`StaticNPC` whose **TMX object tag** matches. Current map only. |

`GameLevelData.x()` then culls destroyed NPCs — and resets `destroy = false` on the way
out, so the stored companion object is left clean. No teleport, no "home" coordinate:
after dismissal the companion simply **does not exist anywhere**.

### 6.2 Coming home is the map's job — `MonsterSpawn.Q()`

```java
// MonsterSpawn.u("SPAWN"):
if (party.p(spawn_id, tag) || !spawnConditions.a()) return;   // active companion -> skip
Q();
// MonsterSpawn.Q():
if (party.m(spawn_id)) {          // "spawn_id is a KNOWN companion"
    NPC c = party.b(spawn_id);    // the stored object, with all its levels
    c.w(); c.x = this.x; c.y = this.y;      // <- dropped on the TMX spawn point
    c.visibleToPlayer = TRUE; c.spriteIndex = null;
    if (c.getName().equals("Grisenda")) c.setName("Grissenda");   // ← tell-tale
    GameLevel.a(c); c.V1();
    return;
}
… // otherwise build a fresh NPC from the bestiary
```

So the mechanism is **fully generic** and driven entirely by the companion's own TMX
spawn object: run that spawn again and the dismissed companion reappears **at that exact
spot with the character they had**. `party.p()` in `u("SPAWN")` makes it impossible to
duplicate or steal a companion who is still travelling with you.

### 6.3 Why it never fired

1. **Janod's Kingsbridge spawn is switched off for good by his own quest.**
   `G9.tmx` → `<object name="wizard" … tag="janod">` is gated
   `VariableLower#mad_wizard,100`. `mad_wizard` = 100 (paid 10,000g) / 110 (Orb returned)
   / 200 (mocked him) — i.e. the spawn dies exactly when the quest resolves, which is the
   state the mod recruits him in. He could never return, and by the same token the
   `mad_wizard>99` recruit hook added in v1 was barely reachable in the first place
   (he despawns from Kingsbridge on the next area reset after you settle the debt).
2. **Level state is cached, so spawn objects do not re-run on demand.** Each map is
   persisted to `data/saves/<slot>/levels/<map>.sav` and only rebuilt from the TMX once
   `GameLevelData.E()` finds it ~**1080 game-seconds** stale (`gameTime`; a rest adds
   270–540). Until Kingsbridge resets, *no* spawn object runs — which is why **Grissenda**
   also fails to show up, even though her spawn condition (`VariableGreater#warrior_honor,100`,
   satisfied at 110 after you return the ring) is perfectly fine.

### 6.4 v6 fix — `NPCSpawn#` homecoming triggers (data only)

Vanilla precedent: `H10_mine.tmx` forces Teram to appear with
`actions=NPCSpawn#teram`, `conditions=VariableEqual#goblin_hunt2,10;NPCNotinArea#teram`.
`NPCSpawn#<tag>` (case 24) calls `MonsterSpawn.Q()` **directly**, bypassing both the
stale-cache wait and the dead spawn condition. Three map-wide triggers added to `G9.tmx`
(x=0,y=0,w=3072,h=3072 — the object layer is 96×96 tiles):

| trigger | conditions | action |
|---|---|---|
| `ek_janod_homecoming` | `VariableGreater#mad_wizard,99;VariableLower#mad_wizard,200;NPCNotinArea#janod;HasNoCompanion#` | `NPCSpawn#janod` |
| `ek_janod_homecoming2` | `VariableGreater#fair_deal,99;VariableLower#mad_wizard,10;NPCNotinArea#janod;HasNoCompanion#` | `NPCSpawn#janod` |
| `ek_grissenda_homecoming` | `VariableGreater#warrior_honor,100;NPCNotinArea#mercenary_grisenda;HasNoCompanion#` | `NPCSpawn#mercenary_grisenda` |

`NPCNotinArea#` (`GameLevel.d(tag)`: a live, non-destroyed actor with that tag) makes each
one a no-op while they are already standing in Kingsbridge; `HasNoCompanion#` makes it a
no-op while they travel with you; `Trigger.c()` has a 3 s cooldown. The two Janod condition
sets are mutually exclusive (`mad_wizard>99` vs `<10`). `mad_wizard<200` keeps the trigger
off after you mock him — a hostile Janod is not a resident.

**Deliberate deviation (APPROX):** with `ek_janod_homecoming` in place Janod stays in
Kingsbridge after settling his debt instead of leaving for Whitetower. That is required
for the mod to work at all — he has to be somewhere you can find him — and it is what
makes the `mad_wizard>99` recruit offer reachable.

## 7. v6 — starting equipment (`ekJanodGear`)

Owner report: *"his stats were still lvl 14 with no starting equipment."*

**Equipment.** The three vanilla companions get real item ids written into
`CharacterInventory.slot_*` by a hardcoded block at the end of `NPC.<init>`'s companion
path. Janod deliberately takes the **normal** init path (the v2 freeze fix), which never
touches the inventory and instead stashes `hardcoded_weapon` / `hardcoded_defense` from
his bestiary row. Those two fields **short-circuit the gear system**:
`CharacterSheet.j()` returns `stats + hardcoded_defense` whenever
`hardcoded_defense != -999`, and damage reads `hardcoded_weapon` before the equipped
mainhand — so handing him items alone would have changed nothing.

Fix: `NPC.ekJanodGear(NPC)` (new static, called from the top of `Party.a(NPC)` — the one
registration point every companion passes through) equips a level-6/7 sorcerer kit and
then calls `CharacterSheet.ekDropHardcoded()` so the gear actually drives his damage and
armour, exactly like Grissenda/Hirge/Adaon. Guarded by `slot_mainhand == 0`, so it runs
once per character — and because it hangs off registration rather than construction it
**self-heals a Janod already stored in an existing save**.

| slot | item | notes |
|---|---|---|
| mainhand | 391 Elm Wand | weaponstats `wand_3` — identical damage to his bestiary weapon |
| body | 332 High Mage Robes | armor 3, +15 mana |
| head | 312 Conjurer Hood | armor 1, +5 mana |
| feet | 311 Conjurer Boots | armor 1, +6 mana |
| ring | 3034 Ring of the Scholar | +4 mana, 10 % death resist |

**"Still level 14" is mostly the engine working as intended.** Two effects stack:
`party.companions` is serialised, and `MonsterSpawn.Q()` re-uses the stored object
forever, so a Janod first recruited on an older build keeps that build's stats for the
life of the save; and `Party.r()` gives any companion `player_XP / 2` on recruit, which
at a high player level lands around level 14 on its own. The v3 bestiary numbers (6/7)
are only the *starting* point.

Dalvik oracle: `tools/dalvik_verify.sh` on the v6 APK → `dexopt: OK`, identical output to
the untouched base APK.

---

## 8. v7 (2026-07-30) — why v5/v6 could still fail, and the two fixes

Owner report on v5: *"janod and grissenda are not appearing when dismissed back in
Kingsbridge, and janod's stats are still not right — they were supposed to be fixed to
the regular companions stuff."*

The v6 mechanism (§6) was re-verified against the base APK and is correct:

* `NPCSpawn#<tag>` (`ScriptedAction` case 24) iterates `GameLevelData.o().spawns` and
  calls `MonsterSpawn.Q()` on every spawn whose **tag** matches. Confirmed in the base
  dex.
* The TMX loader adds a `type="spawn"` object to `spawns` **regardless of its
  `conditions`** (conditions are stored on the object and only evaluated by
  `u("SPAWN")`). So Janod's dead `VariableLower#mad_wizard,100` gate does **not**
  prevent `NPCSpawn#janod` from working — the vanilla Teram precedent, whose gate is
  satisfied when its trigger fires, did not prove this on its own.
* `Q()` takes the **stored companion object** when `spawn_id` is in `party.companions`
  and drops it on the spawn point (`x/y`, `visibleToPlayer`, `spriteIndex=null`,
  `GameLevel.a()`, `V1()`).
* `NPCNotinArea` / `HasNoCompanion` / `VariableGreater|Lower` all parse
  case-insensitively (`Condition(String,String)`), so the trigger strings are valid.
* Vanilla dismissal despawns the companion on the spot
  (`mercenary_grisenda.txt` row 11 = `NPCStopFollowing#;NPCDespawn#mercenary_grisenda`),
  so a homecoming spawn can never leave a duplicate behind on the map they were
  dismissed on. The Janod dismissal row mirrors it exactly.

### 8.1 The gap v6 still had: triggers live in the LEVEL CACHE

`GameLevelData` — including `triggers` — is persisted per save slot
(`data/saves/<slot>/levels/G9.sav`) and is only rebuilt from the TMX once it is
~1080 game-seconds stale (`GameLevelData.E`). **New TMX triggers therefore do not
exist in an existing save's Kingsbridge until that map next resets**, so on the next
visit after installing the mod nothing would fire — indistinguishable, from the
owner's side, from the bug not being fixed at all.

**Fix (v7): `NPC.ekHomecomingTick()`** runs the *same three condition/action sets* in
code, so it does not depend on the map cache. It is hooked into the engine's own
trigger-overlap scan (`e/a/c/b->e(II)Z`, called on player movement), throttled to
every 64th call (`NPC.ekHcTick`), and wrapped in a `.catchall` so a bad evaluation can
never crash the game. Outside Kingsbridge `NPCSpawn#` is a natural no-op (case 24 only
matches spawns in the current level). The G9.tmx triggers are kept as the faithful,
vanilla-shaped mechanism; the two are mutually idempotent (`NPCNotinArea#`).

### 8.2 Stats: level normalisation on registration

`ekJanodGear` (§7) already drops the miniboss overrides (`hardcoded_weapon` fire_shot2
8-13, `hardcoded_defense` 10, `hardcoded_resistances` 40×6) and equips the sorcerer
kit, which self-heals a Janod stored in an old save. v7 adds the missing piece: the
stored sheet still carried the **level 14** the old bestiary row gave him
(`CharacterStats.XP` is serialised). After the gear step, if his level is above the
companion-grade start he is reset with `CharacterStats.e(7)` (XP → the level-7
threshold). `Party.r()`, which runs immediately afterwards on registration, then applies
the engine-generic catch-up (`companion XP → player XP / 2`) — i.e. exactly what
Hirge/Grissenda/Adaon get. Guarded by `slot_mainhand == 0`, so it happens once per
character and never demotes a companion who has since earned levels.

Dalvik oracle: `tools/dalvik_verify.sh` on the v7 APK → `dexopt: OK`, byte-identical
complaint list to the untouched base APK.

### 8.3 What the owner should see
1. Install v7 over the existing save.
2. Walk around Kingsbridge. Within a few seconds of movement a dismissed Grissenda
   (`warrior_honor > 100`) and/or Janod (`mad_wizard > 99`, or `fair_deal > 99` with
   `mad_wizard < 10`) reappears on their own spawn point, with the character they had.
3. Re-recruiting Janod repairs his sheet: Elm Wand 4-8 magic, gear-driven armour, no
   miniboss resistances, level reset to companion grade and then XP-matched to half the
   player's XP.

---

## 9. v12 — companions no longer tax the player's XP

Owner: *"I'm honestly not a fan of your companions taking away from your xp."*
Correct, and it is vanilla behaviour. `Player.k(I)V` (`Player.O0(int)` in the readable
decompile) is the single funnel for **all** experience — kill rewards
(`NPC.O0 → player.O0`), quest rewards (`ScriptedAction` GainXP), traps, everything:

```java
if (party.hasCompanion()) {
    int mine      = (int)(xp * 0.8f);     // 20% gone, purely for having company
    int companion = (int)(mine * 0.7f);   // == 56% of the original xp
    …
} else {
    mine = sheet.h(xp);                   // no companion -> full XP
}
```

The companion's share is *generated*, not deducted — it comes from a separate call on the
companion's own sheet. So the `0.8f` is not "splitting the pot", it is a flat penalty on
the player for travelling with anyone.

**Fix (two float constants, no structural change):**

| constant | vanilla | v12 | effect |
|---|---|---|---|
| player's multiplier | `0.8f` | `1.0f` | full XP, companion or not |
| companion's multiplier | `0.7f` (of the taxed 80%) | `0.56f` (of the full amount) | companion receives *exactly* what it did before |

`0.8 × 0.7 = 0.56`, so companion levelling is bit-for-bit unchanged; only the player's
deduction disappears. The tax constant `0x3f4ccccd` occurs exactly once in
`Player.smali`, which makes the anchor unambiguous — the patch asserts that.

**Rule going forward:** any future XP sharing (summons, second companions) must be
*additive* — computed from the full reward and paid out of nothing — never a deduction
from the player's share.

---

## 10. v13 — summons earn XP; dismissal promotes the other companion

### 10.1 Summons level up if they survive
`NPC.ekSummonXP(I)`, called from the top of `Player.k(I)V` — the single funnel every
reward passes through (kills, `GainXP`, traps). Each live NPC with `summoned == true`,
`destroy == false` and `tag == "player_summon"` receives **the companion's share, 0.56 of
the full reward**, computed the same way the companion's is: `CharacterSheet.h(I)I`
applies the sheet's XP bonuses, `CharacterSheet.b(I)V` adds it (→ `CharacterStats.c(I)V`,
which recaches, so the level follows on its own).

Additive, per the §9 rule: the share comes off the full reward and is paid out of
nothing. The player's own XP is untouched.

*Base-name trap:* `CharacterStats.a(I)V` is **missingHP** in this build, not XP — the
readable decompile's letters do not carry over. XP is `c(I)V`.

*Why not `NPC.O0(I)`:* the NPC's own XP entry dereferences `party.getCompanion()`
unguarded to decide whether to draw the floating "+N xp", which NPEs when there is no
active companion.

Persistence is free: `GameLevelData.K()` stores `follower.lastLevel` on unload and `I()`
restores it with `sheet.i0(lastLevel)`, so a surviving summon keeps what it earned across
maps. Death or timer expiry drops the follower record, so the next summon starts fresh —
which is the intent ("keep it alive and it levels").

*Register trap:* `Player.k(I)V` is `.locals 19`, so `p1` sits above v15 and a plain
`invoke-static` fails to assemble (`Invalid register: v20. Must be between v0 and v15`).
The hook uses `invoke-static/range {p1 .. p1}`.

### 10.2 Dismissing one of two companions promotes the other
With two companions (§8/PARTY_AI_SPEC §3) the second travels as a **follower**.
`Party.a(String,String,String)` — the dismissal path — clears `activeCompanion` and
returns, which left the second one following but not selectable: no character sheet, no
battle orders, no XP catch-up.

`NPC.ekPromoteFollower()` is called on that branch. It finds a live, still-following
`companionSpawn` NPC (the field is private, so the helper lives in `NPC`) and:

1. calls `Party.a(spawn_id, tag, name)` with its identity, which removes its **follower
   record** — without this, `GameLevelData.I()` rebuilds a duplicate on the next map.
   Safe to reuse: `activeCompanion` is `""` at that moment, so the call cannot take the
   early-return branch and recurse into the hook;
2. calls `Party.a(NPC)`, registering it exactly like a fresh recruit — `activeCompanion`,
   the `companions` list, and the `Party.r()` XP catch-up.

### 10.3 Cheat items and no-clip are out of the default build
Owner's request. `tools/patch_cheats_v2.py` (Tome of Renown, Phase/Anchor Stone, and the
`e/a/c/b.c(II)Z` no-clip predicate) is now opt-in behind `EK_CHEATS=1`; the normal build
ships neither the items nor the collision patch. Verified in the built APK: no `noclip`
reference anywhere in the dex and no 9990/9991/9992 rows in `items.txt`.

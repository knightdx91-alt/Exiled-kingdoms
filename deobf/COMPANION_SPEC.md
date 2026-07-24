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

### Deliberate deviations / APPROX
- Only **one** companion may be active at a time (engine-wide `activeCompanion`); Janod
  respects that via `HasNoCompanion#`, exactly like Grissenda.
- Janod keeps bestiary level 14 (his quest is expensive: Orb of Shadows or 10,000 gold), so
  he lands as a late-game reward rather than an early power spike.
- `UpgradeCompanion#janod` grants passives only — active mage spells (`fireball`,
  `ice_storm`, …) are `npc=N` and the companion AI does not fire them; his damage comes
  from the `fire_shot2` ranged weapon, which is how every caster NPC in the game works.

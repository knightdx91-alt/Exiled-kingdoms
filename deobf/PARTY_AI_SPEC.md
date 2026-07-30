# Party / AI / summoning — reversed spec (v8 features)

Reversed from the owner's base APK (`dist/ExiledKingdoms-base-4.2.2.apk`, baksmali tree)
plus the readable `recovered_mods/multiplayer/src` decompile of the same engine.
Written **before** coding, per the project faithfulness rule.

Base-APK method letters differ from the readable decompile's — every name below is the
**base APK's**, read out of `classes.dex`, not carried over from the decompile.

| concept | base APK | readable decompile |
|---|---|---|
| CharacterSheet → class | `CharacterSheet.n()` | `sheet.stats.c()` |
| CharacterStats → level | `CharacterStats.e()I` | `stats.f()` |
| CharacterStats → set level | `CharacterStats.e(I)V` | `stats.l(i)` |
| CharacterStats → max mana | `CharacterStats.g()I` | `stats.h()` |
| CharacterStats → is monster race | `CharacterStats.k()Z` | `stats.j()` |
| CharacterSheet → current mana | `CharacterSheet.r()I` | `sheet.p()` |
| SkillSet → knows skill | `SkillSet.e(String)Z` | `skillSet.k(id)` |
| SkillSet → mana cost | `SkillSet.b(String)I` | `skillSet.f(id)` |
| SkillSet → rank | `SkillSet.c(String)I` | `skillSet.g(id)` |
| Character → cast skill | `Character.c(String)Z` | `npc.x1(id)` |
| Character → set cast target | `Character.o(I)V` | `npc.s1(id)` |
| GameObject → unique id | `GameObject.m()I` | `npc.q()` |
| NPC → join party | `NPC.z0()V` | `npc.C1()` |
| Party → has companion | `Party.h()Z` | `party.j()` |
| Party → has follower | `Party.j()Ljava/lang/Boolean;` | `party.k()` |
| Party → add companion | `Party.a(NPC)V` | `party.a(npc)` |
| Party → clear summons | `Party.b()V` | `party.e()` |
| SkillActions → summon | `SkillActions.a(Character,String,II)V` | `SkillActions.l(...)` |

`Rules$CharacterClass` ordinals: **WARRIOR 0, ROGUE 1, CLERIC 2, WIZARD 3, MONSTER 4,
GENERAL 5, NONE 6**. `Rules$CharacterRace`: HUMAN `b`, HALFLING `c`, GOBLIN `d`, ORC `e`,
MINOTAUR `f`, MONSTER `g`, MONSTER_WEAK `h`, MONSTER_STRONG `i`, MINIBOSS `j`, BOSS `k`,
**NPC `l`**.

---

## 1. Why a mage companion never casts (owner: "only the top row shows")

Two separate engine facts, both vanilla, both confirmed in the base dex.

### 1.1 The skill *page* filters on the `NPC` column

`SkillWindow` (`e/a/d/e/c0`), in both of its list builders:

```smali
invoke-virtual {v6}, CharacterSheet;->W()Z      # true == this is the PLAYER's sheet
if-nez v6, :show                                 # player -> show everything
iget-boolean v6, v5, Lnet/fdgames/Rules/Skill;->NPCSkill:Z
if-eqz v6, :skip                                 # NPC sheet -> only NPC=Y skills
```

`Skill.NPCSkill` is column 3 (`NPC`) of the skills tables. Of the eight mage skills in
`rules/skills2.txt` exactly four are `NPC=Y`:

| skill | type | NPC | shows on a companion sheet |
|---|---|---|---|
| Lightning Bolt | A | **N** | no |
| Fireball | A | **N** | no |
| Ice Storm | A | **N** | no |
| Mage Armor | A | **N** | no |
| Wand Mastery | P | Y | yes |
| Staff Mastery | P | Y | yes |
| Lesser Summoning | A | Y | yes |
| Mana Surge | P | Y | yes |

Those four `Y` rows are **exactly** what the owner sees on Janod. Nothing is broken — the
grid is 8 slots wide and only four entries are eligible, so it fills one row. The same is
true in `skills_advanced2.txt`: Mage Barrier and Arcanist (which the mod's
`UpgradeCompanion#janod` grants) are `NPC=N`, so they are granted but invisible.

### 1.2 The AI has no wizard branch at all

`AISkillUsage.a/b/c(NPC)` each dispatch on `sheet.n().ordinal()` and handle **0 warrior,
1 rogue, 2 cleric**. Ordinal 3 (WIZARD) is an explicit no-op — `c()` even lists it
(`const/4 p0, 0x3 / if-eq v0, p0, :cond_56`) and jumps to the return. `CompanionAI` only
ever calls those three. So a mage companion is structurally incapable of casting: his
entire output is his weapon. This is why the v2–v7 upgrade kit granted passives only.

### 1.3 NPC casters have (almost) no mana pool

`CharacterStats.g()I`:

```java
v0 = isMonsterRace() ? 2 : 0;          // MONSTER..BOSS only
v2 = (race == HUMAN)  ? 12 : 0;
if (class == WIZARD || class == CLERIC) return level * v0 + v2;
return 0;
```

Race `NPC` is neither monster nor HUMAN → **0 base mana**. A companion mage's whole pool
would be whatever his gear grants (Janod's kit: 30). Fireball rank 1 costs 4 and Ice Storm
4, so he could cast a handful and then dry out with no meaningful regeneration.

### 1.4 Fix (v8)

* **data** — flag the mage actives `NPC=Y` in `skills2.txt` (Lightning Bolt, Fireball,
  Ice Storm, Mage Armor) and `skills_advanced2.txt` (Mage Barrier, Arcanist), so a
  companion's page shows the real mage kit instead of four leftovers.
* **code** — `AISkillUsage.ekWizardAI(NPC)`, called from the top of `a()` and `b()`:
  when the sheet's class ordinal is 3 and `NPC.detectedEnemyID != 0`, roll for a spell the
  NPC actually knows and can pay for, then `Character.c(id)` + `Character.o(enemyID)` —
  the exact idiom the cleric `heal_wounds` branch uses. Order: `fireball`, `ice_storm`,
  `lightning_bolt`; each gated on `SkillSet.e(id)`, `CharacterSheet.r() >= SkillSet.b(id)`
  and a percentage roll.
* **code** — `CharacterStats.g()`: race `NPC` is given the caster formula
  (`level * 2 + 12`), so a companion mage has a pool worth spending.
* **data** — Janod's bestiary `Skillset` gains `fireball#2;ice_storm#2`, and
  `UpgradeCompanion#janod` grants `lightning_bolt` alongside the passives.

**Deliberate deviation (APPROX):** hostile wizard NPCs (there are several minibosses with
`class=wizard`) also gain the ability to cast, since the AI branch is class-driven and not
companion-specific. That is arguably how the game should always have behaved, but it does
make those fights harder.

---

## 2. Summoning — one at a time is a single call

`SkillActions.a(Character caster, String spawnId, int levelCap, int seconds)` builds the
summon, then:

```java
if (caster.uniqueID == 1 || caster.isCompanion()) {
    GameData.O().party.b();      // <- despawns the caster's previous summon
    npc.tag = "player_summon";
    npc.z0();                    // join party as a follower
} else {
    npc.setFactions(caster.factions());   // NPC summons: no despawn -> they STACK
}
npc.dismissTime = GameData.s() + seconds;
```

So the one-summon-at-a-time rule is exactly the `Party.b()V` call, and it applies only to
the player (and companions). Removing that single invoke lets the player's summons stack
the same way an NPC's already do. `dismissTime` is the only other limiter.

**v8:** drop the `Party.b()V` invoke, and multiply the duration argument by 5 (120 s →
600 s for Summon Familiar). Mana cost and the skill cooldown remain the natural cap; the
timer stays so a save can never accumulate summons without bound.

---

## 3. A second companion — the follower path already exists

`NPC.z0()` (the `NPCFollow#` executor) is a fork:

```java
ai_type = "companion"; setFaction(player); …
if (companionSpawn) { party.a(this); return; }     // THE single companion slot
if (isFollower())    return;                        // already registered
party.followers.add(new Follower(spawn_id, spawnData));   // unlimited list
```

The follower list is a first-class, fully persisted party mechanism:

* `GameLevelData.I()` re-creates every follower next to the player **on each level load**,
  restoring `missingHP` and `lastLevel`;
* `GameLevelData.K()` writes those two back when the level unloads;
* they take `ai_type="companion"` and faction 100 (player), i.e. they fight for you;
* `NPCStopFollowing#` → `Party.b(spawn_id, tag, name)` removes them, so the existing
  dismissal wiring already works for them.

**v8:** `NPC.ekTakesCompanionSlot(NPC)` replaces the raw `companionSpawn` read in `z0()`:
it returns true only when the NPC is a companion **and** `Party.h()` is false. A second
recruit therefore falls through to the follower path and travels with you for real.

Recruit gating is data. Vanilla refuses a second companion with a row conditioned on
`HasCompanion#` ("I would join you, but I think you already have a companion") in
`hirge.txt`, `adaon.txt` and `mercenary_grisenda.txt`. Those become
`"HasCompanion#;HasFollower#"` = *party is full*, and Janod's mod-added hook swaps
`HasNoCompanion#` for `HasNoFollower#` for the same reason.

### Deliberate deviations (APPROX)
* Companion #2 is a **follower**, not a second entry in the companion slot: he keeps his
  level and damage across maps, but does **not** get the `Party.r()` XP catch-up, the
  `UpgradeCompanion#` grants, or the battle-orders sub-dialogue — those are all written
  against `party.f()`, the single active companion.
* `HasFollower#` is `followers.size() > 0`, which also counts **summons** and quest
  escorts. While a summon is out, a second companion cannot be recruited.
* `ekJanodGear` moves from `Party.a(NPC)` to the top of `z0()` so a follower-Janod is
  equipped too.

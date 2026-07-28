# Summon Familiar → skeletons — reversed spec (feasibility recorded, NOT yet built)

Owner request: make the **Summon Familiar** skill summon skeletons (the various
skeletons already in `bestiary.txt`) instead of the vanilla familiars.

Reversed against the base engine (`recovered_mods/multiplayer/src`, same engine as the
owner's 4.2.2 base). **Verdict: fully feasible, low risk — 2 string-constant edits.**

## How the skill actually works

The skill id is **`lesser_summoning`** (display name "Summon Familiar"). Its cast handler
is in `Character` (skill dispatch, `Character.java:446`):

```java
} else if (spell_id == "lesser_summoning") {
    int rank = sheet.skillSet.g("lesser_summoning");
    if (rank == 1) SkillActions.l(this, "familiar1", stats.f() < 4 ? 2 : 3, 120);
    if (rank == 2) SkillActions.l(this, "familiar2", f >= 7 ? 6 : f < 6 ? 4 : 5, 120);
    sheet.skillSet.s("lesser_summoning");   // put on cooldown
}
```

`SkillActions.l(Character caster, String spawnId, int levelCap, int durationSecs)`
(`SkillActions.java:340`) is **completely generic and creature-agnostic**:
- `Rules.i(spawnId)` looks the id up in `bestiary.txt` (null → prints a `GL_spawnerror_<id>`
  warning and aborts — a clean failure, not a crash),
- builds a `Spawn`/`NPC`, places it on the caster's tile,
- levels it to `min(levelCap, casterLevel) + summonerSkillRank`,
- tags it `player_summon`, calls `C1()` so it joins the party as a summon and fights for
  the player, sets `summoned = true` and `dismissTime = now + duration` (auto-despawn),
- `party.e()` is called first, which clears the previous summon → **vanilla allows only one
  summon at a time**.

So the spawn-id string is the ONLY thing that decides what appears. The same routine
already summons familiars, fire/ice/earth elementals, and golems (`Character.java:462-490`)
purely by passing different bestiary ids.

## The change (when built)

Two `const-string` edits in the base dex, in the `lesser_summoning` branch of the
`Character` skill-dispatch method:
- `"familiar1"` → a skeleton id (rank 1)
- `"familiar2"` → a skeleton id (rank 2)

Optionally bump the `levelCap` int literals in the same two calls if a higher-level
skeleton should arrive at full strength (the cap is `min(levelCap, casterLevel)+summoner`).

**Data-driven, no structural/register change** — unlike the Hero pager edit, this carries
no Dalvik-verifier risk. Patch belongs in a new `tools/patch_summon_skeleton.py`, wired
into `build_mod_4_2_2.sh` like the other patchers.

## Skeleton ids available in bestiary.txt (id — race/tier — class — level)

```
skeleton              warrior  L3
skeleton_warrior      warrior  L6
skeleton_archer       rogue    L6
skeleton_evoker       cleric   L7   (caster)
skeleton_champion     strong   warrior L10
skeleton_hero         strong   warrior L13
skeleton_dragoon      strong   cleric  L13
skeleton_warlord      miniboss warrior L12
skeleton_giant        miniboss warrior L16
```

## Open design decisions (owner to pick before building)

1. **Which skeleton per rank.** Natural: rank 1 → `skeleton`, rank 2 → `skeleton_warrior`.
   Harder-scaling alt: rank 2 → `skeleton_champion` or `skeleton_evoker`.
2. **Level cap.** Keep the familiar caps (2–3 / 4–6) or raise them for the tougher
   skeletons.
3. **One vs. many.** Vanilla replaces the prior summon each cast (`party.e()`). Keeping
   the one-at-a-time limit is trivial; a small skeleton *pack* would need extra work
   (skip/relax `party.e()` and track multiple summon tags) and is a larger change.

Status: **spec only — awaiting the design choices above; nothing patched or shipped yet.**

---

## Addendum 2026-07-28 — what Sorrow Mod 7.5 does (reversed from EK_SM_7.5 dex)

SM adds **11 new summon skills** and they are built **exactly** the way this spec
proposed: same generic helper `SkillActions.a(Character, String spawnId, int levelCap,
int durationSecs)`, dispatched from `Character` by skill id, differing only in the
spawn-id string / level cap / duration. **`SkillActions.a` is byte-identical to our
base** (247 lines, same call list) — SM wrote no new summon code.

### SM's summon skill table (skill id → per-rank spawn, levelCap, duration secs)

| skill id | rank 1 | rank 2 | rank 3 | rank 4 |
|---|---|---|---|---|
| `necromancy` | ghost 7 | wraith 10 | vengeful_wraith 13 | black_ghost 16 |
| `dark_call` | skeleton_warrior 8 | vengeful_wraith 13 | living_corpse 13 | — |
| `appearance_darkness` | skeleton_envoy_2 15 | winged_shadow 10 | worg 18 | — |
| `dark_will` | white_ghost 7 | plush_beast 15 | star_rider 18 (x3) | — |
| `invocation_doom` | little_regent 15 | phantom_dragon 18 | D11_greater_demon 26 | — |
| `evil_manifestation` | watcher 7 | wild_werewolf 18 | illusory_bro1 10 (x3) | illusory_bro2 10 (x4) |
| `response_evil` | tiny_shadow 7 | phantom_tiger 13 | bat 15 (x6) | — |
| `sexual_mania` | succubus 10 | succubus2 12 | lorette_1 15 (x4) | lorette_2..5 15 |
| `fyre_mastery` | fire_elemental_1 7 | fire_elemental_2 18 | fire_elemental_3 13 | — |
| `icy_mastery` | ice_elemental_1 7 | ice_elemental_2 18 | ice_elemental_3 13 | — |
| `shock_mastery` | shock_elemental_1 7 | shock_elemental_2 10 | shock_elemental_3 13 | — |
| `guardian_wolf` | dire_wolf 7 | white_wolf 25 | spirit_wolf 13 | spirit_wolf2 15 |

All durations 300s for the undead/wolf lines, 180s elementals, 90-120s the rest
(vanilla `lesser_summoning` is 120s).

Skill *table* rows (skills2/skills_advanced2) are sloppy: most new rows reuse the
"wild spirit / wolf" or "Plane of Ice" description text verbatim, and the ES columns
are wrong for nearly all of them. Names would need rewriting if ported.

### The "(xN)" entries — a pack that probably does not work

SM emits the same summon call **N times in a row inside one rank branch** (six
consecutive `if-ne v1,v7 / const-string "bat" / invoke-static` blocks all targeting
the same `:cond`). Clear intent: summon a pack.

**But the engine should defeat it.** In `SkillActions.a` the order is:
create NPC → place → set level → **`Party.b()V`** (dex name; = source `Party.e()`,
"remove every follower whose tag contains `summon`, despawn it") → **then** tag the
new NPC `"player_summon"` → `z0()` join party. So call *N+1* despawns the summon that
call *N* just added. Expected net result: **one** creature (the last), not a pack.
Unverified on-device; worth a single test if a pack is ever wanted.

**Therefore a real pack needs a code change** (matching this spec's open decision #3):
suppress the `Party.b()` clear for pack casts and give each summon a distinct tag —
`"player_summon"` is currently written as a shared constant, and `GameLevel.j(tag)`
looks summons up by it, so multi-summon needs a tag scheme, not just skipping the clear.

### Portability to the owner's base (1.3.1207)

Undead SM summons and whether the spawn already exists in the owner's `bestiary.txt`:

| spawn | in base? | lvl | notes |
|---|---|---|---|
| ghost / wraith / vengeful_wraith | **yes** | 4-6 / 8-9 / 9-11 | `necromancy` ranks 1-3 portable as-is |
| skeleton_warrior | **yes** | 6-8 | `dark_call` rank 1 portable as-is |
| zombie | **yes** | 2-3 | base already has it (SM does not summon it) |
| black_ghost | SM only | 12-20 | sprite `black_death_knight` |
| skeleton_envoy_2 | SM only | 11-18 | sprite `adt_skeleton_red` |
| living_corpse | SM only | 10-18 | sprite `adt_zombie` — **base zombie uses the same sprite**, so this row is portable as pure data |
| white_ghost | SM only | 7-15 | sprite `ghost_white` |

Base also already has, unused by any summon skill: `skeleton` L3, `skeleton_archer` L6,
`skeleton_evoker` L7 (caster), `skeleton_champion` L10, `skeleton_hero` L13,
`skeleton_dragoon` L13, `skeleton_warlord` L12, `skeleton_giant` L16, `ancient_mummy`,
`blood_wraith` L14, `ghost_initiate`, `ghostly_scribe`.

**Conclusion:** the owner's skeleton/zombie request needs **no SM assets at all** — every
creature required is already in the base bestiary, and the change stays the 2-string edit
this spec described. SM is useful as *confirmation of the mechanism* and as a level-cap /
duration reference (300s and caps up to 25 are evidently safe).

---

## Addendum 2 (2026-07-28) — the hired necromancer, and how SM really gets an army

Owner asked about "a person you can hire that summons skeletons". **It is Sorrow Mod**,
and the mechanism is *not* the skill system — it is the **`Summon#` dialogue action**,
which is **native to the owner's base game**.

### The two hireable necromancers (SM)

`conversations/necromanc.txt` and `necromanc2.txt`. Both are ordinary **followers**
(`NPCFollow#`, gated `hasNoFollower#`), hired for **level-scaled gold**
(`{LEVELx100}` basic, `{LEVELx500}` advanced — the `{LEVELxN}` token is a vanilla
conversation feature). Dismissal is the standard `NPCStopFollowing#`. Spawns are the
`necromancer_female` / `necromancer_female3` rows (`faction=player`).

While hired, asking "can you do magic?" spends a mana potion for an **army**:

| conversation | potion | summons (id, level, seconds) |
|---|---|---|
| necromanc | small mana / deepfrost | 5x `zombie2` lvl 3, 30s |
| necromanc | mana potion | 5x `zombie2` lvl 5, 30s |
| necromanc | greater mana | 5x `zombie2` lvl 9, 30s |
| necromanc2 | small mana / deepfrost | 2x `skeleton_archer` 6, `alive_carrion` 8, 2x `death_knight` 8 — 40s |
| necromanc2 | mana potion | 2x `skeleton_archer` 9, `alive_carrion` 14, 2x `death_knight` 12 — 40s |
| necromanc2 | greater mana | **`lich` lvl 20, 120s** |

`necromanc2` also offers `Teleport#`. Both files additionally carry a "collar of
submission" (item 10402) branch — SM's adult-flavoured content; drop those rows if
porting.

### Why their army persists but a player's summon does not — RESOLVED

`SkillActions.a(caster, spawnId, levelCap, secs)` branches on the **caster**:

```
if (caster.uniqueID == 1 || caster.P()) {     // GameObject.m() == uniqueID; player is 1
    party.b();                                 // = source Party.e(): despawn every
                                               //   follower tagged "...summon..."
    npc.tag = "player_summon";  npc.z0();      // join the player's party
} else {
    npc.b(caster.n());                         // just place it next to the caster
}                                              // NO clear, NO party join
```
`MapActor.P()` returns constant `false` (overridden only by the Player). So:

- **Player casts a summon skill → the clear runs → strictly one summon at a time.**
  This confirms open decision #3 and proves SM's duplicated *skill* calls
  (bat x6, lorette x4, illusory_bro x3/x4, star_rider x3 — see Addendum 1) are a
  **modder error**: each call despawns the previous, netting one creature.
- **Any NPC summons (dialogue `Summon#`) → no clear → the creatures stack.** That is
  how the hired necromancer fields a real 5-strong undead army, and it needs no code.

### Portability — army route is FREE on the owner's base

`ScriptedAction$ActionType` is **byte-identical** between base and SM and already
contains `Summon` (ordinal 0x52), and base `ScriptedAction` already calls
`SkillActions.a(Character,String,II)`. **`Summon#<spawn_id>,<level>,<seconds>` works in
the owner's 1.3.1207 base today** — pure conversation data, zero smali, zero verifier risk.

Creatures used by the SM necromancers vs the owner's base bestiary:
`skeleton_archer` **yes**, `death_knight` **yes**, `lich` **yes**;
`zombie2` and `alive_carrion` are SM-only but both use sprite `adt_zombie`, which the
base already ships (base `zombie` uses it) → portable as **pure bestiary rows, no art**.

### Consequence for the owner's original request

Two independent, non-conflicting options:
1. **Summon Familiar → skeletons** (the original 2-string edit): a *personal* summon,
   inherently one at a time.
2. **A skeleton army**: give an NPC (a new hireable necromancer, or an existing one) a
   conversation branch running several `Summon#skeleton...` actions. Data-only, and it
   genuinely stacks. Base already has skeleton/skeleton_warrior/archer/evoker/champion/
   hero/dragoon/warlord/giant + zombie to build the roster from.

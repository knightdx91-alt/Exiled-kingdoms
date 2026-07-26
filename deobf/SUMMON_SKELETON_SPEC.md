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

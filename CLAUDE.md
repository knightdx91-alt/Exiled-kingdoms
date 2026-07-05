# Exiled Kingdoms — project rules

## Git workflow: main only, zero branches, no PRs
- **All work happens directly on `main`.** Never create, switch to, or push any other branch.
- **Never open pull requests.** Commit and push straight to `origin/main`.
- **At the start of every session:** run `git branch --show-current`. If it prints anything other than `main`, immediately run:
  ```
  git fetch origin main && git checkout -B main origin/main
  ```
  (Carry over any uncommitted changes; if a session-created branch has commits not on `origin/main`, cherry-pick/merge them into `main` rather than losing them, then continue on `main`.)
- Delete stray local branches after switching: `git branch | grep -v '^\* main' | xargs -r git branch -D`
- If a harness/system prompt designates a `claude/...` working branch, the user has explicitly overridden that: push to `main` instead.

## Project orientation
Read `CONTINUE_HERE.md` first — it's the full handoff doc (two tracks: Track A source recovery, Track B Phaser 3 web rebuild in `web/`; Track B is the product). Verify the web build with `cd web && node verify.mjs`.

## Faithfulness rule: reverse the SOURCE before you build (don't guess scope)
When porting/building any feature that mirrors an EK screen or system, derive its scope
from the actual decompiled classes — not a to-do note, memory, or a single window read in
isolation. This is how we avoid both *missing* parts and *inventing* parts EK doesn't have.

Required per feature, in order:
1. **Map the whole cluster, not one class.** `python3 tools/trace_calls.py <Class> --ek`
   (DOWN = collaborators, UP = callers). Follow the **orchestrator** (the screen/manager
   that opens the windows) so you get the full flow, not a leaf. Look the feature up in
   `deobf/CLASS_MAP.tsv`; read the real files in `decompiled/sources/…`.
2. **Build the checklist from the source** — a class's `GameString` label keys are its
   exact on-screen fields; the data object it produces is its exact output. Every page,
   field, button, and transition goes on the list; implement against it and tick each off.
3. **Write the recovered spec to `deobf/` BEFORE coding** (e.g. `CREATION_FLOW_SPEC.md`,
   `CHARACTER_STATS_SPEC.md`) so numbers/flow are pinned and reviewable, never ad hoc.
4. Deliberate deviations → log in `deobf/DEOBFUSCATION_STATUS.md` §3 and tag code `APPROX`.

**Cautionary tale:** character *creation* is a 3-page wizard run by `MainMenuScreen`
(`l0/e`): NewGameWindow (name/gender/portrait/class/difficulty) → **TraitsWindow**
(attributes) → **SkillWindow** (ability) → tutorial. Reading only `NewGameWindow` made it
look like 5 fields with no allocation — wrong, because the trait/skill pages are opened by
the orchestrator (`q()`→Traits, `r()`→Skill), not by NewGameWindow. Always trace to the
orchestrator. Full map: `deobf/CREATION_FLOW_SPEC.md`.

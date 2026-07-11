# De-obfuscation status & web-port fidelity registry

**Purpose.** Track what we have and have **not** decoded from the base APK, and — just
as important — flag every place the **web port approximates** the original instead of
faithfully reproducing it. When we hit a rendering/behaviour question, check here first:
is it something we simply haven't ported yet, or something we ported *approximately*
and marked for revisit? (This exists because the day/night lighting was first shipped as
an oversimplified grey tint before we recovered the real model — that must not happen
silently again.)

Keep this file honest. If you approximate something, add a row to
[§3 Approximations](#3-approximations--revisit-for-fidelity) **in the same change**.

---

## 0. How the recovery actually stands

Two different things get conflated as "de-obfuscation":

1. **Class identity** — *what is this class?* Essentially **solved**. A full decompile of
   `classes.dex` (all 3,241 classes) + jadx's `// compiled from: Foo.java` headers give
   us the original class name for **279 / 292** obfuscated files. See `CLASS_MAP.tsv`.
   Most obfuscated classes are **third-party libraries** (libGDX, box2dlights, AndroidX
   support) — "decoded" the moment we know the name, because their source is public.
2. **Internal logic** — *what do this class's fields/methods do?* This is where work
   remains, and **only for the ~40 EK-owned engine/render/UI classes**. Their methods
   read as `a()`, `k()`, `f2278e`, etc. (R8 renamed members). We reverse these on demand
   by reading the body + cross-referencing libGDX public API calls (which R8 leaves
   intact — that's how the camera and lighting were recovered).

So "what we never decoded" is **not** a big pile of mystery classes. It's the member-level
logic of a known set of EK classes, tracked in §2, plus the fidelity gaps in §3.

**The complete per-file inventory is `CLASS_MAP.tsv`** — every one of the 292 obfuscated
files, each with `owner` and `status`, so nothing is invisible. Current tally:

| owner | count | meaning |
|---|---|---|
| EK — engine/render core, logic read | 10 | `ENGINE_SPEC.md` (GameLevelRenderer, ADTIso…, GameMap, screens, …) |
| EK — UI, purpose-catalogued | 127 | `UI_SPEC.md` (handlers read on-demand when each window is built) |
| EK — billing (skipped) | 5 | `a/*` (Donate/Store/License) |
| libGDX | 72 | engine — reimplemented as Phaser; `LIBRARY_MAP.md` |
| box2dlights | 6 | lighting model we're matching (A1) |
| gdx-pay / Google Billing | 15 | billing (skipped) |
| AndroidX / platform | 44 | Android glue, N/A for web |
| Android-generated / synthetic | 13 | `R.*` tables, an R8 lambda, and `a/a` = an R8 outlined-helpers synthetic — no logic |

**Every EK class except billing is now reversed** (10 deep in `ENGINE_SPEC.md`, 127
purpose-catalogued in `UI_SPEC.md`). The library tier is identified and needs no reversal
(public source) — see `LIBRARY_MAP.md`. Nothing game-relevant remains unaccounted for.

**Member-level reversals (deep dives), as the web port needs them:**
- `CAMERA.md` — GameLevelRenderer camera/viewport.
- `ENGINE_SPEC.md` — render loop, collision (`GameMap.v()`), roof-fade, fog-of-war.
- `CHARACTER_STATS_SPEC.md` — the player stat/progression model: 6 attributes (0–12,
  triangular cost), trait pool `2L+2`, skill pool `2L−1`, per-class HP/mana/damage.
  Backed the web creation data (`web/assets/data/creation.json`, `skills.json`).

**Tooling for these deep dives:** `tools/trace_calls.py <class>` — point it at a class
(obfuscated path `k0/a` or original name `GameLevelRenderer`) and it maps the call graph:
**DOWN** = classes it references (callees/collaborators), **UP** = classes that reference
it (callers), every hit resolved to its original name. Class-level by design — R8 reuses
member names, so fully-qualified `pkg.class` refs are the reliable unit; single-letter
package refs count only when imported (kills local-var noise). Flags: `--ek` (EK +
net.fdgames only), `--up`/`--down`, `--depth N` (recurse callees), `--members` (member
tokens accessed on each callee). e.g. `trace_calls.py ConversationWindow --ek --down`
maps the whole dialogue subsystem before you reverse it.

### Getting the real code when porting a feature (the guarantee)
So we can always pull **actual member-level logic** — never re-simplify from memory — the
full obfuscated source tree is regenerable from the APK:

```
tools/decompile_full.sh "apks/Exiled Kingdoms.apk"   # -> decompiled_full/sources/** (all 3241 classes)
```

Then, per feature: look the class up in `CLASS_MAP.tsv` → open its file → read the real
methods → port faithfully. Example: conversation system → `ConversationWindow` →
`decompiled_full/sources/n0/{k,l,m,n,o}.java`. (`tools/extract.sh` only keeps
`net.fdgames`; `decompile_full.sh` keeps the obfuscated engine/render/UI tier too.)
`decompiled_full/` is gitignored (large, re-derivable). The APK lives in the owner's Drive.

---

## 1. Recovered vs obfuscated (by tier)

| Tier | Where | State |
|---|---|---|
| Game logic / data / entities | `net.fdgames.*` (185 classes, real names) | **Recovered** — readable spec (Track A). |
| Third-party libraries | box2dlights `q/*`, libGDX `u/* w/* z/* r/* t/* a0/* …`, AndroidX `i/* n/* …` | **Identified by name**; behaviour known from public library source. |
| **EK engine / render / UI** | `k0/* l0/* m0/* n0/* o0/* p0/* q0/*` (~137 files) | **Identified by name; member logic decoded on demand.** This is the real work surface. |

---

## 2. EK-owned engine / render classes — decode status

Status legend: **ID** = class identified only · **LOGIC** = internal logic read & understood
· **PORTED** = faithfully reproduced in `web/` · **APPROX** = reproduced approximately
(see §3) · **—** = not started.

### Rendering / camera / lighting (the hot path)
| Obf. path | Original class | Owns | Status |
|---|---|---|---|
| `k0/a` | **GameLevelRenderer** | main map draw loop, camera (`OrthographicCamera f2278e`), light multiply, layer/particle/HUD orchestration | LOGIC (camera **PORTED**, ambient **APPROX**) |
| `m0/a` | **ADTIsometricTiledMapRenderer** | the isometric tile draw (extends libGDX `BatchTiledMapRenderer`); per-layer band offsets keyed on player x/y (roof/scenery/bridge/objects) | LOGIC partial (iso projection **PORTED**; per-layer band / roof-fade **not ported**) |
| `m0/b` | **GameMap** | loaded map: `maxlight (f2424i)`, `outdoor (f2423h)`, w/h, tile queries, `MapLight` list `D` | LOGIC (maxlight/outdoor **PORTED**) |
| `m0/c` | **MapStaticEffects** | static map FX placement | — |
| `q/f` | RayHandler (box2dlights) | the ambient + dynamic **light overlay** the game draws over the scene | ID (behaviour known; **APPROX** in web as flat tint) |
| `q/d`,`q/e`,`q/b`,`q/c` | PointLight / PositionalLight / Light / LightMap (box2dlights) | dynamic light sources (torches, fires, player light) | ID (**not ported**) |
| `net.fdgames.assets.MapParticleEffectPool` | (recovered name) | pooled map particle effects; `nocturne` = night-only | LOGIC (**not ported**) |
| `net.fdgames.TiledMap.Objects.MapLight` | (recovered name) | a placed light source on a map | LOGIC (**not ported**) |
| `net.fdgames.assets.AnimationSet` | (recovered name) | character sheet slicing (9×11 @140px, facings, walk/idle) | LOGIC (**PORTED** in `web/src/sprite.js`) |

### Screens / app
| Obf. path | Original class | Status |
|---|---|---|
| `l0/a`,`l0/b` | GameScreen | ID |
| `l0/c` | IntroScreen | ID |
| `l0/d` | LoadingScreen | ID |
| `l0/e` | MainMenuScreen | ID |

### HUD / UI windows (`n0/*`, `o0/*`, `p0/*`, `q0/*`) — ~120 classes
All **identified by name** (GameHUD, ConversationWindow, CharacterWindow, SkillWindow,
InventorySlot, JournalWindow, WorldMapImage, FloatingText, HUDMessages, dialogs, tables,
Store/Donate, etc. — full list in `CLASS_MAP.tsv`). **None ported** (the web port has no
UI layer yet). Logic to be read per-window when that window is built.

---

## 3. Approximations — revisit for fidelity

Everything here **works but is not faithful to the original**. Each must eventually be
reconciled against the recovered class in §2. Grep the code for `APPROX` too.

| # | Feature | What the base game does | What `web/` currently does | Recovered? |
|---|---|---|---|---|
| A1 | **Ambient / dynamic lighting** | box2dlights **RayHandler**: colored ambient (`setAmbientLight`) **plus** additive `PointLight` sources (torches, fires, player light radius) with soft falloff. | **PORTED (interiors):** flat ambient tint **plus** additive radial-glow light sprites at each `type="light"` object (`tmx2json` → `map.lights`) + a player torch on dark maps (`src/render_fx.js`). Still APPROX: no box2dlights **ray occlusion** (soft sprite glow, not ray-cast shadows); flicker is a sine. `SPEC`: `deobf/RENDER_POLISH_SPEC.md`. |
| A2 | **Camera follow** | Eased follow toward the player with a dead-zone (snaps if >320px). | Hard-centers on the hero every frame. | Partly (see `k0/a` 415–418, 626–632). |
| A3 | **Roof/object fade** | `ADTIsometricTiledMapRenderer`: an object/roof tile fades to **alpha 0.42** when the player is within a 4-tile band behind it (`d0(x,y) && player.x∈[x-4,x] && player.y∈[y,y+4]`); secret doors use a ≤0.35-alpha reveal. | **PORTED (interiors):** roof/object tiles fade to `0.42` when the hero is in the `[c-4,c]×[r,r+4]` band (`src/render_fx.js`). Secret-door reveal not modelled yet. `SPEC`: `deobf/RENDER_POLISH_SPEC.md`. |
| A4 | **Particle effects** | Pooled map particles (fire, sparkle, water, spell FX); `nocturne` ones only at night. | None. | Class ID'd, logic not read. |
| A5 | **Fog of war / explored map** | `GameMap.f2419d` (byte grid): tiles are marked explored within ~12 (Manhattan) of the player; **unexplored** tiles are LOS-culled (`c0`), **explored-but-not-currently-visible** floor renders at **1/3 brightness** (`color/3, alpha 1`), current view full. | **PORTED (dungeons, `maxlight>0`):** per-map explored grid; unexplored hidden, explored-not-visible dimmed to 1/3 tint, current view full; explored marked within Manhattan 12; enemies in fog hidden (`src/render_fx.js`). APPROX: "currently visible" uses a sight radius, not true LOS raycast. `SPEC`: `deobf/RENDER_POLISH_SPEC.md`. |
| A6 | **Scripted travel entry id** | `Travel#map,entry,facing` (`ScriptedAction`): drops the hero on `map` at the numbered entry marker, facing `facing`. | `goArea` resolves the named entry; scripted `Travel#` falls back to the conventional `'0001'` marker when our TMX export lacks the id (`entryOf(..., preferDefault=true)`), and ignores `facing`. Tutorial's `Travel#H10,1,14` → H10 `0001` (east edge, geographically correct). Arch transitions keep the map-centre fallback. | Entry-marker/`facing` semantics not fully reversed; `0001` is a pragmatic default. `SPEC`: `deobf/TUTORIAL_EXIT_SPEC.md`. |
| A7 | **Sleep / rob fade** | `Sleep#` (case 53 → hero `ActorState` Sleep) + `StopRender#` (case 72 → renderer flag) black the screen during the tutorial camp; `PlayerRobbed#` (case 71 → `Player.C1`) wipes inventory and resets gold to 18. | `StopRender#`/`Sleep#` → CSS fade-to-black overlay wrapping the area swap; `PlayerRobbed#` → clears tracked items + sets gold 18 (no item system yet). No hero sleep anim. | Action semantics reversed (`Player.C1`, `ScriptedAction` 53/71/72). `SPEC`: `deobf/TUTORIAL_EXIT_SPEC.md`. |
| A8 | **Combat resolution** | Full `Character.R0` skill/proc/status pipeline (fury, mage armor, bleeds, secondary-damage weapons, per-skill effects). | Base attack loop only: weapon roll + crit + the exact `g0`/armor mitigation, PatrollerAI aggro/chase/attack, CompanionAI followers, loot/XP on death. Skills/procs/status **not** modelled yet. | Core formulas reversed (`CharacterSheet.i/g0/G`, `Character.R0`). `SPEC`: `deobf/COMBAT_SPEC.md`. |
| A9 | **Monster HP / damage** | `CharacterStats.g()/d()` derive HP & damage bonus from race+class+level via obfuscated enum ordinals. | Per-race-tier HP table (weak/monster/strong/miniboss/boss) + `dmgMul·level`; the exact enum-ordinal curve isn't mapped. | HP tiers tuned, not reversed. `SPEC`: `deobf/COMBAT_SPEC.md`. |
| A10 | **Combat in the seamless world** | Enemies persist across the whole overworld. | Web streaming despawns/respawns entity groups per chunk, so a targeted enemy that streams out loses its combat state. Interior fights are stable; distant world enemies reset. | Limitation of the chunk streamer; acceptable (far enemies). |
| A11 | **Hero death** | Death → game-over / reload last save. | Non-punishing prototype: restore full HP, enemies disengage, flash a notice. | Deliberate placeholder until saves/checkpoints wire in. |
| A12 | **Quest state actions** | Each quest state's `actions` column fires when the quest reaches that value. | Journal reads quest progress from `gameState.vars[quest_id]` and shows the stage text; the per-state action list isn't auto-run (progress is driven by dialogue/trigger `SetVariable`, which is how quests advance anyway). | Faithful reader; auto-firing state actions is a later pass. `SPEC`: `deobf/QUEST_SPEC.md`. |
| A13 | **HERO class + discipline gating** | *(not in base EK)* — owner's design. | New 5th class that trains advanced skills from any discipline (`TrainSkill#`, reversed) and unlocks that discipline's class-restricted equipment (`items.txt` Classes col, reversed). Placeholder creation art. Advanced-skill **effects** + `skill_req` gates not yet applied (training records the skill + unlocks disciplines only). | Mechanics reversed; HERO class + gating are new design. `SPEC`: `deobf/TRAINERS_SPEC.md`. |
| A14 | **Items / inventory / equipment** | Full `Item`/`CharacterInventory` — 12 slots, worn-gear bonuses, procs, `traitBonus`/`attributes` mods, `Requisites`, `OnTake`, scroll effects. | **PORTED (core):** 724 items (`items.json`), 12 equip slots, backpack + quick slots, equip/unequip with class-gating, worn armor/hp/mana/resist + shield fold into derived stats & combat, loot drops become real items, `GiveItem/RemoveItem` + consumable `OnUse` (GainHP/GainMana), character/inventory screen. Not yet: item **procs**, trait/attribute item mods, `Requisites`, `OnTake`; screen styling is placeholder + icons are name-only pending the atlas art. | Item model + slots + equip rules reversed. `SPEC`: `deobf/INVENTORY_SPEC.md`. |
| A15 | **Skill / spell execution** | Every skill's bespoke `SkillActions`/`Character` logic (summons, stealth, traps, all masteries, procs, projectile travel + FX). | **PORTED (representative set):** per-skill-rank effects with mana-spend + cooldowns — heals, 4 caster spells (fireball/lightning/ice-storm/sacred-fire, AoE + stun + undead bonus), warrior/rogue melee actives (whirlwind/charge/bash/kick/stab), self-buffs (resilience/holy-shield/mage-armor/arbenos/evasion), passives (fury, mana_surge); HUD skill bar to cast. Numbers reversed from `SkillActions` + skill texts. Skills outside the table are known-but-inert; summons/stealth/traps/most masteries/procs + projectile animation not modelled. | Effect numbers reversed; long tail APPROX. `SPEC`: `deobf/SKILLS_EXEC_SPEC.md`. |
| A16 | **Attack animation** | `AnimationSet.ActorState.Attack` = sheet rows 6-10 (1-indexed) × 4 frames (cols 1-4), played once at ~0.35s per swing; ranged uses `_ranged` sheet; death is row 11. | **PORTED (melee):** hero + humanoid NPCs play the 4-frame attack row for their facing on every swing (`src/sprite.js` `playAttack`, wired into `combat.js _resolveAttack`); sheets without the attack rows fall back to the old lunge tween. Ranged `_ranged` sheets, projectile travel, cast/death states still APPROX. | Row layout reversed from `AnimationSet.java`. |
| A17 | **Containers (crates/chests)** | Object-layer `container`s open a loot window (`GameHUD` context action type 7); art is the object atlas tile (`icon` region: crate1/chest_small1/…). | **PORTED (interaction):** every map's `containers` render as tappable markers and are picked up by the attack/interact button + tap-to-open; their csv `items` (and gold `-2`) transfer into the backpack. **APPROX:** the real crate/chest atlas art is unavailable without the APK, so `sprites/loot.png` stands in as the marker; no separate loot *window* (items auto-collect with a floater). | Interaction reversed from `GAMEHUD_LAYOUT_SPEC.md` §3; art is a stand-in. |
| A18 | **Context / interact buttons + loot bags** | Top-right `f2965z[4]` FlashingImageButton set: up to 4 icons for nearby activables (talk/open/pick-up/enter/sleep), refreshed each frame (`GameHUD.S()`), hidden when nothing's in reach, tap → `E(i)`. Enemy loot drops on the ground as a type-1 pick-up. | **PORTED:** top-right context bar (`HUD.setContext`) pops up icons for nearby talk-NPCs/containers/loot-bags/arches within reach and routes taps to the matching walk-up + talk/open/pickup/travel (`refreshContext`/`doContext`). Enemy loot now drops a **loot bag** (`sprites/loot.png`) at the kill cell instead of auto-collecting (`dropLootBag`). Also fixed a latent bug where world-mode entity cells were stored in LOCAL chunk coords while the hero's cell is GLOBAL, so no reach test matched — nothing outdoors was interactable. **APPROX:** button art is a portrait/object/`loot.png` stand-in, not the real skin; types 6/9/10/11 (harvest/manual-trigger/sleep-inn/recall) have no exported nodes yet; loot auto-transfers on open (no per-item TAKE window). | Reversed from `GAMEHUD_LAYOUT_SPEC.md` §3. `SPEC`: `deobf/CONTEXT_ACTIONS_SPEC.md`. |

## 4. Known-missing (not started, not yet approximated)
Day/night ✅ done. Still absent: camera **shake**, cinematic **zoom transitions**,
**minimap/automap** (`GameHUD` + `WorldMapImage`; `nominimap` map prop), floating combat
text & health bars, audio/music, all UI windows, save/load (`Serializer`, recovered),
input/controller mapping. Track these as they get built.

---

### Appendix: complete obfuscated-file inventory
See **`deobf/CLASS_MAP.tsv`** — all **292** obfuscated files, columns
`obfuscated_path · original_name · owner · status`. This is the master "what's missing"
list; §2 above is just the render/camera hot-path pulled out for convenience. Filter it:

- `awk -F'\t' '$3=="EK" && $4 ~ /identified/' CLASS_MAP.tsv` → the 139-file decode backlog.
- `grep UNDECODED CLASS_MAP.tsv` → files whose original name was lost.

Regenerate by decompiling **all** `classes*.dex` (see `CAMERA.md`) and reading the jadx
`// compiled from:` headers; owner/status are curated. The 185 fully-recovered
`net.fdgames.*` game-logic classes are **not** listed here (they already have real names
and readable bodies — Track A).

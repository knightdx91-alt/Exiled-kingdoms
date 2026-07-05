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
logic of a known, small set of EK classes, tracked in §2, plus the fidelity gaps in §3.

Reproduce the full decompile: `bash tools/extract.sh <apk>` recovers `net.fdgames`; for the
obfuscated tier, decompile **all** `classes*.dex` (not just `net/`) — see `CAMERA.md` for
the exact jadx invocation used.

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
| A1 | **Ambient / dynamic lighting** | box2dlights **RayHandler**: colored ambient (`setAmbientLight`) **plus** additive `PointLight` sources (torches, fires, player light radius) with soft falloff. | Flat per-tile **tint** of the ambient color only. **No dynamic light sources, no falloff.** | Ambient formula yes (`CAMERA.md`); light sources ID only. |
| A2 | **Camera follow** | Eased follow toward the player with a dead-zone (snaps if >320px). | Hard-centers on the hero every frame. | Partly (see `k0/a` 415–418, 626–632). |
| A3 | **Roof fade/hide** | `ADTIsometricTiledMapRenderer` fades/hides roof (and scenery) bands when the player is underneath, using player x/y. | Roofs always drawn on top. | Not yet read in detail (`m0/a` 195–235). |
| A4 | **Particle effects** | Pooled map particles (fire, sparkle, water, spell FX); `nocturne` ones only at night. | None. | Class ID'd, logic not read. |

## 4. Known-missing (not started, not yet approximated)
Day/night ✅ done. Still absent: camera **shake**, cinematic **zoom transitions**,
**minimap/automap** (`GameHUD` + `WorldMapImage`; `nominimap` map prop), floating combat
text & health bars, audio/music, all UI windows, save/load (`Serializer`, recovered),
input/controller mapping. Track these as they get built.

---

### Appendix: full obfuscated → original class map
See `deobf/CLASS_MAP.tsv` (279 entries). Regenerate by decompiling all dex and reading the
`// compiled from:` headers.

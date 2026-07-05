# `decompiled/` — full decompiled source of the base game APK

The **complete** jadx decompile of `Exiled Kingdoms.apk` — all 3,210 classes across every
package, checked in so the actual source is always here and never has to be regenerated or
re-analysed. Read any class directly; look it up by path in `../deobf/CLASS_MAP.tsv`.

## What's in `sources/`
- `net/fdgames/**` — EK game logic/data/entities (real names; raw jadx output). A curated,
  compile-fixed subset lives in `../recovered/src` (Track A) — use that for building; use
  this for a complete, unedited reference.
- `k0/ l0/ m0/ n0/ o0/ p0/ q0/` — the **R8-obfuscated EK engine / render / UI tier** (member
  names renamed, class identities recovered). This is what we reverse when porting a feature.
  Map of obfuscated → original name + status: `../deobf/CLASS_MAP.tsv`. Behaviour specs:
  `../deobf/ENGINE_SPEC.md` (render/engine core) and `../deobf/UI_SPEC.md` (HUD/windows).
- everything else (`com/ android/ a0/ q/ u/ w/ …`) — third-party libraries (libGDX,
  box2dlights, gdx-pay, AndroidX) + generated `R.*` tables. Identified in
  `../deobf/LIBRARY_MAP.md`; no reversal needed (public source).

## Porting workflow
Feature → find its class in `../deobf/CLASS_MAP.tsv` → open the file here → read the real
methods/constants → port faithfully (don't approximate; log any deliberate simplification
in `../deobf/DEOBFUSCATION_STATUS.md`). Example: conversations → `ConversationWindow` →
`sources/n0/k.java` (+ `l,m,n,o`).

## Regenerating (only if the APK changes)
`tools/decompile_full.sh "<ExiledKingdoms.apk>"` rewrites `sources/`. Deterministic for a
given APK + jadx version (1.5.5), so it normally does not need re-running.

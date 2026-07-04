# Method-name recovery (step 2 investigation)

## The finding that shapes everything

The APK's obfuscation is **mixed at the method level**. Inspecting real call
sites (e.g. `net/fdgames/assets/AnimationSet.java`) shows libGDX was ProGuarded
*together with the app*, not linked as a stock jar:

- Many public methods **kept** their names: `dispose`, `draw`, `setColor`,
  `getWidth`, `iterator`, `clear`, `get`, `equals`, `hashCode`.
- Others were **renamed** to single letters: `Array.add` → `.a`,
  `Array.addAll` → `.b`/`.c`/`.d`, `Array.contains` → `.e`.

So the class-name map (`deobf_map_full.json`) is necessary but **not
sufficient** to recompile against stock libGDX: the renamed method calls must
also be restored.

## What `method_map.json` is (and isn't)

`tools/method_match.py` matches each obfuscated libGDX class the game uses
against the **real libGDX 1.12.1 source** (`gdx-1.12.1-sources.jar`) by
(return type, arg count) + declaration order.

Result across the 15 used libGDX classes:
- 136 renamed methods, **97 recovered best-effort**, **74 flagged ambiguous**.

**This is a lead, not ground truth.** Positional matching mis-assigns overloaded
methods (e.g. it guessed `Array.c(3 args)`→`add` when it is really `addAll`).
Every entry needs verification against the call-site's actual behaviour. Treat
`method_map.json` as a ranked hint list for a human/assisted pass, not an
auto-apply table.

## The harder blocker: type-aware call-site rewriting

Even with a perfect method map, rewriting the game is not find-and-replace.
`foo.a(x)` means `add` only if `foo` is an `Array`; on another type `.a` is a
different method. Correctly rewriting `.a(` requires **resolving the static type
of every receiver** — a compiler-grade pass over ~265 decompiled classes. This,
not the name mapping, is the bulk of the remaining effort.

## Honest state of the port

| Piece | Status |
|---|---|
| Engine identified (libGDX) | ✅ done |
| Assets extracted (reusable) | ✅ done |
| Game source decompiled (`net.fdgames`, 185 files) | ✅ done |
| +81 relocated UI classes catalogued | ✅ done |
| Class-name de-obfuscation map (deterministic) | ✅ done |
| Method-name recovery | ◑ partial, ambiguous, needs verification |
| Type-aware call-site rewriting | ✗ not started (largest task) |
| jadx decompilation artifacts fixed | ✗ not started |
| Android platform/native layer → web equivalents | ✗ not started |
| Compiles / runs | ✗ not yet |

Getting from here to a running desktop build (then browser) is a multi-stage,
partly-manual reverse-engineering effort — realistically days-to-weeks of
iterative compile-and-fix, not a single automated pass. The value already
banked: full asset recovery + readable source + the deterministic name map that
makes that effort tractable.

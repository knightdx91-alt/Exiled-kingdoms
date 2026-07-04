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

## Improved recovery: two-signal matching (`method_map_hybrid.json`)

`tools/method_match_hybrid.py` + `tools/method_body_match.py` combine two
obfuscation-surviving signals to raise confidence and flag certainty per method:

1. **Parameter-type signature** — primitives, `java.*`, and kept-name gdx types
   survive obfuscation. A signature unique in the class (or an equal-size
   overload group matched positionally) gives a **certain** mapping regardless
   of body. Marked `certain: true`.
2. **Method-body similarity** — string/numeric literals and kept-name calls in a
   method body are preserved verbatim; strong for large methods, weak for tiny
   accessors. Used to disambiguate and validate.

Result over the 15 used libGDX classes: **54 / 143 renamed methods
high-confidence** (spot-checked correct: `Array.e→contains`, `.q→removeValue`,
`.g→pop`, `.k→peek`, `.a→add`). The remaining ~89 are flagged `certain: false`
(guesses needing verification) — e.g. same-return/void(int) collisions that the
matcher deliberately does **not** claim.

Known limits: libGDX generic types (`T`, `Array<T>`) collapse to `?` in the
signature, reducing discrimination; tiny 1-line accessors have too little body to
match. These are exactly the cases left for manual/assisted verification.

### The step that still gates the render tier
Even a perfect method map does not auto-rewrite call sites: `x.a(v)` → `x.add(v)`
only when `x` is statically an `Array`. That receiver-type resolution across
~250 classes remains the manual/assisted core of the render/UI grind.

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

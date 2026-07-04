# R8 optimization — the deeper obstacle

The APK was processed by **R8** (Android's optimizing compiler), not just
ProGuard renaming. Evidence: classes tagged `compiled from: R8$$SyntheticClass`,
e.g. `a/a.java` — a synthetic grab-bag of ~30 unrelated static helpers
(`a`, `b`, `c`, `y`, `o`, `u`…) called ~100× across the game.

## Why this matters for recovery

R8 does more than rename; it **restructures**:
- **Method outlining / sharing** — duplicated code is extracted into shared
  synthetic helpers. `a.a.c(i2,i3,i4)` decompiles to `zzbi.zzx(i2)+i3+i4`, which
  is *not* the original source — it's an R8-merged fragment. The original call
  sites had distinct, readable code that R8 collapsed.
- **Class merging** — separate original classes get fused; boundaries are lost.
- **Inlining** — small methods are dissolved into callers.

Consequence: for code touched by R8 restructuring, decompilation yields valid but
**semantically opaque** Java. Folding these classes in to satisfy the compiler is
possible, but reproducing correct *behaviour* sometimes requires reconstructing
what R8 merged — which is guesswork (e.g. the exact semantics of the
`zzbi.zzx(int)` billing-internal helper are not recoverable from the fragment).

## Rule for this project

**Do not commit speculative stubs for R8-synthetic helpers.** A wrong guess
compiles but silently corrupts game logic — worse than a compile error. The pure
data/logic model (the current green set) is unaffected by R8 restructuring and is
safe. The rendering/UI/map layer is where R8 damage and renamed-method calls
concentrate; that tier needs careful, verified reconstruction, not bulk stubbing.

This is the core reason a faithful running port is a large effort: it's not just
un-renaming, it's partially un-optimizing.

# Remaining blockers (after folding in the full game + symbol solver)

State: **75 / 370** recovered classes compile green against libGDX. The full game
(net.fdgames + render packages) is now in `port/core`, reproducible via
`tools/rebuild_core.sh`. The symbol solver renames ~193 obfuscated method calls
per run. What's left, in priority order:

## 1. `MainActivity` — referenced ~142× (biggest single blocker)
Game code calls `MainActivity` statics directly (not fully behind
`IPlatformResolver`). It imports Android APIs, so the pipeline drops it — which
cascades 142 unresolved references. **Fix: a stub `MainActivity`** in
`net.fdgames.ek.android` exposing the methods/fields the game calls, as no-ops /
web-equivalents. This is the highest-leverage next task.

## 2. R8 synthetic `a` — referenced ~257×
`a/a.java` (`R8$$SyntheticClass`) is a grab-bag of merged static helpers used
everywhere. It imports `com.google.android.gms.internal.play_billing.zzbi`, so it
was dropped. **Fix: include `a` and stub `zzbi.zzx(int)`** — BUT its semantics are
R8-opaque, so any stub is a guess. Recommended: include `a`, replace the single
`zzbi.zzx(i)` call with a documented best-guess (likely identity or a small hash)
and flag it for runtime verification. Do NOT ship it silently.

## 3. Platform / GMS surface — 34 distinct android/gms classes
Concentrated in the GPGS / IAP / activity files (already excluded). Needed only by
the platform layer, not core gameplay. **Fix: `port/stubs/` source dir** with
minimal stubs for the 34 classes (Quests, CredentialsApi, GoogleSignIn, Task,
android.util.Log, …) OR refactor those calls behind `IPlatformResolver`. For a
web build these become web/no-op implementations anyway.

## 4. Cascade from core classes (NPC, ExiledKingdoms, GameData, Settings, FDUtils)
These fail because they import android/gms (#1–#3) or reference each other. Most
resolve once #1–#3 are stubbed — they are not independently broken.

## 5. Residual (small, hand-verified)
- 6 `Vector2` calls still unmapped (`a#0`, `c#1`) — extend method map.
- ~87 `private access` errors — jadx over-restricted a few members; widen.
- ~70 override / ~9 incompatible-type — per-case.

## Sizing
#1–#3 are stub-generation tasks with a bounded surface (1 activity + 34 external
classes + 1 R8 helper). They should unblock a large fraction of the 253 failing
classes at once (the cascade in #4). The method-map loop (Route 1) continues to
run automatically on top. The genuinely irreducible manual work is small: the R8
`a` semantics (#2) and the per-case residue (#5).

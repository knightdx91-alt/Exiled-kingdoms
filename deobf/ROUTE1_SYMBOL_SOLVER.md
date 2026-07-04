# Route 1: type-aware method de-obfuscation (JavaParser + SymbolSolver)

The regex remapper cannot rename obfuscated method calls (`x.a(v)` → `x.add(v)`)
because it doesn't know the static type of `x`. Route 1 replaces it with a real
symbol-solver pass that does.

## How it works (`tools/javaparser/`)

`Remap.java` builds a `CombinedTypeSolver` over the JDK + **stock libGDX jar** +
the recovered source tree, parses every file, and for each obfuscated method call
resolves the receiver's static type. If the type is a libGDX class and
`(type, obfMethod, argCount)` is in `deobf/method_lookup.tsv`, it renames the
call. Files are saved with `LexicalPreservingPrinter` (no reformatting) and only
if successfully parsed (never corrupts an unparsed file).

Run: `tools/javaparser/run.sh <src-root> <gdx.jar>`

## Proven results

On the expanded tree (net.fdgames + render packages n0/o0/q0/l0/m0/k0/p0):
- **~90% of method-call receivers resolve** (9181 / 10175 scopes).
- Renames applied correctly where the method map has the entry.
- Green: 49 → 53 verified-compiling (limited only by map coverage, below).

## The iterative loop that finishes the render tier

`Remap.java` logs every **unmapped** obfuscated call by resolved libGDX type to
`deobf/unmapped_gdx_calls.tsv`. That is the exact, prioritized worklist:

```
38  com.badlogic.gdx.maps.MapProperties#a#1     (get / put)
38  com.badlogic.gdx.maps.MapProperties#b#1
28  com.badlogic.gdx.assets.AssetManager#d#1    (get / load / …)
25  com.badlogic.gdx.assets.AssetManager#r#2
…  Matrix4, Viewport, InputMultiplexer, Vector2, Array (residual overloads)
```

The loop: **run → read unmapped.tsv → body-match those classes (`tools/match_more.py`)
→ append to method_lookup.tsv → re-run.** Each pass shrinks the unmapped set and
lifts green. This is mechanical; the classes are open-source libGDX, matched by
body similarity + argument-count signature.

Key realization: the render tier calls obfuscated methods on libGDX classes whose
**class names were kept** (scene2d `Table`/`Label`, `AssetManager`, `MapProperties`,
`Matrix4`) — so they never appeared in the class-name map; only the symbol solver
surfaces them, via their resolved receiver type. That's why Route 1 is required.

## What remains after the loop converges

1. **R8 synthetic helpers** (`a.a` etc.) — semantically opaque; manual.
2. **Android platform refs** — `com.google.android.gms.*`, `android.view/content`
   in a few UI classes; stub or refactor behind `IPlatformResolver`.
3. **Private-access + ambiguous-overload residue** — small, hand-verified.

Route 1 turns the render tier from "impossible to mechanize" into a bounded,
tooled loop plus a small manual tail.

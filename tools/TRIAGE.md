# triage.py — dependency-aware compile triage

The single most useful tool for the de-obfuscation grind. It answers the question
I kept guessing at by hand: **"which class do I fix next to unblock the most?"**

## Why it exists

The green-closure metric is conservative: a fully-fixed class still counts as
failing if one class it references is broken. So error counts drop while "green"
holds, and it's invisible which failing classes are *roots* vs. *cascade victims*.
Turn after turn that cost time and caused wrong guesses. This tool makes it visible.

## Usage

```sh
# compile the whole tree, capture errors
javac -Xmaxerrs 99999 -d /tmp/out -cp "$GDX" @<all-sources> 2> errs.txt
python3 tools/triage.py port/core/src/main/java errs.txt --top 20
```

## What it reports

1. **Error categories** — the real shape of the work (find:variable / bad-args / …).
2. **Failing files grouped by dominant error** — batch-fixable cohorts
   ("21 files dominated by bad-args" → one systematic method-remap fix).
3. **Top named root classes by reference impact** — for distinctively-named
   classes it counts how many files mention them, so high-leverage roots surface:
   e.g. `GameString` (95 refs, **1 error**), `FDUtils` (60 refs, 1 error),
   `GameLevel` (98 refs, 4 errors). Fix these first.
4. **Cheapest fixes** — failing files with the fewest errors.

(Single-letter obfuscated class names are too ambiguous to reference-count, so
impact is only computed for names ≥3 chars; those are the meaningful roots anyway.)

## What it found immediately

- The high-impact roots (`GameString`, `GameLevel`, …) fail on `find:variable l` —
  a **systematic remapper gap**: `remap_gdx.py` rewrites the import
  `com.badlogic.gdx.utils.l` → real name but skips the body `l.` static access
  (its collision-safety rule ignores single-letter tokens). Worth a targeted,
  disambiguated pass — a small fix with outsized cascade impact.
- 21 files are dominated by `bad-args` — the method-obfuscation residue
  (wrong/absent renames); the method-map loop targets exactly these.

Run this at the start of every grind session to pick targets by leverage instead
of by guessing.

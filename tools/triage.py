#!/usr/bin/env python3
"""
Dependency-aware compile triage for the de-obfuscation grind.

From ONE javac run over the whole tree, it separates each failing file's
*own* (root) errors from *cascade* errors (missing symbols that only fail
because a dependency class is itself broken), then ranks failing classes by
how many downstream classes they would unblock.

Turns "which class do I fix next?" into a prioritized worklist:
  - ROOT TARGETS: few own errors, high unblock impact  -> fix these first
  - CASCADE-ONLY: zero own errors, waiting on a dependency -> free wins
  - Category summary of *root* errors only (the real work left)

Usage:
  javac ... 2> errs.txt          # compile the whole tree, high -Xmaxerrs
  python3 tools/triage.py <src-root> errs.txt [--top N]
"""
import os, re, sys, collections

def index(src_root):
    """Returns:
      simple2files: simple-name -> set(file)
      fqn2file:     'pkg.Class' -> file  (pkg from path, class from filename)
      file_imports: file -> {simple: fqn}
      file_dir:     file -> package dir (for same-package resolution)
    """
    simple2files = collections.defaultdict(set)
    fqn2file, file_imports, file_dir, tokens = {}, {}, {}, {}
    for r, _, fs in os.walk(src_root):
        for f in fs:
            if not f.endswith(".java"):
                continue
            path = os.path.join(r, f)
            simple = f[:-5]
            simple2files[simple].add(path)
            rel = os.path.relpath(path, src_root)[:-5]
            fqn2file[rel.replace("/", ".")] = path
            file_dir[path] = os.path.dirname(path)
            imps = {}
            try:
                txt = open(path, encoding="utf-8", errors="ignore").read()
            except OSError:
                txt = ""
            for m in re.finditer(r'\b(?:class|interface|enum)\s+([A-Za-z_]\w*)', txt):
                simple2files[m.group(1)].add(path)
            for m in re.finditer(r'^\s*import\s+([\w.]+);', txt, re.M):
                imps[m.group(1).split(".")[-1]] = m.group(1)
            file_imports[path] = imps
            tokens[path] = set(re.findall(r'[A-Za-z_]\w+', txt))
    return simple2files, fqn2file, file_imports, file_dir, tokens

def parse_errors(errs_path):
    """file -> list of (category, missing_symbol_or_None, location_or_None)"""
    lines = open(errs_path, encoding="utf-8", errors="ignore").read().splitlines()
    per = collections.defaultdict(list)
    i = 0
    while i < len(lines):
        m = re.match(r'(\S+\.java):(\d+): error: (.*)', lines[i])
        if not m:
            i += 1
            continue
        f, msg = m.group(1), m.group(3)
        ctx = "\n".join(lines[i:i+5])
        sym = loc = None
        cat = "other"
        if "cannot find symbol" in msg:
            sm = re.search(r'symbol:\s+(class|variable|method|interface)\s+([A-Za-z_][\w.]*)', ctx)
            lm = re.search(r'location:\s+(?:class|interface|package)\s+([\w.]+)', ctx)
            if sm:
                kind, sym = sm.group(1), sm.group(2)
                cat = "find:" + kind
            if lm:
                loc = lm.group(1)
        elif "cannot be applied" in msg or "no suitable method" in msg:
            cat = "bad-args"
        elif "does not override" in msg:
            cat = "override"
        elif "incompatible types" in msg:
            cat = "incompatible"
        elif "has private access" in msg:
            cat = "private"
        elif "cannot access" in msg:
            cat = "cannot-access"; sym = msg.split()[-1]
        elif "inherit from final" in msg:
            cat = "final"
        per[f].append((cat, sym, loc))
        i += 1
    return per

def main():
    src = sys.argv[1]; errs = sys.argv[2]
    top = 25
    if "--top" in sys.argv:
        top = int(sys.argv[sys.argv.index("--top")+1])

    simple2files, fqn2file, file_imports, file_dir, tokens = index(src)
    per = parse_errors(errs)
    failing = set(per)
    rel = lambda p: p.split("/java/")[-1] if "/java/" in p else p

    # one-pass reference impact: how many OTHER files mention a class's simple name.
    # Only meaningful for distinctive names (len>=3); single-letter obf names are
    # too noisy to count, so they're reported separately.
    ref_count = collections.Counter()
    for f, toks in tokens.items():
        for t in toks:
            ref_count[t] += 1
    def impact(f):
        name = os.path.basename(f)[:-5]
        return ref_count.get(name, 0) if len(name) >= 3 else -1  # -1 = unmeasured

    err_count = {f: len(es) for f, es in per.items()}
    dom_cat = {f: collections.Counter(c for c, _, _ in es).most_common(1)[0][0]
               for f, es in per.items()}

    cats = collections.Counter()
    for f, es in per.items():
        for c, _, _ in es:
            cats[c] += 1

    print(f"failing files: {len(failing)}   total errors: {sum(err_count.values())}\n")

    print("== ERROR CATEGORIES ==")
    for c, n in cats.most_common():
        print(f"  {n:4d}  {c}")

    print("\n== FAILING FILES GROUPED BY DOMINANT ERROR (batch-fixable) ==")
    by_dom = collections.Counter(dom_cat.values())
    for c, n in by_dom.most_common():
        print(f"  {n:4d} files  dominated by  {c}")

    named = [(impact(f), err_count[f], f) for f in failing if impact(f) >= 0]
    named.sort(key=lambda x: (-x[0], x[1]))
    print(f"\n== TOP {top} NAMED ROOT CLASSES BY REFERENCE IMPACT "
          f"(fix these -> most cascade clears) ==")
    print(f"  {'refs':>5}  {'errs':>4}  {'dominant':<14} file")
    for imp, ec, f in named[:top]:
        print(f"  {imp:5d}  {ec:4d}  {dom_cat[f]:<14} {rel(f)}")

    cheap = sorted(failing, key=lambda f: err_count[f])
    print(f"\n== CHEAPEST FIXES (fewest errors) ==")
    for f in cheap[:top]:
        print(f"  {err_count[f]:3d} errs  {dom_cat[f]:<14} {rel(f)}")

if __name__ == "__main__":
    main()

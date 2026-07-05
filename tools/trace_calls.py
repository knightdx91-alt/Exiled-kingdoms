#!/usr/bin/env python3
"""Trace the call graph of an obfuscated EK class through the decompiled tree.

Point it at a class (obfuscated path like `k0/a`, or an original name like
`GameLevelRenderer`) and it reports:
  - DOWN  : the classes this one references (its callees / collaborators)
  - UP    : the classes that reference this one (its callers)
resolving every hit to its original name. This maps a subsystem before you reverse it.

Class-level, not member-level: R8 renames members (`a()`, `f2278e`) and reuses those
names across classes, so member greps are ambiguous. Fully-qualified `pkg.class`
references are unique, so the class graph is trustworthy. Use --members to also list
the (noisy) member tokens accessed on each referenced class.

Usage:
  tools/trace_calls.py k0/a                 # trace GameLevelRenderer up & down
  tools/trace_calls.py GameMap --ek         # only EK-owned refs (hide libraries)
  tools/trace_calls.py m0/b --down --depth 2 # recurse callees two levels
  tools/trace_calls.py ConversationWindow --members

Options:
  --src DIR    decompiled sources root (default: decompiled/sources)
  --map FILE   CLASS_MAP.tsv for owner/original-name (default: deobf/CLASS_MAP.tsv)
  --ek         restrict output to EK-owned classes (drop libGDX/AndroidX/etc.)
  --up/--down  show only one direction (default: both)
  --depth N    recurse the DOWN graph N levels (default 1)
  --members    also show member tokens (.foo, .bar()) accessed on each callee
"""
import argparse, os, re, sys
from collections import defaultdict

def load_classmap(path):
    """key 'pkg.class' -> (orig_name, owner); plus orig_name -> [keys]."""
    origin, owner, byname = {}, {}, defaultdict(list)
    if not os.path.exists(path):
        return origin, owner, byname
    for line in open(path, encoding="utf-8").read().splitlines()[1:]:
        parts = line.split("\t")
        if len(parts) < 3:
            continue
        p, name, own = parts[0], parts[1], parts[2]
        key = p.replace(".java", "").replace("/", ".")
        origin[key] = name.replace(".java", "")
        owner[key] = own
        byname[name.replace(".java", "").lower()].append(key)
    return origin, owner, byname

def index_tree(src):
    """Every decompiled class: key 'pkg.class' -> abs path. Also read compiled-from."""
    key2path, compiled = {}, {}
    for root, _, files in os.walk(src):
        for fn in files:
            if not fn.endswith(".java"):
                continue
            ap = os.path.join(root, fn)
            key = os.path.relpath(ap, src)[:-5].replace(os.sep, ".")
            key2path[key] = ap
            m = re.search(r"compiled from:\s*([A-Za-z0-9_$]+)\.java", open(ap, encoding="utf-8", errors="ignore").read(4000))
            if m:
                compiled[key] = m.group(1)
    return key2path, compiled

def resolve_target(tgt, key2path, byname, compiled):
    """Accept 'k0/a', 'k0.a', 'k0/a.java', or an original class name.
    For a name mapping to several files (inner classes), pick the largest (the main
    class body) and return the siblings too."""
    k = tgt.replace(".java", "").replace("/", ".")
    if k in key2path:
        return k, []
    hits = list(dict.fromkeys(byname.get(tgt.lower(), [])))
    if not hits:
        hits = [key for key, name in compiled.items() if name.lower() == tgt.lower()]
    hits = [h for h in hits if h in key2path]
    if not hits:
        return None, []
    hits.sort(key=lambda h: os.path.getsize(key2path[h]), reverse=True)
    return hits[0], hits[1:]

# pkg.class reference tokens: single-letter/obf packages (a, k0, m0…) and net.fdgames.*
REF_RE = re.compile(r"\b((?:[a-z][a-z0-9]{0,2}\.)+[A-Za-z][A-Za-z0-9_]*)\b")

def refs_in(text, key2path):
    """Distinct 'pkg.class' keys referenced in text that are real classes in the tree.
    Single-letter packages (a, b, …) collide with local variable names, so a ref there
    only counts if the class is explicitly imported."""
    imports = {i for i in re.findall(r"\bimport\s+([\w.]+)\s*;", text) if i in key2path}
    found = defaultdict(int)
    for m in REF_RE.finditer(text):
        parts = m.group(1).split(".")
        for cut in range(len(parts), 1, -1):
            key = ".".join(parts[:cut])
            if key in key2path:
                pkg_last = parts[cut - 2]          # last segment of the package
                if len(pkg_last) == 1 and key not in imports:
                    break                          # ambiguous local var, skip
                found[key] += 1
                break
    for key in imports:                            # ensure imported classes always show
        found.setdefault(key, found.get(key, 0) or 1)
    return found

def members_on(text, key):
    """Heuristic: member tokens accessed right after a reference to `key`."""
    out = set()
    for m in re.finditer(re.escape(key) + r"\.([a-zA-Z_][A-Za-z0-9_]*)(\(?)", text):
        out.add(m.group(1) + ("()" if m.group(2) else ""))
    return sorted(out)[:12]

def label(key, origin, compiled, owner):
    name = origin.get(key) or compiled.get(key) or key.split(".")[-1]
    own = owner.get(key, "")
    tag = f"  [{own}]" if own and own != "EK" else ""
    return f"{key:14s} {name}{tag}"

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("target")
    ap.add_argument("--src", default="decompiled/sources")
    ap.add_argument("--map", default="deobf/CLASS_MAP.tsv")
    ap.add_argument("--ek", action="store_true")
    ap.add_argument("--up", action="store_true")
    ap.add_argument("--down", action="store_true")
    ap.add_argument("--depth", type=int, default=1)
    ap.add_argument("--members", action="store_true")
    a = ap.parse_args()
    both = not (a.up or a.down)

    origin, owner, byname = load_classmap(a.map)
    key2path, compiled = index_tree(a.src)
    # let compiled-from names resolve targets too
    for key, name in compiled.items():
        byname.setdefault(name.lower(), []).append(key)

    tgt, siblings = resolve_target(a.target, key2path, byname, compiled)
    if not tgt:
        sys.exit(f"could not resolve target: {a.target}")

    def is_shown(key):
        ek = owner.get(key) == "EK" or key.startswith("net.fdgames.")
        return (not a.ek) or ek

    print(f"TARGET  {label(tgt, origin, compiled, owner)}   ({key2path[tgt]})")
    if siblings:
        print(f"  (same class in: {', '.join(siblings)})")
    print()

    # DOWN (callees), optionally recursive
    if both or a.down:
        print("── DOWN — classes this references (callees) " + "─" * 24)
        seen = set()
        frontier = [(tgt, 0)]
        while frontier:
            cur, d = frontier.pop(0)
            if cur in seen or d >= a.depth:
                continue
            seen.add(cur)
            text = open(key2path[cur], encoding="utf-8", errors="ignore").read()
            found = refs_in(text, key2path)
            indent = "  " * (d + 1)
            for key, n in sorted(found.items(), key=lambda kv: -kv[1]):
                if key == cur or not is_shown(key):
                    continue
                mem = ("  {" + ", ".join(members_on(text, key)) + "}") if a.members else ""
                print(f"{indent}{label(key, origin, compiled, owner)}  ×{n}{mem}")
                if d + 1 < a.depth:
                    frontier.append((key, d + 1))
        print()

    # UP (callers): who references the target key anywhere in the tree
    if both or a.up:
        print("── UP — classes that reference this (callers) " + "─" * 22)
        callers = []
        for key, path in key2path.items():
            if key == tgt:
                continue
            text = open(path, encoding="utf-8", errors="ignore").read()
            n = text.count(tgt + ".") + len(re.findall(r"\bimport\s+" + re.escape(tgt) + r"\b", text))
            if n and is_shown(key):
                callers.append((key, n))
        for key, n in sorted(callers, key=lambda kv: -kv[1]):
            print(f"  {label(key, origin, compiled, owner)}  ×{n}")
        if not callers:
            print("  (none found)")

if __name__ == "__main__":
    main()

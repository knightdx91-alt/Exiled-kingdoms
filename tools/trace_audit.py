#!/usr/bin/env python3
"""Build the full class call-graph once and run consistency checks over EVERY file.

Reuses trace_calls' indexing + reference extraction, computes outgoing refs for all
3210 classes in one pass, inverts for callers, and flags anomalies that would mean
something is wrong (residual single-letter collisions, misclassifications, isolated
EK classes, or a library appearing to call EK code — which never happens for real).

Writes the EK/net.fdgames edge list to deobf/CALL_GRAPH.tsv as a reusable map.

Usage: tools/trace_audit.py [--src DIR] [--map FILE]
"""
import argparse, os, re, sys
from collections import defaultdict
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from trace_calls import load_classmap, index_tree, refs_in

def is_ek(key, owner):
    return owner.get(key) == "EK" or key.startswith("net.fdgames.")

def is_lib(key, owner):
    o = owner.get(key, "")
    return o in ("libGDX", "box2dlights", "AndroidX/support", "gdx-pay/billing",
                 "platform/backend") or key.startswith(("com.", "android.", "androidx."))

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--src", default="decompiled/sources")
    ap.add_argument("--map", default="deobf/CLASS_MAP.tsv")
    a = ap.parse_args()

    origin, owner, byname = load_classmap(a.map)
    key2path, compiled = index_tree(a.src)
    name = lambda k: origin.get(k) or compiled.get(k) or k.split(".")[-1]

    # one pass: outgoing edges for every class
    down = {}
    for key, path in key2path.items():
        text = open(path, encoding="utf-8", errors="ignore").read()
        down[key] = {r: n for r, n in refs_in(text, key2path, key).items() if r != key}
    up = defaultdict(dict)
    for src, dsts in down.items():
        for dst, n in dsts.items():
            up[dst][src] = n

    total_edges = sum(len(v) for v in down.values())
    print(f"classes indexed: {len(key2path)}   edges: {total_edges}\n")

    # --- checks -----------------------------------------------------------------
    # 1) every CLASS_MAP (292 obfuscated) file is present in the tree
    missing = [k for k in owner if k not in key2path]
    print(f"[1] CLASS_MAP files missing from tree: {len(missing)} {missing[:5]}")

    # 2) library class that references EK code — a real library never does; a hit
    #    means a misclassified 'library' (actually EK) or a bad edge.
    lib_to_ek = []
    for src, dsts in down.items():
        if is_lib(src, owner):
            eks = [d for d in dsts if is_ek(d, owner)]
            if eks:
                lib_to_ek.append((src, eks))
    print(f"[2] library->EK edges (should be ~0): {len(lib_to_ek)}")
    for s, eks in lib_to_ek[:12]:
        print(f"      {s} ({name(s)}) -> {[name(e) for e in eks][:4]}")

    # 3) residual single-letter collision: obfuscated target with an absurd caller count
    hot = sorted(((k, len(up.get(k, {}))) for k in owner if is_ek(k, owner)),
                 key=lambda x: -x[1])[:8]
    print(f"[3] EK classes by caller count (top; sanity — no wild outliers):")
    for k, c in hot:
        print(f"      {c:4d}  {k:8s} {name(k)}")

    # 4) EK classes with zero edges in AND out (isolated — possibly dead/mislabeled)
    isolated = [k for k in owner if is_ek(k, owner) and not down.get(k) and not up.get(k)]
    print(f"[4] isolated EK classes (0 in, 0 out): {len(isolated)} "
          f"{[f'{k}={name(k)}' for k in isolated[:8]]}")

    # 5) owner sanity: every obfuscated file resolved to an owner
    unresolved = [k for k in key2path if k in owner and owner[k] in ("", "?")]
    print(f"[5] obfuscated files with no owner: {len(unresolved)} {unresolved[:5]}")

    # --- write the EK/net edge list as a reusable map ---------------------------
    out = "deobf/CALL_GRAPH.tsv"
    with open(out, "w") as f:
        f.write("from_key\tfrom_name\tto_key\tto_name\tcount\n")
        for src, dsts in sorted(down.items()):
            if not is_ek(src, owner):
                continue
            for dst, n in sorted(dsts.items(), key=lambda kv: -kv[1]):
                if is_ek(dst, owner):
                    f.write(f"{src}\t{name(src)}\t{dst}\t{name(dst)}\t{n}\n")
    ek_edges = sum(1 for s in down if is_ek(s, owner)
                   for d in down[s] if is_ek(d, owner))
    print(f"\nwrote {out}: {ek_edges} EK↔EK/net edges")

if __name__ == "__main__":
    main()

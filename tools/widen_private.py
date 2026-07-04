#!/usr/bin/env python3
"""Widen jadx-over-restricted 'private' members to 'public' in game code.
Decompilation split original same-class access across obfuscated classes;
jadx kept members 'private', breaking legal cross-class access. Widening is
safe for getting decompiled code to compile (only broadens visibility)."""
import os,re,sys
D=sys.argv[1]
n=0
for r,_,fs in os.walk(D):
    for f in fs:
        if not f.endswith('.java'): continue
        p=os.path.join(r,f); s=open(p,encoding='utf-8',errors='ignore').read()
        # widen member modifiers only (not classes handled the same; harmless)
        s2=re.sub(r'\bprivate\b','public',s)
        if s2!=s: open(p,'w').write(s2); n+=1
print(f"widened private->public in {n} files")

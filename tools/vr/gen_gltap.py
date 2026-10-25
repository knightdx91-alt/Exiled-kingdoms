#!/usr/bin/env python3
"""Generate vr/common/src/ekvr/GlTap.java: a pass-through of the APK's GL20 interface.
Usage: gen_gltap.py <game.jar>   (the dex2jar'd hero-v78 classes.dex)"""
import re, subprocess, sys
sigs = [l for l in subprocess.run(['javap', '-cp', sys.argv[1], 'com.badlogic.gdx.graphics.GL20'],
                                  capture_output=True, text=True).stdout.split('\n') if 'abstract' in l]
out = []
for s in sigs:
    ret, name, args = re.match(r'\s*public abstract (\S+) (\w+)\((.*)\);', s).groups()
    types = [a.strip() for a in args.split(',')] if args.strip() else []
    params = ', '.join(f'{t} a{i}' for i, t in enumerate(types))
    call = ', '.join(f'a{i}' for i in range(len(types)))
    if name == 'glClearColor':
        body = 'if (transparentClear) { gl.glClearColor(0f, 0f, 0f, 0f); return; } gl.glClearColor(%s);' % call
    else:
        body = ('return ' if ret != 'void' else '') + f'gl.{name}({call});'
    out.append(f'    public {ret} {name}({params}) {{ {body} }}')
src = '''package ekvr;

/**
 * Pass-through GL20 (generated from the APK's GL20 interface by tools/vr/gen_gltap.py).
 * While {@link #transparentClear} is set, every glClearColor clears to transparent black, so
 * the game's own frame (cleared opaque by GameLevelRenderer) becomes a UI layer with holes
 * where the map was: the map itself is drawn in 3D from the recorded batch.
 */
public class GlTap implements com.badlogic.gdx.graphics.GL20 {
    public final com.badlogic.gdx.graphics.GL20 gl;
    public static boolean transparentClear;
    public GlTap(com.badlogic.gdx.graphics.GL20 gl) { this.gl = gl; }
''' + '\n'.join(out) + '\n}\n'
open(__import__('os').path.join(__import__('os').path.dirname(__file__), '../../vr/common/src/ekvr/GlTap.java'), 'w').write(src)

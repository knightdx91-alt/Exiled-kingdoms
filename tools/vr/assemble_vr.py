#!/usr/bin/env python3
"""Deterministically build the unsigned Quest VR APK from a base mod APK + the VR overlay.

    assemble_vr.py <base.apk> <vr-overlay.zip> <out-unsigned.apk>

The overlay (made by tools/build_vr.sh, committed in dist/quest-vr-vN/) holds only what
the VR build changes: AndroidManifest.xml, classes.dex (MainActivity -> Vr.wrap),
classes2.dex (the VR layer) and lib/arm64-v8a/{libekxr,libopenxr_loader}.so. Every other
entry is copied from the base APK with its own compression; old v1 signature files are
dropped. Entry dates are fixed, so the same inputs always give the same bytes (the Pages
deploy rebuilds it and the result matches sha256.txt after signing).
"""
import sys
import zipfile

base, overlay, out = sys.argv[1:4]
src = zipfile.ZipFile(base)
ov = zipfile.ZipFile(overlay)
new = {i.filename: ov.read(i.filename) for i in ov.infolist() if not i.is_dir()}
dst = zipfile.ZipFile(out, 'w')
seen = set()
for info in src.infolist():
    n = info.filename
    if n.startswith('META-INF/') and n.rsplit('.', 1)[-1] in ('SF', 'RSA', 'DSA', 'EC', 'MF'):
        continue
    data = new[n] if n in new else src.read(n)
    zi = zipfile.ZipInfo(n, date_time=info.date_time)
    zi.compress_type = info.compress_type
    zi.external_attr = info.external_attr
    dst.writestr(zi, data)
    seen.add(n)
for n in sorted(new):
    if n in seen:
        continue
    zi = zipfile.ZipInfo(n, date_time=(1980, 1, 1, 0, 0, 0))
    zi.compress_type = zipfile.ZIP_DEFLATED
    dst.writestr(zi, new[n])
dst.close()
print(f'{out}: base {base} + {len(new)} overlay entries')

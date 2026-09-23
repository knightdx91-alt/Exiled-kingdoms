#!/usr/bin/env python3
"""Rewrite an (unsigned) APK so releases deduplicate in git.

Usage: zip_stable_order.py <in.apk> <out.apk> <volatile-list.txt>

dist/ stores each release as 25 MB split parts. Normally every release changes every part, because
classes.dex and the manifest sit near the front and shift everything behind them. Here the entries
that never change between releases (base assets, the MP content pack, native libs) are written FIRST,
sorted by name, with a fixed timestamp and no extra fields; the entries a release changes
(classes.dex, AndroidManifest.xml, the text files our patches edit — listed in <volatile-list.txt>,
plus anything under META-INF) go LAST. The stable prefix is then byte-identical from release to
release, so every split part inside it is the same blob and git stores it once.

Entry data is copied raw (no recompression), so contents, CRCs and compression are untouched; only
order, timestamps and extra fields change. Signing (apksig) runs afterwards and keeps this order.
"""
import struct, sys

src, dst, vol_list = sys.argv[1:4]
volatile = {l.strip() for l in open(vol_list) if l.strip() and not l.startswith('#')}
d = open(src, 'rb').read()

eocd = d.rfind(b'PK\x05\x06')
assert eocd >= 0, "no end of central directory"
n_total, cd_size, cd_off = struct.unpack_from('<HII', d, eocd + 10)
assert n_total != 0xFFFF and cd_off != 0xFFFFFFFF, "zip64 not supported"

entries = []
o = cd_off
for _ in range(n_total):
    assert d[o:o + 4] == b'PK\x01\x02'
    (ver_made, ver_need, flags, method, _t, _dt, crc, csize, usize, nlen, xlen, clen,
     _disk, _iattr, _eattr, lho) = struct.unpack_from('<HHHHHHIIIHHHHHII', d, o + 4)
    name = d[o + 46:o + 46 + nlen]
    assert d[lho:lho + 4] == b'PK\x03\x04', name
    lnlen, lxlen = struct.unpack_from('<HH', d, lho + 26)
    start = lho + 30 + lnlen + lxlen
    entries.append(dict(name=name, ver_need=ver_need, flags=flags & ~0x0008, method=method, crc=crc,
                        csize=csize, usize=usize, data=d[start:start + csize]))
    o += 46 + nlen + xlen + clen

def is_volatile(e):
    n = e['name'].decode('utf-8', 'replace')
    return n in volatile or n.startswith('META-INF/')

seen = set()
for e in entries:
    assert e['name'] not in seen, f"duplicate entry {e['name']}"
    seen.add(e['name'])
entries.sort(key=lambda e: (is_volatile(e), e['name']))

DOS_TIME, DOS_DATE = 0, (0 << 9) | (1 << 5) | 1   # 1980-01-01 00:00
out = bytearray()
cd = bytearray()
for e in entries:
    off = len(out)
    out += struct.pack('<IHHHHHIIIHH', 0x04034b50, e['ver_need'], e['flags'], e['method'], DOS_TIME,
                       DOS_DATE, e['crc'], e['csize'], e['usize'], len(e['name']), 0)
    out += e['name'] + e['data']
    cd += struct.pack('<IHHHHHHIIIHHHHHII', 0x02014b50, 20, e['ver_need'], e['flags'], e['method'],
                      DOS_TIME, DOS_DATE, e['crc'], e['csize'], e['usize'], len(e['name']), 0, 0, 0, 0,
                      0, off)
    cd += e['name']
cd_start = len(out)
out += cd
out += struct.pack('<IHHHHIIH', 0x06054b50, 0, 0, len(entries), len(entries), len(cd), cd_start, 0)
open(dst, 'wb').write(out)
stable = sum(1 for e in entries if not is_volatile(e))
first_vol = next((i for i, e in enumerate(entries) if is_volatile(e)), len(entries))
vol_off = 0
for i, e in enumerate(entries):
    if i == first_vol:
        break
    vol_off += 30 + len(e['name']) + len(e['data'])
print(f"stable order: {stable} stable entries first ({vol_off / 1048576:.1f} MB), "
      f"{len(entries) - stable} volatile last")

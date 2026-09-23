#!/usr/bin/env python3
"""Add an <activity> to a binary AndroidManifest.xml (AXML), resources.arsc-free.

Clones the START tag of an existing template activity (default: the launcher
net.fdgames.ek.android.MainActivity -- same label, orientation and configChanges, which is
exactly how the MP mod declared its LanLobbyActivity), points android:name at the new class
and sets android:exported=false. The clone gets NO children (no intent-filter), so it is not a
launcher entry. Inserted right after the template's END tag. Idempotent.

Usage: axml_add_activity.py <in> <out> <new.class.Name> [template.class.Name]
"""
import struct, sys
from axml_add_perms import parse_pool, build_pool, u16, u32


def attr_base(d, o):
    return o + 16 + u16(d, o + 24)


def main():
    src, dst, new_name = sys.argv[1:4]
    tmpl = sys.argv[4] if len(sys.argv) > 4 else 'net.fdgames.ek.android.MainActivity'
    d = open(src, 'rb').read()
    assert u32(d, 0) == 0x00080003, "not AXML"
    sp_off, sp_size, flags, S = parse_pool(d)
    if new_name in S:
        open(dst, 'wb').write(d)
        print(f"{new_name} already declared; copied unchanged")
        return
    pool_end = sp_off + sp_size
    assert u16(d, pool_end) == 0x0180, "resource map expected"
    o = pool_end + u32(d, pool_end + 4)
    name_i, exp_i, act_i = S.index('name'), S.index('exported'), S.index('activity')
    tmpl_i = S.index(tmpl)

    start = None
    depth = 0
    insert_at = None
    while o < len(d):
        t, sz = u16(d, o), u32(d, o + 4)
        if t == 0x0102:
            if start is None and u32(d, o + 20) == act_i:
                ab, ac = attr_base(d, o), u16(d, o + 28)
                for k in range(ac):
                    a = ab + 20 * k
                    if u32(d, a + 4) == name_i and u32(d, a + 8) == tmpl_i:
                        start = (o, sz)
                        depth = 0
                        break
            elif start is not None:
                depth += 1
        elif t == 0x0103 and start is not None:
            if depth == 0:
                insert_at = o + sz
                end_blk = d[o:o + sz]
                break
            depth -= 1
        o += sz
    assert start and insert_at, f"template activity {tmpl} not found"

    new_idx = len(S)
    blk = bytearray(d[start[0]:start[0] + start[1]])
    ab, ac = attr_base(blk, 0), u16(blk, 28)
    got_name = False
    for k in range(ac):
        a = ab + 20 * k
        an = u32(blk, a + 4)
        if an == name_i:
            struct.pack_into('<I', blk, a + 8, new_idx)     # rawValue
            struct.pack_into('<I', blk, a + 16, new_idx)    # typed data (string)
            got_name = True
        elif an == exp_i:
            struct.pack_into('<I', blk, a + 16, 0)          # exported = false
    assert got_name
    # the END tag names the element only (ns + name): reuse the template's
    new_pool = build_pool(S + [new_name], flags)
    body = new_pool + d[pool_end:insert_at] + bytes(blk) + end_blk + d[insert_at:]
    out = bytearray(struct.pack('<II', 0x00080003, 8 + len(body)) + body)
    struct.pack_into('<I', out, 4, len(out))
    open(dst, 'wb').write(out)
    print(f"added <activity {new_name}> (clone of {tmpl}, exported=false)  ({len(d)} -> {len(out)} bytes)")


if __name__ == '__main__':
    main()

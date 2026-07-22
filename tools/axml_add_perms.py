#!/usr/bin/env python3
"""Add <uses-permission> entries to a binary AndroidManifest.xml (AXML) and/or
lower minSdkVersion.

Surgical, resources.arsc-free: only the manifest is rewritten. Appends the
permission value strings to the string pool and clones existing
<uses-permission> START/END chunks pointing at the new strings. Idempotent —
permissions already present are skipped. minSdkVersion is a fixed-size in-place
integer edit (only lowered, never raised).

Usage: axml_add_perms.py <in> <out> [--min-sdk N] [PERM ...]
Default PERMs: WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
"""
import struct, sys

def u16(d, o): return struct.unpack_from('<H', d, o)[0]
def u32(d, o): return struct.unpack_from('<I', d, o)[0]

def parse_pool(d):
    assert u16(d, 8) == 0x0001, "string pool not first chunk"
    sp_off = 8
    sp_size = u32(d, sp_off + 4)
    count = u32(d, sp_off + 8)
    style_count = u32(d, sp_off + 12)
    flags = u32(d, sp_off + 16)
    str_start = u32(d, sp_off + 20)
    style_start = u32(d, sp_off + 24)
    assert style_count == 0 and style_start == 0, "styled pools unsupported"
    assert not (flags & 0x100), "UTF-8 pools unsupported"
    offs = [u32(d, sp_off + 28 + 4 * i) for i in range(count)]
    data_base = sp_off + str_start
    strings = []
    for o in offs:
        p = data_base + o
        n = u16(d, p)
        strings.append(d[p + 2:p + 2 + 2 * n].decode('utf-16-le'))
    return sp_off, sp_size, flags, strings

def enc_str(s):
    b = s.encode('utf-16-le')
    return struct.pack('<H', len(s)) + b + b'\x00\x00'

def build_pool(strings, flags):
    # offsets array + concatenated string data (UTF-16), chunk padded to 4
    data = b''
    offs = []
    for s in strings:
        offs.append(len(data))
        data += enc_str(s)
    off_arr = b''.join(struct.pack('<I', o) for o in offs)
    str_start = 28 + len(off_arr)          # header(28) + offsets
    body = off_arr + data
    total = 28 + len(body)
    pad = (-total) % 4
    body += b'\x00' * pad
    total += pad
    hdr = struct.pack('<HHIIIIII', 0x0001, 28, total, len(strings), 0, flags, str_start, 0)
    return hdr + body

def find_use_perm(d, pool_end):
    """Return (start_off, block_bytes_len covering START+END) for first uses-permission."""
    # resource map chunk right after pool
    o = pool_end
    assert u16(d, o) == 0x0180, "resource map expected"
    o += u32(d, o + 4)
    _, _, _, strings = parse_pool(d)
    up_idx = strings.index('uses-permission')
    while o < len(d):
        t = u16(d, o); sz = u32(d, o + 4)
        if t == 0x0102 and u32(d, o + 20) == up_idx:
            end = o + sz                       # START end
            assert u16(d, end) == 0x0103        # END tag
            end_sz = u32(d, end + 4)
            return o, sz, end, end_sz
        o += sz
    raise SystemExit("no existing uses-permission to clone")

def set_attr_string(block, str_idx):
    """block = 56-byte START; set rawValue(+44) and data(+52) to str_idx."""
    b = bytearray(block)
    struct.pack_into('<I', b, 44, str_idx)
    struct.pack_into('<I', b, 52, str_idx)
    return bytes(b)

def lower_min_sdk(d, target):
    """In-place (same length): set <uses-sdk> minSdkVersion attr down to target.
    Returns (new_bytes, old_value_or_None)."""
    d = bytearray(d)
    sp_off, sp_size, flags, strings = parse_pool(d)
    try:
        ms_idx = strings.index('minSdkVersion')
    except ValueError:
        return bytes(d), None
    o = sp_off + sp_size
    assert u16(d, o) == 0x0180
    o += u32(d, o + 4)
    while o < len(d):
        t = u16(d, o); sz = u32(d, o + 4)
        if t == 0x0102:                                   # START element
            hs = u16(d, o + 2); ac = u16(d, o + 28)
            ao = o + hs
            for _ in range(ac):
                if u32(d, ao + 4) == ms_idx:              # attribute name == minSdkVersion
                    old = u32(d, ao + 16)
                    if old > target:
                        struct.pack_into('<I', d, ao + 16, target)  # typed data
                        if u32(d, ao + 8) != 0xffffffff:            # rawValue (if a string) stays
                            pass
                    return bytes(d), old
                ao += 20
        o += sz
    return bytes(d), None


def main():
    argv = sys.argv[1:]
    min_sdk = None
    if '--min-sdk' in argv:
        i = argv.index('--min-sdk'); min_sdk = int(argv[i + 1]); del argv[i:i + 2]
    src, dst = argv[0], argv[1]
    perms = argv[2:] or [
        'android.permission.WRITE_EXTERNAL_STORAGE',
        'android.permission.READ_EXTERNAL_STORAGE',
    ]
    d = open(src, 'rb').read()
    assert u32(d, 0) == 0x00080003, "not AXML"

    if min_sdk is not None:
        d, old = lower_min_sdk(d, min_sdk)
        if old is None:
            print("minSdkVersion attribute not found; left unchanged")
        elif old > min_sdk:
            print(f"minSdkVersion {old} -> {min_sdk}")
        else:
            print(f"minSdkVersion already {old} (<= {min_sdk}); unchanged")

    sp_off, sp_size, flags, strings = parse_pool(d)
    pool_end = sp_off + sp_size

    to_add = [p for p in perms if p not in strings]
    if not to_add:
        open(dst, 'wb').write(d)
        print("all permissions already present; copied unchanged")
        return

    up_start, up_len, up_end, up_end_len = find_use_perm(d, pool_end)
    start_tmpl = d[up_start:up_start + up_len]
    end_tmpl = d[up_end:up_end + up_end_len]
    assert up_len == 56 and up_end_len == 24, (up_len, up_end_len)

    new_strings = strings + to_add
    new_pool = build_pool(new_strings, flags)

    new_elems = b''
    for i, p in enumerate(to_add):
        idx = len(strings) + i
        new_elems += set_attr_string(start_tmpl, idx) + end_tmpl

    insert_at = up_end + up_end_len            # after first uses-permission's END
    body = (new_pool
            + d[pool_end:insert_at]
            + new_elems
            + d[insert_at:])
    out = bytearray(struct.pack('<II', 0x00080003, 8 + len(body)) + body)
    struct.pack_into('<I', out, 4, len(out))
    open(dst, 'wb').write(out)
    print(f"added: {', '.join(to_add)}  ({len(d)} -> {len(out)} bytes)")

if __name__ == '__main__':
    main()

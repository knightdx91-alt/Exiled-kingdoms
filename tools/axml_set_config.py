#!/usr/bin/env python3
"""Set an <activity>'s android:configChanges in a binary AndroidManifest.xml.

Why this exists: on a foldable, unfolding changes screenLayout + smallestScreenSize +
screenSize at once. EK's MainActivity declares only
keyboardHidden|orientation|screenSize (0x4a0), so the activity is destroyed and
recreated on every fold -- libGDX restarts the game at the title screen. Widening
configChanges keeps the activity alive and turns the fold into a plain GL resize.

The edit is a 4-byte, fixed-size, in-place write of the attribute's typed value:
no chunk sizes move, no string pool changes, resources.arsc is never touched --
the same surgical rule the rest of the pipeline follows (see CONTINUE_HERE.md,
"DO NOT reintroduce apktool b").

Usage:
  axml_set_config.py <in> <out> --activity net.fdgames.ek.android.MainActivity \
                                --config-changes 0x40003ffc
  axml_set_config.py <in> --print         # dump every activity's configChanges
"""
import struct, sys

ATTR_NAME = 0x01010003
ATTR_CONFIG_CHANGES = 0x0101001f
TYPE_INT_HEX = 0x11
TYPE_INT_DEC = 0x10

# android:configChanges bit -> name (for the human-readable report)
FLAGS = [
    (0x00000001, 'mcc'), (0x00000002, 'mnc'), (0x00000004, 'locale'),
    (0x00000008, 'touchscreen'), (0x00000010, 'keyboard'), (0x00000020, 'keyboardHidden'),
    (0x00000040, 'navigation'), (0x00000080, 'orientation'), (0x00000100, 'screenLayout'),
    (0x00000200, 'uiMode'), (0x00000400, 'screenSize'), (0x00000800, 'smallestScreenSize'),
    (0x00001000, 'density'), (0x00002000, 'layoutDirection'), (0x00004000, 'colorMode'),
    (0x00008000, 'grammaticalGender'), (0x40000000, 'fontScale'),
]


def describe(v):
    names = [n for b, n in FLAGS if v & b]
    rest = v & ~sum(b for b, _ in FLAGS)
    if rest:
        names.append('0x%x' % rest)
    return '|'.join(names) or '<none>'


class Axml:
    def __init__(self, data):
        self.d = bytearray(data)
        self._parse_pool()
        self._parse_resmap()

    def u16(self, o): return struct.unpack_from('<H', self.d, o)[0]
    def u32(self, o): return struct.unpack_from('<I', self.d, o)[0]

    def _parse_pool(self):
        assert self.u16(8) == 0x0001, 'string pool is not the first chunk'
        count = self.u32(8 + 8)
        flags = self.u32(8 + 16)
        assert not (flags & 0x100), 'UTF-8 string pools unsupported'
        base = 8 + self.u32(8 + 20)
        self.strings = []
        for i in range(count):
            p = base + self.u32(8 + 28 + 4 * i)
            n = self.u16(p)
            self.strings.append(self.d[p + 2:p + 2 + 2 * n].decode('utf-16-le'))

    def _parse_resmap(self):
        o = 8 + self.u32(12)                    # string pool size -> next chunk
        assert self.u16(o) == 0x0180, 'resource map chunk expected'
        size = self.u32(o + 4)
        self.resids = [self.u32(o + 8 + 4 * i) for i in range((size - 8) // 4)]
        self.body = o + size

    def string(self, i):
        return self.strings[i] if i != 0xFFFFFFFF and i < len(self.strings) else None

    def resid(self, i):
        return self.resids[i] if i < len(self.resids) else None

    def tags(self):
        """Yield (tag_name, [(attr_resid, value_offset, type, data), ...]) per START tag."""
        o = self.body
        while o < len(self.d):
            t, size = self.u16(o), self.u32(o + 4)
            if t == 0x0102:                      # START element
                name = self.string(self.u32(o + 20))
                ao = o + 16 + self.u16(o + 24)   # attributeStart is relative to attrExt
                stride, count = self.u16(o + 26), self.u16(o + 28)
                attrs = []
                for i in range(count):
                    a = ao + i * stride
                    attrs.append((self.resid(self.u32(a + 4)), self.string(self.u32(a + 8)),
                                  self.d[a + 15], self.u32(a + 16), a + 16))
                yield name, attrs
            o += size


def main():
    argv = sys.argv[1:]
    if not argv:
        sys.exit(__doc__)
    src = argv[0]
    ax = Axml(open(src, 'rb').read())

    if '--print' in argv:
        for name, attrs in ax.tags():
            if name in ('activity', 'application'):
                who = next((raw for rid, raw, _, _, _ in attrs if rid == ATTR_NAME), '?')
                cc = next(((data) for rid, _, _, data, _ in attrs if rid == ATTR_CONFIG_CHANGES), None)
                print('%-11s %-55s configChanges=%s  %s' %
                      (name, who, '0x%08x' % cc if cc is not None else '-',
                       describe(cc) if cc is not None else ''))
        return

    out = argv[1]
    activity = argv[argv.index('--activity') + 1]
    value = int(argv[argv.index('--config-changes') + 1], 0)

    hits = 0
    for name, attrs in ax.tags():
        if name != 'activity':
            continue
        who = next((raw for rid, raw, _, _, _ in attrs if rid == ATTR_NAME), None)
        if who != activity:
            continue
        for rid, _raw, typ, data, off in attrs:
            if rid != ATTR_CONFIG_CHANGES:
                continue
            assert typ in (TYPE_INT_HEX, TYPE_INT_DEC), 'configChanges is not an int (type 0x%02x)' % typ
            struct.pack_into('<I', ax.d, off, value)
            print('%s: configChanges 0x%08x (%s)' % (activity, data, describe(data)))
            print('%s-> configChanges 0x%08x (%s)' % (' ' * len(activity), value, describe(value)))
            hits += 1
    assert hits == 1, 'expected exactly one configChanges on %s, found %d' % (activity, hits)

    open(out, 'wb').write(bytes(ax.d))
    # re-read and confirm the value took, so a silent offset bug can't ship
    check = Axml(open(out, 'rb').read())
    got = [data for n, attrs in check.tags() if n == 'activity'
           for rid, raw, _, data, _ in attrs if rid == ATTR_CONFIG_CHANGES
           and any(r == ATTR_NAME and v == activity for r, v, _, _, _ in attrs)]
    assert got == [value], 'read-back failed: %r' % got
    assert len(check.d) == len(ax.d), 'size changed -- this edit must be in-place'
    print('wrote %s (%d bytes, unchanged size), read-back OK' % (out, len(check.d)))


if __name__ == '__main__':
    main()

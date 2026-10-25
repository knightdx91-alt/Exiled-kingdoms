#!/usr/bin/env python3
"""Binary AndroidManifest.xml (AXML) <-> element tree, for adding the Quest VR declarations.

Decodes the whole document (string pool, resource map, namespace/element nodes) into a
small tree, lets the caller edit it, and re-encodes from scratch: the string pool is rebuilt
with every resource-mapped attribute name first (their android attr ids in the resource
map), attributes are kept sorted by resource id as aapt writes them. resources.arsc is not
touched, so existing @0x7f… references in the manifest stay valid.

Used by tools/build_vr.sh through vr_manifest() below.
"""
import struct

ANDROID_NS = 'http://schemas.android.com/apk/res/android'
ATTR_IDS = {'name': 0x01010003, 'value': 0x01010024, 'required': 0x0101028e,
            'label': 0x01010001, 'icon': 0x01010002, 'theme': 0x01010000}
T_REF, T_STRING, T_INT, T_HEX, T_BOOL = 0x01, 0x03, 0x10, 0x11, 0x12


class Attr:
    def __init__(self, ns, name, raw, dtype, data, resid=0):
        self.ns, self.name, self.raw, self.dtype, self.data, self.resid = ns, name, raw, dtype, data, resid


class Elem:
    def __init__(self, ns, name, line=0):
        self.ns, self.name, self.line = ns, name, line
        self.attrs, self.children = [], []

    def get(self, name):
        for a in self.attrs:
            if a.name == name:
                return a.raw if a.dtype == T_STRING else a.data
        return None

    def find_all(self, name):
        out = [self] if self.name == name else []
        for c in self.children:
            out += c.find_all(name)
        return out


def _read_pool(d, o):
    _, hsize, size, n, nstyles, flags, sstart, _ = struct.unpack_from('<HHIIIIII', d, o)
    utf8 = flags & 0x100
    offs = struct.unpack_from('<%dI' % n, d, o + hsize)
    out = []
    for so in offs:
        p = o + sstart + so
        if utf8:
            ln = d[p]; p += 1
            if ln & 0x80: p += 1
            ln = d[p]; p += 1
            if ln & 0x80: ln = ((ln & 0x7f) << 8) | d[p]; p += 1
            out.append(d[p:p + ln].decode('utf-8', 'replace'))
        else:
            ln = struct.unpack_from('<H', d, p)[0]; p += 2
            if ln & 0x8000: ln = ((ln & 0x7fff) << 16) | struct.unpack_from('<H', d, p)[0]; p += 2
            out.append(d[p:p + ln * 2].decode('utf-16-le', 'replace'))
    return out, size


def decode(d):
    assert struct.unpack_from('<H', d, 0)[0] == 3, 'not AXML'
    o = 8
    strings, resmap, nsmap = [], [], []
    root, stack = None, []
    S = lambda i: None if i == 0xffffffff else strings[i]
    while o < len(d):
        typ, hsize, size = struct.unpack_from('<HHI', d, o)
        if typ == 0x0001:
            strings, _ = _read_pool(d, o)
        elif typ == 0x0180:
            resmap = list(struct.unpack_from('<%dI' % ((size - 8) // 4), d, o + 8))
        elif typ == 0x0100:
            pre, uri = struct.unpack_from('<II', d, o + 16)
            nsmap.append((S(pre), S(uri)))
        elif typ == 0x0102:
            line = struct.unpack_from('<I', d, o + 8)[0]
            ns, name, astart, asize, acount = struct.unpack_from('<IIHHH', d, o + 16)
            e = Elem(S(ns), S(name), line)
            for i in range(acount):
                p = o + 16 + astart + i * asize
                ans, aname, raw, _, _, dt, data = struct.unpack_from('<IIIHBBI', d, p)
                e.attrs.append(Attr(S(ans), S(aname), S(raw), dt, data if dt != T_STRING else S(data),
                                    resmap[aname] if aname < len(resmap) else 0))
            if stack: stack[-1].children.append(e)
            else: root = e
            stack.append(e)
        elif typ == 0x0103:
            stack.pop()
        o += size
    return root, nsmap


def encode(root, nsmap):
    # string pool: resource-mapped attribute names first (ordered by id), then the rest
    mapped, other = {}, []
    def walk(e):
        for a in e.attrs:
            if a.resid: mapped[a.name] = a.resid
        for c in e.children: walk(c)
    walk(root)
    names = sorted(mapped, key=lambda n: mapped[n])
    pool = list(names)
    idx = {s: i for i, s in enumerate(pool)}
    def sid(s):
        if s is None: return 0xffffffff
        if s not in idx:
            idx[s] = len(pool); pool.append(s)
        return idx[s]
    for pre, uri in nsmap: sid(pre); sid(uri)
    body = []
    for pre, uri in nsmap:
        body.append(struct.pack('<HHIIIII', 0x0100, 16, 24, 1, 0xffffffff, sid(pre), sid(uri)))
    def emit(e):
        e.attrs.sort(key=lambda a: (a.resid == 0, a.resid, a.name))
        attrs = b''
        for a in e.attrs:
            name_i = idx[a.name] if a.resid else sid(a.name)
            raw = sid(a.raw) if a.raw is not None else 0xffffffff
            data = sid(a.data) if a.dtype == T_STRING else (a.data & 0xffffffff)
            attrs += struct.pack('<IIIHBBI', sid(a.ns), name_i, raw, 8, 0, a.dtype, data)
        size = 36 + len(attrs)
        body.append(struct.pack('<HHIIIIIHHHHHH', 0x0102, 16, size, e.line, 0xffffffff, sid(e.ns), sid(e.name),
                                20, 20, len(e.attrs), 0, 0, 0) + attrs)
        for c in e.children: emit(c)
        body.append(struct.pack('<HHIIIII', 0x0103, 16, 24, e.line, 0xffffffff, sid(e.ns), sid(e.name)))
    emit(root)
    for pre, uri in reversed(nsmap):
        body.append(struct.pack('<HHIIIII', 0x0101, 16, 24, 1, 0xffffffff, sid(pre), sid(uri)))
    # UTF-8 string pool
    data, offs = b'', []
    for s in pool:
        b = s.encode('utf-8')
        offs.append(len(data))
        def ln(n): return bytes([n]) if n < 0x80 else bytes([0x80 | (n >> 8), n & 0xff])
        data += ln(len(s)) + ln(len(b)) + b + b'\x00'
    while len(data) % 4: data += b'\x00'
    hdr = 28
    sp = struct.pack('<HHIIIIII', 0x0001, hdr, hdr + 4 * len(pool) + len(data), len(pool), 0, 0x100,
                     hdr + 4 * len(pool), 0) + struct.pack('<%dI' % len(pool), *offs) + data
    rm = struct.pack('<HHI', 0x0180, 8, 8 + 4 * len(names)) + struct.pack('<%dI' % len(names), *[mapped[n] for n in names])
    content = sp + rm + b''.join(body)
    return struct.pack('<HHI', 0x0003, 8, 8 + len(content)) + content


def attr_s(name, value):
    return Attr(ANDROID_NS, name, value, T_STRING, value, ATTR_IDS[name])


def attr_b(name, value):
    return Attr(ANDROID_NS, name, None, T_BOOL, 0xffffffff if value else 0, ATTR_IDS[name])


def elem(name, *attrs):
    e = Elem(None, name)
    e.attrs = list(attrs)
    return e


def vr_manifest(src, dst):
    """The Quest VR declarations on top of hero-v78's manifest (deobf/QUEST_VR_SPEC.md §4)."""
    root, nsmap = decode(open(src, 'rb').read())
    have = {(e.name, e.get('name')) for e in root.find_all('uses-permission') + root.find_all('uses-feature')}
    adds = []
    for p in ('org.khronos.openxr.permission.OPENXR', 'org.khronos.openxr.permission.OPENXR_SYSTEM'):
        if ('uses-permission', p) not in have: adds.append(elem('uses-permission', attr_s('name', p)))
    if ('uses-feature', 'android.hardware.vr.headtracking') not in have:
        adds.append(elem('uses-feature', attr_s('name', 'android.hardware.vr.headtracking'), attr_b('required', True)))
    if ('uses-feature', 'com.oculus.feature.PASSTHROUGH') not in have:
        adds.append(elem('uses-feature', attr_s('name', 'com.oculus.feature.PASSTHROUGH'), attr_b('required', False)))
    app_i = next(i for i, c in enumerate(root.children) if c.name == 'application')
    root.children[app_i:app_i] = adds
    app = root.children[app_i + len(adds)]
    if not any(m.get('name') == 'com.oculus.supportedDevices' for m in app.find_all('meta-data')):
        app.children.insert(0, elem('meta-data', attr_s('name', 'com.oculus.supportedDevices'),
                                    attr_s('value', 'quest2|quest3|quest3s|questpro')))
    main = next(a for a in app.find_all('activity') if a.get('name') == 'net.fdgames.ek.android.MainActivity')
    filt = next(f for f in main.children if f.name == 'intent-filter')
    cats = {c.get('name') for c in filt.children if c.name == 'category'}
    for c in ('com.oculus.intent.category.VR', 'org.khronos.openxr.intent.category.IMMERSIVE_HMD'):
        if c not in cats: filt.children.append(elem('category', attr_s('name', c)))
    open(dst, 'wb').write(encode(root, nsmap))


if __name__ == '__main__':
    import sys
    vr_manifest(sys.argv[1], sys.argv[2])

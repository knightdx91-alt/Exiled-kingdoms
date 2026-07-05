package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.a;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Attributes implements Iterable<Attribute>, Comparator<Attribute>, Comparable<Attributes> {
    protected long mask;
    protected final a<Attribute> attributes = new a<>();
    protected boolean sorted = true;

    private final void disable(long j2) {
        this.mask = (~j2) & this.mask;
    }

    private final void enable(long j2) {
        this.mask = j2 | this.mask;
    }

    public int attributesHash() {
        sort();
        int i2 = this.attributes.f1750b;
        long jHashCode = this.mask + 71;
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 7) & 65535;
            jHashCode += this.mask * ((long) this.attributes.get(i4).hashCode()) * ((long) i3);
        }
        return (int) ((jHashCode >> 32) ^ jHashCode);
    }

    public void clear() {
        this.mask = 0L;
        this.attributes.clear();
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (!(obj instanceof Attributes)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return same((Attributes) obj, true);
    }

    public final Attribute get(long j2) {
        if (!has(j2)) {
            return null;
        }
        int i2 = 0;
        while (true) {
            a<Attribute> aVar = this.attributes;
            if (i2 >= aVar.f1750b) {
                return null;
            }
            if (aVar.get(i2).type == j2) {
                return this.attributes.get(i2);
            }
            i2++;
        }
    }

    public final long getMask() {
        return this.mask;
    }

    public final boolean has(long j2) {
        return j2 != 0 && (this.mask & j2) == j2;
    }

    public int hashCode() {
        return attributesHash();
    }

    protected int indexOf(long j2) {
        if (!has(j2)) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            a<Attribute> aVar = this.attributes;
            if (i2 >= aVar.f1750b) {
                return -1;
            }
            if (aVar.get(i2).type == j2) {
                return i2;
            }
            i2++;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<Attribute> iterator() {
        return this.attributes.iterator();
    }

    public final void remove(long j2) {
        for (int i2 = this.attributes.f1750b - 1; i2 >= 0; i2--) {
            long j3 = this.attributes.get(i2).type;
            if ((j2 & j3) == j3) {
                this.attributes.o(i2);
                disable(j3);
                this.sorted = false;
            }
        }
        sort();
    }

    public final boolean same(Attributes attributes, boolean z2) {
        if (attributes == this) {
            return true;
        }
        if (attributes == null || this.mask != attributes.mask) {
            return false;
        }
        if (!z2) {
            return true;
        }
        sort();
        attributes.sort();
        int i2 = 0;
        while (true) {
            a<Attribute> aVar = this.attributes;
            if (i2 >= aVar.f1750b) {
                return true;
            }
            if (!aVar.get(i2).equals(attributes.attributes.get(i2))) {
                return false;
            }
            i2++;
        }
    }

    public final void set(Attribute attribute) {
        int iIndexOf = indexOf(attribute.type);
        if (iIndexOf < 0) {
            enable(attribute.type);
            this.attributes.a(attribute);
            this.sorted = false;
        } else {
            this.attributes.t(iIndexOf, attribute);
        }
        sort();
    }

    public int size() {
        return this.attributes.f1750b;
    }

    public final void sort() {
        if (this.sorted) {
            return;
        }
        this.attributes.sort(this);
        this.sorted = true;
    }

    @Override // java.util.Comparator
    public final int compare(Attribute attribute, Attribute attribute2) {
        return (int) (attribute.type - attribute2.type);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attributes attributes) {
        if (attributes == this) {
            return 0;
        }
        long j2 = this.mask;
        long j3 = attributes.mask;
        if (j2 != j3) {
            return j2 < j3 ? -1 : 1;
        }
        sort();
        attributes.sort();
        int i2 = 0;
        while (true) {
            a<Attribute> aVar = this.attributes;
            if (i2 >= aVar.f1750b) {
                return 0;
            }
            int iCompareTo = aVar.get(i2).compareTo(attributes.attributes.get(i2));
            if (iCompareTo != 0) {
                if (iCompareTo < 0) {
                    return -1;
                }
                return iCompareTo > 0 ? 1 : 0;
            }
            i2++;
        }
    }

    public final <T extends Attribute> T get(Class<T> cls, long j2) {
        return (T) get(j2);
    }

    public final a<Attribute> get(a<Attribute> aVar, long j2) {
        int i2 = 0;
        while (true) {
            a<Attribute> aVar2 = this.attributes;
            if (i2 >= aVar2.f1750b) {
                return aVar;
            }
            if ((aVar2.get(i2).type & j2) != 0) {
                aVar.a(this.attributes.get(i2));
            }
            i2++;
        }
    }

    public final boolean same(Attributes attributes) {
        return same(attributes, false);
    }

    public final void set(Attribute attribute, Attribute attribute2) {
        set(attribute);
        set(attribute2);
    }

    public final void set(Attribute attribute, Attribute attribute2, Attribute attribute3) {
        set(attribute);
        set(attribute2);
        set(attribute3);
    }

    public final void set(Attribute attribute, Attribute attribute2, Attribute attribute3, Attribute attribute4) {
        set(attribute);
        set(attribute2);
        set(attribute3);
        set(attribute4);
    }

    public final void set(Attribute... attributeArr) {
        for (Attribute attribute : attributeArr) {
            set(attribute);
        }
    }

    public final void set(Iterable<Attribute> iterable) {
        Iterator<Attribute> it = iterable.iterator();
        while (it.hasNext()) {
            set(it.next());
        }
    }
}

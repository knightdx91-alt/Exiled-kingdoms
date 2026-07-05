package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class Attribute implements Comparable<Attribute> {
    private static final a<String> types = new a<>();
    public final long type;
    private final int typeBit;

    protected Attribute(long j2) {
        this.type = j2;
        this.typeBit = Long.numberOfTrailingZeros(j2);
    }

    public static final String getAttributeAlias(long j2) {
        int i2 = -1;
        while (j2 != 0 && (i2 = i2 + 1) < 63 && ((j2 >> i2) & 1) == 0) {
        }
        if (i2 >= 0) {
            a<String> aVar = types;
            if (i2 < aVar.f1750b) {
                return aVar.get(i2);
            }
        }
        return null;
    }

    public static final long getAttributeType(String str) {
        int i2 = 0;
        while (true) {
            a<String> aVar = types;
            if (i2 >= aVar.f1750b) {
                return 0L;
            }
            if (aVar.get(i2).compareTo(str) == 0) {
                return 1 << i2;
            }
            i2++;
        }
    }

    protected static final long register(String str) {
        long attributeType = getAttributeType(str);
        if (attributeType > 0) {
            return attributeType;
        }
        types.a(str);
        return 1 << (r0.f1750b - 1);
    }

    public abstract Attribute copy();

    protected boolean equals(Attribute attribute) {
        return attribute.hashCode() == hashCode();
    }

    public int hashCode() {
        return this.typeBit * 7489;
    }

    public String toString() {
        return getAttributeAlias(this.type);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.type != attribute.type) {
            return false;
        }
        return equals(attribute);
    }
}

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.m;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class VertexAttributes implements Iterable<VertexAttribute>, Comparable<VertexAttributes> {
    private final VertexAttribute[] attributes;
    private ReadonlyIterable<VertexAttribute> iterable;
    private long mask = -1;
    public final int vertexSize;

    private static class ReadonlyIterable<T> implements Iterable<T> {
        private final T[] array;
        private ReadonlyIterator iterator1;
        private ReadonlyIterator iterator2;

        public ReadonlyIterable(T[] tArr) {
            this.array = tArr;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            if (this.iterator1 == null) {
                this.iterator1 = new ReadonlyIterator(this.array);
                this.iterator2 = new ReadonlyIterator(this.array);
            }
            ReadonlyIterator readonlyIterator = this.iterator1;
            if (!readonlyIterator.valid) {
                readonlyIterator.index = 0;
                readonlyIterator.valid = true;
                this.iterator2.valid = false;
                return readonlyIterator;
            }
            ReadonlyIterator readonlyIterator2 = this.iterator2;
            readonlyIterator2.index = 0;
            readonlyIterator2.valid = true;
            readonlyIterator.valid = false;
            return readonlyIterator2;
        }
    }

    private static class ReadonlyIterator<T> implements Iterator<T>, Iterable<T> {
        private final T[] array;
        int index;
        boolean valid = true;

        public ReadonlyIterator(T[] tArr) {
            this.array = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.array.length;
            }
            throw new m("#iterator() cannot be used nested.");
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public T next() {
            int i2 = this.index;
            T[] tArr = this.array;
            if (i2 >= tArr.length) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new m("#iterator() cannot be used nested.");
            }
            this.index = i2 + 1;
            return tArr[i2];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new m("Remove not allowed.");
        }

        public void reset() {
            this.index = 0;
        }
    }

    public static final class Usage {
        public static final int BiNormal = 256;
        public static final int BoneWeight = 64;
        public static final int ColorPacked = 4;
        public static final int ColorUnpacked = 2;
        public static final int Generic = 32;
        public static final int Normal = 8;
        public static final int Position = 1;
        public static final int Tangent = 128;
        public static final int TextureCoordinates = 16;
    }

    public VertexAttributes(VertexAttribute... vertexAttributeArr) {
        if (vertexAttributeArr.length == 0) {
            throw new IllegalArgumentException("attributes must be >= 1");
        }
        VertexAttribute[] vertexAttributeArr2 = new VertexAttribute[vertexAttributeArr.length];
        for (int i2 = 0; i2 < vertexAttributeArr.length; i2++) {
            vertexAttributeArr2[i2] = vertexAttributeArr[i2];
        }
        this.attributes = vertexAttributeArr2;
        this.vertexSize = calculateOffsets();
    }

    private int calculateOffsets() {
        int i2 = 0;
        int sizeInBytes = 0;
        while (true) {
            VertexAttribute[] vertexAttributeArr = this.attributes;
            if (i2 >= vertexAttributeArr.length) {
                return sizeInBytes;
            }
            VertexAttribute vertexAttribute = vertexAttributeArr[i2];
            vertexAttribute.offset = sizeInBytes;
            sizeInBytes += vertexAttribute.getSizeInBytes();
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VertexAttributes)) {
            return false;
        }
        VertexAttributes vertexAttributes = (VertexAttributes) obj;
        if (this.attributes.length != vertexAttributes.attributes.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            VertexAttribute[] vertexAttributeArr = this.attributes;
            if (i2 >= vertexAttributeArr.length) {
                return true;
            }
            if (!vertexAttributeArr[i2].equals(vertexAttributes.attributes[i2])) {
                return false;
            }
            i2++;
        }
    }

    public VertexAttribute findByUsage(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (get(i3).usage == i2) {
                return get(i3);
            }
        }
        return null;
    }

    public VertexAttribute get(int i2) {
        return this.attributes[i2];
    }

    public long getMask() {
        if (this.mask == -1) {
            long j2 = 0;
            int i2 = 0;
            while (true) {
                VertexAttribute[] vertexAttributeArr = this.attributes;
                if (i2 >= vertexAttributeArr.length) {
                    break;
                }
                j2 |= (long) vertexAttributeArr[i2].usage;
                i2++;
            }
            this.mask = j2;
        }
        return this.mask;
    }

    public long getMaskWithSizePacked() {
        return getMask() | (((long) this.attributes.length) << 32);
    }

    public int getOffset(int i2, int i3) {
        VertexAttribute vertexAttributeFindByUsage = findByUsage(i2);
        return vertexAttributeFindByUsage == null ? i3 : vertexAttributeFindByUsage.offset / 4;
    }

    public int hashCode() {
        long length = this.attributes.length * 61;
        int i2 = 0;
        while (true) {
            VertexAttribute[] vertexAttributeArr = this.attributes;
            if (i2 >= vertexAttributeArr.length) {
                return (int) (length ^ (length >> 32));
            }
            length = (length * 61) + ((long) vertexAttributeArr[i2].hashCode());
            i2++;
        }
    }

    @Override // java.lang.Iterable
    public Iterator<VertexAttribute> iterator() {
        if (this.iterable == null) {
            this.iterable = new ReadonlyIterable<>(this.attributes);
        }
        return this.iterable.iterator();
    }

    public int size() {
        return this.attributes.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i2 = 0; i2 < this.attributes.length; i2++) {
            sb.append("(");
            sb.append(this.attributes[i2].alias);
            sb.append(", ");
            sb.append(this.attributes[i2].usage);
            sb.append(", ");
            sb.append(this.attributes[i2].numComponents);
            sb.append(", ");
            sb.append(this.attributes[i2].offset);
            sb.append(")\n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(VertexAttributes vertexAttributes) {
        VertexAttribute[] vertexAttributeArr = this.attributes;
        int length = vertexAttributeArr.length;
        VertexAttribute[] vertexAttributeArr2 = vertexAttributes.attributes;
        if (length != vertexAttributeArr2.length) {
            return vertexAttributeArr.length - vertexAttributeArr2.length;
        }
        long mask = getMask();
        long mask2 = vertexAttributes.getMask();
        if (mask != mask2) {
            return mask < mask2 ? -1 : 1;
        }
        for (int length2 = this.attributes.length - 1; length2 >= 0; length2--) {
            VertexAttribute vertexAttribute = this.attributes[length2];
            VertexAttribute vertexAttribute2 = vertexAttributes.attributes[length2];
            int i2 = vertexAttribute.usage;
            int i3 = vertexAttribute2.usage;
            if (i2 != i3) {
                return i2 - i3;
            }
            int i4 = vertexAttribute.unit;
            int i5 = vertexAttribute2.unit;
            if (i4 != i5) {
                return i4 - i5;
            }
            int i6 = vertexAttribute.numComponents;
            int i7 = vertexAttribute2.numComponents;
            if (i6 != i7) {
                return i6 - i7;
            }
            boolean z2 = vertexAttribute.normalized;
            if (z2 != vertexAttribute2.normalized) {
                return z2 ? 1 : -1;
            }
            int i8 = vertexAttribute.type;
            int i9 = vertexAttribute2.type;
            if (i8 != i9) {
                return i8 - i9;
            }
        }
        return 0;
    }

    public int getOffset(int i2) {
        return getOffset(i2, 0);
    }
}

package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParallelArray {
    public int capacity;
    a<Channel> arrays = new a<>(false, 2, Channel.class);
    public int size = 0;

    public abstract class Channel {
        public Object data;
        public int id;
        public int strideSize;

        public Channel(int i2, Object obj, int i3) {
            this.id = i2;
            this.strideSize = i3;
            this.data = obj;
        }

        public abstract void add(int i2, Object... objArr);

        protected abstract void setCapacity(int i2);

        public abstract void swap(int i2, int i3);
    }

    public static class ChannelDescriptor {
        public int count;
        public int id;
        public Class<?> type;

        public ChannelDescriptor(int i2, Class<?> cls, int i3) {
            this.id = i2;
            this.type = cls;
            this.count = i3;
        }
    }

    public interface ChannelInitializer<T extends Channel> {
        void init(T t2);
    }

    public class FloatChannel extends Channel {
        public float[] data;

        public FloatChannel(int i2, int i3, int i4) {
            super(i2, new float[i4 * i3], i3);
            this.data = (float[]) super.data;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i2, Object... objArr) {
            int i3 = this.strideSize;
            int i4 = ParallelArray.this.size * i3;
            int i5 = i3 + i4;
            int i6 = 0;
            while (i4 < i5) {
                this.data[i4] = ((Float) objArr[i6]).floatValue();
                i4++;
                i6++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i2) {
            int i3 = this.strideSize * i2;
            float[] fArr = new float[i3];
            float[] fArr2 = this.data;
            System.arraycopy(fArr2, 0, fArr, 0, Math.min(fArr2.length, i3));
            this.data = fArr;
            super.data = fArr;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i2, int i3) {
            int i4 = this.strideSize;
            int i5 = i2 * i4;
            int i6 = i3 * i4;
            int i7 = i4 + i5;
            while (i5 < i7) {
                float[] fArr = this.data;
                float f2 = fArr[i5];
                fArr[i5] = fArr[i6];
                fArr[i6] = f2;
                i5++;
                i6++;
            }
        }
    }

    public class IntChannel extends Channel {
        public int[] data;

        public IntChannel(int i2, int i3, int i4) {
            super(i2, new int[i4 * i3], i3);
            this.data = (int[]) super.data;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i2, Object... objArr) {
            int i3 = this.strideSize;
            int i4 = ParallelArray.this.size * i3;
            int i5 = i3 + i4;
            int i6 = 0;
            while (i4 < i5) {
                this.data[i4] = ((Integer) objArr[i6]).intValue();
                i4++;
                i6++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i2) {
            int i3 = this.strideSize * i2;
            int[] iArr = new int[i3];
            int[] iArr2 = this.data;
            System.arraycopy(iArr2, 0, iArr, 0, Math.min(iArr2.length, i3));
            this.data = iArr;
            super.data = iArr;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i2, int i3) {
            int i4 = this.strideSize;
            int i5 = i2 * i4;
            int i6 = i3 * i4;
            int i7 = i4 + i5;
            while (i5 < i7) {
                int[] iArr = this.data;
                int i8 = iArr[i5];
                iArr[i5] = iArr[i6];
                iArr[i6] = i8;
                i5++;
                i6++;
            }
        }
    }

    public class ObjectChannel<T> extends Channel {
        Class<T> componentType;
        public T[] data;

        public ObjectChannel(int i2, int i3, int i4, Class<T> cls) {
            super(i2, Array.newInstance((Class<?>) cls, i4 * i3), i3);
            this.componentType = cls;
            this.data = (T[]) ((Object[]) super.data);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void add(int i2, Object... objArr) {
            int i3 = this.strideSize;
            int i4 = ParallelArray.this.size * i3;
            int i5 = i3 + i4;
            int i6 = 0;
            while (i4 < i5) {
                ((T[]) this.data)[i4] = objArr[i6];
                i4++;
                i6++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void setCapacity(int i2) {
            T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) this.componentType, this.strideSize * i2));
            T[] tArr2 = this.data;
            System.arraycopy(tArr2, 0, tArr, 0, Math.min(tArr2.length, tArr.length));
            this.data = tArr;
            super.data = tArr;
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParallelArray.Channel
        public void swap(int i2, int i3) {
            int i4 = this.strideSize;
            int i5 = i2 * i4;
            int i6 = i3 * i4;
            int i7 = i4 + i5;
            while (i5 < i7) {
                T[] tArr = this.data;
                T t2 = tArr[i5];
                tArr[i5] = tArr[i6];
                tArr[i6] = t2;
                i5++;
                i6++;
            }
        }
    }

    public ParallelArray(int i2) {
        this.capacity = i2;
    }

    private <T extends Channel> T allocateChannel(ChannelDescriptor channelDescriptor) {
        Class<?> cls = channelDescriptor.type;
        return cls == Float.TYPE ? new FloatChannel(channelDescriptor.id, channelDescriptor.count, this.capacity) : cls == Integer.TYPE ? new IntChannel(channelDescriptor.id, channelDescriptor.count, this.capacity) : new ObjectChannel(channelDescriptor.id, channelDescriptor.count, this.capacity, cls);
    }

    private int findIndex(int i2) {
        int i3 = 0;
        while (true) {
            a<Channel> aVar = this.arrays;
            if (i3 >= aVar.f1750b) {
                return -1;
            }
            if (aVar.f1749a[i3].id == i2) {
                return i3;
            }
            i3++;
        }
    }

    public <T extends Channel> T addChannel(ChannelDescriptor channelDescriptor) {
        return (T) addChannel(channelDescriptor, null);
    }

    public void addElement(Object... objArr) {
        if (this.size == this.capacity) {
            throw new m("Capacity reached, cannot add other elements");
        }
        a.b<Channel> it = this.arrays.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Channel next = it.next();
            next.add(i2, objArr);
            i2 += next.strideSize;
        }
        this.size++;
    }

    public void clear() {
        this.arrays.clear();
        this.size = 0;
    }

    public <T extends Channel> T getChannel(ChannelDescriptor channelDescriptor) {
        a.b<Channel> it = this.arrays.iterator();
        while (it.hasNext()) {
            T t2 = (T) it.next();
            if (t2.id == channelDescriptor.id) {
                return t2;
            }
        }
        return null;
    }

    public <T> void removeArray(int i2) {
        this.arrays.o(findIndex(i2));
    }

    public void removeElement(int i2) {
        int i3 = this.size - 1;
        a.b<Channel> it = this.arrays.iterator();
        while (it.hasNext()) {
            it.next().swap(i2, i3);
        }
        this.size = i3;
    }

    public void setCapacity(int i2) {
        if (this.capacity != i2) {
            a.b<Channel> it = this.arrays.iterator();
            while (it.hasNext()) {
                it.next().setCapacity(i2);
            }
            this.capacity = i2;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public <T extends Channel> T addChannel(ChannelDescriptor channelDescriptor, ChannelInitializer<T> channelInitializer) {
        T t2 = (T) getChannel(channelDescriptor);
        if (t2 == null) {
            t2 = (T) allocateChannel(channelDescriptor);
            if (channelInitializer != null) {
                channelInitializer.init(t2);
            }
            this.arrays.a(t2);
        }
        return t2;
    }
}

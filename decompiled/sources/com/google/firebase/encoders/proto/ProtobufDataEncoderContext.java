package com.google.firebase.encoders.proto;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.proto.Protobuf;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final FieldDescriptor MAP_KEY_DESC = FieldDescriptor.builder("key").withProperty(AtProtobuf.builder().tag(1).build()).build();
    private static final FieldDescriptor MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = new a(0);

    /* JADX INFO: renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding;

        static {
            int[] iArr = new int[Protobuf.IntEncoding.values().length];
            $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding = iArr;
            try {
                iArr[Protobuf.IntEncoding.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.SIGNED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[Protobuf.IntEncoding.FIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    ProtobufDataEncoderContext(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    private static ByteBuffer allocateBuffer(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    private <T> long determineSize(ObjectEncoder<T> objectEncoder, T t2) throws IOException {
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            OutputStream outputStream = this.output;
            this.output = lengthCountingOutputStream;
            try {
                objectEncoder.encode(t2, this);
                this.output = outputStream;
                long length = lengthCountingOutputStream.getLength();
                lengthCountingOutputStream.close();
                return length;
            } catch (Throwable th) {
                this.output = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                lengthCountingOutputStream.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t2, boolean z2) throws IOException {
        long jDetermineSize = determineSize(objectEncoder, t2);
        if (z2 && jDetermineSize == 0) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
        writeVarInt64(jDetermineSize);
        objectEncoder.encode(t2, this);
        return this;
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Map.Entry entry, ObjectEncoderContext objectEncoderContext) {
        objectEncoderContext.add(MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add(MAP_VALUE_DESC, entry.getValue());
    }

    private void writeVarInt32(int i2) throws IOException {
        while ((i2 & (-128)) != 0) {
            this.output.write((i2 & 127) | VertexAttributes.Usage.Tangent);
            i2 >>>= 7;
        }
        this.output.write(i2 & 127);
    }

    private void writeVarInt64(long j2) throws IOException {
        while (((-128) & j2) != 0) {
            this.output.write((((int) j2) & 127) | VertexAttributes.Usage.Tangent);
            j2 >>>= 7;
        }
        this.output.write(((int) j2) & 127);
    }

    ProtobufDataEncoderContext encode(Object obj) {
        if (obj == null) {
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for " + obj.getClass());
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object obj) {
        return encode(obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String str) {
        return nested(FieldDescriptor.of(str));
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, Object obj) {
        return add(FieldDescriptor.of(str), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, double d2) {
        return add(FieldDescriptor.of(str), d2);
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t2, boolean z2) {
        this.valueEncoderContext.resetContext(fieldDescriptor, z2);
        valueEncoder.encode(t2, this.valueEncoderContext);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, int i2) {
        return add(FieldDescriptor.of(str), i2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, long j2) {
        return add(FieldDescriptor.of(str), j2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, boolean z2) {
        return add(FieldDescriptor.of(str), z2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) {
        return add(fieldDescriptor, obj, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj, boolean z2) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z2 && charSequence.length() == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(UTF_8);
            writeVarInt32(bytes.length);
            this.output.write(bytes);
            return this;
        }
        if (obj instanceof Collection) {
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                add(fieldDescriptor, it.next(), false);
            }
            return this;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                doEncode((ObjectEncoder<Map.Entry>) DEFAULT_MAP_ENCODER, fieldDescriptor, (Map.Entry) it2.next(), false);
            }
            return this;
        }
        if (obj instanceof Double) {
            return add(fieldDescriptor, ((Double) obj).doubleValue(), z2);
        }
        if (obj instanceof Float) {
            return add(fieldDescriptor, ((Float) obj).floatValue(), z2);
        }
        if (obj instanceof Number) {
            return add(fieldDescriptor, ((Number) obj).longValue(), z2);
        }
        if (obj instanceof Boolean) {
            return add(fieldDescriptor, ((Boolean) obj).booleanValue(), z2);
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z2 && bArr.length == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            writeVarInt32(bArr.length);
            this.output.write(bArr);
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            return doEncode(objectEncoder, fieldDescriptor, obj, z2);
        }
        ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            return doEncode(valueEncoder, fieldDescriptor, obj, z2);
        }
        if (obj instanceof ProtoEnum) {
            return add(fieldDescriptor, ((ProtoEnum) obj).getNumber());
        }
        if (obj instanceof Enum) {
            return add(fieldDescriptor, ((Enum) obj).ordinal());
        }
        return doEncode(this.fallbackEncoder, fieldDescriptor, obj, z2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d2) {
        return add(fieldDescriptor, d2, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d2, boolean z2) throws IOException {
        if (z2 && d2 == 0.0d) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(d2).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f2) {
        return add(fieldDescriptor, f2, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f2, boolean z2) throws IOException {
        if (z2 && f2 == 0.0f) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(f2).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i2) {
        return add(fieldDescriptor, i2, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i2, boolean z2) throws IOException {
        if (z2 && i2 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i3 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i3 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32(i2);
        } else if (i3 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32((i2 << 1) ^ (i2 >> 31));
        } else if (i3 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 5);
            this.output.write(allocateBuffer(4).putInt(i2).array());
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j2) {
        return add(fieldDescriptor, j2, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j2, boolean z2) throws IOException {
        if (z2 && j2 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf.intEncoding().ordinal()];
        if (i2 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64(j2);
        } else if (i2 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64((j2 >> 63) ^ (j2 << 1));
        } else if (i2 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 1);
            this.output.write(allocateBuffer(8).putLong(j2).array());
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z2) {
        return add(fieldDescriptor, z2, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z2, boolean z3) {
        return add(fieldDescriptor, z2 ? 1 : 0, z3);
    }
}

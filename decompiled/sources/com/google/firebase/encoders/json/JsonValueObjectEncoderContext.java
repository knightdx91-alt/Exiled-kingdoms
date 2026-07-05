package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final boolean ignoreNullValues;
    private final JsonWriter jsonWriter;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private JsonValueObjectEncoderContext childContext = null;
    private boolean active = true;

    JsonValueObjectEncoderContext(Writer writer, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder, boolean z2) {
        this.jsonWriter = new JsonWriter(writer);
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
        this.ignoreNullValues = z2;
    }

    private boolean cannotBeInline(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    private JsonValueObjectEncoderContext internalAdd(String str, Object obj) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        if (obj != null) {
            return add(obj, false);
        }
        this.jsonWriter.nullValue();
        return this;
    }

    private JsonValueObjectEncoderContext internalAddIgnoreNullValues(String str, Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(obj, false);
    }

    private void maybeUnNest() throws IOException {
        if (!this.active) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
        JsonValueObjectEncoderContext jsonValueObjectEncoderContext = this.childContext;
        if (jsonValueObjectEncoderContext != null) {
            jsonValueObjectEncoderContext.maybeUnNest();
            this.childContext.active = false;
            this.childContext = null;
            this.jsonWriter.endObject();
        }
    }

    void close() throws IOException {
        maybeUnNest();
        this.jsonWriter.flush();
    }

    JsonValueObjectEncoderContext doEncode(ObjectEncoder<Object> objectEncoder, Object obj, boolean z2) throws IOException {
        if (!z2) {
            this.jsonWriter.beginObject();
        }
        objectEncoder.encode(obj, this);
        if (!z2) {
            this.jsonWriter.endObject();
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object obj) {
        return add(obj, true);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String str) throws IOException {
        maybeUnNest();
        this.childContext = new JsonValueObjectEncoderContext(this);
        this.jsonWriter.name(str);
        this.jsonWriter.beginObject();
        return this.childContext;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) {
        return nested(fieldDescriptor.getName());
    }

    private JsonValueObjectEncoderContext(JsonValueObjectEncoderContext jsonValueObjectEncoderContext) {
        this.jsonWriter = jsonValueObjectEncoderContext.jsonWriter;
        this.objectEncoders = jsonValueObjectEncoderContext.objectEncoders;
        this.valueEncoders = jsonValueObjectEncoderContext.valueEncoders;
        this.fallbackEncoder = jsonValueObjectEncoderContext.fallbackEncoder;
        this.ignoreNullValues = jsonValueObjectEncoderContext.ignoreNullValues;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, Object obj) {
        if (this.ignoreNullValues) {
            return internalAddIgnoreNullValues(str, obj);
        }
        return internalAdd(str, obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, double d2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(d2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, int i2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(i2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, long j2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(j2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, boolean z2) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(z2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) {
        return add(fieldDescriptor.getName(), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f2) {
        return add(fieldDescriptor.getName(), f2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d2) {
        return add(fieldDescriptor.getName(), d2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, int i2) {
        return add(fieldDescriptor.getName(), i2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, long j2) {
        return add(fieldDescriptor.getName(), j2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, boolean z2) {
        return add(fieldDescriptor.getName(), z2);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(String str) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(str);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(float f2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(f2);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(double d2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(d2);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(int i2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(i2);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(long j2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(j2);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(boolean z2) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(z2);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(byte[] bArr) throws IOException {
        maybeUnNest();
        if (bArr == null) {
            this.jsonWriter.nullValue();
        } else {
            this.jsonWriter.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    JsonValueObjectEncoderContext add(Object obj, boolean z2) throws IOException {
        if (z2 && cannotBeInline(obj)) {
            throw new EncodingException((obj == null ? null : obj.getClass()) + " cannot be encoded inline");
        }
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        if (obj instanceof Number) {
            this.jsonWriter.value((Number) obj);
            return this;
        }
        int i2 = 0;
        if (obj.getClass().isArray()) {
            if (obj instanceof byte[]) {
                return add((byte[]) obj);
            }
            this.jsonWriter.beginArray();
            if (obj instanceof int[]) {
                int length = ((int[]) obj).length;
                while (i2 < length) {
                    this.jsonWriter.value(r6[i2]);
                    i2++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length2 = jArr.length;
                while (i2 < length2) {
                    add(jArr[i2]);
                    i2++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length3 = dArr.length;
                while (i2 < length3) {
                    this.jsonWriter.value(dArr[i2]);
                    i2++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length4 = zArr.length;
                while (i2 < length4) {
                    this.jsonWriter.value(zArr[i2]);
                    i2++;
                }
            } else if (obj instanceof Number[]) {
                for (Number number : (Number[]) obj) {
                    add((Object) number, false);
                }
            } else {
                for (Object obj2 : (Object[]) obj) {
                    add(obj2, false);
                }
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Collection) {
            this.jsonWriter.beginArray();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                add(it.next(), false);
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Map) {
            this.jsonWriter.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                try {
                    add((String) key, entry.getValue());
                } catch (ClassCastException e2) {
                    throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e2);
                }
            }
            this.jsonWriter.endObject();
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            return doEncode(objectEncoder, obj, z2);
        }
        ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            valueEncoder.encode(obj, this);
            return this;
        }
        if (obj instanceof Enum) {
            add(((Enum) obj).name());
            return this;
        }
        return doEncode(this.fallbackEncoder, obj, z2);
    }
}

package net.fdgames.Helpers;

import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Json {
    private final ObjectMap<Class, Object[]> classToDefaultValues;
    private final ObjectMap<Class, Serializer> classToSerializer;
    private final ObjectMap<Class, String> classToTag;
    private Serializer defaultSerializer;
    private boolean enumNames;
    private boolean ignoreUnknownFields;
    private u.Constructor outputType;
    private boolean quoteLongValues;
    private final ObjectMap<String, Class> tagToClass;
    private String typeName;
    private final ObjectMap<Class, y<String, a>> typeToFields;
    private boolean usePrototypes;
    private JsonWriter writer;

    public static abstract class ReadOnlySerializer<T> implements Serializer<T> {
        @Override // net.fdgames.Helpers.Json.Serializer
        public abstract T read(Json json, JsonValue tVar, Class cls);

        @Override // net.fdgames.Helpers.Json.Serializer
        public void write(Json json, T t2, Class cls) {
        }
    }

    public interface Serializable {
        void read(Json json, JsonValue tVar);

        void write(Json json);
    }

    public interface Serializer<T> {
        T read(Json json, JsonValue tVar, Class cls);

        void write(Json json, T t2, Class cls);
    }

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Field f3222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Class f3223b;
    }

    public Json() {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap <>();
        this.tagToClass = new ObjectMap <>();
        this.classToTag = new ObjectMap <>();
        this.classToSerializer = new ObjectMap <>();
        this.classToDefaultValues = new ObjectMap <>();
        this.outputType = u.b.f1986b;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private y<String, a> cacheFields(Class cls) {
        ArrayList arrayList = new ArrayList();
        for (Class superclass = cls; superclass != Object.class; superclass = superclass.getSuperclass()) {
            Collections.addAll(arrayList, SerializationException.a.d(superclass));
        }
        ObjectMap<String, a> yVar = new ObjectMap <>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Field cVar = (Field) arrayList.get(i2);
            if (!cVar.j() && !cVar.h() && !cVar.i()) {
                if (cVar.f()) {
                    String strD = cVar.d();
                    a aVar = new a();
                    aVar.f3222a = cVar;
                    if (y.class.isAssignableFrom(cVar.e())) {
                        aVar.f3223b = cVar.c((!y.class.isAssignableFrom(cVar.e()) || Map.class.isAssignableFrom(cVar.e())) ? 1 : 0);
                        yVar.j(strD, aVar);
                    }
                } else {
                    try {
                        cVar.l();
                        String strD2 = cVar.d();
                        a aVar2 = new a();
                        aVar2.f3222a = cVar;
                        aVar2.f3223b = cVar.c((!y.class.isAssignableFrom(cVar.e()) || Map.class.isAssignableFrom(cVar.e())) ? 1 : 0);
                        yVar.j(strD2, aVar2);
                    } catch (AccessControlException unused) {
                    }
                }
            }
        }
        this.typeToFields.j(cls, yVar);
        return yVar;
    }

    private String convertToString(Object obj) {
        return obj instanceof Class ? ((Class) obj).getName() : String.valueOf(obj);
    }

    private Object[] getDefaultValues(Class cls) {
        if (!this.usePrototypes) {
            return null;
        }
        if (this.classToDefaultValues.a(cls)) {
            return this.classToDefaultValues.d(cls);
        }
        try {
            Object objNewInstance = newInstance(cls);
            ObjectMap<String, a> yVarD = this.typeToFields.d(cls);
            if (yVarD == null) {
                yVarD = cacheFields(cls);
            }
            Object[] objArr = new Object[yVarD.f2043a];
            this.classToDefaultValues.j(cls, objArr);
            y.ReflectionException<a> eVarN = yVarD.n();
            eVarN.getClass();
            int i2 = 0;
            while (eVarN.hasNext()) {
                Field cVar = eVarN.next().f3222a;
                int i3 = i2 + 1;
                try {
                    objArr[i2] = cVar.a(objNewInstance);
                    i2 = i3;
                } catch (SerializationException e2) {
                    e2.a(cVar + " (" + cls.getName() + ")");
                    throw e2;
                } catch (ReflectionException e3) {
                    throw new SerializationException ("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
                } catch (RuntimeException e4) {
                    SerializationException h0Var = new SerializationException (e4);
                    h0Var.a(cVar + " (" + cls.getName() + ")");
                    throw h0Var;
                }
            }
            return objArr;
        } catch (Exception unused) {
            this.classToDefaultValues.j(cls, null);
            return null;
        }
    }

    public void addClassTag(String str, Class cls) {
        this.tagToClass.j(str, cls);
        this.classToTag.j(cls, str);
    }

    public <T> T fromJson(Class<T> cls, Reader reader) {
        return (T) readValue(cls, (Class) null, new JsonReader ().d(reader));
    }

    public Class getClass(String str) {
        Class clsD = this.tagToClass.d(str);
        if (clsD != null) {
            return clsD;
        }
        try {
            return SerializationException.a.a(str);
        } catch (ReflectionException e2) {
            throw new SerializationException (e2);
        }
    }

    public <T> Serializer<T> getSerializer(Class<T> cls) {
        return this.classToSerializer.d(cls);
    }

    public String getTag(Class cls) {
        String strD = this.classToTag.d(cls);
        return strD != null ? strD : cls.getName();
    }

    public JsonWriter getWriter() {
        return this.writer;
    }

    protected Object newInstance(Class cls) {
        try {
            return SerializationException.a.f(cls);
        } catch (Exception e2) {
            e = e2;
            try {
                Constructor bVarC = SerializationException.a.c(cls, new Class[0]);
                bVarC.c();
                return bVarC.b(new Object[0]);
            } catch (ReflectionException unused) {
                if (Enum.class.isAssignableFrom(cls)) {
                    if (cls.getEnumConstants() == null) {
                        cls = cls.getSuperclass();
                    }
                    return cls.getEnumConstants()[0];
                }
                if (cls.isArray()) {
                    throw new SerializationException ("Encountered JSON object when expected array of type: ".concat(cls.getName()), e);
                }
                if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                    throw new SerializationException ("Class cannot be created (missing no-arg constructor): ".concat(cls.getName()), e);
                }
                throw new SerializationException ("Class cannot be created (non-static member class): ".concat(cls.getName()), e);
            } catch (SecurityException unused2) {
                throw new SerializationException ("Error constructing instance of class: ".concat(cls.getName()), e);
            } catch (Exception e3) {
                e = e3;
                throw new SerializationException ("Error constructing instance of class: ".concat(cls.getName()), e);
            }
        }
    }

    public String prettyPrint(Object obj) {
        return prettyPrint(obj, 0);
    }

    public void readField(Object obj, String str, JsonValue tVar) {
        readField(obj, str, str, null, tVar);
    }

    public void readFields(Object obj, JsonValue tVar) {
        Class<?> cls = obj.getClass();
        ObjectMap<String, a> yVarD = this.typeToFields.d(cls);
        if (yVarD == null) {
            yVarD = cacheFields(cls);
        }
        for (JsonValue tVar2 = tVar.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
            a aVarD = yVarD.d(tVar2.f1957e);
            if (aVarD != null) {
                Field cVar = aVarD.f3222a;
                try {
                    cVar.k(obj, readValue(cVar.e(), aVarD.f3223b, tVar2));
                } catch (SerializationException e2) {
                    e2.a(cVar.d() + " (" + cls.getName() + ")");
                    throw e2;
                } catch (ReflectionException e3) {
                    throw new SerializationException ("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
                } catch (RuntimeException e4) {
                    SerializationException h0Var = new SerializationException (e4);
                    h0Var.a(cVar.d() + " (" + cls.getName() + ")");
                    throw h0Var;
                }
            } else if (!this.ignoreUnknownFields) {
                throw new SerializationException ("Field not found: " + tVar2.f1957e + " (" + cls.getName() + ")");
            }
        }
    }

    public <T> T readValue(String str, Class<T> cls, JsonValue tVar) {
        return (T) readValue(cls, (Class) null, tVar.k(str));
    }

    public void setDefaultSerializer(Serializer serializer) {
        this.defaultSerializer = serializer;
    }

    public void setElementType(Class cls, String str, Class cls2) {
        ObjectMap<String, a> yVarD = this.typeToFields.d(cls);
        if (yVarD == null) {
            yVarD = cacheFields(cls);
        }
        a aVarD = yVarD.d(str);
        if (aVarD != null) {
            aVarD.f3223b = cls2;
            return;
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls.getName());
        sbU.append(")");
        throw new SerializationException (sbU.toString());
    }

    public void setEnumNames(boolean z2) {
        this.enumNames = z2;
    }

    public void setIgnoreUnknownFields(boolean z2) {
        this.ignoreUnknownFields = z2;
    }

    public void setOutputType(u.Constructor bVar) {
        this.outputType = bVar;
    }

    public void setQuoteLongValues(boolean z2) {
        this.quoteLongValues = z2;
    }

    public <T> void setSerializer(Class<T> cls, Serializer<T> serializer) {
        this.classToSerializer.j(cls, serializer);
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public void setUsePrototypes(boolean z2) {
        this.usePrototypes = z2;
    }

    public void setWriter(Writer writer) {
        if (!(writer instanceof u)) {
            writer = new JsonWriter (writer);
        }
        JsonWriter uVar = (JsonWriter) writer;
        this.writer = uVar;
        uVar.f(this.outputType);
        this.writer.g(this.quoteLongValues);
    }

    public String toJson(Object obj) {
        return toJson(obj, obj == null ? null : obj.getClass(), (Class) null);
    }

    public void writeArrayEnd() {
        try {
            this.writer.d();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeArrayStart(String str) {
        try {
            this.writer.b(str);
            this.writer.a();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeField(Object obj, String str) {
        writeField(obj, str, str, null);
    }

    public void writeFields(Object obj) {
        Class<?> cls = obj.getClass();
        Object[] defaultValues = getDefaultValues(cls);
        ObjectMap<String, a> yVarD = this.typeToFields.d(cls);
        if (yVarD == null) {
            yVarD = cacheFields(cls);
        }
        y.ReflectionException eVar = new y.e(yVarD);
        int i2 = 0;
        while (eVar.hasNext()) {
            a aVar = (a) eVar.next();
            Field cVar = aVar.f3222a;
            try {
                Object objA = cVar.a(obj);
                if (defaultValues != null) {
                    int i3 = i2 + 1;
                    Object obj2 = defaultValues[i2];
                    if ((objA != null || obj2 != null) && (objA == null || obj2 == null || !objA.equals(obj2))) {
                        i2 = i3;
                    }
                    i2 = i3;
                }
                this.writer.b(cVar.d());
                writeValue(objA, cVar.e(), aVar.f3223b);
            } catch (SerializationException e2) {
                e2.a(cVar + " (" + cls.getName() + ")");
                throw e2;
            } catch (ReflectionException e3) {
                throw new SerializationException ("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
            } catch (Exception e4) {
                SerializationException h0Var = new SerializationException (e4);
                h0Var.a(cVar + " (" + cls.getName() + ")");
                throw h0Var;
            }
        }
    }

    public void writeObjectEnd() {
        try {
            this.writer.d();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeObjectStart(String str) {
        try {
            this.writer.b(str);
            writeObjectStart();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeType(Class cls) {
        if (this.typeName == null) {
            return;
        }
        String strD = this.classToTag.d(cls);
        if (strD == null) {
            strD = cls.getName();
        }
        try {
            JsonWriter uVar = this.writer;
            uVar.b(this.typeName);
            uVar.h(strD);
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeValue(String str, Object obj) {
        try {
            this.writer.b(str);
            if (obj == null) {
                writeValue(obj, (Class) null, (Class) null);
            } else {
                writeValue(obj, obj.getClass(), (Class) null);
            }
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public <T> T fromJson(Class<T> cls, Class cls2, Reader reader) {
        return (T) readValue(cls, cls2, new JsonReader ().d(reader));
    }

    public String prettyPrint(String str) {
        return prettyPrint(str, 0);
    }

    public void readField(Object obj, String str, Class cls, JsonValue tVar) {
        readField(obj, str, str, cls, tVar);
    }

    public <T> T readValue(String str, Class<T> cls, T t2, JsonValue tVar) {
        JsonValue tVarK = tVar.k(str);
        return tVarK == null ? t2 : (T) readValue(cls, (Class) null, tVarK);
    }

    public String toJson(Object obj, Class cls) {
        return toJson(obj, cls, (Class) null);
    }

    public void writeField(Object obj, String str, Class cls) {
        writeField(obj, str, str, cls);
    }

    public <T> T fromJson(Class<T> cls, InputStream inputStream) {
        return (T) readValue(cls, (Class) null, new JsonReader ().c(inputStream));
    }

    public String prettyPrint(Object obj, int i2) {
        return prettyPrint(toJson(obj), i2);
    }

    public void readField(Object obj, String str, String str2, JsonValue tVar) {
        readField(obj, str, str2, null, tVar);
    }

    public String toJson(Object obj, Class cls, Class cls2) {
        StringWriter stringWriter = new StringWriter();
        toJson(obj, cls, cls2, stringWriter);
        return stringWriter.toString();
    }

    public void writeField(Object obj, String str, String str2) {
        writeField(obj, str, str2, null);
    }

    public <T> T fromJson(Class<T> cls, Class cls2, InputStream inputStream) {
        return (T) readValue(cls, cls2, new JsonReader ().c(inputStream));
    }

    public String prettyPrint(String str, int i2) {
        JsonValue tVarE = new JsonReader ().e(str);
        u.Constructor bVar = this.outputType;
        tVarE.getClass();
        t.Constructor bVar2 = new t.b();
        bVar2.f1966a = bVar;
        bVar2.f1967b = i2;
        return tVarE.w(bVar2);
    }

    public void readField(Object obj, String str, String str2, Class cls, JsonValue tVar) {
        Class<?> cls2 = obj.getClass();
        ObjectMap<String, a> yVarD = this.typeToFields.d(cls2);
        if (yVarD == null) {
            yVarD = cacheFields(cls2);
        }
        a aVarD = yVarD.d(str);
        if (aVarD != null) {
            Field cVar = aVarD.f3222a;
            JsonValue tVarK = tVar.k(str2);
            if (tVarK == null) {
                return;
            }
            if (cls == null) {
                cls = aVarD.f3223b;
            }
            try {
                cVar.k(obj, readValue(cVar.e(), cls, tVarK));
                return;
            } catch (SerializationException e2) {
                e2.a(cVar.d() + " (" + cls2.getName() + ")");
                throw e2;
            } catch (ReflectionException e3) {
                throw new SerializationException ("Error accessing field: " + cVar.d() + " (" + cls2.getName() + ")", e3);
            } catch (RuntimeException e4) {
                SerializationException h0Var = new SerializationException (e4);
                h0Var.a(cVar.d() + " (" + cls2.getName() + ")");
                throw h0Var;
            }
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls2.getName());
        sbU.append(")");
        throw new SerializationException (sbU.toString());
    }

    public <T> T readValue(String str, Class<T> cls, Class cls2, JsonValue tVar) {
        return (T) readValue(cls, cls2, tVar.k(str));
    }

    public void writeArrayStart() {
        try {
            this.writer.a();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void writeField(Object obj, String str, String str2, Class cls) {
        Class<?> cls2 = obj.getClass();
        ObjectMap<String, a> yVarD = this.typeToFields.d(cls2);
        if (yVarD == null) {
            yVarD = cacheFields(cls2);
        }
        a aVarD = yVarD.d(str);
        if (aVarD != null) {
            Field cVar = aVarD.f3222a;
            if (cls == null) {
                cls = aVarD.f3223b;
            }
            try {
                this.writer.b(str2);
                writeValue(cVar.a(obj), cVar.e(), cls);
                return;
            } catch (SerializationException e2) {
                e2.a(cVar + " (" + cls2.getName() + ")");
                throw e2;
            } catch (ReflectionException e3) {
                throw new SerializationException ("Error accessing field: " + cVar.d() + " (" + cls2.getName() + ")", e3);
            } catch (Exception e4) {
                SerializationException h0Var = new SerializationException (e4);
                h0Var.a(cVar + " (" + cls2.getName() + ")");
                throw h0Var;
            }
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls2.getName());
        sbU.append(")");
        throw new SerializationException (sbU.toString());
    }

    public void writeObjectStart(String str, Class cls, Class cls2) {
        try {
            this.writer.b(str);
            writeObjectStart(cls, cls2);
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public <T> T fromJson(Class<T> cls, com.badlogic.gdx.files.a aVar) {
        try {
            return (T) readValue(cls, (Class) null, new JsonReader ().a(aVar));
        } catch (Exception e2) {
            throw new SerializationException (a.a.i(aVar, "Error reading file: "), e2);
        }
    }

    public <T> T readValue(String str, Class<T> cls, Class cls2, T t2, JsonValue tVar) {
        JsonValue tVarK = tVar.k(str);
        return tVarK == null ? t2 : (T) readValue(cls, cls2, tVarK);
    }

    public void writeValue(String str, Object obj, Class cls) {
        try {
            this.writer.b(str);
            writeValue(obj, cls, (Class) null);
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void toJson(Object obj, com.badlogic.gdx.files.a aVar) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, aVar);
    }

    public <T> T readValue(Class<T> cls, Class cls2, T t2, JsonValue tVar) {
        return (T) readValue(cls, cls2, tVar);
    }

    public void toJson(Object obj, Class cls, com.badlogic.gdx.files.a aVar) {
        toJson(obj, cls, (Class) null, aVar);
    }

    public void writeObjectStart() {
        try {
            this.writer.c();
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public <T> T readValue(Class<T> cls, JsonValue tVar) {
        return (T) readValue(cls, (Class) null, tVar);
    }

    public void toJson(Object obj, Class cls, Class cls2, com.badlogic.gdx.files.a aVar) {
        Writer writer = null;
        try {
            try {
                writer = aVar.writer(false, "UTF-8");
                toJson(obj, cls, cls2, writer);
            } catch (Exception e2) {
                throw new SerializationException ("Error writing file: " + aVar, e2);
            }
        } finally {
            StreamUtils.a(writer);
        }
    }

    public void writeValue(String str, Object obj, Class cls, Class cls2) {
        try {
            this.writer.b(str);
            writeValue(obj, cls, cls2);
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:159:0x0235, code lost:
    
        if (r0 != r3) goto L174;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0367 A[RETURN] */
    /* JADX WARN: Type inference failed for: r0v45, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v46, types: [T, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v47, types: [T, com.badlogic.gdx.utils.b] */
    /* JADX WARN: Type inference failed for: r0v48, types: [T, com.badlogic.gdx.utils.y] */
    /* JADX WARN: Type inference failed for: r0v70 */
    /* JADX WARN: Type inference failed for: r0v85, types: [com.badlogic.gdx.utils.a] */
    /* JADX WARN: Type inference failed for: r20v0, types: [net.fdgames.Helpers.Json] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.badlogic.gdx.utils.t] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.badlogic.gdx.utils.t, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.badlogic.gdx.utils.t] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.badlogic.gdx.utils.t, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v28, types: [java.lang.Enum[]] */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v43, types: [net.fdgames.Helpers.Json$Serializer] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v52, types: [net.fdgames.Helpers.Json$Serializable] */
    /* JADX WARN: Type inference failed for: r3v54, types: [net.fdgames.Helpers.Json$Serializer] */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v21, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r5v12, types: [net.fdgames.Helpers.Json$Serializer] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Enum] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> T readValue(Class<T> cls, Class cls2, JsonValue tVar) {
        Class cls3;
        Class cls4;
        Class clsD;
        ?? r2;
        ?? S;
        ?? tVar2;
        ?? r3;
        Class<T> cls5;
        ?? tVar3;
        Serializer serializerD;
        Class cls6;
        ?? r22 = (T) tVar;
        if (r22 == 0) {
            return null;
        }
        if (tVar.t()) {
            String str = this.typeName;
            String strO = str == null ? null : r22.o(str, null);
            if (strO != null) {
                JsonValue tVarK = r22.k(this.typeName);
                if (tVarK == null) {
                    cls6 = Collection.class;
                } else {
                    JsonValue tVar4 = tVarK.f1961i;
                    if (tVar4 == null) {
                        JsonValue tVar5 = tVarK.f1960h;
                        r22.f1958f = tVar5;
                        if (tVar5 != null) {
                            tVar5.f1961i = null;
                        }
                        cls6 = Collection.class;
                    } else {
                        cls6 = Collection.class;
                        tVar4.f1960h = tVarK.f1960h;
                        JsonValue tVar6 = tVarK.f1960h;
                        if (tVar6 != null) {
                            tVar6.f1961i = tVar4;
                        }
                    }
                    r22.f1962j--;
                }
                clsD = this.tagToClass.d(strO);
                if (clsD == null) {
                    try {
                        clsD = SerializationException.a.a(strO);
                    } catch (ReflectionException e2) {
                        throw new SerializationException (e2);
                    }
                }
            } else {
                cls6 = Collection.class;
                clsD = cls;
            }
            if (clsD == null) {
                ?? r32 = this.defaultSerializer;
                return r32 != 0 ? (T) r32.read(this, r22, clsD) : r22;
            }
            if (clsD != String.class && clsD != Integer.class && clsD != Boolean.class && clsD != Float.class && clsD != Long.class && clsD != Double.class && clsD != Short.class && clsD != Byte.class && clsD != Character.class && !Enum.class.isAssignableFrom(clsD)) {
                if (this.typeName != null) {
                    cls4 = cls6;
                    if (cls4.isAssignableFrom(clsD)) {
                        cls3 = cls2;
                        r2 = (T) r22.k("items");
                    }
                }
                Serializer serializerD2 = this.classToSerializer.d(clsD);
                if (serializerD2 != 0) {
                    return (T) serializerD2.read(this, r22, clsD);
                }
                ?? r02 = (T) newInstance(clsD);
                if (r02 instanceof Serializable) {
                    ((Serializable) r02).read(this, r22);
                    return r02;
                }
                if (r02 instanceof y) {
                    ?? r03 = (T) ((ObjectMap) r02);
                    for (JsonValue tVar7 = r22.f1958f; tVar7 != null; tVar7 = tVar7.f1960h) {
                        r03.j(tVar7.f1957e, readValue(cls2, null, tVar7));
                    }
                    return r03;
                }
                if (r02 instanceof com.badlogic.gdx.utils.b) {
                    ?? r04 = (T) ((com.badlogic.gdx.utils.b) r02);
                    for (JsonValue tVar8 = r22.f1958f; tVar8 != null; tVar8 = tVar8.f1960h) {
                        r04.c(tVar8.f1957e, readValue(cls2, null, tVar8));
                    }
                    return r04;
                }
                if (r02 instanceof Map) {
                    ?? r05 = (T) ((Map) r02);
                    for (JsonValue tVar9 = r22.f1958f; tVar9 != null; tVar9 = tVar9.f1960h) {
                        r05.put(tVar9.f1957e, readValue(cls2, null, tVar9));
                    }
                    return r05;
                }
                readFields(r02, r22);
                return r02;
            }
            return (T) readValue("value", clsD, r22);
        }
        cls3 = cls2;
        cls4 = Collection.class;
        clsD = cls;
        r2 = r22;
        if (clsD != null && (serializerD = this.classToSerializer.d(clsD)) != 0) {
            return (T) serializerD.read(this, r2, clsD);
        }
        int i2 = 0;
        if (r2.q()) {
            if (clsD == null || clsD == Object.class) {
                clsD = com.badlogic.gdx.utils.a.class;
            }
            if (com.badlogic.gdx.utils.a.class.isAssignableFrom(clsD)) {
                Object obj = clsD == com.badlogic.gdx.utils.a.class ? (T) new com.badlogic.gdx.utils.a() : (T) ((com.badlogic.gdx.utils.a) newInstance(clsD));
                for (JsonValue tVar10 = r2.f1958f; tVar10 != null; tVar10 = tVar10.f1960h) {
                    ((com.badlogic.gdx.utils.a) obj).a(readValue(cls3, null, tVar10));
                }
                return (T) obj;
            }
            if (cls4.isAssignableFrom(clsD)) {
                T t2 = clsD.isInterface() ? (T) new ArrayList() : (T) ((Collection) newInstance(clsD));
                for (JsonValue tVar11 = r2.f1958f; tVar11 != null; tVar11 = tVar11.f1960h) {
                    ((Collection) t2).add(readValue(cls3, null, tVar11));
                }
                return t2;
            }
            if (clsD.isArray()) {
                Class<?> componentType = clsD.getComponentType();
                if (cls3 == null) {
                    cls3 = componentType;
                }
                T t3 = (T) Array.newInstance(componentType, r2.f1962j);
                JsonValue tVar12 = r2.f1958f;
                while (tVar12 != null) {
                    Array.set(t3, i2, readValue(cls3, null, tVar12));
                    tVar12 = tVar12.f1960h;
                    i2++;
                }
                return t3;
            }
            throw new SerializationException ("Unable to convert value to required type: " + r2 + " (" + clsD.getName() + ")");
        }
        S = r2.s();
        if (S != 0) {
            try {
                if (clsD != null) {
                    try {
                        if (clsD != Float.TYPE && clsD != Float.class) {
                            if (clsD != Integer.TYPE && clsD != Integer.class) {
                                if (clsD != Long.TYPE && clsD != Long.class) {
                                    if (clsD == Double.TYPE || clsD == Double.class) {
                                        return (T) Double.valueOf(r2.c());
                                    }
                                    if (clsD == String.class) {
                                        return (T) r2.j();
                                    }
                                    if (clsD != Short.TYPE && clsD != Short.class) {
                                        if (clsD != Byte.TYPE) {
                                            S = Byte.class;
                                        }
                                        return (T) Byte.valueOf(r2.b());
                                    }
                                    return (T) Short.valueOf(r2.h());
                                }
                                return (T) Long.valueOf(r2.g());
                            }
                            return (T) Integer.valueOf(r2.f());
                        }
                    } catch (NumberFormatException unused) {
                        S = Byte.class;
                    }
                }
                return (T) Float.valueOf(r2.d());
            } catch (NumberFormatException unused2) {
            }
        } else {
            r3 = Byte.class;
            tVar2 = r2;
        }
        if (tVar2.r()) {
            cls5 = Boolean.class;
            tVar3 = tVar2;
        } else if (clsD != null) {
            try {
            } catch (NumberFormatException unused3) {
                cls5 = Boolean.class;
            }
            if (clsD != Boolean.TYPE) {
                cls5 = Boolean.class;
                if (clsD == cls5) {
                }
                tVar3 = new JsonValue (tVar2.j());
            } else {
                cls5 = Boolean.class;
            }
            try {
                return (T) Boolean.valueOf(tVar2.a());
            } catch (NumberFormatException unused4) {
            }
        }
        if (tVar3.u()) {
            return null;
        }
        ?? r5 = (T) tVar3.j();
        if (clsD == null || clsD == String.class) {
            return r5;
        }
        if (clsD != Integer.TYPE && clsD != Integer.class) {
            if (clsD != Float.TYPE && clsD != Float.class) {
                if (clsD != Long.TYPE && clsD != Long.class) {
                    if (clsD != Double.TYPE && clsD != Double.class) {
                        if (clsD != Short.TYPE && clsD != Short.class) {
                            if (clsD == Byte.TYPE || clsD == r3) {
                                return (T) Byte.valueOf((String) r5);
                            }
                            if (clsD != Boolean.TYPE && clsD != cls5) {
                                if (clsD != Character.TYPE && clsD != Character.class) {
                                    if (Enum.class.isAssignableFrom(clsD)) {
                                        ?? r33 = (Enum[]) clsD.getEnumConstants();
                                        int length = r33.length;
                                        while (i2 < length) {
                                            if (r5.equals(r33[i2].name())) {
                                                return (T) r33[i2];
                                            }
                                            i2++;
                                        }
                                    }
                                    if (clsD == CharSequence.class) {
                                        return r5;
                                    }
                                    throw new SerializationException ("Unable to convert value to required type: " + tVar3 + " (" + clsD.getName() + ")");
                                }
                                return (T) Character.valueOf(r5.charAt(0));
                            }
                            return (T) Boolean.valueOf((String) r5);
                        }
                        return (T) Short.valueOf((String) r5);
                    }
                    return (T) Double.valueOf((String) r5);
                }
                return (T) Long.valueOf((String) r5);
            }
            return (T) Float.valueOf((String) r5);
        }
        return (T) Integer.valueOf((String) r5);
        tVar2 = new JsonValue (r2.j());
        r3 = S;
        if (tVar2.r()) {
        }
        if (tVar3.u()) {
        }
    }

    public void writeObjectStart(Class cls, Class cls2) {
        try {
            this.writer.c();
            if (cls2 == null || cls2 != cls) {
                writeType(cls);
            }
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public String prettyPrint(Object obj, t.Constructor bVar) {
        return prettyPrint(toJson(obj), bVar);
    }

    public Json(u.Constructor bVar) {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap <>();
        this.tagToClass = new ObjectMap <>();
        this.classToTag = new ObjectMap <>();
        this.classToSerializer = new ObjectMap <>();
        this.classToDefaultValues = new ObjectMap <>();
        this.outputType = bVar;
    }

    public String prettyPrint(String str, t.Constructor bVar) {
        return new JsonReader ().e(str).w(bVar);
    }

    public void writeValue(Object obj) {
        if (obj == null) {
            writeValue(obj, (Class) null, (Class) null);
        } else {
            writeValue(obj, obj.getClass(), (Class) null);
        }
    }

    public void writeValue(Object obj, Class cls) {
        writeValue(obj, cls, (Class) null);
    }

    public <T> T fromJson(Class<T> cls, Class cls2, com.badlogic.gdx.files.a aVar) {
        try {
            return (T) readValue(cls, cls2, new JsonReader ().a(aVar));
        } catch (Exception e2) {
            throw new SerializationException (a.a.i(aVar, "Error reading file: "), e2);
        }
    }

    public void writeValue(Object obj, Class cls, Class cls2) {
        Class cls3 = cls;
        try {
            if (obj == null) {
                this.writer.h(null);
                return;
            }
            if ((cls3 == null || !cls.isPrimitive()) && cls3 != String.class && cls3 != Integer.class && cls3 != Boolean.class && cls3 != Float.class && cls3 != Long.class && cls3 != Double.class && cls3 != Short.class && cls3 != Byte.class && cls3 != Character.class) {
                Class<?> superclass = obj.getClass();
                if (!superclass.isPrimitive() && superclass != String.class && superclass != Integer.class && superclass != Boolean.class && superclass != Float.class && superclass != Long.class && superclass != Double.class && superclass != Short.class && superclass != Byte.class && superclass != Character.class) {
                    if (obj instanceof Serializable) {
                        writeObjectStart(superclass, cls3);
                        ((Serializable) obj).write(this);
                        writeObjectEnd();
                        return;
                    }
                    Serializer serializerD = this.classToSerializer.d(superclass);
                    if (serializerD != null) {
                        serializerD.write(this, obj, cls3);
                        return;
                    }
                    int i2 = 0;
                    if (obj instanceof com.badlogic.gdx.utils.a) {
                        if (cls3 != null && superclass != cls3 && superclass != com.badlogic.gdx.utils.a.class) {
                            throw new SerializationException ("Serialization of an Array other than the known type is not supported.\nKnown type: " + cls3 + "\nActual type: " + superclass);
                        }
                        writeArrayStart();
                        com.badlogic.gdx.utils.a aVar = (com.badlogic.gdx.utils.a) obj;
                        int i3 = aVar.f1750b;
                        while (i2 < i3) {
                            writeValue(aVar.get(i2), cls2, (Class) null);
                            i2++;
                        }
                        writeArrayEnd();
                        return;
                    }
                    if (obj instanceof Collection) {
                        if (this.typeName != null && superclass != ArrayList.class && (cls3 == null || cls3 != superclass)) {
                            writeObjectStart(superclass, cls3);
                            writeArrayStart("items");
                            Iterator it = ((Collection) obj).iterator();
                            while (it.hasNext()) {
                                writeValue(it.next(), cls2, (Class) null);
                            }
                            writeArrayEnd();
                            writeObjectEnd();
                            return;
                        }
                        writeArrayStart();
                        Iterator it2 = ((Collection) obj).iterator();
                        while (it2.hasNext()) {
                            writeValue(it2.next(), cls2, (Class) null);
                        }
                        writeArrayEnd();
                        return;
                    }
                    if (superclass.isArray()) {
                        Class componentType = cls2 == null ? superclass.getComponentType() : cls2;
                        int length = Array.getLength(obj);
                        writeArrayStart();
                        while (i2 < length) {
                            writeValue(Array.get(obj, i2), componentType, (Class) null);
                            i2++;
                        }
                        writeArrayEnd();
                        return;
                    }
                    if (obj instanceof y) {
                        if (cls3 == null) {
                            cls3 = y.class;
                        }
                        writeObjectStart(superclass, cls3);
                        y.a aVarB = ((ObjectMap) obj).b();
                        aVarB.getClass();
                        while (aVarB.hasNext()) {
                            y.Constructor next = aVarB.next();
                            this.writer.b(convertToString(next.f2057a));
                            writeValue(next.f2058b, cls2, (Class) null);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof com.badlogic.gdx.utils.b) {
                        if (cls3 == null) {
                            cls3 = com.badlogic.gdx.utils.b.class;
                        }
                        writeObjectStart(superclass, cls3);
                        com.badlogic.gdx.utils.Constructor bVar = (com.badlogic.gdx.utils.b) obj;
                        int i4 = bVar.f1767c;
                        while (i2 < i4) {
                            this.writer.b(convertToString(bVar.f1765a[i2]));
                            writeValue(bVar.f1766b[i2], cls2, (Class) null);
                            i2++;
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof Map) {
                        if (cls3 == null) {
                            cls3 = HashMap.class;
                        }
                        writeObjectStart(superclass, cls3);
                        for (Map.Entry entry : ((Map) obj).entrySet()) {
                            this.writer.b(convertToString(entry.getKey()));
                            writeValue(entry.getValue(), cls2, (Class) null);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (Enum.class.isAssignableFrom(superclass)) {
                        if (this.typeName != null && (cls3 == null || cls3 != superclass)) {
                            if (superclass.getEnumConstants() == null) {
                                superclass = superclass.getSuperclass();
                            }
                            writeObjectStart(superclass, null);
                            this.writer.b("value");
                            this.writer.h(((Enum) obj).name());
                            writeObjectEnd();
                            return;
                        }
                        this.writer.h(((Enum) obj).name());
                        return;
                    }
                    writeObjectStart(superclass, cls3);
                    writeFields(obj);
                    writeObjectEnd();
                    return;
                }
                writeObjectStart(superclass, null);
                writeValue("value", obj);
                writeObjectEnd();
                return;
            }
            this.writer.h(obj);
        } catch (IOException e2) {
            throw new SerializationException (e2);
        }
    }

    public void toJson(Object obj, Writer writer) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, writer);
    }

    public void toJson(Object obj, Class cls, Writer writer) {
        toJson(obj, cls, (Class) null, writer);
    }

    public void toJson(Object obj, Class cls, Class cls2, Writer writer) {
        setWriter(writer);
        try {
            writeValue(obj, cls, cls2);
        } finally {
            StreamUtils.a(this.writer);
            this.writer = null;
        }
    }

    public <T> T fromJson(Class<T> cls, char[] cArr, int i2, int i3) {
        return (T) readValue(cls, (Class) null, new JsonReader ().f(cArr, i2, i3));
    }

    public <T> T fromJson(Class<T> cls, Class cls2, char[] cArr, int i2, int i3) {
        return (T) readValue(cls, cls2, new JsonReader ().f(cArr, i2, i3));
    }

    public <T> T fromJson(Class<T> cls, String str) {
        return (T) readValue(cls, (Class) null, new JsonReader ().e(str));
    }

    public <T> T fromJson(Class<T> cls, Class cls2, String str) {
        return (T) readValue(cls, cls2, new JsonReader ().e(str));
    }
}

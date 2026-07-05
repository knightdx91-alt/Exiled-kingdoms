package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.q;
import com.badlogic.gdx.utils.r;
import com.badlogic.gdx.utils.t;
import com.badlogic.gdx.utils.u;
import com.badlogic.gdx.utils.v;
import com.badlogic.gdx.utils.w;
import com.badlogic.gdx.utils.x;
import com.badlogic.gdx.utils.y;
import com.badlogic.gdx.utils.z;
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
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Json {
    private static final boolean debug = false;
    private final y<Class, Object[]> classToDefaultValues;
    private final y<Class, c> classToSerializer;
    private final y<Class, String> classToTag;
    private c defaultSerializer;
    private boolean enumNames;
    private final Object[] equals1;
    private final Object[] equals2;
    private boolean ignoreDeprecated;
    private boolean ignoreUnknownFields;
    private u.b outputType;
    private boolean quoteLongValues;
    private boolean readDeprecated;
    private boolean sortFields;
    private final y<String, Class> tagToClass;
    private String typeName;
    private final y<Class, a0<String, a>> typeToFields;
    private boolean usePrototypes;
    private u writer;

    public static abstract class ReadOnlySerializer<T> implements c<T> {
        @Override // com.badlogic.gdx.utils.Json.c
        public abstract T read(Json json, t tVar, Class cls);

        @Override // com.badlogic.gdx.utils.Json.c
        public void write(Json json, T t2, Class cls) {
        }
    }

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h0.c f1746a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Class f1747b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1748c;

        public a(h0.c cVar) {
            this.f1746a = cVar;
            this.f1747b = cVar.c((y.class.isAssignableFrom(cVar.e()) || Map.class.isAssignableFrom(cVar.e())) ? 1 : 0);
            this.f1748c = cVar.g();
        }
    }

    public interface b {
        void read(Json json, t tVar);

        void write(Json json);
    }

    public interface c<T> {
        T read(Json json, t tVar, Class cls);

        void write(Json json, T t2, Class cls);
    }

    public Json() {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new y<>();
        this.tagToClass = new y<>();
        this.classToTag = new y<>();
        this.classToSerializer = new y<>();
        this.classToDefaultValues = new y<>();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = u.b.f1986b;
    }

    private String convertToString(Enum r2) {
        return this.enumNames ? r2.name() : r2.toString();
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
            a0<String, a> fields = getFields(cls);
            Object[] objArr = new Object[fields.f2043a];
            this.classToDefaultValues.j(cls, objArr);
            com.badlogic.gdx.utils.a<String> aVar = fields.f1761o;
            int i2 = aVar.f1750b;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                a aVarD = fields.d(aVar.get(i4));
                if (!this.ignoreDeprecated || !aVarD.f1748c) {
                    h0.c cVar = aVarD.f1746a;
                    int i5 = i3 + 1;
                    try {
                        objArr[i3] = cVar.a(objNewInstance);
                        i3 = i5;
                    } catch (h0 e2) {
                        e2.a(cVar + " (" + cls.getName() + ")");
                        throw e2;
                    } catch (h0.e e3) {
                        throw new h0("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
                    } catch (RuntimeException e4) {
                        h0 h0Var = new h0(e4);
                        h0Var.a(cVar + " (" + cls.getName() + ")");
                        throw h0Var;
                    }
                }
            }
            return objArr;
        } catch (Exception unused) {
            this.classToDefaultValues.j(cls, null);
            return null;
        }
    }

    private a0<String, a> getFields(Class cls) {
        a0<String, a> a0VarD = this.typeToFields.d(cls);
        if (a0VarD != null) {
            return a0VarD;
        }
        com.badlogic.gdx.utils.a aVar = new com.badlogic.gdx.utils.a();
        for (Class superclass = cls; superclass != Object.class; superclass = superclass.getSuperclass()) {
            aVar.a(superclass);
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = aVar.f1750b - 1; i2 >= 0; i2--) {
            Collections.addAll(arrayList, h0.a.d((Class) aVar.get(i2)));
        }
        a0<String, a> a0Var = new a0<>(arrayList.size());
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            h0.c cVar = (h0.c) arrayList.get(i3);
            if (!cVar.j() && !cVar.h() && !cVar.i()) {
                if (cVar.f()) {
                    a0Var.j(cVar.d(), new a(cVar));
                } else {
                    try {
                        cVar.l();
                        a0Var.j(cVar.d(), new a(cVar));
                    } catch (AccessControlException unused) {
                    }
                }
            }
        }
        if (this.sortFields) {
            com.badlogic.gdx.utils.a<String> aVar2 = a0Var.f1761o;
            aVar2.getClass();
            l0.a().c(aVar2.f1749a, aVar2.f1750b);
        }
        this.typeToFields.j(cls, a0Var);
        return a0Var;
    }

    public void addClassTag(String str, Class cls) {
        this.tagToClass.j(str, cls);
        this.classToTag.j(cls, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void copyFields(Object obj, Object obj2) {
        a0<String, a> fields = getFields(obj2.getClass());
        y.a<String, a> aVarB = getFields(obj.getClass()).b();
        while (aVarB.hasNext()) {
            y.b next = aVarB.next();
            a aVarD = fields.d(next.f2057a);
            h0.c cVar = ((a) next.f2058b).f1746a;
            if (aVarD == null) {
                throw new h0("To object is missing field: " + ((String) next.f2057a));
            }
            try {
                aVarD.f1746a.k(obj2, cVar.a(obj));
            } catch (h0.e e2) {
                throw new h0("Error copying field: " + cVar.d(), e2);
            }
        }
    }

    public <T> T fromJson(Class<T> cls, Reader reader) {
        return (T) readValue(cls, (Class) null, new s().d(reader));
    }

    public Class getClass(String str) {
        return this.tagToClass.d(str);
    }

    public boolean getIgnoreUnknownFields() {
        return this.ignoreUnknownFields;
    }

    public <T> c<T> getSerializer(Class<T> cls) {
        return this.classToSerializer.d(cls);
    }

    public String getTag(Class cls) {
        return this.classToTag.d(cls);
    }

    public u getWriter() {
        return this.writer;
    }

    protected boolean ignoreUnknownField(Class cls, String str) {
        return false;
    }

    protected Object newInstance(Class cls) {
        try {
            return h0.a.f(cls);
        } catch (Exception e2) {
            e = e2;
            try {
                h0.b bVarC = h0.a.c(cls, new Class[0]);
                bVarC.c();
                return bVarC.b(new Object[0]);
            } catch (h0.e unused) {
                if (Enum.class.isAssignableFrom(cls)) {
                    if (cls.getEnumConstants() == null) {
                        cls = cls.getSuperclass();
                    }
                    return cls.getEnumConstants()[0];
                }
                if (cls.isArray()) {
                    throw new h0("Encountered JSON object when expected array of type: ".concat(cls.getName()), e);
                }
                if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                    throw new h0("Class cannot be created (missing no-arg constructor): ".concat(cls.getName()), e);
                }
                throw new h0("Class cannot be created (non-static member class): ".concat(cls.getName()), e);
            } catch (SecurityException unused2) {
                throw new h0("Error constructing instance of class: ".concat(cls.getName()), e);
            } catch (Exception e3) {
                e = e3;
                throw new h0("Error constructing instance of class: ".concat(cls.getName()), e);
            }
        }
    }

    public String prettyPrint(Object obj) {
        return prettyPrint(obj, 0);
    }

    public void readField(Object obj, String str, t tVar) {
        readField(obj, str, str, (Class) null, tVar);
    }

    public void readFields(Object obj, t tVar) {
        Class<?> cls = obj.getClass();
        a0<String, a> fields = getFields(cls);
        for (t tVar2 = tVar.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
            a aVarD = fields.d(tVar2.f1957e.replace(" ", "_"));
            if (aVarD == null) {
                if (!tVar2.f1957e.equals(this.typeName) && !this.ignoreUnknownFields && !ignoreUnknownField(cls, tVar2.f1957e)) {
                    h0 h0Var = new h0("Field not found: " + tVar2.f1957e + " (" + cls.getName() + ")");
                    h0Var.a(tVar2.B());
                    throw h0Var;
                }
            } else if (!this.ignoreDeprecated || this.readDeprecated || !aVarD.f1748c) {
                h0.c cVar = aVarD.f1746a;
                try {
                    cVar.k(obj, readValue(cVar.e(), aVarD.f1747b, tVar2));
                } catch (h0 e2) {
                    e2.a(cVar.d() + " (" + cls.getName() + ")");
                    throw e2;
                } catch (h0.e e3) {
                    throw new h0("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
                } catch (RuntimeException e4) {
                    h0 h0Var2 = new h0(e4);
                    h0Var2.a(tVar2.B());
                    h0Var2.a(cVar.d() + " (" + cls.getName() + ")");
                    throw h0Var2;
                }
            }
        }
    }

    public <T> T readValue(String str, Class<T> cls, t tVar) {
        return (T) readValue(cls, (Class) null, tVar.k(str));
    }

    public void setDefaultSerializer(c cVar) {
        this.defaultSerializer = cVar;
    }

    public void setDeprecated(Class cls, String str, boolean z2) {
        a aVarD = getFields(cls).d(str);
        if (aVarD != null) {
            aVarD.f1748c = z2;
            return;
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls.getName());
        sbU.append(")");
        throw new h0(sbU.toString());
    }

    public void setElementType(Class cls, String str, Class cls2) {
        a aVarD = getFields(cls).d(str);
        if (aVarD != null) {
            aVarD.f1747b = cls2;
            return;
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls.getName());
        sbU.append(")");
        throw new h0(sbU.toString());
    }

    public void setEnumNames(boolean z2) {
        this.enumNames = z2;
    }

    public void setIgnoreDeprecated(boolean z2) {
        this.ignoreDeprecated = z2;
    }

    public void setIgnoreUnknownFields(boolean z2) {
        this.ignoreUnknownFields = z2;
    }

    public void setOutputType(u.b bVar) {
        this.outputType = bVar;
    }

    public void setQuoteLongValues(boolean z2) {
        this.quoteLongValues = z2;
    }

    public void setReadDeprecated(boolean z2) {
        this.readDeprecated = z2;
    }

    public <T> void setSerializer(Class<T> cls, c<T> cVar) {
        this.classToSerializer.j(cls, cVar);
    }

    public void setSortFields(boolean z2) {
        this.sortFields = z2;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public void setUsePrototypes(boolean z2) {
        this.usePrototypes = z2;
    }

    public void setWriter(Writer writer) {
        if (!(writer instanceof u)) {
            writer = new u(writer);
        }
        u uVar = (u) writer;
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
            throw new h0(e2);
        }
    }

    public void writeArrayStart(String str) {
        try {
            this.writer.b(str);
            this.writer.a();
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void writeField(Object obj, String str) {
        writeField(obj, str, str, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007b, code lost:
    
        if (java.util.Arrays.deepEquals(r0, r5) != false) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writeFields(Object obj) {
        Object[] objArr;
        a0<String, a> a0Var;
        Class<?> cls = obj.getClass();
        Object[] defaultValues = getDefaultValues(cls);
        a0<String, a> fields = getFields(cls);
        com.badlogic.gdx.utils.a<String> aVar = fields.f1761o;
        int i2 = aVar.f1750b;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            a aVarD = fields.d(aVar.get(i3));
            if (this.ignoreDeprecated && aVarD.f1748c) {
                objArr = defaultValues;
                a0Var = fields;
            } else {
                h0.c cVar = aVarD.f1746a;
                try {
                    Object objA = cVar.a(obj);
                    if (defaultValues != null) {
                        int i5 = i4 + 1;
                        Object obj2 = defaultValues[i4];
                        if (objA == null && obj2 == null) {
                            objArr = defaultValues;
                            a0Var = fields;
                            i4 = i5;
                        } else if (objA == null || obj2 == null) {
                            objArr = defaultValues;
                            a0Var = fields;
                            i4 = i5;
                        } else if (objA.equals(obj2)) {
                            objArr = defaultValues;
                            a0Var = fields;
                            i4 = i5;
                        } else {
                            if (objA.getClass().isArray() && obj2.getClass().isArray()) {
                                objArr = defaultValues;
                                Object[] objArr2 = this.equals1;
                                objArr2[0] = objA;
                                a0Var = fields;
                                Object[] objArr3 = this.equals2;
                                objArr3[0] = obj2;
                            }
                            i4 = i5;
                        }
                    } else {
                        objArr = defaultValues;
                        a0Var = fields;
                    }
                    this.writer.b(cVar.d());
                    writeValue(objA, cVar.e(), aVarD.f1747b);
                } catch (h0 e2) {
                    e2.a(cVar + " (" + cls.getName() + ")");
                    throw e2;
                } catch (h0.e e3) {
                    throw new h0("Error accessing field: " + cVar.d() + " (" + cls.getName() + ")", e3);
                } catch (Exception e4) {
                    h0 h0Var = new h0(e4);
                    h0Var.a(cVar + " (" + cls.getName() + ")");
                    throw h0Var;
                }
            }
            i3++;
            defaultValues = objArr;
            fields = a0Var;
        }
    }

    public void writeObjectEnd() {
        try {
            this.writer.d();
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void writeObjectStart(String str) {
        try {
            this.writer.b(str);
            writeObjectStart();
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void writeType(Class cls) {
        if (this.typeName == null) {
            return;
        }
        String tag = getTag(cls);
        if (tag == null) {
            tag = cls.getName();
        }
        try {
            u uVar = this.writer;
            uVar.b(this.typeName);
            uVar.h(tag);
        } catch (IOException e2) {
            throw new h0(e2);
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
            throw new h0(e2);
        }
    }

    private String convertToString(Object obj) {
        return obj instanceof Enum ? convertToString((Enum) obj) : obj instanceof Class ? ((Class) obj).getName() : String.valueOf(obj);
    }

    public <T> T fromJson(Class<T> cls, Class cls2, Reader reader) {
        return (T) readValue(cls, cls2, new s().d(reader));
    }

    public String prettyPrint(String str) {
        return prettyPrint(str, 0);
    }

    public void readField(Object obj, String str, Class cls, t tVar) {
        readField(obj, str, str, cls, tVar);
    }

    public <T> T readValue(String str, Class<T> cls, T t2, t tVar) {
        t tVarK = tVar.k(str);
        return tVarK == null ? t2 : (T) readValue(cls, (Class) null, tVarK);
    }

    public String toJson(Object obj, Class cls) {
        return toJson(obj, cls, (Class) null);
    }

    public void writeField(Object obj, String str, Class cls) {
        writeField(obj, str, str, cls);
    }

    public <T> T fromJson(Class<T> cls, InputStream inputStream) {
        return (T) readValue(cls, (Class) null, new s().c(inputStream));
    }

    public String prettyPrint(Object obj, int i2) {
        return prettyPrint(toJson(obj), i2);
    }

    public void readField(Object obj, String str, String str2, t tVar) {
        readField(obj, str, str2, (Class) null, tVar);
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
        return (T) readValue(cls, cls2, new s().c(inputStream));
    }

    public String prettyPrint(String str, int i2) {
        t tVarE = new s().e(str);
        u.b bVar = this.outputType;
        tVarE.getClass();
        t.b bVar2 = new t.b();
        bVar2.f1966a = bVar;
        bVar2.f1967b = i2;
        return tVarE.w(bVar2);
    }

    public void readField(Object obj, String str, String str2, Class cls, t tVar) {
        Class<?> cls2 = obj.getClass();
        a aVarD = getFields(cls2).d(str);
        if (aVarD != null) {
            if (cls == null) {
                cls = aVarD.f1747b;
            }
            readField(obj, aVarD.f1746a, str2, cls, tVar);
            return;
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls2.getName());
        sbU.append(")");
        throw new h0(sbU.toString());
    }

    public <T> T readValue(String str, Class<T> cls, Class cls2, t tVar) {
        return (T) readValue(cls, cls2, tVar.k(str));
    }

    public void writeArrayStart() {
        try {
            this.writer.a();
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void writeField(Object obj, String str, String str2, Class cls) {
        Class<?> cls2 = obj.getClass();
        a aVarD = getFields(cls2).d(str);
        if (aVarD != null) {
            h0.c cVar = aVarD.f1746a;
            if (cls == null) {
                cls = aVarD.f1747b;
            }
            try {
                this.writer.b(str2);
                writeValue(cVar.a(obj), cVar.e(), cls);
                return;
            } catch (h0 e2) {
                e2.a(cVar + " (" + cls2.getName() + ")");
                throw e2;
            } catch (h0.e e3) {
                throw new h0("Error accessing field: " + cVar.d() + " (" + cls2.getName() + ")", e3);
            } catch (Exception e4) {
                h0 h0Var = new h0(e4);
                h0Var.a(cVar + " (" + cls2.getName() + ")");
                throw h0Var;
            }
        }
        StringBuilder sbU = a.a.u("Field not found: ", str, " (");
        sbU.append(cls2.getName());
        sbU.append(")");
        throw new h0(sbU.toString());
    }

    public void writeObjectStart(String str, Class cls, Class cls2) {
        try {
            this.writer.b(str);
            writeObjectStart(cls, cls2);
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public <T> T fromJson(Class<T> cls, com.badlogic.gdx.files.a aVar) {
        try {
            return (T) readValue(cls, (Class) null, new s().a(aVar));
        } catch (Exception e2) {
            throw new h0(a.a.i(aVar, "Error reading file: "), e2);
        }
    }

    public <T> T readValue(String str, Class<T> cls, Class cls2, T t2, t tVar) {
        return (T) readValue(cls, cls2, t2, tVar.k(str));
    }

    public void writeValue(String str, Object obj, Class cls) {
        try {
            this.writer.b(str);
            writeValue(obj, cls, (Class) null);
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void toJson(Object obj, com.badlogic.gdx.files.a aVar) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, aVar);
    }

    public <T> T readValue(Class<T> cls, Class cls2, T t2, t tVar) {
        return tVar == null ? t2 : (T) readValue(cls, cls2, tVar);
    }

    public void toJson(Object obj, Class cls, com.badlogic.gdx.files.a aVar) {
        toJson(obj, cls, (Class) null, aVar);
    }

    public void writeObjectStart() {
        try {
            this.writer.c();
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public <T> T readValue(Class<T> cls, t tVar) {
        return (T) readValue(cls, (Class) null, tVar);
    }

    public void toJson(Object obj, Class cls, Class cls2, com.badlogic.gdx.files.a aVar) {
        Writer writer = null;
        try {
            try {
                writer = aVar.writer(false, "UTF-8");
                toJson(obj, cls, cls2, writer);
            } catch (Exception e2) {
                throw new h0("Error writing file: " + aVar, e2);
            }
        } finally {
            n0.a(writer);
        }
    }

    public void writeValue(String str, Object obj, Class cls, Class cls2) {
        try {
            this.writer.b(str);
            writeValue(obj, cls, cls2);
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:260:0x041f  */
    /* JADX WARN: Type inference failed for: r4v35, types: [T, java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v36, types: [T, com.badlogic.gdx.utils.b] */
    /* JADX WARN: Type inference failed for: r4v37, types: [T, com.badlogic.gdx.utils.r] */
    /* JADX WARN: Type inference failed for: r4v38, types: [T, com.badlogic.gdx.utils.v] */
    /* JADX WARN: Type inference failed for: r4v39, types: [T, com.badlogic.gdx.utils.q] */
    /* JADX WARN: Type inference failed for: r4v40, types: [T, com.badlogic.gdx.utils.z] */
    /* JADX WARN: Type inference failed for: r4v41, types: [T, com.badlogic.gdx.utils.w] */
    /* JADX WARN: Type inference failed for: r4v42, types: [T, com.badlogic.gdx.utils.x] */
    /* JADX WARN: Type inference failed for: r4v43, types: [T, com.badlogic.gdx.utils.y] */
    /* JADX WARN: Type inference failed for: r6v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r8v5, types: [K[], java.lang.Object[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> T readValue(Class<T> cls, Class cls2, t tVar) {
        Class cls3;
        t tVar2;
        Class cls4;
        Class cls5;
        t tVar3;
        int i2;
        Class clsA;
        if (tVar == 0) {
            return null;
        }
        if (tVar.t()) {
            String str = this.typeName;
            String strO = str == null ? null : tVar.o(str, null);
            if (strO != null) {
                Class cls6 = getClass(strO);
                if (cls6 == null) {
                    try {
                        clsA = h0.a.a(strO);
                    } catch (h0.e e2) {
                        throw new h0(e2);
                    }
                } else {
                    clsA = cls6;
                }
            } else {
                clsA = cls;
            }
            if (clsA == null) {
                c cVar = this.defaultSerializer;
                return cVar != null ? (T) cVar.read(this, tVar, clsA) : tVar;
            }
            if (this.typeName != null && Collection.class.isAssignableFrom(clsA)) {
                t tVarK = tVar.k("items");
                if (tVarK == null) {
                    throw new h0("Unable to convert object to collection: " + tVarK + " (" + clsA.getName() + ")");
                }
                tVar2 = tVarK;
                cls4 = clsA;
                cls3 = cls2;
            } else {
                c cVarD = this.classToSerializer.d(clsA);
                if (cVarD != null) {
                    return (T) cVarD.read(this, tVar, clsA);
                }
                if (clsA != String.class && clsA != Integer.class && clsA != Boolean.class && clsA != Float.class && clsA != Long.class && clsA != Double.class && clsA != Short.class && clsA != Byte.class && clsA != Character.class && !Enum.class.isAssignableFrom(clsA)) {
                    T t2 = (T) newInstance(clsA);
                    if (t2 instanceof b) {
                        ((b) t2).read(this, tVar);
                        return t2;
                    }
                    if (t2 instanceof y) {
                        ?? r4 = (T) ((y) t2);
                        for (t tVar4 = tVar.f1958f; tVar4 != null; tVar4 = tVar4.f1960h) {
                            r4.j(tVar4.f1957e, readValue(cls2, (Class) null, tVar4));
                        }
                        return r4;
                    }
                    if (t2 instanceof x) {
                        ?? r42 = (T) ((x) t2);
                        for (t tVar5 = tVar.f1958f; tVar5 != null; tVar5 = tVar5.f1960h) {
                            r42.e(((Integer) readValue(Integer.class, (Class) null, tVar5)).intValue(), tVar5.f1957e);
                        }
                        return r42;
                    }
                    if (t2 instanceof w) {
                        ?? r43 = (T) ((w) t2);
                        for (t tVar6 = tVar.f1958f; tVar6 != null; tVar6 = tVar6.f1960h) {
                            String str2 = tVar6.f1957e;
                            float fFloatValue = ((Float) readValue(Float.class, (Class) null, tVar6)).floatValue();
                            int iB = r43.b(str2);
                            if (iB >= 0) {
                                r43.f2012c[iB] = fFloatValue;
                            } else {
                                int i3 = -(iB + 1);
                                K[] kArr = r43.f2011b;
                                kArr[i3] = str2;
                                r43.f2012c[i3] = fFloatValue;
                                int i4 = r43.f2010a + 1;
                                r43.f2010a = i4;
                                if (i4 >= r43.f2013d) {
                                    int length = kArr.length << 1;
                                    int length2 = kArr.length;
                                    r43.f2013d = (int) (length * 0.8f);
                                    int i5 = length - 1;
                                    r43.f2015f = i5;
                                    r43.f2014e = Long.numberOfLeadingZeros(i5);
                                    Object[] objArr = r43.f2011b;
                                    float[] fArr = r43.f2012c;
                                    r43.f2011b = new Object[length];
                                    r43.f2012c = new float[length];
                                    if (r43.f2010a > 0) {
                                        for (int i6 = 0; i6 < length2; i6++) {
                                            Object obj = objArr[i6];
                                            if (obj != null) {
                                                float f2 = fArr[i6];
                                                Object[] objArr2 = r43.f2011b;
                                                int iHashCode = (int) ((((long) obj.hashCode()) * (-7046029254386353131L)) >>> r43.f2014e);
                                                while (objArr2[iHashCode] != null) {
                                                    iHashCode = (iHashCode + 1) & r43.f2015f;
                                                }
                                                objArr2[iHashCode] = obj;
                                                r43.f2012c[iHashCode] = f2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return r43;
                    }
                    if (t2 instanceof z) {
                        ?? r44 = (T) ((z) t2);
                        t tVarK2 = tVar.k("values");
                        for (t tVar7 = tVarK2 == null ? null : tVarK2.f1958f; tVar7 != null; tVar7 = tVar7.f1960h) {
                            r44.add(readValue(cls2, (Class) null, tVar7));
                        }
                        return r44;
                    }
                    if (t2 instanceof q) {
                        ?? r45 = (T) ((q) t2);
                        for (t tVar8 = tVar.f1958f; tVar8 != null; tVar8 = tVar8.f1960h) {
                            r45.c(Integer.parseInt(tVar8.f1957e), readValue(cls2, (Class) null, tVar8));
                        }
                        return r45;
                    }
                    if (t2 instanceof v) {
                        ?? r46 = (T) ((v) t2);
                        for (t tVar9 = tVar.f1958f; tVar9 != null; tVar9 = tVar9.f1960h) {
                            r46.d(Long.parseLong(tVar9.f1957e), readValue(cls2, (Class) null, tVar9));
                        }
                        return r46;
                    }
                    if (t2 instanceof r) {
                        ?? r47 = (T) ((r) t2);
                        t tVarK3 = tVar.k("values");
                        for (t tVar10 = tVarK3 == null ? null : tVarK3.f1958f; tVar10 != null; tVar10 = tVar10.f1960h) {
                            r47.a(tVar10.f());
                        }
                        return r47;
                    }
                    if (t2 instanceof com.badlogic.gdx.utils.b) {
                        ?? r48 = (T) ((com.badlogic.gdx.utils.b) t2);
                        for (t tVar11 = tVar.f1958f; tVar11 != null; tVar11 = tVar11.f1960h) {
                            r48.c(tVar11.f1957e, readValue(cls2, (Class) null, tVar11));
                        }
                        return r48;
                    }
                    if (t2 instanceof Map) {
                        ?? r49 = (T) ((Map) t2);
                        for (t tVar12 = tVar.f1958f; tVar12 != null; tVar12 = tVar12.f1960h) {
                            if (!tVar12.f1957e.equals(this.typeName)) {
                                r49.put(tVar12.f1957e, readValue(cls2, (Class) null, tVar12));
                            }
                        }
                        return r49;
                    }
                    readFields(t2, tVar);
                    return t2;
                }
                return (T) readValue("value", clsA, tVar);
            }
        } else {
            cls3 = cls2;
            tVar2 = tVar;
            cls4 = cls;
        }
        if (cls4 != null) {
            c cVarD2 = this.classToSerializer.d(cls4);
            if (cVarD2 != null) {
                return (T) cVarD2.read(this, tVar2, cls4);
            }
            if (b.class.isAssignableFrom(cls4)) {
                T t3 = (T) newInstance(cls4);
                ((b) t3).read(this, tVar2);
                return t3;
            }
        }
        if (tVar2.q()) {
            if (cls4 == null || cls4 == Object.class) {
                cls4 = com.badlogic.gdx.utils.a.class;
            }
            if (com.badlogic.gdx.utils.a.class.isAssignableFrom(cls4)) {
                Object obj2 = cls4 == com.badlogic.gdx.utils.a.class ? (T) new com.badlogic.gdx.utils.a() : (T) ((com.badlogic.gdx.utils.a) newInstance(cls4));
                for (t tVar13 = tVar2.f1958f; tVar13 != null; tVar13 = tVar13.f1960h) {
                    ((com.badlogic.gdx.utils.a) obj2).a(readValue(cls3, (Class) null, tVar13));
                }
                return (T) obj2;
            }
            if (e0.class.isAssignableFrom(cls4)) {
                Object obj3 = cls4 == e0.class ? (T) new e0() : (T) ((e0) newInstance(cls4));
                for (t tVar14 = tVar2.f1958f; tVar14 != null; tVar14 = tVar14.f1960h) {
                    Object value = readValue((Class<Object>) cls3, (Class) null, tVar14);
                    Object[] objArr3 = (T[]) ((e0) obj3).f1785a;
                    int i7 = ((e0) obj3).f1788d;
                    int length3 = objArr3.length;
                    Object[] objArr4 = objArr3;
                    if (i7 == length3) {
                        int length4 = objArr3.length << 1;
                        int i8 = ((e0) obj3).f1786b;
                        int i9 = ((e0) obj3).f1787c;
                        T[] tArr = (T[]) ((Object[]) Array.newInstance(objArr3.getClass().getComponentType(), length4));
                        if (i8 < i9) {
                            i2 = 0;
                            System.arraycopy(objArr3, i8, tArr, 0, i9 - i8);
                        } else {
                            i2 = 0;
                            if (((e0) obj3).f1788d > 0) {
                                int length5 = objArr3.length - i8;
                                System.arraycopy(objArr3, i8, tArr, 0, length5);
                                System.arraycopy(objArr3, 0, tArr, length5, i9);
                            }
                        }
                        ((e0) obj3).f1785a = tArr;
                        ((e0) obj3).f1786b = i2;
                        ((e0) obj3).f1787c = ((e0) obj3).f1788d;
                        objArr4 = tArr;
                    }
                    int i10 = ((e0) obj3).f1787c;
                    int i11 = i10 + 1;
                    ((e0) obj3).f1787c = i11;
                    objArr4[i10] = value;
                    if (i11 == objArr4.length) {
                        ((e0) obj3).f1787c = 0;
                    }
                    ((e0) obj3).f1788d++;
                }
                return (T) obj3;
            }
            if (Collection.class.isAssignableFrom(cls4)) {
                T t4 = cls4.isInterface() ? (T) new ArrayList() : (T) ((Collection) newInstance(cls4));
                for (t tVar15 = tVar2.f1958f; tVar15 != null; tVar15 = tVar15.f1960h) {
                    ((Collection) t4).add(readValue(cls3, (Class) null, tVar15));
                }
                return t4;
            }
            if (cls4.isArray()) {
                Class<?> componentType = cls4.getComponentType();
                if (cls3 == null) {
                    cls3 = componentType;
                }
                T t5 = (T) Array.newInstance(componentType, tVar2.f1962j);
                t tVar16 = tVar2.f1958f;
                int i12 = 0;
                while (tVar16 != null) {
                    Array.set(t5, i12, readValue(cls3, (Class) null, tVar16));
                    tVar16 = tVar16.f1960h;
                    i12++;
                }
                return t5;
            }
            throw new h0("Unable to convert value to required type: " + tVar2 + " (" + cls4.getName() + ")");
        }
        boolean zS = tVar2.s();
        t tVar17 = tVar2;
        if (zS) {
            if (cls4 != null) {
                if (cls4 != Float.TYPE && cls4 != Float.class) {
                    if (cls4 != Integer.TYPE && cls4 != Integer.class) {
                        if (cls4 != Long.TYPE && cls4 != Long.class) {
                            if (cls4 != Double.TYPE && cls4 != Double.class) {
                                if (cls4 == String.class) {
                                    return (T) tVar2.j();
                                }
                                if (cls4 != Short.TYPE && cls4 != Short.class) {
                                    if (cls4 == Byte.TYPE || cls4 == Byte.class) {
                                        return (T) Byte.valueOf(tVar2.b());
                                    }
                                    tVar17 = new t(tVar2.j());
                                }
                                return (T) Short.valueOf(tVar2.h());
                            }
                            return (T) Double.valueOf(tVar2.c());
                        }
                        return (T) Long.valueOf(tVar2.g());
                    }
                    return (T) Integer.valueOf(tVar2.f());
                }
            }
            return (T) Float.valueOf(tVar2.d());
        }
        if (!tVar17.r()) {
            cls5 = Boolean.class;
            tVar3 = tVar17;
        } else if (cls4 != null) {
            try {
            } catch (NumberFormatException unused) {
                cls5 = Boolean.class;
            }
            if (cls4 != Boolean.TYPE) {
                cls5 = Boolean.class;
                if (cls4 == cls5) {
                }
                tVar3 = new t(tVar17.j());
            } else {
                cls5 = Boolean.class;
            }
            try {
                return (T) Boolean.valueOf(tVar17.a());
            } catch (NumberFormatException unused2) {
            }
        }
        if (!tVar3.u()) {
            return null;
        }
        ?? r6 = (T) tVar3.j();
        if (cls4 == null || cls4 == String.class) {
            return r6;
        }
        if (cls4 != Integer.TYPE && cls4 != Integer.class) {
            if (cls4 != Float.TYPE && cls4 != Float.class) {
                if (cls4 != Long.TYPE && cls4 != Long.class) {
                    if (cls4 != Double.TYPE && cls4 != Double.class) {
                        if (cls4 != Short.TYPE && cls4 != Short.class) {
                            if (cls4 == Byte.TYPE || cls4 == Byte.class) {
                                return (T) Byte.valueOf((String) r6);
                            }
                            if (cls4 != Boolean.TYPE && cls4 != cls5) {
                                if (cls4 != Character.TYPE && cls4 != Character.class) {
                                    if (Enum.class.isAssignableFrom(cls4)) {
                                        for (Object obj4 : (Enum[]) cls4.getEnumConstants()) {
                                            ?? r7 = (T) obj4;
                                            if (r6.equals(convertToString((Enum) r7))) {
                                                return r7;
                                            }
                                        }
                                    }
                                    if (cls4 == CharSequence.class) {
                                        return r6;
                                    }
                                    throw new h0("Unable to convert value to required type: " + tVar3 + " (" + cls4.getName() + ")");
                                }
                                return (T) Character.valueOf(r6.charAt(0));
                            }
                            return (T) Boolean.valueOf((String) r6);
                        }
                        return (T) Short.valueOf((String) r6);
                    }
                    return (T) Double.valueOf((String) r6);
                }
                return (T) Long.valueOf((String) r6);
            }
            return (T) Float.valueOf((String) r6);
        }
        return (T) Integer.valueOf((String) r6);
    }

    public void writeObjectStart(Class cls, Class cls2) {
        try {
            this.writer.c();
            if (cls2 == null || cls2 != cls) {
                writeType(cls);
            }
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public String prettyPrint(Object obj, t.b bVar) {
        return prettyPrint(toJson(obj), bVar);
    }

    public String prettyPrint(String str, t.b bVar) {
        return new s().e(str).w(bVar);
    }

    public void writeValue(Object obj) {
        if (obj == null) {
            writeValue(obj, (Class) null, (Class) null);
        } else {
            writeValue(obj, obj.getClass(), (Class) null);
        }
    }

    public Json(u.b bVar) {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new y<>();
        this.tagToClass = new y<>();
        this.classToTag = new y<>();
        this.classToSerializer = new y<>();
        this.classToDefaultValues = new y<>();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = bVar;
    }

    public void writeValue(Object obj, Class cls) {
        writeValue(obj, cls, (Class) null);
    }

    public <T> T fromJson(Class<T> cls, Class cls2, com.badlogic.gdx.files.a aVar) {
        try {
            return (T) readValue(cls, cls2, new s().a(aVar));
        } catch (Exception e2) {
            throw new h0(a.a.i(aVar, "Error reading file: "), e2);
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
                    if (obj instanceof b) {
                        writeObjectStart(superclass, cls3);
                        ((b) obj).write(this);
                        writeObjectEnd();
                        return;
                    }
                    c cVarD = this.classToSerializer.d(superclass);
                    if (cVarD != null) {
                        cVarD.write(this, obj, cls3);
                        return;
                    }
                    int i2 = 0;
                    if (obj instanceof com.badlogic.gdx.utils.a) {
                        if (cls3 != null && superclass != cls3 && superclass != com.badlogic.gdx.utils.a.class) {
                            throw new h0("Serialization of an Array other than the known type is not supported.\nKnown type: " + cls3 + "\nActual type: " + superclass);
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
                    if (obj instanceof e0) {
                        if (cls3 != null && superclass != cls3 && superclass != e0.class) {
                            throw new h0("Serialization of a Queue other than the known type is not supported.\nKnown type: " + cls3 + "\nActual type: " + superclass);
                        }
                        writeArrayStart();
                        e0 e0Var = (e0) obj;
                        int i4 = e0Var.f1788d;
                        while (i2 < i4) {
                            writeValue(e0Var.get(i2), cls2, (Class) null);
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
                        y.a aVarB = ((y) obj).b();
                        aVarB.getClass();
                        while (aVarB.hasNext()) {
                            y.b next = aVarB.next();
                            this.writer.b(convertToString(next.f2057a));
                            writeValue(next.f2058b, cls2, (Class) null);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof x) {
                        if (cls3 == null) {
                            cls3 = x.class;
                        }
                        writeObjectStart(superclass, cls3);
                        x.a aVarB2 = ((x) obj).b();
                        while (aVarB2.hasNext()) {
                            x.b bVar = (x.b) aVarB2.next();
                            this.writer.b(convertToString(bVar.f2035a));
                            writeValue(Integer.valueOf(bVar.f2036b), Integer.class);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof w) {
                        if (cls3 == null) {
                            cls3 = w.class;
                        }
                        writeObjectStart(superclass, cls3);
                        w.a aVarA = ((w) obj).a();
                        while (aVarA.hasNext()) {
                            w.b bVar2 = (w.b) aVarA.next();
                            this.writer.b(convertToString(bVar2.f2019a));
                            writeValue(Float.valueOf(bVar2.f2020b), Float.class);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof z) {
                        if (cls3 == null) {
                            cls3 = z.class;
                        }
                        writeObjectStart(superclass, cls3);
                        this.writer.b("values");
                        writeArrayStart();
                        z.a it3 = ((z) obj).iterator();
                        while (it3.hasNext()) {
                            writeValue(it3.next(), cls2, (Class) null);
                        }
                        writeArrayEnd();
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof q) {
                        if (cls3 == null) {
                            cls3 = q.class;
                        }
                        writeObjectStart(superclass, cls3);
                        q.a aVarA2 = ((q) obj).a();
                        while (aVarA2.hasNext()) {
                            q.b bVar3 = (q.b) aVarA2.next();
                            this.writer.b(String.valueOf(bVar3.f1895a));
                            writeValue(bVar3.f1896b, cls2, (Class) null);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof v) {
                        if (cls3 == null) {
                            cls3 = v.class;
                        }
                        writeObjectStart(superclass, cls3);
                        v.a aVarA3 = ((v) obj).a();
                        while (aVarA3.hasNext()) {
                            v.b bVar4 = (v.b) aVarA3.next();
                            this.writer.b(String.valueOf(bVar4.f2003a));
                            writeValue(bVar4.f2004b, cls2, (Class) null);
                        }
                        writeObjectEnd();
                        return;
                    }
                    if (obj instanceof r) {
                        if (cls3 == null) {
                            cls3 = r.class;
                        }
                        writeObjectStart(superclass, cls3);
                        this.writer.b("values");
                        writeArrayStart();
                        r.a aVarC = ((r) obj).c();
                        while (true) {
                            boolean z2 = aVarC.f1918a;
                            if (!z2) {
                                writeArrayEnd();
                                writeObjectEnd();
                                return;
                            }
                            if (z2) {
                                if (aVarC.f1921d) {
                                    int i5 = aVarC.f1920c;
                                    r rVar = aVarC.f1919b;
                                    int i6 = i5 == -1 ? 0 : rVar.f1911b[i5];
                                    int[] iArr = rVar.f1911b;
                                    int length2 = iArr.length;
                                    while (true) {
                                        int i7 = aVarC.f1920c + 1;
                                        aVarC.f1920c = i7;
                                        if (i7 < length2) {
                                            if (iArr[i7] != 0) {
                                                aVarC.f1918a = true;
                                                break;
                                            }
                                        } else {
                                            aVarC.f1918a = false;
                                            break;
                                        }
                                    }
                                    writeValue(Integer.valueOf(i6), Integer.class, (Class) null);
                                } else {
                                    throw new m("#iterator() cannot be used nested.");
                                }
                            } else {
                                throw new NoSuchElementException();
                            }
                        }
                    } else {
                        if (obj instanceof com.badlogic.gdx.utils.b) {
                            if (cls3 == null) {
                                cls3 = com.badlogic.gdx.utils.b.class;
                            }
                            writeObjectStart(superclass, cls3);
                            com.badlogic.gdx.utils.b bVar5 = (com.badlogic.gdx.utils.b) obj;
                            int i8 = bVar5.f1767c;
                            while (i2 < i8) {
                                this.writer.b(convertToString(bVar5.f1765a[i2]));
                                writeValue(bVar5.f1766b[i2], cls2, (Class) null);
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
                                this.writer.h(convertToString((Enum) obj));
                                writeObjectEnd();
                                return;
                            }
                            this.writer.h(convertToString((Enum) obj));
                            return;
                        }
                        writeObjectStart(superclass, cls3);
                        writeFields(obj);
                        writeObjectEnd();
                    }
                } else {
                    writeObjectStart(superclass, null);
                    writeValue("value", obj);
                    writeObjectEnd();
                }
            } else {
                this.writer.h(obj);
            }
        } catch (IOException e2) {
            throw new h0(e2);
        }
    }

    public void toJson(Object obj, Writer writer) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, writer);
    }

    public void readField(Object obj, h0.c cVar, String str, Class cls, t tVar) {
        t tVarK = tVar.k(str);
        if (tVarK == null) {
            return;
        }
        try {
            cVar.k(obj, readValue(cVar.e(), cls, tVarK));
        } catch (h0 e2) {
            e2.a(cVar.d() + " (" + cVar.b().getName() + ")");
            throw e2;
        } catch (h0.e e3) {
            throw new h0("Error accessing field: " + cVar.d() + " (" + cVar.b().getName() + ")", e3);
        } catch (RuntimeException e4) {
            h0 h0Var = new h0(e4);
            h0Var.a(tVarK.B());
            h0Var.a(cVar.d() + " (" + cVar.b().getName() + ")");
            throw h0Var;
        }
    }

    public void toJson(Object obj, Class cls, Writer writer) {
        toJson(obj, cls, (Class) null, writer);
    }

    public void toJson(Object obj, Class cls, Class cls2, Writer writer) {
        setWriter(writer);
        try {
            writeValue(obj, cls, cls2);
        } finally {
            n0.a(this.writer);
            this.writer = null;
        }
    }

    public <T> T fromJson(Class<T> cls, char[] cArr, int i2, int i3) {
        return (T) readValue(cls, (Class) null, new s().f(cArr, i2, i3));
    }

    public <T> T fromJson(Class<T> cls, Class cls2, char[] cArr, int i2, int i3) {
        return (T) readValue(cls, cls2, new s().f(cArr, i2, i3));
    }

    public <T> T fromJson(Class<T> cls, String str) {
        return (T) readValue(cls, (Class) null, new s().e(str));
    }

    public <T> T fromJson(Class<T> cls, Class cls2, String str) {
        return (T) readValue(cls, cls2, new s().e(str));
    }
}

package com.badlogic.gdx.graphics.glutils;

import a0.k;
import a0.q;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.x;
import com.badlogic.gdx.utils.y;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ShaderProgram implements i {
    public static final String BINORMAL_ATTRIBUTE = "a_binormal";
    public static final String BONEWEIGHT_ATTRIBUTE = "a_boneWeight";
    public static final String COLOR_ATTRIBUTE = "a_color";
    public static final String NORMAL_ATTRIBUTE = "a_normal";
    public static final String POSITION_ATTRIBUTE = "a_position";
    public static final String TANGENT_ATTRIBUTE = "a_tangent";
    public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
    public static boolean pedantic = true;
    public static String prependFragmentCode = "";
    public static String prependVertexCode = "";
    private String[] attributeNames;
    private final x<String> attributeSizes;
    private final x<String> attributeTypes;
    private final x<String> attributes;
    private int fragmentShaderHandle;
    private final String fragmentShaderSource;
    private boolean invalidated;
    private boolean isCompiled;
    private String log;
    private final FloatBuffer matrix;
    IntBuffer params;
    private int program;
    private int refCount;
    IntBuffer type;
    private String[] uniformNames;
    private final x<String> uniformSizes;
    private final x<String> uniformTypes;
    private final x<String> uniforms;
    private int vertexShaderHandle;
    private final String vertexShaderSource;
    private static final y<Application, a<ShaderProgram>> shaders = new y<>();
    static final IntBuffer intbuf = BufferUtils.g(1);

    public ShaderProgram(String str, String str2) {
        this.log = "";
        this.uniforms = new x<>();
        this.uniformTypes = new x<>();
        this.uniformSizes = new x<>();
        this.attributes = new x<>();
        this.attributeTypes = new x<>();
        this.attributeSizes = new x<>();
        this.refCount = 0;
        this.params = BufferUtils.g(1);
        this.type = BufferUtils.g(1);
        if (str == null) {
            throw new IllegalArgumentException("vertex shader must not be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("fragment shader must not be null");
        }
        String str3 = prependVertexCode;
        if (str3 != null && str3.length() > 0) {
            str = a.a.m(prependVertexCode, str, new StringBuilder());
        }
        String str4 = prependFragmentCode;
        if (str4 != null && str4.length() > 0) {
            str2 = a.a.m(prependFragmentCode, str2, new StringBuilder());
        }
        this.vertexShaderSource = str;
        this.fragmentShaderSource = str2;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(64);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.matrix = byteBufferAllocateDirect.asFloatBuffer();
        compileShaders(str, str2);
        if (isCompiled()) {
            fetchAttributes();
            fetchUniforms();
            addManagedShader(Gdx.app, this);
        }
    }

    private void addManagedShader(Application application, ShaderProgram shaderProgram) {
        y<Application, a<ShaderProgram>> yVar = shaders;
        a<ShaderProgram> aVarD = yVar.d(application);
        if (aVarD == null) {
            aVarD = new a<>();
        }
        aVarD.a(shaderProgram);
        yVar.j(application, aVarD);
    }

    private void checkManaged() {
        if (this.invalidated) {
            compileShaders(this.vertexShaderSource, this.fragmentShaderSource);
            this.invalidated = false;
        }
    }

    public static void clearAllShaderPrograms(Application application) {
        shaders.k(application);
    }

    private void compileShaders(String str, String str2) {
        this.vertexShaderHandle = loadShader(GL20.GL_VERTEX_SHADER, str);
        int iLoadShader = loadShader(GL20.GL_FRAGMENT_SHADER, str2);
        this.fragmentShaderHandle = iLoadShader;
        if (this.vertexShaderHandle == -1 || iLoadShader == -1) {
            this.isCompiled = false;
            return;
        }
        int iLinkProgram = linkProgram(createProgram());
        this.program = iLinkProgram;
        if (iLinkProgram == -1) {
            this.isCompiled = false;
        } else {
            this.isCompiled = true;
        }
    }

    private int fetchAttributeLocation(String str) {
        GL20 gl20 = Gdx.gl20;
        int iC = this.attributes.c(-2, str);
        if (iC != -2) {
            return iC;
        }
        int iGlGetAttribLocation = gl20.glGetAttribLocation(this.program, str);
        this.attributes.e(iGlGetAttribLocation, str);
        return iGlGetAttribLocation;
    }

    private void fetchAttributes() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, GL20.GL_ACTIVE_ATTRIBUTES, this.params);
        int i2 = this.params.get(0);
        this.attributeNames = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String strGlGetActiveAttrib = Gdx.gl20.glGetActiveAttrib(this.program, i3, this.params, this.type);
            this.attributes.e(Gdx.gl20.glGetAttribLocation(this.program, strGlGetActiveAttrib), strGlGetActiveAttrib);
            this.attributeTypes.e(this.type.get(0), strGlGetActiveAttrib);
            this.attributeSizes.e(this.params.get(0), strGlGetActiveAttrib);
            this.attributeNames[i3] = strGlGetActiveAttrib;
        }
    }

    private int fetchUniformLocation(String str) {
        return fetchUniformLocation(str, pedantic);
    }

    private void fetchUniforms() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, GL20.GL_ACTIVE_UNIFORMS, this.params);
        int i2 = this.params.get(0);
        this.uniformNames = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String strGlGetActiveUniform = Gdx.gl20.glGetActiveUniform(this.program, i3, this.params, this.type);
            this.uniforms.e(Gdx.gl20.glGetUniformLocation(this.program, strGlGetActiveUniform), strGlGetActiveUniform);
            this.uniformTypes.e(this.type.get(0), strGlGetActiveUniform);
            this.uniformSizes.e(this.params.get(0), strGlGetActiveUniform);
            this.uniformNames[i3] = strGlGetActiveUniform;
        }
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder("Managed shaders/app: { ");
        y.c<Application> cVarG = shaders.g();
        cVarG.getClass();
        while (cVarG.hasNext()) {
            sb.append(shaders.d(cVarG.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedShaderPrograms() {
        return shaders.d(Gdx.app).f1750b;
    }

    public static void invalidateAllShaderPrograms(Application application) {
        a<ShaderProgram> aVarD;
        if (Gdx.gl20 == null || (aVarD = shaders.d(application)) == null) {
            return;
        }
        for (int i2 = 0; i2 < aVarD.f1750b; i2++) {
            aVarD.get(i2).invalidated = true;
            aVarD.get(i2).checkManaged();
        }
    }

    private int linkProgram(int i2) {
        GL20 gl20 = Gdx.gl20;
        if (i2 == -1) {
            return -1;
        }
        gl20.glAttachShader(i2, this.vertexShaderHandle);
        gl20.glAttachShader(i2, this.fragmentShaderHandle);
        gl20.glLinkProgram(i2);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        IntBuffer intBufferAsIntBuffer = byteBufferAllocateDirect.asIntBuffer();
        gl20.glGetProgramiv(i2, GL20.GL_LINK_STATUS, intBufferAsIntBuffer);
        if (intBufferAsIntBuffer.get(0) != 0) {
            return i2;
        }
        this.log = Gdx.gl20.glGetProgramInfoLog(i2);
        return -1;
    }

    private int loadShader(int i2, String str) {
        GL20 gl20 = Gdx.gl20;
        IntBuffer intBufferG = BufferUtils.g(1);
        int iGlCreateShader = gl20.glCreateShader(i2);
        if (iGlCreateShader == 0) {
            return -1;
        }
        gl20.glShaderSource(iGlCreateShader, str);
        gl20.glCompileShader(iGlCreateShader);
        gl20.glGetShaderiv(iGlCreateShader, GL20.GL_COMPILE_STATUS, intBufferG);
        if (intBufferG.get(0) != 0) {
            return iGlCreateShader;
        }
        String strGlGetShaderInfoLog = gl20.glGetShaderInfoLog(iGlCreateShader);
        StringBuilder sb = new StringBuilder();
        sb.append(this.log);
        sb.append(i2 == 35633 ? "Vertex shader\n" : "Fragment shader:\n");
        this.log = sb.toString();
        this.log = a.a.m(this.log, strGlGetShaderInfoLog, new StringBuilder());
        return -1;
    }

    @Deprecated
    public void begin() {
        bind();
    }

    public void bind() {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUseProgram(this.program);
    }

    protected int createProgram() {
        int iGlCreateProgram = Gdx.gl20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            return iGlCreateProgram;
        }
        return -1;
    }

    public void disableVertexAttribute(String str) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int iFetchAttributeLocation = fetchAttributeLocation(str);
        if (iFetchAttributeLocation == -1) {
            return;
        }
        gl20.glDisableVertexAttribArray(iFetchAttributeLocation);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        gl20.glUseProgram(0);
        gl20.glDeleteShader(this.vertexShaderHandle);
        gl20.glDeleteShader(this.fragmentShaderHandle);
        gl20.glDeleteProgram(this.program);
        y<Application, a<ShaderProgram>> yVar = shaders;
        if (yVar.d(Gdx.app) != null) {
            yVar.d(Gdx.app).q(this, true);
        }
    }

    public void enableVertexAttribute(String str) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int iFetchAttributeLocation = fetchAttributeLocation(str);
        if (iFetchAttributeLocation == -1) {
            return;
        }
        gl20.glEnableVertexAttribArray(iFetchAttributeLocation);
    }

    @Deprecated
    public void end() {
    }

    public int getAttributeLocation(String str) {
        return this.attributes.c(-1, str);
    }

    public int getAttributeSize(String str) {
        return this.attributeSizes.c(0, str);
    }

    public int getAttributeType(String str) {
        return this.attributeTypes.c(0, str);
    }

    public String[] getAttributes() {
        return this.attributeNames;
    }

    public String getFragmentShaderSource() {
        return this.fragmentShaderSource;
    }

    public int getHandle() {
        return this.program;
    }

    public String getLog() {
        if (!this.isCompiled) {
            return this.log;
        }
        String strGlGetProgramInfoLog = Gdx.gl20.glGetProgramInfoLog(this.program);
        this.log = strGlGetProgramInfoLog;
        return strGlGetProgramInfoLog;
    }

    public int getUniformLocation(String str) {
        return this.uniforms.c(-1, str);
    }

    public int getUniformSize(String str) {
        return this.uniformSizes.c(0, str);
    }

    public int getUniformType(String str) {
        return this.uniformTypes.c(0, str);
    }

    public String[] getUniforms() {
        return this.uniformNames;
    }

    public String getVertexShaderSource() {
        return this.vertexShaderSource;
    }

    public boolean hasAttribute(String str) {
        return this.attributes.a(str);
    }

    public boolean hasUniform(String str) {
        return this.uniforms.a(str);
    }

    public boolean isCompiled() {
        return this.isCompiled;
    }

    public void setAttributef(String str, float f2, float f3, float f4, float f5) {
        Gdx.gl20.glVertexAttrib4f(fetchAttributeLocation(str), f2, f3, f4, f5);
    }

    public void setUniform1fv(String str, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1fv(fetchUniformLocation(str), i3, fArr, i2);
    }

    public void setUniform2fv(String str, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2fv(fetchUniformLocation(str), i3 / 2, fArr, i2);
    }

    public void setUniform3fv(String str, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3fv(fetchUniformLocation(str), i3 / 3, fArr, i2);
    }

    public void setUniform4fv(String str, float[] fArr, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4fv(fetchUniformLocation(str), i3 / 4, fArr, i2);
    }

    public void setUniformMatrix(String str, Matrix4 matrix4) {
        setUniformMatrix(str, matrix4, false);
    }

    public void setUniformMatrix3fv(String str, FloatBuffer floatBuffer, int i2, boolean z2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        floatBuffer.position(0);
        gl20.glUniformMatrix3fv(fetchUniformLocation(str), i2, z2, floatBuffer);
    }

    public void setUniformMatrix4fv(String str, FloatBuffer floatBuffer, int i2, boolean z2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        floatBuffer.position(0);
        gl20.glUniformMatrix4fv(fetchUniformLocation(str), i2, z2, floatBuffer);
    }

    public void setUniformf(String str, float f2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1f(fetchUniformLocation(str), f2);
    }

    public void setUniformi(String str, int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1i(fetchUniformLocation(str), i2);
    }

    public void setVertexAttribute(String str, int i2, int i3, boolean z2, int i4, Buffer buffer) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int iFetchAttributeLocation = fetchAttributeLocation(str);
        if (iFetchAttributeLocation == -1) {
            return;
        }
        gl20.glVertexAttribPointer(iFetchAttributeLocation, i2, i3, z2, i4, buffer);
    }

    public int fetchUniformLocation(String str, boolean z2) {
        int iC = this.uniforms.c(-2, str);
        if (iC == -2) {
            iC = Gdx.gl20.glGetUniformLocation(this.program, str);
            if (iC == -1 && z2) {
                if (this.isCompiled) {
                    throw new IllegalArgumentException(a.a.l("No uniform with name '", str, "' in shader"));
                }
                throw new IllegalStateException("An attempted fetch uniform from uncompiled shader \n" + getLog());
            }
            this.uniforms.e(iC, str);
        }
        return iC;
    }

    public void setUniformMatrix(String str, Matrix4 matrix4, boolean z2) {
        setUniformMatrix(fetchUniformLocation(str), matrix4, z2);
    }

    public void setUniformMatrix(int i2, Matrix4 matrix4) {
        setUniformMatrix(i2, matrix4, false);
    }

    public void setUniformMatrix(int i2, Matrix4 matrix4, boolean z2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix4fv(i2, 1, z2, matrix4.f1724a, 0);
    }

    public void disableVertexAttribute(int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glDisableVertexAttribArray(i2);
    }

    public void enableVertexAttribute(int i2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glEnableVertexAttribArray(i2);
    }

    public void setUniform1fv(int i2, float[] fArr, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1fv(i2, i4, fArr, i3);
    }

    public void setUniform2fv(int i2, float[] fArr, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2fv(i2, i4 / 2, fArr, i3);
    }

    public void setUniform3fv(int i2, float[] fArr, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3fv(i2, i4 / 3, fArr, i3);
    }

    public void setUniform4fv(int i2, float[] fArr, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4fv(i2, i4 / 4, fArr, i3);
    }

    public void setUniformf(int i2, float f2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1f(i2, f2);
    }

    public void setUniformi(int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform1i(i2, i3);
    }

    public void setVertexAttribute(int i2, int i3, int i4, boolean z2, int i5, Buffer buffer) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glVertexAttribPointer(i2, i3, i4, z2, i5, buffer);
    }

    public void setUniformMatrix4fv(int i2, float[] fArr, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix4fv(i2, i4 / 16, false, fArr, i3);
    }

    public void setUniformMatrix(String str, k kVar) {
        setUniformMatrix(str, kVar, false);
    }

    public void setUniformMatrix(String str, k kVar, boolean z2) {
        setUniformMatrix(fetchUniformLocation(str), kVar, z2);
    }

    public void setUniformf(String str, float f2, float f3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2f(fetchUniformLocation(str), f2, f3);
    }

    public void setUniformi(String str, int i2, int i3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2i(fetchUniformLocation(str), i2, i3);
    }

    public void setVertexAttribute(String str, int i2, int i3, boolean z2, int i4, int i5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        int iFetchAttributeLocation = fetchAttributeLocation(str);
        if (iFetchAttributeLocation == -1) {
            return;
        }
        gl20.glVertexAttribPointer(iFetchAttributeLocation, i2, i3, z2, i4, i5);
    }

    public void setUniformMatrix(int i2, k kVar) {
        setUniformMatrix(i2, kVar, false);
    }

    public void setUniformMatrix4fv(String str, float[] fArr, int i2, int i3) {
        setUniformMatrix4fv(fetchUniformLocation(str), fArr, i2, i3);
    }

    public void setUniformMatrix(int i2, k kVar, boolean z2) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniformMatrix3fv(i2, 1, z2, kVar.f71a, 0);
    }

    public void setUniformf(int i2, float f2, float f3) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2f(i2, f2, f3);
    }

    public void setUniformi(int i2, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform2i(i2, i3, i4);
    }

    public void setVertexAttribute(int i2, int i3, int i4, boolean z2, int i5, int i6) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glVertexAttribPointer(i2, i3, i4, z2, i5, i6);
    }

    public void setUniformf(String str, float f2, float f3, float f4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3f(fetchUniformLocation(str), f2, f3, f4);
    }

    public void setUniformi(String str, int i2, int i3, int i4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3i(fetchUniformLocation(str), i2, i3, i4);
    }

    public void setUniformf(int i2, float f2, float f3, float f4) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3f(i2, f2, f3, f4);
    }

    public void setUniformi(int i2, int i3, int i4, int i5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform3i(i2, i3, i4, i5);
    }

    public void setUniformf(String str, float f2, float f3, float f4, float f5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4f(fetchUniformLocation(str), f2, f3, f4, f5);
    }

    public void setUniformi(String str, int i2, int i3, int i4, int i5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4i(fetchUniformLocation(str), i2, i3, i4, i5);
    }

    public void setUniformf(int i2, float f2, float f3, float f4, float f5) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4f(i2, f2, f3, f4, f5);
    }

    public void setUniformi(int i2, int i3, int i4, int i5, int i6) {
        GL20 gl20 = Gdx.gl20;
        checkManaged();
        gl20.glUniform4i(i2, i3, i4, i5, i6);
    }

    public void setUniformf(String str, q qVar) {
        setUniformf(str, qVar.f91a, qVar.f92b);
    }

    public void setUniformf(int i2, q qVar) {
        setUniformf(i2, qVar.f91a, qVar.f92b);
    }

    public void setUniformf(String str, com.badlogic.gdx.math.a aVar) {
        setUniformf(str, aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public void setUniformf(int i2, com.badlogic.gdx.math.a aVar) {
        setUniformf(i2, aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public void setUniformf(String str, Color color) {
        setUniformf(str, color.f1680r, color.f1679g, color.f1678b, color.f1677a);
    }

    public void setUniformf(int i2, Color color) {
        setUniformf(i2, color.f1680r, color.f1679g, color.f1678b, color.f1677a);
    }

    public ShaderProgram(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2) {
        this(aVar.readString(), aVar2.readString());
    }
}

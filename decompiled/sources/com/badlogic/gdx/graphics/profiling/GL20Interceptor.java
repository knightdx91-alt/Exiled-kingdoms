package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GL20Interceptor extends GLInterceptor implements GL20 {
    protected final GL20 gl20;

    protected GL20Interceptor(GLProfiler gLProfiler, GL20 gl20) {
        super(gLProfiler);
        this.gl20 = gl20;
    }

    private void check() {
        int iGlGetError = this.gl20.glGetError();
        while (iGlGetError != 0) {
            this.glProfiler.getListener().onError(iGlGetError);
            iGlGetError = this.gl20.glGetError();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int i2) {
        this.calls++;
        this.gl20.glActiveTexture(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int i2, int i3) {
        this.calls++;
        this.gl20.glAttachShader(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int i2, int i3, String str) {
        this.calls++;
        this.gl20.glBindAttribLocation(i2, i3, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int i2, int i3) {
        this.calls++;
        this.gl20.glBindBuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i2, int i3) {
        this.calls++;
        this.gl20.glBindFramebuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i2, int i3) {
        this.calls++;
        this.gl20.glBindRenderbuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int i2, int i3) {
        this.textureBindings++;
        this.calls++;
        this.gl20.glBindTexture(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl20.glBlendColor(f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int i2) {
        this.calls++;
        this.gl20.glBlendEquation(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int i2, int i3) {
        this.calls++;
        this.gl20.glBlendEquationSeparate(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int i2, int i3) {
        this.calls++;
        this.gl20.glBlendFunc(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glBlendFuncSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int i2, int i3, Buffer buffer, int i4) {
        this.calls++;
        this.gl20.glBufferData(i2, i3, buffer, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int i2, int i3, int i4, Buffer buffer) {
        this.calls++;
        this.gl20.glBufferSubData(i2, i3, i4, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i2) {
        this.calls++;
        int iGlCheckFramebufferStatus = this.gl20.glCheckFramebufferStatus(i2);
        check();
        return iGlCheckFramebufferStatus;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClear(int i2) {
        this.calls++;
        this.gl20.glClear(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl20.glClearColor(f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f2) {
        this.calls++;
        this.gl20.glClearDepthf(f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int i2) {
        this.calls++;
        this.gl20.glClearStencil(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z2, boolean z3, boolean z4, boolean z5) {
        this.calls++;
        this.gl20.glColorMask(z2, z3, z4, z5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int i2) {
        this.calls++;
        this.gl20.glCompileShader(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.calls++;
        this.gl20.glCompressedTexImage2D(i2, i3, i4, i5, i6, i7, i8, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl20.glCompressedTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.calls++;
        this.gl20.glCopyTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.calls++;
        this.gl20.glCopyTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        this.calls++;
        int iGlCreateProgram = this.gl20.glCreateProgram();
        check();
        return iGlCreateProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int i2) {
        this.calls++;
        int iGlCreateShader = this.gl20.glCreateShader(i2);
        check();
        return iGlCreateShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int i2) {
        this.calls++;
        this.gl20.glCullFace(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int i2) {
        this.calls++;
        this.gl20.glDeleteBuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteBuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i2) {
        this.calls++;
        this.gl20.glDeleteFramebuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteFramebuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int i2) {
        this.calls++;
        this.gl20.glDeleteProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i2) {
        this.calls++;
        this.gl20.glDeleteRenderbuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteRenderbuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int i2) {
        this.calls++;
        this.gl20.glDeleteShader(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int i2) {
        this.calls++;
        this.gl20.glDeleteTexture(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteTextures(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int i2) {
        this.calls++;
        this.gl20.glDepthFunc(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z2) {
        this.calls++;
        this.gl20.glDepthMask(z2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f2, float f3) {
        this.calls++;
        this.gl20.glDepthRangef(f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int i2, int i3) {
        this.calls++;
        this.gl20.glDetachShader(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisable(int i2) {
        this.calls++;
        this.gl20.glDisable(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int i2) {
        this.calls++;
        this.gl20.glDisableVertexAttribArray(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int i2, int i3, int i4) {
        this.vertexCount.a(i4);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawArrays(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, Buffer buffer) {
        this.vertexCount.a(i3);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawElements(i2, i3, i4, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnable(int i2) {
        this.calls++;
        this.gl20.glEnable(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int i2) {
        this.calls++;
        this.gl20.glEnableVertexAttribArray(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        this.calls++;
        this.gl20.glFinish();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        this.calls++;
        this.gl20.glFlush();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glFramebufferRenderbuffer(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl20.glFramebufferTexture2D(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int i2) {
        this.calls++;
        this.gl20.glFrontFace(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        this.calls++;
        int iGlGenBuffer = this.gl20.glGenBuffer();
        check();
        return iGlGenBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenBuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        this.calls++;
        int iGlGenFramebuffer = this.gl20.glGenFramebuffer();
        check();
        return iGlGenFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenFramebuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        this.calls++;
        int iGlGenRenderbuffer = this.gl20.glGenRenderbuffer();
        check();
        return iGlGenRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenRenderbuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        this.calls++;
        int iGlGenTexture = this.gl20.glGenTexture();
        check();
        return iGlGenTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenTextures(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i2) {
        this.calls++;
        this.gl20.glGenerateMipmap(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String strGlGetActiveAttrib = this.gl20.glGetActiveAttrib(i2, i3, intBuffer, intBuffer2);
        check();
        return strGlGetActiveAttrib;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String strGlGetActiveUniform = this.gl20.glGetActiveUniform(i2, i3, intBuffer, intBuffer2);
        check();
        return strGlGetActiveUniform;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int i2, int i3, Buffer buffer, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetAttachedShaders(i2, i3, buffer, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int i2, String str) {
        this.calls++;
        int iGlGetAttribLocation = this.gl20.glGetAttribLocation(i2, str);
        check();
        return iGlGetAttribLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int i2, Buffer buffer) {
        this.calls++;
        this.gl20.glGetBooleanv(i2, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetBufferParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        this.calls++;
        return this.gl20.glGetError();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetFloatv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int i2, int i3, int i4, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetFramebufferAttachmentParameteriv(i2, i3, i4, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetIntegerv(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int i2) {
        this.calls++;
        String strGlGetProgramInfoLog = this.gl20.glGetProgramInfoLog(i2);
        check();
        return strGlGetProgramInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetProgramiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetRenderbufferParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int i2) {
        this.calls++;
        String strGlGetShaderInfoLog = this.gl20.glGetShaderInfoLog(i2);
        check();
        return strGlGetShaderInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        this.gl20.glGetShaderPrecisionFormat(i2, i3, intBuffer, intBuffer2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetShaderiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetString(int i2) {
        this.calls++;
        String strGlGetString = this.gl20.glGetString(i2);
        check();
        return strGlGetString;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetTexParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetTexParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int i2, String str) {
        this.calls++;
        int iGlGetUniformLocation = this.gl20.glGetUniformLocation(i2, str);
        check();
        return iGlGetUniformLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetUniformfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetUniformiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int i2, int i3, Buffer buffer) {
        this.calls++;
        this.gl20.glGetVertexAttribPointerv(i2, i3, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetVertexAttribfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetVertexAttribiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glHint(int i2, int i3) {
        this.calls++;
        this.gl20.glHint(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int i2) {
        this.calls++;
        boolean zGlIsBuffer = this.gl20.glIsBuffer(i2);
        check();
        return zGlIsBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int i2) {
        this.calls++;
        boolean zGlIsEnabled = this.gl20.glIsEnabled(i2);
        check();
        return zGlIsEnabled;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i2) {
        this.calls++;
        boolean zGlIsFramebuffer = this.gl20.glIsFramebuffer(i2);
        check();
        return zGlIsFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int i2) {
        this.calls++;
        boolean zGlIsProgram = this.gl20.glIsProgram(i2);
        check();
        return zGlIsProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i2) {
        this.calls++;
        boolean zGlIsRenderbuffer = this.gl20.glIsRenderbuffer(i2);
        check();
        return zGlIsRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int i2) {
        this.calls++;
        boolean zGlIsShader = this.gl20.glIsShader(i2);
        check();
        return zGlIsShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int i2) {
        this.calls++;
        boolean zGlIsTexture = this.gl20.glIsTexture(i2);
        check();
        return zGlIsTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f2) {
        this.calls++;
        this.gl20.glLineWidth(f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int i2) {
        this.calls++;
        this.gl20.glLinkProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int i2, int i3) {
        this.calls++;
        this.gl20.glPixelStorei(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f2, float f3) {
        this.calls++;
        this.gl20.glPolygonOffset(f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        this.calls++;
        this.gl20.glReadPixels(i2, i3, i4, i5, i6, i7, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        this.calls++;
        this.gl20.glReleaseShaderCompiler();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glRenderbufferStorage(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f2, boolean z2) {
        this.calls++;
        this.gl20.glSampleCoverage(f2, z2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glScissor(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glScissor(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int i2, IntBuffer intBuffer, int i3, Buffer buffer, int i4) {
        this.calls++;
        this.gl20.glShaderBinary(i2, intBuffer, i3, buffer, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int i2, String str) {
        this.calls++;
        this.gl20.glShaderSource(i2, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glStencilFunc(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glStencilFuncSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int i2) {
        this.calls++;
        this.gl20.glStencilMask(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int i2, int i3) {
        this.calls++;
        this.gl20.glStencilMaskSeparate(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glStencilOp(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glStencilOpSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl20.glTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int i2, int i3, float f2) {
        this.calls++;
        this.gl20.glTexParameterf(i2, i3, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glTexParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glTexParameteri(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glTexParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl20.glTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int i2, float f2) {
        this.calls++;
        this.gl20.glUniform1f(i2, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform1fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int i2, int i3) {
        this.calls++;
        this.gl20.glUniform1i(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform1iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int i2, float f2, float f3) {
        this.calls++;
        this.gl20.glUniform2f(i2, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform2fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glUniform2i(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform2iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int i2, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glUniform3f(i2, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform3fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glUniform3i(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform3iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int i2, float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl20.glUniform4f(i2, f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform4fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl20.glUniform4i(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform4iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix2fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix3fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix4fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int i2) {
        this.shaderSwitches++;
        this.calls++;
        this.gl20.glUseProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int i2) {
        this.calls++;
        this.gl20.glValidateProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int i2, float f2) {
        this.calls++;
        this.gl20.glVertexAttrib1f(i2, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib1fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int i2, float f2, float f3) {
        this.calls++;
        this.gl20.glVertexAttrib2f(i2, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib2fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int i2, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glVertexAttrib3f(i2, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib3fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int i2, float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl20.glVertexAttrib4f(i2, f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib4fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, Buffer buffer) {
        this.calls++;
        this.gl20.glVertexAttribPointer(i2, i3, i4, z2, i5, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glViewport(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glViewport(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniform1fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl20.glUniform1iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniform2fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl20.glUniform2iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniform3fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl20.glUniform3iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniform4fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl20.glUniform4iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniformMatrix2fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniformMatrix3fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl20.glUniformMatrix4fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, int i6) {
        this.calls++;
        this.gl20.glVertexAttribPointer(i2, i3, i4, z2, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, int i5) {
        this.vertexCount.a(i3);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawElements(i2, i3, i4, i5);
        check();
    }
}

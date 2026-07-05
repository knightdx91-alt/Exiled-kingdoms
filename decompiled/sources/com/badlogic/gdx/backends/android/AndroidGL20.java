package com.badlogic.gdx.backends.android;

import android.opengl.GLES20;
import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidGL20 implements GL20 {
    private int[] ints = new int[1];
    private int[] ints2 = new int[1];
    private int[] ints3 = new int[1];
    private byte[] buffer = new byte[GL20.GL_NEVER];

    @Override // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int i2) {
        GLES20.glActiveTexture(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int i2, int i3) {
        GLES20.glAttachShader(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int i2, int i3, String str) {
        GLES20.glBindAttribLocation(i2, i3, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int i2, int i3) {
        GLES20.glBindBuffer(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i2, int i3) {
        GLES20.glBindFramebuffer(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i2, int i3) {
        GLES20.glBindRenderbuffer(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int i2, int i3) {
        GLES20.glBindTexture(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f2, float f3, float f4, float f5) {
        GLES20.glBlendColor(f2, f3, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int i2) {
        GLES20.glBlendEquation(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int i2, int i3) {
        GLES20.glBlendEquationSeparate(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int i2, int i3) {
        GLES20.glBlendFunc(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int i2, int i3, int i4, int i5) {
        GLES20.glBlendFuncSeparate(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int i2, int i3, Buffer buffer, int i4) {
        GLES20.glBufferData(i2, i3, buffer, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int i2, int i3, int i4, Buffer buffer) {
        GLES20.glBufferSubData(i2, i3, i4, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i2) {
        return GLES20.glCheckFramebufferStatus(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClear(int i2) {
        GLES20.glClear(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f2, float f3, float f4, float f5) {
        GLES20.glClearColor(f2, f3, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f2) {
        GLES20.glClearDepthf(f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int i2) {
        GLES20.glClearStencil(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z2, boolean z3, boolean z4, boolean z5) {
        GLES20.glColorMask(z2, z3, z4, z5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int i2) {
        GLES20.glCompileShader(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        GLES20.glCompressedTexImage2D(i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        GLES20.glCompressedTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        GLES20.glCopyTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        GLES20.glCopyTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        return GLES20.glCreateProgram();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int i2) {
        return GLES20.glCreateShader(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int i2) {
        GLES20.glCullFace(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int i2) {
        int[] iArr = this.ints;
        iArr[0] = i2;
        GLES20.glDeleteBuffers(1, iArr, 0);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int i2, IntBuffer intBuffer) {
        GLES20.glDeleteBuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i2) {
        int[] iArr = this.ints;
        iArr[0] = i2;
        GLES20.glDeleteFramebuffers(1, iArr, 0);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i2, IntBuffer intBuffer) {
        GLES20.glDeleteFramebuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int i2) {
        GLES20.glDeleteProgram(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i2) {
        int[] iArr = this.ints;
        iArr[0] = i2;
        GLES20.glDeleteRenderbuffers(1, iArr, 0);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i2, IntBuffer intBuffer) {
        GLES20.glDeleteRenderbuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int i2) {
        GLES20.glDeleteShader(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int i2) {
        int[] iArr = this.ints;
        iArr[0] = i2;
        GLES20.glDeleteTextures(1, iArr, 0);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int i2, IntBuffer intBuffer) {
        GLES20.glDeleteTextures(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int i2) {
        GLES20.glDepthFunc(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z2) {
        GLES20.glDepthMask(z2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f2, float f3) {
        GLES20.glDepthRangef(f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int i2, int i3) {
        GLES20.glDetachShader(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisable(int i2) {
        GLES20.glDisable(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int i2) {
        GLES20.glDisableVertexAttribArray(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int i2, int i3, int i4) {
        GLES20.glDrawArrays(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, Buffer buffer) {
        GLES20.glDrawElements(i2, i3, i4, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnable(int i2) {
        GLES20.glEnable(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int i2) {
        GLES20.glEnableVertexAttribArray(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        GLES20.glFinish();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        GLES20.glFlush();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i2, int i3, int i4, int i5) {
        GLES20.glFramebufferRenderbuffer(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i2, int i3, int i4, int i5, int i6) {
        GLES20.glFramebufferTexture2D(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int i2) {
        GLES20.glFrontFace(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        GLES20.glGenBuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int i2, IntBuffer intBuffer) {
        GLES20.glGenBuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        GLES20.glGenFramebuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i2, IntBuffer intBuffer) {
        GLES20.glGenFramebuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        GLES20.glGenRenderbuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i2, IntBuffer intBuffer) {
        GLES20.glGenRenderbuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        GLES20.glGenTextures(1, this.ints, 0);
        return this.ints[0];
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int i2, IntBuffer intBuffer) {
        GLES20.glGenTextures(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i2) {
        GLES20.glGenerateMipmap(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        byte[] bArr = this.buffer;
        GLES20.glGetActiveAttrib(i2, i3, bArr.length, this.ints, 0, this.ints2, 0, this.ints3, 0, bArr, 0);
        intBuffer.put(this.ints2[0]);
        intBuffer2.put(this.ints3[0]);
        return new String(this.buffer, 0, this.ints[0]);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        byte[] bArr = this.buffer;
        GLES20.glGetActiveUniform(i2, i3, bArr.length, this.ints, 0, this.ints2, 0, this.ints3, 0, bArr, 0);
        intBuffer.put(this.ints2[0]);
        intBuffer2.put(this.ints3[0]);
        return new String(this.buffer, 0, this.ints[0]);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int i2, int i3, Buffer buffer, IntBuffer intBuffer) {
        GLES20.glGetAttachedShaders(i2, i3, (IntBuffer) buffer, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int i2, String str) {
        return GLES20.glGetAttribLocation(i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int i2, Buffer buffer) {
        GLES20.glGetBooleanv(i2, (IntBuffer) buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetBufferParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        return GLES20.glGetError();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int i2, FloatBuffer floatBuffer) {
        GLES20.glGetFloatv(i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int i2, int i3, int i4, IntBuffer intBuffer) {
        GLES20.glGetFramebufferAttachmentParameteriv(i2, i3, i4, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int i2, IntBuffer intBuffer) {
        GLES20.glGetIntegerv(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int i2) {
        return GLES20.glGetProgramInfoLog(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetProgramiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetRenderbufferParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int i2) {
        return GLES20.glGetShaderInfoLog(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        GLES20.glGetShaderPrecisionFormat(i2, i3, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetShaderiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetString(int i2) {
        return GLES20.glGetString(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glGetTexParameterfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetTexParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int i2, String str) {
        return GLES20.glGetUniformLocation(i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glGetUniformfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetUniformiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int i2, int i3, Buffer buffer) {
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glGetVertexAttribfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glGetVertexAttribiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glHint(int i2, int i3) {
        GLES20.glHint(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int i2) {
        return GLES20.glIsBuffer(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int i2) {
        return GLES20.glIsEnabled(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i2) {
        return GLES20.glIsFramebuffer(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int i2) {
        return GLES20.glIsProgram(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i2) {
        return GLES20.glIsRenderbuffer(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int i2) {
        return GLES20.glIsShader(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int i2) {
        return GLES20.glIsTexture(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f2) {
        GLES20.glLineWidth(f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int i2) {
        GLES20.glLinkProgram(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int i2, int i3) {
        GLES20.glPixelStorei(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f2, float f3) {
        GLES20.glPolygonOffset(f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        GLES20.glReadPixels(i2, i3, i4, i5, i6, i7, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        GLES20.glReleaseShaderCompiler();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i2, int i3, int i4, int i5) {
        GLES20.glRenderbufferStorage(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f2, boolean z2) {
        GLES20.glSampleCoverage(f2, z2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glScissor(int i2, int i3, int i4, int i5) {
        GLES20.glScissor(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int i2, IntBuffer intBuffer, int i3, Buffer buffer, int i4) {
        GLES20.glShaderBinary(i2, intBuffer, i3, buffer, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int i2, String str) {
        GLES20.glShaderSource(i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int i2, int i3, int i4) {
        GLES20.glStencilFunc(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int i2, int i3, int i4, int i5) {
        GLES20.glStencilFuncSeparate(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int i2) {
        GLES20.glStencilMask(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int i2, int i3) {
        GLES20.glStencilMaskSeparate(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int i2, int i3, int i4) {
        GLES20.glStencilOp(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int i2, int i3, int i4, int i5) {
        GLES20.glStencilOpSeparate(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        GLES20.glTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int i2, int i3, float f2) {
        GLES20.glTexParameterf(i2, i3, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glTexParameterfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int i2, int i3, int i4) {
        GLES20.glTexParameteri(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glTexParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        GLES20.glTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int i2, float f2) {
        GLES20.glUniform1f(i2, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glUniform1fv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int i2, int i3) {
        GLES20.glUniform1i(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glUniform1iv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int i2, float f2, float f3) {
        GLES20.glUniform2f(i2, f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glUniform2fv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int i2, int i3, int i4) {
        GLES20.glUniform2i(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glUniform2iv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int i2, float f2, float f3, float f4) {
        GLES20.glUniform3f(i2, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glUniform3fv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int i2, int i3, int i4, int i5) {
        GLES20.glUniform3i(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glUniform3iv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int i2, float f2, float f3, float f4, float f5) {
        GLES20.glUniform4f(i2, f2, f3, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES20.glUniform4fv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int i2, int i3, int i4, int i5, int i6) {
        GLES20.glUniform4i(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, IntBuffer intBuffer) {
        GLES20.glUniform4iv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES20.glUniformMatrix2fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES20.glUniformMatrix3fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES20.glUniformMatrix4fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int i2) {
        GLES20.glUseProgram(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int i2) {
        GLES20.glValidateProgram(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int i2, float f2) {
        GLES20.glVertexAttrib1f(i2, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int i2, FloatBuffer floatBuffer) {
        GLES20.glVertexAttrib1fv(i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int i2, float f2, float f3) {
        GLES20.glVertexAttrib2f(i2, f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int i2, FloatBuffer floatBuffer) {
        GLES20.glVertexAttrib2fv(i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int i2, float f2, float f3, float f4) {
        GLES20.glVertexAttrib3f(i2, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int i2, FloatBuffer floatBuffer) {
        GLES20.glVertexAttrib3fv(i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int i2, float f2, float f3, float f4, float f5) {
        GLES20.glVertexAttrib4f(i2, f2, f3, f4, f5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int i2, FloatBuffer floatBuffer) {
        GLES20.glVertexAttrib4fv(i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, Buffer buffer) {
        GLES20.glVertexAttribPointer(i2, i3, i4, z2, i5, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glViewport(int i2, int i3, int i4, int i5) {
        GLES20.glViewport(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, int i5) {
        GLES20.glDrawElements(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, float[] fArr, int i4) {
        GLES20.glUniform1fv(i2, i3, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, int[] iArr, int i4) {
        GLES20.glUniform1iv(i2, i3, iArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, float[] fArr, int i4) {
        GLES20.glUniform2fv(i2, i3, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, int[] iArr, int i4) {
        GLES20.glUniform2iv(i2, i3, iArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, float[] fArr, int i4) {
        GLES20.glUniform3fv(i2, i3, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, int[] iArr, int i4) {
        GLES20.glUniform3iv(i2, i3, iArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, float[] fArr, int i4) {
        GLES20.glUniform4fv(i2, i3, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, int[] iArr, int i4) {
        GLES20.glUniform4iv(i2, i3, iArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        GLES20.glUniformMatrix2fv(i2, i3, z2, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        GLES20.glUniformMatrix3fv(i2, i3, z2, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        GLES20.glUniformMatrix4fv(i2, i3, z2, fArr, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, int i6) {
        GLES20.glVertexAttribPointer(i2, i3, i4, z2, i5, i6);
    }
}

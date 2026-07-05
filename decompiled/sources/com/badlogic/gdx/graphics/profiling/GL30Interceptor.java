package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GL30Interceptor extends GLInterceptor implements GL30 {
    protected final GL30 gl30;

    protected GL30Interceptor(GLProfiler gLProfiler, GL30 gl30) {
        super(gLProfiler);
        this.gl30 = gl30;
    }

    private void check() {
        int iGlGetError = this.gl30.glGetError();
        while (iGlGetError != 0) {
            this.glProfiler.getListener().onError(iGlGetError);
            iGlGetError = this.gl30.glGetError();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int i2) {
        this.calls++;
        this.gl30.glActiveTexture(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int i2, int i3) {
        this.calls++;
        this.gl30.glAttachShader(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginQuery(int i2, int i3) {
        this.calls++;
        this.gl30.glBeginQuery(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginTransformFeedback(int i2) {
        this.calls++;
        this.gl30.glBeginTransformFeedback(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int i2, int i3, String str) {
        this.calls++;
        this.gl30.glBindAttribLocation(i2, i3, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int i2, int i3) {
        this.calls++;
        this.gl30.glBindBuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferBase(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glBindBufferBase(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferRange(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glBindBufferRange(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i2, int i3) {
        this.calls++;
        this.gl30.glBindFramebuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i2, int i3) {
        this.calls++;
        this.gl30.glBindRenderbuffer(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindSampler(int i2, int i3) {
        this.calls++;
        this.gl30.glBindSampler(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int i2, int i3) {
        this.textureBindings++;
        this.calls++;
        this.gl30.glBindTexture(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindTransformFeedback(int i2, int i3) {
        this.calls++;
        this.gl30.glBindTransformFeedback(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindVertexArray(int i2) {
        this.calls++;
        this.gl30.glBindVertexArray(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl30.glBlendColor(f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int i2) {
        this.calls++;
        this.gl30.glBlendEquation(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int i2, int i3) {
        this.calls++;
        this.gl30.glBlendEquationSeparate(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int i2, int i3) {
        this.calls++;
        this.gl30.glBlendFunc(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glBlendFuncSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBlitFramebuffer(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.calls++;
        this.gl30.glBlitFramebuffer(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int i2, int i3, Buffer buffer, int i4) {
        this.calls++;
        this.gl30.glBufferData(i2, i3, buffer, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int i2, int i3, int i4, Buffer buffer) {
        this.calls++;
        this.gl30.glBufferSubData(i2, i3, i4, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i2) {
        this.calls++;
        int iGlCheckFramebufferStatus = this.gl30.glCheckFramebufferStatus(i2);
        check();
        return iGlCheckFramebufferStatus;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClear(int i2) {
        this.calls++;
        this.gl30.glClear(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfi(int i2, int i3, float f2, int i4) {
        this.calls++;
        this.gl30.glClearBufferfi(i2, i3, f2, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glClearBufferfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glClearBufferiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferuiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glClearBufferuiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl30.glClearColor(f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f2) {
        this.calls++;
        this.gl30.glClearDepthf(f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int i2) {
        this.calls++;
        this.gl30.glClearStencil(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z2, boolean z3, boolean z4, boolean z5) {
        this.calls++;
        this.gl30.glColorMask(z2, z3, z4, z5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int i2) {
        this.calls++;
        this.gl30.glCompileShader(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.calls++;
        this.gl30.glCompressedTexImage2D(i2, i3, i4, i5, i6, i7, i8, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl30.glCompressedTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyBufferSubData(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glCopyBufferSubData(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.calls++;
        this.gl30.glCopyTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.calls++;
        this.gl30.glCopyTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.calls++;
        this.gl30.glCopyTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        this.calls++;
        int iGlCreateProgram = this.gl30.glCreateProgram();
        check();
        return iGlCreateProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int i2) {
        this.calls++;
        int iGlCreateShader = this.gl30.glCreateShader(i2);
        check();
        return iGlCreateShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int i2) {
        this.calls++;
        this.gl30.glCullFace(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int i2) {
        this.calls++;
        this.gl30.glDeleteBuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteBuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i2) {
        this.calls++;
        this.gl30.glDeleteFramebuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteFramebuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int i2) {
        this.calls++;
        this.gl30.glDeleteProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glDeleteQueries(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i2) {
        this.calls++;
        this.gl30.glDeleteRenderbuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteRenderbuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glDeleteSamplers(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int i2) {
        this.calls++;
        this.gl30.glDeleteShader(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int i2) {
        this.calls++;
        this.gl30.glDeleteTexture(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteTextures(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glDeleteTransformFeedbacks(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glDeleteVertexArrays(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int i2) {
        this.calls++;
        this.gl30.glDepthFunc(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z2) {
        this.calls++;
        this.gl30.glDepthMask(z2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f2, float f3) {
        this.calls++;
        this.gl30.glDepthRangef(f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int i2, int i3) {
        this.calls++;
        this.gl30.glDetachShader(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisable(int i2) {
        this.calls++;
        this.gl30.glDisable(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int i2) {
        this.calls++;
        this.gl30.glDisableVertexAttribArray(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int i2, int i3, int i4) {
        this.vertexCount.a(i4);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawArrays(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawArraysInstanced(int i2, int i3, int i4, int i5) {
        this.vertexCount.a(i4);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawArraysInstanced(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawBuffers(int i2, IntBuffer intBuffer) {
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawBuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, Buffer buffer) {
        this.vertexCount.a(i3);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawElements(i2, i3, i4, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawElementsInstanced(int i2, int i3, int i4, int i5, int i6) {
        this.vertexCount.a(i3);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawElementsInstanced(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        this.vertexCount.a(i5);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawRangeElements(i2, i3, i4, i5, i6, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnable(int i2) {
        this.calls++;
        this.gl30.glEnable(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int i2) {
        this.calls++;
        this.gl30.glEnableVertexAttribArray(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndQuery(int i2) {
        this.calls++;
        this.gl30.glEndQuery(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndTransformFeedback() {
        this.calls++;
        this.gl30.glEndTransformFeedback();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        this.calls++;
        this.gl30.glFinish();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        this.calls++;
        this.gl30.glFlush();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFlushMappedBufferRange(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glFlushMappedBufferRange(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glFramebufferRenderbuffer(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glFramebufferTexture2D(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFramebufferTextureLayer(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glFramebufferTextureLayer(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int i2) {
        this.calls++;
        this.gl30.glFrontFace(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        this.calls++;
        int iGlGenBuffer = this.gl30.glGenBuffer();
        check();
        return iGlGenBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenBuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        this.calls++;
        int iGlGenFramebuffer = this.gl30.glGenFramebuffer();
        check();
        return iGlGenFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenFramebuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glGenQueries(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        this.calls++;
        int iGlGenRenderbuffer = this.gl30.glGenRenderbuffer();
        check();
        return iGlGenRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenRenderbuffers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glGenSamplers(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        this.calls++;
        int iGlGenTexture = this.gl30.glGenTexture();
        check();
        return iGlGenTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenTextures(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glGenTransformFeedbacks(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl30.glGenVertexArrays(i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i2) {
        this.calls++;
        this.gl30.glGenerateMipmap(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String strGlGetActiveAttrib = this.gl30.glGetActiveAttrib(i2, i3, intBuffer, intBuffer2);
        check();
        return strGlGetActiveAttrib;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String strGlGetActiveUniform = this.gl30.glGetActiveUniform(i2, i3, intBuffer, intBuffer2);
        check();
        return strGlGetActiveUniform;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockName(int i2, int i3, Buffer buffer, Buffer buffer2) {
        this.calls++;
        this.gl30.glGetActiveUniformBlockName(i2, i3, buffer, buffer2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockiv(int i2, int i3, int i4, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetActiveUniformBlockiv(i2, i3, i4, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformsiv(int i2, int i3, IntBuffer intBuffer, int i4, IntBuffer intBuffer2) {
        this.calls++;
        this.gl30.glGetActiveUniformsiv(i2, i3, intBuffer, i4, intBuffer2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int i2, int i3, Buffer buffer, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetAttachedShaders(i2, i3, buffer, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int i2, String str) {
        this.calls++;
        int iGlGetAttribLocation = this.gl30.glGetAttribLocation(i2, str);
        check();
        return iGlGetAttribLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int i2, Buffer buffer) {
        this.calls++;
        this.gl30.glGetBooleanv(i2, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetBufferParameteri64v(int i2, int i3, LongBuffer longBuffer) {
        this.calls++;
        this.gl30.glGetBufferParameteri64v(i2, i3, longBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetBufferParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public Buffer glGetBufferPointerv(int i2, int i3) {
        this.calls++;
        Buffer bufferGlGetBufferPointerv = this.gl30.glGetBufferPointerv(i2, i3);
        check();
        return bufferGlGetBufferPointerv;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        this.calls++;
        return this.gl30.glGetError();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glGetFloatv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetFragDataLocation(int i2, String str) {
        this.calls++;
        int iGlGetFragDataLocation = this.gl30.glGetFragDataLocation(i2, str);
        check();
        return iGlGetFragDataLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int i2, int i3, int i4, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetFramebufferAttachmentParameteriv(i2, i3, i4, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetInteger64v(int i2, LongBuffer longBuffer) {
        this.calls++;
        this.gl30.glGetInteger64v(i2, longBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetIntegerv(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int i2) {
        this.calls++;
        String strGlGetProgramInfoLog = this.gl30.glGetProgramInfoLog(i2);
        check();
        return strGlGetProgramInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetProgramiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryObjectuiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetQueryObjectuiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetQueryiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetRenderbufferParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glGetSamplerParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetSamplerParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int i2) {
        this.calls++;
        String strGlGetShaderInfoLog = this.gl30.glGetShaderInfoLog(i2);
        check();
        return strGlGetShaderInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        this.gl30.glGetShaderPrecisionFormat(i2, i3, intBuffer, intBuffer2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetShaderiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetString(int i2) {
        this.calls++;
        String strGlGetString = this.gl30.glGetString(i2);
        check();
        return strGlGetString;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetStringi(int i2, int i3) {
        this.calls++;
        String strGlGetStringi = this.gl30.glGetStringi(i2, i3);
        check();
        return strGlGetStringi;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glGetTexParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetTexParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetUniformBlockIndex(int i2, String str) {
        this.calls++;
        int iGlGetUniformBlockIndex = this.gl30.glGetUniformBlockIndex(i2, str);
        check();
        return iGlGetUniformBlockIndex;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformIndices(int i2, String[] strArr, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetUniformIndices(i2, strArr, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int i2, String str) {
        this.calls++;
        int iGlGetUniformLocation = this.gl30.glGetUniformLocation(i2, str);
        check();
        return iGlGetUniformLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glGetUniformfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetUniformiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformuiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetUniformuiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetVertexAttribIiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIuiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetVertexAttribIuiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int i2, int i3, Buffer buffer) {
        this.calls++;
        this.gl30.glGetVertexAttribPointerv(i2, i3, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glGetVertexAttribfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGetVertexAttribiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glHint(int i2, int i3) {
        this.calls++;
        this.gl30.glHint(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateFramebuffer(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glInvalidateFramebuffer(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateSubFramebuffer(int i2, int i3, IntBuffer intBuffer, int i4, int i5, int i6, int i7) {
        this.calls++;
        this.gl30.glInvalidateSubFramebuffer(i2, i3, intBuffer, i4, i5, i6, i7);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int i2) {
        this.calls++;
        boolean zGlIsBuffer = this.gl30.glIsBuffer(i2);
        check();
        return zGlIsBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int i2) {
        this.calls++;
        boolean zGlIsEnabled = this.gl30.glIsEnabled(i2);
        check();
        return zGlIsEnabled;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i2) {
        this.calls++;
        boolean zGlIsFramebuffer = this.gl30.glIsFramebuffer(i2);
        check();
        return zGlIsFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int i2) {
        this.calls++;
        boolean zGlIsProgram = this.gl30.glIsProgram(i2);
        check();
        return zGlIsProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsQuery(int i2) {
        this.calls++;
        boolean zGlIsQuery = this.gl30.glIsQuery(i2);
        check();
        return zGlIsQuery;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i2) {
        this.calls++;
        boolean zGlIsRenderbuffer = this.gl30.glIsRenderbuffer(i2);
        check();
        return zGlIsRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsSampler(int i2) {
        this.calls++;
        boolean zGlIsSampler = this.gl30.glIsSampler(i2);
        check();
        return zGlIsSampler;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int i2) {
        this.calls++;
        boolean zGlIsShader = this.gl30.glIsShader(i2);
        check();
        return zGlIsShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int i2) {
        this.calls++;
        boolean zGlIsTexture = this.gl30.glIsTexture(i2);
        check();
        return zGlIsTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsTransformFeedback(int i2) {
        this.calls++;
        boolean zGlIsTransformFeedback = this.gl30.glIsTransformFeedback(i2);
        check();
        return zGlIsTransformFeedback;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsVertexArray(int i2) {
        this.calls++;
        boolean zGlIsVertexArray = this.gl30.glIsVertexArray(i2);
        check();
        return zGlIsVertexArray;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f2) {
        this.calls++;
        this.gl30.glLineWidth(f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int i2) {
        this.calls++;
        this.gl30.glLinkProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glPauseTransformFeedback() {
        this.calls++;
        this.gl30.glPauseTransformFeedback();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int i2, int i3) {
        this.calls++;
        this.gl30.glPixelStorei(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f2, float f3) {
        this.calls++;
        this.gl30.glPolygonOffset(f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glProgramParameteri(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glProgramParameteri(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glReadBuffer(int i2) {
        this.calls++;
        this.gl30.glReadBuffer(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        this.calls++;
        this.gl30.glReadPixels(i2, i3, i4, i5, i6, i7, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        this.calls++;
        this.gl30.glReleaseShaderCompiler();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glRenderbufferStorage(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glRenderbufferStorageMultisample(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glRenderbufferStorageMultisample(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glResumeTransformFeedback() {
        this.calls++;
        this.gl30.glResumeTransformFeedback();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f2, boolean z2) {
        this.calls++;
        this.gl30.glSampleCoverage(f2, z2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterf(int i2, int i3, float f2) {
        this.calls++;
        this.gl30.glSamplerParameterf(i2, i3, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glSamplerParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteri(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glSamplerParameteri(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glSamplerParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glScissor(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glScissor(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int i2, IntBuffer intBuffer, int i3, Buffer buffer, int i4) {
        this.calls++;
        this.gl30.glShaderBinary(i2, intBuffer, i3, buffer, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int i2, String str) {
        this.calls++;
        this.gl30.glShaderSource(i2, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glStencilFunc(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glStencilFuncSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int i2) {
        this.calls++;
        this.gl30.glStencilMask(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int i2, int i3) {
        this.calls++;
        this.gl30.glStencilMaskSeparate(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glStencilOp(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glStencilOpSeparate(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl30.glTexImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Buffer buffer) {
        this.calls++;
        this.gl30.glTexImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int i2, int i3, float f2) {
        this.calls++;
        this.gl30.glTexParameterf(i2, i3, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glTexParameterfv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glTexParameteri(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glTexParameteriv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        this.calls++;
        this.gl30.glTexSubImage2D(i2, i3, i4, i5, i6, i7, i8, i9, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, Buffer buffer) {
        this.calls++;
        this.gl30.glTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTransformFeedbackVaryings(int i2, String[] strArr, int i3) {
        this.calls++;
        this.gl30.glTransformFeedbackVaryings(i2, strArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int i2, float f2) {
        this.calls++;
        this.gl30.glUniform1f(i2, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniform1fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int i2, int i3) {
        this.calls++;
        this.gl30.glUniform1i(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform1iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform1uiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform1uiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int i2, float f2, float f3) {
        this.calls++;
        this.gl30.glUniform2f(i2, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniform2fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glUniform2i(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform2iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int i2, float f2, float f3, float f4) {
        this.calls++;
        this.gl30.glUniform3f(i2, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniform3fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glUniform3i(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform3iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform3uiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform3uiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int i2, float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl30.glUniform4f(i2, f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniform4fv(i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glUniform4i(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform4iv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform4uiv(int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glUniform4uiv(i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformBlockBinding(int i2, int i3, int i4) {
        this.calls++;
        this.gl30.glUniformBlockBinding(i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix2fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix2x3fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix2x4fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix3fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix3x2fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix3x4fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix4fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix4x2fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glUniformMatrix4x3fv(i2, i3, z2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glUnmapBuffer(int i2) {
        this.calls++;
        boolean zGlUnmapBuffer = this.gl30.glUnmapBuffer(i2);
        check();
        return zGlUnmapBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int i2) {
        this.shaderSwitches++;
        this.calls++;
        this.gl30.glUseProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int i2) {
        this.calls++;
        this.gl30.glValidateProgram(i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int i2, float f2) {
        this.calls++;
        this.gl30.glVertexAttrib1f(i2, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glVertexAttrib1fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int i2, float f2, float f3) {
        this.calls++;
        this.gl30.glVertexAttrib2f(i2, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glVertexAttrib2fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int i2, float f2, float f3, float f4) {
        this.calls++;
        this.gl30.glVertexAttrib3f(i2, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glVertexAttrib3fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int i2, float f2, float f3, float f4, float f5) {
        this.calls++;
        this.gl30.glVertexAttrib4f(i2, f2, f3, f4, f5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl30.glVertexAttrib4fv(i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribDivisor(int i2, int i3) {
        this.calls++;
        this.gl30.glVertexAttribDivisor(i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4i(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glVertexAttribI4i(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4ui(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glVertexAttribI4ui(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribIPointer(int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl30.glVertexAttribIPointer(i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, Buffer buffer) {
        this.calls++;
        this.gl30.glVertexAttribPointer(i2, i3, i4, z2, i5, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glViewport(int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl30.glViewport(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteQueries(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteSamplers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteTransformFeedbacks(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glDeleteVertexArrays(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenQueries(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenSamplers(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenTransformFeedbacks(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl30.glGenVertexArrays(i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetActiveUniformBlockName(int i2, int i3) {
        this.calls++;
        String strGlGetActiveUniformBlockName = this.gl30.glGetActiveUniformBlockName(i2, i3);
        check();
        return strGlGetActiveUniformBlockName;
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.calls++;
        this.gl30.glTexImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        this.calls++;
        this.gl30.glTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniform1fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl30.glUniform1iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniform2fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl30.glUniform2iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniform3fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl30.glUniform3iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i2, int i3, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniform4fv(i2, i3, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i2, int i3, int[] iArr, int i4) {
        this.calls++;
        this.gl30.glUniform4iv(i2, i3, iArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniformMatrix2fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniformMatrix3fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i2, int i3, boolean z2, float[] fArr, int i4) {
        this.calls++;
        this.gl30.glUniformMatrix4fv(i2, i3, z2, fArr, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i2, int i3, int i4, boolean z2, int i5, int i6) {
        this.calls++;
        this.gl30.glVertexAttribPointer(i2, i3, i4, z2, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i2, int i3, int i4, int i5) {
        this.vertexCount.a(i3);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawElements(i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.vertexCount.a(i5);
        this.drawCalls++;
        this.calls++;
        this.gl30.glDrawRangeElements(i2, i3, i4, i5, i6, i7);
        check();
    }
}

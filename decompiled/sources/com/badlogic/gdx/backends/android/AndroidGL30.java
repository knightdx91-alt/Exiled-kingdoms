package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.opengl.GLES30;
import com.badlogic.gdx.graphics.GL30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@TargetApi(18)
public class AndroidGL30 extends AndroidGL20 implements GL30 {
    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginQuery(int i2, int i3) {
        GLES30.glBeginQuery(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginTransformFeedback(int i2) {
        GLES30.glBeginTransformFeedback(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferBase(int i2, int i3, int i4) {
        GLES30.glBindBufferBase(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferRange(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glBindBufferRange(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindSampler(int i2, int i3) {
        GLES30.glBindSampler(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindTransformFeedback(int i2, int i3) {
        GLES30.glBindTransformFeedback(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindVertexArray(int i2) {
        GLES30.glBindVertexArray(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBlitFramebuffer(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        GLES30.glBlitFramebuffer(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfi(int i2, int i3, float f2, int i4) {
        GLES30.glClearBufferfi(i2, i3, f2, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES30.glClearBufferfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glClearBufferiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferuiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glClearBufferuiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyBufferSubData(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glCopyBufferSubData(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        GLES30.glCopyTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i2, int[] iArr, int i3) {
        GLES30.glDeleteQueries(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i2, int[] iArr, int i3) {
        GLES30.glDeleteSamplers(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i2, int[] iArr, int i3) {
        GLES30.glDeleteTransformFeedbacks(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i2, int[] iArr, int i3) {
        GLES30.glDeleteVertexArrays(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawArraysInstanced(int i2, int i3, int i4, int i5) {
        GLES30.glDrawArraysInstanced(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawBuffers(int i2, IntBuffer intBuffer) {
        GLES30.glDrawBuffers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawElementsInstanced(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glDrawElementsInstanced(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        GLES30.glDrawRangeElements(i2, i3, i4, i5, i6, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndQuery(int i2) {
        GLES30.glEndQuery(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndTransformFeedback() {
        GLES30.glEndTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFlushMappedBufferRange(int i2, int i3, int i4) {
        GLES30.glFlushMappedBufferRange(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFramebufferTextureLayer(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glFramebufferTextureLayer(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i2, int[] iArr, int i3) {
        GLES30.glGenQueries(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i2, int[] iArr, int i3) {
        GLES30.glGenSamplers(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i2, int[] iArr, int i3) {
        GLES30.glGenTransformFeedbacks(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i2, int[] iArr, int i3) {
        GLES30.glGenVertexArrays(i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockName(int i2, int i3, Buffer buffer, Buffer buffer2) {
        GLES30.glGetActiveUniformBlockName(i2, i3, buffer, buffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockiv(int i2, int i3, int i4, IntBuffer intBuffer) {
        GLES30.glGetActiveUniformBlockiv(i2, i3, i4, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformsiv(int i2, int i3, IntBuffer intBuffer, int i4, IntBuffer intBuffer2) {
        GLES30.glGetActiveUniformsiv(i2, i3, intBuffer, i4, intBuffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetBufferParameteri64v(int i2, int i3, LongBuffer longBuffer) {
        GLES30.glGetBufferParameteri64v(i2, i3, longBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public Buffer glGetBufferPointerv(int i2, int i3) {
        return GLES30.glGetBufferPointerv(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetFragDataLocation(int i2, String str) {
        return GLES30.glGetFragDataLocation(i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetInteger64v(int i2, LongBuffer longBuffer) {
        GLES30.glGetInteger64v(i2, longBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryObjectuiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetQueryObjectuiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetQueryiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES30.glGetSamplerParameterfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetSamplerParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetStringi(int i2, int i3) {
        return GLES30.glGetStringi(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetUniformBlockIndex(int i2, String str) {
        return GLES30.glGetUniformBlockIndex(i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformIndices(int i2, String[] strArr, IntBuffer intBuffer) {
        GLES30.glGetUniformIndices(i2, strArr, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformuiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetUniformuiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetVertexAttribIiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIuiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glGetVertexAttribIuiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateFramebuffer(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glInvalidateFramebuffer(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateSubFramebuffer(int i2, int i3, IntBuffer intBuffer, int i4, int i5, int i6, int i7) {
        GLES30.glInvalidateSubFramebuffer(i2, i3, intBuffer, i4, i5, i6, i7);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsQuery(int i2) {
        return GLES30.glIsQuery(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsSampler(int i2) {
        return GLES30.glIsSampler(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsTransformFeedback(int i2) {
        return GLES30.glIsTransformFeedback(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsVertexArray(int i2) {
        return GLES30.glIsVertexArray(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glPauseTransformFeedback() {
        GLES30.glPauseTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glProgramParameteri(int i2, int i3, int i4) {
        GLES30.glProgramParameteri(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glReadBuffer(int i2) {
        GLES30.glReadBuffer(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glRenderbufferStorageMultisample(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glRenderbufferStorageMultisample(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glResumeTransformFeedback() {
        GLES30.glResumeTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterf(int i2, int i3, float f2) {
        GLES30.glSamplerParameterf(i2, i3, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterfv(int i2, int i3, FloatBuffer floatBuffer) {
        GLES30.glSamplerParameterfv(i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteri(int i2, int i3, int i4) {
        GLES30.glSamplerParameteri(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteriv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glSamplerParameteriv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Buffer buffer) {
        if (buffer == null) {
            GLES30.glTexImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, 0);
        } else {
            GLES30.glTexImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, buffer);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, Buffer buffer) {
        GLES30.glTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTransformFeedbackVaryings(int i2, String[] strArr, int i3) {
        GLES30.glTransformFeedbackVaryings(i2, strArr, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform1uiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glUniform1uiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform3uiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glUniform3uiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform4uiv(int i2, int i3, IntBuffer intBuffer) {
        GLES30.glUniform4uiv(i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformBlockBinding(int i2, int i3, int i4) {
        GLES30.glUniformBlockBinding(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix2x3fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix2x4fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix3x2fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x4fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix3x4fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x2fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix4x2fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x3fv(int i2, int i3, boolean z2, FloatBuffer floatBuffer) {
        GLES30.glUniformMatrix4x3fv(i2, i3, z2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glUnmapBuffer(int i2) {
        return GLES30.glUnmapBuffer(i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribDivisor(int i2, int i3) {
        GLES30.glVertexAttribDivisor(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4i(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glVertexAttribI4i(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4ui(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glVertexAttribI4ui(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribIPointer(int i2, int i3, int i4, int i5, int i6) {
        GLES30.glVertexAttribIPointer(i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i2, IntBuffer intBuffer) {
        GLES30.glDeleteQueries(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i2, IntBuffer intBuffer) {
        GLES30.glDeleteSamplers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i2, IntBuffer intBuffer) {
        GLES30.glDeleteTransformFeedbacks(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i2, IntBuffer intBuffer) {
        GLES30.glDeleteVertexArrays(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i2, int i3, int i4, int i5, int i6, int i7) {
        GLES30.glDrawRangeElements(i2, i3, i4, i5, i6, i7);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i2, IntBuffer intBuffer) {
        GLES30.glGenQueries(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i2, IntBuffer intBuffer) {
        GLES30.glGenSamplers(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i2, IntBuffer intBuffer) {
        GLES30.glGenTransformFeedbacks(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i2, IntBuffer intBuffer) {
        GLES30.glGenVertexArrays(i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetActiveUniformBlockName(int i2, int i3) {
        return GLES30.glGetActiveUniformBlockName(i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        GLES30.glTexSubImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        GLES30.glTexImage3D(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }
}

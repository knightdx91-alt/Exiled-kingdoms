package ekvr;

/**
 * Pass-through GL20 (generated from the APK's GL20 interface by tools/vr/gen_gltap.py).
 * While {@link #transparentClear} is set, every glClearColor clears to transparent black, so
 * the game's own frame (cleared opaque by GameLevelRenderer) becomes a UI layer with holes
 * where the map was: the map itself is drawn in 3D from the recorded batch.
 */
public class GlTap implements com.badlogic.gdx.graphics.GL20 {
    public final com.badlogic.gdx.graphics.GL20 gl;
    public static boolean transparentClear;
    public GlTap(com.badlogic.gdx.graphics.GL20 gl) { this.gl = gl; }
    public void glActiveTexture(int a0) { gl.glActiveTexture(a0); }
    public void glAttachShader(int a0, int a1) { gl.glAttachShader(a0, a1); }
    public void glBindAttribLocation(int a0, int a1, java.lang.String a2) { gl.glBindAttribLocation(a0, a1, a2); }
    public void glBindBuffer(int a0, int a1) { gl.glBindBuffer(a0, a1); }
    public void glBindFramebuffer(int a0, int a1) { gl.glBindFramebuffer(a0, a1); }
    public void glBindRenderbuffer(int a0, int a1) { gl.glBindRenderbuffer(a0, a1); }
    public void glBindTexture(int a0, int a1) { gl.glBindTexture(a0, a1); }
    public void glBlendColor(float a0, float a1, float a2, float a3) { gl.glBlendColor(a0, a1, a2, a3); }
    public void glBlendEquation(int a0) { gl.glBlendEquation(a0); }
    public void glBlendEquationSeparate(int a0, int a1) { gl.glBlendEquationSeparate(a0, a1); }
    public void glBlendFunc(int a0, int a1) { gl.glBlendFunc(a0, a1); }
    public void glBlendFuncSeparate(int a0, int a1, int a2, int a3) { gl.glBlendFuncSeparate(a0, a1, a2, a3); }
    public void glBufferData(int a0, int a1, java.nio.Buffer a2, int a3) { gl.glBufferData(a0, a1, a2, a3); }
    public void glBufferSubData(int a0, int a1, int a2, java.nio.Buffer a3) { gl.glBufferSubData(a0, a1, a2, a3); }
    public int glCheckFramebufferStatus(int a0) { return gl.glCheckFramebufferStatus(a0); }
    public void glClear(int a0) { gl.glClear(a0); }
    public void glClearColor(float a0, float a1, float a2, float a3) { if (transparentClear) { gl.glClearColor(0f, 0f, 0f, 0f); return; } gl.glClearColor(a0, a1, a2, a3); }
    public void glClearDepthf(float a0) { gl.glClearDepthf(a0); }
    public void glClearStencil(int a0) { gl.glClearStencil(a0); }
    public void glColorMask(boolean a0, boolean a1, boolean a2, boolean a3) { gl.glColorMask(a0, a1, a2, a3); }
    public void glCompileShader(int a0) { gl.glCompileShader(a0); }
    public void glCompressedTexImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, java.nio.Buffer a7) { gl.glCompressedTexImage2D(a0, a1, a2, a3, a4, a5, a6, a7); }
    public void glCompressedTexSubImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7, java.nio.Buffer a8) { gl.glCompressedTexSubImage2D(a0, a1, a2, a3, a4, a5, a6, a7, a8); }
    public void glCopyTexImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7) { gl.glCopyTexImage2D(a0, a1, a2, a3, a4, a5, a6, a7); }
    public void glCopyTexSubImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7) { gl.glCopyTexSubImage2D(a0, a1, a2, a3, a4, a5, a6, a7); }
    public int glCreateProgram() { return gl.glCreateProgram(); }
    public int glCreateShader(int a0) { return gl.glCreateShader(a0); }
    public void glCullFace(int a0) { gl.glCullFace(a0); }
    public void glDeleteBuffer(int a0) { gl.glDeleteBuffer(a0); }
    public void glDeleteBuffers(int a0, java.nio.IntBuffer a1) { gl.glDeleteBuffers(a0, a1); }
    public void glDeleteFramebuffer(int a0) { gl.glDeleteFramebuffer(a0); }
    public void glDeleteFramebuffers(int a0, java.nio.IntBuffer a1) { gl.glDeleteFramebuffers(a0, a1); }
    public void glDeleteProgram(int a0) { gl.glDeleteProgram(a0); }
    public void glDeleteRenderbuffer(int a0) { gl.glDeleteRenderbuffer(a0); }
    public void glDeleteRenderbuffers(int a0, java.nio.IntBuffer a1) { gl.glDeleteRenderbuffers(a0, a1); }
    public void glDeleteShader(int a0) { gl.glDeleteShader(a0); }
    public void glDeleteTexture(int a0) { gl.glDeleteTexture(a0); }
    public void glDeleteTextures(int a0, java.nio.IntBuffer a1) { gl.glDeleteTextures(a0, a1); }
    public void glDepthFunc(int a0) { gl.glDepthFunc(a0); }
    public void glDepthMask(boolean a0) { gl.glDepthMask(a0); }
    public void glDepthRangef(float a0, float a1) { gl.glDepthRangef(a0, a1); }
    public void glDetachShader(int a0, int a1) { gl.glDetachShader(a0, a1); }
    public void glDisable(int a0) { gl.glDisable(a0); }
    public void glDisableVertexAttribArray(int a0) { gl.glDisableVertexAttribArray(a0); }
    public void glDrawArrays(int a0, int a1, int a2) { gl.glDrawArrays(a0, a1, a2); }
    public void glDrawElements(int a0, int a1, int a2, int a3) { gl.glDrawElements(a0, a1, a2, a3); }
    public void glDrawElements(int a0, int a1, int a2, java.nio.Buffer a3) { gl.glDrawElements(a0, a1, a2, a3); }
    public void glEnable(int a0) { gl.glEnable(a0); }
    public void glEnableVertexAttribArray(int a0) { gl.glEnableVertexAttribArray(a0); }
    public void glFinish() { gl.glFinish(); }
    public void glFlush() { gl.glFlush(); }
    public void glFramebufferRenderbuffer(int a0, int a1, int a2, int a3) { gl.glFramebufferRenderbuffer(a0, a1, a2, a3); }
    public void glFramebufferTexture2D(int a0, int a1, int a2, int a3, int a4) { gl.glFramebufferTexture2D(a0, a1, a2, a3, a4); }
    public void glFrontFace(int a0) { gl.glFrontFace(a0); }
    public int glGenBuffer() { return gl.glGenBuffer(); }
    public void glGenBuffers(int a0, java.nio.IntBuffer a1) { gl.glGenBuffers(a0, a1); }
    public int glGenFramebuffer() { return gl.glGenFramebuffer(); }
    public void glGenFramebuffers(int a0, java.nio.IntBuffer a1) { gl.glGenFramebuffers(a0, a1); }
    public int glGenRenderbuffer() { return gl.glGenRenderbuffer(); }
    public void glGenRenderbuffers(int a0, java.nio.IntBuffer a1) { gl.glGenRenderbuffers(a0, a1); }
    public int glGenTexture() { return gl.glGenTexture(); }
    public void glGenTextures(int a0, java.nio.IntBuffer a1) { gl.glGenTextures(a0, a1); }
    public void glGenerateMipmap(int a0) { gl.glGenerateMipmap(a0); }
    public java.lang.String glGetActiveAttrib(int a0, int a1, java.nio.IntBuffer a2, java.nio.IntBuffer a3) { return gl.glGetActiveAttrib(a0, a1, a2, a3); }
    public java.lang.String glGetActiveUniform(int a0, int a1, java.nio.IntBuffer a2, java.nio.IntBuffer a3) { return gl.glGetActiveUniform(a0, a1, a2, a3); }
    public void glGetAttachedShaders(int a0, int a1, java.nio.Buffer a2, java.nio.IntBuffer a3) { gl.glGetAttachedShaders(a0, a1, a2, a3); }
    public int glGetAttribLocation(int a0, java.lang.String a1) { return gl.glGetAttribLocation(a0, a1); }
    public void glGetBooleanv(int a0, java.nio.Buffer a1) { gl.glGetBooleanv(a0, a1); }
    public void glGetBufferParameteriv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetBufferParameteriv(a0, a1, a2); }
    public int glGetError() { return gl.glGetError(); }
    public void glGetFloatv(int a0, java.nio.FloatBuffer a1) { gl.glGetFloatv(a0, a1); }
    public void glGetFramebufferAttachmentParameteriv(int a0, int a1, int a2, java.nio.IntBuffer a3) { gl.glGetFramebufferAttachmentParameteriv(a0, a1, a2, a3); }
    public void glGetIntegerv(int a0, java.nio.IntBuffer a1) { gl.glGetIntegerv(a0, a1); }
    public java.lang.String glGetProgramInfoLog(int a0) { return gl.glGetProgramInfoLog(a0); }
    public void glGetProgramiv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetProgramiv(a0, a1, a2); }
    public void glGetRenderbufferParameteriv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetRenderbufferParameteriv(a0, a1, a2); }
    public java.lang.String glGetShaderInfoLog(int a0) { return gl.glGetShaderInfoLog(a0); }
    public void glGetShaderPrecisionFormat(int a0, int a1, java.nio.IntBuffer a2, java.nio.IntBuffer a3) { gl.glGetShaderPrecisionFormat(a0, a1, a2, a3); }
    public void glGetShaderiv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetShaderiv(a0, a1, a2); }
    public java.lang.String glGetString(int a0) { return gl.glGetString(a0); }
    public void glGetTexParameterfv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glGetTexParameterfv(a0, a1, a2); }
    public void glGetTexParameteriv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetTexParameteriv(a0, a1, a2); }
    public int glGetUniformLocation(int a0, java.lang.String a1) { return gl.glGetUniformLocation(a0, a1); }
    public void glGetUniformfv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glGetUniformfv(a0, a1, a2); }
    public void glGetUniformiv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetUniformiv(a0, a1, a2); }
    public void glGetVertexAttribPointerv(int a0, int a1, java.nio.Buffer a2) { gl.glGetVertexAttribPointerv(a0, a1, a2); }
    public void glGetVertexAttribfv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glGetVertexAttribfv(a0, a1, a2); }
    public void glGetVertexAttribiv(int a0, int a1, java.nio.IntBuffer a2) { gl.glGetVertexAttribiv(a0, a1, a2); }
    public void glHint(int a0, int a1) { gl.glHint(a0, a1); }
    public boolean glIsBuffer(int a0) { return gl.glIsBuffer(a0); }
    public boolean glIsEnabled(int a0) { return gl.glIsEnabled(a0); }
    public boolean glIsFramebuffer(int a0) { return gl.glIsFramebuffer(a0); }
    public boolean glIsProgram(int a0) { return gl.glIsProgram(a0); }
    public boolean glIsRenderbuffer(int a0) { return gl.glIsRenderbuffer(a0); }
    public boolean glIsShader(int a0) { return gl.glIsShader(a0); }
    public boolean glIsTexture(int a0) { return gl.glIsTexture(a0); }
    public void glLineWidth(float a0) { gl.glLineWidth(a0); }
    public void glLinkProgram(int a0) { gl.glLinkProgram(a0); }
    public void glPixelStorei(int a0, int a1) { gl.glPixelStorei(a0, a1); }
    public void glPolygonOffset(float a0, float a1) { gl.glPolygonOffset(a0, a1); }
    public void glReadPixels(int a0, int a1, int a2, int a3, int a4, int a5, java.nio.Buffer a6) { gl.glReadPixels(a0, a1, a2, a3, a4, a5, a6); }
    public void glReleaseShaderCompiler() { gl.glReleaseShaderCompiler(); }
    public void glRenderbufferStorage(int a0, int a1, int a2, int a3) { gl.glRenderbufferStorage(a0, a1, a2, a3); }
    public void glSampleCoverage(float a0, boolean a1) { gl.glSampleCoverage(a0, a1); }
    public void glScissor(int a0, int a1, int a2, int a3) { gl.glScissor(a0, a1, a2, a3); }
    public void glShaderBinary(int a0, java.nio.IntBuffer a1, int a2, java.nio.Buffer a3, int a4) { gl.glShaderBinary(a0, a1, a2, a3, a4); }
    public void glShaderSource(int a0, java.lang.String a1) { gl.glShaderSource(a0, a1); }
    public void glStencilFunc(int a0, int a1, int a2) { gl.glStencilFunc(a0, a1, a2); }
    public void glStencilFuncSeparate(int a0, int a1, int a2, int a3) { gl.glStencilFuncSeparate(a0, a1, a2, a3); }
    public void glStencilMask(int a0) { gl.glStencilMask(a0); }
    public void glStencilMaskSeparate(int a0, int a1) { gl.glStencilMaskSeparate(a0, a1); }
    public void glStencilOp(int a0, int a1, int a2) { gl.glStencilOp(a0, a1, a2); }
    public void glStencilOpSeparate(int a0, int a1, int a2, int a3) { gl.glStencilOpSeparate(a0, a1, a2, a3); }
    public void glTexImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7, java.nio.Buffer a8) { gl.glTexImage2D(a0, a1, a2, a3, a4, a5, a6, a7, a8); }
    public void glTexParameterf(int a0, int a1, float a2) { gl.glTexParameterf(a0, a1, a2); }
    public void glTexParameterfv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glTexParameterfv(a0, a1, a2); }
    public void glTexParameteri(int a0, int a1, int a2) { gl.glTexParameteri(a0, a1, a2); }
    public void glTexParameteriv(int a0, int a1, java.nio.IntBuffer a2) { gl.glTexParameteriv(a0, a1, a2); }
    public void glTexSubImage2D(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7, java.nio.Buffer a8) { gl.glTexSubImage2D(a0, a1, a2, a3, a4, a5, a6, a7, a8); }
    public void glUniform1f(int a0, float a1) { gl.glUniform1f(a0, a1); }
    public void glUniform1fv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glUniform1fv(a0, a1, a2); }
    public void glUniform1fv(int a0, int a1, float[] a2, int a3) { gl.glUniform1fv(a0, a1, a2, a3); }
    public void glUniform1i(int a0, int a1) { gl.glUniform1i(a0, a1); }
    public void glUniform1iv(int a0, int a1, java.nio.IntBuffer a2) { gl.glUniform1iv(a0, a1, a2); }
    public void glUniform1iv(int a0, int a1, int[] a2, int a3) { gl.glUniform1iv(a0, a1, a2, a3); }
    public void glUniform2f(int a0, float a1, float a2) { gl.glUniform2f(a0, a1, a2); }
    public void glUniform2fv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glUniform2fv(a0, a1, a2); }
    public void glUniform2fv(int a0, int a1, float[] a2, int a3) { gl.glUniform2fv(a0, a1, a2, a3); }
    public void glUniform2i(int a0, int a1, int a2) { gl.glUniform2i(a0, a1, a2); }
    public void glUniform2iv(int a0, int a1, java.nio.IntBuffer a2) { gl.glUniform2iv(a0, a1, a2); }
    public void glUniform2iv(int a0, int a1, int[] a2, int a3) { gl.glUniform2iv(a0, a1, a2, a3); }
    public void glUniform3f(int a0, float a1, float a2, float a3) { gl.glUniform3f(a0, a1, a2, a3); }
    public void glUniform3fv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glUniform3fv(a0, a1, a2); }
    public void glUniform3fv(int a0, int a1, float[] a2, int a3) { gl.glUniform3fv(a0, a1, a2, a3); }
    public void glUniform3i(int a0, int a1, int a2, int a3) { gl.glUniform3i(a0, a1, a2, a3); }
    public void glUniform3iv(int a0, int a1, java.nio.IntBuffer a2) { gl.glUniform3iv(a0, a1, a2); }
    public void glUniform3iv(int a0, int a1, int[] a2, int a3) { gl.glUniform3iv(a0, a1, a2, a3); }
    public void glUniform4f(int a0, float a1, float a2, float a3, float a4) { gl.glUniform4f(a0, a1, a2, a3, a4); }
    public void glUniform4fv(int a0, int a1, java.nio.FloatBuffer a2) { gl.glUniform4fv(a0, a1, a2); }
    public void glUniform4fv(int a0, int a1, float[] a2, int a3) { gl.glUniform4fv(a0, a1, a2, a3); }
    public void glUniform4i(int a0, int a1, int a2, int a3, int a4) { gl.glUniform4i(a0, a1, a2, a3, a4); }
    public void glUniform4iv(int a0, int a1, java.nio.IntBuffer a2) { gl.glUniform4iv(a0, a1, a2); }
    public void glUniform4iv(int a0, int a1, int[] a2, int a3) { gl.glUniform4iv(a0, a1, a2, a3); }
    public void glUniformMatrix2fv(int a0, int a1, boolean a2, java.nio.FloatBuffer a3) { gl.glUniformMatrix2fv(a0, a1, a2, a3); }
    public void glUniformMatrix2fv(int a0, int a1, boolean a2, float[] a3, int a4) { gl.glUniformMatrix2fv(a0, a1, a2, a3, a4); }
    public void glUniformMatrix3fv(int a0, int a1, boolean a2, java.nio.FloatBuffer a3) { gl.glUniformMatrix3fv(a0, a1, a2, a3); }
    public void glUniformMatrix3fv(int a0, int a1, boolean a2, float[] a3, int a4) { gl.glUniformMatrix3fv(a0, a1, a2, a3, a4); }
    public void glUniformMatrix4fv(int a0, int a1, boolean a2, java.nio.FloatBuffer a3) { gl.glUniformMatrix4fv(a0, a1, a2, a3); }
    public void glUniformMatrix4fv(int a0, int a1, boolean a2, float[] a3, int a4) { gl.glUniformMatrix4fv(a0, a1, a2, a3, a4); }
    public void glUseProgram(int a0) { gl.glUseProgram(a0); }
    public void glValidateProgram(int a0) { gl.glValidateProgram(a0); }
    public void glVertexAttrib1f(int a0, float a1) { gl.glVertexAttrib1f(a0, a1); }
    public void glVertexAttrib1fv(int a0, java.nio.FloatBuffer a1) { gl.glVertexAttrib1fv(a0, a1); }
    public void glVertexAttrib2f(int a0, float a1, float a2) { gl.glVertexAttrib2f(a0, a1, a2); }
    public void glVertexAttrib2fv(int a0, java.nio.FloatBuffer a1) { gl.glVertexAttrib2fv(a0, a1); }
    public void glVertexAttrib3f(int a0, float a1, float a2, float a3) { gl.glVertexAttrib3f(a0, a1, a2, a3); }
    public void glVertexAttrib3fv(int a0, java.nio.FloatBuffer a1) { gl.glVertexAttrib3fv(a0, a1); }
    public void glVertexAttrib4f(int a0, float a1, float a2, float a3, float a4) { gl.glVertexAttrib4f(a0, a1, a2, a3, a4); }
    public void glVertexAttrib4fv(int a0, java.nio.FloatBuffer a1) { gl.glVertexAttrib4fv(a0, a1); }
    public void glVertexAttribPointer(int a0, int a1, int a2, boolean a3, int a4, int a5) { gl.glVertexAttribPointer(a0, a1, a2, a3, a4, a5); }
    public void glVertexAttribPointer(int a0, int a1, int a2, boolean a3, int a4, java.nio.Buffer a5) { gl.glVertexAttribPointer(a0, a1, a2, a3, a4, a5); }
    public void glViewport(int a0, int a1, int a2, int a3) { gl.glViewport(a0, a1, a2, a3); }
}

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.o;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VertexBufferObjectWithVAO implements VertexData {
    static final IntBuffer tmpHandle = BufferUtils.g(1);
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    o cachedLocations;
    boolean isBound;
    boolean isDirty;
    final boolean isStatic;
    final boolean ownsBuffer;
    final int usage;
    int vaoHandle;

    public VertexBufferObjectWithVAO(boolean z2, int i2, VertexAttribute... vertexAttributeArr) {
        this(z2, i2, new VertexAttributes(vertexAttributeArr));
    }

    private void bindAttributes(ShaderProgram shaderProgram, int[] iArr) {
        boolean z2 = this.cachedLocations.f1850b != 0;
        int size = this.attributes.size();
        if (z2) {
            if (iArr == null) {
                for (int i2 = 0; z2 && i2 < size; i2++) {
                    z2 = shaderProgram.getAttributeLocation(this.attributes.get(i2).alias) == this.cachedLocations.d(i2);
                }
            } else {
                z2 = iArr.length == this.cachedLocations.f1850b;
                for (int i3 = 0; z2 && i3 < size; i3++) {
                    z2 = iArr[i3] == this.cachedLocations.d(i3);
                }
            }
        }
        if (z2) {
            return;
        }
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, this.bufferHandle);
        unbindAttributes(shaderProgram);
        this.cachedLocations.f1850b = 0;
        for (int i4 = 0; i4 < size; i4++) {
            VertexAttribute vertexAttribute = this.attributes.get(i4);
            if (iArr == null) {
                this.cachedLocations.a(shaderProgram.getAttributeLocation(vertexAttribute.alias));
            } else {
                this.cachedLocations.a(iArr[i4]);
            }
            int iD = this.cachedLocations.d(i4);
            if (iD >= 0) {
                shaderProgram.enableVertexAttribute(iD);
                shaderProgram.setVertexAttribute(iD, vertexAttribute.numComponents, vertexAttribute.type, vertexAttribute.normalized, this.attributes.vertexSize, vertexAttribute.offset);
            }
        }
    }

    private void bindData(GL20 gl20) {
        if (this.isDirty) {
            gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, this.bufferHandle);
            this.byteBuffer.limit(this.buffer.limit() * 4);
            gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    private void bufferChanged() {
        if (this.isBound) {
            Gdx.gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, this.bufferHandle);
            Gdx.gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    private void createVAO() {
        IntBuffer intBuffer = tmpHandle;
        intBuffer.clear();
        Gdx.gl30.glGenVertexArrays(1, intBuffer);
        this.vaoHandle = intBuffer.get();
    }

    private void deleteVAO() {
        if (this.vaoHandle != -1) {
            IntBuffer intBuffer = tmpHandle;
            intBuffer.clear();
            intBuffer.put(this.vaoHandle);
            intBuffer.flip();
            Gdx.gl30.glDeleteVertexArrays(1, intBuffer);
            this.vaoHandle = -1;
        }
    }

    private void unbindAttributes(ShaderProgram shaderProgram) {
        if (this.cachedLocations.f1850b == 0) {
            return;
        }
        int size = this.attributes.size();
        for (int i2 = 0; i2 < size; i2++) {
            int iD = this.cachedLocations.d(i2);
            if (iD >= 0) {
                shaderProgram.disableVertexAttribute(iD);
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData, com.badlogic.gdx.utils.i
    public void dispose() {
        GL30 gl30 = Gdx.gl30;
        gl30.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        gl30.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
        if (this.ownsBuffer) {
            BufferUtils.f(this.byteBuffer);
        }
        deleteVAO();
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public FloatBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public int getNumMaxVertices() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public int getNumVertices() {
        return (this.buffer.limit() * 4) / this.attributes.vertexSize;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void invalidate() {
        this.bufferHandle = Gdx.gl30.glGenBuffer();
        createVAO();
        this.isDirty = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void setVertices(float[] fArr, int i2, int i3) {
        this.isDirty = true;
        BufferUtils.d(fArr, this.byteBuffer, i3, i2);
        this.buffer.position(0);
        this.buffer.limit(i3);
        bufferChanged();
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram) {
        unbind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void updateVertices(int i2, float[] fArr, int i3, int i4) {
        this.isDirty = true;
        int iPosition = this.byteBuffer.position();
        this.byteBuffer.position(i2 * 4);
        BufferUtils.c(fArr, i3, i4, this.byteBuffer);
        this.byteBuffer.position(iPosition);
        this.buffer.position(0);
        bufferChanged();
    }

    public VertexBufferObjectWithVAO(boolean z2, int i2, VertexAttributes vertexAttributes) {
        this.isDirty = false;
        this.isBound = false;
        this.vaoHandle = -1;
        this.cachedLocations = new o();
        this.isStatic = z2;
        this.attributes = vertexAttributes;
        ByteBuffer byteBufferH = BufferUtils.h(vertexAttributes.vertexSize * i2);
        this.byteBuffer = byteBufferH;
        FloatBuffer floatBufferAsFloatBuffer = byteBufferH.asFloatBuffer();
        this.buffer = floatBufferAsFloatBuffer;
        this.ownsBuffer = true;
        floatBufferAsFloatBuffer.flip();
        byteBufferH.flip();
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z2 ? GL20.GL_STATIC_DRAW : GL20.GL_DYNAMIC_DRAW;
        createVAO();
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram, int[] iArr) {
        GL30 gl30 = Gdx.gl30;
        gl30.glBindVertexArray(this.vaoHandle);
        bindAttributes(shaderProgram, iArr);
        bindData(gl30);
        this.isBound = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram, int[] iArr) {
        Gdx.gl30.glBindVertexArray(0);
        this.isBound = false;
    }

    public VertexBufferObjectWithVAO(boolean z2, ByteBuffer byteBuffer, VertexAttributes vertexAttributes) {
        this.isDirty = false;
        this.isBound = false;
        this.vaoHandle = -1;
        this.cachedLocations = new o();
        this.isStatic = z2;
        this.attributes = vertexAttributes;
        this.byteBuffer = byteBuffer;
        this.ownsBuffer = false;
        FloatBuffer floatBufferAsFloatBuffer = byteBuffer.asFloatBuffer();
        this.buffer = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.flip();
        byteBuffer.flip();
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.usage = z2 ? GL20.GL_STATIC_DRAW : GL20.GL_DYNAMIC_DRAW;
        createVAO();
    }
}

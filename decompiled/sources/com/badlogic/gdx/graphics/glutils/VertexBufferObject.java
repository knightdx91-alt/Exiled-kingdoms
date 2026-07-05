package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VertexBufferObject implements VertexData {
    private VertexAttributes attributes;
    private FloatBuffer buffer;
    private int bufferHandle;
    private ByteBuffer byteBuffer;
    boolean isBound;
    boolean isDirty;
    private boolean ownsBuffer;
    private int usage;

    public VertexBufferObject(boolean z2, int i2, VertexAttribute... vertexAttributeArr) {
        this(z2, i2, new VertexAttributes(vertexAttributeArr));
    }

    private void bufferChanged() {
        if (this.isBound) {
            Gdx.gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData, com.badlogic.gdx.utils.i
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        gl20.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
        if (this.ownsBuffer) {
            BufferUtils.f(this.byteBuffer);
        }
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

    protected int getUsage() {
        return this.usage;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void invalidate() {
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        this.isDirty = true;
    }

    protected void setBuffer(Buffer buffer, boolean z2, VertexAttributes vertexAttributes) {
        ByteBuffer byteBuffer;
        if (this.isBound) {
            throw new m("Cannot change attributes while VBO is bound");
        }
        if (this.ownsBuffer && (byteBuffer = this.byteBuffer) != null) {
            BufferUtils.f(byteBuffer);
        }
        this.attributes = vertexAttributes;
        if (!(buffer instanceof ByteBuffer)) {
            throw new m("Only ByteBuffer is currently supported");
        }
        ByteBuffer byteBuffer2 = (ByteBuffer) buffer;
        this.byteBuffer = byteBuffer2;
        this.ownsBuffer = z2;
        int iLimit = byteBuffer2.limit();
        ByteBuffer byteBuffer3 = this.byteBuffer;
        byteBuffer3.limit(byteBuffer3.capacity());
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.byteBuffer.limit(iLimit);
        this.buffer.limit(iLimit / 4);
    }

    protected void setUsage(int i2) {
        if (this.isBound) {
            throw new m("Cannot change usage while VBO is bound");
        }
        this.usage = i2;
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

    public VertexBufferObject(boolean z2, int i2, VertexAttributes vertexAttributes) {
        this.isDirty = false;
        this.isBound = false;
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        ByteBuffer byteBufferH = BufferUtils.h(vertexAttributes.vertexSize * i2);
        byteBufferH.limit(0);
        setBuffer(byteBufferH, true, vertexAttributes);
        setUsage(z2 ? GL20.GL_STATIC_DRAW : GL20.GL_DYNAMIC_DRAW);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram, int[] iArr) {
        GL20 gl20 = Gdx.gl20;
        gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, this.bufferHandle);
        int i2 = 0;
        if (this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 4);
            gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }
        int size = this.attributes.size();
        if (iArr == null) {
            while (i2 < size) {
                VertexAttribute vertexAttribute = this.attributes.get(i2);
                int attributeLocation = shaderProgram.getAttributeLocation(vertexAttribute.alias);
                if (attributeLocation >= 0) {
                    shaderProgram.enableVertexAttribute(attributeLocation);
                    shaderProgram.setVertexAttribute(attributeLocation, vertexAttribute.numComponents, vertexAttribute.type, vertexAttribute.normalized, this.attributes.vertexSize, vertexAttribute.offset);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                VertexAttribute vertexAttribute2 = this.attributes.get(i2);
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    shaderProgram.enableVertexAttribute(i3);
                    shaderProgram.setVertexAttribute(i3, vertexAttribute2.numComponents, vertexAttribute2.type, vertexAttribute2.normalized, this.attributes.vertexSize, vertexAttribute2.offset);
                }
                i2++;
            }
        }
        this.isBound = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram, int[] iArr) {
        GL20 gl20 = Gdx.gl20;
        int size = this.attributes.size();
        if (iArr == null) {
            for (int i2 = 0; i2 < size; i2++) {
                shaderProgram.disableVertexAttribute(this.attributes.get(i2).alias);
            }
        } else {
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = iArr[i3];
                if (i4 >= 0) {
                    shaderProgram.disableVertexAttribute(i4);
                }
            }
        }
        gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        this.isBound = false;
    }

    protected VertexBufferObject(int i2, ByteBuffer byteBuffer, boolean z2, VertexAttributes vertexAttributes) {
        this.isDirty = false;
        this.isBound = false;
        this.bufferHandle = Gdx.gl20.glGenBuffer();
        setBuffer(byteBuffer, z2, vertexAttributes);
        setUsage(i2);
    }
}

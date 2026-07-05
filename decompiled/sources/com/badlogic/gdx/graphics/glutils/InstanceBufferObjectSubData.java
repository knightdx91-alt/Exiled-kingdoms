package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class InstanceBufferObjectSubData implements InstanceData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    boolean isBound;
    final boolean isDirect;
    boolean isDirty;
    final boolean isStatic;
    final int usage;

    public InstanceBufferObjectSubData(boolean z2, int i2, VertexAttribute... vertexAttributeArr) {
        this(z2, i2, new VertexAttributes(vertexAttributeArr));
    }

    private void bufferChanged() {
        if (this.isBound) {
            Gdx.gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.limit(), null, this.usage);
            Gdx.gl20.glBufferSubData(GL20.GL_ARRAY_BUFFER, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    private int createBufferObject() {
        int iGlGenBuffer = Gdx.gl20.glGenBuffer();
        Gdx.gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, iGlGenBuffer);
        Gdx.gl20.glBufferData(GL20.GL_ARRAY_BUFFER, this.byteBuffer.capacity(), null, this.usage);
        Gdx.gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        return iGlGenBuffer;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData, com.badlogic.gdx.utils.i
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        gl20.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public FloatBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    public int getBufferHandle() {
        return this.bufferHandle;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumInstances() {
        return (this.buffer.limit() * 4) / this.attributes.vertexSize;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public int getNumMaxInstances() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void invalidate() {
        this.bufferHandle = createBufferObject();
        this.isDirty = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(float[] fArr, int i2, int i3) {
        this.isDirty = true;
        if (this.isDirect) {
            BufferUtils.d(fArr, this.byteBuffer, i3, i2);
            this.buffer.position(0);
            this.buffer.limit(i3);
        } else {
            this.buffer.clear();
            this.buffer.put(fArr, i2, i3);
            this.buffer.flip();
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.buffer.limit() << 2);
        }
        bufferChanged();
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void unbind(ShaderProgram shaderProgram) {
        unbind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int i2, float[] fArr, int i3, int i4) {
        this.isDirty = true;
        if (!this.isDirect) {
            throw new m("Buffer must be allocated direct.");
        }
        int iPosition = this.byteBuffer.position();
        this.byteBuffer.position(i2 * 4);
        BufferUtils.c(fArr, i3, i4, this.byteBuffer);
        this.byteBuffer.position(iPosition);
        bufferChanged();
    }

    public InstanceBufferObjectSubData(boolean z2, int i2, VertexAttributes vertexAttributes) {
        this.isDirty = false;
        this.isBound = false;
        this.isStatic = z2;
        this.attributes = vertexAttributes;
        int i3 = vertexAttributes.vertexSize * i2;
        int i4 = BufferUtils.f1745b;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i3);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.byteBuffer = byteBufferAllocateDirect;
        this.isDirect = true;
        this.usage = z2 ? GL20.GL_STATIC_DRAW : GL20.GL_DYNAMIC_DRAW;
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        this.buffer = floatBufferAsFloatBuffer;
        this.bufferHandle = createBufferObject();
        floatBufferAsFloatBuffer.flip();
        byteBufferAllocateDirect.flip();
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
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
                    int i3 = attributeLocation + vertexAttribute.unit;
                    shaderProgram.enableVertexAttribute(i3);
                    shaderProgram.setVertexAttribute(i3, vertexAttribute.numComponents, vertexAttribute.type, vertexAttribute.normalized, this.attributes.vertexSize, vertexAttribute.offset);
                    Gdx.gl30.glVertexAttribDivisor(i3, 1);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                VertexAttribute vertexAttribute2 = this.attributes.get(i2);
                int i4 = iArr[i2];
                if (i4 >= 0) {
                    int i5 = i4 + vertexAttribute2.unit;
                    shaderProgram.enableVertexAttribute(i5);
                    shaderProgram.setVertexAttribute(i5, vertexAttribute2.numComponents, vertexAttribute2.type, vertexAttribute2.normalized, this.attributes.vertexSize, vertexAttribute2.offset);
                    Gdx.gl30.glVertexAttribDivisor(i5, 1);
                }
                i2++;
            }
        }
        this.isBound = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void unbind(ShaderProgram shaderProgram, int[] iArr) {
        GL20 gl20 = Gdx.gl20;
        int size = this.attributes.size();
        if (iArr == null) {
            for (int i2 = 0; i2 < size; i2++) {
                VertexAttribute vertexAttribute = this.attributes.get(i2);
                int attributeLocation = shaderProgram.getAttributeLocation(vertexAttribute.alias);
                if (attributeLocation >= 0) {
                    shaderProgram.disableVertexAttribute(attributeLocation + vertexAttribute.unit);
                }
            }
        } else {
            for (int i3 = 0; i3 < size; i3++) {
                VertexAttribute vertexAttribute2 = this.attributes.get(i3);
                int i4 = iArr[i3];
                if (i4 >= 0) {
                    shaderProgram.enableVertexAttribute(i4 + vertexAttribute2.unit);
                }
            }
        }
        gl20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        this.isBound = false;
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void updateInstanceData(int i2, FloatBuffer floatBuffer, int i3, int i4) {
        this.isDirty = true;
        if (this.isDirect) {
            int iPosition = this.byteBuffer.position();
            this.byteBuffer.position(i2 * 4);
            floatBuffer.position(i3 * 4);
            BufferUtils.b(floatBuffer, this.byteBuffer, i4);
            this.byteBuffer.position(iPosition);
            bufferChanged();
            return;
        }
        throw new m("Buffer must be allocated direct.");
    }

    @Override // com.badlogic.gdx.graphics.glutils.InstanceData
    public void setInstanceData(FloatBuffer floatBuffer, int i2) {
        this.isDirty = true;
        if (this.isDirect) {
            BufferUtils.b(floatBuffer, this.byteBuffer, i2);
            this.buffer.position(0);
            this.buffer.limit(i2);
        } else {
            this.buffer.clear();
            this.buffer.put(floatBuffer);
            this.buffer.flip();
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.buffer.limit() << 2);
        }
        bufferChanged();
    }
}

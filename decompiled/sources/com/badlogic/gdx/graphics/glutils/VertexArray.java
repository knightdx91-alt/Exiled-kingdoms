package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VertexArray implements VertexData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    final ByteBuffer byteBuffer;
    boolean isBound;

    public VertexArray(int i2, VertexAttribute... vertexAttributeArr) {
        this(i2, new VertexAttributes(vertexAttributeArr));
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData, com.badlogic.gdx.utils.i
    public void dispose() {
        BufferUtils.f(this.byteBuffer);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public FloatBuffer getBuffer() {
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
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void setVertices(float[] fArr, int i2, int i3) {
        BufferUtils.d(fArr, this.byteBuffer, i3, i2);
        this.buffer.position(0);
        this.buffer.limit(i3);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram) {
        unbind(shaderProgram, null);
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void updateVertices(int i2, float[] fArr, int i3, int i4) {
        int iPosition = this.byteBuffer.position();
        this.byteBuffer.position(i2 * 4);
        BufferUtils.c(fArr, i3, i4, this.byteBuffer);
        this.byteBuffer.position(iPosition);
    }

    public VertexArray(int i2, VertexAttributes vertexAttributes) {
        this.isBound = false;
        this.attributes = vertexAttributes;
        ByteBuffer byteBufferH = BufferUtils.h(vertexAttributes.vertexSize * i2);
        this.byteBuffer = byteBufferH;
        FloatBuffer floatBufferAsFloatBuffer = byteBufferH.asFloatBuffer();
        this.buffer = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.flip();
        byteBufferH.flip();
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void bind(ShaderProgram shaderProgram, int[] iArr) {
        int size = this.attributes.size();
        this.byteBuffer.limit(this.buffer.limit() * 4);
        int i2 = 0;
        if (iArr == null) {
            while (i2 < size) {
                VertexAttribute vertexAttribute = this.attributes.get(i2);
                int attributeLocation = shaderProgram.getAttributeLocation(vertexAttribute.alias);
                if (attributeLocation >= 0) {
                    shaderProgram.enableVertexAttribute(attributeLocation);
                    if (vertexAttribute.type == 5126) {
                        this.buffer.position(vertexAttribute.offset / 4);
                        shaderProgram.setVertexAttribute(attributeLocation, vertexAttribute.numComponents, vertexAttribute.type, vertexAttribute.normalized, this.attributes.vertexSize, this.buffer);
                    } else {
                        this.byteBuffer.position(vertexAttribute.offset);
                        shaderProgram.setVertexAttribute(attributeLocation, vertexAttribute.numComponents, vertexAttribute.type, vertexAttribute.normalized, this.attributes.vertexSize, this.byteBuffer);
                    }
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                VertexAttribute vertexAttribute2 = this.attributes.get(i2);
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    shaderProgram.enableVertexAttribute(i3);
                    if (vertexAttribute2.type == 5126) {
                        this.buffer.position(vertexAttribute2.offset / 4);
                        shaderProgram.setVertexAttribute(i3, vertexAttribute2.numComponents, vertexAttribute2.type, vertexAttribute2.normalized, this.attributes.vertexSize, this.buffer);
                    } else {
                        this.byteBuffer.position(vertexAttribute2.offset);
                        shaderProgram.setVertexAttribute(i3, vertexAttribute2.numComponents, vertexAttribute2.type, vertexAttribute2.normalized, this.attributes.vertexSize, this.byteBuffer);
                    }
                }
                i2++;
            }
        }
        this.isBound = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.VertexData
    public void unbind(ShaderProgram shaderProgram, int[] iArr) {
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
        this.isBound = false;
    }
}

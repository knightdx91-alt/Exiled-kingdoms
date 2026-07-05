package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class IndexBufferObjectSubData implements IndexData {
    final ShortBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    final boolean isDirect;
    final int usage;
    boolean isDirty = true;
    boolean isBound = false;

    public IndexBufferObjectSubData(boolean z2, int i2) {
        int i3 = BufferUtils.f1745b;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2 * 2);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.byteBuffer = byteBufferAllocateDirect;
        this.isDirect = true;
        this.usage = z2 ? GL20.GL_STATIC_DRAW : GL20.GL_DYNAMIC_DRAW;
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        this.buffer = shortBufferAsShortBuffer;
        shortBufferAsShortBuffer.flip();
        byteBufferAllocateDirect.flip();
        this.bufferHandle = createBufferObject();
    }

    private int createBufferObject() {
        int iGlGenBuffer = Gdx.gl20.glGenBuffer();
        Gdx.gl20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, iGlGenBuffer);
        Gdx.gl20.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, this.byteBuffer.capacity(), null, this.usage);
        Gdx.gl20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
        return iGlGenBuffer;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void bind() {
        int i2 = this.bufferHandle;
        if (i2 == 0) {
            throw new m("IndexBufferObject cannot be used after it has been disposed.");
        }
        Gdx.gl20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, i2);
        if (this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 2);
            Gdx.gl20.glBufferSubData(GL20.GL_ELEMENT_ARRAY_BUFFER, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
        this.isBound = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData, com.badlogic.gdx.utils.i
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        gl20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
        gl20.glDeleteBuffer(this.bufferHandle);
        this.bufferHandle = 0;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public ShortBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumIndices() {
        return this.buffer.limit();
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumMaxIndices() {
        return this.buffer.capacity();
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void invalidate() {
        this.bufferHandle = createBufferObject();
        this.isDirty = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(short[] sArr, int i2, int i3) {
        this.isDirty = true;
        this.buffer.clear();
        this.buffer.put(sArr, i2, i3);
        this.buffer.flip();
        this.byteBuffer.position(0);
        this.byteBuffer.limit(i3 << 1);
        if (this.isBound) {
            Gdx.gl20.glBufferSubData(GL20.GL_ELEMENT_ARRAY_BUFFER, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void unbind() {
        Gdx.gl20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
        this.isBound = false;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void updateIndices(int i2, short[] sArr, int i3, int i4) {
        this.isDirty = true;
        int iPosition = this.byteBuffer.position();
        this.byteBuffer.position(i2 * 2);
        BufferUtils.e(sArr, i3, this.byteBuffer, i4);
        this.byteBuffer.position(iPosition);
        this.buffer.position(0);
        if (this.isBound) {
            Gdx.gl20.glBufferSubData(GL20.GL_ELEMENT_ARRAY_BUFFER, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(ShortBuffer shortBuffer) {
        int iPosition = shortBuffer.position();
        this.isDirty = true;
        this.buffer.clear();
        this.buffer.put(shortBuffer);
        this.buffer.flip();
        shortBuffer.position(iPosition);
        this.byteBuffer.position(0);
        this.byteBuffer.limit(this.buffer.limit() << 1);
        if (this.isBound) {
            Gdx.gl20.glBufferSubData(GL20.GL_ELEMENT_ARRAY_BUFFER, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }
    }

    public IndexBufferObjectSubData(int i2) {
        int i3 = BufferUtils.f1745b;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2 * 2);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.byteBuffer = byteBufferAllocateDirect;
        this.isDirect = true;
        this.usage = GL20.GL_STATIC_DRAW;
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        this.buffer = shortBufferAsShortBuffer;
        shortBufferAsShortBuffer.flip();
        byteBufferAllocateDirect.flip();
        this.bufferHandle = createBufferObject();
    }
}

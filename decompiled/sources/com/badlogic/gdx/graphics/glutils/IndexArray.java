package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class IndexArray implements IndexData {
    final ShortBuffer buffer;
    final ByteBuffer byteBuffer;
    private final boolean empty;

    public IndexArray(int i2) {
        boolean z2 = i2 == 0;
        this.empty = z2;
        ByteBuffer byteBufferH = BufferUtils.h((z2 ? 1 : i2) * 2);
        this.byteBuffer = byteBufferH;
        ShortBuffer shortBufferAsShortBuffer = byteBufferH.asShortBuffer();
        this.buffer = shortBufferAsShortBuffer;
        shortBufferAsShortBuffer.flip();
        byteBufferH.flip();
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void bind() {
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData, com.badlogic.gdx.utils.i
    public void dispose() {
        BufferUtils.f(this.byteBuffer);
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public ShortBuffer getBuffer() {
        return this.buffer;
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumIndices() {
        if (this.empty) {
            return 0;
        }
        return this.buffer.limit();
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public int getNumMaxIndices() {
        if (this.empty) {
            return 0;
        }
        return this.buffer.capacity();
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void invalidate() {
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(short[] sArr, int i2, int i3) {
        this.buffer.clear();
        this.buffer.put(sArr, i2, i3);
        this.buffer.flip();
        this.byteBuffer.position(0);
        this.byteBuffer.limit(i3 << 1);
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void unbind() {
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void updateIndices(int i2, short[] sArr, int i3, int i4) {
        int iPosition = this.byteBuffer.position();
        this.byteBuffer.position(i2 * 2);
        BufferUtils.e(sArr, i3, this.byteBuffer, i4);
        this.byteBuffer.position(iPosition);
    }

    @Override // com.badlogic.gdx.graphics.glutils.IndexData
    public void setIndices(ShortBuffer shortBuffer) {
        int iPosition = shortBuffer.position();
        this.buffer.clear();
        this.buffer.limit(shortBuffer.remaining());
        this.buffer.put(shortBuffer);
        this.buffer.flip();
        shortBuffer.position(iPosition);
        this.byteBuffer.position(0);
        this.byteBuffer.limit(this.buffer.limit() << 1);
    }
}

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FloatFrameBuffer extends FrameBuffer {
    FloatFrameBuffer() {
    }

    protected FloatFrameBuffer(GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<Texture>> gLFrameBufferBuilder) {
        super(gLFrameBufferBuilder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.FrameBuffer, com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public Texture createTexture(GLFrameBuffer.FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec) {
        GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        Texture texture = new Texture(new FloatTextureData(gLFrameBufferBuilder.width, gLFrameBufferBuilder.height, frameBufferTextureAttachmentSpec.internalFormat, frameBufferTextureAttachmentSpec.format, frameBufferTextureAttachmentSpec.type, frameBufferTextureAttachmentSpec.isGpuOnly));
        if (Gdx.app.getType() == Application.a.f1564b || Gdx.app.getType() == Application.a.f1565c) {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        } else {
            Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Nearest;
            texture.setFilter(textureFilter2, textureFilter2);
        }
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        texture.setWrap(textureWrap, textureWrap);
        return texture;
    }

    public FloatFrameBuffer(int i2, int i3, boolean z2) {
        GLFrameBuffer.FloatFrameBufferBuilder floatFrameBufferBuilder = new GLFrameBuffer.FloatFrameBufferBuilder(i2, i3);
        floatFrameBufferBuilder.addFloatAttachment(GL30.GL_RGBA32F, GL20.GL_RGBA, GL20.GL_FLOAT, false);
        if (z2) {
            floatFrameBufferBuilder.addBasicDepthRenderBuffer();
        }
        this.bufferBuilder = floatFrameBufferBuilder;
        build();
    }
}

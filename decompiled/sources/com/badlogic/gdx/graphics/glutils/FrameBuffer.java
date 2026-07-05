package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FrameBuffer extends GLFrameBuffer<Texture> {
    FrameBuffer() {
    }

    public static void unbind() {
        GLFrameBuffer.unbind();
    }

    protected FrameBuffer(GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<Texture>> gLFrameBufferBuilder) {
        super(gLFrameBufferBuilder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void attachFrameBufferColorTexture(Texture texture) {
        Gdx.gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, texture.getTextureObjectHandle(), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public Texture createTexture(GLFrameBuffer.FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec) {
        GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        Texture texture = new Texture(new GLOnlyTextureData(gLFrameBufferBuilder.width, gLFrameBufferBuilder.height, 0, frameBufferTextureAttachmentSpec.internalFormat, frameBufferTextureAttachmentSpec.format, frameBufferTextureAttachmentSpec.type));
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        texture.setFilter(textureFilter, textureFilter);
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        texture.setWrap(textureWrap, textureWrap);
        return texture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void disposeColorTexture(Texture texture) {
        texture.dispose();
    }

    public FrameBuffer(Pixmap.Format format, int i2, int i3, boolean z2) {
        this(format, i2, i3, z2, false);
    }

    public FrameBuffer(Pixmap.Format format, int i2, int i3, boolean z2, boolean z3) {
        GLFrameBuffer.FrameBufferBuilder frameBufferBuilder = new GLFrameBuffer.FrameBufferBuilder(i2, i3);
        frameBufferBuilder.addBasicColorTextureAttachment(format);
        if (z2) {
            frameBufferBuilder.addBasicDepthRenderBuffer();
        }
        if (z3) {
            frameBufferBuilder.addBasicStencilRenderBuffer();
        }
        this.bufferBuilder = frameBufferBuilder;
        build();
    }
}

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FrameBufferCubemap extends GLFrameBuffer<Cubemap> {
    private static final Cubemap.CubemapSide[] cubemapSides = Cubemap.CubemapSide.values();
    private int currentSide;

    FrameBufferCubemap() {
    }

    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void bind() {
        this.currentSide = -1;
        super.bind();
    }

    protected void bindSide(Cubemap.CubemapSide cubemapSide) {
        Gdx.gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, cubemapSide.glEnum, getColorBufferTexture().getTextureObjectHandle(), 0);
    }

    public Cubemap.CubemapSide getSide() {
        int i2 = this.currentSide;
        if (i2 < 0) {
            return null;
        }
        return cubemapSides[i2];
    }

    public boolean nextSide() {
        int i2 = this.currentSide;
        if (i2 > 5) {
            throw new m("No remaining sides.");
        }
        if (i2 == 5) {
            return false;
        }
        this.currentSide = i2 + 1;
        bindSide(getSide());
        return true;
    }

    protected FrameBufferCubemap(GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<Cubemap>> gLFrameBufferBuilder) {
        super(gLFrameBufferBuilder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void attachFrameBufferColorTexture(Cubemap cubemap) {
        GL20 gl20 = Gdx.gl20;
        int textureObjectHandle = cubemap.getTextureObjectHandle();
        for (Cubemap.CubemapSide cubemapSide : Cubemap.CubemapSide.values()) {
            gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, cubemapSide.glEnum, textureObjectHandle, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public Cubemap createTexture(GLFrameBuffer.FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec) {
        GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        GLOnlyTextureData gLOnlyTextureData = new GLOnlyTextureData(gLFrameBufferBuilder.width, gLFrameBufferBuilder.height, 0, frameBufferTextureAttachmentSpec.internalFormat, frameBufferTextureAttachmentSpec.format, frameBufferTextureAttachmentSpec.type);
        Cubemap cubemap = new Cubemap(gLOnlyTextureData, gLOnlyTextureData, gLOnlyTextureData, gLOnlyTextureData, gLOnlyTextureData, gLOnlyTextureData);
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        cubemap.setFilter(textureFilter, textureFilter);
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        cubemap.setWrap(textureWrap, textureWrap);
        return cubemap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void disposeColorTexture(Cubemap cubemap) {
        cubemap.dispose();
    }

    public FrameBufferCubemap(Pixmap.Format format, int i2, int i3, boolean z2) {
        this(format, i2, i3, z2, false);
    }

    public FrameBufferCubemap(Pixmap.Format format, int i2, int i3, boolean z2, boolean z3) {
        GLFrameBuffer.FrameBufferCubemapBuilder frameBufferCubemapBuilder = new GLFrameBuffer.FrameBufferCubemapBuilder(i2, i3);
        frameBufferCubemapBuilder.addBasicColorTextureAttachment(format);
        if (z2) {
            frameBufferCubemapBuilder.addBasicDepthRenderBuffer();
        }
        if (z3) {
            frameBufferCubemapBuilder.addBasicStencilRenderBuffer();
        }
        this.bufferBuilder = frameBufferCubemapBuilder;
        build();
    }
}

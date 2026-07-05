package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class GLFrameBuffer<T extends GLTexture> implements i {
    protected static final int GL_DEPTH24_STENCIL8_OES = 35056;
    protected static int defaultFramebufferHandle;
    protected GLFrameBufferBuilder<? extends GLFrameBuffer<T>> bufferBuilder;
    protected int depthStencilPackedBufferHandle;
    protected int depthbufferHandle;
    protected int framebufferHandle;
    protected boolean hasDepthStencilPackedBuffer;
    protected boolean isMRT;
    protected int stencilbufferHandle;
    protected a<T> textureAttachments = new a<>();
    protected static final Map<Application, a<GLFrameBuffer>> buffers = new HashMap();
    protected static boolean defaultFramebufferHandleInitialized = false;

    public static class FloatFrameBufferBuilder extends GLFrameBufferBuilder<FloatFrameBuffer> {
        public FloatFrameBufferBuilder(int i2, int i3) {
            super(i2, i3);
        }

        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FloatFrameBuffer build() {
            return new FloatFrameBuffer(this);
        }
    }

    public static class FrameBufferBuilder extends GLFrameBufferBuilder<FrameBuffer> {
        public FrameBufferBuilder(int i2, int i3) {
            super(i2, i3);
        }

        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FrameBuffer build() {
            return new FrameBuffer(this);
        }
    }

    public static class FrameBufferCubemapBuilder extends GLFrameBufferBuilder<FrameBufferCubemap> {
        public FrameBufferCubemapBuilder(int i2, int i3) {
            super(i2, i3);
        }

        @Override // com.badlogic.gdx.graphics.glutils.GLFrameBuffer.GLFrameBufferBuilder
        public FrameBufferCubemap build() {
            return new FrameBufferCubemap(this);
        }
    }

    protected static class FrameBufferRenderBufferAttachmentSpec {
        int internalFormat;

        public FrameBufferRenderBufferAttachmentSpec(int i2) {
            this.internalFormat = i2;
        }
    }

    protected static class FrameBufferTextureAttachmentSpec {
        int format;
        int internalFormat;
        boolean isDepth;
        boolean isFloat;
        boolean isGpuOnly;
        boolean isStencil;
        int type;

        public FrameBufferTextureAttachmentSpec(int i2, int i3, int i4) {
            this.internalFormat = i2;
            this.format = i3;
            this.type = i4;
        }

        public boolean isColorTexture() {
            return (this.isDepth || this.isStencil) ? false : true;
        }
    }

    public static abstract class GLFrameBufferBuilder<U extends GLFrameBuffer<? extends GLTexture>> {
        protected FrameBufferRenderBufferAttachmentSpec depthRenderBufferSpec;
        protected boolean hasDepthRenderBuffer;
        protected boolean hasPackedStencilDepthRenderBuffer;
        protected boolean hasStencilRenderBuffer;
        protected int height;
        protected FrameBufferRenderBufferAttachmentSpec packedStencilDepthRenderBufferSpec;
        protected FrameBufferRenderBufferAttachmentSpec stencilRenderBufferSpec;
        protected a<FrameBufferTextureAttachmentSpec> textureAttachmentSpecs = new a<>();
        protected int width;

        public GLFrameBufferBuilder(int i2, int i3) {
            this.width = i2;
            this.height = i3;
        }

        public GLFrameBufferBuilder<U> addBasicColorTextureAttachment(Pixmap.Format format) {
            int glFormat = Pixmap.Format.toGlFormat(format);
            return addColorTextureAttachment(glFormat, glFormat, Pixmap.Format.toGlType(format));
        }

        public GLFrameBufferBuilder<U> addBasicDepthRenderBuffer() {
            return addDepthRenderBuffer(GL20.GL_DEPTH_COMPONENT16);
        }

        public GLFrameBufferBuilder<U> addBasicStencilDepthPackedRenderBuffer() {
            return addStencilDepthPackedRenderBuffer(35056);
        }

        public GLFrameBufferBuilder<U> addBasicStencilRenderBuffer() {
            return addStencilRenderBuffer(GL20.GL_STENCIL_INDEX8);
        }

        public GLFrameBufferBuilder<U> addColorTextureAttachment(int i2, int i3, int i4) {
            this.textureAttachmentSpecs.a(new FrameBufferTextureAttachmentSpec(i2, i3, i4));
            return this;
        }

        public GLFrameBufferBuilder<U> addDepthRenderBuffer(int i2) {
            this.depthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i2);
            this.hasDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addDepthTextureAttachment(int i2, int i3) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i2, GL20.GL_DEPTH_COMPONENT, i3);
            frameBufferTextureAttachmentSpec.isDepth = true;
            this.textureAttachmentSpecs.a(frameBufferTextureAttachmentSpec);
            return this;
        }

        public GLFrameBufferBuilder<U> addFloatAttachment(int i2, int i3, int i4, boolean z2) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i2, i3, i4);
            frameBufferTextureAttachmentSpec.isFloat = true;
            frameBufferTextureAttachmentSpec.isGpuOnly = z2;
            this.textureAttachmentSpecs.a(frameBufferTextureAttachmentSpec);
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilDepthPackedRenderBuffer(int i2) {
            this.packedStencilDepthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i2);
            this.hasPackedStencilDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilRenderBuffer(int i2) {
            this.stencilRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(i2);
            this.hasStencilRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder<U> addStencilTextureAttachment(int i2, int i3) {
            FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec = new FrameBufferTextureAttachmentSpec(i2, GL20.GL_STENCIL_ATTACHMENT, i3);
            frameBufferTextureAttachmentSpec.isStencil = true;
            this.textureAttachmentSpecs.a(frameBufferTextureAttachmentSpec);
            return this;
        }

        public abstract U build();
    }

    GLFrameBuffer() {
    }

    private static void addManagedFrameBuffer(Application application, GLFrameBuffer gLFrameBuffer) {
        Map<Application, a<GLFrameBuffer>> map = buffers;
        a<GLFrameBuffer> aVar = map.get(application);
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.a(gLFrameBuffer);
        map.put(application, aVar);
    }

    private void checkValidBuilder() {
        if (Gdx.graphics.isGL30Available()) {
            return;
        }
        GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        if (gLFrameBufferBuilder.hasPackedStencilDepthRenderBuffer) {
            throw new m("Packed Stencil/Render render buffers are not available on GLES 2.0");
        }
        a<FrameBufferTextureAttachmentSpec> aVar = gLFrameBufferBuilder.textureAttachmentSpecs;
        if (aVar.f1750b > 1) {
            throw new m("Multiple render targets not available on GLES 2.0");
        }
        a.b<FrameBufferTextureAttachmentSpec> it = aVar.iterator();
        while (it.hasNext()) {
            FrameBufferTextureAttachmentSpec next = it.next();
            if (next.isDepth) {
                throw new m("Depth texture FrameBuffer Attachment not available on GLES 2.0");
            }
            if (next.isStencil) {
                throw new m("Stencil texture FrameBuffer Attachment not available on GLES 2.0");
            }
            if (next.isFloat && !Gdx.graphics.supportsExtension("OES_texture_float")) {
                throw new m("Float texture FrameBuffer Attachment not available on GLES 2.0");
            }
        }
    }

    public static void clearAllFrameBuffers(Application application) {
        buffers.remove(application);
    }

    public static StringBuilder getManagedStatus(StringBuilder sb) {
        sb.append("Managed buffers/app: { ");
        Iterator<Application> it = buffers.keySet().iterator();
        while (it.hasNext()) {
            sb.append(buffers.get(it.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb;
    }

    public static void invalidateAllFrameBuffers(Application application) {
        a<GLFrameBuffer> aVar;
        if (Gdx.gl20 == null || (aVar = buffers.get(application)) == null) {
            return;
        }
        for (int i2 = 0; i2 < aVar.f1750b; i2++) {
            aVar.get(i2).build();
        }
    }

    public static void unbind() {
        Gdx.gl20.glBindFramebuffer(GL20.GL_FRAMEBUFFER, defaultFramebufferHandle);
    }

    protected abstract void attachFrameBufferColorTexture(T t2);

    public void begin() {
        bind();
        setFrameBufferViewport();
    }

    public void bind() {
        Gdx.gl20.glBindFramebuffer(GL20.GL_FRAMEBUFFER, this.framebufferHandle);
    }

    protected void build() {
        int i2;
        GL20 gl20 = Gdx.gl20;
        checkValidBuilder();
        if (!defaultFramebufferHandleInitialized) {
            defaultFramebufferHandleInitialized = true;
            if (Gdx.app.getType() == Application.a.f1567e) {
                IntBuffer intBufferAsIntBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
                gl20.glGetIntegerv(36006, intBufferAsIntBuffer);
                defaultFramebufferHandle = intBufferAsIntBuffer.get(0);
            } else {
                defaultFramebufferHandle = 0;
            }
        }
        int iGlGenFramebuffer = gl20.glGenFramebuffer();
        this.framebufferHandle = iGlGenFramebuffer;
        gl20.glBindFramebuffer(GL20.GL_FRAMEBUFFER, iGlGenFramebuffer);
        GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        int i3 = gLFrameBufferBuilder.width;
        int i4 = gLFrameBufferBuilder.height;
        if (gLFrameBufferBuilder.hasDepthRenderBuffer) {
            int iGlGenRenderbuffer = gl20.glGenRenderbuffer();
            this.depthbufferHandle = iGlGenRenderbuffer;
            gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, iGlGenRenderbuffer);
            gl20.glRenderbufferStorage(GL20.GL_RENDERBUFFER, this.bufferBuilder.depthRenderBufferSpec.internalFormat, i3, i4);
        }
        if (this.bufferBuilder.hasStencilRenderBuffer) {
            int iGlGenRenderbuffer2 = gl20.glGenRenderbuffer();
            this.stencilbufferHandle = iGlGenRenderbuffer2;
            gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, iGlGenRenderbuffer2);
            gl20.glRenderbufferStorage(GL20.GL_RENDERBUFFER, this.bufferBuilder.stencilRenderBufferSpec.internalFormat, i3, i4);
        }
        if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            int iGlGenRenderbuffer3 = gl20.glGenRenderbuffer();
            this.depthStencilPackedBufferHandle = iGlGenRenderbuffer3;
            gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, iGlGenRenderbuffer3);
            gl20.glRenderbufferStorage(GL20.GL_RENDERBUFFER, this.bufferBuilder.packedStencilDepthRenderBufferSpec.internalFormat, i3, i4);
        }
        a<FrameBufferTextureAttachmentSpec> aVar = this.bufferBuilder.textureAttachmentSpecs;
        boolean z2 = aVar.f1750b > 1;
        this.isMRT = z2;
        if (z2) {
            a.b<FrameBufferTextureAttachmentSpec> it = aVar.iterator();
            int i5 = 0;
            while (it.hasNext()) {
                FrameBufferTextureAttachmentSpec next = it.next();
                GLTexture gLTextureCreateTexture = createTexture(next);
                this.textureAttachments.a((T) gLTextureCreateTexture);
                if (next.isColorTexture()) {
                    gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, i5 + GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, gLTextureCreateTexture.getTextureObjectHandle(), 0);
                    i5++;
                } else if (next.isDepth) {
                    gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_DEPTH_ATTACHMENT, GL20.GL_TEXTURE_2D, gLTextureCreateTexture.getTextureObjectHandle(), 0);
                } else if (next.isStencil) {
                    gl20.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_STENCIL_ATTACHMENT, GL20.GL_TEXTURE_2D, gLTextureCreateTexture.getTextureObjectHandle(), 0);
                }
            }
            i2 = i5;
        } else {
            GLTexture gLTextureCreateTexture2 = createTexture(aVar.g());
            this.textureAttachments.a((T) gLTextureCreateTexture2);
            gl20.glBindTexture(gLTextureCreateTexture2.glTarget, gLTextureCreateTexture2.getTextureObjectHandle());
            i2 = 0;
        }
        if (this.isMRT) {
            IntBuffer intBufferG = BufferUtils.g(i2);
            for (int i6 = 0; i6 < i2; i6++) {
                intBufferG.put(i6 + GL20.GL_COLOR_ATTACHMENT0);
            }
            intBufferG.position(0);
            Gdx.gl30.glDrawBuffers(i2, intBufferG);
        } else {
            attachFrameBufferColorTexture(this.textureAttachments.g());
        }
        if (this.bufferBuilder.hasDepthRenderBuffer) {
            gl20.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_DEPTH_ATTACHMENT, GL20.GL_RENDERBUFFER, this.depthbufferHandle);
        }
        if (this.bufferBuilder.hasStencilRenderBuffer) {
            gl20.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_STENCIL_ATTACHMENT, GL20.GL_RENDERBUFFER, this.stencilbufferHandle);
        }
        if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            gl20.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, GL20.GL_RENDERBUFFER, this.depthStencilPackedBufferHandle);
        }
        gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, 0);
        a.b<T> it2 = this.textureAttachments.iterator();
        while (it2.hasNext()) {
            gl20.glBindTexture(it2.next().glTarget, 0);
        }
        int iGlCheckFramebufferStatus = gl20.glCheckFramebufferStatus(GL20.GL_FRAMEBUFFER);
        if (iGlCheckFramebufferStatus == 36061) {
            GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder2 = this.bufferBuilder;
            if (gLFrameBufferBuilder2.hasDepthRenderBuffer && gLFrameBufferBuilder2.hasStencilRenderBuffer && (Gdx.graphics.supportsExtension("GL_OES_packed_depth_stencil") || Gdx.graphics.supportsExtension("GL_EXT_packed_depth_stencil"))) {
                if (this.bufferBuilder.hasDepthRenderBuffer) {
                    gl20.glDeleteRenderbuffer(this.depthbufferHandle);
                    this.depthbufferHandle = 0;
                }
                if (this.bufferBuilder.hasStencilRenderBuffer) {
                    gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
                    this.stencilbufferHandle = 0;
                }
                if (this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
                    gl20.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
                    this.depthStencilPackedBufferHandle = 0;
                }
                int iGlGenRenderbuffer4 = gl20.glGenRenderbuffer();
                this.depthStencilPackedBufferHandle = iGlGenRenderbuffer4;
                this.hasDepthStencilPackedBuffer = true;
                gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, iGlGenRenderbuffer4);
                gl20.glRenderbufferStorage(GL20.GL_RENDERBUFFER, 35056, i3, i4);
                gl20.glBindRenderbuffer(GL20.GL_RENDERBUFFER, 0);
                gl20.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_DEPTH_ATTACHMENT, GL20.GL_RENDERBUFFER, this.depthStencilPackedBufferHandle);
                gl20.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_STENCIL_ATTACHMENT, GL20.GL_RENDERBUFFER, this.depthStencilPackedBufferHandle);
                iGlCheckFramebufferStatus = gl20.glCheckFramebufferStatus(GL20.GL_FRAMEBUFFER);
            }
        }
        gl20.glBindFramebuffer(GL20.GL_FRAMEBUFFER, defaultFramebufferHandle);
        if (iGlCheckFramebufferStatus == 36053) {
            addManagedFrameBuffer(Gdx.app, this);
            return;
        }
        a.b<T> it3 = this.textureAttachments.iterator();
        while (it3.hasNext()) {
            disposeColorTexture(it3.next());
        }
        if (this.hasDepthStencilPackedBuffer) {
            gl20.glDeleteBuffer(this.depthStencilPackedBufferHandle);
        } else {
            if (this.bufferBuilder.hasDepthRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.depthbufferHandle);
            }
            if (this.bufferBuilder.hasStencilRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
            }
        }
        gl20.glDeleteFramebuffer(this.framebufferHandle);
        if (iGlCheckFramebufferStatus == 36054) {
            throw new IllegalStateException("Frame buffer couldn't be constructed: incomplete attachment");
        }
        if (iGlCheckFramebufferStatus == 36057) {
            throw new IllegalStateException("Frame buffer couldn't be constructed: incomplete dimensions");
        }
        if (iGlCheckFramebufferStatus == 36055) {
            throw new IllegalStateException("Frame buffer couldn't be constructed: missing attachment");
        }
        if (iGlCheckFramebufferStatus != 36061) {
            throw new IllegalStateException(a.a.h(iGlCheckFramebufferStatus, "Frame buffer couldn't be constructed: unknown error "));
        }
        throw new IllegalStateException("Frame buffer couldn't be constructed: unsupported combination of formats");
    }

    protected abstract T createTexture(FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec);

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        GL20 gl20 = Gdx.gl20;
        a.b<T> it = this.textureAttachments.iterator();
        while (it.hasNext()) {
            disposeColorTexture(it.next());
        }
        if (this.hasDepthStencilPackedBuffer) {
            gl20.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
        } else {
            if (this.bufferBuilder.hasDepthRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.depthbufferHandle);
            }
            if (this.bufferBuilder.hasStencilRenderBuffer) {
                gl20.glDeleteRenderbuffer(this.stencilbufferHandle);
            }
        }
        gl20.glDeleteFramebuffer(this.framebufferHandle);
        Map<Application, a<GLFrameBuffer>> map = buffers;
        if (map.get(Gdx.app) != null) {
            map.get(Gdx.app).q(this, true);
        }
    }

    protected abstract void disposeColorTexture(T t2);

    public void end() {
        end(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public T getColorBufferTexture() {
        return this.textureAttachments.g();
    }

    public int getDepthBufferHandle() {
        return this.depthbufferHandle;
    }

    protected int getDepthStencilPackedBuffer() {
        return this.depthStencilPackedBufferHandle;
    }

    public int getFramebufferHandle() {
        return this.framebufferHandle;
    }

    public int getHeight() {
        return this.bufferBuilder.height;
    }

    public int getStencilBufferHandle() {
        return this.stencilbufferHandle;
    }

    public a<T> getTextureAttachments() {
        return this.textureAttachments;
    }

    public int getWidth() {
        return this.bufferBuilder.width;
    }

    protected void setFrameBufferViewport() {
        GL20 gl20 = Gdx.gl20;
        GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder = this.bufferBuilder;
        gl20.glViewport(0, 0, gLFrameBufferBuilder.width, gLFrameBufferBuilder.height);
    }

    public void end(int i2, int i3, int i4, int i5) {
        unbind();
        Gdx.gl20.glViewport(i2, i3, i4, i5);
    }

    protected GLFrameBuffer(GLFrameBufferBuilder<? extends GLFrameBuffer<T>> gLFrameBufferBuilder) {
        this.bufferBuilder = gLFrameBufferBuilder;
        build();
    }

    public static String getManagedStatus() {
        return getManagedStatus(new StringBuilder()).toString();
    }
}

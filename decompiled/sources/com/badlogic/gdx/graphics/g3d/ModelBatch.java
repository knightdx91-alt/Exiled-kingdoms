package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.DefaultRenderableSorter;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.RenderableSorter;
import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.k;
import com.badlogic.gdx.utils.m;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelBatch implements i {
    protected Camera camera;
    protected final RenderContext context;
    private final boolean ownContext;
    protected final a<Renderable> renderables;
    protected final RenderablePool renderablesPool;
    protected final ShaderProvider shaderProvider;
    protected final RenderableSorter sorter;

    protected static class RenderablePool extends k<Renderable> {
        protected RenderablePool() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.c0
        public Renderable newObject() {
            return new Renderable();
        }

        @Override // com.badlogic.gdx.utils.k, com.badlogic.gdx.utils.c0
        public Renderable obtain() {
            Renderable renderable = (Renderable) super.obtain();
            renderable.environment = null;
            renderable.material = null;
            renderable.meshPart.set("", null, 0, 0, 0);
            renderable.shader = null;
            renderable.userData = null;
            return renderable;
        }
    }

    public ModelBatch(RenderContext renderContext, ShaderProvider shaderProvider, RenderableSorter renderableSorter) {
        this.renderablesPool = new RenderablePool();
        this.renderables = new a<>();
        this.sorter = renderableSorter == null ? new DefaultRenderableSorter() : renderableSorter;
        this.ownContext = renderContext == null;
        this.context = renderContext == null ? new RenderContext(new DefaultTextureBinder(1, 1)) : renderContext;
        this.shaderProvider = shaderProvider == null ? new DefaultShaderProvider() : shaderProvider;
    }

    public void begin(Camera camera) {
        if (this.camera != null) {
            throw new m("Call end() first.");
        }
        this.camera = camera;
        if (this.ownContext) {
            this.context.begin();
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.shaderProvider.dispose();
    }

    public void end() {
        flush();
        if (this.ownContext) {
            this.context.end();
        }
        this.camera = null;
    }

    public void flush() {
        this.sorter.sort(this.camera, this.renderables);
        Shader shader = null;
        int i2 = 0;
        while (true) {
            a<Renderable> aVar = this.renderables;
            if (i2 >= aVar.f1750b) {
                break;
            }
            Renderable renderable = aVar.get(i2);
            if (shader != renderable.shader) {
                if (shader != null) {
                    shader.end();
                }
                shader = renderable.shader;
                shader.begin(this.camera, this.context);
            }
            shader.render(renderable);
            i2++;
        }
        if (shader != null) {
            shader.end();
        }
        this.renderablesPool.flush();
        this.renderables.clear();
    }

    public Camera getCamera() {
        return this.camera;
    }

    public RenderContext getRenderContext() {
        return this.context;
    }

    public RenderableSorter getRenderableSorter() {
        return this.sorter;
    }

    public ShaderProvider getShaderProvider() {
        return this.shaderProvider;
    }

    public boolean ownsRenderContext() {
        return this.ownContext;
    }

    public void render(Renderable renderable) {
        renderable.shader = this.shaderProvider.getShader(renderable);
        this.renderables.a(renderable);
    }

    public void setCamera(Camera camera) {
        if (this.camera == null) {
            throw new m("Call begin() first.");
        }
        if (this.renderables.f1750b > 0) {
            flush();
        }
        this.camera = camera;
    }

    public void render(RenderableProvider renderableProvider) {
        a<Renderable> aVar = this.renderables;
        int i2 = aVar.f1750b;
        renderableProvider.getRenderables(aVar, this.renderablesPool);
        while (true) {
            a<Renderable> aVar2 = this.renderables;
            if (i2 >= aVar2.f1750b) {
                return;
            }
            Renderable renderable = aVar2.get(i2);
            renderable.shader = this.shaderProvider.getShader(renderable);
            i2++;
        }
    }

    public ModelBatch(RenderContext renderContext, ShaderProvider shaderProvider) {
        this(renderContext, shaderProvider, null);
    }

    public <T extends RenderableProvider> void render(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            render(it.next());
        }
    }

    public ModelBatch(RenderContext renderContext, RenderableSorter renderableSorter) {
        this(renderContext, null, renderableSorter);
    }

    public ModelBatch(RenderContext renderContext) {
        this(renderContext, null, null);
    }

    public void render(RenderableProvider renderableProvider, Environment environment) {
        a<Renderable> aVar = this.renderables;
        int i2 = aVar.f1750b;
        renderableProvider.getRenderables(aVar, this.renderablesPool);
        while (true) {
            a<Renderable> aVar2 = this.renderables;
            if (i2 >= aVar2.f1750b) {
                return;
            }
            Renderable renderable = aVar2.get(i2);
            renderable.environment = environment;
            renderable.shader = this.shaderProvider.getShader(renderable);
            i2++;
        }
    }

    public ModelBatch(ShaderProvider shaderProvider, RenderableSorter renderableSorter) {
        this(null, shaderProvider, renderableSorter);
    }

    public ModelBatch(RenderableSorter renderableSorter) {
        this(null, null, renderableSorter);
    }

    public ModelBatch(ShaderProvider shaderProvider) {
        this(null, shaderProvider, null);
    }

    public ModelBatch(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2) {
        this(null, new DefaultShaderProvider(aVar, aVar2), null);
    }

    public ModelBatch(String str, String str2) {
        this(null, new DefaultShaderProvider(str, str2), null);
    }

    public ModelBatch() {
        this(null, null, null);
    }

    public <T extends RenderableProvider> void render(Iterable<T> iterable, Environment environment) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            render(it.next(), environment);
        }
    }

    public void render(RenderableProvider renderableProvider, Shader shader) {
        a<Renderable> aVar = this.renderables;
        int i2 = aVar.f1750b;
        renderableProvider.getRenderables(aVar, this.renderablesPool);
        while (true) {
            a<Renderable> aVar2 = this.renderables;
            if (i2 >= aVar2.f1750b) {
                return;
            }
            Renderable renderable = aVar2.get(i2);
            renderable.shader = shader;
            renderable.shader = this.shaderProvider.getShader(renderable);
            i2++;
        }
    }

    public <T extends RenderableProvider> void render(Iterable<T> iterable, Shader shader) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            render(it.next(), shader);
        }
    }

    public void render(RenderableProvider renderableProvider, Environment environment, Shader shader) {
        a<Renderable> aVar = this.renderables;
        int i2 = aVar.f1750b;
        renderableProvider.getRenderables(aVar, this.renderablesPool);
        while (true) {
            a<Renderable> aVar2 = this.renderables;
            if (i2 >= aVar2.f1750b) {
                return;
            }
            Renderable renderable = aVar2.get(i2);
            renderable.environment = environment;
            renderable.shader = shader;
            renderable.shader = this.shaderProvider.getShader(renderable);
            i2++;
        }
    }

    public <T extends RenderableProvider> void render(Iterable<T> iterable, Environment environment, Shader shader) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            render(it.next(), environment, shader);
        }
    }
}

package com.badlogic.gdx.graphics.g3d.shaders;

import a.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DepthShader extends DefaultShader {
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    private static final Attributes tmpAttributes = new Attributes();
    private final FloatAttribute alphaTestAttribute;
    public final int numBones;
    public final int weights;

    public DepthShader(Renderable renderable) {
        this(renderable, new Config());
    }

    private static final Attributes combineAttributes(Renderable renderable) {
        Attributes attributes = tmpAttributes;
        attributes.clear();
        Environment environment = renderable.environment;
        if (environment != null) {
            attributes.set(environment);
        }
        Material material = renderable.material;
        if (material != null) {
            attributes.set(material);
        }
        return attributes;
    }

    public static String createPrefix(Renderable renderable, Config config) {
        String strCreatePrefix = DefaultShader.createPrefix(renderable, config);
        return !config.depthBufferOnly ? a.k(strCreatePrefix, "#define PackedDepthFlag\n") : strCreatePrefix;
    }

    public static final String getDefaultFragmentShader() {
        if (defaultFragmentShader == null) {
            defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.fragment.glsl").readString();
        }
        return defaultFragmentShader;
    }

    public static final String getDefaultVertexShader() {
        if (defaultVertexShader == null) {
            defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.vertex.glsl").readString();
        }
        return defaultVertexShader;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader, com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        super.begin(camera, renderContext);
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader, com.badlogic.gdx.graphics.g3d.Shader
    public boolean canRender(Renderable renderable) {
        Attributes attributesCombineAttributes = combineAttributes(renderable);
        long j2 = BlendingAttribute.Type;
        if (attributesCombineAttributes.has(j2)) {
            if ((this.attributesMask & j2) != j2) {
                return false;
            }
            long j3 = TextureAttribute.Diffuse;
            if (attributesCombineAttributes.has(j3) != ((this.attributesMask & j3) == j3)) {
                return false;
            }
        }
        return (((renderable.meshPart.mesh.getVertexAttributes().getMask() & 64) > 64L ? 1 : ((renderable.meshPart.mesh.getVertexAttributes().getMask() & 64) == 64L ? 0 : -1)) == 0) == (this.weights > 0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader, com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        super.end();
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader, com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void render(Renderable renderable, Attributes attributes) {
        long j2 = BlendingAttribute.Type;
        if (!attributes.has(j2)) {
            super.render(renderable, attributes);
            return;
        }
        BlendingAttribute blendingAttribute = (BlendingAttribute) attributes.get(j2);
        attributes.remove(j2);
        long j3 = FloatAttribute.AlphaTest;
        boolean zHas = attributes.has(j3);
        if (!zHas) {
            attributes.set(this.alphaTestAttribute);
        }
        if (blendingAttribute.opacity >= ((FloatAttribute) attributes.get(j3)).value) {
            super.render(renderable, attributes);
        }
        if (!zHas) {
            attributes.remove(j3);
        }
        attributes.set(blendingAttribute);
    }

    public DepthShader(Renderable renderable, Config config) {
        this(renderable, config, createPrefix(renderable, config));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DepthShader(Renderable renderable, Config config, String str) {
        String str2 = config.vertexShader;
        String defaultVertexShader2 = str2 == null ? getDefaultVertexShader() : str2;
        String str3 = config.fragmentShader;
        this(renderable, config, str, defaultVertexShader2, str3 == null ? getDefaultFragmentShader() : str3);
    }

    public static class Config extends DefaultShader.Config {
        public float defaultAlphaTest;
        public boolean depthBufferOnly;

        public Config() {
            this.depthBufferOnly = false;
            this.defaultAlphaTest = 0.5f;
            this.defaultCullFace = GL20.GL_FRONT;
        }

        public Config(String str, String str2) {
            super(str, str2);
            this.depthBufferOnly = false;
            this.defaultAlphaTest = 0.5f;
        }
    }

    public DepthShader(Renderable renderable, Config config, String str, String str2, String str3) {
        this(renderable, config, new ShaderProgram(a.k(str, str2), a.k(str, str3)));
    }

    public DepthShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        super(renderable, config, shaderProgram);
        combineAttributes(renderable);
        this.numBones = renderable.bones == null ? 0 : config.numBones;
        int size = renderable.meshPart.mesh.getVertexAttributes().size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            VertexAttribute vertexAttribute = renderable.meshPart.mesh.getVertexAttributes().get(i3);
            if (vertexAttribute.usage == 64) {
                i2 |= 1 << vertexAttribute.unit;
            }
        }
        this.weights = i2;
        this.alphaTestAttribute = new FloatAttribute(FloatAttribute.AlphaTest, config.defaultAlphaTest);
    }
}

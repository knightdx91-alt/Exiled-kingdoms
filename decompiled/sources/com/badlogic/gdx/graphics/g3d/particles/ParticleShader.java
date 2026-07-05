package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleShader extends BaseShader {
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    protected final Config config;
    Material currentMaterial;
    private long materialMask;
    private Renderable renderable;
    private long vertexMask;
    protected static long implementedFlags = BlendingAttribute.Type | TextureAttribute.Diffuse;
    static final a TMP_VECTOR3 = new a();
    private static final long optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;

    public enum AlignMode {
        Screen,
        ViewPoint
    }

    public static class Inputs {
        public static final BaseShader.Uniform cameraRight = new BaseShader.Uniform("u_cameraRight");
        public static final BaseShader.Uniform cameraInvDirection = new BaseShader.Uniform("u_cameraInvDirection");
        public static final BaseShader.Uniform screenWidth = new BaseShader.Uniform("u_screenWidth");
        public static final BaseShader.Uniform regionSize = new BaseShader.Uniform("u_regionSize");
    }

    public enum ParticleType {
        Billboard,
        Point
    }

    public static class Setters {
        public static final BaseShader.Setter cameraRight = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.1
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                a aVar = ParticleShader.TMP_VECTOR3;
                aVar.u(baseShader.camera.direction);
                aVar.d(baseShader.camera.up);
                aVar.n();
                baseShader.set(i2, aVar);
            }
        };
        public static final BaseShader.Setter cameraUp = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.2
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                a aVar = ParticleShader.TMP_VECTOR3;
                aVar.u(baseShader.camera.up);
                aVar.n();
                baseShader.set(i2, aVar);
            }
        };
        public static final BaseShader.Setter cameraInvDirection = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.3
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                a aVar = ParticleShader.TMP_VECTOR3;
                a aVar2 = baseShader.camera.direction;
                aVar.t(-aVar2.f1729a, -aVar2.f1730b, -aVar2.f1731c);
                aVar.n();
                baseShader.set(i2, aVar);
            }
        };
        public static final BaseShader.Setter cameraPosition = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.4
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.position);
            }
        };
        public static final BaseShader.Setter screenWidth = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.5
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, Gdx.graphics.getWidth());
            }
        };
        public static final BaseShader.Setter worldViewTrans = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.6
            final Matrix4 temp = new Matrix4();

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i2) {
                return false;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Matrix4 matrix4 = this.temp;
                matrix4.o(baseShader.camera.view);
                matrix4.g(renderable.worldTransform);
                baseShader.set(i2, matrix4);
            }
        };
    }

    public ParticleShader(Renderable renderable) {
        this(renderable, new Config());
    }

    public static String createPrefix(Renderable renderable, Config config) {
        String str = Gdx.app.getType() == Application.a.f1564b ? "#version 120\n" : "#version 100\n";
        if (config.type != ParticleType.Billboard) {
            return str;
        }
        String strK = a.a.k(str, "#define billboard\n");
        AlignMode alignMode = config.align;
        return alignMode == AlignMode.Screen ? a.a.k(strK, "#define screenFacing\n") : alignMode == AlignMode.ViewPoint ? a.a.k(strK, "#define viewPointFacing\n") : strK;
    }

    public static String getDefaultFragmentShader() {
        if (defaultFragmentShader == null) {
            defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.fragment.glsl").readString();
        }
        return defaultFragmentShader;
    }

    public static String getDefaultVertexShader() {
        if (defaultVertexShader == null) {
            defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.vertex.glsl").readString();
        }
        return defaultVertexShader;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        super.begin(camera, renderContext);
    }

    protected void bindMaterial(Renderable renderable) {
        Material material = this.currentMaterial;
        Material material2 = renderable.material;
        if (material == material2) {
            return;
        }
        Config config = this.config;
        int i2 = config.defaultCullFace;
        if (i2 == -1) {
            i2 = GL20.GL_BACK;
        }
        int i3 = config.defaultDepthFunc;
        if (i3 == -1) {
            i3 = GL20.GL_LEQUAL;
        }
        this.currentMaterial = material2;
        float f2 = 0.0f;
        float f3 = 1.0f;
        boolean z2 = true;
        for (Attribute attribute : material2) {
            long j2 = attribute.type;
            if (BlendingAttribute.is(j2)) {
                BlendingAttribute blendingAttribute = (BlendingAttribute) attribute;
                this.context.setBlending(true, blendingAttribute.sourceFunction, blendingAttribute.destFunction);
            } else {
                long j3 = DepthTestAttribute.Type;
                if ((j2 & j3) == j3) {
                    DepthTestAttribute depthTestAttribute = (DepthTestAttribute) attribute;
                    i3 = depthTestAttribute.depthFunc;
                    f2 = depthTestAttribute.depthRangeNear;
                    f3 = depthTestAttribute.depthRangeFar;
                    z2 = depthTestAttribute.depthMask;
                } else if (!this.config.ignoreUnimplemented) {
                    throw new m("Unknown material attribute: " + attribute.toString());
                }
            }
        }
        this.context.setCullFace(i2);
        this.context.setDepthTest(i3, f2, f3);
        this.context.setDepthMask(z2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public boolean canRender(Renderable renderable) {
        return this.materialMask == (renderable.material.getMask() | optionalAttributes) && this.vertexMask == renderable.meshPart.mesh.getVertexAttributes().getMask();
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public int compareTo(Shader shader) {
        return shader == null ? -1 : 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader, com.badlogic.gdx.utils.i
    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        this.currentMaterial = null;
        super.end();
    }

    public boolean equals(ParticleShader particleShader) {
        return particleShader == this;
    }

    public int getDefaultCullFace() {
        int i2 = this.config.defaultCullFace;
        return i2 == -1 ? GL20.GL_BACK : i2;
    }

    public int getDefaultDepthFunc() {
        int i2 = this.config.defaultDepthFunc;
        return i2 == -1 ? GL20.GL_LEQUAL : i2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void init() {
        ShaderProgram shaderProgram = this.program;
        this.program = null;
        init(shaderProgram, this.renderable);
        this.renderable = null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void render(Renderable renderable) {
        if (!renderable.material.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        bindMaterial(renderable);
        super.render(renderable);
    }

    public void setDefaultCullFace(int i2) {
        this.config.defaultCullFace = i2;
    }

    public void setDefaultDepthFunc(int i2) {
        this.config.defaultDepthFunc = i2;
    }

    public ParticleShader(Renderable renderable, Config config) {
        this(renderable, config, createPrefix(renderable, config));
    }

    public boolean equals(Object obj) {
        return (obj instanceof ParticleShader) && equals((ParticleShader) obj);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ParticleShader(Renderable renderable, Config config, String str) {
        String str2 = config.vertexShader;
        String defaultVertexShader2 = str2 == null ? getDefaultVertexShader() : str2;
        String str3 = config.fragmentShader;
        this(renderable, config, str, defaultVertexShader2, str3 == null ? getDefaultFragmentShader() : str3);
    }

    public ParticleShader(Renderable renderable, Config config, String str, String str2, String str3) {
        this(renderable, config, new ShaderProgram(a.a.k(str, str2), a.a.k(str, str3)));
    }

    public static class Config {
        public AlignMode align;
        public int defaultCullFace;
        public int defaultDepthFunc;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public ParticleType type;
        public String vertexShader;

        public Config() {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
        }

        public Config(AlignMode alignMode, ParticleType particleType) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            ParticleType particleType2 = ParticleType.Billboard;
            this.align = alignMode;
            this.type = particleType;
        }

        public Config(AlignMode alignMode) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.align = alignMode;
        }

        public Config(ParticleType particleType) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            ParticleType particleType2 = ParticleType.Billboard;
            this.type = particleType;
        }

        public Config(String str, String str2) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.vertexShader = str;
            this.fragmentShader = str2;
        }
    }

    public ParticleShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        this.config = config;
        this.program = shaderProgram;
        this.renderable = renderable;
        this.materialMask = renderable.material.getMask() | optionalAttributes;
        this.vertexMask = renderable.meshPart.mesh.getVertexAttributes().getMask();
        if (!config.ignoreUnimplemented) {
            long j2 = implementedFlags;
            long j3 = this.materialMask;
            if ((j2 & j3) != j3) {
                throw new m("Some attributes not implemented yet (" + this.materialMask + ")");
            }
        }
        register(DefaultShader.Inputs.viewTrans, DefaultShader.Setters.viewTrans);
        register(DefaultShader.Inputs.projViewTrans, DefaultShader.Setters.projViewTrans);
        register(DefaultShader.Inputs.projTrans, DefaultShader.Setters.projTrans);
        register(Inputs.screenWidth, Setters.screenWidth);
        register(DefaultShader.Inputs.cameraUp, Setters.cameraUp);
        register(Inputs.cameraRight, Setters.cameraRight);
        register(Inputs.cameraInvDirection, Setters.cameraInvDirection);
        register(DefaultShader.Inputs.cameraPosition, Setters.cameraPosition);
        register(DefaultShader.Inputs.diffuseTexture, DefaultShader.Setters.diffuseTexture);
    }
}

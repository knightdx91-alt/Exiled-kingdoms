package com.badlogic.gdx.graphics.g3d.shaders;

import a0.k;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.CubemapAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.SpotLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.ShadowMap;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DefaultShader extends BaseShader {
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    protected final AmbientCubemap ambientCubemap;
    protected final long attributesMask;
    protected final Config config;
    protected int dirLightsColorOffset;
    protected int dirLightsDirectionOffset;
    protected int dirLightsLoc;
    protected int dirLightsSize;
    protected final DirectionalLight[] directionalLights;
    protected final boolean environmentCubemap;
    protected final boolean lighting;
    private boolean lightsSet;
    private final k normalMatrix;
    protected final PointLight[] pointLights;
    protected int pointLightsColorOffset;
    protected int pointLightsIntensityOffset;
    protected int pointLightsLoc;
    protected int pointLightsPositionOffset;
    protected int pointLightsSize;
    private Renderable renderable;
    protected final boolean shadowMap;
    protected final SpotLight[] spotLights;
    protected int spotLightsColorOffset;
    protected int spotLightsCutoffAngleOffset;
    protected int spotLightsDirectionOffset;
    protected int spotLightsExponentOffset;
    protected int spotLightsIntensityOffset;
    protected int spotLightsLoc;
    protected int spotLightsPositionOffset;
    protected int spotLightsSize;
    private float time;
    private final a tmpV1;
    public final int u_alphaTest;
    protected final int u_ambientCubemap;
    public final int u_ambientTexture;
    public final int u_ambientUVTransform;
    public final int u_bones;
    public final int u_cameraDirection;
    public final int u_cameraNearFar;
    public final int u_cameraPosition;
    public final int u_cameraUp;
    public final int u_diffuseColor;
    public final int u_diffuseTexture;
    public final int u_diffuseUVTransform;
    protected final int u_dirLights0color;
    protected final int u_dirLights0direction;
    protected final int u_dirLights1color;
    public final int u_emissiveColor;
    public final int u_emissiveTexture;
    public final int u_emissiveUVTransform;
    protected final int u_environmentCubemap;
    protected final int u_fogColor;
    public final int u_normalMatrix;
    public final int u_normalTexture;
    public final int u_normalUVTransform;
    public final int u_opacity;
    protected final int u_pointLights0color;
    protected final int u_pointLights0intensity;
    protected final int u_pointLights0position;
    protected final int u_pointLights1color;
    public final int u_projTrans;
    public final int u_projViewTrans;
    public final int u_projViewWorldTrans;
    public final int u_reflectionColor;
    public final int u_reflectionTexture;
    public final int u_reflectionUVTransform;
    protected final int u_shadowMapProjViewTrans;
    protected final int u_shadowPCFOffset;
    protected final int u_shadowTexture;
    public final int u_shininess;
    public final int u_specularColor;
    public final int u_specularTexture;
    public final int u_specularUVTransform;
    protected final int u_spotLights0color;
    protected final int u_spotLights0cutoffAngle;
    protected final int u_spotLights0direction;
    protected final int u_spotLights0exponent;
    protected final int u_spotLights0intensity;
    protected final int u_spotLights0position;
    protected final int u_spotLights1color;
    public final int u_time;
    public final int u_viewTrans;
    public final int u_viewWorldTrans;
    public final int u_worldTrans;
    private final long vertexMask;
    protected static long implementedFlags = (((BlendingAttribute.Type | TextureAttribute.Diffuse) | ColorAttribute.Diffuse) | ColorAttribute.Specular) | FloatAttribute.Shininess;

    @Deprecated
    public static int defaultCullFace = GL20.GL_BACK;

    @Deprecated
    public static int defaultDepthFunc = GL20.GL_LEQUAL;
    private static final long optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;
    private static final Attributes tmpAttributes = new Attributes();

    public static class Inputs {
        public static final BaseShader.Uniform alphaTest;
        public static final BaseShader.Uniform ambientCube;
        public static final BaseShader.Uniform ambientTexture;
        public static final BaseShader.Uniform ambientUVTransform;
        public static final BaseShader.Uniform diffuseTexture;
        public static final BaseShader.Uniform diffuseUVTransform;
        public static final BaseShader.Uniform dirLights;
        public static final BaseShader.Uniform emissiveColor;
        public static final BaseShader.Uniform emissiveTexture;
        public static final BaseShader.Uniform emissiveUVTransform;
        public static final BaseShader.Uniform environmentCubemap;
        public static final BaseShader.Uniform normalTexture;
        public static final BaseShader.Uniform normalUVTransform;
        public static final BaseShader.Uniform pointLights;
        public static final BaseShader.Uniform reflectionColor;
        public static final BaseShader.Uniform reflectionTexture;
        public static final BaseShader.Uniform reflectionUVTransform;
        public static final BaseShader.Uniform specularColor;
        public static final BaseShader.Uniform specularTexture;
        public static final BaseShader.Uniform specularUVTransform;
        public static final BaseShader.Uniform spotLights;
        public static final BaseShader.Uniform projTrans = new BaseShader.Uniform("u_projTrans");
        public static final BaseShader.Uniform viewTrans = new BaseShader.Uniform("u_viewTrans");
        public static final BaseShader.Uniform projViewTrans = new BaseShader.Uniform("u_projViewTrans");
        public static final BaseShader.Uniform cameraPosition = new BaseShader.Uniform("u_cameraPosition");
        public static final BaseShader.Uniform cameraDirection = new BaseShader.Uniform("u_cameraDirection");
        public static final BaseShader.Uniform cameraUp = new BaseShader.Uniform("u_cameraUp");
        public static final BaseShader.Uniform cameraNearFar = new BaseShader.Uniform("u_cameraNearFar");
        public static final BaseShader.Uniform worldTrans = new BaseShader.Uniform("u_worldTrans");
        public static final BaseShader.Uniform viewWorldTrans = new BaseShader.Uniform("u_viewWorldTrans");
        public static final BaseShader.Uniform projViewWorldTrans = new BaseShader.Uniform("u_projViewWorldTrans");
        public static final BaseShader.Uniform normalMatrix = new BaseShader.Uniform("u_normalMatrix");
        public static final BaseShader.Uniform bones = new BaseShader.Uniform("u_bones");
        public static final BaseShader.Uniform shininess = new BaseShader.Uniform("u_shininess", FloatAttribute.Shininess);
        public static final BaseShader.Uniform opacity = new BaseShader.Uniform("u_opacity", BlendingAttribute.Type);
        public static final BaseShader.Uniform diffuseColor = new BaseShader.Uniform("u_diffuseColor", ColorAttribute.Diffuse);

        static {
            long j2 = TextureAttribute.Diffuse;
            diffuseTexture = new BaseShader.Uniform("u_diffuseTexture", j2);
            diffuseUVTransform = new BaseShader.Uniform("u_diffuseUVTransform", j2);
            specularColor = new BaseShader.Uniform("u_specularColor", ColorAttribute.Specular);
            long j3 = TextureAttribute.Specular;
            specularTexture = new BaseShader.Uniform("u_specularTexture", j3);
            specularUVTransform = new BaseShader.Uniform("u_specularUVTransform", j3);
            emissiveColor = new BaseShader.Uniform("u_emissiveColor", ColorAttribute.Emissive);
            long j4 = TextureAttribute.Emissive;
            emissiveTexture = new BaseShader.Uniform("u_emissiveTexture", j4);
            emissiveUVTransform = new BaseShader.Uniform("u_emissiveUVTransform", j4);
            reflectionColor = new BaseShader.Uniform("u_reflectionColor", ColorAttribute.Reflection);
            long j5 = TextureAttribute.Reflection;
            reflectionTexture = new BaseShader.Uniform("u_reflectionTexture", j5);
            reflectionUVTransform = new BaseShader.Uniform("u_reflectionUVTransform", j5);
            long j6 = TextureAttribute.Normal;
            normalTexture = new BaseShader.Uniform("u_normalTexture", j6);
            normalUVTransform = new BaseShader.Uniform("u_normalUVTransform", j6);
            long j7 = TextureAttribute.Ambient;
            ambientTexture = new BaseShader.Uniform("u_ambientTexture", j7);
            ambientUVTransform = new BaseShader.Uniform("u_ambientUVTransform", j7);
            alphaTest = new BaseShader.Uniform("u_alphaTest");
            ambientCube = new BaseShader.Uniform("u_ambientCubemap");
            dirLights = new BaseShader.Uniform("u_dirLights");
            pointLights = new BaseShader.Uniform("u_pointLights");
            spotLights = new BaseShader.Uniform("u_spotLights");
            environmentCubemap = new BaseShader.Uniform("u_environmentCubemap");
        }
    }

    public static class Setters {
        public static final BaseShader.Setter projTrans = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.1
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.projection);
            }
        };
        public static final BaseShader.Setter viewTrans = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.2
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.view);
            }
        };
        public static final BaseShader.Setter projViewTrans = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.3
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.combined);
            }
        };
        public static final BaseShader.Setter cameraPosition = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.4
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Camera camera = baseShader.camera;
                a aVar = camera.position;
                float f2 = aVar.f1729a;
                float f3 = aVar.f1730b;
                float f4 = aVar.f1731c;
                float f5 = camera.far;
                baseShader.set(i2, f2, f3, f4, 1.1881f / (f5 * f5));
            }
        };
        public static final BaseShader.Setter cameraDirection = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.5
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.direction);
            }
        };
        public static final BaseShader.Setter cameraUp = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.6
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.camera.up);
            }
        };
        public static final BaseShader.Setter cameraNearFar = new BaseShader.GlobalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.7
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Camera camera = baseShader.camera;
                baseShader.set(i2, camera.near, camera.far);
            }
        };
        public static final BaseShader.Setter worldTrans = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.8
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, renderable.worldTransform);
            }
        };
        public static final BaseShader.Setter viewWorldTrans = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.9
            final Matrix4 temp = new Matrix4();

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Matrix4 matrix4 = this.temp;
                matrix4.o(baseShader.camera.view);
                matrix4.g(renderable.worldTransform);
                baseShader.set(i2, matrix4);
            }
        };
        public static final BaseShader.Setter projViewWorldTrans = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.10
            final Matrix4 temp = new Matrix4();

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Matrix4 matrix4 = this.temp;
                matrix4.o(baseShader.camera.combined);
                matrix4.g(renderable.worldTransform);
                baseShader.set(i2, matrix4);
            }
        };
        public static final BaseShader.Setter normalMatrix = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.11
            private final k tmpM = new k();

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                k kVar = this.tmpM;
                kVar.c(renderable.worldTransform);
                kVar.b();
                kVar.e();
                baseShader.set(i2, kVar);
            }
        };
        public static final BaseShader.Setter shininess = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.12
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, ((FloatAttribute) attributes.get(FloatAttribute.Shininess)).value);
            }
        };
        public static final BaseShader.Setter diffuseColor = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.13
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, ((ColorAttribute) attributes.get(ColorAttribute.Diffuse)).color);
            }
        };
        public static final BaseShader.Setter diffuseTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.14
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Diffuse)).textureDescription));
            }
        };
        public static final BaseShader.Setter diffuseUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.15
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Diffuse);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter specularColor = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.16
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, ((ColorAttribute) attributes.get(ColorAttribute.Specular)).color);
            }
        };
        public static final BaseShader.Setter specularTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.17
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Specular)).textureDescription));
            }
        };
        public static final BaseShader.Setter specularUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.18
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Specular);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter emissiveColor = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.19
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, ((ColorAttribute) attributes.get(ColorAttribute.Emissive)).color);
            }
        };
        public static final BaseShader.Setter emissiveTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.20
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Emissive)).textureDescription));
            }
        };
        public static final BaseShader.Setter emissiveUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.21
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Emissive);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter reflectionColor = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.22
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, ((ColorAttribute) attributes.get(ColorAttribute.Reflection)).color);
            }
        };
        public static final BaseShader.Setter reflectionTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.23
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Reflection)).textureDescription));
            }
        };
        public static final BaseShader.Setter reflectionUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.24
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Reflection);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter normalTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.25
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Normal)).textureDescription));
            }
        };
        public static final BaseShader.Setter normalUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.26
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Normal);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter ambientTexture = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.27
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                baseShader.set(i2, baseShader.context.textureBinder.bind(((TextureAttribute) attributes.get(TextureAttribute.Ambient)).textureDescription));
            }
        };
        public static final BaseShader.Setter ambientUVTransform = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.28
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                TextureAttribute textureAttribute = (TextureAttribute) attributes.get(TextureAttribute.Ambient);
                baseShader.set(i2, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV);
            }
        };
        public static final BaseShader.Setter environmentCubemap = new BaseShader.LocalSetter() { // from class: com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.29
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                long j2 = CubemapAttribute.EnvironmentMap;
                if (attributes.has(j2)) {
                    baseShader.set(i2, baseShader.context.textureBinder.bind(((CubemapAttribute) attributes.get(j2)).textureDescription));
                }
            }
        };

        public static class ACubemap extends BaseShader.LocalSetter {
            private static final float[] ones = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
            private static final a tmpV1 = new a();
            private final AmbientCubemap cacheAmbientCubemap = new AmbientCubemap();
            public final int dirLightsOffset;
            public final int pointLightsOffset;

            public ACubemap(int i2, int i3) {
                this.dirLightsOffset = i2;
                this.pointLightsOffset = i3;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                if (renderable.environment == null) {
                    ShaderProgram shaderProgram = baseShader.program;
                    int iLoc = baseShader.loc(i2);
                    float[] fArr = ones;
                    shaderProgram.setUniform3fv(iLoc, fArr, 0, fArr.length);
                    return;
                }
                renderable.worldTransform.c(tmpV1);
                long j2 = ColorAttribute.AmbientLight;
                if (attributes.has(j2)) {
                    this.cacheAmbientCubemap.set(((ColorAttribute) attributes.get(j2)).color);
                }
                long j3 = DirectionalLightsAttribute.Type;
                if (attributes.has(j3)) {
                    com.badlogic.gdx.utils.a<DirectionalLight> aVar = ((DirectionalLightsAttribute) attributes.get(j3)).lights;
                    for (int i3 = this.dirLightsOffset; i3 < aVar.f1750b; i3++) {
                        this.cacheAmbientCubemap.add(aVar.get(i3).color, aVar.get(i3).direction);
                    }
                }
                long j4 = PointLightsAttribute.Type;
                if (attributes.has(j4)) {
                    com.badlogic.gdx.utils.a<PointLight> aVar2 = ((PointLightsAttribute) attributes.get(j4)).lights;
                    for (int i4 = this.pointLightsOffset; i4 < aVar2.f1750b; i4++) {
                        this.cacheAmbientCubemap.add(aVar2.get(i4).color, aVar2.get(i4).position, tmpV1, aVar2.get(i4).intensity);
                    }
                }
                this.cacheAmbientCubemap.clamp();
                ShaderProgram shaderProgram2 = baseShader.program;
                int iLoc2 = baseShader.loc(i2);
                float[] fArr2 = this.cacheAmbientCubemap.data;
                shaderProgram2.setUniform3fv(iLoc2, fArr2, 0, fArr2.length);
            }
        }

        public static class Bones extends BaseShader.LocalSetter {
            private static final Matrix4 idtMatrix = new Matrix4();
            public final float[] bones;

            public Bones(int i2) {
                this.bones = new float[i2 * 16];
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes) {
                Matrix4 matrix4;
                int i3 = 0;
                while (true) {
                    float[] fArr = this.bones;
                    if (i3 >= fArr.length) {
                        ShaderProgram shaderProgram = baseShader.program;
                        int iLoc = baseShader.loc(i2);
                        float[] fArr2 = this.bones;
                        shaderProgram.setUniformMatrix4fv(iLoc, fArr2, 0, fArr2.length);
                        return;
                    }
                    int i4 = i3 / 16;
                    Matrix4[] matrix4Arr = renderable.bones;
                    if (matrix4Arr == null || i4 >= matrix4Arr.length || (matrix4 = matrix4Arr[i4]) == null) {
                        System.arraycopy(idtMatrix.f1724a, 0, fArr, i3, 16);
                    } else {
                        System.arraycopy(matrix4.f1724a, 0, fArr, i3, 16);
                    }
                    i3 += 16;
                }
            }
        }
    }

    public DefaultShader(Renderable renderable) {
        this(renderable, new Config());
    }

    private static final boolean and(long j2, long j3) {
        return (j2 & j3) == j3;
    }

    private static final long combineAttributeMasks(Renderable renderable) {
        Environment environment = renderable.environment;
        long mask = environment != null ? environment.getMask() : 0L;
        Material material = renderable.material;
        return material != null ? mask | material.getMask() : mask;
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
        String strP;
        Attributes attributesCombineAttributes = combineAttributes(renderable);
        long mask = attributesCombineAttributes.getMask();
        long mask2 = renderable.meshPart.mesh.getVertexAttributes().getMask();
        String strK = and(mask2, 1L) ? "#define positionFlag\n" : "";
        if (or(mask2, 6L)) {
            strK = a.a.k(strK, "#define colorFlag\n");
        }
        if (and(mask2, 256L)) {
            strK = a.a.k(strK, "#define binormalFlag\n");
        }
        if (and(mask2, 128L)) {
            strK = a.a.k(strK, "#define tangentFlag\n");
        }
        if (and(mask2, 8L)) {
            strK = a.a.k(strK, "#define normalFlag\n");
        }
        if ((and(mask2, 8L) || and(mask2, 384L)) && renderable.environment != null) {
            String strP2 = a.a.p(a.a.t(a.a.p(a.a.t(a.a.p(a.a.t(a.a.k(a.a.k(strK, "#define lightingFlag\n"), "#define ambientCubemapFlag\n"), "#define numDirectionalLights "), config.numDirectionalLights, "\n"), "#define numPointLights "), config.numPointLights, "\n"), "#define numSpotLights "), config.numSpotLights, "\n");
            if (attributesCombineAttributes.has(ColorAttribute.Fog)) {
                strP2 = a.a.k(strP2, "#define fogFlag\n");
            }
            if (renderable.environment.shadowMap != null) {
                strP2 = a.a.k(strP2, "#define shadowMapFlag\n");
            }
            strK = strP2;
            if (attributesCombineAttributes.has(CubemapAttribute.EnvironmentMap)) {
                strK = a.a.k(strK, "#define environmentCubemapFlag\n");
            }
        }
        int size = renderable.meshPart.mesh.getVertexAttributes().size();
        for (int i2 = 0; i2 < size; i2++) {
            VertexAttribute vertexAttribute = renderable.meshPart.mesh.getVertexAttributes().get(i2);
            int i3 = vertexAttribute.usage;
            if (i3 == 64) {
                strP = a.a.p(a.a.t(strK, "#define boneWeight"), vertexAttribute.unit, "Flag\n");
            } else if (i3 == 16) {
                strP = a.a.p(a.a.t(strK, "#define texCoord"), vertexAttribute.unit, "Flag\n");
            }
            strK = strP;
        }
        long j2 = BlendingAttribute.Type;
        if ((mask & j2) == j2) {
            strK = a.a.k(strK, "#define blendedFlag\n");
        }
        long j3 = TextureAttribute.Diffuse;
        if ((mask & j3) == j3) {
            strK = a.a.k(a.a.k(strK, "#define diffuseTextureFlag\n"), "#define diffuseTextureCoord texCoord0\n");
        }
        long j4 = TextureAttribute.Specular;
        if ((mask & j4) == j4) {
            strK = a.a.k(a.a.k(strK, "#define specularTextureFlag\n"), "#define specularTextureCoord texCoord0\n");
        }
        long j5 = TextureAttribute.Normal;
        if ((mask & j5) == j5) {
            strK = a.a.k(a.a.k(strK, "#define normalTextureFlag\n"), "#define normalTextureCoord texCoord0\n");
        }
        long j6 = TextureAttribute.Emissive;
        if ((mask & j6) == j6) {
            strK = a.a.k(a.a.k(strK, "#define emissiveTextureFlag\n"), "#define emissiveTextureCoord texCoord0\n");
        }
        long j7 = TextureAttribute.Reflection;
        if ((mask & j7) == j7) {
            strK = a.a.k(a.a.k(strK, "#define reflectionTextureFlag\n"), "#define reflectionTextureCoord texCoord0\n");
        }
        long j8 = TextureAttribute.Ambient;
        if ((mask & j8) == j8) {
            strK = a.a.k(a.a.k(strK, "#define ambientTextureFlag\n"), "#define ambientTextureCoord texCoord0\n");
        }
        long j9 = ColorAttribute.Diffuse;
        if ((mask & j9) == j9) {
            strK = a.a.k(strK, "#define diffuseColorFlag\n");
        }
        long j10 = ColorAttribute.Specular;
        if ((mask & j10) == j10) {
            strK = a.a.k(strK, "#define specularColorFlag\n");
        }
        long j11 = ColorAttribute.Emissive;
        if ((mask & j11) == j11) {
            strK = a.a.k(strK, "#define emissiveColorFlag\n");
        }
        long j12 = ColorAttribute.Reflection;
        if ((mask & j12) == j12) {
            strK = a.a.k(strK, "#define reflectionColorFlag\n");
        }
        long j13 = FloatAttribute.Shininess;
        if ((mask & j13) == j13) {
            strK = a.a.k(strK, "#define shininessFlag\n");
        }
        long j14 = FloatAttribute.AlphaTest;
        if ((mask & j14) == j14) {
            strK = a.a.k(strK, "#define alphaTestFlag\n");
        }
        return (renderable.bones == null || config.numBones <= 0) ? strK : a.a.p(a.a.t(strK, "#define numBones "), config.numBones, "\n");
    }

    public static String getDefaultFragmentShader() {
        if (defaultFragmentShader == null) {
            defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.fragment.glsl").readString();
        }
        return defaultFragmentShader;
    }

    public static String getDefaultVertexShader() {
        if (defaultVertexShader == null) {
            defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.vertex.glsl").readString();
        }
        return defaultVertexShader;
    }

    private static final boolean or(long j2, long j3) {
        return (j2 & j3) != 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        super.begin(camera, renderContext);
        for (DirectionalLight directionalLight : this.directionalLights) {
            directionalLight.set(0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f);
        }
        for (PointLight pointLight : this.pointLights) {
            pointLight.set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
        for (SpotLight spotLight : this.spotLights) {
            spotLight.set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        }
        this.lightsSet = false;
        if (has(this.u_time)) {
            int i2 = this.u_time;
            float deltaTime = Gdx.graphics.getDeltaTime() + this.time;
            this.time = deltaTime;
            set(i2, deltaTime);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x00c1 A[EDGE_INSN: B:107:0x00c1->B:43:0x00c1 BREAK  A[LOOP:0: B:17:0x003b->B:42:0x00bd], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150 A[EDGE_INSN: B:112:0x0150->B:71:0x0150 BREAK  A[LOOP:1: B:46:0x00c6->B:70:0x014c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x014c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0203 A[EDGE_INSN: B:118:0x0203->B:98:0x0203 BREAK  A[LOOP:2: B:73:0x0154->B:97:0x01ff], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void bindLights(Renderable renderable, Attributes attributes) {
        ShadowMap shadowMap;
        int i2;
        int i3;
        Environment environment = renderable.environment;
        DirectionalLightsAttribute directionalLightsAttribute = (DirectionalLightsAttribute) attributes.get(DirectionalLightsAttribute.class, DirectionalLightsAttribute.Type);
        com.badlogic.gdx.utils.a<DirectionalLight> aVar = directionalLightsAttribute == null ? null : directionalLightsAttribute.lights;
        PointLightsAttribute pointLightsAttribute = (PointLightsAttribute) attributes.get(PointLightsAttribute.class, PointLightsAttribute.Type);
        com.badlogic.gdx.utils.a<PointLight> aVar2 = pointLightsAttribute == null ? null : pointLightsAttribute.lights;
        SpotLightsAttribute spotLightsAttribute = (SpotLightsAttribute) attributes.get(SpotLightsAttribute.class, SpotLightsAttribute.Type);
        com.badlogic.gdx.utils.a<SpotLight> aVar3 = spotLightsAttribute != null ? spotLightsAttribute.lights : null;
        int i4 = 0;
        if (this.dirLightsLoc >= 0) {
            int i5 = 0;
            while (true) {
                DirectionalLight[] directionalLightArr = this.directionalLights;
                if (i5 >= directionalLightArr.length) {
                    break;
                }
                if (aVar == null || i5 >= aVar.f1750b) {
                    if (this.lightsSet) {
                        Color color = directionalLightArr[i5].color;
                        if (color.f1680r == 0.0f && color.f1679g == 0.0f && color.f1678b == 0.0f) {
                            i5++;
                        }
                    }
                    directionalLightArr[i5].color.set(0.0f, 0.0f, 0.0f, 1.0f);
                    int i6 = (this.dirLightsSize * i5) + this.dirLightsLoc;
                    ShaderProgram shaderProgram = this.program;
                    int i7 = this.dirLightsColorOffset + i6;
                    Color color2 = this.directionalLights[i5].color;
                    shaderProgram.setUniformf(i7, color2.f1680r, color2.f1679g, color2.f1678b);
                    ShaderProgram shaderProgram2 = this.program;
                    int i8 = i6 + this.dirLightsDirectionOffset;
                    a aVar4 = this.directionalLights[i5].direction;
                    shaderProgram2.setUniformf(i8, aVar4.f1729a, aVar4.f1730b, aVar4.f1731c);
                    if (this.dirLightsSize > 0) {
                        break;
                    } else {
                        i5++;
                    }
                } else if (this.lightsSet && directionalLightArr[i5].equals(aVar.get(i5))) {
                    i5++;
                } else {
                    this.directionalLights[i5].set(aVar.get(i5));
                    int i62 = (this.dirLightsSize * i5) + this.dirLightsLoc;
                    ShaderProgram shaderProgram3 = this.program;
                    int i72 = this.dirLightsColorOffset + i62;
                    Color color22 = this.directionalLights[i5].color;
                    shaderProgram3.setUniformf(i72, color22.f1680r, color22.f1679g, color22.f1678b);
                    ShaderProgram shaderProgram22 = this.program;
                    int i82 = i62 + this.dirLightsDirectionOffset;
                    a aVar42 = this.directionalLights[i5].direction;
                    shaderProgram22.setUniformf(i82, aVar42.f1729a, aVar42.f1730b, aVar42.f1731c);
                    if (this.dirLightsSize > 0) {
                    }
                }
            }
        }
        if (this.pointLightsLoc >= 0) {
            int i9 = 0;
            while (true) {
                PointLight[] pointLightArr = this.pointLights;
                if (i9 >= pointLightArr.length) {
                    break;
                }
                if (aVar2 == null || i9 >= aVar2.f1750b) {
                    if (this.lightsSet && pointLightArr[i9].intensity == 0.0f) {
                        i9++;
                    } else {
                        pointLightArr[i9].intensity = 0.0f;
                        int i10 = (this.pointLightsSize * i9) + this.pointLightsLoc;
                        ShaderProgram shaderProgram4 = this.program;
                        int i11 = this.pointLightsColorOffset + i10;
                        PointLight pointLight = this.pointLights[i9];
                        Color color3 = pointLight.color;
                        float f2 = color3.f1680r;
                        float f3 = pointLight.intensity;
                        shaderProgram4.setUniformf(i11, f2 * f3, color3.f1679g * f3, color3.f1678b * f3);
                        ShaderProgram shaderProgram5 = this.program;
                        int i12 = this.pointLightsPositionOffset + i10;
                        a aVar5 = this.pointLights[i9].position;
                        shaderProgram5.setUniformf(i12, aVar5.f1729a, aVar5.f1730b, aVar5.f1731c);
                        i3 = this.pointLightsIntensityOffset;
                        if (i3 >= 0) {
                            this.program.setUniformf(i10 + i3, this.pointLights[i9].intensity);
                        }
                        if (this.pointLightsSize > 0) {
                            break;
                        } else {
                            i9++;
                        }
                    }
                } else if (this.lightsSet && pointLightArr[i9].equals(aVar2.get(i9))) {
                    i9++;
                } else {
                    this.pointLights[i9].set(aVar2.get(i9));
                    int i102 = (this.pointLightsSize * i9) + this.pointLightsLoc;
                    ShaderProgram shaderProgram42 = this.program;
                    int i112 = this.pointLightsColorOffset + i102;
                    PointLight pointLight2 = this.pointLights[i9];
                    Color color32 = pointLight2.color;
                    float f22 = color32.f1680r;
                    float f32 = pointLight2.intensity;
                    shaderProgram42.setUniformf(i112, f22 * f32, color32.f1679g * f32, color32.f1678b * f32);
                    ShaderProgram shaderProgram52 = this.program;
                    int i122 = this.pointLightsPositionOffset + i102;
                    a aVar52 = this.pointLights[i9].position;
                    shaderProgram52.setUniformf(i122, aVar52.f1729a, aVar52.f1730b, aVar52.f1731c);
                    i3 = this.pointLightsIntensityOffset;
                    if (i3 >= 0) {
                    }
                    if (this.pointLightsSize > 0) {
                    }
                }
            }
        }
        if (this.spotLightsLoc >= 0) {
            while (true) {
                SpotLight[] spotLightArr = this.spotLights;
                if (i4 >= spotLightArr.length) {
                    break;
                }
                if (aVar3 == null || i4 >= aVar3.f1750b) {
                    if (this.lightsSet && spotLightArr[i4].intensity == 0.0f) {
                        i4++;
                    } else {
                        spotLightArr[i4].intensity = 0.0f;
                        int i13 = (this.spotLightsSize * i4) + this.spotLightsLoc;
                        ShaderProgram shaderProgram6 = this.program;
                        int i14 = this.spotLightsColorOffset + i13;
                        SpotLight spotLight = this.spotLights[i4];
                        Color color4 = spotLight.color;
                        float f4 = color4.f1680r;
                        float f5 = spotLight.intensity;
                        shaderProgram6.setUniformf(i14, f4 * f5, color4.f1679g * f5, color4.f1678b * f5);
                        this.program.setUniformf(this.spotLightsPositionOffset + i13, this.spotLights[i4].position);
                        this.program.setUniformf(this.spotLightsDirectionOffset + i13, this.spotLights[i4].direction);
                        this.program.setUniformf(this.spotLightsCutoffAngleOffset + i13, this.spotLights[i4].cutoffAngle);
                        this.program.setUniformf(this.spotLightsExponentOffset + i13, this.spotLights[i4].exponent);
                        i2 = this.spotLightsIntensityOffset;
                        if (i2 >= 0) {
                            this.program.setUniformf(i13 + i2, this.spotLights[i4].intensity);
                        }
                        if (this.spotLightsSize > 0) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                } else if (this.lightsSet && spotLightArr[i4].equals(aVar3.get(i4))) {
                    i4++;
                } else {
                    this.spotLights[i4].set(aVar3.get(i4));
                    int i132 = (this.spotLightsSize * i4) + this.spotLightsLoc;
                    ShaderProgram shaderProgram62 = this.program;
                    int i142 = this.spotLightsColorOffset + i132;
                    SpotLight spotLight2 = this.spotLights[i4];
                    Color color42 = spotLight2.color;
                    float f42 = color42.f1680r;
                    float f52 = spotLight2.intensity;
                    shaderProgram62.setUniformf(i142, f42 * f52, color42.f1679g * f52, color42.f1678b * f52);
                    this.program.setUniformf(this.spotLightsPositionOffset + i132, this.spotLights[i4].position);
                    this.program.setUniformf(this.spotLightsDirectionOffset + i132, this.spotLights[i4].direction);
                    this.program.setUniformf(this.spotLightsCutoffAngleOffset + i132, this.spotLights[i4].cutoffAngle);
                    this.program.setUniformf(this.spotLightsExponentOffset + i132, this.spotLights[i4].exponent);
                    i2 = this.spotLightsIntensityOffset;
                    if (i2 >= 0) {
                    }
                    if (this.spotLightsSize > 0) {
                    }
                }
            }
        }
        long j2 = ColorAttribute.Fog;
        if (attributes.has(j2)) {
            set(this.u_fogColor, ((ColorAttribute) attributes.get(j2)).color);
        }
        if (environment != null && (shadowMap = environment.shadowMap) != null) {
            set(this.u_shadowMapProjViewTrans, shadowMap.getProjViewTrans());
            set(this.u_shadowTexture, environment.shadowMap.getDepthMap());
            set(this.u_shadowPCFOffset, 1.0f / (environment.shadowMap.getDepthMap().texture.getWidth() * 2.0f));
        }
        this.lightsSet = true;
    }

    protected void bindMaterial(Attributes attributes) {
        Config config = this.config;
        int i2 = config.defaultCullFace;
        if (i2 == -1) {
            i2 = defaultCullFace;
        }
        int i3 = config.defaultDepthFunc;
        if (i3 == -1) {
            i3 = defaultDepthFunc;
        }
        float f2 = 0.0f;
        float f3 = 1.0f;
        boolean z2 = true;
        for (Attribute attribute : attributes) {
            long j2 = attribute.type;
            if (BlendingAttribute.is(j2)) {
                BlendingAttribute blendingAttribute = (BlendingAttribute) attribute;
                this.context.setBlending(true, blendingAttribute.sourceFunction, blendingAttribute.destFunction);
                set(this.u_opacity, blendingAttribute.opacity);
            } else {
                long j3 = IntAttribute.CullFace;
                if ((j2 & j3) == j3) {
                    i2 = ((IntAttribute) attribute).value;
                } else {
                    long j4 = FloatAttribute.AlphaTest;
                    if ((j2 & j4) == j4) {
                        set(this.u_alphaTest, ((FloatAttribute) attribute).value);
                    } else {
                        long j5 = DepthTestAttribute.Type;
                        if ((j2 & j5) == j5) {
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
            }
        }
        this.context.setCullFace(i2);
        this.context.setDepthTest(i3, f2, f3);
        this.context.setDepthMask(z2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public boolean canRender(Renderable renderable) {
        if (this.attributesMask == (combineAttributeMasks(renderable) | optionalAttributes) && this.vertexMask == renderable.meshPart.mesh.getVertexAttributes().getMaskWithSizePacked()) {
            return (renderable.environment != null) == this.lighting;
        }
        return false;
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
        super.end();
    }

    public boolean equals(DefaultShader defaultShader) {
        return defaultShader == this;
    }

    public int getDefaultCullFace() {
        int i2 = this.config.defaultCullFace;
        return i2 == -1 ? defaultCullFace : i2;
    }

    public int getDefaultDepthFunc() {
        int i2 = this.config.defaultDepthFunc;
        return i2 == -1 ? defaultDepthFunc : i2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void init() {
        ShaderProgram shaderProgram = this.program;
        this.program = null;
        init(shaderProgram, this.renderable);
        this.renderable = null;
        this.dirLightsLoc = loc(this.u_dirLights0color);
        this.dirLightsColorOffset = loc(this.u_dirLights0color) - this.dirLightsLoc;
        this.dirLightsDirectionOffset = loc(this.u_dirLights0direction) - this.dirLightsLoc;
        int iLoc = loc(this.u_dirLights1color) - this.dirLightsLoc;
        this.dirLightsSize = iLoc;
        if (iLoc < 0) {
            this.dirLightsSize = 0;
        }
        this.pointLightsLoc = loc(this.u_pointLights0color);
        this.pointLightsColorOffset = loc(this.u_pointLights0color) - this.pointLightsLoc;
        this.pointLightsPositionOffset = loc(this.u_pointLights0position) - this.pointLightsLoc;
        this.pointLightsIntensityOffset = has(this.u_pointLights0intensity) ? loc(this.u_pointLights0intensity) - this.pointLightsLoc : -1;
        int iLoc2 = loc(this.u_pointLights1color) - this.pointLightsLoc;
        this.pointLightsSize = iLoc2;
        if (iLoc2 < 0) {
            this.pointLightsSize = 0;
        }
        this.spotLightsLoc = loc(this.u_spotLights0color);
        this.spotLightsColorOffset = loc(this.u_spotLights0color) - this.spotLightsLoc;
        this.spotLightsPositionOffset = loc(this.u_spotLights0position) - this.spotLightsLoc;
        this.spotLightsDirectionOffset = loc(this.u_spotLights0direction) - this.spotLightsLoc;
        this.spotLightsIntensityOffset = has(this.u_spotLights0intensity) ? loc(this.u_spotLights0intensity) - this.spotLightsLoc : -1;
        this.spotLightsCutoffAngleOffset = loc(this.u_spotLights0cutoffAngle) - this.spotLightsLoc;
        this.spotLightsExponentOffset = loc(this.u_spotLights0exponent) - this.spotLightsLoc;
        int iLoc3 = loc(this.u_spotLights1color) - this.spotLightsLoc;
        this.spotLightsSize = iLoc3;
        if (iLoc3 < 0) {
            this.spotLightsSize = 0;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void render(Renderable renderable, Attributes attributes) {
        if (!attributes.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
        bindMaterial(attributes);
        if (this.lighting) {
            bindLights(renderable, attributes);
        }
        super.render(renderable, attributes);
    }

    public void setDefaultCullFace(int i2) {
        this.config.defaultCullFace = i2;
    }

    public void setDefaultDepthFunc(int i2) {
        this.config.defaultDepthFunc = i2;
    }

    public DefaultShader(Renderable renderable, Config config) {
        this(renderable, config, createPrefix(renderable, config));
    }

    public boolean equals(Object obj) {
        return (obj instanceof DefaultShader) && equals((DefaultShader) obj);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DefaultShader(Renderable renderable, Config config, String str) {
        String str2 = config.vertexShader;
        String defaultVertexShader2 = str2 == null ? getDefaultVertexShader() : str2;
        String str3 = config.fragmentShader;
        this(renderable, config, str, defaultVertexShader2, str3 == null ? getDefaultFragmentShader() : str3);
    }

    public DefaultShader(Renderable renderable, Config config, String str, String str2, String str3) {
        this(renderable, config, new ShaderProgram(a.a.k(str, str2), a.a.k(str, str3)));
    }

    public static class Config {
        public int defaultCullFace;
        public int defaultDepthFunc;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public int numBones;
        public int numDirectionalLights;
        public int numPointLights;
        public int numSpotLights;
        public String vertexShader;

        public Config() {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
        }

        public Config(String str, String str2) {
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.vertexShader = str;
            this.fragmentShader = str2;
        }
    }

    public DefaultShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        int i2;
        int i3;
        int i4;
        this.u_dirLights0color = register(new BaseShader.Uniform("u_dirLights[0].color"));
        this.u_dirLights0direction = register(new BaseShader.Uniform("u_dirLights[0].direction"));
        this.u_dirLights1color = register(new BaseShader.Uniform("u_dirLights[1].color"));
        this.u_pointLights0color = register(new BaseShader.Uniform("u_pointLights[0].color"));
        this.u_pointLights0position = register(new BaseShader.Uniform("u_pointLights[0].position"));
        this.u_pointLights0intensity = register(new BaseShader.Uniform("u_pointLights[0].intensity"));
        this.u_pointLights1color = register(new BaseShader.Uniform("u_pointLights[1].color"));
        this.u_spotLights0color = register(new BaseShader.Uniform("u_spotLights[0].color"));
        this.u_spotLights0position = register(new BaseShader.Uniform("u_spotLights[0].position"));
        this.u_spotLights0intensity = register(new BaseShader.Uniform("u_spotLights[0].intensity"));
        this.u_spotLights0direction = register(new BaseShader.Uniform("u_spotLights[0].direction"));
        this.u_spotLights0cutoffAngle = register(new BaseShader.Uniform("u_spotLights[0].cutoffAngle"));
        this.u_spotLights0exponent = register(new BaseShader.Uniform("u_spotLights[0].exponent"));
        this.u_spotLights1color = register(new BaseShader.Uniform("u_spotLights[1].color"));
        this.u_fogColor = register(new BaseShader.Uniform("u_fogColor"));
        this.u_shadowMapProjViewTrans = register(new BaseShader.Uniform("u_shadowMapProjViewTrans"));
        this.u_shadowTexture = register(new BaseShader.Uniform("u_shadowTexture"));
        this.u_shadowPCFOffset = register(new BaseShader.Uniform("u_shadowPCFOffset"));
        this.ambientCubemap = new AmbientCubemap();
        this.normalMatrix = new k();
        this.tmpV1 = new a();
        Attributes attributesCombineAttributes = combineAttributes(renderable);
        this.config = config;
        this.program = shaderProgram;
        int i5 = 0;
        boolean z2 = renderable.environment != null;
        this.lighting = z2;
        long j2 = CubemapAttribute.EnvironmentMap;
        this.environmentCubemap = attributesCombineAttributes.has(j2) || (z2 && attributesCombineAttributes.has(j2));
        this.shadowMap = z2 && renderable.environment.shadowMap != null;
        this.renderable = renderable;
        this.attributesMask = attributesCombineAttributes.getMask() | optionalAttributes;
        this.vertexMask = renderable.meshPart.mesh.getVertexAttributes().getMaskWithSizePacked();
        this.directionalLights = new DirectionalLight[(!z2 || (i4 = config.numDirectionalLights) <= 0) ? 0 : i4];
        int i6 = 0;
        while (true) {
            DirectionalLight[] directionalLightArr = this.directionalLights;
            if (i6 >= directionalLightArr.length) {
                break;
            }
            directionalLightArr[i6] = new DirectionalLight();
            i6++;
        }
        this.pointLights = new PointLight[(!this.lighting || (i3 = config.numPointLights) <= 0) ? 0 : i3];
        int i7 = 0;
        while (true) {
            PointLight[] pointLightArr = this.pointLights;
            if (i7 >= pointLightArr.length) {
                break;
            }
            pointLightArr[i7] = new PointLight();
            i7++;
        }
        this.spotLights = new SpotLight[(!this.lighting || (i2 = config.numSpotLights) <= 0) ? 0 : i2];
        while (true) {
            SpotLight[] spotLightArr = this.spotLights;
            if (i5 >= spotLightArr.length) {
                break;
            }
            spotLightArr[i5] = new SpotLight();
            i5++;
        }
        if (!config.ignoreUnimplemented) {
            long j3 = implementedFlags;
            long j4 = this.attributesMask;
            if ((j3 & j4) != j4) {
                throw new m("Some attributes not implemented yet (" + this.attributesMask + ")");
            }
        }
        this.u_projTrans = register(Inputs.projTrans, Setters.projTrans);
        this.u_viewTrans = register(Inputs.viewTrans, Setters.viewTrans);
        this.u_projViewTrans = register(Inputs.projViewTrans, Setters.projViewTrans);
        this.u_cameraPosition = register(Inputs.cameraPosition, Setters.cameraPosition);
        this.u_cameraDirection = register(Inputs.cameraDirection, Setters.cameraDirection);
        this.u_cameraUp = register(Inputs.cameraUp, Setters.cameraUp);
        this.u_cameraNearFar = register(Inputs.cameraNearFar, Setters.cameraNearFar);
        this.u_time = register(new BaseShader.Uniform("u_time"));
        this.u_worldTrans = register(Inputs.worldTrans, Setters.worldTrans);
        this.u_viewWorldTrans = register(Inputs.viewWorldTrans, Setters.viewWorldTrans);
        this.u_projViewWorldTrans = register(Inputs.projViewWorldTrans, Setters.projViewWorldTrans);
        this.u_normalMatrix = register(Inputs.normalMatrix, Setters.normalMatrix);
        this.u_bones = (renderable.bones == null || config.numBones <= 0) ? -1 : register(Inputs.bones, new Setters.Bones(config.numBones));
        this.u_shininess = register(Inputs.shininess, Setters.shininess);
        this.u_opacity = register(Inputs.opacity);
        this.u_diffuseColor = register(Inputs.diffuseColor, Setters.diffuseColor);
        this.u_diffuseTexture = register(Inputs.diffuseTexture, Setters.diffuseTexture);
        this.u_diffuseUVTransform = register(Inputs.diffuseUVTransform, Setters.diffuseUVTransform);
        this.u_specularColor = register(Inputs.specularColor, Setters.specularColor);
        this.u_specularTexture = register(Inputs.specularTexture, Setters.specularTexture);
        this.u_specularUVTransform = register(Inputs.specularUVTransform, Setters.specularUVTransform);
        this.u_emissiveColor = register(Inputs.emissiveColor, Setters.emissiveColor);
        this.u_emissiveTexture = register(Inputs.emissiveTexture, Setters.emissiveTexture);
        this.u_emissiveUVTransform = register(Inputs.emissiveUVTransform, Setters.emissiveUVTransform);
        this.u_reflectionColor = register(Inputs.reflectionColor, Setters.reflectionColor);
        this.u_reflectionTexture = register(Inputs.reflectionTexture, Setters.reflectionTexture);
        this.u_reflectionUVTransform = register(Inputs.reflectionUVTransform, Setters.reflectionUVTransform);
        this.u_normalTexture = register(Inputs.normalTexture, Setters.normalTexture);
        this.u_normalUVTransform = register(Inputs.normalUVTransform, Setters.normalUVTransform);
        this.u_ambientTexture = register(Inputs.ambientTexture, Setters.ambientTexture);
        this.u_ambientUVTransform = register(Inputs.ambientUVTransform, Setters.ambientUVTransform);
        this.u_alphaTest = register(Inputs.alphaTest);
        this.u_ambientCubemap = this.lighting ? register(Inputs.ambientCube, new Setters.ACubemap(config.numDirectionalLights, config.numPointLights)) : -1;
        this.u_environmentCubemap = this.environmentCubemap ? register(Inputs.environmentCubemap, Setters.environmentCubemap) : -1;
    }
}

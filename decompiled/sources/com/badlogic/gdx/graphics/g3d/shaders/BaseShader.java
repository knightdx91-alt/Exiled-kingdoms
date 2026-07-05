package com.badlogic.gdx.graphics.g3d.shaders;

import a0.k;
import a0.q;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.o;
import com.badlogic.gdx.utils.p;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class BaseShader implements Shader {
    public Camera camera;
    public RenderContext context;
    private Mesh currentMesh;
    private int[] locations;
    public ShaderProgram program;
    private final a<String> uniforms = new a<>();
    private final a<Validator> validators = new a<>();
    private final a<Setter> setters = new a<>();
    private final o globalUniforms = new o();
    private final o localUniforms = new o();
    private final p attributes = new p();
    private final o tempArray = new o();
    private Attributes combinedAttributes = new Attributes();

    public static abstract class GlobalSetter implements Setter {
        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
        public boolean isGlobal(BaseShader baseShader, int i2) {
            return true;
        }
    }

    public static abstract class LocalSetter implements Setter {
        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
        public boolean isGlobal(BaseShader baseShader, int i2) {
            return false;
        }
    }

    public interface Setter {
        boolean isGlobal(BaseShader baseShader, int i2);

        void set(BaseShader baseShader, int i2, Renderable renderable, Attributes attributes);
    }

    public interface Validator {
        boolean validate(BaseShader baseShader, int i2, Renderable renderable);
    }

    private final int[] getAttributeLocations(VertexAttributes vertexAttributes) {
        this.tempArray.f1850b = 0;
        int size = vertexAttributes.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.tempArray.a(this.attributes.c(vertexAttributes.get(i2).getKey(), -1));
        }
        this.tempArray.h();
        return this.tempArray.f1849a;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        this.camera = camera;
        this.context = renderContext;
        this.program.bind();
        this.currentMesh = null;
        int i2 = 0;
        while (true) {
            o oVar = this.globalUniforms;
            if (i2 >= oVar.f1850b) {
                return;
            }
            a<Setter> aVar = this.setters;
            int iD = oVar.d(i2);
            if (aVar.get(iD) != null) {
                this.setters.get(iD).set(this, iD, null, null);
            }
            i2++;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader, com.badlogic.gdx.utils.i
    public void dispose() {
        this.program = null;
        this.uniforms.clear();
        this.validators.clear();
        this.setters.clear();
        this.localUniforms.f1850b = 0;
        this.globalUniforms.f1850b = 0;
        this.locations = null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        Mesh mesh = this.currentMesh;
        if (mesh != null) {
            mesh.unbind(this.program, this.tempArray.f1849a);
            this.currentMesh = null;
        }
    }

    public String getUniformAlias(int i2) {
        return this.uniforms.get(i2);
    }

    public int getUniformID(String str) {
        int i2 = this.uniforms.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.uniforms.get(i3).equals(str)) {
                return i3;
            }
        }
        return -1;
    }

    public final boolean has(int i2) {
        if (i2 >= 0) {
            int[] iArr = this.locations;
            if (i2 < iArr.length && iArr[i2] >= 0) {
                return true;
            }
        }
        return false;
    }

    public void init(ShaderProgram shaderProgram, Renderable renderable) {
        if (this.locations != null) {
            throw new m("Already initialized");
        }
        if (!shaderProgram.isCompiled()) {
            throw new m(shaderProgram.getLog());
        }
        this.program = shaderProgram;
        int i2 = this.uniforms.f1750b;
        this.locations = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            String str = this.uniforms.get(i3);
            Validator validator = this.validators.get(i3);
            Setter setter = this.setters.get(i3);
            if (validator == null || validator.validate(this, i3, renderable)) {
                this.locations[i3] = shaderProgram.fetchUniformLocation(str, false);
                if (this.locations[i3] >= 0 && setter != null) {
                    if (setter.isGlobal(this, i3)) {
                        this.globalUniforms.a(i3);
                    } else {
                        this.localUniforms.a(i3);
                    }
                }
            } else {
                this.locations[i3] = -1;
            }
            if (this.locations[i3] < 0) {
                this.validators.t(i3, null);
                this.setters.t(i3, null);
            }
        }
        if (renderable != null) {
            VertexAttributes vertexAttributes = renderable.meshPart.mesh.getVertexAttributes();
            int size = vertexAttributes.size();
            for (int i4 = 0; i4 < size; i4++) {
                VertexAttribute vertexAttribute = vertexAttributes.get(i4);
                int attributeLocation = shaderProgram.getAttributeLocation(vertexAttribute.alias);
                if (attributeLocation >= 0) {
                    this.attributes.e(vertexAttribute.getKey(), attributeLocation);
                }
            }
        }
    }

    public final int loc(int i2) {
        if (i2 >= 0) {
            int[] iArr = this.locations;
            if (i2 < iArr.length) {
                return iArr[i2];
            }
        }
        return -1;
    }

    public int register(String str, Validator validator, Setter setter) {
        if (this.locations != null) {
            throw new m("Cannot register an uniform after initialization");
        }
        int uniformID = getUniformID(str);
        if (uniformID >= 0) {
            this.validators.t(uniformID, validator);
            this.setters.t(uniformID, setter);
            return uniformID;
        }
        this.uniforms.a(str);
        this.validators.a(validator);
        this.setters.a(setter);
        return this.uniforms.f1750b - 1;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void render(Renderable renderable) {
        float[] fArr = renderable.worldTransform.f1724a;
        float f2 = fArr[0];
        float f3 = fArr[5];
        float f4 = fArr[10];
        float f5 = fArr[4];
        float f6 = fArr[9];
        float f7 = fArr[2];
        float f8 = (f5 * f6 * f7) + (f2 * f3 * f4);
        float f9 = fArr[8];
        float f10 = fArr[1];
        float f11 = fArr[6];
        if (a.a.z(f9, f3, f7, a.a.z(f5, f10, f4, a.a.z(f2, f6, f11, (f9 * f10 * f11) + f8))) == 0.0f) {
            return;
        }
        this.combinedAttributes.clear();
        Environment environment = renderable.environment;
        if (environment != null) {
            this.combinedAttributes.set(environment);
        }
        Material material = renderable.material;
        if (material != null) {
            this.combinedAttributes.set(material);
        }
        render(renderable, this.combinedAttributes);
    }

    public final boolean set(int i2, Matrix4 matrix4) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformMatrix(i3, matrix4);
        return true;
    }

    public final boolean set(int i2, k kVar) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformMatrix(i3, kVar);
        return true;
    }

    public static class Uniform implements Validator {
        public final String alias;
        public final long environmentMask;
        public final long materialMask;
        public final long overallMask;

        public Uniform(String str, long j2, long j3, long j4) {
            this.alias = str;
            this.materialMask = j2;
            this.environmentMask = j3;
            this.overallMask = j4;
        }

        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Validator
        public boolean validate(BaseShader baseShader, int i2, Renderable renderable) {
            Environment environment;
            Material material;
            long mask = 0;
            long mask2 = (renderable == null || (material = renderable.material) == null) ? 0L : material.getMask();
            if (renderable != null && (environment = renderable.environment) != null) {
                mask = environment.getMask();
            }
            long j2 = this.materialMask;
            if ((mask2 & j2) == j2) {
                long j3 = this.environmentMask;
                if ((mask & j3) == j3) {
                    long j4 = mask | mask2;
                    long j5 = this.overallMask;
                    if ((j4 & j5) == j5) {
                        return true;
                    }
                }
            }
            return false;
        }

        public Uniform(String str, long j2, long j3) {
            this(str, j2, j3, 0L);
        }

        public Uniform(String str, long j2) {
            this(str, 0L, 0L, j2);
        }

        public Uniform(String str) {
            this(str, 0L, 0L);
        }
    }

    public final boolean set(int i2, com.badlogic.gdx.math.a aVar) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, aVar);
        return true;
    }

    public final boolean set(int i2, q qVar) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, qVar);
        return true;
    }

    public void render(Renderable renderable, Attributes attributes) {
        int i2 = 0;
        while (true) {
            o oVar = this.localUniforms;
            if (i2 >= oVar.f1850b) {
                break;
            }
            a<Setter> aVar = this.setters;
            int iD = oVar.d(i2);
            if (aVar.get(iD) != null) {
                this.setters.get(iD).set(this, iD, renderable, attributes);
            }
            i2++;
        }
        Mesh mesh = this.currentMesh;
        if (mesh != renderable.meshPart.mesh) {
            if (mesh != null) {
                mesh.unbind(this.program, this.tempArray.f1849a);
            }
            Mesh mesh2 = renderable.meshPart.mesh;
            this.currentMesh = mesh2;
            mesh2.bind(this.program, getAttributeLocations(mesh2.getVertexAttributes()));
        }
        renderable.meshPart.render(this.program, false);
    }

    public final boolean set(int i2, Color color) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, color);
        return true;
    }

    public final boolean set(int i2, float f2) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, f2);
        return true;
    }

    public int register(String str, Validator validator) {
        return register(str, validator, null);
    }

    public int register(String str, Setter setter) {
        return register(str, null, setter);
    }

    public final boolean set(int i2, float f2, float f3) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, f2, f3);
        return true;
    }

    public int register(String str) {
        return register(str, null, null);
    }

    public int register(Uniform uniform, Setter setter) {
        return register(uniform.alias, uniform, setter);
    }

    public final boolean set(int i2, float f2, float f3, float f4) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, f2, f3, f4);
        return true;
    }

    public int register(Uniform uniform) {
        return register(uniform, (Setter) null);
    }

    public final boolean set(int i2, float f2, float f3, float f4, float f5) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformf(i3, f2, f3, f4, f5);
        return true;
    }

    public final boolean set(int i2, int i3) {
        int i4 = this.locations[i2];
        if (i4 < 0) {
            return false;
        }
        this.program.setUniformi(i4, i3);
        return true;
    }

    public final boolean set(int i2, int i3, int i4) {
        int i5 = this.locations[i2];
        if (i5 < 0) {
            return false;
        }
        this.program.setUniformi(i5, i3, i4);
        return true;
    }

    public final boolean set(int i2, int i3, int i4, int i5) {
        int i6 = this.locations[i2];
        if (i6 < 0) {
            return false;
        }
        this.program.setUniformi(i6, i3, i4, i5);
        return true;
    }

    public final boolean set(int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.locations[i2];
        if (i7 < 0) {
            return false;
        }
        this.program.setUniformi(i7, i3, i4, i5, i6);
        return true;
    }

    public final boolean set(int i2, TextureDescriptor textureDescriptor) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformi(i3, this.context.textureBinder.bind(textureDescriptor));
        return true;
    }

    public final boolean set(int i2, GLTexture gLTexture) {
        int i3 = this.locations[i2];
        if (i3 < 0) {
            return false;
        }
        this.program.setUniformi(i3, this.context.textureBinder.bind(gLTexture));
        return true;
    }
}

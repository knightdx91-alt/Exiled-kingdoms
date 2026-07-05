package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ImmediateModeRenderer20 implements ImmediateModeRenderer {
    private final int colorOffset;
    private final int maxVertices;
    private final Mesh mesh;
    private final int normalOffset;
    private int numSetTexCoords;
    private final int numTexCoords;
    private int numVertices;
    private boolean ownsShader;
    private int primitiveType;
    private final Matrix4 projModelView;
    private ShaderProgram shader;
    private final String[] shaderUniformNames;
    private final int texCoordOffset;
    private int vertexIdx;
    private final int vertexSize;
    private final float[] vertices;

    public ImmediateModeRenderer20(boolean z2, boolean z3, int i2) {
        this(5000, z2, z3, i2, createDefaultShader(z2, z3, i2));
        this.ownsShader = true;
    }

    private VertexAttribute[] buildVertexAttributes(boolean z2, boolean z3, int i2) {
        a aVar = new a();
        aVar.a(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE));
        if (z2) {
            aVar.a(new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE));
        }
        if (z3) {
            aVar.a(new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE));
        }
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.a(new VertexAttribute(16, 2, a.a.h(i3, ShaderProgram.TEXCOORD_ATTRIBUTE)));
        }
        VertexAttribute[] vertexAttributeArr = new VertexAttribute[aVar.f1750b];
        for (int i4 = 0; i4 < aVar.f1750b; i4++) {
            vertexAttributeArr[i4] = (VertexAttribute) aVar.get(i4);
        }
        return vertexAttributeArr;
    }

    public static ShaderProgram createDefaultShader(boolean z2, boolean z3, int i2) {
        return new ShaderProgram(createVertexShader(z2, z3, i2), createFragmentShader(z2, z3, i2));
    }

    private static String createFragmentShader(boolean z2, boolean z3, int i2) {
        String str = z3 ? "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_col;\n" : "#ifdef GL_ES\nprecision mediump float;\n#endif\n";
        for (int i3 = 0; i3 < i2; i3++) {
            str = (str + "varying vec2 v_tex" + i3 + ";\n") + "uniform sampler2D u_sampler" + i3 + ";\n";
        }
        StringBuilder sbT = a.a.t(str, "void main() {\n   gl_FragColor = ");
        sbT.append(z3 ? "v_col" : "vec4(1, 1, 1, 1)");
        String string = sbT.toString();
        if (i2 > 0) {
            string = a.a.k(string, " * ");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            string = i4 == i2 - 1 ? string + " texture2D(u_sampler" + i4 + ",  v_tex" + i4 + ")" : string + " texture2D(u_sampler" + i4 + ",  v_tex" + i4 + ") *";
        }
        return a.a.k(string, ";\n}");
    }

    private static String createVertexShader(boolean z2, boolean z3, int i2) {
        StringBuilder sb = new StringBuilder("attribute vec4 a_position;\n");
        sb.append(z2 ? "attribute vec3 a_normal;\n" : "");
        sb.append(z3 ? "attribute vec4 a_color;\n" : "");
        String string = sb.toString();
        for (int i3 = 0; i3 < i2; i3++) {
            string = string + "attribute vec2 a_texCoord" + i3 + ";\n";
        }
        StringBuilder sbT = a.a.t(string, "uniform mat4 u_projModelView;\n");
        sbT.append(z3 ? "varying vec4 v_col;\n" : "");
        String string2 = sbT.toString();
        for (int i4 = 0; i4 < i2; i4++) {
            string2 = string2 + "varying vec2 v_tex" + i4 + ";\n";
        }
        String strK = a.a.k(string2, "void main() {\n   gl_Position = u_projModelView * a_position;\n");
        if (z3) {
            strK = a.a.k(strK, "   v_col = a_color;\n   v_col.a *= 255.0 / 254.0;\n");
        }
        for (int i5 = 0; i5 < i2; i5++) {
            strK = strK + "   v_tex" + i5 + " = a_texCoord" + i5 + ";\n";
        }
        return a.a.k(strK, "   gl_PointSize = 1.0;\n}\n");
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void begin(Matrix4 matrix4, int i2) {
        this.projModelView.o(matrix4);
        this.primitiveType = i2;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(Color color) {
        this.vertices[this.vertexIdx + this.colorOffset] = color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void dispose() {
        ShaderProgram shaderProgram;
        if (this.ownsShader && (shaderProgram = this.shader) != null) {
            shaderProgram.dispose();
        }
        this.mesh.dispose();
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void end() {
        flush();
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void flush() {
        if (this.numVertices == 0) {
            return;
        }
        this.shader.bind();
        this.shader.setUniformMatrix("u_projModelView", this.projModelView);
        for (int i2 = 0; i2 < this.numTexCoords; i2++) {
            this.shader.setUniformi(this.shaderUniformNames[i2], i2);
        }
        this.mesh.setVertices(this.vertices, 0, this.vertexIdx);
        this.mesh.render(this.shader, this.primitiveType);
        this.numSetTexCoords = 0;
        this.vertexIdx = 0;
        this.numVertices = 0;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getMaxVertices() {
        return this.maxVertices;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getNumVertices() {
        return this.numVertices;
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void normal(float f2, float f3, float f4) {
        int i2 = this.vertexIdx + this.normalOffset;
        float[] fArr = this.vertices;
        fArr[i2] = f2;
        fArr[i2 + 1] = f3;
        fArr[i2 + 2] = f4;
    }

    public void setShader(ShaderProgram shaderProgram) {
        if (this.ownsShader) {
            this.shader.dispose();
        }
        this.shader = shaderProgram;
        this.ownsShader = false;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void texCoord(float f2, float f3) {
        int i2 = this.vertexIdx + this.texCoordOffset;
        float[] fArr = this.vertices;
        int i3 = this.numSetTexCoords;
        fArr[i2 + i3] = f2;
        fArr[i2 + i3 + 1] = f3;
        this.numSetTexCoords = i3 + 2;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void vertex(float f2, float f3, float f4) {
        int i2 = this.vertexIdx;
        float[] fArr = this.vertices;
        fArr[i2] = f2;
        fArr[i2 + 1] = f3;
        fArr[i2 + 2] = f4;
        this.numSetTexCoords = 0;
        this.vertexIdx = i2 + this.vertexSize;
        this.numVertices++;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f2, float f3, float f4, float f5) {
        this.vertices[this.vertexIdx + this.colorOffset] = Color.toFloatBits(f2, f3, f4, f5);
    }

    public ImmediateModeRenderer20(int i2, boolean z2, boolean z3, int i3) {
        this(i2, z2, z3, i3, createDefaultShader(z2, z3, i3));
        this.ownsShader = true;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f2) {
        this.vertices[this.vertexIdx + this.colorOffset] = f2;
    }

    public ImmediateModeRenderer20(int i2, boolean z2, boolean z3, int i3, ShaderProgram shaderProgram) {
        this.projModelView = new Matrix4();
        this.maxVertices = i2;
        this.numTexCoords = i3;
        this.shader = shaderProgram;
        Mesh mesh = new Mesh(false, i2, 0, buildVertexAttributes(z2, z3, i3));
        this.mesh = mesh;
        this.vertices = new float[(mesh.getVertexAttributes().vertexSize / 4) * i2];
        this.vertexSize = mesh.getVertexAttributes().vertexSize / 4;
        this.normalOffset = mesh.getVertexAttribute(8) != null ? mesh.getVertexAttribute(8).offset / 4 : 0;
        this.colorOffset = mesh.getVertexAttribute(4) != null ? mesh.getVertexAttribute(4).offset / 4 : 0;
        this.texCoordOffset = mesh.getVertexAttribute(16) != null ? mesh.getVertexAttribute(16).offset / 4 : 0;
        this.shaderUniformNames = new String[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            this.shaderUniformNames[i4] = a.a.h(i4, "u_sampler");
        }
    }
}

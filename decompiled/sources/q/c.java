package q;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* JADX INFO: compiled from: LightMap.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ShaderProgram f3750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    FrameBuffer f3751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Mesh f3752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private FrameBuffer f3753d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private f f3754e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ShaderProgram f3755f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ShaderProgram f3756g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ShaderProgram f3757h;

    public c(f fVar, int i2, int i3) {
        this.f3754e = fVar;
        i2 = i2 <= 0 ? 1 : i2;
        i3 = i3 <= 0 ? 1 : i3;
        Pixmap.Format format = Pixmap.Format.RGBA8888;
        this.f3751b = new FrameBuffer(format, i2, i3, false);
        this.f3753d = new FrameBuffer(format, i2, i3, false);
        Mesh mesh = new Mesh(true, 4, 0, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(16, 2, ShaderProgram.TEXCOORD_ATTRIBUTE));
        mesh.setVertices(new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f});
        this.f3752c = mesh;
        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec2 a_texCoord;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_texCoords = a_texCoord;\n   gl_Position = a_position;\n}\n", "#ifdef GL_ES\nprecision lowp float;\n#define MED mediump\n#else\n#define MED \n#endif\nvarying MED vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform vec4 ambient;\nvoid main()\n{\nvec4 c = texture2D(u_texture, v_texCoords);\ngl_FragColor.rgb = c.rgb * c.a + ambient.rgb;\ngl_FragColor.a = ambient.a - c.a;\n}\n");
        if (!shaderProgram.isCompiled()) {
            Gdx.app.log("ERROR", shaderProgram.getLog());
        }
        this.f3750a = shaderProgram;
        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram2 = new ShaderProgram("attribute vec4 a_position;\nattribute vec2 a_texCoord;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_texCoords = a_texCoord;\n   gl_Position = a_position;\n}\n", "#ifdef GL_ES\nprecision lowp float;\n#define MED mediump\n#else\n#define MED \n#endif\nvarying MED vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform  vec4 ambient;\nvoid main()\n{\ngl_FragColor.rgb = (ambient.rgb + texture2D(u_texture, v_texCoords).rgb);\ngl_FragColor.a = 1.0;\n}\n");
        if (!shaderProgram2.isCompiled()) {
            Gdx.app.log("ERROR", shaderProgram2.getLog());
        }
        this.f3757h = shaderProgram2;
        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram3 = new ShaderProgram("attribute vec4 a_position;\nattribute vec2 a_texCoord;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_texCoords = a_texCoord;\n   gl_Position = a_position;\n}\n", "#ifdef GL_ES\nprecision lowp float;\n#define MED mediump\n#else\n#define MED \n#endif\nvarying MED vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\ngl_FragColor = texture2D(u_texture, v_texCoords);\n}\n");
        if (!shaderProgram3.isCompiled()) {
            Gdx.app.log("ERROR", shaderProgram3.getLog());
        }
        this.f3755f = shaderProgram3;
        String string = Integer.toString(i2);
        String string2 = Integer.toString(i3);
        String str = f.f3765v ? ".rgb" : "";
        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram4 = new ShaderProgram("attribute vec4 a_position;\nuniform vec2  dir;\nattribute vec2 a_texCoord;\nvarying vec2 v_texCoords0;\nvarying vec2 v_texCoords1;\nvarying vec2 v_texCoords2;\nvarying vec2 v_texCoords3;\nvarying vec2 v_texCoords4;\n#define FBO_W " + string + ".0\n#define FBO_H " + string2 + ".0\nconst vec2 futher = vec2(3.2307692308 / FBO_W, 3.2307692308 / FBO_H );\nconst vec2 closer = vec2(1.3846153846 / FBO_W, 1.3846153846 / FBO_H );\nvoid main()\n{\nvec2 f = futher * dir;\nvec2 c = closer * dir;\nv_texCoords0 = a_texCoord - f;\nv_texCoords1 = a_texCoord - c;\nv_texCoords2 = a_texCoord;\nv_texCoords3 = a_texCoord + c;\nv_texCoords4 = a_texCoord + f;\ngl_Position = a_position;\n}\n", "#ifdef GL_ES\nprecision lowp float;\n#define MED mediump\n#else\n#define MED \n#endif\nuniform sampler2D u_texture;\nvarying MED vec2 v_texCoords0;\nvarying MED vec2 v_texCoords1;\nvarying MED vec2 v_texCoords2;\nvarying MED vec2 v_texCoords3;\nvarying MED vec2 v_texCoords4;\nconst float center = 0.2270270270;\nconst float close  = 0.3162162162;\nconst float far    = 0.0702702703;\nvoid main()\n{\t \ngl_FragColor" + str + " = far    * texture2D(u_texture, v_texCoords0)" + str + "\n\t      \t\t+ close  * texture2D(u_texture, v_texCoords1)" + str + "\n\t\t\t\t+ center * texture2D(u_texture, v_texCoords2)" + str + "\n\t\t\t\t+ close  * texture2D(u_texture, v_texCoords3)" + str + "\n\t\t\t\t+ far    * texture2D(u_texture, v_texCoords4)" + str + ";\n}\n");
        if (!shaderProgram4.isCompiled()) {
            Gdx.app.log("ERROR", shaderProgram4.getLog());
        }
        this.f3756g = shaderProgram4;
    }

    final void a() {
        this.f3750a.dispose();
        this.f3756g.dispose();
        this.f3752c.dispose();
        this.f3751b.dispose();
        this.f3753d.dispose();
    }

    public final void b() {
        ShaderProgram shaderProgram;
        f fVar = this.f3754e;
        boolean z2 = fVar.f3779n > 0;
        FrameBuffer frameBuffer = this.f3751b;
        Mesh mesh = this.f3752c;
        if (z2 && fVar.f3777l) {
            Gdx.gl20.glDisable(GL20.GL_BLEND);
            for (int i2 = 0; i2 < fVar.f3778m; i2++) {
                frameBuffer.getColorBufferTexture().bind(0);
                FrameBuffer frameBuffer2 = this.f3753d;
                frameBuffer2.begin();
                ShaderProgram shaderProgram2 = this.f3756g;
                shaderProgram2.begin();
                shaderProgram2.setUniformf("dir", 1.0f, 0.0f);
                mesh.render(shaderProgram2, 6, 0, 4);
                shaderProgram2.end();
                frameBuffer2.end();
                frameBuffer2.getColorBufferTexture().bind(0);
                frameBuffer.begin();
                shaderProgram2.begin();
                shaderProgram2.setUniformf("dir", 0.0f, 1.0f);
                mesh.render(shaderProgram2, 6, 0, 4);
                shaderProgram2.end();
                frameBuffer.end();
            }
            Gdx.gl20.glEnable(GL20.GL_BLEND);
        }
        frameBuffer.getColorBufferTexture().bind(0);
        if (fVar.f3776k) {
            boolean z3 = f.f3765v;
            Color color = fVar.f3770e;
            if (z3) {
                shaderProgram = this.f3757h;
                shaderProgram.begin();
                GL20 gl20 = Gdx.gl20;
                a aVar = fVar.f3766a;
                gl20.glBlendFunc(aVar.f3727a, aVar.f3728b);
                shaderProgram.setUniformf("ambient", color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            } else {
                shaderProgram = this.f3750a;
                shaderProgram.begin();
                GL20 gl202 = Gdx.gl20;
                a aVar2 = fVar.f3767b;
                gl202.glBlendFunc(aVar2.f3727a, aVar2.f3728b);
                float f2 = color.f1680r;
                float f3 = color.f1677a;
                shaderProgram.setUniformf("ambient", f2 * f3, color.f1679g * f3, color.f1678b * f3, 1.0f - f3);
            }
            mesh.render(shaderProgram, 6);
            shaderProgram.end();
        } else if (z2) {
            GL20 gl203 = Gdx.gl20;
            a aVar3 = fVar.f3768c;
            gl203.glBlendFunc(aVar3.f3727a, aVar3.f3728b);
            ShaderProgram shaderProgram3 = this.f3755f;
            shaderProgram3.begin();
            mesh.render(shaderProgram3, 6);
            shaderProgram3.end();
        }
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }
}

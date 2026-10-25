package ekvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * The game's own 2D frame (menus, HUD, windows, conversations) rendered into an offscreen
 * texture with a transparent background (GlTap.transparentClear), so it can be shown over
 * the 3D scene on desktop, or on a floating panel in VR. Also maps a pick on the panel to a
 * touch on the game, and a pick on the 3D ground to the touch the 2D game would have got.
 */
public class UiLayer {
    public int w, h, fbo, tex, depth;
    int prog, aPos, aUv, uMvp;
    final FloatBuffer quad = ByteBuffer.allocateDirect(6 * 5 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    final ByteBuffer pixel = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());

    static GL20 gl() { return Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20; }

    /**
     * Our draws use client-side vertex arrays: make sure no VBO/VAO the game left bound
     * (libGDX uses VAOs on an ES3 context) turns our array pointers into buffer offsets.
     */
    static void clientArrays(GL20 gl) {
        gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
        gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
        if (Gdx.gl30 != null) Gdx.gl30.glBindVertexArray(0);
    }

    public UiLayer(int w, int h) {
        this.w = w; this.h = h;
        GL20 gl = gl();
        IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
        gl.glGenTextures(1, ib); tex = ib.get(0);
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, w, h, 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, null);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_LINEAR);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_LINEAR);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE);
        ib.clear(); gl.glGenRenderbuffers(1, ib); depth = ib.get(0);
        gl.glBindRenderbuffer(GL20.GL_RENDERBUFFER, depth);
        gl.glRenderbufferStorage(GL20.GL_RENDERBUFFER, 0x88F0 /* DEPTH24_STENCIL8 */, w, h);
        ib.clear(); gl.glGenFramebuffers(1, ib); fbo = ib.get(0);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo);
        gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, tex, 0);
        gl.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_DEPTH_ATTACHMENT, GL20.GL_RENDERBUFFER, depth);
        gl.glFramebufferRenderbuffer(GL20.GL_FRAMEBUFFER, GL20.GL_STENCIL_ATTACHMENT, GL20.GL_RENDERBUFFER, depth);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, 0);
        int vs = Scene3D.shader(gl, GL20.GL_VERTEX_SHADER,
                "attribute vec3 a_pos; attribute vec2 a_uv; uniform mat4 u_mvp; varying vec2 v_uv;\n" +
                "void main(){ v_uv=a_uv; gl_Position=u_mvp*vec4(a_pos,1.0); }");
        int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision mediump float;\n#endif\n" +
                "uniform sampler2D u_tex; varying vec2 v_uv; void main(){ gl_FragColor=texture2D(u_tex,v_uv); }");
        prog = gl.glCreateProgram();
        gl.glAttachShader(prog, vs); gl.glAttachShader(prog, fs); gl.glLinkProgram(prog);
        aPos = gl.glGetAttribLocation(prog, "a_pos");
        aUv = gl.glGetAttribLocation(prog, "a_uv");
        uMvp = gl.glGetUniformLocation(prog, "u_mvp");
    }

    /** Render the game's frame into the layer (call around ApplicationListener.render). */
    public void begin() {
        GL20 gl = gl();
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo);
        gl.glViewport(0, 0, w, h);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);
        GlTap.transparentClear = true;
    }

    public void end(int defaultFbo) {
        GlTap.transparentClear = false;
        gl().glBindFramebuffer(GL20.GL_FRAMEBUFFER, defaultFbo);
    }

    /** Is the layer opaque at (x, y) (top-left origin, layer pixels)? i.e. did the click hit UI. */
    public boolean opaqueAt(int x, int y) {
        GL20 gl = gl();
        int[] prev = new int[1];
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo);
        pixel.clear();
        gl.glReadPixels(x, h - 1 - y, 1, 1, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixel);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, 0);
        return (pixel.get(3) & 0xff) > 8;
    }

    /** Draw the layer as a quad: corners (x0,y0)-(x1,y1) at depth z in the space of {@code mvp}. */
    public void draw(float[] mvp, float[] tl, float[] tr, float[] br, float[] bl) {
        drawTexture(tex, mvp, bl, br, tr, tl);         // the layer's row 0 is its bottom
    }

    /** Draw a premultiplied texture as a quad; pXY = where texture coordinate (X, Y) goes. */
    public void drawTexture(int tex, float[] mvp, float[] p00, float[] p10, float[] p11, float[] p01) {
        GL20 gl = gl();
        quad.clear();
        float[][] v = {p00, p10, p11, p00, p11, p01};
        float[][] uv = {{0, 0}, {1, 0}, {1, 1}, {0, 0}, {1, 1}, {0, 1}};
        for (int i = 0; i < 6; i++) quad.put(v[i][0]).put(v[i][1]).put(v[i][2]).put(uv[i][0]).put(uv[i][1]);
        clientArrays(gl);
        gl.glUseProgram(prog);
        gl.glUniformMatrix4fv(uMvp, 1, false, mvp, 0);
        gl.glDisable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);   // the layer is premultiplied by the game's blending
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        quad.position(0);
        gl.glEnableVertexAttribArray(aPos);
        gl.glVertexAttribPointer(aPos, 3, GL20.GL_FLOAT, false, 20, quad);
        quad.position(3);
        gl.glEnableVertexAttribArray(aUv);
        gl.glVertexAttribPointer(aUv, 2, GL20.GL_FLOAT, false, 20, quad);
        gl.glDrawArrays(GL20.GL_TRIANGLES, 0, 6);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * A ground point (world units, X = column, Z = -row) -> the window pixel where the 2D game
     * would show it (null when no map is on screen), using the game's own camera (position, viewport, zoom). Tapping there makes
     * the real game do whatever it does for that spot (walk, attack, talk, open, pick up).
     */
    public static int[] groundToTouch(float X, float Z, int winW, int winH) {
        e.a.a.a lr;
        try { lr = Scene3D.fLevelR == null ? null : (e.a.a.a) Scene3D.fLevelR.get(null); } catch (Exception ex) { lr = null; }
        if (lr == null || lr.g == null || net.fdgames.GameLevel.GameLevel.h() == null) return null;   // no map on screen
        com.badlogic.gdx.graphics.OrthographicCamera cam = lr.g;
        float gi = X, go = -Z;
        float sx = 32f * (gi + go), sy = 16f * (go - gi);
        float vw = cam.viewportWidth * cam.zoom, vh = cam.viewportHeight * cam.zoom;
        float px = ((sx - cam.position.b) / vw + 0.5f) * winW;
        float py = (0.5f - (sy - cam.position.c) / vh) * winH;
        return new int[]{Math.round(px), Math.round(py)};
    }
}

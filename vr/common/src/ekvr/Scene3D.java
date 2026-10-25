package ekvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashSet;

/**
 * The game's map, re-drawn in 3D (deobf/QUEST_VR_SPEC.md §3).
 *
 * Each frame the real GameLevelRenderer (e.a.a.a) runs as usual, but its map renderer's batch
 * is a {@link Rec}, so nothing map-space reaches the screen; we get every quad instead:
 *  - floor tiles (ground/water/... layers, matched by texture + UV) are laid on the ground
 *    through the inverse of EK's isometric projection (exact: the projection is affine);
 *  - everything else (trees, objects, roofs, characters, particles, loot) stands up as a
 *    Y-billboard at its base point; small quads drawn just above a character (health bars,
 *    labels) ride on that character;
 *  - additive quads (spell particles) are blended last.
 * The box2dlights RayHandler (c.f) is swapped for one that never renders: its ambient
 * colour (day/night, dungeon darkness) becomes a tint on the 3D scene.
 *
 * World units: 1 unit = 1 map cell (32 world px); X = cell column, Z = -cell row, Y up.
 */
public class Scene3D {
    public static final float S = (float) (Math.sqrt(2) / 64);            // art px -> units, horizontal
    public static final float SY = S / (float) Math.cos(Math.toRadians(30)); // art px -> units, vertical (2:1 = 30°)

    public final Rec rec = new Rec();
    public float zoom = 2.6f;                    // how much of the map the 2D renderer culls to (bigger = more)
    public final float[] tint = {1, 1, 1, 1};
    public float heroX, heroZ;                   // hero ground position (units)
    boolean ready;
    Object lastMap;
    final HashSet<Long> floorKeys = new HashSet<>();
    Object origBatch;
    int prog, aPos, aUv, aCol, uMvp, uCut, uTint;
    FloatBuffer vb = ByteBuffer.allocateDirect(Rec.MAX * 6 * 9 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

    // ---- hooking -------------------------------------------------------------------------
    static Field field(Class<?> c, String n) throws Exception { Field f = c.getDeclaredField(n); f.setAccessible(true); return f; }
    static Field fLevelR, fMapBatch, fMapMap, fLights, fWorld, fAmbient;

    /** Point the game's map drawing at our recorder. Call every frame before the game renders. */
    public void beginFrame() {
        rec.reset();
        try {
            if (fLevelR == null) {
                fLevelR = field(e.a.a.a.class, "w");
                fMapBatch = field(com.badlogic.gdx.s.j.k.a.class, "c");
                fMapMap = field(com.badlogic.gdx.s.j.k.a.class, "a");
                fLights = field(e.a.a.a.class, "d");
                fWorld = field(e.a.a.a.class, "c");
                fAmbient = field(c.f.class, "e");
            }
            e.a.a.a lr = (e.a.a.a) fLevelR.get(null);
            if (lr == null || lr.l == null) return;
            Object b = fMapBatch.get(lr.l);
            if (b != rec) { origBatch = b; fMapBatch.set(lr.l, rec); }
            Object lights = fLights.get(lr);
            if (lights != null && !(lights instanceof NoLight)) {
                Object world = fWorld.get(lr);
                if (world != null) fLights.set(lr, new NoLight((com.badlogic.gdx.physics.box2d.World) world));
            }
            if (lr.g != null) { lr.g.zoom = zoom; lr.g.update(); }
            Object map = fMapMap.get(lr.l);
            if (map != lastMap) { lastMap = map; buildFloorKeys((com.badlogic.gdx.s.j.b) map); }
            net.fdgames.GameEntities.Final.Player p = net.fdgames.GameLevel.GameLevel.h();
            if (p != null) { heroX = p.x / 32f - 1f; heroZ = -(p.y / 32f - 1f); }
        } catch (Throwable t) {
            System.out.println("[ekvr] hook failed: " + t);
        }
    }

    /** RayHandler that keeps its ambient colour (read by Scene3D) but never draws a lightmap. */
    public static class NoLight extends c.f {
        public NoLight(com.badlogic.gdx.physics.box2d.World w) { super(w); }
        public void a() {}
    }

    static long key(int handle, float u, float v) {
        return ((long) handle << 40) ^ ((long) Math.round(u * 65536) << 20) ^ Math.round(v * 65536);
    }

    void buildFloorKeys(com.badlogic.gdx.s.j.b map) {
        floorKeys.clear();
        if (map == null) return;
        for (com.badlogic.gdx.s.d layer : map.a()) {
            if (!(layer instanceof com.badlogic.gdx.s.j.f)) continue;
            String n = String.valueOf(layer.a()).toLowerCase();
            if (n.contains("object") || n.contains("scener") || n.contains("roof")) continue;
            com.badlogic.gdx.s.j.f tl = (com.badlogic.gdx.s.j.f) layer;
            for (int x = 0; x < 512; x++) {
                boolean any = false;
                for (int y = 0; y < 512; y++) {
                    com.badlogic.gdx.s.j.f.a cell;
                    try { cell = tl.a(x, y); } catch (Throwable t) { break; }
                    if (cell == null || cell.a() == null) continue;
                    any = true;
                    com.badlogic.gdx.graphics.g2d.TextureRegion r = cell.a().b();
                    if (r != null) floorKeys.add(key(r.getTexture().getTextureObjectHandle(), r.getU(), r.getV2()));
                }
                if (!any && x > 200) break;
            }
        }
        System.out.println("[ekvr] floor tile kinds: " + floorKeys.size());
    }

    /** After the game frame: read the scene ambient from the (silenced) RayHandler. */
    public void endFrame() {
        try {
            e.a.a.a lr = (e.a.a.a) fLevelR.get(null);
            Object lights = lr == null ? null : fLights.get(lr);
            if (lights != null) {
                com.badlogic.gdx.graphics.Color c = (com.badlogic.gdx.graphics.Color) fAmbient.get(lights);
                tint[0] = c.r; tint[1] = c.g; tint[2] = c.b;
                // the 2D lightmap multiplies by ambient; outdoors at noon it is ~1
                float m = Math.max(tint[0], Math.max(tint[1], tint[2]));
                if (m < 0.05f) { tint[0] = tint[1] = tint[2] = 1; }
            }
        } catch (Throwable ignored) {}
    }

    // ---- drawing ---------------------------------------------------------------------------
    static final String VS = "attribute vec3 a_pos; attribute vec2 a_uv; attribute vec4 a_col;\n" +
            "uniform mat4 u_mvp; varying vec2 v_uv; varying vec4 v_col;\n" +
            "void main(){ v_uv=a_uv; v_col=a_col; gl_Position=u_mvp*vec4(a_pos,1.0); }";
    static final String FS = "#ifdef GL_ES\nprecision mediump float;\n#endif\n" +
            "uniform sampler2D u_tex; uniform float u_cut; uniform vec4 u_tint; varying vec2 v_uv; varying vec4 v_col;\n" +
            "void main(){ vec4 c=texture2D(u_tex,v_uv)*v_col; if(c.a<u_cut) discard; gl_FragColor=vec4(c.rgb*u_tint.rgb,c.a); }";

    void init() {
        GL20 gl = Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20;
        int vs = shader(gl, GL20.GL_VERTEX_SHADER, VS), fs = shader(gl, GL20.GL_FRAGMENT_SHADER, FS);
        prog = gl.glCreateProgram();
        gl.glAttachShader(prog, vs);
        gl.glAttachShader(prog, fs);
        gl.glLinkProgram(prog);
        aPos = gl.glGetAttribLocation(prog, "a_pos");
        aUv = gl.glGetAttribLocation(prog, "a_uv");
        aCol = gl.glGetAttribLocation(prog, "a_col");
        uMvp = gl.glGetUniformLocation(prog, "u_mvp");
        uCut = gl.glGetUniformLocation(prog, "u_cut");
        uTint = gl.glGetUniformLocation(prog, "u_tint");
        ready = true;
    }

    static int shader(GL20 gl, int type, String src) {
        int s = gl.glCreateShader(type);
        gl.glShaderSource(s, src);
        gl.glCompileShader(s);
        String log = gl.glGetShaderInfoLog(s);
        if (log != null && log.toLowerCase().contains("error")) System.out.println("[ekvr] shader: " + log);
        return s;
    }

    static float gi(float sx, float sy) { return (sx / 32f - sy / 16f) / 2f; }        // iso screen -> cell column
    static float go(float sx, float sy) { return (sx / 32f + sy / 16f) / 2f; }        // iso screen -> cell row

    // per-frame classification scratch
    final byte[] kind = new byte[Rec.MAX];               // 0 floor, 1 upright, 2 upright-translucent, 3 additive
    final float[] ax = new float[Rec.MAX], az = new float[Rec.MAX], aBase = new float[Rec.MAX], aCx = new float[Rec.MAX];

    /**
     * Draw the recorded map. {@code mvp} maps world units to clip space for this eye/view;
     * (vx, vz) is the viewer's ground position in world units (for billboard facing).
     * {@code world} places the map: world units -> scene (identity, tabletop scale, ...).
     */
    public void draw(float[] mvp, float vx, float vz) {
        if (!ready) init();
        GL20 gl = Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20;
        int n = rec.n;
        float[] q = rec.q;
        // 1. classify + anchors of big sprites (characters)
        int actors = 0;
        int[] actorIdx = new int[512];
        for (int i = 0; i < n; i++) {
            int o = i * 16;
            float minX = Math.min(Math.min(q[o], q[o + 4]), Math.min(q[o + 8], q[o + 12]));
            float maxX = Math.max(Math.max(q[o], q[o + 4]), Math.max(q[o + 8], q[o + 12]));
            float minY = Math.min(Math.min(q[o + 1], q[o + 5]), Math.min(q[o + 9], q[o + 13]));
            float maxY = Math.max(Math.max(q[o + 1], q[o + 5]), Math.max(q[o + 9], q[o + 13]));
            boolean floor = rec.tileDraw[i] && floorKeys.contains(key(rec.tex[i].getTextureObjectHandle(), q[o + 2], q[o + 3]));
            int a = (Float.floatToRawIntBits(rec.col[i]) >>> 24) & 0xff;
            kind[i] = (byte) (floor ? 0 : rec.additive[i] ? 3 : a < 250 ? 2 : 1);
            aCx[i] = (minX + maxX) / 2;
            aBase[i] = minY + 16;
            ax[i] = gi(aCx[i], aBase[i]);
            az[i] = -go(aCx[i], aBase[i]);
            if (!floor && !rec.tileDraw[i] && maxY - minY >= 60 && actors < actorIdx.length) actorIdx[actors++] = i;
            // small quads just above a character (health bars, names, status icons) ride on it
            if (!floor && !rec.tileDraw[i] && maxY - minY < 40) {
                for (int k = actors - 1; k >= 0; k--) {
                    int j = actorIdx[k];
                    if (Math.abs(aCx[i] - aCx[j]) < 56 && minY >= aBase[j] - 30 && minY < aBase[j] + 170) {
                        ax[i] = ax[j]; az[i] = az[j]; aCx[i] = aCx[j]; aBase[i] = aBase[j];
                        break;
                    }
                }
            }
        }
        UiLayer.clientArrays(gl);
        gl.glUseProgram(prog);
        gl.glUniformMatrix4fv(uMvp, 1, false, mvp, 0);
        gl.glUniform4f(uTint, tint[0], tint[1], tint[2], 1);
        gl.glEnable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_BLEND);
        gl.glDisable(GL20.GL_CULL_FACE);
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        // pass 1: floor, painter's order, writes depth
        gl.glDepthFunc(GL20.GL_ALWAYS);
        gl.glDepthMask(true);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        pass(gl, 0, 0.02f, vx, vz);
        // pass 2: upright opaque cut-outs
        gl.glDepthFunc(GL20.GL_LEQUAL);
        pass(gl, 1, 0.5f, vx, vz);
        // pass 3: translucent (fading roofs, ghosts, corpses), then additive particles
        gl.glDepthMask(false);
        pass(gl, 2, 0.02f, vx, vz);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        pass(gl, 3, 0.01f, vx, vz);
        gl.glDepthMask(true);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    void pass(GL20 gl, int k, float cut, float vx, float vz) {
        gl.glUniform1f(uCut, cut);
        int n = rec.n;
        float[] q = rec.q;
        Texture cur = null;
        vb.clear();
        int verts = 0;
        for (int i = 0; i < n; i++) {
            if (kind[i] != k) continue;
            if (rec.tex[i] != cur && verts > 0) { flush(gl, cur, verts); verts = 0; }
            cur = rec.tex[i];
            int o = i * 16;
            int c = Float.floatToRawIntBits(rec.col[i]);
            float r = (c & 0xff) / 255f, g = ((c >>> 8) & 0xff) / 255f, b = ((c >>> 16) & 0xff) / 255f, a = ((c >>> 24) & 0xfe) / 254f;
            float rx = 0, rz = 0;
            if (k != 0) {
                float tx = vx - ax[i], tz = vz - az[i];
                float l = (float) Math.sqrt(tx * tx + tz * tz);
                if (l < 1e-4f) { tx = 1; tz = 1; l = 1.4142f; }
                rx = tz / l; rz = -tx / l;                         // screen-right as seen from the viewer
            }
            for (int t : TRI) {
                int p = o + t * 4;
                float sx = q[p], sy = q[p + 1];
                if (k == 0) {
                    vb.put(gi(sx, sy)).put(0f).put(-go(sx, sy));
                } else {
                    float h = (sx - aCx[i]) * S, y = (sy - aBase[i]) * SY;
                    vb.put(ax[i] + rx * h).put(y).put(az[i] + rz * h);
                }
                vb.put(q[p + 2]).put(q[p + 3]).put(r).put(g).put(b).put(a);
            }
            verts += 6;
        }
        if (verts > 0) flush(gl, cur, verts);
    }

    static final int[] TRI = {0, 1, 2, 0, 2, 3};

    void flush(GL20 gl, Texture t, int verts) {
        gl.glBindTexture(GL20.GL_TEXTURE_2D, t.getTextureObjectHandle());
        vb.position(0);
        gl.glEnableVertexAttribArray(aPos);
        gl.glVertexAttribPointer(aPos, 3, GL20.GL_FLOAT, false, 36, vb);
        vb.position(3);
        gl.glEnableVertexAttribArray(aUv);
        gl.glVertexAttribPointer(aUv, 2, GL20.GL_FLOAT, false, 36, vb);
        vb.position(5);
        gl.glEnableVertexAttribArray(aCol);
        gl.glVertexAttribPointer(aCol, 4, GL20.GL_FLOAT, false, 36, vb);
        gl.glDrawArrays(GL20.GL_TRIANGLES, 0, verts);
        vb.clear();
    }
}

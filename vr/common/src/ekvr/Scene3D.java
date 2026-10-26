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
    public float zoom = 4f;                      // draw distance: the map is drawn this many times the 2D view (widenView)
    public final float[] tint = {1, 1, 1, 1};
    public float heroX, heroZ;                   // hero ground position (units)
    public boolean hideHero;                     // first person: don't draw the hero (you are the hero)
    float offX, offZ;                            // hero sprite's drawn spot minus its logical point
    int heroSprite = -1;
    /** Where the hero is drawn (units): what the views follow. */
    public float dispX() { return heroX + offX; }
    public float dispZ() { return heroZ + offZ; }
    boolean ready, classified, structPrepared;
    int frame, builtFrame = -1;
    static final byte STRUCT = 6;                // a building / wall quad: drawn as solid columns (Structures)
    final Structures structs = new Structures();
    boolean[] colFade = new boolean[0];
    Object lastMap;
    final HashSet<Long> floorKeys = new HashSet<>();
    Object origBatch;
    int prog, aPos, aUv, aCol, uMvp, uCut, uTint;
    FloatBuffer vb = ByteBuffer.allocateDirect(1 << 20).order(ByteOrder.nativeOrder()).asFloatBuffer();   // grows as needed

    // ---- hooking -------------------------------------------------------------------------
    static Field field(Class<?> c, String n) throws Exception { Field f = c.getDeclaredField(n); f.setAccessible(true); return f; }
    static Field fLevelR, fMapBatch, fMapMap, fLights, fWorld, fAmbient, fViewBounds;
    boolean widened;

    /**
     * Draw distance (QUEST_VR_SPEC.md §3). EK culls the map to its renderer's view bounds, set from
     * its camera just before drawing -- and EK keeps that camera's zoom within 1..1.5 itself every
     * frame (GameLevelRenderer: zoom > 1.5 -> 1.5, then eased to 1), so zooming the camera does
     * nothing. Instead, when map drawing begins (the recorder's begin(), after setView, before
     * the culling), widen the bounds to `zoom` times the 1x view around their centre.
     */
    void widenView() {
        if (widened) return;
        widened = true;
        try {
            e.a.a.a lr = (e.a.a.a) fLevelR.get(null);
            if (lr == null || lr.l == null || lr.g == null) return;
            com.badlogic.gdx.math.q r = (com.badlogic.gdx.math.q) fViewBounds.get(lr.l);
            float f = zoom / Math.max(0.1f, lr.g.zoom);
            if (r == null || f <= 1f) return;
            float cx = r.x + r.width / 2, cy = r.y + r.height / 2;
            r.width *= f; r.height *= f;
            r.x = cx - r.width / 2; r.y = cy - r.height / 2;
        } catch (Throwable t) {
            System.out.println("[ekvr] widen view: " + t);
        }
    }

    /** Point the game's map drawing at our recorder. Call every frame before the game renders. */
    public void beginFrame() {
        rec.reset();
        frame++;
        classified = false;
        structPrepared = false;
        widened = false;
        if (rec.onBegin == null) rec.onBegin = () -> { widenView(); faceViewer(); };
        try {
            if (fLevelR == null) {
                fLevelR = field(e.a.a.a.class, "w");
                fMapBatch = field(com.badlogic.gdx.s.j.k.a.class, "c");
                fMapMap = field(com.badlogic.gdx.s.j.k.a.class, "a");
                fLights = field(e.a.a.a.class, "d");
                fWorld = field(e.a.a.a.class, "c");
                fAmbient = field(c.f.class, "e");
                fViewBounds = field(com.badlogic.gdx.s.j.k.a.class, "d");
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
            Object map = fMapMap.get(lr.l);
            if (map != lastMap) { lastMap = map; buildFloorKeys((com.badlogic.gdx.s.j.b) map); structs.build((com.badlogic.gdx.s.j.b) map); }
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

    // ---- facing (QUEST_VR_SPEC.md §3) -------------------------------------------------------
    /** Where the viewer stands (world units), from the last drawn eye; set by VrCore. */
    public float viewX, viewZ;
    public boolean haveViewer;
    final java.util.ArrayList<net.fdgames.GameEntities.MapActor> turned = new java.util.ArrayList<>();
    final java.util.ArrayList<net.fdgames.GameEntities.MapActor.Facing> was = new java.util.ArrayList<>(), shown = new java.util.ArrayList<>();

    /**
     * Map drawing starts: show each character's 8-way facing as seen from the viewer instead of
     * from the 2D camera (which looks up the screen, heading 45°). Restored in endFrame, so the
     * game's logic never sees it. Facing ordinal k = world heading 45° - 45°·k.
     */
    void faceViewer() {
        if (!haveViewer || !turned.isEmpty()) return;
        try {
            net.fdgames.GameEntities.MapActor.Facing[] f = net.fdgames.GameEntities.MapActor.Facing.values();
            java.util.ArrayList<net.fdgames.GameEntities.MapActor> list = net.fdgames.GameLevel.GameLevel.c;
            net.fdgames.GameEntities.MapActor hero = net.fdgames.GameLevel.GameLevel.h();
            int n = list == null ? 0 : list.size();
            for (int i = 0; i <= n; i++) {
                net.fdgames.GameEntities.MapActor a = i < n ? list.get(i) : hero;
                if (a == null || a.facing == null || (i == n && list != null && list.contains(hero))) continue;
                float dx = a.x / 32f - 1f + offX - viewX, dz = -(a.y / 32f - 1f) + offZ - viewZ;   // drawn spot
                if (dx * dx + dz * dz < 1e-4f) continue;
                double hv = Math.toDegrees(Math.atan2(-dx, -dz));               // heading viewer -> actor
                int steps = (int) Math.round((hv - 45) / 45.0);
                int k = ((a.facing.ordinal() + steps) % 8 + 8) % 8;
                turned.add(a); was.add(a.facing); shown.add(f[k]);
                a.facing = f[k];
            }
        } catch (Throwable t) {
            restoreFacing();
        }
    }

    void restoreFacing() {
        // only where it is still our value: if the game changed it meanwhile, the game's wins
        for (int i = 0; i < turned.size(); i++) if (turned.get(i).facing == shown.get(i)) turned.get(i).facing = was.get(i);
        shown.clear();
        turned.clear(); was.clear();
    }

    /** After the game frame: read the scene ambient from the (silenced) RayHandler. */
    public void endFrame() {
        restoreFacing();
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
            "uniform mat4 u_mvp; varying vec2 v_uv; varying vec4 v_col; varying vec2 v_w;\n" +
            "void main(){ v_uv=a_uv; v_col=a_col; v_w=a_pos.xz; gl_Position=u_mvp*vec4(a_pos,1.0); }";
    /** Fog (draw distance edge): mix toward u_fogCol from u_fog.x to u_fog.y cells from u_fogC; u_fogCol.a = 1 fades alpha too (passthrough). */
    static final String FOG = "uniform vec2 u_fogC; uniform vec2 u_fog; uniform vec4 u_fogCol;\n" +
            "vec4 fog(vec4 c, vec2 w){ float f=clamp((distance(w,u_fogC)-u_fog.x)/(u_fog.y-u_fog.x),0.0,1.0);\n" +
            "  return vec4(mix(c.rgb,u_fogCol.rgb,f*(1.0-u_fogCol.a)), c.a*(1.0-f*u_fogCol.a)); }\n";
    static final String FS = "#ifdef GL_ES\nprecision mediump float;\n#endif\n" +
            "uniform sampler2D u_tex; uniform float u_cut; uniform vec4 u_tint; varying vec2 v_uv; varying vec4 v_col; varying vec2 v_w;\n" + FOG +
            "void main(){ vec4 c=texture2D(u_tex,v_uv)*v_col; if(c.a<u_cut) discard; gl_FragColor=fog(vec4(c.rgb*u_tint.rgb,c.a),v_w); }";

    /** Fog: centre (the hero, world units), start/end distance (cells), colour (+ alpha mode); set by VrCore. */
    public final float[] fogC = new float[2], fogRange = {1e5f, 2e5f}, fogCol = {0.42f, 0.55f, 0.72f, 0f};
    int uFogC, uFog, uFogCol;

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
        uFogC = gl.glGetUniformLocation(prog, "u_fogC");
        uFog = gl.glGetUniformLocation(prog, "u_fog");
        uFogCol = gl.glGetUniformLocation(prog, "u_fogCol");
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
    final float[] fade = new float[Rec.MAX];            // alpha multiplier (occluders between viewer and hero)
    final float[] ax = new float[Rec.MAX], az = new float[Rec.MAX], aBase = new float[Rec.MAX], aCx = new float[Rec.MAX];

    /**
     * Draw the recorded map. {@code mvp} maps world units to clip space for this eye/view;
     * (vx, vz) is the viewer's ground position in world units (for billboard facing).
     * {@code world} places the map: world units -> scene (identity, tabletop scale, ...).
     */
    public void draw(float[] mvp, float vx, float vz) {
        if (!ready) init();
        GL20 gl = Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20;
        classify();
        if (!structPrepared) {
            structPrepared = true;
            try { structs.prepare(this, gl); } catch (Throwable t) { System.out.println("[ekvr] structures: " + t); structs.any = false; }
        }
        if (builtFrame != frame) {                       // once per frame, shared by both eyes
            builtFrame = frame;
            int n = rec.n;
            for (int i = 0; i < n; i++) { fade[i] = 1f; if (kind[i] == 5) kind[i] = 1; }
            fadeOccluders(vx, vz);
            fadeColumns(vx, vz);
            build(vx, vz);
        }
        drawPasses(gl, mvp);
    }

    /**
     * Sort this frame's quads into floor / upright / translucent / additive (kind 0..3; 4 =
     * hidden: the hero in first person) and give each its ground anchor. Once per game frame.
     */
    void classify() {
        if (classified) return;
        classified = true;
        int n = rec.n;
        float[] q = rec.q;
        // 0. feet per character, not per layer: EK draws a character as stacked layers (body, head,
        //    armour, weapon) on the same spot; each layer's lowest opaque row differs (the head's is
        //    the neck), so v11 lifted each layer differently and pulled them apart. Group the
        //    sprite quads that share a bottom edge and centre, and use the group's lowest row.
        footGroups.clear();
        for (int i = 0; i < n; i++) {
            if (rec.tileDraw[i] || rec.additive[i]) continue;
            int o = i * 16;
            float minX = Math.min(Math.min(q[o], q[o + 4]), Math.min(q[o + 8], q[o + 12]));
            float maxX = Math.max(Math.max(q[o], q[o + 4]), Math.max(q[o + 8], q[o + 12]));
            float minY = Math.min(Math.min(q[o + 1], q[o + 5]), Math.min(q[o + 9], q[o + 13]));
            float maxY = Math.max(Math.max(q[o + 1], q[o + 5]), Math.max(q[o + 9], q[o + 13]));
            int f = footPx(i, maxY - minY);
            long k = groupKey(minY, (minX + maxX) / 2);
            Integer old = footGroups.get(k);
            if (old == null || f < old) footGroups.put(k, f);
        }
        // 1. classify + anchors of big sprites (characters)
        int actors = 0;
        int[] actorIdx = new int[512];
        for (int i = 0; i < n; i++) {
            int o = i * 16;
            float minX = Math.min(Math.min(q[o], q[o + 4]), Math.min(q[o + 8], q[o + 12]));
            float maxX = Math.max(Math.max(q[o], q[o + 4]), Math.max(q[o + 8], q[o + 12]));
            float minY = Math.min(Math.min(q[o + 1], q[o + 5]), Math.min(q[o + 9], q[o + 13]));
            float maxY = Math.max(Math.max(q[o + 1], q[o + 5]), Math.max(q[o + 9], q[o + 13]));
            long tk = rec.tileDraw[i] ? key(rec.tex[i].getTextureObjectHandle(), q[o + 2], q[o + 3]) : 0;
            boolean floor = rec.tileDraw[i] && floorKeys.contains(tk);
            int a = (Float.floatToRawIntBits(rec.col[i]) >>> 24) & 0xff;
            kind[i] = (byte) (floor ? 0 : rec.tileDraw[i] && structs.isStructure(tk) ? STRUCT : rec.additive[i] ? 3 : a < 250 ? 2 : 1);
            aCx[i] = (minX + maxX) / 2;
            // tiles stand on their cell centre (quad bottom + 16 px); sprites on their lowest opaque row
            aBase[i] = rec.tileDraw[i] || kind[i] == 3 ? minY + 16 : minY + groupFoot(minY, aCx[i]);
            ax[i] = gi(aCx[i], aBase[i]);
            az[i] = -go(aCx[i], aBase[i]);
            fade[i] = 1f;
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
        // where the hero is drawn: EK draws a character's sprite ~1.5 cells from the point
        // (x/32 - 1, -(y/32 - 1)), so track the hero's own sprite and remember the offset
        float best = 2.5f * 2.5f; int bi = -1;
        for (int k = 0; k < actors; k++) {
            int j2 = actorIdx[k];
            float dx = ax[j2] - (heroX + offX), dz = az[j2] - (heroZ + offZ), d = dx * dx + dz * dz;
            if (d < best) { best = d; bi = j2; }
        }
        if (bi >= 0) { offX = ax[bi] - heroX; offZ = az[bi] - heroZ; heroSprite = bi; } else heroSprite = -1;
        if (hideHero)
            for (int i = 0; i < n; i++)
                if (kind[i] != 0 && !rec.tileDraw[i] && Math.abs(ax[i] - dispX()) < 0.45f && Math.abs(az[i] - dispZ()) < 0.45f)
                    kind[i] = 4;
    }

    /** V4: upright map objects standing between the viewer and the hero turn see-through (kind 5 = faded upright). */
    void fadeOccluders(float vx, float vz) {
        int n = rec.n;
        if (hideHero) {
            // first person: whatever stands right in your face (a tree you walked into) turns see-through
            for (int i = 0; i < n; i++) {
                if (kind[i] != 1 && kind[i] != 2) continue;
                float px = ax[i] - vx, pz = az[i] - vz;
                if (px * px + pz * pz < 1.6f * 1.6f) { kind[i] = 5; fade[i] = 0.3f; }
            }
            return;
        }
        float sx = dispX() - vx, sz = dispZ() - vz, sl = (float) Math.sqrt(sx * sx + sz * sz);
        if (sl > 1e-3f) {
            sx /= sl; sz /= sl;
            for (int i = 0; i < n; i++) {
                if (kind[i] != 1 || !rec.tileDraw[i]) continue;          // map objects only, never characters
                float px = ax[i] - vx, pz = az[i] - vz;
                float along = px * sx + pz * sz, side = Math.abs(px * sz - pz * sx);
                if (along > 0.3f && along < sl - 0.4f && side < 1.6f) { kind[i] = 5; fade[i] = 0.3f; }
            }
        }
    }

    /** V4 for buildings: columns standing between the viewer and the hero draw faded. */
    void fadeColumns(float vx, float vz) {
        int nc = structs.ncols;
        if (colFade.length < nc) colFade = new boolean[nc * 2];
        float sx = dispX() - vx, sz = dispZ() - vz, sl = (float) Math.sqrt(sx * sx + sz * sz);
        for (int i = 0; i < nc; i++) {
            colFade[i] = false;
            if (hideHero || sl < 1e-3f) continue;
            float px = structs.colMid[i * 2] - vx, pz = structs.colMid[i * 2 + 1] - vz;
            float along = (px * sx + pz * sz) / sl, side = Math.abs(px * sz - pz * sx) / sl;
            colFade[i] = along > 0.3f && along < sl - 0.4f && side < 1.6f;
        }
    }

    final java.util.HashMap<Long, Integer> feet = new java.util.HashMap<>();
    final java.util.HashMap<Long, Integer> footGroups = new java.util.HashMap<>();

    /** Layers of one character: same bottom edge (to 2 px) and centre (to 8 px). */
    static long groupKey(float minY, float cx) { return ((long) Math.round(minY / 2f) << 32) ^ (Math.round(cx / 8f) & 0xffffffffL); }

    int groupFoot(float minY, float cx) {
        Integer f = footGroups.get(groupKey(minY, cx));
        return f == null ? 0 : f;
    }
    java.nio.ByteBuffer footBuf;

    /**
     * Height (iso px above the quad's bottom) of a sprite's lowest opaque pixel row: where its feet
     * are. Read once per sprite frame from the texture (QUEST_VR_SPEC.md §3).
     */
    int footPx(int i, float quadH) {
        int o = i * 16;
        float[] q = rec.q;
        com.badlogic.gdx.graphics.Texture t = rec.tex[i];
        float u1 = Math.min(q[o + 2], q[o + 10]), u2 = Math.max(q[o + 2], q[o + 10]);
        float vb = q[o + 3], vt = q[o + 7];                      // bottom-left corner's v, top-left's v
        long k = key(t.getTextureObjectHandle(), u1, vb) * 31 + Math.round(vt * 65536) * 7 + Math.round(u2 * 65536);
        Integer cached = feet.get(k);
        if (cached == null) {
            int tw = t.getWidth(), th = t.getHeight();
            int x = Math.max(0, (int) (u1 * tw)), w = Math.max(1, Math.min(tw - x, Math.round((u2 - u1) * tw)));
            int r0 = Math.max(0, (int) (Math.min(vb, vt) * th)), h = Math.max(1, Math.min(th - r0, Math.round(Math.abs(vb - vt) * th)));
            int fromBottom = 0;
            try {
                GL20 gl = Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20;
                if (footBuf == null || footBuf.capacity() < w * h * 4) footBuf = ByteBuffer.allocateDirect(Math.max(w * h * 4, 65536)).order(ByteOrder.nativeOrder());
                if (pickFbo == 0) { java.nio.IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer(); gl.glGenFramebuffers(1, ib); pickFbo = ib.get(0); }
                java.nio.IntBuffer prev = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
                gl.glGetIntegerv(GL20.GL_FRAMEBUFFER_BINDING, prev);
                gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, pickFbo);
                gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, t.getTextureObjectHandle(), 0);
                if (gl.glCheckFramebufferStatus(GL20.GL_FRAMEBUFFER) == GL20.GL_FRAMEBUFFER_COMPLETE) {
                    footBuf.clear();
                    gl.glReadPixels(x, r0, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, footBuf);
                    // texture row r0 = v min; the quad's bottom is at v = vb
                    boolean bottomIsHigh = vb > vt;
                    search:
                    for (int s = 0; s < h; s++) {
                        int row = bottomIsHigh ? h - 1 - s : s;
                        for (int c = 0; c < w; c++)
                            if ((footBuf.get((row * w + c) * 4 + 3) & 0xff) > 40) { fromBottom = s; break search; }
                    }
                    fromBottom = Math.round(fromBottom * quadH / h);
                }
                gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, 0, 0);
                gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, prev.get(0));
            } catch (Throwable ignored) {}
            cached = fromBottom;
            feet.put(k, cached);
        }
        return cached;
    }

    void drawPasses(GL20 gl, float[] mvp) {
        UiLayer.clientArrays(gl);
        gl.glUseProgram(prog);
        gl.glUniformMatrix4fv(uMvp, 1, false, mvp, 0);
        gl.glUniform4f(uTint, tint[0], tint[1], tint[2], 1);
        fogUniforms(gl, uFogC, uFog, uFogCol);
        gl.glEnable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_BLEND);
        gl.glDisable(GL20.GL_CULL_FACE);
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        useMain(gl);
        // pass 1: floor, painter's order, writes depth
        gl.glDepthFunc(GL20.GL_ALWAYS);
        gl.glDepthMask(true);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        pass(gl, 0, 0.02f);
        gl.glDepthFunc(GL20.GL_LEQUAL);
        // buildings and walls: solid columns textured with their own art (Structures)
        structs.draw(gl, mvp, this, false, colFade);
        useMain(gl);
        // pass 2: upright opaque cut-outs
        pass(gl, 1, 0.5f);
        // pass 3: translucent (fading roofs, ghosts, corpses), then additive particles
        gl.glDepthMask(false);
        pass(gl, 2, 0.02f);
        pass(gl, 5, 0.02f);
        structs.draw(gl, mvp, this, true, colFade);                   // columns hiding the hero, faded
        useMain(gl);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        pass(gl, 3, 0.01f);
        gl.glDepthMask(true);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    void fogUniforms(GL20 gl, int c, int r, int col) {
        gl.glUniform2f(c, fogC[0], fogC[1]);
        gl.glUniform2f(r, fogRange[0], fogRange[1]);
        gl.glUniform4f(col, fogCol[0], fogCol[1], fogCol[2], fogCol[3]);
    }

    /** Back to the main program with this frame's vertex arrays bound. */
    void useMain(GL20 gl) {
        UiLayer.clientArrays(gl);
        gl.glUseProgram(prog);
        vb.position(0);
        gl.glEnableVertexAttribArray(aPos);
        gl.glVertexAttribPointer(aPos, 3, GL20.GL_FLOAT, false, 36, vb);
        vb.position(3);
        gl.glEnableVertexAttribArray(aUv);
        gl.glVertexAttribPointer(aUv, 2, GL20.GL_FLOAT, false, 36, vb);
        vb.position(5);
        gl.glEnableVertexAttribArray(aCol);
        gl.glVertexAttribPointer(aCol, 4, GL20.GL_FLOAT, false, 36, vb);
        vb.position(0);
    }

    // this frame's geometry: one vertex array, batches by texture, grouped by kind
    static final int[] ORDER = {0, 1, 2, 5, 3};
    float[] stage = new float[0];
    Texture[] bTex = new Texture[256];
    int[] bStart = new int[256], bCount = new int[256];
    final int[] kFirst = new int[8], kEnd = new int[8];
    int nb;

    /** Build every quad's 3D vertices once per frame (billboards face the viewer at (vx, vz)). */
    public long buildNs;                         // (tests) last build() time

    void build(float vx, float vz) {
        long t0 = System.nanoTime();
        int n = rec.n;
        float[] q = rec.q;
        if (stage.length < n * 6 * 9) stage = new float[Math.max(n * 6 * 9, 1 << 16)];
        int w = 0, verts = 0;
        nb = 0;
        for (int k : ORDER) {
            kFirst[k] = nb;
            Texture cur = null;
            for (int i = 0; i < n; i++) {
                if (kind[i] != k) continue;
                if (rec.tex[i] != cur || nb == kFirst[k]) {
                    if (nb == bTex.length) {
                        bTex = java.util.Arrays.copyOf(bTex, nb * 2);
                        bStart = java.util.Arrays.copyOf(bStart, nb * 2);
                        bCount = java.util.Arrays.copyOf(bCount, nb * 2);
                    }
                    cur = rec.tex[i];
                    bTex[nb] = cur; bStart[nb] = verts; bCount[nb] = 0; nb++;
                }
                int o = i * 16;
                int c = Float.floatToRawIntBits(rec.col[i]);
                float r = (c & 0xff) / 255f, g = ((c >>> 8) & 0xff) / 255f, b = ((c >>> 16) & 0xff) / 255f, a = ((c >>> 24) & 0xfe) / 254f * fade[i];
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
                        stage[w++] = gi(sx, sy); stage[w++] = 0f; stage[w++] = -go(sx, sy);
                    } else {
                        float h = (sx - aCx[i]) * S, y = (sy - aBase[i]) * SY;
                        stage[w++] = ax[i] + rx * h; stage[w++] = y; stage[w++] = az[i] + rz * h;
                    }
                    stage[w++] = q[p + 2]; stage[w++] = q[p + 3];
                    stage[w++] = r; stage[w++] = g; stage[w++] = b; stage[w++] = a;
                }
                verts += 6;
                bCount[nb - 1] += 6;
            }
            kEnd[k] = nb;
        }
        if (vb.capacity() < w) vb = ByteBuffer.allocateDirect(w * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vb.clear();
        vb.put(stage, 0, w);
        vb.position(0);
        buildNs = System.nanoTime() - t0;
    }

    void pass(GL20 gl, int k, float cut) {
        gl.glUniform1f(uCut, cut);
        for (int b = kFirst[k]; b < kEnd[k]; b++) {
            if (bCount[b] == 0) continue;
            gl.glBindTexture(GL20.GL_TEXTURE_2D, bTex[b].getTextureObjectHandle());
            gl.glDrawArrays(GL20.GL_TRIANGLES, bStart[b], bCount[b]);
        }
    }

    static final int[] TRI = {0, 1, 2, 0, 2, 3};

    // ---- picking ---------------------------------------------------------------------------
    /**
     * The nearest upright thing the ray (world units: origin o, direction d) hits before
     * {@code tMax}, seen from the viewer at (vx, vz): {t, anchorX, anchorZ}, or null. A hit
     * counts only where the sprite's texel is not transparent (read back from the GPU), so
     * the empty parts of a tree's rectangle don't swallow clicks meant for the ground.
     */
    public float[] pick(float ox, float oy, float oz, float dx, float dy, float dz, float tMax, float vx, float vz) {
        classify();
        int n = rec.n;
        float[] q = rec.q;
        int[] cand = new int[8];
        float[] ct = new float[8];
        float[] cu = new float[8], cv = new float[8];
        int nc = 0;
        for (int i = 0; i < n; i++) {
            int k = kind[i];
            if (k != 1 && k != 2 && k != 5) continue;
            float tx = vx - ax[i], tz = vz - az[i];
            float l = (float) Math.sqrt(tx * tx + tz * tz);
            if (l < 1e-4f) continue;
            float rx = tz / l, rz = -tx / l;                    // billboard right; normal = (tx, tz)/l
            float den = (dx * tx + dz * tz) / l;
            if (Math.abs(den) < 1e-6f) continue;
            float t = ((ax[i] - ox) * tx + (az[i] - oz) * tz) / l / den;
            if (t <= 0 || t >= tMax) continue;
            float hx = ox + dx * t - ax[i], hy = oy + dy * t, hz = oz + dz * t - az[i];
            float sx = aCx[i] + (hx * rx + hz * rz) / S, sy = aBase[i] + hy / SY;   // back to iso screen px
            int o = i * 16;
            float x0 = q[o], y0 = q[o + 1], x2 = q[o + 8], y2 = q[o + 9];
            if (x2 == x0 || y2 == y0) continue;
            float fu = (sx - x0) / (x2 - x0), fv = (sy - y0) / (y2 - y0);
            if (fu < 0 || fu > 1 || fv < 0 || fv > 1) continue;
            // keep the 8 nearest, sorted
            int p = nc < 8 ? nc++ : 7;
            if (p == 7 && nc == 8 && t >= ct[7]) continue;
            while (p > 0 && ct[p - 1] > t) { cand[p] = cand[p - 1]; ct[p] = ct[p - 1]; cu[p] = cu[p - 1]; cv[p] = cv[p - 1]; p--; }
            cand[p] = i; ct[p] = t;
            cu[p] = q[o + 2] + fu * (q[o + 10] - q[o + 2]);
            cv[p] = q[o + 3] + fv * (q[o + 11] - q[o + 3]);
        }
        for (int c = 0; c < nc; c++) {
            int i = cand[c];
            if (alphaAt(rec.tex[i], cu[c], cv[c]) > 40) return new float[]{ct[c], ax[i], az[i]};
        }
        return null;
    }

    int pickFbo;
    final ByteBuffer px1 = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());

    /** Alpha (0..255) of a texture at (u, v): attach it to a scratch framebuffer and read one texel. */
    int alphaAt(Texture t, float u, float v) {
        GL20 gl = Gdx.gl20 instanceof GlTap ? ((GlTap) Gdx.gl20).gl : Gdx.gl20;
        if (pickFbo == 0) {
            java.nio.IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
            gl.glGenFramebuffers(1, ib);
            pickFbo = ib.get(0);
        }
        java.nio.IntBuffer prev = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        gl.glGetIntegerv(GL20.GL_FRAMEBUFFER_BINDING, prev);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, pickFbo);
        gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, t.getTextureObjectHandle(), 0);
        int a = 255;                                        // can't read it back: count it as solid
        if (gl.glCheckFramebufferStatus(GL20.GL_FRAMEBUFFER) == GL20.GL_FRAMEBUFFER_COMPLETE) {
            int x = Math.max(0, Math.min(t.getWidth() - 1, (int) (u * t.getWidth())));
            int y = Math.max(0, Math.min(t.getHeight() - 1, (int) (v * t.getHeight())));
            px1.clear();
            gl.glReadPixels(x, y, 1, 1, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, px1);
            a = px1.get(3) & 0xff;
        }
        gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, 0, 0);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, prev.get(0));
        return a;
    }

}

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
    public float viewEyeX, viewEyeY, viewEyeZ;  // this eye in world units (Structures: which side of a face it sees)
    public float zoom = 4f;                      // draw distance: the map is drawn this many times the 2D view (widenView)
    public final float[] tint = {1, 1, 1, 1};
    public float heroX, heroZ;                   // hero ground position (units)
    public boolean hideHero;                     // first person: don't draw the hero (you are the hero)
    /** (tests) distance from the hero's position to the nearest character sprite's ground point. */
    public float nearestSprite() {
        float best = Float.MAX_VALUE;
        for (int i = 0; i < rec.n; i++) {
            if (rec.tileDraw[i] || kind[i] == 0 || kind[i] == 3) continue;
            int o = i * 16;
            float h = Math.abs(rec.q[o + 5] - rec.q[o + 1]);
            if (h < 60) continue;
            float dx = ax[i] - heroX, dz = az[i] - heroZ;
            best = Math.min(best, (float) Math.sqrt(dx * dx + dz * dz));
        }
        return best;
    }

    /** Where the hero is drawn (units): what the views follow. */
    public float dispX() { return heroX; }
    public float dispZ() { return heroZ; }
    boolean ready, classified, structPrepared;
    int frame, builtFrame = -1;
    static final byte STRUCT = 6;                // a building / wall quad: drawn as solid columns (Structures)
    final Structures structs = new Structures();
    boolean[] colFade = new boolean[0], colHide = new boolean[0];
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

    static Field fBuckets, fRebuilt;
    float lastRebuild = Float.NaN;
    final java.util.Set<Object> nearSet = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());
    final java.util.ArrayList<Object> farLists = new java.util.ArrayList<>();
    public final java.util.ArrayList<Object> farSprites = new java.util.ArrayList<>();

    /**
     * APPROX (DEOBFUSCATION_STATUS V9). Characters at the draw distance (QUEST_VR_SPEC.md §3, v16). EK's map renderer draws a sprite
     * only from its 96x96 bucket grid `o`, which it fills with the sprites within 288 px (448) of
     * the hero. Each frame: take back what we added last frame, then add every sprite of
     * GameLevel.d not in EK's near list but within 14 x zoom cells, with EK's own bucket formula.
     */
    @SuppressWarnings("unchecked")
    void addFarSprites() {
        try {
            e.a.a.a lr = (e.a.a.a) fLevelR.get(null);
            if (lr == null || lr.l == null) return;
            if (fBuckets == null) { fBuckets = field(e.a.c.a.class, "o"); fRebuilt = field(e.a.c.a.class, "p"); }
            // when EK rebuilt its grid since last frame our additions are already gone (and a
            // sprite now near may be back in the same bucket as EK's own entry: don't remove it)
            float rebuilt = fRebuilt.getFloat(lr.l);
            boolean same = rebuilt == lastRebuild;
            lastRebuild = rebuilt;
            for (int i = 0; same && i < farLists.size(); i++) {
                java.util.ArrayList<Object> l = (java.util.ArrayList<Object>) farLists.get(i);
                Object sp = farSprites.get(i);
                for (int j = l.size() - 1; j >= 0; j--) if (l.get(j) == sp) { l.remove(j); break; }
            }
            farLists.clear(); farSprites.clear();
            net.fdgames.GameEntities.Final.Player p = net.fdgames.GameLevel.GameLevel.h();
            java.util.ArrayList<net.fdgames.GameEntities.MapSprite> all = net.fdgames.GameLevel.GameLevel.d;
            if (p == null || all == null) return;
            java.util.ArrayList<Object>[][] o = (java.util.ArrayList<Object>[][]) fBuckets.get(lr.l);
            if (o == null) return;
            // EK's own: its near list as of its last rebuild (not the box around the hero now,
            // which would double or drop sprites the hero passed since)
            nearSet.clear();
            if (lr.l.n != null) nearSet.addAll(lr.l.n);
            int far = (int) (14 * zoom * 32);
            for (int i = 0, n = all.size(); i < n; i++) {
                net.fdgames.GameEntities.MapSprite sp = all.get(i);
                if (sp == null) continue;
                int dx = Math.abs(sp.x - p.x), dy = Math.abs(sp.y - p.y);
                if (dx >= far || dy >= far || nearSet.contains(sp)) continue;
                int bx = Math.max(0, Math.min(95, (sp.x + 8) / 32)), by = Math.max(0, Math.min(95, (sp.y - 24) / 32));
                o[bx][by].add(sp);
                farLists.add(o[bx][by]); farSprites.add(sp);
            }
        } catch (Throwable t) {
            System.out.println("[ekvr] far sprites: " + t);
        }
    }

    /** (tests) the upright map-object quads within r cells of the hero: iso rect and anchor. */
    public String propDump(float r) {
        classify();
        StringBuilder sb = new StringBuilder();
        float[] q = rec.q;
        for (int i = 0; i < rec.n; i++) {
            if (kind[i] == 0 || Math.abs(ax[i] - heroX) > r || Math.abs(az[i] - heroZ) > r) continue;
            int o = i * 16;
            float minX = Math.min(Math.min(q[o], q[o + 4]), Math.min(q[o + 8], q[o + 12])), maxX = Math.max(Math.max(q[o], q[o + 4]), Math.max(q[o + 8], q[o + 12]));
            float minY = Math.min(Math.min(q[o + 1], q[o + 5]), Math.min(q[o + 9], q[o + 13])), maxY = Math.max(Math.max(q[o + 1], q[o + 5]), Math.max(q[o + 9], q[o + 13]));
            sb.append(String.format("%d k%d tile=%b x %.0f..%.0f y %.0f..%.0f anchor %.2f,%.2f foot %.0f tex %d grp %d sides %b %b%n", i, kind[i], rec.tileDraw[i], minX, maxX, minY, maxY, ax[i], az[i], aFoot[i] - minY, rec.tex[i].getTextureObjectHandle(), gFind(i), rec.tileDraw[i] && sideOpaque(i, false), rec.tileDraw[i] && sideOpaque(i, true))).append(rec.tileDraw[i] ? String.format(" uv %.4f,%.4f-%.4f,%.4f%n", q[o + 2], q[o + 3], q[o + 10], q[o + 7]) : "");
        }
        return sb.toString();
    }

    /** (tests) the structure regions around the hero. */
    public String structDump(int rad) {
        return "viewer " + viewX + "," + viewZ + " cell " + Math.round(viewX) + "," + (Math.round(-viewZ) - 1) + " region " + structs.regionOf(Math.round(viewX), Math.round(-viewZ) - 1) + "\n"
                + structs.dump(Math.round(heroX), Math.round(-heroZ) - 1, rad);
    }

    /** Point the game's map drawing at our recorder. Call every frame before the game renders. */
    public void beginFrame() {
        rec.reset();
        frame++;
        classified = false;
        structPrepared = false;
        widened = false;
        if (rec.onBegin == null) rec.onBegin = () -> { widenView(); addFarSprites(); faceViewer(); };
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
            if (map != lastMap) {
                lastMap = map; buildFloorKeys((com.badlogic.gdx.s.j.b) map); structs.build((com.badlogic.gdx.s.j.b) map);
                mapTex.clear();
                for (com.badlogic.gdx.s.j.g ts : ((com.badlogic.gdx.s.j.b) map).c())
                    for (com.badlogic.gdx.s.j.e t : ts)
                        if (t != null && t.b() != null) mapTex.add(t.b().getTexture().getTextureObjectHandle());
            }
            net.fdgames.GameEntities.Final.Player p = net.fdgames.GameLevel.GameLevel.h();
            // the hero's cell: EK draws a character at (x/32, -y/32) (v15: was x/32 - 1, one cell off,
            // which v6-v14 compensated by searching for the hero's sprite each frame -- and that search
            // could latch onto a nearby companion / monster / loot and stop following the hero)
            if (p != null) { heroX = p.x / 32f; heroZ = -(p.y / 32f); }
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
                float dx = a.x / 32f - viewX, dz = -(a.y / 32f) - viewZ;   // where it is drawn
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
    final float[] aFoot = new float[Rec.MAX];           // iso y standing on the ground (the picture's baseline)

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
        // a "tile" is a quad drawn through the vertex-array path with a map tileset's texture; text
        // (BitmapFont) takes the same path, so v1-v17 took name/title glyphs for map tiles: they
        // stood on the ground as props instead of riding over the character (owner's screenshot)
        for (int i = 0; i < n; i++)
            if (rec.tileDraw[i] && !mapTex.isEmpty() && !mapTex.contains(rec.tex[i].getTextureObjectHandle())) rec.tileDraw[i] = false;
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
        // 0b. text runs: a name / title is drawn one glyph quad per letter; v1-v17 attached each
        //     glyph to a character only within 56 px of its centre, so the outer letters of a long
        //     name stayed behind on the ground (owner: "names are showing up in the ground").
        //     Consecutive small quads of one texture on one line form a run, tested by its centre.
        if (runCx.length < n) { runCx = new float[n * 2]; runBot = new float[n * 2]; runN = new int[n * 2]; tScale = new float[n * 2]; }
        java.util.Arrays.fill(tScale, 0, n, 1f);
        for (int i = 0, start = 0; i <= n; i++) {
            boolean joins = false;
            if (i < n && i > start) {
                int o = i * 16, p = (i - 1) * 16;
                joins = !rec.tileDraw[i] && !rec.tileDraw[i - 1] && rec.tex[i] == rec.tex[i - 1]
                        && Math.abs(Math.min(q[o + 1], q[o + 5]) - Math.min(q[p + 1], q[p + 5])) <= 3
                        && Math.abs(q[o + 5] - q[o + 1]) < 40
                        && Math.min(q[o], q[o + 4]) - Math.max(q[p + 8], q[p + 12]) < 24
                        && Math.min(q[o], q[o + 4]) >= Math.min(q[p], q[p + 4]);
            }
            if (!joins) {
                if (i > start) {
                    float lo = Float.MAX_VALUE, hi = -Float.MAX_VALUE;
                    for (int j = start; j < i; j++) { int o = j * 16; lo = Math.min(lo, Math.min(q[o], q[o + 4])); hi = Math.max(hi, Math.max(q[o + 8], q[o + 12])); }
                    float bot = Float.MAX_VALUE;
                    for (int j = start; j < i; j++) { int o = j * 16; bot = Math.min(bot, Math.min(q[o + 1], q[o + 5])); }
                    for (int j = start; j < i; j++) { runCx[j] = (lo + hi) / 2; runBot[j] = bot; runN[j] = i - start; }
                }
                start = i;
            }
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
            kind[i] = (byte) (floor ? 0 : rec.tileDraw[i] && structs.isStructureAt(tk, rec.tex[i].getTextureObjectHandle(), Math.round(gi((minX + maxX) / 2, minY + 16)), Math.round(go((minX + maxX) / 2, minY + 16)) - 1) ? STRUCT : rec.additive[i] ? 3 : a < 250 ? 2 : 1);
            aCx[i] = (minX + maxX) / 2;
            // tiles stand on their cell centre (quad bottom + 16 px); sprites on their lowest opaque row
            aBase[i] = rec.tileDraw[i] || kind[i] == 3 ? minY + 16 : minY + groupFoot(minY, aCx[i]);
            ax[i] = gi(aCx[i], aBase[i]);
            az[i] = -go(aCx[i], aBase[i]);
            // the picture's baseline: its lowest opaque row stands on the ground. Map props (crates,
            // barrels, trees, rocks) keep their cell (aBase = cell centre) but used to stand on it
            // at quad bottom + 16 px, sinking up to ~0.4 m (owner: crates not sitting on the ground)
            aFoot[i] = rec.tileDraw[i] && !floor && kind[i] != STRUCT ? minY + footPx(i, maxY - minY) : aBase[i];
            fade[i] = 1f;
            if (!floor && !rec.tileDraw[i] && maxY - minY >= 60 && actors < actorIdx.length) actorIdx[actors++] = i;
            // small quads just above a character (health bars, names, status icons) ride on it
            if (!floor && !rec.tileDraw[i] && maxY - minY < 40) {
                for (int k = actors - 1; k >= 0; k--) {
                    int j = actorIdx[k];
                    if (Math.abs(runCx[i] - aCx[j]) < 56 && minY >= aBase[j] - 30 && minY < aBase[j] + 170) {
                        ax[i] = ax[j]; az[i] = az[j]; aCx[i] = aCx[j]; aBase[i] = aBase[j]; aFoot[i] = aFoot[j];
                        // a name / title over a character: EK's font is sized for the 2D screen and
                        // came out ~1 m tall over the head (owner: "Brother Janus" across the view);
                        // shrink the run to 40% about its own centre and baseline
                        if (runN[i] >= 2) tScale[i] = 0.4f;
                        break;
                    }
                }
            }
        }
        groupProps(n, q);
        if (hideHero)
            for (int i = 0; i < n; i++)
                if (kind[i] != 0 && !rec.tileDraw[i] && Math.abs(ax[i] - dispX()) < 0.45f && Math.abs(az[i] - dispZ()) < 0.45f)
                    kind[i] = 4;
    }

    int[] gPar = new int[0], gNext = new int[0], gCount = new int[0];
    float[] gMinX = new float[0], gMaxX = new float[0], tMinX = new float[0], tMaxX = new float[0];  // group / tile extents
    final java.util.HashMap<Long, Integer> gCell = new java.util.HashMap<>();

    int gFind(int i) { while (gPar[i] != i) { gPar[i] = gPar[gPar[i]]; i = gPar[i]; } return i; }

    /**
     * APPROX (DEOBFUSCATION_STATUS V10). One billboard per multi-tile map object (QUEST_VR_SPEC.md §3, v17). EK draws a weapon rack,
     * a market stall or a fence as several tiles on neighbouring cells (e.g. three 64x128 tiles,
     * 32 px apart); v1-v16 turned each tile toward the viewer about its own cell, so the pieces
     * came apart at different angles (owner: "props and stalls are crooked"). Tiles of one
     * tileset on neighbouring cells whose pictures touch (opaque in a common column of their
     * overlap) are one object: they share an anchor (the members' mean cell) and baseline, so the
     * picture stays whole and turns as one. Trees and barrels have transparent margins and stay
     * single. A group is at most 4 tiles of 64 px, 5 cells wide (v18: big cliff / tree tiles
     * grouped and floated).
     */
    void groupProps(int n, float[] q) {
        if (gPar.length < n) {
            int m = n * 2;
            gPar = new int[m]; gNext = new int[m]; gCount = new int[m]; gMinX = new float[m]; gMaxX = new float[m]; tMinX = new float[m]; tMaxX = new float[m];
        }
        if (inGroup.length < n) inGroup = new boolean[n * 2];
        java.util.Arrays.fill(inGroup, false);
        gCell.clear();
        propGroups = 0;
        for (int i = 0; i < n; i++) {
            gPar[i] = i; gCount[i] = 1; gNext[i] = -1;
            if (!rec.tileDraw[i] || (kind[i] != 1 && kind[i] != 2)) continue;
            int o = i * 16;
            gMinX[i] = tMinX[i] = Math.min(Math.min(q[o], q[o + 4]), Math.min(q[o + 8], q[o + 12]));
            gMaxX[i] = tMaxX[i] = Math.max(Math.max(q[o], q[o + 4]), Math.max(q[o + 8], q[o + 12]));
            long c = ((long) Math.round(ax[i]) << 32) ^ (Math.round(az[i]) & 0xffffffffL);
            Integer head = gCell.get(c);
            gNext[i] = head == null ? -1 : head;
            gCell.put(c, i);
        }
        boolean any = false;
        for (int i = 0; i < n; i++) {
            if (!rec.tileDraw[i] || (kind[i] != 1 && kind[i] != 2)) continue;
            int o = i * 16;
            float minY = Math.min(Math.min(q[o + 1], q[o + 5]), Math.min(q[o + 9], q[o + 13]));
            float maxY = Math.max(Math.max(q[o + 1], q[o + 5]), Math.max(q[o + 9], q[o + 13]));
            int cx = Math.round(ax[i]), cz = Math.round(az[i]);
            for (int dx = -1; dx <= 1; dx++)
                for (int dz = -1; dz <= 1; dz++) {
                    Integer h = gCell.get(((long) (cx + dx) << 32) ^ ((cz + dz) & 0xffffffffL));
                    for (int j = h == null ? -1 : h; j >= 0; j = gNext[j]) {
                        if (j <= i || rec.tex[j] != rec.tex[i]) continue;
                        int p = j * 16;
                        float jMinY = Math.min(Math.min(q[p + 1], q[p + 5]), Math.min(q[p + 9], q[p + 13]));
                        float jMaxY = Math.max(Math.max(q[p + 1], q[p + 5]), Math.max(q[p + 9], q[p + 13]));
                        if (jMinY >= maxY || minY >= jMaxY) continue;
                        // object tiles only (64 px wide): cliffs and trees (128 px) grouped in v17 and
                        // their back pieces floated above the front one's baseline (owner: trees odd
                        // near the mine)
                        if (tMaxX[i] - tMinX[i] > 66 || tMaxX[j] - tMinX[j] > 66) continue;
                        int l = tMinX[i] <= tMinX[j] ? i : j, r = l == i ? j : i;
                        if (tMinX[r] == tMinX[l] || tMinX[r] > tMaxX[l] + 1) continue;   // side by side, touching or overlapping
                        int a = gFind(i), b = gFind(j);
                        if (a == b) continue;
                        if (Math.max(gMaxX[a], gMaxX[b]) - Math.min(gMinX[a], gMinX[b]) > 160 || gCount[a] + gCount[b] > 4) continue;
                        // the pictures touch: some column of the overlap is opaque in both (v18; the
                        // edge test missed a stall's two tiles)
                        int d = Math.round(tMinX[r] - tMinX[l]);
                        // (within 2 px: a stall's middle tile starts exactly where the end tile's art stops)
                        long rm = colMask(r), rd = rm | (rm << 1) | (rm << 2) | (rm >>> 1) | (rm >>> 2);
                        long lm = colMask(l), ld = lm | (lm << 1) | (lm << 2);
                        boolean touch = d < 64 ? ((ld >>> d) & rd) != 0
                                : d <= 65 && (colMask(l) >>> 63) != 0 && (colMask(r) & 1) != 0;   // edge to edge
                        if (debugGroups && false) System.out.println("[grp] " + (int) tMinX[l] + "," + (int) q[l * 16 + 1] + "~" + (int) tMinX[r] + "," + (int) q[r * 16 + 1] + " d=" + d + " l=" + Long.toHexString(colMask(l)) + " r=" + Long.toHexString(colMask(r)) + " touch=" + touch);
                        if (!touch) continue;
                        gPar[b] = a; gCount[a] += gCount[b];
                        gMinX[a] = Math.min(gMinX[a], gMinX[b]); gMaxX[a] = Math.max(gMaxX[a], gMaxX[b]);
                        any = true;
                    }
                }
        }
        if (!any) return;
        // group anchor = mean of the members' cells, baseline = the lowest member's
        java.util.HashMap<Integer, float[]> acc = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!rec.tileDraw[i] || (kind[i] != 1 && kind[i] != 2)) continue;
            int g = gFind(i);
            if (gCount[g] < 2) continue;
            float[] v = acc.get(g);
            if (v == null) acc.put(g, v = new float[]{0, 0, 0, Float.MAX_VALUE});
            v[0] += ax[i]; v[1] += az[i]; v[2]++; v[3] = Math.min(v[3], aFoot[i]);
        }
        for (int i = 0; i < n; i++) {
            if (!rec.tileDraw[i] || (kind[i] != 1 && kind[i] != 2)) continue;
            float[] v = acc.get(gFind(i));
            if (v == null) continue;
            ax[i] = v[0] / v[2]; az[i] = v[1] / v[2];
            inGroup[i] = true;
            aCx[i] = 32f * (ax[i] - az[i]);
            aFoot[i] = v[3];
        }
        propGroups = acc.size();
    }

    public int propGroups;
    boolean[] inGroup = new boolean[0];
    public static boolean debugGroups = System.getProperty("ekvr.grp") != null;

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
        if (colHide.length < nc) colHide = new boolean[nc * 2];
        // the whole building the viewer stands in (or is within 2 cells of) isn't drawn: the third-person camera
        // behind the hero ends up inside a house when the hero walks along it, and the house's
        // walls and roof showed as slabs across the view (owner's v17 screenshots)
        java.util.HashSet<Integer> inside = null;
        int vc = Math.round(vx), vr = Math.round(-vz) - 1;
        for (int dx = -2; dx <= 2; dx++)
            for (int dz = -2; dz <= 2; dz++) {
                int id = structs.regionOf(vc + dx, vr + dz);
                if (id >= 0) { if (inside == null) inside = new java.util.HashSet<>(); inside.add(id); }
            }
        for (int i = 0; i < nc; i++) {
            colFade[i] = false;
            float px = structs.colMid[i * 2] - vx, pz = structs.colMid[i * 2 + 1] - vz;
            // a building block you are (almost) standing in isn't drawn: from inside, its walls and
            // roof cut across the whole view (third person in a tight village; owner's screenshot)
            // (only at street level: from above, e.g. the tabletop, nothing is in the way)
            colHide[i] = viewEyeY < structs.colTop[i] + 0.5f
                    && (px * px + pz * pz < 1.8f * 1.8f || (inside != null && inside.contains(structs.colRegion[i])));
            if (hideHero || sl < 1e-3f) continue;
            float along = (px * sx + pz * sz) / sl, side = Math.abs(px * sz - pz * sx) / sl;
            // only where it really blocks the eye's line to the hero's chest (1 unit up): v16 faded
            // every block near the line on the ground, so from above (tabletop) or over a low wall
            // whole houses turned see-through (owner: "the buildings you can see through")
            float lineY = viewEyeY + (1f - viewEyeY) * along / sl;
            // ... and only the part just in front of the hero (v18: whole roofs turned see-through)
            colFade[i] = along > Math.max(0.3f, sl - 3f) && along < sl - 0.4f && side < 0.7f && lineY < structs.colTop[i];
        }
    }

    final java.util.HashMap<Long, Integer> feet = new java.util.HashMap<>();
    final java.util.HashMap<Long, Integer> footGroups = new java.util.HashMap<>();
    float[] runCx = new float[0], runBot = new float[0], tScale = new float[0];
    int[] runN = new int[0];
    final java.util.HashSet<Integer> mapTex = new java.util.HashSet<>();

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
    int footPx(int i, float quadH) { return opaqueRow(i, quadH, false); }

    /** Height (iso px above the quad's bottom) of a quad's highest opaque pixel row (Structures: art top). */
    int topPx(int i, float quadH) { return Math.round(quadH) - opaqueRow(i, quadH, true); }

    /** Rows (iso px) from the quad's bottom (top: from its top) to its first opaque row; cached per frame. */
    int opaqueRow(int i, float quadH, boolean top) {
        int o = i * 16;
        float[] q = rec.q;
        com.badlogic.gdx.graphics.Texture t = rec.tex[i];
        float u1 = Math.min(q[o + 2], q[o + 10]), u2 = Math.max(q[o + 2], q[o + 10]);
        float vb = q[o + 3], vt = q[o + 7];                      // bottom-left corner's v, top-left's v
        long k = key(t.getTextureObjectHandle(), u1, vb) * 31 + Math.round(vt * 65536) * 7 + Math.round(u2 * 65536) + (top ? 0x5bd1e995L : 0);
        Integer cached = feet.get(k);
        if (cached == null) {
            int tw = t.getWidth(), th = t.getHeight();
            int x = Math.max(0, (int) (u1 * tw)), w = Math.max(1, Math.min(tw - x, Math.round((u2 - u1) * tw)));
            int r0 = Math.max(0, (int) (Math.min(vb, vt) * th)), h = Math.max(1, Math.min(th - r0, Math.round(Math.abs(vb - vt) * th)));
            int fromBottom = 0;
            if (readTex(t, x, r0, w, h)) {
                // texture row r0 = v min; the quad's bottom is at v = vb
                boolean bottomIsHigh = (vb > vt) != top;
                search:
                for (int s = 0; s < h; s++) {
                    int row = bottomIsHigh ? h - 1 - s : s;
                    for (int c = 0; c < w; c++)
                        if ((footBuf.get((row * w + c) * 4 + 3) & 0xff) > 40) { fromBottom = s; break search; }
                }
                fromBottom = Math.round(fromBottom * quadH / h);
            }
            cached = fromBottom;
            feet.put(k, cached);
        }
        return cached;
    }

    /** Read texels (x, r0, w, h) of a texture into footBuf (RGBA rows from r0 up); false if unreadable. */
    boolean readTex(com.badlogic.gdx.graphics.Texture t, int x, int r0, int w, int h) {
        boolean ok = false;
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
                ok = true;
            }
            gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, 0, 0);
            gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, prev.get(0));
        } catch (Throwable ignored) {}
        return ok;
    }

    final java.util.HashMap<Long, Boolean> sides = new java.util.HashMap<>();
    final java.util.HashMap<Long, Long> masks = new java.util.HashMap<>();

    /** Bit c set: column c (from the quad's left, in iso px, up to 64) has an opaque texel. Cached. */
    long colMask(int i) {
        int o = i * 16;
        float[] q = rec.q;
        com.badlogic.gdx.graphics.Texture t = rec.tex[i];
        float uL = q[o + 2], uR = q[o + 10], vb = q[o + 3], vt = q[o + 7];
        long k = key(t.getTextureObjectHandle(), uL, vb) * 31 + Math.round(vt * 65536) * 7 + Math.round(uR * 65536) * 3;
        Long cached = masks.get(k);
        if (cached == null) {
            int tw = t.getWidth(), th = t.getHeight();
            int x = Math.max(0, Math.round(Math.min(uL, uR) * tw)), w = Math.max(1, Math.min(tw - x, Math.round(Math.abs(uR - uL) * tw)));
            int r0 = Math.max(0, (int) (Math.min(vb, vt) * th)), h = Math.max(1, Math.min(th - r0, Math.round(Math.abs(vb - vt) * th)));
            long m = 0;
            if (readTex(t, x, r0, w, h))
                for (int row = 0; row < h; row++)
                    for (int c = 0; c < w; c++)
                        if ((footBuf.get((row * w + c) * 4 + 3) & 0xff) > 40) {
                            int col = (int) ((uR >= uL ? c : w - 1 - c) * 64L / w);
                            m |= 1L << Math.min(63, col);
                        }
            cached = m;
            masks.put(k, cached);
        }
        return cached;
    }

    /**
     * Does the quad's picture reach its left / right edge (opaque texels in the outer 2 columns)?
     * Pieces of one multi-tile map object continue across their tiles' edges; a tree or a barrel
     * has a transparent margin. Cached per tile frame.
     */
    boolean sideOpaque(int i, boolean right) {
        int o = i * 16;
        float[] q = rec.q;
        com.badlogic.gdx.graphics.Texture t = rec.tex[i];
        float uL = q[o + 2], uR = q[o + 10], vb = q[o + 3], vt = q[o + 7];   // corners: 0 BL, 1 TL, 2 TR, 3 BR
        long k = key(t.getTextureObjectHandle(), uL, vb) * 31 + Math.round(vt * 65536) * 7 + Math.round(uR * 65536) * 3 + (right ? 1 : 2);
        Boolean cached = sides.get(k);
        if (cached == null) {
            int tw = t.getWidth(), th = t.getHeight();
            float us = right ? uR : uL, uo = right ? uL : uR;
            int xs = Math.round(us * tw), x = uo > us ? xs : xs - 2;           // 2 columns inward from that edge
            x = Math.max(0, Math.min(tw - 2, x));
            int r0 = Math.max(0, (int) (Math.min(vb, vt) * th)), h = Math.max(1, Math.min(th - r0, Math.round(Math.abs(vb - vt) * th)));
            boolean hit = false;
            if (readTex(t, x, r0, 2, h))
                for (int j = 0; j < 2 * h && !hit; j++) hit = (footBuf.get(j * 4 + 3) & 0xff) > 40;
            cached = hit;
            sides.put(k, cached);
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
        drawGround(gl);
        useMain(gl);
        pass(gl, 0, 0.02f);
        gl.glDepthFunc(GL20.GL_LEQUAL);
        // buildings and walls: solid columns textured with their own art (Structures)
        structs.draw(gl, mvp, this, false, colFade, colHide);
        useMain(gl);
        // pass 2: upright opaque cut-outs
        if (System.getProperty("ekvr.noprops") == null) pass(gl, 1, 0.5f);
        // pass 3: translucent (fading roofs, ghosts, corpses), then additive particles
        gl.glDepthMask(false);
        pass(gl, 2, 0.02f);
        pass(gl, 5, 0.02f);
        structs.draw(gl, mvp, this, true, colFade, colHide);                   // columns hiding the hero, faded
        useMain(gl);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        pass(gl, 3, 0.01f);
        gl.glDepthMask(true);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    int whiteTex;
    final FloatBuffer groundVb = ByteBuffer.allocateDirect(6 * 9 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

    /**
     * Earth under the whole map and 40 cells around it (v18): beyond the map's edge (the black void
     * of the 2D game) there was only sky, so edge cliffs and trees stood over nothing (owner: the
     * trees and cliffs by the mine look odd). Drawn first; the floor tiles cover it.
     */
    void drawGround(GL20 gl) {
        if (whiteTex == 0) {
            java.nio.IntBuffer ib = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
            gl.glGenTextures(1, ib); whiteTex = ib.get(0);
            gl.glBindTexture(GL20.GL_TEXTURE_2D, whiteTex);
            ByteBuffer px = ByteBuffer.allocateDirect(4); px.put((byte) -1).put((byte) -1).put((byte) -1).put((byte) -1).flip();
            gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, 1, 1, 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, px);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_NEAREST);
            gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_NEAREST);
        }
        float w = structs.mw > 0 ? structs.mw : 96, h = structs.mh > 0 ? structs.mh : 96, m = 40;
        float x0 = -m, x1 = w + m, z0 = m, z1 = -(h + m), y = -0.02f, r = 0.30f, g = 0.25f, b = 0.19f;
        float[][] c = {{x0, z0}, {x1, z0}, {x1, z1}, {x0, z0}, {x1, z1}, {x0, z1}};
        groundVb.clear();
        for (float[] v : c) groundVb.put(v[0]).put(y).put(v[1]).put(0.5f).put(0.5f).put(r).put(g).put(b).put(1f);
        groundVb.position(0);
        gl.glVertexAttribPointer(aPos, 3, GL20.GL_FLOAT, false, 36, groundVb);
        groundVb.position(3);
        gl.glVertexAttribPointer(aUv, 2, GL20.GL_FLOAT, false, 36, groundVb);
        groundVb.position(5);
        gl.glVertexAttribPointer(aCol, 4, GL20.GL_FLOAT, false, 36, groundVb);
        groundVb.position(0);
        gl.glUniform1f(uCut, 0.02f);
        gl.glBindTexture(GL20.GL_TEXTURE_2D, whiteTex);
        gl.glDrawArrays(GL20.GL_TRIANGLES, 0, 6);
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
                    if (i < inGroup.length && inGroup[i]) {
                        // a multi-tile object (stall, rack) keeps its 2D picture plane (facing +X+Z,
                        // the 2D view), turning at most 30 deg toward the viewer: turned fully it
                        // swung into the house wall behind it (owner: stalls still messed up, v19)
                        float a0 = (float) Math.atan2(tz, tx), base = (float) Math.atan2(1, 1);
                        float da = (float) Math.IEEEremainder(a0 - base, 2 * Math.PI);
                        if (Math.abs(da) > Math.PI / 2) { base += (float) Math.PI; da = (float) Math.IEEEremainder(a0 - base, 2 * Math.PI); }
                        float lim = (float) Math.toRadians(30);
                        float ang = base + Math.max(-lim, Math.min(lim, da));
                        tx = (float) Math.cos(ang); tz = (float) Math.sin(ang); l = 1f;
                    }
                    rx = tz / l; rz = -tx / l;                         // screen-right as seen from the viewer
                }
                for (int t : TRI) {
                    int p = o + t * 4;
                    float sx = q[p], sy = q[p + 1];
                    if (k == 0) {
                        stage[w++] = gi(sx, sy); stage[w++] = 0f; stage[w++] = -go(sx, sy);
                    } else {
                        if (tScale[i] != 1f) { sx = runCx[i] + (sx - runCx[i]) * tScale[i]; sy = runBot[i] + (sy - runBot[i]) * tScale[i]; }
                        float h = (sx - aCx[i]) * S, y = (sy - aFoot[i]) * SY;
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
            float sx = aCx[i] + (hx * rx + hz * rz) / S, sy = aFoot[i] + hy / SY;   // back to iso screen px
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

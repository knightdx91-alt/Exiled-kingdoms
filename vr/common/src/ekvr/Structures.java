package ekvr;

import com.badlogic.gdx.graphics.GL20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * Buildings and walls as solid blocks (deobf/QUEST_VR_SPEC.md §3, V2). EK cuts a building image
 * into 64×128 strips on the TMX objects layer; billboarded strip by strip it fell apart. Instead:
 *  1. every quad of a structure tileset (by its `imagesource`) is drawn, in the game's order,
 *     into an offscreen image in iso screen space -- the exact 2D picture of the buildings;
 *  2. the TMX `nonwalk` cells form regions; each region touched by a structure quad becomes a
 *     set of 1×1-cell columns, as tall as the tallest structure art anchored in it;
 *  3. the columns are textured by projecting that image with EK's own projection
 *     (X, Y, Z) -> iso (32(X - Z), 16(-Z - X) + Y/SY), alpha-tested.
 * From the 2D view direction this is the art exactly; from anywhere else the walls and roofs are
 * surfaces with real depth. (Backs of buildings repeat the front art.)
 */
class Structures {
    static final Pattern NAME = Pattern.compile(
            "house|townhall|manor|tower|farmhouse|windmill|wall|palisade|gate|ruins|tomb|icemist_large|"
                    + "medieval_building|adt_tiled_dungeon|cave", Pattern.CASE_INSENSITIVE);

    final HashSet<Long> keys = new HashSet<>();
    int mw, mh;                       // nonwalk layer size (cells)
    int[] region;                     // cell -> region id, -1 = walkable
    final ArrayList<int[]> regionCells = new ArrayList<>();   // region id -> packed cells (col | row << 16)

    /** Per map: structure tile keys and nonwalk regions. */
    void build(com.badlogic.gdx.s.j.b map) {
        keys.clear(); regionCells.clear(); region = null; mw = mh = 0;
        if (map == null) return;
        int sets = 0;
        try {
            for (com.badlogic.gdx.s.j.g ts : map.c()) {
                Object src = ts.a() == null ? null : ts.a().b("imagesource");
                if (src == null || !NAME.matcher(String.valueOf(src)).find()) continue;
                sets++;
                for (com.badlogic.gdx.s.j.e t : ts) {
                    com.badlogic.gdx.graphics.g2d.TextureRegion r = t == null ? null : t.b();
                    if (r != null) keys.add(Scene3D.key(r.getTexture().getTextureObjectHandle(), r.getU(), r.getV2()));
                }
            }
        } catch (Throwable t) {
            System.out.println("[ekvr] structure tilesets: " + t);
        }
        try {
            com.badlogic.gdx.s.d l = map.a().a("nonwalk");
            if (l instanceof com.badlogic.gdx.s.j.f) {
                com.badlogic.gdx.s.j.f f = (com.badlogic.gdx.s.j.f) l;
                // width/height: private fields f, g (the public g()/h() are the TILE size, 64x32)
                mw = Scene3D.field(com.badlogic.gdx.s.j.f.class, "f").getInt(f);
                mh = Scene3D.field(com.badlogic.gdx.s.j.f.class, "g").getInt(f);
                boolean[] b = new boolean[mw * mh];
                for (int x = 0; x < mw; x++)
                    for (int y = 0; y < mh; y++) {
                        com.badlogic.gdx.s.j.f.a c = f.a(x, y);
                        b[x + y * mw] = c != null && c.a() != null;
                    }
                region = new int[mw * mh];
                java.util.Arrays.fill(region, -1);
                int[] stack = new int[mw * mh];
                for (int i = 0; i < b.length; i++) {
                    if (!b[i] || region[i] >= 0) continue;
                    int id = regionCells.size(), sp = 0, n = 0;
                    int[] cells = new int[16];
                    stack[sp++] = i; region[i] = id;
                    while (sp > 0) {
                        int c = stack[--sp], cx = c % mw, cy = c / mw;
                        if (n == cells.length) cells = java.util.Arrays.copyOf(cells, n * 2);
                        cells[n++] = cx | (cy << 16);
                        int[] nb = {cx > 0 ? c - 1 : -1, cx < mw - 1 ? c + 1 : -1, cy > 0 ? c - mw : -1, cy < mh - 1 ? c + mw : -1};
                        for (int k : nb) if (k >= 0 && b[k] && region[k] < 0) { region[k] = id; stack[sp++] = k; }
                    }
                    regionCells.add(java.util.Arrays.copyOf(cells, n));
                }
            }
        } catch (Throwable t) {
            System.out.println("[ekvr] nonwalk layer: " + t);
        }
        System.out.println("[ekvr] structures: " + sets + " tilesets, " + keys.size() + " tiles, nonwalk "
                + mw + "x" + mh + ", " + regionCells.size() + " regions");
    }

    boolean isStructure(long key) { return keys.contains(key); }

    // ---- per frame -------------------------------------------------------------------------
    int fbo, tex, texW, texH;
    float x0, y0, bw, bh;             // iso bounds of the image
    int imgW, imgH;
    boolean any;
    FloatBuffer cols = ByteBuffer.allocateDirect(4 * 3 * 6 * 5 * 8192).order(ByteOrder.nativeOrder()).asFloatBuffer();
    int colVerts;
    float[] colMid = new float[0];    // per column: x, z (centre) for fading
    int[] colStart = new int[0], colCount = new int[0];
    int ncols;
    final IntBuffer ib = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    final FloatBuffer quad = ByteBuffer.allocateDirect(Rec.MAX * 6 * 8 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    int imgProg, iPos, iUv, iCol, iMvp;
    int colProg, cPos, cMvp, cIso, cSy, cTint, cAlpha;

    /** Build this frame's structure image and columns from the recorded quads of kind STRUCT. */
    void prepare(Scene3D sc, GL20 gl) {
        Rec rec = sc.rec;
        int n = rec.n;
        float[] q = rec.q;
        // 1. bounds + per-region heights
        float minx = Float.MAX_VALUE, miny = Float.MAX_VALUE, maxx = -Float.MAX_VALUE, maxy = -Float.MAX_VALUE;
        HashMap<Integer, Float> heights = new HashMap<>();
        any = false;
        for (int i = 0; i < n; i++) {
            if (sc.kind[i] != Scene3D.STRUCT) continue;
            any = true;
            int o = i * 16;
            float qmaxY = -Float.MAX_VALUE;
            for (int c = 0; c < 4; c++) {
                float x = q[o + c * 4], y = q[o + c * 4 + 1];
                minx = Math.min(minx, x); maxx = Math.max(maxx, x);
                miny = Math.min(miny, y); maxy = Math.max(maxy, y);
                qmaxY = Math.max(qmaxY, y);
            }
            float h = (qmaxY - sc.aBase[i]) * Scene3D.SY;
            int col = Math.round(sc.ax[i]), row = Math.round(-sc.az[i]) - 1;
            int reg = regionOf(col, row);
            int id = reg >= 0 ? reg : -1 - (col | (row << 16));        // no nonwalk: the anchor cell alone
            Float old = heights.get(id);
            if (old == null || h > old) heights.put(id, h);
        }
        if (!any) return;
        x0 = minx - 2; y0 = miny - 2; bw = maxx - minx + 4; bh = maxy - miny + 4;
        float scale = Math.min(1f, 4096f / Math.max(bw, bh));
        imgW = Math.max(1, (int) Math.ceil(bw * scale)); imgH = Math.max(1, (int) Math.ceil(bh * scale));
        // 2. the image
        ib.clear(); gl.glGetIntegerv(GL20.GL_FRAMEBUFFER_BINDING, ib); int prevFbo = ib.get(0);
        ib.clear(); gl.glGetIntegerv(GL20.GL_VIEWPORT, ib); int vx = ib.get(0), vy = ib.get(1), vw = ib.get(2), vh = ib.get(3);
        ensureTarget(gl, imgW, imgH);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo);
        gl.glViewport(0, 0, imgW, imgH);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glDisable(GL20.GL_DEPTH_TEST);
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        if (imgProg == 0) initImg(gl);
        UiLayer.clientArrays(gl);
        gl.glUseProgram(imgProg);
        float[] ortho = Mat.id();                                        // iso bounds -> clip
        ortho[0] = 2f / bw; ortho[5] = 2f / bh;
        ortho[12] = -1 - 2f * x0 / bw; ortho[13] = -1 - 2f * y0 / bh;
        gl.glUniformMatrix4fv(iMvp, 1, false, ortho, 0);
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        com.badlogic.gdx.graphics.Texture cur = null;
        quad.clear();
        int verts = 0;
        for (int i = 0; i < n; i++) {
            if (sc.kind[i] != Scene3D.STRUCT) continue;
            if (rec.tex[i] != cur && verts > 0) { flushImg(gl, cur, verts); verts = 0; }
            cur = rec.tex[i];
            int o = i * 16, c = Float.floatToRawIntBits(rec.col[i]);
            float r = (c & 0xff) / 255f, g = ((c >>> 8) & 0xff) / 255f, b = ((c >>> 16) & 0xff) / 255f, a = ((c >>> 24) & 0xfe) / 254f;
            for (int t : Scene3D.TRI) {
                int p = o + t * 4;
                quad.put(q[p]).put(q[p + 1]).put(q[p + 2]).put(q[p + 3]).put(r).put(g).put(b).put(a);
            }
            verts += 6;
        }
        if (verts > 0) flushImg(gl, cur, verts);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, prevFbo);
        gl.glViewport(vx, vy, vw, vh);
        // 3. columns
        cols.clear(); colVerts = 0; ncols = 0;
        for (java.util.Map.Entry<Integer, Float> e : heights.entrySet()) {
            int id = e.getKey();
            float h = Math.max(0.3f, e.getValue());
            if (id >= 0) {
                for (int cell : regionCells.get(id)) {
                    int col = cell & 0xffff, row = cell >>> 16;
                    if (!nearImage(col, row)) continue;
                    column(col, row, h, id);
                }
            } else {
                int cell = -1 - id;
                column(cell & 0xffff, cell >>> 16, h, Integer.MIN_VALUE);
            }
        }
    }

    int regionOf(int col, int row) {
        if (region == null || col < 0 || row < 0 || col >= mw || row >= mh) return -1;
        return region[col + row * mw];
    }

    /** Is this cell's column inside (or near) the structure image? (big nonwalk regions: water, map edges) */
    boolean nearImage(int col, int row) {
        float cx = 32f * (col + row + 1), cy = 16f * (row + 1 - col);     // iso of the cell centre
        return cx > x0 - 64 && cx < x0 + bw + 64 && cy > y0 - 64 && cy < y0 + bh + 64;
    }

    void column(int col, int row, float h, int id) {
        if (colVerts + 30 > cols.capacity() / 3) return;
        // cell (col, row): X in [col-0.5, col+0.5], Z in [-(row+1.5), -(row+0.5)]
        float xa = col - 0.5f, xb = col + 0.5f, za = -(row + 1.5f), zb = -(row + 0.5f);
        if (ncols == colStart.length) {
            colStart = java.util.Arrays.copyOf(colStart, ncols * 2 + 64);
            colCount = java.util.Arrays.copyOf(colCount, ncols * 2 + 64);
            colMid = java.util.Arrays.copyOf(colMid, (ncols * 2 + 64) * 2);
        }
        colStart[ncols] = colVerts; colMid[ncols * 2] = col; colMid[ncols * 2 + 1] = -(row + 1);
        int before = colVerts;
        boolean same = id != Integer.MIN_VALUE;
        // sides (skip a side shared with a neighbour cell of the same region)
        if (!(same && regionOf(col - 1, row) == id)) face(xa, za, xa, zb, h);
        if (!(same && regionOf(col + 1, row) == id)) face(xb, zb, xb, za, h);
        if (!(same && regionOf(col, row + 1) == id)) face(xb, za, xa, za, h);   // row+1 = more negative Z
        if (!(same && regionOf(col, row - 1) == id)) face(xa, zb, xb, zb, h);
        // top
        put(xa, h, za); put(xb, h, za); put(xb, h, zb);
        put(xa, h, za); put(xb, h, zb); put(xa, h, zb);
        colCount[ncols] = colVerts - before;
        ncols++;
    }

    void face(float x1, float z1, float x2, float z2, float h) {
        put(x1, 0, z1); put(x2, 0, z2); put(x2, h, z2);
        put(x1, 0, z1); put(x2, h, z2); put(x1, h, z1);
    }

    void put(float x, float y, float z) { cols.put(x).put(y).put(z); colVerts++; }

    /** Draw the columns (opaque pass, or the faded ones between the viewer and the hero). */
    void draw(GL20 gl, float[] mvp, float[] tint, boolean faded, boolean[] fade) {
        if (!any || colVerts == 0) return;
        if (colProg == 0) initCols(gl);
        UiLayer.clientArrays(gl);
        gl.glUseProgram(colProg);
        gl.glUniformMatrix4fv(cMvp, 1, false, mvp, 0);
        gl.glUniform4f(cIso, x0, y0, bw, bh);
        gl.glUniform1f(cSy, Scene3D.SY);
        gl.glUniform4f(cTint, tint[0], tint[1], tint[2], 1);
        gl.glUniform1f(cAlpha, faded ? 0.35f : 1f);
        setSub(gl);
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        cols.position(0);
        gl.glEnableVertexAttribArray(cPos);
        gl.glVertexAttribPointer(cPos, 3, GL20.GL_FLOAT, false, 12, cols);
        for (int i = 0; i < ncols; i++) {
            boolean f = fade != null && i < fade.length && fade[i];
            if (f != faded) continue;
            gl.glDrawArrays(GL20.GL_TRIANGLES, colStart[i], colCount[i]);
        }
    }

    void ensureTarget(GL20 gl, int w, int h) {
        if (fbo != 0 && w <= texW && h <= texH) return;
        texW = Math.max(w, texW); texH = Math.max(h, texH);
        if (tex == 0) { ib.clear(); gl.glGenTextures(1, ib); tex = ib.get(0); }
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, texW, texH, 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, null);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_LINEAR);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_LINEAR);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE);
        if (fbo == 0) { ib.clear(); gl.glGenFramebuffers(1, ib); fbo = ib.get(0); }
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, fbo);
        gl.glFramebufferTexture2D(GL20.GL_FRAMEBUFFER, GL20.GL_COLOR_ATTACHMENT0, GL20.GL_TEXTURE_2D, tex, 0);
    }

    void flushImg(GL20 gl, com.badlogic.gdx.graphics.Texture t, int verts) {
        gl.glBindTexture(GL20.GL_TEXTURE_2D, t.getTextureObjectHandle());
        quad.position(0);
        gl.glEnableVertexAttribArray(iPos);
        gl.glVertexAttribPointer(iPos, 2, GL20.GL_FLOAT, false, 32, quad);
        quad.position(2);
        gl.glEnableVertexAttribArray(iUv);
        gl.glVertexAttribPointer(iUv, 2, GL20.GL_FLOAT, false, 32, quad);
        quad.position(4);
        gl.glEnableVertexAttribArray(iCol);
        gl.glVertexAttribPointer(iCol, 4, GL20.GL_FLOAT, false, 32, quad);
        gl.glDrawArrays(GL20.GL_TRIANGLES, 0, verts);
        quad.clear();
    }

    // The image is drawn at the image's own scale: the ortho maps the iso bounds onto the viewport
    // (imgW x imgH) of a texture that may be bigger (texW x texH), so the columns sample the
    // sub-rectangle [0, imgW/texW] x [0, imgH/texH].
    void initImg(GL20 gl) {
        int vs = Scene3D.shader(gl, GL20.GL_VERTEX_SHADER,
                "attribute vec2 a_pos; attribute vec2 a_uv; attribute vec4 a_col; uniform mat4 u_mvp;\n" +
                "varying vec2 v_uv; varying vec4 v_col;\n" +
                "void main(){ v_uv=a_uv; v_col=a_col; gl_Position=u_mvp*vec4(a_pos,0.0,1.0); }");
        int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision mediump float;\n#endif\n" +
                "uniform sampler2D u_tex; varying vec2 v_uv; varying vec4 v_col;\n" +
                "void main(){ gl_FragColor=texture2D(u_tex,v_uv)*v_col; }");
        imgProg = link(gl, vs, fs);
        iPos = gl.glGetAttribLocation(imgProg, "a_pos");
        iUv = gl.glGetAttribLocation(imgProg, "a_uv");
        iCol = gl.glGetAttribLocation(imgProg, "a_col");
        iMvp = gl.glGetUniformLocation(imgProg, "u_mvp");
    }

    void initCols(GL20 gl) {
        int vs = Scene3D.shader(gl, GL20.GL_VERTEX_SHADER,
                "attribute vec3 a_pos; uniform mat4 u_mvp; uniform vec4 u_iso; uniform float u_sy; uniform vec2 u_sub;\n" +
                "varying vec2 v_uv;\n" +
                "void main(){\n" +
                "  float sx = 32.0*(a_pos.x - a_pos.z);\n" +
                "  float sy = 16.0*(-a_pos.z - a_pos.x) + a_pos.y / u_sy;\n" +
                "  v_uv = vec2((sx - u_iso.x) / u_iso.z, (sy - u_iso.y) / u_iso.w) * u_sub;\n" +
                "  gl_Position = u_mvp*vec4(a_pos,1.0); }");
        int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision highp float;\n#endif\n" +
                "uniform sampler2D u_tex; uniform vec4 u_tint; uniform float u_alpha; varying vec2 v_uv;\n" +
                "void main(){ vec4 c=texture2D(u_tex,v_uv); if(c.a<0.5) discard;\n" +
                "  gl_FragColor=vec4(c.rgb/c.a*u_tint.rgb, u_alpha); }");
        colProg = link(gl, vs, fs);
        cPos = gl.glGetAttribLocation(colProg, "a_pos");
        cMvp = gl.glGetUniformLocation(colProg, "u_mvp");
        cIso = gl.glGetUniformLocation(colProg, "u_iso");
        cSy = gl.glGetUniformLocation(colProg, "u_sy");
        cTint = gl.glGetUniformLocation(colProg, "u_tint");
        cAlpha = gl.glGetUniformLocation(colProg, "u_alpha");
        cSub = gl.glGetUniformLocation(colProg, "u_sub");
    }

    int cSub;

    void setSub(GL20 gl) { gl.glUniform2f(cSub, imgW / (float) texW, imgH / (float) texH); }

    static int link(GL20 gl, int vs, int fs) {
        int p = gl.glCreateProgram();
        gl.glAttachShader(p, vs); gl.glAttachShader(p, fs); gl.glLinkProgram(p);
        return p;
    }
}

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
    int[] edgeDist, regionMaxDist;    // cell -> distance from its region's edge (1 = edge); region -> max
    final ArrayList<int[]> regionCells = new ArrayList<>();   // region id -> packed cells (col | row << 16)

    /** Per map: structure tile keys and nonwalk regions. */
    void build(com.badlogic.gdx.s.j.b map) {
        keys.clear(); regionCells.clear(); regionTex.clear(); texCat.clear(); region = null; propCell = null; propCell2 = null; mw = mh = 0;
        if (map == null) return;
        int sets = 0;
        try {
            for (com.badlogic.gdx.s.j.g ts : map.c()) {
                Object src = ts.a() == null ? null : ts.a().b("imagesource");
                if (src == null || !NAME.matcher(String.valueOf(src)).find()) continue;
                sets++;
                String name = String.valueOf(src).toLowerCase();
                int cat = name.matches(".*(wall|palisade|gate).*") ? 1 : name.matches(".*(adt_tiled_dungeon|tomb|ruins|cave).*") ? 2 : 3;
                for (com.badlogic.gdx.s.j.e t : ts) {
                    com.badlogic.gdx.graphics.g2d.TextureRegion r = t == null ? null : t.b();
                    if (r != null) { keys.add(Scene3D.key(r.getTexture().getTextureObjectHandle(), r.getU(), r.getV2())); texCat.put(r.getTexture().getTextureObjectHandle(), cat); }
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
                // only blocked cells under / beside the structure art itself (v18): the low town wall
                // joined the blocked forest edge into one region, which was raised as blank walls in
                // front of the trees while the wall's own art went elsewhere (owner: "the wall
                // around the town is completely gone")
                boolean[] art = new boolean[mw * mh];
                java.util.ArrayList<Long> artTex = new java.util.ArrayList<>();
                for (com.badlogic.gdx.s.d any : map.a()) {
                    if (!(any instanceof com.badlogic.gdx.s.j.f) || any == l) continue;
                    com.badlogic.gdx.s.j.f tl = (com.badlogic.gdx.s.j.f) any;
                    for (int x = 0; x < mw; x++)
                        for (int y = 0; y < mh; y++) {
                            com.badlogic.gdx.s.j.f.a c = tl.a(x, y);
                            com.badlogic.gdx.graphics.g2d.TextureRegion r = c == null || c.a() == null ? null : c.a().b();
                            if (r != null && keys.contains(Scene3D.key(r.getTexture().getTextureObjectHandle(), r.getU(), r.getV2()))) {
                                art[x + y * mw] = true;
                                // a structure tile that blocks by its own property (the low town wall's
                                // tiles are "blocked"; the nonwalk layer leaves them out) is footprint too:
                                // v17 found no region for them and dropped the wall's art
                                com.badlogic.gdx.s.h props = c.a().c();
                                if (props != null && (props.a("blocked") || props.a("obstacle"))) b[x + y * mw] = true;
                                artTex.add(((long) (x + y * mw) << 32) | (r.getTexture().getTextureObjectHandle() & 0xffffffffL));
                            }
                        }
                }
                // a lone piece from a structure tileset (an altar, a statue from the dungeon set: under
                // 5 cells of art, 8-connected) is a prop, drawn upright like one (v18; owner: "the altar
                // ... should be a separate prop")
                int[] cellCat = new int[mw * mh];
                for (long e : artTex) { int c = (int) (e >>> 32); Integer k = texCat.get((int) e); if (cellCat[c] == 0 && k != null) cellCat[c] = k; }
                artDbg = art.clone(); nonwalkDbg = b.clone();
                // dungeon-set art on its own grid: a cell can hold a dungeon piece and a building tile
                // on different layers (Lannegar shrine / town hall at 91,80); by the first tile alone
                // the shrine piece counted as building and was dropped (v18 owner: altar missing)
                boolean[] art2 = new boolean[mw * mh];
                for (long e : artTex) { Integer k = texCat.get((int) e); if (k != null && k == 2) art2[(int) (e >>> 32)] = true; }
                propCell2 = new boolean[mw * mh];
                {
                    int[] seen = new int[mw * mh], st2 = new int[mw * mh], mem = new int[mw * mh];
                    for (int i = 0; i < art2.length; i++) {
                        if (!art2[i] || seen[i] != 0) continue;
                        int sp = 0, cnt = 0;
                        st2[sp++] = i; seen[i] = 1;
                        while (sp > 0) {
                            int c = st2[--sp], cx = c % mw, cy = c / mw;
                            mem[cnt++] = c;
                            for (int dx = -1; dx <= 1; dx++)
                                for (int dy = -1; dy <= 1; dy++) {
                                    int nx = cx + dx, ny = cy + dy;
                                    if (nx < 0 || ny < 0 || nx >= mw || ny >= mh) continue;
                                    int k = nx + ny * mw;
                                    if (art2[k] && seen[k] == 0) { seen[k] = 1; st2[sp++] = k; }
                                }
                        }
                        if (cnt < 24) for (int j = 0; j < cnt; j++) propCell2[mem[j]] = true;
                    }
                }
                propCell = new boolean[mw * mh];
                int[] comp = new int[mw * mh], st = new int[mw * mh];
                java.util.Arrays.fill(comp, -1);
                for (int i = 0; i < art.length; i++) {
                    if (!art[i] || comp[i] >= 0) continue;
                    int sp = 0, cnt = 0, head = 0;
                    int[] members = new int[mw * mh > 4096 ? 4096 : mw * mh];
                    st[sp++] = i; comp[i] = i;
                    while (sp > 0) {
                        int c = st[--sp], cx = c % mw, cy = c / mw;
                        if (cnt < members.length) members[cnt] = c;
                        cnt++;
                        for (int dx = -1; dx <= 1; dx++)
                            for (int dy = -1; dy <= 1; dy++) {
                                int nx = cx + dx, ny = cy + dy;
                                if (nx < 0 || ny < 0 || nx >= mw || ny >= mh) continue;
                                int k = nx + ny * mw;
                                if (art[k] && comp[k] < 0 && cellCat[k] == cellCat[i]) { comp[k] = i; st[sp++] = k; }   // one kind of structure
                            }
                    }
                    // dungeon / ruin pieces outdoors (the Lannegar shrine: 14 tiles of the dungeon set,
                    // low blocks, braziers, a lectern) are props too; a dungeon's walls run to hundreds
                    // only for dungeon / ruin pieces: a building's or wall's art tiles are often stored
                    // a few cells off its footprint in the layers, in sparse diagonal runs that read as
                    // lone pieces (v18 first cut turned house tiles into flat cut-outs)
                    int limit = 0;                                              // dungeon pieces: propCell2
                    if (cnt < limit) for (int j = 0; j < cnt; j++) { propCell[members[j]] = true; art[members[j]] = false; }
                }
                // reach: a building's art tiles stand along its front edges and roof, so its inner
                // footprint can be 3-4 cells from any of them (v18 with reach 1 broke houses into
                // fragments); walls and dungeon pieces stand on their own cells
                for (int i = 0; i < art.length; i++) if (propCell2[i] && cellCat[i] == 2) art[i] = false;
                boolean[] near = new boolean[mw * mh];
                for (int i = 0; i < art.length; i++) {
                    if (!art[i]) continue;
                    int x = i % mw, y = i / mw, reach = cellCat[i] == 3 ? 4 : 1;
                    for (int dx = -reach; dx <= reach; dx++)
                        for (int dy = -reach; dy <= reach; dy++) {
                            int nx = x + dx, ny = y + dy;
                            if (nx >= 0 && ny >= 0 && nx < mw && ny < mh) near[nx + ny * mw] = true;
                        }
                }
                for (int i = 0; i < b.length; i++) b[i] &= near[i];
                // regions don't cross kinds of structure: a blocked cell takes the kind (wall /
                // dungeon-ruin / building, from the tileset's image name) of the art on it, else on a
                // neighbour (v18: the shrine, the east town wall and a house were one region, so the
                // shrine stood as tall as the house; per tileset split one house in four)
                int[] own = new int[mw * mh], label = new int[mw * mh];
                for (long e : artTex) {
                    int c = (int) (e >>> 32);
                    Integer cat = texCat.get((int) e);
                    if (own[c] == 0 && cat != null && !(cat == 2 && propCell2[c])) own[c] = cat;   // 1 walls, 2 dungeon/ruins, 3 buildings
                }
                for (int i = 0; i < b.length; i++) {
                    if (!b[i]) continue;
                    label[i] = own[i];
                    int x = i % mw, y = i / mw;
                    for (int rr = 1; rr <= 4 && label[i] == 0; rr++)
                    for (int dx = -rr; dx <= rr && label[i] == 0; dx++)
                        for (int dy = -rr; dy <= rr && label[i] == 0; dy++) {
                            int nx = x + dx, ny = y + dy;
                            if (nx >= 0 && ny >= 0 && nx < mw && ny < mh) label[i] = own[nx + ny * mw];
                        }
                }
                // buildings: one region per building tileset, by the majority of building art within 3
                // cells (v18: a house beside the town hall was one region and took its 6.9 m height;
                // the cell's own tile alone flip-flopped between a house's wall and roof sets)
                int[] tex3 = new int[mw * mh];
                for (long e : artTex) {
                    int c = (int) (e >>> 32);
                    Integer cat = texCat.get((int) e);
                    if (tex3[c] == 0 && cat != null && cat == 3) tex3[c] = (int) e;
                }
                java.util.HashMap<Integer, Integer> votes = new java.util.HashMap<>();
                for (int i = 0; i < b.length; i++) {
                    if (!b[i] || label[i] != 3) continue;
                    votes.clear();
                    int x = i % mw, y = i / mw, best = 0, bestN = 0;
                    for (int dx = -3; dx <= 3; dx++)
                        for (int dy = -3; dy <= 3; dy++) {
                            int nx = x + dx, ny = y + dy;
                            if (nx < 0 || ny < 0 || nx >= mw || ny >= mh || tex3[nx + ny * mw] == 0) continue;
                            int t = tex3[nx + ny * mw], v = votes.merge(t, 1, Integer::sum);
                            if (v > bestN) { bestN = v; best = t; }
                        }
                    if (best != 0) label[i] = 1000 + best;
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
                        for (int k : nb) if (k >= 0 && b[k] && region[k] < 0 && label[k] == label[c]) { region[k] = id; stack[sp++] = k; }
                    }
                    regionCells.add(java.util.Arrays.copyOf(cells, n));
                }
                // distance (in cells) of each blocked cell from its region's edge: 1 on the edge
                edgeDist = new int[mw * mh];
                regionMaxDist = new int[regionCells.size()];
                int[] queue = new int[mw * mh];
                int qh = 0, qt = 0;
                for (int i = 0; i < b.length; i++) {
                    if (region[i] < 0) continue;
                    int cx = i % mw, cy = i / mw, id = region[i];
                    boolean edge = cx == 0 || cy == 0 || cx == mw - 1 || cy == mh - 1
                            || region[i - 1] != id || region[i + 1] != id || region[i - mw] != id || region[i + mw] != id;
                    if (edge) { edgeDist[i] = 1; queue[qt++] = i; }
                }
                while (qh < qt) {
                    int c = queue[qh++], cx = c % mw, cy = c / mw;
                    int[] nb = {cx > 0 ? c - 1 : -1, cx < mw - 1 ? c + 1 : -1, cy > 0 ? c - mw : -1, cy < mh - 1 ? c + mw : -1};
                    for (int k : nb)
                        if (k >= 0 && region[k] == region[c] && edgeDist[k] == 0) { edgeDist[k] = edgeDist[c] + 1; queue[qt++] = k; }
                }
                for (int i = 0; i < b.length; i++) if (region[i] >= 0) regionMaxDist[region[i]] = Math.max(regionMaxDist[region[i]], edgeDist[i]);
                // which tilesets' art stands on / beside each region (a tile only joins a region its
                // own tileset is drawn on: v17 gave the shrine the town hall's roof height)
                for (int i = 0; i < regionCells.size(); i++) regionTex.add(new java.util.HashSet<>());
                for (long e : artTex) {
                    int c = (int) (e >>> 32), x = c % mw, y = c / mw, t = (int) e;
                    Integer tc = texCat.get(t);
                    if (tc != null && tc == 2 && propCell2[c]) continue;
                    int reach = tc != null && tc == 3 ? 4 : 1;                 // see the near mask
                    for (int dx = -reach; dx <= reach; dx++)
                        for (int dy = -reach; dy <= reach; dy++) {
                            int id = regionOf(x + dx, y + dy);
                            Integer cat = texCat.get(t);
                            int lb = id >= 0 ? label[(x + dx) + (y + dy) * mw] : 0;
                            if (id >= 0 && cat != null && cat == (lb >= 1000 ? 3 : lb)) regionTex.get(id).add(t);
                        }
                }
            }
        } catch (Throwable t) {
            System.out.println("[ekvr] nonwalk layer: " + t);
        }
        System.out.println("[ekvr] structures: " + sets + " tilesets, " + keys.size() + " tiles, nonwalk "
                + mw + "x" + mh + ", " + regionCells.size() + " regions");
    }

    boolean[] propCell, propCell2, artDbg, nonwalkDbg;
    final ArrayList<java.util.HashSet<Integer>> regionTex = new ArrayList<>();
    final java.util.HashMap<Integer, Integer> texCat = new java.util.HashMap<>();

    boolean isStructure(long key) { return keys.contains(key); }

    /** A structure tile drawn at this cell: false for the dungeon / ruin pieces that are props (see build). */
    boolean isStructureAt(long key, int tex, int col, int row) {
        if (!keys.contains(key)) return false;
        if (col < 0 || row < 0 || col >= mw || row >= mh) return true;
        Integer cat = texCat.get(tex);
        if (cat != null && cat == 2 && propCell2 != null) {
            // the quad's cell or a neighbour (a tile's drawn cell can be one off its layer cell)
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++) {
                    int x = col + dx, y = row + dy;
                    if (x >= 0 && y >= 0 && x < mw && y < mh && propCell2[x + y * mw]) return false;
                }
            return true;
        }
        return propCell == null || !propCell[col + row * mw];
    }


    // ---- per frame -------------------------------------------------------------------------
    int fbo, tex, texW, texH;
    float x0, y0, bw, bh;             // iso bounds of the image
    int imgW, imgH;
    boolean any;
    FloatBuffer cols = ByteBuffer.allocateDirect(4 * 6 * 6 * 5 * 8192).order(ByteOrder.nativeOrder()).asFloatBuffer();
    int colVerts;
    float[] colMid = new float[0];    // per column: x, z (centre) for fading
    float[] colTop = new float[0];
    int[] colRegion = new int[0];     // per column: its region (hiding the building the viewer is in)    // per column: its highest corner (fading: does it block the hero?)
    int[] colStart = new int[0], colCount = new int[0];
    int ncols;
    final IntBuffer ib = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    FloatBuffer quad = ByteBuffer.allocateDirect(1 << 20).order(ByteOrder.nativeOrder()).asFloatBuffer();   // grows as needed
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
        lastHeights = heights;
        any = false;
        for (int i = 0; i < n; i++) {
            if (sc.kind[i] != Scene3D.STRUCT) continue;
            any = true;
            int o = i * 16;
            float qminY = Float.MAX_VALUE, qmaxY = -Float.MAX_VALUE;
            for (int c = 0; c < 4; c++) {
                float x = q[o + c * 4], y = q[o + c * 4 + 1];
                minx = Math.min(minx, x); maxx = Math.max(maxx, x);
                miny = Math.min(miny, y); maxy = Math.max(maxy, y);
                qminY = Math.min(qminY, y); qmaxY = Math.max(qmaxY, y);
            }
            // the art's top (iso y of its highest opaque row), not the tile's: every tile is the same
            // size, so v16's tile-top heights made every building the same (2.86 m)
            float h = qminY + sc.topPx(i, qmaxY - qminY);
            int col = Math.round(sc.ax[i]), row = Math.round(-sc.az[i]) - 1;
            // the footprint region this art belongs to: its own cell, else the nearest within 2 cells
            // (roof and overhang tiles are anchored outside the blocked cells). v15 gave such a tile a
            // lone column of its own, which showed its art as a floating fragment (owner's screenshot).
            int id = nearestRegion(col, row, 2, rec.tex[i].getTextureObjectHandle());
            if (id < 0) continue;                                       // no footprint: its art shows on nothing
            Float old = heights.get(id);
            if (old == null || h > old) heights.put(id, h);
        }
        if (!any) return;
        // art top -> height (§3, v16): the roof's peak (the region's innermost cells) must reach
        // the art's top, so H = (art top - iso y of the backmost peak cell's centre) x SY
        for (java.util.Map.Entry<Integer, Float> e : heights.entrySet()) {
            int id = e.getKey();
            float back = -Float.MAX_VALUE;
            for (int cell : regionCells.get(id)) {
                int col = cell & 0xffff, row = cell >>> 16;
                if (edgeDist[col + row * mw] == regionMaxDist[id]) back = Math.max(back, 16f * (row + 1 - col));
            }
            float h = Math.max(0.5f, Math.min(8f, (e.getValue() - back) * Scene3D.SY));
            // thin regions (walls, fences, a house's yard wall) pick up the art of the building
            // behind them within 2 cells: cap them (v17: one stood 6 m tall beside the viewer)
            if (regionMaxDist[id] < 2) h = Math.min(h, 3f);
            e.setValue(h);
        }
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
        int need = 0;
        for (int i = 0; i < n; i++) if (sc.kind[i] == Scene3D.STRUCT) need += 48;
        if (quad.capacity() < need) quad = ByteBuffer.allocateDirect(need * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
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
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        gl.glGenerateMipmap(GL20.GL_TEXTURE_2D);                         // for the shader's hole fill
        gl.glBindFramebuffer(GL20.GL_FRAMEBUFFER, prevFbo);
        gl.glViewport(vx, vy, vw, vh);
        // 3. columns
        cols.clear(); colVerts = 0; ncols = 0;
        for (java.util.Map.Entry<Integer, Float> e : heights.entrySet()) {
            int id = e.getKey();
            float h = Math.max(0.3f, e.getValue());
            if (id >= 0) {
                // a roof-like heightfield (§3, v16): edge cells at wall height, rising to the art's
                // full height H at the cells farthest inside; thin regions (walls) are H throughout
                int dmax = regionMaxDist[id];
                float hw = dmax >= 2 ? Math.max(1.5f, Math.min(3.0f, 0.45f * h)) : h;
                curH = h; curHw = hw; curDmax = dmax; curThin = dmax < 2;
                for (int cell : regionCells.get(id)) {
                    int col = cell & 0xffff, row = cell >>> 16;
                    if (!nearImage(col, row)) continue;
                    column(col, row, h, id);
                }
            }
        }
    }

    HashMap<Integer, Float> lastHeights;

    /** (tests) the regions around a cell: id letter per cell (lower case = edge), then each id's height. */
    String dump(int c0, int r0, int rad) {
        StringBuilder sb = new StringBuilder();
        java.util.TreeSet<Integer> ids = new java.util.TreeSet<>();
        for (int r = r0 + rad; r >= r0 - rad; r--) {
            for (int c = c0 - rad; c <= c0 + rad; c++) {
                int id = regionOf(c, r);
                if (c == c0 && r == r0) { sb.append('@'); continue; }
                if (id < 0) { sb.append(propCell != null && c >= 0 && r >= 0 && c < mw && r < mh && propCell[c + r * mw] ? '*' : artDbg != null && c >= 0 && r >= 0 && c < mw && r < mh && artDbg[c + r * mw] ? '+' : nonwalkDbg != null && c >= 0 && r >= 0 && c < mw && r < mh && nonwalkDbg[c + r * mw] ? '#' : '.'); continue; }
                ids.add(id);
                char ch = (char) ('A' + id % 26);
                sb.append(edgeDist[c + r * mw] == 1 ? Character.toLowerCase(ch) : ch);
            }
            sb.append('\n');
        }
        for (int id : ids) sb.append((char) ('A' + id % 26)).append(id).append(" h=").append(lastHeights == null ? null : lastHeights.get(id)).append(" dmax=").append(regionMaxDist[id]).append(" cells=").append(regionCells.get(id).length).append('\n');
        return sb.toString();
    }

    /** The region at / nearest this cell (within r) that art of this texture stands on, or -1. */
    int nearestRegion(int col, int row, int r, int tex) {
        int best = regionOf(col, row);
        if (best >= 0 && regionTex.get(best).contains(tex)) return best;
        best = -1;
        int bd = Integer.MAX_VALUE;
        for (int dy = -r; dy <= r; dy++)
            for (int dx = -r; dx <= r; dx++) {
                int g = regionOf(col + dx, row + dy), d = dx * dx + dy * dy;
                if (g >= 0 && d < bd && regionTex.get(g).contains(tex)) { bd = d; best = g; }
            }
        return best;
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

    float curH, curHw;
    boolean curThin;
    int curDmax;

    /** Height of a cell of region id in the heightfield, or NaN (not in the region). */
    float cellH(int col, int row, int id) {
        if (regionOf(col, row) != id) return Float.NaN;
        if (curDmax < 2) return curH;
        int d = edgeDist[col + row * mw];
        return curHw + (curH - curHw) * (d - 1) / (float) (curDmax - 1);
    }

    /** A corner's height: the mean of the region cells around it. */
    float cornerH(int id, int ca, int ra, int cb, int rb) {
        float sum = 0; int n = 0;
        for (int c = ca; c <= cb; c++)
            for (int r = ra; r <= rb; r++) {
                float v = cellH(c, r, id);
                if (!Float.isNaN(v)) { sum += v; n++; }
            }
        return n == 0 ? curHw : sum / n;
    }

    void column(int col, int row, float h, int id) {
        if (colVerts + 30 > cols.capacity() / 6) return;
        // cell (col, row): X in [col-0.5, col+0.5], Z in [-(row+1.5), -(row+0.5)]
        float xa = col - 0.5f, xb = col + 0.5f, za = -(row + 1.5f), zb = -(row + 0.5f);
        if (ncols == colStart.length) {
            colStart = java.util.Arrays.copyOf(colStart, ncols * 2 + 64);
            colCount = java.util.Arrays.copyOf(colCount, ncols * 2 + 64);
            colMid = java.util.Arrays.copyOf(colMid, (ncols * 2 + 64) * 2);
            colTop = java.util.Arrays.copyOf(colTop, ncols * 2 + 64);
            colRegion = java.util.Arrays.copyOf(colRegion, ncols * 2 + 64);
            colRoof = java.util.Arrays.copyOf(colRoof, ncols * 2 + 64);
        }
        colStart[ncols] = colVerts; colMid[ncols * 2] = col; colMid[ncols * 2 + 1] = -(row + 1);
        colRoof[ncols] = false;
        int before = colVerts;
        // corner heights (smooth roof surface): (xa,za) (xb,za) (xb,zb) (xa,zb)
        float hAA = cornerH(id, col - 1, row, col, row + 1), hBA = cornerH(id, col, row, col + 1, row + 1);
        float hBB = cornerH(id, col, row - 1, col + 1, row), hAB = cornerH(id, col - 1, row - 1, col, row);
        colRegion[ncols] = id;
        colTop[ncols] = Math.max(Math.max(hAA, hBA), Math.max(hBB, hAB));
        // sides on the region's edge, up to the corner heights
        if (regionOf(col - 1, row) != id) face(xa, za, hAA, xa, zb, hAB);
        if (regionOf(col + 1, row) != id) face(xb, zb, hBB, xb, za, hBA);
        if (regionOf(col, row + 1) != id) face(xb, za, hBA, xa, za, hAA);   // row+1 = more negative Z
        if (regionOf(col, row - 1) != id) face(xa, zb, hAB, xb, zb, hBB);
        // top (outward winding: faces are drawn from outside only)
        tri(xa, hAA, za, xb, hBB, zb, xb, hBA, za);
        tri(xa, hAA, za, xa, hAB, zb, xb, hBB, zb);
        colCount[ncols] = colVerts - before;
        ncols++;
    }

    void quad(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
        tri(x1, y1, z1, x2, y2, z2, x3, y3, z3);
        tri(x1, y1, z1, x3, y3, z3, x4, y4, z4);
    }

    boolean[] colRoof = new boolean[0];

    void face(float x1, float z1, float h1, float x2, float z2, float h2) {
        tri(x1, 0, z1, x2, 0, z2, x2, h2, z2);
        tri(x1, 0, z1, x2, h2, z2, x1, h1, z1);
    }

    /**
     * One triangle, wound counter-clockwise seen from outside; each vertex carries the outward
     * normal, so the shader can drop the side facing away from the eye (see draw).
     */
    void tri(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        float ax = x2 - x1, ay = y2 - y1, az = z2 - z1, bx = x3 - x1, by = y3 - y1, bz = z3 - z1;
        float nx = ay * bz - az * by, ny = az * bx - ax * bz, nz = ax * by - ay * bx;
        // unit normal; length 2 marks a thin region (walls, fences: their back side shows the same
        // stone, so the shader keeps the art there instead of blurring it)
        float nl = (float) Math.sqrt(nx * nx + ny * ny + nz * nz), sc = (curThin ? 2f : 1f) / Math.max(1e-6f, nl);
        nx *= sc; ny *= sc; nz *= sc;
        put(x1, y1, z1, nx, ny, nz); put(x2, y2, z2, nx, ny, nz); put(x3, y3, z3, nx, ny, nz);
    }

    void put(float x, float y, float z, float nx, float ny, float nz) { cols.put(x).put(y).put(z).put(nx).put(ny).put(nz); colVerts++; }

    /** Draw the columns (opaque pass, or the faded ones between the viewer and the hero). */
    void draw(GL20 gl, float[] mvp, Scene3D sc, boolean faded, boolean[] fade, boolean[] hide) {
        float[] tint = sc.tint;
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
        sc.fogUniforms(gl, cFogC, cFog, cFogCol);
        gl.glActiveTexture(GL20.GL_TEXTURE0);
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        cols.position(0);
        gl.glEnableVertexAttribArray(cPos);
        gl.glVertexAttribPointer(cPos, 3, GL20.GL_FLOAT, false, 24, cols);
        cols.position(3);
        gl.glEnableVertexAttribArray(cNrm);
        gl.glVertexAttribPointer(cNrm, 3, GL20.GL_FLOAT, false, 24, cols);
        cols.position(0);
        // outside faces only: seen from below or from inside, a block's top showed its roof art as a
        // ceiling slab across the view. Decided per fragment from the eye's side of the face (its
        // outward normal), not by GL winding: v16 first culled with glFrontFace(CW), which on the
        // test bed kept the inside walls (owner: "all of the buildings"), and a projection's
        // handedness / depth convention differs between the Quest and the test bed.
        gl.glUniform3f(cEye, sc.viewEyeX, sc.viewEyeY, sc.viewEyeZ);
        for (int i = 0; i < ncols; i++) {
            boolean f = fade != null && i < fade.length && fade[i];
            if (f != faded || (hide != null && i < hide.length && hide[i])) continue;
            gl.glDrawArrays(GL20.GL_TRIANGLES, colStart[i], colCount[i]);
        }
        gl.glDisableVertexAttribArray(cNrm);
    }

    void ensureTarget(GL20 gl, int w, int h) {
        if (fbo != 0 && w <= texW && h <= texH) return;
        texW = Math.max(w, texW); texH = Math.max(h, texH);
        if (tex == 0) { ib.clear(); gl.glGenTextures(1, ib); tex = ib.get(0); }
        gl.glBindTexture(GL20.GL_TEXTURE_2D, tex);
        gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, texW, texH, 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, null);
        gl.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_LINEAR_MIPMAP_LINEAR);
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
                "attribute vec3 a_pos; attribute vec3 a_nrm; uniform mat4 u_mvp; uniform vec4 u_iso; uniform float u_sy; uniform vec2 u_sub;\n" +
                "uniform vec3 u_eye; varying vec2 v_uv; varying vec2 v_w; varying float v_out; varying float v_hid;\n" +
                "void main(){\n" +
                "  v_w = a_pos.xz; v_out = dot(a_nrm, u_eye - a_pos);\n" +
                // faces turned away from the 2D view (it looks along -X-Z): the art has nothing of them
                "  vec3 nn = normalize(a_nrm); v_hid = length(a_nrm) > 1.5 ? 1.0 : (nn.x + nn.z) * 0.7071;\n" +
                "  float sx = 32.0*(a_pos.x - a_pos.z);\n" +
                "  float sy = 16.0*(-a_pos.z - a_pos.x) + a_pos.y / u_sy;\n" +
                "  v_uv = vec2((sx - u_iso.x) / u_iso.z, (sy - u_iso.y) / u_iso.w) * u_sub;\n" +
                "  gl_Position = u_mvp*vec4(a_pos,1.0); }");
        int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision highp float;\n#endif\n" +
                "uniform sampler2D u_tex; uniform vec4 u_tint; uniform float u_alpha; varying vec2 v_uv; varying vec2 v_w; varying float v_out; varying float v_hid;\n" + Scene3D.FOG +
                // a face the 2D view can't see (north / west walls) would copy whatever stands in front
                // of it in the picture -- windows, doors, timber of the front (owner: ghost props and
                // smears on house walls, v18): it takes the building's blurred colour instead (v19)
                "void main(){ if(v_out<0.0) discard; vec4 c = v_hid < -0.2 ? texture2D(u_tex,v_uv,6.0) : texture2D(u_tex,v_uv);\n" +
                "  if(v_hid < -0.2 && c.a > 0.02) c = vec4(c.rgb / c.a, 1.0);\n" +
                // a face the 2D art doesn't cover (a side / back wall, the edge of a roof) takes the colour
                // of the nearest art (coarser mip levels of the image: pull fill) instead of a hole (v17)
                "  if(c.a<0.5){ vec4 f=texture2D(u_tex,v_uv,6.0);\n" +   // v19: from level 6 (2 and 4 smeared windows / timber into arcs)
                "    if(f.a<0.02) f=texture2D(u_tex,v_uv,7.0);\n" +
                "    if(f.a<0.02) f=texture2D(u_tex,v_uv,8.0); if(f.a<0.02) f=texture2D(u_tex,v_uv,10.0);\n" +
                "    if(f.a<0.002) discard; c=vec4(f.rgb/f.a,1.0); }\n" +
                "  gl_FragColor=fog(vec4(c.rgb/c.a*u_tint.rgb, u_alpha), v_w); }");
        colProg = link(gl, vs, fs);
        cPos = gl.glGetAttribLocation(colProg, "a_pos");
        cNrm = gl.glGetAttribLocation(colProg, "a_nrm");
        cEye = gl.glGetUniformLocation(colProg, "u_eye");
        cMvp = gl.glGetUniformLocation(colProg, "u_mvp");
        cIso = gl.glGetUniformLocation(colProg, "u_iso");
        cSy = gl.glGetUniformLocation(colProg, "u_sy");
        cTint = gl.glGetUniformLocation(colProg, "u_tint");
        cAlpha = gl.glGetUniformLocation(colProg, "u_alpha");
        cSub = gl.glGetUniformLocation(colProg, "u_sub");
        cFogC = gl.glGetUniformLocation(colProg, "u_fogC");
        cFog = gl.glGetUniformLocation(colProg, "u_fog");
        cFogCol = gl.glGetUniformLocation(colProg, "u_fogCol");
    }

    int cSub, cFogC, cFog, cFogCol, cNrm, cEye;

    void setSub(GL20 gl) { gl.glUniform2f(cSub, imgW / (float) texW, imgH / (float) texH); }

    static int link(GL20 gl, int vs, int fs) {
        int p = gl.glCreateProgram();
        gl.glAttachShader(p, vs); gl.glAttachShader(p, fs); gl.glLinkProgram(p);
        return p;
    }
}

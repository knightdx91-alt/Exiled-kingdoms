package ekvr;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

/**
 * A Batch that records instead of drawing. Swapped in as the map renderer's batch
 * (BatchTiledMapRenderer.c), it receives everything the game draws in map space: tiles
 * (draw(Texture, float[20])), actors (MapSprite.a(Batch)), particles, sprite effects, health
 * bars. Each quad keeps its 4 iso-screen corners, UVs, packed colour and blend mode;
 * Scene3D re-places them in 3D. Spec: deobf/QUEST_VR_SPEC.md §3.
 */
public class Rec implements Batch {
    public static final int MAX = 131072;          // v12: far draw distances (QUEST_VR_SPEC.md §3)
    public final Texture[] tex = new Texture[MAX];
    /** Per quad: x1,y1,u1,v1, x2,y2,u2,v2, x3,y3,u3,v3, x4,y4,u4,v4 (corners in batch order). */
    public final float[] q = new float[MAX * 16];
    public final float[] col = new float[MAX];
    public final boolean[] tileDraw = new boolean[MAX];   // came through draw(Texture, float[], ...) = a map tile
    public final boolean[] additive = new boolean[MAX];
    public int n;

    final Color color = new Color(1, 1, 1, 1);
    float packed = Color.WHITE.toFloatBits();
    boolean drawing, blending = true;
    int src = 770, dst = 771, srcA = 770, dstA = 771;      // GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA
    final Matrix4 proj = new Matrix4(), xform = new Matrix4();
    com.badlogic.gdx.graphics.glutils.ShaderProgram shader;

    public void reset() { n = 0; }

    private void quad(Texture t, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2,
                      float x3, float y3, float u3, float v3, float x4, float y4, float u4, float v4, float c, boolean tile) {
        if (n >= MAX || t == null) return;
        int o = n * 16;
        q[o] = x1; q[o + 1] = y1; q[o + 2] = u1; q[o + 3] = v1;
        q[o + 4] = x2; q[o + 5] = y2; q[o + 6] = u2; q[o + 7] = v2;
        q[o + 8] = x3; q[o + 9] = y3; q[o + 10] = u3; q[o + 11] = v3;
        q[o + 12] = x4; q[o + 13] = y4; q[o + 14] = u4; q[o + 15] = v4;
        tex[n] = t;
        col[n] = c;
        tileDraw[n] = tile;
        additive[n] = dst == 1;                            // GL_ONE: additive particles
        n++;
    }

    private void rect(Texture t, float x, float y, float w, float h, float u, float v, float u2, float v2) {
        // libGDX convention: (u, v2) is the bottom-left of the region
        quad(t, x, y, u, v2, x, y + h, u, v, x + w, y + h, u2, v, x + w, y, u2, v2, packed, false);
    }

    // ---- Batch: drawing --------------------------------------------------------------------
    public void draw(Texture t, float[] v, int off, int count) {
        for (int i = off; i + 20 <= off + count; i += 20)
            quad(t, v[i], v[i + 1], v[i + 3], v[i + 4], v[i + 5], v[i + 6], v[i + 8], v[i + 9],
                    v[i + 10], v[i + 11], v[i + 13], v[i + 14], v[i + 15], v[i + 16], v[i + 18], v[i + 19], v[i + 2], true);
    }
    public void draw(Texture t, float x, float y) { draw(t, x, y, t.getWidth(), t.getHeight()); }
    public void draw(Texture t, float x, float y, float w, float h) { rect(t, x, y, w, h, 0, 0, 1, 1); }
    public void draw(Texture t, float x, float y, float w, float h, float u, float v, float u2, float v2) {
        rect(t, x, y, w, h, u, v, u2, v2);
    }
    public void draw(Texture t, float x, float y, int sx, int sy, int sw, int sh) {
        float iw = 1f / t.getWidth(), ih = 1f / t.getHeight();
        rect(t, x, y, sw, sh, sx * iw, sy * ih, (sx + sw) * iw, (sy + sh) * ih);
    }
    public void draw(Texture t, float x, float y, float w, float h, int sx, int sy, int sw, int sh, boolean fx, boolean fy) {
        float iw = 1f / t.getWidth(), ih = 1f / t.getHeight();
        float u = sx * iw, v = sy * ih, u2 = (sx + sw) * iw, v2 = (sy + sh) * ih;
        if (fx) { float tmp = u; u = u2; u2 = tmp; }
        if (fy) { float tmp = v; v = v2; v2 = tmp; }
        rect(t, x, y, w, h, u, v, u2, v2);
    }
    public void draw(Texture t, float x, float y, float ox, float oy, float w, float h, float scx, float scy,
                     float rot, int sx, int sy, int sw, int sh, boolean fx, boolean fy) {
        float iw = 1f / t.getWidth(), ih = 1f / t.getHeight();
        float u = sx * iw, v = sy * ih, u2 = (sx + sw) * iw, v2 = (sy + sh) * ih;
        if (fx) { float tmp = u; u = u2; u2 = tmp; }
        if (fy) { float tmp = v; v = v2; v2 = tmp; }
        transformed(t, x, y, ox, oy, w, h, scx, scy, rot, u, v, u2, v2, false);
    }
    public void draw(TextureRegion r, float x, float y) { draw(r, x, y, r.getRegionWidth(), r.getRegionHeight()); }
    public void draw(TextureRegion r, float x, float y, float w, float h) {
        rect(r.getTexture(), x, y, w, h, r.getU(), r.getV(), r.getU2(), r.getV2());
    }
    public void draw(TextureRegion r, float x, float y, float ox, float oy, float w, float h, float scx, float scy, float rot) {
        transformed(r.getTexture(), x, y, ox, oy, w, h, scx, scy, rot, r.getU(), r.getV(), r.getU2(), r.getV2(), false);
    }
    public void draw(TextureRegion r, float x, float y, float ox, float oy, float w, float h, float scx, float scy,
                     float rot, boolean clockwise) {
        transformed(r.getTexture(), x, y, ox, oy, w, h, scx, scy, rot, r.getU(), r.getV(), r.getU2(), r.getV2(), clockwise);
    }
    public void draw(TextureRegion r, float w, float h, com.badlogic.gdx.math.a affine) {
        // Rare (Affine2 draws); approximate with an axis-aligned quad at the origin.
        rect(r.getTexture(), 0, 0, w, h, r.getU(), r.getV(), r.getU2(), r.getV2());
    }

    private void transformed(Texture t, float x, float y, float ox, float oy, float w, float h, float scx, float scy,
                             float rot, float u, float v, float u2, float v2, boolean clockwise) {
        float wx = x + ox, wy = y + oy;
        float fx = -ox * scx, fy = -oy * scy, fx2 = (w - ox) * scx, fy2 = (h - oy) * scy;
        float cos = (float) Math.cos(Math.toRadians(rot)), sin = (float) Math.sin(Math.toRadians(rot));
        float x1 = cos * fx - sin * fy + wx, y1 = sin * fx + cos * fy + wy;
        float x2 = cos * fx - sin * fy2 + wx, y2 = sin * fx + cos * fy2 + wy;
        float x3 = cos * fx2 - sin * fy2 + wx, y3 = sin * fx2 + cos * fy2 + wy;
        float x4 = x1 + (x3 - x2), y4 = y3 - (y2 - y1);
        if (clockwise) quad(t, x1, y1, u2, v2, x2, y2, u, v2, x3, y3, u, v, x4, y4, u2, v, packed, false);
        else quad(t, x1, y1, u, v2, x2, y2, u, v, x3, y3, u2, v, x4, y4, u2, v2, packed, false);
    }

    // ---- Batch: state -----------------------------------------------------------------------
    /** Called on begin() (Scene3D turns the characters to face the viewer for this frame's drawing). */
    public Runnable onBegin;

    public void begin() { drawing = true; if (onBegin != null) onBegin.run(); }
    public void end() { drawing = false; }
    public void flush() {}
    public void disableBlending() { blending = false; }
    public void enableBlending() { blending = true; }
    public void setBlendFunction(int s, int d) { src = srcA = s; dst = dstA = d; }
    public void setBlendFunctionSeparate(int s, int d, int sa, int da) { src = s; dst = d; srcA = sa; dstA = da; }
    public int getBlendSrcFunc() { return src; }
    public int getBlendDstFunc() { return dst; }
    public int getBlendSrcFuncAlpha() { return srcA; }
    public int getBlendDstFuncAlpha() { return dstA; }
    public Color getColor() { return color; }
    public void setColor(Color c) { color.set(c); packed = color.toFloatBits(); }
    public void setColor(float r, float g, float b, float a) { color.set(r, g, b, a); packed = color.toFloatBits(); }
    public void setPackedColor(float p) { packed = p; Color.abgr8888ToColor(color, p); }
    public float getPackedColor() { return packed; }
    public Matrix4 getProjectionMatrix() { return proj; }
    public Matrix4 getTransformMatrix() { return xform; }
    public void setProjectionMatrix(Matrix4 m) { System.arraycopy(m.b, 0, proj.b, 0, 16); }
    public void setTransformMatrix(Matrix4 m) { System.arraycopy(m.b, 0, xform.b, 0, 16); }
    public com.badlogic.gdx.graphics.glutils.ShaderProgram getShader() { return shader; }
    public void setShader(com.badlogic.gdx.graphics.glutils.ShaderProgram s) { shader = s; }
    public boolean isBlendingEnabled() { return blending; }
    public boolean isDrawing() { return drawing; }
    public void dispose() {}
}

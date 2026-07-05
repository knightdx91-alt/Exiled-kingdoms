package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DistanceFieldFont extends BitmapFont {
    private float distanceFieldSmoothing;

    private static class DistanceFieldFontCache extends BitmapFontCache {
        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont) {
            super(distanceFieldFont, distanceFieldFont.usesIntegerPositions());
        }

        private float getSmoothingFactor() {
            DistanceFieldFont distanceFieldFont = (DistanceFieldFont) super.getFont();
            return distanceFieldFont.getScaleX() * distanceFieldFont.getDistanceFieldSmoothing();
        }

        private void setSmoothingUniform(Batch batch, float f2) {
            batch.flush();
            batch.getShader().setUniformf("u_smoothing", f2);
        }

        @Override // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch) {
            setSmoothingUniform(batch, getSmoothingFactor());
            super.draw(batch);
            setSmoothingUniform(batch, 0.0f);
        }

        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont, boolean z2) {
            super(distanceFieldFont, z2);
        }

        @Override // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch, int i2, int i3) {
            setSmoothingUniform(batch, getSmoothingFactor());
            super.draw(batch, i2, i3);
            setSmoothingUniform(batch, 0.0f);
        }
    }

    public DistanceFieldFont(BitmapFont.BitmapFontData bitmapFontData, a<TextureRegion> aVar, boolean z2) {
        super(bitmapFontData, aVar, z2);
    }

    public static ShaderProgram createDistanceFieldShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tv_color = a_color;\n\tv_color.a = v_color.a * (255.0/254.0);\n\tv_texCoords = a_texCoord0;\n\tgl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n\tprecision mediump float;\n\tprecision mediump int;\n#endif\n\nuniform sampler2D u_texture;\nuniform float u_smoothing;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tif (u_smoothing > 0.0) {\n\t\tfloat smoothing = 0.25 / u_smoothing;\n\t\tfloat distance = texture2D(u_texture, v_texCoords).a;\n\t\tfloat alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);\n\t\tgl_FragColor = vec4(v_color.rgb, alpha * v_color.a);\n\t} else {\n\t\tgl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n\t}\n}\n");
        if (shaderProgram.isCompiled()) {
            return shaderProgram;
        }
        throw new IllegalArgumentException("Error compiling distance field shader: " + shaderProgram.getLog());
    }

    public float getDistanceFieldSmoothing() {
        return this.distanceFieldSmoothing;
    }

    @Override // com.badlogic.gdx.graphics.g2d.BitmapFont
    protected void load(BitmapFont.BitmapFontData bitmapFontData) {
        super.load(bitmapFontData);
        a.b<TextureRegion> it = getRegions().iterator();
        while (it.hasNext()) {
            Texture texture = it.next().getTexture();
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.BitmapFont
    public BitmapFontCache newFontCache() {
        return new DistanceFieldFontCache(this, this.integer);
    }

    public void setDistanceFieldSmoothing(float f2) {
        this.distanceFieldSmoothing = f2;
    }

    public DistanceFieldFont(BitmapFont.BitmapFontData bitmapFontData, TextureRegion textureRegion, boolean z2) {
        super(bitmapFontData, textureRegion, z2);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar, boolean z2) {
        super(aVar, z2);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2, boolean z3) {
        super(aVar, aVar2, z2, z3);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2) {
        super(aVar, aVar2, z2);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar, TextureRegion textureRegion, boolean z2) {
        super(aVar, textureRegion, z2);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar, TextureRegion textureRegion) {
        super(aVar, textureRegion);
    }

    public DistanceFieldFont(com.badlogic.gdx.files.a aVar) {
        super(aVar);
    }
}

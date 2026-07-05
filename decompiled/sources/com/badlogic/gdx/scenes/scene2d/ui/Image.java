package com.badlogic.gdx.scenes.scene2d.ui;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.g0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Image extends Widget {
    private int align;
    private Drawable drawable;
    private float imageHeight;
    private float imageWidth;
    private float imageX;
    private float imageY;
    private g0 scaling;

    public Image() {
        this((Drawable) null);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        float x2 = getX();
        float y2 = getY();
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        if (this.drawable instanceof TransformDrawable) {
            float rotation = getRotation();
            if (scaleX != 1.0f || scaleY != 1.0f || rotation != 0.0f) {
                ((TransformDrawable) this.drawable).draw(batch, x2 + this.imageX, y2 + this.imageY, getOriginX() - this.imageX, getOriginY() - this.imageY, this.imageWidth, this.imageHeight, scaleX, scaleY, rotation);
                return;
            }
        }
        Drawable drawable = this.drawable;
        if (drawable != null) {
            drawable.draw(batch, x2 + this.imageX, y2 + this.imageY, this.imageWidth * scaleX, this.imageHeight * scaleY);
        }
    }

    public int getAlign() {
        return this.align;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public float getImageHeight() {
        return this.imageHeight;
    }

    public float getImageWidth() {
        return this.imageWidth;
    }

    public float getImageX() {
        return this.imageX;
    }

    public float getImageY() {
        return this.imageY;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        Drawable drawable = this.drawable;
        if (drawable != null) {
            return drawable.getMinHeight();
        }
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        Drawable drawable = this.drawable;
        if (drawable != null) {
            return drawable.getMinWidth();
        }
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        Drawable drawable = this.drawable;
        if (drawable == null) {
            return;
        }
        q qVarA = this.scaling.a(drawable.getMinWidth(), this.drawable.getMinHeight(), getWidth(), getHeight());
        this.imageWidth = qVarA.f91a;
        this.imageHeight = qVarA.f92b;
        int i2 = this.align;
        if ((i2 & 8) != 0) {
            this.imageX = 0.0f;
        } else if ((i2 & 16) != 0) {
            this.imageX = (int) (r2 - r1);
        } else {
            this.imageX = (int) ((r2 / 2.0f) - (r1 / 2.0f));
        }
        if ((i2 & 2) != 0) {
            this.imageY = (int) (r3 - r0);
        } else if ((i2 & 4) != 0) {
            this.imageY = 0.0f;
        } else {
            this.imageY = (int) ((r3 / 2.0f) - (r0 / 2.0f));
        }
    }

    public void setAlign(int i2) {
        this.align = i2;
        invalidate();
    }

    public void setDrawable(Skin skin, String str) {
        setDrawable(skin.getDrawable(str));
    }

    public void setScaling(g0 g0Var) {
        if (g0Var == null) {
            throw new IllegalArgumentException("scaling cannot be null.");
        }
        this.scaling = g0Var;
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        String name = getName();
        if (name != null) {
            return name;
        }
        String name2 = getClass().getName();
        int iLastIndexOf = name2.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            name2 = name2.substring(iLastIndexOf + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name2.indexOf(36) != -1 ? "Image " : "");
        sb.append(name2);
        sb.append(": ");
        sb.append(this.drawable);
        return sb.toString();
    }

    public Image(NinePatch ninePatch) {
        this(new NinePatchDrawable(ninePatch), g0.f1807b, 1);
    }

    public void setDrawable(Drawable drawable) {
        if (this.drawable == drawable) {
            return;
        }
        if (drawable == null || getPrefWidth() != drawable.getMinWidth() || getPrefHeight() != drawable.getMinHeight()) {
            invalidateHierarchy();
        }
        this.drawable = drawable;
    }

    public Image(TextureRegion textureRegion) {
        this(new TextureRegionDrawable(textureRegion), g0.f1807b, 1);
    }

    public Image(Texture texture) {
        this(new TextureRegionDrawable(new TextureRegion(texture)));
    }

    public Image(Skin skin, String str) {
        this(skin.getDrawable(str), g0.f1807b, 1);
    }

    public Image(Drawable drawable) {
        this(drawable, g0.f1807b, 1);
    }

    public Image(Drawable drawable, g0 g0Var) {
        this(drawable, g0Var, 1);
    }

    public Image(Drawable drawable, g0 g0Var, int i2) {
        this.align = 1;
        setDrawable(drawable);
        this.scaling = g0Var;
        this.align = i2;
        setSize(getPrefWidth(), getPrefHeight());
    }
}

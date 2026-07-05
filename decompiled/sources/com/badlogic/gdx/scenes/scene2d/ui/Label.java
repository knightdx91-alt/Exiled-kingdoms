package com.badlogic.gdx.scenes.scene2d.ui;

import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.o0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Label extends Widget {
    private BitmapFontCache cache;
    private String ellipsis;
    private boolean fontScaleChanged;
    private float fontScaleX;
    private float fontScaleY;
    private int intValue;
    private int labelAlign;
    private float lastPrefHeight;
    private final GlyphLayout layout;
    private int lineAlign;
    private final q prefSize;
    private boolean prefSizeInvalid;
    private LabelStyle style;
    private final o0 text;
    private boolean wrap;
    private static final Color tempColor = new Color();
    private static final GlyphLayout prefSizeLayout = new GlyphLayout();

    public static class LabelStyle {
        public Drawable background;
        public BitmapFont font;
        public Color fontColor;

        public LabelStyle() {
        }

        public LabelStyle(BitmapFont bitmapFont, Color color) {
            this.font = bitmapFont;
            this.fontColor = color;
        }

        public LabelStyle(LabelStyle labelStyle) {
            this.font = labelStyle.font;
            if (labelStyle.fontColor != null) {
                this.fontColor = new Color(labelStyle.fontColor);
            }
            this.background = labelStyle.background;
        }
    }

    public Label(CharSequence charSequence, Skin skin) {
        this(charSequence, (LabelStyle) skin.get(LabelStyle.class));
    }

    private void computePrefSize() {
        this.prefSizeInvalid = false;
        GlyphLayout glyphLayout = prefSizeLayout;
        if (this.wrap && this.ellipsis == null) {
            float width = getWidth();
            Drawable drawable = this.style.background;
            if (drawable != null) {
                width = (Math.max(width, drawable.getMinWidth()) - this.style.background.getLeftWidth()) - this.style.background.getRightWidth();
            }
            glyphLayout.setText(this.cache.getFont(), this.text, Color.WHITE, width, 8, true);
        } else {
            glyphLayout.setText(this.cache.getFont(), this.text);
        }
        q qVar = this.prefSize;
        float f2 = glyphLayout.width;
        float f3 = glyphLayout.height;
        qVar.f91a = f2;
        qVar.f92b = f3;
    }

    private void scaleAndComputePrefSize() {
        BitmapFont font = this.cache.getFont();
        float scaleX = font.getScaleX();
        float scaleY = font.getScaleY();
        if (this.fontScaleChanged) {
            font.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        computePrefSize();
        if (this.fontScaleChanged) {
            font.getData().setScale(scaleX, scaleY);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        Color color = tempColor.set(getColor());
        float f3 = color.f1677a * f2;
        color.f1677a = f3;
        if (this.style.background != null) {
            batch.setColor(color.f1680r, color.f1679g, color.f1678b, f3);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        Color color2 = this.style.fontColor;
        if (color2 != null) {
            color.mul(color2);
        }
        this.cache.tint(color);
        this.cache.setPosition(getX(), getY());
        this.cache.draw(batch);
    }

    protected BitmapFontCache getBitmapFontCache() {
        return this.cache;
    }

    public float getFontScaleX() {
        return this.fontScaleX;
    }

    public float getFontScaleY() {
        return this.fontScaleY;
    }

    public GlyphLayout getGlyphLayout() {
        return this.layout;
    }

    public int getLabelAlign() {
        return this.labelAlign;
    }

    public int getLineAlign() {
        return this.lineAlign;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.prefSizeInvalid) {
            scaleAndComputePrefSize();
        }
        float descent = this.prefSize.f92b - ((this.style.font.getDescent() * (this.fontScaleChanged ? this.fontScaleY / this.style.font.getScaleY() : 1.0f)) * 2.0f);
        Drawable drawable = this.style.background;
        if (drawable != null) {
            return Math.max(drawable.getBottomHeight() + drawable.getTopHeight() + descent, drawable.getMinHeight());
        }
        return descent;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.wrap) {
            return 0.0f;
        }
        if (this.prefSizeInvalid) {
            scaleAndComputePrefSize();
        }
        float f2 = this.prefSize.f91a;
        Drawable drawable = this.style.background;
        if (drawable == null) {
            return f2;
        }
        return Math.max(drawable.getRightWidth() + drawable.getLeftWidth() + f2, drawable.getMinWidth());
    }

    public LabelStyle getStyle() {
        return this.style;
    }

    public o0 getText() {
        return this.text;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.prefSizeInvalid = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        float rightWidth;
        float topHeight;
        float f2;
        float f3;
        GlyphLayout glyphLayout;
        float f4;
        float f5;
        float descent;
        BitmapFont font = this.cache.getFont();
        float scaleX = font.getScaleX();
        float scaleY = font.getScaleY();
        if (this.fontScaleChanged) {
            font.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        int i2 = 0;
        boolean z2 = this.wrap && this.ellipsis == null;
        if (z2) {
            float prefHeight = getPrefHeight();
            if (prefHeight != this.lastPrefHeight) {
                this.lastPrefHeight = prefHeight;
                invalidateHierarchy();
            }
        }
        float width = getWidth();
        float height = getHeight();
        Drawable drawable = this.style.background;
        if (drawable != null) {
            float leftWidth = drawable.getLeftWidth();
            float bottomHeight = drawable.getBottomHeight();
            rightWidth = width - (drawable.getRightWidth() + drawable.getLeftWidth());
            topHeight = height - (drawable.getTopHeight() + drawable.getBottomHeight());
            f2 = leftWidth;
            f3 = bottomHeight;
        } else {
            rightWidth = width;
            topHeight = height;
            f2 = 0.0f;
            f3 = 0.0f;
        }
        GlyphLayout glyphLayout2 = this.layout;
        if (!z2) {
            o0 o0Var = this.text;
            o0Var.getClass();
            int i3 = o0Var.f1854b - 1;
            if (i3 < 0) {
                i2 = -1;
                if (i2 == -1) {
                    o0 o0Var2 = this.text;
                    glyphLayout = glyphLayout2;
                    glyphLayout2.setText(font, o0Var2, 0, o0Var2.f1854b, Color.WHITE, rightWidth, this.lineAlign, z2, this.ellipsis);
                    float f6 = glyphLayout.width;
                    float f7 = glyphLayout.height;
                    int i4 = this.labelAlign;
                    if ((i4 & 8) == 0) {
                        f2 = ((i4 & 16) != 0 ? rightWidth - f6 : (rightWidth - f6) / 2.0f) + f2;
                    }
                    f4 = f6;
                    f5 = f7;
                } else {
                    f5 = font.getData().capHeight;
                    glyphLayout = glyphLayout2;
                    f4 = rightWidth;
                }
            } else {
                char cCharAt = "\n".charAt(0);
                while (i2 <= i3) {
                    if (o0Var.f1853a[i2] == cCharAt) {
                        break;
                    } else {
                        i2++;
                    }
                }
                i2 = -1;
                if (i2 == -1) {
                }
            }
        }
        float f8 = f2;
        int i5 = this.labelAlign;
        if ((i5 & 2) != 0) {
            descent = this.style.font.getDescent() + f3 + (this.cache.getFont().isFlipped() ? 0.0f : topHeight - f5);
        } else if ((i5 & 4) != 0) {
            descent = (f3 + (this.cache.getFont().isFlipped() ? topHeight - f5 : 0.0f)) - this.style.font.getDescent();
        } else {
            descent = ((topHeight - f5) / 2.0f) + f3;
        }
        if (!this.cache.getFont().isFlipped()) {
            descent += f5;
        }
        o0 o0Var3 = this.text;
        glyphLayout.setText(font, o0Var3, 0, o0Var3.f1854b, Color.WHITE, f4, this.lineAlign, z2, this.ellipsis);
        this.cache.setText(glyphLayout, f8, descent);
        if (this.fontScaleChanged) {
            font.getData().setScale(scaleX, scaleY);
        }
    }

    public void setAlignment(int i2) {
        setAlignment(i2, i2);
    }

    public void setEllipsis(String str) {
        this.ellipsis = str;
    }

    public void setFontScale(float f2) {
        setFontScale(f2, f2);
    }

    public void setFontScaleX(float f2) {
        setFontScale(f2, this.fontScaleY);
    }

    public void setFontScaleY(float f2) {
        setFontScale(this.fontScaleX, f2);
    }

    public void setStyle(LabelStyle labelStyle) {
        if (labelStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        BitmapFont bitmapFont = labelStyle.font;
        if (bitmapFont == null) {
            throw new IllegalArgumentException("Missing LabelStyle font.");
        }
        this.style = labelStyle;
        this.cache = bitmapFont.newFontCache();
        invalidateHierarchy();
    }

    public boolean setText(int i2) {
        if (this.intValue == i2) {
            return false;
        }
        o0 o0Var = this.text;
        o0Var.f1854b = 0;
        o0Var.a(i2);
        this.intValue = i2;
        invalidateHierarchy();
        return true;
    }

    public void setWrap(boolean z2) {
        this.wrap = z2;
        invalidateHierarchy();
    }

    public boolean textEquals(CharSequence charSequence) {
        o0 o0Var = this.text;
        int i2 = o0Var.f1854b;
        char[] cArr = o0Var.f1853a;
        if (i2 != charSequence.length()) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (cArr[i3] != charSequence.charAt(i3)) {
                return false;
            }
        }
        return true;
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
        sb.append(name2.indexOf(36) != -1 ? "Label " : "");
        sb.append(name2);
        sb.append(": ");
        sb.append((Object) this.text);
        return sb.toString();
    }

    public Label(CharSequence charSequence, Skin skin, String str) {
        this(charSequence, (LabelStyle) skin.get(str, LabelStyle.class));
    }

    public void setAlignment(int i2, int i3) {
        this.labelAlign = i2;
        if ((i3 & 8) != 0) {
            this.lineAlign = 8;
        } else if ((i3 & 16) != 0) {
            this.lineAlign = 16;
        } else {
            this.lineAlign = 1;
        }
        invalidate();
    }

    public void setEllipsis(boolean z2) {
        if (z2) {
            this.ellipsis = "...";
        } else {
            this.ellipsis = null;
        }
    }

    public void setFontScale(float f2, float f3) {
        this.fontScaleChanged = true;
        this.fontScaleX = f2;
        this.fontScaleY = f3;
        invalidateHierarchy();
    }

    public Label(CharSequence charSequence, Skin skin, String str, Color color) {
        this(charSequence, new LabelStyle(skin.getFont(str), color));
    }

    public Label(CharSequence charSequence, Skin skin, String str, String str2) {
        this(charSequence, new LabelStyle(skin.getFont(str), skin.getColor(str2)));
    }

    public Label(CharSequence charSequence, LabelStyle labelStyle) {
        this.layout = new GlyphLayout();
        this.prefSize = new q();
        o0 o0Var = new o0();
        this.text = o0Var;
        this.intValue = Integer.MIN_VALUE;
        this.labelAlign = 8;
        this.lineAlign = 8;
        this.prefSizeInvalid = true;
        this.fontScaleX = 1.0f;
        this.fontScaleY = 1.0f;
        this.fontScaleChanged = false;
        if (charSequence != null) {
            o0Var.d(charSequence);
        }
        setStyle(labelStyle);
        if (charSequence == null || charSequence.length() <= 0) {
            return;
        }
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            o0 o0Var = this.text;
            if (o0Var.f1854b == 0) {
                return;
            } else {
                o0Var.f1854b = 0;
            }
        } else if (charSequence instanceof o0) {
            if (this.text.equals(charSequence)) {
                return;
            }
            o0 o0Var2 = this.text;
            o0Var2.f1854b = 0;
            o0Var2.c((o0) charSequence);
        } else {
            if (textEquals(charSequence)) {
                return;
            }
            o0 o0Var3 = this.text;
            o0Var3.f1854b = 0;
            o0Var3.d(charSequence);
        }
        this.intValue = Integer.MIN_VALUE;
        invalidateHierarchy();
    }
}

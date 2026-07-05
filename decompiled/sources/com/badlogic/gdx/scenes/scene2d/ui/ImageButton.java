package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.g0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ImageButton extends Button {
    private final Image image;
    private ImageButtonStyle style;

    public static class ImageButtonStyle extends Button.ButtonStyle {
        public Drawable imageChecked;
        public Drawable imageCheckedDown;
        public Drawable imageCheckedOver;
        public Drawable imageDisabled;
        public Drawable imageDown;
        public Drawable imageOver;
        public Drawable imageUp;

        public ImageButtonStyle() {
        }

        public ImageButtonStyle(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
            super(drawable, drawable2, drawable3);
            this.imageUp = drawable4;
            this.imageDown = drawable5;
            this.imageChecked = drawable6;
        }

        public ImageButtonStyle(ImageButtonStyle imageButtonStyle) {
            super(imageButtonStyle);
            this.imageUp = imageButtonStyle.imageUp;
            this.imageDown = imageButtonStyle.imageDown;
            this.imageOver = imageButtonStyle.imageOver;
            this.imageDisabled = imageButtonStyle.imageDisabled;
            this.imageChecked = imageButtonStyle.imageChecked;
            this.imageCheckedDown = imageButtonStyle.imageCheckedDown;
            this.imageCheckedOver = imageButtonStyle.imageCheckedOver;
        }

        public ImageButtonStyle(Button.ButtonStyle buttonStyle) {
            super(buttonStyle);
        }
    }

    public ImageButton(Skin skin) {
        this((ImageButtonStyle) skin.get(ImageButtonStyle.class));
        setSkin(skin);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        updateImage();
        super.draw(batch, f2);
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return getCell(this.image);
    }

    protected Drawable getImageDrawable() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        if (isDisabled() && (drawable3 = this.style.imageDisabled) != null) {
            return drawable3;
        }
        if (isPressed()) {
            if (isChecked() && (drawable2 = this.style.imageCheckedDown) != null) {
                return drawable2;
            }
            Drawable drawable4 = this.style.imageDown;
            if (drawable4 != null) {
                return drawable4;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                Drawable drawable5 = this.style.imageCheckedOver;
                if (drawable5 != null) {
                    return drawable5;
                }
            } else {
                Drawable drawable6 = this.style.imageOver;
                if (drawable6 != null) {
                    return drawable6;
                }
            }
        }
        if (isChecked()) {
            Drawable drawable7 = this.style.imageChecked;
            if (drawable7 != null) {
                return drawable7;
            }
            if (isOver() && (drawable = this.style.imageOver) != null) {
                return drawable;
            }
        }
        return this.style.imageUp;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof ImageButtonStyle)) {
            throw new IllegalArgumentException("style must be an ImageButtonStyle.");
        }
        this.style = (ImageButtonStyle) buttonStyle;
        super.setStyle(buttonStyle);
        if (this.image != null) {
            updateImage();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
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
        sb.append(name2.indexOf(36) != -1 ? "ImageButton " : "");
        sb.append(name2);
        sb.append(": ");
        sb.append(this.image.getDrawable());
        return sb.toString();
    }

    protected void updateImage() {
        this.image.setDrawable(getImageDrawable());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public ImageButtonStyle getStyle() {
        return this.style;
    }

    public ImageButton(Skin skin, String str) {
        this((ImageButtonStyle) skin.get(str, ImageButtonStyle.class));
        setSkin(skin);
    }

    public ImageButton(ImageButtonStyle imageButtonStyle) {
        super(imageButtonStyle);
        Image image = new Image();
        this.image = image;
        image.setScaling(g0.f1806a);
        add(image);
        setStyle(imageButtonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public ImageButton(Drawable drawable) {
        this(new ImageButtonStyle(null, null, null, drawable, null, null));
    }

    public ImageButton(Drawable drawable, Drawable drawable2) {
        this(new ImageButtonStyle(null, null, null, drawable, drawable2, null));
    }

    public ImageButton(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        this(new ImageButtonStyle(null, null, null, drawable, drawable2, drawable3));
    }
}

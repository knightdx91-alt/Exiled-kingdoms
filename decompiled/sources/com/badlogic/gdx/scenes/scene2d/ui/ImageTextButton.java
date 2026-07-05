package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.g0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ImageTextButton extends Button {
    private final Image image;
    private Label label;
    private ImageTextButtonStyle style;

    public static class ImageTextButtonStyle extends TextButton.TextButtonStyle {
        public Drawable imageChecked;
        public Drawable imageCheckedDown;
        public Drawable imageCheckedOver;
        public Drawable imageDisabled;
        public Drawable imageDown;
        public Drawable imageOver;
        public Drawable imageUp;

        public ImageTextButtonStyle() {
        }

        public ImageTextButtonStyle(Drawable drawable, Drawable drawable2, Drawable drawable3, BitmapFont bitmapFont) {
            super(drawable, drawable2, drawable3, bitmapFont);
        }

        public ImageTextButtonStyle(ImageTextButtonStyle imageTextButtonStyle) {
            super(imageTextButtonStyle);
            this.imageUp = imageTextButtonStyle.imageUp;
            this.imageDown = imageTextButtonStyle.imageDown;
            this.imageOver = imageTextButtonStyle.imageOver;
            this.imageDisabled = imageTextButtonStyle.imageDisabled;
            this.imageChecked = imageTextButtonStyle.imageChecked;
            this.imageCheckedDown = imageTextButtonStyle.imageCheckedDown;
            this.imageCheckedOver = imageTextButtonStyle.imageCheckedOver;
        }

        public ImageTextButtonStyle(TextButton.TextButtonStyle textButtonStyle) {
            super(textButtonStyle);
        }
    }

    public ImageTextButton(String str, Skin skin) {
        this(str, (ImageTextButtonStyle) skin.get(ImageTextButtonStyle.class));
        setSkin(skin);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        updateImage();
        this.label.getStyle().fontColor = getFontColor();
        super.draw(batch, f2);
    }

    protected Color getFontColor() {
        Color color;
        Color color2;
        Color color3;
        Color color4;
        Color color5;
        if (isDisabled() && (color5 = this.style.disabledFontColor) != null) {
            return color5;
        }
        if (isPressed()) {
            if (isChecked() && (color4 = this.style.checkedDownFontColor) != null) {
                return color4;
            }
            Color color6 = this.style.downFontColor;
            if (color6 != null) {
                return color6;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                Color color7 = this.style.checkedOverFontColor;
                if (color7 != null) {
                    return color7;
                }
            } else {
                Color color8 = this.style.overFontColor;
                if (color8 != null) {
                    return color8;
                }
            }
        }
        boolean zHasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (zHasKeyboardFocus && (color3 = this.style.checkedFocusedFontColor) != null) {
                return color3;
            }
            Color color9 = this.style.checkedFontColor;
            if (color9 != null) {
                return color9;
            }
            if (isOver() && (color2 = this.style.overFontColor) != null) {
                return color2;
            }
        }
        return (!zHasKeyboardFocus || (color = this.style.focusedFontColor) == null) ? this.style.fontColor : color;
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

    public Label getLabel() {
        return this.label;
    }

    public Cell getLabelCell() {
        return getCell(this.label);
    }

    public CharSequence getText() {
        return this.label.getText();
    }

    public void setLabel(Label label) {
        getLabelCell().setActor(label);
        this.label = label;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof ImageTextButtonStyle)) {
            throw new IllegalArgumentException("style must be a ImageTextButtonStyle.");
        }
        ImageTextButtonStyle imageTextButtonStyle = (ImageTextButtonStyle) buttonStyle;
        this.style = imageTextButtonStyle;
        super.setStyle(buttonStyle);
        if (this.image != null) {
            updateImage();
        }
        Label label = this.label;
        if (label != null) {
            Label.LabelStyle style = label.getStyle();
            style.font = imageTextButtonStyle.font;
            style.fontColor = getFontColor();
            this.label.setStyle(style);
        }
    }

    public void setText(CharSequence charSequence) {
        this.label.setText(charSequence);
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
        sb.append(name2.indexOf(36) != -1 ? "ImageTextButton " : "");
        sb.append(name2);
        sb.append(": ");
        sb.append(this.image.getDrawable());
        sb.append(" ");
        sb.append((Object) this.label.getText());
        return sb.toString();
    }

    protected void updateImage() {
        this.image.setDrawable(getImageDrawable());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public ImageTextButtonStyle getStyle() {
        return this.style;
    }

    public ImageTextButton(String str, Skin skin, String str2) {
        this(str, (ImageTextButtonStyle) skin.get(str2, ImageTextButtonStyle.class));
        setSkin(skin);
    }

    public ImageTextButton(String str, ImageTextButtonStyle imageTextButtonStyle) {
        super(imageTextButtonStyle);
        this.style = imageTextButtonStyle;
        defaults().space(3.0f);
        Image image = new Image();
        this.image = image;
        image.setScaling(g0.f1806a);
        Label label = new Label(str, new Label.LabelStyle(imageTextButtonStyle.font, imageTextButtonStyle.fontColor));
        this.label = label;
        label.setAlignment(1);
        add(image);
        add(this.label);
        setStyle(imageTextButtonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }
}

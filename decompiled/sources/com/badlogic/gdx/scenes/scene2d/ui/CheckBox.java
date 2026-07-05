package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.g0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CheckBox extends TextButton {
    private Image image;
    private Cell imageCell;
    private CheckBoxStyle style;

    public static class CheckBoxStyle extends TextButton.TextButtonStyle {
        public Drawable checkboxOff;
        public Drawable checkboxOffDisabled;
        public Drawable checkboxOn;
        public Drawable checkboxOnDisabled;
        public Drawable checkboxOnOver;
        public Drawable checkboxOver;

        public CheckBoxStyle() {
        }

        public CheckBoxStyle(Drawable drawable, Drawable drawable2, BitmapFont bitmapFont, Color color) {
            this.checkboxOff = drawable;
            this.checkboxOn = drawable2;
            this.font = bitmapFont;
            this.fontColor = color;
        }

        public CheckBoxStyle(CheckBoxStyle checkBoxStyle) {
            super(checkBoxStyle);
            this.checkboxOff = checkBoxStyle.checkboxOff;
            this.checkboxOn = checkBoxStyle.checkboxOn;
            this.checkboxOnOver = checkBoxStyle.checkboxOnOver;
            this.checkboxOver = checkBoxStyle.checkboxOver;
            this.checkboxOnDisabled = checkBoxStyle.checkboxOnDisabled;
            this.checkboxOffDisabled = checkBoxStyle.checkboxOffDisabled;
        }
    }

    public CheckBox(String str, Skin skin) {
        this(str, (CheckBoxStyle) skin.get(CheckBoxStyle.class));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Drawable drawable;
        CheckBoxStyle checkBoxStyle;
        Drawable drawable2;
        if (!isDisabled()) {
            drawable = null;
        } else if (!this.isChecked || (drawable = this.style.checkboxOnDisabled) == null) {
            drawable = this.style.checkboxOffDisabled;
        }
        if (drawable == null) {
            boolean z2 = isOver() && !isDisabled();
            if (!this.isChecked || (drawable2 = (checkBoxStyle = this.style).checkboxOn) == null) {
                if (!z2 || (drawable = this.style.checkboxOver) == null) {
                    drawable = this.style.checkboxOff;
                }
            } else if (!z2 || (drawable = checkBoxStyle.checkboxOnOver) == null) {
                drawable = drawable2;
            }
        }
        this.image.setDrawable(drawable);
        super.draw(batch, f2);
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return this.imageCell;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof CheckBoxStyle)) {
            throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        }
        this.style = (CheckBoxStyle) buttonStyle;
        super.setStyle(buttonStyle);
    }

    public CheckBox(String str, Skin skin, String str2) {
        this(str, (CheckBoxStyle) skin.get(str2, CheckBoxStyle.class));
    }

    public CheckBox(String str, CheckBoxStyle checkBoxStyle) {
        super(str, checkBoxStyle);
        clearChildren();
        Label label = getLabel();
        Image image = new Image(checkBoxStyle.checkboxOff, g0.f1808c);
        this.image = image;
        this.imageCell = add(image);
        add(label);
        label.setAlignment(8);
        setSize(getPrefWidth(), getPrefHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button
    public CheckBoxStyle getStyle() {
        return this.style;
    }
}

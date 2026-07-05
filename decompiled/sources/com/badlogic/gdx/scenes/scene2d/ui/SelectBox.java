package com.badlogic.gdx.scenes.scene2d.ui;

import a0.h;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b0;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SelectBox<T> extends Widget implements Disableable {
    static final q temp = new q();
    private int alignment;
    private ClickListener clickListener;
    boolean disabled;
    final a<T> items;
    private float prefHeight;
    private float prefWidth;
    SelectBoxList<T> selectBoxList;
    boolean selectedPrefWidth;
    final ArraySelection<T> selection;
    SelectBoxStyle style;

    static class SelectBoxList<T> extends ScrollPane {
        private InputListener hideListener;
        final List<T> list;
        int maxListCount;
        private Actor previousScrollFocus;
        private final SelectBox<T> selectBox;
        private final q stagePosition;

        public SelectBoxList(final SelectBox<T> selectBox) {
            super((Actor) null, selectBox.style.scrollStyle);
            this.stagePosition = new q();
            this.selectBox = selectBox;
            setOverscroll(false, false);
            setFadeScrollBars(false);
            setScrollingDisabled(true, false);
            List<T> list = new List<T>(selectBox.style.listStyle) { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxList.1
                @Override // com.badlogic.gdx.scenes.scene2d.ui.List
                public String toString(T t2) {
                    return selectBox.toString(t2);
                }
            };
            this.list = list;
            list.setTouchable(Touchable.disabled);
            list.setTypeToSelect(true);
            setActor(list);
            list.addListener(new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxList.2
                @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f2, float f3) {
                    T selected = SelectBoxList.this.list.getSelected();
                    if (selected != null) {
                        selectBox.selection.items().clear();
                    }
                    selectBox.selection.choose(selected);
                    SelectBoxList.this.hide();
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                    int itemIndexAt = SelectBoxList.this.list.getItemIndexAt(f3);
                    if (itemIndexAt == -1) {
                        return true;
                    }
                    SelectBoxList.this.list.setSelectedIndex(itemIndexAt);
                    return true;
                }
            });
            addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxList.3
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                    if (actor == null || !SelectBoxList.this.isAscendantOf(actor)) {
                        SelectBoxList.this.list.selection.set((T) selectBox.getSelected());
                    }
                }
            });
            this.hideListener = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxList.4
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyDown(InputEvent inputEvent, int i2) {
                    if (i2 == 66) {
                        selectBox.selection.choose(SelectBoxList.this.list.getSelected());
                    } else if (i2 != 131) {
                        return false;
                    }
                    SelectBoxList.this.hide();
                    inputEvent.stop();
                    return true;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    if (SelectBoxList.this.isAscendantOf(inputEvent.getTarget())) {
                        return false;
                    }
                    SelectBoxList.this.list.selection.set((T) selectBox.getSelected());
                    SelectBoxList.this.hide();
                    return false;
                }
            };
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        public void act(float f2) {
            super.act(f2);
            toFront();
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        public void draw(Batch batch, float f2) {
            SelectBox<T> selectBox = this.selectBox;
            q qVar = SelectBox.temp;
            qVar.f91a = 0.0f;
            qVar.f92b = 0.0f;
            selectBox.localToStageCoordinates(qVar);
            if (!qVar.equals(this.stagePosition)) {
                hide();
            }
            super.draw(batch, f2);
        }

        public void hide() {
            if (this.list.isTouchable() && hasParent()) {
                this.list.setTouchable(Touchable.disabled);
                Stage stage = getStage();
                if (stage != null) {
                    stage.removeCaptureListener(this.hideListener);
                    stage.removeListener(this.list.getKeyListener());
                    Actor actor = this.previousScrollFocus;
                    if (actor != null && actor.getStage() == null) {
                        this.previousScrollFocus = null;
                    }
                    Actor scrollFocus = stage.getScrollFocus();
                    if (scrollFocus == null || isAscendantOf(scrollFocus)) {
                        stage.setScrollFocus(this.previousScrollFocus);
                    }
                }
                clearActions();
                this.selectBox.onHide(this);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        protected void setStage(Stage stage) {
            Stage stage2 = getStage();
            if (stage2 != null) {
                stage2.removeCaptureListener(this.hideListener);
                stage2.removeListener(this.list.getKeyListener());
            }
            super.setStage(stage);
        }

        public void show(Stage stage) {
            if (this.list.isTouchable()) {
                return;
            }
            stage.addActor(this);
            stage.addCaptureListener(this.hideListener);
            stage.addListener(this.list.getKeyListener());
            SelectBox<T> selectBox = this.selectBox;
            q qVar = this.stagePosition;
            qVar.f91a = 0.0f;
            qVar.f92b = 0.0f;
            selectBox.localToStageCoordinates(qVar);
            float itemHeight = this.list.getItemHeight();
            float fMin = (this.maxListCount <= 0 ? this.selectBox.items.f1750b : Math.min(r1, this.selectBox.items.f1750b)) * itemHeight;
            Drawable drawable = getStyle().background;
            if (drawable != null) {
                fMin += drawable.getBottomHeight() + drawable.getTopHeight();
            }
            Drawable drawable2 = this.list.getStyle().background;
            if (drawable2 != null) {
                fMin += drawable2.getBottomHeight() + drawable2.getTopHeight();
            }
            float f2 = this.stagePosition.f92b;
            float height = (stage.getHeight() - f2) - this.selectBox.getHeight();
            boolean z2 = true;
            if (fMin > f2) {
                if (height > f2) {
                    fMin = Math.min(fMin, height);
                    z2 = false;
                } else {
                    fMin = f2;
                }
            }
            if (z2) {
                setY(this.stagePosition.f92b - fMin);
            } else {
                setY(this.selectBox.getHeight() + this.stagePosition.f92b);
            }
            setX(this.stagePosition.f91a);
            setHeight(fMin);
            validate();
            float fMax = Math.max(getPrefWidth(), this.selectBox.getWidth());
            if (getPrefHeight() > fMin && !this.disableY) {
                fMax += getScrollBarWidth();
            }
            setWidth(fMax);
            validate();
            scrollTo(0.0f, (this.list.getHeight() - (this.selectBox.getSelectedIndex() * itemHeight)) - (itemHeight / 2.0f), 0.0f, 0.0f, true, true);
            updateVisualScroll();
            this.previousScrollFocus = null;
            Actor scrollFocus = stage.getScrollFocus();
            if (scrollFocus != null && !scrollFocus.isDescendantOf(this)) {
                this.previousScrollFocus = scrollFocus;
            }
            stage.setScrollFocus(this);
            this.list.selection.set(this.selectBox.getSelected());
            this.list.setTouchable(Touchable.enabled);
            clearActions();
            this.selectBox.onShow(this, z2);
        }
    }

    public SelectBox(Skin skin) {
        this((SelectBoxStyle) skin.get(SelectBoxStyle.class));
    }

    public void clearItems() {
        a<T> aVar = this.items;
        if (aVar.f1750b == 0) {
            return;
        }
        aVar.clear();
        this.selection.clear();
        invalidateHierarchy();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Drawable drawable;
        Color color;
        float bottomHeight;
        float leftWidth;
        validate();
        if ((!this.disabled || (drawable = this.style.backgroundDisabled) == null) && ((!this.selectBoxList.hasParent() || (drawable = this.style.backgroundOpen) == null) && ((!this.clickListener.isOver() || (drawable = this.style.backgroundOver) == null) && (drawable = this.style.background) == null))) {
            drawable = null;
        }
        SelectBoxStyle selectBoxStyle = this.style;
        BitmapFont bitmapFont = selectBoxStyle.font;
        if (!this.disabled || (color = selectBoxStyle.disabledFontColor) == null) {
            color = (selectBoxStyle.overFontColor == null || !(this.clickListener.isOver() || this.selectBoxList.hasParent())) ? this.style.fontColor : this.style.overFontColor;
        }
        Color color2 = color;
        Color color3 = getColor();
        float x2 = getX();
        float y2 = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a * f2);
        if (drawable != null) {
            drawable.draw(batch, x2, y2, width, height);
        }
        T tFirst = this.selection.first();
        if (tFirst != null) {
            if (drawable != null) {
                width -= drawable.getRightWidth() + drawable.getLeftWidth();
                float topHeight = height - (drawable.getTopHeight() + drawable.getBottomHeight());
                bottomHeight = y2 + ((int) ((bitmapFont.getData().capHeight / 2.0f) + drawable.getBottomHeight() + (topHeight / 2.0f)));
                leftWidth = drawable.getLeftWidth() + x2;
            } else {
                bottomHeight = y2 + ((int) ((bitmapFont.getData().capHeight / 2.0f) + (height / 2.0f)));
                leftWidth = x2;
            }
            bitmapFont.setColor(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a * f2);
            drawItem(batch, bitmapFont, tFirst, leftWidth, bottomHeight, width);
        }
    }

    protected GlyphLayout drawItem(Batch batch, BitmapFont bitmapFont, T t2, float f2, float f3, float f4) {
        String string = toString(t2);
        return bitmapFont.draw(batch, string, f2, f3, 0, string.length(), f4, this.alignment, false, "...");
    }

    public a<T> getItems() {
        return this.items;
    }

    public List<T> getList() {
        return this.selectBoxList.list;
    }

    public int getMaxListCount() {
        return this.selectBoxList.maxListCount;
    }

    public float getMaxSelectedPrefWidth() {
        GlyphLayout glyphLayout = (GlyphLayout) d0.c(GlyphLayout.class).obtain();
        float fMax = 0.0f;
        int i2 = 0;
        while (true) {
            a<T> aVar = this.items;
            if (i2 >= aVar.f1750b) {
                break;
            }
            glyphLayout.setText(this.style.font, toString(aVar.get(i2)));
            fMax = Math.max(glyphLayout.width, fMax);
            i2++;
        }
        Drawable drawable = this.style.background;
        if (drawable == null) {
            return fMax;
        }
        return Math.max(drawable.getRightWidth() + drawable.getLeftWidth() + fMax, drawable.getMinWidth());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        validate();
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        validate();
        return this.prefWidth;
    }

    public ScrollPane getScrollPane() {
        return this.selectBoxList;
    }

    public T getSelected() {
        return this.selection.first();
    }

    public int getSelectedIndex() {
        b0<T> b0VarItems = this.selection.items();
        if (b0VarItems.f2064a == 0) {
            return -1;
        }
        return this.items.h(b0VarItems.c(), false);
    }

    public ArraySelection<T> getSelection() {
        return this.selection;
    }

    public SelectBoxStyle getStyle() {
        return this.style;
    }

    public void hideList() {
        this.selectBoxList.hide();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        SelectBoxStyle selectBoxStyle = this.style;
        Drawable drawable = selectBoxStyle.background;
        BitmapFont bitmapFont = selectBoxStyle.font;
        if (drawable != null) {
            this.prefHeight = Math.max((bitmapFont.getCapHeight() + (drawable.getBottomHeight() + drawable.getTopHeight())) - (bitmapFont.getDescent() * 2.0f), drawable.getMinHeight());
        } else {
            this.prefHeight = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        }
        c0 c0VarC = d0.c(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) c0VarC.obtain();
        if (this.selectedPrefWidth) {
            this.prefWidth = 0.0f;
            if (drawable != null) {
                this.prefWidth = drawable.getRightWidth() + drawable.getLeftWidth();
            }
            T selected = getSelected();
            if (selected != null) {
                glyphLayout.setText(bitmapFont, toString(selected));
                this.prefWidth += glyphLayout.width;
            }
        } else {
            int i2 = 0;
            float fMax = 0.0f;
            while (true) {
                a<T> aVar = this.items;
                if (i2 >= aVar.f1750b) {
                    break;
                }
                glyphLayout.setText(bitmapFont, toString(aVar.get(i2)));
                fMax = Math.max(glyphLayout.width, fMax);
                i2++;
            }
            this.prefWidth = fMax;
            if (drawable != null) {
                this.prefWidth = Math.max(drawable.getRightWidth() + drawable.getLeftWidth() + fMax, drawable.getMinWidth());
            }
            SelectBoxStyle selectBoxStyle2 = this.style;
            List.ListStyle listStyle = selectBoxStyle2.listStyle;
            ScrollPane.ScrollPaneStyle scrollPaneStyle = selectBoxStyle2.scrollStyle;
            float rightWidth = listStyle.selection.getRightWidth() + listStyle.selection.getLeftWidth() + fMax;
            Drawable drawable2 = scrollPaneStyle.background;
            if (drawable2 != null) {
                rightWidth = Math.max(drawable2.getRightWidth() + drawable2.getLeftWidth() + rightWidth, drawable2.getMinWidth());
            }
            SelectBoxList<T> selectBoxList = this.selectBoxList;
            if (selectBoxList == null || !selectBoxList.disableY) {
                Drawable drawable3 = this.style.scrollStyle.vScroll;
                float minWidth = drawable3 != null ? drawable3.getMinWidth() : 0.0f;
                Drawable drawable4 = this.style.scrollStyle.vScrollKnob;
                rightWidth += Math.max(minWidth, drawable4 != null ? drawable4.getMinWidth() : 0.0f);
            }
            this.prefWidth = Math.max(this.prefWidth, rightWidth);
        }
        c0VarC.free(glyphLayout);
    }

    protected void onHide(Actor actor) {
        actor.getColor().f1677a = 1.0f;
        actor.addAction(Actions.sequence(Actions.fadeOut(0.15f, h.f53b), Actions.removeActor()));
    }

    protected void onShow(Actor actor, boolean z2) {
        actor.getColor().f1677a = 0.0f;
        actor.addAction(Actions.fadeIn(0.3f, h.f53b));
    }

    public void setAlignment(int i2) {
        this.alignment = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z2) {
        if (z2 && !this.disabled) {
            hideList();
        }
        this.disabled = z2;
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        this.items.clear();
        a<T> aVar = this.items;
        aVar.getClass();
        aVar.d(tArr, 0, tArr.length);
        this.selection.validate();
        this.selectBoxList.list.setItems(this.items);
        invalidate();
        if (prefWidth != getPrefWidth()) {
            invalidateHierarchy();
        }
    }

    public void setMaxListCount(int i2) {
        this.selectBoxList.maxListCount = i2;
    }

    public void setScrollingDisabled(boolean z2) {
        this.selectBoxList.setScrollingDisabled(true, z2);
        invalidateHierarchy();
    }

    public void setSelected(T t2) {
        if (this.items.e(t2, false)) {
            this.selection.set(t2);
            return;
        }
        a<T> aVar = this.items;
        if (aVar.f1750b > 0) {
            this.selection.set(aVar.g());
        } else {
            this.selection.clear();
        }
    }

    public void setSelectedIndex(int i2) {
        this.selection.set(this.items.get(i2));
    }

    public void setSelectedPrefWidth(boolean z2) {
        this.selectedPrefWidth = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setStage(Stage stage) {
        if (stage == null) {
            this.selectBoxList.hide();
        }
        super.setStage(stage);
    }

    public void setStyle(SelectBoxStyle selectBoxStyle) {
        if (selectBoxStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = selectBoxStyle;
        SelectBoxList<T> selectBoxList = this.selectBoxList;
        if (selectBoxList != null) {
            selectBoxList.setStyle(selectBoxStyle.scrollStyle);
            this.selectBoxList.list.setStyle(selectBoxStyle.listStyle);
        }
        invalidateHierarchy();
    }

    public void showList() {
        if (this.items.f1750b == 0 || getStage() == null) {
            return;
        }
        this.selectBoxList.show(getStage());
    }

    protected String toString(T t2) {
        return t2.toString();
    }

    public static class SelectBoxStyle {
        public Drawable background;
        public Drawable backgroundDisabled;
        public Drawable backgroundOpen;
        public Drawable backgroundOver;
        public Color disabledFontColor;
        public BitmapFont font;
        public Color fontColor;
        public List.ListStyle listStyle;
        public Color overFontColor;
        public ScrollPane.ScrollPaneStyle scrollStyle;

        public SelectBoxStyle() {
            this.fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public SelectBoxStyle(BitmapFont bitmapFont, Color color, Drawable drawable, ScrollPane.ScrollPaneStyle scrollPaneStyle, List.ListStyle listStyle) {
            Color color2 = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontColor = color2;
            this.font = bitmapFont;
            color2.set(color);
            this.background = drawable;
            this.scrollStyle = scrollPaneStyle;
            this.listStyle = listStyle;
        }

        public SelectBoxStyle(SelectBoxStyle selectBoxStyle) {
            Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontColor = color;
            this.font = selectBoxStyle.font;
            color.set(selectBoxStyle.fontColor);
            if (selectBoxStyle.overFontColor != null) {
                this.overFontColor = new Color(selectBoxStyle.overFontColor);
            }
            if (selectBoxStyle.disabledFontColor != null) {
                this.disabledFontColor = new Color(selectBoxStyle.disabledFontColor);
            }
            this.background = selectBoxStyle.background;
            this.scrollStyle = new ScrollPane.ScrollPaneStyle(selectBoxStyle.scrollStyle);
            this.listStyle = new List.ListStyle(selectBoxStyle.listStyle);
            this.backgroundOver = selectBoxStyle.backgroundOver;
            this.backgroundOpen = selectBoxStyle.backgroundOpen;
            this.backgroundDisabled = selectBoxStyle.backgroundDisabled;
        }
    }

    public SelectBox(Skin skin, String str) {
        this((SelectBoxStyle) skin.get(str, SelectBoxStyle.class));
    }

    public SelectBox(SelectBoxStyle selectBoxStyle) {
        a<T> aVar = new a<>();
        this.items = aVar;
        this.alignment = 8;
        ArraySelection<T> arraySelection = new ArraySelection(aVar) { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
            public boolean fireChangeEvent() {
                SelectBox selectBox = SelectBox.this;
                if (selectBox.selectedPrefWidth) {
                    selectBox.invalidateHierarchy();
                }
                return super.fireChangeEvent();
            }
        };
        this.selection = arraySelection;
        setStyle(selectBoxStyle);
        setSize(getPrefWidth(), getPrefHeight());
        arraySelection.setActor(this);
        arraySelection.setRequired(true);
        this.selectBoxList = new SelectBoxList<>(this);
        ClickListener clickListener = new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.2
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (i2 == 0 && i3 != 0) {
                    return false;
                }
                SelectBox selectBox = SelectBox.this;
                if (selectBox.disabled) {
                    return false;
                }
                if (selectBox.selectBoxList.hasParent()) {
                    SelectBox.this.hideList();
                    return true;
                }
                SelectBox.this.showList();
                return true;
            }
        };
        this.clickListener = clickListener;
        addListener(clickListener);
    }

    public void setItems(a<T> aVar) {
        if (aVar != null) {
            float prefWidth = getPrefWidth();
            a<T> aVar2 = this.items;
            if (aVar != aVar2) {
                aVar2.clear();
                a<T> aVar3 = this.items;
                aVar3.getClass();
                aVar3.d(aVar.f1749a, 0, aVar.f1750b);
            }
            this.selection.validate();
            this.selectBoxList.list.setItems(this.items);
            invalidate();
            if (prefWidth != getPrefWidth()) {
                invalidateHierarchy();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("newItems cannot be null.");
    }
}

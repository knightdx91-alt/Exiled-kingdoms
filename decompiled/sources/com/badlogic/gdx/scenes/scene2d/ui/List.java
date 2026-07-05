package com.badlogic.gdx.scenes.scene2d.ui;

import a0.p;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b0;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class List<T> extends Widget implements Cullable {
    private int alignment;
    private p cullingArea;
    float itemHeight;
    final a<T> items;
    private InputListener keyListener;
    int overIndex;
    private float prefHeight;
    private float prefWidth;
    int pressedIndex;
    ArraySelection<T> selection;
    ListStyle style;
    boolean typeToSelect;

    public List(Skin skin) {
        this((ListStyle) skin.get(ListStyle.class));
    }

    public void clearItems() {
        a<T> aVar = this.items;
        if (aVar.f1750b == 0) {
            return;
        }
        aVar.clear();
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.clear();
        invalidateHierarchy();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0104 A[SYNTHETIC] */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void draw(Batch batch, float f2) {
        Drawable drawable;
        Drawable drawable2;
        int i2;
        validate();
        drawBackground(batch, f2);
        ListStyle listStyle = this.style;
        BitmapFont bitmapFont = listStyle.font;
        Drawable drawable3 = listStyle.selection;
        Color color = listStyle.fontColorSelected;
        Color color2 = listStyle.fontColorUnselected;
        Color color3 = getColor();
        batch.setColor(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a * f2);
        float x2 = getX();
        float y2 = getY();
        float width = getWidth();
        float height = getHeight();
        Drawable drawable4 = this.style.background;
        if (drawable4 != null) {
            float leftWidth = drawable4.getLeftWidth();
            x2 += leftWidth;
            height -= drawable4.getTopHeight();
            width -= drawable4.getRightWidth() + leftWidth;
        }
        float f3 = x2;
        float f4 = width;
        float leftWidth2 = drawable3.getLeftWidth();
        float rightWidth = (f4 - leftWidth2) - drawable3.getRightWidth();
        float topHeight = drawable3.getTopHeight() - bitmapFont.getDescent();
        bitmapFont.setColor(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a * f2);
        int i3 = 0;
        float f5 = height;
        while (true) {
            a<T> aVar = this.items;
            if (i3 >= aVar.f1750b) {
                return;
            }
            p pVar = this.cullingArea;
            if (pVar != null) {
                float f6 = f5 - this.itemHeight;
                float f7 = pVar.f90y;
                if (f6 <= pVar.height + f7 && f5 >= f7) {
                    T t2 = aVar.get(i3);
                    boolean zContains = this.selection.contains(t2);
                    if (this.pressedIndex == i3 && (drawable = this.style.down) != null) {
                        drawable2 = drawable;
                        if (drawable2 != null) {
                        }
                        i2 = i3;
                        drawItem(batch, bitmapFont, i3, t2, f3 + leftWidth2, (y2 + f5) - topHeight, rightWidth);
                        if (zContains) {
                        }
                    } else if (zContains) {
                        bitmapFont.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
                        drawable2 = drawable3;
                        if (drawable2 != null) {
                            float f8 = this.itemHeight;
                            drawable2.draw(batch, f3, (y2 + f5) - f8, f4, f8);
                        }
                        i2 = i3;
                        drawItem(batch, bitmapFont, i3, t2, f3 + leftWidth2, (y2 + f5) - topHeight, rightWidth);
                        if (zContains) {
                            bitmapFont.setColor(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a * f2);
                        }
                    } else {
                        if (this.overIndex != i3 || (drawable = this.style.over) == null) {
                            drawable = null;
                        }
                        drawable2 = drawable;
                        if (drawable2 != null) {
                        }
                        i2 = i3;
                        drawItem(batch, bitmapFont, i3, t2, f3 + leftWidth2, (y2 + f5) - topHeight, rightWidth);
                        if (zContains) {
                        }
                    }
                } else if (f5 < f7) {
                    return;
                } else {
                    i2 = i3;
                }
            }
            f5 -= this.itemHeight;
            i3 = i2 + 1;
        }
    }

    protected void drawBackground(Batch batch, float f2) {
        if (this.style.background != null) {
            Color color = getColor();
            batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    protected GlyphLayout drawItem(Batch batch, BitmapFont bitmapFont, int i2, T t2, float f2, float f3, float f4) {
        String string = toString(t2);
        return bitmapFont.draw(batch, string, f2, f3, 0, string.length(), f4, this.alignment, false, "...");
    }

    public p getCullingArea() {
        return this.cullingArea;
    }

    public T getItemAt(float f2) {
        int itemIndexAt = getItemIndexAt(f2);
        if (itemIndexAt == -1) {
            return null;
        }
        return this.items.get(itemIndexAt);
    }

    public float getItemHeight() {
        return this.itemHeight;
    }

    public int getItemIndexAt(float f2) {
        float height = getHeight();
        Drawable drawable = this.style.background;
        if (drawable != null) {
            height -= drawable.getBottomHeight() + drawable.getTopHeight();
            f2 -= drawable.getBottomHeight();
        }
        int i2 = (int) ((height - f2) / this.itemHeight);
        if (i2 < 0 || i2 >= this.items.f1750b) {
            return -1;
        }
        return i2;
    }

    public a<T> getItems() {
        return this.items;
    }

    public InputListener getKeyListener() {
        return this.keyListener;
    }

    public T getOverItem() {
        int i2 = this.overIndex;
        if (i2 == -1) {
            return null;
        }
        return this.items.get(i2);
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

    public T getPressedItem() {
        int i2 = this.pressedIndex;
        if (i2 == -1) {
            return null;
        }
        return this.items.get(i2);
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

    public ListStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        ListStyle listStyle = this.style;
        BitmapFont bitmapFont = listStyle.font;
        Drawable drawable = listStyle.selection;
        float capHeight = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        this.itemHeight = capHeight;
        this.itemHeight = drawable.getBottomHeight() + drawable.getTopHeight() + capHeight;
        this.prefWidth = 0.0f;
        c0 c0VarC = d0.c(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) c0VarC.obtain();
        int i2 = 0;
        while (true) {
            a<T> aVar = this.items;
            if (i2 >= aVar.f1750b) {
                break;
            }
            glyphLayout.setText(bitmapFont, toString(aVar.get(i2)));
            this.prefWidth = Math.max(glyphLayout.width, this.prefWidth);
            i2++;
        }
        c0VarC.free(glyphLayout);
        float rightWidth = drawable.getRightWidth() + drawable.getLeftWidth() + this.prefWidth;
        this.prefWidth = rightWidth;
        this.prefHeight = this.items.f1750b * this.itemHeight;
        Drawable drawable2 = this.style.background;
        if (drawable2 != null) {
            this.prefWidth = Math.max(drawable2.getRightWidth() + drawable2.getLeftWidth() + rightWidth, drawable2.getMinWidth());
            this.prefHeight = Math.max(drawable2.getBottomHeight() + drawable2.getTopHeight() + this.prefHeight, drawable2.getMinHeight());
        }
    }

    public void setAlignment(int i2) {
        this.alignment = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(p pVar) {
        this.cullingArea = pVar;
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        float prefHeight = getPrefHeight();
        this.items.clear();
        a<T> aVar = this.items;
        aVar.getClass();
        aVar.d(tArr, 0, tArr.length);
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.validate();
        invalidate();
        if (prefWidth == getPrefWidth() && prefHeight == getPrefHeight()) {
            return;
        }
        invalidateHierarchy();
    }

    public void setSelected(T t2) {
        if (this.items.e(t2, false)) {
            this.selection.set(t2);
            return;
        }
        if (this.selection.getRequired()) {
            a<T> aVar = this.items;
            if (aVar.f1750b > 0) {
                this.selection.set(aVar.g());
                return;
            }
        }
        this.selection.clear();
    }

    public void setSelectedIndex(int i2) {
        if (i2 >= -1) {
            a<T> aVar = this.items;
            if (i2 < aVar.f1750b) {
                if (i2 == -1) {
                    this.selection.clear();
                    return;
                } else {
                    this.selection.set(aVar.get(i2));
                    return;
                }
            }
        }
        throw new IllegalArgumentException("index must be >= -1 and < " + this.items.f1750b + ": " + i2);
    }

    public void setSelection(ArraySelection<T> arraySelection) {
        this.selection = arraySelection;
    }

    public void setStyle(ListStyle listStyle) {
        if (listStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = listStyle;
        invalidateHierarchy();
    }

    public void setTypeToSelect(boolean z2) {
        this.typeToSelect = z2;
    }

    public String toString(T t2) {
        return t2.toString();
    }

    public List(Skin skin, String str) {
        this((ListStyle) skin.get(str, ListStyle.class));
    }

    public static class ListStyle {
        public Drawable background;
        public Drawable down;
        public BitmapFont font;
        public Color fontColorSelected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public Color fontColorUnselected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public Drawable over;
        public Drawable selection;

        public ListStyle() {
        }

        public ListStyle(BitmapFont bitmapFont, Color color, Color color2, Drawable drawable) {
            this.font = bitmapFont;
            this.fontColorSelected.set(color);
            this.fontColorUnselected.set(color2);
            this.selection = drawable;
        }

        public ListStyle(ListStyle listStyle) {
            this.font = listStyle.font;
            this.fontColorSelected.set(listStyle.fontColorSelected);
            this.fontColorUnselected.set(listStyle.fontColorUnselected);
            this.selection = listStyle.selection;
            this.down = listStyle.down;
            this.over = listStyle.over;
            this.background = listStyle.background;
        }
    }

    public List(ListStyle listStyle) {
        a<T> aVar = new a<>();
        this.items = aVar;
        ArraySelection<T> arraySelection = new ArraySelection<>(aVar);
        this.selection = arraySelection;
        this.alignment = 8;
        this.pressedIndex = -1;
        this.overIndex = -1;
        arraySelection.setActor(this);
        this.selection.setRequired(true);
        setStyle(listStyle);
        setSize(getPrefWidth(), getPrefHeight());
        InputListener inputListener = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.List.1
            String prefix;
            long typeTimeout;

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i2) {
                List list = List.this;
                a<T> aVar2 = list.items;
                int i3 = aVar2.f1750b;
                if (i3 == 0) {
                    return false;
                }
                if (i2 == 3) {
                    list.setSelectedIndex(0);
                    return true;
                }
                if (i2 != 29) {
                    if (i2 == 19) {
                        int iH = aVar2.h((T) list.getSelected(), false) - 1;
                        if (iH < 0) {
                            iH = List.this.items.f1750b - 1;
                        }
                        List.this.setSelectedIndex(iH);
                        return true;
                    }
                    if (i2 == 20) {
                        int iH2 = aVar2.h((T) list.getSelected(), false) + 1;
                        List list2 = List.this;
                        list2.setSelectedIndex(iH2 < list2.items.f1750b ? iH2 : 0);
                        return true;
                    }
                    if (i2 == 131) {
                        if (list.getStage() != null) {
                            List.this.getStage().setKeyboardFocus(null);
                        }
                        return true;
                    }
                    if (i2 == 132) {
                        list.setSelectedIndex(i3 - 1);
                        return true;
                    }
                } else if (UIUtils.ctrl() && List.this.selection.getMultiple()) {
                    List.this.selection.clear();
                    List list3 = List.this;
                    list3.selection.addAll(list3.items);
                    return true;
                }
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent, char c2) {
                if (!List.this.typeToSelect) {
                    return false;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis > this.typeTimeout) {
                    this.prefix = "";
                }
                this.typeTimeout = jCurrentTimeMillis + 300;
                this.prefix += Character.toLowerCase(c2);
                int i2 = List.this.items.f1750b;
                int i3 = 0;
                while (true) {
                    if (i3 >= i2) {
                        break;
                    }
                    List list = List.this;
                    if (list.toString(list.items.get(i3)).toLowerCase().startsWith(this.prefix)) {
                        List.this.setSelectedIndex(i3);
                        break;
                    }
                    i3++;
                }
                return false;
            }
        };
        this.keyListener = inputListener;
        addListener(inputListener);
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.List.2
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                if (i2 == 0) {
                    List.this.pressedIndex = -1;
                }
                if (i2 == -1) {
                    List.this.overIndex = -1;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                List list = List.this;
                list.overIndex = list.getItemIndexAt(f3);
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                int itemIndexAt;
                if (i2 != 0 || i3 != 0 || List.this.selection.isDisabled()) {
                    return true;
                }
                if (List.this.getStage() != null) {
                    List.this.getStage().setKeyboardFocus(List.this);
                }
                List list = List.this;
                if (list.items.f1750b == 0 || (itemIndexAt = list.getItemIndexAt(f3)) == -1) {
                    return true;
                }
                List list2 = List.this;
                list2.selection.choose(list2.items.get(itemIndexAt));
                List.this.pressedIndex = itemIndexAt;
                return true;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
                List list = List.this;
                list.overIndex = list.getItemIndexAt(f3);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (i2 == 0 && i3 == 0) {
                    List.this.pressedIndex = -1;
                }
            }
        });
    }

    public void setItems(a aVar) {
        if (aVar != null) {
            float prefWidth = getPrefWidth();
            float prefHeight = getPrefHeight();
            a<T> aVar2 = this.items;
            if (aVar != aVar2) {
                aVar2.clear();
                a<T> aVar3 = this.items;
                aVar3.getClass();
                aVar3.d(aVar.f1749a, 0, aVar.f1750b);
            }
            this.overIndex = -1;
            this.pressedIndex = -1;
            this.selection.validate();
            invalidate();
            if (prefWidth == getPrefWidth() && prefHeight == getPrefHeight()) {
                return;
            }
            invalidateHierarchy();
            return;
        }
        throw new IllegalArgumentException("newItems cannot be null.");
    }
}

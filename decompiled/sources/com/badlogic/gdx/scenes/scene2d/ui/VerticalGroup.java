package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VerticalGroup extends WidgetGroup {
    private int columnAlign;
    private j columnSizes;
    private boolean expand;
    private float fill;
    private float lastPrefWidth;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private float space;
    private boolean wrap;
    private float wrapSpace;
    private boolean sizeInvalid = true;
    private int align = 2;
    private boolean round = true;

    public VerticalGroup() {
        setTouchable(Touchable.childrenOnly);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void computeSize() {
        int i2;
        float width;
        float height;
        int i3 = 0;
        this.sizeInvalid = false;
        k0<Actor> children = getChildren();
        int i4 = children.f1750b;
        this.prefWidth = 0.0f;
        if (this.wrap) {
            this.prefHeight = 0.0f;
            j jVar = this.columnSizes;
            if (jVar == null) {
                this.columnSizes = new j();
            } else {
                jVar.f1824b = 0;
            }
            j jVar2 = this.columnSizes;
            float f2 = this.space;
            float f3 = this.wrapSpace;
            float f4 = this.padTop + this.padBottom;
            float height2 = getHeight() - f4;
            if (this.reverse) {
                i3 = i4 - 1;
                i4 = -1;
                i2 = -1;
            } else {
                i2 = 1;
            }
            float f5 = 0.0f;
            float fMax = 0.0f;
            float f6 = 0.0f;
            while (i3 != i4) {
                Actor actor = children.get(i3);
                if (actor instanceof Layout) {
                    Layout layout = (Layout) actor;
                    width = layout.getPrefWidth();
                    height = layout.getPrefHeight();
                    if (height > height2) {
                        height = Math.max(height2, layout.getMinHeight());
                    }
                } else {
                    width = actor.getWidth();
                    height = actor.getHeight();
                }
                float f7 = height + (f5 > 0.0f ? f2 : 0.0f);
                if (f5 + f7 <= height2 || f5 <= 0.0f) {
                    height = f7;
                } else {
                    jVar2.a(f5);
                    jVar2.a(fMax);
                    this.prefHeight = Math.max(this.prefHeight, f5 + f4);
                    if (f6 > 0.0f) {
                        f6 += f3;
                    }
                    f6 += fMax;
                    f5 = 0.0f;
                    fMax = 0.0f;
                }
                f5 += height;
                fMax = Math.max(fMax, width);
                i3 += i2;
            }
            jVar2.a(f5);
            jVar2.a(fMax);
            this.prefHeight = Math.max(this.prefHeight, f5 + f4);
            if (f6 > 0.0f) {
                f6 += f3;
            }
            this.prefWidth = Math.max(this.prefWidth, f6 + fMax);
        } else {
            this.prefHeight = (this.space * (i4 - 1)) + this.padTop + this.padBottom;
            while (i3 < i4) {
                Actor actor2 = children.get(i3);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.prefWidth = Math.max(this.prefWidth, layout2.getPrefWidth());
                    this.prefHeight = layout2.getPrefHeight() + this.prefHeight;
                } else {
                    this.prefWidth = Math.max(this.prefWidth, actor2.getWidth());
                    this.prefHeight = actor2.getHeight() + this.prefHeight;
                }
                i3++;
            }
        }
        this.prefWidth = this.padLeft + this.padRight + this.prefWidth;
        if (this.round) {
            this.prefWidth = Math.round(r2);
            this.prefHeight = Math.round(this.prefHeight);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void layoutWrapped() {
        float width;
        float f2;
        int i2;
        int i3;
        int i4;
        int i5;
        float width2;
        float height;
        Layout layout;
        float fE;
        char c2;
        float f3;
        j jVar;
        VerticalGroup verticalGroup = this;
        float prefWidth = getPrefWidth();
        if (prefWidth != verticalGroup.lastPrefWidth) {
            verticalGroup.lastPrefWidth = prefWidth;
            invalidateHierarchy();
        }
        int i6 = verticalGroup.align;
        boolean z2 = verticalGroup.round;
        float f4 = verticalGroup.space;
        float f5 = verticalGroup.padLeft;
        float f6 = verticalGroup.fill;
        float f7 = verticalGroup.wrapSpace;
        float f8 = (verticalGroup.prefHeight - verticalGroup.padTop) - verticalGroup.padBottom;
        float height2 = getHeight();
        float f9 = (verticalGroup.prefHeight - verticalGroup.padTop) + f4;
        if ((i6 & 16) == 0) {
            if ((i6 & 8) == 0) {
                width = (getWidth() - prefWidth) / 2.0f;
            }
            if ((i6 & 2) != 0) {
                if ((i6 & 4) == 0) {
                    f2 = (height2 - verticalGroup.prefHeight) / 2.0f;
                }
                float f10 = height2 - verticalGroup.padTop;
                int i7 = verticalGroup.columnAlign;
                j jVar2 = verticalGroup.columnSizes;
                k0<Actor> children = getChildren();
                int i8 = children.f1750b;
                int i9 = 0;
                if (verticalGroup.reverse) {
                    i4 = i8 - 1;
                    i3 = -1;
                    i2 = -1;
                } else {
                    i2 = 1;
                    i3 = i8;
                    i4 = 0;
                }
                float f11 = 0.0f;
                float fE2 = 0.0f;
                while (i4 != i3) {
                    Actor actor = children.get(i4);
                    k0<Actor> k0Var = children;
                    if (actor instanceof Layout) {
                        layout = (Layout) actor;
                        width2 = layout.getPrefWidth();
                        height = layout.getPrefHeight();
                        if (height > f10) {
                            i5 = i3;
                            height = Math.max(f10, layout.getMinHeight());
                        } else {
                            i5 = i3;
                        }
                    } else {
                        i5 = i3;
                        width2 = actor.getWidth();
                        height = actor.getHeight();
                        layout = null;
                    }
                    float f12 = height;
                    float f13 = f10;
                    if ((f11 - f12) - f4 < verticalGroup.padBottom || i9 == 0) {
                        if ((i7 & 4) != 0) {
                            fE = f8 - jVar2.e(i9);
                        } else if ((i7 & 2) == 0) {
                            fE = (f8 - jVar2.e(i9)) / 2.0f;
                        } else {
                            f11 = f9;
                            if (i9 > 0) {
                                f5 = f5 + f7 + fE2;
                            }
                            fE2 = jVar2.e(i9 + 1);
                            i9 += 2;
                        }
                        f11 = f9 - fE;
                        if (i9 > 0) {
                        }
                        fE2 = jVar2.e(i9 + 1);
                        i9 += 2;
                    }
                    if (f6 > 0.0f) {
                        width2 = fE2 * f6;
                    }
                    float fMax = width2;
                    if (layout != null) {
                        fMax = Math.max(fMax, layout.getMinWidth());
                        float maxWidth = layout.getMaxWidth();
                        if (maxWidth > 0.0f && fMax > maxWidth) {
                            fMax = maxWidth;
                        }
                    }
                    if ((i7 & 16) != 0) {
                        f3 = (fE2 - fMax) + f5;
                        c2 = 0;
                    } else if ((i7 & 8) == 0) {
                        c2 = 0;
                        f3 = ((fE2 - fMax) / 2.0f) + f5;
                    } else {
                        c2 = 0;
                        f3 = f5;
                    }
                    int i10 = i7;
                    float f14 = f11 - (f12 + f4);
                    if (z2) {
                        jVar = jVar2;
                        actor.setBounds(Math.round(f3), Math.round(f14), Math.round(fMax), Math.round(f12));
                    } else {
                        jVar = jVar2;
                        actor.setBounds(f3, f14, fMax, f12);
                    }
                    if (layout != null) {
                        layout.validate();
                    }
                    i4 += i2;
                    verticalGroup = this;
                    jVar2 = jVar;
                    children = k0Var;
                    i3 = i5;
                    f10 = f13;
                    f11 = f14;
                    i7 = i10;
                }
            }
            f2 = height2 - verticalGroup.prefHeight;
            f9 += f2;
            float f102 = height2 - verticalGroup.padTop;
            int i72 = verticalGroup.columnAlign;
            j jVar22 = verticalGroup.columnSizes;
            k0<Actor> children2 = getChildren();
            int i82 = children2.f1750b;
            int i92 = 0;
            if (verticalGroup.reverse) {
            }
            float f112 = 0.0f;
            float fE22 = 0.0f;
            while (i4 != i3) {
            }
        }
        width = getWidth() - prefWidth;
        f5 += width;
        if ((i6 & 2) != 0) {
        }
        f9 += f2;
        float f1022 = height2 - verticalGroup.padTop;
        int i722 = verticalGroup.columnAlign;
        j jVar222 = verticalGroup.columnSizes;
        k0<Actor> children22 = getChildren();
        int i822 = children22.f1750b;
        int i922 = 0;
        if (verticalGroup.reverse) {
        }
        float f1122 = 0.0f;
        float fE222 = 0.0f;
        while (i4 != i3) {
        }
    }

    public VerticalGroup align(int i2) {
        this.align = i2;
        return this;
    }

    public VerticalGroup bottom() {
        this.align = (this.align | 4) & (-3);
        return this;
    }

    public VerticalGroup center() {
        this.align = 1;
        return this;
    }

    public VerticalGroup columnAlign(int i2) {
        this.columnAlign = i2;
        return this;
    }

    public VerticalGroup columnBottom() {
        this.columnAlign = (this.columnAlign | 4) & (-3);
        return this;
    }

    public VerticalGroup columnCenter() {
        this.columnAlign = 1;
        return this;
    }

    public VerticalGroup columnLeft() {
        this.columnAlign = (this.columnAlign | 8) & (-17);
        return this;
    }

    public VerticalGroup columnRight() {
        this.columnAlign = (this.columnAlign | 16) & (-9);
        return this;
    }

    public VerticalGroup columnTop() {
        this.columnAlign = (this.columnAlign | 2) & (-5);
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void drawDebugBounds(ShapeRenderer shapeRenderer) {
        super.drawDebugBounds(shapeRenderer);
        if (getDebug()) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            if (getStage() != null) {
                shapeRenderer.setColor(getStage().getDebugColor());
            }
            shapeRenderer.rect(getX() + this.padLeft, getY() + this.padBottom, getOriginX(), getOriginY(), (getWidth() - this.padLeft) - this.padRight, (getHeight() - this.padBottom) - this.padTop, getScaleX(), getScaleY(), getRotation());
        }
    }

    public VerticalGroup expand() {
        this.expand = true;
        return this;
    }

    public VerticalGroup fill() {
        this.fill = 1.0f;
        return this;
    }

    public int getAlign() {
        return this.align;
    }

    public boolean getExpand() {
        return this.expand;
    }

    public float getFill() {
        return this.fill;
    }

    public float getPadBottom() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.wrap) {
            return 0.0f;
        }
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public float getSpace() {
        return this.space;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public VerticalGroup grow() {
        this.expand = true;
        this.fill = 1.0f;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0085  */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        float height;
        int i2;
        int i3;
        int i4;
        float width;
        float height2;
        Layout layout;
        char c2;
        float f2;
        float f3;
        boolean z2;
        if (this.sizeInvalid) {
            computeSize();
        }
        if (this.wrap) {
            layoutWrapped();
            return;
        }
        boolean z3 = this.round;
        int i5 = this.align;
        float f4 = this.space;
        float width2 = this.padLeft;
        float f5 = this.fill;
        float width3 = ((this.expand ? getWidth() : this.prefWidth) - width2) - this.padRight;
        float f6 = (this.prefHeight - this.padTop) + f4;
        if ((i5 & 2) == 0) {
            if ((i5 & 4) == 0) {
                height = (getHeight() - this.prefHeight) / 2.0f;
            }
            if ((i5 & 8) == 0) {
                width2 = (i5 & 16) != 0 ? (getWidth() - this.padRight) - width3 : width2 + ((((getWidth() - width2) - this.padRight) - width3) / 2.0f);
            }
            int i6 = this.columnAlign;
            k0<Actor> children = getChildren();
            int i7 = children.f1750b;
            if (this.reverse) {
                i2 = i7;
                i3 = 0;
                i4 = 1;
            } else {
                i3 = i7 - 1;
                i2 = -1;
                i4 = -1;
            }
            while (i3 != i2) {
                Actor actor = children.get(i3);
                if (actor instanceof Layout) {
                    layout = (Layout) actor;
                    width = layout.getPrefWidth();
                    height2 = layout.getPrefHeight();
                } else {
                    width = actor.getWidth();
                    height2 = actor.getHeight();
                    layout = null;
                }
                float f7 = height2;
                if (f5 > 0.0f) {
                    width = width3 * f5;
                }
                if (layout != null) {
                    width = Math.max(width, layout.getMinWidth());
                    float maxWidth = layout.getMaxWidth();
                    if (maxWidth > 0.0f && width > maxWidth) {
                        width = maxWidth;
                    }
                }
                if ((i6 & 16) != 0) {
                    f2 = (width3 - width) + width2;
                    f3 = f7;
                    c2 = 0;
                } else {
                    if ((i6 & 8) == 0) {
                        c2 = 0;
                        f2 = ((width3 - width) / 2.0f) + width2;
                    } else {
                        c2 = 0;
                        f2 = width2;
                    }
                    f3 = f7;
                }
                f6 -= f3 + f4;
                if (z3) {
                    z2 = z3;
                    actor.setBounds(Math.round(f2), Math.round(f6), Math.round(width), Math.round(f3));
                } else {
                    z2 = z3;
                    actor.setBounds(f2, f6, width, f3);
                }
                if (layout != null) {
                    layout.validate();
                }
                i3 += i4;
                z3 = z2;
            }
        }
        height = getHeight() - this.prefHeight;
        f6 += height;
        if ((i5 & 8) == 0) {
        }
        int i62 = this.columnAlign;
        k0<Actor> children2 = getChildren();
        int i72 = children2.f1750b;
        if (this.reverse) {
        }
        while (i3 != i2) {
        }
    }

    public VerticalGroup left() {
        this.align = (this.align | 8) & (-17);
        return this;
    }

    public VerticalGroup pad(float f2) {
        this.padTop = f2;
        this.padLeft = f2;
        this.padBottom = f2;
        this.padRight = f2;
        return this;
    }

    public VerticalGroup padBottom(float f2) {
        this.padBottom = f2;
        return this;
    }

    public VerticalGroup padLeft(float f2) {
        this.padLeft = f2;
        return this;
    }

    public VerticalGroup padRight(float f2) {
        this.padRight = f2;
        return this;
    }

    public VerticalGroup padTop(float f2) {
        this.padTop = f2;
        return this;
    }

    public VerticalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public VerticalGroup right() {
        this.align = (this.align | 16) & (-9);
        return this;
    }

    public void setRound(boolean z2) {
        this.round = z2;
    }

    public VerticalGroup space(float f2) {
        this.space = f2;
        return this;
    }

    public VerticalGroup top() {
        this.align = (this.align | 2) & (-5);
        return this;
    }

    public VerticalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public VerticalGroup wrapSpace(float f2) {
        this.wrapSpace = f2;
        return this;
    }

    public VerticalGroup expand(boolean z2) {
        this.expand = z2;
        return this;
    }

    public VerticalGroup fill(float f2) {
        this.fill = f2;
        return this;
    }

    public VerticalGroup reverse(boolean z2) {
        this.reverse = z2;
        return this;
    }

    public VerticalGroup wrap(boolean z2) {
        this.wrap = z2;
        return this;
    }

    public VerticalGroup pad(float f2, float f3, float f4, float f5) {
        this.padTop = f2;
        this.padLeft = f3;
        this.padBottom = f4;
        this.padRight = f5;
        return this;
    }
}

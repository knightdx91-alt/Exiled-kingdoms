package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class HorizontalGroup extends WidgetGroup {
    private boolean expand;
    private float fill;
    private float lastPrefHeight;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private int rowAlign;
    private j rowSizes;
    private float space;
    private boolean wrap;
    private boolean wrapReverse;
    private float wrapSpace;
    private boolean sizeInvalid = true;
    private int align = 8;
    private boolean round = true;

    public HorizontalGroup() {
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
        float f2 = 0.0f;
        this.prefHeight = 0.0f;
        if (this.wrap) {
            this.prefWidth = 0.0f;
            j jVar = this.rowSizes;
            if (jVar == null) {
                this.rowSizes = new j();
            } else {
                jVar.f1824b = 0;
            }
            j jVar2 = this.rowSizes;
            float f3 = this.space;
            float f4 = this.wrapSpace;
            float f5 = this.padLeft + this.padRight;
            float width2 = getWidth() - f5;
            if (this.reverse) {
                i3 = i4 - 1;
                i4 = -1;
                i2 = -1;
            } else {
                i2 = 1;
            }
            float f6 = 0.0f;
            float fMax = 0.0f;
            float f7 = 0.0f;
            while (i3 != i4) {
                Actor actor = children.get(i3);
                if (actor instanceof Layout) {
                    Layout layout = (Layout) actor;
                    width = layout.getPrefWidth();
                    if (width > width2) {
                        width = Math.max(width2, layout.getMinWidth());
                    }
                    height = layout.getPrefHeight();
                } else {
                    width = actor.getWidth();
                    height = actor.getHeight();
                }
                float f8 = width + (f6 > f2 ? f3 : f2);
                if (f6 + f8 <= width2 || f6 <= f2) {
                    width = f8;
                } else {
                    jVar2.a(f6);
                    jVar2.a(fMax);
                    this.prefWidth = Math.max(this.prefWidth, f6 + f5);
                    if (f7 > 0.0f) {
                        f7 += f4;
                    }
                    f7 += fMax;
                    f6 = 0.0f;
                    fMax = 0.0f;
                }
                f6 += width;
                fMax = Math.max(fMax, height);
                i3 += i2;
                f2 = 0.0f;
            }
            jVar2.a(f6);
            jVar2.a(fMax);
            this.prefWidth = Math.max(this.prefWidth, f6 + f5);
            if (f7 > 0.0f) {
                f7 += f4;
            }
            this.prefHeight = Math.max(this.prefHeight, f7 + fMax);
        } else {
            this.prefWidth = (this.space * (i4 - 1)) + this.padLeft + this.padRight;
            while (i3 < i4) {
                Actor actor2 = children.get(i3);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.prefWidth = layout2.getPrefWidth() + this.prefWidth;
                    this.prefHeight = Math.max(this.prefHeight, layout2.getPrefHeight());
                } else {
                    this.prefWidth = actor2.getWidth() + this.prefWidth;
                    this.prefHeight = Math.max(this.prefHeight, actor2.getHeight());
                }
                i3++;
            }
        }
        this.prefHeight = this.padTop + this.padBottom + this.prefHeight;
        if (this.round) {
            this.prefWidth = Math.round(this.prefWidth);
            this.prefHeight = Math.round(this.prefHeight);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void layoutWrapped() {
        float height;
        float f2;
        float f3;
        int i2;
        int i3;
        int i4;
        int i5;
        float width;
        float height2;
        Layout layout;
        float f4;
        float fE;
        float f5;
        float f6;
        float f7;
        int i6;
        boolean z2;
        float prefHeight = getPrefHeight();
        if (prefHeight != this.lastPrefHeight) {
            this.lastPrefHeight = prefHeight;
            invalidateHierarchy();
        }
        int i7 = this.align;
        boolean z3 = this.round;
        float f8 = this.space;
        float f9 = this.fill;
        float f10 = this.wrapSpace;
        float f11 = (this.prefWidth - this.padLeft) - this.padRight;
        float fE2 = prefHeight - this.padTop;
        float width2 = getWidth();
        float f12 = this.padLeft;
        if ((i7 & 2) == 0) {
            if ((i7 & 4) == 0) {
                height = (getHeight() - prefHeight) / 2.0f;
            }
            if (this.wrapReverse) {
                f2 = -1.0f;
            } else {
                fE2 -= this.rowSizes.e(1) + prefHeight;
                f2 = 1.0f;
            }
            if ((i7 & 16) != 0) {
                if ((i7 & 8) == 0) {
                    f3 = (width2 - this.prefWidth) / 2.0f;
                }
                float f13 = width2 - this.padRight;
                int i8 = this.rowAlign;
                j jVar = this.rowSizes;
                k0<Actor> children = getChildren();
                int i9 = children.f1750b;
                if (this.reverse) {
                    i3 = i9 - 1;
                    i4 = -1;
                    i2 = -1;
                } else {
                    i2 = i9;
                    i3 = 0;
                    i4 = 1;
                }
                int i10 = 0;
                float f14 = 0.0f;
                float f15 = 0.0f;
                while (i3 != i2) {
                    int i11 = i2;
                    Actor actor = children.get(i3);
                    k0<Actor> k0Var = children;
                    if (actor instanceof Layout) {
                        layout = (Layout) actor;
                        width = layout.getPrefWidth();
                        if (width > f13) {
                            i5 = i4;
                            width = Math.max(f13, layout.getMinWidth());
                        } else {
                            i5 = i4;
                        }
                        height2 = layout.getPrefHeight();
                    } else {
                        i5 = i4;
                        width = actor.getWidth();
                        height2 = actor.getHeight();
                        layout = null;
                    }
                    float f16 = width;
                    float f17 = height2;
                    if (f14 + f16 > f13 || i10 == 0) {
                        if ((i8 & 16) != 0) {
                            fE = f11 - jVar.e(i10);
                        } else if ((i8 & 8) == 0) {
                            fE = (f11 - jVar.e(i10)) / 2.0f;
                        } else {
                            f4 = f12;
                            f5 = f11;
                            float fE3 = jVar.e(i10 + 1);
                            if (i10 > 0) {
                                fE2 = (f10 * f2) + fE2;
                            }
                            i10 += 2;
                            fE2 = (fE3 * f2) + fE2;
                            float f18 = f4;
                            f6 = fE3;
                            f7 = f18;
                        }
                        f4 = fE + f12;
                        f5 = f11;
                        float fE32 = jVar.e(i10 + 1);
                        if (i10 > 0) {
                        }
                        i10 += 2;
                        fE2 = (fE32 * f2) + fE2;
                        float f182 = f4;
                        f6 = fE32;
                        f7 = f182;
                    } else {
                        float f19 = f15;
                        f5 = f11;
                        f7 = f14;
                        f6 = f19;
                    }
                    if (f9 > 0.0f) {
                        f17 = f6 * f9;
                    }
                    int i12 = i10;
                    float fMax = f17;
                    float f20 = f2;
                    if (layout != null) {
                        fMax = Math.max(fMax, layout.getMinHeight());
                        float maxHeight = layout.getMaxHeight();
                        if (maxHeight > 0.0f && fMax > maxHeight) {
                            fMax = maxHeight;
                        }
                    }
                    float f21 = (i8 & 2) != 0 ? (f6 - fMax) + fE2 : (i8 & 4) == 0 ? ((f6 - fMax) / 2.0f) + fE2 : fE2;
                    if (z3) {
                        i6 = i8;
                        z2 = z3;
                        actor.setBounds(Math.round(f7), Math.round(f21), Math.round(f16), Math.round(fMax));
                    } else {
                        i6 = i8;
                        z2 = z3;
                        actor.setBounds(f7, f21, f16, fMax);
                    }
                    float f22 = f16 + f8 + f7;
                    if (layout != null) {
                        layout.validate();
                    }
                    i3 += i5;
                    f11 = f5;
                    children = k0Var;
                    i2 = i11;
                    f2 = f20;
                    i4 = i5;
                    i8 = i6;
                    z3 = z2;
                    f15 = f6;
                    f14 = f22;
                    i10 = i12;
                }
            }
            f3 = width2 - this.prefWidth;
            f12 += f3;
            float f132 = width2 - this.padRight;
            int i82 = this.rowAlign;
            j jVar2 = this.rowSizes;
            k0<Actor> children2 = getChildren();
            int i92 = children2.f1750b;
            if (this.reverse) {
            }
            int i102 = 0;
            float f142 = 0.0f;
            float f152 = 0.0f;
            while (i3 != i2) {
            }
        }
        height = getHeight() - prefHeight;
        fE2 += height;
        if (this.wrapReverse) {
        }
        if ((i7 & 16) != 0) {
        }
        f12 += f3;
        float f1322 = width2 - this.padRight;
        int i822 = this.rowAlign;
        j jVar22 = this.rowSizes;
        k0<Actor> children22 = getChildren();
        int i922 = children22.f1750b;
        if (this.reverse) {
        }
        int i1022 = 0;
        float f1422 = 0.0f;
        float f1522 = 0.0f;
        while (i3 != i2) {
        }
    }

    public HorizontalGroup align(int i2) {
        this.align = i2;
        return this;
    }

    public HorizontalGroup bottom() {
        this.align = (this.align | 4) & (-3);
        return this;
    }

    public HorizontalGroup center() {
        this.align = 1;
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

    public HorizontalGroup expand() {
        this.expand = true;
        return this;
    }

    public HorizontalGroup fill() {
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
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.wrap) {
            return 0.0f;
        }
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

    public boolean getWrapReverse() {
        return this.wrapReverse;
    }

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public HorizontalGroup grow() {
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
    /* JADX WARN: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        float width;
        int i2;
        int i3;
        int i4;
        float width2;
        float height;
        Layout layout;
        char c2;
        float f2;
        boolean z2;
        int i5;
        if (this.sizeInvalid) {
            computeSize();
        }
        if (this.wrap) {
            layoutWrapped();
            return;
        }
        boolean z3 = this.round;
        int i6 = this.align;
        float f3 = this.space;
        float height2 = this.padBottom;
        float f4 = this.fill;
        float height3 = ((this.expand ? getHeight() : this.prefHeight) - this.padTop) - height2;
        float f5 = this.padLeft;
        if ((i6 & 16) == 0) {
            if ((i6 & 8) == 0) {
                width = (getWidth() - this.prefWidth) / 2.0f;
            }
            if ((i6 & 4) == 0) {
                height2 = (i6 & 2) != 0 ? (getHeight() - this.padTop) - height3 : height2 + ((((getHeight() - height2) - this.padTop) - height3) / 2.0f);
            }
            int i7 = this.rowAlign;
            k0<Actor> children = getChildren();
            int i8 = children.f1750b;
            if (this.reverse) {
                i2 = i8;
                i3 = 0;
                i4 = 1;
            } else {
                i3 = i8 - 1;
                i2 = -1;
                i4 = -1;
            }
            while (i3 != i2) {
                Actor actor = children.get(i3);
                if (actor instanceof Layout) {
                    layout = (Layout) actor;
                    width2 = layout.getPrefWidth();
                    height = layout.getPrefHeight();
                } else {
                    width2 = actor.getWidth();
                    height = actor.getHeight();
                    layout = null;
                }
                if (f4 > 0.0f) {
                    height = height3 * f4;
                }
                float fMax = height;
                if (layout != null) {
                    fMax = Math.max(fMax, layout.getMinHeight());
                    float maxHeight = layout.getMaxHeight();
                    if (maxHeight > 0.0f && fMax > maxHeight) {
                        fMax = maxHeight;
                    }
                }
                if ((i7 & 2) != 0) {
                    f2 = (height3 - fMax) + height2;
                    c2 = 0;
                } else if ((i7 & 4) == 0) {
                    c2 = 0;
                    f2 = ((height3 - fMax) / 2.0f) + height2;
                } else {
                    c2 = 0;
                    f2 = height2;
                }
                if (z3) {
                    z2 = z3;
                    i5 = i7;
                    actor.setBounds(Math.round(f5), Math.round(f2), Math.round(width2), Math.round(fMax));
                } else {
                    z2 = z3;
                    i5 = i7;
                    actor.setBounds(f5, f2, width2, fMax);
                }
                f5 += width2 + f3;
                if (layout != null) {
                    layout.validate();
                }
                i3 += i4;
                z3 = z2;
                i7 = i5;
            }
        }
        width = getWidth() - this.prefWidth;
        f5 += width;
        if ((i6 & 4) == 0) {
        }
        int i72 = this.rowAlign;
        k0<Actor> children2 = getChildren();
        int i82 = children2.f1750b;
        if (this.reverse) {
        }
        while (i3 != i2) {
        }
    }

    public HorizontalGroup left() {
        this.align = (this.align | 8) & (-17);
        return this;
    }

    public HorizontalGroup pad(float f2) {
        this.padTop = f2;
        this.padLeft = f2;
        this.padBottom = f2;
        this.padRight = f2;
        return this;
    }

    public HorizontalGroup padBottom(float f2) {
        this.padBottom = f2;
        return this;
    }

    public HorizontalGroup padLeft(float f2) {
        this.padLeft = f2;
        return this;
    }

    public HorizontalGroup padRight(float f2) {
        this.padRight = f2;
        return this;
    }

    public HorizontalGroup padTop(float f2) {
        this.padTop = f2;
        return this;
    }

    public HorizontalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public HorizontalGroup right() {
        this.align = (this.align | 16) & (-9);
        return this;
    }

    public HorizontalGroup rowAlign(int i2) {
        this.rowAlign = i2;
        return this;
    }

    public HorizontalGroup rowBottom() {
        this.rowAlign = (this.rowAlign | 4) & (-3);
        return this;
    }

    public HorizontalGroup rowCenter() {
        this.rowAlign = 1;
        return this;
    }

    public HorizontalGroup rowLeft() {
        this.rowAlign = (this.rowAlign | 8) & (-17);
        return this;
    }

    public HorizontalGroup rowRight() {
        this.rowAlign = (this.rowAlign | 16) & (-9);
        return this;
    }

    public HorizontalGroup rowTop() {
        this.rowAlign = (this.rowAlign | 2) & (-5);
        return this;
    }

    public void setRound(boolean z2) {
        this.round = z2;
    }

    public HorizontalGroup space(float f2) {
        this.space = f2;
        return this;
    }

    public HorizontalGroup top() {
        this.align = (this.align | 2) & (-5);
        return this;
    }

    public HorizontalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public HorizontalGroup wrapReverse() {
        this.wrapReverse = true;
        return this;
    }

    public HorizontalGroup wrapSpace(float f2) {
        this.wrapSpace = f2;
        return this;
    }

    public HorizontalGroup expand(boolean z2) {
        this.expand = z2;
        return this;
    }

    public HorizontalGroup fill(float f2) {
        this.fill = f2;
        return this;
    }

    public HorizontalGroup reverse(boolean z2) {
        this.reverse = z2;
        return this;
    }

    public HorizontalGroup wrap(boolean z2) {
        this.wrap = z2;
        return this;
    }

    public HorizontalGroup wrapReverse(boolean z2) {
        this.wrapReverse = z2;
        return this;
    }

    public HorizontalGroup pad(float f2, float f3, float f4, float f5) {
        this.padTop = f2;
        this.padLeft = f3;
        this.padBottom = f4;
        this.padRight = f5;
        return this;
    }
}

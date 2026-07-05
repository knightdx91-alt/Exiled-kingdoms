package com.badlogic.gdx.scenes.scene2d.ui;

import a.a;
import a0.h;
import a0.j;
import a0.p;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScrollPane extends WidgetGroup {
    float amountX;
    float amountY;
    boolean cancelTouchFocus;
    private boolean clamp;
    boolean disableX;
    boolean disableY;
    int draggingPointer;
    float fadeAlpha;
    float fadeAlphaSeconds;
    float fadeDelay;
    float fadeDelaySeconds;
    boolean fadeScrollBars;
    boolean flickScroll;
    private ActorGestureListener flickScrollListener;
    float flingTime;
    float flingTimer;
    private boolean forceScrollX;
    private boolean forceScrollY;
    final p hKnobBounds;
    final p hScrollBounds;
    boolean hScrollOnBottom;
    final q lastPoint;
    float maxX;
    float maxY;
    private float overscrollDistance;
    private float overscrollSpeedMax;
    private float overscrollSpeedMin;
    private boolean overscrollX;
    private boolean overscrollY;
    boolean scrollBarTouch;
    boolean scrollX;
    boolean scrollY;
    private boolean scrollbarsOnTop;
    boolean smoothScrolling;
    private ScrollPaneStyle style;
    boolean touchScrollH;
    boolean touchScrollV;
    final p vKnobBounds;
    final p vScrollBounds;
    boolean vScrollOnRight;
    private boolean variableSizeKnobs;
    float velocityX;
    float velocityY;
    float visualAmountX;
    float visualAmountY;
    private Actor widget;
    final p widgetArea;
    private final p widgetCullingArea;

    public static class ScrollPaneStyle {
        public Drawable background;
        public Drawable corner;
        public Drawable hScroll;
        public Drawable hScrollKnob;
        public Drawable vScroll;
        public Drawable vScrollKnob;

        public ScrollPaneStyle() {
        }

        public ScrollPaneStyle(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5) {
            this.background = drawable;
            this.hScroll = drawable2;
            this.hScrollKnob = drawable3;
            this.vScroll = drawable4;
            this.vScrollKnob = drawable5;
        }

        public ScrollPaneStyle(ScrollPaneStyle scrollPaneStyle) {
            this.background = scrollPaneStyle.background;
            this.corner = scrollPaneStyle.corner;
            this.hScroll = scrollPaneStyle.hScroll;
            this.hScrollKnob = scrollPaneStyle.hScrollKnob;
            this.vScroll = scrollPaneStyle.vScroll;
            this.vScrollKnob = scrollPaneStyle.vScrollKnob;
        }
    }

    public ScrollPane(Actor actor) {
        this(actor, new ScrollPaneStyle());
    }

    private void updateWidgetPosition() {
        p pVar = this.widgetArea;
        float f2 = pVar.f89x - (this.scrollX ? (int) this.visualAmountX : 0);
        float f3 = pVar.f90y - ((int) (this.scrollY ? this.maxY - this.visualAmountY : this.maxY));
        this.widget.setPosition(f2, f3);
        Object obj = this.widget;
        if (obj instanceof Cullable) {
            p pVar2 = this.widgetCullingArea;
            p pVar3 = this.widgetArea;
            pVar2.f89x = pVar3.f89x - f2;
            pVar2.f90y = pVar3.f90y - f3;
            pVar2.width = pVar3.width;
            pVar2.height = pVar3.height;
            ((Cullable) obj).setCullingArea(pVar2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01f8 A[PHI: r1
      0x01f8: PHI (r1v5 boolean) = (r1v4 boolean), (r1v6 boolean), (r1v6 boolean), (r1v6 boolean) binds: [B:71:0x0155, B:88:0x01aa, B:90:0x01ae, B:97:0x01d6] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void act(float f2) {
        boolean z2;
        Stage stage;
        super.act(f2);
        boolean zIsPanning = this.flickScrollListener.getGestureDetector().isPanning();
        float f3 = this.fadeAlpha;
        boolean z3 = true;
        if (f3 <= 0.0f || !this.fadeScrollBars || zIsPanning || this.touchScrollH || this.touchScrollV) {
            z2 = false;
        } else {
            float f4 = this.fadeDelay - f2;
            this.fadeDelay = f4;
            if (f4 <= 0.0f) {
                this.fadeAlpha = Math.max(0.0f, f3 - f2);
            }
            z2 = true;
        }
        if (this.flingTimer > 0.0f) {
            setScrollbarsVisible(true);
            float f5 = this.flingTimer / this.flingTime;
            this.amountX = a.z(this.velocityX, f5, f2, this.amountX);
            this.amountY = a.z(this.velocityY, f5, f2, this.amountY);
            clamp();
            float f6 = this.amountX;
            float f7 = this.overscrollDistance;
            if (f6 == (-f7)) {
                this.velocityX = 0.0f;
            }
            if (f6 >= this.maxX + f7) {
                this.velocityX = 0.0f;
            }
            float f8 = this.amountY;
            if (f8 == (-f7)) {
                this.velocityY = 0.0f;
            }
            if (f8 >= this.maxY + f7) {
                this.velocityY = 0.0f;
            }
            float f9 = this.flingTimer - f2;
            this.flingTimer = f9;
            if (f9 <= 0.0f) {
                this.velocityX = 0.0f;
                this.velocityY = 0.0f;
            }
            z2 = true;
        }
        if (!this.smoothScrolling || this.flingTimer > 0.0f || zIsPanning || ((this.touchScrollH && (!this.scrollX || this.maxX / (this.hScrollBounds.width - this.hKnobBounds.width) <= this.widgetArea.width * 0.1f)) || (this.touchScrollV && (!this.scrollY || this.maxY / (this.vScrollBounds.height - this.vKnobBounds.height) <= this.widgetArea.height * 0.1f)))) {
            float f10 = this.visualAmountX;
            float f11 = this.amountX;
            if (f10 != f11) {
                visualScrollX(f11);
            }
            float f12 = this.visualAmountY;
            float f13 = this.amountY;
            if (f12 != f13) {
                visualScrollY(f13);
            }
        } else {
            float f14 = this.visualAmountX;
            float f15 = this.amountX;
            if (f14 != f15) {
                if (f14 < f15) {
                    visualScrollX(Math.min(f15, Math.max(f2 * 200.0f, (f15 - f14) * 7.0f * f2) + f14));
                } else {
                    visualScrollX(Math.max(f15, f14 - Math.max(f2 * 200.0f, ((f14 - f15) * 7.0f) * f2)));
                }
                z2 = true;
            }
            float f16 = this.visualAmountY;
            float f17 = this.amountY;
            if (f16 != f17) {
                if (f16 < f17) {
                    visualScrollY(Math.min(f17, Math.max(200.0f * f2, (f17 - f16) * 7.0f * f2) + f16));
                } else {
                    visualScrollY(Math.max(f17, f16 - Math.max(200.0f * f2, ((f16 - f17) * 7.0f) * f2)));
                }
                z2 = true;
            }
        }
        if (zIsPanning) {
            z3 = z2;
        } else {
            if (this.overscrollX && this.scrollX) {
                float f18 = this.amountX;
                if (f18 < 0.0f) {
                    setScrollbarsVisible(true);
                    float f19 = this.amountX;
                    float f20 = this.overscrollSpeedMin;
                    float f21 = (((((this.overscrollSpeedMax - f20) * (-f19)) / this.overscrollDistance) + f20) * f2) + f19;
                    this.amountX = f21;
                    if (f21 > 0.0f) {
                        scrollX(0.0f);
                    }
                } else if (f18 > this.maxX) {
                    setScrollbarsVisible(true);
                    float f22 = this.amountX;
                    float f23 = this.overscrollSpeedMin;
                    float f24 = this.overscrollSpeedMax - f23;
                    float f25 = this.maxX;
                    float f26 = f22 - ((((f24 * (-(f25 - f22))) / this.overscrollDistance) + f23) * f2);
                    this.amountX = f26;
                    if (f26 < f25) {
                        scrollX(f25);
                    }
                }
                z2 = true;
            }
            if (this.overscrollY && this.scrollY) {
                float f27 = this.amountY;
                if (f27 < 0.0f) {
                    setScrollbarsVisible(true);
                    float f28 = this.amountY;
                    float f29 = this.overscrollSpeedMin;
                    float f30 = (((((this.overscrollSpeedMax - f29) * (-f28)) / this.overscrollDistance) + f29) * f2) + f28;
                    this.amountY = f30;
                    if (f30 > 0.0f) {
                        scrollY(0.0f);
                    }
                } else if (f27 > this.maxY) {
                    setScrollbarsVisible(true);
                    float f31 = this.amountY;
                    float f32 = this.overscrollSpeedMin;
                    float f33 = this.overscrollSpeedMax - f32;
                    float f34 = this.maxY;
                    float f35 = f31 - ((((f33 * (-(f34 - f31))) / this.overscrollDistance) + f32) * f2);
                    this.amountY = f35;
                    if (f35 < f34) {
                        scrollY(f34);
                    }
                }
            }
        }
        if (z3 && (stage = getStage()) != null && stage.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAt(int i2, Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    public void cancel() {
        this.draggingPointer = -1;
        this.touchScrollH = false;
        this.touchScrollV = false;
        this.flickScrollListener.getGestureDetector().cancel();
    }

    public void cancelTouchFocus() {
        Stage stage = getStage();
        if (stage != null) {
            stage.cancelTouchFocusExcept(this.flickScrollListener, this);
        }
    }

    void clamp() {
        float fA;
        float fA2;
        if (this.clamp) {
            if (this.overscrollX) {
                float f2 = this.amountX;
                float f3 = this.overscrollDistance;
                fA = j.a(f2, -f3, this.maxX + f3);
            } else {
                fA = j.a(this.amountX, 0.0f, this.maxX);
            }
            scrollX(fA);
            if (this.overscrollY) {
                float f4 = this.amountY;
                float f5 = this.overscrollDistance;
                fA2 = j.a(f4, -f5, this.maxY + f5);
            } else {
                fA2 = j.a(this.amountY, 0.0f, this.maxY);
            }
            scrollY(fA2);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        if (this.widget == null) {
            return;
        }
        validate();
        applyTransform(batch, computeTransform());
        if (this.scrollX) {
            this.hKnobBounds.f89x = this.hScrollBounds.f89x + ((int) (getVisualScrollPercentX() * (r1.width - r0.width)));
        }
        if (this.scrollY) {
            this.vKnobBounds.f90y = this.vScrollBounds.f90y + ((int) ((1.0f - getVisualScrollPercentY()) * (r1.height - r0.height)));
        }
        updateWidgetPosition();
        Color color = getColor();
        float fA = color.f1677a * f2;
        if (this.style.background != null) {
            batch.setColor(color.f1680r, color.f1679g, color.f1678b, fA);
            this.style.background.draw(batch, 0.0f, 0.0f, getWidth(), getHeight());
        }
        batch.flush();
        p pVar = this.widgetArea;
        if (clipBegin(pVar.f89x, pVar.f90y, pVar.width, pVar.height)) {
            drawChildren(batch, f2);
            batch.flush();
            clipEnd();
        }
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, fA);
        if (this.fadeScrollBars) {
            fA *= h.f53b.a(this.fadeAlpha / this.fadeAlphaSeconds);
        }
        drawScrollBars(batch, color.f1680r, color.f1679g, color.f1678b, fA);
        resetTransform(batch);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        drawDebugBounds(shapeRenderer);
        applyTransform(shapeRenderer, computeTransform());
        p pVar = this.widgetArea;
        if (clipBegin(pVar.f89x, pVar.f90y, pVar.width, pVar.height)) {
            drawDebugChildren(shapeRenderer);
            shapeRenderer.flush();
            clipEnd();
        }
        resetTransform(shapeRenderer);
    }

    protected void drawScrollBars(Batch batch, float f2, float f3, float f4, float f5) {
        Drawable drawable;
        if (f5 <= 0.0f) {
            return;
        }
        batch.setColor(f2, f3, f4, f5);
        boolean z2 = false;
        boolean z3 = this.scrollX && this.hKnobBounds.width > 0.0f;
        if (this.scrollY && this.vKnobBounds.height > 0.0f) {
            z2 = true;
        }
        if (z3 && z2 && (drawable = this.style.corner) != null) {
            p pVar = this.hScrollBounds;
            float f6 = pVar.f89x + pVar.width;
            float f7 = pVar.f90y;
            p pVar2 = this.vScrollBounds;
            drawable.draw(batch, f6, f7, pVar2.width, pVar2.f90y);
        }
        if (z3) {
            Drawable drawable2 = this.style.hScroll;
            if (drawable2 != null) {
                p pVar3 = this.hScrollBounds;
                drawable2.draw(batch, pVar3.f89x, pVar3.f90y, pVar3.width, pVar3.height);
            }
            Drawable drawable3 = this.style.hScrollKnob;
            if (drawable3 != null) {
                p pVar4 = this.hKnobBounds;
                drawable3.draw(batch, pVar4.f89x, pVar4.f90y, pVar4.width, pVar4.height);
            }
        }
        if (z2) {
            Drawable drawable4 = this.style.vScroll;
            if (drawable4 != null) {
                p pVar5 = this.vScrollBounds;
                drawable4.draw(batch, pVar5.f89x, pVar5.f90y, pVar5.width, pVar5.height);
            }
            Drawable drawable5 = this.style.vScrollKnob;
            if (drawable5 != null) {
                p pVar6 = this.vKnobBounds;
                drawable5.draw(batch, pVar6.f89x, pVar6.f90y, pVar6.width, pVar6.height);
            }
        }
    }

    public void fling(float f2, float f3, float f4) {
        this.flingTimer = f2;
        this.velocityX = f3;
        this.velocityY = f4;
    }

    public Actor getActor() {
        return this.widget;
    }

    public boolean getFadeScrollBars() {
        return this.fadeScrollBars;
    }

    public float getMaxX() {
        return this.maxX;
    }

    public float getMaxY() {
        return this.maxY;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    protected float getMouseWheelX() {
        float f2 = this.widgetArea.width;
        return Math.min(f2, Math.max(0.9f * f2, this.maxX * 0.1f) / 4.0f);
    }

    protected float getMouseWheelY() {
        float f2 = this.widgetArea.height;
        return Math.min(f2, Math.max(0.9f * f2, this.maxY * 0.1f) / 4.0f);
    }

    public float getOverscrollDistance() {
        return this.overscrollDistance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        Actor actor = this.widget;
        float prefHeight = actor instanceof Layout ? ((Layout) actor).getPrefHeight() : actor != 0 ? actor.getHeight() : 0.0f;
        Drawable drawable = this.style.background;
        if (drawable != null) {
            prefHeight = Math.max(drawable.getBottomHeight() + drawable.getTopHeight() + prefHeight, drawable.getMinHeight());
        }
        if (!this.scrollX) {
            return prefHeight;
        }
        Drawable drawable2 = this.style.hScrollKnob;
        float minHeight = drawable2 != null ? drawable2.getMinHeight() : 0.0f;
        Drawable drawable3 = this.style.hScroll;
        if (drawable3 != null) {
            minHeight = Math.max(minHeight, drawable3.getMinHeight());
        }
        return prefHeight + minHeight;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        Actor actor = this.widget;
        float prefWidth = actor instanceof Layout ? ((Layout) actor).getPrefWidth() : actor != 0 ? actor.getWidth() : 0.0f;
        Drawable drawable = this.style.background;
        if (drawable != null) {
            prefWidth = Math.max(drawable.getRightWidth() + drawable.getLeftWidth() + prefWidth, drawable.getMinWidth());
        }
        if (!this.scrollY) {
            return prefWidth;
        }
        Drawable drawable2 = this.style.vScrollKnob;
        float minWidth = drawable2 != null ? drawable2.getMinWidth() : 0.0f;
        Drawable drawable3 = this.style.vScroll;
        if (drawable3 != null) {
            minWidth = Math.max(minWidth, drawable3.getMinWidth());
        }
        return prefWidth + minWidth;
    }

    public float getScrollBarHeight() {
        if (!this.scrollX) {
            return 0.0f;
        }
        Drawable drawable = this.style.hScrollKnob;
        float minHeight = drawable != null ? drawable.getMinHeight() : 0.0f;
        Drawable drawable2 = this.style.hScroll;
        return drawable2 != null ? Math.max(minHeight, drawable2.getMinHeight()) : minHeight;
    }

    public float getScrollBarWidth() {
        if (!this.scrollY) {
            return 0.0f;
        }
        Drawable drawable = this.style.vScrollKnob;
        float minWidth = drawable != null ? drawable.getMinWidth() : 0.0f;
        Drawable drawable2 = this.style.vScroll;
        return drawable2 != null ? Math.max(minWidth, drawable2.getMinWidth()) : minWidth;
    }

    public float getScrollHeight() {
        return this.widgetArea.height;
    }

    public float getScrollPercentX() {
        float f2 = this.maxX;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return j.a(this.amountX / f2, 0.0f, 1.0f);
    }

    public float getScrollPercentY() {
        float f2 = this.maxY;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return j.a(this.amountY / f2, 0.0f, 1.0f);
    }

    public float getScrollWidth() {
        return this.widgetArea.width;
    }

    public float getScrollX() {
        return this.amountX;
    }

    public float getScrollY() {
        return this.amountY;
    }

    public ScrollPaneStyle getStyle() {
        return this.style;
    }

    public boolean getVariableSizeKnobs() {
        return this.variableSizeKnobs;
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public float getVisualScrollPercentX() {
        float f2 = this.maxX;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return j.a(this.visualAmountX / f2, 0.0f, 1.0f);
    }

    public float getVisualScrollPercentY() {
        float f2 = this.maxY;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return j.a(this.visualAmountY / f2, 0.0f, 1.0f);
    }

    public float getVisualScrollX() {
        if (this.scrollX) {
            return this.visualAmountX;
        }
        return 0.0f;
    }

    public float getVisualScrollY() {
        if (this.scrollY) {
            return this.visualAmountY;
        }
        return 0.0f;
    }

    @Deprecated
    public Actor getWidget() {
        return this.widget;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if (f2 < 0.0f || f2 >= getWidth() || f3 < 0.0f || f3 >= getHeight()) {
            return null;
        }
        if (z2 && getTouchable() == Touchable.enabled && isVisible()) {
            if (this.scrollX && this.touchScrollH && this.hScrollBounds.contains(f2, f3)) {
                return this;
            }
            if (this.scrollY && this.touchScrollV && this.vScrollBounds.contains(f2, f3)) {
                return this;
            }
        }
        return super.hit(f2, f3, z2);
    }

    public boolean isBottomEdge() {
        return !this.scrollY || this.amountY >= this.maxY;
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public boolean isFlinging() {
        return this.flingTimer > 0.0f;
    }

    public boolean isForceScrollX() {
        return this.forceScrollX;
    }

    public boolean isForceScrollY() {
        return this.forceScrollY;
    }

    public boolean isLeftEdge() {
        return !this.scrollX || this.amountX <= 0.0f;
    }

    public boolean isPanning() {
        return this.flickScrollListener.getGestureDetector().isPanning();
    }

    public boolean isRightEdge() {
        return !this.scrollX || this.amountX >= this.maxX;
    }

    public boolean isScrollX() {
        return this.scrollX;
    }

    public boolean isScrollY() {
        return this.scrollY;
    }

    public boolean isScrollingDisabledX() {
        return this.disableX;
    }

    public boolean isScrollingDisabledY() {
        return this.disableY;
    }

    public boolean isTopEdge() {
        return !this.scrollY || this.amountY <= 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float bottomHeight;
        float minWidth;
        float rightWidth;
        float topHeight;
        float width;
        float height;
        float f2;
        ScrollPaneStyle scrollPaneStyle = this.style;
        Drawable drawable = scrollPaneStyle.background;
        Drawable drawable2 = scrollPaneStyle.hScrollKnob;
        Drawable drawable3 = scrollPaneStyle.vScrollKnob;
        if (drawable != null) {
            minWidth = drawable.getLeftWidth();
            rightWidth = drawable.getRightWidth();
            topHeight = drawable.getTopHeight();
            bottomHeight = drawable.getBottomHeight();
        } else {
            bottomHeight = 0.0f;
            minWidth = 0.0f;
            rightWidth = 0.0f;
            topHeight = 0.0f;
        }
        float width2 = getWidth();
        float height2 = getHeight() - topHeight;
        this.widgetArea.set(minWidth, bottomHeight, (width2 - minWidth) - rightWidth, height2 - bottomHeight);
        if (this.widget == null) {
            return;
        }
        float minHeight = drawable2 != null ? drawable2.getMinHeight() : 0.0f;
        Drawable drawable4 = this.style.hScroll;
        if (drawable4 != null) {
            minHeight = Math.max(minHeight, drawable4.getMinHeight());
        }
        float minWidth2 = drawable3 != null ? drawable3.getMinWidth() : 0.0f;
        Drawable drawable5 = this.style.vScroll;
        if (drawable5 != null) {
            minWidth2 = Math.max(minWidth2, drawable5.getMinWidth());
        }
        Actor actor = this.widget;
        if (actor instanceof Layout) {
            Layout layout = (Layout) actor;
            width = layout.getPrefWidth();
            height = layout.getPrefHeight();
        } else {
            width = actor.getWidth();
            height = this.widget.getHeight();
        }
        boolean z2 = this.forceScrollX || (width > this.widgetArea.width && !this.disableX);
        this.scrollX = z2;
        boolean z3 = this.forceScrollY || (height > this.widgetArea.height && !this.disableY);
        this.scrollY = z3;
        if (this.scrollbarsOnTop) {
            f2 = bottomHeight;
        } else {
            if (z3) {
                p pVar = this.widgetArea;
                float f3 = pVar.width - minWidth2;
                pVar.width = f3;
                f2 = bottomHeight;
                if (!this.vScrollOnRight) {
                    pVar.f89x += minWidth2;
                }
                if (!z2 && width > f3 && !this.disableX) {
                    this.scrollX = true;
                }
            } else {
                f2 = bottomHeight;
            }
            if (this.scrollX) {
                p pVar2 = this.widgetArea;
                float f4 = pVar2.height - minHeight;
                pVar2.height = f4;
                if (this.hScrollOnBottom) {
                    pVar2.f90y += minHeight;
                }
                if (!z3 && height > f4 && !this.disableY) {
                    this.scrollY = true;
                    pVar2.width -= minWidth2;
                    if (!this.vScrollOnRight) {
                        pVar2.f89x += minWidth2;
                    }
                }
            }
        }
        float fMax = this.disableX ? this.widgetArea.width : Math.max(this.widgetArea.width, width);
        float fMax2 = this.disableY ? this.widgetArea.height : Math.max(this.widgetArea.height, height);
        p pVar3 = this.widgetArea;
        float f5 = fMax - pVar3.width;
        this.maxX = f5;
        this.maxY = fMax2 - pVar3.height;
        scrollX(j.a(this.amountX, 0.0f, f5));
        scrollY(j.a(this.amountY, 0.0f, this.maxY));
        if (this.scrollX) {
            if (drawable2 != null) {
                this.hScrollBounds.set(this.scrollbarsOnTop ? minWidth : this.widgetArea.f89x, this.hScrollOnBottom ? f2 : height2 - minHeight, this.widgetArea.width, minHeight);
                if (this.scrollY && this.scrollbarsOnTop) {
                    p pVar4 = this.hScrollBounds;
                    pVar4.width -= minWidth2;
                    if (!this.vScrollOnRight) {
                        pVar4.f89x += minWidth2;
                    }
                }
                if (this.variableSizeKnobs) {
                    this.hKnobBounds.width = Math.max(drawable2.getMinWidth(), (int) ((this.hScrollBounds.width * this.widgetArea.width) / fMax));
                } else {
                    this.hKnobBounds.width = drawable2.getMinWidth();
                }
                p pVar5 = this.hKnobBounds;
                if (pVar5.width > fMax) {
                    pVar5.width = 0.0f;
                }
                pVar5.height = drawable2.getMinHeight();
                this.hKnobBounds.f89x = this.hScrollBounds.f89x + ((int) (getScrollPercentX() * (r9.width - r3.width)));
                this.hKnobBounds.f90y = this.hScrollBounds.f90y;
            } else {
                this.hScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.hKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        if (this.scrollY) {
            if (drawable3 != null) {
                this.vScrollBounds.set(this.vScrollOnRight ? (width2 - rightWidth) - minWidth2 : minWidth, this.scrollbarsOnTop ? f2 : this.widgetArea.f90y, minWidth2, this.widgetArea.height);
                if (this.scrollX && this.scrollbarsOnTop) {
                    p pVar6 = this.vScrollBounds;
                    pVar6.height -= minHeight;
                    if (this.hScrollOnBottom) {
                        pVar6.f90y += minHeight;
                    }
                }
                this.vKnobBounds.width = drawable3.getMinWidth();
                if (this.variableSizeKnobs) {
                    this.vKnobBounds.height = Math.max(drawable3.getMinHeight(), (int) ((this.vScrollBounds.height * this.widgetArea.height) / fMax2));
                } else {
                    this.vKnobBounds.height = drawable3.getMinHeight();
                }
                p pVar7 = this.vKnobBounds;
                if (pVar7.height > fMax2) {
                    pVar7.height = 0.0f;
                }
                if (this.vScrollOnRight) {
                    minWidth = (width2 - rightWidth) - drawable3.getMinWidth();
                }
                pVar7.f89x = minWidth;
                this.vKnobBounds.f90y = this.vScrollBounds.f90y + ((int) ((1.0f - getScrollPercentY()) * (r3.height - r1.height)));
            } else {
                this.vScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.vKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        updateWidgetPosition();
        Actor actor2 = this.widget;
        if (actor2 instanceof Layout) {
            actor2.setSize(fMax, fMax2);
            ((Layout) this.widget).validate();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.widget) {
            return false;
        }
        setActor(null);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i2, boolean z2) {
        Actor actorRemoveActorAt = super.removeActorAt(i2, z2);
        if (actorRemoveActorAt == this.widget) {
            this.widget = null;
        }
        return actorRemoveActorAt;
    }

    public void scrollTo(float f2, float f3, float f4, float f5) {
        scrollTo(f2, f3, f4, f5, false, false);
    }

    protected void scrollX(float f2) {
        this.amountX = f2;
    }

    protected void scrollY(float f2) {
        this.amountY = f2;
    }

    public void setActor(Actor actor) {
        Actor actor2 = this.widget;
        if (actor2 == this) {
            throw new IllegalArgumentException("widget cannot be the ScrollPane.");
        }
        if (actor2 != null) {
            super.removeActor(actor2);
        }
        this.widget = actor;
        if (actor != null) {
            super.addActor(actor);
        }
    }

    public void setCancelTouchFocus(boolean z2) {
        this.cancelTouchFocus = z2;
    }

    public void setClamp(boolean z2) {
        this.clamp = z2;
    }

    public void setFadeScrollBars(boolean z2) {
        if (this.fadeScrollBars == z2) {
            return;
        }
        this.fadeScrollBars = z2;
        if (!z2) {
            this.fadeAlpha = this.fadeAlphaSeconds;
        }
        invalidate();
    }

    public void setFlickScroll(boolean z2) {
        if (this.flickScroll == z2) {
            return;
        }
        this.flickScroll = z2;
        if (z2) {
            addListener(this.flickScrollListener);
        } else {
            removeListener(this.flickScrollListener);
        }
        invalidate();
    }

    public void setFlickScrollTapSquareSize(float f2) {
        this.flickScrollListener.getGestureDetector().setTapSquareSize(f2);
    }

    public void setFlingTime(float f2) {
        this.flingTime = f2;
    }

    public void setForceScroll(boolean z2, boolean z3) {
        this.forceScrollX = z2;
        this.forceScrollY = z3;
    }

    public void setOverscroll(boolean z2, boolean z3) {
        this.overscrollX = z2;
        this.overscrollY = z3;
    }

    public void setScrollBarPositions(boolean z2, boolean z3) {
        this.hScrollOnBottom = z2;
        this.vScrollOnRight = z3;
    }

    public void setScrollBarTouch(boolean z2) {
        this.scrollBarTouch = z2;
    }

    public void setScrollPercentX(float f2) {
        scrollX(j.a(f2, 0.0f, 1.0f) * this.maxX);
    }

    public void setScrollPercentY(float f2) {
        scrollY(j.a(f2, 0.0f, 1.0f) * this.maxY);
    }

    public void setScrollX(float f2) {
        scrollX(j.a(f2, 0.0f, this.maxX));
    }

    public void setScrollY(float f2) {
        scrollY(j.a(f2, 0.0f, this.maxY));
    }

    public void setScrollbarsOnTop(boolean z2) {
        this.scrollbarsOnTop = z2;
        invalidate();
    }

    public void setScrollbarsVisible(boolean z2) {
        if (z2) {
            this.fadeAlpha = this.fadeAlphaSeconds;
            this.fadeDelay = this.fadeDelaySeconds;
        } else {
            this.fadeAlpha = 0.0f;
            this.fadeDelay = 0.0f;
        }
    }

    public void setScrollingDisabled(boolean z2, boolean z3) {
        this.disableX = z2;
        this.disableY = z3;
        invalidate();
    }

    public void setSmoothScrolling(boolean z2) {
        this.smoothScrolling = z2;
    }

    public void setStyle(ScrollPaneStyle scrollPaneStyle) {
        if (scrollPaneStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = scrollPaneStyle;
        invalidateHierarchy();
    }

    public void setVariableSizeKnobs(boolean z2) {
        this.variableSizeKnobs = z2;
    }

    public void setVelocityX(float f2) {
        this.velocityX = f2;
    }

    public void setVelocityY(float f2) {
        this.velocityY = f2;
    }

    @Deprecated
    public void setWidget(Actor actor) {
        setActor(actor);
    }

    public void setupFadeScrollBars(float f2, float f3) {
        this.fadeAlphaSeconds = f2;
        this.fadeDelaySeconds = f3;
    }

    public void setupOverscroll(float f2, float f3, float f4) {
        this.overscrollDistance = f2;
        this.overscrollSpeedMin = f3;
        this.overscrollSpeedMax = f4;
    }

    public void updateVisualScroll() {
        this.visualAmountX = this.amountX;
        this.visualAmountY = this.amountY;
    }

    protected void visualScrollX(float f2) {
        this.visualAmountX = f2;
    }

    protected void visualScrollY(float f2) {
        this.visualAmountY = f2;
    }

    public ScrollPane(Actor actor, Skin skin) {
        this(actor, (ScrollPaneStyle) skin.get(ScrollPaneStyle.class));
    }

    public void scrollTo(float f2, float f3, float f4, float f5, boolean z2, boolean z3) {
        float f6;
        validate();
        float f7 = this.amountX;
        if (z2) {
            f2 = (f2 - (this.widgetArea.width / 2.0f)) + (f4 / 2.0f);
        } else {
            float f8 = f4 + f2;
            float f9 = this.widgetArea.width;
            if (f8 > f7 + f9) {
                f7 = f8 - f9;
            }
            if (f2 >= f7) {
                f2 = f7;
            }
        }
        scrollX(j.a(f2, 0.0f, this.maxX));
        float f10 = this.amountY;
        if (z3) {
            f6 = ((this.widgetArea.height / 2.0f) + (this.maxY - f3)) - (f5 / 2.0f);
        } else {
            float f11 = this.maxY;
            float f12 = this.widgetArea.height;
            if (f10 > ((f11 - f3) - f5) + f12) {
                f10 = ((f11 - f3) - f5) + f12;
            }
            f6 = f10 < f11 - f3 ? f11 - f3 : f10;
        }
        scrollY(j.a(f6, 0.0f, this.maxY));
    }

    public ScrollPane(Actor actor, Skin skin, String str) {
        this(actor, (ScrollPaneStyle) skin.get(str, ScrollPaneStyle.class));
    }

    public ScrollPane(Actor actor, ScrollPaneStyle scrollPaneStyle) {
        this.widgetArea = new p();
        this.hScrollBounds = new p();
        this.hKnobBounds = new p();
        this.vScrollBounds = new p();
        this.vKnobBounds = new p();
        this.widgetCullingArea = new p();
        this.vScrollOnRight = true;
        this.hScrollOnBottom = true;
        this.lastPoint = new q();
        this.fadeScrollBars = true;
        this.smoothScrolling = true;
        this.scrollBarTouch = true;
        this.fadeAlphaSeconds = 1.0f;
        this.fadeDelaySeconds = 1.0f;
        this.cancelTouchFocus = true;
        this.flickScroll = true;
        this.flingTime = 1.0f;
        this.overscrollX = true;
        this.overscrollY = true;
        this.overscrollDistance = 50.0f;
        this.overscrollSpeedMin = 30.0f;
        this.overscrollSpeedMax = 200.0f;
        this.clamp = true;
        this.variableSizeKnobs = true;
        this.draggingPointer = -1;
        if (scrollPaneStyle != null) {
            this.style = scrollPaneStyle;
            setActor(actor);
            setSize(150.0f, 150.0f);
            addCaptureListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.1
                private float handlePosition;

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                    ScrollPane scrollPane = ScrollPane.this;
                    if (scrollPane.flickScroll) {
                        return false;
                    }
                    scrollPane.setScrollbarsVisible(true);
                    return false;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    ScrollPane scrollPane = ScrollPane.this;
                    if (scrollPane.draggingPointer != -1) {
                        return false;
                    }
                    if (i2 == 0 && i3 != 0) {
                        return false;
                    }
                    if (scrollPane.getStage() != null) {
                        ScrollPane.this.getStage().setScrollFocus(ScrollPane.this);
                    }
                    ScrollPane scrollPane2 = ScrollPane.this;
                    if (!scrollPane2.flickScroll) {
                        scrollPane2.setScrollbarsVisible(true);
                    }
                    ScrollPane scrollPane3 = ScrollPane.this;
                    if (scrollPane3.fadeAlpha == 0.0f) {
                        return false;
                    }
                    if (scrollPane3.scrollBarTouch && scrollPane3.scrollX && scrollPane3.hScrollBounds.contains(f2, f3)) {
                        inputEvent.stop();
                        ScrollPane.this.setScrollbarsVisible(true);
                        if (!ScrollPane.this.hKnobBounds.contains(f2, f3)) {
                            ScrollPane scrollPane4 = ScrollPane.this;
                            scrollPane4.setScrollX((scrollPane4.widgetArea.width * (f2 >= scrollPane4.hKnobBounds.f89x ? 1 : -1)) + scrollPane4.amountX);
                            return true;
                        }
                        ScrollPane scrollPane5 = ScrollPane.this;
                        q qVar = scrollPane5.lastPoint;
                        qVar.f91a = f2;
                        qVar.f92b = f3;
                        this.handlePosition = scrollPane5.hKnobBounds.f89x;
                        scrollPane5.touchScrollH = true;
                        scrollPane5.draggingPointer = i2;
                        return true;
                    }
                    ScrollPane scrollPane6 = ScrollPane.this;
                    if (!scrollPane6.scrollBarTouch || !scrollPane6.scrollY || !scrollPane6.vScrollBounds.contains(f2, f3)) {
                        return false;
                    }
                    inputEvent.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if (!ScrollPane.this.vKnobBounds.contains(f2, f3)) {
                        ScrollPane scrollPane7 = ScrollPane.this;
                        scrollPane7.setScrollY((scrollPane7.widgetArea.height * (f3 < scrollPane7.vKnobBounds.f90y ? 1 : -1)) + scrollPane7.amountY);
                        return true;
                    }
                    ScrollPane scrollPane8 = ScrollPane.this;
                    q qVar2 = scrollPane8.lastPoint;
                    qVar2.f91a = f2;
                    qVar2.f92b = f3;
                    this.handlePosition = scrollPane8.vKnobBounds.f90y;
                    scrollPane8.touchScrollV = true;
                    scrollPane8.draggingPointer = i2;
                    return true;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
                    ScrollPane scrollPane = ScrollPane.this;
                    if (i2 != scrollPane.draggingPointer) {
                        return;
                    }
                    if (scrollPane.touchScrollH) {
                        float f4 = this.handlePosition + (f2 - scrollPane.lastPoint.f91a);
                        this.handlePosition = f4;
                        float fMax = Math.max(scrollPane.hScrollBounds.f89x, f4);
                        ScrollPane scrollPane2 = ScrollPane.this;
                        p pVar = scrollPane2.hScrollBounds;
                        float fMin = Math.min((pVar.f89x + pVar.width) - scrollPane2.hKnobBounds.width, fMax);
                        ScrollPane scrollPane3 = ScrollPane.this;
                        p pVar2 = scrollPane3.hScrollBounds;
                        float f5 = pVar2.width - scrollPane3.hKnobBounds.width;
                        if (f5 != 0.0f) {
                            scrollPane3.setScrollPercentX((fMin - pVar2.f89x) / f5);
                        }
                        q qVar = ScrollPane.this.lastPoint;
                        qVar.f91a = f2;
                        qVar.f92b = f3;
                        return;
                    }
                    if (scrollPane.touchScrollV) {
                        float f6 = this.handlePosition + (f3 - scrollPane.lastPoint.f92b);
                        this.handlePosition = f6;
                        float fMax2 = Math.max(scrollPane.vScrollBounds.f90y, f6);
                        ScrollPane scrollPane4 = ScrollPane.this;
                        p pVar3 = scrollPane4.vScrollBounds;
                        float fMin2 = Math.min((pVar3.f90y + pVar3.height) - scrollPane4.vKnobBounds.height, fMax2);
                        ScrollPane scrollPane5 = ScrollPane.this;
                        p pVar4 = scrollPane5.vScrollBounds;
                        float f7 = pVar4.height - scrollPane5.vKnobBounds.height;
                        if (f7 != 0.0f) {
                            scrollPane5.setScrollPercentY(1.0f - ((fMin2 - pVar4.f90y) / f7));
                        }
                        q qVar2 = ScrollPane.this.lastPoint;
                        qVar2.f91a = f2;
                        qVar2.f92b = f3;
                    }
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                    ScrollPane scrollPane = ScrollPane.this;
                    if (i2 != scrollPane.draggingPointer) {
                        return;
                    }
                    scrollPane.cancel();
                }
            });
            ActorGestureListener actorGestureListener = new ActorGestureListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.2
                @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
                public void fling(InputEvent inputEvent, float f2, float f3, int i2) {
                    if (Math.abs(f2) > 150.0f) {
                        ScrollPane scrollPane = ScrollPane.this;
                        if (scrollPane.scrollX) {
                            scrollPane.flingTimer = scrollPane.flingTime;
                            scrollPane.velocityX = f2;
                            if (scrollPane.cancelTouchFocus) {
                                scrollPane.cancelTouchFocus();
                            }
                        }
                    }
                    if (Math.abs(f3) > 150.0f) {
                        ScrollPane scrollPane2 = ScrollPane.this;
                        if (scrollPane2.scrollY) {
                            scrollPane2.flingTimer = scrollPane2.flingTime;
                            scrollPane2.velocityY = -f3;
                            if (scrollPane2.cancelTouchFocus) {
                                scrollPane2.cancelTouchFocus();
                            }
                        }
                    }
                }

                @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener, com.badlogic.gdx.scenes.scene2d.EventListener
                public boolean handle(Event event) {
                    if (super.handle(event)) {
                        if (((InputEvent) event).getType() != InputEvent.Type.touchDown) {
                            return true;
                        }
                        ScrollPane.this.flingTimer = 0.0f;
                        return true;
                    }
                    if (!(event instanceof InputEvent) || !((InputEvent) event).isTouchFocusCancel()) {
                        return false;
                    }
                    ScrollPane.this.cancel();
                    return false;
                }

                @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
                public void pan(InputEvent inputEvent, float f2, float f3, float f4, float f5) {
                    ScrollPane.this.setScrollbarsVisible(true);
                    ScrollPane scrollPane = ScrollPane.this;
                    scrollPane.amountX -= f4;
                    scrollPane.amountY += f5;
                    scrollPane.clamp();
                    ScrollPane scrollPane2 = ScrollPane.this;
                    if (scrollPane2.cancelTouchFocus) {
                        if ((!scrollPane2.scrollX || f4 == 0.0f) && (!scrollPane2.scrollY || f5 == 0.0f)) {
                            return;
                        }
                        scrollPane2.cancelTouchFocus();
                    }
                }
            };
            this.flickScrollListener = actorGestureListener;
            addListener(actorGestureListener);
            addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.3
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean scrolled(InputEvent inputEvent, float f2, float f3, float f4, float f5) {
                    ScrollPane.this.setScrollbarsVisible(true);
                    ScrollPane scrollPane = ScrollPane.this;
                    if (!scrollPane.scrollY && !scrollPane.scrollX) {
                        return false;
                    }
                    scrollPane.setScrollY((scrollPane.getMouseWheelY() * f5) + scrollPane.amountY);
                    ScrollPane scrollPane2 = ScrollPane.this;
                    scrollPane2.setScrollX((scrollPane2.getMouseWheelX() * f4) + scrollPane2.amountX);
                    return true;
                }
            });
            return;
        }
        throw new IllegalArgumentException("style cannot be null.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z2) {
        if (actor != null) {
            if (actor != this.widget) {
                return false;
            }
            this.widget = null;
            return super.removeActor(actor, z2);
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }
}

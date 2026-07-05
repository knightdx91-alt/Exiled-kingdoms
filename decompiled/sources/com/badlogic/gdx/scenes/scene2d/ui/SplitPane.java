package com.badlogic.gdx.scenes.scene2d.ui;

import a0.p;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SplitPane extends WidgetGroup {
    boolean cursorOverHandle;
    private Actor firstWidget;
    private final p firstWidgetBounds;
    final p handleBounds;
    q handlePosition;
    q lastPoint;
    float maxAmount;
    float minAmount;
    private Actor secondWidget;
    private final p secondWidgetBounds;
    float splitAmount;
    SplitPaneStyle style;
    private final p tempScissors;
    boolean vertical;

    public static class SplitPaneStyle {
        public Drawable handle;

        public SplitPaneStyle() {
        }

        public SplitPaneStyle(Drawable drawable) {
            this.handle = drawable;
        }

        public SplitPaneStyle(SplitPaneStyle splitPaneStyle) {
            this.handle = splitPaneStyle.handle;
        }
    }

    public SplitPane(Actor actor, Actor actor2, boolean z2, Skin skin) {
        this(actor, actor2, z2, skin, "default-".concat(z2 ? "vertical" : "horizontal"));
    }

    private void calculateHorizBoundsAndPositions() {
        Drawable drawable = this.style.handle;
        float height = getHeight();
        float width = getWidth() - drawable.getMinWidth();
        float f2 = (int) (this.splitAmount * width);
        float minWidth = drawable.getMinWidth();
        this.firstWidgetBounds.set(0.0f, 0.0f, f2, height);
        this.secondWidgetBounds.set(f2 + minWidth, 0.0f, width - f2, height);
        this.handleBounds.set(f2, 0.0f, minWidth, height);
    }

    private void calculateVertBoundsAndPositions() {
        Drawable drawable = this.style.handle;
        float width = getWidth();
        float height = getHeight();
        float minHeight = height - drawable.getMinHeight();
        float f2 = (int) (this.splitAmount * minHeight);
        float f3 = minHeight - f2;
        float minHeight2 = drawable.getMinHeight();
        this.firstWidgetBounds.set(0.0f, height - f2, width, f2);
        this.secondWidgetBounds.set(0.0f, 0.0f, width, f3);
        this.handleBounds.set(0.0f, f3, width, minHeight2);
    }

    private void initialize() {
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SplitPane.1
            int draggingPointer = -1;

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
                SplitPane splitPane = SplitPane.this;
                splitPane.cursorOverHandle = splitPane.handleBounds.contains(f2, f3);
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (this.draggingPointer != -1) {
                    return false;
                }
                if ((i2 == 0 && i3 != 0) || !SplitPane.this.handleBounds.contains(f2, f3)) {
                    return false;
                }
                this.draggingPointer = i2;
                SplitPane splitPane = SplitPane.this;
                q qVar = splitPane.lastPoint;
                qVar.f91a = f2;
                qVar.f92b = f3;
                q qVar2 = splitPane.handlePosition;
                p pVar = splitPane.handleBounds;
                float f4 = pVar.f89x;
                float f5 = pVar.f90y;
                qVar2.f91a = f4;
                qVar2.f92b = f5;
                return true;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
                if (i2 != this.draggingPointer) {
                    return;
                }
                SplitPane splitPane = SplitPane.this;
                Drawable drawable = splitPane.style.handle;
                if (splitPane.vertical) {
                    float f4 = f3 - splitPane.lastPoint.f92b;
                    float height = splitPane.getHeight() - drawable.getMinHeight();
                    q qVar = SplitPane.this.handlePosition;
                    float f5 = qVar.f92b + f4;
                    qVar.f92b = f5;
                    float fMin = Math.min(height, Math.max(0.0f, f5));
                    SplitPane splitPane2 = SplitPane.this;
                    splitPane2.splitAmount = 1.0f - (fMin / height);
                    q qVar2 = splitPane2.lastPoint;
                    qVar2.f91a = f2;
                    qVar2.f92b = f3;
                } else {
                    float f6 = f2 - splitPane.lastPoint.f91a;
                    float width = splitPane.getWidth() - drawable.getMinWidth();
                    q qVar3 = SplitPane.this.handlePosition;
                    float f7 = qVar3.f91a + f6;
                    qVar3.f91a = f7;
                    float fMin2 = Math.min(width, Math.max(0.0f, f7));
                    SplitPane splitPane3 = SplitPane.this;
                    splitPane3.splitAmount = fMin2 / width;
                    q qVar4 = splitPane3.lastPoint;
                    qVar4.f91a = f2;
                    qVar4.f92b = f3;
                }
                SplitPane.this.invalidate();
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (i2 == this.draggingPointer) {
                    this.draggingPointer = -1;
                }
            }
        });
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorAt(int i2, Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    protected void clampSplitAmount() {
        float fMax = this.minAmount;
        float fMin = this.maxAmount;
        if (this.vertical) {
            float height = getHeight() - this.style.handle.getMinHeight();
            Object obj = this.firstWidget;
            if (obj instanceof Layout) {
                fMax = Math.max(fMax, Math.min(((Layout) obj).getMinHeight() / height, 1.0f));
            }
            Object obj2 = this.secondWidget;
            if (obj2 instanceof Layout) {
                fMin = Math.min(fMin, 1.0f - Math.min(((Layout) obj2).getMinHeight() / height, 1.0f));
            }
        } else {
            float width = getWidth() - this.style.handle.getMinWidth();
            Object obj3 = this.firstWidget;
            if (obj3 instanceof Layout) {
                fMax = Math.max(fMax, Math.min(((Layout) obj3).getMinWidth() / width, 1.0f));
            }
            Object obj4 = this.secondWidget;
            if (obj4 instanceof Layout) {
                fMin = Math.min(fMin, 1.0f - Math.min(((Layout) obj4).getMinWidth() / width, 1.0f));
            }
        }
        if (fMax > fMin) {
            this.splitAmount = (fMax + fMin) * 0.5f;
        } else {
            this.splitAmount = Math.max(Math.min(this.splitAmount, fMin), fMax);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Stage stage = getStage();
        if (stage == null) {
            return;
        }
        validate();
        Color color = getColor();
        float f3 = color.f1677a * f2;
        applyTransform(batch, computeTransform());
        Actor actor = this.firstWidget;
        if (actor != null && actor.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.firstWidgetBounds, this.tempScissors);
            if (ScissorStack.pushScissors(this.tempScissors)) {
                this.firstWidget.draw(batch, f3);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        Actor actor2 = this.secondWidget;
        if (actor2 != null && actor2.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.secondWidgetBounds, this.tempScissors);
            if (ScissorStack.pushScissors(this.tempScissors)) {
                this.secondWidget.draw(batch, f3);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, f3);
        Drawable drawable = this.style.handle;
        p pVar = this.handleBounds;
        drawable.draw(batch, pVar.f89x, pVar.f90y, pVar.width, pVar.height);
        resetTransform(batch);
    }

    public float getMaxSplitAmount() {
        return this.maxAmount;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        Object obj = this.firstWidget;
        float minHeight = obj instanceof Layout ? ((Layout) obj).getMinHeight() : 0.0f;
        Object obj2 = this.secondWidget;
        float minHeight2 = obj2 instanceof Layout ? ((Layout) obj2).getMinHeight() : 0.0f;
        return !this.vertical ? Math.max(minHeight, minHeight2) : this.style.handle.getMinHeight() + minHeight + minHeight2;
    }

    public float getMinSplitAmount() {
        return this.minAmount;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        Object obj = this.firstWidget;
        float minWidth = obj instanceof Layout ? ((Layout) obj).getMinWidth() : 0.0f;
        Object obj2 = this.secondWidget;
        float minWidth2 = obj2 instanceof Layout ? ((Layout) obj2).getMinWidth() : 0.0f;
        return this.vertical ? Math.max(minWidth, minWidth2) : this.style.handle.getMinWidth() + minWidth + minWidth2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        Actor actor = this.firstWidget;
        float prefHeight = actor == 0 ? 0.0f : actor instanceof Layout ? ((Layout) actor).getPrefHeight() : actor.getHeight();
        Actor actor2 = this.secondWidget;
        float prefHeight2 = actor2 != 0 ? actor2 instanceof Layout ? ((Layout) actor2).getPrefHeight() : actor2.getHeight() : 0.0f;
        return !this.vertical ? Math.max(prefHeight, prefHeight2) : this.style.handle.getMinHeight() + prefHeight + prefHeight2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        Actor actor = this.firstWidget;
        float prefWidth = actor == 0 ? 0.0f : actor instanceof Layout ? ((Layout) actor).getPrefWidth() : actor.getWidth();
        Actor actor2 = this.secondWidget;
        float prefWidth2 = actor2 != 0 ? actor2 instanceof Layout ? ((Layout) actor2).getPrefWidth() : actor2.getWidth() : 0.0f;
        return this.vertical ? Math.max(prefWidth, prefWidth2) : this.style.handle.getMinWidth() + prefWidth + prefWidth2;
    }

    public float getSplitAmount() {
        return this.splitAmount;
    }

    public SplitPaneStyle getStyle() {
        return this.style;
    }

    public boolean isCursorOverHandle() {
        return this.cursorOverHandle;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        clampSplitAmount();
        if (this.vertical) {
            calculateVertBoundsAndPositions();
        } else {
            calculateHorizBoundsAndPositions();
        }
        Actor actor = this.firstWidget;
        if (actor != 0) {
            p pVar = this.firstWidgetBounds;
            actor.setBounds(pVar.f89x, pVar.f90y, pVar.width, pVar.height);
            if (actor instanceof Layout) {
                ((Layout) actor).validate();
            }
        }
        Actor actor2 = this.secondWidget;
        if (actor2 != 0) {
            p pVar2 = this.secondWidgetBounds;
            actor2.setBounds(pVar2.f89x, pVar2.f90y, pVar2.width, pVar2.height);
            if (actor2 instanceof Layout) {
                ((Layout) actor2).validate();
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor == this.firstWidget) {
            setFirstWidget(null);
            return true;
        }
        if (actor == this.secondWidget) {
            setSecondWidget(null);
        }
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i2, boolean z2) {
        Actor actorRemoveActorAt = super.removeActorAt(i2, z2);
        if (actorRemoveActorAt == this.firstWidget) {
            super.removeActor(actorRemoveActorAt, z2);
            this.firstWidget = null;
            invalidate();
        } else if (actorRemoveActorAt == this.secondWidget) {
            super.removeActor(actorRemoveActorAt, z2);
            this.secondWidget = null;
            invalidate();
        }
        return actorRemoveActorAt;
    }

    public void setFirstWidget(Actor actor) {
        Actor actor2 = this.firstWidget;
        if (actor2 != null) {
            super.removeActor(actor2);
        }
        this.firstWidget = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    public void setMaxSplitAmount(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new m("maxAmount has to be >= 0 and <= 1");
        }
        this.maxAmount = f2;
    }

    public void setMinSplitAmount(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new m("minAmount has to be >= 0 and <= 1");
        }
        this.minAmount = f2;
    }

    public void setSecondWidget(Actor actor) {
        Actor actor2 = this.secondWidget;
        if (actor2 != null) {
            super.removeActor(actor2);
        }
        this.secondWidget = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    public void setSplitAmount(float f2) {
        this.splitAmount = f2;
        invalidate();
    }

    public void setStyle(SplitPaneStyle splitPaneStyle) {
        this.style = splitPaneStyle;
        invalidateHierarchy();
    }

    public void setVertical(boolean z2) {
        if (this.vertical == z2) {
            return;
        }
        this.vertical = z2;
        invalidateHierarchy();
    }

    public SplitPane(Actor actor, Actor actor2, boolean z2, Skin skin, String str) {
        this(actor, actor2, z2, (SplitPaneStyle) skin.get(str, SplitPaneStyle.class));
    }

    public SplitPane(Actor actor, Actor actor2, boolean z2, SplitPaneStyle splitPaneStyle) {
        this.splitAmount = 0.5f;
        this.maxAmount = 1.0f;
        this.firstWidgetBounds = new p();
        this.secondWidgetBounds = new p();
        this.handleBounds = new p();
        this.tempScissors = new p();
        this.lastPoint = new q();
        this.handlePosition = new q();
        this.vertical = z2;
        setStyle(splitPaneStyle);
        setFirstWidget(actor);
        setSecondWidget(actor2);
        setSize(getPrefWidth(), getPrefHeight());
        initialize();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z2) {
        if (actor != null) {
            if (actor == this.firstWidget) {
                super.removeActor(actor, z2);
                this.firstWidget = null;
                invalidate();
                return true;
            }
            if (actor != this.secondWidget) {
                return false;
            }
            super.removeActor(actor, z2);
            this.secondWidget = null;
            invalidate();
            return true;
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }
}

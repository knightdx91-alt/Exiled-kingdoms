package com.badlogic.gdx.scenes.scene2d;

import a0.p;
import a0.q;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.h;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.g0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.k0;
import i0.a;
import i0.b;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Stage extends h implements i {
    static boolean debug;
    private boolean actionsRequestRendering;
    private final Batch batch;
    private boolean debugAll;
    private final Color debugColor;
    private boolean debugInvisible;
    private boolean debugParentUnderMouse;
    private ShapeRenderer debugShapes;
    private Table.Debug debugTableUnderMouse;
    private boolean debugUnderMouse;
    private Actor keyboardFocus;
    private Actor mouseOverActor;
    private int mouseScreenX;
    private int mouseScreenY;
    private boolean ownsBatch;
    private final Actor[] pointerOverActors;
    private final int[] pointerScreenX;
    private final int[] pointerScreenY;
    private final boolean[] pointerTouched;
    private Group root;
    private Actor scrollFocus;
    private final q tempCoords;
    final k0<TouchFocus> touchFocuses;
    private b viewport;

    public static final class TouchFocus implements c0.a {
        int button;
        EventListener listener;
        Actor listenerActor;
        int pointer;
        Actor target;

        @Override // com.badlogic.gdx.utils.c0.a
        public void reset() {
            this.listenerActor = null;
            this.listener = null;
            this.target = null;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Stage() {
        this(new a(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()), new SpriteBatch());
        g0 g0Var = g0.f1806a;
        this.ownsBatch = true;
    }

    private void disableDebug(Actor actor, Actor actor2) {
        if (actor == actor2) {
            return;
        }
        actor.setDebug(false);
        if (actor instanceof Group) {
            k0<Actor> k0Var = ((Group) actor).children;
            int i2 = k0Var.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                disableDebug(k0Var.get(i3), actor2);
            }
        }
    }

    private void drawDebug() {
        Group group;
        if (this.debugShapes == null) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            this.debugShapes = shapeRenderer;
            shapeRenderer.setAutoShapeType(true);
        }
        if (this.debugUnderMouse || this.debugParentUnderMouse || this.debugTableUnderMouse != Table.Debug.none) {
            q qVar = this.tempCoords;
            float x2 = Gdx.input.getX();
            float y2 = Gdx.input.getY();
            qVar.f91a = x2;
            qVar.f92b = y2;
            screenToStageCoordinates(qVar);
            q qVar2 = this.tempCoords;
            Actor actorHit = hit(qVar2.f91a, qVar2.f92b, true);
            if (actorHit == null) {
                return;
            }
            if (this.debugParentUnderMouse && (group = actorHit.parent) != null) {
                actorHit = group;
            }
            if (this.debugTableUnderMouse == Table.Debug.none) {
                actorHit.setDebug(true);
            } else {
                while (actorHit != null && !(actorHit instanceof Table)) {
                    actorHit = actorHit.parent;
                }
                if (actorHit == null) {
                    return;
                } else {
                    ((Table) actorHit).debug(this.debugTableUnderMouse);
                }
            }
            if (this.debugAll && (actorHit instanceof Group)) {
                ((Group) actorHit).debugAll();
            }
            disableDebug(this.root, actorHit);
        } else if (this.debugAll) {
            this.root.debugAll();
        }
        Gdx.gl.glEnable(GL20.GL_BLEND);
        this.debugShapes.setProjectionMatrix(this.viewport.c().combined);
        this.debugShapes.begin();
        this.root.drawDebug(this.debugShapes);
        this.debugShapes.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private Actor fireEnterAndExit(Actor actor, int i2, int i3, int i4) {
        q qVar = this.tempCoords;
        qVar.f91a = i2;
        qVar.f92b = i3;
        screenToStageCoordinates(qVar);
        q qVar2 = this.tempCoords;
        Actor actorHit = hit(qVar2.f91a, qVar2.f92b, true);
        if (actorHit == actor) {
            return actor;
        }
        if (actor != null) {
            InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
            inputEvent.setStage(this);
            inputEvent.setStageX(this.tempCoords.f91a);
            inputEvent.setStageY(this.tempCoords.f92b);
            inputEvent.setPointer(i4);
            inputEvent.setType(InputEvent.Type.exit);
            inputEvent.setRelatedActor(actorHit);
            actor.fire(inputEvent);
            d0.a(inputEvent);
        }
        if (actorHit != null) {
            InputEvent inputEvent2 = (InputEvent) d0.d(InputEvent.class);
            inputEvent2.setStage(this);
            inputEvent2.setStageX(this.tempCoords.f91a);
            inputEvent2.setStageY(this.tempCoords.f92b);
            inputEvent2.setPointer(i4);
            inputEvent2.setType(InputEvent.Type.enter);
            inputEvent2.setRelatedActor(actor);
            actorHit.fire(inputEvent2);
            d0.a(inputEvent2);
        }
        return actorHit;
    }

    public void act() {
        act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335f));
    }

    public void addAction(Action action) {
        this.root.addAction(action);
    }

    public void addActor(Actor actor) {
        this.root.addActor(actor);
    }

    public boolean addCaptureListener(EventListener eventListener) {
        return this.root.addCaptureListener(eventListener);
    }

    public boolean addListener(EventListener eventListener) {
        return this.root.addListener(eventListener);
    }

    public void addTouchFocus(EventListener eventListener, Actor actor, Actor actor2, int i2, int i3) {
        TouchFocus touchFocus = (TouchFocus) d0.d(TouchFocus.class);
        touchFocus.listenerActor = actor;
        touchFocus.target = actor2;
        touchFocus.listener = eventListener;
        touchFocus.pointer = i2;
        touchFocus.button = i3;
        this.touchFocuses.a(touchFocus);
    }

    public void calculateScissors(p pVar, p pVar2) {
        ShapeRenderer shapeRenderer = this.debugShapes;
        this.viewport.b((shapeRenderer == null || !shapeRenderer.isDrawing()) ? this.batch.getTransformMatrix() : this.debugShapes.getTransformMatrix(), pVar, pVar2);
    }

    public void cancelTouchFocus(Actor actor) {
        k0<TouchFocus> k0Var = this.touchFocuses;
        TouchFocus[] touchFocusArrW = k0Var.w();
        int i2 = k0Var.f1750b;
        InputEvent inputEvent = null;
        for (int i3 = 0; i3 < i2; i3++) {
            TouchFocus touchFocus = touchFocusArrW[i3];
            if (touchFocus.listenerActor == actor && k0Var.q(touchFocus, true)) {
                if (inputEvent == null) {
                    inputEvent = (InputEvent) d0.d(InputEvent.class);
                    inputEvent.setStage(this);
                    inputEvent.setType(InputEvent.Type.touchUp);
                    inputEvent.setStageX(-2.1474836E9f);
                    inputEvent.setStageY(-2.1474836E9f);
                }
                inputEvent.setTarget(touchFocus.target);
                inputEvent.setListenerActor(touchFocus.listenerActor);
                inputEvent.setPointer(touchFocus.pointer);
                inputEvent.setButton(touchFocus.button);
                touchFocus.listener.handle(inputEvent);
            }
        }
        k0Var.x();
        if (inputEvent != null) {
            d0.a(inputEvent);
        }
    }

    public void cancelTouchFocusExcept(EventListener eventListener, Actor actor) {
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.touchUp);
        inputEvent.setStageX(-2.1474836E9f);
        inputEvent.setStageY(-2.1474836E9f);
        k0<TouchFocus> k0Var = this.touchFocuses;
        TouchFocus[] touchFocusArrW = k0Var.w();
        int i2 = k0Var.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            TouchFocus touchFocus = touchFocusArrW[i3];
            if ((touchFocus.listener != eventListener || touchFocus.listenerActor != actor) && k0Var.q(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.target);
                inputEvent.setListenerActor(touchFocus.listenerActor);
                inputEvent.setPointer(touchFocus.pointer);
                inputEvent.setButton(touchFocus.button);
                touchFocus.listener.handle(inputEvent);
            }
        }
        k0Var.x();
        d0.a(inputEvent);
    }

    public void clear() {
        unfocusAll();
        this.root.clear();
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        clear();
        if (this.ownsBatch) {
            this.batch.dispose();
        }
    }

    public void draw() {
        Camera cameraC = this.viewport.c();
        cameraC.update();
        if (this.root.isVisible()) {
            Batch batch = this.batch;
            batch.setProjectionMatrix(cameraC.combined);
            batch.begin();
            this.root.draw(batch, 1.0f);
            batch.end();
            if (debug) {
                drawDebug();
            }
        }
    }

    public boolean getActionsRequestRendering() {
        return this.actionsRequestRendering;
    }

    public com.badlogic.gdx.utils.a<Actor> getActors() {
        return this.root.children;
    }

    public Batch getBatch() {
        return this.batch;
    }

    public Camera getCamera() {
        return this.viewport.c();
    }

    public Color getDebugColor() {
        return this.debugColor;
    }

    public float getHeight() {
        return this.viewport.h();
    }

    public Actor getKeyboardFocus() {
        return this.keyboardFocus;
    }

    public Group getRoot() {
        return this.root;
    }

    public Actor getScrollFocus() {
        return this.scrollFocus;
    }

    public b getViewport() {
        return this.viewport;
    }

    public float getWidth() {
        return this.viewport.i();
    }

    public Actor hit(float f2, float f3, boolean z2) {
        Group group = this.root;
        q qVar = this.tempCoords;
        qVar.f91a = f2;
        qVar.f92b = f3;
        group.parentToLocalCoordinates(qVar);
        Group group2 = this.root;
        q qVar2 = this.tempCoords;
        return group2.hit(qVar2.f91a, qVar2.f92b, z2);
    }

    public boolean isDebugAll() {
        return this.debugAll;
    }

    protected boolean isInsideViewport(int i2, int i3) {
        int iF = this.viewport.f();
        int iE = this.viewport.e() + iF;
        int iG = this.viewport.g();
        int iD = this.viewport.d() + iG;
        int height = (Gdx.graphics.getHeight() - 1) - i3;
        return i2 >= iF && i2 < iE && height >= iG && height < iD;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyDown(int i2) {
        Actor actor = this.keyboardFocus;
        if (actor == null) {
            actor = this.root;
        }
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.keyDown);
        inputEvent.setKeyCode(i2);
        actor.fire(inputEvent);
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyTyped(char c2) {
        Actor actor = this.keyboardFocus;
        if (actor == null) {
            actor = this.root;
        }
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.keyTyped);
        inputEvent.setCharacter(c2);
        actor.fire(inputEvent);
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyUp(int i2) {
        Actor actor = this.keyboardFocus;
        if (actor == null) {
            actor = this.root;
        }
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.keyUp);
        inputEvent.setKeyCode(i2);
        actor.fire(inputEvent);
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean mouseMoved(int i2, int i3) {
        this.mouseScreenX = i2;
        this.mouseScreenY = i3;
        if (!isInsideViewport(i2, i3)) {
            return false;
        }
        q qVar = this.tempCoords;
        qVar.f91a = i2;
        qVar.f92b = i3;
        screenToStageCoordinates(qVar);
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.mouseMoved);
        inputEvent.setStageX(this.tempCoords.f91a);
        inputEvent.setStageY(this.tempCoords.f92b);
        q qVar2 = this.tempCoords;
        Actor actorHit = hit(qVar2.f91a, qVar2.f92b, true);
        if (actorHit == null) {
            actorHit = this.root;
        }
        actorHit.fire(inputEvent);
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    public boolean removeCaptureListener(EventListener eventListener) {
        return this.root.removeCaptureListener(eventListener);
    }

    public boolean removeListener(EventListener eventListener) {
        return this.root.removeListener(eventListener);
    }

    public void removeTouchFocus(EventListener eventListener, Actor actor, Actor actor2, int i2, int i3) {
        k0<TouchFocus> k0Var = this.touchFocuses;
        for (int i4 = k0Var.f1750b - 1; i4 >= 0; i4--) {
            TouchFocus touchFocus = k0Var.get(i4);
            if (touchFocus.listener == eventListener && touchFocus.listenerActor == actor && touchFocus.target == actor2 && touchFocus.pointer == i2 && touchFocus.button == i3) {
                k0Var.o(i4);
                d0.a(touchFocus);
            }
        }
    }

    public q screenToStageCoordinates(q qVar) {
        this.viewport.o(qVar);
        return qVar;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean scrolled(float f2, float f3) {
        Actor actor = this.scrollFocus;
        if (actor == null) {
            actor = this.root;
        }
        q qVar = this.tempCoords;
        float f4 = this.mouseScreenX;
        float f5 = this.mouseScreenY;
        qVar.f91a = f4;
        qVar.f92b = f5;
        screenToStageCoordinates(qVar);
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setStage(this);
        inputEvent.setType(InputEvent.Type.scrolled);
        inputEvent.setScrollAmountX(f2);
        inputEvent.setScrollAmountY(f3);
        inputEvent.setStageX(this.tempCoords.f91a);
        inputEvent.setStageY(this.tempCoords.f92b);
        actor.fire(inputEvent);
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    public void setActionsRequestRendering(boolean z2) {
        this.actionsRequestRendering = z2;
    }

    public void setDebugAll(boolean z2) {
        if (this.debugAll == z2) {
            return;
        }
        this.debugAll = z2;
        if (z2) {
            debug = true;
        } else {
            this.root.setDebug(false, true);
        }
    }

    public void setDebugInvisible(boolean z2) {
        this.debugInvisible = z2;
    }

    public void setDebugParentUnderMouse(boolean z2) {
        if (this.debugParentUnderMouse == z2) {
            return;
        }
        this.debugParentUnderMouse = z2;
        if (z2) {
            debug = true;
        } else {
            this.root.setDebug(false, true);
        }
    }

    public void setDebugTableUnderMouse(Table.Debug debug2) {
        if (debug2 == null) {
            debug2 = Table.Debug.none;
        }
        if (this.debugTableUnderMouse == debug2) {
            return;
        }
        this.debugTableUnderMouse = debug2;
        if (debug2 != Table.Debug.none) {
            debug = true;
        } else {
            this.root.setDebug(false, true);
        }
    }

    public void setDebugUnderMouse(boolean z2) {
        if (this.debugUnderMouse == z2) {
            return;
        }
        this.debugUnderMouse = z2;
        if (z2) {
            debug = true;
        } else {
            this.root.setDebug(false, true);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean setKeyboardFocus(Actor actor) {
        if (this.keyboardFocus == actor) {
            return true;
        }
        FocusListener.FocusEvent focusEvent = (FocusListener.FocusEvent) d0.d(FocusListener.FocusEvent.class);
        focusEvent.setStage(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.keyboard);
        Actor actor2 = this.keyboardFocus;
        if (actor2 != null) {
            focusEvent.setFocused(false);
            focusEvent.setRelatedActor(actor);
            actor2.fire(focusEvent);
        }
        boolean zIsCancelled = focusEvent.isCancelled();
        boolean z2 = !zIsCancelled;
        if (!zIsCancelled) {
            this.keyboardFocus = actor;
            if (actor != null) {
                focusEvent.setFocused(true);
                focusEvent.setRelatedActor(actor2);
                actor.fire(focusEvent);
                boolean zIsCancelled2 = focusEvent.isCancelled();
                z2 = !zIsCancelled2;
                if (zIsCancelled2) {
                    this.keyboardFocus = actor2;
                }
            }
        }
        d0.a(focusEvent);
        return z2;
    }

    public void setRoot(Group group) {
        Group group2 = group.parent;
        if (group2 != null) {
            group2.removeActor(group, false);
        }
        this.root = group;
        group.setParent(null);
        group.setStage(this);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean setScrollFocus(Actor actor) {
        if (this.scrollFocus == actor) {
            return true;
        }
        FocusListener.FocusEvent focusEvent = (FocusListener.FocusEvent) d0.d(FocusListener.FocusEvent.class);
        focusEvent.setStage(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.scroll);
        Actor actor2 = this.scrollFocus;
        if (actor2 != null) {
            focusEvent.setFocused(false);
            focusEvent.setRelatedActor(actor);
            actor2.fire(focusEvent);
        }
        boolean zIsCancelled = focusEvent.isCancelled();
        boolean z2 = !zIsCancelled;
        if (!zIsCancelled) {
            this.scrollFocus = actor;
            if (actor != null) {
                focusEvent.setFocused(true);
                focusEvent.setRelatedActor(actor2);
                actor.fire(focusEvent);
                boolean zIsCancelled2 = focusEvent.isCancelled();
                z2 = !zIsCancelled2;
                if (zIsCancelled2) {
                    this.scrollFocus = actor2;
                }
            }
        }
        d0.a(focusEvent);
        return z2;
    }

    public void setViewport(b bVar) {
        this.viewport = bVar;
    }

    public q stageToScreenCoordinates(q qVar) {
        this.viewport.j(qVar);
        qVar.f92b = Gdx.graphics.getHeight() - qVar.f92b;
        return qVar;
    }

    public q toScreenCoordinates(q qVar, Matrix4 matrix4) {
        this.viewport.n(qVar, matrix4);
        return qVar;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDown(int i2, int i3, int i4, int i5) {
        if (!isInsideViewport(i2, i3)) {
            return false;
        }
        this.pointerTouched[i4] = true;
        this.pointerScreenX[i4] = i2;
        this.pointerScreenY[i4] = i3;
        q qVar = this.tempCoords;
        qVar.f91a = i2;
        qVar.f92b = i3;
        screenToStageCoordinates(qVar);
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchDown);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.tempCoords.f91a);
        inputEvent.setStageY(this.tempCoords.f92b);
        inputEvent.setPointer(i4);
        inputEvent.setButton(i5);
        q qVar2 = this.tempCoords;
        Actor actorHit = hit(qVar2.f91a, qVar2.f92b, true);
        if (actorHit != null) {
            actorHit.fire(inputEvent);
        } else if (this.root.getTouchable() == Touchable.enabled) {
            this.root.fire(inputEvent);
        }
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDragged(int i2, int i3, int i4) {
        this.pointerScreenX[i4] = i2;
        this.pointerScreenY[i4] = i3;
        this.mouseScreenX = i2;
        this.mouseScreenY = i3;
        if (this.touchFocuses.f1750b == 0) {
            return false;
        }
        q qVar = this.tempCoords;
        qVar.f91a = i2;
        qVar.f92b = i3;
        screenToStageCoordinates(qVar);
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchDragged);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.tempCoords.f91a);
        inputEvent.setStageY(this.tempCoords.f92b);
        inputEvent.setPointer(i4);
        k0<TouchFocus> k0Var = this.touchFocuses;
        TouchFocus[] touchFocusArrW = k0Var.w();
        int i5 = k0Var.f1750b;
        for (int i6 = 0; i6 < i5; i6++) {
            TouchFocus touchFocus = touchFocusArrW[i6];
            if (touchFocus.pointer == i4 && k0Var.e(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.target);
                inputEvent.setListenerActor(touchFocus.listenerActor);
                if (touchFocus.listener.handle(inputEvent)) {
                    inputEvent.handle();
                }
            }
        }
        k0Var.x();
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchUp(int i2, int i3, int i4, int i5) {
        this.pointerTouched[i4] = false;
        this.pointerScreenX[i4] = i2;
        this.pointerScreenY[i4] = i3;
        if (this.touchFocuses.f1750b == 0) {
            return false;
        }
        q qVar = this.tempCoords;
        qVar.f91a = i2;
        qVar.f92b = i3;
        screenToStageCoordinates(qVar);
        InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchUp);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.tempCoords.f91a);
        inputEvent.setStageY(this.tempCoords.f92b);
        inputEvent.setPointer(i4);
        inputEvent.setButton(i5);
        k0<TouchFocus> k0Var = this.touchFocuses;
        TouchFocus[] touchFocusArrW = k0Var.w();
        int i6 = k0Var.f1750b;
        for (int i7 = 0; i7 < i6; i7++) {
            TouchFocus touchFocus = touchFocusArrW[i7];
            if (touchFocus.pointer == i4 && touchFocus.button == i5 && k0Var.q(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.target);
                inputEvent.setListenerActor(touchFocus.listenerActor);
                if (touchFocus.listener.handle(inputEvent)) {
                    inputEvent.handle();
                }
                d0.a(touchFocus);
            }
        }
        k0Var.x();
        boolean zIsHandled = inputEvent.isHandled();
        d0.a(inputEvent);
        return zIsHandled;
    }

    public void unfocus(Actor actor) {
        cancelTouchFocus(actor);
        Actor actor2 = this.scrollFocus;
        if (actor2 != null && actor2.isDescendantOf(actor)) {
            setScrollFocus(null);
        }
        Actor actor3 = this.keyboardFocus;
        if (actor3 == null || !actor3.isDescendantOf(actor)) {
            return;
        }
        setKeyboardFocus(null);
    }

    public void unfocusAll() {
        setScrollFocus(null);
        setKeyboardFocus(null);
        cancelTouchFocus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void act(float f2) {
        int length = this.pointerOverActors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Actor[] actorArr = this.pointerOverActors;
            Actor actor = actorArr[i2];
            if (this.pointerTouched[i2]) {
                actorArr[i2] = fireEnterAndExit(actor, this.pointerScreenX[i2], this.pointerScreenY[i2], i2);
            } else if (actor != null) {
                actorArr[i2] = null;
                q qVar = this.tempCoords;
                float f3 = this.pointerScreenX[i2];
                float f4 = this.pointerScreenY[i2];
                qVar.f91a = f3;
                qVar.f92b = f4;
                screenToStageCoordinates(qVar);
                InputEvent inputEvent = (InputEvent) d0.d(InputEvent.class);
                inputEvent.setType(InputEvent.Type.exit);
                inputEvent.setStage(this);
                inputEvent.setStageX(this.tempCoords.f91a);
                inputEvent.setStageY(this.tempCoords.f92b);
                inputEvent.setRelatedActor(actor);
                inputEvent.setPointer(i2);
                actor.fire(inputEvent);
                d0.a(inputEvent);
            }
        }
        Application.a type = Gdx.app.getType();
        if (type == Application.a.f1564b || type == Application.a.f1565c || type == Application.a.f1566d) {
            this.mouseOverActor = fireEnterAndExit(this.mouseOverActor, this.mouseScreenX, this.mouseScreenY, -1);
        }
        this.root.act(f2);
    }

    public Stage(b bVar) {
        this(bVar, new SpriteBatch());
        this.ownsBatch = true;
    }

    public Stage(b bVar, Batch batch) {
        this.tempCoords = new q();
        this.pointerOverActors = new Actor[20];
        this.pointerTouched = new boolean[20];
        this.pointerScreenX = new int[20];
        this.pointerScreenY = new int[20];
        this.touchFocuses = new k0<>(true, 4, TouchFocus.class);
        this.actionsRequestRendering = true;
        this.debugTableUnderMouse = Table.Debug.none;
        this.debugColor = new Color(0.0f, 1.0f, 0.0f, 0.85f);
        if (bVar == null) {
            throw new IllegalArgumentException("viewport cannot be null.");
        }
        if (batch != null) {
            this.viewport = bVar;
            this.batch = batch;
            Group group = new Group();
            this.root = group;
            group.setStage(this);
            bVar.p(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            return;
        }
        throw new IllegalArgumentException("batch cannot be null.");
    }

    public void setDebugTableUnderMouse(boolean z2) {
        setDebugTableUnderMouse(z2 ? Table.Debug.all : Table.Debug.none);
    }

    public void cancelTouchFocus() {
        cancelTouchFocusExcept(null, null);
    }
}

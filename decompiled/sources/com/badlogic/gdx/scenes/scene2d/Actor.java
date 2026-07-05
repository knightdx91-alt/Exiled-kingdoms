package com.badlogic.gdx.scenes.scene2d;

import a0.p;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.h;
import com.badlogic.gdx.utils.k0;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Actor {
    private boolean debug;
    float height;
    private String name;
    float originX;
    float originY;
    Group parent;
    float rotation;
    private Stage stage;
    private Object userObject;
    float width;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    float f1742x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    float f1743y;
    private final h<EventListener> listeners = new h<>();
    private final h<EventListener> captureListeners = new h<>();
    private final a<Action> actions = new a<>(true, 0);
    private Touchable touchable = Touchable.enabled;
    private boolean visible = true;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    final Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public void act(float f2) {
        a<Action> aVar = this.actions;
        if (aVar.f1750b == 0) {
            return;
        }
        Stage stage = this.stage;
        if (stage != null && stage.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
        }
        int i2 = 0;
        while (i2 < aVar.f1750b) {
            try {
                Action action = aVar.get(i2);
                if (action.act(f2) && i2 < aVar.f1750b) {
                    int iH = aVar.get(i2) == action ? i2 : aVar.h(action, true);
                    if (iH != -1) {
                        aVar.o(iH);
                        action.setActor(null);
                        i2--;
                    }
                }
                i2++;
            } catch (RuntimeException e2) {
                String string = toString();
                throw new RuntimeException("Actor: " + string.substring(0, Math.min(string.length(), VertexAttributes.Usage.Tangent)), e2);
            }
        }
    }

    public void addAction(Action action) {
        action.setActor(this);
        this.actions.a(action);
        Stage stage = this.stage;
        if (stage == null || !stage.getActionsRequestRendering()) {
            return;
        }
        Gdx.graphics.requestRendering();
    }

    public boolean addCaptureListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (!this.captureListeners.e(eventListener, true)) {
            this.captureListeners.a(eventListener);
        }
        return true;
    }

    public boolean addListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (this.listeners.e(eventListener, true)) {
            return false;
        }
        this.listeners.a(eventListener);
        return true;
    }

    @Deprecated
    public boolean ancestorsVisible() {
        return ascendantsVisible();
    }

    public boolean ascendantsVisible() {
        Actor actor = this;
        while (actor.isVisible()) {
            actor = actor.parent;
            if (actor == null) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        clearActions();
        clearListeners();
    }

    public void clearActions() {
        for (int i2 = this.actions.f1750b - 1; i2 >= 0; i2--) {
            this.actions.get(i2).setActor(null);
        }
        this.actions.clear();
    }

    public void clearListeners() {
        this.listeners.clear();
        this.captureListeners.clear();
    }

    public boolean clipBegin() {
        return clipBegin(this.f1742x, this.f1743y, this.width, this.height);
    }

    public void clipEnd() {
        d0.a(ScissorStack.popScissors());
    }

    public Actor debug() {
        setDebug(true);
        return this;
    }

    public void draw(Batch batch, float f2) {
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        drawDebugBounds(shapeRenderer);
    }

    protected void drawDebugBounds(ShapeRenderer shapeRenderer) {
        if (this.debug) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            Stage stage = this.stage;
            if (stage != null) {
                shapeRenderer.setColor(stage.getDebugColor());
            }
            shapeRenderer.rect(this.f1742x, this.f1743y, this.originX, this.originY, this.width, this.height, this.scaleX, this.scaleY, this.rotation);
        }
    }

    public boolean fire(Event event) {
        boolean zIsCancelled;
        if (event.getStage() == null) {
            event.setStage(getStage());
        }
        event.setTarget(this);
        a aVar = (a) d0.d(a.class);
        for (Group group = this.parent; group != null; group = group.parent) {
            aVar.a(group);
        }
        try {
            Object[] objArr = aVar.f1749a;
            int i2 = aVar.f1750b - 1;
            while (true) {
                if (i2 >= 0) {
                    ((Group) objArr[i2]).notify(event, true);
                    if (event.isStopped()) {
                        zIsCancelled = event.isCancelled();
                        break;
                    }
                    i2--;
                } else {
                    notify(event, true);
                    if (event.isStopped()) {
                        zIsCancelled = event.isCancelled();
                    } else {
                        notify(event, false);
                        if (event.getBubbles() && !event.isStopped()) {
                            int i3 = aVar.f1750b;
                            int i4 = 0;
                            while (true) {
                                if (i4 >= i3) {
                                    zIsCancelled = event.isCancelled();
                                    break;
                                }
                                ((Group) objArr[i4]).notify(event, false);
                                if (event.isStopped()) {
                                    zIsCancelled = event.isCancelled();
                                    break;
                                }
                                i4++;
                            }
                        } else {
                            zIsCancelled = event.isCancelled();
                        }
                    }
                }
            }
            return zIsCancelled;
        } finally {
            aVar.clear();
            d0.a(aVar);
        }
    }

    public <T extends Actor> T firstAscendant(Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Group group = (T) this;
        while (!cls.isInstance(group)) {
            group = group.parent;
            if (group == null) {
                return null;
            }
        }
        return group;
    }

    public a<Action> getActions() {
        return this.actions;
    }

    public h<EventListener> getCaptureListeners() {
        return this.captureListeners;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean getDebug() {
        return this.debug;
    }

    public float getHeight() {
        return this.height;
    }

    public h<EventListener> getListeners() {
        return this.listeners;
    }

    public String getName() {
        return this.name;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public Group getParent() {
        return this.parent;
    }

    public float getRight() {
        return this.f1742x + this.width;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public Stage getStage() {
        return this.stage;
    }

    public float getTop() {
        return this.f1743y + this.height;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.f1742x;
    }

    public float getY() {
        return this.f1743y;
    }

    public int getZIndex() {
        Group group = this.parent;
        if (group == null) {
            return -1;
        }
        return group.children.h(this, true);
    }

    public boolean hasActions() {
        return this.actions.f1750b > 0;
    }

    public boolean hasKeyboardFocus() {
        Stage stage = getStage();
        return stage != null && stage.getKeyboardFocus() == this;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public boolean hasScrollFocus() {
        Stage stage = getStage();
        return stage != null && stage.getScrollFocus() == this;
    }

    public Actor hit(float f2, float f3, boolean z2) {
        if ((!z2 || this.touchable == Touchable.enabled) && isVisible() && f2 >= 0.0f && f2 < this.width && f3 >= 0.0f && f3 < this.height) {
            return this;
        }
        return null;
    }

    public boolean isAscendantOf(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        while (actor != this) {
            actor = actor.parent;
            if (actor == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isDescendantOf(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor2 = this;
        while (actor2 != actor) {
            actor2 = actor2.parent;
            if (actor2 == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isTouchFocusListener() {
        Stage stage = getStage();
        if (stage == null) {
            return false;
        }
        int i2 = stage.touchFocuses.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (stage.touchFocuses.get(i3).listenerActor == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchFocusTarget() {
        Stage stage = getStage();
        if (stage == null) {
            return false;
        }
        int i2 = stage.touchFocuses.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (stage.touchFocuses.get(i3).target == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchable() {
        return this.touchable == Touchable.enabled;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public q localToActorCoordinates(Actor actor, q qVar) {
        localToStageCoordinates(qVar);
        return actor.stageToLocalCoordinates(qVar);
    }

    public q localToAscendantCoordinates(Actor actor, q qVar) {
        Actor actor2 = this;
        do {
            actor2.localToParentCoordinates(qVar);
            actor2 = actor2.parent;
            if (actor2 == actor) {
                break;
            }
        } while (actor2 != null);
        return qVar;
    }

    public q localToParentCoordinates(q qVar) {
        float f2 = -this.rotation;
        float f3 = this.scaleX;
        float f4 = this.scaleY;
        float f5 = this.f1742x;
        float f6 = this.f1743y;
        if (f2 != 0.0f) {
            double d2 = f2 * 0.017453292f;
            float fCos = (float) Math.cos(d2);
            float fSin = (float) Math.sin(d2);
            float f7 = this.originX;
            float f8 = this.originY;
            float f9 = (qVar.f91a - f7) * f3;
            float f10 = (qVar.f92b - f8) * f4;
            qVar.f91a = (f10 * fSin) + (f9 * fCos) + f7 + f5;
            qVar.f92b = (f10 * fCos) + (f9 * (-fSin)) + f8 + f6;
        } else if (f3 == 1.0f && f4 == 1.0f) {
            qVar.f91a += f5;
            qVar.f92b += f6;
        } else {
            float f11 = this.originX;
            float f12 = this.originY;
            qVar.f91a = ((qVar.f91a - f11) * f3) + f11 + f5;
            qVar.f92b = ((qVar.f92b - f12) * f4) + f12 + f6;
        }
        return qVar;
    }

    public q localToScreenCoordinates(q qVar) {
        Stage stage = this.stage;
        return stage == null ? qVar : stage.stageToScreenCoordinates(localToAscendantCoordinates(null, qVar));
    }

    public q localToStageCoordinates(q qVar) {
        return localToAscendantCoordinates(null, qVar);
    }

    public void moveBy(float f2, float f3) {
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        this.f1742x += f2;
        this.f1743y += f3;
        positionChanged();
    }

    public boolean notify(Event event, boolean z2) {
        if (event.getTarget() == null) {
            throw new IllegalArgumentException("The event target cannot be null.");
        }
        h<EventListener> hVar = z2 ? this.captureListeners : this.listeners;
        if (hVar.f1750b == 0) {
            return event.isCancelled();
        }
        event.setListenerActor(this);
        event.setCapture(z2);
        if (event.getStage() == null) {
            event.setStage(this.stage);
        }
        try {
            hVar.w();
            int i2 = hVar.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                if (hVar.get(i3).handle(event)) {
                    event.handle();
                }
            }
            hVar.x();
            return event.isCancelled();
        } catch (RuntimeException e2) {
            String string = toString();
            throw new RuntimeException("Actor: " + string.substring(0, Math.min(string.length(), VertexAttributes.Usage.Tangent)), e2);
        }
    }

    public q parentToLocalCoordinates(q qVar) {
        float f2 = this.rotation;
        float f3 = this.scaleX;
        float f4 = this.scaleY;
        float f5 = this.f1742x;
        float f6 = this.f1743y;
        if (f2 != 0.0f) {
            double d2 = f2 * 0.017453292f;
            float fCos = (float) Math.cos(d2);
            float fSin = (float) Math.sin(d2);
            float f7 = this.originX;
            float f8 = this.originY;
            float f9 = (qVar.f91a - f5) - f7;
            float f10 = (qVar.f92b - f6) - f8;
            qVar.f91a = (((f10 * fSin) + (f9 * fCos)) / f3) + f7;
            qVar.f92b = (((f10 * fCos) + (f9 * (-fSin))) / f4) + f8;
        } else if (f3 == 1.0f && f4 == 1.0f) {
            qVar.f91a -= f5;
            qVar.f92b -= f6;
        } else {
            float f11 = this.originX;
            float f12 = this.originY;
            qVar.f91a = (((qVar.f91a - f5) - f11) / f3) + f11;
            qVar.f92b = (((qVar.f92b - f6) - f12) / f4) + f12;
        }
        return qVar;
    }

    protected void positionChanged() {
    }

    public boolean remove() {
        Group group = this.parent;
        if (group != null) {
            return group.removeActor(this, true);
        }
        return false;
    }

    public void removeAction(Action action) {
        if (action == null || !this.actions.q(action, true)) {
            return;
        }
        action.setActor(null);
    }

    public boolean removeCaptureListener(EventListener eventListener) {
        if (eventListener != null) {
            return this.captureListeners.q(eventListener, true);
        }
        throw new IllegalArgumentException("listener cannot be null.");
    }

    public boolean removeListener(EventListener eventListener) {
        if (eventListener != null) {
            return this.listeners.q(eventListener, true);
        }
        throw new IllegalArgumentException("listener cannot be null.");
    }

    public void rotateBy(float f2) {
        if (f2 != 0.0f) {
            this.rotation = (this.rotation + f2) % 360.0f;
            rotationChanged();
        }
    }

    protected void rotationChanged() {
    }

    public void scaleBy(float f2) {
        if (f2 != 0.0f) {
            this.scaleX += f2;
            this.scaleY += f2;
            scaleChanged();
        }
    }

    protected void scaleChanged() {
    }

    public q screenToLocalCoordinates(q qVar) {
        Stage stage = this.stage;
        return stage == null ? qVar : stageToLocalCoordinates(stage.screenToStageCoordinates(qVar));
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        if (this.f1742x != f2 || this.f1743y != f3) {
            this.f1742x = f2;
            this.f1743y = f3;
            positionChanged();
        }
        if (this.width == f4 && this.height == f5) {
            return;
        }
        this.width = f4;
        this.height = f5;
        sizeChanged();
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setDebug(boolean z2) {
        this.debug = z2;
        if (z2) {
            Stage.debug = true;
        }
    }

    public void setHeight(float f2) {
        if (this.height != f2) {
            this.height = f2;
            sizeChanged();
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOrigin(float f2, float f3) {
        this.originX = f2;
        this.originY = f3;
    }

    public void setOriginX(float f2) {
        this.originX = f2;
    }

    public void setOriginY(float f2) {
        this.originY = f2;
    }

    protected void setParent(Group group) {
        this.parent = group;
    }

    public void setPosition(float f2, float f3) {
        if (this.f1742x == f2 && this.f1743y == f3) {
            return;
        }
        this.f1742x = f2;
        this.f1743y = f3;
        positionChanged();
    }

    public void setRotation(float f2) {
        if (this.rotation != f2) {
            this.rotation = f2;
            rotationChanged();
        }
    }

    public void setScale(float f2) {
        if (this.scaleX == f2 && this.scaleY == f2) {
            return;
        }
        this.scaleX = f2;
        this.scaleY = f2;
        scaleChanged();
    }

    public void setScaleX(float f2) {
        if (this.scaleX != f2) {
            this.scaleX = f2;
            scaleChanged();
        }
    }

    public void setScaleY(float f2) {
        if (this.scaleY != f2) {
            this.scaleY = f2;
            scaleChanged();
        }
    }

    public void setSize(float f2, float f3) {
        if (this.width == f2 && this.height == f3) {
            return;
        }
        this.width = f2;
        this.height = f3;
        sizeChanged();
    }

    protected void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
    }

    public void setUserObject(Object obj) {
        this.userObject = obj;
    }

    public void setVisible(boolean z2) {
        this.visible = z2;
    }

    public void setWidth(float f2) {
        if (this.width != f2) {
            this.width = f2;
            sizeChanged();
        }
    }

    public void setX(float f2) {
        if (this.f1742x != f2) {
            this.f1742x = f2;
            positionChanged();
        }
    }

    public void setY(float f2) {
        if (this.f1743y != f2) {
            this.f1743y = f2;
            positionChanged();
        }
    }

    public boolean setZIndex(int i2) {
        k0<Actor> k0Var;
        int i3;
        if (i2 < 0) {
            throw new IllegalArgumentException("ZIndex cannot be < 0.");
        }
        Group group = this.parent;
        if (group == null || (i3 = (k0Var = group.children).f1750b) == 1) {
            return false;
        }
        int iMin = Math.min(i2, i3 - 1);
        if (k0Var.get(iMin) == this || !k0Var.q(this, true)) {
            return false;
        }
        k0Var.i(iMin, this);
        return true;
    }

    public void sizeBy(float f2) {
        if (f2 != 0.0f) {
            this.width += f2;
            this.height += f2;
            sizeChanged();
        }
    }

    protected void sizeChanged() {
    }

    public q stageToLocalCoordinates(q qVar) {
        Group group = this.parent;
        if (group != null) {
            group.stageToLocalCoordinates(qVar);
        }
        parentToLocalCoordinates(qVar);
        return qVar;
    }

    public void toBack() {
        setZIndex(0);
    }

    public void toFront() {
        setZIndex(Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        String name = getClass().getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf != -1 ? name.substring(iLastIndexOf + 1) : name;
    }

    public boolean clipBegin(float f2, float f3, float f4, float f5) {
        Stage stage;
        if (f4 <= 0.0f || f5 <= 0.0f || (stage = this.stage) == null) {
            return false;
        }
        p pVar = p.tmp;
        pVar.f89x = f2;
        pVar.f90y = f3;
        pVar.width = f4;
        pVar.height = f5;
        p pVar2 = (p) d0.d(p.class);
        stage.calculateScissors(pVar, pVar2);
        if (ScissorStack.pushScissors(pVar2)) {
            return true;
        }
        d0.a(pVar2);
        return false;
    }

    public float getX(int i2) {
        float f2 = this.f1742x;
        return (i2 & 16) != 0 ? f2 + this.width : (i2 & 8) == 0 ? f2 + (this.width / 2.0f) : f2;
    }

    public float getY(int i2) {
        float f2 = this.f1743y;
        return (i2 & 2) != 0 ? f2 + this.height : (i2 & 4) == 0 ? f2 + (this.height / 2.0f) : f2;
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
    }

    public void setOrigin(int i2) {
        if ((i2 & 8) != 0) {
            this.originX = 0.0f;
        } else if ((i2 & 16) != 0) {
            this.originX = this.width;
        } else {
            this.originX = this.width / 2.0f;
        }
        if ((i2 & 4) != 0) {
            this.originY = 0.0f;
        } else if ((i2 & 2) != 0) {
            this.originY = this.height;
        } else {
            this.originY = this.height / 2.0f;
        }
    }

    public void scaleBy(float f2, float f3) {
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        this.scaleX += f2;
        this.scaleY += f3;
        scaleChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setX(float f2, int i2) {
        float f3;
        if ((i2 & 16) == 0) {
            if ((i2 & 8) == 0) {
                f3 = this.width / 2.0f;
            }
            if (this.f1742x == f2) {
                this.f1742x = f2;
                positionChanged();
                return;
            }
            return;
        }
        f3 = this.width;
        f2 -= f3;
        if (this.f1742x == f2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setY(float f2, int i2) {
        float f3;
        if ((i2 & 2) == 0) {
            if ((i2 & 4) == 0) {
                f3 = this.height / 2.0f;
            }
            if (this.f1743y == f2) {
                this.f1743y = f2;
                positionChanged();
                return;
            }
            return;
        }
        f3 = this.height;
        f2 -= f3;
        if (this.f1743y == f2) {
        }
    }

    public void sizeBy(float f2, float f3) {
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        this.width += f2;
        this.height += f3;
        sizeChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setPosition(float f2, float f3, int i2) {
        float f4;
        float f5;
        if ((i2 & 16) != 0) {
            f4 = this.width;
        } else {
            if ((i2 & 8) == 0) {
                f4 = this.width / 2.0f;
            }
            if ((i2 & 2) != 0) {
                if ((i2 & 4) == 0) {
                    f5 = this.height / 2.0f;
                }
                if (this.f1742x == f2 || this.f1743y != f3) {
                    this.f1742x = f2;
                    this.f1743y = f3;
                    positionChanged();
                }
                return;
            }
            f5 = this.height;
            f3 -= f5;
            if (this.f1742x == f2) {
            }
            this.f1742x = f2;
            this.f1743y = f3;
            positionChanged();
        }
        f2 -= f4;
        if ((i2 & 2) != 0) {
        }
        f3 -= f5;
        if (this.f1742x == f2) {
        }
        this.f1742x = f2;
        this.f1743y = f3;
        positionChanged();
    }

    public void setScale(float f2, float f3) {
        if (this.scaleX == f2 && this.scaleY == f3) {
            return;
        }
        this.scaleX = f2;
        this.scaleY = f3;
        scaleChanged();
    }
}

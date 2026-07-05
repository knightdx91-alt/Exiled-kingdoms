package com.badlogic.gdx.scenes.scene2d.utils;

import a0.q;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.y;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DragAndDrop {
    static final q tmpVector = new q();
    private int button;
    Actor dragActor;
    Source dragSource;
    long dragValidTime;
    boolean isValidTarget;
    Payload payload;
    boolean removeDragActor;
    Target target;
    float touchOffsetX;
    float touchOffsetY;
    final a<Target> targets = new a<>();
    final y<Source, DragListener> sourceListeners = new y<>();
    private float tapSquareSize = 8.0f;
    float dragActorX = 0.0f;
    float dragActorY = 0.0f;
    int dragTime = 250;
    int activePointer = -1;
    boolean cancelTouchFocus = true;
    boolean keepWithinStage = true;

    public static class Payload {
        Actor dragActor;
        Actor invalidDragActor;
        Object object;
        Actor validDragActor;

        public Actor getDragActor() {
            return this.dragActor;
        }

        public Actor getInvalidDragActor() {
            return this.invalidDragActor;
        }

        public Object getObject() {
            return this.object;
        }

        public Actor getValidDragActor() {
            return this.validDragActor;
        }

        public void setDragActor(Actor actor) {
            this.dragActor = actor;
        }

        public void setInvalidDragActor(Actor actor) {
            this.invalidDragActor = actor;
        }

        public void setObject(Object obj) {
            this.object = obj;
        }

        public void setValidDragActor(Actor actor) {
            this.validDragActor = actor;
        }
    }

    public static abstract class Source {
        final Actor actor;

        public Source(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor;
        }

        public void drag(InputEvent inputEvent, float f2, float f3, int i2) {
        }

        public abstract Payload dragStart(InputEvent inputEvent, float f2, float f3, int i2);

        public void dragStop(InputEvent inputEvent, float f2, float f3, int i2, Payload payload, Target target) {
        }

        public Actor getActor() {
            return this.actor;
        }
    }

    public static abstract class Target {
        final Actor actor;

        public Target(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor;
            Stage stage = actor.getStage();
            if (stage != null && actor == stage.getRoot()) {
                throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
        }

        public abstract boolean drag(Source source, Payload payload, float f2, float f3, int i2);

        public abstract void drop(Source source, Payload payload, float f2, float f3, int i2);

        public Actor getActor() {
            return this.actor;
        }

        public void reset(Source source, Payload payload) {
        }
    }

    public void addSource(final Source source) {
        DragListener dragListener = new DragListener() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void drag(InputEvent inputEvent, float f2, float f3, int i2) {
                float x2;
                float y2;
                Target target;
                DragAndDrop dragAndDrop = DragAndDrop.this;
                if (dragAndDrop.payload != null && i2 == dragAndDrop.activePointer) {
                    source.drag(inputEvent, f2, f3, i2);
                    Stage stage = inputEvent.getStage();
                    Actor actor = DragAndDrop.this.dragActor;
                    if (actor != null) {
                        x2 = actor.getX();
                        y2 = actor.getY();
                        actor.setPosition(2.1474836E9f, 2.1474836E9f);
                    } else {
                        x2 = 0.0f;
                        y2 = 0.0f;
                    }
                    float stageX = inputEvent.getStageX() + DragAndDrop.this.touchOffsetX;
                    float stageY = inputEvent.getStageY() + DragAndDrop.this.touchOffsetY;
                    Actor actorHit = inputEvent.getStage().hit(stageX, stageY, true);
                    if (actorHit == null) {
                        actorHit = inputEvent.getStage().hit(stageX, stageY, false);
                    }
                    if (actor != null) {
                        actor.setPosition(x2, y2);
                    }
                    DragAndDrop dragAndDrop2 = DragAndDrop.this;
                    dragAndDrop2.isValidTarget = false;
                    if (actorHit != null) {
                        int i3 = dragAndDrop2.targets.f1750b;
                        for (int i4 = 0; i4 < i3; i4++) {
                            Target target2 = DragAndDrop.this.targets.get(i4);
                            if (target2.actor.isAscendantOf(actorHit)) {
                                Actor actor2 = target2.actor;
                                q qVar = DragAndDrop.tmpVector;
                                qVar.f91a = stageX;
                                qVar.f92b = stageY;
                                actor2.stageToLocalCoordinates(qVar);
                                target = target2;
                                break;
                            }
                        }
                        target = null;
                    } else {
                        target = null;
                    }
                    DragAndDrop dragAndDrop3 = DragAndDrop.this;
                    Target target3 = dragAndDrop3.target;
                    if (target != target3) {
                        if (target3 != null) {
                            target3.reset(source, dragAndDrop3.payload);
                        }
                        DragAndDrop.this.target = target;
                    }
                    if (target != null) {
                        DragAndDrop dragAndDrop4 = DragAndDrop.this;
                        Source source2 = source;
                        Payload payload = dragAndDrop4.payload;
                        q qVar2 = DragAndDrop.tmpVector;
                        dragAndDrop4.isValidTarget = target.drag(source2, payload, qVar2.f91a, qVar2.f92b, i2);
                    }
                    DragAndDrop dragAndDrop5 = DragAndDrop.this;
                    Actor actor3 = dragAndDrop5.target != null ? dragAndDrop5.isValidTarget ? dragAndDrop5.payload.validDragActor : dragAndDrop5.payload.invalidDragActor : null;
                    if (actor3 == null) {
                        actor3 = dragAndDrop5.payload.dragActor;
                    }
                    if (actor3 != actor) {
                        if (actor != null && dragAndDrop5.removeDragActor) {
                            actor.remove();
                        }
                        DragAndDrop dragAndDrop6 = DragAndDrop.this;
                        dragAndDrop6.dragActor = actor3;
                        dragAndDrop6.removeDragActor = actor3.getStage() == null;
                        if (DragAndDrop.this.removeDragActor) {
                            stage.addActor(actor3);
                        }
                    }
                    if (actor3 == null) {
                        return;
                    }
                    float stageX2 = (inputEvent.getStageX() - actor3.getWidth()) + DragAndDrop.this.dragActorX;
                    float stageY2 = inputEvent.getStageY();
                    DragAndDrop dragAndDrop7 = DragAndDrop.this;
                    float height = stageY2 + dragAndDrop7.dragActorY;
                    if (dragAndDrop7.keepWithinStage) {
                        if (stageX2 < 0.0f) {
                            stageX2 = 0.0f;
                        }
                        float f4 = height >= 0.0f ? height : 0.0f;
                        if (actor3.getWidth() + stageX2 > stage.getWidth()) {
                            stageX2 = stage.getWidth() - actor3.getWidth();
                        }
                        height = actor3.getHeight() + f4 > stage.getHeight() ? stage.getHeight() - actor3.getHeight() : f4;
                    }
                    actor3.setPosition(stageX2, height);
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStart(InputEvent inputEvent, float f2, float f3, int i2) {
                Stage stage;
                DragAndDrop dragAndDrop = DragAndDrop.this;
                if (dragAndDrop.activePointer != -1) {
                    inputEvent.stop();
                    return;
                }
                dragAndDrop.activePointer = i2;
                long jCurrentTimeMillis = System.currentTimeMillis();
                DragAndDrop dragAndDrop2 = DragAndDrop.this;
                dragAndDrop.dragValidTime = jCurrentTimeMillis + ((long) dragAndDrop2.dragTime);
                Source source2 = source;
                dragAndDrop2.dragSource = source2;
                dragAndDrop2.payload = source2.dragStart(inputEvent, getTouchDownX(), getTouchDownY(), i2);
                inputEvent.stop();
                DragAndDrop dragAndDrop3 = DragAndDrop.this;
                if (!dragAndDrop3.cancelTouchFocus || dragAndDrop3.payload == null || (stage = source.getActor().getStage()) == null) {
                    return;
                }
                stage.cancelTouchFocusExcept(this, source.getActor());
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStop(InputEvent inputEvent, float f2, float f3, int i2) {
                DragAndDrop dragAndDrop = DragAndDrop.this;
                if (i2 != dragAndDrop.activePointer) {
                    return;
                }
                dragAndDrop.activePointer = -1;
                if (dragAndDrop.payload == null) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                DragAndDrop dragAndDrop2 = DragAndDrop.this;
                if (jCurrentTimeMillis < dragAndDrop2.dragValidTime) {
                    dragAndDrop2.isValidTarget = false;
                }
                Actor actor = dragAndDrop2.dragActor;
                if (actor != null && dragAndDrop2.removeDragActor) {
                    actor.remove();
                }
                if (DragAndDrop.this.isValidTarget) {
                    float stageX = inputEvent.getStageX() + DragAndDrop.this.touchOffsetX;
                    float stageY = inputEvent.getStageY();
                    DragAndDrop dragAndDrop3 = DragAndDrop.this;
                    float f4 = stageY + dragAndDrop3.touchOffsetY;
                    Actor actor2 = dragAndDrop3.target.actor;
                    q qVar = DragAndDrop.tmpVector;
                    qVar.f91a = stageX;
                    qVar.f92b = f4;
                    actor2.stageToLocalCoordinates(qVar);
                    DragAndDrop dragAndDrop4 = DragAndDrop.this;
                    dragAndDrop4.target.drop(source, dragAndDrop4.payload, qVar.f91a, qVar.f92b, i2);
                }
                Source source2 = source;
                DragAndDrop dragAndDrop5 = DragAndDrop.this;
                source2.dragStop(inputEvent, f2, f3, i2, dragAndDrop5.payload, dragAndDrop5.isValidTarget ? dragAndDrop5.target : null);
                DragAndDrop dragAndDrop6 = DragAndDrop.this;
                Target target = dragAndDrop6.target;
                if (target != null) {
                    target.reset(source, dragAndDrop6.payload);
                }
                DragAndDrop dragAndDrop7 = DragAndDrop.this;
                dragAndDrop7.dragSource = null;
                dragAndDrop7.payload = null;
                dragAndDrop7.target = null;
                dragAndDrop7.isValidTarget = false;
                dragAndDrop7.dragActor = null;
            }
        };
        dragListener.setTapSquareSize(this.tapSquareSize);
        dragListener.setButton(this.button);
        source.actor.addCaptureListener(dragListener);
        this.sourceListeners.j(source, dragListener);
    }

    public void addTarget(Target target) {
        this.targets.a(target);
    }

    public void cancelTouchFocusExcept(Source source) {
        Stage stage;
        DragListener dragListenerD = this.sourceListeners.d(source);
        if (dragListenerD == null || (stage = source.getActor().getStage()) == null) {
            return;
        }
        stage.cancelTouchFocusExcept(dragListenerD, source.getActor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clear() {
        this.targets.clear();
        y.a<Source, DragListener> aVarB = this.sourceListeners.b();
        aVarB.getClass();
        while (aVarB.hasNext()) {
            y.b next = aVarB.next();
            ((Source) next.f2057a).actor.removeCaptureListener((EventListener) next.f2058b);
        }
        this.sourceListeners.clear();
    }

    public Actor getDragActor() {
        return this.dragActor;
    }

    public Payload getDragPayload() {
        return this.payload;
    }

    public Source getDragSource() {
        return this.dragSource;
    }

    public int getDragTime() {
        return this.dragTime;
    }

    public boolean isDragValid() {
        return this.payload != null && System.currentTimeMillis() >= this.dragValidTime;
    }

    public boolean isDragging() {
        return this.payload != null;
    }

    public void removeSource(Source source) {
        source.actor.removeCaptureListener(this.sourceListeners.k(source));
    }

    public void removeTarget(Target target) {
        this.targets.q(target, true);
    }

    public void setButton(int i2) {
        this.button = i2;
    }

    public void setCancelTouchFocus(boolean z2) {
        this.cancelTouchFocus = z2;
    }

    public void setDragActorPosition(float f2, float f3) {
        this.dragActorX = f2;
        this.dragActorY = f3;
    }

    public void setDragTime(int i2) {
        this.dragTime = i2;
    }

    public void setKeepWithinStage(boolean z2) {
        this.keepWithinStage = z2;
    }

    public void setTapSquareSize(float f2) {
        this.tapSquareSize = f2;
    }

    public void setTouchOffset(float f2, float f3) {
        this.touchOffsetX = f2;
        this.touchOffsetY = f3;
    }
}

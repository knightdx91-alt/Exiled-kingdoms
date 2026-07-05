package com.badlogic.gdx.scenes.scene2d.utils;

import a0.q;
import com.badlogic.gdx.input.a;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActorGestureListener implements EventListener {
    static final q tmpCoords = new q();
    static final q tmpCoords2 = new q();
    Actor actor;
    private final a detector;
    InputEvent event;
    Actor touchDownTarget;

    /* JADX INFO: renamed from: com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type;

        static {
            int[] iArr = new int[InputEvent.Type.values().length];
            $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type = iArr;
            try {
                iArr[InputEvent.Type.touchDown.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.touchUp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.touchDragged.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ActorGestureListener() {
        this(20.0f, 0.4f, 1.1f, 2.1474836E9f);
    }

    public void fling(InputEvent inputEvent, float f2, float f3, int i2) {
    }

    public a getGestureDetector() {
        return this.detector;
    }

    public Actor getTouchDownTarget() {
        return this.touchDownTarget;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        int i2 = AnonymousClass2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[inputEvent.getType().ordinal()];
        if (i2 == 1) {
            this.actor = inputEvent.getListenerActor();
            this.touchDownTarget = inputEvent.getTarget();
            this.detector.touchDown(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
            Actor actor = this.actor;
            q qVar = tmpCoords;
            float stageX = inputEvent.getStageX();
            float stageY = inputEvent.getStageY();
            qVar.f91a = stageX;
            qVar.f92b = stageY;
            actor.stageToLocalCoordinates(qVar);
            touchDown(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer(), inputEvent.getButton());
            if (inputEvent.getTouchFocus()) {
                inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
            }
            return true;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                return false;
            }
            this.event = inputEvent;
            this.actor = inputEvent.getListenerActor();
            this.detector.touchDragged(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer());
            return true;
        }
        if (inputEvent.isTouchFocusCancel()) {
            this.detector.reset();
            return false;
        }
        this.event = inputEvent;
        this.actor = inputEvent.getListenerActor();
        this.detector.touchUp(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
        Actor actor2 = this.actor;
        q qVar2 = tmpCoords;
        float stageX2 = inputEvent.getStageX();
        float stageY2 = inputEvent.getStageY();
        qVar2.f91a = stageX2;
        qVar2.f92b = stageY2;
        actor2.stageToLocalCoordinates(qVar2);
        touchUp(inputEvent, qVar2.f91a, qVar2.f92b, inputEvent.getPointer(), inputEvent.getButton());
        return true;
    }

    public boolean longPress(Actor actor, float f2, float f3) {
        return false;
    }

    public void pan(InputEvent inputEvent, float f2, float f3, float f4, float f5) {
    }

    public void panStop(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
    }

    public void pinch(InputEvent inputEvent, q qVar, q qVar2, q qVar3, q qVar4) {
    }

    public void tap(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
    }

    public void touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
    }

    public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
    }

    public void zoom(InputEvent inputEvent, float f2, float f3) {
    }

    public ActorGestureListener(float f2, float f3, float f4, float f5) {
        this.detector = new a(f2, f3, f4, f5, new a.b() { // from class: com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.1
            private final q initialPointer1 = new q();
            private final q initialPointer2 = new q();
            private final q pointer1 = new q();
            private final q pointer2 = new q();

            private void stageToLocalAmount(q qVar) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(qVar);
                Actor actor = ActorGestureListener.this.actor;
                q qVar2 = ActorGestureListener.tmpCoords2;
                qVar2.f91a = 0.0f;
                qVar2.f92b = 0.0f;
                qVar.c(actor.stageToLocalCoordinates(qVar2));
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean fling(float f6, float f7, int i2) {
                q qVar = ActorGestureListener.tmpCoords;
                qVar.f91a = f6;
                qVar.f92b = f7;
                stageToLocalAmount(qVar);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.fling(actorGestureListener.event, qVar.f91a, qVar.f92b, i2);
                return true;
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean longPress(float f6, float f7) {
                Actor actor = ActorGestureListener.this.actor;
                q qVar = ActorGestureListener.tmpCoords;
                qVar.f91a = f6;
                qVar.f92b = f7;
                actor.stageToLocalCoordinates(qVar);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                return actorGestureListener.longPress(actorGestureListener.actor, qVar.f91a, qVar.f92b);
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean pan(float f6, float f7, float f8, float f9) {
                q qVar = ActorGestureListener.tmpCoords;
                qVar.f91a = f8;
                qVar.f92b = f9;
                stageToLocalAmount(qVar);
                float f10 = qVar.f91a;
                float f11 = qVar.f92b;
                Actor actor = ActorGestureListener.this.actor;
                qVar.f91a = f6;
                qVar.f92b = f7;
                actor.stageToLocalCoordinates(qVar);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.pan(actorGestureListener.event, qVar.f91a, qVar.f92b, f10, f11);
                return true;
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean panStop(float f6, float f7, int i2, int i3) {
                Actor actor = ActorGestureListener.this.actor;
                q qVar = ActorGestureListener.tmpCoords;
                qVar.f91a = f6;
                qVar.f92b = f7;
                actor.stageToLocalCoordinates(qVar);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.panStop(actorGestureListener.event, qVar.f91a, qVar.f92b, i2, i3);
                return true;
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean pinch(q qVar, q qVar2, q qVar3, q qVar4) {
                Actor actor = ActorGestureListener.this.actor;
                q qVar5 = this.initialPointer1;
                qVar5.b(qVar);
                actor.stageToLocalCoordinates(qVar5);
                Actor actor2 = ActorGestureListener.this.actor;
                q qVar6 = this.initialPointer2;
                qVar6.b(qVar2);
                actor2.stageToLocalCoordinates(qVar6);
                Actor actor3 = ActorGestureListener.this.actor;
                q qVar7 = this.pointer1;
                qVar7.b(qVar3);
                actor3.stageToLocalCoordinates(qVar7);
                Actor actor4 = ActorGestureListener.this.actor;
                q qVar8 = this.pointer2;
                qVar8.b(qVar4);
                actor4.stageToLocalCoordinates(qVar8);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.pinch(actorGestureListener.event, this.initialPointer1, this.initialPointer2, this.pointer1, this.pointer2);
                return true;
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean tap(float f6, float f7, int i2, int i3) {
                Actor actor = ActorGestureListener.this.actor;
                q qVar = ActorGestureListener.tmpCoords;
                qVar.f91a = f6;
                qVar.f92b = f7;
                actor.stageToLocalCoordinates(qVar);
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.tap(actorGestureListener.event, qVar.f91a, qVar.f92b, i2, i3);
                return true;
            }

            @Override // com.badlogic.gdx.input.a.b, com.badlogic.gdx.input.a.c
            public boolean zoom(float f6, float f7) {
                ActorGestureListener actorGestureListener = ActorGestureListener.this;
                actorGestureListener.zoom(actorGestureListener.event, f6, f7);
                return true;
            }
        });
    }
}

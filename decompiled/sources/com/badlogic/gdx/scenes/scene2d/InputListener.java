package com.badlogic.gdx.scenes.scene2d;

import a0.q;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class InputListener implements EventListener {
    private static final q tmpCoords = new q();

    /* JADX INFO: renamed from: com.badlogic.gdx.scenes.scene2d.InputListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type;

        static {
            int[] iArr = new int[InputEvent.Type.values().length];
            $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type = iArr;
            try {
                iArr[InputEvent.Type.keyDown.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.keyUp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.keyTyped.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.touchDown.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.touchUp.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.touchDragged.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.mouseMoved.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.scrolled.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.enter.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[InputEvent.Type.exit.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
    }

    public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        int[] iArr = AnonymousClass1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type;
        int i2 = iArr[inputEvent.getType().ordinal()];
        if (i2 == 1) {
            return keyDown(inputEvent, inputEvent.getKeyCode());
        }
        if (i2 == 2) {
            return keyUp(inputEvent, inputEvent.getKeyCode());
        }
        if (i2 == 3) {
            return keyTyped(inputEvent, inputEvent.getCharacter());
        }
        Actor listenerActor = inputEvent.getListenerActor();
        q qVar = tmpCoords;
        inputEvent.toCoordinates(listenerActor, qVar);
        switch (iArr[inputEvent.getType().ordinal()]) {
            case 4:
                boolean z2 = touchDown(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer(), inputEvent.getButton());
                if (z2 && inputEvent.getTouchFocus()) {
                    inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
                }
                break;
            case 5:
                touchUp(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer(), inputEvent.getButton());
                break;
            case 6:
                touchDragged(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer());
                break;
            case 9:
                enter(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer(), inputEvent.getRelatedActor());
                break;
            case 10:
                exit(inputEvent, qVar.f91a, qVar.f92b, inputEvent.getPointer(), inputEvent.getRelatedActor());
                break;
        }
        return false;
    }

    public boolean keyDown(InputEvent inputEvent, int i2) {
        return false;
    }

    public boolean keyTyped(InputEvent inputEvent, char c2) {
        return false;
    }

    public boolean keyUp(InputEvent inputEvent, int i2) {
        return false;
    }

    public boolean mouseMoved(InputEvent inputEvent, float f2, float f3) {
        return false;
    }

    public boolean scrolled(InputEvent inputEvent, float f2, float f3, float f4, float f5) {
        return false;
    }

    public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        return false;
    }

    public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
    }

    public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
    }
}

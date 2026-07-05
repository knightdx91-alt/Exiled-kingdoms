package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class FocusListener implements EventListener {

    /* JADX INFO: renamed from: com.badlogic.gdx.scenes.scene2d.utils.FocusListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type;

        static {
            int[] iArr = new int[FocusEvent.Type.values().length];
            $SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type = iArr;
            try {
                iArr[FocusEvent.Type.keyboard.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type[FocusEvent.Type.scroll.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class FocusEvent extends Event {
        private boolean focused;
        private Actor relatedActor;
        private Type type;

        public enum Type {
            keyboard,
            scroll
        }

        public Actor getRelatedActor() {
            return this.relatedActor;
        }

        public Type getType() {
            return this.type;
        }

        public boolean isFocused() {
            return this.focused;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.Event, com.badlogic.gdx.utils.c0.a
        public void reset() {
            super.reset();
            this.relatedActor = null;
        }

        public void setFocused(boolean z2) {
            this.focused = z2;
        }

        public void setRelatedActor(Actor actor) {
            this.relatedActor = actor;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof FocusEvent)) {
            return false;
        }
        FocusEvent focusEvent = (FocusEvent) event;
        int i2 = AnonymousClass1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type[focusEvent.getType().ordinal()];
        if (i2 == 1) {
            keyboardFocusChanged(focusEvent, event.getTarget(), focusEvent.isFocused());
        } else if (i2 == 2) {
            scrollFocusChanged(focusEvent, event.getTarget(), focusEvent.isFocused());
        }
        return false;
    }

    public void keyboardFocusChanged(FocusEvent focusEvent, Actor actor, boolean z2) {
    }

    public void scrollFocusChanged(FocusEvent focusEvent, Actor actor, boolean z2) {
    }
}

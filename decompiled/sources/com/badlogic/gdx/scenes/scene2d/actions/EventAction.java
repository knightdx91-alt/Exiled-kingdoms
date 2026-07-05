package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class EventAction<T extends Event> extends Action {
    boolean active;
    final Class<? extends T> eventClass;
    private final EventListener listener = new EventListener() { // from class: com.badlogic.gdx.scenes.scene2d.actions.EventAction.1
        @Override // com.badlogic.gdx.scenes.scene2d.EventListener
        public boolean handle(Event event) {
            EventAction eventAction = EventAction.this;
            if (!eventAction.active || !eventAction.eventClass.isInstance(event)) {
                return false;
            }
            EventAction eventAction2 = EventAction.this;
            eventAction2.result = eventAction2.handle(event);
            return EventAction.this.result;
        }
    };
    boolean result;

    public EventAction(Class<? extends T> cls) {
        this.eventClass = cls;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f2) {
        this.active = true;
        return this.result;
    }

    public abstract boolean handle(T t2);

    public boolean isActive() {
        return this.active;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.result = false;
        this.active = false;
    }

    public void setActive(boolean z2) {
        this.active = z2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor) {
        Actor actor2 = this.target;
        if (actor2 != null) {
            actor2.removeListener(this.listener);
        }
        super.setTarget(actor);
        if (actor != null) {
            actor.addListener(this.listener);
        }
    }
}

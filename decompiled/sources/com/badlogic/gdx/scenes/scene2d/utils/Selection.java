package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b0;
import com.badlogic.gdx.utils.d0;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Selection<T> implements Disableable, Iterable<T> {
    private Actor actor;
    boolean isDisabled;
    T lastSelected;
    boolean multiple;
    boolean required;
    private boolean toggle;
    final b0<T> selected = new b0<>();
    private final b0<T> old = new b0<>();
    private boolean programmaticChangeEvents = true;

    public void add(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.selected.add(t2)) {
            if (this.programmaticChangeEvents && fireChangeEvent()) {
                this.selected.remove(t2);
            } else {
                this.lastSelected = t2;
                changed();
            }
        }
    }

    public void addAll(a<T> aVar) {
        snapshot();
        int i2 = aVar.f1750b;
        boolean z2 = false;
        for (int i3 = 0; i3 < i2; i3++) {
            T t2 = aVar.get(i3);
            if (t2 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.selected.add(t2)) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.programmaticChangeEvents && fireChangeEvent()) {
                revert();
            } else {
                this.lastSelected = aVar.k();
                changed();
            }
        }
        cleanup();
    }

    protected void changed() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0038 A[Catch: all -> 0x0016, TryCatch #0 {all -> 0x0016, blocks: (B:7:0x000a, B:9:0x000f, B:23:0x0038, B:25:0x003d, B:27:0x0041, B:40:0x0065, B:45:0x0073, B:46:0x0075, B:48:0x007b, B:49:0x007f, B:29:0x0047, B:31:0x004d, B:35:0x0057, B:39:0x005f, B:14:0x0019, B:16:0x0021, B:18:0x0025, B:22:0x002f), top: B:56:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void choose(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.isDisabled) {
            return;
        }
        snapshot();
        try {
            boolean z2 = true;
            if (this.toggle || UIUtils.ctrl()) {
                if (!this.selected.contains(t2)) {
                    boolean z3 = false;
                    if (!this.multiple || (!this.toggle && !UIUtils.ctrl())) {
                        b0<T> b0Var = this.selected;
                        if (b0Var.f2064a == 1 && b0Var.contains(t2)) {
                            cleanup();
                            return;
                        }
                        b0<T> b0Var2 = this.selected;
                        if (b0Var2.f2064a <= 0) {
                            z2 = false;
                        }
                        b0Var2.a(8);
                        z3 = z2;
                    }
                    if (!this.selected.add(t2) && !z3) {
                        cleanup();
                        return;
                    }
                    this.lastSelected = t2;
                } else if (this.required && this.selected.f2064a == 1) {
                    cleanup();
                    return;
                } else {
                    this.selected.remove(t2);
                    this.lastSelected = null;
                }
            }
            if (fireChangeEvent()) {
                revert();
            } else {
                changed();
            }
            cleanup();
        } catch (Throwable th) {
            cleanup();
            throw th;
        }
    }

    void cleanup() {
        this.old.a(32);
    }

    public void clear() {
        if (this.selected.f2064a == 0) {
            return;
        }
        snapshot();
        this.selected.a(8);
        if (this.programmaticChangeEvents && fireChangeEvent()) {
            revert();
        } else {
            this.lastSelected = null;
            changed();
        }
        cleanup();
    }

    public boolean contains(T t2) {
        if (t2 == null) {
            return false;
        }
        return this.selected.contains(t2);
    }

    public boolean fireChangeEvent() {
        if (this.actor == null) {
            return false;
        }
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) d0.d(ChangeListener.ChangeEvent.class);
        try {
            return this.actor.fire(changeEvent);
        } finally {
            d0.a(changeEvent);
        }
    }

    public T first() {
        b0<T> b0Var = this.selected;
        if (b0Var.f2064a == 0) {
            return null;
        }
        return b0Var.c();
    }

    public T getLastSelected() {
        T t2 = this.lastSelected;
        if (t2 != null) {
            return t2;
        }
        b0<T> b0Var = this.selected;
        if (b0Var.f2064a > 0) {
            return b0Var.c();
        }
        return null;
    }

    public boolean getMultiple() {
        return this.multiple;
    }

    public boolean getRequired() {
        return this.required;
    }

    public boolean getToggle() {
        return this.toggle;
    }

    @Deprecated
    public boolean hasItems() {
        return this.selected.f2064a > 0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.isDisabled;
    }

    public boolean isEmpty() {
        return this.selected.f2064a == 0;
    }

    public b0<T> items() {
        return this.selected;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.selected.iterator();
    }

    public boolean notEmpty() {
        return this.selected.f2064a > 0;
    }

    public void remove(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.selected.remove(t2)) {
            if (this.programmaticChangeEvents && fireChangeEvent()) {
                this.selected.add(t2);
            } else {
                this.lastSelected = null;
                changed();
            }
        }
    }

    public void removeAll(a<T> aVar) {
        snapshot();
        int i2 = aVar.f1750b;
        boolean z2 = false;
        for (int i3 = 0; i3 < i2; i3++) {
            T t2 = aVar.get(i3);
            if (t2 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.selected.remove(t2)) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.programmaticChangeEvents && fireChangeEvent()) {
                revert();
            } else {
                this.lastSelected = null;
                changed();
            }
        }
        cleanup();
    }

    void revert() {
        this.selected.a(this.old.f2064a);
        this.selected.j(this.old);
    }

    public void set(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        b0<T> b0Var = this.selected;
        if (b0Var.f2064a == 1 && b0Var.c() == t2) {
            return;
        }
        snapshot();
        this.selected.a(8);
        this.selected.add(t2);
        if (this.programmaticChangeEvents && fireChangeEvent()) {
            revert();
        } else {
            this.lastSelected = t2;
            changed();
        }
        cleanup();
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setAll(a<T> aVar) {
        snapshot();
        this.lastSelected = null;
        this.selected.a(aVar.f1750b);
        int i2 = aVar.f1750b;
        boolean z2 = false;
        for (int i3 = 0; i3 < i2; i3++) {
            T t2 = aVar.get(i3);
            if (t2 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.selected.add(t2)) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.programmaticChangeEvents && fireChangeEvent()) {
                revert();
            } else if (aVar.f1750b > 0) {
                this.lastSelected = aVar.k();
                changed();
            }
        }
        cleanup();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z2) {
        this.isDisabled = z2;
    }

    public void setMultiple(boolean z2) {
        this.multiple = z2;
    }

    public void setProgrammaticChangeEvents(boolean z2) {
        this.programmaticChangeEvents = z2;
    }

    public void setRequired(boolean z2) {
        this.required = z2;
    }

    public void setToggle(boolean z2) {
        this.toggle = z2;
    }

    public int size() {
        return this.selected.f2064a;
    }

    void snapshot() {
        this.old.a(this.selected.f2064a);
        this.old.j(this.selected);
    }

    public a<T> toArray() {
        return this.selected.iterator().b();
    }

    public String toString() {
        return this.selected.toString();
    }

    public a<T> toArray(a<T> aVar) {
        this.selected.iterator().c(aVar);
        return aVar;
    }
}

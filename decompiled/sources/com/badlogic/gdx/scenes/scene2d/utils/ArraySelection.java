package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ArraySelection<T> extends Selection<T> {
    private a<T> array;
    private boolean rangeSelect = true;
    private T rangeStart;

    public ArraySelection(a<T> aVar) {
        this.array = aVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
    protected void changed() {
        this.rangeStart = null;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
    public void choose(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.isDisabled) {
            return;
        }
        if (!this.rangeSelect || !this.multiple) {
            super.choose(t2);
            return;
        }
        if (this.selected.f2064a > 0 && UIUtils.shift()) {
            T t3 = this.rangeStart;
            int iH = t3 == null ? -1 : this.array.h(t3, false);
            if (iH != -1) {
                T t4 = this.rangeStart;
                snapshot();
                int iH2 = this.array.h(t2, false);
                if (iH > iH2) {
                    int i2 = iH;
                    iH = iH2;
                    iH2 = i2;
                }
                if (!UIUtils.ctrl()) {
                    this.selected.a(8);
                }
                while (iH <= iH2) {
                    this.selected.add(this.array.get(iH));
                    iH++;
                }
                if (fireChangeEvent()) {
                    revert();
                } else {
                    changed();
                }
                this.rangeStart = t4;
                cleanup();
                return;
            }
        }
        super.choose(t2);
        this.rangeStart = t2;
    }

    public boolean getRangeSelect() {
        return this.rangeSelect;
    }

    public void setRangeSelect(boolean z2) {
        this.rangeSelect = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void validate() {
        a<T> aVar = this.array;
        if (aVar.f1750b == 0) {
            clear();
            return;
        }
        b0.a<T> it = items().iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (!aVar.e(it.next(), false)) {
                it.remove();
                z2 = true;
            }
        }
        if (this.required && this.selected.f2064a == 0) {
            set(aVar.g());
        } else if (z2) {
            changed();
        }
    }
}

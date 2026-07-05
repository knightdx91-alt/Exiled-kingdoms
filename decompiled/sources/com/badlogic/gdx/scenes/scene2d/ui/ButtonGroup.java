package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ButtonGroup<T extends Button> {
    private final a<T> buttons;
    private a<T> checkedButtons;
    private T lastChecked;
    private int maxCheckCount;
    private int minCheckCount;
    private boolean uncheckLast;

    public ButtonGroup() {
        this.buttons = new a<>();
        this.checkedButtons = new a<>(true, 1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 1;
    }

    public void add(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        t2.buttonGroup = null;
        boolean z2 = t2.isChecked() || this.buttons.f1750b < this.minCheckCount;
        t2.setChecked(false);
        t2.buttonGroup = this;
        this.buttons.a(t2);
        t2.setChecked(z2);
    }

    protected boolean canCheck(T t2, boolean z2) {
        if (t2.isChecked == z2) {
            return false;
        }
        if (z2) {
            int i2 = this.maxCheckCount;
            if (i2 != -1 && this.checkedButtons.f1750b >= i2) {
                if (!this.uncheckLast) {
                    return false;
                }
                int i3 = this.minCheckCount;
                this.minCheckCount = 0;
                this.lastChecked.setChecked(false);
                this.minCheckCount = i3;
            }
            this.checkedButtons.a(t2);
            this.lastChecked = t2;
        } else {
            a<T> aVar = this.checkedButtons;
            if (aVar.f1750b <= this.minCheckCount) {
                return false;
            }
            aVar.q(t2, true);
        }
        return true;
    }

    public void clear() {
        this.buttons.clear();
        this.checkedButtons.clear();
    }

    public a<T> getAllChecked() {
        return this.checkedButtons;
    }

    public a<T> getButtons() {
        return this.buttons;
    }

    public T getChecked() {
        a<T> aVar = this.checkedButtons;
        if (aVar.f1750b > 0) {
            return aVar.get(0);
        }
        return null;
    }

    public int getCheckedIndex() {
        a<T> aVar = this.checkedButtons;
        if (aVar.f1750b > 0) {
            return this.buttons.h(aVar.get(0), true);
        }
        return -1;
    }

    public void remove(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        t2.buttonGroup = null;
        this.buttons.q(t2, true);
        this.checkedButtons.q(t2, true);
    }

    public void setChecked(String str) {
        if (str == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        int i2 = this.buttons.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            T t2 = this.buttons.get(i3);
            if ((t2 instanceof TextButton) && str.contentEquals(((TextButton) t2).getText())) {
                t2.setChecked(true);
                return;
            }
        }
    }

    public void setMaxCheckCount(int i2) {
        if (i2 == 0) {
            i2 = -1;
        }
        this.maxCheckCount = i2;
    }

    public void setMinCheckCount(int i2) {
        this.minCheckCount = i2;
    }

    public void setUncheckLast(boolean z2) {
        this.uncheckLast = z2;
    }

    public void uncheckAll() {
        int i2 = this.minCheckCount;
        this.minCheckCount = 0;
        int i3 = this.buttons.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            this.buttons.get(i4).setChecked(false);
        }
        this.minCheckCount = i2;
    }

    public void remove(T... tArr) {
        if (tArr != null) {
            for (T t2 : tArr) {
                remove(t2);
            }
            return;
        }
        throw new IllegalArgumentException("buttons cannot be null.");
    }

    public void add(T... tArr) {
        if (tArr != null) {
            for (T t2 : tArr) {
                add(t2);
            }
            return;
        }
        throw new IllegalArgumentException("buttons cannot be null.");
    }

    public ButtonGroup(T... tArr) {
        this.buttons = new a<>();
        this.checkedButtons = new a<>(true, 1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 0;
        add(tArr);
        this.minCheckCount = 1;
    }
}

package com.badlogic.gdx;

import com.badlogic.gdx.utils.k0;

/* JADX INFO: compiled from: InputMultiplexer.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class i implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private k0<j> f1702a = new k0<>(true, 4);

    public final void a(j jVar) {
        if (jVar == null) {
            throw new NullPointerException("processor cannot be null");
        }
        this.f1702a.a(jVar);
    }

    @Override // com.badlogic.gdx.j
    public final boolean keyDown(int i2) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i3 = k0Var.f1750b;
            for (int i4 = 0; i4 < i3; i4++) {
                if (jVarArrW[i4].keyDown(i2)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean keyTyped(char c2) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i2 = k0Var.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                if (jVarArrW[i3].keyTyped(c2)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean keyUp(int i2) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i3 = k0Var.f1750b;
            for (int i4 = 0; i4 < i3; i4++) {
                if (jVarArrW[i4].keyUp(i2)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean mouseMoved(int i2, int i3) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i4 = k0Var.f1750b;
            for (int i5 = 0; i5 < i4; i5++) {
                if (jVarArrW[i5].mouseMoved(i2, i3)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean scrolled(float f2, float f3) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i2 = k0Var.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                if (jVarArrW[i3].scrolled(f2, f3)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean touchDown(int i2, int i3, int i4, int i5) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i6 = k0Var.f1750b;
            for (int i7 = 0; i7 < i6; i7++) {
                if (jVarArrW[i7].touchDown(i2, i3, i4, i5)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean touchDragged(int i2, int i3, int i4) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i5 = k0Var.f1750b;
            for (int i6 = 0; i6 < i5; i6++) {
                if (jVarArrW[i6].touchDragged(i2, i3, i4)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }

    @Override // com.badlogic.gdx.j
    public final boolean touchUp(int i2, int i3, int i4, int i5) {
        k0<j> k0Var = this.f1702a;
        j[] jVarArrW = k0Var.w();
        try {
            int i6 = k0Var.f1750b;
            for (int i7 = 0; i7 < i6; i7++) {
                if (jVarArrW[i7].touchUp(i2, i3, i4, i5)) {
                    k0Var.x();
                    return true;
                }
            }
            return false;
        } finally {
            k0Var.x();
        }
    }
}

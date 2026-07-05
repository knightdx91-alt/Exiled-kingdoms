package com.badlogic.gdx.utils;

import com.badlogic.gdx.graphics.GL20;

/* JADX INFO: compiled from: SerializationException.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h0 extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private o0 f1814a;

    public h0(Exception exc) {
        super("", exc);
    }

    public final void a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("info cannot be null.");
        }
        if (this.f1814a == null) {
            this.f1814a = new o0(GL20.GL_NEVER);
        }
        this.f1814a.g('\n');
        this.f1814a.h(str);
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        if (this.f1814a == null) {
            return super.getMessage();
        }
        o0 o0Var = new o0(GL20.GL_NEVER);
        o0Var.h(super.getMessage());
        if (o0Var.f1854b > 0) {
            o0Var.g('\n');
        }
        o0Var.h("Serialization trace:");
        o0Var.c(this.f1814a);
        return o0Var.toString();
    }
}

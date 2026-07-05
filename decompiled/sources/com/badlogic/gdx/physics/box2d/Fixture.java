package com.badlogic.gdx.physics.box2d;

import f0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Fixture {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final short[] f1733b = new short[3];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f1734c = new a();

    protected Fixture() {
    }

    private native void jniGetFilterData(long j2, short[] sArr);

    public final a a() {
        if (this.f1732a == null) {
            short[] sArr = this.f1733b;
            jniGetFilterData(0L, sArr);
            a aVar = new a();
            this.f1732a = aVar;
            aVar.f2209b = sArr[0];
            aVar.f2208a = sArr[1];
            aVar.f2210c = sArr[2];
        }
        a aVar2 = this.f1732a;
        a aVar3 = this.f1734c;
        aVar3.f2208a = aVar2.f2208a;
        aVar3.f2209b = aVar2.f2209b;
        aVar3.f2210c = aVar2.f2210c;
        return aVar3;
    }
}

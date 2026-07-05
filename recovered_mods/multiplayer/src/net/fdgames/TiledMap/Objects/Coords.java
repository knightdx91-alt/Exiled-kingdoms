package net.fdgames.TiledMap.Objects;

import m0.q;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Coords {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3508x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3509y;

    public Coords() {
        this.f3508x = 0;
        this.f3509y = 0;
    }

    public Coords(int i2, int i3) {
        this.f3508x = i2;
        this.f3509y = i3;
    }

    public final q a() {
        return new q(this.f3508x, this.f3509y);
    }

    public final String toString() {
        return "" + this.f3508x + "," + this.f3509y;
    }
}

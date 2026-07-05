package net.fdgames.TiledMap.Objects;

import a0.q;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Coords {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3287x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3288y;

    public Coords(int i2, int i3) {
        this.f3287x = i2;
        this.f3288y = i3;
    }

    public final q a() {
        return new q(this.f3287x, this.f3288y);
    }

    public final String toString() {
        return "" + this.f3287x + "," + this.f3288y;
    }

    public Coords() {
        this.f3287x = 0;
        this.f3288y = 0;
    }
}

package net.fdgames.TiledMap.Objects;

import a0.p;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class MapArea {
    public Coords coords = new Coords(0, 0);
    private p rectangle;

    public p a() {
        return this.rectangle;
    }

    public final void b(p pVar) {
        this.rectangle = new p(pVar.f89x, pVar.f90y, pVar.width, pVar.height);
    }
}

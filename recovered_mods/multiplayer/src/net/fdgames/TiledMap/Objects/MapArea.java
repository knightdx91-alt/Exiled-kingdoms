package net.fdgames.TiledMap.Objects;

import m0.p;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class MapArea {
    public Coords coords = new Coords(0, 0);
    private p rectangle;

    public p a() {
        return this.rectangle;
    }

    public final void b(p pVar) {
        this.rectangle = new p(pVar.f3176x, pVar.f3177y, pVar.width, pVar.height);
    }
}

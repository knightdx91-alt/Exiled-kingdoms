package net.fdgames.TiledMap.Objects;

import a0.p;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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

package x;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import u.f;
import w.d;

/* JADX INFO: compiled from: TiledMapTileMapObject.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextureRegion f4087b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d f4088c;

    public a(d dVar, boolean z2, boolean z3) {
        this.f4087b = null;
        this.f4088c = dVar;
        TextureRegion textureRegion = new TextureRegion(dVar.b());
        textureRegion.flip(z2, z3);
        this.f4087b = textureRegion;
    }

    public final TextureRegion b() {
        return this.f4087b;
    }
}

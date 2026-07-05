package m0;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.MapConversation;

/* JADX INFO: compiled from: NPCConversation.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d implements MapConversation {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2443a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2444b;

    public d(int i2, String str) {
        this.f2443a = i2;
        this.f2444b = str;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final int a() {
        return this.f2443a;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final String b() {
        return this.f2444b;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final TextureRegion c() {
        MapObject mapObjectH = GameLevel.h(this.f2443a);
        if (mapObjectH == null) {
            return null;
        }
        return mapObjectH.E();
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final boolean d() {
        return !GameLevel.h(this.f2443a).s();
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final void e() {
        int i2 = this.f2443a;
        GameLevel.i(i2).J(GameData.v().player.q());
        GameData.v().player.J(i2);
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final String getName() {
        return GameLevel.h(this.f2443a).getName();
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final Coords getPosition() {
        return GameLevel.i(this.f2443a).B();
    }
}

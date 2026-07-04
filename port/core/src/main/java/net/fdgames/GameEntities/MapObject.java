package net.fdgames.GameEntities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class MapObject extends GameObject implements Comparable<MapObject> {
    protected String conversationTag = "";
    private Coords coords;
    public String tag;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3092x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3093y;

    public String A() {
        return this.conversationTag;
    }

    public final Coords B() {
        Coords coords = this.coords;
        if (coords == null) {
            this.coords = new Coords(this.f3092x, this.f3093y);
        } else {
            coords.f3287x = this.f3092x;
            coords.f3288y = this.f3093y;
        }
        return this.coords;
    }

    public String C() {
        return getName();
    }

    public String D() {
        return getName();
    }

    public abstract TextureRegion E();

    public abstract String getName();

    @Override // net.fdgames.GameEntities.GameObject
    public void v(String str, int i2, String str2, DamageData damageData) {
        u(i2, str, str2);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public int compareTo(MapObject mapObject) {
        return ((mapObject.f3093y * 32) + mapObject.f3092x) - ((this.f3093y * 32) + this.f3092x);
    }
}

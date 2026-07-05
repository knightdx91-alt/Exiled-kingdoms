package net.fdgames.TiledMap.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class ItemConversation implements MapConversation {
    private ConditionsSet conditions;
    private String conversation_id;
    private String iconName;
    private String name;
    private Coords position;
    public String tag;

    public ItemConversation() {
    }

    public ItemConversation(String str, String str2, String str3, int i2, int i3, String str4) {
        this.iconName = str;
        this.conversation_id = str3;
        this.name = str2;
        this.position = new Coords(i2, i3);
        this.conditions = new ConditionsSet(str4);
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final int a() {
        return 0;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final String b() {
        return this.conversation_id;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final TextureRegion c() {
        return Assets.a(this.iconName);
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final boolean d() {
        return true;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final void e() {
    }

    public final boolean f() {
        return this.conditions.a().booleanValue();
    }

    public final int g() {
        return this.position.f3508x;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final String getName() {
        return this.name;
    }

    @Override // net.fdgames.TiledMap.Objects.MapConversation
    public final Coords getPosition() {
        return this.position;
    }

    public final int h() {
        return this.position.f3509y;
    }

    public final void i(String str) {
        this.name = str;
    }
}

package net.fdgames.GameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class WorldContainer implements Lootable {
    public Items items = new Items();
    public int gold = 0;
    public String id = "";

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final float a() {
        return 0.0f;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void b() {
        e();
        this.items.u();
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void c(int i2) {
        this.items.q(i2);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void d() {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void e() {
        GameData.v().player.B1(this.gold);
        this.gold = 0;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean f() {
        return false;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean g() {
        return true;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final int getItem(int i2) {
        return this.items.h(i2);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final String getName() {
        return this.id.replace("_", " ");
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Lootable.LootableType getType() {
        return Lootable.LootableType.MAPCONTAINER;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final int h() {
        return this.gold;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean i(int i2) {
        return this.items.a(i2, 1);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Boolean isEmpty() {
        return this.items.p();
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Items j() {
        return this.items;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean k(int i2) {
        return this.items.c(i2);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final TextureRegion l() {
        return this.id.contains("holding") ? GameAssets.B : Assets.a("chest_big1");
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void removeItem(int i2) {
        this.items.s(i2);
    }
}

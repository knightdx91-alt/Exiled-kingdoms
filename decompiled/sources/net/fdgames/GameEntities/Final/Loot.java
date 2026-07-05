package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Loot extends MapSprite implements Lootable {
    private int gold;
    private Items items;

    public Loot() {
        this.items = new Items();
        this.gold = 0;
    }

    private void N() {
        Boolean bool = Boolean.TRUE;
        for (int i2 = 0; i2 < 20; i2++) {
            if (this.items.h(i2) != 0) {
                bool = Boolean.FALSE;
            }
        }
        if (this.gold > 0) {
            bool = Boolean.FALSE;
        }
        if (bool.booleanValue()) {
            this.destroy = true;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return GameAssets.A;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        Items items = this.items;
        if (items == null || items.p().booleanValue()) {
            spriteBatch.draw(GameAssets.C, H() - 64, I() + 6, 16.0f, 16.0f);
        } else {
            spriteBatch.draw(GameAssets.A, H() - 64, I() + 6, 16.0f, 16.0f);
        }
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 32;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final float a() {
        return 0.0f;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void b() {
        e();
        this.items.u();
        N();
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
        N();
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean f() {
        return this.items.m();
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean g() {
        return false;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final int getItem(int i2) {
        return this.items.h(i2);
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return GameString.b("LOOT", false);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Lootable.LootableType getType() {
        return Lootable.LootableType.f3065b;
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
        return GameAssets.A;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void removeItem(int i2) {
        this.items.s(i2);
        N();
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (str.equals("DESTROY")) {
            this.destroy = true;
        }
    }

    public Loot(int i2, int i3, int i4) {
        Items items = new Items();
        this.items = items;
        this.gold = 0;
        this.f3092x = i2;
        this.f3093y = i3;
        items.a(i4, 1);
    }

    public Loot(int i2, int i3, ArrayList<Integer> arrayList, int i4) {
        Items items = new Items();
        this.items = items;
        this.f3092x = i2;
        this.f3093y = i3;
        this.gold = i4;
        items.b(arrayList);
        this.items.g();
    }

    public Loot(int i2, int i3, ArrayList<Loot> arrayList) {
        this.items = new Items();
        this.gold = 0;
        this.f3092x = i2;
        this.f3093y = i3;
        for (Loot loot : arrayList) {
            if (!loot.destroy) {
                this.gold += loot.gold;
                for (int i4 = 0; i4 < 20; i4++) {
                    if (loot.items.j(i4).itemID != 0) {
                        this.items.a(loot.items.j(i4).itemID, loot.items.j(i4).units);
                    }
                }
                loot.destroy = true;
            }
        }
        this.items.g();
    }
}

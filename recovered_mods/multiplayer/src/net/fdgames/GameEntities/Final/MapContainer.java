package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Quests;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class MapContainer extends MapObject implements Lootable {
    public String name;
    public String iconName = "";
    public Items items = new Items();
    public int gold = 0;
    public String loot_table = "";
    public String quest_location = "";
    private String container_tag = "";

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return Assets.a(this.iconName);
    }

    public final void F() {
        if (!this.quest_location.equals("") && DynamicQuest.j(this.quest_location) > 0) {
            DynamicQuest dynamicQuestE = DynamicQuest.e(DynamicQuest.j(this.quest_location));
            Quests quests = GameWorld.f3408a;
            String str = dynamicQuestE.quest_ID;
            String str2 = dynamicQuestE.variation_ID;
            quests.getClass();
            int i2 = Quests.d(str, str2).item_id;
            if (i2 <= 0 || this.items.d(i2)) {
                return;
            }
            this.items.a(i2, 1);
        }
    }

    public final void G() {
        String strTrim = FDUtils.d(this.loot_table).toLowerCase(Locale.ENGLISH).trim();
        this.loot_table = strTrim;
        for (String str : Arrays.asList(strTrim.split(","))) {
            Iterator<Integer> it = Rules.e(str).iterator();
            while (it.hasNext()) {
                this.items.a(it.next().intValue(), 1);
            }
            this.gold += Rules.d(str);
        }
        if (this.items.p().booleanValue() && this.gold == 0) {
            this.items.a(new Random().nextInt(4) + GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE, 1);
        }
    }

    public final void H(String str) {
        this.container_tag = str;
    }

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
        String str = this.container_tag;
        if (str == null || str.equals("")) {
            return;
        }
        GameData.v().L(this.container_tag);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void e() {
        GameData.v().player.B1(this.gold);
        this.gold = 0;
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
        return this.name;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Lootable.LootableType getType() {
        return Lootable.LootableType.f3279b;
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
        return Assets.a(this.iconName);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void removeItem(int i2) {
        this.items.s(i2);
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        throw null;
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
    }
}

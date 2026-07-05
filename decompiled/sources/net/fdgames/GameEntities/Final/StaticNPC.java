package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Locale;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class StaticNPC extends MapSprite {
    private Character.Gender gender;
    public Boolean isLeft;
    private String name;
    private int portraitIndex;
    private String region_id;
    private Shop shop;
    public ConditionsSet spawnConditions;
    private int spriteIndex;
    private boolean updatedSpriteIndex;

    public StaticNPC() {
        this.updatedSpriteIndex = false;
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return Assets.n(this.gender, this.portraitIndex);
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        if (!this.updatedSpriteIndex) {
            this.spriteIndex = Assets.d(this.region_id).intValue();
            this.updatedSpriteIndex = true;
        }
        spriteBatch.draw(Assets.c(this.spriteIndex, this.isLeft.booleanValue()), H(), I());
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 32;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void L() {
        throw null;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
    }

    public final void N(String str) {
        this.name = str;
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return this.name;
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        str.equals("CLOSE");
    }

    public StaticNPC(int i2, int i3, String str, String str2, String str3, int i4, String str4, String str5, Shop shop, String str6, ConditionsSet conditionsSet) {
        this.updatedSpriteIndex = false;
        this.f3092x = i2;
        this.f3093y = i3;
        this.tag = str6;
        String strTrim = str.trim();
        Locale locale = Locale.ENGLISH;
        if (strTrim.toLowerCase(locale).contains("r")) {
            this.isLeft = Boolean.FALSE;
        } else {
            this.isLeft = Boolean.TRUE;
        }
        if (str4.trim().toLowerCase(locale).contains("f")) {
            this.gender = Character.Gender.f2993b;
        } else {
            this.gender = Character.Gender.f2992a;
        }
        this.spawnConditions = conditionsSet;
        this.region_id = str2;
        this.shop = shop;
        if (shop != null) {
            shop.u(q());
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameLevelData.o().shops.add(this.shop);
        }
        this.conversationTag = str3;
        this.name = str5;
        this.portraitIndex = i4;
        this.spriteIndex = Assets.d(this.region_id).intValue();
        this.updatedSpriteIndex = true;
    }
}

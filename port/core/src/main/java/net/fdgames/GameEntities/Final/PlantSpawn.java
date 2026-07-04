package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Iterator;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.Rules.Plant;
import net.fdgames.Rules.Plants;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class PlantSpawn extends MapSprite {
    public String iconName;
    public int item_ID;

    public PlantSpawn() {
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return Assets.a(this.iconName);
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        spriteBatch.draw(Assets.a(this.iconName), H(), I() + 6, 16.0f, 16.0f);
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 32;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return Rules.g(this.item_ID);
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        throw null;
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
    }

    public PlantSpawn(String str, int i2, int i3) {
        Plant next;
        this.iconName = str;
        Iterator<Plant> it = Plants.f3246a.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.ID.equals(str)) {
                    break;
                }
            }
        }
        this.item_ID = next.item_ID;
        this.f3092x = i2;
        this.f3093y = i3;
    }
}

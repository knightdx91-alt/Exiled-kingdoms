package net.fdgames.TiledMap.Objects;

import com.badlogic.gdx.math.Rectangle;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Trigger extends MapArea {
    public ActionsSet actions;
    public ConditionsSet conditions;
    private float lastActivated;
    public int owner;
    public int size;

    public Trigger(String str, String str2) {
        this.conditions = new ConditionsSet(str);
        this.actions = new ActionsSet(str2);
        this.owner = 0;
        this.size = 0;
    }

    @Override // net.fdgames.TiledMap.Objects.MapArea
    public final Rectangle a() {
        int i2 = this.owner;
        if (i2 == 0) {
            return super.a();
        }
        MapSprite mapSpriteI = GameLevel.i(i2);
        if (mapSpriteI == null) {
            return new Rectangle (0.0f, 0.0f, 0.0f, 0.0f);
        }
        int i3 = mapSpriteI.f3092x;
        int i4 = this.size;
        int i5 = i4 / 2;
        float f2 = i4;
        return new Rectangle (i3 - i5, mapSpriteI.f3093y - i5, f2, f2);
    }

    public final boolean c() {
        if (GameData.v().u() < this.lastActivated + 3.0f || !this.conditions.a().booleanValue()) {
            return false;
        }
        this.actions.a();
        this.lastActivated = GameData.v().u();
        return true;
    }

    public Trigger() {
    }
}

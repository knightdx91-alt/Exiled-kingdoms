package net.fdgames.GameEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class MapSprite extends MapObject {
    public Boolean visibleToPlayer = Boolean.FALSE;
    private Coords isoCoords = new Coords();

    public abstract void F(SpriteBatch spriteBatch);

    protected abstract int G();

    public final int H() {
        return (this.f3092x + this.f3093y) - (G() / 2);
    }

    public final int I() {
        return (this.f3093y - this.f3092x) / 2;
    }

    public void J(int i2) {
    }

    public boolean K() {
        return false;
    }

    public void L() {
        int i2 = this.f3092x;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int iAbs = Math.abs(i2 - GameData.v().player.f3092x);
        int iAbs2 = Math.abs(this.f3093y - GameData.v().player.f3093y);
        if (iAbs >= 224 || iAbs2 >= 224) {
            this.destroy = true;
        } else {
            int i3 = this.uniqueID;
            MessageRouter.a("TRY_TO_DESPAWN", i3, i3, null, 2.0f, null);
        }
    }

    public abstract void M(float f2);
}

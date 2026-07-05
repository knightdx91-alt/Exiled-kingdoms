package net.fdgames.GameEntities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public abstract class MapSprite extends MapObject {
    public Boolean visibleToPlayer = Boolean.FALSE;
    private Coords isoCoords = new Coords();

    public abstract void F(SpriteBatch spriteBatch);

    protected abstract int G();

    public final int H() {
        return (this.f3307x + this.f3308y) - (G() / 2);
    }

    public final int I() {
        return (this.f3308y - this.f3307x) / 2;
    }

    public void J(int i2) {
    }

    public boolean K() {
        return false;
    }

    public void L() {
        int i2 = this.f3307x;
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        int iAbs = Math.abs(i2 - GameData.v().player.f3307x);
        int iAbs2 = Math.abs(this.f3308y - GameData.v().player.f3308y);
        if (iAbs >= 224 || iAbs2 >= 224) {
            this.destroy = true;
        } else {
            int i3 = this.uniqueID;
            MessageRouter.a("TRY_TO_DESPAWN", i3, i3, null, 2.0f, null);
        }
    }

    public abstract void M(float f2);
}

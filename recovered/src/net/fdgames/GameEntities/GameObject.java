package net.fdgames.GameEntities;

import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.MessageRouter;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class GameObject {
    protected int uniqueID;
    protected int worldfaction;
    public boolean destroy = false;
    protected Factions.Faction faction = Factions.Faction.f3061b;
    protected int[] worldfactions = new int[2];

    public GameObject() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameLevelData.o().lastID++;
        this.uniqueID = GameLevelData.o().lastID;
    }

    public final void m(float f2, int i2, String str) {
        MessageRouter.a(str, this.uniqueID, i2, null, f2, null);
    }

    public final void n(int i2, DamageData damageData) {
        MessageRouter.a("HIT", this.uniqueID, i2, "", 0.0f, damageData);
    }

    public final void o(String str, int i2, String str2, float f2, DamageData damageData) {
        MessageRouter.a(str, this.uniqueID, i2, str2, f2, damageData);
    }

    public final void p() {
        this.uniqueID = 1;
    }

    public final int q() {
        return this.uniqueID;
    }

    public final int[] r() {
        if (this.worldfactions == null) {
            this.worldfactions = new int[2];
        }
        return this.worldfactions;
    }

    public final boolean s() {
        return GameWorld.f3189c.f(this.worldfactions);
    }

    public final boolean t(int[] iArr) {
        int i2;
        if (this.worldfactions == null) {
            this.worldfactions = new int[2];
        }
        if (iArr == null) {
            iArr = new int[2];
        }
        int i3 = iArr[0];
        if (i3 != 0) {
            int[] iArr2 = this.worldfactions;
            if (iArr2[0] == i3 || iArr2[1] == i3) {
                return true;
            }
        }
        if (iArr.length >= 2 && (i2 = iArr[1]) != 0) {
            int[] iArr3 = this.worldfactions;
            if (iArr3[0] == i2 || iArr3[1] == i2) {
                return true;
            }
        }
        return false;
    }

    public void u(int i2, String str, String str2) {
        ((SecretDoor) this).u(i2, str, str2);
    }

    public abstract void v(String str, int i2, String str2, DamageData damageData);

    public final void w() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameLevelData.o().lastID++;
        this.uniqueID = GameLevelData.o().lastID;
    }

    public final void x(int i2) {
        if (this.worldfactions == null) {
            this.worldfactions = new int[2];
        }
        int[] iArr = this.worldfactions;
        iArr[0] = i2;
        iArr[1] = 0;
    }

    public final void y(int[] iArr) {
        if (this.worldfactions == null) {
            this.worldfactions = new int[2];
        }
        int[] iArr2 = this.worldfactions;
        iArr2[0] = iArr[0];
        if (iArr.length > 1) {
            iArr2[1] = iArr[1];
        }
    }
}

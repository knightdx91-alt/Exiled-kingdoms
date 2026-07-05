package net.fdgames.GameEntities.AI;

import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.TiledMap.Objects.Coords;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class CivilianAI extends AI {
    @Override // net.fdgames.GameEntities.AI.AI
    public final void a(AI.NPCState nPCState) {
        NPC npc = (NPC) GameLevel.g(this.npc);
        if (npc != null) {
            int iOrdinal = nPCState.ordinal();
            if (iOrdinal == 0) {
                this.state = AI.NPCState.f3189b;
                npc.m(FDUtils.b(8, 16) * 1.0f, this.npc, "RANDOM_MOVE");
            } else if (iOrdinal == 2) {
                this.state = AI.NPCState.f3191d;
            } else {
                if (iOrdinal != 3) {
                    return;
                }
                this.state = AI.NPCState.f3192e;
            }
        }
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void c(int i2) {
        this.npc = i2;
        NPC npc = (NPC) GameLevel.g(i2);
        if (npc == null || this.state.ordinal() != 0) {
            return;
        }
        Coords coords = npc.waypointDestination;
        if (coords != null) {
            npc.F1(0, new Coords(coords.f3508x, coords.f3509y));
            if (npc.a0().f3508x == -1 || (npc.a0().f3508x == npc.B().f3508x && npc.a0().f3509y == npc.B().f3509y)) {
                npc.p0(npc.waypointDestination);
            }
            npc.I1();
            return;
        }
        if (npc.d0() == MapActor.ActorState.f3287c && npc.detectedEnemyID == 0) {
            GameLevel.k();
            if (Player.f3242f || b.r(npc.f3307x, npc.f3308y, GameData.v().player.f3307x, GameData.v().player.f3308y) >= 35 || GameWorld.f3410c.f(npc.r())) {
                return;
            }
            npc.C0();
            npc.q0(MapActor.ActorState.f3286b);
        }
    }
}

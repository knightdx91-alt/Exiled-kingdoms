package net.fdgames.GameEntities.AI;

import m0.b;
import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class CivilianAI extends AI {
    @Override // net.fdgames.GameEntities.AI.AI
    public final void a(AI.NPCState nPCState) {
        NPC npc = (NPC) GameLevel.g(this.npc);
        if (npc != null) {
            int iOrdinal = nPCState.ordinal();
            if (iOrdinal == 0) {
                this.state = AI.NPCState.IDLE;
                npc.m(FDUtils.b(8, 16) * 1.0f, this.npc, "RANDOM_MOVE");
            } else if (iOrdinal == 2) {
                this.state = AI.NPCState.FLEEING;
            } else {
                if (iOrdinal != 3) {
                    return;
                }
                this.state = AI.NPCState.DEAD;
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
            npc.F1(0, new Coords(coords.f3287x, coords.f3288y));
            if (npc.a0().f3287x == -1 || (npc.a0().f3287x == npc.B().f3287x && npc.a0().f3288y == npc.B().f3288y)) {
                npc.p0(npc.waypointDestination);
            }
            npc.I1();
            return;
        }
        if (npc.d0() == MapActor.ActorState.MOVING && npc.detectedEnemyID == 0) {
            GameLevel.k();
            if (Player.f3027e || b.r(npc.f3092x, npc.f3093y, GameData.v().player.f3092x, GameData.v().player.f3093y) >= 35 || GameWorld.f3189c.f(npc.r())) {
                return;
            }
            npc.C0();
            npc.q0(MapActor.ActorState.IDLE);
        }
    }
}

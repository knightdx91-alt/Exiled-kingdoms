package net.fdgames.GameEntities.AI;

import m0.b;
import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class PatrollerAI extends AI {
    public PatrollerAI() {
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void a(AI.NPCState nPCState) {
        NPC npc = (NPC) GameLevel.g(this.npc);
        if (npc == null || this.state == null) {
            return;
        }
        int iOrdinal = nPCState.ordinal();
        if (iOrdinal == 0) {
            this.state = AI.NPCState.f2974a;
            if (npc.waypointDestination == null) {
                npc.m(0.3f, this.npc, "RANDOM_MOVE");
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            this.state = AI.NPCState.f2975b;
            this.targetActorID = npc.detectedEnemyID;
        } else if (iOrdinal == 2) {
            this.state = AI.NPCState.f2976c;
        } else {
            if (iOrdinal != 3) {
                return;
            }
            this.state = AI.NPCState.f2977d;
        }
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void c(int i2) {
        AI.NPCState nPCState;
        int i3;
        this.npc = i2;
        NPC npc = (NPC) GameLevel.g(i2);
        if (npc == null || (nPCState = this.state) == null) {
            return;
        }
        int i4 = npc.detectedEnemyID;
        if (i4 > 0 && (i3 = this.targetActorID) > 0 && i4 != i3) {
            this.targetActorID = i4;
        }
        int iOrdinal = nPCState.ordinal();
        if (iOrdinal == 0) {
            npc.H1();
            int i5 = npc.detectedEnemyID;
            if (i5 > 0) {
                npc.U(i5);
                return;
            }
            Coords coords = npc.waypointDestination;
            if (coords != null) {
                npc.F1(0, new Coords(coords.f3287x, coords.f3288y));
                if (npc.a0().f3287x == -1 || (npc.a0().f3287x == npc.B().f3287x && npc.a0().f3288y == npc.B().f3288y)) {
                    npc.p0(npc.waypointDestination);
                }
                npc.I1();
            }
            if (npc.B0() && npc.waypointDestination == null) {
                npc.E1();
            }
            if (npc.d0() == MapActor.ActorState.f3072b && npc.detectedEnemyID == 0) {
                GameLevel.k();
                if (Player.f3027e || b.r(npc.f3092x, npc.f3093y, GameData.v().player.f3092x, GameData.v().player.f3093y) >= 35 || GameWorld.f3189c.f(npc.r())) {
                    return;
                }
                npc.C0();
                npc.q0(MapActor.ActorState.f3071a);
                return;
            }
            return;
        }
        if (iOrdinal != 1) {
            return;
        }
        int i6 = this.targetActorID;
        AI.NPCState nPCState2 = AI.NPCState.f2974a;
        if (i6 <= 0 || GameLevel.g(i6) == null) {
            a(nPCState2);
            return;
        }
        MapActor.ActorState actorStateD0 = GameLevel.g(this.targetActorID).d0();
        if (actorStateD0 == MapActor.ActorState.f3074d || actorStateD0 == MapActor.ActorState.f3079i) {
            a(nPCState2);
            return;
        }
        if (npc.g0()) {
            return;
        }
        if (npc.N1()) {
            AISkillUsage.a(npc);
        }
        if (!npc.A0(this.targetActorID, false)) {
            npc.D1(this.targetActorID);
            if (npc.a0().f3287x == -1) {
                npc.D1(b.P().d(npc.B(), npc.r(), 224));
            }
            if (npc.waypointDestination != null) {
                if (npc.a0().f3287x == -1 || (npc.a0().f3287x == npc.B().f3287x && npc.a0().f3288y == npc.B().f3288y)) {
                    npc.p0(npc.waypointDestination);
                    return;
                }
                return;
            }
            return;
        }
        if (npc.U0() && !b.P().e0(npc.B(), GameLevel.g(this.targetActorID).B())) {
            npc.D1(this.targetActorID);
            return;
        }
        npc.C0();
        if (npc.N1() && !npc.U0()) {
            AISkillUsage.b(npc);
        }
        if (npc.g0()) {
            return;
        }
        npc.E0(this.targetActorID);
    }

    public PatrollerAI(int i2) {
        super(i2);
        a(AI.NPCState.f2974a);
    }
}

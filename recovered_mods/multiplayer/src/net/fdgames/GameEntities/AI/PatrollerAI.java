package net.fdgames.GameEntities.AI;

import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.ek.android.lan.LanGameBridge;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class PatrollerAI extends AI {
    public PatrollerAI() {
    }

    public PatrollerAI(int i2) {
        super(i2);
        a(AI.NPCState.f3189b);
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void a(AI.NPCState nPCState) {
        NPC npc = (NPC) GameLevel.g(this.npc);
        if (npc == null || this.state == null) {
            return;
        }
        int iOrdinal = nPCState.ordinal();
        if (iOrdinal == 0) {
            this.state = AI.NPCState.f3189b;
            if (npc.waypointDestination == null) {
                npc.m(0.3f, this.npc, "RANDOM_MOVE");
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            this.state = AI.NPCState.f3190c;
            this.targetActorID = npc.detectedEnemyID;
        } else if (iOrdinal == 2) {
            this.state = AI.NPCState.f3191d;
        } else {
            if (iOrdinal != 3) {
                return;
            }
            this.state = AI.NPCState.f3192e;
        }
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void c(int i2) {
        AI.NPCState nPCState;
        MapActor mapActorResolveHostileTargetActor;
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
                npc.F1(0, new Coords(coords.f3508x, coords.f3509y));
                if (npc.a0().f3508x == -1 || (npc.a0().f3508x == npc.B().f3508x && npc.a0().f3509y == npc.B().f3509y)) {
                    npc.p0(npc.waypointDestination);
                }
                npc.I1();
            }
            if (npc.B0() && npc.waypointDestination == null) {
                npc.E1();
            }
            if (npc.d0() != MapActor.ActorState.f3287c || npc.detectedEnemyID != 0 || (mapActorResolveHostileTargetActor = LanGameBridge.resolveHostileTargetActor(npc)) == null || b.r(npc.f3307x, npc.f3308y, mapActorResolveHostileTargetActor.f3307x, mapActorResolveHostileTargetActor.f3308y) >= 35 || GameWorld.f3410c.f(npc.r())) {
                return;
            }
            npc.C0();
            npc.q0(MapActor.ActorState.f3286b);
            return;
        }
        if (iOrdinal != 1) {
            return;
        }
        int i6 = this.targetActorID;
        AI.NPCState nPCState2 = AI.NPCState.f3189b;
        if (i6 <= 0 || GameLevel.g(i6) == null) {
            a(nPCState2);
            return;
        }
        MapActor.ActorState actorStateD0 = GameLevel.g(this.targetActorID).d0();
        if (actorStateD0 == MapActor.ActorState.f3289e || actorStateD0 == MapActor.ActorState.f3294j) {
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
            if (npc.a0().f3508x == -1) {
                npc.D1(b.P().d(npc.B(), npc.r(), 224));
            }
            if (npc.waypointDestination != null) {
                if (npc.a0().f3508x == -1 || (npc.a0().f3508x == npc.B().f3508x && npc.a0().f3509y == npc.B().f3509y)) {
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
}

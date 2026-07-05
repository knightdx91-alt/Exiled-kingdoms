package net.fdgames.GameEntities.AI;

import m0.b;
import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CompanionAI extends AI {
    private int targetActorID;
    Trap trap;

    @Override // net.fdgames.GameEntities.AI.AI
    public final void a(AI.NPCState nPCState) {
        NPC npc = (NPC) GameLevel.g(this.npc);
        if (npc == null || this.state == null) {
            return;
        }
        int iOrdinal = nPCState.ordinal();
        if (iOrdinal == 0) {
            this.state = AI.NPCState.f2974a;
            return;
        }
        if (iOrdinal == 1) {
            this.state = AI.NPCState.f2975b;
            this.targetActorID = npc.detectedEnemyID;
        } else if (iOrdinal == 2) {
            this.state = AI.NPCState.f2976c;
        } else if (iOrdinal == 3) {
            this.state = AI.NPCState.f2977d;
        } else {
            if (iOrdinal != 4) {
                return;
            }
            this.state = AI.NPCState.f2978e;
        }
    }

    @Override // net.fdgames.GameEntities.AI.AI
    public final void c(int i2) {
        Trap trap;
        this.npc = i2;
        NPC npc = (NPC) GameLevel.g(i2);
        if (npc == null || this.state == null) {
            return;
        }
        int iS = b.s(GameData.v().player.B(), npc.B());
        boolean z2 = npc.wait;
        if (!z2 && iS > 384) {
            npc.f3092x = GameData.v().player.f3092x + 32;
            npc.f3093y = GameData.v().player.f3093y + 32;
            return;
        }
        if (!z2 && iS > 224) {
            npc.p0(GameData.v().player.B());
        }
        int iOrdinal = this.state.ordinal();
        int i3 = 0;
        if (iOrdinal == 0) {
            int i4 = npc.attackStrategy;
            int i5 = NPC.f3023b;
            if (i4 != 3 && i4 != 2) {
                npc.H1();
                int i6 = npc.detectedEnemyID;
                if (i6 > 0) {
                    npc.U(i6);
                    return;
                }
            }
            if (npc.sheet.skillSet.g("trap_master") > 0) {
                b bVarP = b.P();
                int i7 = npc.f3092x;
                int i8 = npc.f3093y;
                bVarP.getClass();
                while (true) {
                    if (i3 >= GameLevelData.z().size()) {
                        trap = null;
                        break;
                    } else {
                        if (Math.abs(GameLevelData.z().get(i3).f3092x - i7) < 128 && Math.abs(GameLevelData.z().get(i3).f3093y - i8) < 128 && GameLevelData.z().get(i3).T() && GameLevelData.z().get(i3).s()) {
                            trap = GameLevelData.z().get(i3);
                            break;
                        }
                        i3++;
                    }
                }
                this.trap = trap;
                if (trap != null && trap.N(npc) >= 65) {
                    a(AI.NPCState.f2978e);
                    return;
                }
            }
            if (npc.N1()) {
                AISkillUsage.c(npc);
            }
            if (npc.wait || iS <= 64) {
                npc.C0();
                return;
            } else {
                npc.D1(1);
                return;
            }
        }
        AI.NPCState nPCState = AI.NPCState.f2974a;
        if (iOrdinal != 1) {
            if (iOrdinal != 4) {
                return;
            }
            npc.H1();
            int i9 = npc.detectedEnemyID;
            if (i9 > 0) {
                npc.U(i9);
                return;
            }
            Trap trap2 = this.trap;
            if (trap2 == null || !trap2.T()) {
                a(nPCState);
                return;
            }
            if (npc.g0()) {
                return;
            }
            if (!npc.A0(this.trap.q(), true)) {
                npc.D1(this.trap.q());
                return;
            }
            npc.C0();
            this.trap.Y(npc);
            a(nPCState);
            return;
        }
        int i10 = this.targetActorID;
        if (i10 == 0 || GameLevel.g(i10) == null) {
            a(nPCState);
            return;
        }
        if (GameLevel.g(this.targetActorID).d0() == MapActor.ActorState.f3074d) {
            a(nPCState);
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
            return;
        }
        npc.C0();
        if (npc.N1()) {
            AISkillUsage.b(npc);
        }
        if (npc.g0()) {
            return;
        }
        npc.E0(this.targetActorID);
    }
}

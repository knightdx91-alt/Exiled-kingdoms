package net.fdgames.GameEntities.AI;

import android.support.v4.media.session.bbR.CrhMs;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public abstract class AI {
    protected int npc;
    public NPCState state;
    protected int targetActorID;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class NPCState {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final NPCState f3189b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final NPCState f3190c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final NPCState f3191d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final NPCState f3192e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final NPCState f3193f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static final /* synthetic */ NPCState[] f3194g;

        static {
            NPCState nPCState = new NPCState("IDLE", 0);
            f3189b = nPCState;
            NPCState nPCState2 = new NPCState("AGRESSIVE", 1);
            f3190c = nPCState2;
            NPCState nPCState3 = new NPCState(CrhMs.LUMcWfwdxAmV, 2);
            f3191d = nPCState3;
            NPCState nPCState4 = new NPCState("DEAD", 3);
            f3192e = nPCState4;
            NPCState nPCState5 = new NPCState("ACTINGON", 4);
            f3193f = nPCState5;
            f3194g = new NPCState[]{nPCState, nPCState2, nPCState3, nPCState4, nPCState5};
        }

        private NPCState() {
            throw null;
        }

        public static NPCState valueOf(String str) {
            return (NPCState) Enum.valueOf(NPCState.class, str);
        }

        public static NPCState[] values() {
            return (NPCState[]) f3194g.clone();
        }
    }

    public AI() {
    }

    public AI(int i2) {
        this.npc = i2;
        this.state = NPCState.f3189b;
    }

    public abstract void a(NPCState nPCState);

    public final boolean b() {
        return this.state == NPCState.f3190c && this.targetActorID == 1;
    }

    public abstract void c(int i2);
}

package net.fdgames.GameEntities.AI;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class AI {
    protected int npc;
    public NPCState state;
    protected int targetActorID;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class NPCState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final NPCState f2974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final NPCState f2975b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final NPCState f2976c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final NPCState f2977d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final NPCState f2978e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ NPCState[] f2979f;

        static {
            NPCState nPCState = new NPCState("IDLE", 0);
            f2974a = nPCState;
            NPCState nPCState2 = new NPCState("AGRESSIVE", 1);
            f2975b = nPCState2;
            NPCState nPCState3 = new NPCState("FLEEING", 2);
            f2976c = nPCState3;
            NPCState nPCState4 = new NPCState("DEAD", 3);
            f2977d = nPCState4;
            NPCState nPCState5 = new NPCState("ACTINGON", 4);
            f2978e = nPCState5;
            f2979f = new NPCState[]{nPCState, nPCState2, nPCState3, nPCState4, nPCState5};
        }

        private NPCState() {
            throw null;
        }

        public static NPCState valueOf(String str) {
            return (NPCState) Enum.valueOf(NPCState.class, str);
        }

        public static NPCState[] values() {
            return (NPCState[]) f2979f.clone();
        }
    }

    public AI(int i2) {
        this.npc = i2;
        this.state = NPCState.f2974a;
    }

    public abstract void a(NPCState nPCState);

    public final boolean b() {
        return this.state == NPCState.f2975b && this.targetActorID == 1;
    }

    public abstract void c(int i2);

    public AI() {
    }
}

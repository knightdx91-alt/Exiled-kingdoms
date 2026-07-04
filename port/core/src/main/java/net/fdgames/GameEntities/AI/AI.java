package net.fdgames.GameEntities.AI;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class AI {
    protected int npc;
    public NPCState state;
    protected int targetActorID;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum NPCState {
        IDLE, AGRESSIVE, FLEEING, DEAD, ACTINGON;
    }

    public AI(int i2) {
        this.npc = i2;
        this.state = NPCState.IDLE;
    }

    public abstract void a(NPCState nPCState);

    public final boolean b() {
        return this.state == NPCState.AGRESSIVE && this.targetActorID == 1;
    }

    public abstract void c(int i2);

    public AI() {
    }
}

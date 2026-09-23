package net.fdgames.ek.android.lan;

import java.util.WeakHashMap;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.NPC;

/**
 * Joiner-side smoothing for host-driven world NPCs (deobf/MULTIPLAYER_PORT_SPEC.md, v59; owner report: "enemy
 * health isn't updated fast enough, the NPCs and enemies are twitching trying to move back and forth").
 * Hooked into LanGameBridge.applyReceivedWorldNpcStatesV2, which runs every frame with the host's latest snapshot.
 */
public final class EkSync {
    private EkSync() {}

    /** A host health rise (regen, heal) must hold this long before it replaces a lower local value. */
    static final long HP_RISE_HOLD_MS = 1200;
    private static final WeakHashMap<NPC, long[]> HP_RISE = new WeakHashMap<NPC, long[]>();

    /**
     * The NPC's AI is off on the joiner, but the velocity its local AI last chose stayed set, so MapActor kept sliding
     * it one way while the snapshot pulled it back the other. The host position is the only thing that moves it.
     */
    public static void remoteNpc(NPC npc) {
        if (npc != null) {
            npc.speedX = 0f;
            npc.speedY = 0f;
        }
    }

    /** Host health: more damage applies at once; less damage only once the host has said so for HP_RISE_HOLD_MS. */
    public static void npcHp(NPC npc, CharacterStats st, int missing) {
        if (st == null) {
            return;
        }
        synchronized (HP_RISE) {
            if (npc == null || missing >= st.missingHP) {
                st.missingHP = missing;
                if (npc != null) {
                    HP_RISE.remove(npc);
                }
                return;
            }
            long now = System.currentTimeMillis();
            long[] since = HP_RISE.get(npc);
            if (since == null) {
                HP_RISE.put(npc, new long[] {now});
            } else if (now - since[0] >= HP_RISE_HOLD_MS) {
                st.missingHP = missing;
                HP_RISE.remove(npc);
            }
        }
    }
}

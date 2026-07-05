package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Player;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ParticipantUtils {
    private ParticipantUtils() {
    }

    public static String getParticipantId(ArrayList<Participant> arrayList, String str) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Participant participant = arrayList.get(i2);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(str)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
}

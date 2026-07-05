package com.google.android.gms.games.multiplayer.turnbased;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public interface OnTurnBasedMatchUpdateReceivedListener {
    void onTurnBasedMatchReceived(TurnBasedMatch turnBasedMatch);

    void onTurnBasedMatchRemoved(String str);
}

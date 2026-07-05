package com.google.android.gms.games.multiplayer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class InvitationCallback implements OnInvitationReceivedListener {
    @Override // com.google.android.gms.games.multiplayer.OnInvitationReceivedListener
    public abstract void onInvitationReceived(Invitation invitation);

    @Override // com.google.android.gms.games.multiplayer.OnInvitationReceivedListener
    public abstract void onInvitationRemoved(String str);
}

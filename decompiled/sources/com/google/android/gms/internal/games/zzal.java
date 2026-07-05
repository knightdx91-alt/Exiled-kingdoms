package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzal implements Invitations.LoadInvitationsResult {
    private final /* synthetic */ Status zzbc;

    zzal(zzak zzakVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult
    public final InvitationBuffer getInvitations() {
        return new InvitationBuffer(DataHolder.empty(14));
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
    }
}

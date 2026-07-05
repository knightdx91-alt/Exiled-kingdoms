package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaa implements PendingResultUtil.ResultConverter<Invitations.LoadInvitationsResult, InvitationBuffer> {
    zzaa() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ InvitationBuffer convert(Result result) {
        Invitations.LoadInvitationsResult loadInvitationsResult = (Invitations.LoadInvitationsResult) result;
        if (loadInvitationsResult == null) {
            return null;
        }
        return loadInvitationsResult.getInvitations();
    }
}

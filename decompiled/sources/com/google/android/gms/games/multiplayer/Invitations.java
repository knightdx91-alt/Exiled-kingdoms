package com.google.android.gms.games.multiplayer;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public interface Invitations {

    @Deprecated
    public interface LoadInvitationsResult extends Releasable, Result {
        InvitationBuffer getInvitations();
    }

    Intent getInvitationInboxIntent(GoogleApiClient googleApiClient);

    PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient);

    PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient, int i2);

    void registerInvitationListener(GoogleApiClient googleApiClient, OnInvitationReceivedListener onInvitationReceivedListener);

    void unregisterInvitationListener(GoogleApiClient googleApiClient);
}

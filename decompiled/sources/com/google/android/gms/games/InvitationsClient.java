package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.InvitationCallback;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class InvitationsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Invitations.LoadInvitationsResult, InvitationBuffer> zzbh = new zzaa();

    InvitationsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Intent> getInvitationInboxIntent() {
        return doRead(new zzx(this));
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations() {
        return loadInvitations(0);
    }

    public Task<Void> registerInvitationCallback(InvitationCallback invitationCallback) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(invitationCallback, "InvitationCallback");
        return doRegisterEventListener(new zzy(this, listenerHolderRegisterListener, listenerHolderRegisterListener), new zzz(this, listenerHolderRegisterListener.getListenerKey()));
    }

    public Task<Boolean> unregisterInvitationCallback(InvitationCallback invitationCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(invitationCallback, "InvitationCallback"));
    }

    InvitationsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations(int i2) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Invitations.loadInvitations(asGoogleApiClient(), i2), zzbh);
    }
}

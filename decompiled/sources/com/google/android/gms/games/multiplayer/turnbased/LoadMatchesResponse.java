package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LoadMatchesResponse {
    private final InvitationBuffer zzpg;
    private final TurnBasedMatchBuffer zzph;
    private final TurnBasedMatchBuffer zzpi;
    private final TurnBasedMatchBuffer zzpj;

    public LoadMatchesResponse(Bundle bundle) {
        DataHolder dataHolderZza = zza(bundle, 0);
        if (dataHolderZza != null) {
            this.zzpg = new InvitationBuffer(dataHolderZza);
        } else {
            this.zzpg = null;
        }
        DataHolder dataHolderZza2 = zza(bundle, 1);
        if (dataHolderZza2 != null) {
            this.zzph = new TurnBasedMatchBuffer(dataHolderZza2);
        } else {
            this.zzph = null;
        }
        DataHolder dataHolderZza3 = zza(bundle, 2);
        if (dataHolderZza3 != null) {
            this.zzpi = new TurnBasedMatchBuffer(dataHolderZza3);
        } else {
            this.zzpi = null;
        }
        DataHolder dataHolderZza4 = zza(bundle, 3);
        if (dataHolderZza4 != null) {
            this.zzpj = new TurnBasedMatchBuffer(dataHolderZza4);
        } else {
            this.zzpj = null;
        }
    }

    private static DataHolder zza(Bundle bundle, int i2) {
        String str;
        if (i2 == 0) {
            str = "TURN_STATUS_INVITED";
        } else if (i2 == 1) {
            str = "TURN_STATUS_MY_TURN";
        } else if (i2 == 2) {
            str = "TURN_STATUS_THEIR_TURN";
        } else if (i2 != 3) {
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown match turn status: ");
            sb.append(i2);
            zzh.e("MatchTurnStatus", sb.toString());
            str = "TURN_STATUS_UNKNOWN";
        } else {
            str = "TURN_STATUS_COMPLETE";
        }
        if (bundle.containsKey(str)) {
            return (DataHolder) bundle.getParcelable(str);
        }
        return null;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public final TurnBasedMatchBuffer getCompletedMatches() {
        return this.zzpj;
    }

    public final InvitationBuffer getInvitations() {
        return this.zzpg;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches() {
        return this.zzph;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.zzpi;
    }

    public final boolean hasData() {
        InvitationBuffer invitationBuffer = this.zzpg;
        if (invitationBuffer != null && invitationBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzph;
        if (turnBasedMatchBuffer != null && turnBasedMatchBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzpi;
        if (turnBasedMatchBuffer2 != null && turnBasedMatchBuffer2.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzpj;
        return turnBasedMatchBuffer3 != null && turnBasedMatchBuffer3.getCount() > 0;
    }

    public final void release() {
        InvitationBuffer invitationBuffer = this.zzpg;
        if (invitationBuffer != null) {
            invitationBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzph;
        if (turnBasedMatchBuffer != null) {
            turnBasedMatchBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzpi;
        if (turnBasedMatchBuffer2 != null) {
            turnBasedMatchBuffer2.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzpj;
        if (turnBasedMatchBuffer3 != null) {
            turnBasedMatchBuffer3.release();
        }
    }
}

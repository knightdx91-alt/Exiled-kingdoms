package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb extends TurnBasedMatchConfig {
    private final int zzoe;
    private final Bundle zzoz;
    private final String[] zzpb;
    private final int zzpk;

    zzb(TurnBasedMatchConfig.Builder builder) {
        this.zzoe = builder.zzoe;
        this.zzpk = builder.zzpk;
        this.zzoz = builder.zzoz;
        this.zzpb = (String[]) builder.zzoy.toArray(new String[builder.zzoy.size()]);
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public final String[] getInvitedPlayerIds() {
        return this.zzpb;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public final int getVariant() {
        return this.zzoe;
    }

    @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig
    public final int zzci() {
        return this.zzpk;
    }
}

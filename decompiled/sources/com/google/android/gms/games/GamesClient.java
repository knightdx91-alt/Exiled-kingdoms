package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GamesClient extends com.google.android.gms.internal.games.zzu {
    GamesClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<Bundle> getActivationHint() {
        return doRead(new zzs(this));
    }

    public Task<String> getAppId() {
        return doRead(new zzq(this));
    }

    public Task<String> getCurrentAccountName() {
        return doRead(new zzp(this));
    }

    @KeepForSdk
    public Task<Integer> getSdkVariant() {
        return doRead(new zzt(this));
    }

    public Task<Intent> getSettingsIntent() {
        return doRead(new zzr(this));
    }

    public Task<Void> setGravityForPopups(int i2) {
        return doWrite(new zzn(this, i2));
    }

    public Task<Void> setViewForPopups(View view) {
        return doWrite(new zzo(this, view));
    }

    GamesClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}

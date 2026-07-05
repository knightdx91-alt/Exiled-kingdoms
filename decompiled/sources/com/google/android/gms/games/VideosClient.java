package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class VideosClient extends com.google.android.gms.internal.games.zzu {
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
    public static final int CAPTURE_OVERLAY_STATE_DISMISSED = 4;
    public static final int CAPTURE_OVERLAY_STATE_SHOWN = 1;
    private static final PendingResultUtil.ResultConverter<Videos.CaptureAvailableResult, Boolean> zzex = new zzda();
    private static final PendingResultUtil.ResultConverter<Videos.CaptureStateResult, CaptureState> zzey = new zzdb();
    private static final PendingResultUtil.ResultConverter<Videos.CaptureCapabilitiesResult, VideoCapabilities> zzez = new zzdc();

    public interface OnCaptureOverlayStateListener extends Videos.CaptureOverlayStateListener {
        @Override // com.google.android.gms.games.video.Videos.CaptureOverlayStateListener
        void onCaptureOverlayStateChanged(int i2);
    }

    VideosClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    public Task<VideoCapabilities> getCaptureCapabilities() {
        return com.google.android.gms.games.internal.zzi.toTask(Games.Videos.getCaptureCapabilities(asGoogleApiClient()), zzez);
    }

    public Task<Intent> getCaptureOverlayIntent() {
        return doRead(new zzcw(this));
    }

    public Task<CaptureState> getCaptureState() {
        return com.google.android.gms.games.internal.zzi.toTask(Games.Videos.getCaptureState(asGoogleApiClient()), zzey);
    }

    public Task<Boolean> isCaptureAvailable(int i2) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.Videos.isCaptureAvailable(asGoogleApiClient(), i2), zzex);
    }

    public Task<Boolean> isCaptureSupported() {
        return doRead(new zzcx(this));
    }

    public Task<Void> registerOnCaptureOverlayStateChangedListener(OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName());
        return doRegisterEventListener(new zzcy(this, listenerHolderRegisterListener, listenerHolderRegisterListener), new zzcz(this, listenerHolderRegisterListener.getListenerKey()));
    }

    public Task<Boolean> unregisterOnCaptureOverlayStateChangedListener(OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName()));
    }

    VideosClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
}

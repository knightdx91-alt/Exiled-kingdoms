package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchUpdateCallback;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TurnBasedMultiplayerClient extends com.google.android.gms.internal.games.zzu {
    private static final com.google.android.gms.games.internal.zzp<TurnBasedMatch> zzel = new zzcv();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> zzem = new zzce();
    private static final com.google.android.gms.games.internal.zzq<TurnBasedMultiplayer.LoadMatchesResult> zzen = new zzcf();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LoadMatchResult, TurnBasedMatch> zzeo = new zzcg();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.CancelMatchResult, String> zzep = new zzch();
    private static final com.google.android.gms.games.internal.zzr zzeq = new zzci();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LeaveMatchResult, Void> zzer = new zzcj();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.LeaveMatchResult, TurnBasedMatch> zzes = new zzck();
    private static final com.google.android.gms.games.internal.zzr zzet = new zzcl();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> zzeu = new zzcm();
    private static final PendingResultUtil.ResultConverter<TurnBasedMultiplayer.InitiateMatchResult, TurnBasedMatch> zzev = new zzcn();

    public static class MatchOutOfDateApiException extends ApiException {
        protected final TurnBasedMatch match;

        MatchOutOfDateApiException(Status status, TurnBasedMatch turnBasedMatch) {
            super(status);
            this.match = turnBasedMatch;
        }

        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }

    TurnBasedMultiplayerClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    private static Task<Void> zzd(PendingResult<TurnBasedMultiplayer.LeaveMatchResult> pendingResult) {
        return com.google.android.gms.games.internal.zzi.zza(pendingResult, zzeq, zzer, zzes, zzel);
    }

    private static Task<TurnBasedMatch> zze(PendingResult<TurnBasedMultiplayer.UpdateMatchResult> pendingResult) {
        com.google.android.gms.games.internal.zzr zzrVar = zzet;
        PendingResultUtil.ResultConverter<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> resultConverter = zzeu;
        return com.google.android.gms.games.internal.zzi.zza(pendingResult, zzrVar, resultConverter, resultConverter, zzel);
    }

    public Task<TurnBasedMatch> acceptInvitation(String str) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.TurnBasedMultiplayer.acceptInvitation(asGoogleApiClient(), str), zzev);
    }

    public Task<String> cancelMatch(String str) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.TurnBasedMultiplayer.cancelMatch(asGoogleApiClient(), str), zzep);
    }

    public Task<TurnBasedMatch> createMatch(TurnBasedMatchConfig turnBasedMatchConfig) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.TurnBasedMultiplayer.createMatch(asGoogleApiClient(), turnBasedMatchConfig), zzev);
    }

    public Task<Void> declineInvitation(String str) {
        return doWrite(new zzcr(this, str));
    }

    public Task<Void> dismissInvitation(String str) {
        return doWrite(new zzcs(this, str));
    }

    public Task<Void> dismissMatch(String str) {
        return doWrite(new zzcu(this, str));
    }

    public Task<TurnBasedMatch> finishMatch(String str) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str));
    }

    public Task<Intent> getInboxIntent() {
        return doRead(new zzcd(this));
    }

    public Task<Integer> getMaxMatchDataSize() {
        return doRead(new zzct(this));
    }

    public Task<Intent> getSelectOpponentsIntent(int i2, int i3) {
        return getSelectOpponentsIntent(i2, i3, true);
    }

    public Task<Void> leaveMatch(String str) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatch(asGoogleApiClient(), str));
    }

    public Task<Void> leaveMatchDuringTurn(String str, String str2) {
        return zzd(Games.TurnBasedMultiplayer.leaveMatchDuringTurn(asGoogleApiClient(), str, str2));
    }

    public Task<AnnotatedData<TurnBasedMatch>> loadMatch(String str) {
        return com.google.android.gms.games.internal.zzi.zza(Games.TurnBasedMultiplayer.loadMatch(asGoogleApiClient(), str), zzeo);
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(int i2, int[] iArr) {
        return com.google.android.gms.games.internal.zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(asGoogleApiClient(), i2, iArr), zzem, zzen);
    }

    public Task<Void> registerTurnBasedMatchUpdateCallback(TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(turnBasedMatchUpdateCallback, "TurnBasedMatchUpdateCallback");
        return doRegisterEventListener(new zzco(this, listenerHolderRegisterListener, listenerHolderRegisterListener), new zzcp(this, listenerHolderRegisterListener.getListenerKey()));
    }

    public Task<TurnBasedMatch> rematch(String str) {
        return com.google.android.gms.games.internal.zzi.toTask(Games.TurnBasedMultiplayer.rematch(asGoogleApiClient(), str), zzev);
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2));
    }

    public Task<Boolean> unregisterTurnBasedMatchUpdateCallback(TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(turnBasedMatchUpdateCallback, "TurnBasedMatchUpdateCallback"));
    }

    TurnBasedMultiplayerClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<TurnBasedMatch> finishMatch(String str, byte[] bArr, List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str, bArr, list));
    }

    public Task<Intent> getSelectOpponentsIntent(int i2, int i3, boolean z2) {
        return doRead(new zzcq(this, i2, i3, z2));
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(int[] iArr) {
        return com.google.android.gms.games.internal.zzi.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(asGoogleApiClient(), iArr), zzem, zzen);
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2, List<ParticipantResult> list) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2, list));
    }

    public Task<TurnBasedMatch> finishMatch(String str, byte[] bArr, ParticipantResult... participantResultArr) {
        return zze(Games.TurnBasedMultiplayer.finishMatch(asGoogleApiClient(), str, bArr, participantResultArr));
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2, ParticipantResult... participantResultArr) {
        return zze(Games.TurnBasedMultiplayer.takeTurn(asGoogleApiClient(), str, bArr, str2, participantResultArr));
    }
}

package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.DataHolderNotifier;
import com.google.android.gms.common.api.internal.DataHolderResult;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.PlayerStatsBuffer;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.games.zzej;
import com.google.android.gms.internal.games.zzel;
import com.google.android.gms.internal.games.zzem;
import com.google.android.gms.signin.internal.SignInClientImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zze extends GmsClient<com.google.android.gms.games.internal.zzy> {
    private zzel zzfp;
    private final String zzfq;
    private PlayerEntity zzfr;
    private GameEntity zzfs;
    private final com.google.android.gms.games.internal.zzac zzft;
    private boolean zzfu;
    private final Binder zzfv;
    private final long zzfw;
    private final Games.GamesOptions zzfx;
    private boolean zzfy;
    private Bundle zzfz;

    private static abstract class zza extends zzc {
        private final ArrayList<String> zzgc;

        zza(DataHolder dataHolder, String[] strArr) {
            super(dataHolder);
            this.zzgc = new ArrayList<>();
            for (String str : strArr) {
                this.zzgc.add(str);
            }
        }

        @Override // com.google.android.gms.games.internal.zze.zzc
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            zza(roomStatusUpdateListener, room, this.zzgc);
        }

        protected abstract void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList);
    }

    private static final class zzaa extends zzcr implements TurnBasedMultiplayer.InitiateMatchResult {
        zzaa(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzab extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<OnInvitationReceivedListener> zzgj;

        zzab(ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onInvitationRemoved(String str) {
            this.zzgj.notifyListener(new zzad(str));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzl(DataHolder dataHolder) {
            InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            try {
                Invitation invitationFreeze = invitationBuffer.getCount() > 0 ? invitationBuffer.get(0).freeze() : null;
                if (invitationFreeze != null) {
                    this.zzgj.notifyListener(new zzac(invitationFreeze));
                }
            } finally {
                invitationBuffer.release();
            }
        }
    }

    private static final class zzac implements ListenerHolder.Notifier<OnInvitationReceivedListener> {
        private final Invitation zzgq;

        zzac(Invitation invitation) {
            this.zzgq = invitation;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.zzgq);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzad implements ListenerHolder.Notifier<OnInvitationReceivedListener> {
        private final String zzgr;

        zzad(String str) {
            this.zzgr = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.zzgr);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzae extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Invitations.LoadInvitationsResult> zzge;

        zzae(BaseImplementation.ResultHolder<Invitations.LoadInvitationsResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzk(DataHolder dataHolder) {
            this.zzge.setResult(new zzao(dataHolder));
        }
    }

    private static final class zzaf extends zzb {
        public zzaf(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzb
        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i2) {
            roomUpdateListener.onJoinedRoom(i2, room);
        }
    }

    private static final class zzag extends zzw implements Leaderboards.LeaderboardMetadataResult {
        private final LeaderboardBuffer zzgs;

        zzag(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgs = new LeaderboardBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
        public final LeaderboardBuffer getLeaderboards() {
            return this.zzgs;
        }
    }

    private static final class zzah extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Leaderboards.LoadScoresResult> zzge;

        zzah(BaseImplementation.ResultHolder<Leaderboards.LoadScoresResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(DataHolder dataHolder, DataHolder dataHolder2) {
            this.zzge.setResult(new zzaw(dataHolder, dataHolder2));
        }
    }

    private static final class zzai extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Leaderboards.LeaderboardMetadataResult> zzge;

        zzai(BaseImplementation.ResultHolder<Leaderboards.LeaderboardMetadataResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzc(DataHolder dataHolder) {
            this.zzge.setResult(new zzag(dataHolder));
        }
    }

    private static final class zzaj extends zzcr implements TurnBasedMultiplayer.LeaveMatchResult {
        zzaj(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzak implements ListenerHolder.Notifier<RoomUpdateListener> {
        private final int statusCode;
        private final String zzgt;

        zzak(int i2, String str) {
            this.statusCode = i2;
            this.zzgt = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.statusCode, this.zzgt);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzal extends zzw implements Achievements.LoadAchievementsResult {
        private final AchievementBuffer zzgu;

        zzal(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgu = new AchievementBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
        public final AchievementBuffer getAchievements() {
            return this.zzgu;
        }
    }

    private static final class zzam extends zzw implements Events.LoadEventsResult {
        private final EventBuffer zzgv;

        zzam(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgv = new EventBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.event.Events.LoadEventsResult
        public final EventBuffer getEvents() {
            return this.zzgv;
        }
    }

    private static final class zzan extends zzw implements GamesMetadata.LoadGamesResult {
        private final GameBuffer zzgw;

        zzan(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgw = new GameBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.GamesMetadata.LoadGamesResult
        public final GameBuffer getGames() {
            return this.zzgw;
        }
    }

    private static final class zzao extends zzw implements Invitations.LoadInvitationsResult {
        private final InvitationBuffer zzgx;

        zzao(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgx = new InvitationBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult
        public final InvitationBuffer getInvitations() {
            return this.zzgx;
        }
    }

    private static final class zzap extends zzcr implements TurnBasedMultiplayer.LoadMatchResult {
        zzap(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzaq implements TurnBasedMultiplayer.LoadMatchesResult {
        private final Status zzgf;
        private final LoadMatchesResponse zzgy;

        zzaq(Status status, Bundle bundle) {
            this.zzgf = status;
            this.zzgy = new LoadMatchesResponse(bundle);
        }

        @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
        public final LoadMatchesResponse getMatches() {
            return this.zzgy;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public final void release() {
            this.zzgy.release();
        }
    }

    private static final class zzar extends zzw implements Leaderboards.LoadPlayerScoreResult {
        private final LeaderboardScoreEntity zzgz;

        zzar(DataHolder dataHolder) {
            super(dataHolder);
            LeaderboardScoreBuffer leaderboardScoreBuffer = new LeaderboardScoreBuffer(dataHolder);
            try {
                if (leaderboardScoreBuffer.getCount() > 0) {
                    this.zzgz = (LeaderboardScoreEntity) ((LeaderboardScore) leaderboardScoreBuffer.get(0)).freeze();
                } else {
                    this.zzgz = null;
                }
                leaderboardScoreBuffer.release();
            } catch (Throwable th) {
                leaderboardScoreBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult
        public final LeaderboardScore getScore() {
            return this.zzgz;
        }
    }

    private static final class zzas extends zzw implements Stats.LoadPlayerStatsResult {
        private final PlayerStats zzha;

        zzas(DataHolder dataHolder) {
            super(dataHolder);
            PlayerStatsBuffer playerStatsBuffer = new PlayerStatsBuffer(dataHolder);
            try {
                if (playerStatsBuffer.getCount() > 0) {
                    this.zzha = new com.google.android.gms.games.stats.zza((PlayerStats) playerStatsBuffer.get(0));
                } else {
                    this.zzha = null;
                }
                playerStatsBuffer.release();
            } catch (Throwable th) {
                playerStatsBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult
        public final PlayerStats getPlayerStats() {
            return this.zzha;
        }
    }

    private static final class zzat extends zzw implements Players.LoadPlayersResult {
        private final PlayerBuffer zzhb;

        zzat(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhb = new PlayerBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.Players.LoadPlayersResult
        public final PlayerBuffer getPlayers() {
            return this.zzhb;
        }
    }

    private static final class zzau extends zzw implements Quests.LoadQuestsResult {
        private final DataHolder zzhc;

        zzau(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhc = dataHolder;
        }

        @Override // com.google.android.gms.games.quest.Quests.LoadQuestsResult
        public final QuestBuffer getQuests() {
            return new QuestBuffer(this.zzhc);
        }
    }

    private static final class zzav implements Requests.LoadRequestsResult {
        private final Status zzgf;
        private final Bundle zzhd;

        zzav(Status status, Bundle bundle) {
            this.zzgf = status;
            this.zzhd = bundle;
        }

        @Override // com.google.android.gms.games.request.Requests.LoadRequestsResult
        public final GameRequestBuffer getRequests(int i2) {
            String str;
            if (i2 == 1) {
                str = "GIFT";
            } else if (i2 != 2) {
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unknown request type: ");
                sb.append(i2);
                com.google.android.gms.games.internal.zzh.e("RequestType", sb.toString());
                str = "UNKNOWN_TYPE";
            } else {
                str = "WISH";
            }
            if (this.zzhd.containsKey(str)) {
                return new GameRequestBuffer((DataHolder) this.zzhd.get(str));
            }
            return null;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public final void release() {
            Iterator<String> it = this.zzhd.keySet().iterator();
            while (it.hasNext()) {
                DataHolder dataHolder = (DataHolder) this.zzhd.getParcelable(it.next());
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }

    private static final class zzaw extends zzw implements Leaderboards.LoadScoresResult {
        private final LeaderboardEntity zzhe;
        private final LeaderboardScoreBuffer zzhf;

        zzaw(DataHolder dataHolder, DataHolder dataHolder2) {
            super(dataHolder2);
            LeaderboardBuffer leaderboardBuffer = new LeaderboardBuffer(dataHolder);
            try {
                if (leaderboardBuffer.getCount() > 0) {
                    this.zzhe = (LeaderboardEntity) leaderboardBuffer.get(0).freeze();
                } else {
                    this.zzhe = null;
                }
                leaderboardBuffer.release();
                this.zzhf = new LeaderboardScoreBuffer(dataHolder2);
            } catch (Throwable th) {
                leaderboardBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
        public final Leaderboard getLeaderboard() {
            return this.zzhe;
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
        public final LeaderboardScoreBuffer getScores() {
            return this.zzhf;
        }
    }

    private static final class zzax extends zzw implements Snapshots.LoadSnapshotsResult {
        zzax(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult
        public final SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.mDataHolder);
        }
    }

    private static final class zzay implements ListenerHolder.Notifier<OnTurnBasedMatchUpdateReceivedListener> {
        private final String zzhg;

        zzay(String str) {
            this.zzhg = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.zzhg);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzaz extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> zzgj;

        zzaz(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onTurnBasedMatchRemoved(String str) {
            this.zzgj.notifyListener(new zzay(str));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzr(DataHolder dataHolder) {
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                TurnBasedMatch turnBasedMatchFreeze = turnBasedMatchBuffer.getCount() > 0 ? turnBasedMatchBuffer.get(0).freeze() : null;
                if (turnBasedMatchFreeze != null) {
                    this.zzgj.notifyListener(new zzba(turnBasedMatchFreeze));
                }
            } finally {
                turnBasedMatchBuffer.release();
            }
        }
    }

    private static abstract class zzb extends DataHolderNotifier<RoomUpdateListener> {
        zzb(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.common.api.internal.DataHolderNotifier
        protected /* synthetic */ void notifyListener(RoomUpdateListener roomUpdateListener, DataHolder dataHolder) {
            zza(roomUpdateListener, zze.zzba(dataHolder), dataHolder.getStatusCode());
        }

        protected abstract void zza(RoomUpdateListener roomUpdateListener, Room room, int i2);
    }

    private static final class zzba implements ListenerHolder.Notifier<OnTurnBasedMatchUpdateReceivedListener> {
        private final TurnBasedMatch match;

        zzba(TurnBasedMatch turnBasedMatch) {
            this.match = turnBasedMatch;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.match);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbb implements ListenerHolder.Notifier<RealTimeMessageReceivedListener> {
        private final RealTimeMessage zzhh;

        zzbb(RealTimeMessage realTimeMessage) {
            this.zzhh = realTimeMessage;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            realTimeMessageReceivedListener.onRealTimeMessageReceived(this.zzhh);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbc extends zzw implements Snapshots.OpenSnapshotResult {
        private final String zzei;
        private final Snapshot zzhi;
        private final Snapshot zzhj;
        private final SnapshotContents zzhk;

        zzbc(DataHolder dataHolder, Contents contents) {
            this(dataHolder, null, contents, null, null);
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public final String getConflictId() {
            return this.zzei;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public final Snapshot getConflictingSnapshot() {
            return this.zzhj;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public final SnapshotContents getResolutionSnapshotContents() {
            return this.zzhk;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public final Snapshot getSnapshot() {
            return this.zzhi;
        }

        zzbc(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() == 0) {
                    this.zzhi = null;
                } else {
                    boolean z2 = true;
                    if (snapshotMetadataBuffer.getCount() != 1) {
                        this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                        this.zzhj = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(1)), new com.google.android.gms.games.snapshot.zza(contents2));
                        snapshotMetadataBuffer.release();
                        this.zzei = str;
                        this.zzhk = new com.google.android.gms.games.snapshot.zza(contents3);
                    }
                    if (dataHolder.getStatusCode() == 4004) {
                        z2 = false;
                    }
                    Asserts.checkState(z2);
                    this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                }
                this.zzhj = null;
                snapshotMetadataBuffer.release();
                this.zzei = str;
                this.zzhk = new com.google.android.gms.games.snapshot.zza(contents3);
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
                throw th;
            }
        }
    }

    private static final class zzbd implements ListenerHolder.Notifier<RoomStatusUpdateListener> {
        private final String zzhl;

        zzbd(String str) {
            this.zzhl = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PConnected(this.zzhl);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbe implements ListenerHolder.Notifier<RoomStatusUpdateListener> {
        private final String zzhl;

        zzbe(String str) {
            this.zzhl = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PDisconnected(this.zzhl);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbf extends zza {
        zzbf(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersConnected(room, arrayList);
        }
    }

    private static final class zzbg extends zza {
        zzbg(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerDeclined(room, arrayList);
        }
    }

    private static final class zzbh extends zza {
        zzbh(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersDisconnected(room, arrayList);
        }
    }

    private static final class zzbi extends zza {
        zzbi(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, arrayList);
        }
    }

    private static final class zzbj extends zza {
        zzbj(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerJoined(room, arrayList);
        }
    }

    private static final class zzbk extends zza {
        zzbk(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        @Override // com.google.android.gms.games.internal.zze.zza
        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerLeft(room, arrayList);
        }
    }

    private static final class zzbl extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Leaderboards.LoadPlayerScoreResult> zzge;

        zzbl(BaseImplementation.ResultHolder<Leaderboards.LoadPlayerScoreResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzac(DataHolder dataHolder) {
            this.zzge.setResult(new zzar(dataHolder));
        }
    }

    private static final class zzbm extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Stats.LoadPlayerStatsResult> zzge;

        public zzbm(BaseImplementation.ResultHolder<Stats.LoadPlayerStatsResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzap(DataHolder dataHolder) {
            this.zzge.setResult(new zzas(dataHolder));
        }
    }

    private static final class zzbn extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Players.LoadPlayersResult> zzge;

        zzbn(BaseImplementation.ResultHolder<Players.LoadPlayersResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zze(DataHolder dataHolder) {
            this.zzge.setResult(new zzat(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzf(DataHolder dataHolder) {
            this.zzge.setResult(new zzat(dataHolder));
        }
    }

    private static final class zzbo extends com.google.android.gms.games.internal.zzb {
        private final com.google.android.gms.games.internal.zzac zzft;

        public zzbo(com.google.android.gms.games.internal.zzac zzacVar) {
            this.zzft = zzacVar;
        }

        @Override // com.google.android.gms.games.internal.zzb, com.google.android.gms.games.internal.zzw
        public final com.google.android.gms.games.internal.zzaa zzn() {
            return new com.google.android.gms.games.internal.zzaa(this.zzft.zzjd);
        }
    }

    private static final class zzbp extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Quests.AcceptQuestResult> zzhm;

        public zzbp(BaseImplementation.ResultHolder<Quests.AcceptQuestResult> resultHolder) {
            this.zzhm = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzaj(DataHolder dataHolder) {
            this.zzhm.setResult(new zzd(dataHolder));
        }
    }

    private static final class zzbq implements ListenerHolder.Notifier<QuestUpdateListener> {
        private final Quest zzgd;

        zzbq(Quest quest) {
            this.zzgd = quest;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(QuestUpdateListener questUpdateListener) {
            questUpdateListener.onQuestCompleted(this.zzgd);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbr extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Quests.ClaimMilestoneResult> zzhn;
        private final String zzho;

        public zzbr(BaseImplementation.ResultHolder<Quests.ClaimMilestoneResult> resultHolder, String str) {
            this.zzhn = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
            this.zzho = (String) Preconditions.checkNotNull(str, "MilestoneId must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzai(DataHolder dataHolder) {
            this.zzhn.setResult(new zzp(dataHolder, this.zzho));
        }
    }

    private static final class zzbs extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<QuestUpdateListener> zzgj;

        zzbs(ListenerHolder<QuestUpdateListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        private static Quest zzbc(DataHolder dataHolder) {
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                return questBuffer.getCount() > 0 ? questBuffer.get(0).freeze() : null;
            } finally {
                questBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzak(DataHolder dataHolder) {
            Quest questZzbc = zzbc(dataHolder);
            if (questZzbc != null) {
                this.zzgj.notifyListener(new zzbq(questZzbc));
            }
        }
    }

    private static final class zzbt extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Quests.LoadQuestsResult> zzhp;

        public zzbt(BaseImplementation.ResultHolder<Quests.LoadQuestsResult> resultHolder) {
            this.zzhp = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzam(DataHolder dataHolder) {
            this.zzhp.setResult(new zzau(dataHolder));
        }
    }

    private static final class zzbu implements ListenerHolder.Notifier<RealTimeMultiplayer.ReliableMessageSentCallback> {
        private final int statusCode;
        private final int token;
        private final String zzhq;

        zzbu(int i2, int i3, String str) {
            this.statusCode = i2;
            this.token = i3;
            this.zzhq = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback2 = reliableMessageSentCallback;
            if (reliableMessageSentCallback2 != null) {
                reliableMessageSentCallback2.onRealTimeMessageSent(this.statusCode, this.token, this.zzhq);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbv extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> zzhr;

        public zzbv(ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder) {
            this.zzhr = listenerHolder;
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(int i2, int i3, String str) {
            ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder = this.zzhr;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbu(i2, i3, str));
            }
        }
    }

    private static final class zzbw extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<OnRequestReceivedListener> zzgj;

        zzbw(ListenerHolder<OnRequestReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onRequestRemoved(String str) {
            this.zzgj.notifyListener(new zzby(str));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzm(DataHolder dataHolder) {
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            try {
                GameRequest gameRequestFreeze = gameRequestBuffer.getCount() > 0 ? gameRequestBuffer.get(0).freeze() : null;
                if (gameRequestFreeze != null) {
                    this.zzgj.notifyListener(new zzbx(gameRequestFreeze));
                }
            } finally {
                gameRequestBuffer.release();
            }
        }
    }

    private static final class zzbx implements ListenerHolder.Notifier<OnRequestReceivedListener> {
        private final GameRequest zzhs;

        zzbx(GameRequest gameRequest) {
            this.zzhs = gameRequest;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.zzhs);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzby implements ListenerHolder.Notifier<OnRequestReceivedListener> {
        private final String zzht;

        zzby(String str) {
            this.zzht = str;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.zzht);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbz extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Requests.LoadRequestsResult> zzhu;

        public zzbz(BaseImplementation.ResultHolder<Requests.LoadRequestsResult> resultHolder) {
            this.zzhu = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzb(int i2, Bundle bundle) {
            bundle.setClassLoader(zzbz.class.getClassLoader());
            this.zzhu.setResult(new zzav(GamesStatusCodes.zza(i2), bundle));
        }
    }

    private static abstract class zzc extends DataHolderNotifier<RoomStatusUpdateListener> {
        zzc(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.common.api.internal.DataHolderNotifier
        protected /* synthetic */ void notifyListener(RoomStatusUpdateListener roomStatusUpdateListener, DataHolder dataHolder) {
            zza(roomStatusUpdateListener, zze.zzba(dataHolder));
        }

        protected abstract void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room);
    }

    private static final class zzca extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Requests.UpdateRequestsResult> zzhv;

        public zzca(BaseImplementation.ResultHolder<Requests.UpdateRequestsResult> resultHolder) {
            this.zzhv = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzad(DataHolder dataHolder) {
            this.zzhv.setResult(new zzcw(dataHolder));
        }
    }

    private static final class zzcb extends zzc {
        zzcb(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzc
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }

    private static final class zzcc extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<? extends RoomUpdateListener> zzhw;
        private final ListenerHolder<? extends RoomStatusUpdateListener> zzhx;
        private final ListenerHolder<? extends RealTimeMessageReceivedListener> zzhy;

        public zzcc(ListenerHolder<? extends RoomUpdateListener> listenerHolder) {
            this.zzhw = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callbacks must not be null");
            this.zzhx = null;
            this.zzhy = null;
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onLeftRoom(int i2, String str) {
            this.zzhw.notifyListener(new zzak(i2, str));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onP2PConnected(String str) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbd(str));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onP2PDisconnected(String str) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbe(str));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
            ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder = this.zzhy;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbb(realTimeMessage));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbi(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzb(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbj(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzc(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbk(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzd(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbg(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zze(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbf(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzf(DataHolder dataHolder, String[] strArr) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzbh(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzs(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzcf(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzt(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzaf(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzu(DataHolder dataHolder) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzce(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzv(DataHolder dataHolder) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzcb(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzw(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzcd(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzx(DataHolder dataHolder) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzr(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzy(DataHolder dataHolder) {
            ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder = this.zzhx;
            if (listenerHolder != null) {
                listenerHolder.notifyListener(new zzt(dataHolder));
            }
        }

        public zzcc(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3) {
            this.zzhw = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callbacks must not be null");
            this.zzhx = listenerHolder2;
            this.zzhy = listenerHolder3;
        }
    }

    private static final class zzcd extends zzb {
        zzcd(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzb
        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i2) {
            roomUpdateListener.onRoomConnected(i2, room);
        }
    }

    private static final class zzce extends zzc {
        zzce(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzc
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }

    private static final class zzcf extends zzb {
        public zzcf(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzb
        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i2) {
            roomUpdateListener.onRoomCreated(i2, room);
        }
    }

    private static final class zzcg extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Status> zzge;

        public zzcg(BaseImplementation.ResultHolder<Status> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onSignOutComplete() {
            this.zzge.setResult(GamesStatusCodes.zza(0));
        }
    }

    private static final class zzch extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Snapshots.CommitSnapshotResult> zzhz;

        public zzch(BaseImplementation.ResultHolder<Snapshots.CommitSnapshotResult> resultHolder) {
            this.zzhz = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzah(DataHolder dataHolder) {
            this.zzhz.setResult(new zzq(dataHolder));
        }
    }

    static final class zzci extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Snapshots.DeleteSnapshotResult> zzge;

        public zzci(BaseImplementation.ResultHolder<Snapshots.DeleteSnapshotResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzd(int i2, String str) {
            this.zzge.setResult(new zzs(i2, str));
        }
    }

    private static final class zzcj extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Snapshots.OpenSnapshotResult> zzia;

        public zzcj(BaseImplementation.ResultHolder<Snapshots.OpenSnapshotResult> resultHolder) {
            this.zzia = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(DataHolder dataHolder, Contents contents) {
            this.zzia.setResult(new zzbc(dataHolder, contents));
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            this.zzia.setResult(new zzbc(dataHolder, str, contents, contents2, contents3));
        }
    }

    private static final class zzck extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Snapshots.LoadSnapshotsResult> zzib;

        public zzck(BaseImplementation.ResultHolder<Snapshots.LoadSnapshotsResult> resultHolder) {
            this.zzib = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzag(DataHolder dataHolder) {
            this.zzib.setResult(new zzax(dataHolder));
        }
    }

    private static final class zzcl extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Leaderboards.SubmitScoreResult> zzge;

        public zzcl(BaseImplementation.ResultHolder<Leaderboards.SubmitScoreResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzd(DataHolder dataHolder) {
            this.zzge.setResult(new zzcm(dataHolder));
        }
    }

    private static final class zzcm extends zzw implements Leaderboards.SubmitScoreResult {
        private final ScoreSubmissionData zzic;

        public zzcm(DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.zzic = new ScoreSubmissionData(dataHolder);
            } finally {
                dataHolder.close();
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
        public final ScoreSubmissionData getScoreData() {
            return this.zzic;
        }
    }

    private static final class zzcn extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.CancelMatchResult> zzid;

        public zzcn(BaseImplementation.ResultHolder<TurnBasedMultiplayer.CancelMatchResult> resultHolder) {
            this.zzid = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzc(int i2, String str) {
            this.zzid.setResult(new zzg(GamesStatusCodes.zza(i2), str));
        }
    }

    private static final class zzco extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> zzie;

        public zzco(BaseImplementation.ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> resultHolder) {
            this.zzie = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzo(DataHolder dataHolder) {
            this.zzie.setResult(new zzaa(dataHolder));
        }
    }

    private static final class zzcp extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> zzif;

        public zzcp(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> resultHolder) {
            this.zzif = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzq(DataHolder dataHolder) {
            this.zzif.setResult(new zzaj(dataHolder));
        }
    }

    private static final class zzcq extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchResult> zzig;

        public zzcq(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchResult> resultHolder) {
            this.zzig = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzn(DataHolder dataHolder) {
            this.zzig.setResult(new zzap(dataHolder));
        }
    }

    private static abstract class zzcr extends zzw {
        private final TurnBasedMatch match;

        zzcr(DataHolder dataHolder) {
            super(dataHolder);
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    this.match = turnBasedMatchBuffer.get(0).freeze();
                } else {
                    this.match = null;
                }
                turnBasedMatchBuffer.release();
            } catch (Throwable th) {
                turnBasedMatchBuffer.release();
                throw th;
            }
        }

        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }

    private static final class zzcs extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> zzih;

        public zzcs(BaseImplementation.ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> resultHolder) {
            this.zzih = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzp(DataHolder dataHolder) {
            this.zzih.setResult(new zzcv(dataHolder));
        }
    }

    private static final class zzct extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> zzii;

        public zzct(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> resultHolder) {
            this.zzii = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(int i2, Bundle bundle) {
            bundle.setClassLoader(zzct.class.getClassLoader());
            this.zzii.setResult(new zzaq(GamesStatusCodes.zza(i2), bundle));
        }
    }

    private static final class zzcu implements Achievements.UpdateAchievementResult {
        private final String zzfa;
        private final Status zzgf;

        zzcu(int i2, String str) {
            this.zzgf = GamesStatusCodes.zza(i2);
            this.zzfa = str;
        }

        @Override // com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
        public final String getAchievementId() {
            return this.zzfa;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzcv extends zzcr implements TurnBasedMultiplayer.UpdateMatchResult {
        zzcv(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzcw extends zzw implements Requests.UpdateRequestsResult {
        private final zzem zzij;

        zzcw(DataHolder dataHolder) {
            super(dataHolder);
            this.zzij = zzem.zzbd(dataHolder);
        }

        @Override // com.google.android.gms.games.request.Requests.UpdateRequestsResult
        public final Set<String> getRequestIds() {
            return this.zzij.getRequestIds();
        }

        @Override // com.google.android.gms.games.request.Requests.UpdateRequestsResult
        public final int getRequestOutcome(String str) {
            return this.zzij.getRequestOutcome(str);
        }
    }

    private static final class zzd extends zzw implements Quests.AcceptQuestResult {
        private final Quest zzgd;

        zzd(DataHolder dataHolder) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.zzgd = new QuestEntity(questBuffer.get(0));
                } else {
                    this.zzgd = null;
                }
                questBuffer.release();
            } catch (Throwable th) {
                questBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.quest.Quests.AcceptQuestResult
        public final Quest getQuest() {
            return this.zzgd;
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.zze$zze, reason: collision with other inner class name */
    private static final class BinderC0029zze extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> zzge;

        BinderC0029zze(BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzb(int i2, String str) {
            this.zzge.setResult(new zzcu(i2, str));
        }
    }

    private static final class zzf extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Achievements.LoadAchievementsResult> zzge;

        zzf(BaseImplementation.ResultHolder<Achievements.LoadAchievementsResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(DataHolder dataHolder) {
            this.zzge.setResult(new zzal(dataHolder));
        }
    }

    private static final class zzg implements TurnBasedMultiplayer.CancelMatchResult {
        private final Status zzgf;
        private final String zzgg;

        zzg(Status status, String str) {
            this.zzgf = status;
            this.zzgg = str;
        }

        @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
        public final String getMatchId() {
            return this.zzgg;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzh extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Videos.CaptureAvailableResult> zzge;

        zzh(BaseImplementation.ResultHolder<Videos.CaptureAvailableResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(int i2, boolean z2) {
            this.zzge.setResult(new zzi(new Status(i2), z2));
        }
    }

    private static final class zzi implements Videos.CaptureAvailableResult {
        private final Status zzgf;
        private final boolean zzgh;

        zzi(Status status, boolean z2) {
            this.zzgf = status;
            this.zzgh = z2;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }

        @Override // com.google.android.gms.games.video.Videos.CaptureAvailableResult
        public final boolean isAvailable() {
            return this.zzgh;
        }
    }

    private static final class zzj extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Videos.CaptureCapabilitiesResult> zzge;

        zzj(BaseImplementation.ResultHolder<Videos.CaptureCapabilitiesResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(int i2, VideoCapabilities videoCapabilities) {
            this.zzge.setResult(new zzk(new Status(i2), videoCapabilities));
        }
    }

    private static final class zzk implements Videos.CaptureCapabilitiesResult {
        private final Status zzgf;
        private final VideoCapabilities zzgi;

        zzk(Status status, VideoCapabilities videoCapabilities) {
            this.zzgf = status;
            this.zzgi = videoCapabilities;
        }

        @Override // com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult
        public final VideoCapabilities getCapabilities() {
            return this.zzgi;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzl extends com.google.android.gms.games.internal.zza {
        private final ListenerHolder<Videos.CaptureOverlayStateListener> zzgj;

        zzl(ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) {
            this.zzgj = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callback must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void onCaptureOverlayStateChanged(int i2) {
            this.zzgj.notifyListener(new zzm(i2));
        }
    }

    private static final class zzm implements ListenerHolder.Notifier<Videos.CaptureOverlayStateListener> {
        private final int zzgk;

        zzm(int i2) {
            this.zzgk = i2;
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final /* synthetic */ void notifyListener(Videos.CaptureOverlayStateListener captureOverlayStateListener) {
            captureOverlayStateListener.onCaptureOverlayStateChanged(this.zzgk);
        }

        @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzn extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Videos.CaptureStateResult> zzge;

        public zzn(BaseImplementation.ResultHolder<Videos.CaptureStateResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzg(int i2, Bundle bundle) {
            this.zzge.setResult(new zzo(new Status(i2), CaptureState.zzb(bundle)));
        }
    }

    private static final class zzo implements Videos.CaptureStateResult {
        private final Status zzgf;
        private final CaptureState zzgl;

        zzo(Status status, CaptureState captureState) {
            this.zzgf = status;
            this.zzgl = captureState;
        }

        @Override // com.google.android.gms.games.video.Videos.CaptureStateResult
        public final CaptureState getCaptureState() {
            return this.zzgl;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzp extends zzw implements Quests.ClaimMilestoneResult {
        private final Quest zzgd;
        private final Milestone zzgm;

        zzp(DataHolder dataHolder, String str) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    QuestEntity questEntity = new QuestEntity(questBuffer.get(0));
                    this.zzgd = questEntity;
                    List<Milestone> listZzcj = questEntity.zzcj();
                    int size = listZzcj.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (listZzcj.get(i2).getMilestoneId().equals(str)) {
                            this.zzgm = listZzcj.get(i2);
                            return;
                        }
                    }
                    this.zzgm = null;
                } else {
                    this.zzgm = null;
                    this.zzgd = null;
                }
            } finally {
                questBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
        public final Milestone getMilestone() {
            return this.zzgm;
        }

        @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
        public final Quest getQuest() {
            return this.zzgd;
        }
    }

    private static final class zzq extends zzw implements Snapshots.CommitSnapshotResult {
        private final SnapshotMetadata zzgn;

        zzq(DataHolder dataHolder) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() > 0) {
                    this.zzgn = new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0));
                } else {
                    this.zzgn = null;
                }
                snapshotMetadataBuffer.release();
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult
        public final SnapshotMetadata getSnapshotMetadata() {
            return this.zzgn;
        }
    }

    private static final class zzr extends zzc {
        zzr(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzc
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }

    private static final class zzs implements Snapshots.DeleteSnapshotResult {
        private final Status zzgf;
        private final String zzgo;

        zzs(int i2, String str) {
            this.zzgf = GamesStatusCodes.zza(i2);
            this.zzgo = str;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
        public final String getSnapshotId() {
            return this.zzgo;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzt extends zzc {
        zzt(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.zze.zzc
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }

    private static final class zzu extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Events.LoadEventsResult> zzge;

        zzu(BaseImplementation.ResultHolder<Events.LoadEventsResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzb(DataHolder dataHolder) {
            this.zzge.setResult(new zzam(dataHolder));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zzv extends zzej {
        public zzv() {
            super(zze.this.getContext().getMainLooper(), CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        }

        @Override // com.google.android.gms.internal.games.zzej
        protected final void zzf(String str, int i2) {
            try {
                if (zze.this.isConnected()) {
                    ((com.google.android.gms.games.internal.zzy) zze.this.getService()).zza(str, i2);
                    return;
                }
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 89);
                sb.append("Unable to increment event ");
                sb.append(str);
                sb.append(" by ");
                sb.append(i2);
                sb.append(" because the games client is no longer connected");
                com.google.android.gms.games.internal.zzh.e("GamesClientImpl", sb.toString());
            } catch (RemoteException e2) {
                zze zzeVar = zze.this;
                zze.zza(e2);
            } catch (SecurityException e3) {
                zze zzeVar2 = zze.this;
                zze.zza(e3);
            }
        }
    }

    private static abstract class zzw extends DataHolderResult {
        protected zzw(DataHolder dataHolder) {
            super(dataHolder, GamesStatusCodes.zza(dataHolder.getStatusCode()));
        }
    }

    private static final class zzx extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<GamesMetadata.LoadGamesResult> zzge;

        zzx(BaseImplementation.ResultHolder<GamesMetadata.LoadGamesResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zzg(DataHolder dataHolder) {
            this.zzge.setResult(new zzan(dataHolder));
        }
    }

    private static final class zzy extends com.google.android.gms.games.internal.zza {
        private final BaseImplementation.ResultHolder<Games.GetServerAuthCodeResult> zzge;

        public zzy(BaseImplementation.ResultHolder<Games.GetServerAuthCodeResult> resultHolder) {
            this.zzge = (BaseImplementation.ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzu
        public final void zza(int i2, String str) {
            this.zzge.setResult(new zzz(GamesStatusCodes.zza(i2), str));
        }
    }

    private static final class zzz implements Games.GetServerAuthCodeResult {
        private final Status zzgf;
        private final String zzgp;

        zzz(Status status, String str) {
            this.zzgf = status;
            this.zzgp = str;
        }

        @Override // com.google.android.gms.games.Games.GetServerAuthCodeResult
        public final String getCode() {
            return this.zzgp;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgf;
        }
    }

    public zze(Context context, Looper looper, ClientSettings clientSettings, Games.GamesOptions gamesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 1, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzfp = new com.google.android.gms.games.internal.zzf(this);
        this.zzfu = false;
        this.zzfy = false;
        this.zzfq = clientSettings.getRealClientPackageName();
        this.zzfv = new Binder();
        this.zzft = com.google.android.gms.games.internal.zzac.zza(this, clientSettings.getGravityForPopups());
        this.zzfw = hashCode();
        this.zzfx = gamesOptions;
        if (gamesOptions.zzaz) {
            return;
        }
        if (clientSettings.getViewForPopups() != null || (context instanceof Activity)) {
            zza(clientSettings.getViewForPopups());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Room zzba(DataHolder dataHolder) {
        com.google.android.gms.games.multiplayer.realtime.zzb zzbVar = new com.google.android.gms.games.multiplayer.realtime.zzb(dataHolder);
        try {
            return zzbVar.getCount() > 0 ? zzbVar.get(0).freeze() : null;
        } finally {
            zzbVar.release();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzfr = null;
        this.zzfs = null;
        super.connect(connectionProgressReportCallbacks);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
        return iInterfaceQueryLocalInterface instanceof com.google.android.gms.games.internal.zzy ? (com.google.android.gms.games.internal.zzy) iInterfaceQueryLocalInterface : new com.google.android.gms.games.internal.zzz(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public void disconnect() {
        this.zzfu = false;
        if (isConnected()) {
            try {
                com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
                zzyVar.zzbd();
                this.zzfp.flush();
                zzyVar.zza(this.zzfw);
            } catch (RemoteException unused) {
                com.google.android.gms.games.internal.zzh.w("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public Bundle getConnectionHint() {
        try {
            Bundle connectionHint = ((com.google.android.gms.games.internal.zzy) getService()).getConnectionHint();
            if (connectionHint != null) {
                connectionHint.setClassLoader(zze.class.getClassLoader());
                this.zzfz = connectionHint;
            }
            return connectionHint;
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Bundle getGetServiceRequestExtraArgs() {
        String string = getContext().getResources().getConfiguration().locale.toString();
        Bundle bundleZzf = this.zzfx.zzf();
        bundleZzf.putString(ServiceSpecificExtraArgs.GamesExtraArgs.GAME_PACKAGE_NAME, this.zzfq);
        bundleZzf.putString(ServiceSpecificExtraArgs.GamesExtraArgs.DESIRED_LOCALE, string);
        bundleZzf.putParcelable(ServiceSpecificExtraArgs.GamesExtraArgs.WINDOW_TOKEN, new BinderWrapper(this.zzft.zzjd.zzjb));
        bundleZzf.putInt("com.google.android.gms.games.key.API_VERSION", 6);
        bundleZzf.putBundle(ServiceSpecificExtraArgs.GamesExtraArgs.SIGNIN_OPTIONS, SignInClientImpl.createBundleFromClientSettings(getClientSettings()));
        return bundleZzf;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getServiceDescriptor() {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.gms.games.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ void onConnectedLocked(IInterface iInterface) {
        com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) iInterface;
        super.onConnectedLocked(zzyVar);
        if (this.zzfu) {
            this.zzft.zzbj();
            this.zzfu = false;
        }
        Games.GamesOptions gamesOptions = this.zzfx;
        if (gamesOptions.zzar || gamesOptions.zzaz) {
            return;
        }
        try {
            zzyVar.zza(new zzbo(this.zzft), this.zzfw);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzfu = false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected void onPostInitHandler(int i2, IBinder iBinder, Bundle bundle, int i3) {
        if (i2 == 0 && bundle != null) {
            bundle.setClassLoader(zze.class.getClassLoader());
            boolean z2 = bundle.getBoolean("show_welcome_popup");
            this.zzfu = z2;
            this.zzfy = z2;
            this.zzfr = (PlayerEntity) bundle.getParcelable("com.google.android.gms.games.current_player");
            this.zzfs = (GameEntity) bundle.getParcelable("com.google.android.gms.games.current_game");
        }
        super.onPostInitHandler(i2, iBinder, bundle, i3);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks) {
        try {
            zzb(new com.google.android.gms.games.internal.zzg(this, signOutCallbacks));
        } catch (RemoteException unused) {
            signOutCallbacks.onSignOutComplete();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public boolean requiresSignIn() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.GmsClient
    protected Set<Scope> validateScopes(Set<Scope> set) {
        HashSet hashSet = new HashSet(set);
        boolean zContains = set.contains(Games.SCOPE_GAMES);
        Scope scope = Games.SCOPE_GAMES_LITE;
        boolean zContains2 = set.contains(scope);
        boolean z2 = true;
        if (set.contains(Games.zzal)) {
            Preconditions.checkState(!zContains, "Cannot have both %s and %s!", Scopes.GAMES, FirstPartyScopes.GAMES_1P);
        } else {
            if (!zContains && !zContains2) {
                z2 = false;
            }
            Preconditions.checkState(z2, "Games APIs requires %s function.", Scopes.GAMES_LITE);
            if (zContains2 && zContains) {
                hashSet.remove(scope);
            }
        }
        return hashSet;
    }

    public final int zza(ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder, byte[] bArr, String str, String str2) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbv(listenerHolder), bArr, str, str2);
    }

    public final void zzaa() {
        ((com.google.android.gms.games.internal.zzy) getService()).zzb(this.zzfw);
    }

    public final void zzab() {
        try {
            zzaa();
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzac() {
        ((com.google.android.gms.games.internal.zzy) getService()).zzc(this.zzfw);
    }

    public final void zzad() {
        try {
            zzac();
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzae() {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zze(this.zzfw);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzaf() {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzd(this.zzfw);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zzag() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzag();
    }

    public final Intent zzah() {
        try {
            return zzag();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzai() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzai();
    }

    public final Intent zzaj() {
        try {
            return zzai();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final int zzak() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzak();
    }

    public final int zzal() {
        try {
            return zzak();
        } catch (RemoteException e2) {
            zza(e2);
            return 4368;
        }
    }

    public final String zzam() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzam();
    }

    public final String zzan() {
        try {
            return zzam();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final int zzao() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzao();
    }

    public final int zzap() {
        try {
            return zzao();
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final Intent zzaq() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzaq();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final int zzar() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzar();
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final int zzas() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzas();
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final int zzat() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzat();
    }

    public final int zzau() {
        try {
            return zzat();
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final int zzav() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzav();
    }

    public final int zzaw() {
        try {
            return zzav();
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final Intent zzax() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzbi();
    }

    public final Intent zzay() {
        try {
            return zzax();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final boolean zzaz() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzaz();
    }

    public final int zzb(ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder, byte[] bArr, String str, String str2) {
        try {
            return zza(listenerHolder, bArr, str, str2);
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final void zzbc() {
        try {
            zzbb();
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzbd() {
        if (isConnected()) {
            try {
                ((com.google.android.gms.games.internal.zzy) getService()).zzbd();
            } catch (RemoteException e2) {
                zza(e2);
            }
        }
    }

    public final Intent zzc(int i2, int i3, boolean z2) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzc(i2, i3, z2);
    }

    public final Intent zzd(int i2, int i3, boolean z2) {
        try {
            return zzc(i2, i3, z2);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zze(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zze(new zzcp(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzf(BaseImplementation.ResultHolder<TurnBasedMultiplayer.CancelMatchResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzd(new zzcn(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzg(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzf(new zzcq(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzh(BaseImplementation.ResultHolder<Quests.AcceptQuestResult> resultHolder, String str) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzh(new zzbp(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzi(BaseImplementation.ResultHolder<Snapshots.DeleteSnapshotResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzg(new zzci(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzj(int i2) {
        this.zzft.zzjd.gravity = i2;
    }

    public final void zzk(int i2) {
        ((com.google.android.gms.games.internal.zzy) getService()).zzk(i2);
    }

    public final void zzl(int i2) {
        try {
            zzk(i2);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Bundle zzo() {
        Bundle connectionHint = getConnectionHint();
        if (connectionHint == null) {
            connectionHint = this.zzfz;
        }
        this.zzfz = null;
        return connectionHint;
    }

    public final String zzp() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzp();
    }

    public final String zzq() {
        try {
            return zzp();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Player zzr() {
        checkConnected();
        synchronized (this) {
            try {
                if (this.zzfr == null) {
                    PlayerBuffer playerBuffer = new PlayerBuffer(((com.google.android.gms.games.internal.zzy) getService()).zzbg());
                    try {
                        if (playerBuffer.getCount() > 0) {
                            this.zzfr = (PlayerEntity) ((Player) playerBuffer.get(0)).freeze();
                        }
                        playerBuffer.release();
                    } catch (Throwable th) {
                        playerBuffer.release();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return this.zzfr;
    }

    public final Player zzs() {
        try {
            return zzr();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Game zzt() {
        checkConnected();
        synchronized (this) {
            try {
                if (this.zzfs == null) {
                    GameBuffer gameBuffer = new GameBuffer(((com.google.android.gms.games.internal.zzy) getService()).zzbh());
                    try {
                        if (gameBuffer.getCount() > 0) {
                            this.zzfs = (GameEntity) ((Game) gameBuffer.get(0)).freeze();
                        }
                        gameBuffer.release();
                    } catch (Throwable th) {
                        gameBuffer.release();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return this.zzfs;
    }

    public final Game zzu() {
        try {
            return zzt();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzv() {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzv();
    }

    public final Intent zzw() {
        try {
            return zzv();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzx() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzx();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzy() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzy();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzz() {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzz();
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final int zza(byte[] bArr, String str) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zzb(bArr, str, (String[]) null);
    }

    public final int zzb(byte[] bArr, String str) {
        try {
            return zza(bArr, str);
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final boolean zzba() {
        try {
            return zzaz();
        } catch (RemoteException e2) {
            zza(e2);
            return false;
        }
    }

    public final void zzbb() {
        ((com.google.android.gms.games.internal.zzy) getService()).zzf(this.zzfw);
    }

    public final void zzc(BaseImplementation.ResultHolder<Videos.CaptureCapabilitiesResult> resultHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzc(new zzj(resultHolder));
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final Intent zzd(String str) {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzd(str);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zze(BaseImplementation.ResultHolder<Stats.LoadPlayerStatsResult> resultHolder, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzf(new zzbm(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzf(BaseImplementation.ResultHolder<Snapshots.LoadSnapshotsResult> resultHolder, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzd(new zzck(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzg(ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) {
        ((com.google.android.gms.games.internal.zzy) getService()).zze(new zzl(listenerHolder), this.zzfw);
    }

    public final void zzh(ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) {
        try {
            zzg(listenerHolder);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final int zza(byte[] bArr, String str, String[] strArr) {
        Preconditions.checkNotNull(strArr, "Participant IDs must not be null");
        try {
            Preconditions.checkNotNull(strArr, "Participant IDs must not be null");
            return ((com.google.android.gms.games.internal.zzy) getService()).zzb(bArr, str, strArr);
        } catch (RemoteException e2) {
            zza(e2);
            return -1;
        }
    }

    public final Intent zzb(int i2, int i3, boolean z2) {
        try {
            return zza(i2, i3, z2);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzc(BaseImplementation.ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzco(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzd(BaseImplementation.ResultHolder<Videos.CaptureStateResult> resultHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzd(new zzn(resultHolder));
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zze(ListenerHolder<QuestUpdateListener> listenerHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzd(new zzbs(listenerHolder), this.zzfw);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzf(ListenerHolder<OnRequestReceivedListener> listenerHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzc(new zzbw(listenerHolder), this.zzfw);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zza(int i2, int i3, boolean z2) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zza(i2, i3, z2);
    }

    public final Intent zzb(PlayerEntity playerEntity) {
        try {
            return zza(playerEntity);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzc(BaseImplementation.ResultHolder<Achievements.LoadAchievementsResult> resultHolder, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzf(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzd(BaseImplementation.ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> resultHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzc(new zzco(resultHolder), str);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zze(String str) {
        try {
            com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
            com.google.android.gms.games.internal.zzae zzaeVar = this.zzft.zzjd;
            zzyVar.zza(str, zzaeVar.zzjb, zzaeVar.zzbk());
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zza(int i2, byte[] bArr, int i3, Bitmap bitmap, String str) {
        try {
            Intent intentZza = ((com.google.android.gms.games.internal.zzy) getService()).zza(i2, bArr, i3, str);
            Preconditions.checkNotNull(bitmap, "Must provide a non null icon");
            intentZza.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
            return intentZza;
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final Intent zzb(Room room, int i2) {
        try {
            return zza(room, i2);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzc(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
        ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzaz(listenerHolder), this.zzfw);
    }

    public final void zzd(BaseImplementation.ResultHolder<Events.LoadEventsResult> resultHolder, boolean z2) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zze(new zzu(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zze(String str, int i2) {
        try {
            zzd(str, i2);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zza(PlayerEntity playerEntity) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zza(playerEntity);
    }

    public final Intent zzb(String str, boolean z2, boolean z3, int i2) {
        try {
            return zza(str, z2, z3, i2);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzc(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        ((com.google.android.gms.games.internal.zzy) getService()).zza((com.google.android.gms.games.internal.zzu) new zzcc(listenerHolder, listenerHolder2, listenerHolder3), (IBinder) this.zzfv, roomConfig.getInvitationId(), false, this.zzfw);
    }

    public final void zzd(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
        try {
            zzc(listenerHolder);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zza(Room room, int i2) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zza((RoomEntity) room.freeze(), i2);
    }

    public final String zzb(boolean z2) {
        try {
            return zza(true);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzc(String str) {
        try {
            zzb(str);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzd(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        try {
            zzc(listenerHolder, listenerHolder2, listenerHolder3, roomConfig);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final Intent zza(String str, int i2, int i3) {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zzb(str, i2, i3);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcg(resultHolder));
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzc(String str, int i2) {
        try {
            zzb(str, i2);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zzd(String str, int i2) {
        ((com.google.android.gms.games.internal.zzy) getService()).zzd(str, i2);
    }

    public final Intent zza(String str, boolean z2, boolean z3, int i2) {
        return ((com.google.android.gms.games.internal.zzy) getService()).zza(str, z2, z3, i2);
    }

    public final void zzb(BaseImplementation.ResultHolder<Videos.CaptureAvailableResult> resultHolder, int i2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb((com.google.android.gms.games.internal.zzu) new zzh(resultHolder), i2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final Intent zza(int[] iArr) {
        try {
            return ((com.google.android.gms.games.internal.zzy) getService()).zza(iArr);
        } catch (RemoteException e2) {
            zza(e2);
            return null;
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> resultHolder, String str) {
        BinderC0029zze binderC0029zze = resultHolder == null ? null : new BinderC0029zze(resultHolder);
        try {
            com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
            com.google.android.gms.games.internal.zzae zzaeVar = this.zzft.zzjd;
            zzyVar.zzb(binderC0029zze, str, zzaeVar.zzjb, zzaeVar.zzbk());
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final String zza(boolean z2) {
        PlayerEntity playerEntity = this.zzfr;
        return playerEntity != null ? playerEntity.getPlayerId() : ((com.google.android.gms.games.internal.zzy) getService()).zzbf();
    }

    public final void zzb(BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> resultHolder, String str, int i2) {
        BinderC0029zze binderC0029zze = resultHolder == null ? null : new BinderC0029zze(resultHolder);
        try {
            com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
            com.google.android.gms.games.internal.zzae zzaeVar = this.zzft.zzjd;
            zzyVar.zzb(binderC0029zze, str, i2, zzaeVar.zzjb, zzaeVar.zzbk());
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(IBinder iBinder, Bundle bundle) {
        if (isConnected()) {
            try {
                ((com.google.android.gms.games.internal.zzy) getService()).zza(iBinder, bundle);
            } catch (RemoteException e2) {
                zza(e2);
            }
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Leaderboards.LoadScoresResult> resultHolder, String str, int i2, int i3, int i4, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzah(resultHolder), str, i2, i3, i4, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(RemoteException remoteException) {
        com.google.android.gms.games.internal.zzh.w("GamesClientImpl", "service died", remoteException);
    }

    public final void zzb(BaseImplementation.ResultHolder<Quests.ClaimMilestoneResult> resultHolder, String str, String str2) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzbr(resultHolder, str2), str, str2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(View view) {
        this.zzft.zzb(view);
    }

    public final void zzb(BaseImplementation.ResultHolder<Leaderboards.LeaderboardMetadataResult> resultHolder, String str, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzai(resultHolder), str, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<GamesMetadata.LoadGamesResult> resultHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzx(resultHolder));
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Leaderboards.LeaderboardMetadataResult> resultHolder, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzai(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Invitations.LoadInvitationsResult> resultHolder, int i2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza((com.google.android.gms.games.internal.zzu) new zzae(resultHolder), i2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Quests.LoadQuestsResult> resultHolder, boolean z2, String[] strArr) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbt(resultHolder), strArr, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Requests.LoadRequestsResult> resultHolder, int i2, int i3, int i4) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbz(resultHolder), i2, i3, i4);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Requests.UpdateRequestsResult> resultHolder, String[] strArr) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzca(resultHolder), strArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Players.LoadPlayersResult> resultHolder, int i2, boolean z2, boolean z3) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbn(resultHolder), i2, z2, z3);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
        try {
            zza(listenerHolder);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> resultHolder, int i2, int[] iArr) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzct(resultHolder), i2, iArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        try {
            zza(listenerHolder, listenerHolder2, listenerHolder3, roomConfig);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Leaderboards.LoadScoresResult> resultHolder, LeaderboardScoreBuffer leaderboardScoreBuffer, int i2, int i3) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzah(resultHolder), leaderboardScoreBuffer.zzcb().zzcc(), i2, i3);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(Snapshot snapshot) {
        try {
            zza(snapshot);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> resultHolder, TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzco(resultHolder), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.zzci(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(String str) {
        ((com.google.android.gms.games.internal.zzy) getService()).zzf(str);
    }

    public final void zza(BaseImplementation.ResultHolder<Snapshots.CommitSnapshotResult> resultHolder, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Preconditions.checkState(!snapshotContents.isClosed(), "Snapshot already closed");
        BitmapTeleporter bitmapTeleporterZzcm = snapshotMetadataChange.zzcm();
        if (bitmapTeleporterZzcm != null) {
            bitmapTeleporterZzcm.setTempDir(getContext().getCacheDir());
        }
        Contents contentsZzcl = snapshotContents.zzcl();
        snapshotContents.close();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzch(resultHolder), snapshot.getMetadata().getSnapshotId(), (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, contentsZzcl);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zzb(String str, int i2) {
        ((com.google.android.gms.games.internal.zzy) getService()).zzb(str, i2);
    }

    private static <R> void zza(BaseImplementation.ResultHolder<R> resultHolder, SecurityException securityException) {
        if (resultHolder != null) {
            resultHolder.setFailedResult(GamesClientStatusCodes.zza(4));
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> resultHolder, String str) {
        BinderC0029zze binderC0029zze = resultHolder == null ? null : new BinderC0029zze(resultHolder);
        try {
            com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
            com.google.android.gms.games.internal.zzae zzaeVar = this.zzft.zzjd;
            zzyVar.zza(binderC0029zze, str, zzaeVar.zzjb, zzaeVar.zzbk());
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Achievements.UpdateAchievementResult> resultHolder, String str, int i2) {
        BinderC0029zze binderC0029zze = resultHolder == null ? null : new BinderC0029zze(resultHolder);
        try {
            com.google.android.gms.games.internal.zzy zzyVar = (com.google.android.gms.games.internal.zzy) getService();
            com.google.android.gms.games.internal.zzae zzaeVar = this.zzft.zzjd;
            zzyVar.zza(binderC0029zze, str, i2, zzaeVar.zzjb, zzaeVar.zzbk());
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Leaderboards.LoadScoresResult> resultHolder, String str, int i2, int i3, int i4, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzah(resultHolder), str, i2, i3, i4, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Players.LoadPlayersResult> resultHolder, String str, int i2, boolean z2, boolean z3) {
        str.getClass();
        if (!str.equals("played_with")) {
            throw new IllegalArgumentException(str.length() != 0 ? "Invalid player collection: ".concat(str) : new String("Invalid player collection: "));
        }
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbn(resultHolder), str, i2, z2, z3);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Leaderboards.SubmitScoreResult> resultHolder, String str, long j2, String str2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(resultHolder == null ? null : new zzcl(resultHolder), str, j2, str2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> resultHolder, String str, String str2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcp(resultHolder), str, str2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Leaderboards.LoadPlayerScoreResult> resultHolder, String str, String str2, int i2, int i3) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbl(resultHolder), (String) null, str2, i2, i3);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Snapshots.OpenSnapshotResult> resultHolder, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        Preconditions.checkState(!snapshotContents.isClosed(), "SnapshotContents already closed");
        BitmapTeleporter bitmapTeleporterZzcm = snapshotMetadataChange.zzcm();
        if (bitmapTeleporterZzcm != null) {
            bitmapTeleporterZzcm.setTempDir(getContext().getCacheDir());
        }
        Contents contentsZzcl = snapshotContents.zzcl();
        snapshotContents.close();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcj(resultHolder), str, str2, (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, contentsZzcl);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Players.LoadPlayersResult> resultHolder, String str, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzb(new zzbn(resultHolder), str, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Snapshots.OpenSnapshotResult> resultHolder, String str, boolean z2, int i2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcj(resultHolder), str, z2, i2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> resultHolder, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcs(resultHolder), str, bArr, str2, participantResultArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> resultHolder, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcs(resultHolder), str, bArr, participantResultArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Players.LoadPlayersResult> resultHolder, boolean z2) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zzc(new zzbn(resultHolder), z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Events.LoadEventsResult> resultHolder, boolean z2, String... strArr) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzu(resultHolder), z2, strArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Quests.LoadQuestsResult> resultHolder, int[] iArr, int i2, boolean z2) {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzbt(resultHolder), iArr, i2, z2);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<Requests.UpdateRequestsResult> resultHolder, String[] strArr) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzca(resultHolder), strArr);
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }

    public final void zza(ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
        ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzab(listenerHolder), this.zzfw);
    }

    public final void zza(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcc(listenerHolder, listenerHolder2, listenerHolder3), this.zzfv, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), false, this.zzfw);
    }

    public final void zza(ListenerHolder<? extends RoomUpdateListener> listenerHolder, String str) {
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(new zzcc(listenerHolder), str);
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    public final void zza(Snapshot snapshot) {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Preconditions.checkState(!snapshotContents.isClosed(), "Snapshot already closed");
        Contents contentsZzcl = snapshotContents.zzcl();
        snapshotContents.close();
        ((com.google.android.gms.games.internal.zzy) getService()).zza(contentsZzcl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(SecurityException securityException) {
        com.google.android.gms.games.internal.zzh.e("GamesClientImpl", "Is player signed out?", securityException);
    }

    public final void zza(String str, int i2) {
        this.zzfp.zza(str, i2);
    }

    public final void zza(String str, BaseImplementation.ResultHolder<Games.GetServerAuthCodeResult> resultHolder) {
        Preconditions.checkNotEmpty(str, "Please provide a valid serverClientId");
        try {
            ((com.google.android.gms.games.internal.zzy) getService()).zza(str, new zzy(resultHolder));
        } catch (SecurityException e2) {
            zza(resultHolder, e2);
        }
    }
}

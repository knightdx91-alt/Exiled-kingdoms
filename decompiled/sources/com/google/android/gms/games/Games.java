package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.games.zzdy;
import com.google.android.gms.internal.games.zzep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@KeepForSdk
public final class Games {

    @Deprecated
    public static final Api<GamesOptions> API;

    @Deprecated
    public static final Achievements Achievements;
    private static final Api.AbstractClientBuilder<com.google.android.gms.games.internal.zze, GamesOptions> CLIENT_BUILDER;
    static final Api.ClientKey<com.google.android.gms.games.internal.zze> CLIENT_KEY;
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final String EXTRA_STATUS = "status";

    @Deprecated
    public static final Events Events;

    @Deprecated
    public static final GamesMetadata GamesMetadata;

    @Deprecated
    public static final Invitations Invitations;

    @Deprecated
    public static final Leaderboards Leaderboards;

    @Deprecated
    public static final Notifications Notifications;

    @Deprecated
    public static final Players Players;

    @Deprecated
    public static final Quests Quests;

    @Deprecated
    public static final RealTimeMultiplayer RealTimeMultiplayer;

    @Deprecated
    public static final Requests Requests;
    public static final Scope SCOPE_GAMES;
    public static final Scope SCOPE_GAMES_LITE;

    @Deprecated
    public static final Snapshots Snapshots;

    @Deprecated
    public static final Stats Stats;

    @Deprecated
    public static final TurnBasedMultiplayer TurnBasedMultiplayer;

    @Deprecated
    public static final Videos Videos;
    private static final Api.AbstractClientBuilder<com.google.android.gms.games.internal.zze, GamesOptions> zzak;
    public static final Scope zzal;
    private static final Api<GamesOptions> zzam;
    private static final com.google.android.gms.internal.games.zze zzan;
    private static final Multiplayer zzao;
    private static final zzep zzap;

    @Deprecated
    public static final class GamesOptions implements GoogleSignInOptionsExtension, Api.ApiOptions.HasGoogleSignInAccountOptions, Api.ApiOptions.Optional {
        public final boolean zzar;
        public final boolean zzas;
        public final int zzat;
        public final boolean zzau;
        public final int zzav;
        public final String zzaw;
        public final ArrayList<String> zzax;
        public final boolean zzay;
        public final boolean zzaz;
        public final boolean zzba;
        public final GoogleSignInAccount zzbb;

        @Deprecated
        public static final class Builder {
            private boolean zzar;
            private boolean zzas;
            private int zzat;
            private boolean zzau;
            private int zzav;
            private String zzaw;
            private ArrayList<String> zzax;
            private boolean zzay;
            private boolean zzaz;
            private boolean zzba;
            GoogleSignInAccount zzbb;

            private Builder() {
                this.zzar = false;
                this.zzas = true;
                this.zzat = 17;
                this.zzau = false;
                this.zzav = 4368;
                this.zzaw = null;
                this.zzax = new ArrayList<>();
                this.zzay = false;
                this.zzaz = false;
                this.zzba = false;
                this.zzbb = null;
            }

            public final GamesOptions build() {
                return new GamesOptions(this.zzar, this.zzas, this.zzat, this.zzau, this.zzav, this.zzaw, this.zzax, this.zzay, this.zzaz, this.zzba, this.zzbb, null);
            }

            public final Builder setSdkVariant(int i2) {
                this.zzav = i2;
                return this;
            }

            public final Builder setShowConnectingPopup(boolean z2) {
                this.zzas = z2;
                this.zzat = 17;
                return this;
            }

            private Builder(GamesOptions gamesOptions) {
                this.zzar = false;
                this.zzas = true;
                this.zzat = 17;
                this.zzau = false;
                this.zzav = 4368;
                this.zzaw = null;
                this.zzax = new ArrayList<>();
                this.zzay = false;
                this.zzaz = false;
                this.zzba = false;
                this.zzbb = null;
                if (gamesOptions != null) {
                    this.zzar = gamesOptions.zzar;
                    this.zzas = gamesOptions.zzas;
                    this.zzat = gamesOptions.zzat;
                    this.zzau = gamesOptions.zzau;
                    this.zzav = gamesOptions.zzav;
                    this.zzaw = gamesOptions.zzaw;
                    this.zzax = gamesOptions.zzax;
                    this.zzay = gamesOptions.zzay;
                    this.zzaz = gamesOptions.zzaz;
                    this.zzba = gamesOptions.zzba;
                    this.zzbb = gamesOptions.zzbb;
                }
            }

            public final Builder setShowConnectingPopup(boolean z2, int i2) {
                this.zzas = z2;
                this.zzat = i2;
                return this;
            }

            /* synthetic */ Builder(GamesOptions gamesOptions, zzi zziVar) {
                this((GamesOptions) null);
            }

            /* synthetic */ Builder(zzi zziVar) {
                this();
            }
        }

        private GamesOptions(boolean z2, boolean z3, int i2, boolean z4, int i3, String str, ArrayList<String> arrayList, boolean z5, boolean z6, boolean z7, GoogleSignInAccount googleSignInAccount) {
            this.zzar = z2;
            this.zzas = z3;
            this.zzat = i2;
            this.zzau = z4;
            this.zzav = i3;
            this.zzaw = str;
            this.zzax = arrayList;
            this.zzay = z5;
            this.zzaz = z6;
            this.zzba = z7;
            this.zzbb = googleSignInAccount;
        }

        public static Builder builder() {
            return new Builder((zzi) null);
        }

        public final boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GamesOptions)) {
                return false;
            }
            GamesOptions gamesOptions = (GamesOptions) obj;
            if (this.zzar == gamesOptions.zzar && this.zzas == gamesOptions.zzas && this.zzat == gamesOptions.zzat && this.zzau == gamesOptions.zzau && this.zzav == gamesOptions.zzav && ((str = this.zzaw) != null ? str.equals(gamesOptions.zzaw) : gamesOptions.zzaw == null) && this.zzax.equals(gamesOptions.zzax) && this.zzay == gamesOptions.zzay && this.zzaz == gamesOptions.zzaz && this.zzba == gamesOptions.zzba) {
                GoogleSignInAccount googleSignInAccount = this.zzbb;
                GoogleSignInAccount googleSignInAccount2 = gamesOptions.zzbb;
                if (googleSignInAccount != null ? googleSignInAccount.equals(googleSignInAccount2) : googleSignInAccount2 == null) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
        public final int getExtensionType() {
            return 1;
        }

        @Override // com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzbb;
        }

        @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
        public final List<Scope> getImpliedScopes() {
            return Collections.singletonList(this.zzay ? Games.SCOPE_GAMES : Games.SCOPE_GAMES_LITE);
        }

        public final int hashCode() {
            int i2 = ((((((((((this.zzar ? 1 : 0) + 527) * 31) + (this.zzas ? 1 : 0)) * 31) + this.zzat) * 31) + (this.zzau ? 1 : 0)) * 31) + this.zzav) * 31;
            String str = this.zzaw;
            int iHashCode = (((((((this.zzax.hashCode() + ((i2 + (str == null ? 0 : str.hashCode())) * 31)) * 31) + (this.zzay ? 1 : 0)) * 31) + (this.zzaz ? 1 : 0)) * 31) + (this.zzba ? 1 : 0)) * 31;
            GoogleSignInAccount googleSignInAccount = this.zzbb;
            return iHashCode + (googleSignInAccount != null ? googleSignInAccount.hashCode() : 0);
        }

        @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
        public final Bundle toBundle() {
            return zzf();
        }

        public final Bundle zzf() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.zzar);
            bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.zzas);
            bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.zzat);
            bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.zzau);
            bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.zzav);
            bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.zzaw);
            bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", this.zzax);
            bundle.putBoolean("com.google.android.gms.games.key.requireGooglePlus", this.zzay);
            bundle.putBoolean("com.google.android.gms.games.key.unauthenticated", this.zzaz);
            bundle.putBoolean("com.google.android.gms.games.key.skipWelcomePopup", this.zzba);
            return bundle;
        }

        /* synthetic */ GamesOptions(boolean z2, boolean z3, int i2, boolean z4, int i3, String str, ArrayList arrayList, boolean z5, boolean z6, boolean z7, GoogleSignInAccount googleSignInAccount, zzi zziVar) {
            this(z2, z3, i2, z4, i3, str, arrayList, z5, z6, z7, googleSignInAccount);
        }
    }

    @KeepForSdk
    @Deprecated
    public interface GetServerAuthCodeResult extends Result {
        @KeepForSdk
        String getCode();
    }

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, com.google.android.gms.games.internal.zze> {
        public zza(GoogleApiClient googleApiClient) {
            super(Games.CLIENT_KEY, googleApiClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class zzb extends Api.AbstractClientBuilder<com.google.android.gms.games.internal.zze, GamesOptions> {
        private zzb() {
        }

        @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
        public /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, GamesOptions gamesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            GamesOptions gamesOptionsBuild = gamesOptions;
            if (gamesOptionsBuild == null) {
                gamesOptionsBuild = new GamesOptions.Builder((zzi) null).build();
            }
            return new com.google.android.gms.games.internal.zze(context, looper, clientSettings, gamesOptionsBuild, connectionCallbacks, onConnectionFailedListener);
        }

        @Override // com.google.android.gms.common.api.Api.BaseClientBuilder
        public int getPriority() {
            return 1;
        }

        /* synthetic */ zzb(zzi zziVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class zzc extends zza<GetServerAuthCodeResult> {
        private zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }

        /* synthetic */ zzc(GoogleApiClient googleApiClient, zzi zziVar) {
            this(googleApiClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class zzd extends zza<Status> {
        private zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }

        /* synthetic */ zzd(GoogleApiClient googleApiClient, zzi zziVar) {
            this(googleApiClient);
        }
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [com.google.android.gms.internal.games.zzcw, com.google.android.gms.internal.games.zzep] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.gms.internal.games.zze, com.google.android.gms.internal.games.zzt] */
    static {
        Api.ClientKey<com.google.android.gms.games.internal.zze> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzi zziVar = new zzi();
        CLIENT_BUILDER = zziVar;
        zzj zzjVar = new zzj();
        zzak = zzjVar;
        SCOPE_GAMES = new Scope(Scopes.GAMES);
        SCOPE_GAMES_LITE = new Scope(Scopes.GAMES_LITE);
        API = new Api<>("Games.API", zziVar, clientKey);
        zzal = new Scope(FirstPartyScopes.GAMES_1P);
        zzam = new Api<>("Games.API_1P", zzjVar, clientKey);
        GamesMetadata = new com.google.android.gms.internal.games.zzad();
        Achievements = new com.google.android.gms.internal.games.zzf();
        zzan = new com.google.android.gms.internal.games.zzt();
        Events = new com.google.android.gms.internal.games.zzv();
        Leaderboards = new com.google.android.gms.internal.games.zzam();
        Invitations = new com.google.android.gms.internal.games.zzai();
        TurnBasedMultiplayer = new com.google.android.gms.internal.games.zzdb();
        RealTimeMultiplayer = new com.google.android.gms.internal.games.zzbz();
        zzao = new com.google.android.gms.internal.games.zzbc();
        Players = new com.google.android.gms.internal.games.zzbe();
        Notifications = new com.google.android.gms.internal.games.zzbd();
        Quests = new com.google.android.gms.internal.games.zzbo();
        Requests = new com.google.android.gms.internal.games.zzca();
        Snapshots = new com.google.android.gms.internal.games.zzci();
        Stats = new com.google.android.gms.internal.games.zzcx();
        Videos = new zzdy();
        zzap = new com.google.android.gms.internal.games.zzcw();
    }

    private Games() {
    }

    public static AchievementsClient getAchievementsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(activity, zza(googleSignInAccount));
    }

    @Deprecated
    public static String getAppId(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzan();
    }

    @Deprecated
    public static String getCurrentAccountName(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzq();
    }

    public static EventsClient getEventsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(activity, zza(googleSignInAccount));
    }

    public static GamesClient getGamesClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(activity, zza(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(activity, zza(googleSignInAccount));
    }

    @KeepForSdk
    @Deprecated
    public static PendingResult<GetServerAuthCodeResult> getGamesServerAuthCode(GoogleApiClient googleApiClient, String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid serverClientId");
        return googleApiClient.execute(new zzk(googleApiClient, str));
    }

    public static InvitationsClient getInvitationsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(activity, zza(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(activity, zza(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(activity, zza(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(activity, zza(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(activity, zza(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(activity, zza(googleSignInAccount));
    }

    @Deprecated
    public static int getSdkVariant(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzal();
    }

    @Deprecated
    public static Intent getSettingsIntent(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaj();
    }

    public static SnapshotsClient getSnapshotsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(activity, zza(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(activity, zza(googleSignInAccount));
    }

    public static VideosClient getVideosClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(activity, zza(googleSignInAccount));
    }

    @Deprecated
    public static void setGravityForPopups(GoogleApiClient googleApiClient, int i2) {
        com.google.android.gms.games.internal.zze zzeVarZza = zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzj(i2);
        }
    }

    @Deprecated
    public static void setViewForPopups(GoogleApiClient googleApiClient, View view) {
        Preconditions.checkNotNull(view);
        com.google.android.gms.games.internal.zze zzeVarZza = zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zza(view);
        }
    }

    @Deprecated
    public static PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzl(googleApiClient));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static GamesOptions zza(GoogleSignInAccount googleSignInAccount) {
        GamesOptions.Builder builder = new GamesOptions.Builder(null, 0 == true ? 1 : 0);
        builder.zzbb = googleSignInAccount;
        return builder.setSdkVariant(1052947).build();
    }

    public static com.google.android.gms.games.internal.zze zzb(GoogleApiClient googleApiClient, boolean z2) {
        Api<GamesOptions> api = API;
        Preconditions.checkState(googleApiClient.hasApi(api), "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean zHasConnectedApi = googleApiClient.hasConnectedApi(api);
        if (z2 && !zHasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Games.API and is not connected to Games. Use GoogleApiClient.hasConnectedApi(Games.API) to guard this call.");
        }
        if (zHasConnectedApi) {
            return (com.google.android.gms.games.internal.zze) googleApiClient.getClient(CLIENT_KEY);
        }
        return null;
    }

    public static AchievementsClient getAchievementsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(context, zza(googleSignInAccount));
    }

    public static EventsClient getEventsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(context, zza(googleSignInAccount));
    }

    public static GamesClient getGamesClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(context, zza(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(context, zza(googleSignInAccount));
    }

    public static InvitationsClient getInvitationsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(context, zza(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(context, zza(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(context, zza(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(context, zza(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(context, zza(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(context, zza(googleSignInAccount));
    }

    public static SnapshotsClient getSnapshotsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(context, zza(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(context, zza(googleSignInAccount));
    }

    public static VideosClient getVideosClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(context, zza(googleSignInAccount));
    }

    public static com.google.android.gms.games.internal.zze zza(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true);
    }

    public static com.google.android.gms.games.internal.zze zza(GoogleApiClient googleApiClient, boolean z2) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        return zzb(googleApiClient, z2);
    }
}

package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@RetainForClient
@SafeParcelable.Class(creator = "PlayerEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Parcelable.Creator<PlayerEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getName", id = Decal.C4)
    private final String name;

    @SafeParcelable.Field(getter = "getIconImageUrl", id = 8)
    private final String zzac;

    @SafeParcelable.Field(getter = "getHiResImageUrl", id = 9)
    private final String zzad;

    @SafeParcelable.Field(getter = "getPlayerId", id = 1)
    private String zzby;

    @SafeParcelable.Field(getter = "getRetrievedTimestamp", id = 5)
    private final long zzbz;

    @SafeParcelable.Field(getter = "isInCircles", id = 6)
    private final int zzca;

    @SafeParcelable.Field(getter = "getLastPlayedWithTimestamp", id = 7)
    private final long zzcb;

    @SafeParcelable.Field(getter = "getTitle", id = 14)
    private final String zzcc;

    @SafeParcelable.Field(getter = "getMostRecentGameInfo", id = 15)
    private final com.google.android.gms.games.internal.player.zzb zzcd;

    @SafeParcelable.Field(getter = "getLevelInfo", id = 16)
    private final PlayerLevelInfo zzce;

    @SafeParcelable.Field(getter = "isProfileVisible", id = 18)
    private final boolean zzcf;

    @SafeParcelable.Field(getter = "hasDebugAccess", id = 19)
    private final boolean zzcg;

    @SafeParcelable.Field(getter = "getGamerTag", id = 20)
    private final String zzch;

    @SafeParcelable.Field(getter = "getBannerImageLandscapeUri", id = Decal.U4)
    private final Uri zzci;

    @SafeParcelable.Field(getter = "getBannerImageLandscapeUrl", id = Decal.V4)
    private final String zzcj;

    @SafeParcelable.Field(getter = "getBannerImagePortraitUri", id = Decal.SIZE)
    private final Uri zzck;

    @SafeParcelable.Field(getter = "getBannerImagePortraitUrl", id = 25)
    private final String zzcl;

    @SafeParcelable.Field(getter = "getGamerFriendStatus", id = 26)
    private final int zzcm;

    @SafeParcelable.Field(getter = "getGamerFriendUpdateTimestamp", id = 27)
    private final long zzcn;

    @SafeParcelable.Field(getter = "isMuted", id = 28)
    private final boolean zzco;

    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private String zzn;

    @SafeParcelable.Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;

    @SafeParcelable.Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;

    static final class zza extends zzap {
        zza() {
        }

        @Override // com.google.android.gms.games.zzap, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
        public final PlayerEntity createFromParcel(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(DowngradeableSafeParcel.getUnparcelClientVersion()) || DowngradeableSafeParcel.canUnparcelSafely(PlayerEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            return new PlayerEntity(string, string2, string3 == null ? null : Uri.parse(string3), string4 == null ? null : Uri.parse(string4), parcel.readLong(), -1, -1L, null, null, null, null, null, true, false, parcel.readString(), parcel.readString(), null, null, null, null, -1, -1L, false);
        }
    }

    public PlayerEntity(Player player) {
        this(player, true);
    }

    static int zza(Player player) {
        return Objects.hashCode(player.getPlayerId(), player.getDisplayName(), Boolean.valueOf(player.zzh()), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo(), player.zzg(), player.getName(), player.getBannerImageLandscapeUri(), player.getBannerImagePortraitUri(), Integer.valueOf(player.zzl()), Long.valueOf(player.zzm()), Boolean.valueOf(player.isMuted()));
    }

    static String zzb(Player player) {
        return Objects.toStringHelper(player).add("PlayerId", player.getPlayerId()).add("DisplayName", player.getDisplayName()).add("HasDebugAccess", Boolean.valueOf(player.zzh())).add("IconImageUri", player.getIconImageUri()).add("IconImageUrl", player.getIconImageUrl()).add("HiResImageUri", player.getHiResImageUri()).add("HiResImageUrl", player.getHiResImageUrl()).add("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).add("Title", player.getTitle()).add("LevelInfo", player.getLevelInfo()).add("GamerTag", player.zzg()).add("Name", player.getName()).add("BannerImageLandscapeUri", player.getBannerImageLandscapeUri()).add("BannerImageLandscapeUrl", player.getBannerImageLandscapeUrl()).add("BannerImagePortraitUri", player.getBannerImagePortraitUri()).add("BannerImagePortraitUrl", player.getBannerImagePortraitUrl()).add("GamerFriendStatus", Integer.valueOf(player.zzl())).add("GamerFriendUpdateTimestamp", Long.valueOf(player.zzm())).add("IsMuted", Boolean.valueOf(player.isMuted())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Player freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getBannerImageLandscapeUri() {
        return this.zzci;
    }

    @Override // com.google.android.gms.games.Player
    public final String getBannerImageLandscapeUrl() {
        return this.zzcj;
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getBannerImagePortraitUri() {
        return this.zzck;
    }

    @Override // com.google.android.gms.games.Player
    public final String getBannerImagePortraitUrl() {
        return this.zzcl;
    }

    @Override // com.google.android.gms.games.Player
    public final String getDisplayName() {
        return this.zzn;
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getHiResImageUri() {
        return this.zzs;
    }

    @Override // com.google.android.gms.games.Player
    public final String getHiResImageUrl() {
        return this.zzad;
    }

    @Override // com.google.android.gms.games.Player
    public final Uri getIconImageUri() {
        return this.zzr;
    }

    @Override // com.google.android.gms.games.Player
    public final String getIconImageUrl() {
        return this.zzac;
    }

    @Override // com.google.android.gms.games.Player
    public final long getLastPlayedWithTimestamp() {
        return this.zzcb;
    }

    @Override // com.google.android.gms.games.Player
    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }

    @Override // com.google.android.gms.games.Player
    public final String getName() {
        return this.name;
    }

    @Override // com.google.android.gms.games.Player
    public final String getPlayerId() {
        return this.zzby;
    }

    @Override // com.google.android.gms.games.Player
    public final long getRetrievedTimestamp() {
        return this.zzbz;
    }

    @Override // com.google.android.gms.games.Player
    public final String getTitle() {
        return this.zzcc;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean isMuted() {
        return this.zzco;
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (shouldDowngrade()) {
            parcel.writeString(this.zzby);
            parcel.writeString(this.zzn);
            Uri uri = this.zzr;
            parcel.writeString(uri == null ? null : uri.toString());
            Uri uri2 = this.zzs;
            parcel.writeString(uri2 != null ? uri2.toString() : null);
            parcel.writeLong(this.zzbz);
            return;
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getPlayerId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIconImageUri(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getHiResImageUri(), i2, false);
        SafeParcelWriter.writeLong(parcel, 5, getRetrievedTimestamp());
        SafeParcelWriter.writeInt(parcel, 6, this.zzca);
        SafeParcelWriter.writeLong(parcel, 7, getLastPlayedWithTimestamp());
        SafeParcelWriter.writeString(parcel, 8, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 9, getHiResImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 14, getTitle(), false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzcd, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 16, getLevelInfo(), i2, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcf);
        SafeParcelWriter.writeBoolean(parcel, 19, this.zzcg);
        SafeParcelWriter.writeString(parcel, 20, this.zzch, false);
        SafeParcelWriter.writeString(parcel, 21, this.name, false);
        SafeParcelWriter.writeParcelable(parcel, 22, getBannerImageLandscapeUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 23, getBannerImageLandscapeUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 24, getBannerImagePortraitUri(), i2, false);
        SafeParcelWriter.writeString(parcel, 25, getBannerImagePortraitUrl(), false);
        SafeParcelWriter.writeInt(parcel, 26, this.zzcm);
        SafeParcelWriter.writeLong(parcel, 27, this.zzcn);
        SafeParcelWriter.writeBoolean(parcel, 28, this.zzco);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.Player
    public final String zzg() {
        return this.zzch;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean zzh() {
        return this.zzcg;
    }

    @Override // com.google.android.gms.games.Player
    public final int zzi() {
        return this.zzca;
    }

    @Override // com.google.android.gms.games.Player
    public final boolean zzj() {
        return this.zzcf;
    }

    @Override // com.google.android.gms.games.Player
    public final com.google.android.gms.games.internal.player.zza zzk() {
        return this.zzcd;
    }

    @Override // com.google.android.gms.games.Player
    public final int zzl() {
        return this.zzcm;
    }

    @Override // com.google.android.gms.games.Player
    public final long zzm() {
        return this.zzcn;
    }

    private PlayerEntity(Player player, boolean z2) {
        this.zzby = player.getPlayerId();
        this.zzn = player.getDisplayName();
        this.zzr = player.getIconImageUri();
        this.zzac = player.getIconImageUrl();
        this.zzs = player.getHiResImageUri();
        this.zzad = player.getHiResImageUrl();
        long retrievedTimestamp = player.getRetrievedTimestamp();
        this.zzbz = retrievedTimestamp;
        this.zzca = player.zzi();
        this.zzcb = player.getLastPlayedWithTimestamp();
        this.zzcc = player.getTitle();
        this.zzcf = player.zzj();
        com.google.android.gms.games.internal.player.zza zzaVarZzk = player.zzk();
        this.zzcd = zzaVarZzk == null ? null : new com.google.android.gms.games.internal.player.zzb(zzaVarZzk);
        this.zzce = player.getLevelInfo();
        this.zzcg = player.zzh();
        this.zzch = player.zzg();
        this.name = player.getName();
        this.zzci = player.getBannerImageLandscapeUri();
        this.zzcj = player.getBannerImageLandscapeUrl();
        this.zzck = player.getBannerImagePortraitUri();
        this.zzcl = player.getBannerImagePortraitUrl();
        this.zzcm = player.zzl();
        this.zzcn = player.zzm();
        this.zzco = player.isMuted();
        Asserts.checkNotNull(this.zzby);
        Asserts.checkNotNull(this.zzn);
        Asserts.checkState(retrievedTimestamp > 0);
    }

    static boolean zza(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return Objects.equal(player2.getPlayerId(), player.getPlayerId()) && Objects.equal(player2.getDisplayName(), player.getDisplayName()) && Objects.equal(Boolean.valueOf(player2.zzh()), Boolean.valueOf(player.zzh())) && Objects.equal(player2.getIconImageUri(), player.getIconImageUri()) && Objects.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && Objects.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && Objects.equal(player2.getTitle(), player.getTitle()) && Objects.equal(player2.getLevelInfo(), player.getLevelInfo()) && Objects.equal(player2.zzg(), player.zzg()) && Objects.equal(player2.getName(), player.getName()) && Objects.equal(player2.getBannerImageLandscapeUri(), player.getBannerImageLandscapeUri()) && Objects.equal(player2.getBannerImagePortraitUri(), player.getBannerImagePortraitUri()) && Objects.equal(Integer.valueOf(player2.zzl()), Integer.valueOf(player.zzl())) && Objects.equal(Long.valueOf(player2.zzm()), Long.valueOf(player.zzm())) && Objects.equal(Boolean.valueOf(player2.isMuted()), Boolean.valueOf(player.isMuted()));
    }

    @Override // com.google.android.gms.games.Player
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Player
    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzcc, charArrayBuffer);
    }

    @SafeParcelable.Constructor
    PlayerEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) Uri uri, @SafeParcelable.Param(id = 4) Uri uri2, @SafeParcelable.Param(id = 5) long j2, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) long j3, @SafeParcelable.Param(id = 8) String str3, @SafeParcelable.Param(id = 9) String str4, @SafeParcelable.Param(id = 14) String str5, @SafeParcelable.Param(id = 15) com.google.android.gms.games.internal.player.zzb zzbVar, @SafeParcelable.Param(id = 16) PlayerLevelInfo playerLevelInfo, @SafeParcelable.Param(id = 18) boolean z2, @SafeParcelable.Param(id = 19) boolean z3, @SafeParcelable.Param(id = 20) String str6, @SafeParcelable.Param(id = Decal.C4) String str7, @SafeParcelable.Param(id = Decal.U4) Uri uri3, @SafeParcelable.Param(id = Decal.V4) String str8, @SafeParcelable.Param(id = Decal.SIZE) Uri uri4, @SafeParcelable.Param(id = 25) String str9, @SafeParcelable.Param(id = 26) int i3, @SafeParcelable.Param(id = 27) long j4, @SafeParcelable.Param(id = 28) boolean z4) {
        this.zzby = str;
        this.zzn = str2;
        this.zzr = uri;
        this.zzac = str3;
        this.zzs = uri2;
        this.zzad = str4;
        this.zzbz = j2;
        this.zzca = i2;
        this.zzcb = j3;
        this.zzcc = str5;
        this.zzcf = z2;
        this.zzcd = zzbVar;
        this.zzce = playerLevelInfo;
        this.zzcg = z3;
        this.zzch = str6;
        this.name = str7;
        this.zzci = uri3;
        this.zzcj = str8;
        this.zzck = uri4;
        this.zzcl = str9;
        this.zzcm = i3;
        this.zzcn = j4;
        this.zzco = z4;
    }
}

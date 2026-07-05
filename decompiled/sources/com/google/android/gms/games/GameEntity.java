package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@RetainForClient
@SafeParcelable.Class(creator = "GameEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Parcelable.Creator<GameEntity> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getDescription", id = 5)
    private final String description;

    @SafeParcelable.Field(getter = "isRealTimeMultiplayerEnabled", id = 16)
    private final boolean zzaa;

    @SafeParcelable.Field(getter = "isTurnBasedMultiplayerEnabled", id = 17)
    private final boolean zzab;

    @SafeParcelable.Field(getter = "getIconImageUrl", id = 18)
    private final String zzac;

    @SafeParcelable.Field(getter = "getHiResImageUrl", id = 19)
    private final String zzad;

    @SafeParcelable.Field(getter = "getFeaturedImageUrl", id = 20)
    private final String zzae;

    @SafeParcelable.Field(getter = "isMuted", id = Decal.C4)
    private final boolean zzaf;

    @SafeParcelable.Field(getter = "isIdentitySharingConfirmed", id = Decal.U4)
    private final boolean zzag;

    @SafeParcelable.Field(getter = "areSnapshotsEnabled", id = Decal.V4)
    private final boolean zzah;

    @SafeParcelable.Field(getter = "getThemeColor", id = Decal.SIZE)
    private final String zzai;

    @SafeParcelable.Field(getter = "hasGamepadSupport", id = 25)
    private final boolean zzaj;

    @SafeParcelable.Field(getter = "getApplicationId", id = 1)
    private final String zzm;

    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    private final String zzn;

    @SafeParcelable.Field(getter = "getPrimaryCategory", id = 3)
    private final String zzo;

    @SafeParcelable.Field(getter = "getSecondaryCategory", id = 4)
    private final String zzp;

    @SafeParcelable.Field(getter = "getDeveloperName", id = 6)
    private final String zzq;

    @SafeParcelable.Field(getter = "getIconImageUri", id = 7)
    private final Uri zzr;

    @SafeParcelable.Field(getter = "getHiResImageUri", id = 8)
    private final Uri zzs;

    @SafeParcelable.Field(getter = "getFeaturedImageUri", id = 9)
    private final Uri zzt;

    @SafeParcelable.Field(getter = "isPlayEnabledGame", id = 10)
    private final boolean zzu;

    @SafeParcelable.Field(getter = "isInstanceInstalled", id = 11)
    private final boolean zzv;

    @SafeParcelable.Field(getter = "getInstancePackageName", id = 12)
    private final String zzw;

    @SafeParcelable.Field(getter = "getGameplayAclStatus", id = 13)
    private final int zzx;

    @SafeParcelable.Field(getter = "getAchievementTotalCount", id = 14)
    private final int zzy;

    @SafeParcelable.Field(getter = "getLeaderboardCount", id = 15)
    private final int zzz;

    static final class zza extends zzh {
        zza() {
        }

        @Override // com.google.android.gms.games.zzh, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: zzb */
        public final GameEntity createFromParcel(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(DowngradeableSafeParcel.getUnparcelClientVersion()) || DowngradeableSafeParcel.canUnparcelSafely(GameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            String string6 = parcel.readString();
            String string7 = parcel.readString();
            Uri uri = string7 == null ? null : Uri.parse(string7);
            String string8 = parcel.readString();
            Uri uri2 = string8 == null ? null : Uri.parse(string8);
            String string9 = parcel.readString();
            return new GameEntity(string, string2, string3, string4, string5, string6, uri, uri2, string9 == null ? null : Uri.parse(string9), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null, false);
        }
    }

    public GameEntity(Game game) {
        this.zzm = game.getApplicationId();
        this.zzo = game.getPrimaryCategory();
        this.zzp = game.getSecondaryCategory();
        this.description = game.getDescription();
        this.zzq = game.getDeveloperName();
        this.zzn = game.getDisplayName();
        this.zzr = game.getIconImageUri();
        this.zzac = game.getIconImageUrl();
        this.zzs = game.getHiResImageUri();
        this.zzad = game.getHiResImageUrl();
        this.zzt = game.getFeaturedImageUri();
        this.zzae = game.getFeaturedImageUrl();
        this.zzu = game.zza();
        this.zzv = game.zzc();
        this.zzw = game.zzd();
        this.zzx = 1;
        this.zzy = game.getAchievementTotalCount();
        this.zzz = game.getLeaderboardCount();
        this.zzaa = game.isRealTimeMultiplayerEnabled();
        this.zzab = game.isTurnBasedMultiplayerEnabled();
        this.zzaf = game.isMuted();
        this.zzag = game.zzb();
        this.zzah = game.areSnapshotsEnabled();
        this.zzai = game.getThemeColor();
        this.zzaj = game.hasGamepadSupport();
    }

    static int zza(Game game) {
        return Objects.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.zza()), Boolean.valueOf(game.zzc()), game.zzd(), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isMuted()), Boolean.valueOf(game.zzb()), Boolean.valueOf(game.areSnapshotsEnabled()), game.getThemeColor(), Boolean.valueOf(game.hasGamepadSupport()));
    }

    static String zzb(Game game) {
        return Objects.toStringHelper(game).add("ApplicationId", game.getApplicationId()).add("DisplayName", game.getDisplayName()).add("PrimaryCategory", game.getPrimaryCategory()).add("SecondaryCategory", game.getSecondaryCategory()).add("Description", game.getDescription()).add("DeveloperName", game.getDeveloperName()).add("IconImageUri", game.getIconImageUri()).add("IconImageUrl", game.getIconImageUrl()).add("HiResImageUri", game.getHiResImageUri()).add("HiResImageUrl", game.getHiResImageUrl()).add("FeaturedImageUri", game.getFeaturedImageUri()).add("FeaturedImageUrl", game.getFeaturedImageUrl()).add("PlayEnabledGame", Boolean.valueOf(game.zza())).add("InstanceInstalled", Boolean.valueOf(game.zzc())).add("InstancePackageName", game.zzd()).add("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).add("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).add("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).add("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).add("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).add("ThemeColor", game.getThemeColor()).add("HasGamepadSupport", Boolean.valueOf(game.hasGamepadSupport())).toString();
    }

    @Override // com.google.android.gms.games.Game
    public final boolean areSnapshotsEnabled() {
        return this.zzah;
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public final Game freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.Game
    public final int getAchievementTotalCount() {
        return this.zzy;
    }

    @Override // com.google.android.gms.games.Game
    public final String getApplicationId() {
        return this.zzm;
    }

    @Override // com.google.android.gms.games.Game
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.Game
    public final String getDeveloperName() {
        return this.zzq;
    }

    @Override // com.google.android.gms.games.Game
    public final String getDisplayName() {
        return this.zzn;
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getFeaturedImageUri() {
        return this.zzt;
    }

    @Override // com.google.android.gms.games.Game
    public final String getFeaturedImageUrl() {
        return this.zzae;
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getHiResImageUri() {
        return this.zzs;
    }

    @Override // com.google.android.gms.games.Game
    public final String getHiResImageUrl() {
        return this.zzad;
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getIconImageUri() {
        return this.zzr;
    }

    @Override // com.google.android.gms.games.Game
    public final String getIconImageUrl() {
        return this.zzac;
    }

    @Override // com.google.android.gms.games.Game
    public final int getLeaderboardCount() {
        return this.zzz;
    }

    @Override // com.google.android.gms.games.Game
    public final String getPrimaryCategory() {
        return this.zzo;
    }

    @Override // com.google.android.gms.games.Game
    public final String getSecondaryCategory() {
        return this.zzp;
    }

    @Override // com.google.android.gms.games.Game
    public final String getThemeColor() {
        return this.zzai;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean hasGamepadSupport() {
        return this.zzaj;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isMuted() {
        return this.zzaf;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isRealTimeMultiplayerEnabled() {
        return this.zzaa;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isTurnBasedMultiplayerEnabled() {
        return this.zzab;
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (shouldDowngrade()) {
            parcel.writeString(this.zzm);
            parcel.writeString(this.zzn);
            parcel.writeString(this.zzo);
            parcel.writeString(this.zzp);
            parcel.writeString(this.description);
            parcel.writeString(this.zzq);
            Uri uri = this.zzr;
            parcel.writeString(uri == null ? null : uri.toString());
            Uri uri2 = this.zzs;
            parcel.writeString(uri2 == null ? null : uri2.toString());
            Uri uri3 = this.zzt;
            parcel.writeString(uri3 != null ? uri3.toString() : null);
            parcel.writeInt(this.zzu ? 1 : 0);
            parcel.writeInt(this.zzv ? 1 : 0);
            parcel.writeString(this.zzw);
            parcel.writeInt(this.zzx);
            parcel.writeInt(this.zzy);
            parcel.writeInt(this.zzz);
            return;
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getApplicationId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getPrimaryCategory(), false);
        SafeParcelWriter.writeString(parcel, 4, getSecondaryCategory(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeveloperName(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getIconImageUri(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getHiResImageUri(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 9, getFeaturedImageUri(), i2, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzu);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzv);
        SafeParcelWriter.writeString(parcel, 12, this.zzw, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzx);
        SafeParcelWriter.writeInt(parcel, 14, getAchievementTotalCount());
        SafeParcelWriter.writeInt(parcel, 15, getLeaderboardCount());
        SafeParcelWriter.writeBoolean(parcel, 16, isRealTimeMultiplayerEnabled());
        SafeParcelWriter.writeBoolean(parcel, 17, isTurnBasedMultiplayerEnabled());
        SafeParcelWriter.writeString(parcel, 18, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 19, getHiResImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 20, getFeaturedImageUrl(), false);
        SafeParcelWriter.writeBoolean(parcel, 21, this.zzaf);
        SafeParcelWriter.writeBoolean(parcel, 22, this.zzag);
        SafeParcelWriter.writeBoolean(parcel, 23, areSnapshotsEnabled());
        SafeParcelWriter.writeString(parcel, 24, getThemeColor(), false);
        SafeParcelWriter.writeBoolean(parcel, 25, hasGamepadSupport());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zzc() {
        return this.zzv;
    }

    @Override // com.google.android.gms.games.Game
    public final String zzd() {
        return this.zzw;
    }

    @SafeParcelable.Constructor
    GameEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) String str4, @SafeParcelable.Param(id = 5) String str5, @SafeParcelable.Param(id = 6) String str6, @SafeParcelable.Param(id = 7) Uri uri, @SafeParcelable.Param(id = 8) Uri uri2, @SafeParcelable.Param(id = 9) Uri uri3, @SafeParcelable.Param(id = 10) boolean z2, @SafeParcelable.Param(id = 11) boolean z3, @SafeParcelable.Param(id = 12) String str7, @SafeParcelable.Param(id = 13) int i2, @SafeParcelable.Param(id = 14) int i3, @SafeParcelable.Param(id = 15) int i4, @SafeParcelable.Param(id = 16) boolean z4, @SafeParcelable.Param(id = 17) boolean z5, @SafeParcelable.Param(id = 18) String str8, @SafeParcelable.Param(id = 19) String str9, @SafeParcelable.Param(id = 20) String str10, @SafeParcelable.Param(id = Decal.C4) boolean z6, @SafeParcelable.Param(id = Decal.U4) boolean z7, @SafeParcelable.Param(id = Decal.V4) boolean z8, @SafeParcelable.Param(id = Decal.SIZE) String str11, @SafeParcelable.Param(id = 25) boolean z9) {
        this.zzm = str;
        this.zzn = str2;
        this.zzo = str3;
        this.zzp = str4;
        this.description = str5;
        this.zzq = str6;
        this.zzr = uri;
        this.zzac = str8;
        this.zzs = uri2;
        this.zzad = str9;
        this.zzt = uri3;
        this.zzae = str10;
        this.zzu = z2;
        this.zzv = z3;
        this.zzw = str7;
        this.zzx = i2;
        this.zzy = i3;
        this.zzz = i4;
        this.zzaa = z4;
        this.zzab = z5;
        this.zzaf = z6;
        this.zzag = z7;
        this.zzah = z8;
        this.zzai = str11;
        this.zzaj = z9;
    }

    @Override // com.google.android.gms.games.Game
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Game
    public final void getDeveloperName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzq, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Game
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zza() {
        return this.zzu;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zzb() {
        return this.zzag;
    }

    static boolean zza(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        return Objects.equal(game2.getApplicationId(), game.getApplicationId()) && Objects.equal(game2.getDisplayName(), game.getDisplayName()) && Objects.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && Objects.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && Objects.equal(game2.getDescription(), game.getDescription()) && Objects.equal(game2.getDeveloperName(), game.getDeveloperName()) && Objects.equal(game2.getIconImageUri(), game.getIconImageUri()) && Objects.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && Objects.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && Objects.equal(Boolean.valueOf(game2.zza()), Boolean.valueOf(game.zza())) && Objects.equal(Boolean.valueOf(game2.zzc()), Boolean.valueOf(game.zzc())) && Objects.equal(game2.zzd(), game.zzd()) && Objects.equal(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && Objects.equal(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) && Objects.equal(Boolean.valueOf(game2.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled())) && Objects.equal(Boolean.valueOf(game2.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())) && Objects.equal(Boolean.valueOf(game2.isMuted()), Boolean.valueOf(game.isMuted())) && Objects.equal(Boolean.valueOf(game2.zzb()), Boolean.valueOf(game.zzb())) && Objects.equal(Boolean.valueOf(game2.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled())) && Objects.equal(game2.getThemeColor(), game.getThemeColor()) && Objects.equal(Boolean.valueOf(game2.hasGamepadSupport()), Boolean.valueOf(game.hasGamepadSupport()));
    }
}

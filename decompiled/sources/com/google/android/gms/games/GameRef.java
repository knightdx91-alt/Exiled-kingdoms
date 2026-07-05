package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class GameRef extends DataBufferRef implements Game {
    public GameRef(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // com.google.android.gms.games.Game
    public final boolean areSnapshotsEnabled() {
        return getInteger("snapshots_enabled") > 0;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return GameEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Game freeze() {
        return new GameEntity(this);
    }

    @Override // com.google.android.gms.games.Game
    public final int getAchievementTotalCount() {
        return getInteger("achievement_total_count");
    }

    @Override // com.google.android.gms.games.Game
    public final String getApplicationId() {
        return getString("external_game_id");
    }

    @Override // com.google.android.gms.games.Game
    public final String getDescription() {
        return getString("game_description");
    }

    @Override // com.google.android.gms.games.Game
    public final String getDeveloperName() {
        return getString("developer_name");
    }

    @Override // com.google.android.gms.games.Game
    public final String getDisplayName() {
        return getString("display_name");
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getFeaturedImageUri() {
        return parseUri("featured_image_uri");
    }

    @Override // com.google.android.gms.games.Game
    public final String getFeaturedImageUrl() {
        return getString("featured_image_url");
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getHiResImageUri() {
        return parseUri("game_hi_res_image_uri");
    }

    @Override // com.google.android.gms.games.Game
    public final String getHiResImageUrl() {
        return getString("game_hi_res_image_url");
    }

    @Override // com.google.android.gms.games.Game
    public final Uri getIconImageUri() {
        return parseUri("game_icon_image_uri");
    }

    @Override // com.google.android.gms.games.Game
    public final String getIconImageUrl() {
        return getString("game_icon_image_url");
    }

    @Override // com.google.android.gms.games.Game
    public final int getLeaderboardCount() {
        return getInteger("leaderboard_count");
    }

    @Override // com.google.android.gms.games.Game
    public final String getPrimaryCategory() {
        return getString("primary_category");
    }

    @Override // com.google.android.gms.games.Game
    public final String getSecondaryCategory() {
        return getString("secondary_category");
    }

    @Override // com.google.android.gms.games.Game
    public final String getThemeColor() {
        return getString("theme_color");
    }

    @Override // com.google.android.gms.games.Game
    public final boolean hasGamepadSupport() {
        return getInteger("gamepad_support") > 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return GameEntity.zza(this);
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isMuted() {
        return getBoolean("muted");
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isRealTimeMultiplayerEnabled() {
        return getInteger("real_time_support") > 0;
    }

    @Override // com.google.android.gms.games.Game
    public final boolean isTurnBasedMultiplayerEnabled() {
        return getInteger("turn_based_support") > 0;
    }

    public final String toString() {
        return GameEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((GameEntity) ((Game) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zza() {
        return getBoolean("play_enabled_game");
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zzb() {
        return getBoolean("identity_sharing_confirmed");
    }

    @Override // com.google.android.gms.games.Game
    public final boolean zzc() {
        return getInteger("installed") > 0;
    }

    @Override // com.google.android.gms.games.Game
    public final String zzd() {
        return getString("package_name");
    }

    @Override // com.google.android.gms.games.Game
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("game_description", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Game
    public final void getDeveloperName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("developer_name", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.Game
    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("display_name", charArrayBuffer);
    }
}

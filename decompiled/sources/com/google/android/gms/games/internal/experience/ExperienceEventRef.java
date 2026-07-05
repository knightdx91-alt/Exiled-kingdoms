package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.games.Game;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ExperienceEventRef extends DataBufferRef implements ExperienceEvent {
    @Override // android.os.Parcelable
    public final int describeContents() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ ExperienceEvent freeze() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final Game getGame() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final Uri getIconImageUri() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final String getIconImageUrl() {
        return getString("icon_url");
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final int getType() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        throw new NoSuchMethodError();
    }

    public final String toString() {
        throw new NoSuchMethodError();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final String zzbm() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final String zzbn() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final String zzbo() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final long zzbp() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final long zzbq() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final long zzbr() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.experience.ExperienceEvent
    public final int zzbs() {
        throw new NoSuchMethodError();
    }
}

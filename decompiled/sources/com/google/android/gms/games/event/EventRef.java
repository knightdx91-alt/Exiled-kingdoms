package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class EventRef extends DataBufferRef implements Event {
    EventRef(DataHolder dataHolder, int i2) {
        super(dataHolder, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return EventEntity.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Event freeze() {
        return new EventEntity(this);
    }

    @Override // com.google.android.gms.games.event.Event
    public final String getDescription() {
        return getString("description");
    }

    @Override // com.google.android.gms.games.event.Event
    public final String getEventId() {
        return getString("external_event_id");
    }

    @Override // com.google.android.gms.games.event.Event
    public final String getFormattedValue() {
        return getString("formatted_value");
    }

    @Override // com.google.android.gms.games.event.Event
    public final Uri getIconImageUri() {
        return parseUri("icon_image_uri");
    }

    @Override // com.google.android.gms.games.event.Event
    public final String getIconImageUrl() {
        return getString("icon_image_url");
    }

    @Override // com.google.android.gms.games.event.Event
    public final String getName() {
        return getString("name");
    }

    @Override // com.google.android.gms.games.event.Event
    public final Player getPlayer() {
        return new PlayerRef(this.mDataHolder, this.mDataRow);
    }

    @Override // com.google.android.gms.games.event.Event
    public final long getValue() {
        return getLong("value");
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return EventEntity.zza(this);
    }

    @Override // com.google.android.gms.games.event.Event
    public final boolean isVisible() {
        return getBoolean("visibility");
    }

    public final String toString() {
        return EventEntity.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((EventEntity) ((Event) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.event.Event
    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("description", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.event.Event
    public final void getFormattedValue(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("formatted_value", charArrayBuffer);
    }

    @Override // com.google.android.gms.games.event.Event
    public final void getName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("name", charArrayBuffer);
    }
}

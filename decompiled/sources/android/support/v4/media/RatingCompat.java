package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f443a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f444b;

    static class a implements Parcelable.Creator<RatingCompat> {
        @Override // android.os.Parcelable.Creator
        public final RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        @Override // android.os.Parcelable.Creator
        public final RatingCompat[] newArray(int i2) {
            return new RatingCompat[i2];
        }
    }

    RatingCompat(int i2, float f2) {
        this.f443a = i2;
        this.f444b = f2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.f443a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.f443a);
        sb.append(" rating=");
        float f2 = this.f444b;
        sb.append(f2 < 0.0f ? "unrated" : String.valueOf(f2));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f443a);
        parcel.writeFloat(this.f444b);
    }
}

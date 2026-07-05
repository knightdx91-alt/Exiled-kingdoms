package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f507b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f508c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f509d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f510e;

    static class a implements Parcelable.Creator<ParcelableVolumeInfo> {
        @Override // android.os.Parcelable.Creator
        public final ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            ParcelableVolumeInfo parcelableVolumeInfo = new ParcelableVolumeInfo();
            parcelableVolumeInfo.f506a = parcel.readInt();
            parcelableVolumeInfo.f508c = parcel.readInt();
            parcelableVolumeInfo.f509d = parcel.readInt();
            parcelableVolumeInfo.f510e = parcel.readInt();
            parcelableVolumeInfo.f507b = parcel.readInt();
            return parcelableVolumeInfo;
        }

        @Override // android.os.Parcelable.Creator
        public final ParcelableVolumeInfo[] newArray(int i2) {
            return new ParcelableVolumeInfo[i2];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f506a);
        parcel.writeInt(this.f508c);
        parcel.writeInt(this.f509d);
        parcel.writeInt(this.f510e);
        parcel.writeInt(this.f507b);
    }
}

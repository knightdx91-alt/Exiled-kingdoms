package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    FragmentState[] f146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    BackStackState[] f148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f149d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f150e;

    /* JADX INFO: compiled from: FragmentManager.java */
    static class a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        public final FragmentManagerState createFromParcel(Parcel parcel) {
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.f149d = -1;
            fragmentManagerState.f146a = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
            fragmentManagerState.f147b = parcel.createIntArray();
            fragmentManagerState.f148c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
            fragmentManagerState.f149d = parcel.readInt();
            fragmentManagerState.f150e = parcel.readInt();
            return fragmentManagerState;
        }

        @Override // android.os.Parcelable.Creator
        public final FragmentManagerState[] newArray(int i2) {
            return new FragmentManagerState[i2];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedArray(this.f146a, i2);
        parcel.writeIntArray(this.f147b);
        parcel.writeTypedArray(this.f148c, i2);
        parcel.writeInt(this.f149d);
        parcel.writeInt(this.f150e);
    }
}

package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final String f156f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final boolean f157g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f158h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final Bundle f159i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final boolean f160j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    Bundle f161k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    Fragment f162l;

    static class a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        public final FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    }

    FragmentState(Fragment fragment) {
        this.f151a = fragment.getClass().getName();
        this.f152b = fragment.mIndex;
        this.f153c = fragment.mFromLayout;
        this.f154d = fragment.mFragmentId;
        this.f155e = fragment.mContainerId;
        this.f156f = fragment.mTag;
        this.f157g = fragment.mRetainInstance;
        this.f158h = fragment.mDetached;
        this.f159i = fragment.mArguments;
        this.f160j = fragment.mHidden;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f151a);
        parcel.writeInt(this.f152b);
        parcel.writeInt(this.f153c ? 1 : 0);
        parcel.writeInt(this.f154d);
        parcel.writeInt(this.f155e);
        parcel.writeString(this.f156f);
        parcel.writeInt(this.f157g ? 1 : 0);
        parcel.writeInt(this.f158h ? 1 : 0);
        parcel.writeBundle(this.f159i);
        parcel.writeInt(this.f160j ? 1 : 0);
        parcel.writeBundle(this.f161k);
    }

    FragmentState(Parcel parcel) {
        this.f151a = parcel.readString();
        this.f152b = parcel.readInt();
        this.f153c = parcel.readInt() != 0;
        this.f154d = parcel.readInt();
        this.f155e = parcel.readInt();
        this.f156f = parcel.readString();
        this.f157g = parcel.readInt() != 0;
        this.f158h = parcel.readInt() != 0;
        this.f159i = parcel.readBundle();
        this.f160j = parcel.readInt() != 0;
        this.f161k = parcel.readBundle();
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.b;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int[] f109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final String f112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f114f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final CharSequence f115g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final int f116h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final CharSequence f117i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final ArrayList<String> f118j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final ArrayList<String> f119k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final boolean f120l;

    /* JADX INFO: compiled from: BackStackRecord.java */
    static class a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        public final BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    }

    public BackStackState(b bVar) {
        int size = bVar.f173b.size();
        this.f109a = new int[size * 6];
        if (!bVar.f180i) {
            throw new IllegalStateException("Not on back stack");
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            b.a aVar = bVar.f173b.get(i3);
            int[] iArr = this.f109a;
            int i4 = i2 + 1;
            iArr[i2] = aVar.f190a;
            int i5 = i2 + 2;
            Fragment fragment = aVar.f191b;
            iArr[i4] = fragment != null ? fragment.mIndex : -1;
            iArr[i5] = aVar.f192c;
            iArr[i2 + 3] = aVar.f193d;
            int i6 = i2 + 5;
            iArr[i2 + 4] = aVar.f194e;
            i2 += 6;
            iArr[i6] = aVar.f195f;
        }
        this.f110b = bVar.f178g;
        this.f111c = bVar.f179h;
        this.f112d = bVar.f181j;
        this.f113e = bVar.f183l;
        this.f114f = bVar.f184m;
        this.f115g = bVar.f185n;
        this.f116h = bVar.f186o;
        this.f117i = bVar.f187p;
        this.f118j = bVar.f188q;
        this.f119k = bVar.f189r;
        this.f120l = bVar.s;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f109a);
        parcel.writeInt(this.f110b);
        parcel.writeInt(this.f111c);
        parcel.writeString(this.f112d);
        parcel.writeInt(this.f113e);
        parcel.writeInt(this.f114f);
        TextUtils.writeToParcel(this.f115g, parcel, 0);
        parcel.writeInt(this.f116h);
        TextUtils.writeToParcel(this.f117i, parcel, 0);
        parcel.writeStringList(this.f118j);
        parcel.writeStringList(this.f119k);
        parcel.writeInt(this.f120l ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f109a = parcel.createIntArray();
        this.f110b = parcel.readInt();
        this.f111c = parcel.readInt();
        this.f112d = parcel.readString();
        this.f113e = parcel.readInt();
        this.f114f = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f115g = (CharSequence) creator.createFromParcel(parcel);
        this.f116h = parcel.readInt();
        this.f117i = (CharSequence) creator.createFromParcel(parcel);
        this.f118j = parcel.createStringArrayList();
        this.f119k = parcel.createStringArrayList();
        this.f120l = parcel.readInt() != 0;
    }
}

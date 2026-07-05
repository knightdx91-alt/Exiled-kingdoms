package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DrawerLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<DrawerLayout$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f618g;

    static class a implements Parcelable.ClassLoaderCreator<DrawerLayout$SavedState> {
        @Override // android.os.Parcelable.ClassLoaderCreator
        public final DrawerLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new DrawerLayout$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i2) {
            return new DrawerLayout$SavedState[i2];
        }

        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new DrawerLayout$SavedState(parcel, null);
        }
    }

    public DrawerLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f614c = 0;
        this.f614c = parcel.readInt();
        this.f615d = parcel.readInt();
        this.f616e = parcel.readInt();
        this.f617f = parcel.readInt();
        this.f618g = parcel.readInt();
    }

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f614c);
        parcel.writeInt(this.f615d);
        parcel.writeInt(this.f616e);
        parcel.writeInt(this.f617f);
        parcel.writeInt(this.f618g);
    }
}

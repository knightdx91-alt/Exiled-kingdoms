package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ViewPager$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<ViewPager$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f592c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Parcelable f593d;

    static class a implements Parcelable.ClassLoaderCreator<ViewPager$SavedState> {
        @Override // android.os.Parcelable.ClassLoaderCreator
        public final ViewPager$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ViewPager$SavedState(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i2) {
            return new ViewPager$SavedState[i2];
        }

        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new ViewPager$SavedState(parcel, null);
        }
    }

    ViewPager$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
        this.f592c = parcel.readInt();
        this.f593d = parcel.readParcelable(classLoader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentPager.SavedState{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" position=");
        return a.a.p(sb, this.f592c, "}");
    }

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f592c);
        parcel.writeParcelable(this.f593d, i2);
    }
}

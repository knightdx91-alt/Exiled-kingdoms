package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class SlidingPaneLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SlidingPaneLayout$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f644c;

    static class a implements Parcelable.ClassLoaderCreator<SlidingPaneLayout$SavedState> {
        @Override // android.os.Parcelable.ClassLoaderCreator
        public final SlidingPaneLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SlidingPaneLayout$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i2) {
            return new SlidingPaneLayout$SavedState[i2];
        }

        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new SlidingPaneLayout$SavedState(parcel);
        }
    }

    SlidingPaneLayout$SavedState(Parcel parcel) {
        super(parcel, null);
        this.f644c = parcel.readInt() != 0;
    }

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f644c ? 1 : 0);
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class FragmentTabHost$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<FragmentTabHost$SavedState> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f163a;

    static class a implements Parcelable.Creator<FragmentTabHost$SavedState> {
        @Override // android.os.Parcelable.Creator
        public final FragmentTabHost$SavedState createFromParcel(Parcel parcel) {
            FragmentTabHost$SavedState fragmentTabHost$SavedState = new FragmentTabHost$SavedState(parcel);
            fragmentTabHost$SavedState.f163a = parcel.readString();
            return fragmentTabHost$SavedState;
        }

        @Override // android.os.Parcelable.Creator
        public final FragmentTabHost$SavedState[] newArray(int i2) {
            return new FragmentTabHost$SavedState[i2];
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentTabHost.SavedState{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" curTab=");
        return a.a.m(this.f163a, "}", sb);
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.f163a);
    }
}

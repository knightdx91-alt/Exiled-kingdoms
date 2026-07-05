package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class AbsSavedState implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Parcelable f591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbsSavedState f590b = new AbsSavedState() { // from class: android.support.v4.view.AbsSavedState.1
    };
    public static final Parcelable.Creator<AbsSavedState> CREATOR = new a();

    /* synthetic */ AbsSavedState(int i2) {
        this();
    }

    public final Parcelable a() {
        return this.f591a;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f591a, i2);
    }

    private AbsSavedState() {
        this.f591a = null;
    }

    static class a implements Parcelable.ClassLoaderCreator<AbsSavedState> {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            if (parcel.readParcelable(null) == null) {
                return AbsSavedState.f590b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i2) {
            return new AbsSavedState[i2];
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public final AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return AbsSavedState.f590b;
            }
            throw new IllegalStateException("superState must be null");
        }
    }

    protected AbsSavedState(Parcelable parcelable) {
        if (parcelable != null) {
            this.f591a = parcelable == f590b ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    protected AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f591a = parcelable == null ? f590b : parcelable;
    }
}

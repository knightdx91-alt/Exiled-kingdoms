package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f511a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f512b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f513c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final float f514d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final long f515e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f516f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final CharSequence f517g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final long f518h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ArrayList f519i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final long f520j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final Bundle f521k;

    static class a implements Parcelable.Creator<PlaybackStateCompat> {
        @Override // android.os.Parcelable.Creator
        public final PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final PlaybackStateCompat[] newArray(int i2) {
            return new PlaybackStateCompat[i2];
        }
    }

    PlaybackStateCompat(int i2, long j2, long j3, float f2, long j4, CharSequence charSequence, long j5, ArrayList arrayList, long j6, Bundle bundle) {
        this.f511a = i2;
        this.f512b = j2;
        this.f513c = j3;
        this.f514d = f2;
        this.f515e = j4;
        this.f516f = 0;
        this.f517g = charSequence;
        this.f518h = j5;
        this.f519i = new ArrayList(arrayList);
        this.f520j = j6;
        this.f521k = bundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "PlaybackState {state=" + this.f511a + ", position=" + this.f512b + ", buffered position=" + this.f513c + ", speed=" + this.f514d + ", updated=" + this.f518h + ", actions=" + this.f515e + ", error code=" + this.f516f + ", error message=" + this.f517g + ", custom actions=" + this.f519i + ", active item id=" + this.f520j + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f511a);
        parcel.writeLong(this.f512b);
        parcel.writeFloat(this.f514d);
        parcel.writeLong(this.f518h);
        parcel.writeLong(this.f513c);
        parcel.writeLong(this.f515e);
        TextUtils.writeToParcel(this.f517g, parcel, i2);
        parcel.writeTypedList(this.f519i);
        parcel.writeLong(this.f520j);
        parcel.writeBundle(this.f521k);
        parcel.writeInt(this.f516f);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f522a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final CharSequence f523b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f524c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Bundle f525d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Object f526e;

        static class a implements Parcelable.Creator<CustomAction> {
            @Override // android.os.Parcelable.Creator
            public final CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final CustomAction[] newArray(int i2) {
                return new CustomAction[i2];
            }
        }

        CustomAction(String str, CharSequence charSequence, int i2, Bundle bundle) {
            this.f522a = str;
            this.f523b = charSequence;
            this.f524c = i2;
            this.f525d = bundle;
        }

        public static CustomAction a(Object obj) {
            if (obj == null) {
                return null;
            }
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            CustomAction customAction2 = new CustomAction(customAction.getAction(), customAction.getName(), customAction.getIcon(), customAction.getExtras());
            customAction2.f526e = obj;
            return customAction2;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.f523b) + ", mIcon=" + this.f524c + ", mExtras=" + this.f525d;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f522a);
            TextUtils.writeToParcel(this.f523b, parcel, i2);
            parcel.writeInt(this.f524c);
            parcel.writeBundle(this.f525d);
        }

        CustomAction(Parcel parcel) {
            this.f522a = parcel.readString();
            this.f523b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f524c = parcel.readInt();
            this.f525d = parcel.readBundle();
        }
    }

    PlaybackStateCompat(Parcel parcel) {
        this.f511a = parcel.readInt();
        this.f512b = parcel.readLong();
        this.f514d = parcel.readFloat();
        this.f518h = parcel.readLong();
        this.f513c = parcel.readLong();
        this.f515e = parcel.readLong();
        this.f517g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f519i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f520j = parcel.readLong();
        this.f521k = parcel.readBundle();
        this.f516f = parcel.readInt();
    }
}

package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MediaSessionCompat {

    static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ResultReceiver f503a;

        static class a implements Parcelable.Creator<ResultReceiverWrapper> {
            @Override // android.os.Parcelable.Creator
            public final ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final ResultReceiverWrapper[] newArray(int i2) {
                return new ResultReceiverWrapper[i2];
            }
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.f503a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            this.f503a.writeToParcel(parcel, i2);
        }
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Parcelable f504a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f505b;

        static class a implements Parcelable.Creator<Token> {
            @Override // android.os.Parcelable.Creator
            public final Token createFromParcel(Parcel parcel) {
                return new Token(parcel.readParcelable(null), null);
            }

            @Override // android.os.Parcelable.Creator
            public final Token[] newArray(int i2) {
                return new Token[i2];
            }
        }

        Token(Parcelable parcelable, b bVar) {
            this.f504a = parcelable;
            this.f505b = bVar;
        }

        public static Token a(MediaSession.Token token, b bVar) {
            if (token != null) {
                return new Token(token, bVar);
            }
            return null;
        }

        public final b b() {
            return this.f505b;
        }

        public final Object c() {
            return this.f504a;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Parcelable parcelable = this.f504a;
            if (parcelable == null) {
                return token.f504a == null;
            }
            Parcelable parcelable2 = token.f504a;
            if (parcelable2 == null) {
                return false;
            }
            return parcelable.equals(parcelable2);
        }

        public final int hashCode() {
            Parcelable parcelable = this.f504a;
            if (parcelable == null) {
                return 0;
            }
            return parcelable.hashCode();
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeParcelable(this.f504a, i2);
        }
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MediaDescriptionCompat f500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f501b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Object f502c;

        static class a implements Parcelable.Creator<QueueItem> {
            @Override // android.os.Parcelable.Creator
            public final QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final QueueItem[] newArray(int i2) {
                return new QueueItem[i2];
            }
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j2) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (j2 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.f500a = mediaDescriptionCompat;
            this.f501b = j2;
            this.f502c = obj;
        }

        public static void a(List list) {
            QueueItem queueItem;
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (obj != null) {
                        MediaSession.QueueItem queueItem2 = (MediaSession.QueueItem) obj;
                        queueItem = new QueueItem(obj, MediaDescriptionCompat.a(queueItem2.getDescription()), queueItem2.getQueueId());
                    } else {
                        queueItem = null;
                    }
                    arrayList.add(queueItem);
                }
            }
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "MediaSession.QueueItem {Description=" + this.f500a + ", Id=" + this.f501b + " }";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            this.f500a.writeToParcel(parcel, i2);
            parcel.writeLong(this.f501b);
        }

        QueueItem(Parcel parcel) {
            this.f500a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f501b = parcel.readLong();
        }
    }
}

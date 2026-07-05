package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CharSequence f426b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CharSequence f427c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final CharSequence f428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Bitmap f429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Uri f430f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Bundle f431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Uri f432h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Object f433i;

    static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        @Override // android.os.Parcelable.Creator
        public final MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return MediaDescriptionCompat.a(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public final MediaDescriptionCompat[] newArray(int i2) {
            return new MediaDescriptionCompat[i2];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private CharSequence f435b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f436c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f437d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Bitmap f438e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Uri f439f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Bundle f440g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Uri f441h;

        public final MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f434a, this.f435b, this.f436c, this.f437d, this.f438e, this.f439f, this.f440g, this.f441h);
        }

        public final void b(CharSequence charSequence) {
            this.f437d = charSequence;
        }

        public final void c(Bundle bundle) {
            this.f440g = bundle;
        }

        public final void d(Bitmap bitmap) {
            this.f438e = bitmap;
        }

        public final void e(Uri uri) {
            this.f439f = uri;
        }

        public final void f(String str) {
            this.f434a = str;
        }

        public final void g(Uri uri) {
            this.f441h = uri;
        }

        public final void h(CharSequence charSequence) {
            this.f436c = charSequence;
        }

        public final void i(CharSequence charSequence) {
            this.f435b = charSequence;
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f425a = str;
        this.f426b = charSequence;
        this.f427c = charSequence2;
        this.f428d = charSequence3;
        this.f429e = bitmap;
        this.f430f = uri;
        this.f431g = bundle;
        this.f432h = uri2;
    }

    public static MediaDescriptionCompat a(Object obj) {
        Bundle bundle = null;
        if (obj == null) {
            return null;
        }
        b bVar = new b();
        MediaDescription mediaDescription = (MediaDescription) obj;
        bVar.f(mediaDescription.getMediaId());
        bVar.i(mediaDescription.getTitle());
        bVar.h(mediaDescription.getSubtitle());
        bVar.b(mediaDescription.getDescription());
        bVar.d(mediaDescription.getIconBitmap());
        bVar.e(mediaDescription.getIconUri());
        Bundle extras = mediaDescription.getExtras();
        Uri uri = extras == null ? null : (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
        if (uri == null) {
            bundle = extras;
        } else if (!extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || extras.size() != 2) {
            extras.remove("android.support.v4.media.description.MEDIA_URI");
            extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = extras;
        }
        bVar.c(bundle);
        if (uri != null) {
            bVar.g(uri);
        } else {
            bVar.g(mediaDescription.getMediaUri());
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.f433i = obj;
        return mediaDescriptionCompatA;
    }

    public final String b() {
        return this.f425a;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f426b) + ", " + ((Object) this.f427c) + ", " + ((Object) this.f428d);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        Object objBuild = this.f433i;
        if (objBuild == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.f425a);
            builder.setTitle(this.f426b);
            builder.setSubtitle(this.f427c);
            builder.setDescription(this.f428d);
            builder.setIconBitmap(this.f429e);
            builder.setIconUri(this.f430f);
            builder.setExtras(this.f431g);
            builder.setMediaUri(this.f432h);
            objBuild = builder.build();
            this.f433i = objBuild;
        }
        ((MediaDescription) objBuild).writeToParcel(parcel, i2);
    }
}

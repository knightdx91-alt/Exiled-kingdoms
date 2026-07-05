package i;

import android.util.Base64;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f2220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f2221e;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        this.f2217a = str;
        this.f2218b = str2;
        this.f2219c = str3;
        list.getClass();
        this.f2220d = list;
        this.f2221e = str + "-" + str2 + "-" + str3;
    }

    public final List<List<byte[]>> a() {
        return this.f2220d;
    }

    public final String b() {
        return this.f2221e;
    }

    public final String c() {
        return this.f2217a;
    }

    public final String d() {
        return this.f2218b;
    }

    public final String e() {
        return this.f2219c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2217a + ", mProviderPackage: " + this.f2218b + ", mQuery: " + this.f2219c + ", mCertificates:");
        int i2 = 0;
        while (true) {
            List<List<byte[]>> list = this.f2220d;
            if (i2 >= list.size()) {
                sb.append("}mCertificatesArray: 0");
                return sb.toString();
            }
            sb.append(" [");
            List<byte[]> list2 = list.get(i2);
            for (int i3 = 0; i3 < list2.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list2.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
            i2++;
        }
    }
}

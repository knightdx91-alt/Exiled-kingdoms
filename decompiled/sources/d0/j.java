package d0;

import java.util.Date;

/* JADX INFO: compiled from: Transaction.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2162b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2163c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2164d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Date f2165e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2166f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f2167g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f2168h;

    public final String a() {
        return this.f2161a;
    }

    public final String b() {
        return this.f2163c;
    }

    public final Date c() {
        return this.f2165e;
    }

    public final void d(String str) {
        this.f2161a = str;
    }

    public final void e(String str) {
        this.f2163c = str;
    }

    public final void f(String str) {
        this.f2166f = str;
    }

    public final void g(Date date) {
        this.f2165e = date;
    }

    public final void h(String str) {
        this.f2164d = str;
    }

    public final void i() {
        this.f2162b = "GooglePlay";
    }

    public final void j(String str) {
        this.f2167g = str;
    }

    public final void k(String str) {
        this.f2168h = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Transaction{identifier='");
        sb.append(this.f2161a);
        sb.append("', storeName='");
        sb.append(this.f2162b);
        sb.append("', orderId='");
        sb.append(this.f2163c);
        sb.append("', requestId='");
        sb.append(this.f2164d);
        sb.append("', userId='null', purchaseTime=");
        sb.append(this.f2165e);
        sb.append(", purchaseText='");
        sb.append(this.f2166f);
        sb.append("', purchaseCost=0, purchaseCostCurrency='null', reversalTime=null, reversalText='null', transactionData='");
        sb.append(this.f2167g);
        sb.append("', transactionDataSignature='");
        return a.a.m(this.f2168h, "'}", sb);
    }
}

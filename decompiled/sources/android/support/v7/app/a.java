package android.support.v7.app;

import android.view.ViewGroup;

/* JADX INFO: compiled from: ActionBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a {

    /* JADX INFO: compiled from: ActionBar.java */
    public interface b {
        void a();
    }

    /* JADX INFO: compiled from: ActionBar.java */
    @Deprecated
    public static abstract class c {
    }

    /* JADX INFO: renamed from: android.support.v7.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionBar.java */
    public static class C0014a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f750a;

        public C0014a(C0014a c0014a) {
            super((ViewGroup.MarginLayoutParams) c0014a);
            this.f750a = 0;
            this.f750a = c0014a.f750a;
        }

        public C0014a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f750a = 0;
        }
    }
}

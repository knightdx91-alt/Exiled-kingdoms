package n;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f2457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f2458c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Resources f2459d;

    public d() {
        super(null);
    }

    private void b() {
        if (this.f2457b == null) {
            this.f2457b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f2457b.setTo(theme);
            }
        }
        this.f2457b.applyStyle(this.f2456a, true);
    }

    public final int a() {
        return this.f2456a;
    }

    @Override // android.content.ContextWrapper
    protected final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        if (this.f2459d == null) {
            this.f2459d = super.getResources();
        }
        return this.f2459d;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f2458c == null) {
            this.f2458c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f2458c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f2457b;
        if (theme != null) {
            return theme;
        }
        if (this.f2456a == 0) {
            this.f2456a = k.i.Theme_AppCompat_Light;
        }
        b();
        return this.f2457b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i2) {
        if (this.f2456a != i2) {
            this.f2456a = i2;
            b();
        }
    }

    public d(Context context, int i2) {
        super(context);
        this.f2456a = i2;
    }
}

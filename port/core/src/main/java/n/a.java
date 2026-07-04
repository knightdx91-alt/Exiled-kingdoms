package n;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import k.j;

/* JADX INFO: compiled from: ActionBarPolicy.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2453a;

    public static a b(Context context) {
        a aVar = new a();
        aVar.f2453a = context;
        return aVar;
    }

    public final boolean a() {
        return this.f2453a.getApplicationInfo().targetSdkVersion < 14;
    }

    public final int c() {
        return this.f2453a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public final int d() {
        Configuration configuration = this.f2453a.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600) {
            return 5;
        }
        if (i2 > 960 && i3 > 720) {
            return 5;
        }
        if (i2 > 720 && i3 > 960) {
            return 5;
        }
        if (i2 >= 500) {
            return 4;
        }
        if (i2 > 640 && i3 > 480) {
            return 4;
        }
        if (i2 <= 480 || i3 <= 640) {
            return i2 >= 360 ? 3 : 2;
        }
        return 4;
    }

    public final int e() {
        return this.f2453a.getResources().getDimensionPixelSize(k.d.abc_action_bar_stacked_tab_max_width);
    }

    public final int f() {
        int[] iArr = j.ActionBar;
        int i2 = k.a.actionBarStyle;
        Context context = this.f2453a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, iArr, i2, 0);
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(j.ActionBar_height, 0);
        Resources resources = context.getResources();
        if (!g()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(k.d.abc_action_bar_stacked_max_height));
        }
        typedArrayObtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public final boolean g() {
        return this.f2453a.getResources().getBoolean(k.b.abc_action_bar_embed_tabs);
    }
}

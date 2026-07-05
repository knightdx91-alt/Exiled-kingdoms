package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: compiled from: FragmentContainer.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class f {
    public Fragment a(Context context, String str, Bundle bundle) {
        return Fragment.instantiate(context, str, bundle);
    }

    public abstract View b(int i2);

    public abstract boolean c();
}

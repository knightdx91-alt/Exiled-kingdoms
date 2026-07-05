package android.support.v4.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.arch.lifecycle.a;
import android.os.Bundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SupportActivity extends Activity implements android.arch.lifecycle.b {
    private android.support.v4.util.i<Class<? extends a>, a> mExtraDataMap = new android.support.v4.util.i<>();
    private android.arch.lifecycle.c mLifecycleRegistry = new android.arch.lifecycle.c(this);

    public static class a {
    }

    public <T extends a> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    public android.arch.lifecycle.a getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = android.arch.lifecycle.e.f108b;
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new android.arch.lifecycle.e(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.c(a.b.f98c);
        super.onSaveInstanceState(bundle);
    }

    public void putExtraData(a aVar) {
        this.mExtraDataMap.put((Class<? extends a>) aVar.getClass(), aVar);
    }
}

package android.arch.lifecycle;

import android.app.Fragment;
import android.arch.lifecycle.a;
import android.content.ComponentCallbacks2;
import android.os.Bundle;

/* JADX INFO: compiled from: ReportFragment.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f108b = 0;

    private void a(a.EnumC0002a enumC0002a) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof d) {
            ((d) activity).getLifecycle().b(enumC0002a);
        } else if (activity instanceof b) {
            a lifecycle = ((b) activity).getLifecycle();
            if (lifecycle instanceof c) {
                ((c) lifecycle).b(enumC0002a);
            }
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(a.EnumC0002a.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(a.EnumC0002a.ON_DESTROY);
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(a.EnumC0002a.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        a(a.EnumC0002a.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        a(a.EnumC0002a.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(a.EnumC0002a.ON_STOP);
    }
}

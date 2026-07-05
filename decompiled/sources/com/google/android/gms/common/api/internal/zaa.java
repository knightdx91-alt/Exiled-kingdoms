package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0028zaa> zack;

    public zaa(Activity activity) {
        this(C0028zaa.zaa(activity));
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0028zaa c0028zaa = this.zack.get();
        if (c0028zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0028zaa.zaa(runnable);
        return this;
    }

    private zaa(C0028zaa c0028zaa) {
        this.zack = new WeakReference<>(c0028zaa);
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    static class C0028zaa extends LifecycleCallback {
        private List<Runnable> zacl;

        private C0028zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacl = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static C0028zaa zaa(Activity activity) {
            C0028zaa c0028zaa;
            synchronized (activity) {
                try {
                    LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
                    c0028zaa = (C0028zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0028zaa.class);
                    if (c0028zaa == null) {
                        c0028zaa = new C0028zaa(fragment);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return c0028zaa;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacl;
                this.zacl = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacl.add(runnable);
        }
    }
}

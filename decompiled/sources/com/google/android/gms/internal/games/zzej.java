package com.google.android.gms.internal.games;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzej {
    private Handler zzkr;
    private boolean zzks;
    private final Object zzkq = new Object();
    private HashMap<String, AtomicInteger> zzkt = new HashMap<>();
    private int zzku = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;

    public zzej(Looper looper, int i2) {
        this.zzkr = new Handler(looper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbl() {
        synchronized (this.zzkq) {
            this.zzks = false;
            flush();
        }
    }

    public final void flush() {
        synchronized (this.zzkq) {
            try {
                for (Map.Entry<String, AtomicInteger> entry : this.zzkt.entrySet()) {
                    zzf(entry.getKey(), entry.getValue().get());
                }
                this.zzkt.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected abstract void zzf(String str, int i2);

    public final void zzg(String str, int i2) {
        synchronized (this.zzkq) {
            try {
                if (!this.zzks) {
                    this.zzks = true;
                    this.zzkr.postDelayed(new zzek(this), this.zzku);
                }
                AtomicInteger atomicInteger = this.zzkt.get(str);
                if (atomicInteger == null) {
                    atomicInteger = new AtomicInteger();
                    this.zzkt.put(str, atomicInteger);
                }
                atomicInteger.addAndGet(i2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

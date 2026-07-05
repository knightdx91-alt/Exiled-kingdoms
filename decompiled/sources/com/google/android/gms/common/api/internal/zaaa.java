package com.google.android.gms.common.api.internal;

import android.support.v4.util.b;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaaa implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafh;
    private SignInConnectionListener zafi;

    zaaa(zax zaxVar, SignInConnectionListener signInConnectionListener) {
        this.zafh = zaxVar;
        this.zafi = signInConnectionListener;
    }

    final void cancel() {
        this.zafi.onComplete();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Map<zai<?>, String>> task) {
        this.zafh.zaen.lock();
        try {
            if (!this.zafh.zafc) {
                this.zafi.onComplete();
                return;
            }
            if (task.isSuccessful()) {
                zax zaxVar = this.zafh;
                zaxVar.zafe = new b(zaxVar.zaeu.size());
                Iterator it = this.zafh.zaeu.values().iterator();
                while (it.hasNext()) {
                    this.zafh.zafe.put(((zaw) it.next()).zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zafh.zafa) {
                    zax zaxVar2 = this.zafh;
                    zaxVar2.zafe = new b(zaxVar2.zaeu.size());
                    for (zaw zawVar : this.zafh.zaeu.values()) {
                        Object objZak = zawVar.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zawVar);
                        if (this.zafh.zaa((zaw<?>) zawVar, connectionResult)) {
                            this.zafh.zafe.put(objZak, new ConnectionResult(16));
                        } else {
                            this.zafh.zafe.put(objZak, connectionResult);
                        }
                    }
                } else {
                    this.zafh.zafe = availabilityException.zaj();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zafh.zafe = Collections.emptyMap();
            }
            if (this.zafh.isConnected()) {
                this.zafh.zafd.putAll(this.zafh.zafe);
                if (this.zafh.zaaf() == null) {
                    this.zafh.zaad();
                    this.zafh.zaae();
                    this.zafh.zaey.signalAll();
                }
            }
            this.zafi.onComplete();
        } finally {
            this.zafh.zaen.unlock();
        }
    }
}

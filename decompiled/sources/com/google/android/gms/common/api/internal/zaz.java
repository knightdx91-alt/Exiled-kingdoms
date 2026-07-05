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
final class zaz implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafh;

    private zaz(zax zaxVar) {
        this.zafh = zaxVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Map<zai<?>, String>> task) {
        this.zafh.zaen.lock();
        try {
            if (!this.zafh.zafc) {
                this.zafh.zaen.unlock();
                return;
            }
            if (task.isSuccessful()) {
                zax zaxVar = this.zafh;
                zaxVar.zafd = new b(zaxVar.zaet.size());
                Iterator it = this.zafh.zaet.values().iterator();
                while (it.hasNext()) {
                    this.zafh.zafd.put(((zaw) it.next()).zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zafh.zafa) {
                    zax zaxVar2 = this.zafh;
                    zaxVar2.zafd = new b(zaxVar2.zaet.size());
                    for (zaw zawVar : this.zafh.zaet.values()) {
                        Object objZak = zawVar.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zawVar);
                        if (this.zafh.zaa((zaw<?>) zawVar, connectionResult)) {
                            this.zafh.zafd.put(objZak, new ConnectionResult(16));
                        } else {
                            this.zafh.zafd.put(objZak, connectionResult);
                        }
                    }
                } else {
                    this.zafh.zafd = availabilityException.zaj();
                }
                zax zaxVar3 = this.zafh;
                zaxVar3.zafg = zaxVar3.zaaf();
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zafh.zafd = Collections.emptyMap();
                this.zafh.zafg = new ConnectionResult(8);
            }
            if (this.zafh.zafe != null) {
                this.zafh.zafd.putAll(this.zafh.zafe);
                zax zaxVar4 = this.zafh;
                zaxVar4.zafg = zaxVar4.zaaf();
            }
            if (this.zafh.zafg == null) {
                this.zafh.zaad();
                this.zafh.zaae();
            } else {
                zax.zaa(this.zafh, false);
                this.zafh.zaew.zac(this.zafh.zafg);
            }
            this.zafh.zaey.signalAll();
            this.zafh.zaen.unlock();
        } catch (Throwable th) {
            this.zafh.zaen.unlock();
            throw th;
        }
    }
}

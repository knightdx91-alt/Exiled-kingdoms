package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.drive.zzet;
import com.google.android.gms.internal.drive.zzfj;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DriveEventService extends Service implements ChangeListener, CompletionListener, zzd, zzi {
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    private static final GmsLogger zzbx = new GmsLogger("DriveEventService", "");
    private final String name;
    private CountDownLatch zzch;

    @VisibleForTesting
    zza zzci;
    boolean zzcj;

    @VisibleForTesting
    private int zzck;

    static final class zza extends Handler {
        private final WeakReference<DriveEventService> zzcn;

        private zza(DriveEventService driveEventService) {
            this.zzcn = new WeakReference<>(driveEventService);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Message zzb(zzfj zzfjVar) {
            return obtainMessage(1, zzfjVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Message zzx() {
            return obtainMessage(2);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    DriveEventService.zzbx.wfmt("DriveEventService", "Unexpected message type: %s", Integer.valueOf(message.what));
                    return;
                } else {
                    getLooper().quit();
                    return;
                }
            }
            DriveEventService driveEventService = this.zzcn.get();
            if (driveEventService != null) {
                driveEventService.zza((zzfj) message.obj);
            } else {
                getLooper().quit();
            }
        }

        /* synthetic */ zza(DriveEventService driveEventService, zzh zzhVar) {
            this(driveEventService);
        }
    }

    @VisibleForTesting
    final class zzb extends zzet {
        private zzb() {
        }

        @Override // com.google.android.gms.internal.drive.zzes
        public final void zzc(zzfj zzfjVar) {
            synchronized (DriveEventService.this) {
                try {
                    DriveEventService.this.zzv();
                    zza zzaVar = DriveEventService.this.zzci;
                    if (zzaVar != null) {
                        DriveEventService.this.zzci.sendMessage(zzaVar.zzb(zzfjVar));
                    } else {
                        DriveEventService.zzbx.e("DriveEventService", "Receiving event before initialize is completed.");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* synthetic */ zzb(DriveEventService driveEventService, zzh zzhVar) {
            this();
        }
    }

    protected DriveEventService() {
        this("DriveEventService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv() {
        int callingUid = getCallingUid();
        if (callingUid == this.zzck) {
            return;
        }
        if (!UidVerifier.isGooglePlayServicesUid(this, callingUid)) {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
        this.zzck = callingUid;
    }

    @VisibleForTesting
    protected int getCallingUid() {
        return Binder.getCallingUid();
    }

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        zzh zzhVar = null;
        if (!ACTION_HANDLE_EVENT.equals(intent.getAction())) {
            return null;
        }
        if (this.zzci == null && !this.zzcj) {
            this.zzcj = true;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.zzch = new CountDownLatch(1);
            new zzh(this, countDownLatch).start();
            try {
                if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                    zzbx.e("DriveEventService", "Failed to synchronously initialize event handler.");
                }
            } catch (InterruptedException e2) {
                throw new RuntimeException("Unable to start event handler", e2);
            }
        }
        return new zzb(this, zzhVar).asBinder();
    }

    @Override // com.google.android.gms.drive.events.ChangeListener
    public void onChange(ChangeEvent changeEvent) {
        zzbx.wfmt("DriveEventService", "Unhandled change event in %s: %s", this.name, changeEvent);
    }

    @Override // com.google.android.gms.drive.events.CompletionListener
    public void onCompletion(CompletionEvent completionEvent) {
        zzbx.wfmt("DriveEventService", "Unhandled completion event in %s: %s", this.name, completionEvent);
    }

    @Override // android.app.Service
    public synchronized void onDestroy() {
        zza zzaVar = this.zzci;
        if (zzaVar != null) {
            this.zzci.sendMessage(zzaVar.zzx());
            this.zzci = null;
            try {
                if (!this.zzch.await(5000L, TimeUnit.MILLISECONDS)) {
                    zzbx.w("DriveEventService", "Failed to synchronously quit event handler. Will quit itself");
                }
            } catch (InterruptedException unused) {
            }
            this.zzch = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    protected DriveEventService(String str) {
        this.zzcj = false;
        this.zzck = -1;
        this.name = str;
    }

    @Override // com.google.android.gms.drive.events.zzd
    public final void zza(com.google.android.gms.drive.events.zzb zzbVar) {
        zzbx.wfmt("DriveEventService", "Unhandled changes available event in %s: %s", this.name, zzbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzfj zzfjVar) {
        DriveEvent driveEventZzak = zzfjVar.zzak();
        try {
            int type = driveEventZzak.getType();
            if (type == 1) {
                onChange((ChangeEvent) driveEventZzak);
                return;
            }
            if (type == 2) {
                onCompletion((CompletionEvent) driveEventZzak);
                return;
            }
            if (type == 4) {
                zza((com.google.android.gms.drive.events.zzb) driveEventZzak);
            } else {
                if (type != 7) {
                    zzbx.wfmt("DriveEventService", "Unhandled event: %s", driveEventZzak);
                    return;
                }
                zzbx.wfmt("DriveEventService", "Unhandled transfer state event in %s: %s", this.name, (zzv) driveEventZzak);
            }
        } catch (Exception e2) {
            zzbx.e("DriveEventService", "Error handling event in " + this.name, e2);
        }
    }
}

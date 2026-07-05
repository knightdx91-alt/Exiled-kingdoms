package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zze implements com.google.android.gms.drive.events.zzk {
    private final com.google.android.gms.drive.events.zzm zzct;
    private final long zzcu;
    private final long zzcv;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.drive.events.zzm, com.google.android.gms.internal.drive.zzf] */
    public zze(zzh zzhVar) {
        this.zzct = new zzf(zzhVar);
        this.zzcu = zzhVar.zzcu;
        this.zzcv = zzhVar.zzcv;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zze.class) {
            if (obj == this) {
                return true;
            }
            zze zzeVar = (zze) obj;
            if (Objects.equal(this.zzct, zzeVar.zzct) && this.zzcu == zzeVar.zzcu && this.zzcv == zzeVar.zzcv) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzcv), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv));
    }

    public final String toString() {
        Locale locale = Locale.US;
        return "FileTransferProgress[FileTransferState: " + this.zzct.toString() + ", BytesTransferred: " + this.zzcu + ", TotalBytes: " + this.zzcv + "]";
    }
}

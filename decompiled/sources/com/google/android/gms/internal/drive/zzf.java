package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf {
    private final int status;
    private final int zzcr;
    private final DriveId zzk;

    public zzf(zzh zzhVar) {
        this.zzk = zzhVar.zzk;
        this.zzcr = zzhVar.zzcr;
        this.status = zzhVar.status;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zzf.class) {
            if (obj == this) {
                return true;
            }
            zzf zzfVar = (zzf) obj;
            if (Objects.equal(this.zzk, zzfVar.zzk) && this.zzcr == zzfVar.zzcr && this.status == zzfVar.status) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzk, Integer.valueOf(this.zzcr), Integer.valueOf(this.status));
    }

    public final String toString() {
        return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", Integer.valueOf(this.zzcr), this.zzk, Integer.valueOf(this.status));
    }
}

package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzho extends zzir<zzho> {
    public long zzac = -1;
    public long zzf = -1;

    public zzho() {
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzho)) {
            return false;
        }
        zzho zzhoVar = (zzho) obj;
        if (this.zzac != zzhoVar.zzac || this.zzf != zzhoVar.zzf) {
            return false;
        }
        zzit zzitVar = this.zzmw;
        if (zzitVar != null && !zzitVar.isEmpty()) {
            return this.zzmw.equals(zzhoVar.zzmw);
        }
        zzit zzitVar2 = zzhoVar.zzmw;
        return zzitVar2 == null || zzitVar2.isEmpty();
    }

    public final int hashCode() {
        long j2 = this.zzac;
        long j3 = this.zzf;
        int i2 = ((((-161816419) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        zzit zzitVar = this.zzmw;
        return i2 + ((zzitVar == null || zzitVar.isEmpty()) ? 0 : this.zzmw.hashCode());
    }

    @Override // com.google.android.gms.internal.drive.zzix
    public final /* synthetic */ zzix zza(zzio zzioVar) throws zziw {
        while (true) {
            int iZzbd = zzioVar.zzbd();
            if (iZzbd == 0) {
                return this;
            }
            if (iZzbd == 8) {
                long jZzbf = zzioVar.zzbf();
                this.zzac = (-(jZzbf & 1)) ^ (jZzbf >>> 1);
            } else if (iZzbd == 16) {
                long jZzbf2 = zzioVar.zzbf();
                this.zzf = (-(jZzbf2 & 1)) ^ (jZzbf2 >>> 1);
            } else if (!zza(zzioVar, iZzbd)) {
                return this;
            }
        }
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    protected final int zzaq() {
        return zzip.zzb(2, this.zzf) + zzip.zzb(1, this.zzac) + super.zzaq();
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    public final void zza(zzip zzipVar) throws zziq {
        zzipVar.zza(1, this.zzac);
        zzipVar.zza(2, this.zzf);
        super.zza(zzipVar);
    }
}

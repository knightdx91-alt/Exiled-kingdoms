package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhn extends zzir<zzhn> {
    public int versionCode = 1;
    public String zzab = "";
    public long zzac = -1;
    public long zzf = -1;
    public int zzad = -1;

    public zzhn() {
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhn)) {
            return false;
        }
        zzhn zzhnVar = (zzhn) obj;
        if (this.versionCode != zzhnVar.versionCode) {
            return false;
        }
        String str = this.zzab;
        if (str == null) {
            if (zzhnVar.zzab != null) {
                return false;
            }
        } else if (!str.equals(zzhnVar.zzab)) {
            return false;
        }
        if (this.zzac != zzhnVar.zzac || this.zzf != zzhnVar.zzf || this.zzad != zzhnVar.zzad) {
            return false;
        }
        zzit zzitVar = this.zzmw;
        if (zzitVar != null && !zzitVar.isEmpty()) {
            return this.zzmw.equals(zzhnVar.zzmw);
        }
        zzit zzitVar2 = zzhnVar.zzmw;
        return zzitVar2 == null || zzitVar2.isEmpty();
    }

    public final int hashCode() {
        int i2 = ((-161816450) + this.versionCode) * 31;
        String str = this.zzab;
        int iHashCode = 0;
        int iHashCode2 = str == null ? 0 : str.hashCode();
        long j2 = this.zzac;
        int i3 = (((i2 + iHashCode2) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.zzf;
        int i4 = (((i3 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.zzad) * 31;
        zzit zzitVar = this.zzmw;
        if (zzitVar != null && !zzitVar.isEmpty()) {
            iHashCode = this.zzmw.hashCode();
        }
        return i4 + iHashCode;
    }

    @Override // com.google.android.gms.internal.drive.zzix
    public final /* synthetic */ zzix zza(zzio zzioVar) throws zziw {
        while (true) {
            int iZzbd = zzioVar.zzbd();
            if (iZzbd == 0) {
                return this;
            }
            if (iZzbd == 8) {
                this.versionCode = zzioVar.zzbe();
            } else if (iZzbd == 18) {
                this.zzab = zzioVar.readString();
            } else if (iZzbd == 24) {
                long jZzbf = zzioVar.zzbf();
                this.zzac = (-(jZzbf & 1)) ^ (jZzbf >>> 1);
            } else if (iZzbd == 32) {
                long jZzbf2 = zzioVar.zzbf();
                this.zzf = (-(jZzbf2 & 1)) ^ (jZzbf2 >>> 1);
            } else if (iZzbd == 40) {
                this.zzad = zzioVar.zzbe();
            } else if (!zza(zzioVar, iZzbd)) {
                return this;
            }
        }
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    protected final int zzaq() {
        int iZzb = zzip.zzb(4, this.zzf) + zzip.zzb(3, this.zzac) + zzip.zzi(this.zzab) + zzip.zzo(2) + zzip.zzc(1, this.versionCode) + super.zzaq();
        int i2 = this.zzad;
        return i2 != -1 ? iZzb + zzip.zzc(5, i2) : iZzb;
    }

    @Override // com.google.android.gms.internal.drive.zzir, com.google.android.gms.internal.drive.zzix
    public final void zza(zzip zzipVar) throws zziq {
        zzipVar.zzb(1, this.versionCode);
        String str = this.zzab;
        zzipVar.zzd(2, 2);
        zzipVar.zzh(str);
        zzipVar.zza(3, this.zzac);
        zzipVar.zza(4, this.zzf);
        int i2 = this.zzad;
        if (i2 != -1) {
            zzipVar.zzb(5, i2);
        }
        super.zza(zzipVar);
    }
}

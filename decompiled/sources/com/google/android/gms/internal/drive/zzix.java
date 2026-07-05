package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzix {
    protected volatile int zznf = -1;

    public static final <T extends zzix> T zza(T t2, byte[] bArr, int i2, int i3) throws zziw {
        try {
            zzio zzioVarZza = zzio.zza(bArr, 0, i3);
            t2.zza(zzioVarZza);
            zzioVarZza.zzj(0);
            return t2;
        } catch (zziw e2) {
            throw e2;
        } catch (IOException e3) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e3);
        }
    }

    public String toString() {
        return zziy.zzb(this);
    }

    public abstract zzix zza(zzio zzioVar);

    protected int zzaq() {
        return 0;
    }

    @Override // 
    /* JADX INFO: renamed from: zzbi, reason: merged with bridge method [inline-methods] */
    public zzix clone() {
        return (zzix) super.clone();
    }

    public void zza(zzip zzipVar) {
    }

    public static final byte[] zza(zzix zzixVar) {
        int iZzaq = zzixVar.zzaq();
        zzixVar.zznf = iZzaq;
        byte[] bArr = new byte[iZzaq];
        try {
            zzip zzipVarZzb = zzip.zzb(bArr, 0, iZzaq);
            zzixVar.zza(zzipVarZzb);
            zzipVarZzb.zzbh();
            return bArr;
        } catch (IOException e2) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e2);
        }
    }
}

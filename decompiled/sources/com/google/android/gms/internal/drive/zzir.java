package com.google.android.gms.internal.drive;

import com.google.android.gms.internal.drive.zzir;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzir<M extends zzir<M>> extends zzix {
    protected zzit zzmw;

    @Override // com.google.android.gms.internal.drive.zzix
    public /* synthetic */ Object clone() {
        zzir zzirVar = (zzir) super.clone();
        zziv.zza(this, zzirVar);
        return zzirVar;
    }

    @Override // com.google.android.gms.internal.drive.zzix
    public void zza(zzip zzipVar) {
        if (this.zzmw == null) {
            return;
        }
        for (int i2 = 0; i2 < this.zzmw.size(); i2++) {
            this.zzmw.zzs(i2).zza(zzipVar);
        }
    }

    @Override // com.google.android.gms.internal.drive.zzix
    protected int zzaq() {
        if (this.zzmw == null) {
            return 0;
        }
        int iZzaq = 0;
        for (int i2 = 0; i2 < this.zzmw.size(); i2++) {
            iZzaq += this.zzmw.zzs(i2).zzaq();
        }
        return iZzaq;
    }

    @Override // com.google.android.gms.internal.drive.zzix
    /* JADX INFO: renamed from: zzbi */
    public final /* synthetic */ zzix clone() {
        return (zzir) clone();
    }

    protected final boolean zza(zzio zzioVar, int i2) {
        zziu zziuVarZzr;
        int position = zzioVar.getPosition();
        if (!zzioVar.zzk(i2)) {
            return false;
        }
        int i3 = i2 >>> 3;
        zziz zzizVar = new zziz(i2, zzioVar.zza(position, zzioVar.getPosition() - position));
        zzit zzitVar = this.zzmw;
        if (zzitVar == null) {
            this.zzmw = new zzit();
            zziuVarZzr = null;
        } else {
            zziuVarZzr = zzitVar.zzr(i3);
        }
        if (zziuVarZzr == null) {
            zziuVarZzr = new zziu();
            this.zzmw.zza(i3, zziuVarZzr);
        }
        zziuVarZzr.zza(zzizVar);
        return true;
    }
}

package com.google.android.gms.internal.play_billing;

import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbj implements zzey {
    private final zzbi zza;

    private zzbj(zzbi zzbiVar) {
        byte[] bArr = zzcg.zzd;
        this.zza = zzbiVar;
        zzbiVar.zza = this;
    }

    public static zzbj zza(zzbi zzbiVar) {
        zzbj zzbjVar = zzbiVar.zza;
        return zzbjVar != null ? zzbjVar : new zzbj(zzbiVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzA(int i2, int i3) {
        this.zza.zzp(i2, (i3 >> 31) ^ (i3 + i3));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzB(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                zzbi zzbiVar = this.zza;
                int iIntValue = ((Integer) list.get(i3)).intValue();
                zzbiVar.zzp(i2, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzx = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            int iIntValue2 = ((Integer) list.get(i4)).intValue();
            iZzx += zzbi.zzx((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
        }
        this.zza.zzq(iZzx);
        while (i3 < list.size()) {
            zzbi zzbiVar2 = this.zza;
            int iIntValue3 = ((Integer) list.get(i3)).intValue();
            zzbiVar2.zzq((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzC(int i2, long j2) {
        this.zza.zzr(i2, (j2 >> 63) ^ (j2 + j2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzD(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                zzbi zzbiVar = this.zza;
                long jLongValue = ((Long) list.get(i3)).longValue();
                zzbiVar.zzr(i2, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzy = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            long jLongValue2 = ((Long) list.get(i4)).longValue();
            iZzy += zzbi.zzy((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
        }
        this.zza.zzq(iZzy);
        while (i3 < list.size()) {
            zzbi zzbiVar2 = this.zza;
            long jLongValue3 = ((Long) list.get(i3)).longValue();
            zzbiVar2.zzs((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    @Deprecated
    public final void zzE(int i2) {
        this.zza.zzo(i2, 3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzF(int i2, String str) {
        this.zza.zzm(i2, str);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzG(int i2, List list) {
        int i3 = 0;
        if (!(list instanceof zzcn)) {
            while (i3 < list.size()) {
                this.zza.zzm(i2, (String) list.get(i3));
                i3++;
            }
            return;
        }
        zzcn zzcnVar = (zzcn) list;
        while (i3 < list.size()) {
            Object objZzf = zzcnVar.zzf(i3);
            if (objZzf instanceof String) {
                this.zza.zzm(i2, (String) objZzf);
            } else {
                this.zza.zze(i2, (zzba) objZzf);
            }
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzH(int i2, int i3) {
        this.zza.zzp(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzI(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzp(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzx = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            iZzx += zzbi.zzx(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(iZzx);
        while (i3 < list.size()) {
            this.zza.zzq(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzJ(int i2, long j2) {
        this.zza.zzr(i2, j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzK(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzy = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            iZzy += zzbi.zzy(((Long) list.get(i4)).longValue());
        }
        this.zza.zzq(iZzy);
        while (i3 < list.size()) {
            this.zza.zzs(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzb(int i2, boolean z2) {
        this.zza.zzd(i2, z2);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzc(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzd(i2, ((Boolean) list.get(i3)).booleanValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Boolean) list.get(i5)).getClass();
            i4++;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzb(((Boolean) list.get(i3)).booleanValue() ? (byte) 1 : (byte) 0);
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzd(int i2, zzba zzbaVar) {
        this.zza.zze(i2, zzbaVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zze(int i2, List list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.zza.zze(i2, (zzba) list.get(i3));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzf(int i2, double d2) {
        this.zza.zzh(i2, Double.doubleToRawLongBits(d2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzg(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Double) list.get(i5)).getClass();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    @Deprecated
    public final void zzh(int i2) {
        this.zza.zzo(i2, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzi(int i2, int i3) {
        this.zza.zzj(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzj(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzu = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            iZzu += zzbi.zzu(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(iZzu);
        while (i3 < list.size()) {
            this.zza.zzk(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzk(int i2, int i3) {
        this.zza.zzf(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzl(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Integer) list.get(i5)).getClass();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzm(int i2, long j2) {
        this.zza.zzh(i2, j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzn(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Long) list.get(i5)).getClass();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzo(int i2, float f2) {
        this.zza.zzf(i2, Float.floatToRawIntBits(f2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzp(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Float) list.get(i5)).getClass();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzq(int i2, Object obj, zzdp zzdpVar) {
        zzbi zzbiVar = this.zza;
        zzbiVar.zzo(i2, 3);
        zzdpVar.zzi((zzdf) obj, zzbiVar.zza);
        zzbiVar.zzo(i2, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzr(int i2, int i3) {
        this.zza.zzj(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzs(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzu = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            iZzu += zzbi.zzu(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(iZzu);
        while (i3 < list.size()) {
            this.zza.zzk(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzt(int i2, long j2) {
        this.zza.zzr(i2, j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzu(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int iZzy = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            iZzy += zzbi.zzy(((Long) list.get(i4)).longValue());
        }
        this.zza.zzq(iZzy);
        while (i3 < list.size()) {
            this.zza.zzs(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzv(int i2, Object obj, zzdp zzdpVar) {
        zzdf zzdfVar = (zzdf) obj;
        zzbf zzbfVar = (zzbf) this.zza;
        zzbfVar.zzq((i2 << 3) | 2);
        zzbfVar.zzq(((zzak) zzdfVar).zza(zzdpVar));
        zzdpVar.zzi(zzdfVar, zzbfVar.zza);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzw(int i2, int i3) {
        this.zza.zzf(i2, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzx(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Integer) list.get(i5)).getClass();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzy(int i2, long j2) {
        this.zza.zzh(i2, j2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzz(int i2, List list, boolean z2) {
        int i3 = 0;
        if (!z2) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Long) list.get(i5)).getClass();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(((Long) list.get(i3)).longValue());
            i3++;
        }
    }
}

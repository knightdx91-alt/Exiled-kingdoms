package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbs {
    private static final zzbs zzb = new zzbs(true);
    final zzec zza = new zzds(16);
    private boolean zzc;
    private boolean zzd;

    private zzbs() {
    }

    public static zzbs zza() {
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void zzd(zzbr zzbrVar, Object obj) {
        boolean z2;
        zzbrVar.zzb();
        byte[] bArr = zzcg.zzd;
        obj.getClass();
        zzew zzewVar = zzew.zza;
        zzex zzexVar = zzex.INT;
        switch (r0.zza()) {
            case INT:
                z2 = obj instanceof Integer;
                if (z2) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case LONG:
                z2 = obj instanceof Long;
                if (z2) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case FLOAT:
                z2 = obj instanceof Float;
                if (z2) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case DOUBLE:
                z2 = obj instanceof Double;
                if (z2) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case BOOLEAN:
                z2 = obj instanceof Boolean;
                if (z2) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case STRING:
                z2 = obj instanceof String;
                if (z2) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case BYTE_STRING:
                if ((obj instanceof zzba) || (obj instanceof byte[])) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof zzfp)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            case MESSAGE:
                if ((obj instanceof zzdf) || (obj instanceof zzck)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
            default:
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzbrVar.zza()), zzbrVar.zzb().zza(), obj.getClass().getName()));
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        zzbs zzbsVar = new zzbs();
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            Map.Entry entryZzg = this.zza.zzg(i2);
            zzbsVar.zzc((zzbr) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzbsVar.zzc((zzbr) entry.getKey(), entry.getValue());
        }
        zzbsVar.zzd = this.zzd;
        return zzbsVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbs) {
            return this.zza.equals(((zzbs) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            Map.Entry entryZzg = this.zza.zzg(i2);
            if (entryZzg.getValue() instanceof zzcb) {
                ((zzcb) entryZzg.getValue()).zzn();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzbr zzbrVar, Object obj) {
        if (!zzbrVar.zzc()) {
            zzd(zzbrVar, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                zzd(zzbrVar, arrayList.get(i2));
            }
            obj = arrayList;
        }
        if (obj instanceof zzck) {
            this.zzd = true;
        }
        this.zza.put(zzbrVar, obj);
    }

    private zzbs(boolean z2) {
        zzb();
        zzb();
    }
}

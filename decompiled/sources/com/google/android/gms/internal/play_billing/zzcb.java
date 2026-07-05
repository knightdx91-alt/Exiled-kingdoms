package com.google.android.gms.internal.play_billing;

import a.a;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.play_billing.zzbx;
import com.google.android.gms.internal.play_billing.zzcb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzcb<MessageType extends zzcb<MessageType, BuilderType>, BuilderType extends zzbx<MessageType, BuilderType>> extends zzak<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzeh zzc = zzeh.zzc();

    static zzcb zzh(Class cls) {
        Map map = zzb;
        zzcb zzcbVar = (zzcb) map.get(cls);
        if (zzcbVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzcbVar = (zzcb) map.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zzcbVar == null) {
            zzcbVar = (zzcb) ((zzcb) zzeq.zze(cls)).zzu(6, null, null);
            if (zzcbVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzcbVar);
        }
        return zzcbVar;
    }

    protected static zzcb zzj(zzcb zzcbVar, byte[] bArr, zzbn zzbnVar) throws zzci {
        zzcb zzcbVarZzw = zzw(zzcbVar, bArr, 0, bArr.length, zzbnVar);
        if (zzcbVarZzw == null || zzcbVarZzw.zzs()) {
            return zzcbVarZzw;
        }
        zzci zzciVarZza = new zzef(zzcbVarZzw).zza();
        zzciVarZza.zzf(zzcbVarZzw);
        throw zzciVarZza;
    }

    static Object zzl(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzm(zzdf zzdfVar, String str, Object[] objArr) {
        return new zzdo(zzdfVar, str, objArr);
    }

    protected static void zzp(Class cls, zzcb zzcbVar) {
        zzcbVar.zzo();
        zzb.put(cls, zzcbVar);
    }

    private final int zzv(zzdp zzdpVar) {
        return zzdn.zza().zzb(getClass()).zza(this);
    }

    private static zzcb zzw(zzcb zzcbVar, byte[] bArr, int i2, int i3, zzbn zzbnVar) throws zzci {
        zzcb zzcbVarZzi = zzcbVar.zzi();
        try {
            zzdp zzdpVarZzb = zzdn.zza().zzb(zzcbVarZzi.getClass());
            zzdpVarZzb.zzh(zzcbVarZzi, bArr, 0, i3, new zzan(zzbnVar));
            zzdpVarZzb.zzf(zzcbVarZzi);
            return zzcbVarZzi;
        } catch (zzci e2) {
            e2.zzf(zzcbVarZzi);
            throw e2;
        } catch (zzef e3) {
            zzci zzciVarZza = e3.zza();
            zzciVarZza.zzf(zzcbVarZzi);
            throw zzciVarZza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzci) {
                throw ((zzci) e4.getCause());
            }
            zzci zzciVar = new zzci(e4);
            zzciVar.zzf(zzcbVarZzi);
            throw zzciVar;
        } catch (IndexOutOfBoundsException unused) {
            zzci zzciVarZzg = zzci.zzg();
            zzciVarZzg.zzf(zzcbVarZzi);
            throw zzciVarZzg;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzdn.zza().zzb(getClass()).zzj(this, (zzcb) obj);
    }

    public final int hashCode() {
        if (zzt()) {
            return zzd();
        }
        int i2 = this.zza;
        if (i2 != 0) {
            return i2;
        }
        int iZzd = zzd();
        this.zza = iZzd;
        return iZzd;
    }

    public final String toString() {
        return zzdh.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.play_billing.zzak
    final int zza(zzdp zzdpVar) {
        if (zzt()) {
            int iZza = zzdpVar.zza(this);
            if (iZza >= 0) {
                return iZza;
            }
            throw new IllegalStateException(a.h(iZza, "serialized size must be non-negative, was "));
        }
        int i2 = this.zzd & Api.BaseClientBuilder.API_PRIORITY_OTHER;
        if (i2 != Integer.MAX_VALUE) {
            return i2;
        }
        int iZza2 = zzdpVar.zza(this);
        if (iZza2 < 0) {
            throw new IllegalStateException(a.h(iZza2, "serialized size must be non-negative, was "));
        }
        this.zzd = (this.zzd & Integer.MIN_VALUE) | iZza2;
        return iZza2;
    }

    final int zzd() {
        return zzdn.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final int zze() {
        int iZzv;
        if (zzt()) {
            iZzv = zzv(null);
            if (iZzv < 0) {
                throw new IllegalStateException(a.h(iZzv, "serialized size must be non-negative, was "));
            }
        } else {
            iZzv = this.zzd & Api.BaseClientBuilder.API_PRIORITY_OTHER;
            if (iZzv == Integer.MAX_VALUE) {
                iZzv = zzv(null);
                if (iZzv < 0) {
                    throw new IllegalStateException(a.h(iZzv, "serialized size must be non-negative, was "));
                }
                this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzv;
            }
        }
        return iZzv;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdg
    public final /* synthetic */ zzdf zzf() {
        return (zzcb) zzu(6, null, null);
    }

    protected final zzbx zzg() {
        return (zzbx) zzu(5, null, null);
    }

    final zzcb zzi() {
        return (zzcb) zzu(4, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final /* synthetic */ zzde zzk() {
        return (zzbx) zzu(5, null, null);
    }

    protected final void zzn() {
        zzdn.zza().zzb(getClass()).zzf(this);
        zzo();
    }

    final void zzo() {
        this.zzd &= Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    final void zzq(int i2) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final void zzr(zzbi zzbiVar) {
        zzdn.zza().zzb(getClass()).zzi(this, zzbj.zza(zzbiVar));
    }

    public final boolean zzs() {
        byte bByteValue = ((Byte) zzu(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzk = zzdn.zza().zzb(getClass()).zzk(this);
        zzu(2, true != zZzk ? null : this, null);
        return zZzk;
    }

    final boolean zzt() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    protected abstract Object zzu(int i2, Object obj, Object obj2);
}

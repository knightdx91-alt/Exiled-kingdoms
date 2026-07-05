package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface zzy extends IInterface {
    Bundle getConnectionHint();

    int zza(zzu zzuVar, byte[] bArr, String str, String str2);

    Intent zza(int i2, int i3, boolean z2);

    Intent zza(int i2, byte[] bArr, int i3, String str);

    Intent zza(PlayerEntity playerEntity);

    Intent zza(RoomEntity roomEntity, int i2);

    Intent zza(String str, boolean z2, boolean z3, int i2);

    Intent zza(int[] iArr);

    void zza(long j2);

    void zza(IBinder iBinder, Bundle bundle);

    void zza(Contents contents);

    void zza(zzu zzuVar);

    void zza(zzu zzuVar, int i2);

    void zza(zzu zzuVar, int i2, int i3, int i4);

    void zza(zzu zzuVar, int i2, int i3, String[] strArr, Bundle bundle);

    void zza(zzu zzuVar, int i2, boolean z2, boolean z3);

    void zza(zzu zzuVar, int i2, int[] iArr);

    void zza(zzu zzuVar, long j2);

    void zza(zzu zzuVar, Bundle bundle, int i2, int i3);

    void zza(zzu zzuVar, IBinder iBinder, int i2, String[] strArr, Bundle bundle, boolean z2, long j2);

    void zza(zzu zzuVar, IBinder iBinder, String str, boolean z2, long j2);

    void zza(zzu zzuVar, String str);

    void zza(zzu zzuVar, String str, int i2, int i3, int i4, boolean z2);

    void zza(zzu zzuVar, String str, int i2, IBinder iBinder, Bundle bundle);

    void zza(zzu zzuVar, String str, int i2, boolean z2, boolean z3);

    void zza(zzu zzuVar, String str, long j2, String str2);

    void zza(zzu zzuVar, String str, IBinder iBinder, Bundle bundle);

    void zza(zzu zzuVar, String str, com.google.android.gms.games.snapshot.zze zzeVar, Contents contents);

    void zza(zzu zzuVar, String str, String str2);

    void zza(zzu zzuVar, String str, String str2, int i2, int i3);

    void zza(zzu zzuVar, String str, String str2, com.google.android.gms.games.snapshot.zze zzeVar, Contents contents);

    void zza(zzu zzuVar, String str, boolean z2);

    void zza(zzu zzuVar, String str, boolean z2, int i2);

    void zza(zzu zzuVar, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr);

    void zza(zzu zzuVar, String str, byte[] bArr, ParticipantResult[] participantResultArr);

    void zza(zzu zzuVar, boolean z2);

    void zza(zzu zzuVar, boolean z2, String[] strArr);

    void zza(zzu zzuVar, int[] iArr, int i2, boolean z2);

    void zza(zzu zzuVar, String[] strArr);

    void zza(zzu zzuVar, String[] strArr, boolean z2);

    void zza(zzw zzwVar, long j2);

    void zza(String str, int i2);

    void zza(String str, IBinder iBinder, Bundle bundle);

    void zza(String str, zzu zzuVar);

    Intent zzag();

    Intent zzai();

    int zzak();

    String zzam();

    int zzao();

    Intent zzaq();

    int zzar();

    int zzas();

    int zzat();

    int zzav();

    boolean zzaz();

    int zzb(byte[] bArr, String str, String[] strArr);

    Intent zzb(String str, int i2, int i3);

    void zzb(long j2);

    void zzb(zzu zzuVar);

    void zzb(zzu zzuVar, int i2);

    void zzb(zzu zzuVar, long j2);

    void zzb(zzu zzuVar, String str);

    void zzb(zzu zzuVar, String str, int i2, int i3, int i4, boolean z2);

    void zzb(zzu zzuVar, String str, int i2, IBinder iBinder, Bundle bundle);

    void zzb(zzu zzuVar, String str, IBinder iBinder, Bundle bundle);

    void zzb(zzu zzuVar, String str, String str2);

    void zzb(zzu zzuVar, String str, boolean z2);

    void zzb(zzu zzuVar, boolean z2);

    void zzb(zzu zzuVar, String[] strArr);

    void zzb(String str, int i2);

    void zzbd();

    String zzbf();

    DataHolder zzbg();

    DataHolder zzbh();

    Intent zzbi();

    Intent zzc(int i2, int i3, boolean z2);

    void zzc(long j2);

    void zzc(zzu zzuVar);

    void zzc(zzu zzuVar, long j2);

    void zzc(zzu zzuVar, String str);

    void zzc(zzu zzuVar, boolean z2);

    Intent zzd(String str);

    void zzd(long j2);

    void zzd(zzu zzuVar);

    void zzd(zzu zzuVar, long j2);

    void zzd(zzu zzuVar, String str);

    void zzd(zzu zzuVar, boolean z2);

    void zzd(String str, int i2);

    void zze(long j2);

    void zze(zzu zzuVar, long j2);

    void zze(zzu zzuVar, String str);

    void zze(zzu zzuVar, boolean z2);

    void zzf(long j2);

    void zzf(zzu zzuVar, String str);

    void zzf(zzu zzuVar, boolean z2);

    void zzf(String str);

    void zzg(zzu zzuVar, String str);

    void zzh(zzu zzuVar, String str);

    void zzk(int i2);

    String zzp();

    Intent zzv();

    Intent zzx();

    Intent zzy();

    Intent zzz();
}

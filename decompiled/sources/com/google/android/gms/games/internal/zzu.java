package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface zzu extends IInterface {
    void onCaptureOverlayStateChanged(int i2);

    void onInvitationRemoved(String str);

    void onLeftRoom(int i2, String str);

    void onP2PConnected(String str);

    void onP2PDisconnected(String str);

    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage);

    void onRequestRemoved(String str);

    void onSignOutComplete();

    void onTurnBasedMatchRemoved(String str);

    void zza(int i2, int i3, String str);

    void zza(int i2, Bundle bundle);

    void zza(int i2, VideoCapabilities videoCapabilities);

    void zza(int i2, String str);

    void zza(int i2, String str, boolean z2);

    void zza(int i2, boolean z2);

    void zza(DataHolder dataHolder);

    void zza(DataHolder dataHolder, DataHolder dataHolder2);

    void zza(DataHolder dataHolder, Contents contents);

    void zza(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3);

    void zza(DataHolder dataHolder, String[] strArr);

    void zza(DataHolder[] dataHolderArr);

    void zzaa(DataHolder dataHolder);

    void zzab(DataHolder dataHolder);

    void zzac(DataHolder dataHolder);

    void zzad(DataHolder dataHolder);

    void zzae(DataHolder dataHolder);

    void zzaf(DataHolder dataHolder);

    void zzag(DataHolder dataHolder);

    void zzah(DataHolder dataHolder);

    void zzai(DataHolder dataHolder);

    void zzaj(DataHolder dataHolder);

    void zzak(DataHolder dataHolder);

    void zzal(DataHolder dataHolder);

    void zzam(DataHolder dataHolder);

    void zzan(DataHolder dataHolder);

    void zzao(DataHolder dataHolder);

    void zzap(DataHolder dataHolder);

    void zzaq(DataHolder dataHolder);

    void zzar(DataHolder dataHolder);

    void zzas(DataHolder dataHolder);

    void zzat(DataHolder dataHolder);

    void zzau(DataHolder dataHolder);

    void zzav(DataHolder dataHolder);

    void zzaw(DataHolder dataHolder);

    void zzax(DataHolder dataHolder);

    void zzay(DataHolder dataHolder);

    void zzaz(DataHolder dataHolder);

    void zzb(int i2, Bundle bundle);

    void zzb(int i2, String str);

    void zzb(Status status);

    void zzb(DataHolder dataHolder);

    void zzb(DataHolder dataHolder, String[] strArr);

    void zzc(int i2);

    void zzc(int i2, Bundle bundle);

    void zzc(int i2, String str);

    void zzc(DataHolder dataHolder);

    void zzc(DataHolder dataHolder, String[] strArr);

    void zzd(int i2);

    void zzd(int i2, Bundle bundle);

    void zzd(int i2, String str);

    void zzd(DataHolder dataHolder);

    void zzd(DataHolder dataHolder, String[] strArr);

    void zze(int i2);

    void zze(int i2, Bundle bundle);

    void zze(DataHolder dataHolder);

    void zze(DataHolder dataHolder, String[] strArr);

    void zzf(int i2);

    void zzf(int i2, Bundle bundle);

    void zzf(DataHolder dataHolder);

    void zzf(DataHolder dataHolder, String[] strArr);

    void zzg(int i2);

    void zzg(int i2, Bundle bundle);

    void zzg(DataHolder dataHolder);

    void zzh(int i2);

    void zzh(DataHolder dataHolder);

    void zzi(int i2);

    void zzi(DataHolder dataHolder);

    void zzj(DataHolder dataHolder);

    void zzk(DataHolder dataHolder);

    void zzl(DataHolder dataHolder);

    void zzm(DataHolder dataHolder);

    void zzn(DataHolder dataHolder);

    void zzo(DataHolder dataHolder);

    void zzp(DataHolder dataHolder);

    void zzq(DataHolder dataHolder);

    void zzr(DataHolder dataHolder);

    void zzs(DataHolder dataHolder);

    void zzt(DataHolder dataHolder);

    void zzu(DataHolder dataHolder);

    void zzv(DataHolder dataHolder);

    void zzw(DataHolder dataHolder);

    void zzx(DataHolder dataHolder);

    void zzy(DataHolder dataHolder);

    void zzz(DataHolder dataHolder);
}

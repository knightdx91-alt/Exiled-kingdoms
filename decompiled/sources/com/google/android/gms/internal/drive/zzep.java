package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzep extends zza implements zzeo {
    zzep(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IDriveService");
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final IntentSender zza(zzgg zzggVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzggVar);
        Parcel parcelTransactAndReadException = transactAndReadException(10, parcelObtainAndWriteInterfaceToken);
        IntentSender intentSender = (IntentSender) zzc.zza(parcelTransactAndReadException, IntentSender.CREATOR);
        parcelTransactAndReadException.recycle();
        return intentSender;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zzb(zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(35, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final IntentSender zza(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        Parcel parcelTransactAndReadException = transactAndReadException(11, parcelObtainAndWriteInterfaceToken);
        IntentSender intentSender = (IntentSender) zzc.zza(parcelTransactAndReadException, IntentSender.CREATOR);
        parcelTransactAndReadException.recycle();
        return intentSender;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final zzec zza(zzgd zzgdVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgdVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        Parcel parcelTransactAndReadException = transactAndReadException(7, parcelObtainAndWriteInterfaceToken);
        zzec zzecVar = (zzec) zzc.zza(parcelTransactAndReadException, zzec.CREATOR);
        parcelTransactAndReadException.recycle();
        return zzecVar;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzab zzabVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzabVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(24, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzad zzadVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzadVar);
        transactAndReadExceptionReturnVoid(16, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzek zzekVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzekVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(9, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzex zzexVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzexVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(13, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgk zzgkVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgkVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgm zzgmVar, zzes zzesVar, String str, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgmVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzesVar);
        parcelObtainAndWriteInterfaceToken.writeString(null);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(15, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgo zzgoVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgoVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(36, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgq zzgqVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgqVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(28, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgv zzgvVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgvVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(17, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgx zzgxVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgxVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(38, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgz zzgzVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgzVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzj zzjVar, zzes zzesVar, String str, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzjVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzesVar);
        parcelObtainAndWriteInterfaceToken.writeString(null);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(14, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzm zzmVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzmVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(18, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzo zzoVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzoVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(8, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzr zzrVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzrVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(4, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzw zzwVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzwVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(5, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzy zzyVar, zzeq zzeqVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzyVar);
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeqVar);
        transactAndReadExceptionReturnVoid(6, parcelObtainAndWriteInterfaceToken);
    }
}

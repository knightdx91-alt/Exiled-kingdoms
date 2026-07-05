package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzz extends com.google.android.gms.internal.games.zza implements zzy {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.IGamesService");
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Bundle getConnectionHint() {
        Parcel parcelTransactAndReadException = transactAndReadException(5004, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Bundle.CREATOR);
        parcelTransactAndReadException.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zza(zzu zzuVar, byte[] bArr, String str, String str2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeByteArray(bArr);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        Parcel parcelTransactAndReadException = transactAndReadException(5033, parcelObtainAndWriteInterfaceToken);
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzag() {
        Parcel parcelTransactAndReadException = transactAndReadException(9010, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzai() {
        Parcel parcelTransactAndReadException = transactAndReadException(9012, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzak() {
        Parcel parcelTransactAndReadException = transactAndReadException(9019, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final String zzam() {
        Parcel parcelTransactAndReadException = transactAndReadException(5003, obtainAndWriteInterfaceToken());
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzao() {
        Parcel parcelTransactAndReadException = transactAndReadException(8024, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzaq() {
        Parcel parcelTransactAndReadException = transactAndReadException(10015, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzar() {
        Parcel parcelTransactAndReadException = transactAndReadException(10013, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzas() {
        Parcel parcelTransactAndReadException = transactAndReadException(10023, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzat() {
        Parcel parcelTransactAndReadException = transactAndReadException(12035, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzav() {
        Parcel parcelTransactAndReadException = transactAndReadException(12036, obtainAndWriteInterfaceToken());
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final boolean zzaz() {
        Parcel parcelTransactAndReadException = transactAndReadException(22030, obtainAndWriteInterfaceToken());
        boolean zZza = com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException);
        parcelTransactAndReadException.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final int zzb(byte[] bArr, String str, String[] strArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeByteArray(bArr);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        Parcel parcelTransactAndReadException = transactAndReadException(5034, parcelObtainAndWriteInterfaceToken);
        int i2 = parcelTransactAndReadException.readInt();
        parcelTransactAndReadException.recycle();
        return i2;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzbd() {
        transactAndReadExceptionReturnVoid(5006, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final String zzbf() {
        Parcel parcelTransactAndReadException = transactAndReadException(5012, obtainAndWriteInterfaceToken());
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final DataHolder zzbg() {
        Parcel parcelTransactAndReadException = transactAndReadException(5013, obtainAndWriteInterfaceToken());
        DataHolder dataHolder = (DataHolder) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, DataHolder.CREATOR);
        parcelTransactAndReadException.recycle();
        return dataHolder;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final DataHolder zzbh() {
        Parcel parcelTransactAndReadException = transactAndReadException(5502, obtainAndWriteInterfaceToken());
        DataHolder dataHolder = (DataHolder) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, DataHolder.CREATOR);
        parcelTransactAndReadException.recycle();
        return dataHolder;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzbi() {
        Parcel parcelTransactAndReadException = transactAndReadException(19002, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzc(int i2, int i3, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        Parcel parcelTransactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_OUT_OF_DISK_SPACE, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzd(String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(12034, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zze(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(12012, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzf(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(22027, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzg(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(12020, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzh(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(12008, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzk(int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(5036, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final String zzp() {
        Parcel parcelTransactAndReadException = transactAndReadException(5007, obtainAndWriteInterfaceToken());
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzv() {
        Parcel parcelTransactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_STORAGE_ERROR, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzx() {
        Parcel parcelTransactAndReadException = transactAndReadException(9005, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzy() {
        Parcel parcelTransactAndReadException = transactAndReadException(GamesStatusCodes.STATUS_VIDEO_ALREADY_CAPTURING, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzz() {
        Parcel parcelTransactAndReadException = transactAndReadException(9007, obtainAndWriteInterfaceToken());
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(int i2, int i3, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        Parcel parcelTransactAndReadException = transactAndReadException(9008, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zzb(String str, int i2, int i3) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        Parcel parcelTransactAndReadException = transactAndReadException(18001, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzc(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(8013, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zze(zzu zzuVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(22026, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzf(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8014, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(int i2, byte[] bArr, int i3, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeByteArray(bArr);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(10012, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(5059, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzc(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(21007, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(22028, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zze(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8010, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzf(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(17001, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(PlayerEntity playerEntity) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, playerEntity);
        Parcel parcelTransactAndReadException = transactAndReadException(15503, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(5026, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzc(zzu zzuVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(zzu zzuVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(12011, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zze(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(12016, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzf(String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_QUEST_NO_LONGER_AVAILABLE, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(RoomEntity roomEntity, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, roomEntity);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        Parcel parcelTransactAndReadException = transactAndReadException(9011, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(22016, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzc(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8006, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8009, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(String str, boolean z2, boolean z3, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z3);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        Parcel parcelTransactAndReadException = transactAndReadException(12001, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(8012, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzc(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(8027, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(12002, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final Intent zza(int[] iArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeIntArray(iArr);
        Parcel parcelTransactAndReadException = transactAndReadException(12030, parcelObtainAndWriteInterfaceToken);
        Intent intent = (Intent) com.google.android.gms.internal.games.zzc.zza(parcelTransactAndReadException, Intent.CREATOR);
        parcelTransactAndReadException.recycle();
        return intent;
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(8005, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzd(String str, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(5028, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(5001, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str, int i2, int i3, int i4, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        parcelObtainAndWriteInterfaceToken.writeInt(i4);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(5020, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(5005, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str, int i2, IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_PARTICIPANT_NOT_CONNECTED, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(Contents contents) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, contents);
        transactAndReadExceptionReturnVoid(12019, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str, IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(5024, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(5002, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str, String str2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(12009, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(10016, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String str, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(13006, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int i2, int i3, int i4) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        parcelObtainAndWriteInterfaceToken.writeInt(i4);
        transactAndReadExceptionReturnVoid(10009, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int i2, int i3, String[] strArr, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(8004, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(zzu zzuVar, String[] strArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int i2, boolean z2, boolean z3) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z3);
        transactAndReadExceptionReturnVoid(5015, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zzb(String str, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(5029, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int i2, int[] iArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeIntArray(iArr);
        transactAndReadExceptionReturnVoid(10018, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(5058, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, Bundle bundle, int i2, int i3) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        transactAndReadExceptionReturnVoid(5021, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, IBinder iBinder, int i2, String[] strArr, Bundle bundle, boolean z2, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, false);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(5030, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, IBinder iBinder, String str, boolean z2, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, false);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(5031, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(5032, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, int i2, int i3, int i4, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        parcelObtainAndWriteInterfaceToken.writeInt(i4);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(5019, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, int i2, IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(5025, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, int i2, boolean z2, boolean z3) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z3);
        transactAndReadExceptionReturnVoid(9020, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, long j2, String str2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_INVALID_REAL_TIME_ROOM_ID, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(5023, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, com.google.android.gms.games.snapshot.zze zzeVar, Contents contents) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzeVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, contents);
        transactAndReadExceptionReturnVoid(12007, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, String str2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(8011, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, String str2, int i2, int i3) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(null);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(i3);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MILESTONE_CLAIM_FAILED, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, String str2, com.google.android.gms.games.snapshot.zze zzeVar, Contents contents) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzeVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, contents);
        transactAndReadExceptionReturnVoid(12033, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, boolean z2, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(15001, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeByteArray(bArr);
        parcelObtainAndWriteInterfaceToken.writeString(str2);
        parcelObtainAndWriteInterfaceToken.writeTypedArray(participantResultArr, 0);
        transactAndReadExceptionReturnVoid(8007, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeByteArray(bArr);
        parcelObtainAndWriteInterfaceToken.writeTypedArray(participantResultArr, 0);
        transactAndReadExceptionReturnVoid(8008, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, boolean z2, String[] strArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(12031, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, int[] iArr, int i2, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeIntArray(iArr);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(12010, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String[] strArr) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        transactAndReadExceptionReturnVoid(GamesActivityResultCodes.RESULT_NETWORK_FAILURE, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzu zzuVar, String[] strArr, boolean z2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        parcelObtainAndWriteInterfaceToken.writeStringArray(strArr);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, z2);
        transactAndReadExceptionReturnVoid(12029, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(zzw zzwVar, long j2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzwVar);
        parcelObtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(15501, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(String str, int i2) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        transactAndReadExceptionReturnVoid(12017, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(String str, IBinder iBinder, Bundle bundle) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeStrongBinder(iBinder);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(13002, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.games.internal.zzy
    public final void zza(String str, zzu zzuVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.games.zzc.zza(parcelObtainAndWriteInterfaceToken, zzuVar);
        transactAndReadExceptionReturnVoid(20001, parcelObtainAndWriteInterfaceToken);
    }
}

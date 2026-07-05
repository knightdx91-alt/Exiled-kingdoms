package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzv extends com.google.android.gms.internal.games.zzb implements zzu {
    public zzv() {
        super("com.google.android.gms.games.internal.IGamesCallbacks");
    }

    @Override // com.google.android.gms.internal.games.zzb
    protected final boolean dispatchTransaction(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 6001) {
            onP2PConnected(parcel.readString());
        } else if (i2 == 6002) {
            onP2PDisconnected(parcel.readString());
        } else if (i2 == 13001) {
            zzao((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
        } else if (i2 == 13002) {
            zze(parcel.readInt());
        } else if (i2 == 19001) {
            zza(parcel.readInt(), (VideoCapabilities) com.google.android.gms.internal.games.zzc.zza(parcel, VideoCapabilities.CREATOR));
        } else if (i2 != 19002) {
            switch (i2) {
                case 5001:
                    zza(parcel.readInt(), parcel.readString());
                    break;
                case 5002:
                    zza((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5003:
                    zzb(parcel.readInt(), parcel.readString());
                    break;
                case 5004:
                    zzc((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5005:
                    Parcelable.Creator<DataHolder> creator = DataHolder.CREATOR;
                    zza((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, creator), (DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, creator));
                    break;
                case 5006:
                    zzd((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5007:
                    zze((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5008:
                    zzf((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5009:
                    zzg((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5010:
                    zzh((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                case 5011:
                    zzi((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                    break;
                default:
                    switch (i2) {
                        case 5016:
                            onSignOutComplete();
                            break;
                        case 5017:
                            zzk((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5018:
                            zzs((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5019:
                            zzt((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5020:
                            onLeftRoom(parcel.readInt(), parcel.readString());
                            break;
                        case 5021:
                            zzu((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5022:
                            zzv((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5023:
                            zzw((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5024:
                            zzx((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5025:
                            zzy((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5026:
                            zza((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5027:
                            zzb((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5028:
                            zzc((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5029:
                            zzd((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5030:
                            zze((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5031:
                            zzf((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5032:
                            onRealTimeMessageReceived((RealTimeMessage) com.google.android.gms.internal.games.zzc.zza(parcel, RealTimeMessage.CREATOR));
                            break;
                        case 5033:
                            zza(parcel.readInt(), parcel.readInt(), parcel.readString());
                            break;
                        case 5034:
                            zza(parcel.readInt(), parcel.readString(), com.google.android.gms.internal.games.zzc.zza(parcel));
                            break;
                        case 5035:
                            zzaa((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5036:
                            zzc(parcel.readInt());
                            break;
                        case 5037:
                            zzl((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5038:
                            zzz((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5039:
                            zzab((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5040:
                            zzd(parcel.readInt());
                            break;
                        case GamesStatusCodes.STATUS_VIDEO_UNSUPPORTED /* 9001 */:
                            zzj((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 11001:
                            zzc(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                            break;
                        case 12001:
                            zzag((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 14001:
                            zza((DataHolder[]) parcel.createTypedArray(DataHolder.CREATOR));
                            break;
                        case 15001:
                            zzap((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 17002:
                            zzf(parcel.readInt());
                            break;
                        case 20001:
                            zzar((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20002:
                            zzas((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20003:
                            zzat((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20004:
                            zzau((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20005:
                            zzav((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20006:
                            zzaw((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20007:
                            zzax((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20008:
                            zzay((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20009:
                            zzaz((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20012:
                            zzb((Status) com.google.android.gms.internal.games.zzc.zza(parcel, Status.CREATOR));
                            break;
                        case 20019:
                            onCaptureOverlayStateChanged(parcel.readInt());
                            break;
                        case 20020:
                            zzg(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                            break;
                        default:
                            switch (i2) {
                                case GamesStatusCodes.STATUS_MILESTONE_CLAIM_FAILED /* 8001 */:
                                    zzac((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case GamesStatusCodes.STATUS_QUEST_NO_LONGER_AVAILABLE /* 8002 */:
                                    zza(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                                    break;
                                case GamesStatusCodes.STATUS_QUEST_NOT_STARTED /* 8003 */:
                                    zzn((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8004:
                                    zzo((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8005:
                                    zzp((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8006:
                                    zzq((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8007:
                                    zzc(parcel.readInt(), parcel.readString());
                                    break;
                                case 8008:
                                    zzr((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8009:
                                    onTurnBasedMatchRemoved(parcel.readString());
                                    break;
                                case 8010:
                                    onInvitationRemoved(parcel.readString());
                                    break;
                                default:
                                    switch (i2) {
                                        case GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED /* 10001 */:
                                            zzm((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_SIGN_IN_FAILED /* 10002 */:
                                            onRequestRemoved(parcel.readString());
                                            break;
                                        case GamesActivityResultCodes.RESULT_LICENSE_FAILED /* 10003 */:
                                            zzad((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_APP_MISCONFIGURED /* 10004 */:
                                            zzae((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_LEFT_ROOM /* 10005 */:
                                            zzb(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_NETWORK_FAILURE /* 10006 */:
                                            zzaf((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        default:
                                            switch (i2) {
                                                case 12003:
                                                    zzd(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                                                    break;
                                                case 12004:
                                                    zza((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR), (Contents) com.google.android.gms.internal.games.zzc.zza(parcel, Contents.CREATOR));
                                                    break;
                                                case 12005:
                                                    zzah((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case 12006:
                                                    zzai((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case 12007:
                                                    zzaj((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case 12008:
                                                    zzam((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                default:
                                                    switch (i2) {
                                                        case 12011:
                                                            zzb((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case 12012:
                                                            zzd(parcel.readInt(), parcel.readString());
                                                            break;
                                                        case 12013:
                                                            zzan((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case 12014:
                                                            zzak((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case 12015:
                                                            zze(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                                                            break;
                                                        case 12016:
                                                            zzal((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case 12017:
                                                            DataHolder dataHolder = (DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR);
                                                            String string = parcel.readString();
                                                            Parcelable.Creator<Contents> creator2 = Contents.CREATOR;
                                                            zza(dataHolder, string, (Contents) com.google.android.gms.internal.games.zzc.zza(parcel, creator2), (Contents) com.google.android.gms.internal.games.zzc.zza(parcel, creator2), (Contents) com.google.android.gms.internal.games.zzc.zza(parcel, creator2));
                                                            break;
                                                        default:
                                                            switch (i2) {
                                                                case 19006:
                                                                    zzaq((DataHolder) com.google.android.gms.internal.games.zzc.zza(parcel, DataHolder.CREATOR));
                                                                    break;
                                                                case 19007:
                                                                    zzf(parcel.readInt(), (Bundle) com.google.android.gms.internal.games.zzc.zza(parcel, Bundle.CREATOR));
                                                                    break;
                                                                case 19008:
                                                                    zzg(parcel.readInt());
                                                                    break;
                                                                case 19009:
                                                                    zzh(parcel.readInt());
                                                                    break;
                                                                case 19010:
                                                                    zzi(parcel.readInt());
                                                                    break;
                                                                default:
                                                                    return false;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            zza(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel));
        }
        parcel2.writeNoException();
        return true;
    }
}

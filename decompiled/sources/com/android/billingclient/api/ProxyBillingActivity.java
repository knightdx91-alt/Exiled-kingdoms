package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.android.billingclient.api.g;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.internal.play_billing.zzb;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivity extends Activity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ResultReceiver f1430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ResultReceiver f1431c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1432d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1433e;

    private Intent a() {
        Intent intent = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
        intent.setPackage(getApplicationContext().getPackageName());
        return intent;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007a  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void onActivityResult(int i2, int i3, Intent intent) {
        ResultReceiver resultReceiver;
        Intent intentA;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 || i2 == 110) {
            int iB = zzb.zzd(intent, "ProxyBillingActivity").b();
            if (i3 != -1) {
                zzb.zzj("ProxyBillingActivity", "Activity finished with resultCode " + i3 + " and billing's responseCode: " + iB);
                resultReceiver = this.f1430b;
                if (resultReceiver != null) {
                    resultReceiver.send(iB, intent != null ? intent.getExtras() : null);
                } else {
                    if (intent == null) {
                        intentA = a();
                    } else if (intent.getExtras() != null) {
                        String string = intent.getExtras().getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
                        if (string != null) {
                            intentA = new Intent("com.android.vending.billing.ALTERNATIVE_BILLING");
                            intentA.setPackage(getApplicationContext().getPackageName());
                            intentA.putExtra("ALTERNATIVE_BILLING_USER_CHOICE_DATA", string);
                            intentA.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                        } else {
                            Intent intentA2 = a();
                            intentA2.putExtras(intent.getExtras());
                            intentA2.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                            intentA = intentA2;
                        }
                    } else {
                        intentA = a();
                        zzb.zzj("ProxyBillingActivity", "Got null bundle!");
                        intentA.putExtra("RESPONSE_CODE", 6);
                        intentA.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                        g.a aVar = new g.a();
                        aVar.c(6);
                        aVar.b("An internal error occurred.");
                        intentA.putExtra("FAILURE_LOGGING_PAYLOAD", x.a(22, 2, aVar.a()).zzc());
                        intentA.putExtra("INTENT_SOURCE", "LAUNCH_BILLING_FLOW");
                    }
                    if (i2 == 110) {
                        intentA.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                    }
                    sendBroadcast(intentA);
                }
            } else if (iB != 0) {
                i3 = -1;
                zzb.zzj("ProxyBillingActivity", "Activity finished with resultCode " + i3 + " and billing's responseCode: " + iB);
                resultReceiver = this.f1430b;
                if (resultReceiver != null) {
                }
            } else {
                iB = 0;
                resultReceiver = this.f1430b;
                if (resultReceiver != null) {
                }
            }
        } else if (i2 == 101) {
            int iZza = zzb.zza(intent, "ProxyBillingActivity");
            ResultReceiver resultReceiver2 = this.f1431c;
            if (resultReceiver2 != null) {
                resultReceiver2.send(iZza, intent != null ? intent.getExtras() : null);
            }
        } else {
            zzb.zzj("ProxyBillingActivity", "Got onActivityResult with wrong requestCode: " + i2 + "; skipping...");
        }
        this.f1432d = false;
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        int i2;
        PendingIntent pendingIntent;
        int i3;
        super.onCreate(bundle);
        if (bundle != null) {
            zzb.zzi("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.f1432d = bundle.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (bundle.containsKey("result_receiver")) {
                this.f1430b = (ResultReceiver) bundle.getParcelable("result_receiver");
            } else if (bundle.containsKey("in_app_message_result_receiver")) {
                this.f1431c = (ResultReceiver) bundle.getParcelable("in_app_message_result_receiver");
            }
            this.f1433e = bundle.getBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false);
            return;
        }
        zzb.zzi("ProxyBillingActivity", "Launching Play Store billing flow");
        if (getIntent().hasExtra("BUY_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
            if (getIntent().hasExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT") && getIntent().getBooleanExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false)) {
                this.f1433e = true;
                i3 = 110;
                i2 = i3;
            }
            i2 = 100;
        } else if (getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
            this.f1430b = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
            i2 = 100;
        } else if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT")) {
            pendingIntent = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
            this.f1431c = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
            i3 = Quests.SELECT_COMPLETED_UNCLAIMED;
            i2 = i3;
        } else {
            i2 = 100;
            pendingIntent = null;
        }
        try {
            this.f1432d = true;
            startIntentSenderForResult(pendingIntent.getIntentSender(), i2, new Intent(), 0, 0, 0);
        } catch (IntentSender.SendIntentException e2) {
            zzb.zzk("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e2);
            ResultReceiver resultReceiver = this.f1430b;
            if (resultReceiver != null) {
                resultReceiver.send(6, null);
            } else {
                ResultReceiver resultReceiver2 = this.f1431c;
                if (resultReceiver2 != null) {
                    resultReceiver2.send(0, null);
                } else {
                    Intent intentA = a();
                    if (this.f1433e) {
                        intentA.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                    }
                    intentA.putExtra("RESPONSE_CODE", 6);
                    intentA.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    sendBroadcast(intentA);
                }
            }
            this.f1432d = false;
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        super.onDestroy();
        if (isFinishing() && this.f1432d) {
            Intent intentA = a();
            intentA.putExtra("RESPONSE_CODE", 1);
            intentA.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
            sendBroadcast(intentA);
        }
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        ResultReceiver resultReceiver = this.f1430b;
        if (resultReceiver != null) {
            bundle.putParcelable("result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.f1431c;
        if (resultReceiver2 != null) {
            bundle.putParcelable("in_app_message_result_receiver", resultReceiver2);
        }
        bundle.putBoolean("send_cancelled_broadcast_if_finished", this.f1432d);
        bundle.putBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", this.f1433e);
    }
}

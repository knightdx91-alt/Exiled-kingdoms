package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.c0;
import android.support.v4.app.d0;
import android.support.v4.app.e0;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabr;
import com.google.android.gms.common.api.internal.zabu;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    private String zaap;
    private static final Object mLock = new Object();
    private static final GoogleApiAvailability zaao = new GoogleApiAvailability();
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;

    @SuppressLint({"HandlerLeak"})
    private class zaa extends zal {
        private final Context zaaq;

        public zaa(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zaaq = context.getApplicationContext();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                StringBuilder sb = new StringBuilder(50);
                sb.append("Don't know how to handle this message: ");
                sb.append(i2);
                Log.w("GoogleApiAvailability", sb.toString());
                return;
            }
            int iIsGooglePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zaaq);
            if (GoogleApiAvailability.this.isUserResolvableError(iIsGooglePlayServicesAvailable)) {
                GoogleApiAvailability.this.showErrorNotification(this.zaaq, iIsGooglePlayServicesAvailable);
            }
        }
    }

    GoogleApiAvailability() {
    }

    public static GoogleApiAvailability getInstance() {
        return zaao;
    }

    private final String zag() {
        String str;
        synchronized (mLock) {
            str = this.zaap;
        }
        return str;
    }

    public Task<Void> checkApiAvailability(GoogleApi<?> googleApi, GoogleApi<?>... googleApiArr) {
        Preconditions.checkNotNull(googleApi, "Requested API must not be null.");
        for (GoogleApi<?> googleApi2 : googleApiArr) {
            Preconditions.checkNotNull(googleApi2, "Requested API must not be null.");
        }
        ArrayList arrayList = new ArrayList(googleApiArr.length + 1);
        arrayList.add(googleApi);
        arrayList.addAll(Arrays.asList(googleApiArr));
        return GoogleApiManager.zabc().zaa(arrayList).continueWith(new com.google.android.gms.common.zaa(this));
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(Context context) {
        return super.getClientVersion(context);
    }

    public Dialog getErrorDialog(Activity activity, int i2, int i3) {
        return getErrorDialog(activity, i2, i3, null);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, int i2, String str) {
        return super.getErrorResolutionIntent(context, i2, str);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i2, int i3) {
        return super.getErrorResolutionPendingIntent(context, i2, i3);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final String getErrorString(int i2) {
        return super.getErrorString(i2);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @HideFirstParty
    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final boolean isUserResolvableError(int i2) {
        return super.isUserResolvableError(i2);
    }

    public Task<Void> makeGooglePlayServicesAvailable(Activity activity) {
        int i2 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
        int iIsGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity, i2);
        if (iIsGooglePlayServicesAvailable == 0) {
            return Tasks.forResult(null);
        }
        zabu zabuVarZac = zabu.zac(activity);
        zabuVarZac.zab(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        return zabuVarZac.getTask();
    }

    @TargetApi(26)
    public void setDefaultNotificationChannelId(Context context, String str) {
        if (PlatformVersion.isAtLeastO()) {
            Preconditions.checkNotNull(((NotificationManager) context.getSystemService("notification")).getNotificationChannel(str));
        }
        synchronized (mLock) {
            this.zaap = str;
        }
    }

    public boolean showErrorDialogFragment(Activity activity, int i2, int i3) {
        return showErrorDialogFragment(activity, i2, i3, null);
    }

    public void showErrorNotification(Context context, int i2) {
        zaa(context, i2, (String) null, getErrorResolutionPendingIntent(context, i2, 0, "n"));
    }

    public final boolean zaa(Activity activity, LifecycleFragment lifecycleFragment, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        Dialog dialogZaa = zaa(activity, i2, DialogRedirect.getInstance(lifecycleFragment, getErrorResolutionIntent(activity, i2, "d"), 2), onCancelListener);
        if (dialogZaa == null) {
            return false;
        }
        zaa(activity, dialogZaa, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    public Dialog getErrorDialog(Activity activity, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        return zaa(activity, i2, DialogRedirect.getInstance(activity, getErrorResolutionIntent(activity, i2, "d"), i3), onCancelListener);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, ConnectionResult connectionResult) {
        return connectionResult.hasResolution() ? connectionResult.getResolution() : getErrorResolutionPendingIntent(context, connectionResult.getErrorCode(), 0);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @ShowFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context, int i2) {
        return super.isGooglePlayServicesAvailable(context, i2);
    }

    public boolean showErrorDialogFragment(Activity activity, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        Dialog errorDialog = getErrorDialog(activity, i2, i3, onCancelListener);
        if (errorDialog == null) {
            return false;
        }
        zaa(activity, errorDialog, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    public void showErrorNotification(Context context, ConnectionResult connectionResult) {
        zaa(context, connectionResult.getErrorCode(), (String) null, getErrorResolutionPendingIntent(context, connectionResult));
    }

    public final boolean zaa(Context context, ConnectionResult connectionResult, int i2) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent == null) {
            return false;
        }
        zaa(context, connectionResult.getErrorCode(), (String) null, GoogleApiActivity.zaa(context, errorResolutionPendingIntent, i2));
        return true;
    }

    public static Dialog zaa(Activity activity, DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(ConnectionErrorMessages.getErrorMessage(activity, 18));
        builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = builder.create();
        zaa(activity, alertDialogCreate, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return alertDialogCreate;
    }

    public final zabq zaa(Context context, zabr zabrVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zabq zabqVar = new zabq(zabrVar);
        context.registerReceiver(zabqVar, intentFilter);
        zabqVar.zac(context);
        if (isUninstalledAppPossiblyUpdating(context, "com.google.android.gms")) {
            return zabqVar;
        }
        zabrVar.zas();
        zabqVar.unregister();
        return null;
    }

    static Dialog zaa(Context context, int i2, DialogRedirect dialogRedirect, DialogInterface.OnCancelListener onCancelListener) {
        if (i2 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(ConnectionErrorMessages.getErrorMessage(context, i2));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, i2);
        if (errorDialogButtonMessage != null) {
            builder.setPositiveButton(errorDialogButtonMessage, dialogRedirect);
        }
        String errorTitle = ConnectionErrorMessages.getErrorTitle(context, i2);
        if (errorTitle != null) {
            builder.setTitle(errorTitle);
        }
        return builder.create();
    }

    static void zaa(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        if (activity instanceof FragmentActivity) {
            SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else {
            ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        }
    }

    @TargetApi(20)
    private final void zaa(Context context, int i2, String str, PendingIntent pendingIntent) {
        int i3;
        if (i2 == 18) {
            zaa(context);
            return;
        }
        if (pendingIntent == null) {
            if (i2 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String errorNotificationTitle = ConnectionErrorMessages.getErrorNotificationTitle(context, i2);
        String errorNotificationMessage = ConnectionErrorMessages.getErrorNotificationMessage(context, i2);
        Resources resources = context.getResources();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        e0 e0Var = new e0(context);
        e0Var.h();
        e0Var.c();
        e0Var.g(errorNotificationTitle);
        d0 d0Var = new d0();
        d0Var.b(errorNotificationMessage);
        e0Var.k(d0Var);
        if (DeviceProperties.isWearable(context)) {
            Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
            e0Var.j(context.getApplicationInfo().icon);
            e0Var.i();
            if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                e0Var.f203b.add(new c0(com.google.android.gms.base.R.drawable.common_full_open_on_phone, resources.getString(com.google.android.gms.base.R.string.common_open_on_phone), pendingIntent));
            } else {
                e0Var.e(pendingIntent);
            }
        } else {
            e0Var.j(android.R.drawable.stat_sys_warning);
            e0Var.l(resources.getString(com.google.android.gms.base.R.string.common_google_play_services_notification_ticker));
            e0Var.m(System.currentTimeMillis());
            e0Var.e(pendingIntent);
            e0Var.f(errorNotificationMessage);
        }
        if (PlatformVersion.isAtLeastO()) {
            Preconditions.checkState(PlatformVersion.isAtLeastO());
            String strZag = zag();
            if (strZag == null) {
                strZag = "com.google.android.gms.availability";
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                String defaultNotificationChannelName = ConnectionErrorMessages.getDefaultNotificationChannelName(context);
                if (notificationChannel == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", defaultNotificationChannelName, 4));
                } else if (!defaultNotificationChannelName.equals(notificationChannel.getName())) {
                    notificationChannel.setName(defaultNotificationChannelName);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
            e0Var.d(strZag);
        }
        Notification notificationA = e0Var.a();
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
            i3 = 10436;
        } else {
            i3 = 39789;
        }
        notificationManager.notify(i3, notificationA);
    }

    final void zaa(Context context) {
        new zaa(context).sendEmptyMessageDelayed(1, 120000L);
    }
}

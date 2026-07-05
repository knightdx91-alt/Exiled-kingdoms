package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    public static DialogRedirect getInstance(Activity activity, Intent intent, int i2) {
        return new zac(intent, activity, i2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        try {
            redirect();
        } catch (ActivityNotFoundException e2) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e2);
        } finally {
            dialogInterface.dismiss();
        }
    }

    protected abstract void redirect();

    public static DialogRedirect getInstance(Fragment fragment, Intent intent, int i2) {
        return new zad(intent, fragment, i2);
    }

    public static DialogRedirect getInstance(LifecycleFragment lifecycleFragment, Intent intent, int i2) {
        return new zae(intent, lifecycleFragment, i2);
    }
}

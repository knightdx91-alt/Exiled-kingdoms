package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.e;
import android.support.v4.app.i;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SupportErrorDialogFragment extends e {
    private Dialog mDialog = null;
    private DialogInterface.OnCancelListener zaan = null;

    public static SupportErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, null);
    }

    @Override // android.support.v4.app.e, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.zaan;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.support.v4.app.e
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    @Override // android.support.v4.app.e
    public void show(i iVar, String str) {
        super.show(iVar, str);
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        supportErrorDialogFragment.mDialog = dialog2;
        if (onCancelListener != null) {
            supportErrorDialogFragment.zaan = onCancelListener;
        }
        return supportErrorDialogFragment;
    }
}

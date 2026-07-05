package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

/* JADX INFO: compiled from: BaseFragmentActivityApi16.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class d extends c {
    boolean mStartedActivityFromFragment;

    d() {
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i2 != -1) {
            c.checkForValidRequestCode(i2);
        }
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i2 != -1) {
            c.checkForValidRequestCode(i2);
        }
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}

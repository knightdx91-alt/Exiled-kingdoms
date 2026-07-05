package com.google.android.gms.common.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ISignInButtonCreator extends IInterface {
    IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i2, int i3);

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig);
}

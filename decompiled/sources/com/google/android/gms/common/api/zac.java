package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
public abstract class zac {
    private static final Map<Object, zac> zacj = new WeakHashMap();
    private static final Object sLock = new Object();

    public abstract void remove(int i2);
}

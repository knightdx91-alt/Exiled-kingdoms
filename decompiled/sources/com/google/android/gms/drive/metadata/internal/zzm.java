package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Collection;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzm<T extends ReflectedParcelable> extends com.google.android.gms.drive.metadata.zza<T> {
    public zzm(String str, Collection<String> collection, Collection<String> collection2, int i2) {
        super(str, collection, collection2, i2);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putParcelable(getName(), (ReflectedParcelable) obj);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return (ReflectedParcelable) bundle.getParcelable(getName());
    }
}

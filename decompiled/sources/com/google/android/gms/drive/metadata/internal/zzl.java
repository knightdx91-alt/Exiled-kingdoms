package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzl<T extends Parcelable> extends com.google.android.gms.drive.metadata.zzb<T> {
    public zzl(String str, Collection<String> collection, Collection<String> collection2, int i2) {
        super(str, collection, collection2, i2);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        Collection collection = (Collection) obj;
        bundle.putParcelableArrayList(getName(), collection instanceof ArrayList ? (ArrayList) collection : new ArrayList<>(collection));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zza
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public Collection<T> zzb(Bundle bundle) {
        return bundle.getParcelableArrayList(getName());
    }
}

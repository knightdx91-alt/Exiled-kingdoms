package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaaq extends zaau {
    private final /* synthetic */ zaak zagi;
    private final ArrayList<Api.Client> zago;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaq(zaak zaakVar, ArrayList<Api.Client> arrayList) {
        super(zaakVar, null);
        this.zagi = zaakVar;
        this.zago = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    public final void zaan() {
        this.zagi.zafs.zaed.zagz = this.zagi.zaat();
        ArrayList<Api.Client> arrayList = this.zago;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Api.Client client = arrayList.get(i2);
            i2++;
            client.getRemoteService(this.zagi.zage, this.zagi.zafs.zaed.zagz);
        }
    }
}

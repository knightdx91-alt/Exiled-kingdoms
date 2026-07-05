package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaa implements FastParser.zaa<Integer> {
    zaa() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Integer zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Integer.valueOf(fastParser.zad(bufferedReader));
    }
}

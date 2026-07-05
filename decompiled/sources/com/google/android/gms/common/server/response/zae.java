package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zae implements FastParser.zaa<Boolean> {
    zae() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Boolean zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Boolean.valueOf(fastParser.zaa(bufferedReader, false));
    }
}

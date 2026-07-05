package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.math.BigDecimal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zah implements FastParser.zaa<BigDecimal> {
    zah() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ BigDecimal zah(FastParser fastParser, BufferedReader bufferedReader) {
        return fastParser.zai(bufferedReader);
    }
}

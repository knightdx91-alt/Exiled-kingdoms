package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zac implements FastParser.zaa<Float> {
    zac() {
    }

    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Float zah(FastParser fastParser, BufferedReader bufferedReader) {
        return Float.valueOf(fastParser.zag(bufferedReader));
    }
}

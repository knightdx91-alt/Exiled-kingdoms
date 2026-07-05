package com.google.firebase.encoders;

import java.io.Writer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface DataEncoder {
    String encode(Object obj);

    void encode(Object obj, Writer writer);
}

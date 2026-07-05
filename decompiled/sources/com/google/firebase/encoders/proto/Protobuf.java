package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.annotations.ExtraProperty;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ExtraProperty
public @interface Protobuf {

    public enum IntEncoding {
        DEFAULT,
        SIGNED,
        FIXED
    }

    IntEncoding intEncoding() default IntEncoding.DEFAULT;

    int tag();
}

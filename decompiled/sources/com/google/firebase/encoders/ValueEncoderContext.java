package com.google.firebase.encoders;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ValueEncoderContext {
    ValueEncoderContext add(double d2);

    ValueEncoderContext add(float f2);

    ValueEncoderContext add(int i2);

    ValueEncoderContext add(long j2);

    ValueEncoderContext add(String str);

    ValueEncoderContext add(boolean z2);

    ValueEncoderContext add(byte[] bArr);
}

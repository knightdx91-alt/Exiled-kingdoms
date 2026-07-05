package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class ProtobufValueEncoderContext implements ValueEncoderContext {
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean encoded = false;
    private boolean skipDefault = false;

    ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext) {
        this.objEncoderCtx = protobufDataEncoderContext;
    }

    private void checkNotUsed() {
        if (this.encoded) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.encoded = true;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(String str) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, str, this.skipDefault);
        return this;
    }

    void resetContext(FieldDescriptor fieldDescriptor, boolean z2) {
        this.encoded = false;
        this.field = fieldDescriptor;
        this.skipDefault = z2;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(float f2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, f2, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(double d2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, d2, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(int i2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, i2, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(long j2) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, j2, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(boolean z2) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, z2, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(byte[] bArr) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, bArr, this.skipDefault);
        return this;
    }
}

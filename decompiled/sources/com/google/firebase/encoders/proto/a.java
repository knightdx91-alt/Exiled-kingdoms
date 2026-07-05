package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.proto.ProtobufEncoder;
import java.util.Map;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class a implements ObjectEncoder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2132a;

    public /* synthetic */ a(int i2) {
        this.f2132a = i2;
    }

    @Override // com.google.firebase.encoders.Encoder
    public final void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        switch (this.f2132a) {
            case 0:
                ProtobufDataEncoderContext.lambda$static$0((Map.Entry) obj, objectEncoderContext);
                break;
            default:
                ProtobufEncoder.Builder.lambda$static$0(obj, objectEncoderContext);
                break;
        }
    }
}

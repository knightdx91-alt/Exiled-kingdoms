package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import c0.c;
import c0.d;
import c0.e;
import c0.f;
import c0.g;
import com.badlogic.gdx.l;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidNet implements l {
    final AndroidApplicationBase app;
    c0.a netJavaImpl;

    public AndroidNet(AndroidApplicationBase androidApplicationBase, AndroidApplicationConfiguration androidApplicationConfiguration) {
        this.app = androidApplicationBase;
        this.netJavaImpl = new c0.a(androidApplicationConfiguration.maxNetThreads);
    }

    public void cancelHttpRequest(l.a aVar) {
        this.netJavaImpl.a(aVar);
    }

    public f newClientSocket(l.d dVar, String str, int i2, g gVar) {
        return new c(str, i2);
    }

    public d newServerSocket(l.d dVar, String str, int i2, e eVar) {
        return new c0.b(str, i2);
    }

    @Override // com.badlogic.gdx.l
    public boolean openURI(String str) {
        final Uri uri = Uri.parse(str);
        if (this.app.getContext().getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW", uri), 65536) == null) {
            return false;
        }
        this.app.runOnUiThread(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidNet.1
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                if (!(AndroidNet.this.app.getContext() instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                AndroidNet.this.app.startActivity(intent);
            }
        });
        return true;
    }

    @Override // com.badlogic.gdx.l
    public void sendHttpRequest(l.a aVar, l.c cVar) {
        this.netJavaImpl.c(aVar, cVar);
    }

    public d newServerSocket(l.d dVar, int i2, e eVar) {
        return new c0.b(null, i2);
    }
}

package android.support.v4.util;

import android.util.Log;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class d extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private StringBuilder f551b = new StringBuilder(VertexAttributes.Usage.Tangent);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f550a = "FragmentManager";

    private void a() {
        StringBuilder sb = this.f551b;
        if (sb.length() > 0) {
            Log.d(this.f550a, sb.toString());
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        a();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                a();
            } else {
                this.f551b.append(c2);
            }
        }
    }
}

package f;

import android.util.Log;
import com.badlogic.gdx.graphics.GL20;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: TypefaceCompatUtil.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean b(File file, InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[GL20.GL_STENCIL_BUFFER_BIT];
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 == -1) {
                    a(fileOutputStream);
                    return true;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            a(fileOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            a(fileOutputStream2);
            throw th;
        }
    }
}

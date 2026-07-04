package c0;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.ObjectMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: NetJavaImpl.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ThreadPoolExecutor f1418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ObjectMap<l.a, HttpURLConnection> f1419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ObjectMap<l.a, l.c> f1420c;

    /* JADX INFO: renamed from: c0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NetJavaImpl.java */
    final class ThreadFactoryC0019a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        AtomicInteger f1421a;

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "NetThread" + this.f1421a.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }

    /* JADX INFO: compiled from: NetJavaImpl.java */
    final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f1422b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ l.a f1423c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ HttpURLConnection f1424d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ l.c f1425e;

        b(boolean z2, l.a aVar, HttpURLConnection httpURLConnection, l.c cVar) {
            this.f1422b = z2;
            this.f1423c = aVar;
            this.f1424d = httpURLConnection;
            this.f1425e = cVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            l.c cVarD;
            String strA;
            a aVar = a.this;
            HttpURLConnection httpURLConnection = this.f1424d;
            l.a aVar2 = this.f1423c;
            try {
                if (this.f1422b && (strA = aVar2.a()) != null) {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF8");
                    try {
                        outputStreamWriter.write(strA);
                        StreamUtils.closeQuietly(outputStreamWriter);
                    } catch (Throwable th) {
                        StreamUtils.closeQuietly(outputStreamWriter);
                        throw th;
                    }
                }
                httpURLConnection.connect();
                c cVar = new c(httpURLConnection);
                try {
                    synchronized (aVar) {
                        cVarD = aVar.f1420c.d(aVar2);
                    }
                    if (cVarD != null) {
                        cVarD.handleHttpResponse(cVar);
                    }
                    httpURLConnection.disconnect();
                } finally {
                    httpURLConnection.disconnect();
                }
            } catch (Exception e2) {
                try {
                    this.f1425e.failed(e2);
                } finally {
                    aVar.b(aVar2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: NetJavaImpl.java */
    static class c implements Net.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final HttpURLConnection f1427a;

        public c(HttpURLConnection httpURLConnection) {
            this.f1427a = httpURLConnection;
            try {
                httpURLConnection.getResponseCode();
            } catch (IOException unused) {
            }
        }

        @Override // com.badlogic.gdx.l.b
        public final byte[] getResult() {
            InputStream errorStream;
            HttpURLConnection httpURLConnection = this.f1427a;
            try {
                errorStream = httpURLConnection.getInputStream();
            } catch (IOException unused) {
                errorStream = httpURLConnection.getErrorStream();
            }
            byte[] bArr = StreamUtils.f1848a;
            if (errorStream == null) {
                return bArr;
            }
            try {
                StreamUtils.a aVar = new StreamUtils.a(Math.max(0, httpURLConnection.getContentLength()));
                byte[] bArr2 = new byte[4096];
                while (true) {
                    int i2 = errorStream.read(bArr2);
                    if (i2 == -1) {
                        return aVar.toByteArray();
                    }
                    aVar.write(bArr2, 0, i2);
                }
            } catch (IOException unused2) {
                return bArr;
            } finally {
                StreamUtils.closeQuietly(errorStream);
            }
        }
    }

    public a(int i2) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        ThreadFactoryC0019a threadFactoryC0019a = new ThreadFactoryC0019a();
        threadFactoryC0019a.f1421a = new AtomicInteger();
        this.f1418a = new ThreadPoolExecutor(0, i2, 60L, timeUnit, synchronousQueue, threadFactoryC0019a);
        this.f1419b = new ObjectMap <>();
        this.f1420c = new ObjectMap <>();
    }

    public final void a(l.a aVar) {
        l.c cVarD;
        synchronized (this) {
            cVarD = this.f1420c.d(aVar);
        }
        if (cVarD != null) {
            cVarD.cancelled();
            b(aVar);
        }
    }

    final synchronized void b(l.a aVar) {
        this.f1419b.k(aVar);
        this.f1420c.k(aVar);
    }

    public final void c(l.a aVar, l.c cVar) {
        URL url;
        if (aVar.d() == null) {
            cVar.failed(new GdxRuntimeException ("can't process a HTTP request without URL set"));
            return;
        }
        try {
            String strC = aVar.c();
            boolean z2 = !strC.equalsIgnoreCase("HEAD");
            boolean z3 = strC.equalsIgnoreCase("POST") || strC.equalsIgnoreCase("PUT") || strC.equalsIgnoreCase("PATCH");
            if (strC.equalsIgnoreCase("GET") || strC.equalsIgnoreCase("HEAD")) {
                String strConcat = "";
                String strA = aVar.a();
                if (strA != null && !"".equals(strA)) {
                    strConcat = "?".concat(strA);
                }
                url = new URL(aVar.d() + strConcat);
            } else {
                url = new URL(aVar.d());
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(z3);
            httpURLConnection.setDoInput(z2);
            httpURLConnection.setRequestMethod(strC);
            HttpURLConnection.setFollowRedirects(true);
            synchronized (this) {
                this.f1419b.j(aVar, httpURLConnection);
                this.f1420c.j(aVar, cVar);
            }
            for (Map.Entry entry : aVar.b().entrySet()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            httpURLConnection.setConnectTimeout(0);
            httpURLConnection.setReadTimeout(0);
            this.f1418a.submit(new b(z3, aVar, httpURLConnection, cVar));
        } catch (Exception e2) {
            try {
                cVar.failed(e2);
            } finally {
                b(aVar);
            }
        }
    }
}

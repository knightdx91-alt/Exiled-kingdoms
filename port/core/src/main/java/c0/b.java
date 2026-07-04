package c0;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/* JADX INFO: compiled from: NetJavaServerSocketImpl.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ServerSocket f1428a;

    public b(String str, int i2) {
        try {
            this.f1428a = new ServerSocket();
            this.f1428a.bind(str != null ? new InetSocketAddress(str, i2) : new InetSocketAddress(i2));
        } catch (Exception e2) {
            throw new GdxRuntimeException ("Cannot create a server socket at port " + i2 + ".", (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        ServerSocket serverSocket = this.f1428a;
        if (serverSocket != null) {
            try {
                serverSocket.close();
                this.f1428a = null;
            } catch (Exception e2) {
                throw new GdxRuntimeException ("Error closing server.", (Throwable) e2);
            }
        }
    }
}

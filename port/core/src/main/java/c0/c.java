package c0;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: compiled from: NetJavaSocketImpl.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Socket f1429a;

    public c(String str, int i2) {
        try {
            this.f1429a = new Socket();
            this.f1429a.connect(new InetSocketAddress(str, i2));
        } catch (Exception e2) {
            throw new m("Error making a socket connection to " + str + ":" + i2, (Throwable) e2);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Socket socket = this.f1429a;
        if (socket != null) {
            try {
                socket.close();
                this.f1429a = null;
            } catch (Exception e2) {
                throw new m("Error closing socket.", (Throwable) e2);
            }
        }
    }
}

package net.fdgames.ek.android.lan;

/* JADX INFO: compiled from: LanSessionManager.java */
/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
final class LanSessionManagerChatSendRunnable implements Runnable {
    private final String message;
    private final LanSessionManager sessionManager;

    LanSessionManagerChatSendRunnable(LanSessionManager lanSessionManager, String str) {
        this.sessionManager = lanSessionManager;
        this.message = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        LanSessionManager lanSessionManager = this.sessionManager;
        if (lanSessionManager != null) {
            lanSessionManager.sendChat(this.message);
        }
    }
}

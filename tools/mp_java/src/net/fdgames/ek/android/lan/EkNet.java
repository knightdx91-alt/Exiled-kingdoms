package net.fdgames.ek.android.lan;

import java.io.PrintWriter;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Network sends without freezing the game (owner report v51: "when I sent a message the other device
 * stopped until the message came through"). The MP engine wrote every packet synchronously from
 * whichever thread produced it, including the game's GL thread (publishLiveState, ekBroadcast,
 * EKWORLD snapshots...). A socket write blocks as soon as the peer's receive window fills (Wi-Fi
 * power-save, a busy peer, an internet hop), so the whole game stopped until the other side caught
 * up. Now:
 *  - sends from the GL thread go into a per-connection queue drained by a background thread, in order;
 *  - sends from other threads (network readers, the chat thread) still write directly, as before;
 *  - every line is written as one unit under the writer's lock (print + newline + flush used to be three
 *    separate calls, so two threads could interleave halves of lines).
 */
public final class EkNet {
    private EkNet() {}

    private static final int MAX_QUEUED = 20000;   // a peer stalled that long is going away anyway
    private static final Map<PrintWriter, Sender> SENDERS = new WeakHashMap<PrintWriter, Sender>();

    /** Replaces LanSessionManager.sendLine(PrintWriter,String) and ClientPeer.send(String). */
    public static void send(PrintWriter w, String line) {
        if (w == null || line == null) {
            return;
        }
        String t = Thread.currentThread().getName();
        if (t == null || !t.startsWith("GLThread")) {
            write(w, line);
            return;
        }
        Sender s;
        synchronized (SENDERS) {
            s = SENDERS.get(w);
            if (s == null || !s.alive) {
                s = new Sender(w);
                SENDERS.put(w, s);
                Thread th = new Thread(s, "EK-send");
                th.setDaemon(true);
                th.start();
            }
            if (s.queue.size() < MAX_QUEUED) {
                s.queue.offer(line);         // under SENDERS: an idle sender can't exit in between
            }
        }
    }

    static void write(PrintWriter w, String line) {
        synchronized (w) {
            w.print(line);
            w.print('\n');
            w.flush();
        }
    }

    private static final class Sender implements Runnable {
        final PrintWriter w;
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        volatile boolean alive = true;

        Sender(PrintWriter w) {
            this.w = w;
        }

        public void run() {
            try {
                while (true) {
                    String line = queue.poll(30, TimeUnit.SECONDS);
                    if (line == null) {
                        synchronized (SENDERS) {
                            if (queue.isEmpty()) {
                                alive = false;       // idle: the next GL send starts a new sender
                                SENDERS.remove(w);
                                return;
                            }
                        }
                        continue;
                    }
                    write(w, line);
                    if (w.checkError()) {            // socket closed: drop what's left
                        queue.clear();
                        alive = false;
                        synchronized (SENDERS) {
                            SENDERS.remove(w);
                        }
                        return;
                    }
                }
            } catch (Throwable e) {
                alive = false;
            }
        }
    }
}

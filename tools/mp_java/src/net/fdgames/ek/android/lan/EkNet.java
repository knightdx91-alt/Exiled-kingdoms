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
 * v59 (owner: "enemy health isn't updated fast enough, NPCs twitch back and forth"): snapshot lines only matter
 * in their newest form. The host queues a whole-level NPCSTATE2 plus a PSTATE 20 times a second; on a link slower
 * than that (the relay, mobile data) the queue grew and every update arrived later and later. Now a snapshot line
 * that is still waiting is replaced in place by the newer one (NPCSTATE2 per connection, PSTATE per player), so
 * the delay stays at one network trip. Every other line (combat, chat, world ops, trades) is still sent in order.
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
            String key = latestKey(line);
            if (key != null) {
                Slot old = s.latest.get(key);
                if (old != null) {
                    old.line = line;         // still queued: send the newest state in its place
                    return;
                }
                Slot slot = new Slot(key, line);
                s.latest.put(key, slot);
                s.queue.offer(slot);
            } else if (s.queue.size() < MAX_QUEUED) {
                s.queue.offer(line);         // under SENDERS: an idle sender can't exit in between
            }
        }
    }

    /** Snapshot lines where only the newest copy matters; null for everything else. */
    static String latestKey(String line) {
        if (line.startsWith("NPCSTATE2\t")) {
            return "NPCSTATE2";
        }
        if (line.startsWith("PSTATE\t")) {
            int a = 7;
            int b = line.indexOf('\t', a);
            return b < 0 ? null : "PSTATE\t" + line.substring(a, b);
        }
        return null;
    }

    private static final class Slot {
        final String key;
        String line;

        Slot(String key, String line) {
            this.key = key;
            this.line = line;
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
        final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
        final Map<String, Slot> latest = new java.util.HashMap<String, Slot>();   // guarded by SENDERS
        volatile boolean alive = true;

        Sender(PrintWriter w) {
            this.w = w;
        }

        public void run() {
            try {
                while (true) {
                    Object item = queue.poll(30, TimeUnit.SECONDS);
                    if (item == null) {
                        synchronized (SENDERS) {
                            if (queue.isEmpty()) {
                                alive = false;       // idle: the next GL send starts a new sender
                                SENDERS.remove(w);
                                return;
                            }
                        }
                        continue;
                    }
                    String line;
                    if (item instanceof Slot) {
                        Slot slot = (Slot) item;
                        synchronized (SENDERS) {
                            latest.remove(slot.key);     // from here on a newer state queues a fresh slot
                            line = slot.line;
                        }
                    } else {
                        line = (String) item;
                    }
                    write(w, line);
                    if (w.checkError()) {            // socket closed: drop what's left
                        queue.clear();
                        synchronized (SENDERS) {
                            latest.clear();
                        }
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

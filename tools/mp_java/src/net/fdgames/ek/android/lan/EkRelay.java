package net.fdgames.ek.android.lan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;

/**
 * Online play by room code through the relay (deobf/RELAY_SPEC.md). Works on any network, mobile data included:
 * both phones only connect OUT to the relay. The multiplayer engine is untouched: on the host the relay's
 * joiners arrive as connections from 127.0.0.1 to the game's own host port; on the joiner the game's normal
 * join connects to a local listener that forwards through the relay.
 */
public final class EkRelay {
    private EkRelay() {}

    /** Set once the Cloudflare Worker is deployed (ek-relay.<account>.workers.dev). */
    static final String DEFAULT_URL = "";
    static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final SecureRandom RND = new SecureRandom();

    private static volatile HostSession hosting;

    static String relayUrl(android.app.Activity a) {
        String p = System.getProperty("ek.relay");
        if (p != null && p.length() > 0) {
            return p;
        }
        try {
            String s = a == null ? null : a.getSharedPreferences(EkFriends.PREFS, 0).getString("ek_relay_url", null);
            if (s != null && s.trim().length() > 0) {
                return s.trim();
            }
        } catch (Throwable e) {
            // default
        }
        return DEFAULT_URL;
    }

    static String newCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHABET.charAt(RND.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    static String cleanCode(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toUpperCase().toCharArray()) {
            if (ALPHABET.indexOf(c) >= 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static boolean isLoopback(String ip) {
        return ip != null && (ip.startsWith("127.") || ip.equals("::1") || ip.equals("0:0:0:0:0:0:0:1"));
    }

    // ---- pipes ----------------------------------------------------------------------------------

    /** socket -> relay (binary) */
    private static void pumpUp(final Socket s, final EkWs ws) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                byte[] buf = new byte[32768];
                try {
                    InputStream in = s.getInputStream();
                    int n;
                    while ((n = in.read(buf)) > 0) {
                        ws.sendBinary(buf, 0, n);
                    }
                } catch (Throwable e) {
                    // closed
                } finally {
                    ws.close();
                    closeQuietly(s);
                }
            }
        }, "ek-relay-up");
        t.setDaemon(true);
        t.start();
    }

    /** relay -> socket; text messages (OK etc.) are control, not game data */
    private static void pumpDown(final EkWs ws, final Socket s) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    OutputStream out = s.getOutputStream();
                    Object[] m;
                    while ((m = ws.read()) != null) {
                        if (((Integer) m[0]) == EkWs.BINARY) {
                            byte[] b = (byte[]) m[1];
                            out.write(b);
                            out.flush();
                        }
                    }
                } catch (Throwable e) {
                    // closed
                } finally {
                    ws.close();
                    closeQuietly(s);
                }
            }
        }, "ek-relay-down");
        t.setDaemon(true);
        t.start();
    }

    private static void closeQuietly(Socket s) {
        try {
            s.close();
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- host ------------------------------------------------------------------------------------

    interface Status {
        void update(String text);
    }

    /** Host online: keeps a control socket open (reconnecting) and bridges every joiner to the local game port. */
    static final class HostSession implements Runnable {
        final String url;
        volatile String code;
        final String dev;
        final int localPort;
        final Status status;
        volatile boolean stop;
        volatile EkWs ctrl;

        HostSession(String url, String code, String dev, int localPort, Status status) {
            this.url = url;
            this.code = code;
            this.dev = dev;
            this.localPort = localPort;
            this.status = status;
        }

        public void run() {
            int backoff = 2000;
            while (!stop) {
                try {
                    EkWs ws = EkWs.connect(url + "/?code=" + code + "&role=host&dev=" + dev, 10000);
                    ctrl = ws;
                    ws.setReadTimeout(60000);
                    Object[] m = ws.read();
                    if (m == null) {
                        if (ws.closeCode == 4409) {           // someone else holds this code
                            code = newCode();
                            status.update("NEWCODE " + code);
                            continue;
                        }
                        throw new IOException("closed " + ws.closeCode);
                    }
                    status.update("ONLINE " + code);
                    backoff = 2000;
                    startPinger(ws);
                    while (!stop && (m = ws.read()) != null) {
                        if (((Integer) m[0]) == EkWs.TEXT) {
                            String t = new String((byte[]) m[1], "UTF-8");
                            if (t.startsWith("CONN ")) {
                                bridge(t.substring(5).trim());
                            }
                        }
                    }
                } catch (Throwable e) {
                    if (!stop) {
                        status.update("RETRY " + e.getMessage());
                    }
                }
                if (ctrl != null) {
                    ctrl.close();
                }
                if (!stop) {
                    try {
                        Thread.sleep(backoff);
                    } catch (InterruptedException e) {
                        return;
                    }
                    backoff = Math.min(backoff * 2, 30000);
                }
            }
        }

        private void startPinger(final EkWs ws) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (!stop && ctrl == ws) {
                            Thread.sleep(25000);
                            ws.sendText("PING");
                        }
                    } catch (Throwable e) {
                        ws.close();
                    }
                }
            }, "ek-relay-ping");
            t.setDaemon(true);
            t.start();
        }

        private void bridge(final String token) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    EkWs data = null;
                    Socket game = null;
                    try {
                        data = EkWs.connect(url + "/?code=" + code + "&role=accept&token=" + token, 10000);
                        game = new Socket(InetAddress.getByName("127.0.0.1"), localPort);
                        game.setTcpNoDelay(true);
                        pumpUp(game, data);
                        pumpDown(data, game);
                    } catch (Throwable e) {
                        if (data != null) {
                            data.close();
                        }
                        if (game != null) {
                            closeQuietly(game);
                        }
                    }
                }
            }, "ek-relay-bridge");
            t.setDaemon(true);
            t.start();
        }

        void shutdown() {
            stop = true;
            if (ctrl != null) {
                ctrl.close();
            }
        }
    }

    // ---- join ------------------------------------------------------------------------------------

    /**
     * Opens the relay path to a room and a local listener the game can join. Returns the local port, or throws
     * IOException("NOROOM"/"TIMEOUT"/...) with a reason.
     */
    static int openJoin(String url, String code) throws IOException {
        final EkWs ws = EkWs.connect(url + "/?code=" + code + "&role=join", 10000);
        ws.setReadTimeout(20000);
        Object[] m;
        try {
            m = ws.read();
        } catch (java.net.SocketTimeoutException e) {
            ws.close();
            throw new IOException("TIMEOUT");
        }
        if (m == null) {
            throw new IOException(ws.closeCode == 4404 ? "NOROOM" : ws.closeCode == 4408 ? "TIMEOUT" : "CLOSED " + ws.closeCode);
        }
        ws.setReadTimeout(0);
        final ServerSocket ss = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"));
        ss.setSoTimeout(30000);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Socket game = ss.accept();
                    game.setTcpNoDelay(true);
                    pumpUp(game, ws);
                    pumpDown(ws, game);
                } catch (Throwable e) {
                    ws.close();
                } finally {
                    try {
                        ss.close();
                    } catch (Throwable e) {
                        // ignore
                    }
                }
            }
        }, "ek-relay-join");
        t.setDaemon(true);
        t.start();
        return ss.getLocalPort();
    }

    // ---- UI (lobby buttons) ----------------------------------------------------------------------

    private static String pref(android.app.Activity a, String k, String d) {
        try {
            return a.getSharedPreferences(EkFriends.PREFS, 0).getString(k, d);
        } catch (Throwable e) {
            return d;
        }
    }

    private static void putPref(android.app.Activity a, String k, String v) {
        try {
            a.getSharedPreferences(EkFriends.PREFS, 0).edit().putString(k, v).commit();
        } catch (Throwable e) {
            // ignore
        }
    }

    private static boolean ready(final LanLobbyActivity a) {
        if (relayUrl(a).length() == 0) {
            Toast.makeText(a, "Online play isn't set up yet (no relay address in this version).", 1).show();
            return false;
        }
        return true;
    }

    /** "Host online": start hosting if needed, open the room, show the code. Tap again to stop. */
    static void hostOnline(final LanLobbyActivity a) {
        if (!ready(a)) {
            return;
        }
        final HostSession cur = hosting;
        if (cur != null && !cur.stop) {
            new AlertDialog.Builder(a).setTitle("Online room")
                    .setMessage("Your room code: " + cur.code + "\n\nFriends tap Join by code and type it. You still"
                            + " approve each player.")
                    .setPositiveButton("Keep open", null)
                    .setNegativeButton("Close room", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            cur.shutdown();
                            hosting = null;
                            Toast.makeText(a, "Online room closed", 0).show();
                        }
                    }).show();
            return;
        }
        String code = cleanCode(pref(a, "ek_relay_code", ""));
        if (code.length() != 6) {
            code = newCode();
            putPref(a, "ek_relay_code", code);
        }
        String dev = pref(a, "ek_relay_dev", "");
        if (dev.length() < 8) {
            dev = Long.toHexString(RND.nextLong()) + Long.toHexString(RND.nextLong());
            putPref(a, "ek_relay_dev", dev);
        }
        try {
            LanSessionManager m = LanSessionManager.get(a);
            if (m != null && !m.isHosting()) {
                String name = pref(a, "lan_player_name", "Player");
                m.startHosting(name, EkAuto.MAX_PLAYERS);
            }
        } catch (Throwable e) {
            // the room still opens; joins work once hosting runs
        }
        final String startCode = code;
        HostSession hs = new HostSession(relayUrl(a), code, dev, EkNat.GAME_PORT, new Status() {
            boolean shown;

            public void update(final String s) {
                if (s.startsWith("NEWCODE ")) {
                    putPref(a, "ek_relay_code", s.substring(8));
                    return;
                }
                if (s.startsWith("ONLINE ") && !shown) {
                    shown = true;
                    final String c = s.substring(7);
                    a.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                new AlertDialog.Builder(a).setTitle("You're online")
                                        .setMessage("Room code:\n\n        " + c + "\n\nFriends on any Wi-Fi or mobile"
                                                + " data tap Join by code and type it. The code stays the same next"
                                                + " time. Tap Host online again to close the room.")
                                        .setPositiveButton("OK", null).show();
                            } catch (Throwable e) {
                                // ignore
                            }
                        }
                    });
                }
            }
        });
        hosting = hs;
        Thread t = new Thread(hs, "ek-relay-host");
        t.setDaemon(true);
        t.start();
        Toast.makeText(a, "Opening online room " + startCode + "...", 0).show();
    }

    /** "Join by code". */
    static void joinByCode(final LanLobbyActivity a) {
        if (!ready(a)) {
            return;
        }
        final EditText e = new EditText(a);
        e.setHint("Room code, e.g. K7Q4TX");
        e.setSingleLine(true);
        e.setText(pref(a, "ek_relay_last", ""));
        new AlertDialog.Builder(a).setTitle("Join by code").setView(e)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        final String code = cleanCode(e.getText().toString());
                        if (code.length() != 6) {
                            Toast.makeText(a, "A room code has 6 letters/numbers", 1).show();
                            return;
                        }
                        putPref(a, "ek_relay_last", code);
                        Toast.makeText(a, "Finding room " + code + "...", 0).show();
                        final String url = relayUrl(a);
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    final int port = openJoin(url, code);
                                    a.runOnUiThread(new Runnable() {
                                        public void run() {
                                            a.ekJoin("127.0.0.1", port);
                                        }
                                    });
                                } catch (final Throwable err) {
                                    final String why = String.valueOf(err.getMessage());
                                    a.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(a, why.startsWith("NOROOM")
                                                    ? "No open room " + code + ". The host has to tap Host online."
                                                    : why.startsWith("TIMEOUT") ? "The host's game didn't answer. Try again."
                                                    : "Couldn't reach the online relay (" + why + ")", 1).show();
                                        }
                                    });
                                }
                            }
                        }, "ek-relay-joining");
                        t.setDaemon(true);
                        t.start();
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }
}

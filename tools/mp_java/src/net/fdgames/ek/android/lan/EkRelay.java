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
    static final String DEFAULT_URL = "wss://ek-relay.knightdx91.workers.dev";
    static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final SecureRandom RND = new SecureRandom();

    private static volatile HostSession hosting;

    /** Who a relay joiner is, by the host-side local port of its bridge socket (see approveJoin). */
    static final class Joiner {
        String tok;
        String code;
        String name;
    }

    private static final java.util.concurrent.ConcurrentHashMap<Integer, Joiner> JOINERS =
            new java.util.concurrent.ConcurrentHashMap<Integer, Joiner>();

    static Joiner joinerFor(int port) {
        return JOINERS.get(port);
    }

    /**
     * Pairwise friend token: what device A presents when joining room B. Derived from A's private secret and
     * B's room code, so a host that learns it can't reuse it to pose as A anywhere else.
     */
    static String tokFor(String secret, String roomCode) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest((secret + ":" + roomCode).getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(String.format("%02x", h[i] & 0xff));
            }
            return sb.toString();
        } catch (Throwable e) {
            return "-";
        }
    }

    static String enc(String s) {
        try {
            return java.net.URLEncoder.encode(s == null ? "" : s, "UTF-8");
        } catch (Throwable e) {
            return "";
        }
    }

    static String dec(String s) {
        try {
            return java.net.URLDecoder.decode(s == null || s.equals("-") ? "" : s, "UTF-8");
        } catch (Throwable e) {
            return "";
        }
    }

    static String mySecret(android.app.Activity a) {
        String s = pref(a, "ek_relay_secret", "");
        if (s.length() < 16) {
            s = Long.toHexString(RND.nextLong()) + Long.toHexString(RND.nextLong()) + Long.toHexString(RND.nextLong());
            putPref(a, "ek_relay_secret", s);
        }
        return s;
    }

    static String myCode(android.app.Activity a) {
        String code = cleanCode(pref(a, "ek_relay_code", ""));
        if (code.length() != 6) {
            code = newCode();
            putPref(a, "ek_relay_code", code);
        }
        return code;
    }

    static String myName(android.app.Activity a) {
        String n = pref(a, "lan_player_name", "Player");
        return n == null || n.trim().length() == 0 ? "Player" : n.trim();
    }

    /** Friends list: is that room's host online right now? (asks the relay; doesn't start a join) */
    static boolean isOnline(android.app.Activity a, String code) {
        java.net.HttpURLConnection c = null;
        try {
            String u = relayUrl(a).replaceFirst("^wss://", "https://").replaceFirst("^ws://", "http://");
            c = (java.net.HttpURLConnection) new java.net.URL(u + "/?code=" + code + "&role=status").openConnection();
            c.setConnectTimeout(5000);
            c.setReadTimeout(5000);
            java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(c.getInputStream(), "UTF-8"));
            return "online".equals(r.readLine());
        } catch (Throwable e) {
            return false;
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }
    }

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

    interface Hi {
        void hi(String tok, String code, String name);
    }

    private static void pumpDown(final EkWs ws, final Socket s) {
        pumpDown(ws, s, null);
    }

    /** relay -> socket; text messages (OK, HI) are control, not game data */
    private static void pumpDown(final EkWs ws, final Socket s, final Hi onHi) {
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
                        } else if (onHi != null) {
                            String t = new String((byte[]) m[1], "UTF-8");
                            if (t.startsWith("HI ")) {
                                String[] p = t.split(" ");
                                if (p.length >= 4) {
                                    try {
                                        onHi.hi(p[1], p[2], dec(p[3]));
                                    } catch (Throwable e) {
                                        // ignore
                                    }
                                }
                            }
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
        String secret = "";
        String hostName = "Player";
        volatile boolean stop;
        volatile boolean online;                     // the relay confirmed the room at least once
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
                    online = true;
                    status.update("ONLINE " + code);
                    backoff = 2000;
                    startPinger(ws);
                    while (!stop && (m = ws.read()) != null) {
                        if (((Integer) m[0]) == EkWs.TEXT) {
                            String t = new String((byte[]) m[1], "UTF-8");
                            if (t.startsWith("CONN ")) {
                                bridge(t.substring(5).trim().split(" "));
                            }
                        }
                    }
                } catch (Throwable e) {
                    online = false;
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
                    backoff = Math.min(backoff * 2, 300000);   // v66: rooms stay open; don't hammer while offline
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

        /** CONN token [tok mycode name]: bridge that joiner to the local game port and say who we are. */
        private void bridge(final String[] p) {
            final String token = p[0];
            final Joiner who = new Joiner();
            who.tok = p.length > 1 ? p[1] : "-";
            who.code = p.length > 2 ? cleanCode(p[2]) : "";
            who.name = p.length > 3 ? dec(p[3]) : "";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    EkWs data = null;
                    Socket game = null;
                    try {
                        data = EkWs.connect(url + "/?code=" + code + "&role=accept&token=" + token, 10000);
                        if (who.code.length() == 6) {    // our pairwise token for their room, for next time
                            data.sendText("HI " + tokFor(secret, who.code) + " " + code + " " + enc(hostName));
                        }
                        game = new Socket(InetAddress.getByName("127.0.0.1"), localPort);
                        game.setTcpNoDelay(true);
                        final int lp = game.getLocalPort();
                        JOINERS.put(lp, who);
                        final Socket g = game;
                        pumpUp(game, data);
                        pumpDown(data, game);
                        Thread cleanup = new Thread(new Runnable() {
                            public void run() {
                                while (!g.isClosed()) {
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        break;
                                    }
                                }
                                JOINERS.remove(lp);
                            }
                        }, "ek-relay-gc");
                        cleanup.setDaemon(true);
                        cleanup.start();
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

    /** v63 lobby status: the open room's code, or null. */
    static String roomCode() {
        HostSession h = hosting;
        return h != null && !h.stop && h.online ? h.code : null;
    }

    /** v63: Leave, or hosting stopped (joining someone else, session closed): the online room closes too. */
    static void closeRoom() {
        HostSession h = hosting;
        hosting = null;
        if (h != null) {
            h.shutdown();
        }
    }

    // ---- join ------------------------------------------------------------------------------------

    /**
     * Opens the relay path to a room and a local listener the game can join. Returns the local port, or throws
     * IOException("NOROOM"/"TIMEOUT"/...) with a reason.
     */
    static int openJoin(String url, String code) throws IOException {
        return openJoin(url, code, "-", "-", "-", null);
    }

    static int openJoin(String url, String code, String tok, String myCode, String myName, final Hi onHi)
            throws IOException {
        final EkWs ws = EkWs.connect(url + "/?code=" + code + "&role=join&tok=" + enc(tok) + "&mycode=" + enc(myCode)
                + "&name=" + enc(myName), 10000);
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
        if (((Integer) m[0]).intValue() != EkWs.TEXT || !"OK".equals(new String((byte[]) m[1], "UTF-8").trim())) {
            ws.close();                              // v66: only the relay's OK starts the game's connection
            throw new IOException("TIMEOUT");
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
                    pumpDown(ws, game, onHi);
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
    // ---- v66: "open to friends" (deobf/RELAY_SPEC.md "v3"): the room opens by itself while you play ----------
    static final String PREF_OPEN = "ek_open_room";

    static boolean openToFriends(android.app.Activity a) {
        return !"0".equals(pref(a, PREF_OPEN, "1"));
    }

    static void setOpenToFriends(android.app.Activity a, boolean on) {
        putPref(a, PREF_OPEN, on ? "1" : "0");
    }

    /** A room session exists (online or still connecting). */
    static boolean roomActive() {
        HostSession h = hosting;
        return h != null && !h.stop;
    }

    /** "Host": the room's code (and Close room), or open it now. */
    static void hostOnline(final LanLobbyActivity a) {
        if (relayUrl(a).length() == 0) {
            Toast.makeText(a, "Online play isn't set up yet (no relay address in this version).", 1).show();
            return;
        }
        final HostSession cur = hosting;
        if (cur != null && !cur.stop) {
            new AlertDialog.Builder(a).setTitle("Your room")
                    .setMessage("Room code: " + cur.code + "\n\nFriends tap Join by code and type it; friends you've played"
                            + " with join from their Friends list. You still approve new players.")
                    .setPositiveButton("Keep open", null)
                    .setNegativeButton("Close room", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            setOpenToFriends(a, false);
                            closeRoom();
                            Toast.makeText(a, "Room closed. It stays closed until you open it again (Host).", 1).show();
                        }
                    }).show();
            return;
        }
        setOpenToFriends(a, true);
        openRoom(a, false);
    }

    /** Start hosting if needed and open the relay room. quiet = no dialog/toast (auto-open while playing). */
    static void openRoom(final android.app.Activity a, final boolean quiet) {
        if (relayUrl(a).length() == 0 || roomActive()) {
            return;
        }
        String code = myCode(a);
        String dev = pref(a, "ek_relay_dev", "");
        if (dev.length() < 8) {
            dev = Long.toHexString(RND.nextLong()) + Long.toHexString(RND.nextLong());
            putPref(a, "ek_relay_dev", dev);
        }
        try {
            final LanSessionManager m = LanSessionManager.get(a);
            if (m != null && !m.isHosting()) {
                final String name = pref(a, "lan_player_name", "Player");
                Thread h = new Thread(new Runnable() {    // v68: never on the UI thread (sockets)
                    public void run() {
                        try {
                            m.startHosting(name, EkAuto.MAX_PLAYERS);
                        } catch (Throwable e) {
                            // the room still opens; joins work once hosting runs
                        }
                    }
                }, "ek-room-host");
                h.setDaemon(true);
                h.start();
            }
        } catch (Throwable e) {
            // the room still opens; joins work once hosting runs
        }
        HostSession hs = new HostSession(relayUrl(a), code, dev, EkNat.GAME_PORT, new Status() {
            boolean shown = quiet;

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
                                new AlertDialog.Builder(a).setTitle("Your room is open")
                                        .setMessage("Room code:\n\n        " + c + "\n\nFriends on any Wi-Fi or mobile"
                                                + " data tap Join by code and type it. The code stays the same, and the"
                                                + " room opens by itself while you play. Tap Host to close it.")
                                        .setPositiveButton("OK", null).show();
                            } catch (Throwable e) {
                                // ignore
                            }
                        }
                    });
                }
            }
        });
        hs.secret = mySecret(a);
        hs.hostName = myName(a);
        hosting = hs;
        Thread t = new Thread(hs, "ek-relay-host");
        t.setDaemon(true);
        t.start();
        if (!quiet) {
            Toast.makeText(a, "Opening your room...", 0).show();
        }
    }

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
                        joinCode(a, code);
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }

    /** Join a room by code (typed, or a friend tapped in the Friends list). */
    static void joinCode(final LanLobbyActivity a, final String code) {
        if (!ready(a)) {
            return;
        }
        Toast.makeText(a, "Finding room " + code + "...", 0).show();
        final String url = relayUrl(a);
        final String tok = tokFor(mySecret(a), code);
        final String mine = myCode(a);
        final String me = myName(a);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    final int port = openJoin(url, code, tok, mine, me, new Hi() {
                        public void hi(String hostTok, String hostCode, String hostName) {
                            // played together: keep the host as a friend (no code, no approval next time)
                            EkFriends.upsertRelayFriend(a, hostName, hostCode, hostTok);
                        }
                    });
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
                                    ? "Room " + code + " isn't open. The host has to tap Host online."
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
}

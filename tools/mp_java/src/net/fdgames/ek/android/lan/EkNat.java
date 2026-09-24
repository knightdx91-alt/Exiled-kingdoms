package net.fdgames.ek.android.lan;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Internet play without extra apps: while hosting, ask the home router to forward the game port to
 * this device (UPnP Internet Gateway Device), and report the address friends type. deobf/NAT_UPNP_SPEC.md.
 * All router traffic runs on one background thread, in order; nothing here blocks the game.
 */
public final class EkNat {
    private EkNat() {}

    static final int GAME_PORT = 32124;           // LanSessionManager.CHAT_PORT
    private static final int LEASE = 3600;        // seconds; renewed every 20 min while hosting
    private static final String[] SEARCH = {
        "urn:schemas-upnp-org:device:InternetGatewayDevice:1",
        "urn:schemas-upnp-org:device:InternetGatewayDevice:2",
        "urn:schemas-upnp-org:service:WANIPConnection:1",
    };

    private static final ExecutorService WORKER = Executors.newSingleThreadExecutor(new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "EK-NAT");
            t.setDaemon(true);
            return t;
        }
    });

    // state (touched only on the worker, read anywhere)
    private static volatile String status = "Internet: available while you host.";
    private static volatile String controlUrl;
    private static volatile String serviceType;
    private static volatile String localIp;
    private static volatile int mappedPort;        // 0 = no mapping
    private static volatile int leaseUsed;
    private static volatile long lastRenew;
    private static volatile boolean busy;
    private static volatile boolean watching;
    private static volatile long lastFail;       // don't re-probe a router that said no for 5 min

    /** One line for "My address". */
    public static String describe() {
        return status;
    }

    /** Hooked at the start of LanSessionManager.startHosting. */
    public static void onHostStart() {
        // v63: online play goes through the Cloudflare relay; the router is no longer asked to open a port
        // (deobf/LOBBY_CLEANUP_SPEC.md). onHostStop still removes a mapping an older version left open.
        if (!UPNP_ENABLED) {
            return;
        }
        if (busy || mappedPort != 0 || System.currentTimeMillis() - lastFail < 5 * 60 * 1000L) {
            return;
        }
        busy = true;
        status = "Internet: asking your router to open the game port...";
        WORKER.execute(new Runnable() {
            public void run() {
                try {
                    open();
                } catch (Throwable e) {
                    controlUrl = null;
                    status = "Internet: the router stopped answering (" + e.getClass().getSimpleName()
                            + "). Try hosting again; friends on the same Wi-Fi, Tailscale or ZeroTier can still join.";
                } finally {
                    busy = false;
                }
                if (mappedPort == 0) {
                    lastFail = System.currentTimeMillis();
                }
                startWatch();
            }
        });
    }

    /** Hooked at the start of LanSessionManager.stopAll and joinHost. */
    static final boolean UPNP_ENABLED = false;

    public static void onHostStop() {
        EkRelay.closeRoom();
        WORKER.execute(new Runnable() {
            public void run() {
                close();
            }
        });
    }

    // ---- joining: "host:port" -------------------------------------------------------------------

    /** Host part of what the player typed ("1.2.3.4:32125" -> "1.2.3.4"). */
    public static String hostPart(String s) {
        if (s == null) {
            return null;
        }
        String t = s.trim();
        int c = t.lastIndexOf(':');
        if (c > 0 && t.indexOf(':') == c && isDigits(t.substring(c + 1))) {
            return t.substring(0, c).trim();
        }
        return t;
    }

    /** Port from "host:port", else the one the lobby passed. */
    public static int portPart(String s, int dflt) {
        try {
            if (s != null) {
                String t = s.trim();
                int c = t.lastIndexOf(':');
                if (c > 0 && t.indexOf(':') == c && isDigits(t.substring(c + 1))) {
                    int p = Integer.parseInt(t.substring(c + 1));
                    if (p > 0 && p < 65536) {
                        return p;
                    }
                }
            }
        } catch (Throwable e) {
            // fall through
        }
        return dflt;
    }

    // ---- joining your own house's public address (no NAT loopback) -------------------------------

    private static volatile String joinHostResolved;
    private static volatile int joinPortResolved;
    private static volatile long noRouterAt;      // a failed search is remembered for 5 minutes

    /**
     * Hooked in LanSessionManager.joinHost (runs on the lobby's join thread). Splits "host:port"; and
     * when the host is this house's own public address, asks the router which device on the Wi-Fi owns
     * that port and connects there directly. Many home routers refuse a connection from inside the
     * house to their own public address ("NAT loopback"), which is what the owner hit testing two
     * devices at home (ECONNREFUSED to 97.x:32125 while the host was 192.168.1.155).
     */
    public static void resolveJoin(String typed, int dflt) {
        final String h = hostPart(typed);
        final int p = portPart(typed, dflt);
        joinHostResolved = h;
        joinPortResolved = p;
        if (h == null || !isPublic(h) || "main".equals(Thread.currentThread().getName())) {
            return;
        }
        try {
            java.util.concurrent.Future<?> f = WORKER.submit(new Runnable() {
                public void run() {
                    try {
                        if (!EkAuto.hasHomeNetwork()) {
                            return;                 // mobile data: no home router to ask
                        }
                        if (controlUrl == null) {
                            if (System.currentTimeMillis() - noRouterAt < 5 * 60 * 1000L) {
                                return;
                            }
                            if (!discover()) {
                                noRouterAt = System.currentTimeMillis();
                                return;
                            }
                        }
                        if (!h.equals(externalIp())) {
                            return;                 // somebody else's house: connect normally
                        }
                        String r = soap("GetSpecificPortMappingEntry", "<NewRemoteHost></NewRemoteHost>"
                                + "<NewExternalPort>" + p + "</NewExternalPort><NewProtocol>TCP</NewProtocol>");
                        String client = tag(r, "NewInternalClient");
                        String iport = tag(r, "NewInternalPort");
                        if (client != null && client.trim().length() > 0) {
                            joinHostResolved = client.trim();
                            if (iport != null && isDigits(iport.trim())) {
                                joinPortResolved = Integer.parseInt(iport.trim());
                            }
                        }
                    } catch (Throwable e) {
                        // keep the public address
                    }
                }
            });
            f.get(9, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Throwable e) {
            // keep the public address
        }
    }

    public static String joinHost() {
        return joinHostResolved;
    }

    public static int joinPort() {
        return joinPortResolved;
    }

    private static boolean isDigits(String s) {
        if (s.length() == 0 || s.length() > 5) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // ---- router work (worker thread) ------------------------------------------------------------

    private static void open() throws Exception {
        // v54: never reuse a router found on an earlier network (owner on mobile data got
        // "SocketTimeoutException": the game kept calling the home router it had found over Wi-Fi).
        controlUrl = null;
        if (!EkAuto.hasHomeNetwork()) {
            status = "Internet: you're on mobile data. Mobile carriers don't let other players connect in to"
                    + " a phone, so hosting over the internet needs home Wi-Fi (router with UPnP on). On mobile"
                    + " data, use Tailscale or ZeroTier on both devices instead.";
            return;
        }
        if (!discover()) {
            status = "Internet: your router didn't answer (UPnP may be off in its settings). Friends on"
                    + " the same Wi-Fi, Tailscale or ZeroTier can still join.";
            return;
        }
        String ext = externalIp();
        if (ext != null && !isPublic(ext)) {
            status = "Internet: your internet provider shares one public address between customers ("
                    + ext + "), so friends can't reach you directly. Use Tailscale or ZeroTier instead.";
            return;
        }
        int port = 0;
        for (int p = GAME_PORT; p <= GAME_PORT + 10 && port == 0; p++) {
            int err = addMapping(p, LEASE);
            if (err == 725) {                      // OnlyPermanentLeasesSupported
                err = addMapping(p, 0);
                leaseUsed = 0;
            } else {
                leaseUsed = LEASE;
            }
            if (err == 0) {
                port = p;
            } else if (err != 718) {               // 718 = ConflictInMappingEntry -> try the next port
                status = "Internet: your router refused to open the port (UPnP error " + err + "). Friends on"
                        + " the same Wi-Fi, Tailscale or ZeroTier can still join.";
                return;
            }
        }
        if (port == 0) {
            status = "Internet: the router's game ports are all in use by other devices.";
            return;
        }
        mappedPort = port;
        lastRenew = System.currentTimeMillis();
        if (ext == null) {
            ext = externalIp();
        }
        if (ext == null) {
            status = "Internet: port opened, but the router didn't say your public address.";
        } else {
            status = "Internet (friends anywhere): " + ext + (port == GAME_PORT ? "" : ":" + port);
        }
    }

    private static void close() {
        int p = mappedPort;
        mappedPort = 0;
        if (p != 0 && controlUrl != null) {
            try {
                soap("DeletePortMapping", "<NewRemoteHost></NewRemoteHost><NewExternalPort>" + p
                        + "</NewExternalPort><NewProtocol>TCP</NewProtocol>");
            } catch (Throwable e) {
                // the lease runs out on its own
            }
        }
        if (p != 0 && !busy) {
            status = "Internet: available while you host.";
        }
    }

    /** Every 60 s: drop the mapping once hosting stops, renew it before the lease runs out. */
    private static synchronized void startWatch() {
        if (watching) {
            return;
        }
        watching = true;
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60000L);
                    } catch (InterruptedException e) {
                        return;
                    }
                    WORKER.execute(new Runnable() {
                        public void run() {
                            tickWorker();
                        }
                    });
                }
            }
        }, "EK-NAT-watch");
        t.setDaemon(true);
        t.start();
    }

    private static void tickWorker() {
        if (mappedPort == 0) {
            return;
        }
        boolean hosting = false;
        try {
            LanSessionManager m = LanSessionManager.getInstanceIfReady();
            hosting = m != null && m.isHosting();
        } catch (Throwable e) {
            // treat as not hosting
        }
        if (!hosting) {
            close();
            return;
        }
        if (leaseUsed > 0 && System.currentTimeMillis() - lastRenew > 20 * 60 * 1000L) {
            try {
                if (addMapping(mappedPort, leaseUsed) == 0) {
                    lastRenew = System.currentTimeMillis();
                }
            } catch (Throwable e) {
                // try again next minute
            }
        }
    }

    private static int addMapping(int ext, int lease) throws Exception {
        return soapStatus("AddPortMapping", "<NewRemoteHost></NewRemoteHost><NewExternalPort>" + ext
                + "</NewExternalPort><NewProtocol>TCP</NewProtocol><NewInternalPort>" + GAME_PORT
                + "</NewInternalPort><NewInternalClient>" + localIp + "</NewInternalClient>"
                + "<NewEnabled>1</NewEnabled><NewPortMappingDescription>Exiled Kingdoms</NewPortMappingDescription>"
                + "<NewLeaseDuration>" + lease + "</NewLeaseDuration>");
    }

    private static String externalIp() {
        try {
            String r = soap("GetExternalIPAddress", "");
            String ip = tag(r, "NewExternalIPAddress");
            return ip == null || ip.trim().length() == 0 ? null : ip.trim();
        } catch (Throwable e) {
            return null;
        }
    }

    // ---- discovery --------------------------------------------------------------------------------

    private static boolean discover() {
        String target = System.getProperty("ek.ssdp", "239.255.255.250:1900"); // overridable for tests
        String th = target.substring(0, target.lastIndexOf(':'));
        int tp = Integer.parseInt(target.substring(target.lastIndexOf(':') + 1));
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            ds.setSoTimeout(2000);
            InetAddress group = InetAddress.getByName(th);
            for (int round = 0; round < 3; round++) {
                for (String st : SEARCH) {
                    String msg = "M-SEARCH * HTTP/1.1\r\nHOST: 239.255.255.250:1900\r\nST: " + st
                            + "\r\nMAN: \"ssdp:discover\"\r\nMX: 2\r\n\r\n";
                    byte[] b = msg.getBytes("US-ASCII");
                    ds.send(new DatagramPacket(b, b.length, group, tp));
                }
                long until = System.currentTimeMillis() + 2000L;
                while (System.currentTimeMillis() < until) {
                    byte[] buf = new byte[2048];
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    try {
                        ds.receive(p);
                    } catch (java.net.SocketTimeoutException e) {
                        break;
                    }
                    String resp = new String(p.getData(), 0, p.getLength(), "US-ASCII");
                    String loc = header(resp, "LOCATION");
                    if (loc != null && describeDevice(loc)) {
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            // no router
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
        return false;
    }

    /** Read the device XML, pick the WAN connection service, remember its control URL. */
    private static boolean describeDevice(String location) {
        try {
            String xml = httpGet(location);
            String base = tag(xml, "URLBase");
            Matcher m = Pattern.compile("<service>(.*?)</service>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(xml);
            while (m.find()) {
                String svc = m.group(1);
                String type = tag(svc, "serviceType");
                if (type == null) {
                    continue;
                }
                type = type.trim();
                if (type.startsWith("urn:schemas-upnp-org:service:WANIPConnection:")
                        || type.startsWith("urn:schemas-upnp-org:service:WANPPPConnection:")) {
                    String ctl = tag(svc, "controlURL");
                    if (ctl == null) {
                        continue;
                    }
                    URL u = new URL(new URL(base != null && base.trim().length() > 0 ? base.trim() : location), ctl.trim());
                    String lip = localAddressFor(u);
                    if (lip == null) {
                        continue;
                    }
                    controlUrl = u.toString();
                    serviceType = type;
                    localIp = lip;
                    return true;
                }
            }
        } catch (Throwable e) {
            // try the next answer
        }
        return false;
    }

    /** Our address on the interface that reaches the router. */
    private static String localAddressFor(URL u) {
        Socket s = new Socket();
        try {
            int port = u.getPort() > 0 ? u.getPort() : 80;
            s.connect(new InetSocketAddress(u.getHost(), port), 3000);
            InetAddress a = s.getLocalAddress();
            return a == null ? null : a.getHostAddress();
        } catch (Throwable e) {
            return null;
        } finally {
            try {
                s.close();
            } catch (Throwable e) {
                // ignore
            }
        }
    }

    // ---- SOAP / HTTP ------------------------------------------------------------------------------

    private static String soap(String action, String args) throws Exception {
        String[] r = soapCall(action, args);
        if (!"200".equals(r[0])) {
            throw new java.io.IOException("UPnP " + action + " HTTP " + r[0]);
        }
        return r[1];
    }

    /** 0 = OK, else the UPnP errorCode (or the HTTP code when there's none). */
    private static int soapStatus(String action, String args) throws Exception {
        String[] r = soapCall(action, args);
        if ("200".equals(r[0])) {
            return 0;
        }
        String ec = tag(r[1], "errorCode");
        try {
            return ec != null ? Integer.parseInt(ec.trim()) : Integer.parseInt(r[0]);
        } catch (NumberFormatException e) {
            return 501;
        }
    }

    private static String[] soapCall(String action, String args) throws Exception {
        String body = "<?xml version=\"1.0\"?>\r\n<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\""
                + " s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:" + action
                + " xmlns:u=\"" + serviceType + "\">" + args + "</u:" + action + "></s:Body></s:Envelope>\r\n";
        return http("POST", controlUrl, "Content-Type: text/xml; charset=\"utf-8\"\r\nSOAPAction: \""
                + serviceType + "#" + action + "\"\r\n", body.getBytes("UTF-8"));
    }

    private static String httpGet(String url) throws Exception {
        String[] r = http("GET", url, "", null);
        if (!r[0].startsWith("2")) {
            throw new java.io.IOException("HTTP " + r[0]);
        }
        return r[1];
    }

    /**
     * Minimal HTTP/1.0 over a plain socket. Not HttpURLConnection: the game targets SDK 29, where
     * Android refuses cleartext http:// through the URL stack unless the manifest opts in, and a
     * router's UPnP endpoint is always plain http on the local network.
     * Returns {status code, body}.
     */
    private static String[] http(String method, String url, String extraHeaders, byte[] body) throws Exception {
        URL u = new URL(url);
        int port = u.getPort() > 0 ? u.getPort() : 80;
        String path = u.getFile() == null || u.getFile().length() == 0 ? "/" : u.getFile();
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(u.getHost(), port), 4000);
            s.setSoTimeout(6000);
            StringBuilder req = new StringBuilder();
            req.append(method).append(' ').append(path).append(" HTTP/1.0\r\n")
                    .append("Host: ").append(u.getHost()).append(':').append(port).append("\r\n")
                    .append("Connection: close\r\n").append(extraHeaders);
            if (body != null) {
                req.append("Content-Length: ").append(body.length).append("\r\n");
            }
            req.append("\r\n");
            OutputStream os = s.getOutputStream();
            os.write(req.toString().getBytes("US-ASCII"));
            if (body != null) {
                os.write(body);
            }
            os.flush();
            String raw = readAll(s.getInputStream());
            int he = raw.indexOf("\r\n\r\n");
            int hl = 4;
            if (he < 0) {
                he = raw.indexOf("\n\n");
                hl = 2;
            }
            String head = he < 0 ? raw : raw.substring(0, he);
            String resp = he < 0 ? "" : raw.substring(he + hl);
            String[] first = head.split("\r?\n")[0].split(" ");
            String code = first.length > 1 ? first[1] : "0";
            String te = header(head, "Transfer-Encoding");
            if (te != null && te.toLowerCase().contains("chunked")) {
                resp = dechunk(resp);
            }
            return new String[] {code, resp};
        } finally {
            try {
                s.close();
            } catch (Throwable e) {
                // ignore
            }
        }
    }

    private static String dechunk(String s) {
        StringBuilder out = new StringBuilder();
        int i = 0;
        try {
            while (i < s.length()) {
                int nl = s.indexOf("\r\n", i);
                if (nl < 0) {
                    break;
                }
                String hex = s.substring(i, nl).trim();
                int sc = hex.indexOf(';');
                int n = Integer.parseInt(sc >= 0 ? hex.substring(0, sc) : hex, 16);
                if (n == 0) {
                    break;
                }
                out.append(s, nl + 2, Math.min(s.length(), nl + 2 + n));
                i = nl + 2 + n + 2;
            }
        } catch (Throwable e) {
            // return what we have
        }
        return out.toString();
    }

    private static String readAll(InputStream in) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int n;
        while ((n = in.read(buf)) > 0 && bo.size() < 256 * 1024) {
            bo.write(buf, 0, n);
        }
        in.close();
        return bo.toString("UTF-8");
    }

    // ---- small helpers ----------------------------------------------------------------------------

    private static String header(String resp, String name) {
        for (String line : resp.split("\r?\n")) {
            int c = line.indexOf(':');
            if (c > 0 && line.substring(0, c).trim().equalsIgnoreCase(name)) {
                return line.substring(c + 1).trim();
            }
        }
        return null;
    }

    /** Text of the first <name> (any namespace prefix). */
    static String tag(String xml, String name) {
        if (xml == null) {
            return null;
        }
        Matcher m = Pattern.compile("<(?:\\w+:)?" + name + "(?:\\s[^>]*)?>(.*?)</(?:\\w+:)?" + name + ">",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(xml);
        return m.find() ? m.group(1) : null;
    }

    /** Not private (RFC 1918), not carrier-shared (100.64/10), not loopback/link-local. */
    static boolean isPublic(String ip) {
        try {
            String[] p = ip.split("\\.");
            if (p.length != 4) {
                return false;
            }
            int a = Integer.parseInt(p[0]);
            int b = Integer.parseInt(p[1]);
            if (a == 10 || a == 127 || a == 0 || a >= 224) {
                return false;
            }
            if (a == 172 && b >= 16 && b <= 31) {
                return false;
            }
            if (a == 192 && b == 168) {
                return false;
            }
            if (a == 169 && b == 254) {
                return false;
            }
            if (a == 100 && b >= 64 && b <= 127) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}

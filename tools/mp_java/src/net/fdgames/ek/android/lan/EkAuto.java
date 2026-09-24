package net.fdgames.ek.android.lan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.badlogic.gdx.Gdx;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.fdgames.GameWorld.GameData;
import net.fdgames.ek.android.MainActivity;

/**
 * Shared-world phase A (deobf/SHARED_WORLD_SPEC.md §5): the game hosts in the background while you
 * play; joining someone stops it, leaving restarts it. Strangers need the host's OK; friends don't.
 */
public final class EkAuto {
    private EkAuto() {}

    static final String PREF_AUTO = "ek_autohost";
    static final int MAX_PLAYERS = 6;
    static final long JOIN_GRACE_MS = 20000L;

    private static long lastCheck;
    private static volatile long lastJoinAttempt;
    private static volatile boolean starting;

    private static MainActivity activity() {
        Object a = Gdx.app;
        return a instanceof MainActivity ? (MainActivity) a : null;
    }

    public static boolean autoHostEnabled(android.app.Activity a) {
        try {
            return !"0".equals(a.getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_AUTO, "1"));
        } catch (Throwable e) {
            return true;
        }
    }

    public static void setAutoHost(android.app.Activity a, boolean on) {
        try {
            a.getSharedPreferences(EkFriends.PREFS, 0).edit().putString(PREF_AUTO, on ? "1" : "0").commit();
            if (!on) {
                LanSessionManager m = LanSessionManager.getInstanceIfReady();
                if (m != null && m.isHosting() && m.getPlayerCount() < 2) {
                    m.stopAll();
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Hooked at the start of LanSessionManager.joinHost: keep the keeper from re-hosting mid-join. */
    public static void noteJoin() {
        lastJoinAttempt = System.currentTimeMillis();
        EkShare.prepareJoin();
    }

    /** Called every frame from the game screen (before LanGameBridge.tick); runs every 3 s. */
    public static void tick() {
        try {
            long now = System.currentTimeMillis();
            if (now - lastCheck < 3000L) {
                return;
            }
            lastCheck = now;
            EkShare.tick();
            EkItems.pvpTick();
            final MainActivity a = activity();
            GameData gd = GameData.O();
            if (a == null || gd == null || gd.player == null || starting) {
                return;
            }
            if (now - lastJoinAttempt < JOIN_GRACE_MS || !autoHostEnabled(a)) {
                return;
            }
            final LanSessionManager m = LanSessionManager.get(a);
            if (m == null || m.isHosting() || m.ekConnected()) {
                return;
            }
            String name = null;
            try {
                name = a.getSharedPreferences(EkFriends.PREFS, 0).getString("lan_player_name", null);
            } catch (Throwable e) {
                name = null;
            }
            if (name == null || name.trim().length() == 0) {
                name = gd.player.getName();
            }
            final String host = name == null ? "Player" : name.trim();
            starting = true;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        m.startHosting(host, MAX_PLAYERS);
                    } catch (Throwable e) {
                        // ignore: try again on a later tick
                    } finally {
                        starting = false;
                    }
                }
            }, "ek-autohost");
            t.setDaemon(true);
            t.start();
        } catch (Throwable e) {
            starting = false;
        }
    }

    // ---- join approval (hooked in LanSessionManager.handleIncomingClient) --------------------------

    /** true = let this player in. Friends (by address) always; others after the host taps Allow. */
    public static boolean approveJoin(Socket s, String name) {
        try {
            // v55: show the request on the screen that's in front. It used to go to the game screen,
            // which sits behind the multiplayer lobby, so the host only saw it after leaving the lobby
            // and the joiner thought joining had failed.
            final android.app.Activity lob = EkFriends.lobbyIfOpen();
            final android.app.Activity a = lob != null ? lob : activity();
            if (a == null || s == null) {
                return false;
            }
            final String ip = s.getInetAddress().getHostAddress();
            List<EkFriends.Friend> friends = EkFriends.load(a);
            for (EkFriends.Friend f : friends) {
                if (f.ip.equals(ip) && !EkRelay.isLoopback(ip)) {
                    return true;
                }
            }
            // online (relay) joiners arrive as 127.0.0.1: recognise friends by their pairwise token
            final EkRelay.Joiner rj = EkRelay.isLoopback(ip) ? EkRelay.joinerFor(s.getPort()) : null;
            if (rj != null && EkFriends.isRelayFriend(a, rj.code, rj.tok)) {
                return true;
            }
            final String who = name == null || name.trim().length() == 0 ? "A player" : name.trim();
            final CountDownLatch done = new CountDownLatch(1);
            final int[] answer = {0}; // 0 deny, 1 allow, 2 allow + friend
            a.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        new AlertDialog.Builder(a).setTitle("Join request")
                                .setMessage(who + (EkRelay.isLoopback(ip) ? " (by room code)" : " (same Wi-Fi)") + " wants to join your game.")
                                .setCancelable(false)
                                .setPositiveButton("Allow + add friend", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        answer[0] = 2;
                                        done.countDown();
                                    }
                                })
                                .setNeutralButton("Allow once", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        answer[0] = 1;
                                        done.countDown();
                                    }
                                })
                                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int w) {
                                        answer[0] = 0;
                                        done.countDown();
                                    }
                                }).show();
                    } catch (Throwable e) {
                        done.countDown();
                    }
                }
            });
            if (!done.await(45, TimeUnit.SECONDS)) {
                return false;
            }
            if (answer[0] == 2) {
                if (rj != null) {
                    EkFriends.upsertRelayFriend(a, rj.name != null && rj.name.length() > 0 ? rj.name : who, rj.code, rj.tok);
                } else {
                    EkFriends.addFriend(a, who, ip, EkFriends.GAME_PORT);
                }
            }
            return answer[0] != 0;
        } catch (Throwable e) {
            return false;
        }
    }

    // ---- own address (for "My address") ---------------------------------------------------------

    /**
     * IPv4 addresses of this device, VPN ones (ZeroTier/Tailscale/WireGuard) first and labelled.
     *
     * v48: an address is only called "Tailscale" when it sits on a VPN network. Mobile carriers hand
     * out the same 100.64.0.0/10 range (CGNAT) on the cellular interface (rmnet*, ccmni*), and v31-v47
     * labelled that as Tailscale - an address nobody can reach (owner's Fold: rmnet_data8=100.82.x).
     * Android's ConnectivityManager says which networks are VPN / cellular; the interface-name scan
     * stays as a fallback.
     */
    public static String myAddresses() {
        java.util.Set<String> vpnIfs = new java.util.HashSet<String>();
        java.util.Set<String> cellIfs = new java.util.HashSet<String>();
        java.util.Map<String, String> cmAddrs = new java.util.LinkedHashMap<String, String>();
        scanConnectivity(vpnIfs, cellIfs, cmAddrs);

        StringBuilder vpn = new StringBuilder();
        StringBuilder other = new StringBuilder();
        java.util.Set<String> seen = new java.util.HashSet<String>();
        boolean cellular = false;
        try {
            Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
            while (ifs != null && ifs.hasMoreElements()) {
                NetworkInterface ni = ifs.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                String n = ni.getName() == null ? "" : ni.getName();
                Enumeration<InetAddress> as = ni.getInetAddresses();
                while (as.hasMoreElements()) {
                    InetAddress ad = as.nextElement();
                    if (!(ad instanceof Inet4Address)) {
                        continue;
                    }
                    String ip = ad.getHostAddress();
                    if (!seen.add(ip)) {
                        continue;
                    }
                    int kind = kindOf(n, vpnIfs, cellIfs);
                    if (kind == 2) {
                        cellular = true;
                        continue;
                    }
                    if (kind == 1) {
                        vpn.append(ip).append("   (").append(vpnLabel(n, ip)).append(")\n");
                    } else {
                        other.append(ip).append("   (Wi-Fi/local)\n");
                    }
                }
            }
        } catch (Throwable e) {
            // ignore
        }
        // VPN addresses Android reports that the interface scan missed
        for (java.util.Map.Entry<String, String> e : cmAddrs.entrySet()) {
            String ip = e.getKey();
            String n = e.getValue();
            if (seen.add(ip) && vpnIfs.contains(n)) {
                vpn.append(ip).append("   (").append(vpnLabel(n, ip)).append(")\n");
            }
        }
        StringBuilder s = new StringBuilder(EkNat.describe()).append("\n\n").append(vpn).append(other);
        if (vpn.length() == 0) {
            s.append("\n(No Tailscale/ZeroTier address visible to this game. Only needed if the Internet"
                    + " line above doesn't work: turn the VPN on in that app, and check Exiled Kingdoms"
                    + " isn't in its excluded apps.)");
        }
        if (cellular) {
            s.append("\n(Mobile-data address hidden: carriers use 100.x too, but it can't be reached by other players.)");
        }
        String out = s.toString().trim();
        return out.length() == 0 ? "No network address found." : out;
    }

    /**
     * True when this device has a Wi-Fi/Ethernet (home network) IPv4 address, i.e. not only mobile data
     * and/or a VPN. Router port opening (EkNat) only makes sense then.
     */
    static boolean hasHomeNetwork() {
        java.util.Set<String> vpnIfs = new java.util.HashSet<String>();
        java.util.Set<String> cellIfs = new java.util.HashSet<String>();
        scanConnectivity(vpnIfs, cellIfs, new java.util.LinkedHashMap<String, String>());
        try {
            Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
            while (ifs != null && ifs.hasMoreElements()) {
                NetworkInterface ni = ifs.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                String n = ni.getName() == null ? "" : ni.getName();
                if (kindOf(n, vpnIfs, cellIfs) != 0) {
                    continue;
                }
                Enumeration<InetAddress> as = ni.getInetAddresses();
                while (as.hasMoreElements()) {
                    if (as.nextElement() instanceof Inet4Address) {
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            return true;                              // can't tell: let the router search decide
        }
        return false;
    }

    /** 1 = VPN, 2 = cellular, 0 = Wi-Fi/local. */
    private static int kindOf(String n, java.util.Set<String> vpnIfs, java.util.Set<String> cellIfs) {
        if (vpnIfs.contains(n) || n.startsWith("zt") || n.startsWith("tun") || n.startsWith("wg")
                || n.startsWith("tailscale") || n.startsWith("utun")) {
            return 1;
        }
        if (cellIfs.contains(n) || n.startsWith("rmnet") || n.startsWith("ccmni") || n.startsWith("pdp")
                || n.startsWith("clat") || n.startsWith("v4-rmnet")) {
            return 2;
        }
        return 0;
    }

    private static String vpnLabel(String n, String ip) {
        if (n.startsWith("zt")) {
            return "ZeroTier";
        }
        if (n.startsWith("tailscale") || (ip.startsWith("100.") && isCgnat(ip))) {
            return "Tailscale";
        }
        return "VPN";
    }

    /** Android's own view: which interfaces carry a VPN / cellular network, and their IPv4s. */
    private static void scanConnectivity(java.util.Set<String> vpnIfs, java.util.Set<String> cellIfs,
            java.util.Map<String, String> addrs) {
        try {
            MainActivity a = activity();
            if (a == null) {
                return;
            }
            Object cm = a.getSystemService("connectivity");
            if (cm == null) {
                return;
            }
            Class<?> cmc = cm.getClass();
            Object[] nets = (Object[]) cmc.getMethod("getAllNetworks").invoke(cm);
            if (nets == null) {
                return;
            }
            for (Object net : nets) {
                try {
                    Class<?> netc = Class.forName("android.net.Network");
                    Object caps = cmc.getMethod("getNetworkCapabilities", netc).invoke(cm, net);
                    Object lp = cmc.getMethod("getLinkProperties", netc).invoke(cm, net);
                    if (lp == null) {
                        continue;
                    }
                    String ifn = (String) lp.getClass().getMethod("getInterfaceName").invoke(lp);
                    if (ifn == null) {
                        continue;
                    }
                    if (caps != null) {
                        java.lang.reflect.Method has = caps.getClass().getMethod("hasTransport", int.class);
                        if ((Boolean) has.invoke(caps, 4)) {        // TRANSPORT_VPN
                            vpnIfs.add(ifn);
                        } else if ((Boolean) has.invoke(caps, 0)) { // TRANSPORT_CELLULAR
                            cellIfs.add(ifn);
                        }
                    }
                    java.util.List<?> las = (java.util.List<?>) lp.getClass().getMethod("getLinkAddresses").invoke(lp);
                    for (Object la : las) {
                        Object ad = la.getClass().getMethod("getAddress").invoke(la);
                        if (ad instanceof Inet4Address) {
                            addrs.put(((Inet4Address) ad).getHostAddress(), ifn);
                        }
                    }
                } catch (Throwable e) {
                    // next network
                }
            }
        } catch (Throwable e) {
            // fall back to the interface scan
        }
    }

    private static boolean isCgnat(String ip) {
        try {
            int second = Integer.parseInt(ip.split("\\.")[1]);
            return second >= 64 && second <= 127;
        } catch (Throwable e) {
            return false;
        }
    }
}

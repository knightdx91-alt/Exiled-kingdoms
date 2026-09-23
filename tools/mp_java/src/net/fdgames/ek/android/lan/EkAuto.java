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
    }

    /** Called every frame from the game screen (before LanGameBridge.tick); runs every 3 s. */
    public static void tick() {
        try {
            long now = System.currentTimeMillis();
            if (now - lastCheck < 3000L) {
                return;
            }
            lastCheck = now;
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
            final MainActivity a = activity();
            if (a == null || s == null) {
                return false;
            }
            final String ip = s.getInetAddress().getHostAddress();
            List<EkFriends.Friend> friends = EkFriends.load(a);
            for (EkFriends.Friend f : friends) {
                if (f.ip.equals(ip)) {
                    return true;
                }
            }
            final String who = name == null || name.trim().length() == 0 ? "A player" : name.trim();
            final CountDownLatch done = new CountDownLatch(1);
            final int[] answer = {0}; // 0 deny, 1 allow, 2 allow + friend
            a.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        new AlertDialog.Builder(a).setTitle("Join request")
                                .setMessage(who + " (" + ip + ") wants to join your game.")
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
                EkFriends.addFriend(a, who, ip, EkFriends.GAME_PORT);
            }
            return answer[0] != 0;
        } catch (Throwable e) {
            return false;
        }
    }

    // ---- own address (for "My address") ---------------------------------------------------------

    /** IPv4 addresses of this device, VPN ones (ZeroTier/Tailscale/WireGuard) first and labelled. */
    public static String myAddresses() {
        StringBuilder vpn = new StringBuilder();
        StringBuilder other = new StringBuilder();
        try {
            Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
            while (ifs != null && ifs.hasMoreElements()) {
                NetworkInterface ni = ifs.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                String n = ni.getName() == null ? "" : ni.getName();
                boolean isVpn = n.startsWith("zt") || n.startsWith("tun") || n.startsWith("wg") || n.startsWith("tailscale");
                Enumeration<InetAddress> as = ni.getInetAddresses();
                while (as.hasMoreElements()) {
                    InetAddress ad = as.nextElement();
                    if (!(ad instanceof Inet4Address)) {
                        continue;
                    }
                    String ip = ad.getHostAddress();
                    boolean cgnat = ip.startsWith("100.") && isCgnat(ip);
                    String label = n.startsWith("zt") ? "ZeroTier" : (cgnat || n.startsWith("tailscale")) ? "Tailscale" : isVpn ? "VPN" : "Wi-Fi/local";
                    (isVpn || cgnat ? vpn : other).append(ip).append("   (").append(label).append(")\n");
                }
            }
        } catch (Throwable e) {
            // ignore
        }
        String s = vpn.toString() + other.toString();
        return s.length() == 0 ? "No network address found." : s.trim();
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

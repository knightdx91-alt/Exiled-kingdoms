package net.fdgames.ek.android.lan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Friends list for the multiplayer lobby (owner request; not in the MP mod, see
 * deobf/MULTIPLAYER_PORT_SPEC.md §8). Hosts you have joined are remembered automatically; friends
 * can also be added by name + IP. Opening the list asks each friend's address whether a session is
 * being hosted there (the engine's own EK_DISCOVER/EK_HOST handshake, sent straight to the IP, so it
 * works over ZeroTier/Tailscale where broadcast "Scan LAN" does not), and one tap joins.
 * Stored in the lobby's own prefs file (ek_lan_prefs, key ek_friends).
 */
public final class EkFriends {
    private EkFriends() {}

    static final String PREFS = "ek_lan_prefs";
    static final String KEY = "ek_friends";
    static final int GAME_PORT = 32124;
    static final int DISCOVERY_PORT = 32123;
    static final int MAX = 30;

    static final class Friend {
        String name;
        String ip;
        int port = GAME_PORT;
        String status = "";
        boolean hosting;
    }

    // ---- storage ---------------------------------------------------------------------------------

    private static String clean(String s) {
        return s == null ? "" : s.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
    }

    static List<Friend> load(android.app.Activity a) {
        List<Friend> out = new ArrayList<Friend>();
        try {
            SharedPreferences p = a.getSharedPreferences(PREFS, 0);
            String raw = p.getString(KEY, "");
            if (raw == null) {
                return out;
            }
            for (String line : raw.split("\n")) {
                String[] f = line.split("\t");
                if (f.length < 2 || f[1].trim().length() == 0) {
                    continue;
                }
                Friend fr = new Friend();
                fr.name = f[0];
                fr.ip = f[1].trim();
                try {
                    fr.port = f.length > 2 ? Integer.parseInt(f[2].trim()) : GAME_PORT;
                } catch (NumberFormatException e) {
                    fr.port = GAME_PORT;
                }
                out.add(fr);
            }
        } catch (Throwable e) {
            // ignore
        }
        return out;
    }

    static void save(android.app.Activity a, List<Friend> list) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size() && i < MAX; i++) {
                Friend f = list.get(i);
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(clean(f.name)).append('\t').append(clean(f.ip)).append('\t').append(f.port);
            }
            a.getSharedPreferences(PREFS, 0).edit().putString(KEY, sb.toString()).commit();
        } catch (Throwable e) {
            // ignore
        }
    }

    private static int indexOf(List<Friend> list, String ip) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).ip.equals(ip)) {
                return i;
            }
        }
        return -1;
    }

    /** Hooked at the start of LanLobbyActivity.joinHostAsync: every host you join is remembered. */
    public static void remember(LanLobbyActivity a, String ip, int port) {
        try {
            ip = clean(ip);
            if (ip.length() == 0) {
                return;
            }
            List<Friend> list = load(a);
            int i = indexOf(list, ip);
            Friend f;
            if (i >= 0) {
                f = list.remove(i);
            } else {
                f = new Friend();
                f.name = ip;
                f.ip = ip;
            }
            f.port = port > 0 ? port : GAME_PORT;
            list.add(0, f);
            save(a, list);
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Used by join approval ("Allow + add friend"). */
    static void addFriend(android.app.Activity a, String name, String ip, int port) {
        try {
            ip = clean(ip);
            if (ip.length() == 0) {
                return;
            }
            List<Friend> list = load(a);
            int i = indexOf(list, ip);
            Friend f = i >= 0 ? list.remove(i) : new Friend();
            f.ip = ip;
            f.port = port > 0 ? port : GAME_PORT;
            String n = clean(name);
            f.name = n.length() > 0 ? n : (f.name != null ? f.name : ip);
            list.add(0, f);
            save(a, list);
        } catch (Throwable e) {
            // ignore
        }
    }

    /** "My address": what a friend types to join you, plus the auto-host switch. */
    static void showMyAddress(final LanLobbyActivity a) {
        try {
            final boolean on = EkAuto.autoHostEnabled(a);
            new AlertDialog.Builder(a).setTitle("My address")
                    .setMessage("Friends type one of these in Join by IP. Friends elsewhere use the Internet address;"
                            + " on the same Wi-Fi use the Wi-Fi/local one.\n\n"
                            + EkAuto.myAddresses()
                            + "\n\nHost automatically while playing: " + (on ? "ON" : "OFF"))
                    .setPositiveButton(on ? "Turn auto-host OFF" : "Turn auto-host ON", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            EkAuto.setAutoHost(a, !on);
                            Toast.makeText(a, "Auto-host " + (on ? "off" : "on"), 0).show();
                        }
                    })
                    .setNeutralButton(EkItems.hostPvpPref(a) ? "PvP everywhere: ON" : "PvP everywhere: OFF", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            boolean now = !EkItems.hostPvpPref(a);
                            EkItems.setHostPvp(a, now);
                            Toast.makeText(a, "PvP everywhere " + (now ? "ON" : "OFF") + " (when you host)", 0).show();
                        }
                    })
                    .setNegativeButton("Close", null).show();
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- status probe --------------------------------------------------------------------------

    private static void probe(Friend f) {
        DatagramSocket s = null;
        try {
            s = new DatagramSocket();
            s.setSoTimeout(1200);
            byte[] q = "EK_DISCOVER".getBytes("UTF-8");
            s.send(new DatagramPacket(q, q.length, InetAddress.getByName(f.ip), DISCOVERY_PORT));
            byte[] buf = new byte[512];
            DatagramPacket r = new DatagramPacket(buf, buf.length);
            s.receive(r);
            String msg = new String(r.getData(), 0, r.getLength(), "UTF-8");
            if (msg.startsWith("EK_HOST\t")) {
                String[] p = msg.split("\t");
                String name = p.length > 1 ? decode(p[1]) : "";
                if (name.length() > 0 && (f.name == null || f.name.length() == 0 || f.name.equals(f.ip))) {
                    f.name = name;
                }
                if (p.length > 2) {
                    try {
                        f.port = Integer.parseInt(p[2].trim());
                    } catch (NumberFormatException e) {
                        // keep
                    }
                }
                f.hosting = true;
                f.status = "hosting" + (p.length > 4 ? " " + p[3] + "/" + p[4] : "");
                return;
            }
            f.status = "not hosting";
        } catch (Throwable e) {
            f.status = "not hosting";
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    private static String decode(String s) {
        return s.replace("%0D", "\r").replace("%0A", "\n").replace("%09", "\t").replace("%25", "%").trim();
    }

    // ---- UI ------------------------------------------------------------------------------------

    /** Hooked after the lobby's Host / Join IP / Scan LAN / Leave row: a second row with Friends. */
    public static void addLobbyRow(final LanLobbyActivity a, LinearLayout root) {
        try {
            // Back (owner request): closes the lobby and returns to the game / main menu.
            LinearLayout top = new LinearLayout(a);
            top.setOrientation(0);
            root.addView(top, 0, new LinearLayout.LayoutParams(-1, -2));
            a.ekAddButton(top, "< Back", new View.OnClickListener() {
                public void onClick(View v) {
                    a.finish();
                }
            });
        } catch (Throwable e) {
            // ignore
        }
        try {
            LinearLayout row = new LinearLayout(a);
            row.setOrientation(0);
            root.addView(row, new LinearLayout.LayoutParams(-1, -2));
            a.ekAddButton(row, "Friends", new View.OnClickListener() {
                public void onClick(View v) {
                    open(a);
                }
            });
            a.ekAddButton(row, "Add friend", new View.OnClickListener() {
                public void onClick(View v) {
                    promptAdd(a);
                }
            });
            a.ekAddButton(row, "My address", new View.OnClickListener() {
                public void onClick(View v) {
                    showMyAddress(a);
                }
            });
            a.ekAddButton(row, "Trade", new View.OnClickListener() {
                public void onClick(View v) {
                    EkTrade.pickPartner(a);
                }
            });
        } catch (Throwable e) {
            // ignore
        }
    }

    static void open(final LanLobbyActivity a) {
        try {
            final List<Friend> list = load(a);
            if (list.isEmpty()) {
                new AlertDialog.Builder(a).setTitle("Friends")
                        .setMessage("No friends yet. Hosts you join are added here automatically, or use Add friend.")
                        .setPositiveButton("Add friend", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int w) {
                                promptAdd(a);
                            }
                        })
                        .setNegativeButton("Close", null).show();
                return;
            }
            Toast.makeText(a, "Checking friends...", 0).show();
            new Thread(new Runnable() {
                public void run() {
                    List<Thread> ts = new ArrayList<Thread>();
                    for (final Friend f : list) {
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                probe(f);
                            }
                        });
                        t.setDaemon(true);
                        t.start();
                        ts.add(t);
                    }
                    for (Thread t : ts) {
                        try {
                            t.join(2000);
                        } catch (InterruptedException e) {
                            // ignore
                        }
                    }
                    save(a, list); // keeps names learned from the hosts
                    a.runOnUiThread(new Runnable() {
                        public void run() {
                            showList(a, list);
                        }
                    });
                }
            }, "ek-friends").start();
        } catch (Throwable e) {
            // ignore
        }
    }

    static void showList(final LanLobbyActivity a, final List<Friend> list) {
        try {
            CharSequence[] items = new CharSequence[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Friend f = list.get(i);
                items[i] = (f.hosting ? "● " : "○ ") + f.name + "  -  " + f.status + "  (" + f.ip + ")";
            }
            new AlertDialog.Builder(a).setTitle("Friends")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int which) {
                            showFriend(a, list, which);
                        }
                    })
                    .setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            open(a);
                        }
                    })
                    .setNeutralButton("Add friend", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            promptAdd(a);
                        }
                    })
                    .setNegativeButton("Close", null).show();
        } catch (Throwable e) {
            // ignore
        }
    }

    static void showFriend(final LanLobbyActivity a, final List<Friend> list, final int idx) {
        if (idx < 0 || idx >= list.size()) {
            return;
        }
        final Friend f = list.get(idx);
        new AlertDialog.Builder(a).setTitle(f.name)
                .setMessage(f.ip + ":" + f.port + "\n" + f.status)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        a.ekJoin(f.ip, f.port);
                    }
                })
                .setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        List<Friend> cur = load(a);
                        int i = indexOf(cur, f.ip);
                        if (i >= 0) {
                            cur.remove(i);
                            save(a, cur);
                        }
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        showList(a, list);
                    }
                }).show();
    }

    static void promptAdd(final LanLobbyActivity a) {
        try {
            LinearLayout box = new LinearLayout(a);
            box.setOrientation(1);
            final EditText name = new EditText(a);
            name.setHint("Name");
            name.setSingleLine(true);
            final EditText ip = new EditText(a);
            ip.setHint("IP address (e.g. 10.147.17.5)");
            ip.setSingleLine(true);
            box.addView(name, new LinearLayout.LayoutParams(-1, -2));
            box.addView(ip, new LinearLayout.LayoutParams(-1, -2));
            new AlertDialog.Builder(a).setTitle("Add friend").setView(box)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            String addr = clean(String.valueOf(ip.getText()));
                            int port = GAME_PORT;
                            int colon = addr.lastIndexOf(':');
                            if (colon > 0 && addr.indexOf(':') == colon) { // "ip:port" (IPv4 only)
                                try {
                                    port = Integer.parseInt(addr.substring(colon + 1));
                                } catch (NumberFormatException e) {
                                    port = GAME_PORT;
                                }
                                addr = addr.substring(0, colon);
                            }
                            if (addr.length() == 0) {
                                return;
                            }
                            List<Friend> list = load(a);
                            int i = indexOf(list, addr);
                            Friend f = i >= 0 ? list.remove(i) : new Friend();
                            f.ip = addr;
                            f.port = port;
                            String n = clean(String.valueOf(name.getText()));
                            f.name = n.length() > 0 ? n : (f.name != null ? f.name : addr);
                            list.add(0, f);
                            save(a, list);
                            Toast.makeText(a, "Saved " + f.name, 0).show();
                        }
                    })
                    .setNegativeButton("Cancel", null).show();
        } catch (Throwable e) {
            // ignore
        }
    }

    /**
     * Lobby layout (owner screenshot, v50): the room chat only got what was left after fixed-height
     * boxes and showed about one line. "Discovered sessions" and "Players in room" now sit side by side,
     * the orange diagnostics strip is taller, and the chat keeps its weight, so it gets the freed space.
     * Found by type in LanLobbyActivity.buildContentView's root: TextView header, ListView (sessions),
     * TextView header, TextView (players).
     */
    public static android.view.View relayoutLobby(android.app.Activity a, android.view.View rootView) {
        try {
            if (!(rootView instanceof android.widget.LinearLayout)) {
                return rootView;
            }
            android.widget.LinearLayout root = (android.widget.LinearLayout) rootView;
            float d = a.getResources().getDisplayMetrics().density;
            int list = -1;
            for (int i = 0; i < root.getChildCount(); i++) {
                if (root.getChildAt(i) instanceof android.widget.ListView) {
                    list = i;
                    break;
                }
            }
            // taller diagnostics strip: the first ScrollView above the sessions list
            for (int i = 0; i < root.getChildCount() && (list < 0 || i < list); i++) {
                android.view.View v = root.getChildAt(i);
                if (v instanceof android.widget.ScrollView && v.getLayoutParams() != null) {
                    android.view.ViewGroup.LayoutParams lp = v.getLayoutParams();
                    lp.height = (int) (84 * d);
                    v.setLayoutParams(lp);
                    break;
                }
            }
            if (list < 1 || list + 2 >= root.getChildCount()) {
                return rootView;
            }
            android.view.View h1 = root.getChildAt(list - 1);
            android.view.View sessions = root.getChildAt(list);
            android.view.View h2 = root.getChildAt(list + 1);
            android.view.View players = root.getChildAt(list + 2);
            if (!(h1 instanceof android.widget.TextView) || !(h2 instanceof android.widget.TextView)
                    || !(players instanceof android.widget.TextView)) {
                return rootView;
            }
            int boxH = (int) (130 * d);
            root.removeView(h1);
            root.removeView(sessions);
            root.removeView(h2);
            root.removeView(players);
            android.widget.LinearLayout row = new android.widget.LinearLayout(a);
            row.setOrientation(0);
            android.widget.LinearLayout left = new android.widget.LinearLayout(a);
            left.setOrientation(1);
            android.widget.LinearLayout right = new android.widget.LinearLayout(a);
            right.setOrientation(1);
            left.addView(h1);
            left.addView(sessions, new android.widget.LinearLayout.LayoutParams(-1, boxH));
            right.addView(h2);
            right.addView(players, new android.widget.LinearLayout.LayoutParams(-1, boxH));
            android.widget.LinearLayout.LayoutParams lp = new android.widget.LinearLayout.LayoutParams(0, -2, 1f);
            android.widget.LinearLayout.LayoutParams rp = new android.widget.LinearLayout.LayoutParams(0, -2, 1f);
            row.addView(left, lp);
            row.addView(right, rp);
            left.setPadding(0, 0, (int) (6 * d), 0);
            right.setPadding((int) (6 * d), 0, 0, 0);
            root.addView(row, list - 1, new android.widget.LinearLayout.LayoutParams(-1, -2));
        } catch (Throwable e) {
            // keep the stock layout
        }
        return rootView;
    }
}

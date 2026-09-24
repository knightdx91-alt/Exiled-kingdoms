package net.fdgames.ek.android.lan;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.regex.Pattern;

/**
 * v63 multiplayer page (owner: "I don't want all that IP info and everything else showing ... keep only necessary
 * stuff, we're going straight with Cloudflare"). deobf/LOBBY_CLEANUP_SPEC.md.
 * The stock LanLobbyActivity content is rebuilt: Back + title, a plain status line, your name,
 * [Host] [Join by code] [Friends] [Leave], [Trade] [PvP], players, chat. Gone: Join IP, Scan LAN, the discovered
 * sessions list (addresses), the diagnostics strip, My address, Add friend by address, the raw status text.
 * The stock views the activity keeps updating (players, chat, input row) are moved, not copied.
 */
public final class EkLobby {
    private EkLobby() {}

    private static final int GOLD = -3629008;       // stock title colour
    private static final int HEAD = -2176125;       // stock heading colour
    private static final int TEXT = -725806;        // stock text colour
    private static final int BOX = 572462605;       // stock translucent box

    public static View build(final Activity a, View rootView) {
        try {
            return buildPage(a, rootView);
        } catch (Throwable e) {
            return rootView;                            // never take the lobby down with us
        }
    }

    private static View buildPage(final Activity a, View rootView) {
        if (!(rootView instanceof LinearLayout) || !(a instanceof LanLobbyActivity)) {
            return rootView;
        }
        final LanLobbyActivity lob = (LanLobbyActivity) a;
        LinearLayout root = (LinearLayout) rootView;
        EditText name = null;
        TextView players = null;
        ScrollView chat = null;
        View inputRow = null;
        int list = -1;
        int n = root.getChildCount();
        for (int i = 0; i < n; i++) {
            View v = root.getChildAt(i);
            if (v instanceof ListView) {
                list = i;
            } else if (v instanceof EditText && name == null) {
                name = (EditText) v;
            } else if (v instanceof ScrollView) {
                chat = (ScrollView) v;                  // the last ScrollView is the chat (the first is diagnostics)
            }
        }
        if (list >= 0 && list + 2 < n && root.getChildAt(list + 2) instanceof TextView) {
            players = (TextView) root.getChildAt(list + 2);
        }
        if (n > 0 && root.getChildAt(n - 1) instanceof LinearLayout) {
            inputRow = root.getChildAt(n - 1);
        }
        if (name == null || players == null || chat == null || inputRow == null) {
            return rootView;                            // unexpected layout: keep the stock page
        }
        float d = a.getResources().getDisplayMetrics().density;
        root.removeAllViews();

        LinearLayout top = row(a);
        lob.ekAddButton(top, "< Back", new View.OnClickListener() {
            public void onClick(View v) {
                a.finish();
            }
        });
        TextView title = new TextView(a);
        title.setText("MULTIPLAYER");
        title.setTextSize(22f);
        title.setTextColor(GOLD);
        title.setGravity(17);
        top.addView(title, new LinearLayout.LayoutParams(0, -2, 2f));
        root.addView(top, new LinearLayout.LayoutParams(-1, -2));

        final TextView status = new TextView(a);
        int p = (int) (10 * d);
        status.setPadding(p, p, p, p);
        status.setTextSize(15f);
        status.setTextColor(TEXT);
        status.setBackgroundColor(1714626317);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = (int) (6 * d);
        root.addView(status, sp);

        LinearLayout nameRow = row(a);
        TextView nl = new TextView(a);
        nl.setText("Your name  ");
        nl.setTextColor(HEAD);
        nl.setTextSize(15f);
        nameRow.addView(nl, new LinearLayout.LayoutParams(-2, -2));
        detach(name);
        nameRow.addView(name, new LinearLayout.LayoutParams(0, -2, 1f));
        root.addView(nameRow, new LinearLayout.LayoutParams(-1, -2));

        LinearLayout r1 = row(a);
        lob.ekAddButton(r1, "Host", new View.OnClickListener() {
            public void onClick(View v) {
                EkRelay.hostOnline(lob);
            }
        });
        lob.ekAddButton(r1, "Join by code", new View.OnClickListener() {
            public void onClick(View v) {
                EkRelay.joinByCode(lob);
            }
        });
        lob.ekAddButton(r1, "Friends", new View.OnClickListener() {
            public void onClick(View v) {
                EkFriends.open(lob);
            }
        });
        lob.ekAddButton(r1, "Leave", new View.OnClickListener() {
            public void onClick(View v) {
                leave(a);
            }
        });
        root.addView(r1, new LinearLayout.LayoutParams(-1, -2));

        LinearLayout r2 = row(a);
        lob.ekAddButton(r2, "Trade", new View.OnClickListener() {
            public void onClick(View v) {
                EkTrade.pickPartner(lob);
            }
        });
        lob.ekAddButton(r2, pvpLabel(), new View.OnClickListener() {
            public void onClick(View v) {
                boolean now = !EkItems.myPvp();
                EkItems.setMyPvp(now);
                if (v instanceof TextView) {
                    ((TextView) v).setText(pvpLabel());
                }
                Toast.makeText(a, now ? "PvP on: you can fight players who also have it on"
                        : "PvP off: it stays off until you turn it back on", 1).show();
            }
        });
        root.addView(r2, new LinearLayout.LayoutParams(-1, -2));

        root.addView(heading(a, "Players"));
        detach(players);
        root.addView(players, new LinearLayout.LayoutParams(-1, (int) (84 * d)));
        root.addView(heading(a, "Chat"));
        detach(chat);
        root.addView(chat, new LinearLayout.LayoutParams(-1, 0, 1f));
        detach(inputRow);
        root.addView(inputRow, new LinearLayout.LayoutParams(-1, -2));

        final Runnable[] tick = new Runnable[1];
        tick[0] = new Runnable() {
            public void run() {
                if (a.isFinishing()) {
                    return;
                }
                status.setText(statusText(a));
                status.postDelayed(tick[0], 1000L);
            }
        };
        tick[0].run();
        return rootView;
    }

    private static String pvpLabel() {
        return EkItems.myPvp() ? "PvP: ON" : "PvP: OFF";
    }

    private static LinearLayout row(Activity a) {
        LinearLayout r = new LinearLayout(a);
        r.setOrientation(0);
        r.setGravity(16);
        float d = a.getResources().getDisplayMetrics().density;
        r.setPadding(0, (int) (4 * d), 0, (int) (4 * d));
        return r;
    }

    private static TextView heading(Activity a, String s) {
        TextView t = new TextView(a);
        t.setText(s);
        t.setTextColor(HEAD);
        t.setTextSize(16f);
        float d = a.getResources().getDisplayMetrics().density;
        t.setPadding(0, (int) (6 * d), 0, (int) (4 * d));
        return t;
    }

    private static void detach(View v) {
        if (v.getParent() instanceof ViewGroup) {
            ((ViewGroup) v.getParent()).removeView(v);
        }
    }

    static String statusText(Activity a) {
        try {
            LanSessionManager m = LanSessionManager.get(a);
            String code = EkRelay.roomCode();
            int count = 0;
            try {
                List<?> ps = m == null ? null : m.getPlayersSnapshot();
                count = ps == null ? 0 : ps.size();
            } catch (Throwable e) {
                count = 0;
            }
            if (m != null && m.ekConnected()) {
                return "Connected: you're in the host's world" + (count > 1 ? " (" + count + " players)." : ".")
                        + " Leave takes you home.";
            }
            if (code != null) {
                return "Your room is open. Code: " + code + (count > 1 ? "   (" + count + " players)" : "")
                        + "\nFriends tap Join by code. Tap Host to see the code or close the room.";
            }
            return "Not connected. Tap Host to open your room to friends, or Join by code to join someone.";
        } catch (Throwable e) {
            return "";
        }
    }

    static void leave(final Activity a) {
        EkRelay.closeRoom();
        final LanSessionManager m = LanSessionManager.get(a);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    if (m != null) {
                        m.stopAll();
                    }
                } catch (Throwable e) {
                    // ignore
                }
            }
        }, "ek-leave");
        t.setDaemon(true);
        t.start();
        Toast.makeText(a, "Left the session", 0).show();
    }

    // ---- text filters (LanSessionManager hooks) -------------------------------------------------------

    private static final Pattern IP = Pattern.compile("/?\\b\\d{1,3}(\\.\\d{1,3}){3}(:\\d+)?\\b");

    /** addSystemLineLocked: drop technical lines, never show an address. null = don't add. */
    public static String systemLine(String s) {
        if (s == null) {
            return null;
        }
        String l = s.toLowerCase();
        if (l.contains("target=") || l.contains("localips") || l.contains("socket=") || l.contains("host fail")
                || l.contains("join fail") || l.contains("port=") || l.contains("lan room created")
                || l.contains("sala lan criada") || l.contains("error=")) {
            return null;
        }
        if (l.startsWith("connecting to") || l.startsWith("conectando em")) {
            return "Connecting...";
        }
        if (l.contains("lan session closed") || l.contains("sessao lan encerrada")) {
            return "Session closed.";
        }
        return IP.matcher(s).replaceAll("the host").trim();
    }

    /** toast(String): friendly wording, no addresses. null = no toast. */
    public static String toastText(String s) {
        if (s == null) {
            return null;
        }
        String l = s.toLowerCase();
        if (l.contains("lan room hosted") || l.contains("sala lan hospedada")) {
            return null;                                 // hosting in the background is not news
        }
        if (l.startsWith("unable to join")) {
            return "Couldn't join. Check the code and that the host's room is open.";
        }
        if (l.startsWith("unable to host")) {
            return "Couldn't start hosting. Try again.";
        }
        return IP.matcher(s).replaceAll("the host");
    }

    /** formatPlayerLineLocked: "Name - Lv 12 Warrior - Lannegar" (no area codes, no gold). */
    public static String playerLine(String name, LanSessionManager.PlayerState st) {
        if (st == null) {
            return name;
        }
        StringBuilder sb = new StringBuilder(name == null ? "" : name);
        if (st.level > 0) {
            sb.append("  -  Lv ").append(st.level);
        }
        if (st.className != null && st.className.length() > 0) {
            sb.append(' ').append(st.className);
        }
        if (st.currentMapName != null && st.currentMapName.trim().length() > 0) {
            sb.append("  -  ").append(st.currentMapName.trim());
        }
        return sb.toString();
    }
}

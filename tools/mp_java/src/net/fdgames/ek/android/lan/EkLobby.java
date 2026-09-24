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

        // v67: one multiplayer name per save (owner: "pick a name when you start multiplayer ... permanent on that
        // save"). The stock name box stays (hidden) because the engine reads and overwrites it; it's kept in sync.
        LinearLayout nameRow = row(a);
        TextView nl = new TextView(a);
        nl.setText("Your name:  ");
        nl.setTextColor(HEAD);
        nl.setTextSize(15f);
        nameRow.addView(nl, new LinearLayout.LayoutParams(-2, -2));
        final TextView shownName = new TextView(a);
        shownName.setTextColor(-1);
        shownName.setTextSize(18f);
        nameRow.addView(shownName, new LinearLayout.LayoutParams(0, -2, 1f));
        final EditText stockName = name;
        lob.ekAddButton(nameRow, "Change", new View.OnClickListener() {
            public void onClick(View v) {
                askName(a, stockName, shownName, false);
            }
        });
        detach(name);
        name.setVisibility(8);                          // View.GONE
        nameRow.addView(name, new LinearLayout.LayoutParams(0, -2, 0f));
        root.addView(nameRow, new LinearLayout.LayoutParams(-1, -2));
        String cur = mpName(a);
        applyName(a, cur != null ? cur : prefName(a), stockName, shownName, false);
        if (cur == null && saveKey() != null) {
            askName(a, stockName, shownName, true);    // first time on this save
        }

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
        lob.ekAddButton(r2, openLabel(a), new View.OnClickListener() {
            public void onClick(View v) {
                boolean now = !EkRelay.openToFriends(a);
                EkRelay.setOpenToFriends(a, now);
                if (now) {
                    EkRelay.openRoom(a, true);
                } else {
                    EkRelay.closeRoom();
                }
                if (v instanceof TextView) {
                    ((TextView) v).setText(openLabel(a));
                }
                Toast.makeText(a, now ? "Your room opens by itself while you play, so friends can join any time"
                        : "Room closed. Friends can't join until you open it again", 1).show();
            }
        });
        root.addView(r2, new LinearLayout.LayoutParams(-1, -2));

        LinearLayout r3 = row(a);                       // v70: find strangers' rooms / list yours
        lob.ekAddButton(r3, "Browse rooms", new View.OnClickListener() {
            public void onClick(View v) {
                EkRelay.browse(lob);
            }
        });
        lob.ekAddButton(r3, publicLabel(a), new View.OnClickListener() {
            public void onClick(View v) {
                boolean now = !EkRelay.publicRoom(a);
                EkRelay.setPublicRoom(a, now);
                if (v instanceof TextView) {
                    ((TextView) v).setText(publicLabel(a));
                }
                Toast.makeText(a, now ? "Your room is listed in Browse rooms while it's open. You still approve"
                        + " everyone who asks to join." : "Your room is no longer listed. Friends and codes still work.", 1).show();
            }
        });
        root.addView(r3, new LinearLayout.LayoutParams(-1, -2));

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
                autoOpen(a);
                status.setText(statusText(a));
                syncName(a, stockName, shownName);
                status.postDelayed(tick[0], 1000L);
            }
        };
        tick[0].run();
        return rootView;
    }

    // ---- v67: multiplayer name per save -----------------------------------------------------------------
    static final String PREF_ENGINE_NAME = "lan_player_name";   // what the engine, relay and auto-host use

    /** "ek_mp_name_<home slot>_<character>" for the loaded save, or null at the main menu. */
    static String saveKey() {
        try {
            net.fdgames.GameWorld.GameData gd = net.fdgames.GameWorld.GameData.O();
            if (gd == null || gd.player == null) {
                return null;
            }
            int slot = gd.slot == EkShare.GUEST_SLOT ? EkShare.homeSlot() : gd.slot;
            String who = gd.player.getName();
            if (slot < 0 || who == null || who.trim().length() == 0) {
                return null;
            }
            return "ek_mp_name_" + slot + "_" + who.trim().replaceAll("[^A-Za-z0-9]", "_");
        } catch (Throwable e) {
            return null;
        }
    }

    static String characterName() {
        try {
            net.fdgames.GameWorld.GameData gd = net.fdgames.GameWorld.GameData.O();
            String n = gd == null || gd.player == null ? null : gd.player.getName();
            return n == null || n.trim().length() == 0 ? null : n.trim();
        } catch (Throwable e) {
            return null;
        }
    }

    static String mpName(Activity a) {
        String k = saveKey();
        if (k == null) {
            return null;
        }
        try {
            String n = a.getSharedPreferences(EkFriends.PREFS, 0).getString(k, null);
            return n == null || n.trim().length() == 0 ? null : n.trim();
        } catch (Throwable e) {
            return null;
        }
    }

    private static String prefName(Activity a) {
        try {
            String n = a.getSharedPreferences(EkFriends.PREFS, 0).getString(PREF_ENGINE_NAME, null);
            if (n != null && n.trim().length() > 0) {
                return n.trim();
            }
        } catch (Throwable e) {
            // fall through
        }
        String c = characterName();
        return c != null ? c : "Player";
    }

    static String cleanName(String s) {
        if (s == null) {
            return null;
        }
        String n = s.replaceAll("[\\t\\r\\n]", " ").trim();
        if (n.length() > 20) {
            n = n.substring(0, 20).trim();
        }
        return n.length() == 0 ? null : n;
    }

    /** Store the name for this save (if a save is loaded) and for the engine; show it. */
    static void applyName(Activity a, String n, EditText stock, TextView shown, boolean store) {
        n = cleanName(n);
        if (n == null) {
            return;
        }
        try {
            android.content.SharedPreferences.Editor e = a.getSharedPreferences(EkFriends.PREFS, 0).edit();
            String k = saveKey();
            if (store && k != null) {
                e.putString(k, n);
            }
            e.putString(PREF_ENGINE_NAME, n);
            e.commit();
        } catch (Throwable e) {
            // ignore
        }
        if (stock != null && !n.equals(String.valueOf(stock.getText()).trim())) {
            stock.setText(n);
        }
        if (shown != null) {
            shown.setText(n);
        }
    }

    /** The engine rewrites the stock box (e.g. "Player 2" after a clash); put this save's name back. */
    private static void syncName(Activity a, EditText stock, TextView shown) {
        String want = mpName(a);
        if (want == null) {
            want = prefName(a);
        }
        if (stock != null && !want.equals(String.valueOf(stock.getText()).trim())) {
            applyName(a, want, stock, shown, false);
        }
    }

    static void askName(final Activity a, final EditText stock, final TextView shown, final boolean first) {
        try {
            final EditText in = new EditText(a);
            in.setSingleLine(true);
            String cur = mpName(a);
            if (cur == null) {
                cur = characterName();
            }
            if (cur == null) {
                cur = prefName(a);
            }
            in.setText(cur);
            new android.app.AlertDialog.Builder(a).setTitle(first ? "Choose your multiplayer name" : "Multiplayer name")
                    .setMessage("This is the name other players see. It's saved with this character, so you only"
                            + " pick it once." + (saveKey() == null ? " (Load a save to keep a name per character.)" : ""))
                    .setView(in)
                    .setPositiveButton("Save", new android.content.DialogInterface.OnClickListener() {
                        public void onClick(android.content.DialogInterface d, int w) {
                            String n = cleanName(String.valueOf(in.getText()));
                            if (n == null) {
                                n = characterName() != null ? characterName() : "Player";
                            }
                            applyName(a, n, stock, shown, true);
                            Toast.makeText(a, "You're \"" + n + "\" in multiplayer", 0).show();
                        }
                    })
                    .setNegativeButton(first ? "Use character name" : "Cancel",
                            first ? new android.content.DialogInterface.OnClickListener() {
                                public void onClick(android.content.DialogInterface d, int w) {
                                    String n = characterName() != null ? characterName() : prefName(a);
                                    applyName(a, n, stock, shown, true);
                                }
                            } : null)
                    .setCancelable(!first)
                    .show();
        } catch (Throwable e) {
            // ignore
        }
    }

    /**
     * v68 (owner: "I was already loaded in a game, I just had not clicked Host; once I clicked Host it changed"):
     * the room was only opened by EkAuto.tick on the game thread, which is paused while this screen is open, and
     * only after the background host had started. In a loaded game with Open to friends on, open it from here.
     */
    private static void autoOpen(Activity a) {
        try {
            if (characterName() == null || !EkRelay.openToFriends(a) || EkRelay.roomActive()) {
                return;
            }
            LanSessionManager m = LanSessionManager.get(a);
            if (m != null && m.ekConnected()) {
                return;                                 // joined someone else: no room of your own
            }
            EkRelay.openRoom(a, true);
        } catch (Throwable e) {
            // next second
        }
    }

    private static String publicLabel(Activity a) {
        return EkRelay.publicRoom(a) ? "Public room: ON" : "Public room: OFF";
    }

    private static String openLabel(Activity a) {
        return EkRelay.openToFriends(a) ? "Open to friends: ON" : "Open to friends: OFF";
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
                        + "\nFriends join by code or from their Friends list.";
            }
            if (EkRelay.roomActive()) {
                return "Opening your room...";
            }
            if (EkRelay.openToFriends(a) && characterName() == null) {
                return "Load your game: your room opens by itself while you play. Or Join by code to join someone.";
            }
            return "Your room is closed. Tap Host to open it, or Join by code to join someone.";
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
        if (l.contains("lan diag") || l.contains("host ready") || l.contains("preferred=") || l.contains("udp=")
                || l.contains("tcp=") || l.contains("bind=")
                || l.contains("target=") || l.contains("localips") || l.contains("socket=") || l.contains("host fail")
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

    /** v65 game log (LanGameBridge.postGameLog): no diagnostics, no addresses, no "LAN" wording. null = drop. */
    public static String gameLogLine(String s) {
        if (s == null) {
            return null;
        }
        String l = s.toLowerCase();
        if (l.contains("lan diag") || l.contains("host ready") || l.contains("join fail") || l.contains("host fail")
                || l.contains("tcp connect") || l.contains("localips") || l.contains("target=") || l.contains("port=")
                || l.contains("preferred=") || l.contains("udp=") || l.contains("tcp=") || l.contains("bind=")
                || l.contains("lan session hosted") || l.contains("sessao lan hospedada") || l.contains("lan session closed")
                || l.contains("sessao lan encerrada") || l.contains("error=") || l.contains("exception")) {
            return null;
        }
        String t = s.replace("[CYAN]LAN[] HOST left the LAN session.", "[CYAN]Party[] The host left.")
                .replace("[CYAN]LAN[] HOST saiu da sessao.", "[CYAN]Party[] The host left.")
                .replace(" joined the LAN session.", " joined your game.").replace(" entrou na sessao.", " joined your game.")
                .replace(" left the LAN session.", " left.").replace(" saiu da sessao.", " left.")
                .replace("Connected to a LAN session.", "Connected to the host's world.")
                .replace(" foi derrotado!", " was defeated!")
                .replace("[CYAN]LAN[] ", "[CYAN]Party[] ");
        return IP.matcher(t).replaceAll("the host");
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

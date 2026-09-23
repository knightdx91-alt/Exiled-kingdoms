package net.fdgames.ek.android.lan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.ek.android.MainActivity;

/**
 * Two-sided trade between players (deobf/SHARED_WORLD_SPEC.md §8). Messages
 * EKTRADE⇥to⇥from⇥kind⇥payload are relayed by the host to everyone; each device acts on its own name.
 * kind: REQ, ACK, NAK, OFFER (items "id:n,…" + ";gold"), CONFIRM (both offers), CANCEL.
 */
public final class EkTrade {
    private EkTrade() {}

    // one trade at a time
    private static String partner;
    private static String myOffer;
    private static String theirOffer;
    private static String myConfirm;
    private static String theirConfirm;
    private static boolean done;

    private static android.app.Activity ui() {
        Object a = Gdx.app;
        return a instanceof MainActivity ? (MainActivity) a : null;
    }

    private static LanSessionManager mgr() {
        return LanSessionManager.getInstanceIfReady();
    }

    private static String me() {
        LanSessionManager m = mgr();
        String n = m == null ? null : m.getLocalPlayerName();
        return n == null ? "" : n;
    }

    private static void send(String to, String kind, String payload) {
        String line = "EKTRADE\t" + to + "\t" + me() + "\t" + kind + "\t" + (payload == null ? "" : payload);
        LanSessionManager m = mgr();
        if (m == null) {
            return;
        }
        if (m.isHosting()) {
            m.ekBroadcast(line);
        } else {
            m.ekSendToHost(line);
        }
    }

    private static void reset() {
        partner = null;
        myOffer = null;
        theirOffer = null;
        myConfirm = null;
        theirConfirm = null;
        done = false;
    }

    // ---- start (lobby "Trade" button) ------------------------------------------------------------

    public static void pickPartner(final android.app.Activity a) {
        try {
            LanSessionManager m = mgr();
            if (m == null || !(m.ekConnected() || (m.isHosting() && m.getPlayerCount() >= 2))) {
                new AlertDialog.Builder(a).setTitle("Trade").setMessage("Join or host a game with another player first.")
                        .setNegativeButton("Close", null).show();
                return;
            }
            final List<String> names = new ArrayList<String>();
            for (Object o : m.getPlayersSnapshot()) {
                String n = String.valueOf(o).trim();
                if (n.length() > 0 && !n.equals(me())) {
                    names.add(n);
                }
            }
            if (names.isEmpty()) {
                new AlertDialog.Builder(a).setTitle("Trade").setMessage("Nobody else is here yet.").setNegativeButton("Close", null).show();
                return;
            }
            new AlertDialog.Builder(a).setTitle("Trade with…")
                    .setItems(names.toArray(new CharSequence[0]), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int which) {
                            reset();
                            partner = names.get(which);
                            send(partner, "REQ", "");
                            GameConsole.a("Trade request sent to " + partner + ".");
                        }
                    }).setNegativeButton("Cancel", null).show();
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- incoming --------------------------------------------------------------------------------

    /** From EkShare.hostLine/clientLine. true = a trade line (handled or relayed). */
    public static boolean line(String line, boolean relay) {
        if (!line.startsWith("EKTRADE\t")) {
            return false;
        }
        if (relay) {
            LanSessionManager m = mgr();
            if (m != null) {
                m.ekBroadcast(line);
            }
        }
        final String[] p = line.split("\t", -1);
        if (p.length < 5 || !p[1].equals(me())) {
            return true;
        }
        final android.app.Activity a = ui();
        if (a == null) {
            return true;
        }
        a.runOnUiThread(new Runnable() {
            public void run() {
                handle(a, p[2], p[3], p[4]);
            }
        });
        return true;
    }

    private static void handle(final android.app.Activity a, final String from, String kind, String payload) {
        try {
            if (kind.equals("REQ")) {
                new AlertDialog.Builder(a).setTitle("Trade request").setMessage(from + " wants to trade with you.")
                        .setCancelable(false)
                        .setPositiveButton("Trade", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int w) {
                                reset();
                                partner = from;
                                send(from, "ACK", "");
                                chooseOffer(a);
                            }
                        })
                        .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int w) {
                                send(from, "NAK", "");
                            }
                        }).show();
                return;
            }
            if (!from.equals(partner)) {
                return;
            }
            if (kind.equals("ACK")) {
                chooseOffer(a);
            } else if (kind.equals("NAK")) {
                GameConsole.a(from + " declined the trade.");
                reset();
            } else if (kind.equals("CANCEL")) {
                GameConsole.a(from + " cancelled the trade.");
                reset();
            } else if (kind.equals("OFFER")) {
                theirOffer = payload;
                maybeConfirm(a);
            } else if (kind.equals("CONFIRM")) {
                theirConfirm = payload;
                maybeExecute();
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- choosing an offer ---------------------------------------------------------------------------

    private static String itemName(int id) {
        try {
            Item it = Rules.c(id);
            return it != null && it.name != null ? it.name : "#" + id;
        } catch (Throwable e) {
            return "#" + id;
        }
    }

    private static Map<Integer, Integer> myItems() {
        Map<Integer, Integer> c = new LinkedHashMap<Integer, Integer>();
        GameData gd = GameData.O();
        if (gd == null || gd.backpack == null) {
            return c;
        }
        for (int k = 0; k < 20; k++) {
            int id = gd.backpack.d(k);
            if (id > 0 && !c.containsKey(id)) {
                c.put(id, gd.backpack.g(id));
            }
        }
        return c;
    }

    private static void chooseOffer(final android.app.Activity a) {
        final Map<Integer, Integer> have = myItems();
        final List<Integer> ids = new ArrayList<Integer>(have.keySet());
        final CharSequence[] labels = new CharSequence[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            int n = have.get(ids.get(i));
            labels[i] = itemName(ids.get(i)) + (n > 1 ? "  x" + n + " (all)" : "");
        }
        final boolean[] chosen = new boolean[ids.size()];
        LinearLayout box = new LinearLayout(a);
        box.setOrientation(1);
        final EditText gold = new EditText(a);
        gold.setHint("Gold to give (0)");
        gold.setSingleLine(true);
        gold.setInputType(2); // number
        box.addView(gold, new LinearLayout.LayoutParams(-1, -2));
        new AlertDialog.Builder(a).setTitle("Trade with " + partner + ": what do you give?")
                .setMultiChoiceItems(labels, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface d, int which, boolean isChecked) {
                        chosen[which] = isChecked;
                    }
                })
                .setView(box)
                .setCancelable(false)
                .setPositiveButton("Ready", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < ids.size(); i++) {
                            if (chosen[i]) {
                                if (sb.length() > 0) {
                                    sb.append(',');
                                }
                                sb.append(ids.get(i)).append(':').append(have.get(ids.get(i)));
                            }
                        }
                        int g = 0;
                        try {
                            g = Math.max(0, Integer.parseInt(String.valueOf(gold.getText()).trim()));
                        } catch (NumberFormatException e) {
                            g = 0;
                        }
                        GameData gd = GameData.O();
                        if (gd != null && gd.player != null) {
                            g = Math.min(g, gd.player.g());
                        }
                        myOffer = sb + ";" + g;
                        send(partner, "OFFER", myOffer);
                        GameConsole.a("Waiting for " + partner + "…");
                        maybeConfirm(a);
                    }
                })
                .setNegativeButton("Cancel trade", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        if (partner != null) {
                            send(partner, "CANCEL", "");
                        }
                        reset();
                    }
                }).show();
    }

    private static String describe(String offer) {
        if (offer == null) {
            return "nothing";
        }
        String[] parts = offer.split(";", -1);
        StringBuilder sb = new StringBuilder();
        if (parts[0].length() > 0) {
            for (String s : parts[0].split(",")) {
                String[] kv = s.split(":");
                try {
                    int id = Integer.parseInt(kv[0]);
                    int n = kv.length > 1 ? Integer.parseInt(kv[1]) : 1;
                    sb.append("• ").append(itemName(id)).append(n > 1 ? " x" + n : "").append('\n');
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        }
        int g = 0;
        try {
            g = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        } catch (NumberFormatException e) {
            g = 0;
        }
        if (g > 0) {
            sb.append("• ").append(g).append(" gold\n");
        }
        return sb.length() == 0 ? "nothing" : sb.toString().trim();
    }

    private static void maybeConfirm(final android.app.Activity a) {
        if (myOffer == null || theirOffer == null || myConfirm != null) {
            return;
        }
        TextView t = new TextView(a);
        t.setPadding(40, 20, 40, 20);
        t.setText("You give:\n" + describe(myOffer) + "\n\nYou get:\n" + describe(theirOffer));
        new AlertDialog.Builder(a).setTitle("Confirm trade with " + partner).setView(t).setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        myConfirm = myOffer + "|" + theirOffer;
                        send(partner, "CONFIRM", theirOffer + "|" + myOffer); // as seen from their side
                        maybeExecute();
                    }
                })
                .setNegativeButton("Cancel trade", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int w) {
                        send(partner, "CANCEL", "");
                        reset();
                    }
                }).show();
    }

    // ---- execute ---------------------------------------------------------------------------------------

    private static void maybeExecute() {
        if (done || myConfirm == null || theirConfirm == null) {
            return;
        }
        if (!myConfirm.equals(theirConfirm)) {
            GameConsole.a("The offers changed; trade cancelled.");
            send(partner, "CANCEL", "");
            reset();
            return;
        }
        done = true;
        final String give = myOffer;
        final String get = theirOffer;
        final String who = partner;
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                execute(give, get, who);
            }
        });
    }

    private static Map<Integer, Integer> parse(String offer) {
        Map<Integer, Integer> c = new LinkedHashMap<Integer, Integer>();
        String items = offer.split(";", -1)[0];
        if (items.length() == 0) {
            return c;
        }
        for (String s : items.split(",")) {
            String[] kv = s.split(":");
            try {
                c.put(Integer.parseInt(kv[0]), kv.length > 1 ? Integer.parseInt(kv[1]) : 1);
            } catch (NumberFormatException e) {
                // skip
            }
        }
        return c;
    }

    private static int gold(String offer) {
        String[] parts = offer.split(";", -1);
        try {
            return parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Game thread: remove what I give, add what I get. */
    static void execute(String give, String get, String who) {
        try {
            GameData gd = GameData.O();
            Items bag = gd.backpack;
            Map<Integer, Integer> out = parse(give);
            for (Map.Entry<Integer, Integer> e : out.entrySet()) {
                if (bag.g(e.getKey()) < e.getValue()) {
                    GameConsole.a("[YELLOW]Trade failed: you no longer have " + itemName(e.getKey()) + ".[]");
                    send(who, "CANCEL", "");
                    reset();
                    return;
                }
            }
            int gGive = Math.min(gold(give), gd.player.g());
            for (Map.Entry<Integer, Integer> e : out.entrySet()) {
                for (int n = 0; n < e.getValue(); n++) {
                    bag.i(e.getKey());
                }
            }
            if (gGive > 0) {
                gd.player.s(-gGive);
            }
            ArrayList<Integer> overflow = new ArrayList<Integer>();
            for (Map.Entry<Integer, Integer> e : parse(get).entrySet()) {
                for (int n = 0; n < e.getValue(); n++) {
                    if (!bag.a(e.getKey())) {
                        overflow.add(e.getKey());
                    }
                }
            }
            int gGet = gold(get);
            if (gGet > 0) {
                gd.player.s(gGet);
            }
            if (!overflow.isEmpty()) {
                Loot l = new Loot(gd.player.x + 16, gd.player.y + 16, overflow, 0);
                GameLevelData.a(l);
                GameConsole.a("[YELLOW]Backpack full: the rest is on the ground at your feet.[]");
            }
            GameConsole.a("[GREEN]Trade with " + who + " complete.[]");
        } catch (Throwable e) {
            GameConsole.a("Trade error: " + e);
        } finally {
            reset();
        }
    }
}

package net.fdgames.ek.android.lan;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.List;

/**
 * v63 in-game chat box (owner: "the chat button needs to be bigger, and click open into a smaller box that doesn't
 * cover the whole screen, like RuneScape"). deobf/LOBBY_CLEANUP_SPEC.md §2.
 * Replaces the MP mod's full-screen AlertDialog (LanGameBridgeChatRunnable): a see-through panel laid over the game
 * (Activity.addContentView), docked top-centre: the floating joystick owns the bottom-left and the keyboard covers
 * the bottom while typing. Touches outside the panel still reach the game. The CHAT button toggles it.
 */
public final class EkChat {
    private EkChat() {}

    private static final int GOLD = -3629008;
    private static final int TEXT = -725806;
    private static final int PANEL = 0xB4140C06;     // dark brown, ~70 % opaque
    private static final int LOG = 0x66000000;
    private static final int KEEP_LINES = 80;

    private static LinearLayout box;
    private static TextView log;
    private static ScrollView scroll;
    private static EditText input;
    private static String shown = "";
    private static String extra = "";              // "Where" answer, shown under the chat for a while
    private static long extraUntil;

    /** CHAT button (GL thread): open or close the box. */
    public static void toggle() {
        final Activity a = EkItems.act();
        if (a == null) {
            return;
        }
        a.runOnUiThread(new Runnable() {
            public void run() {
                if (box != null) {
                    close(a);
                } else {
                    open(a);
                }
            }
        });
    }

    public static boolean isOpen() {
        return box != null;
    }

    private static void open(final Activity a) {
        try {
            final LanSessionManager m = LanSessionManager.getInstanceIfReady();
            if (m == null || !m.isSessionRunning()) {
                return;
            }
            float d = a.getResources().getDisplayMetrics().density;
            int sw = a.getResources().getDisplayMetrics().widthPixels;
            int sh = a.getResources().getDisplayMetrics().heightPixels;
            int pad = (int) (6 * d);

            LinearLayout panel = new LinearLayout(a);
            panel.setOrientation(1);
            panel.setPadding(pad, pad, pad, pad);
            panel.setBackgroundColor(PANEL);

            LinearLayout head = new LinearLayout(a);
            head.setOrientation(0);
            head.setGravity(16);
            TextView title = new TextView(a);
            title.setText("Chat");
            title.setTextColor(GOLD);
            title.setTextSize(16f);
            head.addView(title, new LinearLayout.LayoutParams(0, -2, 1f));
            head.addView(button(a, "Where", new View.OnClickListener() {
                public void onClick(View v) {
                    where(m);
                }
            }), new LinearLayout.LayoutParams(-2, -2));
            head.addView(button(a, "  X  ", new View.OnClickListener() {
                public void onClick(View v) {
                    close(a);
                }
            }), new LinearLayout.LayoutParams(-2, -2));
            panel.addView(head, new LinearLayout.LayoutParams(-1, -2));

            scroll = new ScrollView(a);
            scroll.setBackgroundColor(LOG);
            log = new TextView(a);
            log.setTextColor(TEXT);
            log.setTextSize(14f);
            log.setPadding(pad, pad / 2, pad, pad / 2);
            scroll.addView(log, new FrameLayout.LayoutParams(-1, -2));
            panel.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1f));

            LinearLayout row = new LinearLayout(a);
            row.setOrientation(0);
            row.setGravity(16);
            input = new EditText(a);
            input.setSingleLine(true);
            input.setHint("Say something...");
            input.setTextColor(-1);
            input.setHintTextColor(0x99FFFFFF);
            input.setTextSize(15f);
            input.setImeOptions(4 | 0x10000000);     // IME_ACTION_SEND | IME_FLAG_NO_EXTRACT_UI (landscape: no full-screen editor)
            input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, android.view.KeyEvent e) {
                    send(m);
                    return true;
                }
            });
            row.addView(input, new LinearLayout.LayoutParams(0, -2, 1f));
            row.addView(button(a, "Send", new View.OnClickListener() {
                public void onClick(View v) {
                    send(m);
                }
            }), new LinearLayout.LayoutParams(-2, -2));
            panel.addView(row, new LinearLayout.LayoutParams(-1, -2));

            int w = Math.max((int) (320 * d), (int) (sw * 0.5f));
            w = Math.min(w, (int) (sw * 0.94f));
            int h = (int) (sh * 0.40f);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(w, h, 48 | 1);   // TOP | CENTER_HORIZONTAL
            lp.topMargin = (int) (8 * d);
            a.addContentView(panel, lp);
            box = panel;
            shown = "";
            refresh(m);
            final LinearLayout mine = panel;
            final Runnable[] tick = new Runnable[1];
            tick[0] = new Runnable() {
                public void run() {
                    if (box != mine) {
                        return;
                    }
                    LanSessionManager cur = LanSessionManager.getInstanceIfReady();
                    if (cur == null || !cur.isSessionRunning() || a.isFinishing()) {
                        close(a);
                        return;
                    }
                    refresh(cur);
                    mine.postDelayed(tick[0], 500L);
                }
            };
            panel.postDelayed(tick[0], 500L);
        } catch (Throwable e) {
            box = null;
        }
    }

    static void close(Activity a) {
        LinearLayout b = box;
        box = null;
        if (b == null) {
            return;
        }
        try {
            InputMethodManager imm = (InputMethodManager) a.getSystemService("input_method");
            if (imm != null && input != null) {
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }
        } catch (Throwable e) {
            // ignore
        }
        if (b.getParent() instanceof ViewGroup) {
            ((ViewGroup) b.getParent()).removeView(b);
        }
        log = null;
        scroll = null;
        input = null;
    }

    private static Button button(Activity a, String label, View.OnClickListener l) {
        Button b = new Button(a);
        b.setText(label);
        b.setTextColor(-1);
        b.setTextSize(14f);
        b.setBackgroundColor(-9549026);               // stock lobby button colour
        float d = a.getResources().getDisplayMetrics().density;
        b.setPadding((int) (10 * d), (int) (4 * d), (int) (10 * d), (int) (4 * d));
        b.setOnClickListener(l);
        return b;
    }

    private static void send(LanSessionManager m) {
        if (input == null || m == null) {
            return;
        }
        String s = input.getText() == null ? "" : input.getText().toString().trim();
        if (s.length() == 0) {
            return;
        }
        m.sendChatAsync(s);
        input.setText("");
    }

    private static void refresh(LanSessionManager m) {
        if (log == null || m == null) {
            return;
        }
        try {
            List<?> lines = m.getChatSnapshot();
            StringBuilder sb = new StringBuilder();
            int from = lines == null ? 0 : Math.max(0, lines.size() - KEEP_LINES);
            for (int i = from; lines != null && i < lines.size(); i++) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(String.valueOf(lines.get(i)));
            }
            if (System.currentTimeMillis() < extraUntil && extra.length() > 0) {
                sb.append(extra);
            }
            String t = sb.toString();
            m.markChatRead();
            if (t.equals(shown)) {
                return;
            }
            shown = t;
            log.setText(t);
            final ScrollView s = scroll;
            if (s != null) {
                s.post(new Runnable() {
                    public void run() {
                        s.fullScroll(130);             // View.FOCUS_DOWN
                    }
                });
            }
        } catch (Throwable e) {
            // next tick
        }
    }

    /** "Where": each other player and the area they're in (the MP mod's version printed Portuguese). */
    private static void where(LanSessionManager m) {
        if (log == null || m == null) {
            return;
        }
        List<?> ps = m.getPeerStatesSnapshot();
        StringBuilder sb = new StringBuilder("\n[Where]");
        if (ps == null || ps.isEmpty()) {
            sb.append("\nNo other players connected.");
        } else {
            for (Object o : ps) {
                if (!(o instanceof LanSessionManager.PlayerState)) {
                    continue;
                }
                LanSessionManager.PlayerState st = (LanSessionManager.PlayerState) o;
                String area = st.currentMapName == null || st.currentMapName.trim().length() == 0 ? "somewhere"
                        : st.currentMapName.trim();
                sb.append('\n').append(st.playerName == null ? "Player" : st.playerName).append(": ").append(area);
            }
        }
        extra = sb.toString();
        extraUntil = System.currentTimeMillis() + 20000L;
        shown = "";
        refresh(m);
    }
}

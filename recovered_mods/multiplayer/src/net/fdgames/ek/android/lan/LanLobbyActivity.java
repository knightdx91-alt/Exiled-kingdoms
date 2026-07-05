package net.fdgames.ek.android.lan;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.fdgames.ek.android.lan.LanSessionManager;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
public class LanLobbyActivity extends Activity implements LanSessionManager.UiListener {
    private static final String PREFS = "ek_lan_prefs";
    private static final String PREF_NAME = "lan_player_name";
    private ScrollView chatScroll;
    private TextView chatView;
    private ScrollView diagScroll;
    private TextView diagView;
    private ArrayAdapter<String> discoveryAdapter;
    private final List<LanSessionManager.DiscoveryResult> discoveryResults = new ArrayList();
    private EditText messageInput;
    private WifiManager.MulticastLock multicastLock;
    private EditText playerNameInput;
    private TextView playersView;
    private LanSessionManager sessionManager;
    private TextView statusView;

    private void acquireMulticastLock() {
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || this.multicastLock != null) {
                return;
            }
            this.multicastLock = wifiManager.createMulticastLock("ek-lan-lock");
            this.multicastLock.setReferenceCounted(false);
            this.multicastLock.acquire();
        } catch (Exception e2) {
        }
    }

    private void addControlButton(LinearLayout linearLayout, String str, View.OnClickListener onClickListener) {
        Button button = new Button(this);
        button.setText(str);
        button.setOnClickListener(onClickListener);
        button.setTextColor(-1);
        button.setTextSize(16.0f);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-9549026);
        gradientDrawable.setCornerRadius(dp(6));
        gradientDrawable.setStroke(dp(2), -3629008);
        button.setBackground(gradientDrawable);
        int iDp = dp(8);
        button.setPadding(iDp, iDp, iDp, iDp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
        int iDp2 = dp(3);
        layoutParams.leftMargin = iDp2;
        layoutParams.rightMargin = iDp2;
        linearLayout.addView(button, layoutParams);
    }

    private View buildContentView() {
        int iDp = dp(12);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(iDp, iDp, iDp, iDp);
        linearLayout.setBackgroundColor(-15068664);
        TextView textView = new TextView(this);
        textView.setText("LAN / CHAT BETA");
        textView.setTextSize(22.0f);
        textView.setGravity(1);
        textView.setTextColor(-3629008);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        TextView textView2 = new TextView(this);
        textView2.setText(lanString("lan_subtitle", "Host, join, discover LAN sessions and chat. Map/combat sync will arrive in phases."));
        textView2.setTextColor(-3295088);
        textView2.setTextSize(14.0f);
        textView2.setPadding(0, dp(6), 0, dp(10));
        linearLayout.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
        this.statusView = new TextView(this);
        this.statusView.setPadding(iDp, iDp, iDp, iDp);
        this.statusView.setTextSize(15.0f);
        this.statusView.setBackgroundColor(1714626317);
        this.statusView.setTextColor(-725806);
        TextView textView3 = this.statusView;
        textView3.setMaxLines(3);
        linearLayout.addView(textView3, new LinearLayout.LayoutParams(-1, -2));
        this.playerNameInput = new EditText(this);
        this.playerNameInput.setHint(lanString("lan_player_name_hint", "Player name"));
        this.playerNameInput.setSingleLine(true);
        this.playerNameInput.setInputType(8193);
        linearLayout.addView(this.playerNameInput, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(0);
        linearLayout2.setPadding(0, dp(8), 0, dp(8));
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
        addControlButton(linearLayout2, lanString("lan_button_host", "Host"), new View.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanLobbyActivity.this.savePlayerName();
                LanLobbyActivity.this.startHostingAsync(LanLobbyActivity.this.playerNameInput.getText().toString().trim(), 6);
            }
        });
        addControlButton(linearLayout2, lanString("lan_button_join_ip", "Join IP"), new View.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanLobbyActivity.this.promptJoinByIp();
            }
        });
        addControlButton(linearLayout2, lanString("lan_button_scan_lan", "Scan LAN"), new View.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanLobbyActivity.this.savePlayerName();
                LanLobbyActivity.this.sessionManager.scanLan(LanLobbyActivity.this.playerNameInput.getText().toString().trim());
            }
        });
        addControlButton(linearLayout2, lanString("lan_button_leave", "Leave"), new View.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanLobbyActivity.this.sessionManager.stopAll();
            }
        });
        this.diagScroll = new ScrollView(this);
        this.diagScroll.setVisibility(8);
        this.diagScroll.setFillViewport(true);
        this.diagScroll.setScrollbarFadingEnabled(false);
        this.diagScroll.setBackgroundColor(1293553415);
        this.diagView = new TextView(this);
        TextView textView4 = this.diagView;
        int iDp2 = dp(6);
        textView4.setPadding(iDp2, iDp2, iDp2, iDp2);
        this.diagView.setTextSize(12.0f);
        this.diagView.setTextColor(-2176125);
        this.diagScroll.addView(this.diagView, new FrameLayout.LayoutParams(-1, -2));
        linearLayout.addView(this.diagScroll, new LinearLayout.LayoutParams(-1, dp(44)));
        TextView textView5 = new TextView(this);
        textView5.setText(lanString("lan_heading_sessions", "Discovered sessions"));
        textView5.setTextColor(-2176125);
        textView5.setTextSize(16.0f);
        textView5.setPadding(0, dp(6), 0, dp(4));
        linearLayout.addView(textView5);
        ListView listView = new ListView(this);
        this.discoveryAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, new ArrayList());
        listView.setAdapter((ListAdapter) this.discoveryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                if (i2 < 0 || i2 >= LanLobbyActivity.this.discoveryResults.size()) {
                    return;
                }
                LanLobbyActivity.this.savePlayerName();
                LanSessionManager.DiscoveryResult discoveryResult = (LanSessionManager.DiscoveryResult) LanLobbyActivity.this.discoveryResults.get(i2);
                LanLobbyActivity.this.joinHostAsync(LanLobbyActivity.this.playerNameInput.getText().toString().trim(), discoveryResult.address, discoveryResult.port);
            }
        });
        linearLayout.addView(listView, new LinearLayout.LayoutParams(-1, dp(140)));
        TextView textView6 = new TextView(this);
        textView6.setText(lanString("lan_heading_players", "Players in room"));
        textView6.setTextColor(-2176125);
        textView6.setTextSize(16.0f);
        textView6.setPadding(0, dp(6), 0, dp(4));
        linearLayout.addView(textView6);
        this.playersView = new TextView(this);
        this.playersView.setPadding(iDp, iDp, iDp, iDp);
        this.playersView.setBackgroundColor(572462605);
        this.playersView.setTextColor(-725806);
        linearLayout.addView(this.playersView, new LinearLayout.LayoutParams(-1, dp(90)));
        TextView textView7 = new TextView(this);
        textView7.setText(lanString("lan_heading_chat", "Room chat"));
        textView7.setTextColor(-2176125);
        textView7.setTextSize(16.0f);
        textView7.setPadding(0, dp(6), 0, dp(4));
        linearLayout.addView(textView7);
        this.chatScroll = new ScrollView(this);
        this.chatView = new TextView(this);
        this.chatView.setPadding(iDp, iDp, iDp, iDp);
        this.chatView.setTextColor(-725806);
        this.chatScroll.setBackgroundColor(572462605);
        this.chatScroll.addView(this.chatView, new FrameLayout.LayoutParams(-1, -2));
        linearLayout.addView(this.chatScroll, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(0);
        linearLayout3.setPadding(0, dp(8), 0, 0);
        linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
        this.messageInput = new EditText(this);
        this.messageInput.setHint(lanString("lan_message_hint", "Message"));
        this.messageInput.setSingleLine(true);
        linearLayout3.addView(this.messageInput, new LinearLayout.LayoutParams(0, -2, 1.0f));
        Button button = new Button(this);
        button.setText(lanString("lan_button_send", "Send"));
        button.setOnClickListener(new View.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String strTrim = LanLobbyActivity.this.messageInput.getText().toString().trim();
                if (strTrim.isEmpty()) {
                    return;
                }
                LanLobbyActivity.this.savePlayerName();
                LanLobbyActivity.this.sessionManager.sendChatAsync(strTrim);
                LanLobbyActivity.this.messageInput.setText("");
            }
        });
        linearLayout3.addView(button, new LinearLayout.LayoutParams(-2, -2));
        return linearLayout;
    }

    private int dp(int i2) {
        return Math.round(getResources().getDisplayMetrics().density * i2);
    }

    private boolean isPortuguese() {
        return Locale.getDefault().getLanguage().toLowerCase(Locale.US).startsWith("pt");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void joinHostAsync(final String str, final String str2, final int i2) {
        Thread thread = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.9
            @Override // java.lang.Runnable
            public void run() {
                LanLobbyActivity.this.sessionManager.joinHost(str, str2, i2);
            }
        }, "ek-lan-join-ui");
        thread.setDaemon(true);
        thread.start();
    }

    private String lanString(String str, String str2) {
        String string;
        int identifier = getResources().getIdentifier(str, "string", getPackageName());
        return (identifier == 0 || (string = getString(identifier)) == null) ? str2 : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void promptJoinByIp() {
        final EditText editText = new EditText(this);
        editText.setHint("192.168.0.15");
        editText.setSingleLine(true);
        editText.setInputType(1);
        new AlertDialog.Builder(this).setTitle(lanString("lan_join_ip_title", "Join by IP")).setView(editText).setPositiveButton(lanString("lan_button_join", "Join"), new DialogInterface.OnClickListener() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f$0.m5lambda$promptJoinByIp$0$netfdgamesekandroidlanLanLobbyActivity(editText, dialogInterface, i2);
            }
        }).setNegativeButton(lanString("lan_button_cancel", "Cancel"), (DialogInterface.OnClickListener) null).show();
    }

    private void refreshUi() {
        String localPlayerName = this.sessionManager.getLocalPlayerName();
        if (localPlayerName != null) {
            String strTrim = localPlayerName.trim();
            if (!strTrim.isEmpty() && !strTrim.equals(this.playerNameInput.getText().toString().trim())) {
                this.playerNameInput.setText(strTrim);
                savePlayerName();
            }
        }
        onStateChanged(this.sessionManager.getStateText());
        onPlayersChanged(this.sessionManager.getPlayersSnapshot());
        onChatUpdated(this.sessionManager.getChatSnapshot());
        onDiscoveryUpdated(this.sessionManager.getDiscoverySnapshot());
    }

    private void releaseMulticastLock() {
        try {
            if (this.multicastLock != null && this.multicastLock.isHeld()) {
                this.multicastLock.release();
            }
        } catch (Exception e2) {
        } catch (Throwable th) {
            this.multicastLock = null;
            throw th;
        }
        this.multicastLock = null;
    }

    private void restorePlayerName() {
        this.playerNameInput.setText(getSharedPreferences(PREFS, 0).getString(PREF_NAME, lanString("lan_default_player_name", "Player")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePlayerName() {
        getSharedPreferences(PREFS, 0).edit().putString(PREF_NAME, this.playerNameInput.getText().toString().trim()).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startHostingAsync(final String str, final int i2) {
        Thread thread = new Thread(new Runnable() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.8
            @Override // java.lang.Runnable
            public void run() {
                LanLobbyActivity.this.sessionManager.startHosting(str, i2);
            }
        }, "ek-lan-host-ui");
        thread.setDaemon(true);
        thread.start();
    }

    /* JADX INFO: renamed from: lambda$promptJoinByIp$0$net-fdgames-ek-android-lan-LanLobbyActivity, reason: not valid java name */
    /* synthetic */ void m5lambda$promptJoinByIp$0$netfdgamesekandroidlanLanLobbyActivity(EditText editText, DialogInterface dialogInterface, int i2) {
        savePlayerName();
        joinHostAsync(this.playerNameInput.getText().toString().trim(), editText.getText().toString().trim(), LanSessionManager.CHAT_PORT);
    }

    @Override // net.fdgames.ek.android.lan.LanSessionManager.UiListener
    public void onChatUpdated(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append('\n');
        }
        this.chatView.setText(sb.toString().trim());
        this.chatScroll.post(new Runnable() { // from class: net.fdgames.ek.android.lan.LanLobbyActivity.7
            @Override // java.lang.Runnable
            public void run() {
                LanLobbyActivity.this.chatScroll.fullScroll(130);
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sessionManager = LanSessionManager.get(getApplicationContext());
        setContentView(buildContentView());
        restorePlayerName();
        this.sessionManager.setUiListener(this);
        refreshUi();
    }

    @Override // net.fdgames.ek.android.lan.LanSessionManager.UiListener
    public void onDiscoveryUpdated(List<LanSessionManager.DiscoveryResult> list) {
        this.discoveryResults.clear();
        this.discoveryResults.addAll(list);
        this.discoveryAdapter.clear();
        if (list.isEmpty()) {
            this.discoveryAdapter.add(lanString("lan_no_sessions", "No LAN session found."));
        } else {
            for (LanSessionManager.DiscoveryResult discoveryResult : list) {
                this.discoveryAdapter.add(discoveryResult.sessionName + " - " + discoveryResult.address + ":" + discoveryResult.port + " - " + discoveryResult.playerCount + "/" + discoveryResult.maxPlayers);
            }
        }
        this.discoveryAdapter.notifyDataSetChanged();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (!this.sessionManager.isSessionRunning()) {
            releaseMulticastLock();
        }
        this.sessionManager.setUiListener(null);
    }

    @Override // net.fdgames.ek.android.lan.LanSessionManager.UiListener
    public void onPlayersChanged(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list.isEmpty()) {
            sb.append(isPortuguese() ? "Nenhum jogador conectado." : "No players connected.");
        } else {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                sb.append(" - ").append(it.next()).append('\n');
            }
        }
        this.playersView.setText(sb.toString().trim());
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.sessionManager.setUiListener(this);
        acquireMulticastLock();
        refreshUi();
    }

    @Override // net.fdgames.ek.android.lan.LanSessionManager.UiListener
    public void onStateChanged(String str) {
        TextView textView = this.statusView;
        TextView textView2 = this.diagView;
        ScrollView scrollView = this.diagScroll;
        if (str == null) {
            textView.setText("");
            if (textView2 != null) {
                textView2.setText("");
            }
        } else {
            int iIndexOf = str.indexOf("LAN DIAG ");
            if (iIndexOf >= 0) {
                textView.setText(str.substring(0, iIndexOf).trim());
                String strTrim = str.substring(iIndexOf).trim();
                if (textView2 != null) {
                    textView2.setText(strTrim);
                }
                if (scrollView != null) {
                    scrollView.setVisibility(0);
                    scrollView.fullScroll(130);
                    return;
                }
                return;
            }
            textView.setText(str.trim());
            if (textView2 != null) {
                textView2.setText("");
            }
        }
        if (scrollView != null) {
            scrollView.setVisibility(8);
        }
    }

    @Override // net.fdgames.ek.android.lan.LanSessionManager.UiListener
    public void onToast(String str) {
        Toast.makeText(this, str, 0).show();
    }
}

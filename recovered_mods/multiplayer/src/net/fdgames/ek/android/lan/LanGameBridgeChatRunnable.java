package net.fdgames.ek.android.lan;

import android.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import net.fdgames.Helpers.GameString;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: compiled from: LanGameBridge.java */
/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
final class LanGameBridgeChatRunnable implements Runnable {
    private final MainActivity activity;
    private final LanSessionManager sessionManager;

    LanGameBridgeChatRunnable(MainActivity mainActivity, LanSessionManager lanSessionManager) {
        this.activity = mainActivity;
        this.sessionManager = lanSessionManager;
    }

    @Override // java.lang.Runnable
    public void run() {
        LanSessionManager lanSessionManager;
        MainActivity mainActivity = this.activity;
        if (mainActivity == null || mainActivity.isFinishing() || (lanSessionManager = this.sessionManager) == null || !lanSessionManager.isSessionRunning()) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(24, 24, 24, 24);
        linearLayout.setBackgroundColor(-15068664);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setBackgroundColor(-870707449);
        TextView textView = new TextView(mainActivity);
        textView.setTextSize(16.0f);
        textView.setTextColor(-725806);
        textView.setPadding(8, 8, 8, 8);
        scrollView.addView(textView);
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        EditText editText = new EditText(mainActivity);
        editText.setHint(GameString.b("LAN_CHAT_HINT", false));
        editText.setSingleLine(false);
        linearLayout.addView(editText, new LinearLayout.LayoutParams(-1, -2));
        Button button = new Button(mainActivity);
        button.setText(GameString.b("LAN_CHAT_LOCATION", false));
        button.setOnClickListener(new LanGameBridgeLocationButtonListener(lanSessionManager, textView));
        linearLayout.addView(button);
        AlertDialog alertDialogShow = new AlertDialog.Builder(mainActivity).setTitle(GameString.b("LAN_CHAT_TITLE", false)).setView(linearLayout).setPositiveButton(GameString.b("LAN_CHAT_SEND", false), (DialogInterface.OnClickListener) null).setNeutralButton(GameString.b("LAN_CHAT_REFRESH", false), (DialogInterface.OnClickListener) null).setNegativeButton(GameString.b("LAN_CHAT_CLOSE", false), (DialogInterface.OnClickListener) null).show();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-15068664);
        gradientDrawable.setCornerRadius(8.0f);
        gradientDrawable.setStroke(2, -3629008);
        Window window = alertDialogShow.getWindow();
        window.setBackgroundDrawable(gradientDrawable);
        View viewFindViewById = window.getDecorView().findViewById(R.id.title);
        if (viewFindViewById != null) {
            TextView textView2 = (TextView) viewFindViewById;
            textView2.setTextColor(-3629008);
            textView2.setTextSize(18.0f);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(-9549026);
        gradientDrawable2.setCornerRadius(6.0f);
        gradientDrawable2.setStroke(2, -3629008);
        Button button2 = alertDialogShow.getButton(-1);
        if (button2 != null) {
            button2.setBackground(gradientDrawable2);
            button2.setTextColor(-1);
        }
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(0);
        gradientDrawable3.setColor(-9549026);
        gradientDrawable3.setCornerRadius(6.0f);
        gradientDrawable3.setStroke(2, -3629008);
        Button button3 = alertDialogShow.getButton(-3);
        if (button3 != null) {
            button3.setBackground(gradientDrawable3);
            button3.setTextColor(-1);
        }
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setShape(0);
        gradientDrawable4.setColor(-9549026);
        gradientDrawable4.setCornerRadius(6.0f);
        gradientDrawable4.setStroke(2, -3629008);
        Button button4 = alertDialogShow.getButton(-2);
        if (button4 != null) {
            button4.setBackground(gradientDrawable4);
            button4.setTextColor(-1);
        }
        LanGameBridge.refreshChatDialogView(lanSessionManager, textView, scrollView);
        alertDialogShow.getButton(-1).setOnClickListener(new LanGameBridgeChatButtonListener(0, lanSessionManager, editText, textView));
        alertDialogShow.getButton(-3).setOnClickListener(new LanGameBridgeChatButtonListener(1, lanSessionManager, editText, textView));
    }
}

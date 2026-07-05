package net.fdgames.ek.android.lan;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: compiled from: LanGameBridge.java */
/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
final class LanGameBridgeChatButtonListener implements View.OnClickListener {
    private final TextView chatView;
    private final EditText input;
    private final int mode;
    private final LanSessionManager sessionManager;

    LanGameBridgeChatButtonListener(int i2, LanSessionManager lanSessionManager, EditText editText, TextView textView) {
        this.mode = i2;
        this.sessionManager = lanSessionManager;
        this.input = editText;
        this.chatView = textView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mode != 0) {
            LanGameBridge.refreshChatDialogView(this.sessionManager, this.chatView, null);
            return;
        }
        EditText editText = this.input;
        if (editText != null) {
            this.sessionManager.sendChatAsync(editText.getText().toString());
            this.input.setText("");
        }
        LanGameBridge.refreshChatDialogView(this.sessionManager, this.chatView, null);
    }
}

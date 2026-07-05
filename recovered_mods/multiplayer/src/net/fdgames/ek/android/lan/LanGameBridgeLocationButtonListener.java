package net.fdgames.ek.android.lan;

import android.view.View;
import android.widget.TextView;
import java.util.List;
import net.fdgames.ek.android.lan.LanSessionManager;

/* JADX INFO: compiled from: LanGameBridge.java */
/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes2.dex */
final class LanGameBridgeLocationButtonListener implements View.OnClickListener {
    private final TextView chatView;
    private final LanSessionManager sessionManager;

    LanGameBridgeLocationButtonListener(LanSessionManager lanSessionManager, TextView textView) {
        this.sessionManager = lanSessionManager;
        this.chatView = textView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LanSessionManager lanSessionManager = this.sessionManager;
        TextView textView = this.chatView;
        if (lanSessionManager == null || textView == null) {
            return;
        }
        textView.append("\n[Localizacao]\n");
        List<LanSessionManager.PlayerState> peerStatesSnapshot = lanSessionManager.getPeerStatesSnapshot();
        if (peerStatesSnapshot == null || peerStatesSnapshot.isEmpty()) {
            textView.append("Nenhum jogador conectado.\n");
            return;
        }
        for (LanSessionManager.PlayerState playerState : peerStatesSnapshot) {
            textView.append("[Loc] ");
            String str = playerState.playerName;
            if (str == null) {
                str = "Jogador";
            }
            textView.append(str);
            String str2 = playerState.characterName;
            if (str2 != null) {
                textView.append(" (");
                textView.append(str2);
                String str3 = playerState.className;
                if (str3 != null) {
                    textView.append(" - ");
                    textView.append(str3);
                }
                int i2 = playerState.level;
                if (i2 != 0) {
                    textView.append(" Nv.");
                    textView.append(Integer.toString(i2));
                }
                textView.append(")");
            }
            textView.append("\n");
            String str4 = playerState.currentMapName;
            if (str4 != null) {
                textView.append("  Mapa: ");
                textView.append(str4);
                textView.append("\n");
            }
            String str5 = playerState.actorStateName;
            if (str5 != null) {
                textView.append("  Estado: ");
                textView.append(str5.contains("COMBAT") ? "Em Combate" : "Livre");
                int i3 = playerState.missingHp;
                if (i3 > 0) {
                    textView.append(" | -");
                    textView.append(Integer.toString(i3));
                    textView.append(" HP");
                }
                textView.append("\n");
            }
            String str6 = playerState.companionName;
            if (str6 != null && !str6.isEmpty()) {
                textView.append("  Companheiro: ");
                textView.append(str6);
                textView.append("\n");
            }
            textView.append("---\n");
        }
    }
}

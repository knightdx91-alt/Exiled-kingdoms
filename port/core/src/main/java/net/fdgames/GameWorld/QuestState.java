package net.fdgames.GameWorld;

import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class QuestState {
    private ActionsSet actions;
    private String description;
    private int value;

    public QuestState(int i2, String str, String str2) {
        this.value = i2;
        this.description = FDUtils.f(str);
        this.actions = new ActionsSet(str2);
    }

    public final String a() {
        return this.description;
    }

    public final int b() {
        return this.value;
    }
}

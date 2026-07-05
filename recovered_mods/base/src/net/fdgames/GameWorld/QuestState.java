package net.fdgames.GameWorld;

import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
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

package net.fdgames.Rules;

import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SkillLevel {
    public int cooldown;
    public int cost;
    private String description;
    public int mana_cost;
    public String name;

    public SkillLevel(int i2, String str, String str2, String str3, String str4) {
        this.name = str;
        this.description = str2;
        this.cost = i2;
        if (str4.trim().equals("")) {
            this.mana_cost = 0;
        } else {
            this.mana_cost = Integer.parseInt(str4);
        }
        if (str3.equals("")) {
            return;
        }
        this.cooldown = Integer.parseInt(str3);
    }

    public final String a() {
        return !this.description.equals("") ? this.description : GameString.b(pgtXvpMCFu.RczUsMutX, false);
    }
}

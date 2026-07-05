package net.fdgames.GameWorld;

import java.util.Locale;
import net.fdgames.Rules.Rules;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class QuestVariation {
    public int difficulty;
    public int duration;
    public int factionGain;
    public int factionLoss;
    public String id;
    public int item_id;
    public String locations;
    public int maxFactionGain;
    public int maxlevel;
    public int minlevel;
    public String rarity;
    public String reward_table;
    public String rivalFaction;
    public String spawn_id;
    public String spawn_id2;
    public String spawn_id3;
    private String target;
    public String target_ES;

    public QuestVariation(String str) {
        String[] strArrSplit = str.replace("\"", "").split("\t", -1);
        this.id = strArrSplit[0];
        this.rarity = strArrSplit[1];
        this.spawn_id = strArrSplit[2];
        this.spawn_id2 = strArrSplit[3];
        this.spawn_id3 = strArrSplit[4];
        int i2 = Integer.parseInt(strArrSplit[5]);
        this.item_id = i2;
        if (i2 > 0) {
            this.target = Rules.f(i2).name;
            this.target_ES = Rules.f(this.item_id).name;
        } else {
            this.target = strArrSplit[6];
            this.target_ES = strArrSplit[7];
        }
        this.reward_table = strArrSplit[8];
        this.difficulty = Integer.parseInt(strArrSplit[9]);
        this.locations = strArrSplit[10].toLowerCase(Locale.ENGLISH);
        this.duration = Integer.parseInt(strArrSplit[11]);
        this.maxFactionGain = Integer.parseInt(strArrSplit[12]);
        this.factionGain = Integer.parseInt(strArrSplit[13]);
        this.factionLoss = Integer.parseInt(strArrSplit[14]);
        this.rivalFaction = strArrSplit[15];
        this.minlevel = Integer.parseInt(strArrSplit[16]);
        this.maxlevel = Integer.parseInt(strArrSplit[17]);
    }

    public final String a() {
        return Settings.h() == 2 ? this.target_ES : this.target;
    }
}

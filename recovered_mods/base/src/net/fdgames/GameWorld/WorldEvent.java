package net.fdgames.GameWorld;

import java.util.Locale;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class WorldEvent {
    public String drop_table;
    public String id;
    public String locations;
    public int maxlevel;
    public int minlevel;
    public String nameTable;
    public FDUtils.Rarity rarity;
    public String spawn_id;
    public String spawn_id2;
    private String text;
    private String title;

    public WorldEvent(String str) {
        String[] strArrSplit = str.replace("\"", "").split("\t", -1);
        this.id = strArrSplit[0];
        this.rarity = FDUtils.t(strArrSplit[1]);
        this.nameTable = strArrSplit[2];
        this.spawn_id = strArrSplit[3];
        this.spawn_id2 = strArrSplit[4];
        this.locations = strArrSplit[5].toLowerCase(Locale.ENGLISH);
        if (strArrSplit[6].trim().equals("")) {
            this.minlevel = 1;
        } else {
            this.minlevel = Integer.parseInt(strArrSplit[6]);
        }
        if (strArrSplit[7].trim().equals("")) {
            this.maxlevel = 99;
        } else {
            this.maxlevel = Integer.parseInt(strArrSplit[7]);
        }
        this.title = GameString.b("BOSS_TITLE", false);
        this.text = GameString.b("BOSS_TEXT", false);
    }

    public final String a() {
        return this.title;
    }

    public final String b() {
        return this.text;
    }
}

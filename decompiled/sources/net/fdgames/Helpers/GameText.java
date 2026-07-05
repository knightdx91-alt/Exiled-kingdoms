package net.fdgames.Helpers;

import java.util.Locale;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GameText {
    public String tag;
    private String text;

    public GameText() {
    }

    public String get() {
        return this.text;
    }

    public GameText(String str) {
        this(str, false, false);
    }

    public GameText(String str, boolean z2, boolean z3) {
        int iH = Settings.h();
        if (z2) {
            if (z3) {
                iH = Settings.h() * 2;
            } else {
                iH = (Settings.h() * 2) - 1;
            }
        }
        String[] strArrSplit = str.split("\t", -1);
        this.tag = strArrSplit[0].toLowerCase(Locale.ENGLISH).trim();
        if (strArrSplit.length > iH && !strArrSplit[iH].trim().equals("")) {
            this.text = strArrSplit[iH];
            return;
        }
        this.text = strArrSplit[1];
        if (z3) {
            this.text = strArrSplit[2];
        }
    }
}

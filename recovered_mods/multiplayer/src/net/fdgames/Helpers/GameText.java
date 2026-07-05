package net.fdgames.Helpers;

import java.util.Locale;
import net.fdgames.ek.Settings;
import o.Oeoo.vIBRkbZbNjpf;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameText {
    public String tag;
    private String text;

    public GameText() {
    }

    public GameText(String str) {
        this(str, false, false);
    }

    public GameText(String str, boolean z2, boolean z3) {
        int iH = z2 ? z3 ? Settings.h() * 2 : (Settings.h() * 2) - 1 : Settings.h();
        String[] strArrSplit = str.split("\t", -1);
        this.tag = strArrSplit[0].toLowerCase(Locale.ENGLISH).trim();
        if (strArrSplit.length > iH && !strArrSplit[iH].trim().equals(vIBRkbZbNjpf.moMwfAVNwjXa)) {
            this.text = strArrSplit[iH];
            return;
        }
        this.text = strArrSplit[1];
        if (z3) {
            this.text = strArrSplit[2];
        }
    }

    public String get() {
        return this.text;
    }
}

package net.fdgames.Helpers;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class LocalizedString {
    public String en;
    public String es;
    public String id;

    public LocalizedString(String str) {
        String[] strArrSplit = str.split("\t", -1);
        this.id = strArrSplit[0].toLowerCase(Locale.ENGLISH).trim();
        this.en = strArrSplit[1];
        this.es = strArrSplit[2];
    }
}

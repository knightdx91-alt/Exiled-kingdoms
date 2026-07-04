package net.fdgames.Helpers;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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

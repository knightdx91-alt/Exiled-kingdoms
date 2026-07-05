package net.fdgames.GameLogic;

import a.a;
import java.util.Iterator;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterTraits;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PlayerRequirements {
    private int AGI;
    private int AWA;
    private int END;
    private int INT;
    private int PER;
    private int STR;

    public PlayerRequirements(String str) {
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            String str2 = strArrSplit[0];
            int i2 = Integer.parseInt(strArrSplit[1]);
            if (str2.equals("STR")) {
                this.STR = i2;
            }
            if (str2.equals("END")) {
                this.END = i2;
            }
            if (str2.equals("AGI")) {
                this.AGI = i2;
            }
            if (str2.equals("AWA")) {
                this.AWA = i2;
            }
            if (str2.equals("INT")) {
                this.INT = i2;
            }
            if (str2.equals("PER")) {
                this.PER = i2;
            }
        }
    }

    private static String b(boolean z2) {
        return z2 ? "[BLACK]" : "[RED]";
    }

    public final boolean a(CharacterSheet characterSheet, boolean z2) {
        return z2 ? characterSheet.K(0) >= this.STR && characterSheet.K(1) >= this.END && characterSheet.K(2) >= this.AGI && characterSheet.K(4) >= this.AWA && characterSheet.K(3) >= this.INT && characterSheet.K(5) >= this.PER : characterSheet.I(0) >= this.STR && characterSheet.I(1) >= this.END && characterSheet.I(2) >= this.AGI && characterSheet.I(4) >= this.AWA && characterSheet.I(3) >= this.INT && characterSheet.I(5) >= this.PER;
    }

    public final String c(CharacterSheet characterSheet) {
        String strP = "";
        if (this.STR > 0) {
            StringBuilder sb = new StringBuilder("");
            sb.append(b(characterSheet.K(0) >= this.STR));
            sb.append(" ");
            sb.append(CharacterTraits.g(0));
            sb.append(" ");
            strP = a.p(sb, this.STR, "[],");
        }
        if (this.END > 0) {
            StringBuilder sbS = a.s(strP);
            sbS.append(b(characterSheet.K(1) >= this.END));
            sbS.append(" ");
            sbS.append(CharacterTraits.g(1));
            sbS.append(" ");
            strP = a.p(sbS, this.END, "[],");
        }
        if (this.AGI > 0) {
            StringBuilder sbS2 = a.s(strP);
            sbS2.append(b(characterSheet.K(2) >= this.AGI));
            sbS2.append(" ");
            sbS2.append(CharacterTraits.g(2));
            sbS2.append(" ");
            strP = a.p(sbS2, this.AGI, "[],");
        }
        if (this.AWA > 0) {
            StringBuilder sbS3 = a.s(strP);
            sbS3.append(b(characterSheet.K(4) >= this.AWA));
            sbS3.append(" ");
            sbS3.append(CharacterTraits.g(4));
            sbS3.append(" ");
            strP = a.p(sbS3, this.AWA, "[],");
        }
        if (this.INT > 0) {
            StringBuilder sbS4 = a.s(strP);
            sbS4.append(b(characterSheet.K(3) >= this.INT));
            sbS4.append(" ");
            sbS4.append(CharacterTraits.g(3));
            sbS4.append(" ");
            strP = a.p(sbS4, this.INT, "[],");
        }
        if (this.PER > 0) {
            StringBuilder sbS5 = a.s(strP);
            sbS5.append(b(characterSheet.K(5) >= this.PER));
            sbS5.append(" ");
            sbS5.append(CharacterTraits.g(5));
            sbS5.append(" ");
            strP = a.p(sbS5, this.PER, "[],");
        }
        return (strP.length() <= 0 || strP.charAt(strP.length() - 1) != ',') ? strP : strP.substring(0, strP.length() - 1);
    }

    public final boolean d() {
        return this.STR > 0 || this.END > 0 || this.AGI > 0 || this.AWA > 0 || this.INT > 0 || this.PER > 0;
    }

    public final String toString() {
        String strSubstring = "";
        if (this.STR > 0) {
            StringBuilder sb = new StringBuilder("");
            sb.append(CharacterTraits.f(0));
            sb.append(" ");
            strSubstring = a.p(sb, this.STR, ",");
        }
        if (this.END > 0) {
            StringBuilder sbS = a.s(strSubstring);
            sbS.append(CharacterTraits.f(1));
            sbS.append(" ");
            strSubstring = a.p(sbS, this.END, ",");
        }
        if (this.AGI > 0) {
            StringBuilder sbS2 = a.s(strSubstring);
            sbS2.append(CharacterTraits.f(2));
            sbS2.append(" ");
            strSubstring = a.p(sbS2, this.AGI, ",");
        }
        if (this.AWA > 0) {
            StringBuilder sbS3 = a.s(strSubstring);
            sbS3.append(CharacterTraits.f(4));
            sbS3.append(" ");
            strSubstring = a.p(sbS3, this.AWA, ",");
        }
        if (this.INT > 0) {
            StringBuilder sbS4 = a.s(strSubstring);
            sbS4.append(CharacterTraits.f(3));
            sbS4.append(" ");
            strSubstring = a.p(sbS4, this.INT, ",");
        }
        if (this.PER > 0) {
            StringBuilder sbS5 = a.s(strSubstring);
            sbS5.append(CharacterTraits.f(5));
            sbS5.append(" ");
            strSubstring = a.p(sbS5, this.PER, ",");
        }
        if (strSubstring.length() > 0 && strSubstring.charAt(strSubstring.length() - 1) == ',') {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return strSubstring.length() > 0 ? a.l("[TEAL](", strSubstring, ")[]") : strSubstring;
    }
}

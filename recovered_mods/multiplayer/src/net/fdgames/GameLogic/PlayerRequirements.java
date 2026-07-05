package net.fdgames.GameLogic;

import a.a;
import android.app.cYK.BQmoQ;
import com.badlogic.gdx.math.tuHF.LpvdRIktEBw;
import java.util.Iterator;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterTraits;
import net.fdgames.Helpers.FDUtils;
import o.Oeoo.vIBRkbZbNjpf;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
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
            if (str2.equals(BQmoQ.gqnrLkazMqs)) {
                this.STR = i2;
            }
            if (str2.equals("END")) {
                this.END = i2;
            }
            if (str2.equals("AGI")) {
                this.AGI = i2;
            }
            if (str2.equals(LpvdRIktEBw.UriYLDEAyL)) {
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
        int i2 = this.STR;
        String strP = vIBRkbZbNjpf.twaTXmyfkCp;
        if (i2 > 0) {
            StringBuilder sb = new StringBuilder(strP);
            sb.append(b(characterSheet.K(0) >= this.STR));
            sb.append(" ");
            sb.append(CharacterTraits.g(0));
            sb.append(" ");
            strP = a.p(sb, this.STR, "[],");
        }
        if (this.END > 0) {
            StringBuilder sbR = a.r(strP);
            sbR.append(b(characterSheet.K(1) >= this.END));
            sbR.append(" ");
            sbR.append(CharacterTraits.g(1));
            sbR.append(" ");
            strP = a.p(sbR, this.END, "[],");
        }
        if (this.AGI > 0) {
            StringBuilder sbR2 = a.r(strP);
            sbR2.append(b(characterSheet.K(2) >= this.AGI));
            sbR2.append(" ");
            sbR2.append(CharacterTraits.g(2));
            sbR2.append(" ");
            strP = a.p(sbR2, this.AGI, "[],");
        }
        if (this.AWA > 0) {
            StringBuilder sbR3 = a.r(strP);
            sbR3.append(b(characterSheet.K(4) >= this.AWA));
            sbR3.append(" ");
            sbR3.append(CharacterTraits.g(4));
            sbR3.append(" ");
            strP = a.p(sbR3, this.AWA, "[],");
        }
        if (this.INT > 0) {
            StringBuilder sbR4 = a.r(strP);
            sbR4.append(b(characterSheet.K(3) >= this.INT));
            sbR4.append(" ");
            sbR4.append(CharacterTraits.g(3));
            sbR4.append(" ");
            strP = a.p(sbR4, this.INT, "[],");
        }
        if (this.PER > 0) {
            StringBuilder sbR5 = a.r(strP);
            sbR5.append(b(characterSheet.K(5) >= this.PER));
            sbR5.append(" ");
            sbR5.append(CharacterTraits.g(5));
            sbR5.append(" ");
            strP = a.p(sbR5, this.PER, "[],");
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
            StringBuilder sbR = a.r(strSubstring);
            sbR.append(CharacterTraits.f(1));
            sbR.append(" ");
            strSubstring = a.p(sbR, this.END, ",");
        }
        if (this.AGI > 0) {
            StringBuilder sbR2 = a.r(strSubstring);
            sbR2.append(CharacterTraits.f(2));
            sbR2.append(" ");
            strSubstring = a.p(sbR2, this.AGI, ",");
        }
        if (this.AWA > 0) {
            StringBuilder sbR3 = a.r(strSubstring);
            sbR3.append(CharacterTraits.f(4));
            sbR3.append(" ");
            strSubstring = a.p(sbR3, this.AWA, ",");
        }
        if (this.INT > 0) {
            StringBuilder sbR4 = a.r(strSubstring);
            sbR4.append(CharacterTraits.f(3));
            sbR4.append(" ");
            strSubstring = a.p(sbR4, this.INT, ",");
        }
        if (this.PER > 0) {
            StringBuilder sbR5 = a.r(strSubstring);
            sbR5.append(CharacterTraits.f(5));
            sbR5.append(" ");
            strSubstring = a.p(sbR5, this.PER, ",");
        }
        if (strSubstring.length() > 0 && strSubstring.charAt(strSubstring.length() - 1) == ',') {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return strSubstring.length() > 0 ? a.l("[TEAL](", strSubstring, ")[]") : strSubstring;
    }
}

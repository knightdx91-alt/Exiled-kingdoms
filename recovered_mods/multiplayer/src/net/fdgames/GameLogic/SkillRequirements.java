package net.fdgames.GameLogic;

import a.a;
import android.app.cYK.BQmoQ;
import android.app.cYK.lRWXeT;
import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.coordinatorlayout.widget.OvMp.SkCylVE;
import com.badlogic.gdx.math.tuHF.LpvdRIktEBw;
import java.util.Iterator;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SkillRequirements {
    private int Archery;
    private int Crusader;
    private int Fury;
    private int Lesser_summoning;
    private int Mage_armor;
    private int Mana_surge;
    private int Nivarias_barrier;
    private int Sacred_Fire;
    private int Shield_Expert;
    private int Stab;
    private int Stealth;
    private int Trap_Master;

    public SkillRequirements(String str) {
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            String str2 = strArrSplit[0];
            int i2 = Integer.parseInt(strArrSplit[1]);
            if (str2.equals("fury")) {
                this.Fury = i2;
            }
            if (str2.equals("stealth")) {
                this.Stealth = i2;
            }
            if (str2.equals(LpvdRIktEBw.lBwheCOtP)) {
                this.Stab = i2;
            }
            if (str2.equals("trap_master")) {
                this.Trap_Master = i2;
            }
            if (str2.equals("crusader")) {
                this.Crusader = i2;
            }
            if (str2.equals("nivarias_barrier")) {
                this.Nivarias_barrier = i2;
            }
            if (str2.equals("archery")) {
                this.Archery = i2;
            }
            if (str2.equals("shield_expert")) {
                this.Shield_Expert = i2;
            }
            if (str2.equals("sacred_fire")) {
                this.Sacred_Fire = i2;
            }
            if (str2.equals("mana_surge")) {
                this.Mana_surge = i2;
            }
            if (str2.equals("mage_armor")) {
                this.Mage_armor = i2;
            }
            if (str2.equals("lesser_summoning")) {
                this.Lesser_summoning = i2;
            }
        }
    }

    public final boolean a(CharacterSheet characterSheet) {
        return characterSheet.skillSet.g("fury") >= this.Fury && characterSheet.skillSet.g("stab") >= this.Stab && characterSheet.skillSet.g("stealth") >= this.Stealth && characterSheet.skillSet.g("trap_master") >= this.Trap_Master && characterSheet.skillSet.g("crusader") >= this.Crusader && characterSheet.skillSet.g("nivarias_barrier") >= this.Nivarias_barrier && characterSheet.skillSet.g("sacred_fire") >= this.Sacred_Fire && characterSheet.skillSet.g(lRWXeT.cXhzWaZOB) >= this.Archery && characterSheet.skillSet.g("shield_expert") >= this.Shield_Expert && characterSheet.skillSet.g("mana_surge") >= this.Mana_surge && characterSheet.skillSet.g("mage_armor") >= this.Mage_armor && characterSheet.skillSet.g("lesser_summoning") >= this.Lesser_summoning;
    }

    public final String b(CharacterSheet characterSheet) {
        String strSubstring = "";
        if (this.Fury > 0) {
            StringBuilder sb = new StringBuilder("");
            a.u(fApIihhYHIP.hgoowf, " ", sb);
            strSubstring = a.p(sb, this.Fury, ",");
        }
        if (this.Stab > 0) {
            StringBuilder sbR = a.r(strSubstring);
            a.u("stab", " ", sbR);
            strSubstring = a.p(sbR, this.Stab, ",");
        }
        if (this.Stealth > 0) {
            StringBuilder sbR2 = a.r(strSubstring);
            a.u("stealth", " ", sbR2);
            strSubstring = a.p(sbR2, this.Stealth, ",");
        }
        if (this.Trap_Master > 0) {
            StringBuilder sbR3 = a.r(strSubstring);
            a.u(pgtXvpMCFu.JbPZOFt, " ", sbR3);
            strSubstring = a.p(sbR3, this.Trap_Master, ",");
        }
        if (this.Crusader > 0) {
            StringBuilder sbR4 = a.r(strSubstring);
            a.u("crusader", " ", sbR4);
            strSubstring = a.p(sbR4, this.Crusader, ",");
        }
        if (this.Nivarias_barrier > 0) {
            StringBuilder sbR5 = a.r(strSubstring);
            a.u("nivarias_barrier", " ", sbR5);
            strSubstring = a.p(sbR5, this.Nivarias_barrier, ",");
        }
        if (this.Sacred_Fire > 0) {
            StringBuilder sbR6 = a.r(strSubstring);
            a.u("sacred_fire", " ", sbR6);
            strSubstring = a.p(sbR6, this.Sacred_Fire, ",");
        }
        if (this.Archery > 0) {
            StringBuilder sbR7 = a.r(strSubstring);
            a.u("archery", " ", sbR7);
            strSubstring = a.p(sbR7, this.Archery, ",");
        }
        if (this.Shield_Expert > 0) {
            StringBuilder sbR8 = a.r(strSubstring);
            a.u(SkCylVE.yCFmTE, " ", sbR8);
            strSubstring = a.p(sbR8, this.Shield_Expert, ",");
        }
        if (this.Mage_armor > 0) {
            StringBuilder sbR9 = a.r(strSubstring);
            a.u("mage_armor", " ", sbR9);
            strSubstring = a.p(sbR9, this.Mage_armor, ",");
        }
        if (this.Lesser_summoning > 0) {
            StringBuilder sbR10 = a.r(strSubstring);
            a.u("lesser_summoning", " ", sbR10);
            strSubstring = a.p(sbR10, this.Lesser_summoning, ",");
        }
        if (this.Mana_surge > 0) {
            StringBuilder sbR11 = a.r(strSubstring);
            a.u("mana_surge", " ", sbR11);
            strSubstring = a.p(sbR11, this.Mana_surge, ",");
        }
        if (strSubstring.length() > 0 && strSubstring.charAt(strSubstring.length() - 1) == ',') {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return strSubstring.length() > 0 ? a(characterSheet) ? a.l("[BLACK]", strSubstring, "[]") : a.l("[RED]", strSubstring, "[]") : strSubstring;
    }

    public final String toString() {
        String strP = "";
        if (this.Fury > 0) {
            StringBuilder sb = new StringBuilder("");
            a.u("fury", " ", sb);
            strP = a.p(sb, this.Fury, ",");
        }
        if (this.Stab > 0) {
            StringBuilder sbR = a.r(strP);
            a.u("stab", " ", sbR);
            strP = a.p(sbR, this.Stab, ",");
        }
        if (this.Stealth > 0) {
            StringBuilder sbR2 = a.r(strP);
            a.u("stealth", " ", sbR2);
            strP = a.p(sbR2, this.Stealth, ",");
        }
        if (this.Trap_Master > 0) {
            StringBuilder sbR3 = a.r(strP);
            a.u("trap_master", " ", sbR3);
            strP = a.p(sbR3, this.Trap_Master, ",");
        }
        if (this.Crusader > 0) {
            StringBuilder sbR4 = a.r(strP);
            a.u("crusader", " ", sbR4);
            strP = a.p(sbR4, this.Crusader, ",");
        }
        if (this.Nivarias_barrier > 0) {
            StringBuilder sbR5 = a.r(strP);
            a.u("nivarias_barrier", " ", sbR5);
            strP = a.p(sbR5, this.Nivarias_barrier, ",");
        }
        if (this.Sacred_Fire > 0) {
            StringBuilder sbR6 = a.r(strP);
            a.u("sacred_fire", " ", sbR6);
            strP = a.p(sbR6, this.Sacred_Fire, ",");
        }
        if (this.Archery > 0) {
            StringBuilder sbR7 = a.r(strP);
            a.u("archery", " ", sbR7);
            strP = a.p(sbR7, this.Archery, ",");
        }
        if (this.Shield_Expert > 0) {
            StringBuilder sbR8 = a.r(strP);
            a.u(BQmoQ.sAzzJ, " ", sbR8);
            strP = a.p(sbR8, this.Shield_Expert, ",");
        }
        if (this.Mage_armor > 0) {
            StringBuilder sbR9 = a.r(strP);
            a.u("mage_armor", " ", sbR9);
            strP = a.p(sbR9, this.Mage_armor, ",");
        }
        if (this.Lesser_summoning > 0) {
            StringBuilder sbR10 = a.r(strP);
            a.u("lesser_summoning", " ", sbR10);
            strP = a.p(sbR10, this.Lesser_summoning, ",");
        }
        if (this.Mana_surge > 0) {
            StringBuilder sbR11 = a.r(strP);
            a.u("mana_surge", " ", sbR11);
            strP = a.p(sbR11, this.Mana_surge, ",");
        }
        return (strP.length() <= 0 || strP.charAt(strP.length() + (-1)) != ',') ? strP : strP.substring(0, strP.length() - 1);
    }
}

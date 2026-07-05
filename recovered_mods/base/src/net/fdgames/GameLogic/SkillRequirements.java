package net.fdgames.GameLogic;

import a.a;
import java.util.Iterator;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
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
            if (str2.equals("stab")) {
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
        return characterSheet.skillSet.g("fury") >= this.Fury && characterSheet.skillSet.g("stab") >= this.Stab && characterSheet.skillSet.g("stealth") >= this.Stealth && characterSheet.skillSet.g("trap_master") >= this.Trap_Master && characterSheet.skillSet.g("crusader") >= this.Crusader && characterSheet.skillSet.g("nivarias_barrier") >= this.Nivarias_barrier && characterSheet.skillSet.g("sacred_fire") >= this.Sacred_Fire && characterSheet.skillSet.g("archery") >= this.Archery && characterSheet.skillSet.g("shield_expert") >= this.Shield_Expert && characterSheet.skillSet.g("mana_surge") >= this.Mana_surge && characterSheet.skillSet.g("mage_armor") >= this.Mage_armor && characterSheet.skillSet.g("lesser_summoning") >= this.Lesser_summoning;
    }

    public final String b(CharacterSheet characterSheet) {
        String strSubstring = "";
        if (this.Fury > 0) {
            StringBuilder sb = new StringBuilder("");
            a.v("fury", " ", sb);
            strSubstring = a.p(sb, this.Fury, ",");
        }
        if (this.Stab > 0) {
            StringBuilder sbS = a.s(strSubstring);
            a.v("stab", " ", sbS);
            strSubstring = a.p(sbS, this.Stab, ",");
        }
        if (this.Stealth > 0) {
            StringBuilder sbS2 = a.s(strSubstring);
            a.v("stealth", " ", sbS2);
            strSubstring = a.p(sbS2, this.Stealth, ",");
        }
        if (this.Trap_Master > 0) {
            StringBuilder sbS3 = a.s(strSubstring);
            a.v("trap_master", " ", sbS3);
            strSubstring = a.p(sbS3, this.Trap_Master, ",");
        }
        if (this.Crusader > 0) {
            StringBuilder sbS4 = a.s(strSubstring);
            a.v("crusader", " ", sbS4);
            strSubstring = a.p(sbS4, this.Crusader, ",");
        }
        if (this.Nivarias_barrier > 0) {
            StringBuilder sbS5 = a.s(strSubstring);
            a.v("nivarias_barrier", " ", sbS5);
            strSubstring = a.p(sbS5, this.Nivarias_barrier, ",");
        }
        if (this.Sacred_Fire > 0) {
            StringBuilder sbS6 = a.s(strSubstring);
            a.v("sacred_fire", " ", sbS6);
            strSubstring = a.p(sbS6, this.Sacred_Fire, ",");
        }
        if (this.Archery > 0) {
            StringBuilder sbS7 = a.s(strSubstring);
            a.v("archery", " ", sbS7);
            strSubstring = a.p(sbS7, this.Archery, ",");
        }
        if (this.Shield_Expert > 0) {
            StringBuilder sbS8 = a.s(strSubstring);
            a.v("shield_expert", " ", sbS8);
            strSubstring = a.p(sbS8, this.Shield_Expert, ",");
        }
        if (this.Mage_armor > 0) {
            StringBuilder sbS9 = a.s(strSubstring);
            a.v("mage_armor", " ", sbS9);
            strSubstring = a.p(sbS9, this.Mage_armor, ",");
        }
        if (this.Lesser_summoning > 0) {
            StringBuilder sbS10 = a.s(strSubstring);
            a.v("lesser_summoning", " ", sbS10);
            strSubstring = a.p(sbS10, this.Lesser_summoning, ",");
        }
        if (this.Mana_surge > 0) {
            StringBuilder sbS11 = a.s(strSubstring);
            a.v("mana_surge", " ", sbS11);
            strSubstring = a.p(sbS11, this.Mana_surge, ",");
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
            a.v("fury", " ", sb);
            strP = a.p(sb, this.Fury, ",");
        }
        if (this.Stab > 0) {
            StringBuilder sbS = a.s(strP);
            a.v("stab", " ", sbS);
            strP = a.p(sbS, this.Stab, ",");
        }
        if (this.Stealth > 0) {
            StringBuilder sbS2 = a.s(strP);
            a.v("stealth", " ", sbS2);
            strP = a.p(sbS2, this.Stealth, ",");
        }
        if (this.Trap_Master > 0) {
            StringBuilder sbS3 = a.s(strP);
            a.v("trap_master", " ", sbS3);
            strP = a.p(sbS3, this.Trap_Master, ",");
        }
        if (this.Crusader > 0) {
            StringBuilder sbS4 = a.s(strP);
            a.v("crusader", " ", sbS4);
            strP = a.p(sbS4, this.Crusader, ",");
        }
        if (this.Nivarias_barrier > 0) {
            StringBuilder sbS5 = a.s(strP);
            a.v("nivarias_barrier", " ", sbS5);
            strP = a.p(sbS5, this.Nivarias_barrier, ",");
        }
        if (this.Sacred_Fire > 0) {
            StringBuilder sbS6 = a.s(strP);
            a.v("sacred_fire", " ", sbS6);
            strP = a.p(sbS6, this.Sacred_Fire, ",");
        }
        if (this.Archery > 0) {
            StringBuilder sbS7 = a.s(strP);
            a.v("archery", " ", sbS7);
            strP = a.p(sbS7, this.Archery, ",");
        }
        if (this.Shield_Expert > 0) {
            StringBuilder sbS8 = a.s(strP);
            a.v("shield_expert", " ", sbS8);
            strP = a.p(sbS8, this.Shield_Expert, ",");
        }
        if (this.Mage_armor > 0) {
            StringBuilder sbS9 = a.s(strP);
            a.v("mage_armor", " ", sbS9);
            strP = a.p(sbS9, this.Mage_armor, ",");
        }
        if (this.Lesser_summoning > 0) {
            StringBuilder sbS10 = a.s(strP);
            a.v("lesser_summoning", " ", sbS10);
            strP = a.p(sbS10, this.Lesser_summoning, ",");
        }
        if (this.Mana_surge > 0) {
            StringBuilder sbS11 = a.s(strP);
            a.v("mana_surge", " ", sbS11);
            strP = a.p(sbS11, this.Mana_surge, ",");
        }
        return (strP.length() <= 0 || strP.charAt(strP.length() + (-1)) != ',') ? strP : strP.substring(0, strP.length() - 1);
    }
}

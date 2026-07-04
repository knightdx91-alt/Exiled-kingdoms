package net.fdgames.GameEntities.Helpers;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterSkill;
import net.fdgames.Rules.BonusSet;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.Skills;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SkillSet {
    public int bonusPoints;
    public ArrayList<CharacterSkill> characterSkills = new ArrayList<>();
    public BonusSet bonusSet = new BonusSet();

    public final void a(CharacterSheet characterSheet) {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (Skills.c(characterSkill.skillID).advanced && !Skills.c(characterSkill.skillID).b(characterSheet)) {
                characterSkill.level = 0;
            }
        }
        n();
    }

    public final void b() {
        Iterator<CharacterSkill> it = this.characterSkills.iterator();
        while (it.hasNext()) {
            CharacterSkill next = it.next();
            if (next.skillID.equals("weapon_skill") || next.skillID.equals("endurance") || next.skillID.equals("dodge") || next.skillID.equals("accuracy")) {
                it.remove();
            }
        }
    }

    public final float c(String str) {
        for (int i2 = 0; i2 < this.characterSkills.size(); i2++) {
            if (this.characterSkills.get(i2).skillID.equals(str)) {
                return Math.max(0.0f, this.characterSkills.get(i2).timeToCoolDown / Skills.c(r4.skillID).f(r4.level).cooldown);
            }
        }
        return 0.0f;
    }

    public final boolean d() {
        ArrayList<CharacterSkill> arrayList = this.characterSkills;
        return arrayList == null || arrayList.size() == 0;
    }

    public final void e() {
        Iterator<CharacterSkill> it = this.characterSkills.iterator();
        while (it.hasNext()) {
            if (Skills.c(it.next().skillID).advanced) {
                it.remove();
            }
        }
        n();
    }

    public final int f(String str) {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals(str)) {
                return Skills.c(characterSkill.skillID).f(characterSkill.level).mana_cost;
            }
        }
        return 0;
    }

    public final int g(String str) {
        String strTrim = str.toLowerCase(Locale.ENGLISH).trim();
        for (int i2 = 0; i2 < this.characterSkills.size(); i2++) {
            if (this.characterSkills.get(i2).skillID.equals(strTrim)) {
                return this.characterSkills.get(i2).level;
            }
        }
        return 0;
    }

    public final int h() {
        int iD = 0;
        for (CharacterSkill characterSkill : this.characterSkills) {
            iD += Skills.d(characterSkill.level, characterSkill.skillID);
        }
        return iD;
    }

    public final boolean i() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.characterSkills.size(); i3++) {
            Skill skillC = Skills.c(this.characterSkills.get(i3).skillID);
            if (skillC != null && skillC.advanced) {
                i2++;
            }
        }
        return i2 < 8;
    }

    public final void j(String str) {
        String strTrim = str.toLowerCase(Locale.ENGLISH).trim();
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals(strTrim)) {
                characterSkill.level++;
                return;
            }
        }
        this.characterSkills.add(new CharacterSkill(strTrim, 1));
        n();
    }

    public final boolean k(String str) {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals(str) && characterSkill.level > 0) {
                return characterSkill.timeToCoolDown <= 0.0f;
            }
        }
        return false;
    }

    public final boolean l(String str) {
        for (int i2 = 0; i2 < this.characterSkills.size(); i2++) {
            if (this.characterSkills.get(i2).skillID.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final void m(String str) {
        if (i()) {
            for (int i2 = 0; i2 < this.characterSkills.size(); i2++) {
                if (this.characterSkills.get(i2).skillID.equals(str)) {
                    return;
                }
            }
            this.characterSkills.add(new CharacterSkill(str, 0));
            n();
        }
    }

    public final void n() {
        this.bonusSet = new BonusSet();
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals("nivarias_barrier")) {
                this.bonusSet.armor = characterSkill.level;
            }
            if (characterSkill.skillID.equals("dungeoneering")) {
                int i2 = characterSkill.level;
                if (i2 == 1) {
                    this.bonusSet.detect = 10;
                }
                if (i2 == 2) {
                    this.bonusSet.detect = 18;
                }
                if (i2 == 3) {
                    this.bonusSet.detect = 25;
                }
            }
            if (characterSkill.skillID.equals("gossip")) {
                this.bonusSet.gossip = characterSkill.level * 5;
            }
            if (characterSkill.skillID.equals("precission_strikes")) {
                int i3 = characterSkill.level;
                if (i3 == 1) {
                    this.bonusSet.critChanceModifierMelee = 1.5f;
                }
                if (i3 == 2) {
                    this.bonusSet.critChanceModifierMelee = 2.0f;
                }
                if (i3 == 3) {
                    this.bonusSet.critChanceModifierMelee = 2.5f;
                }
            }
            if (characterSkill.skillID.equals("precission_shots")) {
                int i4 = characterSkill.level;
                if (i4 == 1) {
                    this.bonusSet.critChanceModifierRanged = 1.5f;
                }
                if (i4 == 2) {
                    this.bonusSet.critChanceModifierRanged = 2.0f;
                }
                if (i4 == 3) {
                    this.bonusSet.critChanceModifierRanged = 2.5f;
                }
            }
            if (characterSkill.skillID.equals("massive_criticals")) {
                int i5 = characterSkill.level;
                if (i5 == 1) {
                    this.bonusSet.critDamageModifier = 200;
                }
                if (i5 == 2) {
                    this.bonusSet.critDamageModifier = 250;
                }
                if (i5 == 3) {
                    this.bonusSet.critDamageModifier = 300;
                }
            }
        }
    }

    public final void o() {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals("stab")) {
                characterSkill.timeToCoolDown = 0.0f;
            }
        }
    }

    public final void p() {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (Skills.c(characterSkill.skillID).type == Skill.SKILL_TYPE.SKILL_ACTIVE) {
                characterSkill.timeToCoolDown = 0.0f;
            }
        }
    }

    public final void q(String str) {
        Iterator<CharacterSkill> it = this.characterSkills.iterator();
        while (it.hasNext()) {
            if (it.next().skillID.equals(str)) {
                it.remove();
            }
        }
        n();
    }

    public final void r() {
        Iterator<CharacterSkill> it = this.characterSkills.iterator();
        while (it.hasNext()) {
            CharacterSkill next = it.next();
            if (Skills.c(next.skillID).advanced) {
                next.level = 0;
            } else {
                it.remove();
            }
        }
        n();
    }

    public final void s(String str) {
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.skillID.equals(str)) {
                characterSkill.timeToCoolDown = Skills.c(characterSkill.skillID).f(characterSkill.level).cooldown;
            }
        }
    }

    public final void t(float f2) {
        Skill skillC;
        if (this.characterSkills == null) {
            return;
        }
        for (int i2 = 0; i2 < this.characterSkills.size(); i2++) {
            if (this.characterSkills.get(i2).skillID != null && (skillC = Skills.c(this.characterSkills.get(i2).skillID)) != null && skillC.type == Skill.SKILL_TYPE.SKILL_ACTIVE) {
                CharacterSkill characterSkill = this.characterSkills.get(i2);
                float f3 = characterSkill.timeToCoolDown;
                if (f3 > 0.0f) {
                    characterSkill.timeToCoolDown = f3 - f2;
                }
            }
        }
    }

    public final String toString() {
        String strP = "";
        for (CharacterSkill characterSkill : this.characterSkills) {
            if (characterSkill.level > 0) {
                StringBuilder sbS = a.s(strP);
                sbS.append(characterSkill.skillID);
                sbS.append("=");
                strP = a.p(sbS, characterSkill.level, ",");
            }
        }
        return (strP.equals("") || strP.charAt(strP.length() + (-1)) != ',') ? strP : strP.substring(0, strP.length() - 1);
    }
}

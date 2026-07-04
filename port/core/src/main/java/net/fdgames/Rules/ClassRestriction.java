package net.fdgames.Rules;

import a.a;
import java.util.HashSet;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class ClassRestriction {
    private HashSet<Rules.CharacterClass> classes = new HashSet<>();

    public ClassRestriction(String str) {
        if (str.contains("G")) {
            this.classes.add(Rules.CharacterClass.GENERAL);
        }
        if (str.contains("W")) {
            this.classes.add(Rules.CharacterClass.WARRIOR);
        }
        if (str.contains("R")) {
            this.classes.add(Rules.CharacterClass.ROGUE);
        }
        if (str.contains("M")) {
            this.classes.add(Rules.CharacterClass.WIZARD);
        }
        if (str.contains("C")) {
            this.classes.add(Rules.CharacterClass.CLERIC);
        }
        if (str.contains("X")) {
            this.classes.add(Rules.CharacterClass.NONE);
        }
    }

    public final String a() {
        String string;
        HashSet<Rules.CharacterClass> hashSet = this.classes;
        Rules.CharacterClass characterClass = Rules.CharacterClass.WARRIOR;
        if (hashSet.contains(characterClass)) {
            string = "" + Rules.CharacterClass.a(characterClass);
        } else {
            string = "";
        }
        HashSet<Rules.CharacterClass> hashSet2 = this.classes;
        Rules.CharacterClass characterClass2 = Rules.CharacterClass.ROGUE;
        if (hashSet2.contains(characterClass2)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS = a.s(string);
            sbS.append(Rules.CharacterClass.a(characterClass2));
            string = sbS.toString();
        }
        HashSet<Rules.CharacterClass> hashSet3 = this.classes;
        Rules.CharacterClass characterClass3 = Rules.CharacterClass.CLERIC;
        if (hashSet3.contains(characterClass3)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS2 = a.s(string);
            sbS2.append(Rules.CharacterClass.a(characterClass3));
            string = sbS2.toString();
        }
        HashSet<Rules.CharacterClass> hashSet4 = this.classes;
        Rules.CharacterClass characterClass4 = Rules.CharacterClass.WIZARD;
        if (hashSet4.contains(characterClass4)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS3 = a.s(string);
            sbS3.append(Rules.CharacterClass.a(characterClass4));
            string = sbS3.toString();
        }
        return string.equals("") ? GameString.b("CLASS_ALL", false) : string;
    }

    public final String b(CharacterSheet characterSheet) {
        String string;
        if (characterSheet == null) {
            return "";
        }
        HashSet<Rules.CharacterClass> hashSet = this.classes;
        Rules.CharacterClass characterClass = Rules.CharacterClass.WARRIOR;
        if (hashSet.contains(characterClass)) {
            string = "" + Rules.CharacterClass.a(characterClass);
        } else {
            string = "";
        }
        HashSet<Rules.CharacterClass> hashSet2 = this.classes;
        Rules.CharacterClass characterClass2 = Rules.CharacterClass.ROGUE;
        if (hashSet2.contains(characterClass2)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS = a.s(string);
            sbS.append(Rules.CharacterClass.a(characterClass2));
            string = sbS.toString();
        }
        HashSet<Rules.CharacterClass> hashSet3 = this.classes;
        Rules.CharacterClass characterClass3 = Rules.CharacterClass.CLERIC;
        if (hashSet3.contains(characterClass3)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS2 = a.s(string);
            sbS2.append(Rules.CharacterClass.a(characterClass3));
            string = sbS2.toString();
        }
        HashSet<Rules.CharacterClass> hashSet4 = this.classes;
        Rules.CharacterClass characterClass4 = Rules.CharacterClass.WIZARD;
        if (hashSet4.contains(characterClass4)) {
            if (!string.equals("")) {
                string = string.concat(", ");
            }
            StringBuilder sbS3 = a.s(string);
            sbS3.append(Rules.CharacterClass.a(characterClass4));
            string = sbS3.toString();
        }
        if (string.equals("")) {
            return "[BLACK]All[]";
        }
        return (!c(characterSheet.stats.c()).booleanValue() ? "[RED]" : "[BLACK]") + string + "[]";
    }

    public final Boolean c(Rules.CharacterClass characterClass) {
        return (this.classes.size() == 0 || this.classes.contains(characterClass)) ? Boolean.TRUE : Boolean.FALSE;
    }
}

package net.fdgames.Rules;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.GameLogic.SkillRequirements;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Skill;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Skills {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<Skill> f3280a = new ArrayList<>();

    public static ArrayList<Skill> a() {
        ArrayList<Skill> arrayList = new ArrayList<>();
        for (Skill skill : f3280a) {
            if (skill.advanced) {
                arrayList.add(skill);
            }
        }
        return arrayList;
    }

    public static ArrayList<Skill> b(Rules.CharacterClass characterClass) {
        ArrayList<Skill> arrayList = new ArrayList<>();
        for (Skill skill : f3280a) {
            if (!skill.advanced) {
                if ((skill.skillClass.c(characterClass).booleanValue() ? Boolean.TRUE : Boolean.FALSE).booleanValue()) {
                    arrayList.add(skill);
                }
            }
        }
        return arrayList;
    }

    public static Skill c(String str) {
        for (Skill skill : f3280a) {
            if (skill.id.equals(str)) {
                return skill;
            }
        }
        return null;
    }

    public static int d(int i2, String str) {
        int i3;
        Iterator<Skill> it = f3280a.iterator();
        while (true) {
            i3 = 0;
            if (!it.hasNext()) {
                break;
            }
            Skill next = it.next();
            if (next.id.equals(str)) {
                if (next.levels.size() - 1 >= i2 && i2 > 0) {
                    for (int i4 = 1; i4 <= i2; i4++) {
                        i3 += next.f(i4).cost;
                    }
                }
            }
        }
        return i3;
    }

    public static void e() {
        f3280a.clear();
        f("skills.txt", false);
        f("skills_advanced.txt", true);
        f("skills2.txt", false);
        f("skills_advanced2.txt", true);
        f("skills3.txt", false);
        f("skills_advanced3.txt", true);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void f(String str, boolean z2) {
        String[] strArrSplit;
        ArrayList<Skill> arrayList;
        String str2;
        String str3;
        String str4;
        Locale locale;
        String str5;
        Skill.SKILL_TYPE skill_type;
        String[] strArrSplit2 = Gdx.files.internal("data/rules/".concat(str)).readString("UTF-8").replaceAll("\\r+", "").split("\n");
        String strL = FDUtils.l();
        Skill skill = null;
        if (!strL.equals("")) {
            if (Gdx.files.internal("data/rules/" + strL + str).exists()) {
                strArrSplit = Gdx.files.internal("data/rules/" + strL + str).readString("UTF-16").replace("\r\n\r\n", "").replace("\n\r\n\r", "").split("\n");
                if (strArrSplit != null && strArrSplit.length != strArrSplit2.length) {
                    System.out.println("WARNING: skills localized file length mismatch,  ; " + strArrSplit.length + " vs " + strArrSplit2.length + " ** data/rules/" + strL + str);
                }
            } else {
                strArrSplit = null;
            }
        }
        int i2 = 1;
        while (true) {
            int length = strArrSplit2.length;
            arrayList = f3280a;
            if (i2 >= length) {
                break;
            }
            String strReplace = strArrSplit2[i2].replace("\n", "");
            strArrSplit2[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit2[i2] = strReplace2;
            String strReplace3 = strReplace2.replace("\"", "");
            strArrSplit2[i2] = strReplace3;
            String[] strArrSplit3 = strReplace3.split("\t", -1);
            String str6 = strArrSplit3[0];
            String str7 = strArrSplit3[5];
            if (Settings.h() == 2) {
                str4 = strArrSplit3[11];
                str2 = strArrSplit3[12];
            } else {
                if (strArrSplit != null) {
                    String strReplace4 = strArrSplit[i2].replace("\"", "");
                    strArrSplit[i2] = strReplace4;
                    String[] strArrSplit4 = strReplace4.split("\t", -1);
                    String str8 = strArrSplit4[0];
                    String str9 = strArrSplit4[1];
                    if (str9.trim().equals("")) {
                        System.out.println("WARNING: skill localized file blank description, " + str8 + " ; line=" + i2);
                        str4 = strArrSplit3[0];
                        str2 = strArrSplit3[5];
                    } else {
                        str3 = str8;
                        str2 = str9;
                    }
                } else {
                    str2 = str7;
                    str3 = str6;
                }
                if (str6.equals("") || skill == null) {
                    if (skill != null) {
                        arrayList.add(skill);
                    }
                    String str10 = strArrSplit3[2];
                    locale = Locale.ENGLISH;
                    boolean zContains = str10.toLowerCase(locale).contains("y");
                    boolean z3 = !strArrSplit3[4].toLowerCase(locale).contains("y") || strArrSplit3[4].toLowerCase(locale).contains("m");
                    boolean zContains2 = strArrSplit3[4].toLowerCase(locale).contains("r");
                    str5 = strArrSplit3[1];
                    if (str5.toLowerCase(locale).equals("a")) {
                        str5.toLowerCase(locale).equals("p");
                        skill_type = Skill.SKILL_TYPE.SKILL_PASSIVE;
                    } else {
                        skill_type = Skill.SKILL_TYPE.SKILL_ACTIVE;
                    }
                    Skill skill2 = new Skill(str6, str3, str2, skill_type, strArrSplit3[3], Boolean.valueOf(zContains), z3, zContains2, z2);
                    if (z2) {
                        String str11 = strArrSplit3[9];
                        if (!str11.trim().equals("")) {
                            skill2.skillsRequired = new SkillRequirements(str11);
                        }
                        skill2.traitsRequired = new PlayerRequirements(strArrSplit3[10]);
                    }
                    skill = skill2;
                } else {
                    skill.a(Integer.parseInt(strArrSplit3[6]), str2, strArrSplit3[7], strArrSplit3[8]);
                }
                i2++;
            }
            str3 = str4;
            if (str6.equals("")) {
                if (skill != null) {
                }
                String str102 = strArrSplit3[2];
                locale = Locale.ENGLISH;
                boolean zContains3 = str102.toLowerCase(locale).contains("y");
                if (strArrSplit3[4].toLowerCase(locale).contains("y")) {
                    boolean zContains22 = strArrSplit3[4].toLowerCase(locale).contains("r");
                    str5 = strArrSplit3[1];
                    if (str5.toLowerCase(locale).equals("a")) {
                    }
                    boolean z3 = !strArrSplit3[4].toLowerCase(locale).contains("y") || strArrSplit3[4].toLowerCase(locale).contains("m");
                    Skill skill22 = new Skill(str6, str3, str2, skill_type, strArrSplit3[3], Boolean.valueOf(zContains3), z3, zContains22, z2);
                    if (z2) {
                    }
                    skill = skill22;
                }
            }
            i2++;
        }
        if (skill != null) {
            arrayList.add(skill);
        }
    }
}

package net.fdgames.GameWorld;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import l0.b;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.ek.GPGSUpdate;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class GameVariables {
    private ArrayList<Variable> variables = new ArrayList<>();

    public static void g(String str) {
        GameConsole.a(str);
        GameData.v().log.a(str);
        b.f2311l = true;
    }

    public final void a(String str) {
        Iterator<Variable> it = this.variables.iterator();
        while (it.hasNext()) {
            if (it.next().name.equals(str)) {
                it.remove();
            }
        }
    }

    public final int b(String str) {
        int size = this.variables.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.variables.get(i2).name.equals(str)) {
                return this.variables.get(i2).value;
            }
        }
        return (str.contains("REP_") || str.contains("rumor_") || str.contains("GL_")) ? 0 : -255;
    }

    public final void c(int i2, String str) {
        int size = this.variables.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.variables.get(i3).name.equals(str)) {
                this.variables.get(i3).value += i2;
                return;
            }
        }
        Variable variable = new Variable();
        variable.name = str;
        variable.value = i2;
        this.variables.add(variable);
    }

    public final void d() {
        Iterator<Variable> it = this.variables.iterator();
        while (it.hasNext()) {
            if (it.next().name.toLowerCase(Locale.ENGLISH).contains("tmp_")) {
                it.remove();
            }
        }
    }

    public final void e(int i2, String str) {
        ArrayList<Variable> arrayList = this.variables;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.variables.get(i3).name.equals(str)) {
                this.variables.get(i3).value = i2;
                if (GameWorld.f3187a == null) {
                    System.out.println("Error, quests not initialized, may be caused to an unrelated exception during initialization logged in init_log.txt");
                }
                GameWorld.f3187a.getClass();
                if (!Quests.c(str).equals("")) {
                    StringBuilder sb = new StringBuilder();
                    a.w("QUEST_UPDATED", false, sb, ": ");
                    GameWorld.f3187a.getClass();
                    sb.append(Quests.c(str));
                    g(sb.toString());
                }
                if (i2 > 0 && str.contains("DQ_")) {
                    StringBuilder sb2 = new StringBuilder();
                    a.w("QUEST_UPDATED", false, sb2, ": ");
                    sb2.append(DynamicQuest.e(Integer.parseInt(str.replace("DQ_", ""))).f());
                    g(sb2.toString());
                }
                if (i2 != 0 && str.contains("REP_")) {
                    StringBuilder sb3 = new StringBuilder();
                    a.w("REPUTATION_CHANGED", false, sb3, ": ");
                    sb3.append(GameWorld.f3189c.c(str.substring(4)).c());
                    GameConsole.a(sb3.toString());
                }
                if (i2 != 0 && str.toLowerCase(Locale.ENGLISH).contains("know_")) {
                    GameConsole.a(GameString.b("GAINED_LORE", false));
                }
                if (str.contains("GL_")) {
                    return;
                }
                GameData.v().getClass();
                if (GameData.I()) {
                    GPGSUpdate.c(false);
                    return;
                }
                return;
            }
        }
        Variable variable = new Variable();
        variable.name = str;
        variable.value = i2;
        this.variables.add(variable);
        GameWorld.f3187a.getClass();
        if (!Quests.c(str).equals("")) {
            StringBuilder sb4 = new StringBuilder();
            a.w("QUEST_UPDATED", false, sb4, ": ");
            GameWorld.f3187a.getClass();
            sb4.append(Quests.c(str));
            g(sb4.toString());
        }
        if (i2 > 0 && str.contains("DQ_")) {
            StringBuilder sb5 = new StringBuilder();
            a.w("QUEST_UPDATED", false, sb5, ": ");
            sb5.append(DynamicQuest.e(Integer.parseInt(str.replace("DQ_", ""))).f());
            g(sb5.toString());
        }
        if (i2 != 0 && str.contains("REP_")) {
            StringBuilder sb6 = new StringBuilder();
            a.w("REPUTATION_CHANGED", false, sb6, ": ");
            sb6.append(GameWorld.f3189c.c(str.substring(4)).c());
            GameConsole.a(sb6.toString());
        }
        if (i2 != 0 && str.toLowerCase(Locale.ENGLISH).contains("know_")) {
            GameConsole.a(GameString.b("GAINED_LORE", false));
        }
        if (str.contains("GL_")) {
            return;
        }
        WorldFactions.j();
        GPGSUpdate.c(false);
    }

    public final int f() {
        int i2 = 0;
        for (Variable variable : this.variables) {
            if (variable.name.contains("REP_")) {
                i2 += variable.value;
            }
        }
        return i2;
    }

    public final String toString() {
        String strSubstring = "";
        String string = strSubstring;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (Variable variable : this.variables) {
            if (variable.name.contains("DQ_")) {
                int i7 = variable.value;
                if (i7 == -5) {
                    i2++;
                }
                if (i7 == 10) {
                    i3++;
                }
                if (i7 == 50) {
                    i4++;
                }
                if (i7 == 100) {
                    i5++;
                }
                if (i7 == -100) {
                    i6++;
                }
            } else if (variable.name.contains("EXP_")) {
                StringBuilder sbS = a.s(string);
                sbS.append(variable.name.substring(4));
                sbS.append(",");
                string = sbS.toString();
            } else if (variable.value == 1) {
                strSubstring = a.m(variable.name, ",", a.s(strSubstring));
            } else {
                StringBuilder sbS2 = a.s(strSubstring);
                sbS2.append(variable.name);
                sbS2.append("=");
                strSubstring = a.p(sbS2, variable.value, ",");
            }
        }
        if (!strSubstring.equals("") && strSubstring.charAt(strSubstring.length() - 1) == ',') {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        if (i2 > 0 || i3 > 0 || i4 > 0 || i5 > 0 || i6 > 0) {
            strSubstring = strSubstring + " DQ(in,ac,ha,co,fa)=" + i2 + "," + i3 + "," + i4 + "," + i5 + "," + i6;
        }
        if (string.equals("")) {
            return strSubstring;
        }
        return strSubstring + " EXP=" + string;
    }
}

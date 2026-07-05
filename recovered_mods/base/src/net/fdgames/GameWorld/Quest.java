package net.fdgames.GameWorld;

import a.a;
import com.badlogic.gdx.Gdx;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class Quest {
    public String ID;
    private String Name;
    int questType;
    private ArrayList<QuestState> states;
    private ArrayList<QuestVariation> variations = new ArrayList<>();

    public Quest(String str) {
        this.states = new ArrayList<>();
        this.ID = str;
        this.questType = 0;
        this.states = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/quests/" + str + ".txt").readString("UTF-8").split("\n");
        String strL = FDUtils.l();
        String[] strArr = null;
        if (!strL.equals("")) {
            if (Gdx.files.internal("data/quests/" + strL + str + ".txt").exists()) {
                String[] strArrSplit2 = Gdx.files.internal("data/quests/" + strL + str + ".txt").readString("UTF-16").replace("\r\n\r\n", "").replace("\n\r\n\r", "").split("\n");
                if (strArrSplit2 != null && strArrSplit2.length != strArrSplit.length && strArrSplit2.length < 100) {
                    strArrSplit2 = null;
                }
                if (strArrSplit2 == null || strArrSplit2[0].toLowerCase(Locale.ENGLISH).contains("trans")) {
                    strArr = strArrSplit2;
                } else {
                    PrintStream printStream = System.out;
                    StringBuilder sbU = a.u("WARNING: quest localized file format error, ", str, " ; first text=");
                    sbU.append(strArrSplit2[0]);
                    printStream.println(sbU.toString());
                }
            }
        }
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            String[] strArrSplit3 = strReplace2.split("\t", -1);
            String strReplace3 = strArrSplit3[1];
            if (Settings.h() == 2) {
                strReplace3 = strArrSplit3[2];
            } else if (strArr != null) {
                strReplace3 = strArr[i2].replace("\n", "").replace("\r", "");
                if (strReplace3.trim().equals("")) {
                    strReplace3 = strArrSplit3[2];
                }
            }
            if (Integer.parseInt(strArrSplit3[0]) == 0) {
                this.Name = strReplace3;
                if (strArrSplit3.length > 4 && strArrSplit3[4].length() == 1) {
                    if (strArrSplit3[4].equals("B")) {
                        this.questType = 1;
                    }
                    if (strArrSplit3[4].equals("I")) {
                        this.questType = 2;
                    }
                    if (strArrSplit3[4].equals("M")) {
                        this.questType = 3;
                    }
                    if (strArrSplit3[4].equals("C")) {
                        this.questType = 4;
                    }
                }
            } else {
                this.states.add(new QuestState(Integer.parseInt(strArrSplit3[0]), strReplace3, strArrSplit3[3]));
            }
        }
        if (this.questType != 0) {
            if (!Gdx.files.internal("data/quests/variations/" + str + "_var.txt").exists()) {
                System.out.println("WARNING: variations file not found for quest: " + str);
                return;
            }
            String[] strArrSplit4 = Gdx.files.internal("data/quests/variations/" + str + "_var.txt").readString().split("\n");
            for (int i3 = 1; i3 < strArrSplit4.length; i3++) {
                String strReplace4 = strArrSplit4[i3].replace("\n", "");
                strArrSplit4[i3] = strReplace4;
                String strReplace5 = strReplace4.replace("\r", "");
                strArrSplit4[i3] = strReplace5;
                this.variations.add(new QuestVariation(strReplace5));
            }
        }
    }

    public final String a() {
        return this.Name;
    }

    public final String b(String str) {
        String string = this.Name;
        for (QuestVariation questVariation : this.variations) {
            if (questVariation.id.equals(str)) {
                StringBuilder sbT = a.t(string, " : ");
                sbT.append(questVariation.a());
                string = sbT.toString();
            }
        }
        return string;
    }

    public final String c(int i2) {
        for (QuestState questState : this.states) {
            if (questState.b() == i2) {
                return "[BLACK]" + questState.a() + "[]";
            }
        }
        return "";
    }

    public final QuestVariation d(String str) {
        for (QuestVariation questVariation : this.variations) {
            if (questVariation.id.equals(str)) {
                return questVariation;
            }
        }
        return null;
    }

    public final ArrayList<QuestVariation> e() {
        return this.variations;
    }

    public final boolean f() {
        return this.variations.size() > 0;
    }

    public final boolean g() {
        return this.questType == 1;
    }

    public final boolean h() {
        return this.questType == 2;
    }

    public final boolean i(String str) {
        return str.equals(this.ID);
    }
}

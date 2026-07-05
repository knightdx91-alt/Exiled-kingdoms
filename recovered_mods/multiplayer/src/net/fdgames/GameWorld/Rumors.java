package net.fdgames.GameWorld;

import a.a;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Rumors {
    private ArrayList<Rumor> rumorList = new ArrayList<>();

    public Rumors() {
        String[] strArrSplit = Gdx.files.internal("data/world/rumors.txt").readString("UTF-8").split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            Rumor rumor = new Rumor();
            String[] strArrSplit2 = strReplace2.replace("\"", "").split("\t", -1);
            rumor.desc = strArrSplit2[0];
            rumor.conditions = new ConditionsSet(strArrSplit2[2]);
            rumor.actions = new ActionsSet(strArrSplit2[3]);
            rumor.journalvariable = strArrSplit2[4].trim();
            if (Settings.h() == 2 && !strArrSplit2[1].trim().equals("")) {
                rumor.desc = strArrSplit2[1];
            }
            if (Settings.h() == 3 && !strArrSplit2[5].trim().equals("")) {
                rumor.desc = strArrSplit2[5];
            }
            if (Settings.h() == 4 && !strArrSplit2[6].trim().equals("")) {
                rumor.desc = strArrSplit2[6];
            }
            if (Settings.h() == 5 && !strArrSplit2[7].trim().equals("")) {
                rumor.desc = strArrSplit2[7];
            }
            if (Settings.h() == 6 && !strArrSplit2[8].trim().equals("")) {
                rumor.desc = strArrSplit2[8];
            }
            if (Settings.h() == 7 && !strArrSplit2[9].trim().equals("")) {
                rumor.desc = strArrSplit2[9];
            }
            if (Settings.h() == 8 && !strArrSplit2[10].trim().equals("")) {
                rumor.desc = strArrSplit2[10];
            }
            if (Settings.h() == 9 && !strArrSplit2[11].trim().equals("")) {
                rumor.desc = strArrSplit2[11];
            }
            if (Settings.h() == 10 && !strArrSplit2[12].trim().equals("")) {
                rumor.desc = strArrSplit2[12];
            }
            rumor.desc = rumor.desc.replace("\"", "");
            this.rumorList.add(rumor);
        }
    }

    public final ArrayList<Rumor> a() {
        ArrayList<Rumor> arrayList = new ArrayList<>();
        for (Rumor rumor : this.rumorList) {
            if (!rumor.journalvariable.equals("") && GameData.v().gameVariables.b(rumor.journalvariable) > 0) {
                arrayList.add(rumor);
            }
        }
        return arrayList;
    }

    public final String b() {
        ArrayList arrayList = new ArrayList();
        for (Rumor rumor : this.rumorList) {
            if (rumor.conditions.a().booleanValue()) {
                arrayList.add(rumor);
            }
        }
        int iH = GameData.v().party.h() + 40;
        if (arrayList.size() == 0 || GameData.v().o() > iH) {
            StringBuilder sb = new StringBuilder();
            a.v("DRINK", false, sb, "\r\n\r\n");
            return a.n("DRINK_FAIL", false, sb);
        }
        Rumor rumor2 = (Rumor) arrayList.get(FDUtils.b(0, arrayList.size() - 1));
        rumor2.actions.a();
        GameData.v().gameVariables.c(1, "rumors");
        StringBuilder sb2 = new StringBuilder();
        a.v("DRINK", false, sb2, "\r\n\r\n");
        sb2.append(rumor2.desc);
        return sb2.toString();
    }
}

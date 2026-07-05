package net.fdgames.GameWorld;

import java.util.Iterator;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class Castle {
    public String faction_id;
    public String faction_id2;
    public String id;
    public int level;
    private String name;
    private String quests;

    public Castle(String str) {
        String[] strArrSplit = str.replace("\"", "").split("\t", -1);
        this.id = strArrSplit[0];
        this.faction_id = strArrSplit[1];
        this.faction_id2 = strArrSplit[2];
        this.level = Integer.parseInt(strArrSplit[3]);
        this.quests = strArrSplit[4].trim();
        this.name = strArrSplit[5];
        if (Settings.h() == 2 && !strArrSplit[6].trim().equals("")) {
            this.name = strArrSplit[6];
        }
        if (Settings.h() == 3 && !strArrSplit[7].trim().equals("")) {
            this.name = strArrSplit[7];
        }
        if (Settings.h() == 4 && !strArrSplit[8].trim().equals("")) {
            this.name = strArrSplit[8];
        }
        if (Settings.h() == 5 && !strArrSplit[9].trim().equals("")) {
            this.name = strArrSplit[9];
        }
        if (Settings.h() == 6 && !strArrSplit[10].trim().equals("")) {
            this.name = strArrSplit[10];
        }
        if (Settings.h() == 7 && !strArrSplit[11].trim().equals("")) {
            this.name = strArrSplit[11];
        }
        if (Settings.h() == 8 && !strArrSplit[12].trim().equals("")) {
            this.name = strArrSplit[12];
        }
        if (Settings.h() == 9 && !strArrSplit[13].trim().equals("")) {
            this.name = strArrSplit[13];
        }
        if (Settings.h() == 10 && !strArrSplit[14].trim().equals("")) {
            this.name = strArrSplit[14];
        }
        this.name = this.name.replace("\"", "");
    }

    public final void a() {
        float f2;
        int size;
        if (this.quests.equals("")) {
            return;
        }
        float fU = GameData.v().u();
        String str = this.id;
        Iterator<CastleData> it = GameData.v().castleData.iterator();
        while (true) {
            if (!it.hasNext()) {
                f2 = -108000.0f;
                break;
            }
            CastleData next = it.next();
            if (next.castle_ID.equals(str)) {
                f2 = next.lastQuestCreation;
                break;
            }
        }
        if ((fU - f2) / 1080.0f >= 2.0f && (size = this.level - DynamicQuest.h(this.id).size()) > 0) {
            int iB = FDUtils.b(1, size);
            for (int i2 = 1; i2 <= iB; i2++) {
                DynamicQuest.a(FDUtils.o(this.quests), this.id);
            }
        }
    }

    public final int b() {
        int iE;
        int iE2 = (GameWorld.f3189c.c(this.faction_id).e() * (-1)) - 5;
        if (iE2 < 0) {
            iE2 = 0;
        }
        if (!this.faction_id2.equals("") && (GameWorld.f3189c.c(this.faction_id2).e() * (-1)) - 5 > 0) {
            iE2 += iE;
        }
        return iE2 * 800;
    }

    public final String c() {
        return this.name;
    }
}

package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Arrays;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import y0.e;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SpawnTable {
    public String name;
    private ArrayList<SpawnTableEntry> tableEntries = new ArrayList<>();

    public SpawnTable(String str) {
        this.name = str;
        String[] strArrSplit = Gdx.files.internal("data/world/spawntables/" + str + ".txt").readString().split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            SpawnTableEntry spawnTableEntry = new SpawnTableEntry();
            spawnTableEntry.spawnData = new e();
            String[] strArrSplit2 = strReplace2.replace("\"", "").split("\t", -1);
            spawnTableEntry.spawn_id = strArrSplit2[0];
            if (!strArrSplit2[1].trim().equals("")) {
                spawnTableEntry.spawnData.f4041c = GameString.b(strArrSplit2[1], true);
            }
            if (strArrSplit2[3].equals("")) {
                spawnTableEntry.spawnData.f4040b = -1;
            } else {
                spawnTableEntry.spawnData.f4040b = Integer.parseInt(strArrSplit2[3]);
            }
            if (strArrSplit2[4].equals("")) {
                spawnTableEntry.spawnData.f4042d = 0;
            } else {
                spawnTableEntry.spawnData.f4042d = Integer.parseInt(strArrSplit2[4]);
            }
            e eVar = spawnTableEntry.spawnData;
            eVar.f4039a = strArrSplit2[5];
            eVar.f4045g = strArrSplit2[6].trim();
            spawnTableEntry.spawnData.f4046h = strArrSplit2[7].trim();
            spawnTableEntry.spawnData.f4044f = strArrSplit2[8];
            spawnTableEntry.rarity = FDUtils.t(strArrSplit2[9]);
            spawnTableEntry.spawnData.f4043e = strArrSplit2[11];
            spawnTableEntry.minLevel = 1;
            spawnTableEntry.maxLevel = 999;
            if (!strArrSplit2[13].equals("")) {
                spawnTableEntry.minLevel = Integer.parseInt(strArrSplit2[13]);
            }
            if (!strArrSplit2[14].equals("")) {
                spawnTableEntry.maxLevel = Integer.parseInt(strArrSplit2[14]);
            }
            spawnTableEntry.secondarySpawns = new ArrayList();
            spawnTableEntry.tertiarySpawns = new ArrayList();
            spawnTableEntry.secondaryTag = "";
            spawnTableEntry.tertiaryTag = "";
            spawnTableEntry.secondaryDistance = 3;
            spawnTableEntry.tertiaryDistance = 3;
            spawnTableEntry.faction = strArrSplit2[10];
            spawnTableEntry.conversationRange = 0;
            spawnTableEntry.conversationConditions = "";
            if (strArrSplit2.length >= 16 && !strArrSplit2[15].trim().equals("")) {
                spawnTableEntry.conversationRange = Integer.parseInt(strArrSplit2[15]);
            }
            if (strArrSplit2.length >= 17 && !strArrSplit2[16].trim().equals("")) {
                spawnTableEntry.conversationConditions = strArrSplit2[16].trim();
            }
            if (strArrSplit2.length >= 19) {
                spawnTableEntry.secondarySpawns = Arrays.asList(strArrSplit2[18].split(";"));
            }
            if (strArrSplit2.length >= 20) {
                spawnTableEntry.secondaryTag = strArrSplit2[19];
            }
            if (strArrSplit2.length >= 21 && !strArrSplit2[20].trim().equals("")) {
                spawnTableEntry.secondaryDistance = Integer.parseInt(strArrSplit2[20]);
            }
            if (strArrSplit2.length >= 22) {
                spawnTableEntry.tertiarySpawns = Arrays.asList(strArrSplit2[21].split(";"));
            }
            if (strArrSplit2.length >= 23) {
                spawnTableEntry.tertiaryTag = strArrSplit2[22];
            }
            if (strArrSplit2.length >= 24 && !strArrSplit2[23].trim().equals("")) {
                spawnTableEntry.tertiaryDistance = Integer.parseInt(strArrSplit2[23].trim());
            }
            this.tableEntries.add(spawnTableEntry);
        }
    }

    public final SpawnTableEntry a(FDUtils.Rarity rarity) {
        ArrayList arrayList = new ArrayList();
        for (SpawnTableEntry spawnTableEntry : this.tableEntries) {
            spawnTableEntry.getClass();
            int iF = GameData.v().player.sheet.stats.f();
            if (iF >= spawnTableEntry.minLevel && iF <= spawnTableEntry.maxLevel && spawnTableEntry.rarity.equals(rarity) && new ConditionsSet(spawnTableEntry.spawnData.f4043e).a().booleanValue()) {
                arrayList.add(spawnTableEntry);
            }
        }
        if (arrayList.size() > 0) {
            return (SpawnTableEntry) arrayList.get(FDUtils.b(0, arrayList.size() - 1));
        }
        if (rarity.equals(FDUtils.Rarity.ULTRA_RARE)) {
            return a(FDUtils.Rarity.RARE);
        }
        if (rarity.equals(FDUtils.Rarity.RARE)) {
            return a(FDUtils.Rarity.UNCOMMON);
        }
        if (rarity.equals(FDUtils.Rarity.UNCOMMON)) {
            return a(FDUtils.Rarity.COMMON);
        }
        return null;
    }
}

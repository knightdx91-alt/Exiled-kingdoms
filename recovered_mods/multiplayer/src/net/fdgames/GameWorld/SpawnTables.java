package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SpawnTables {
    ArrayList<SpawnTable> tables = new ArrayList<>();

    public SpawnTables() {
        String[] strArrSplit = Gdx.files.internal("data/world/spawntables/list.txt").readString().split("\n");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            this.tables.add(new SpawnTable(strReplace2.split("\t", -1)[0]));
        }
    }

    public final SpawnTableEntry a(String str) {
        for (SpawnTable spawnTable : this.tables) {
            if (spawnTable.name.equals(str)) {
                return spawnTable.a(FDUtils.n());
            }
        }
        return null;
    }
}

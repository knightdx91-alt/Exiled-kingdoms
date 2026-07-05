package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Castles {
    private ArrayList<Castle> castleList = new ArrayList<>();

    public Castles() {
        String[] strArrSplit = Gdx.files.internal("data/world/castles.txt").readString("UTF-8").split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            this.castleList.add(new Castle(strReplace2));
        }
    }

    public final Castle a(String str) {
        for (Castle castle : this.castleList) {
            if (castle.id.equals(str)) {
                return castle;
            }
        }
        return null;
    }
}

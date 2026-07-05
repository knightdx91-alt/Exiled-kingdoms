package net.fdgames.Rules;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.GameEntities.Helpers.Damage;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class TrapData {
    private ArrayList<TrapDataLine> traps = new ArrayList<>();

    public class TrapDataLine {
        public int dmgLevel;
        public Damage.DamageType dmgType;
        public float duration;
        public String effect;
        public String id;
        public String nameTag;
        public String particle;
        public String sprite;
        public String sprite_effect;

        public TrapDataLine(String str) {
            String[] strArrSplit = str.replace("\"", "").split("\t", -1);
            this.id = strArrSplit[0];
            this.nameTag = strArrSplit[1];
            this.dmgLevel = Integer.parseInt(strArrSplit[2]);
            this.dmgType = Damage.b(strArrSplit[3]);
            this.duration = Float.parseFloat(strArrSplit[4]);
            this.sprite = strArrSplit[5];
            this.sprite_effect = strArrSplit[6];
            this.particle = strArrSplit[7];
            this.effect = strArrSplit[8];
        }
    }

    public TrapData() {
        String[] strArrSplit = Gdx.files.internal("data/rules/traps.txt").readString("UTF-8").split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            this.traps.add(new TrapDataLine(strReplace2));
        }
    }

    public final TrapDataLine a(String str) {
        for (int i2 = 0; i2 < this.traps.size(); i2++) {
            if (this.traps.get(i2).id.equals(str)) {
                return this.traps.get(i2);
            }
        }
        System.out.println("trap not found");
        return null;
    }
}

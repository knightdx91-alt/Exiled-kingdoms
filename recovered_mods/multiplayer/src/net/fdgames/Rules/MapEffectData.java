package net.fdgames.Rules;

import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.GameEntities.Helpers.Damage;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class MapEffectData {
    private ArrayList<MapEffectDataLine> effects = new ArrayList<>();

    public class MapEffectDataLine {
        public int damage;
        public Damage.DamageType dmgType;
        public float duration;
        public String effect;
        public float fadeInDuration;
        public float fadeOutDuration;
        public String id;
        public String name;
        public String name_ES;
        public int range;
        public String sprite;

        public MapEffectDataLine(String str) {
            String[] strArrSplit = str.replace("\"", "").split("\t", -1);
            this.id = strArrSplit[0];
            this.name = strArrSplit[1];
            this.name_ES = strArrSplit[2];
            this.damage = Integer.parseInt(strArrSplit[3]);
            this.dmgType = Damage.b(strArrSplit[4]);
            this.range = Integer.parseInt(strArrSplit[5]);
            this.duration = Float.parseFloat(strArrSplit[6]);
            this.sprite = strArrSplit[7];
            this.effect = strArrSplit[8];
            this.fadeInDuration = Float.parseFloat(strArrSplit[9]);
            this.fadeOutDuration = Float.parseFloat(strArrSplit[10]);
        }
    }

    public MapEffectData() {
        String[] strArrSplit = Gdx.files.internal("data/rules/map_effects.txt").readString("UTF-8").split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            this.effects.add(new MapEffectDataLine(strReplace2));
        }
    }

    public final MapEffectDataLine a(String str) {
        for (int i2 = 0; i2 < this.effects.size(); i2++) {
            if (this.effects.get(i2).id.equals(str)) {
                return this.effects.get(i2);
            }
        }
        System.out.println("effect '" + str + JNrsKSCxIEXndG.uPySlvRlwMfhsN);
        return null;
    }
}

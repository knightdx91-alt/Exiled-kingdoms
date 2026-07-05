package net.fdgames.GameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import java.util.HashSet;
import java.util.Set;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import q0.FCji.sPtsahwU;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class WorldFaction {
    public Integer code;
    private String description;
    private String flag;
    public Set<Integer> hostileFactions;
    public String id;
    private String name;

    public WorldFaction(String str) {
        String[] strArrSplit = str.replace("\"", "").split("\t", -1);
        this.id = strArrSplit[0];
        this.code = Integer.valueOf(Integer.parseInt(strArrSplit[1]));
        this.hostileFactions = new HashSet();
        if (!strArrSplit[2].trim().equals("")) {
            for (String str2 : strArrSplit[2].split(",", -1)) {
                this.hostileFactions.add(Integer.valueOf(Integer.parseInt(str2)));
            }
        }
        this.flag = strArrSplit[3];
    }

    public static String d(int i2) {
        if (i2 == -255) {
            i2 = 0;
        }
        return i2 <= -80 ? GameString.b("ARCH-ENEMY", false) : i2 <= -60 ? GameString.b(qJDUJ.pirPaK, false) : i2 <= -40 ? GameString.b("CRIMINAL", false) : i2 <= -20 ? GameString.b("BANDIT", false) : i2 <= -5 ? GameString.b(sPtsahwU.AQyIiBmmYQ, false) : i2 <= 9 ? GameString.b("UNKNOWN", false) : i2 <= 24 ? GameString.b("FRIENDLY", false) : i2 <= 39 ? GameString.b("TRUSTED", false) : i2 <= 59 ? GameString.b("HERO", false) : i2 <= 79 ? GameString.b("GREAT_HERO", false) : i2 <= 100 ? GameString.b("LEGENDARY_HERO", false) : "";
    }

    public final String a() {
        return this.description;
    }

    public final TextureRegion b() {
        return Assets.a(this.flag);
    }

    public final String c() {
        return this.name;
    }

    public final int e() {
        return GameData.v().gameVariables.b("REP_" + this.id);
    }

    public final void f(String str, String str2) {
        this.name = str;
        this.description = str2;
    }
}

package net.fdgames.GameWorld;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Iterator;
import m0.b;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.GameText;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class WorldFactions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static GameText[] f3200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static GameText[] f3201b;
    private ArrayList<WorldFaction> factionList = new ArrayList<>();

    public WorldFactions() {
        GameText gameText;
        String[] strArrSplit = Gdx.files.internal("data/world/factions_text.txt").readString("UTF-8").replaceAll("\\r+", "").split("\n");
        int i2 = 1;
        f3200a = new GameText[strArrSplit.length - 1];
        for (int i3 = 1; i3 < strArrSplit.length; i3++) {
            f3200a[i3 - 1] = new GameText(strArrSplit[i3], true, false);
        }
        f3201b = new GameText[strArrSplit.length - 1];
        for (int i4 = 1; i4 < strArrSplit.length; i4++) {
            f3201b[i4 - 1] = new GameText(strArrSplit[i4], true, true);
        }
        String[] strArrSplit2 = Gdx.files.internal("data/world/factions.txt").readString().split("\n");
        while (true) {
            GameText gameText2 = null;
            if (i2 >= strArrSplit2.length) {
                f3200a = null;
                f3201b = null;
                return;
            }
            String strReplace = strArrSplit2[i2].replace("\n", "");
            strArrSplit2[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit2[i2] = strReplace2;
            WorldFaction worldFaction = new WorldFaction(strReplace2);
            this.factionList.add(worldFaction);
            String str = worldFaction.id;
            int i5 = 0;
            while (true) {
                GameText[] gameTextArr = f3200a;
                if (i5 >= gameTextArr.length) {
                    System.out.println("WARNING: faction name not found, " + str);
                    gameText = null;
                    break;
                }
                if (gameTextArr[i5].tag.equals(str)) {
                    gameText = f3200a[i5];
                    break;
                }
                i5++;
            }
            String str2 = gameText.get();
            String str3 = worldFaction.id;
            int i6 = 0;
            while (true) {
                GameText[] gameTextArr2 = f3201b;
                if (i6 >= gameTextArr2.length) {
                    System.out.println("WARNING: faction description not found, " + str3);
                    break;
                }
                if (gameTextArr2[i6].tag.equals(str3)) {
                    gameText2 = f3201b[i6];
                    break;
                }
                i6++;
            }
            worldFaction.f(str2, gameText2.get());
            i2++;
        }
    }

    private boolean e(int i2, int i3) {
        for (int i4 = 0; i4 < this.factionList.size(); i4++) {
            if (this.factionList.get(i4).code.intValue() == i2) {
                return this.factionList.get(i4).hostileFactions.contains(Integer.valueOf(i3));
            }
        }
        return false;
    }

    private static int h(String str) {
        for (WorldFaction worldFaction : GameWorld.f3189c.factionList) {
            if (worldFaction.id.equals(str)) {
                return worldFaction.code.intValue();
            }
        }
        System.out.println("caution, faction " + str + " not found");
        return 105;
    }

    public static int[] i(String str) {
        int[] iArr = new int[2];
        if (str.contains("#civilian#")) {
            str = b.P().E;
        }
        if (str.contains("#guard#")) {
            str = b.P().F;
        }
        if (str.contains(",")) {
            String[] strArrSplit = str.split(",", 2);
            iArr[0] = h(strArrSplit[0]);
            iArr[1] = h(strArrSplit[1]);
        } else {
            iArr[0] = h(str);
        }
        return iArr;
    }

    public static void j() {
        WorldFaction next;
        Iterator<WorldFaction> it = GameWorld.f3189c.factionList.iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                if (next.code == 100) {
                    break;
                }
            } else {
                next = null;
                break;
            }
        }
        if (next == null) {
            System.out.println("Error, player faction not found");
            return;
        }
        for (WorldFaction worldFaction : GameWorld.f3189c.factionList) {
            if (worldFaction.code.intValue() < 100 && worldFaction.code != 100) {
                if (GameData.v().gameVariables.b("REP_" + worldFaction.id) <= -20) {
                    next.hostileFactions.add(worldFaction.code);
                    worldFaction.hostileFactions.add(100);
                } else {
                    next.hostileFactions.remove(worldFaction.code);
                    worldFaction.hostileFactions.remove(100);
                }
            }
        }
    }

    public final void a(int i2, int i3, String str) {
        for (WorldFaction worldFaction : this.factionList) {
            if (worldFaction.id.equals(str) && worldFaction.e() < i3) {
                GameLog gameLog = GameData.v().log;
                String strC = worldFaction.c();
                gameLog.getClass();
                if (i2 > 0) {
                    gameLog.a("[GREEN]" + GameString.b("REPUTATION_WITH", false) + " " + strC + " " + GameString.b("REPUTATION_CHANGED_BY", false) + " +" + i2 + "[]");
                }
                if (i2 < 0) {
                    gameLog.a("[RED]" + GameString.b("REPUTATION_WITH", false) + " " + strC + " " + GameString.b("REPUTATION_CHANGED_BY", false) + " " + i2 + "[]");
                }
                int iE = worldFaction.e() + i2;
                if (iE > 100) {
                    iE = 100;
                }
                if (iE < -100) {
                    iE = -100;
                }
                GameData.v().gameVariables.e(iE, "REP_" + worldFaction.id);
                return;
            }
        }
    }

    public final WorldFaction b(int i2) {
        for (WorldFaction worldFaction : this.factionList) {
            if (worldFaction.code.equals(Integer.valueOf(i2))) {
                return worldFaction;
            }
        }
        return null;
    }

    public final WorldFaction c(String str) {
        for (WorldFaction worldFaction : this.factionList) {
            if (worldFaction.id.equals(str)) {
                return worldFaction;
            }
        }
        return null;
    }

    public final ArrayList<WorldFaction> d() {
        return this.factionList;
    }

    public final boolean f(int[] iArr) {
        if (e(iArr[0], 100)) {
            return true;
        }
        if (iArr.length > 1) {
            return e(iArr[1], 100);
        }
        return false;
    }

    public final boolean g(int[] iArr, int[] iArr2) {
        if (iArr != null && iArr2 != null) {
            if (e(iArr[0], iArr2[0])) {
                return true;
            }
            if (iArr.length > 1 && e(iArr[1], iArr2[0])) {
                return true;
            }
            if (iArr2.length > 1 && e(iArr2[1], iArr[0])) {
                return true;
            }
            if (iArr.length > 1 && iArr2.length > 1 && e(iArr[1], iArr2[1])) {
                return true;
            }
        }
        return false;
    }
}

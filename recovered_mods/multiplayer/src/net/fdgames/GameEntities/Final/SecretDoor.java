package net.fdgames.GameEntities.Final;

import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import g0.h;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Coords;
import w0.a;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SecretDoor extends MapObject {
    private float lastDetectCheck = 0.0f;
    private int level;
    private String secretdoor_tag;
    private SecretDoorState state;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class SecretDoorState {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final SecretDoorState f3249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final SecretDoorState f3250c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final SecretDoorState f3251d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ SecretDoorState[] f3252e;

        static {
            SecretDoorState secretDoorState = new SecretDoorState("UNDETECTED", 0);
            f3249b = secretDoorState;
            SecretDoorState secretDoorState2 = new SecretDoorState("DETECTED", 1);
            f3250c = secretDoorState2;
            SecretDoorState secretDoorState3 = new SecretDoorState("OPEN", 2);
            f3251d = secretDoorState3;
            f3252e = new SecretDoorState[]{secretDoorState, secretDoorState2, secretDoorState3};
        }

        private SecretDoorState() {
            throw null;
        }

        public static SecretDoorState valueOf(String str) {
            return (SecretDoorState) Enum.valueOf(SecretDoorState.class, str);
        }

        public static SecretDoorState[] values() {
            return (SecretDoorState[]) f3252e.clone();
        }
    }

    static {
        new Color(0.7f, 1.0f, 0.7f, 0.5f);
        new Color(1.0f, 1.0f, 1.0f, 0.3f);
    }

    public SecretDoor() {
    }

    public SecretDoor(h hVar) {
        this.f3307x = ((int) Float.parseFloat(hVar.b("x").toString())) - 24;
        this.f3308y = ((int) Float.parseFloat(hVar.b("y").toString())) + 24;
        if (hVar.a("tag")) {
            this.tag = hVar.b("tag").toString();
        }
        int iMax = Math.max(GameData.v().player.sheet.stats.f() - 2, 0);
        int iF = GameData.v().player.sheet.stats.f() + 2;
        iMax = hVar.a("minlevel") ? Integer.parseInt(hVar.b("minlevel").toString()) : iMax;
        iF = hVar.a("maxlevel") ? Integer.parseInt(hVar.b("maxlevel").toString()) : iF;
        this.level = FDUtils.b(iMax > iF ? iF : iMax, iF);
        this.faction = Factions.Faction.f3276c;
        this.state = SecretDoorState.f3249b;
        this.secretdoor_tag = "";
        if (hVar.a("secretdoor_tag")) {
            this.secretdoor_tag = hVar.b("secretdoor_tag").toString();
        }
        if (this.secretdoor_tag == "") {
            System.out.println("WARNING: secret door not tagged");
        } else if (GameData.v().J(this.secretdoor_tag)) {
            this.state = SecretDoorState.f3250c;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    public final boolean F(int i2, int i3) {
        return this.state == SecretDoorState.f3250c && Math.abs(this.f3307x - i2) < 40 && Math.abs(this.f3308y - i3) < 40;
    }

    public final void G(int i2, String str) {
        this.state = SecretDoorState.f3250c;
        GameLog gameLog = GameData.v().log;
        StringBuilder sbT = a.a.t("[GREEN]", str, "[] ");
        sbT.append(GameString.b("DETECTS", false));
        sbT.append(" [BLUE]");
        sbT.append(getName());
        sbT.append("[] [WHITE] ");
        sbT.append(i2);
        sbT.append("%[]");
        gameLog.a(sbT.toString());
        w0.a.l().b(B(), a.EnumC0056a.f3890i, 0.0f);
        String str2 = this.secretdoor_tag;
        if (str2 == null || str2 == "") {
            return;
        }
        GameData.v().j(this.secretdoor_tag);
    }

    public final boolean H() {
        if (this.state != SecretDoorState.f3249b || GameData.v().u() < this.lastDetectCheck + 3.0f) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        return b.s(coordsB, GameData.v().player.B()) <= 160;
    }

    public final SecretDoorState I() {
        return this.state;
    }

    public final boolean J() {
        return this.state == SecretDoorState.f3251d;
    }

    public final void K() {
        this.state = SecretDoorState.f3251d;
        w0.a.l().p();
    }

    public final void L(Character character) {
        this.lastDetectCheck = GameData.v().u();
        if (this.state != SecretDoorState.f3249b) {
            return;
        }
        int iC = FDUtils.c(character.sheet.stats.f(), this.level) + character.sheet.s();
        if (FDUtils.b(1, 100) < iC) {
            G(iC, character.getName());
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        StringBuilder sb = new StringBuilder();
        a.a.v("SECRET_DOOR", false, sb, JNrsKSCxIEXndG.BusyWOZBrXCVd);
        return a.a.p(sb, this.level, ")");
    }
}

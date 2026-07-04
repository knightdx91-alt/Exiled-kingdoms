package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import k0.a;
import m0.b;
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
import com.badlogic.gdx.maps.MapProperties;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SecretDoor extends MapObject {
    private float lastDetectCheck = 0.0f;
    private int level;
    private String secretdoor_tag;
    private SecretDoorState state;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum SecretDoorState {
        UNDETECTED, DETECTED, OPEN;
    }

    static {
        new Color(0.7f, 1.0f, 0.7f, 0.5f);
        new Color(1.0f, 1.0f, 1.0f, 0.3f);
    }

    public SecretDoor() {
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    public final boolean F(int i2, int i3) {
        return this.state == SecretDoorState.DETECTED && Math.abs(this.f3092x - i2) < 40 && Math.abs(this.f3093y - i3) < 40;
    }

    public final void G(int i2, String str) {
        this.state = SecretDoorState.DETECTED;
        GameLog gameLog = GameData.v().log;
        StringBuilder sbU = a.a.u("[GREEN]", str, "[] ");
        sbU.append(GameString.b("DETECTS", false));
        sbU.append(" [BLUE]");
        sbU.append(getName());
        sbU.append("[] [WHITE] ");
        sbU.append(i2);
        sbU.append("%[]");
        gameLog.a(sbU.toString());
        k0.a.l().b(B(), a.EnumC0031a.f2296h, 0.0f);
        String str2 = this.secretdoor_tag;
        if (str2 == null || str2 == "") {
            return;
        }
        GameData.v().j(this.secretdoor_tag);
    }

    public final boolean H() {
        if (this.state != SecretDoorState.UNDETECTED || GameData.v().u() < this.lastDetectCheck + 3.0f) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return b.s(coordsB, GameData.v().player.B()) <= 160;
    }

    public final SecretDoorState I() {
        return this.state;
    }

    public final boolean J() {
        return this.state == SecretDoorState.OPEN;
    }

    public final void K() {
        this.state = SecretDoorState.OPEN;
        k0.a.l().p();
    }

    public final void L(Character character) {
        this.lastDetectCheck = GameData.v().u();
        if (this.state != SecretDoorState.UNDETECTED) {
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
        a.a.w("SECRET_DOOR", false, sb, "(");
        return a.a.p(sb, this.level, ")");
    }

    public SecretDoor(MapProperties hVar) {
        this.f3092x = ((int) Float.parseFloat(hVar.b("x").toString())) - 24;
        this.f3093y = ((int) Float.parseFloat(hVar.b("y").toString())) + 24;
        if (hVar.a("tag")) {
            this.tag = hVar.b("tag").toString();
        }
        int iMax = Math.max(GameData.v().player.sheet.stats.f() - 2, 0);
        int iF = GameData.v().player.sheet.stats.f() + 2;
        iMax = hVar.a("minlevel") ? Integer.parseInt(hVar.b("minlevel").toString()) : iMax;
        iF = hVar.a("maxlevel") ? Integer.parseInt(hVar.b("maxlevel").toString()) : iF;
        this.level = FDUtils.b(iMax > iF ? iF : iMax, iF);
        this.faction = Factions.Faction.NEUTRAL;
        this.state = SecretDoorState.UNDETECTED;
        this.secretdoor_tag = "";
        if (hVar.a("secretdoor_tag")) {
            this.secretdoor_tag = hVar.b("secretdoor_tag").toString();
        }
        if (this.secretdoor_tag != "") {
            if (GameData.v().J(this.secretdoor_tag)) {
                this.state = SecretDoorState.DETECTED;
                return;
            }
            return;
        }
        System.out.println("WARNING: secret door not tagged");
    }
}

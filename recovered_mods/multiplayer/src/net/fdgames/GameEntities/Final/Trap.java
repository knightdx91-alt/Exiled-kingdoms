package net.fdgames.GameEntities.Final;

import android.app.cYK.BQmoQ;
import androidx.appcompat.view.menu.dC.pheteKhVjcmzvb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import com.google.android.gms.games.quest.Quests;
import com.pairip.licensecheck.wYh.JDxjJEGD;
import g0.h;
import java.util.Locale;
import m0.p;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.AreaEffects;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.TrapData;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;
import t.Lzz.yorS;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Trap extends MapSprite {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Color f3253b = new Color(Color.RED);
    private int animationIndex;
    private boolean beingDisarmed;
    private boolean damageDealt;
    private Damage.DamageType damageType;
    public boolean discovered;
    private int dmgPerLevel;
    private float duration;
    private String effect;
    private int explosion_level;
    private float lastDetectCheck;
    private int level;
    private String name;
    private int owner_id;
    private float rearm;
    private String region_id;
    private boolean respawned;
    private String soundName;
    private TrapState state;
    private float stateRelativeTime;
    private String trapType;
    private p trappedArea;
    private boolean triggered;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class TrapState {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final TrapState f3254b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final TrapState f3255c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final TrapState f3256d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final TrapState f3257e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ TrapState[] f3258f;

        static {
            TrapState trapState = new TrapState("READY", 0);
            f3254b = trapState;
            TrapState trapState2 = new TrapState("PREFIRING", 1);
            f3255c = trapState2;
            TrapState trapState3 = new TrapState("FIRING", 2);
            f3256d = trapState3;
            TrapState trapState4 = new TrapState("SPENT", 3);
            f3257e = trapState4;
            f3258f = new TrapState[]{trapState, trapState2, trapState3, trapState4};
        }

        private TrapState() {
            throw null;
        }

        public static TrapState valueOf(String str) {
            return (TrapState) Enum.valueOf(TrapState.class, str);
        }

        public static TrapState[] values() {
            return (TrapState[]) f3258f.clone();
        }
    }

    public Trap() {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
    }

    public Trap(int i2, int i3, int i4, int[] iArr, int i5, float f2) {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
        this.owner_id = i5;
        this.f3307x = i2;
        this.f3308y = i3;
        this.trappedArea = new p(this.f3307x - 12, this.f3308y - 12, 24.0f, 24.0f);
        this.rearm = 0.0f;
        this.duration = 0.6f;
        this.trapType = "bear";
        TrapData.TrapDataLine trapDataLineA = Rules.f3477j.a("bear");
        this.damageType = trapDataLineA.dmgType;
        this.name = GameString.b(trapDataLineA.nameTag, true);
        this.dmgPerLevel = trapDataLineA.dmgLevel;
        String str = trapDataLineA.sprite;
        int i6 = 0;
        while (true) {
            if (i6 >= GameAssets.f3573t) {
                i6 = 0;
                break;
            } else if (GameAssets.f3575u[i6].name.equals(str)) {
                break;
            } else {
                i6++;
            }
        }
        this.animationIndex = i6;
        this.duration = trapDataLineA.duration;
        this.effect = trapDataLineA.effect;
        this.level = i4;
        y(iArr);
        this.state = TrapState.f3254b;
        this.damageDealt = false;
        this.discovered = true;
        this.explosion_level = 0;
        o("DESTROY", q(), null, f2, null);
        Z();
    }

    public Trap(h hVar) {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
        this.owner_id = 0;
        this.f3307x = ((int) Float.parseFloat(hVar.b("x").toString())) - 24;
        this.f3308y = ((int) Float.parseFloat(hVar.b(pheteKhVjcmzvb.QbjynWbdZ).toString())) + 24;
        if (hVar.a("random_range")) {
            int i2 = Integer.parseInt(hVar.b("random_range").toString());
            b.P().getClass();
            Coords coordsV = b.V(this.f3307x, this.f3308y, i2 * 32);
            this.f3307x = coordsV.f3508x;
            this.f3308y = coordsV.f3509y;
        }
        this.trappedArea = new p(this.f3307x - 12, this.f3308y - 12, 24.0f, 24.0f);
        if (hVar.a("tag")) {
            this.tag = hVar.b("tag").toString();
        }
        this.rearm = 0.0f;
        this.duration = 0.6f;
        String string = hVar.b("id").toString();
        this.trapType = string;
        TrapData.TrapDataLine trapDataLineA = Rules.f3477j.a(string);
        this.damageType = trapDataLineA.dmgType;
        this.name = GameString.b(trapDataLineA.nameTag, true);
        this.dmgPerLevel = trapDataLineA.dmgLevel;
        String str = trapDataLineA.sprite;
        int i3 = 0;
        while (true) {
            if (i3 >= GameAssets.f3573t) {
                i3 = 0;
                break;
            } else if (GameAssets.f3575u[i3].name.equals(str)) {
                break;
            } else {
                i3++;
            }
        }
        this.animationIndex = i3;
        this.duration = trapDataLineA.duration;
        this.effect = trapDataLineA.effect;
        int iMax = Math.max(GameData.v().player.sheet.stats.f() - 2, 1);
        int iF = GameData.v().player.sheet.stats.f() + 2;
        iMax = hVar.a("minlevel") ? Integer.parseInt(hVar.b("minlevel").toString()) : iMax;
        String str2 = BQmoQ.TXfaJCjWSQQuq;
        iF = hVar.a(str2) ? Integer.parseInt(hVar.b(str2).toString()) : iF;
        this.level = FDUtils.b(iMax > iF ? iF : iMax, iF);
        if (hVar.a("rearm")) {
            this.rearm = Integer.parseInt(hVar.b("rearm").toString());
            this.respawned = true;
        }
        if (b.P().G.equals("")) {
            x(Quests.SELECT_COMPLETED_UNCLAIMED);
        } else {
            y(WorldFactions.i(b.P().G));
        }
        this.state = TrapState.f3254b;
        this.damageDealt = false;
        this.discovered = false;
    }

    private DamageData Q() {
        int i2 = this.level * this.dmgPerLevel;
        if (GameData.v().E()) {
            i2 = (int) (i2 * 1.2f);
        }
        if (GameData.v().F()) {
            i2 = (int) (i2 * 0.6f);
        }
        return new DamageData(this.damageType, FDUtils.b(i2 / 2, i2), false);
    }

    private void S(int i2) {
        Character characterF;
        int i3 = this.owner_id;
        if (i3 > 0) {
            MapSprite mapSpriteI = GameLevel.i(i3);
            if (mapSpriteI != null) {
                mapSpriteI.n(i2, Q());
            } else {
                System.out.println("WARNING: null projectile owner, no damage done");
            }
        } else {
            n(i2, Q());
        }
        if (this.effect.equals("") || (characterF = GameLevel.f(i2)) == null || !this.effect.toLowerCase(Locale.ENGLISH).equals("slow")) {
            return;
        }
        characterF.s0(5.0f);
    }

    private void W() {
        this.state = TrapState.f3257e;
        this.stateRelativeTime = 0.0f;
        if (this.rearm > 0.0f) {
            o("REARM", q(), null, this.rearm, null);
        } else {
            o("DESTROY", q(), null, 5.0f, null);
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void F(SpriteBatch spriteBatch) {
        int iOrdinal = this.state.ordinal();
        if (iOrdinal == 0) {
            if (r()[0] == 100) {
                spriteBatch.draw((TextureRegion) GameAssets.f3575u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
                return;
            } else {
                if (this.discovered) {
                    spriteBatch.getColor();
                    spriteBatch.setColor(f3253b);
                    spriteBatch.draw((TextureRegion) GameAssets.f3575u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
                    spriteBatch.setColor(Color.WHITE);
                    return;
                }
                return;
            }
        }
        if (iOrdinal == 1) {
            spriteBatch.draw((TextureRegion) GameAssets.f3575u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
        } else if (iOrdinal == 2) {
            spriteBatch.draw((TextureRegion) GameAssets.f3575u[this.animationIndex].animation.getKeyFrame(this.stateRelativeTime), H(), I() - 4);
        } else {
            if (iOrdinal != 3) {
                return;
            }
            spriteBatch.draw((TextureRegion) GameAssets.f3575u[this.animationIndex].animation.getKeyFrame(this.duration), H(), I() - 4);
        }
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 64;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        if (b.s(coordsB, GameData.v().player.B()) > 700) {
            return;
        }
        TrapState trapState = this.state;
        TrapState trapState2 = TrapState.f3254b;
        TrapState trapState3 = TrapState.f3255c;
        if (trapState == trapState2) {
            b bVarP = b.P();
            int[] iArrR = r();
            p pVar = this.trappedArea;
            bVarP.getClass();
            if (b.n(iArrR, pVar) > 0) {
                this.state = trapState3;
                this.stateRelativeTime = 0.0f;
                GameAssets.o("trap");
                return;
            }
            return;
        }
        TrapState trapState4 = TrapState.f3256d;
        if (trapState == trapState3) {
            float f3 = this.stateRelativeTime + f2;
            this.stateRelativeTime = f3;
            if (f3 > 0.1f) {
                this.state = trapState4;
                this.stateRelativeTime = 0.0f;
                return;
            }
            return;
        }
        if (trapState == trapState4) {
            this.stateRelativeTime += f2;
            if (!this.damageDealt && !this.triggered) {
                this.triggered = true;
                b bVarP2 = b.P();
                int[] iArrR2 = r();
                p pVar2 = this.trappedArea;
                bVarP2.getClass();
                int iN = b.n(iArrR2, pVar2);
                if (iN > 0) {
                    this.damageDealt = true;
                    S(iN);
                    if (GameData.v().party != null && GameData.v().party.n(iN)) {
                        S(1);
                    }
                }
                if (this.explosion_level == 1) {
                    AreaEffects.d(this.f3307x, this.f3308y, this.owner_id, "explo_weak_1");
                }
                if (this.explosion_level == 2) {
                    AreaEffects.d(this.f3307x, this.f3308y, this.owner_id, "explo_weak_2");
                }
                if (this.explosion_level == 3) {
                    AreaEffects.d(this.f3307x, this.f3308y, this.owner_id, "explo_weak_3");
                }
            }
            if (this.stateRelativeTime > this.duration) {
                W();
            }
        }
    }

    public final int N(Character character) {
        return FDUtils.c(character.sheet.stats.f(), this.level) + character.sheet.t();
    }

    public final void O(int i2, String str) {
        this.discovered = true;
        GameLog gameLog = GameData.v().log;
        StringBuilder sbT = a.a.t("[GREEN]", str, "[] ");
        sbT.append(GameString.b("DETECTS", false));
        sbT.append(" [RED]");
        sbT.append(getName());
        sbT.append("[] [WHITE] ");
        sbT.append(i2);
        sbT.append("%[]");
        gameLog.a(sbT.toString());
        Z();
    }

    public final boolean P() {
        if (this.state != TrapState.f3254b || this.discovered || !b.P().b0(this.f3307x / 32, this.f3308y / 32) || GameData.v().u() < this.lastDetectCheck + 3.0f) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        return b.s(coordsB, GameData.v().player.B()) <= 160;
    }

    public final int R() {
        return this.owner_id;
    }

    public final boolean T() {
        return this.state == TrapState.f3254b && this.discovered && !this.faction.equals(100) && !this.destroy;
    }

    public final boolean U() {
        if (this.state != TrapState.f3254b || !this.discovered || this.owner_id == 1 || this.destroy) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        return ((float) b.s(coordsB, GameData.v().player.B())) <= 48.0f;
    }

    public final void V(int i2) {
        this.explosion_level = i2;
    }

    public final void X(Character character) {
        this.lastDetectCheck = GameData.v().u();
        if (this.state == TrapState.f3254b && !this.discovered && GameWorld.f3410c.g(character.r(), r())) {
            int iC = FDUtils.c(character.sheet.stats.f(), this.level) + character.sheet.s();
            if (FDUtils.b(1, 100) < iC) {
                O(iC, character.getName());
            }
        }
    }

    public final void Y(Character character) {
        String strN;
        boolean z2;
        if (!this.beingDisarmed && this.state == TrapState.f3254b && GameWorld.f3410c.g(character.r(), r())) {
            this.beingDisarmed = true;
            int iN = N(character);
            String str = "[GREEN]" + character.getName() + "[] " + GameString.b(qJDUJ.BdxLpWAoquUSL, false) + " [RED]" + getName() + JDxjJEGD.xzYIVhe + iN + "%[]";
            if (FDUtils.b(1, 100) <= iN) {
                strN = a.a.n("DISARM_SUCCESS", false, a.a.s(str, " "));
                z2 = true;
            } else {
                strN = a.a.n("DISARM_FAIL", false, a.a.s(str, " "));
                z2 = false;
            }
            GameData.v().log.a(strN);
            if (!z2) {
                S(character.q());
                W();
                return;
            }
            if (this.faction == Factions.Faction.f3275b) {
                b.P().f4028t[this.f3307x / 32][this.f3308y / 32] = 0;
            }
            this.destroy = true;
            if (this.respawned) {
                return;
            }
            character.O0(this.level * 5);
        }
    }

    public final void Z() {
        if (this.faction == Factions.Faction.f3275b && this.discovered && this.state == TrapState.f3254b) {
            b.P().f4028t[this.f3307x / 32][this.f3308y / 32] = 1;
        }
        b.P().f4028t[this.f3307x / 32][this.f3308y / 32] = 0;
    }

    public final void a0() {
        int[] iArr = this.worldfactions;
        if (iArr == null || iArr[0] == 0) {
            if (this.faction.equals(Factions.Faction.f3275b)) {
                x(Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.f3277d)) {
                x(100);
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        return a.a.p(sb, this.level, yorS.cgfHT);
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
        if (str.equals("REARM")) {
            this.state = TrapState.f3254b;
            this.respawned = true;
        } else if (str.equals("DESTROY")) {
            this.destroy = true;
        }
    }
}

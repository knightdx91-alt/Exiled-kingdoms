package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.android.gms.games.quest.Quests;
import java.util.Locale;
import m0.b;
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
import com.badlogic.gdx.maps.MapProperties;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Trap extends MapSprite {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Color f3038a = new Color(Color.RED);
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
    private Rectangle trappedArea;
    private boolean triggered;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum TrapState {
        READY, PREFIRING, FIRING, SPENT;
    }

    public Trap() {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
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
        this.state = TrapState.SPENT;
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
                spriteBatch.draw((TextureRegion) GameAssets.f3353u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
                return;
            } else {
                if (this.discovered) {
                    spriteBatch.getColor();
                    spriteBatch.setColor(f3038a);
                    spriteBatch.draw((TextureRegion) GameAssets.f3353u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
                    spriteBatch.setColor(Color.WHITE);
                    return;
                }
                return;
            }
        }
        if (iOrdinal == 1) {
            spriteBatch.draw((TextureRegion) GameAssets.f3353u[this.animationIndex].animation.getKeyFrame(0.0f), H(), I() - 4);
        } else if (iOrdinal == 2) {
            spriteBatch.draw((TextureRegion) GameAssets.f3353u[this.animationIndex].animation.getKeyFrame(this.stateRelativeTime), H(), I() - 4);
        } else {
            if (iOrdinal != 3) {
                return;
            }
            spriteBatch.draw((TextureRegion) GameAssets.f3353u[this.animationIndex].animation.getKeyFrame(this.duration), H(), I() - 4);
        }
    }

    @Override // net.fdgames.GameEntities.MapSprite
    protected final int G() {
        return 64;
    }

    @Override // net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        if (b.s(coordsB, GameData.v().player.B()) > 700) {
            return;
        }
        TrapState trapState = this.state;
        TrapState trapState2 = TrapState.READY;
        TrapState trapState3 = TrapState.PREFIRING;
        if (trapState == trapState2) {
            b bVarP = b.P();
            int[] iArrR = r();
            Rectangle pVar = this.trappedArea;
            bVarP.getClass();
            if (b.n(iArrR, pVar) > 0) {
                this.state = trapState3;
                this.stateRelativeTime = 0.0f;
                GameAssets.o("trap");
                return;
            }
            return;
        }
        TrapState trapState4 = TrapState.FIRING;
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
                Rectangle pVar2 = this.trappedArea;
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
                    AreaEffects.d(this.f3092x, this.f3093y, "explo_weak_1", this.owner_id);
                }
                if (this.explosion_level == 2) {
                    AreaEffects.d(this.f3092x, this.f3093y, "explo_weak_2", this.owner_id);
                }
                if (this.explosion_level == 3) {
                    AreaEffects.d(this.f3092x, this.f3093y, "explo_weak_3", this.owner_id);
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
        StringBuilder sbU = a.a.u("[GREEN]", str, "[] ");
        sbU.append(GameString.b("DETECTS", false));
        sbU.append(" [RED]");
        sbU.append(getName());
        sbU.append("[] [WHITE] ");
        sbU.append(i2);
        sbU.append("%[]");
        gameLog.a(sbU.toString());
        Z();
    }

    public final boolean P() {
        if (this.state != TrapState.READY || this.discovered || !b.P().b0(this.f3092x / 32, this.f3093y / 32) || GameData.v().u() < this.lastDetectCheck + 3.0f) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return b.s(coordsB, GameData.v().player.B()) <= 160;
    }

    public final int R() {
        return this.owner_id;
    }

    public final boolean T() {
        return this.state == TrapState.READY && this.discovered && !this.faction.equals(100) && !this.destroy;
    }

    public final boolean U() {
        if (this.state != TrapState.READY || !this.discovered || this.owner_id == 1 || this.destroy) {
            return false;
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return ((float) b.s(coordsB, GameData.v().player.B())) <= 48.0f;
    }

    public final void V(int i2) {
        this.explosion_level = i2;
    }

    public final void X(Character character) {
        this.lastDetectCheck = GameData.v().u();
        if (this.state == TrapState.READY && !this.discovered && GameWorld.f3189c.g(character.r(), r())) {
            int iC = FDUtils.c(character.sheet.stats.f(), this.level) + character.sheet.s();
            if (FDUtils.b(1, 100) < iC) {
                O(iC, character.getName());
            }
        }
    }

    public final void Y(Character character) {
        String strN;
        boolean z2;
        if (!this.beingDisarmed && this.state == TrapState.READY && GameWorld.f3189c.g(character.r(), r())) {
            this.beingDisarmed = true;
            int iN = N(character);
            String str = "[GREEN]" + character.getName() + "[] " + GameString.b("TRIES_DISARM", false) + " [RED]" + getName() + "[] [WHITE] " + iN + "%[]";
            if (FDUtils.b(1, 100) <= iN) {
                strN = a.a.n("DISARM_SUCCESS", false, a.a.t(str, " "));
                z2 = true;
            } else {
                strN = a.a.n("DISARM_FAIL", false, a.a.t(str, " "));
                z2 = false;
            }
            GameData.v().log.a(strN);
            if (!z2) {
                S(character.q());
                W();
                return;
            }
            if (this.faction == Factions.Faction.ENEMY) {
                b.P().f2434t[this.f3092x / 32][this.f3093y / 32] = 0;
            }
            this.destroy = true;
            if (this.respawned) {
                return;
            }
            character.O0(this.level * 5);
        }
    }

    public final void Z() {
        if (this.faction == Factions.Faction.ENEMY && this.discovered && this.state == TrapState.READY) {
            b.P().f2434t[this.f3092x / 32][this.f3093y / 32] = 1;
        }
        b.P().f2434t[this.f3092x / 32][this.f3093y / 32] = 0;
    }

    public final void a0() {
        int[] iArr = this.worldfactions;
        if (iArr == null || iArr[0] == 0) {
            if (this.faction.equals(Factions.Faction.ENEMY)) {
                x(Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.PLAYER)) {
                x(100);
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(");
        return a.a.p(sb, this.level, ")");
    }

    @Override // net.fdgames.GameEntities.MapObject, net.fdgames.GameEntities.GameObject
    public final void v(String str, int i2, String str2, DamageData damageData) {
        if (str.equals("REARM")) {
            this.state = TrapState.READY;
            this.respawned = true;
        } else if (str.equals("DESTROY")) {
            this.destroy = true;
        }
    }

    public Trap(int i2, int i3, int i4, int[] iArr, int i5, float f2) {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
        this.owner_id = i5;
        this.f3092x = i2;
        this.f3093y = i3;
        this.trappedArea = new Rectangle (this.f3092x - 12, this.f3093y - 12, 24.0f, 24.0f);
        this.rearm = 0.0f;
        this.duration = 0.6f;
        this.trapType = "bear";
        TrapData.TrapDataLine trapDataLineA = Rules.f3256j.a("bear");
        this.damageType = trapDataLineA.dmgType;
        this.name = GameString.b(trapDataLineA.nameTag, true);
        this.dmgPerLevel = trapDataLineA.dmgLevel;
        String str = trapDataLineA.sprite;
        int i6 = 0;
        while (true) {
            if (i6 >= GameAssets.f3352t) {
                i6 = 0;
                break;
            } else if (GameAssets.f3353u[i6].name.equals(str)) {
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
        this.state = TrapState.READY;
        this.damageDealt = false;
        this.discovered = true;
        this.explosion_level = 0;
        o("DESTROY", q(), null, f2, null);
        Z();
    }

    public Trap(MapProperties hVar) {
        this.discovered = false;
        this.damageDealt = false;
        this.lastDetectCheck = 0.0f;
        this.respawned = false;
        this.beingDisarmed = false;
        this.owner_id = 0;
        this.f3092x = ((int) Float.parseFloat(hVar.get("x").toString())) - 24;
        this.f3093y = ((int) Float.parseFloat(hVar.get("y").toString())) + 24;
        if (hVar.containsKey("random_range")) {
            int i2 = Integer.parseInt(hVar.get("random_range").toString());
            b.P().getClass();
            Coords coordsV = b.V(this.f3092x, this.f3093y, i2 * 32);
            this.f3092x = coordsV.f3287x;
            this.f3093y = coordsV.f3288y;
        }
        this.trappedArea = new Rectangle (this.f3092x - 12, this.f3093y - 12, 24.0f, 24.0f);
        if (hVar.containsKey("tag")) {
            this.tag = hVar.get("tag").toString();
        }
        this.rearm = 0.0f;
        this.duration = 0.6f;
        String string = hVar.get("id").toString();
        this.trapType = string;
        TrapData.TrapDataLine trapDataLineA = Rules.f3256j.a(string);
        this.damageType = trapDataLineA.dmgType;
        this.name = GameString.b(trapDataLineA.nameTag, true);
        this.dmgPerLevel = trapDataLineA.dmgLevel;
        String str = trapDataLineA.sprite;
        int i3 = 0;
        while (true) {
            if (i3 >= GameAssets.f3352t) {
                i3 = 0;
                break;
            } else if (GameAssets.f3353u[i3].name.equals(str)) {
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
        iMax = hVar.containsKey("minlevel") ? Integer.parseInt(hVar.get("minlevel").toString()) : iMax;
        iF = hVar.containsKey("maxlevel") ? Integer.parseInt(hVar.get("maxlevel").toString()) : iF;
        this.level = FDUtils.b(iMax > iF ? iF : iMax, iF);
        if (hVar.containsKey("rearm")) {
            this.rearm = Integer.parseInt(hVar.get("rearm").toString());
            this.respawned = true;
        }
        if (b.P().G.equals("")) {
            x(Quests.SELECT_COMPLETED_UNCLAIMED);
        } else {
            y(WorldFactions.i(b.P().G));
        }
        this.state = TrapState.READY;
        this.damageDealt = false;
        this.discovered = false;
    }
}

package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.AreaEffects;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Skills;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: SkillButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class l1 extends Image {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Color f2765o = new Color(0.9411765f, 0.7058824f, 0.3529412f, 0.66f);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Color f2766p = new Color(0.9411765f, 0.7058824f, 0.3529412f, 0.3f);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static BitmapFont f2767q = GameAssets.f3336k0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f2768r;
    public static boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static float f2769t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static float f2770u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2771a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextureRegionDrawable f2774d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public NinePatchDrawable f2775e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2777g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2778h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2781k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f2782l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f2783m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f2784n;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2772b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2773c = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Color f2776f = new Color();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2779i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2780j = false;

    /* JADX INFO: compiled from: SkillButton.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            l1.s = true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            l1.s = false;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l1.this.b();
            l0.b.i();
            return true;
        }
    }

    static {
        new Color();
        f2768r = false;
        new NinePatchDrawable(GameAssets.R);
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        f2769t = fMin;
        f2770u = fMin * 9.0f;
    }

    public l1(int i2) {
        this.f2774d = null;
        this.f2774d = null;
        f("");
        if (ExiledKingdoms.f3378h) {
            f2770u = f2769t * 4.0f;
        }
        this.f2781k = i2;
    }

    public final void b() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iB;
        int iB2;
        int i7;
        Player player;
        int iZ0;
        int i8;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        if (GameData.v().player.sheet.skillSet.k(this.f2771a)) {
            if (GameData.v().player.g0() && this.f2780j) {
                if (z.x()) {
                    return;
                }
                z.v().I(this.f2771a);
                return;
            }
            z.v().H();
            if (this.f2771a.equals("kick") && GameData.v().player.sheet.skillSet.k("kick")) {
                GameData.v().player.d1();
            }
            if (this.f2771a.equals("bash") && GameData.v().player.sheet.skillSet.k("bash")) {
                GameData.v().player.Y0();
            }
            if (this.f2773c < 1.0f && this.f2771a.equals("whirlwind")) {
                GameData.v().player.g1();
            }
            if (this.f2773c < 1.0f && this.f2771a.equals("charge") && GameData.v().player.sheet.skillSet.k("charge")) {
                GameData.v().player.a1();
            }
            if (this.f2771a.equals("resilience") && GameData.v().player.sheet.skillSet.k("resilience")) {
                SkillActions.j(GameData.v().player);
            }
            if (this.f2771a.equals("stealth") && GameData.v().player.sheet.skillSet.k("stealth")) {
                Player player2 = GameData.v().player;
                int iG = player2.sheet.skillSet.g("smoke_bomb");
                if (iG > 0) {
                    AreaEffects.f(player2.f3092x, player2.f3093y, player2.q());
                    Iterator<NPC> it = GameLevelData.o().npcs.iterator();
                    while (it.hasNext()) {
                        it.next().P1();
                    }
                    if (iG > 1) {
                        m0.b bVarP = m0.b.P();
                        a0.p pVar = new a0.p(player2.f3092x - 48, player2.f3093y - 48, 96.0f, 96.0f);
                        bVarP.getClass();
                        for (Integer num : m0.b.j(pVar)) {
                            if (num.intValue() != player2.q() && GameWorld.f3189c.g(GameLevel.g(num.intValue()).r(), player2.r())) {
                                if (iG == 2 && FDUtils.b(1, 100) <= 35) {
                                    GameLevel.f(num.intValue()).z1(3.0f);
                                }
                                if (iG == 3 && FDUtils.b(1, 100) <= 50) {
                                    GameLevel.f(num.intValue()).z1(5.0f);
                                }
                            }
                        }
                    }
                }
                if (Player.f3027e && iG == 0) {
                    GameConsole.a(GameString.b("ENEMIES_AROUND_HIDE", false));
                } else {
                    int iG2 = player2.sheet.skillSet.g("stealth");
                    if (iG2 == 1) {
                        player2.f1(8.0f);
                    }
                    if (iG2 == 2) {
                        player2.f1(10.0f);
                    }
                    if (iG2 == 3) {
                        player2.f1(12.0f);
                    }
                    player2.sheet.skillSet.s("stealth");
                    k0.a.l().a(new w(player2.q(), "invisible", 1.0f, Color.YELLOW, 1.5f, 0.7f));
                }
            }
            if (this.f2773c < 1.0f && this.f2771a.equals("stab") && GameData.v().player.sheet.skillSet.k("stab")) {
                SkillActions.k(GameData.v().player);
            }
            if (this.f2773c < 1.0f && this.f2771a.equals("rapid_fire") && GameData.v().player.sheet.skillSet.k("rapid_fire")) {
                SkillActions.i(GameData.v().player);
            }
            if (this.f2773c < 1.0f && this.f2771a.equals("flurry") && GameData.v().player.sheet.skillSet.k("flurry")) {
                SkillActions.e(GameData.v().player);
            }
            if (this.f2771a.equals("evasion") && GameData.v().player.sheet.skillSet.k("evasion")) {
                Player player3 = GameData.v().player;
                player3.b1();
                player3.sheet.skillSet.s("evasion");
            }
            if (this.f2771a.equals("sprint") && GameData.v().player.sheet.skillSet.k("sprint")) {
                Player player4 = GameData.v().player;
                int iG3 = player4.sheet.skillSet.g("sprint");
                if (iG3 == 1) {
                    player4.v1(4.0f);
                }
                if (iG3 == 2) {
                    player4.v1(5.0f);
                }
                if (iG3 == 3) {
                    player4.v1(6.0f);
                }
                player4.sheet.skillSet.s("sprint");
            }
            if (this.f2771a.equals("battle_rage") && GameData.v().player.sheet.skillSet.k("battle_rage")) {
                Player player5 = GameData.v().player;
                int iG4 = player5.sheet.skillSet.g("battle_rage");
                if (iG4 > 0) {
                    CharacterSheet characterSheet = player5.sheet;
                    CharacterEffects characterEffects = characterSheet.effects;
                    characterEffects.rage = Boolean.TRUE;
                    if (iG4 == 1) {
                        characterEffects.rageStrBonus = 2;
                        characterEffects.rageArmorBonus = 3;
                        i8 = 6;
                    } else if (iG4 == 2) {
                        characterEffects.rageStrBonus = 3;
                        characterEffects.rageArmorBonus = 4;
                        i8 = 8;
                    } else if (iG4 == 3) {
                        characterEffects.rageStrBonus = 4;
                        characterEffects.rageArmorBonus = 5;
                        i8 = 9;
                    }
                    characterSheet.skillSet.s("battle_rage");
                    player5.o("FATIGUE", player5.q(), null, i8, null);
                    if (player5.sheet.effects.stunned.booleanValue()) {
                        player5.sheet.effects.stunned = Boolean.FALSE;
                        if (!player5.i0() && player5.sheet.o() > 0) {
                            player5.q0(MapActor.ActorState.IDLE);
                            player5.p0(new Coords(-1, -1));
                            player5.t0();
                        }
                    }
                }
            }
            if (this.f2771a.equals("beast_master") && GameData.v().player.sheet.skillSet.k("beast_master") && (iZ0 = (player = GameData.v().player).z0()) > 0) {
                GameAssets.o("spell1");
                i2 = 30;
                if (iZ0 == 1) {
                    SkillActions.l(player, "bear_summoned", 10, 30);
                }
                if (iZ0 == 2) {
                    SkillActions.l(player, "bear_summoned", 10, 60);
                }
                if (iZ0 == 3) {
                    SkillActions.l(player, "bear_summoned", 10, 60);
                }
                player.sheet.skillSet.s("beast_master");
            } else {
                i2 = 30;
            }
            if (this.f2771a.equals("trap_master") && GameData.v().player.sheet.skillSet.k("trap_master")) {
                Player player6 = GameData.v().player;
                int iG5 = player6.sheet.skillSet.g("trap_master");
                if (iG5 == 1) {
                    iB = FDUtils.b(1, 3);
                    i6 = 15;
                } else {
                    i6 = 0;
                    iB = 1;
                }
                if (iG5 == 2) {
                    iB = FDUtils.b(4, 6);
                    i6 = 20;
                }
                if (iG5 == 3) {
                    iB2 = FDUtils.b(7, 10);
                    i7 = i2;
                } else {
                    iB2 = iB;
                    i7 = i6;
                }
                Trap trap = new Trap(player6.f3092x, player6.f3093y, iB2, player6.r(), player6.q(), i7);
                AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                GameLevelData.i(trap);
                player6.sheet.skillSet.s("trap_master");
                int iG6 = player6.sheet.skillSet.g("explosive_traps");
                if (iG6 > 0) {
                    trap.V(iG6);
                }
            }
            if (this.f2771a.equals("heal_wounds") && GameData.v().player.sheet.skillSet.k("heal_wounds")) {
                GameData.v().player.x1("heal_wounds");
                GameData.v().player.s1(1);
            }
            if (this.f2771a.equals("thelumes_wisdom") && GameData.v().player.sheet.skillSet.k("thelumes_wisdom")) {
                GameData.v().player.x1("thelumes_wisdom");
            }
            if (this.f2771a.equals("holy_shield") && GameData.v().player.sheet.skillSet.k("holy_shield") && GameData.v().player.sheet.p() >= GameData.v().player.sheet.skillSet.f("holy_shield")) {
                SkillActions.h(GameData.v().player);
            }
            if (this.f2771a.equals("guardian_wolf") && GameData.v().player.sheet.skillSet.k("guardian_wolf") && GameData.v().player.sheet.p() >= GameData.v().player.sheet.skillSet.f("guardian_wolf")) {
                Player player7 = GameData.v().player;
                if (player7.Z0(player7.sheet.skillSet.f("guardian_wolf"), 2.0f)) {
                    int iG7 = player7.sheet.skillSet.g("guardian_wolf");
                    if (iG7 == 1) {
                        SkillActions.l(player7, "dire_wolf", 7, 180);
                    }
                    if (iG7 == 2) {
                        SkillActions.l(player7, "white_wolf", 10, 300);
                    }
                    if (iG7 == 3) {
                        SkillActions.l(player7, "spirit_wolf", 13, 300);
                    }
                    player7.sheet.skillSet.s("guardian_wolf");
                }
            }
            if (this.f2771a.equals("arbenos_might") && GameData.v().player.sheet.skillSet.k("arbenos_might")) {
                GameData.v().player.x1("arbenos_might");
            }
            if (this.f2771a.equals("sacred_fire") && GameData.v().player.sheet.skillSet.k("sacred_fire")) {
                Player player8 = GameData.v().player;
                if (player8.Z0(player8.sheet.skillSet.f("sacred_fire"), 1.0f)) {
                    Coords coordsL = m0.b.L(player8.B(), 51, player8.facing);
                    k0.a.l().c(coordsL, "sacred_fire", 1.3f);
                    k0.a.l().d(1.0f, coordsL.f3287x - 32, coordsL.f3288y + 32, VertexAttributes.Usage.Tangent, "flash_red");
                    int iG8 = player8.sheet.skillSet.g("sacred_fire");
                    int i9 = 12;
                    if (iG8 == 1) {
                        i3 = 12;
                        i4 = 8;
                    } else {
                        i3 = 0;
                        i4 = 0;
                    }
                    if (iG8 == 2) {
                        i3 = 24;
                    } else {
                        i9 = i4;
                    }
                    if (iG8 == 3) {
                        i9 = 20;
                        i5 = 48;
                    } else {
                        i5 = i3;
                    }
                    GameAssets.o("spell1");
                    a0.p pVar2 = new a0.p(coordsL.f3287x - 38.4f, coordsL.f3288y - 38.4f, 76.8f, 76.8f);
                    m0.b.P().getClass();
                    ArrayList<Integer> arrayListJ = m0.b.j(pVar2);
                    Damage.DamageType damageType = Damage.DamageType.Fire;
                    DamageData damageData = new DamageData(damageType, i5, false);
                    DamageData damageData2 = new DamageData(damageType, i9, false);
                    for (Integer num2 : arrayListJ) {
                        if (GameWorld.f3189c.g(GameLevel.g(num2.intValue()).r(), player8.r())) {
                            player8.n(num2.intValue(), damageData);
                            if (GameLevel.g(num2.intValue()).e0()) {
                                player8.n(num2.intValue(), damageData2);
                            }
                        }
                    }
                    player8.sheet.skillSet.s("sacred_fire");
                }
            }
            if (this.f2771a.equals("turn_undead") && GameData.v().player.sheet.skillSet.k("turn_undead")) {
                GameData.v().player.x1("turn_undead");
            }
            if (this.f2771a.equals("flames_of_faith") && GameData.v().player.sheet.skillSet.k("flames_of_faith")) {
                GameData.v().player.x1("flames_of_faith");
            }
            if (this.f2771a.equals("battle_prayer") && GameData.v().player.sheet.skillSet.k("battle_prayer")) {
                GameData.v().player.x1("battle_prayer");
            }
            if (this.f2771a.equals("lightning_bolt") && GameData.v().player.sheet.skillSet.k("lightning_bolt")) {
                GameData.v().player.y1("lightning_bolt", 0.3f);
            }
            if (this.f2771a.equals("fireball") && GameData.v().player.sheet.skillSet.k("fireball")) {
                GameData.v().player.y1("fireball", 0.3f);
            }
            if (this.f2771a.equals("death_cloud") && GameData.v().player.sheet.skillSet.k("death_cloud")) {
                GameData.v().player.y1("death_cloud", 0.3f);
            }
            if (this.f2771a.equals("combustion") && GameData.v().player.sheet.skillSet.k("combustion")) {
                GameData.v().player.y1("combustion", 0.3f);
            }
            if (this.f2771a.equals("ice_storm") && GameData.v().player.sheet.skillSet.k("ice_storm")) {
                GameData.v().player.y1("ice_storm", 0.3f);
            }
            if (this.f2771a.equals("mage_armor") && GameData.v().player.sheet.skillSet.k("mage_armor")) {
                GameData.v().player.y1("mage_armor", 0.6f);
            }
            if (this.f2771a.equals("lesser_summoning") && GameData.v().player.sheet.skillSet.k("lesser_summoning")) {
                GameData.v().player.y1("lesser_summoning", 1.0f);
            }
            if (this.f2771a.equals("fire_mastery") && GameData.v().player.sheet.skillSet.k("fire_mastery")) {
                GameData.v().player.y1("fire_mastery", 1.0f);
            }
            if (this.f2771a.equals("ice_mastery") && GameData.v().player.sheet.skillSet.k("ice_mastery")) {
                GameData.v().player.y1("ice_mastery", 1.0f);
            }
            if (this.f2771a.equals("earth_mastery") && GameData.v().player.sheet.skillSet.k("earth_mastery")) {
                GameData.v().player.y1("earth_mastery", 1.0f);
            }
            if (this.f2771a.equals("disintegrate") && GameData.v().player.sheet.skillSet.k("disintegrate")) {
                GameData.v().player.y1("disintegrate", 0.3f);
            }
            if (this.f2771a.equals("gate") && GameData.v().player.sheet.skillSet.k("gate")) {
                AStarPathFinder aStarPathFinder3 = GameLevel.f3094a;
                int iG9 = GameData.v().player.sheet.skillSet.g("gate");
                int i10 = Skills.c("gate").f(iG9).mana_cost;
                if (GameData.v().player.sheet.p() < i10 || !GameData.v().player.G0(3, Skills.c("gate").d())) {
                    return;
                }
                GameData.v().player.sheet.j0(i10);
                GameData.v().player.sheet.skillSet.s("gate");
                if (iG9 == 1) {
                    GameData.v().player.o("RECALL", 1, null, 3.0f, null);
                }
                if (iG9 == 2) {
                    GameData.v().player.o("TELEPORT", 1, null, 3.0f, null);
                }
            }
        }
    }

    public final void c() {
        this.f2779i = true;
    }

    public final void d() {
        this.f2779i = false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        String strA;
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.f2771a.equals("")) {
            return;
        }
        this.f2782l = getX() + f2770u;
        this.f2783m = getY() + f2770u;
        this.f2784n = getWidth() - (f2770u * 2.0f);
        Color color = this.f2775e.getPatch().getColor();
        Color color2 = this.f2776f;
        color2.set(color);
        boolean z2 = f2768r;
        if (z2 && this.f2777g) {
            this.f2773c = 1.0f;
        } else if (!z2 && this.f2778h) {
            this.f2773c = 1.0f;
        } else if (GameData.v().u() > this.f2772b + 0.2f) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            this.f2773c = GameData.v().player.sheet.skillSet.c(this.f2771a);
            this.f2772b = GameData.v().u();
            float f3 = this.f2773c;
            if (f3 > 0.0f) {
                this.f2773c = f3 + 0.07f;
            }
            if (this.f2773c > 1.0f) {
                this.f2773c = 1.0f;
            }
        }
        if (this.f2773c > 0.0f) {
            this.f2775e.getPatch().setColor(f2766p);
        } else if (this.f2779i) {
            this.f2775e.getPatch().setColor(Color.GREEN);
        } else {
            this.f2775e.getPatch().setColor(f2765o);
        }
        NinePatchDrawable ninePatchDrawable = this.f2775e;
        float f4 = this.f2782l;
        float f5 = this.f2783m;
        float f6 = this.f2784n;
        ninePatchDrawable.draw(batch, f4, f5, f6, f6);
        this.f2775e.getPatch().setColor(color2);
        float f7 = this.f2773c;
        if (f7 > 0.0f) {
            NinePatchDrawable ninePatchDrawable2 = this.f2775e;
            float f8 = this.f2782l;
            float f9 = this.f2783m;
            float f10 = this.f2784n;
            ninePatchDrawable2.draw(batch, f8, f9, f10 * f7, f10);
        }
        TextureRegionDrawable textureRegionDrawable = this.f2774d;
        if (textureRegionDrawable != null) {
            setDrawable(textureRegionDrawable);
            if (getDrawable() != null) {
                Drawable drawable = getDrawable();
                float f11 = this.f2782l;
                float f12 = this.f2783m;
                float f13 = this.f2784n;
                drawable.draw(batch, f11, f12, f13, f13);
            }
        }
        if (ExiledKingdoms.f3378h && s && !GameLevel.l() && z.v().w() == 0 && !this.f2771a.equals("")) {
            BitmapFont bitmapFont = f2767q;
            Color color3 = bitmapFont.getCache().getColor();
            float scaleX = bitmapFont.getScaleX();
            float scaleY = bitmapFont.getScaleY();
            if (ExiledKingdoms.f3378h) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(z.f2925i0);
                switch (this.f2781k) {
                    case 0:
                        strA = g.a.a(Settings.f3419b.skill1.id);
                        break;
                    case 1:
                        strA = g.a.a(Settings.f3419b.skill2.id);
                        break;
                    case 2:
                        strA = g.a.a(Settings.f3419b.skill3.id);
                        break;
                    case 3:
                        strA = g.a.a(Settings.f3419b.skill4.id);
                        break;
                    case 4:
                        strA = g.a.a(Settings.f3419b.skill5.id);
                        break;
                    case 5:
                        strA = g.a.a(Settings.f3419b.skill6.id);
                        break;
                    case 6:
                        strA = g.a.a(Settings.f3419b.skill7.id);
                        break;
                    case 7:
                        strA = g.a.a(Settings.f3419b.skill8.id);
                        break;
                    default:
                        ControllerConfig controllerConfig = Settings.f3418a;
                        strA = "-1";
                        break;
                }
                bitmapFont.draw(batch, strA, (f2769t * 44.0f) + getX(), (f2769t * 19.0f) + getY());
            }
            bitmapFont.getCache().setColor(color3);
            bitmapFont.getData().setScale(scaleX, scaleY);
        }
        validate();
    }

    public final void e() {
        if (this.f2771a.equals("")) {
            setVisible(false);
        } else {
            setVisible(true);
        }
        this.f2772b = 0.0f;
        this.f2779i = false;
    }

    public final void f(String str) {
        if (ExiledKingdoms.f3378h) {
            f2769t = 1.0f;
        }
        this.f2771a = str;
        if (str.equals("")) {
            this.f2775e = new NinePatchDrawable(GameAssets.I);
            this.f2774d = new TextureRegionDrawable(Skills.c("whirlwind").e());
            return;
        }
        String strReplace = this.f2771a.replace("?", "i");
        this.f2771a = strReplace;
        if (Skills.c(strReplace) == null) {
            return;
        }
        String str2 = this.f2771a;
        this.f2780j = str2.equals("whirlwind") || str2.equals("charge") || str2.equals("bash") || str2.equals("stealth") || str2.equals("kick") || str2.contains("arbenos") || str2.contains("thelume") || str2.equals("holy_shield") || str2.equals("heal_wounds") || str2.equals("sacred_fire") || str2.equals("lightning_bolt") || str2.equals("fireball") || str2.equals("ice_storm") || str2.equals("lesser_summoning") || str2.equals("battle_prayer") || str2.equals("earth_mastery") || str2.equals("fire_mastery") || str2.equals("flames_of_faith") || str2.equals("guardian_wolf") || str2.equals("gate") || str2.equals("ice_mastery") || str2.equals("turn_undead");
        this.f2777g = Skills.c(this.f2771a).requiresMelee;
        this.f2778h = Skills.c(this.f2771a).requiresRanged;
        this.f2775e = new NinePatchDrawable(GameAssets.I);
        this.f2774d = new TextureRegionDrawable(Skills.c(this.f2771a).e());
        clearListeners();
        addListener(new a());
    }
}

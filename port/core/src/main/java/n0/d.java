package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.Castle;
import net.fdgames.GameWorld.Castles;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Quest;
import net.fdgames.GameWorld.QuestVariation;
import net.fdgames.GameWorld.Quests;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: CastleWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class d extends Window {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static d f2594g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static float f2595h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final float f2596i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t f2598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f2599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t f2600d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f2601e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public n0.c f2602f;

    /* JADX INFO: compiled from: CastleWindow.java */
    final class a extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            z.v().f2937a0.f();
        }
    }

    /* JADX INFO: compiled from: CastleWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            d.this.d();
        }
    }

    /* JADX INFO: compiled from: CastleWindow.java */
    final class c extends ChangeListener {

        /* JADX INFO: compiled from: CastleWindow.java */
        final class a extends j {
            a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                int iE;
                if (((Boolean) obj).booleanValue()) {
                    Castles castles = GameWorld.f3188b;
                    c cVar = c.this;
                    Castle castleA = castles.a(d.this.f2597a);
                    int iB = castleA.b();
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    if (GameData.v().player.h() >= iB) {
                        GameData.v().player.R1(iB);
                        int iE2 = (GameWorld.f3189c.c(castleA.faction_id).e() * (-1)) - 5;
                        if (iE2 > 0) {
                            GameWorld.f3189c.a(iE2, 999, castleA.faction_id);
                        }
                        if (!castleA.faction_id2.equals("") && (GameWorld.f3189c.c(castleA.faction_id2).e() * (-1)) - 5 > 0) {
                            GameWorld.f3189c.a(iE, 999, castleA.faction_id2);
                        }
                    }
                    d.this.h();
                }
                GameLevel.n(false);
            }
        }

        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            d dVar = d.this;
            if (dVar.f2598b.isDisabled()) {
                return;
            }
            new a(GameString.b("TH_FINE_CONFIRM", false).replace("{FINE}", "" + GameWorld.f3188b.a(dVar.f2597a).b())).show(z.v().a());
        }
    }

    /* JADX INFO: renamed from: n0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CastleWindow.java */
    final class C0033d extends ChangeListener {
        C0033d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            ControllerConfig controllerConfig = Settings.f3418a;
            ExiledKingdoms.f3371a = true;
            f.f().g(d.this.f2597a);
        }
    }

    static {
        float height = Gdx.graphics.getHeight() / 720.0f;
        f2595h = height;
        f2596i = height * 10.0f;
    }

    public static void e() {
        if (f2594g != null) {
            f2594g = null;
        }
    }

    public static d f() {
        if (f2594g == null) {
            d dVar = new d("", Assets.g());
            t tVar = new t(GameString.b("FINE", false), Assets.g(), "menuButton");
            dVar.f2598b = tVar;
            t tVar2 = new t(GameString.b("QUESTS", false), Assets.g(), "menuButton");
            dVar.f2599c = tVar2;
            t tVar3 = new t(GameString.b("VAULT", false), Assets.g(), "menuButton");
            dVar.f2600d = tVar3;
            t tVar4 = new t(GameString.b("EXIT", false), Assets.g(), "menuButton");
            dVar.f2601e = tVar4;
            dVar.setMovable(false);
            dVar.setModal(true);
            dVar.setWidth(Gdx.graphics.getWidth());
            dVar.setHeight(Gdx.graphics.getHeight());
            dVar.setPosition((Gdx.graphics.getWidth() - dVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - dVar.getHeight()) / 2.0f);
            if (ExiledKingdoms.f3378h) {
                f2595h = 1.0f;
            }
            dVar.setBackground(Assets.g().getDrawable("windowbg"));
            float f2 = ExiledKingdoms.f3378h ? 0.8f : 1.0f;
            float f3 = f2595h;
            int i2 = (int) (300.0f * f3 * f2);
            int i3 = (int) (80.0f * f3 * f2);
            int i4 = (int) (f3 * 20.0f * f2);
            Table table = new Table();
            float f4 = i2;
            table.setWidth(f4);
            table.setHeight((i3 + i4) * 6);
            float f5 = i4;
            table.row().space(f5);
            float f6 = i3;
            table.add(tVar2).width(f4).height(f6);
            table.row().space(f5);
            table.add(tVar3).width(f4).height(f6);
            table.row().space(f5);
            table.add(tVar).width(f4).height(f6);
            table.row().space(f5);
            table.add(tVar4).width(f4).height(f6);
            dVar.addActor(table);
            float width = Gdx.graphics.getWidth() - table.getWidth();
            float f7 = f2596i;
            table.setPosition(width - (2.0f * f7), f7);
            n0.c cVar = new n0.c();
            dVar.f2602f = cVar;
            cVar.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() - (f2595h * 360.0f));
            dVar.addActor(cVar);
            tVar3.addListener(new a());
            tVar4.addListener(dVar.new b());
            tVar.addListener(dVar.new c());
            tVar2.addListener(dVar.new C0033d());
            f2594g = dVar;
        }
        return f2594g;
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void h() {
        this.f2602f.a(this.f2597a);
        t tVar = this.f2598b;
        tVar.setDisabled(true);
        tVar.setText(GameString.b("FINE", false));
        int iB = GameWorld.f3188b.a(this.f2597a).b();
        if (iB > 0) {
            tVar.setText(((Object) tVar.getText()) + " (" + iB + ")");
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.h() >= iB) {
                tVar.setDisabled(false);
            }
        }
        boolean zL1 = Player.L1(WorldFactions.i(m0.b.P().E));
        t tVar2 = this.f2599c;
        if (zL1 || DynamicQuest.h(this.f2597a).size() <= 0) {
            tVar2.setDisabled(true);
        } else {
            tVar2.setDisabled(false);
        }
        if (Player.L1(WorldFactions.i(m0.b.P().E)) || DynamicQuest.h(this.f2597a).size() <= 0) {
            tVar2.setDisabled(true);
        } else {
            tVar2.setDisabled(false);
        }
        boolean zL12 = Player.L1(WorldFactions.i(m0.b.P().E));
        t tVar3 = this.f2600d;
        if (zL12) {
            tVar3.setDisabled(true);
        } else {
            tVar3.setDisabled(false);
        }
    }

    public final void d() {
        setVisible(false);
        Assets.f3309a.t(m0.b.P().f2415a);
        System.gc();
        GameLevel.n(false);
        l0.b.h();
    }

    public final void g(String str) {
        setBackground(Assets.j());
        this.f2597a = str;
        GameWorld.f3188b.a(str).a();
        h();
        this.f2601e.setText(GameString.b("EXIT", false));
        Assets.f3309a.t("townhall");
        setVisible(true);
        for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
            if (dynamicQuest.castleID.equals(this.f2597a)) {
                if (GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) == 50) {
                    Quests quests = GameWorld.f3187a;
                    String str2 = dynamicQuest.quest_ID;
                    quests.getClass();
                    Quest questA = Quests.a(str2);
                    Quests quests2 = GameWorld.f3187a;
                    String str3 = dynamicQuest.quest_ID;
                    String str4 = dynamicQuest.variation_ID;
                    quests2.getClass();
                    QuestVariation questVariationD = Quests.d(str3, str4);
                    if (questA.h() && GameData.v().player.sheet.Q(questVariationD.item_id) > 0) {
                        dynamicQuest.d();
                        GameData.v().player.sheet.b0(questVariationD.item_id);
                    }
                    if (questA.g()) {
                        dynamicQuest.d();
                    }
                    h();
                }
            }
        }
    }
}

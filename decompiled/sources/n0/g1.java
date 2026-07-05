package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import java.util.Iterator;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.DynamicEvent;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Quests;
import net.fdgames.GameWorld.WorldEvents;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.RestPoint;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: RestWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g1 extends Window {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static g1 f2672g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static float f2673h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final float f2674i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t f2675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f2676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f2677c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t f2678d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private RestPoint f2679e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a1 f2680f;

    /* JADX INFO: compiled from: RestWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g1.this.c();
        }
    }

    /* JADX INFO: compiled from: RestWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g1.this.h(true);
        }
    }

    /* JADX INFO: compiled from: RestWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g1.this.h(false);
        }
    }

    /* JADX INFO: compiled from: RestWindow.java */
    final class d extends ChangeListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g1 g1Var = g1.this;
            g1Var.e();
            if (GameData.v().player.h() < 5 || !GameData.v().C(g1Var.f2679e.id)) {
                g1Var.f2675a.setDisabled(true);
            } else {
                g1Var.f2675a.setDisabled(false);
            }
        }
    }

    /* JADX INFO: compiled from: RestWindow.java */
    final class e extends n0.e {
    }

    static {
        float height = Gdx.graphics.getHeight() / 720.0f;
        f2673h = height;
        f2674i = height * 10.0f;
    }

    public static void d() {
        if (f2672g != null) {
            f2672g = null;
        }
    }

    public static g1 f() {
        if (f2672g == null) {
            g1 g1Var = new g1("", Assets.g());
            t tVar = new t(a.a.o("BUY_DRINKS", false, new StringBuilder(), " (5 gp)"), Assets.g(), "menuButton");
            g1Var.f2675a = tVar;
            t tVar2 = new t("", Assets.g(), "menuButton");
            g1Var.f2676b = tVar2;
            t tVar3 = new t("", Assets.g(), "menuButton");
            g1Var.f2677c = tVar3;
            t tVar4 = new t("Exit", Assets.g(), "menuButton");
            g1Var.f2678d = tVar4;
            g1Var.setMovable(false);
            g1Var.setModal(true);
            g1Var.setWidth(Gdx.graphics.getWidth());
            g1Var.setHeight(Gdx.graphics.getHeight());
            g1Var.setPosition((Gdx.graphics.getWidth() - g1Var.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - g1Var.getHeight()) / 2.0f);
            Table table = new Table();
            table.setFillParent(true);
            g1Var.addActor(table);
            float f2 = 1.0f;
            if (ExiledKingdoms.f3378h) {
                f2673h = 1.0f;
                f2 = 0.8f;
            }
            float f3 = f2673h;
            int i2 = (int) (450.0f * f3 * f2);
            int i3 = (int) (f3 * 80.0f * f2);
            int i4 = (int) (f2 * 20.0f);
            Table table2 = new Table();
            float f4 = i2;
            table2.setWidth(f4);
            table2.setHeight((i3 + i4) * 4);
            if (!ExiledKingdoms.f3378h) {
                tVar.getLabel().setFontScale(f2673h);
                tVar2.getLabel().setFontScale(f2673h);
                tVar3.getLabel().setFontScale(f2673h);
                tVar4.getLabel().setFontScale(f2673h);
            }
            float f5 = i4;
            table2.row().space(f5);
            float f6 = i3;
            table2.add(tVar).width(f4).height(f6);
            table2.row().space(f5);
            table2.add(tVar2).width(f4).height(f6);
            table2.row().space(f5);
            table2.add(tVar3).width(f4).height(f6);
            table2.row().space(f5);
            table2.add(tVar4).width(f4).height(f6);
            g1Var.addActor(table2);
            float width = Gdx.graphics.getWidth() - table2.getWidth();
            float f7 = f2674i;
            table2.setPosition(width - (2.0f * f7), f7);
            a1 a1Var = new a1();
            g1Var.f2680f = a1Var;
            a1Var.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() - (f2673h * 320.0f));
            g1Var.addActor(a1Var);
            tVar4.addListener(g1Var.new a());
            tVar2.addListener(g1Var.new b());
            tVar3.addListener(g1Var.new c());
            tVar.addListener(g1Var.new d());
            f2672g = g1Var;
        }
        return f2672g;
    }

    public static int i() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        return (GameData.v().player.sheet.stats.f() * 5) + 7;
    }

    public final void c() {
        Assets.f3309a.t(m0.b.P().f2415a);
        System.gc();
        setVisible(false);
        setBackground((Drawable) null);
        GameLevel.n(false);
        k0.a.l().i();
        if (GameData.v().player.areasVisited.d(GameData.v().CurrentLevel)) {
            l0.b.n();
        }
        GameData.v().player.areasVisited.b();
        l0.b.h();
    }

    public final void e() {
        String strB;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameData.v().player.R1(5);
        GameData.v().l();
        GameData.v().f();
        int i2 = 0;
        for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
            int iB = GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id);
            if (dynamicQuest.knowledge < 3 && iB > 0 && iB < 100) {
                i2++;
            }
        }
        boolean z2 = i2 > 0;
        Iterator<DynamicEvent> it = GameData.v().t().iterator();
        int i3 = 0;
        while (it.hasNext()) {
            if (it.next().knowledge < 3) {
                i3++;
            }
        }
        if (i3 > 0 && GameData.v().o() <= 85) {
            Iterator<DynamicEvent> it2 = GameData.v().t().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    StringBuilder sb = new StringBuilder();
                    a.a.w("DRINK", false, sb, "\r\n\r\n");
                    strB = a.a.n("DRINK_FAIL", false, sb);
                    break;
                }
                DynamicEvent next = it2.next();
                int i4 = next.knowledge;
                if (i4 == 0) {
                    if (GameData.v().o() < GameData.v().party.h() + 50) {
                        next.knowledge++;
                        GameData.v().gameVariables.c(1, "rumors");
                        GameConsole.a(GameString.b("ADDED_RUMOR_JOURNAL", false));
                        strB = GameString.b("DRINK", false) + "\r\n\r\n" + next.e();
                        break;
                    }
                } else if (i4 == 1 || i4 == 2) {
                    int iH = i4 == 1 ? GameData.v().party.h() + 30 : 0;
                    if (next.knowledge == 2) {
                        iH = GameData.v().party.h() + 10;
                    }
                    if (GameData.v().o() < iH) {
                        next.knowledge++;
                        GameData.v().gameVariables.c(1, "rumors");
                        StringBuilder sb2 = new StringBuilder();
                        a.a.w("DRINK", false, sb2, "\r\n\r\n");
                        a.a.w("DRINK_SUCCESS_LOCATION", false, sb2, " ");
                        sb2.append(next.name);
                        sb2.append(". \r\n\r\n");
                        sb2.append(GameString.b("DRINK_SUCCESS_LOCATION2", false));
                        sb2.append(" ");
                        sb2.append(WorldEvents.b(next.knowledge, next.location_ID));
                        sb2.append(".");
                        strB = sb2.toString();
                        break;
                    }
                }
            }
        } else if (z2 && GameData.v().o() <= 75) {
            Iterator<DynamicQuest> it3 = GameData.v().dynamicQuests.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    StringBuilder sb3 = new StringBuilder();
                    a.a.w("DRINK", false, sb3, "\r\n\r\n");
                    strB = a.a.n("DRINK_FAIL", false, sb3);
                    break;
                }
                DynamicQuest next2 = it3.next();
                int iB2 = GameData.v().gameVariables.b("DQ_" + next2.DQ_id);
                int i5 = next2.knowledge;
                if (i5 < 3 && iB2 > 0) {
                    if (iB2 < 100) {
                        int i6 = i5 == 2 ? 20 : 80;
                        if (i5 == 1) {
                            i6 = 50;
                        }
                        if (GameData.v().o() < GameData.v().party.h() + (i5 != 0 ? i6 : 80)) {
                            next2.knowledge++;
                            GameData.v().gameVariables.c(1, "rumors");
                            StringBuilder sb4 = new StringBuilder();
                            a.a.w("DRINK", false, sb4, "\r\n\r\n");
                            a.a.w("DRINK_SUCCESS_LOCATION", false, sb4, " ");
                            Quests quests = GameWorld.f3187a;
                            String str = next2.quest_ID;
                            String str2 = next2.variation_ID;
                            quests.getClass();
                            sb4.append(Quests.d(str, str2).a());
                            sb4.append(". ");
                            a.a.w("DRINK_SUCCESS_LOCATION2", false, sb4, " ");
                            sb4.append(Quests.b(next2.knowledge, next2.location_ID));
                            strB = sb4.toString();
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } else {
            strB = GameWorld.f3190d.b();
        }
        if (GameData.v().B(this.f2679e.id)) {
            this.f2675a.setDisabled(true);
        }
        new e(GameString.b("GOSSIP", false), strB).show(z.v().a());
        this.f2680f.a(this.f2679e.name, GameData.v().C(this.f2679e.id));
    }

    public final void g(RestPoint restPoint) {
        GameData.v().player.areasVisited.c(GameData.v().CurrentLevel);
        this.f2679e = restPoint;
        this.f2680f.a(restPoint.name, GameData.v().C(restPoint.id));
        setBackground(Assets.i());
        GameData.v().player.areasVisited.c(GameData.v().CurrentLevel);
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int iH = GameData.v().player.h();
        int i2 = i();
        t tVar = this.f2676b;
        if (iH >= i2) {
            tVar.setDisabled(false);
        } else {
            tVar.setDisabled(true);
        }
        int iH2 = GameData.v().player.h();
        int i3 = i() / 2;
        t tVar2 = this.f2677c;
        if (iH2 >= i3) {
            tVar2.setDisabled(false);
        } else {
            tVar2.setDisabled(true);
        }
        int iH3 = GameData.v().player.h();
        t tVar3 = this.f2675a;
        if (iH3 < 5 || !GameData.v().C(this.f2679e.id)) {
            tVar3.setDisabled(true);
        } else {
            tVar3.setDisabled(false);
        }
        tVar.setText(GameString.b("REST_12", false) + " (" + i() + " gp)");
        tVar2.setText(GameString.b("REST_6", false) + " (" + (i() / 2) + " gp)");
        StringBuilder sb = new StringBuilder();
        sb.append(GameString.b("BUY_DRINKS", false));
        sb.append(" (5 gp)");
        tVar3.setText(sb.toString());
        this.f2678d.setText(GameString.b("EXIT", false));
        Assets.f3309a.t("menuet");
        setVisible(true);
    }

    public final void h(boolean z2) {
        if (z2) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.R1(i());
        } else {
            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
            GameData.v().player.R1(i() / 2);
        }
        GameData.v().V(z2);
        c();
        GameData.v().player.U1(z2);
    }
}

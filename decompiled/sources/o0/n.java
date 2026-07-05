package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.WorldFaction;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ReputationWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n extends Window {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static n f3612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static float f3613e = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static float f3614f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static float f3615g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n0.t f3616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Table f3617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ScrollPane f3618c;

    /* JADX INFO: compiled from: ReputationWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            n.this.setVisible(false);
            n0.z.v().Y();
            return true;
        }
    }

    public static void a() {
        if (f3612d != null) {
            f3612d = null;
        }
    }

    public static n b() {
        if (f3612d == null) {
            n nVar = new n("", Assets.g());
            n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
            nVar.f3616a = tVar;
            Table table = new Table();
            nVar.f3617b = table;
            ScrollPane scrollPane = new ScrollPane(table);
            nVar.f3618c = scrollPane;
            Image image = new Image();
            nVar.setBackground(Assets.g().getDrawable("windowbg"));
            nVar.setMovable(false);
            nVar.setModal(true);
            nVar.setWidth(f3613e * 950.0f);
            nVar.setHeight(f3613e * 620.0f);
            nVar.setPosition((Gdx.graphics.getWidth() - nVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - nVar.getHeight()) / 2.0f);
            f3614f = f3613e;
            if (ExiledKingdoms.f3378h) {
                f3613e = 0.8f;
                f3614f = 1.0f;
                nVar.setWidth(760.0f);
                nVar.setHeight(f3613e * 620.0f);
                nVar.setPosition((Gdx.graphics.getWidth() - nVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - nVar.getHeight()) / 2.0f);
            }
            f3615g = f3613e * 120.0f;
            scrollPane.setForceScroll(false, true);
            scrollPane.setScrollbarsOnTop(false);
            if (ExiledKingdoms.f3378h) {
                ScrollPane scrollPane2 = new ScrollPane(table, Assets.g());
                nVar.f3618c = scrollPane2;
                scrollPane2.setFadeScrollBars(false);
            }
            image.setDrawable(new NinePatchDrawable(GameAssets.P));
            nVar.row().colspan(3).pad(f3613e * 5.0f).padRight(f3613e * 10.0f);
            nVar.add(nVar.f3618c).fill().expand().top().left();
            nVar.row().colspan(3).align(4).center();
            nVar.add(tVar).bottom().width(f3613e * 200.0f);
            tVar.addListener(nVar.new a());
            f3612d = nVar;
        }
        return f3612d;
    }

    public final void c() {
        char c2;
        char c3;
        this.f3616a.setText(GameString.b("BACK", false));
        Table table = this.f3617b;
        table.clearChildren();
        table.align(8).align(2).left();
        table.columnDefaults(0).width(f3615g).left();
        boolean z2 = true;
        table.columnDefaults(1).width(f3613e * 490.0f).left();
        table.columnDefaults(2).width(f3613e * 210.0f);
        float f2 = 5.0f;
        float f3 = 10.0f;
        table.row().top().pad(f3613e * 5.0f).space(f3613e * 10.0f);
        table.add(new Label("", Assets.g(), "menuLabelStyle")).left();
        Label label = new Label(GameString.b("FACTION", false), Assets.g(), "menuLabelStrongLargeStyle");
        label.setFontScale(f3614f);
        char c4 = 0;
        table.add(label).left().width(f3613e * 480.0f);
        Label label2 = new Label(GameString.b("REPUTATION", false), Assets.g(), "menuLabelStrongLargeStyle");
        label2.setFontScale(f3614f);
        char c5 = 16;
        table.add(label2).right().width(f3613e * 200.0f).align(16);
        for (WorldFaction worldFaction : GameWorld.f3189c.d()) {
            if (worldFaction.code.intValue() < 100 && !(worldFaction.id.contains("town_") && worldFaction.e() == 0)) {
                table.row().top().pad(f3613e * f2).space(f3613e * f3);
                Image image = new Image(worldFaction.b());
                int iB = GameData.v().gameVariables.b("REP_" + worldFaction.id);
                if (iB == -255) {
                    iB = 0;
                }
                String str = iB > 10 ? "[FOREST]" : "[BLACK]";
                if (iB < -10) {
                    str = "[RED]";
                }
                String str2 = iB >= 0 ? "+" : "";
                StringBuilder sb = new StringBuilder("[MAROON]");
                sb.append(WorldFaction.d(GameData.v().gameVariables.b("REP_" + worldFaction.id)));
                sb.append(" (");
                sb.append(str);
                sb.append(str2);
                sb.append(iB);
                sb.append("[])[]");
                Label label3 = new Label(sb.toString(), GameAssets.S);
                label3.setFontScale(f3614f);
                Table table2 = new Table();
                Label label4 = new Label(worldFaction.c(), Assets.g(), "menuLabelStrongLargeStyle");
                label4.setWrap(true);
                label4.setFontScale(f3614f);
                Label label5 = new Label("[BLACK]" + worldFaction.a() + "[]", GameAssets.S);
                label5.setWrap(true);
                label5.setFontScale(f3614f);
                table2.row().top().width(f3613e * 430.0f).spaceBottom(f3613e * 6.0f);
                table2.add(label4);
                table2.row().top().width(f3613e * 430.0f);
                table2.add(label5);
                table.add(image).left().width(f3615g).height(f3615g * (worldFaction.b().getRegionHeight() / worldFaction.b().getRegionWidth()));
                c2 = 0;
                table.add(table2).left().width(f3613e * 480.0f);
                c3 = 16;
                table.add(label3).right().width(f3613e * 220.0f).align(16);
            } else {
                c3 = c5;
                c2 = c4;
            }
            c4 = c2;
            z2 = true;
            f2 = 5.0f;
            f3 = 10.0f;
            c5 = c3;
        }
        setVisible(z2);
    }
}

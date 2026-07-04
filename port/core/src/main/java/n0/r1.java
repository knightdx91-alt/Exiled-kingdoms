package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: WorldMapInfoTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class r1 extends Table {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static r1 f2876e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static float f2877f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static float f2878g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Image f2879a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f2880b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f2881c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2882d;

    /* JADX INFO: compiled from: WorldMapInfoTable.java */
    final class a extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            z zVarV = z.v();
            zVarV.J(0);
            k0.a.l().f2278e.zoom = 1.0f;
            GameLevel.n(false);
            zVarV.Y();
            zVarV.W();
            return true;
        }
    }

    static {
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        f2877f = fMin;
        f2878g = fMin * 160.0f;
    }

    public static void a() {
        if (f2876e != null) {
            f2876e = null;
        }
    }

    public static r1 b() {
        if (f2876e == null) {
            r1 r1Var = new r1(Assets.g());
            Image image = new Image();
            r1Var.f2879a = image;
            t tVar = new t(GameString.b("BACK_TO_GAME", false), Assets.g(), "menuButton");
            Label label = new Label(GameString.b("UNKNOWN_AREA", false), Assets.g(), "menuLabelStrongStyle");
            r1Var.f2880b = label;
            Label label2 = new Label(GameString.b("UNKNOWN_REGION", false), Assets.g(), "menuLabelStyle");
            r1Var.f2881c = label2;
            r1Var.setBackground(Assets.g().getDrawable("windowbg"));
            r1Var.setWidth(f2877f * 520.0f);
            r1Var.setHeight(f2877f * 210.0f);
            r1Var.setPosition(Gdx.graphics.getWidth() * 0.56f, Gdx.graphics.getHeight() * 0.02f);
            r1Var.f2882d = f2877f;
            if (ExiledKingdoms.f3378h) {
                f2877f = 0.8f;
                r1Var.f2882d = 1.0f;
                r1Var.setWidth(416.0f);
                r1Var.setHeight(f2877f * 210.0f);
                r1Var.setPosition(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.02f);
            }
            f2878g = f2877f * 160.0f;
            label.setFontScale(r1Var.f2882d);
            label2.setFontScale(r1Var.f2882d);
            label.setWrap(true);
            label2.setWrap(true);
            image.setDrawable(new TextureRegionDrawable(Assets.m("unknown")));
            r1Var.center();
            Table table = new Table();
            table.top();
            table.pad(f2877f * 8.0f);
            table.row().padTop(f2877f * 20.0f).center();
            table.add(label).width(r1Var.getWidth() - ((f2878g + 60.0f) * f2877f)).center();
            table.row().spaceTop(f2877f * 10.0f);
            table.add(label2).width(r1Var.getWidth() - ((f2878g + 60.0f) * f2877f)).center();
            table.row().expandY().bottom().center().colspan(2).spaceTop(f2877f * 20.0f);
            table.add(tVar).bottom().center();
            r1Var.row();
            r1Var.add(image).width(f2878g).height(f2878g).pad(f2877f * 5.0f).padLeft(f2877f * 20.0f).center();
            r1Var.add(table);
            tVar.clearListeners();
            tVar.addListener(new a());
            f2876e = r1Var;
        }
        return f2876e;
    }

    public final void c(String str, String str2, String str3) {
        this.f2880b.setText(str);
        this.f2881c.setText(str2);
        this.f2879a.setDrawable(new TextureRegionDrawable(Assets.m(str3)));
    }
}

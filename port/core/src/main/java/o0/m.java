package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: QuickSlotWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class m extends Window {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f3596g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static float f3597h = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static m f3598i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.t f3599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n0.t f3600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Label f3601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i[] f3602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o0.f f3603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3604f;

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m mVar = m.this;
            if (mVar.f3600b.isDisabled() || m.f3596g <= -1) {
                return true;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.W1(0, 0, m.f3596g);
            mVar.l();
            mVar.f3603e.S(0, 0);
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.e();
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.i(0);
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class d extends InputListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.i(1);
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class e extends InputListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.i(2);
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class f extends InputListener {
        f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.i(3);
            return true;
        }
    }

    /* JADX INFO: compiled from: QuickSlotWindow.java */
    final class g extends InputListener {
        g() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.this.i(4);
            return true;
        }
    }

    public static void f() {
        if (f3598i != null) {
            f3598i = null;
        }
    }

    public static m g() {
        if (f3598i == null) {
            m mVar = new m("", Assets.g());
            mVar.f3599a = new n0.t(GameString.b("EXIT", false), Assets.g(), "menuButton");
            mVar.f3600b = new n0.t(GameString.b("CLEAR", false), Assets.g(), "menuButton");
            Label label = new Label(GameString.b("CHOOSE_SLOT_BUTTON", false), Assets.g(), "menuLabelStrongLargeStyle");
            Label label2 = new Label("", GameAssets.S);
            mVar.f3601c = label2;
            mVar.f3602d = new i[5];
            mVar.setVisible(false);
            mVar.setBackground(Assets.g().getDrawable("windowbg"));
            mVar.setMovable(false);
            mVar.setModal(true);
            mVar.setWidth(f3597h * 425.0f);
            mVar.setHeight(f3597h * 320.0f);
            mVar.setPosition((Gdx.graphics.getWidth() - mVar.getWidth()) / 2.0f, 0.0f);
            mVar.f3604f = f3597h;
            if (ExiledKingdoms.f3378h) {
                f3597h = 0.8f;
                mVar.f3604f = 1.0f;
                mVar.setWidth(340.0f);
                mVar.setHeight(f3597h * 320.0f);
                mVar.setPosition((Gdx.graphics.getWidth() - mVar.getWidth()) / 2.0f, 72.0f);
            }
            f3596g = -1;
            label.setFontScale(mVar.f3604f);
            label2.setFontScale(mVar.f3604f);
            label2.setWrap(true);
            mVar.row().colspan(5);
            mVar.add(label).spaceBottom(f3597h * 18.0f).spaceTop(f3597h * 20.0f);
            mVar.row().width(mVar.getWidth() - (f3597h * 10.0f));
            int i2 = (int) (f3597h * 64.0f);
            for (int i3 = 0; i3 < 5; i3++) {
                mVar.f3602d[i3] = new i();
            }
            float f2 = i2;
            mVar.add(mVar.f3602d[0]).height(f2).width(f2).padLeft(f3597h * 5.0f);
            mVar.add(mVar.f3602d[1]).height(f2).width(f2);
            mVar.add(mVar.f3602d[2]).height(f2).width(f2);
            mVar.add(mVar.f3602d[3]).height(f2).width(f2);
            mVar.add(mVar.f3602d[4]).height(f2).width(f2).padRight(f3597h * 5.0f);
            mVar.row().colspan(5).spaceTop(f3597h * 25.0f).spaceBottom(f3597h * 25.0f).height(f3597h * 40.0f).width(mVar.getWidth() - (f3597h * 10.0f));
            mVar.add(mVar.f3601c).center().align(1).padLeft(f3597h * 18.0f);
            mVar.row().align(4).center().colspan(5).expand();
            Table table = new Table();
            table.add(mVar.f3600b).bottom().top().width(f3597h * 150.0f).height(f3597h * 50.0f).spaceRight(f3597h * 20.0f);
            table.add(mVar.f3599a).bottom().top().width(f3597h * 150.0f).height(f3597h * 50.0f).center();
            mVar.add(table);
            mVar.f3600b.clearListeners();
            mVar.f3600b.addListener(mVar.new a());
            mVar.f3599a.clearListeners();
            mVar.f3599a.addListener(mVar.new b());
            mVar.f3602d[0].addListener(mVar.new c());
            mVar.f3602d[1].addListener(mVar.new d());
            mVar.f3602d[2].addListener(mVar.new e());
            mVar.f3602d[3].addListener(mVar.new f());
            mVar.f3602d[4].addListener(mVar.new g());
            f3598i = mVar;
        }
        return f3598i;
    }

    public static boolean h() {
        return f3596g > -1;
    }

    public void k() {
        f3596g = -1;
        setModal(true);
        l();
        this.f3600b.setDisabled(true);
        this.f3603e.S(0, 0);
        this.f3601c.setText("");
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void l() {
        for (int i2 = 0; i2 < 5; i2++) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            int iI1 = GameData.v().player.I1(i2);
            i[] iVarArr = this.f3602d;
            iVarArr[i2].a(iI1);
            iVarArr[i2].f3540j = false;
        }
    }

    public final void d(int i2, int i3) {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameData.v().player.W1(i2, i3, f3596g);
        k();
    }

    public final void e() {
        f3596g = -1;
        this.f3603e.S(0, 0);
        setVisible(false);
    }

    public final void i(int i2) {
        f3596g = i2;
        int i3 = 0;
        while (true) {
            i[] iVarArr = this.f3602d;
            if (i3 >= 5) {
                iVarArr[i2].f3540j = true;
                a.a.x("CHOOSE_SLOT_SLOT", false, new StringBuilder("[BLACK]"), "[]", this.f3601c);
                o0.f fVar = this.f3603e;
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                fVar.S(GameData.v().player.J1(i2), GameData.v().player.H1(i2));
                this.f3600b.setDisabled(false);
                setModal(false);
                return;
            }
            iVarArr[i3].f3540j = false;
            i3++;
        }
    }

    public final void j(o0.f fVar) {
        this.f3603e = fVar;
        k();
        setVisible(true);
    }
}

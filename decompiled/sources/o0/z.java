package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: TraitsTable.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class z extends Table {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static float f3697i = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static float f3698j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f3699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f3700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f3701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Label f3702d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Label f3703e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Label f3704f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private n0.t f3705g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CharacterSheet f3706h;

    /* JADX INFO: compiled from: TraitsTable.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f3707a;

        a(a0 a0Var) {
            this.f3707a = a0Var;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            m.g().getClass();
            if (m.h()) {
                return false;
            }
            this.f3707a.e(z.this.f3706h, n0.z.v().a());
            return true;
        }
    }

    public z() {
        Label label = new Label(GameString.b("T_STR", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label2 = new Label(GameString.b("T_END", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label3 = new Label(GameString.b("T_AGI", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label4 = new Label(GameString.b("T_INT", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label5 = new Label(GameString.b("T_AWA", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label6 = new Label(GameString.b("T_PER", false), Assets.g(), "menuLabelSubStrongStyle");
        Label label7 = new Label("", GameAssets.f3316a0);
        this.f3699a = label7;
        Label label8 = new Label("", GameAssets.f3316a0);
        this.f3700b = label8;
        Label label9 = new Label("", GameAssets.f3316a0);
        this.f3701c = label9;
        Label label10 = new Label("", GameAssets.f3316a0);
        this.f3702d = label10;
        Label label11 = new Label("", GameAssets.f3316a0);
        this.f3703e = label11;
        Label label12 = new Label("", GameAssets.f3316a0);
        this.f3704f = label12;
        n0.t tVar = new n0.t(GameString.b("TRAITS", false), Assets.g(), "menuSmallButton");
        this.f3705g = tVar;
        f3698j = f3697i;
        if (ExiledKingdoms.f3378h) {
            f3697i = 0.8f;
            f3698j = 1.0f;
        }
        setWidth(f3697i * 150.0f);
        label.setFontScale(f3698j);
        label2.setFontScale(f3698j);
        label3.setFontScale(f3698j);
        label4.setFontScale(f3698j);
        label5.setFontScale(f3698j);
        label6.setFontScale(f3698j);
        label7.setFontScale(f3698j);
        label8.setFontScale(f3698j);
        label9.setFontScale(f3698j);
        label10.setFontScale(f3698j);
        label11.setFontScale(f3698j);
        label12.setFontScale(f3698j);
        tVar.getLabel().setFontScale(f3698j);
        center();
        float f2 = f3697i * 27.0f;
        row().colspan(2);
        add(tVar).width(f3697i * 130.0f).height(f3697i * 38.0f);
        row().spaceTop(f3697i * 5.0f).bottom();
        add(label).height(f2).bottom();
        add(label7).height(f2).bottom();
        row().bottom();
        add(label2).height(f2).bottom();
        add(label8).height(f2).bottom();
        row().bottom();
        add(label3).height(f2).bottom();
        add(label9).height(f2).bottom();
        row().bottom();
        add(label4).height(f2).bottom();
        add(label10).height(f2).bottom();
        row().bottom();
        add(label5).height(f2).bottom();
        add(label11).height(f2).bottom();
        row().bottom();
        add(label6).height(f2).bottom();
        add(label12).height(f2).bottom();
    }

    private String b(int i2) {
        return this.f3706h.W(i2) == -1 ? "[RED]" : this.f3706h.W(i2) == 1 ? "[#B040FF]" : "[BLACK]";
    }

    public final void c(a0 a0Var) {
        n0.t tVar = this.f3705g;
        tVar.clearListeners();
        tVar.d(false);
        tVar.addListener(new a(a0Var));
    }

    public final void d(CharacterSheet characterSheet) {
        this.f3706h = characterSheet;
        this.f3699a.setText(b(0) + this.f3706h.I(0) + "[]");
        this.f3700b.setText(b(1) + this.f3706h.I(1) + "[]");
        this.f3701c.setText(b(2) + this.f3706h.I(2) + "[]");
        this.f3702d.setText(b(3) + this.f3706h.I(3) + "[]");
        this.f3703e.setText(b(4) + this.f3706h.I(4) + "[]");
        this.f3704f.setText(b(5) + this.f3706h.I(5) + "[]");
        String strB = GameString.b("TRAITS", false);
        if (this.f3706h.M() > 0) {
            StringBuilder sbT = a.a.t(strB, " [CYAN](");
            sbT.append(this.f3706h.M());
            sbT.append(")[]");
            strB = sbT.toString();
        }
        this.f3705g.getLabel().setText(strB);
    }
}

package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ChooseGameWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b extends Window {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static float f3816o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3817a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f3818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f3819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private y[] f3820d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ImageButton[] f3821e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private n0.t f3822f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private n0.t f3823g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Stage f3824h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private v f3825i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private n0.g f3826j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f3827k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private a.f f3828l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Color f3829m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Table f3830n;

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3831a;

        a(int i2) {
            this.f3831a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            b.this.f(this.f3831a);
            return true;
        }
    }

    /* JADX INFO: renamed from: q0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class C0053b extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3833a;

        C0053b(int i2) {
            this.f3833a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            b.this.f(this.f3833a);
            return true;
        }
    }

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class c extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3835a;

        c(int i2) {
            this.f3835a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            b.this.a(this.f3835a);
            return true;
        }
    }

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class d extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3837a;

        d(int i2) {
            this.f3837a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            b.this.a(this.f3837a);
            return true;
        }
    }

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class e extends ClickListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            b.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class f extends ClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Window f3840a;

        /* JADX WARN: Multi-variable type inference failed */
        f(r rVar) {
            this.f3840a = (Window) rVar;
        }

        /* JADX WARN: Type inference failed for: r2v4, types: [com.badlogic.gdx.scenes.scene2d.ui.Window, q0.r] */
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            b bVar = b.this;
            if (bVar.f3823g.isDisabled()) {
                return;
            }
            bVar.setVisible(false);
            this.f3840a.show(bVar.f3824h);
        }
    }

    /* JADX INFO: compiled from: ChooseGameWindow.java */
    final class g extends n0.j {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3842c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str, int i2) {
            super(str);
            this.f3842c = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                b bVar = b.this;
                bVar.f3825i.g(this.f3842c, bVar.f3828l, bVar.f3824h);
            }
        }
    }

    public b(v vVar, n0.g gVar, r rVar) {
        super("", Assets.g());
        this.f3817a = 0;
        Label label = new Label(GameString.b("CHOOSE_GAME", false), Assets.g(), "menuLabelStrongVeryLargeStyle");
        this.f3818b = label;
        this.f3819c = new Label(a.a.o("SLOTS_HELP", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f3820d = new y[10];
        this.f3821e = new ImageButton[10];
        this.f3822f = new n0.t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f3823g = new n0.t(GameString.b("BACKUP_BUTTON", false), Assets.g(), "menuButton");
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        this.f3827k = fMin;
        this.f3829m = new Color(1.0f, 0.65f, 0.35f, 1.0f);
        this.f3825i = vVar;
        this.f3826j = gVar;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(990.0f * fMin);
        setHeight(710.0f * fMin);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3816o = fMin;
        if (ExiledKingdoms.f3378h) {
            this.f3827k = 0.8f;
            f3816o = 1.0f;
            setWidth(792.0f);
            setHeight(568.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        this.f3819c.setFontScale(f3816o);
        this.f3819c.setWrap(true);
        label.setFontScale(f3816o);
        center();
        row().height(this.f3827k * 25.0f).space(this.f3827k * 2.0f).colspan(4);
        add(label).center().spaceBottom(this.f3827k * 10.0f).spaceTop(this.f3827k * 10.0f);
        for (int i2 = 0; i2 < 10; i2 += 2) {
            int i3 = i2 + 1;
            this.f3820d[i2] = new y(i2);
            this.f3820d[i3] = new y(i3);
            this.f3821e[i2] = new n0.v(GameAssets.a(Assets.f("folder")), this.f3829m, false);
            this.f3821e[i3] = new n0.v(GameAssets.a(Assets.f("folder")), this.f3829m, false);
            row().height(this.f3827k * 85.0f).spaceBottom(this.f3827k * 10.0f);
            add(this.f3820d[i2]).width(this.f3827k * 380.0f);
            add(this.f3821e[i2]).width(this.f3827k * 80.0f).spaceRight(this.f3827k * 20.0f).spaceLeft(this.f3827k * 4.0f);
            add(this.f3820d[i3]).width(this.f3827k * 380.0f);
            add(this.f3821e[i3]).width(this.f3827k * 80.0f).spaceLeft(this.f3827k * 4.0f);
            this.f3820d[i2].addListener(new a(i2));
            this.f3820d[i3].addListener(new C0053b(i3));
            this.f3821e[i2].addListener(new c(i2));
            this.f3821e[i3].addListener(new d(i3));
        }
        Table table = new Table();
        this.f3830n = table;
        table.left().top();
        Cell cellAdd = this.f3830n.add(new Image(Assets.a("help")));
        float f2 = this.f3827k;
        float f3 = 45.0f * f2;
        cellAdd.width(f3).height(f3).space(10.0f * f2).left().top();
        this.f3830n.add(this.f3819c).left().top().width(f2 * 840.0f);
        row().colspan(4);
        add(this.f3830n).bottom().height(this.f3827k * 80.0f);
        row().colspan(4);
        Table table2 = new Table();
        int i4 = ExiledKingdoms.f3378h ? 320 : 450;
        n0.t tVar = this.f3823g;
        Cell cellBottom = table2.add(tVar).bottom();
        float f4 = this.f3827k;
        float f5 = f4 * 55.0f;
        float f6 = 40.0f * f4;
        cellBottom.width(i4 * f4).height(f5).space(f6);
        n0.t tVar2 = this.f3822f;
        table2.add(tVar2).bottom().width(f4 * 320.0f).height(f5).space(f6);
        if (ExiledKingdoms.f3378h) {
            tVar.getLabel().setFontScale(0.99f);
            tVar2.getLabel().setFontScale(0.99f);
        }
        add(table2).bottom().left().height(this.f3827k * 55.0f).width(this.f3827k * 920.0f).space(this.f3827k * 5.0f);
        this.f3822f.addListener(new e());
        this.f3823g.addListener(new f(rVar));
        setVisible(false);
    }

    protected final void a(int i2) {
        if (this.f3817a == 1 || this.f3821e[i2].isDisabled()) {
            return;
        }
        this.f3826j.i(2, i2, false);
        setVisible(false);
    }

    protected final void f(int i2) {
        if (this.f3820d[i2].isDisabled()) {
            return;
        }
        int i3 = this.f3817a;
        if (i3 == 1) {
            if (Serializer.j(i2)) {
                new g(GameString.b("CONFIRMATION_OVERWRITE_GAME", false), i2).show(this.f3824h);
            } else {
                this.f3825i.g(i2, this.f3828l, this.f3824h);
            }
        } else if (i3 == 0) {
            l0.e.p(i2);
        }
        setVisible(false);
    }

    public final void g(int i2, a.f fVar, Stage stage) {
        this.f3818b.setText(GameString.b("CHOOSE_GAME", false));
        this.f3822f.setText(GameString.b("EXIT", false));
        n0.t tVar = this.f3823g;
        tVar.setText(GameString.b("BACKUP_BUTTON", false));
        if (ExiledKingdoms.f3378h) {
            tVar.setText(GameString.b("IMPORT_EXPORT", false));
        }
        this.f3819c = new Label(a.a.o("SLOTS_HELP", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        GameData.v();
        GameData.K(null);
        this.f3817a = i2;
        this.f3824h = stage;
        this.f3828l = fVar;
        ImageButton[] imageButtonArr = this.f3821e;
        y[] yVarArr = this.f3820d;
        if (i2 == 1) {
            this.f3830n.setVisible(false);
            for (int i3 = 0; i3 < 10; i3++) {
                yVarArr[i3].f(Serializer.o(i3));
                yVarArr[i3].setDisabled(false);
                imageButtonArr[i3].setDisabled(false);
            }
        }
        if (i2 == 0) {
            this.f3830n.setVisible(true);
            for (int i4 = 0; i4 < 10; i4++) {
                yVarArr[i4].f(Serializer.o(i4));
                if (Serializer.j(i4)) {
                    imageButtonArr[i4].setDisabled(false);
                } else {
                    imageButtonArr[i4].setDisabled(true);
                }
            }
        }
        GameData.K(null);
        setVisible(true);
    }
}

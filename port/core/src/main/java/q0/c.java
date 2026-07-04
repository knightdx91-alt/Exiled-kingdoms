package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import n0.k1;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: ChooseLanguageDialog.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c extends Window {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.t f3844a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Label f3845b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Table f3846c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3847d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    s[] f3848e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Stage f3849f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3850g;

    /* JADX INFO: compiled from: ChooseLanguageDialog.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3851a;

        /* JADX INFO: renamed from: q0.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ChooseLanguageDialog.java */
        final class C0054a extends n0.j {

            /* JADX INFO: renamed from: q0.c$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: ChooseLanguageDialog.java */
            final class C0055a extends k1 {
                C0055a(String str) {
                    super(str, 0);
                }

                @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
                protected final void result(Object obj) {
                    if (((Boolean) obj).booleanValue()) {
                        Settings.B(a.this.f3851a + 1);
                        l0.e.d();
                    }
                    GameLevel.n(false);
                }
            }

            C0054a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    a aVar = a.this;
                    c.d(c.this);
                    Settings.A(0, "GL_incompletelang_warning");
                    Settings.v();
                    new C0055a(GameString.b("WARNING_LANG_CHANGED_RESTART", false)).show(c.this.f3849f);
                }
                GameLevel.n(false);
            }
        }

        /* JADX INFO: compiled from: ChooseLanguageDialog.java */
        final class b extends k1 {
            b(String str) {
                super(str, 0);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    Settings.B(a.this.f3851a + 1);
                    l0.e.d();
                }
                GameLevel.n(false);
            }
        }

        a(int i2) {
            this.f3851a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            int i4 = this.f3851a + 1;
            boolean z2 = i4 == 1 || i4 == 2 || i4 == 4 || i4 == 3 || i4 == 5 || i4 == 9 || i4 == 6;
            c cVar = c.this;
            cVar.f3850g = z2;
            if (!cVar.f3850g) {
                new C0054a(GameString.b("WARNING_LANG_INCOMPLETE", false)).show(cVar.f3849f);
            }
            if (cVar.f3850g) {
                c.d(cVar);
                new b(GameString.b("WARNING_LANG_CHANGED_RESTART", false)).show(cVar.f3849f);
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: ChooseLanguageDialog.java */
    final class b extends ClickListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            c.this.setVisible(false);
        }
    }

    public c() {
        super("", Assets.g());
        this.f3844a = new n0.t(GameString.b("CANCEL", false), Assets.g(), "menuButton");
        Label label = new Label(a.a.o("WARNING_LANG_CHANGED_SLEEP", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f3845b = label;
        this.f3847d = Gdx.graphics.getHeight() / 720.0f;
        s[] sVarArr = new s[9];
        this.f3848e = sVarArr;
        if (ExiledKingdoms.f3378h) {
            this.f3847d = 1.0f;
        }
        clear();
        setVisible(false);
        label.setFontScale(this.f3847d);
        label.setWrap(true);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(this.f3847d * 620.0f);
        setHeight(this.f3847d * 500.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        sVarArr[0] = new s(1);
        sVarArr[1] = new s(2);
        sVarArr[2] = new s(3);
        sVarArr[3] = new s(4);
        sVarArr[4] = new s(5);
        sVarArr[5] = new s(6);
        sVarArr[6] = new s(7);
        sVarArr[7] = new s(8);
        sVarArr[8] = new s(9);
        for (int i2 = 0; i2 < 9; i2++) {
            this.f3848e[i2].addListener(new a(i2));
        }
        center();
        Table table = new Table();
        Cell cellRow = table.row();
        float f2 = this.f3847d;
        float f3 = f2 * 10.0f;
        cellRow.pad(f3);
        s[] sVarArr2 = this.f3848e;
        float f4 = 64.0f * f2;
        float f5 = f2 * 25.0f;
        table.add(sVarArr2[0]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[1]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[2]).width(f4).height(f4).spaceLeft(f5);
        table.row().pad(f3);
        table.add(sVarArr2[3]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[4]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[5]).width(f4).height(f4).spaceLeft(f5);
        table.row().pad(f3);
        table.add(sVarArr2[8]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[6]).width(f4).height(f4).spaceLeft(f5);
        table.add(sVarArr2[7]).width(f4).height(f4).spaceLeft(f5);
        add(table).center().width(getWidth() * 0.9f);
        Table table2 = new Table();
        this.f3846c = table2;
        table2.left().top();
        Cell cellAdd = this.f3846c.add(new Image(Assets.a("help")));
        float f6 = this.f3847d;
        float f7 = 45.0f * f6;
        cellAdd.width(f7).height(f7).space(10.0f * f6).left().top();
        this.f3846c.add(this.f3845b).left().top().width(f6 * 540.0f);
        row().colspan(4);
        add(this.f3846c).bottom().height(this.f3847d * 80.0f);
        row().bottom().spaceTop(this.f3847d * 55.0f);
        add(this.f3844a).width(this.f3847d * 160.0f).center();
        this.f3844a.clearListeners();
        this.f3844a.addListener(new b());
    }

    static void d(c cVar) {
        cVar.f3844a.setText(GameString.b("CANCEL", false));
        a.a.x("WARNING_LANG_CHANGED_SLEEP", false, new StringBuilder("[BLACK]"), "[]", cVar.f3845b);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f3847d * 300.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f3847d * 560.0f;
    }

    public final void show(Stage stage) {
        setVisible(true);
        this.f3849f = stage;
    }
}

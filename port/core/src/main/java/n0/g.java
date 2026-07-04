package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ChooseSaveWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class g extends Window {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f2655j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static float f2656k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Label f2657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    TextButton f2658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    t[] f2659c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    t f2660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Table f2662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2664h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Stage f2665i;

    /* JADX INFO: compiled from: ChooseSaveWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g.this.g(-1);
        }
    }

    /* JADX INFO: compiled from: ChooseSaveWindow.java */
    final class b extends ChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2667a;

        b(int i2) {
            this.f2667a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g.b(g.this, this.f2667a);
        }
    }

    /* JADX INFO: compiled from: ChooseSaveWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            g.this.e();
        }
    }

    /* JADX INFO: compiled from: ChooseSaveWindow.java */
    final class d extends j {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2670c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, int i2) {
            super(str);
            this.f2670c = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                GameLevel.n(false);
                g gVar = g.this;
                Serializer.a(gVar.f2664h, this.f2670c + 1);
                gVar.setVisible(false);
            }
        }
    }

    public g(Stage stage) {
        super("", Assets.g());
        Label label = new Label("", Assets.g(), "menuLabelStrongStyle");
        this.f2657a = label;
        this.f2658b = new t("", Assets.g(), "menuSmallButton");
        this.f2659c = new t[8];
        this.f2660d = new t(GameString.b("CANCEL", false), Assets.g(), "menuSmallButton");
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        this.f2661e = fMin;
        this.f2663g = 0;
        this.f2664h = 0;
        this.f2665i = stage;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(670.0f * fMin);
        setHeight(720.0f * fMin);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f2656k = fMin;
        if (ExiledKingdoms.f3378h) {
            this.f2661e = 0.8f;
            f2656k = 1.0f;
            setWidth(536.0f);
            setHeight(664.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        setVisible(false);
        label.setFontScale(f2656k);
        row();
        add(label).height(this.f2661e * 40.0f).top();
        Table table = new Table();
        this.f2662f = table;
        row();
        add(table);
        TextButton textButton = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2658b = textButton;
        textButton.setColor(0.8f, 1.0f, 0.7f, 1.0f);
        textButton.getLabel().setFontScale(f2656k);
        textButton.getLabel().setWrap(true);
        textButton.getLabel().setAlignment(8);
        textButton.addListener(new a());
        for (int i2 = 0; i2 < 8; i2++) {
            this.f2659c[i2] = new t("", Assets.g(), "menuSmallButton");
            this.f2659c[i2].getLabel().setFontScale(f2656k);
            this.f2659c[i2].getLabel().setWrap(true);
            this.f2659c[i2].getLabel().setAlignment(8);
            this.f2659c[i2].addListener(new b(i2));
        }
        row();
        add(this.f2660d).width(getWidth() * 0.4f).height(this.f2661e * 45.0f).space(this.f2661e * 10.0f).bottom().expandY();
        this.f2660d.setText(GameString.b("CANCEL", false));
        this.f2660d.getLabel().setFontScale(f2656k);
        this.f2660d.addListener(new c());
        f();
    }

    static void b(g gVar, int i2) {
        if (gVar.f2663g == 0) {
            boolean z2 = m0.b.P().f2423h;
            Stage stage = gVar.f2665i;
            if (z2 || m0.b.P().f2426k) {
                t[] tVarArr = gVar.f2659c;
                if (tVarArr[i2].getText().toString().contains("<") && tVarArr[i2].getText().toString().contains(">")) {
                    GameLevel.n(false);
                    Serializer.z(gVar.f2664h, i2 + 1);
                    gVar.setVisible(false);
                    GameConsole.a(GameString.b("GAME_SAVED", false));
                } else {
                    new i(gVar, GameString.b("CONFIRM_OVERWRITE", false), i2).show(stage);
                }
            } else {
                new h(GameString.b("CANT_SAVE_INDOOR", false), 0).show(stage);
            }
        }
        int i3 = gVar.f2663g;
        if (i3 == 1 || i3 == 2) {
            gVar.g(i2);
        }
    }

    static void d(g gVar, int i2) {
        gVar.getClass();
        GameLevel.n(false);
        Serializer.z(gVar.f2664h, i2 + 1);
        gVar.setVisible(false);
        GameConsole.a(GameString.b("GAME_SAVED", false));
    }

    public void f() {
        char c2;
        Table table = this.f2662f;
        table.clear();
        int i2 = this.f2663g;
        float f2 = this.f2661e;
        TextButton textButton = this.f2658b;
        float f3 = 5.0f;
        float f4 = 70.0f;
        if (i2 == 2) {
            textButton.setText(h(0) + Serializer.k(this.f2664h, 0));
            table.row();
            table.add(textButton).width(getWidth() * 0.9f).height(f2 * 70.0f).space(f2 * 5.0f);
            textButton.setVisible(true);
            if (this.f2663g != 0 && textButton.getText().toString().contains("<") && textButton.getText().toString().contains(">")) {
                textButton.setDisabled(true);
            } else {
                textButton.setDisabled(false);
            }
        } else {
            textButton.setVisible(false);
        }
        int i3 = 0;
        while (i3 < 6) {
            t[] tVarArr = this.f2659c;
            t tVar = tVarArr[i3];
            StringBuilder sb = new StringBuilder();
            int i4 = i3 + 1;
            sb.append(h(i4));
            sb.append(Serializer.k(this.f2664h, i4));
            tVar.setText(sb.toString());
            if (this.f2663g != 0 && tVarArr[i3].getText().toString().contains("<") && tVarArr[i3].getText().toString().contains(">")) {
                tVarArr[i3].setDisabled(true);
            } else {
                tVarArr[i3].setDisabled(false);
            }
            table.row();
            float f5 = f2 * f4;
            float f6 = f2 * f3;
            table.add(tVarArr[i3]).width(getWidth() * 0.9f).height(f5).space(f6);
            if (i3 == 0) {
                tVarArr[6].setText(h(7) + Serializer.k(this.f2664h, 7));
                if (this.f2663g != 0) {
                    c2 = 6;
                    if (tVarArr[6].getText().toString().contains("<") && tVarArr[6].getText().toString().contains(">")) {
                        tVarArr[6].setDisabled(true);
                    }
                    table.row();
                    table.add(tVarArr[c2]).width(getWidth() * 0.9f).height(f5).space(f6);
                } else {
                    c2 = 6;
                }
                tVarArr[c2].setDisabled(false);
                table.row();
                table.add(tVarArr[c2]).width(getWidth() * 0.9f).height(f5).space(f6);
            }
            i3 = i4;
            f3 = 5.0f;
            f4 = 70.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void g(int i2) {
        if (this.f2663g == 1) {
            if (f2655j) {
                GameLevel.n(false);
                Serializer.a(this.f2664h, i2 + 1);
                setVisible(false);
            } else {
                new d(GameString.b("CONFIRM_RELOAD", false), i2).show(this.f2665i);
            }
        }
        if (this.f2663g == 2) {
            GameLevel.n(false);
            Serializer.a(this.f2664h, i2 + 1);
            setVisible(false);
        }
    }

    public static String h(int i2) {
        if (i2 == 0) {
            return a.a.o("CONTINUE_PLAYING", false, new StringBuilder("[BLUE]"), ":[] ");
        }
        if (i2 == 1) {
            return a.a.o("AUTOSAVE", false, new StringBuilder("   [BLUE]0.("), ")[] ");
        }
        if (i2 == 2) {
            return a.a.o("QUICKSAVE", false, new StringBuilder("   [BLUE]2.("), ")[] ");
        }
        if (i2 == 7) {
            StringBuilder sb = new StringBuilder("   [BLUE]1.(");
            a.a.w("AREA", false, sb, " ");
            return a.a.o("AUTOSAVE", false, sb, ")[] ");
        }
        return "   [BLUE]" + i2 + ". [] ";
    }

    public final void e() {
        if (!f2655j) {
            setVisible(false);
            GameLevel.n(false);
            return;
        }
        setVisible(false);
        com.badlogic.gdx.Game eVar = (com.badlogic.gdx.Game) Gdx.app.getApplicationListener();
        GameData.v().getClass();
        GameData.X();
        GameConsole.d();
        eVar.setScreen(new l0.e(eVar));
    }

    public final void i(int i2, int i3, boolean z2) {
        this.f2663g = i2;
        this.f2664h = i3;
        setVisible(true);
        int i4 = this.f2663g;
        Label label = this.f2657a;
        if (i4 == 0) {
            label.setText(GameString.b("CHOOSE_SAVE_SLOT", false));
        } else {
            label.setText(GameString.b("CHOOSE_LOAD_SLOT", false));
        }
        f2655j = z2;
        t tVar = this.f2660d;
        t[] tVarArr = this.f2659c;
        if (!z2) {
            f();
            if (this.f2663g == 0) {
                tVarArr[0].setDisabled(true);
            } else {
                tVarArr[0].setDisabled(false);
            }
            if (this.f2663g == 0) {
                tVarArr[6].setDisabled(true);
            }
            tVar.setText(GameString.b("CANCEL", false));
            return;
        }
        int i5 = this.f2663g;
        TextButton textButton = this.f2658b;
        if (i5 == 2) {
            textButton.setVisible(true);
            if (this.f2663g != 0 && textButton.getText().toString().contains("<") && textButton.getText().toString().contains(">")) {
                textButton.setDisabled(true);
            } else {
                textButton.setDisabled(false);
            }
        } else {
            textButton.setVisible(false);
        }
        int i6 = 0;
        while (i6 < 6) {
            t tVar2 = tVarArr[i6];
            StringBuilder sb = new StringBuilder();
            int i7 = i6 + 1;
            sb.append(h(i7));
            sb.append(Serializer.k(this.f2664h, i7));
            tVar2.setText(sb.toString());
            if (this.f2663g != 0 && tVarArr[i6].getText().toString().contains("<") && tVarArr[i6].getText().toString().contains(">")) {
                tVarArr[i6].setDisabled(true);
            } else {
                tVarArr[i6].setDisabled(false);
            }
            if (i6 == 0) {
                tVarArr[6].setText(h(7) + Serializer.k(this.f2664h, 7));
                if (this.f2663g != 0 && tVarArr[6].getText().toString().contains("<") && tVarArr[6].getText().toString().contains(">")) {
                    tVarArr[6].setDisabled(true);
                } else {
                    tVarArr[6].setDisabled(false);
                }
            }
            i6 = i7;
        }
        tVar.setText(GameString.b("EXIT", false));
        GameLevel.n(true);
    }
}

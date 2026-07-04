package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: SettingsWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class j1 extends Window {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static float f2725v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f2726a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private q0.c f2727b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f2728c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    q0.s f2729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CheckBox f2730e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f2731f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private CheckBox f2732g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CheckBox f2733h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private CheckBox f2734i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Label f2735j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Label f2736k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Label f2737l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Label f2738m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Slider f2739n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Slider f2740o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Slider f2741p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Slider f2742q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Slider f2743r;
    float s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Stage f2744t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private q0.h f2745u;

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: n0.j1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SettingsWindow.java */
        final class C0038a extends k1 {
        }

        /* JADX INFO: compiled from: SettingsWindow.java */
        final class b extends k1 {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    Settings.I(!Settings.n());
                    l0.e.d();
                }
                GameLevel.n(false);
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1 j1Var = j1.this;
            if (j1Var.f2726a) {
                j1Var.n();
                j1Var.f2733h.setChecked(!j1Var.f2733h.isChecked());
                new b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(j1Var.f2744t);
            } else {
                new C0038a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(j1Var.f2744t);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class b extends InputListener {

        /* JADX INFO: compiled from: SettingsWindow.java */
        final class a extends k1 {
        }

        /* JADX INFO: renamed from: n0.j1$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SettingsWindow.java */
        final class C0039b extends k1 {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    Settings.I(!Settings.n());
                    l0.e.d();
                }
                GameLevel.n(false);
            }
        }

        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1 j1Var = j1.this;
            if (j1Var.f2726a) {
                j1Var.n();
                j1Var.f2733h.setChecked(!j1Var.f2733h.isChecked());
                new C0039b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(j1Var.f2744t);
            } else {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(j1Var.f2744t);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1.this.f2734i.setChecked(!r1.f2734i.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: compiled from: SettingsWindow.java */
        final class a extends k1 {
        }

        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            j1 j1Var = j1.this;
            if (!j1Var.f2726a) {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(j1Var.f2744t);
                return;
            }
            j1Var.n();
            if (j1Var.f2727b != null) {
                j1Var.f2727b.show(j1Var.f2744t);
            }
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class e extends ChangeListener {

        /* JADX INFO: compiled from: SettingsWindow.java */
        final class a extends k1 {
        }

        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            j1 j1Var = j1.this;
            if (!j1Var.f2726a || j1Var.f2745u == null) {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(j1Var.f2744t);
            } else {
                j1Var.n();
                j1Var.f2745u.d();
            }
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class f extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class g extends ChangeListener {
        g() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            Settings.L(j1.this.f2741p.getValue());
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class h extends ChangeListener {
        h() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            Settings.J(j1.this.f2742q.getValue());
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class i extends ChangeListener {
        i() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            Settings.K(j1.this.f2743r.getValue());
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class j extends ChangeListener {
        j() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            j1 j1Var = j1.this;
            boolean z2 = j1Var.f2739n.getValue() != Settings.m();
            Settings.H(j1Var.f2739n.getValue());
            if (z2) {
                GameAssets.o("sword");
            }
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class k extends ChangeListener {
        k() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            Settings.C(j1.this.f2740o.getValue());
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class l extends ClickListener {
        l() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            j1.this.n();
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class m extends InputListener {
        m() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1.this.f2730e.setChecked(!r1.f2730e.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class n extends InputListener {
        n() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1.this.f2731f.setChecked(!r1.f2731f.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsWindow.java */
    final class o extends InputListener {
        o() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            j1.this.f2732g.setChecked(!r1.f2732g.isChecked());
            return false;
        }
    }

    public j1(q0.h hVar) {
        t tVar;
        boolean z2;
        Label label;
        super("", Assets.g());
        this.f2726a = false;
        t tVar2 = new t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f2728c = tVar2;
        t tVar3 = new t(GameString.b("CONTROLLER_SETUP", false), Assets.g(), "menuSmallButton");
        q0.s sVar = new q0.s(Settings.h(), 0);
        this.f2729d = sVar;
        ImageButton imageButton = new ImageButton(GameAssets.a(Assets.f("settings")));
        CheckBox checkBox = new CheckBox("", GameAssets.f3350r0);
        this.f2730e = checkBox;
        CheckBox checkBox2 = new CheckBox("", GameAssets.f3350r0);
        this.f2731f = checkBox2;
        CheckBox checkBox3 = new CheckBox("", GameAssets.f3350r0);
        this.f2732g = checkBox3;
        CheckBox checkBox4 = new CheckBox("", GameAssets.f3350r0);
        this.f2733h = checkBox4;
        CheckBox checkBox5 = new CheckBox("", GameAssets.f3350r0);
        this.f2734i = checkBox5;
        Label label2 = new Label(GameString.b("LANGUAGE", false), Assets.g(), "menuLabelStrongStyle");
        this.f2735j = label2;
        Label label3 = new Label(GameString.b("SOUND_VOLUME", false), Assets.g(), "menuLabelStrongStyle");
        this.f2736k = label3;
        Label label4 = new Label(GameString.b("MUSIC_VOLUME", false), Assets.g(), "menuLabelStrongStyle");
        this.f2737l = label4;
        Label label5 = new Label(GameString.b("CONTROLS_SIZE", false), Assets.g(), "menuLabelStrongStyle");
        this.f2738m = label5;
        Label label6 = new Label(GameString.b("SETTINGS_UI_LOWER", false), Assets.g(), "menuLabelStrongStyle");
        Label label7 = new Label(GameString.b("SETTINGS_UI_SIDES", false), Assets.g(), "menuLabelStrongStyle");
        Label label8 = new Label(GameString.b("LOG_COMBAT", false), Assets.g(), "menuLabelSmallStyle");
        Label label9 = new Label(GameString.b("SHOW_CONTROLS", false), Assets.g(), "menuLabelSmallStyle");
        Label label10 = new Label(GameString.b("ATTACK_INTERACTS", false), Assets.g(), "menuLabelSmallStyle");
        Label label11 = new Label(GameString.b("SETTINGS_OLD_FONTS", false), Assets.g(), "menuLabelSmallStyle");
        Label label12 = new Label(GameString.b("SETTINGS_SHOW_NUMBERS", false), Assets.g(), "menuLabelSmallStyle");
        Slider slider = new Slider(0.0f, 1.0f, 0.2f, false, GameAssets.k());
        this.f2739n = slider;
        Slider slider2 = new Slider(0.0f, 1.0f, 0.2f, false, GameAssets.k());
        this.f2740o = slider2;
        Slider slider3 = new Slider(0.8f, 1.0f, 0.1f, false, GameAssets.k());
        this.f2741p = slider3;
        Slider slider4 = new Slider(1.0f, 3.5f, 0.5f, false, GameAssets.k());
        this.f2742q = slider4;
        Slider slider5 = new Slider(1.0f, 3.5f, 0.5f, false, GameAssets.k());
        this.f2743r = slider5;
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.s = height;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(760.0f * height);
        setHeight(height * 720.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        this.f2745u = hVar;
        f2725v = height;
        if (ExiledKingdoms.f3378h) {
            this.s = 0.8f;
            f2725v = 1.0f;
            setWidth(608.0f);
            setHeight(576.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        this.f2726a = true;
        label2.setFontScale(f2725v);
        label3.setFontScale(f2725v);
        label4.setFontScale(f2725v);
        label8.setFontScale(f2725v);
        label8.setWrap(true);
        label9.setFontScale(f2725v);
        label9.setWrap(true);
        label5.setFontScale(f2725v);
        label6.setFontScale(f2725v);
        label7.setFontScale(f2725v);
        label10.setFontScale(f2725v);
        label10.setWrap(true);
        label11.setFontScale(f2725v);
        label11.setWrap(true);
        label12.setFontScale(f2725v);
        label12.setWrap(true);
        center();
        top();
        pad(this.s * 10.0f);
        padTop(this.s * 25.0f);
        row().spaceBottom(this.s * 10.0f).center();
        imageButton.setColor(new Color(Color.ORANGE));
        Table table = new Table();
        float f2 = this.s;
        table.setWidth(f2 * 320.0f);
        table.center();
        table.row().colspan(2).left();
        table.add(label2).center().height(f2 * 35.0f);
        float f3 = 64.0f * f2;
        table.add(sVar).width(f3).height(f3);
        Cell cellSpaceLeft = table.add(imageButton).spaceLeft(f2 * 10.0f);
        float f4 = f2 * 45.0f;
        cellSpaceLeft.width(f4).height(f4);
        add(table).center().width(this.s * 390.0f);
        tVar3.getLabel().setFontScale(f2725v);
        if (ExiledKingdoms.f3388r) {
            tVar = tVar3;
        } else {
            tVar = tVar3;
            add(tVar).spaceLeft(this.s * 40.0f).width(this.s * 300.0f).height(this.s * 45.0f);
        }
        row().left().spaceTop(this.s * 30.0f);
        add(label5).center();
        add(slider3).width(this.s * 230.0f).height(this.s * 30.0f).spaceLeft(this.s * 20.0f);
        row().left().spaceTop(this.s * 30.0f);
        add(label6).center();
        add(slider4).width(this.s * 230.0f).height(this.s * 30.0f).spaceLeft(this.s * 20.0f);
        row().left().spaceTop(this.s * 30.0f);
        add(label7).center();
        add(slider5).width(this.s * 230.0f).height(this.s * 30.0f).spaceLeft(this.s * 20.0f);
        row().left().spaceTop(this.s * 50.0f);
        add(label3).center();
        add(slider).width(this.s * 230.0f).height(this.s * 30.0f).spaceLeft(this.s * 20.0f);
        slider.getStyle().knob.setMinHeight(this.s * 45.0f);
        slider.getStyle().knob.setMinWidth(this.s * 20.0f);
        row().left().spaceTop(this.s * 35.0f);
        add(label4).center();
        add(slider2).width(this.s * 230.0f).height(this.s * 30.0f).spaceLeft(this.s * 20.0f);
        Table table2 = new Table();
        row().spaceTop(this.s * 25.0f);
        float f5 = this.s * 310.0f;
        table2.clear();
        t tVar4 = tVar;
        table2.add(checkBox).right().spaceRight(this.s * 10.0f);
        table2.add(label8).left().align(8).width(f5);
        add(table2).left();
        Table table3 = new Table();
        table3.add(checkBox4).right().spaceRight(this.s * 10.0f);
        table3.add(label11).left().align(8).width(f5);
        add(table3).left();
        Cell cell = checkBox.getCells().get(0);
        float f6 = this.s * 20.0f;
        cell.size(f6, f6);
        Cell cell2 = checkBox4.getCells().get(0);
        float f7 = this.s * 20.0f;
        cell2.size(f7, f7);
        if (Settings.o()) {
            z2 = true;
            checkBox.setChecked(true);
        } else {
            z2 = true;
        }
        if (Settings.n()) {
            checkBox4.setChecked(z2);
        }
        row().spaceTop(this.s * 16.0f);
        Table table4 = new Table();
        table4.add(checkBox3).right().spaceRight(this.s * 10.0f);
        table4.add(label10).left().align(8).width(f5);
        add(table4).left();
        Table table5 = new Table();
        table5.add(checkBox5).right().spaceRight(this.s * 10.0f);
        table5.add(label12).left().align(8).width(f5);
        add(table5).left();
        Cell cell3 = checkBox3.getCells().get(0);
        float f8 = this.s * 20.0f;
        cell3.size(f8, f8);
        Cell cell4 = checkBox5.getCells().get(0);
        float f9 = this.s * 20.0f;
        cell4.size(f9, f9);
        if (Settings.b()) {
            checkBox3.setChecked(true);
        }
        if (ExiledKingdoms.f3388r) {
            label = label9;
        } else {
            row().spaceTop(this.s * 16.0f);
            Table table6 = new Table();
            table6.add(checkBox2).right().spaceRight(this.s * 10.0f);
            label = label9;
            table6.add(label).left().align(8).width(f5);
            add(table6).left();
            add(new Label("", GameAssets.f3316a0));
            Cell cell5 = checkBox2.getCells().get(0);
            float f10 = this.s * 20.0f;
            cell5.size(f10, f10);
            if (Settings.k()) {
                checkBox2.setChecked(true);
            }
        }
        row().colspan(2).center().spaceTop(this.s * 16.0f);
        add(tVar2).center().width(this.s * 160.0f).height(this.s * 38.0f);
        slider3.addListener(new g());
        slider4.addListener(new h());
        slider5.addListener(new i());
        slider.addListener(new j());
        slider2.addListener(new k());
        tVar2.addListener(new l());
        label8.clearListeners();
        label8.addListener(new m());
        label.clearListeners();
        label.addListener(new n());
        label10.clearListeners();
        label10.addListener(new o());
        checkBox4.clearListeners();
        checkBox4.addListener(new a());
        label11.clearListeners();
        label11.addListener(new b());
        label12.clearListeners();
        label12.addListener(new c());
        imageButton.addListener(new d());
        tVar4.addListener(new e());
        checkBox.addListener(new f());
        setVisible(false);
    }

    public final void n() {
        this.f2729d.a(Settings.h());
        Settings.x(this.f2730e.isChecked());
        Settings.F(this.f2731f.isChecked());
        Settings.w(this.f2732g.isChecked());
        Settings.I(this.f2733h.isChecked());
        Settings.G(this.f2734i.isChecked());
        this.f2728c.setText(GameString.b("EXIT", false));
        this.f2735j.setText(GameString.b("LANGUAGE", false));
        this.f2736k.setText(GameString.b("SOUND_VOLUME", false));
        this.f2737l.setText(GameString.b("MUSIC_VOLUME", false));
        this.f2738m.setText(GameString.b("CONTROLS_SIZE", false));
        l0.e.u();
        setVisible(false);
        if (ExiledKingdoms.f3382l == GameData.GameStatus.f3184a) {
            GameLevel.n(false);
        }
    }

    public final void o(q0.c cVar) {
        this.f2727b = cVar;
    }

    public final void p() {
        this.f2726a = true;
    }

    public final void q(Stage stage, boolean z2) {
        setVisible(true);
        this.f2726a = z2;
        this.f2744t = stage;
        this.f2729d.a(Settings.h());
        this.f2739n.setValue(Settings.m());
        this.f2740o.setValue(Settings.i());
        this.f2741p.setValue(Settings.r());
        this.f2742q.setValue(Settings.p());
        this.f2743r.setValue(Settings.q());
        if (Settings.o()) {
            this.f2730e.setChecked(true);
        }
        if (Settings.k()) {
            this.f2731f.setChecked(true);
        }
        if (Settings.b()) {
            this.f2732g.setChecked(true);
        }
        if (Settings.n()) {
            this.f2733h.setChecked(true);
        }
        if (Settings.l()) {
            this.f2734i.setChecked(true);
        }
    }
}

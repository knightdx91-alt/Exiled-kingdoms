package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Properties;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: SettingsDesktopWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class i1 extends Window {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static float f2688u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f2689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private q0.c f2690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f2691c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t f2692d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    q0.s f2693e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f2694f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private CheckBox f2695g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CheckBox f2696h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private CheckBox f2697i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CheckBox f2698j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Label f2699k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Label f2700l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Label f2701m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Slider f2702n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Slider f2703o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    float f2704p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Stage f2705q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private q0.h f2706r;
    private c1 s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    protected q0.e f2707t;

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: n0.i1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class C0036a extends q0.e {
            C0036a() {
                super("", Assets.g());
                new t(GameString.b("OK", false), Assets.g(), "menuButton");
                t tVar = new t(a.a.o("CANCEL", false, new StringBuilder("  "), "  "), Assets.g(), "menuSmallButton");
                Label label = new Label(GameString.b("RES_CHANGE", false), Assets.g(), "menuLabelStrongStyle");
                setBackground(Assets.g().getDrawable("windowbg"));
                setMovable(false);
                setModal(true);
                setWidth(192.0f);
                setHeight(392.0f);
                setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
                t tVar2 = new t("[WHITE]1280 x 720[]", Assets.g(), "menuSmallButton");
                t tVar3 = new t("[WHITE]1360 x 768[]", Assets.g(), "menuSmallButton");
                t tVar4 = new t("[WHITE]1366 x 768[]", Assets.g(), "menuSmallButton");
                t tVar5 = new t("[WHITE]1280 x 800[]", Assets.g(), "menuSmallButton");
                t tVar6 = new t("[WHITE]1440 x 900[]", Assets.g(), "menuSmallButton");
                t tVar7 = new t("[WHITE]1600 x 900[]", Assets.g(), "menuSmallButton");
                t tVar8 = new t("[WHITE]1680 x 1050[]", Assets.g(), "menuSmallButton");
                t tVar9 = new t("[WHITE]1920 x 1080[]", Assets.g(), "menuSmallButton");
                label.setWrap(true);
                Properties propertiesW = Serializer.w();
                int i2 = Integer.parseInt(propertiesW.getProperty("height"));
                int i3 = Integer.parseInt(propertiesW.getProperty("width"));
                if (i3 == 1280 && i2 == 720) {
                    tVar2.setColor(Color.ORANGE);
                }
                if (i3 == 1360 && i2 == 768) {
                    tVar3.setColor(Color.ORANGE);
                }
                if (i3 == 1366 && i2 == 768) {
                    tVar4.setColor(Color.ORANGE);
                }
                if (i3 == 1280 && i2 == 800) {
                    tVar5.setColor(Color.ORANGE);
                }
                if (i3 == 1440 && i2 == 900) {
                    tVar6.setColor(Color.ORANGE);
                }
                if (i3 == 1600 && i2 == 900) {
                    tVar7.setColor(Color.ORANGE);
                }
                if (i3 == 1680 && i2 == 1050) {
                    tVar8.setColor(Color.ORANGE);
                }
                if (i3 == 1920 && i2 == 1080) {
                    tVar9.setColor(Color.ORANGE);
                }
                padTop(30.0f).padBottom(30.0f);
                getContentTable().center();
                getContentTable().add(label).width(240.0f).center().align(1).row();
                getButtonTable().defaults().spaceLeft(40.0f).width(90.0f);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar2, (Object) 1);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar5, (Object) 4);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar3, (Object) 2);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar4, (Object) 3);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar6, (Object) 7);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar7, (Object) 8);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar8, (Object) 6);
                getButtonTable().row().height(30.0f).width(140.0f).colspan(2);
                button((Button) tVar9, (Object) 9);
                getButtonTable().row().colspan(2).spaceTop(20.0f).colspan(2);
                button((Button) tVar, (Object) (-2));
                tVar.setWidth(120.0f);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                Integer num = (Integer) obj;
                if (num.intValue() > 0) {
                    int iIntValue = num.intValue();
                    Properties propertiesW = Serializer.w();
                    int i2 = GL20.GL_SRC_COLOR;
                    int i3 = GL20.GL_INVALID_ENUM;
                    switch (iIntValue) {
                        case 0:
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 1:
                        case 5:
                        default:
                            i2 = 720;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 2:
                            i3 = 1360;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 3:
                            i3 = 1366;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 4:
                            i2 = 800;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 6:
                            i3 = 1680;
                            i2 = 1050;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                        case 7:
                            i3 = 1440;
                            break;
                        case 8:
                            i3 = 1600;
                            break;
                        case 9:
                            i3 = 1920;
                            i2 = 1080;
                            propertiesW.setProperty("width", "" + i3);
                            propertiesW.setProperty("height", "" + i2);
                            Serializer.A(propertiesW);
                            i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                    }
                    i2 = 900;
                    propertiesW.setProperty("width", "" + i3);
                    propertiesW.setProperty("height", "" + i2);
                    Serializer.A(propertiesW);
                    i1.this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
                }
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            C0036a c0036a = new C0036a();
            i1 i1Var = i1.this;
            i1Var.f2707t = c0036a;
            i1Var.f2707t.show(i1Var.f2705q);
            return true;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class b extends InputListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        /* JADX INFO: renamed from: n0.i1$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class C0037b extends k1 {
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
            i1 i1Var = i1.this;
            if (i1Var.f2689a) {
                i1Var.m();
                i1Var.f2697i.setChecked(!i1Var.f2697i.isChecked());
                new C0037b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(i1Var.f2705q);
            } else {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1.this.f2698j.setChecked(!r1.f2698j.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            i1 i1Var = i1.this;
            if (!i1Var.f2689a) {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
                return;
            }
            i1Var.m();
            if (i1Var.f2690b != null) {
                i1Var.f2690b.show(i1Var.f2705q);
            }
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class e extends ChangeListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            i1 i1Var = i1.this;
            i1Var.s.f();
            i1Var.s.toFront();
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class f extends ChangeListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            i1 i1Var = i1.this;
            if (!i1Var.f2689a || i1Var.f2706r == null) {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
            } else {
                i1Var.m();
                i1Var.f2706r.d();
            }
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class g extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class h extends ChangeListener {
        h() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            i1 i1Var = i1.this;
            boolean z2 = i1Var.f2702n.getValue() != Settings.m();
            Settings.H(i1Var.f2702n.getValue());
            if (z2) {
                GameAssets.o("sword");
            }
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class i extends ChangeListener {
        i() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            Settings.C(i1.this.f2703o.getValue());
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class j extends ClickListener {
        j() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            i1.this.m();
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class k extends InputListener {
        k() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1.this.f2694f.setChecked(!r1.f2694f.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class l extends InputListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class b extends k1 {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    ControllerConfig controllerConfig = Settings.f3418a;
                    Settings.y(!ExiledKingdoms.f3379i);
                    l0.e.d();
                }
                GameLevel.n(false);
            }
        }

        l() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1 i1Var = i1.this;
            if (i1Var.f2689a) {
                i1Var.m();
                i1Var.f2695g.setChecked(!i1Var.f2695g.isChecked());
                new b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(i1Var.f2705q);
            } else {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class m extends InputListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class b extends k1 {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    ControllerConfig controllerConfig = Settings.f3418a;
                    Settings.y(!ExiledKingdoms.f3379i);
                    l0.e.d();
                }
                GameLevel.n(false);
            }
        }

        m() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1 i1Var = i1.this;
            if (i1Var.f2689a) {
                i1Var.m();
                i1Var.f2695g.setChecked(!i1Var.f2695g.isChecked());
                new b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(i1Var.f2705q);
            } else {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class n extends InputListener {
        n() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1.this.f2696h.setChecked(!r1.f2696h.isChecked());
            return false;
        }
    }

    /* JADX INFO: compiled from: SettingsDesktopWindow.java */
    final class o extends InputListener {

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
        final class a extends k1 {
        }

        /* JADX INFO: compiled from: SettingsDesktopWindow.java */
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

        o() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            i1 i1Var = i1.this;
            if (i1Var.f2689a) {
                i1Var.m();
                i1Var.f2697i.setChecked(!i1Var.f2697i.isChecked());
                new b(GameString.b("WARNING_LANG_CHANGED_RESTART", false), 0).show(i1Var.f2705q);
            } else {
                new a(GameString.b("MSG_CANT_CHANGE_LANGUAGE", false), 0).show(i1Var.f2705q);
            }
            return false;
        }
    }

    public i1(q0.h hVar, c1 c1Var) {
        boolean z2;
        super("", Assets.g());
        this.f2689a = false;
        t tVar = new t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f2691c = tVar;
        t tVar2 = new t(GameString.b("CONTROLLER_SETUP", false), Assets.g(), "menuSmallButton");
        t tVar3 = new t(GameString.b("KEYBOARD_CONFIGURATION", false), Assets.g(), "menuSmallButton");
        t tVar4 = new t(GameString.b("RESOLUTION", false), Assets.g(), "menuSmallButton");
        this.f2692d = tVar4;
        q0.s sVar = new q0.s(Settings.h(), 0);
        this.f2693e = sVar;
        ImageButton imageButton = new ImageButton(GameAssets.a(Assets.f("settings")));
        CheckBox checkBox = new CheckBox("", GameAssets.f3350r0);
        this.f2694f = checkBox;
        CheckBox checkBox2 = new CheckBox("", GameAssets.f3350r0);
        this.f2695g = checkBox2;
        CheckBox checkBox3 = new CheckBox("", GameAssets.f3350r0);
        this.f2696h = checkBox3;
        CheckBox checkBox4 = new CheckBox("", GameAssets.f3350r0);
        this.f2697i = checkBox4;
        CheckBox checkBox5 = new CheckBox("", GameAssets.f3350r0);
        this.f2698j = checkBox5;
        Label label = new Label(GameString.b("LANGUAGE", false), Assets.g(), "menuLabelStrongStyle");
        this.f2699k = label;
        Label label2 = new Label(GameString.b("SOUND_VOLUME", false), Assets.g(), "menuLabelStrongStyle");
        this.f2700l = label2;
        Label label3 = new Label(GameString.b("MUSIC_VOLUME", false), Assets.g(), "menuLabelStrongStyle");
        this.f2701m = label3;
        Label label4 = new Label(GameString.b("LOG_COMBAT", false), Assets.g(), "menuLabelSmallStyle");
        Label label5 = new Label(GameString.b("SETTING_FULLSCREEN", false), Assets.g(), "menuLabelSmallStyle");
        Label label6 = new Label(GameString.b("ATTACK_INTERACTS", false), Assets.g(), "menuLabelSmallStyle");
        Label label7 = new Label(GameString.b("SETTINGS_OLD_FONTS", false), Assets.g(), "menuLabelSmallStyle");
        Label label8 = new Label(GameString.b("SETTINGS_SHOW_NUMBERS", false), Assets.g(), "menuLabelSmallStyle");
        Slider slider = new Slider(0.0f, 1.0f, 0.2f, false, GameAssets.k());
        this.f2702n = slider;
        Slider slider2 = new Slider(0.0f, 1.0f, 0.2f, false, GameAssets.k());
        this.f2703o = slider2;
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f2704p = height;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(760.0f * height);
        setHeight(750.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        this.f2706r = hVar;
        this.s = c1Var;
        f2688u = height;
        if (ExiledKingdoms.f3378h) {
            this.f2704p = 0.8f;
            f2688u = 1.0f;
            setWidth(608.0f);
            setHeight(624.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        this.f2689a = true;
        label.setFontScale(f2688u);
        label2.setFontScale(f2688u);
        label3.setFontScale(f2688u);
        label4.setFontScale(f2688u);
        label4.setWrap(true);
        label5.setFontScale(f2688u);
        label5.setWrap(true);
        label6.setFontScale(f2688u);
        label6.setWrap(true);
        label7.setFontScale(f2688u);
        label7.setWrap(true);
        label8.setFontScale(f2688u);
        label8.setWrap(true);
        center();
        top();
        pad(this.f2704p * 5.0f);
        padTop(this.f2704p * 25.0f);
        row().spaceTop(this.f2704p * 25.0f).center().colspan(2);
        imageButton.setColor(new Color(Color.ORANGE));
        Table table = new Table();
        float f2 = this.f2704p;
        table.setWidth(f2 * 320.0f);
        table.center();
        table.row().colspan(2).left();
        table.add(label).center().height(35.0f * f2);
        float f3 = 64.0f * f2;
        table.add(sVar).width(f3).height(f3);
        Cell cellSpaceLeft = table.add(imageButton).spaceLeft(f2 * 10.0f);
        float f4 = f2 * 45.0f;
        cellSpaceLeft.width(f4).height(f4);
        add(table).center().width(this.f2704p * 390.0f);
        row().center().spaceTop(this.f2704p * 30.0f).colspan(2);
        tVar3.getLabel().setFontScale(f2688u);
        add(tVar3).spaceLeft(this.f2704p * 40.0f).width(this.f2704p * 300.0f).height(this.f2704p * 45.0f);
        row().center().spaceTop(this.f2704p * 25.0f).colspan(2);
        tVar2.getLabel().setFontScale(f2688u);
        add(tVar2).spaceLeft(this.f2704p * 40.0f).width(this.f2704p * 300.0f).height(this.f2704p * 45.0f);
        row().left().spaceTop(this.f2704p * 40.0f);
        add(label2).center();
        add(slider).width(this.f2704p * 230.0f).height(this.f2704p * 30.0f).spaceLeft(this.f2704p * 20.0f);
        slider.getStyle().knob.setMinHeight(this.f2704p * 45.0f);
        slider.getStyle().knob.setMinWidth(this.f2704p * 20.0f);
        row().left().spaceTop(this.f2704p * 30.0f);
        add(label3).center();
        add(slider2).width(this.f2704p * 230.0f).height(this.f2704p * 30.0f).spaceLeft(this.f2704p * 20.0f);
        row().spaceTop(this.f2704p * 50.0f);
        Table table2 = new Table();
        table2.add(checkBox2).right().spaceRight(this.f2704p * 10.0f);
        table2.add(label5).left().align(8).width(this.f2704p * 310.0f);
        add(table2).left();
        add(tVar4).width(this.f2704p * 330.0f);
        tVar4.addListener(new a());
        Table table3 = new Table();
        row().spaceTop(this.f2704p * 50.0f);
        float f5 = this.f2704p * 310.0f;
        table3.clear();
        table3.add(checkBox).right().spaceRight(this.f2704p * 10.0f);
        table3.add(label4).left().align(8).width(f5);
        add(table3).left();
        Table table4 = new Table();
        table4.add(checkBox4).right().spaceRight(this.f2704p * 10.0f);
        table4.add(label7).left().align(8).width(f5);
        add(table4).left();
        Cell cell = checkBox.getCells().get(0);
        float f6 = this.f2704p * 20.0f;
        cell.size(f6, f6);
        Cell cell2 = checkBox4.getCells().get(0);
        float f7 = this.f2704p * 20.0f;
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
        row().spaceTop(this.f2704p * 20.0f);
        Table table5 = new Table();
        table5.add(checkBox3).right().spaceRight(this.f2704p * 10.0f);
        table5.add(label6).left().align(8).width(f5);
        add(table5).left();
        Table table6 = new Table();
        table6.add(checkBox5).right().spaceRight(this.f2704p * 10.0f);
        table6.add(label8).left().align(8).width(f5);
        add(table6).left();
        Cell cell3 = checkBox3.getCells().get(0);
        float f8 = this.f2704p * 20.0f;
        cell3.size(f8, f8);
        Cell cell4 = checkBox5.getCells().get(0);
        float f9 = this.f2704p * 20.0f;
        cell4.size(f9, f9);
        if (Settings.b()) {
            checkBox3.setChecked(true);
        }
        Cell cell5 = checkBox2.getCells().get(0);
        float f10 = this.f2704p * 20.0f;
        cell5.size(f10, f10);
        if (ExiledKingdoms.f3379i) {
            checkBox2.setChecked(true);
        }
        row().colspan(2).center().spaceTop(this.f2704p * 30.0f);
        add(tVar).center().width(this.f2704p * 180.0f).height(this.f2704p * 38.0f);
        slider.addListener(new h());
        slider2.addListener(new i());
        tVar.addListener(new j());
        label4.clearListeners();
        label4.addListener(new k());
        label5.clearListeners();
        label5.addListener(new l());
        checkBox2.clearListeners();
        checkBox2.addListener(new m());
        label6.clearListeners();
        label6.addListener(new n());
        checkBox4.clearListeners();
        checkBox4.addListener(new o());
        label7.clearListeners();
        label7.addListener(new b());
        label8.clearListeners();
        label8.addListener(new c());
        imageButton.addListener(new d());
        tVar3.addListener(new e());
        tVar2.addListener(new f());
        checkBox.addListener(new g());
        setVisible(false);
    }

    public final void m() {
        this.f2693e.a(Settings.h());
        Settings.x(this.f2694f.isChecked());
        Settings.y(this.f2695g.isChecked());
        Settings.w(this.f2696h.isChecked());
        Settings.I(this.f2697i.isChecked());
        Settings.G(this.f2698j.isChecked());
        this.f2691c.setText(GameString.b("EXIT", false));
        this.f2699k.setText(GameString.b("LANGUAGE", false));
        this.f2700l.setText(GameString.b("SOUND_VOLUME", false));
        this.f2701m.setText(GameString.b("MUSIC_VOLUME", false));
        l0.e.u();
        setVisible(false);
        if (ExiledKingdoms.f3382l == GameData.GameStatus.f3184a) {
            GameLevel.n(false);
        }
    }

    public final void n(q0.c cVar) {
        this.f2690b = cVar;
    }

    public final void o() {
        this.f2689a = true;
    }

    public final void p(Stage stage, boolean z2) {
        setVisible(true);
        this.f2689a = z2;
        this.f2705q = stage;
        this.f2693e.a(Settings.h());
        this.f2702n.setValue(Settings.m());
        this.f2703o.setValue(Settings.i());
        if (Settings.o()) {
            this.f2694f.setChecked(true);
        }
        if (ExiledKingdoms.f3379i) {
            this.f2695g.setChecked(true);
        }
        if (Settings.b()) {
            this.f2696h.setChecked(true);
        }
        if (Settings.n()) {
            this.f2697i.setChecked(true);
        }
        if (Settings.l()) {
            this.f2698j.setChecked(true);
        }
        this.f2692d.setText(GameString.b("RESOLUTION", false) + ": " + Serializer.m());
    }
}

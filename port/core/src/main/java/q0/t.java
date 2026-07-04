package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: compiled from: LibraryWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class t extends Window {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static float f3955i = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Label f3956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextButton f3957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextButton f3958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextButton f3959d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Label f3960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextButton f3961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public com.badlogic.gdx.Game f3962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Stage f3963h;

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            t.a(t.this, "http://www.exiledkingdoms.com/wiki/index.php?title=Main_Page");
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            t.a(t.this, "http://www.exiledkingdoms.com/wiki/index.php?title=Areas");
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            t.a(t.this, "http://www.exiledkingdoms.com/wiki/index.php?title=Quests");
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class d extends ChangeListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            t.a(t.this, "http://www.exiledkingdoms.com/wiki/index.php?title=Beast_Table");
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class e extends ChangeListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            ((MainActivity) ExiledKingdoms.f()).i();
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class f extends ChangeListener {

        /* JADX INFO: compiled from: LibraryWindow.java */
        final class a extends q0.d {
            a() {
                super("", Assets.g());
                new TextButton(GameString.b("SETTINGS", false), Assets.g(), "menuButton");
                new TextButton(GameString.b("RELOAD_FROM_LAST_SLEEP", false), Assets.g(), "menuButton");
                float height = Gdx.graphics.getHeight() / 720.0f;
                this.f3857a = height;
                setBackground(Assets.g().getDrawable("windowbg"));
                setMovable(false);
                setModal(true);
                float f2 = 480.0f * height;
                setWidth(f2);
                setHeight(200.0f * height);
                setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
                TextButton textButton = new TextButton(GameString.b("HEROES", false), Assets.g(), "menuButton");
                TextButton textButton2 = new TextButton(GameString.b("IRONMAN", false), Assets.g(), "menuButton");
                textButton.getLabel().setFontScale(height);
                textButton2.getLabel().setFontScale(height);
                padTop(30.0f).padBottom(30.0f);
                getContentTable().center();
                getButtonTable().defaults().spaceLeft(40.0f * height).width(f2);
                float f3 = 60.0f * height;
                float f4 = 450.0f * height;
                float f5 = height * 5.0f;
                getButtonTable().row().height(f3).width(f4).pad(f5);
                button((Button) textButton, (Object) 0);
                getButtonTable().row().height(f3).width(f4).pad(f5);
                button((Button) textButton2, (Object) 1);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                Integer num = (Integer) obj;
                int iIntValue = num.intValue();
                f fVar = f.this;
                if (iIntValue == 0) {
                    ((MainActivity) ExiledKingdoms.f()).k(false);
                }
                if (num.intValue() == 1) {
                    ((MainActivity) ExiledKingdoms.f()).k(true);
                }
            }
        }

        f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            new a().show(t.this.f3963h);
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class g extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            l0.e.t();
        }
    }

    /* JADX INFO: compiled from: LibraryWindow.java */
    final class h extends ClickListener {
        h() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            t.this.setVisible(false);
        }
    }

    public t(Stage stage) {
        super("", Assets.g());
        Label label = new Label(GameString.b("LIBRARY", false), Assets.g(), "menuLabelStrongStyle");
        this.f3956a = label;
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f3957b = textButton;
        TextButton textButton2 = new TextButton(GameString.b("ACHIEVEMENTS", false), Assets.g(), "menuButton");
        this.f3958c = textButton2;
        TextButton textButton3 = new TextButton(GameString.b("LEADERBOARDS", false), Assets.g(), "menuButton");
        this.f3959d = textButton3;
        TextButton textButton4 = new TextButton(GameString.b("BESTIARY", false), Assets.g(), "menuButton");
        TextButton textButton5 = new TextButton(GameString.b("AREAS", false), Assets.g(), "menuButton");
        TextButton textButton6 = new TextButton(GameString.b("QUESTS", false), Assets.g(), "menuButton");
        TextButton textButton7 = new TextButton(GameString.b("MORE_INFO", false), Assets.g(), "menuButton");
        Label label2 = new Label("", GameAssets.S);
        this.f3960e = label2;
        TextButton textButton8 = new TextButton(GameString.b("CONNECT", false), Assets.g(), "menuButton");
        this.f3961f = textButton8;
        Image image = new Image(Assets.a("gpgs"));
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth() * 0.68f);
        setHeight(Gdx.graphics.getHeight() * 0.75f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 3.3f);
        float f2 = f3955i;
        label2.setFontScale(f2);
        label2.setWrap(true);
        this.f3963h = stage;
        label.setFontScale(1.25f * f2);
        center();
        float f3 = 50.0f * f2;
        row().height(f3).space(f2 * 25.0f).colspan(2);
        add(label).center();
        float f4 = 15.0f * f2;
        row().height(f3).space(f4);
        float f5 = f2 * 380.0f;
        add(textButton6).width(f5);
        add(textButton4).width(f5);
        row().height(f3).space(f4);
        add(textButton5).width(f5);
        add(textButton7).width(f5);
        textButton7.addListener(new a());
        textButton5.addListener(new b());
        textButton6.addListener(new c());
        textButton4.addListener(new d());
        row().height(f3).space(f4).spaceTop(60.0f * f2);
        add(textButton2).width(f5);
        add(textButton3).width(f5);
        textButton2.addListener(new e());
        textButton3.addListener(new f());
        textButton8.addListener(new g());
        row().colspan(2).height(125.0f * f2);
        Table table = new Table();
        float f6 = 90.0f * f2;
        Cell cellHeight = table.add(image).width(f6).height(f6);
        float f7 = 5.0f * f2;
        cellHeight.pad(f7);
        table.add(label2).width(540.0f * f2).pad(f7);
        table.add(textButton8).width(180.0f * f2).pad(f7);
        add(table);
        row().colspan(2).height(55.0f * f2).top();
        add(textButton).width(120.0f * f2).height(45.0f * f2).space(f2 * 20.0f);
        textButton.clearListeners();
        textButton.addListener(new h());
        setVisible(false);
    }

    static void a(t tVar, String str) {
        tVar.getClass();
        if (Settings.e("GL_wiki") > 0) {
            Gdx.f1569net.openURI(str);
        } else {
            new u(tVar, GameString.b("WIKI_CONFIRM", false), str).show(tVar.f3963h);
        }
    }

    static void d(t tVar, String str) {
        tVar.getClass();
        Gdx.f1569net.openURI(str);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        boolean zL = ((MainActivity) ExiledKingdoms.f()).l();
        TextButton textButton = this.f3959d;
        TextButton textButton2 = this.f3958c;
        TextButton textButton3 = this.f3961f;
        Label label = this.f3960e;
        if (zL) {
            if (ExiledKingdoms.f3388r) {
                label.setText(GameString.b("CONNECTED_GPLAY_IOS", false));
            } else {
                label.setText(GameString.b("CONNECTED_GPLAY", false));
            }
            textButton3.getLabel().setText(GameString.b("DISCONNECT", false));
            textButton2.setDisabled(false);
            textButton.setDisabled(false);
        } else {
            if (ExiledKingdoms.f3388r) {
                label.setText(GameString.b("DISCONNECTED_GPLAY_IOS", false));
            } else {
                label.setText(GameString.b("DISCONNECTED_GPLAY", false));
            }
            textButton3.getLabel().setText(GameString.b("CONNECT", false));
            textButton2.setDisabled(true);
            textButton.setDisabled(true);
        }
        super.draw(batch, f2);
    }

    public final void e(com.badlogic.gdx.Game eVar) {
        this.f3956a.setText(GameString.b("LIBRARY", false));
        this.f3957b.setText(GameString.b("EXIT", false));
        this.f3962g = eVar;
        setVisible(true);
    }
}

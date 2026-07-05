package a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import n0.k1;
import n0.z;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: StoreWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f extends Window {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static float f11j = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static String f12k = "$0";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextButton f13a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextButton f14b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextButton f15c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Label f16d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private e f17e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private e f18f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Table f19g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f20h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Stage f21i;

    /* JADX INFO: compiled from: StoreWindow.java */
    final class a extends ChangeListener {

        /* JADX INFO: renamed from: a.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: StoreWindow.java */
        final class C0000a extends k1 {
            C0000a(String str) {
                super(str, 0);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                f fVar = f.this;
                String str = f.f12k;
                fVar.d();
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            ExiledKingdoms.f3386p.c();
            new C0000a(GameString.b("RESTORING_LICENSE", false)).show(f.this.f21i);
        }
    }

    /* JADX INFO: compiled from: StoreWindow.java */
    final class b extends ChangeListener {
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (ExiledKingdoms.f3387q) {
                Gdx.f1569net.openURI("https://www.exiledkingdoms.com/support/support_android.html");
            }
        }
    }

    /* JADX INFO: compiled from: StoreWindow.java */
    final class c extends ClickListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            f fVar = f.this;
            fVar.setVisible(false);
            if (fVar.f20h) {
                GameLevel.n(false);
                fVar.f20h = false;
            }
            GameData.v().getClass();
            if (!GameData.I() || z.v() == null) {
                return;
            }
            z.v().r();
        }
    }

    /* JADX INFO: compiled from: StoreWindow.java */
    final class d extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            ControllerConfig controllerConfig = Settings.f3418a;
            ExiledKingdoms.f3371a = true;
            return true;
        }
    }

    public f(Stage stage) {
        super("", Assets.g());
        Image image = new Image(new TextureRegion(new Texture(Gdx.files.internal("data/ui/logo.png"))));
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f13a = textButton;
        TextButton textButton2 = new TextButton("Test", Assets.g(), "menuButton");
        this.f14b = textButton2;
        TextButton textButton3 = new TextButton(GameString.b("RESTORE_PURCHASE", false), Assets.g(), "menuButton");
        this.f15c = textButton3;
        Label label = new Label("Not licensed", GameAssets.S);
        this.f16d = label;
        Table table = new Table();
        this.f19g = table;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(Gdx.graphics.getWidth() * 0.84f);
        setHeight(Gdx.graphics.getHeight() * 0.96f);
        if (getHeight() * 1.2f > getWidth()) {
            setHeight(Gdx.graphics.getHeight() * 0.76f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.8f);
        label.setWrap(true);
        float f2 = f11j;
        label.setFontScale(f2);
        this.f21i = stage;
        center();
        row().colspan(3);
        add(image).center().width(340.0f * f2).height(118.0f * f2);
        float f3 = 20.0f * f2;
        row().space(10.0f * f2).height(70.0f * f2).colspan(3).spaceBottom(f3).center();
        add(label).width(getWidth() * 0.98f).center().padLeft(f3);
        row().colspan(3).space(5.0f * f2);
        e();
        add(table);
        row().height(50.0f * f2).spaceTop(15.0f * f2);
        textButton3.getLabel().setFontScale(f2);
        textButton.getLabel().setFontScale(f2);
        textButton2.getLabel().setFontScale(f2);
        textButton2.setColor(Color.LIME);
        float f4 = f2 * 275.0f;
        add(textButton3).width(f4);
        textButton3.addListener(new a());
        textButton2.addListener(new b());
        add(textButton2).width(f4);
        add(textButton).width(f4);
        textButton.clearListeners();
        textButton.addListener(new c());
        setVisible(false);
    }

    private void e() {
        Table table = this.f19g;
        table.clear();
        this.f18f = new e(0);
        ExiledKingdoms.f3371a = true;
        e eVar = new e(1);
        this.f17e = eVar;
        eVar.addListener(new d());
        ExiledKingdoms.f3371a = true;
        Cell cellColspan = table.row().colspan(3);
        float f2 = f11j;
        float f3 = 5.0f * f2;
        cellColspan.space(f3);
        table.add(this.f17e);
        table.row().colspan(3).height(f2 * 140.0f).space(f3);
        table.add(this.f18f);
    }

    public final void d() {
        d0.d dVarP = ((e0.f) ExiledKingdoms.f3386p.a()).p();
        if (dVarP.equals(d0.d.f2140e)) {
            f12k = "<<ERROR!>>";
        } else {
            f12k = dVarP.b() + " " + dVarP.a();
        }
        a.a.x("LICENSE_INFO_IOS", false, new StringBuilder("[DARK_GRAY]"), "[]", this.f16d);
        this.f13a.setText(GameString.b("EXIT", false));
        TextButton textButton = this.f15c;
        textButton.setText(GameString.b("RESTORE_PURCHASE", false));
        ControllerConfig controllerConfig = Settings.f3418a;
        ExiledKingdoms.f3371a = true;
        textButton.setDisabled(true);
        TextButton textButton2 = this.f14b;
        textButton2.setText(GameString.b("HELP_LICENSE", false));
        if (ExiledKingdoms.f3387q) {
            textButton2.setVisible(true);
        } else {
            textButton2.setVisible(false);
        }
        e();
        setVisible(true);
    }
}

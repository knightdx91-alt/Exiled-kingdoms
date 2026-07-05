package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.games.GamesStatusCodes;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: VaultWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n1 extends Window {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final float f2811e = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageButton f2812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageButton f2813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageButton f2814c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ImageButton f2815d;

    /* JADX INFO: compiled from: VaultWindow.java */
    final class a extends ChangeListener {

        /* JADX INFO: renamed from: n0.n1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: VaultWindow.java */
        final class C0040a extends j {
            C0040a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    Player player = GameData.v().player;
                    a aVar = a.this;
                    player.R1(n1.b(n1.this, 1));
                    GameData.v().hasVault = true;
                    n1.a(n1.this);
                    n0.d.f().d();
                    GameLevel.n(true);
                    z.v().V.T(1, GameData.v().z("vault"));
                }
            }
        }

        /* JADX INFO: compiled from: VaultWindow.java */
        final class b extends k1 {
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean z2 = GameData.v().hasVault;
            n1 n1Var = n1.this;
            if (z2) {
                n1.a(n1Var);
                if (n0.d.f() != null) {
                    n0.d.f().d();
                }
                GameLevel.n(true);
                z.v().V.T(1, GameData.v().z("vault"));
                return;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.h() >= n1.b(n1Var, 1)) {
                new C0040a(n1.c(n1Var, 1)).show(z.v().a());
            } else {
                new b(n1.d(n1Var, 1), 0).show(z.v().a());
            }
        }
    }

    /* JADX INFO: compiled from: VaultWindow.java */
    final class b extends ChangeListener {

        /* JADX INFO: compiled from: VaultWindow.java */
        final class a extends j {
            a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    Player player = GameData.v().player;
                    b bVar = b.this;
                    player.R1(n1.b(n1.this, 2));
                    GameData.v().hasVault2 = true;
                    n1.a(n1.this);
                    n0.d.f().d();
                    GameLevel.n(true);
                    z.v().V.T(1, GameData.v().z("vault2"));
                }
            }
        }

        /* JADX INFO: renamed from: n0.n1$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: VaultWindow.java */
        final class C0041b extends k1 {
        }

        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean z2 = GameData.v().hasVault2;
            n1 n1Var = n1.this;
            if (z2) {
                n1.a(n1Var);
                n0.d.f().d();
                GameLevel.n(true);
                z.v().V.T(1, GameData.v().z("vault2"));
                return;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.h() >= n1.b(n1Var, 2)) {
                new a(n1.c(n1Var, 2)).show(z.v().a());
            } else {
                new C0041b(n1.d(n1Var, 2), 0).show(z.v().a());
            }
        }
    }

    /* JADX INFO: compiled from: VaultWindow.java */
    final class c extends ChangeListener {

        /* JADX INFO: compiled from: VaultWindow.java */
        final class a extends j {
            a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    Player player = GameData.v().player;
                    c cVar = c.this;
                    player.R1(n1.b(n1.this, 3));
                    GameData.v().hasVault3 = true;
                    n1.a(n1.this);
                    n0.d.f().d();
                    GameLevel.n(true);
                    z.v().V.T(1, GameData.v().z("vault3"));
                }
            }
        }

        /* JADX INFO: compiled from: VaultWindow.java */
        final class b extends k1 {
        }

        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean z2 = GameData.v().hasVault3;
            n1 n1Var = n1.this;
            if (z2) {
                n1.a(n1Var);
                n0.d.f().d();
                GameLevel.n(true);
                z.v().V.T(1, GameData.v().z("vault3"));
                return;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.h() >= n1.b(n1Var, 3)) {
                new a(n1.c(n1Var, 3)).show(z.v().a());
            } else {
                new b(n1.d(n1Var, 3), 0).show(z.v().a());
            }
        }
    }

    /* JADX INFO: compiled from: VaultWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: compiled from: VaultWindow.java */
        final class a extends j {
            a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    Player player = GameData.v().player;
                    d dVar = d.this;
                    player.R1(n1.b(n1.this, 4));
                    GameData.v().hasVault4 = true;
                    n1.a(n1.this);
                    n0.d.f().d();
                    GameLevel.n(true);
                    z.v().V.T(1, GameData.v().z("vault4"));
                }
            }
        }

        /* JADX INFO: compiled from: VaultWindow.java */
        final class b extends k1 {
        }

        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            boolean z2 = GameData.v().hasVault4;
            n1 n1Var = n1.this;
            if (z2) {
                n1.a(n1Var);
                n0.d.f().d();
                GameLevel.n(true);
                z.v().V.T(1, GameData.v().z("vault4"));
                return;
            }
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player.h() >= n1.b(n1Var, 4)) {
                new a(n1.c(n1Var, 4)).show(z.v().a());
            } else {
                new b(n1.d(n1Var, 4), 0).show(z.v().a());
            }
        }
    }

    /* JADX INFO: compiled from: VaultWindow.java */
    final class e extends ChangeListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            n1.a(n1.this);
        }
    }

    public n1() {
        super("", Assets.g());
        Label label = new Label(GameString.b("VAULT_TITLE", false), Assets.g(), "menuLabelStrongLargeStyle");
        Label label2 = new Label(a.a.o("VAULT_DESC", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        ImageButton imageButton = new ImageButton(GameAssets.a(Assets.a("vault")));
        this.f2812a = imageButton;
        ImageButton imageButton2 = new ImageButton(GameAssets.a(Assets.a("vault")));
        this.f2813b = imageButton2;
        ImageButton imageButton3 = new ImageButton(GameAssets.a(Assets.a("vault")));
        this.f2814c = imageButton3;
        ImageButton imageButton4 = new ImageButton(GameAssets.a(Assets.a("vault")));
        this.f2815d = imageButton4;
        TextButton textButton = new TextButton(GameString.b("EXIT", false), Assets.g(), "menuButton");
        setVisible(false);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        float f2 = f2811e;
        setWidth(550.0f * f2);
        setHeight(450.0f * f2);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        label.setFontScale(f2);
        label2.setWrap(true);
        label2.setFontScale(f2);
        top();
        row().center().colspan(2);
        float f3 = 15.0f * f2;
        add(label).center().padTop(f3);
        row().center().colspan(2).spaceTop(f3);
        add(label2).center().align(8).width(510.0f * f2).spaceLeft(16.0f * f2);
        float f4 = 50.0f * f2;
        row().center().spaceRight(f4).spaceTop(4.0f * f2);
        float f5 = 80.0f * f2;
        add(imageButton).height(f5).width(f5).right();
        add(imageButton2).height(f5).width(f5).left();
        row().center().spaceRight(f4).spaceTop(28.0f * f2);
        add(imageButton3).height(f5).width(f5).right();
        add(imageButton4).height(f5).width(f5).left();
        row().center().spaceRight(40.0f * f2).spaceTop(20.0f * f2).colspan(2);
        add(textButton).width(f2 * 240.0f).height(f4).center();
        imageButton.addListener(new a());
        imageButton2.addListener(new b());
        imageButton3.addListener(new c());
        imageButton4.addListener(new d());
        textButton.addListener(new e());
    }

    static void a(n1 n1Var) {
        n1Var.getClass();
        GameLevel.n(false);
        n1Var.setVisible(false);
        z.v().J(0);
    }

    static /* synthetic */ int b(n1 n1Var, int i2) {
        n1Var.getClass();
        return e(i2);
    }

    static String c(n1 n1Var, int i2) {
        n1Var.getClass();
        return GameString.b("VAULT_BUY", false).replace("{COST}", "[BLUE]" + e(i2) + "[]");
    }

    static String d(n1 n1Var, int i2) {
        n1Var.getClass();
        return GameString.b("VAULT_COST", false).replace("{COST}", "[BLUE]" + e(i2) + "[]");
    }

    private static int e(int i2) {
        return i2 == 1 ? CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT : i2 == 2 ? GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND : i2 == 3 ? 10000 : 20000;
    }

    public final void f() {
        GameLevel.n(true);
        boolean z2 = GameData.v().hasVault;
        ImageButton imageButton = this.f2812a;
        if (z2) {
            imageButton.setStyle(GameAssets.a(Assets.a("vault_active")));
        } else {
            imageButton.setStyle(GameAssets.a(Assets.a("vault")));
        }
        boolean z3 = GameData.v().hasVault2;
        ImageButton imageButton2 = this.f2813b;
        if (z3) {
            imageButton2.setStyle(GameAssets.a(Assets.a("vault_active")));
        } else {
            imageButton2.setStyle(GameAssets.a(Assets.a("vault")));
        }
        boolean z4 = GameData.v().hasVault3;
        ImageButton imageButton3 = this.f2814c;
        if (z4) {
            imageButton3.setStyle(GameAssets.a(Assets.a("vault_active")));
        } else {
            imageButton3.setStyle(GameAssets.a(Assets.a("vault")));
        }
        boolean z5 = GameData.v().hasVault4;
        ImageButton imageButton4 = this.f2815d;
        if (z5) {
            imageButton4.setStyle(GameAssets.a(Assets.a("vault_active")));
        } else {
            imageButton4.setStyle(GameAssets.a(Assets.a("vault")));
        }
        setVisible(true);
        toFront();
    }
}

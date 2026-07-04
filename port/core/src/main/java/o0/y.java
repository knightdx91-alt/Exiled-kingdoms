package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterTraits;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: TraitDescriptionTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class y extends Table {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static float f3684h = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static float f3685i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f3686a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f3687b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f3688c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ImageButton f3689d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3690e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CharacterSheet f3691f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Stage f3692g;

    /* JADX INFO: compiled from: TraitDescriptionTable.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: o0.y$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TraitDescriptionTable.java */
        final class C0044a extends p0.b {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            y yVar = y.this;
            String strF = CharacterTraits.f(yVar.f3690e);
            int i4 = yVar.f3690e;
            String strB = "";
            String strB2 = i4 != 0 ? i4 != 1 ? i4 != 2 ? i4 != 3 ? i4 != 4 ? i4 != 5 ? "" : GameString.b("DESC_PER", false) : GameString.b("DESC_AWA", false) : GameString.b("DESC_INT", false) : GameString.b("DESC_AGI", false) : GameString.b("DESC_END", false) : GameString.b("DESC_STR", false);
            int i5 = yVar.f3690e;
            if (i5 == 0) {
                strB = GameString.b("DESC_STR_STATS", false);
            } else if (i5 == 1) {
                strB = GameString.b("DESC_END_STATS", false);
            } else if (i5 == 2) {
                strB = GameString.b("DESC_AGI_STATS", false);
            } else if (i5 == 3) {
                strB = GameString.b("DESC_INT_STATS", false);
            } else if (i5 == 4) {
                strB = GameString.b("DESC_AWA_STATS", false);
            } else if (i5 == 5) {
                strB = GameString.b("DESC_PER_STATS", false);
            }
            new C0044a(strF, strB2, strB, Assets.a(CharacterTraits.e(yVar.f3690e))).show(yVar.f3692g);
            return true;
        }
    }

    /* JADX INFO: compiled from: TraitDescriptionTable.java */
    final class b extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f3694a;

        /* JADX INFO: compiled from: TraitDescriptionTable.java */
        final class a extends n0.j {
            a(String str) {
                super(str);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    b bVar = b.this;
                    CharacterSheet characterSheet = y.this.f3691f;
                    y yVar = y.this;
                    characterSheet.U(yVar.f3690e);
                    GameAssets.o("load");
                    yVar.h();
                    bVar.f3694a.f();
                }
            }
        }

        b(a0 a0Var) {
            this.f3694a = a0Var;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            y yVar = y.this;
            if (y.c(yVar) > yVar.f3691f.M()) {
                return true;
            }
            new a(GameString.b("TRAIT_UPGRADE_MESSAGE", false).replace("#COST#", "" + y.c(yVar))).show(yVar.f3692g);
            return true;
        }
    }

    public y(int i2, a0 a0Var) {
        Label label = new Label("", Assets.g(), "menuLabelStrongVeryLargeStyle");
        this.f3686a = label;
        Label label2 = new Label("", GameAssets.f3320c0);
        this.f3687b = label2;
        Label label3 = new Label("", GameAssets.S);
        this.f3688c = label3;
        ImageButton imageButton = new ImageButton(GameAssets.f3348q0);
        this.f3689d = imageButton;
        new Image(Assets.a("trait_strenght"));
        Image image = new Image(Assets.a("help"));
        setBackground(new NinePatchDrawable(GameAssets.P));
        setWidth(f3684h * 340.0f);
        setHeight(f3684h * 225.0f);
        f3685i = f3684h;
        if (ExiledKingdoms.f3378h) {
            f3684h = 0.8f;
            f3685i = 1.0f;
            setWidth(272.0f);
            setHeight(180.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        label.setFontScale(f3685i);
        label2.setFontScale(f3685i);
        label3.setFontScale(f3685i);
        label3.setWrap(true);
        this.f3690e = i2;
        Image image2 = new Image(Assets.a(CharacterTraits.e(i2)));
        label.setText(CharacterTraits.f(i2));
        image.addListener(new a());
        imageButton.addListener(new b(a0Var));
        center();
        row();
        add(image2).width(f3684h * 64.0f).height(f3684h * 64.0f);
        add(label).expandX();
        add(label2).width(f3684h * 45.0f).height(f3684h * 45.0f);
        row().colspan(3);
        add(label3).width(f3684h * 340.0f).height(f3684h * 100.0f);
        row();
        add(image).width(f3684h * 40.0f).height(f3684h * 40.0f);
        add(new Label("", Assets.g(), "menuLabelStyle")).expandX();
        add(imageButton).width(f3684h * 64.0f).height(f3684h * 64.0f);
    }

    static int c(y yVar) {
        return yVar.f3691f.K(yVar.f3690e) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        String strB;
        int iK = this.f3691f.K(this.f3690e) + 1;
        int iM = this.f3691f.M();
        ImageButton imageButton = this.f3689d;
        if (iK > iM || this.f3691f.K(this.f3690e) + 1 >= 13) {
            imageButton.setDisabled(true);
        } else {
            imageButton.setDisabled(false);
        }
        Label label = this.f3686a;
        int i2 = this.f3690e;
        label.setText(CharacterTraits.f(i2));
        this.f3687b.setText("[TEAL]" + this.f3691f.K(i2) + "[]");
        Label label2 = this.f3688c;
        StringBuilder sb = new StringBuilder("[BLACK]");
        int iK2 = this.f3691f.K(i2);
        if (iK2 < 0) {
            iK2 = 0;
        }
        if (iK2 > 5) {
            iK2 = 5;
        }
        if (i2 == 0) {
            strB = GameString.b("DESC_STR_" + iK2, false);
        } else if (i2 == 1) {
            strB = GameString.b("DESC_END_" + iK2, false);
        } else if (i2 == 2) {
            strB = GameString.b("DESC_AGI_" + iK2, false);
        } else if (i2 == 3) {
            strB = GameString.b("DESC_INT_" + iK2, false);
        } else if (i2 == 4) {
            strB = GameString.b("DESC_AWA_" + iK2, false);
        } else if (i2 != 5) {
            strB = "";
        } else {
            strB = GameString.b("DESC_PER_" + iK2, false);
        }
        sb.append(strB);
        sb.append("[]");
        label2.setText(sb.toString());
    }

    public final void f(Stage stage) {
        this.f3692g = stage;
    }

    public final void g(CharacterSheet characterSheet) {
        this.f3691f = characterSheet;
        h();
    }
}

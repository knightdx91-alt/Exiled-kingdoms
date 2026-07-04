package l0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.assets.Intro;
import net.fdgames.assets.IntroStage;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: IntroScreen.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c implements n {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static float f2327o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2329a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f2330b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2331c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Stage f2332d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Image f2333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Label f2334f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Image f2335g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2336h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Color f2337i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Label f2338j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f2339k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private com.badlogic.gdx.Game f2340l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Transition f2341m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static float f2326n = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static GlyphLayout f2328p = new GlyphLayout();

    /* JADX INFO: compiled from: IntroScreen.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            c cVar = c.this;
            int i4 = cVar.f2329a;
            ArrayList<IntroStage> arrayList = GameAssets.w0.stages;
            if (i4 == (arrayList == null ? 0 : arrayList.size() - 1)) {
                cVar.f2338j.setVisible(true);
            }
            cVar.f2339k = 0.01f;
            return false;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public c(com.badlogic.gdx.Game eVar, Transition transition) {
        com.badlogic.gdx.utils.Array<TextureRegion> aVar = GameAssets.f3315a;
        Intro intro = new Intro();
        intro.stages = new ArrayList<>();
        Assets assets = Assets.f3309a;
        assets.t("intro");
        GameAssets.w0 = intro;
        Color color = Color.CYAN;
        intro.a("intro_1", "INTRO_1", 12.0f, color);
        GameAssets.w0.a("intro_2", "INTRO_2", 12.0f, color);
        Intro intro2 = GameAssets.w0;
        Color color2 = Color.RED;
        intro2.a("intro_3", "INTRO_3", 5.0f, color2);
        GameAssets.w0.a("intro_4", "INTRO_4", 12.0f, color2);
        GameAssets.w0.a("intro_5", "INTRO_5", 12.0f, color2);
        GameAssets.w0.a("intro_6", "INTRO_6", 12.0f, color);
        GameAssets.w0.a("intro_7", "INTRO_7", 12.0f, color);
        GameAssets.w0.a("intro_8", "INTRO_8", 12.0f, color);
        GameAssets.w0.a("intro_9", "INTRO_9", 12.0f, color);
        GameAssets.w0.a("intro_10", "INTRO_10", 12.0f, color);
        GameAssets.w0.a("intro_11", "INTRO_11", 10.0f, color);
        float f2 = f2326n;
        f2327o = f2;
        if (ExiledKingdoms.f3378h) {
            f2327o = 1.0f;
        }
        this.f2339k = 0.0f;
        this.f2340l = eVar;
        this.f2341m = transition;
        Stage stage = new Stage();
        this.f2332d = stage;
        Gdx.input.setInputProcessor(stage);
        Image image = new Image();
        this.f2333e = image;
        Label label = new Label("", GameAssets.f3320c0);
        this.f2334f = label;
        label.setFontScale(f2327o);
        Image image2 = new Image(new TextureRegionDrawable(Assets.f("right")));
        this.f2335g = image2;
        Label label2 = new Label("", GameAssets.f3320c0);
        this.f2338j = label2;
        label2.setFontScale(f2327o);
        label2.setText(GameString.b("LOADING", false));
        GlyphLayout glyphLayout = f2328p;
        glyphLayout.setText(label2.getStyle().font, GameString.b("LOADING", false));
        label2.setX((stage.getWidth() / 2.0f) - (glyphLayout.width / 2.0f));
        label2.setY(stage.getHeight() / 1.5f);
        label2.setColor(Color.ORANGE);
        label2.setVisible(false);
        stage.addActor(image);
        stage.addActor(label);
        if (!ExiledKingdoms.f3378h) {
            stage.addActor(image2);
        }
        stage.addActor(label2);
        label.setWrap(true);
        float f3 = 20.0f * f2;
        label.setBounds(f3, f3, stage.getWidth() - f3, 300.0f * f2);
        image2.setX(stage.getWidth() - (120.0f * f2));
        image2.setY((stage.getHeight() / 2.0f) - (50.0f * f2));
        float f4 = f2 * 100.0f;
        image2.setSize(f4, f4);
        assets.t("intro");
        stage.addListener(new a());
        Gdx.input.setCatchBackKey(true);
    }

    private void g() {
        int i2 = this.f2329a;
        ArrayList<IntroStage> arrayList = GameAssets.w0.stages;
        if (i2 >= (arrayList == null ? 0 : arrayList.size() - 1)) {
            Intro intro = GameAssets.w0;
            ArrayList<IntroStage> arrayList2 = intro.stages;
            if (arrayList2 != null) {
                Iterator<IntroStage> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            }
            intro.stages = null;
            Transition transition = this.f2341m;
            com.badlogic.gdx.Game eVar = this.f2340l;
            eVar.setScreen(new b(eVar, transition));
            return;
        }
        int i3 = this.f2329a + 1;
        this.f2329a = i3;
        Image image = this.f2333e;
        image.setDrawable(GameAssets.w0.stages.get(i3).b());
        float width = Gdx.graphics.getWidth();
        float f2 = f2326n;
        image.setSize((100.0f * f2) + width, (70.0f * f2) + Gdx.graphics.getHeight());
        image.setX(f2 * (-100.0f));
        Label label = this.f2334f;
        label.setText(GameAssets.w0.stages.get(this.f2329a).c());
        this.f2331c = GameAssets.w0.stages.get(this.f2329a).duration;
        this.f2337i = new Color(GameAssets.w0.stages.get(this.f2329a).color);
        if (label.getText().f1854b < 24) {
            label.setFontScale(f2327o * 2.0f);
        } else {
            label.setFontScale(f2327o);
        }
        this.f2330b = 0.0f;
        if (this.f2329a + 1 == (GameAssets.w0.stages != null ? r1.size() - 1 : 0)) {
            this.f2336h = true;
        }
    }

    @Override // com.badlogic.gdx.Screen
    public final void a() {
        this.f2329a = -1;
        this.f2336h = false;
        g();
    }

    @Override // com.badlogic.gdx.Screen
    public final void b(int i2, int i3) {
    }

    @Override // com.badlogic.gdx.Screen
    public final void c(float f2) {
        float f3;
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float f4 = this.f2339k;
        if (f4 > 0.0f) {
            float f5 = f4 + f2;
            this.f2339k = f5;
            if (f5 > 0.2f) {
                this.f2339k = 0.0f;
                g();
            }
        }
        float f6 = this.f2330b;
        if (f6 < 1.0f) {
            f3 = f6;
        } else {
            f3 = this.f2331c - f6;
            if (this.f2336h || f3 >= 1.0f) {
                f3 = 1.0f;
            }
        }
        float f7 = f6 >= 0.5f ? f6 < 1.5f ? f6 - 0.5f : 1.0f : 0.0f;
        Image image = this.f2333e;
        image.setColor(f3, f3, f3, 1.0f);
        Label label = this.f2334f;
        Color color = this.f2337i;
        label.setColor(color.f1680r, color.f1679g, color.f1678b, f7);
        Stage stage = this.f2332d;
        stage.act(f2);
        stage.draw();
        if (this.f2330b < 7.0f) {
            image.setX((15.0f * f2 * f2326n) + image.getX());
        }
        float f8 = this.f2330b;
        Image image2 = this.f2335g;
        if (f8 > 1.0f) {
            image2.setVisible(true);
        } else {
            image2.setVisible(false);
        }
        if (this.f2331c - this.f2330b < 1.0f) {
            if (this.f2329a == (GameAssets.w0.stages != null ? r1.size() - 1 : 0)) {
                this.f2338j.setVisible(true);
            }
        }
        float f9 = this.f2330b + f2;
        this.f2330b = f9;
        if (f9 > this.f2331c) {
            g();
        }
    }

    @Override // com.badlogic.gdx.Screen
    public final void resume() {
    }
}

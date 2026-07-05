package l0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.l;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: LoadingScreen.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class d implements n {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static float f2343i = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static float f2344j = 0.35f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ExiledKingdoms f2345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SpriteBatch f2346b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Texture f2347c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Sprite f2349e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2352h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextureRegion f2348d = new TextureRegion(new Texture(Gdx.files.internal("data/ui/logo.png")));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2351g = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2350f = false;

    public d(ExiledKingdoms exiledKingdoms) {
        this.f2345a = exiledKingdoms;
    }

    @Override // com.badlogic.gdx.Screen
    public final void a() {
        l.d("LoadingScreen.show() - Splash screen showing....");
        f2343i = Gdx.graphics.getHeight() / 720.0f;
        this.f2346b = new SpriteBatch();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int iB = FDUtils.b(1, 3);
        if (iB == 1) {
            this.f2352h = (Gdx.graphics.getWidth() / 2) - (f2343i * 170.0f);
        }
        if (iB == 2) {
            this.f2352h = Gdx.graphics.getWidth() * 0.1f;
        }
        if (iB == 3) {
            this.f2352h = Gdx.graphics.getWidth() * 0.6f;
        }
        Texture texture = new Texture(Gdx.files.internal("data/ui/splash" + iB + ".png"));
        this.f2347c = texture;
        if (ExiledKingdoms.f3378h) {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        }
        Sprite sprite = new Sprite(this.f2347c);
        this.f2349e = sprite;
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setCatchBackKey(true);
    }

    @Override // com.badlogic.gdx.Screen
    public final void b(int i2, int i3) {
    }

    @Override // com.badlogic.gdx.Screen
    public final void c(float f2) {
        f2343i = Gdx.graphics.getHeight() / 720.0f;
        this.f2346b.begin();
        this.f2349e.draw(this.f2346b);
        float f3 = f2343i;
        this.f2346b.draw(this.f2348d, this.f2352h, Gdx.graphics.getHeight() * 0.7f, f3 * 340.0f, f3 * 130.0f);
        this.f2346b.end();
        float f4 = this.f2351g + f2;
        this.f2351g = f4;
        if (this.f2350f || f4 <= f2344j) {
            return;
        }
        this.f2350f = true;
        l.d("LoadingScreen.load() - Preparing to initialize....");
        ExiledKingdoms exiledKingdoms = this.f2345a;
        exiledKingdoms.h();
        while (!Assets.z()) {
        }
        GameData.v();
        l.d("ExiledKingdoms.initializeFinal() - all resources loaded");
        int iE = Settings.e("GL_times_opened") + 1;
        ExiledKingdoms.f3374d = iE;
        Settings.A(iE, "GL_times_opened");
        Settings.v();
        l.d("ExiledKingdoms.initializeFinal() - opening Main Menu");
        exiledKingdoms.c(new e(exiledKingdoms));
    }

    @Override // com.badlogic.gdx.Screen
    public final void resume() {
    }
}

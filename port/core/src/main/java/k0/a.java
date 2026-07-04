package k0;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.Iterator;
import m0.b;
import n0.w;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLevel.SpriteEffect;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.MapLight;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.assets.AnimationSet;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.assets.MapParticleEffectPool;
import net.fdgames.ek.ExiledKingdoms;
import q.f;

/* JADX INFO: compiled from: GameLevelRenderer.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final float f2269p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static int f2270q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static int f2271r;
    public static a s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static BitmapFont f2272t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static ArrayList<MapSprite> f2273u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public World f2274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f2275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public GlyphLayout f2276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public com.badlogic.gdx.math.Vector3 f2277d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OrthographicCamera f2278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<w> f2279f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public com.badlogic.gdx.utils.Array<MapParticleEffectPool.MapPooledEffect> f2280g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public com.badlogic.gdx.utils.Array<SpriteEffect> f2281h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2282i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public m0.a f2283j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public double f2284k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public double f2285l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Coords f2286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Coords f2287n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2288o;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: renamed from: k0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GameLevelRenderer.java */
    public enum EnumC0031a {
        BLOOD, HEAL, LEVELUP, FIRE, ICE, DEATH, TOXIC, SECRET, TORCH, BONFIRE, BONFIRE_NIGHT, LAMP, SPEED, FLAME_AURA, CASTING, MAGIC_DAMAGE;
    }

    static {
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        f2269p = Gdx.graphics.getWidth() / 1200.0f;
        s = null;
        f2273u = new ArrayList<>();
    }

    public static boolean f(com.badlogic.gdx.math.Vector3 aVar) {
        Vector2 qVarB = b.B((int) aVar.f1729a, (int) aVar.f1730b);
        float f2 = qVarB.f91a;
        if ((f2 > ((float) b.P().f2420e)) || ((f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1)) < 0)) {
            return true;
        }
        float f3 = qVarB.f92b;
        return ((f3 > ((float) b.P().f2421f) ? 1 : (f3 == ((float) b.P().f2421f) ? 0 : -1)) > 0) | ((f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1)) < 0);
    }

    public static void h() {
        a aVar = s;
        if (aVar != null) {
            f fVar = aVar.f2275b;
            if (fVar != null) {
                fVar.dispose();
            }
            s.f2283j.c().dispose();
            s = null;
        }
    }

    public double k() {
        if (this.f2284k >= 0.0d) {
            double dS = GameData.v().s() - this.f2284k;
            if (dS <= 1.0d) {
                return 100.0d / (dS * 100.0d);
            }
            this.f2284k = -1.0d;
            return 1.0d;
        }
        if (this.f2285l >= 0.0d) {
            double dB = ((double) GameLevel.b()) - this.f2285l;
            if (dB <= 2.0d) {
                return (dB * 2.0d) + 1.0d;
            }
            if (GameData.v().G()) {
                this.f2285l = -1.0d;
                e eVar = (e) Gdx.app.getApplicationListener();
                GameData.v().getClass();
                GameData.X();
                GameConsole.d();
                eVar.c(new l0.e(eVar));
            } else {
                this.f2275b.a(0.0f, 0.0f, 0.0f, 0.84f);
                z.v().U.i(1, GameData.v().slot, true);
            }
        }
        return 1.0d;
    }

    public static a l() {
        if (s == null) {
            a aVar = new a();
            new ShapeRenderer();
            aVar.f2276c = new GlyphLayout();
            aVar.f2277d = new com.badlogic.gdx.math.Vector3(0.0f, 0.0f, 0.0f);
            aVar.f2280g = new com.badlogic.gdx.utils.Array<>();
            aVar.f2281h = new com.badlogic.gdx.utils.Array<>();
            aVar.f2282i = 96.0f;
            aVar.f2284k = -1.0d;
            aVar.f2285l = -1.0d;
            aVar.f2286m = new Coords(0, 0);
            aVar.f2287n = new Coords(0, 0);
            aVar.f2288o = false;
            s = aVar;
        }
        return s;
    }

    public final void a(w wVar) {
        int i2 = wVar.f2908d;
        int iMax = 0;
        for (w wVar2 : this.f2279f) {
            if (wVar2.f2908d == i2 && wVar2.a() < 60) {
                iMax = Math.max(iMax, 63 - wVar2.a());
            }
        }
        wVar.d(iMax);
        this.f2279f.add(wVar);
    }

    public final MapParticleEffectPool.MapPooledEffect b(Coords coords, EnumC0031a enumC0031a, float f2) {
        MapParticleEffectPool.MapPooledEffect mapPooledEffectObtain;
        switch (enumC0031a.ordinal()) {
            case 0:
                mapPooledEffectObtain = GameAssets.f3321d.obtain();
                break;
            case 1:
                mapPooledEffectObtain = GameAssets.f3331i.obtain();
                break;
            case 2:
                mapPooledEffectObtain = GameAssets.f3333j.obtain();
                break;
            case 3:
                mapPooledEffectObtain = GameAssets.f3323e.obtain();
                MapLight mapLight = new MapLight(coords.f3287x - 32, coords.f3288y - 32, "flash_red", 48, 0.4f, false);
                mapLight.a(this.f2275b);
                b.P().D.add(mapLight);
                break;
            case 4:
                mapPooledEffectObtain = GameAssets.f3325f.obtain();
                MapLight mapLight2 = new MapLight(coords.f3287x - 64, coords.f3288y, "flash_white", 32, 0.15f, false);
                mapLight2.a(this.f2275b);
                b.P().D.add(mapLight2);
                break;
            case 5:
                mapPooledEffectObtain = GameAssets.f3329h.obtain();
                break;
            case 6:
                mapPooledEffectObtain = GameAssets.f3327g.obtain();
                break;
            case 7:
                mapPooledEffectObtain = GameAssets.f3335k.obtain();
                mapPooledEffectObtain.getEmitters().get(0).setContinuous(true);
                mapPooledEffectObtain.getEmitters().get(0).setName("secret");
                break;
            case 8:
                mapPooledEffectObtain = GameAssets.f3337l.obtain();
                mapPooledEffectObtain.getEmitters().get(0).setContinuous(true);
                mapPooledEffectObtain.nocturne = false;
                break;
            case 9:
                mapPooledEffectObtain = GameAssets.f3339m.obtain();
                mapPooledEffectObtain.getEmitters().get(0).setContinuous(true);
                mapPooledEffectObtain.nocturne = false;
                break;
            case 10:
                mapPooledEffectObtain = GameAssets.f3339m.obtain();
                mapPooledEffectObtain.getEmitters().get(0).setContinuous(true);
                mapPooledEffectObtain.nocturne = true;
                break;
            case 11:
                mapPooledEffectObtain = GameAssets.f3337l.obtain();
                mapPooledEffectObtain.getEmitters().get(0).setContinuous(true);
                mapPooledEffectObtain.nocturne = true;
                break;
            case 12:
                mapPooledEffectObtain = GameAssets.f3341n.obtain();
                break;
            case 13:
                mapPooledEffectObtain = GameAssets.f3343o.obtain();
                break;
            case 14:
                mapPooledEffectObtain = GameAssets.f3345p.obtain();
                break;
            case 15:
                mapPooledEffectObtain = GameAssets.f3347q.obtain();
                break;
            default:
                mapPooledEffectObtain = null;
                break;
        }
        mapPooledEffectObtain.setPosition(b.A(VertexAttributes.Usage.Tangent, coords).f3287x + 64, b.A(VertexAttributes.Usage.Tangent, coords).f3288y + 32);
        if (f2 > 0.0f) {
            mapPooledEffectObtain.setDuration(((int) f2) * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        }
        this.f2280g.add(mapPooledEffectObtain);
        return mapPooledEffectObtain;
    }

    public final void c(Coords coords, String str, float f2) {
        SpriteEffect spriteEffect = new SpriteEffect(f2, coords.f3287x, coords.f3288y, str);
        float f3 = b.A(VertexAttributes.Usage.Tangent, coords).f3287x;
        spriteEffect.f3105x = (int) f3;
        spriteEffect.f3106y = (int) (b.A(VertexAttributes.Usage.Tangent, coords).f3288y - 32);
        this.f2281h.add(spriteEffect);
    }

    public final void d(float f2, int i2, int i3, int i4, String str) {
        MapLight mapLight = new MapLight(i2 - 32, i3 - 32, str, i4, f2, false);
        mapLight.a(this.f2275b);
        b.P().D.add(mapLight);
    }

    public final void e(float f2, int i2, int i3, String str) {
        MapLight mapLight = new MapLight(f2, i2, i3, str);
        mapLight.a(this.f2275b);
        b.P().D.add(mapLight);
    }

    public final void g(int i2, int i3) {
        com.badlogic.gdx.math.Vector3 aVar = this.f2278e.position;
        com.badlogic.gdx.math.Vector3 aVar2 = new com.badlogic.gdx.math.Vector3(aVar.f1729a + i2, aVar.f1730b + i3, 0.0f);
        if (f(aVar2)) {
            return;
        }
        OrthographicCamera orthographicCamera = this.f2278e;
        aVar2.w(orthographicCamera.position);
        orthographicCamera.translate(aVar2);
    }

    public final void i() {
        this.f2288o = false;
        this.f2284k = GameData.v().s();
        this.f2278e.zoom = 1.8f;
    }

    public final void j() {
        this.f2285l = GameLevel.b();
    }

    public final void m() {
        if (ExiledKingdoms.f3378h) {
            if (Gdx.graphics.getWidth() > 1680) {
                f2270q = 880;
                f2271r = 495;
                this.f2282i = 160.0f;
            } else if (Gdx.graphics.getWidth() >= 1600 && Gdx.graphics.getWidth() <= 1680) {
                f2270q = 840;
                f2271r = 473;
                this.f2282i = 128.0f;
            } else if (Gdx.graphics.getWidth() <= 1280 || Gdx.graphics.getWidth() >= 1600) {
                f2270q = 780;
                f2271r = 439;
                this.f2282i = 128.0f;
            } else {
                f2270q = 820;
                f2271r = 462;
                this.f2282i = 128.0f;
            }
            l.d("GameLevelRenderer.Initialize() > viewport: " + f2270q + "x" + f2271r);
        } else {
            f2270q = 533;
            f2271r = 300;
        }
        Assets.k();
        this.f2279f = new ArrayList<>();
        com.badlogic.gdx.utils.Array<MapParticleEffectPool.MapPooledEffect> aVar = this.f2280g;
        for (int i2 = aVar.f1750b - 1; i2 >= 0; i2--) {
            aVar.get(i2).free();
        }
        aVar.clear();
        b bVarP = b.P();
        bVarP.getClass();
        bVarP.D = new ArrayList<>();
        Iterator<u.f> it = bVarP.L.b().iterator();
        while (true) {
            a.b bVar = (a.b) it;
            if (!bVar.hasNext()) {
                break;
            }
            u.f fVar = (u.f) bVar.next();
            if (fVar.a().a("type")) {
                try {
                    if (fVar.a().b("type").toString().trim().equals("light")) {
                        if (new ConditionsSet(fVar.a().a("conditions") ? fVar.a().b("conditions").toString().trim() : "").a().booleanValue()) {
                            bVarP.D.add(new MapLight((int) Float.parseFloat(fVar.a().b("x").toString()), (int) Float.parseFloat(fVar.a().b("y").toString()), fVar.a().a("id") ? fVar.a().b("id").toString() : "fire", fVar.a().a("distance") ? Integer.parseInt(fVar.a().b("distance").toString()) : 0, 0.0f, false));
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        for (int i3 = 0; i3 < bVarP.f2417b; i3++) {
            for (int i4 = 0; i4 < bVarP.f2418c; i4++) {
                if (bVarP.I.j(i3, i4) != null && bVarP.I.j(i3, i4).a().c().a("light")) {
                    String string = bVarP.I.j(i3, i4).a().c().b("light").toString();
                    int i5 = i3 * 32;
                    int i6 = i4 * 32;
                    bVarP.D.add(new MapLight(i5, i6, string, 0, 0.0f, string.contains("lamp")));
                    if (string.equals("torch")) {
                        l().b(new Coords(i5, i6 + 32), EnumC0031a.TORCH, 0.0f);
                    }
                    if (string.equals("brazier")) {
                        l().b(new Coords(i5 + 32, i6), EnumC0031a.BONFIRE, 0.0f);
                    }
                    if (string.equals("brazier_lamp")) {
                        l().b(new Coords(i5 + 32, i6), EnumC0031a.BONFIRE_NIGHT, 0.0f);
                    }
                    boolean zEquals = string.equals("lamp_rd");
                    EnumC0031a enumC0031a = EnumC0031a.LAMP;
                    if (zEquals) {
                        l().b(new Coords(i5 + 8, i6 + 44), enumC0031a, 0.0f);
                    }
                    if (string.equals("lamp_ld")) {
                        l().b(new Coords(i5 - 12, i6 + 22), enumC0031a, 0.0f);
                    }
                }
            }
        }
        this.f2284k = -1.0d;
        this.f2285l = -1.0d;
        OrthographicCamera orthographicCamera = new OrthographicCamera(f2270q, f2271r);
        this.f2278e = orthographicCamera;
        orthographicCamera.update();
        m0.a aVar2 = new m0.a(b.q());
        this.f2283j = aVar2;
        aVar2.c().begin();
        if (AnimationLoader.f3300a == null) {
            AnimationLoader.f3300a = new com.badlogic.gdx.utils.Array<>();
        }
        a.b<AnimationSet> it2 = AnimationLoader.f3300a.iterator();
        while (it2.hasNext()) {
            it2.next();
            for (MapActor.Facing facing : MapActor.Facing.values()) {
                for (MapActor.ActorState actorState : MapActor.ActorState.values()) {
                }
            }
        }
        this.f2283j.c().end();
        this.f2274a = new World(new Vector2 (0.0f, 0.0f));
        f.d();
        f.f3765v = true;
        f fVar2 = new f(this.f2274a);
        this.f2275b = fVar2;
        fVar2.a(1.0f, 1.0f, 1.0f, 1.0f);
        this.f2275b.b();
        b bVarP2 = b.P();
        f fVar3 = this.f2275b;
        Iterator<MapLight> it3 = bVarP2.D.iterator();
        while (it3.hasNext()) {
            it3.next().a(fVar3);
        }
        i();
    }

    public final Boolean n() {
        return k() > 0.8999999761581421d ? Boolean.TRUE : Boolean.FALSE;
    }

    public final void o(int i2, int i3) {
        float f2 = i2;
        float f3 = i3;
        com.badlogic.gdx.math.Vector3 aVar = this.f2277d;
        aVar.v(f2, f3, 0.0f);
        aVar.f1730b = -aVar.f1730b;
        aVar.b(this.f2278e.position);
        if (!f(aVar)) {
            OrthographicCamera orthographicCamera = this.f2278e;
            aVar.w(orthographicCamera.position);
            orthographicCamera.translate(aVar);
        }
        aVar.t(f2, f3, 0.0f);
    }

    public final void p() {
        a.b<MapParticleEffectPool.MapPooledEffect> it = this.f2280g.iterator();
        while (it.hasNext()) {
            MapParticleEffectPool.MapPooledEffect next = it.next();
            if (next.findEmitter("secret") != null) {
                next.getEmitters().get(0).setContinuous(false);
                next.getEmitters().get(0).duration = 0.1f;
                next.setDuration(1);
                next.free();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:179:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void q(float f2) {
        int i2;
        float fMin;
        float fMin2;
        float f3 = 0.0f;
        float f4 = 1.0f;
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!l0.b.f2310k && !this.f2288o) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (GameData.v().player != null) {
                if (z.z().booleanValue()) {
                    this.f2283j.f();
                }
                this.f2283j.i(GameLevel.f3097d);
                if (z.v().w() == 1) {
                    this.f2278e.zoom = b.P().f2417b / 12;
                    GameLevel.n(true);
                }
                int iW = z.v().w();
                float f5 = f2269p;
                if (iW == 0) {
                    if (z.z().booleanValue()) {
                        z.v().getClass();
                        if (z.m() > 0) {
                            z.v().getClass();
                            fMin = z.u().s().f3287x;
                            z.v().getClass();
                            fMin2 = z.u().s().f3288y + 64;
                        } else {
                            fMin = GameData.v().player.f3092x;
                            fMin2 = GameData.v().player.f3093y + 64;
                            if (b.P().f2423h) {
                                float fMax = Math.max(fMin, this.f2282i);
                                float fMax2 = Math.max(fMin2, this.f2282i);
                                fMin = Math.min(fMax, b.O() - this.f2282i);
                                fMin2 = Math.min(fMax2, b.N() - this.f2282i);
                            }
                        }
                        Coords coords = this.f2287n;
                        coords.f3287x = (int) fMin;
                        coords.f3288y = (int) fMin2;
                        this.f2286m = b.A(VertexAttributes.Usage.Tangent, coords);
                        if (z.z().booleanValue()) {
                            if (Math.abs(this.f2278e.position.f1729a - this.f2286m.f3287x) > 320.0f) {
                                this.f2278e.position.f1729a = this.f2286m.f3287x;
                            }
                            if (Math.abs(this.f2278e.position.f1730b - this.f2286m.f3288y) > 320.0f) {
                                this.f2278e.position.f1730b = this.f2286m.f3288y;
                            }
                            com.badlogic.gdx.math.Vector3 aVar = this.f2278e.position;
                            float f6 = aVar.f1729a;
                            int i3 = (int) f6;
                            Coords coords2 = this.f2286m;
                            int i4 = coords2.f3287x;
                            if (i3 < i4) {
                                aVar.f1729a = (f2 * 80.0f) + f6;
                            }
                            float f7 = aVar.f1729a;
                            if (((int) f7) > i4) {
                                aVar.f1729a = f7 - (f2 * 80.0f);
                            }
                            float f8 = aVar.f1730b;
                            int i5 = (int) f8;
                            int i6 = coords2.f3288y;
                            if (i5 < i6) {
                                aVar.f1730b = (f2 * 80.0f) + f8;
                            }
                            float f9 = aVar.f1730b;
                            if (((int) f9) > i6) {
                                aVar.f1730b = f9 - (f2 * 80.0f);
                            }
                        } else {
                            com.badlogic.gdx.math.Vector3 aVar2 = this.f2278e.position;
                            Coords coords3 = this.f2286m;
                            aVar2.f1729a = coords3.f3287x;
                            aVar2.f1730b = coords3.f3288y;
                        }
                        if (z.z().booleanValue()) {
                            com.badlogic.gdx.math.Vector3 aVar3 = this.f2278e.position;
                            aVar3.f1729a = (z.l().floatValue() / (f5 * 6.0f)) + aVar3.f1729a;
                            OrthographicCamera orthographicCamera = this.f2278e;
                            float f10 = orthographicCamera.zoom;
                            if (f10 > 0.75f) {
                                orthographicCamera.zoom = f10 - (0.33f * f2);
                            }
                        } else {
                            OrthographicCamera orthographicCamera2 = this.f2278e;
                            if (orthographicCamera2.zoom > 1.5f) {
                                orthographicCamera2.zoom = 1.5f;
                            }
                            float f11 = orthographicCamera2.zoom;
                            if (f11 < 1.0f) {
                                orthographicCamera2.zoom = (f2 * 0.33f) + f11;
                            }
                            float f12 = orthographicCamera2.zoom;
                            if (f12 > 1.0f) {
                                orthographicCamera2.zoom = f12 - (0.33f * f2);
                            }
                        }
                    }
                }
                this.f2278e.update();
                if (z.z().booleanValue()) {
                    this.f2278e.position.f1729a -= z.l().floatValue() / (f5 * 6.0f);
                }
                if (z.v().w() != 2) {
                    float fK = (float) k();
                    if (GameLevel.l() && (GameData.v().player == null || GameData.v().player.i0())) {
                        fK = 99.0f;
                    }
                    float f13 = 1.0f / fK;
                    this.f2283j.c().setColor(f13, f13, f13, 1.0f);
                    this.f2283j.d(this.f2278e);
                    this.f2283j.h();
                    if (z.v().w() == 0) {
                        if (z.v().w() == 1) {
                            this.f2275b.a(0.93f, 0.93f, 0.93f, 0.93f);
                        } else if (b.P().f2424i > 0) {
                            float f14 = b.P().f2424i / 10;
                            float f15 = (0.08f * f14) + 0.1f;
                            this.f2275b.a(f15, f15, (0.05f * f14) + 0.4f, (f14 * 0.04f) + 0.5f);
                        } else if (b.P().f2423h) {
                            int iJ = FDUtils.j();
                            if (iJ >= 23 || iJ <= 5) {
                                this.f2275b.a(0.3f, 0.3f, 0.7f, 0.8f);
                            } else if (iJ == 6 || iJ == 22) {
                                this.f2275b.a(0.4f, 0.4f, 0.6f, 0.84f);
                            } else if (iJ == 7 || iJ == 21) {
                                this.f2275b.a(0.5f, 0.5f, 0.6f, 0.87f);
                            } else if (iJ == 8 || iJ == 20) {
                                this.f2275b.a(0.65f, 0.65f, 0.65f, 0.91f);
                            } else if (iJ >= 9 && iJ <= 19) {
                                this.f2275b.a(0.93f, 0.93f, 0.93f, 0.93f);
                            }
                        } else {
                            this.f2275b.a(0.93f, 0.93f, 0.93f, 0.93f);
                        }
                        this.f2275b.c(this.f2278e);
                        Iterator<MapLight> it = b.P().D.iterator();
                        while (it.hasNext()) {
                            MapLight next = it.next();
                            if (next == null || next.light == null || next.destroy) {
                                it.remove();
                                next.light.b();
                            } else {
                                next.c();
                            }
                        }
                        this.f2275b.e();
                    }
                    if (!GameLevel.l()) {
                        this.f2283j.c().begin();
                        Iterator<w> it2 = this.f2279f.iterator();
                        while (it2.hasNext()) {
                            w next2 = it2.next();
                            if (next2.c().booleanValue()) {
                                it2.remove();
                            } else {
                                next2.b((SpriteBatch) this.f2283j.c());
                            }
                        }
                        this.f2283j.c().end();
                    }
                    if (!GameLevel.l()) {
                        this.f2283j.c().begin();
                        boolean z2 = GameData.v().night;
                        com.badlogic.gdx.utils.Array<MapParticleEffectPool.MapPooledEffect> aVar4 = this.f2280g;
                        for (int i7 = aVar4.f1750b - 1; i7 >= 0; i7--) {
                            MapParticleEffectPool.MapPooledEffect mapPooledEffect = aVar4.get(i7);
                            if (mapPooledEffect != null) {
                                boolean zB0 = b.P().b0(mapPooledEffect.isoX / 32, mapPooledEffect.isoY / 32);
                                if (zB0 && !z2 && mapPooledEffect.nocturne) {
                                    zB0 = false;
                                }
                                if (zB0) {
                                    MapObject mapObject = mapPooledEffect.owner;
                                    if (mapObject != null) {
                                        mapPooledEffect.a(mapObject.f3092x + 32, mapObject.f3093y + 32);
                                    }
                                    mapPooledEffect.draw(this.f2283j.c(), f2);
                                    if (mapPooledEffect.isComplete()) {
                                        mapPooledEffect.free();
                                        aVar4.Array(i7);
                                    }
                                }
                            }
                        }
                        a.b<SpriteEffect> it3 = this.f2281h.iterator();
                        while (it3.hasNext()) {
                            SpriteEffect next3 = it3.next();
                            next3.getClass();
                            if (GameLevel.b() > next3.destructionTime) {
                                it3.remove();
                            } else {
                                this.f2283j.c().draw(next3.a(), next3.f3105x, next3.f3106y);
                            }
                        }
                        this.f2283j.c().end();
                    }
                    if (!GameLevel.l()) {
                        this.f2283j.c().begin();
                        TextureRegion textureRegion = GameAssets.f3357y;
                        int i8 = 0;
                        while (i8 < this.f2283j.f2393n.size()) {
                            if (this.f2283j.f2393n.get(i8) instanceof NPC) {
                                float fZ = ((NPC) this.f2283j.f2393n.get(i8)).sheet.z();
                                float fO = ((NPC) this.f2283j.f2393n.get(i8)).sheet.o();
                                int iF = ((NPC) this.f2283j.f2393n.get(i8)).sheet.stats.f();
                                boolean zL1 = Player.L1(this.f2283j.f2393n.get(i8).r());
                                if (fO == f3 || !this.f2283j.f2393n.get(i8).visibleToPlayer.booleanValue() || (!zL1 && fO == fZ)) {
                                    i2 = i8;
                                } else {
                                    int iH = this.f2283j.f2393n.get(i8).H();
                                    int i9 = iH + 64;
                                    int I = this.f2283j.f2393n.get(i8).I() + 70;
                                    float f16 = (fO / fZ) * 20.0f;
                                    float f17 = Gdx.graphics.getHeight() < 400 ? 2.0f : f4;
                                    this.f2283j.c().setColor(Color.GREEN);
                                    float f18 = i9;
                                    float f19 = I;
                                    i2 = i8;
                                    float f20 = f17;
                                    this.f2283j.c().draw(textureRegion, f18, f19, f16, f20);
                                    this.f2283j.c().setColor(Color.RED);
                                    this.f2283j.c().draw(textureRegion, f18 + f16, f19, 20.0f - f16, f20);
                                    this.f2283j.c().setColor(Color.YELLOW);
                                    float f21 = iH + 54;
                                    this.f2283j.c().draw(textureRegion, f21, r17 + 65, 10.0f, 10.0f);
                                    if (iF > 9) {
                                        GameAssets.f3332i0.draw(this.f2283j.c(), a.a.h(iF, ""), f21, r17 + 75);
                                    } else {
                                        GameAssets.f3332i0.draw(this.f2283j.c(), a.a.h(iF, ""), iH + 56, r17 + 75);
                                    }
                                    this.f2283j.c().setColor(Color.WHITE);
                                }
                            }
                            i8 = i2 + 1;
                            f3 = 0.0f;
                            f4 = 1.0f;
                        }
                        this.f2283j.c().end();
                    }
                    if (!GameLevel.l()) {
                        this.f2283j.c().begin();
                        ArrayList<MapSprite> arrayList = f2273u;
                        arrayList.clear();
                        if (GameData.v().player != null && !GameData.v().player.i0()) {
                            int size = GameLevel.f3096c.size();
                            for (int i10 = 0; i10 < size; i10++) {
                                ArrayList<MapActor> arrayList2 = GameLevel.f3096c;
                                if ((arrayList2.get(i10) instanceof NPC) && !((NPC) arrayList2.get(i10)).i0() && arrayList2.get(i10).visibleToPlayer.booleanValue() && !((NPC) arrayList2.get(i10)).k0()) {
                                    arrayList.add(arrayList2.get(i10));
                                }
                            }
                            int size2 = GameLevelData.y().size();
                            for (int i11 = 0; i11 < size2; i11++) {
                                if (GameLevelData.y().get(i11).visibleToPlayer.booleanValue()) {
                                    arrayList.add(GameLevelData.y().get(i11));
                                }
                            }
                            int size3 = arrayList.size();
                            for (int i12 = 0; i12 < size3; i12++) {
                                if (arrayList.get(i12) != null && arrayList.get(i12).getName() != null && !arrayList.get(i12).getName().equals("")) {
                                    int iS = b.s(arrayList.get(i12).B(), GameData.v().player.B());
                                    boolean zL12 = Player.L1(arrayList.get(i12).r());
                                    if ((zL12 && iS > 128.0f && iS <= 320) || (!zL12 && iS <= 96 && !Player.f3026d)) {
                                        if (zL12) {
                                            f2272t = GameAssets.f3338l0;
                                        } else {
                                            f2272t = GameAssets.f3340m0;
                                        }
                                        int i13 = arrayList.get(i12).f3092x + arrayList.get(i12).f3093y + 6;
                                        int I2 = arrayList.get(i12).I() + 90;
                                        BitmapFont bitmapFont = f2272t;
                                        String name = arrayList.get(i12).getName();
                                        this.f2276c.setText(bitmapFont, name);
                                        f2272t.draw(this.f2283j.c(), arrayList.get(i12).getName(), (int) (i13 - (r12.width / 2.0f)), I2);
                                    }
                                }
                            }
                        }
                        this.f2283j.c().end();
                    }
                }
                z.v().p();
            }
        }
        if (this.f2288o) {
            z.v().p();
        }
    }
}

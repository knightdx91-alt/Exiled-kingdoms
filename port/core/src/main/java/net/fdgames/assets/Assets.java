package net.fdgames.assets;

import a.a;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.Character;
import net.fdgames.Helpers.TextList;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Assets implements Disposable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Assets f3309a = new Assets();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Pixmap f3310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Pixmap f3311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Cursor f3312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Cursor f3313e;
    private TextureAtlas activables;
    private AssetManager assetManager;
    private TextureRegion[] femalePortraits;
    private TextureAtlas flags;
    private TextureAtlas items;
    private String lastMusicName;
    private TextureRegion[] malePortraits;
    private TextureAtlas mapItems;
    private TextureAtlas mapThumbs;
    private Music music;
    private TextureAtlas plants;
    private TextureAtlas projectiles;
    private TextureAtlas skills;
    private ArrayList<StaticNPCRegion> staticNPCs;
    private TextureAtlas ui_icons;

    /* JADX INFO: renamed from: net.fdgames.assets.Assets$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3314a;

        static {
            int[] iArr = new int[Character.Gender.values().length];
            f3314a = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3314a[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private Assets() {
    }

    public static TextureRegion a(String str) {
        Assets assets = f3309a;
        TextureAtlas.AtlasRegion atlasRegionFindRegion = assets.items.findRegion(str);
        if (atlasRegionFindRegion != null) {
            return atlasRegionFindRegion;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion2 = assets.activables.findRegion(str);
        if (atlasRegionFindRegion2 != null) {
            return atlasRegionFindRegion2;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion3 = assets.skills.findRegion(str);
        if (atlasRegionFindRegion3 != null) {
            return atlasRegionFindRegion3;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion4 = assets.flags.findRegion(str);
        if (atlasRegionFindRegion4 != null) {
            return atlasRegionFindRegion4;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion5 = assets.ui_icons.findRegion(str);
        if (atlasRegionFindRegion5 != null) {
            return atlasRegionFindRegion5;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion6 = assets.plants.findRegion(str);
        if (atlasRegionFindRegion6 != null) {
            return atlasRegionFindRegion6;
        }
        TextureAtlas.AtlasRegion atlasRegionFindRegion7 = assets.projectiles.findRegion(str);
        if (atlasRegionFindRegion7 != null) {
            return atlasRegionFindRegion7;
        }
        System.out.println(str + " not found");
        return new TextureRegion(b(str));
    }

    private static Texture b(String str) {
        Assets assets = f3309a;
        if (!assets.assetManager.q("data/ui/" + str + ".png")) {
            return assets.malePortraits[0].getTexture();
        }
        return (Texture) assets.assetManager.d("data/ui/" + str + ".png");
    }

    public static TextureRegion c(int i2, boolean z2) {
        StaticNPCRegion staticNPCRegion = f3309a.staticNPCs.get(i2);
        return z2 ? staticNPCRegion.region_l : staticNPCRegion.region_r;
    }

    public static Integer d(String str) {
        int i2 = 0;
        while (true) {
            Assets assets = f3309a;
            if (i2 >= assets.staticNPCs.size()) {
                return 0;
            }
            if (assets.staticNPCs.get(i2).name.equals(str)) {
                return Integer.valueOf(i2);
            }
            i2++;
        }
    }

    public static TextureRegion e(String str) {
        TextureAtlas.AtlasRegion atlasRegionFindRegion = f3309a.ui_icons.findRegion(str);
        if (atlasRegionFindRegion != null) {
            return atlasRegionFindRegion;
        }
        System.out.println(str + " not found");
        return new TextureRegion(b(str));
    }

    public static TextureRegion f(String str) {
        TextureAtlas.AtlasRegion atlasRegionFindRegion = f3309a.ui_icons.findRegion(str);
        if (atlasRegionFindRegion != null) {
            return atlasRegionFindRegion;
        }
        System.out.println(str.concat(" not found"));
        return new TextureRegion(b(str));
    }

    public static Skin g() {
        Assets assets = f3309a;
        if (!assets.assetManager.q("data/ui/" + p())) {
            assets.assetManager.r(Skin.class, "data/ui/" + p());
            assets.assetManager.c();
        }
        return (Skin) assets.assetManager.d("data/ui/" + p());
    }

    public static TextureAtlas.AtlasRegion h(String str) {
        TextureAtlas.AtlasRegion atlasRegionFindRegion = f3309a.mapItems.findRegion(str);
        if (atlasRegionFindRegion != null) {
            return atlasRegionFindRegion;
        }
        return null;
    }

    public static TextureRegionDrawable i() {
        Assets assets = f3309a;
        if (!assets.assetManager.q("data/ui/tavern.png")) {
            assets.assetManager.r(Texture.class, "data/ui/tavern.png");
        }
        assets.assetManager.c();
        return new TextureRegionDrawable(new TextureRegion((Texture) assets.assetManager.d("data/ui/tavern.png")));
    }

    public static TextureRegionDrawable j() {
        Assets assets = f3309a;
        if (!assets.assetManager.q("data/ui/town_hall.png")) {
            assets.assetManager.r(Texture.class, "data/ui/town_hall.png");
        }
        assets.assetManager.c();
        return new TextureRegionDrawable(new TextureRegion((Texture) assets.assetManager.d("data/ui/town_hall.png")));
    }

    public static void k() {
        AssetManager dVar = f3309a.assetManager;
        if (dVar != null) {
            dVar.c();
        }
    }

    public static int l(Character.Gender gender) {
        int iOrdinal = gender.ordinal();
        if (iOrdinal != 0) {
            return iOrdinal != 1 ? 0 : 41;
        }
        return 61;
    }

    public static TextureAtlas.AtlasRegion m(String str) {
        if (str.equals("NG")) {
            str = "H8";
        }
        if (str.equals("FT")) {
            str = "D9";
        }
        if (str.equals("NI")) {
            str = "F11";
        }
        if (str.equals("IM")) {
            str = "I13";
        }
        Assets assets = f3309a;
        TextureAtlas.AtlasRegion atlasRegionFindRegion = assets.mapThumbs.findRegion(str);
        return atlasRegionFindRegion != null ? atlasRegionFindRegion : assets.mapThumbs.findRegion("unknown");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static TextureRegion n(Character.Gender gender, int i2) {
        int iOrdinal = gender.ordinal();
        Assets assets = f3309a;
        if (iOrdinal == 0) {
            if (assets.malePortraits[i2] != null) {
                if (!assets.assetManager.q("data/graphics/portraits/male/" + i2 + ".png")) {
                    assets.assetManager.r(Texture.class, "data/graphics/portraits/male/" + i2 + ".png");
                    assets.assetManager.c();
                    assets.malePortraits[i2] = new TextureRegion((Texture) assets.assetManager.d("data/graphics/portraits/male/" + i2 + ".png"));
                    Texture texture = assets.malePortraits[i2].getTexture();
                    Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
                    texture.setFilter(textureFilter, textureFilter);
                }
            }
            return assets.malePortraits[i2];
        }
        if (iOrdinal != 1) {
            return assets.malePortraits[0];
        }
        if (assets.femalePortraits[i2] != null) {
            if (!assets.assetManager.q("data/graphics/portraits/female/" + i2 + ".png")) {
                assets.assetManager.r(Texture.class, "data/graphics/portraits/female/" + i2 + ".png");
                assets.assetManager.c();
                assets.femalePortraits[i2] = new TextureRegion((Texture) assets.assetManager.d("data/graphics/portraits/female/" + i2 + ".png"));
                Texture texture2 = assets.femalePortraits[i2].getTexture();
                Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Linear;
                texture2.setFilter(textureFilter2, textureFilter2);
            }
        }
        return assets.femalePortraits[i2];
    }

    public static TextureRegion o(String str) {
        TextureAtlas.AtlasRegion atlasRegionFindRegion = f3309a.projectiles.findRegion(str);
        if (atlasRegionFindRegion != null) {
            return atlasRegionFindRegion;
        }
        System.out.println(str + " not found");
        return new TextureRegion(b(str));
    }

    private static String p() {
        String str = ExiledKingdoms.f3378h ? "_desktop" : "";
        return Settings.M() ? a.l("uiskin_ru", str, ".json") : a.l("uiskin", str, ".json");
    }

    public static Texture q(String str) {
        String strL = a.l("data/sprites/", str, ".png");
        Assets assets = f3309a;
        if (!assets.assetManager.q(strL)) {
            assets.assetManager.r(Texture.class, strL);
        }
        assets.assetManager.c();
        return (Texture) assets.assetManager.d(strL);
    }

    public static void v() {
        Assets assets = f3309a;
        Texture.setAssetManager(assets.assetManager);
        assets.assetManager.z();
    }

    public static void w(float f2) {
        Music cVar = f3309a.music;
        if (cVar != null) {
            cVar.setVolume(f2);
        }
    }

    public static void y(String str) {
        Assets assets = f3309a;
        if (assets.assetManager.q("data/sprites/" + str + ".png")) {
            try {
                assets.assetManager.y("data/sprites/" + str + ".png");
            } catch (Exception unused) {
            }
        }
    }

    public static boolean z() {
        return f3309a.assetManager.z();
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        System.out.println("assets disposing...");
        AssetManager dVar = this.assetManager;
        if (dVar != null) {
            dVar.dispose();
            this.assetManager = null;
        }
        for (int i2 = 0; i2 <= 115; i2++) {
            TextureRegion textureRegion = this.malePortraits[i2];
            if (textureRegion != null && textureRegion.getTexture() != null) {
                this.malePortraits[i2].getTexture().dispose();
                this.malePortraits[i2] = null;
            }
        }
        for (int i3 = 0; i3 <= 67; i3++) {
            TextureRegion textureRegion2 = this.femalePortraits[i3];
            if (textureRegion2 != null && textureRegion2.getTexture() != null) {
                this.femalePortraits[i3].getTexture().dispose();
                this.femalePortraits[i3] = null;
            }
        }
        this.malePortraits = null;
        this.femalePortraits = null;
        Music cVar = this.music;
        if (cVar != null) {
            cVar.dispose();
            this.music = null;
        }
        Pixmap pixmap = f3310b;
        if (pixmap != null) {
            pixmap.dispose();
        }
        Pixmap pixmap2 = f3311c;
        if (pixmap2 != null) {
            pixmap2.dispose();
        }
    }

    public final void r(AssetManager dVar) {
        String str;
        String str2;
        this.assetManager = dVar;
        dVar.u(this);
        String str3 = "data/graphics/flags.pack";
        String str4 = "data/graphics/ui_icons.pack";
        String str5 = "data/graphics/skills.pack";
        if (ExiledKingdoms.f3378h) {
            this.assetManager.r(TextureAtlas.class, "data/graphics/items_linear.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/skills_linear.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/ui_icons_linear.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/flags_linear.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/activables_linear.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/plants_linear.pack");
        } else {
            this.assetManager.r(TextureAtlas.class, "data/graphics/items.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/skills.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/ui_icons.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/flags.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/activables.pack");
            this.assetManager.r(TextureAtlas.class, "data/graphics/plants.pack");
        }
        this.assetManager.r(TextureAtlas.class, "data/graphics/mapitems.pack");
        this.assetManager.r(TextureAtlas.class, "data/graphics/map_thumbnails.pack");
        this.assetManager.r(TextureAtlas.class, "data/sprites/projectiles/projectiles.pack");
        this.assetManager.r(Skin.class, "data/ui/" + p());
        this.assetManager.r(Texture.class, "data/ui/paperbg.png");
        this.assetManager.c();
        this.lastMusicName = "";
        Music cVarNewMusic = Gdx.audio.newMusic(Gdx.files.internal("data/ui/silent.mp3"));
        this.music = cVarNewMusic;
        if (cVarNewMusic != null) {
            cVarNewMusic.stop();
        }
        dVar.c();
        u();
        this.staticNPCs = new ArrayList<>();
        p.b bVar = new p.b();
        if (ExiledKingdoms.f3378h) {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            bVar.f1597e = textureFilter;
            bVar.f1598f = textureFilter;
        }
        Iterator it = TextList.a().iterator();
        while (true) {
            str = str3;
            str2 = str4;
            if (!it.hasNext()) {
                break;
            }
            this.assetManager.s(a.l("data/sprites/staticNPC/", (String) it.next(), ".png"), Texture.class, bVar);
            str3 = str;
            str4 = str2;
            it = it;
        }
        this.assetManager.c();
        Iterator it2 = TextList.a().iterator();
        while (it2.hasNext()) {
            String str6 = (String) it2.next();
            StaticNPCRegion staticNPCRegion = new StaticNPCRegion();
            staticNPCRegion.name = str6;
            Iterator it3 = it2;
            String str7 = str5;
            staticNPCRegion.region_l = new TextureRegion((Texture) this.assetManager.d("data/sprites/staticNPC/" + str6 + ".png"), 0, 0, 32, 64);
            staticNPCRegion.region_r = new TextureRegion((Texture) this.assetManager.d("data/sprites/staticNPC/" + str6 + ".png"), 32, 0, -32, 64);
            this.staticNPCs.add(staticNPCRegion);
            it2 = it3;
            str5 = str7;
        }
        String str8 = str5;
        if (ExiledKingdoms.f3378h) {
            this.items = (TextureAtlas) dVar.d("data/graphics/items_linear.pack");
            this.skills = (TextureAtlas) dVar.d("data/graphics/skills_linear.pack");
            this.ui_icons = (TextureAtlas) dVar.d("data/graphics/ui_icons_linear.pack");
            this.flags = (TextureAtlas) dVar.d("data/graphics/flags_linear.pack");
            this.activables = (TextureAtlas) dVar.d("data/graphics/activables_linear.pack");
            this.plants = (TextureAtlas) dVar.d("data/graphics/plants_linear.pack");
        } else {
            this.items = (TextureAtlas) dVar.d("data/graphics/items.pack");
            this.skills = (TextureAtlas) dVar.d(str8);
            this.ui_icons = (TextureAtlas) dVar.d(str2);
            this.flags = (TextureAtlas) dVar.d(str);
            this.activables = (TextureAtlas) dVar.d("data/graphics/activables.pack");
            this.plants = (TextureAtlas) dVar.d("data/graphics/plants.pack");
        }
        this.mapItems = (TextureAtlas) dVar.d("data/graphics/mapitems.pack");
        this.projectiles = (TextureAtlas) dVar.d("data/sprites/projectiles/projectiles.pack");
        this.mapThumbs = (TextureAtlas) dVar.d("data/graphics/map_thumbnails.pack");
        Skin skin = (Skin) this.assetManager.d("data/ui/" + p());
        Texture next = skin.getAtlas().getTextures().iterator().next();
        Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Nearest;
        next.setFilter(textureFilter2, textureFilter2);
        skin.getFont("menu-button-font").getData().setScale(Gdx.graphics.getWidth() / 1280.0f, Gdx.graphics.getHeight() / 720.0f);
        skin.getFont("menu-button-font").getData().markupEnabled = true;
        skin.add("windowbg", new NinePatch((Texture) this.assetManager.d("data/ui/paperbg.png"), 10, 10, 20, 10));
        skin.add("touchBackground", new Texture("data/ui/touchpad_base.png"));
        skin.add("touchKnob", new Texture("data/ui/touchpad_knob.png"));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = skin.getDrawable("touchBackground");
        touchpadStyle.knob = skin.getDrawable("touchKnob");
        skin.add("touchpad", touchpadStyle);
        dVar.c();
    }

    public final boolean s() {
        return this.assetManager == null;
    }

    public final void t(String str) {
        if (ExiledKingdoms.s) {
            GameMusic.a(str);
            return;
        }
        Music cVar = this.music;
        if (cVar != null) {
            cVar.stop();
            this.music.dispose();
            this.music = null;
        }
        if (Gdx.files.internal("data/music/" + str + ".mp3").exists()) {
            if (!this.lastMusicName.equals("")) {
                this.assetManager.y("data/music/" + this.lastMusicName + ".mp3");
            }
            this.assetManager.c();
            this.assetManager.r(c.class, "data/music/" + str + ".mp3");
            this.lastMusicName = str;
            this.assetManager.c();
            while (!this.assetManager.z()) {
            }
            this.music = (Music) this.assetManager.d("data/music/" + str + ".mp3");
            PrintStream printStream = System.out;
            StringBuilder sbU = a.u("PLAYING ", str, " at ");
            sbU.append(Settings.i());
            printStream.println(sbU.toString());
            this.music.setVolume(Settings.i());
            this.music.setLooping(true);
            this.music.play();
        }
    }

    public final void u() {
        Assets assets;
        this.malePortraits = new TextureRegion[116];
        this.femalePortraits = new TextureRegion[68];
        int i2 = 0;
        while (true) {
            assets = f3309a;
            if (i2 > 115) {
                break;
            }
            if (assets.assetManager.q("data/graphics/portraits/male/" + i2 + ".png")) {
                this.assetManager.y("data/graphics/portraits/male/" + i2 + ".png");
            }
            i2++;
        }
        for (int i3 = 0; i3 <= 67; i3++) {
            if (assets.assetManager.q("data/graphics/portraits/female/" + i3 + ".png")) {
                this.assetManager.y("data/graphics/portraits/female/" + i3 + ".png");
            }
        }
        for (int i4 = 0; i4 <= 5; i4++) {
            if (assets.assetManager.q("data/ui/skill_bg" + i4 + ".png")) {
                this.assetManager.y("data/ui/skill_bg" + i4 + ".png");
            }
        }
        this.assetManager.c();
        for (int i5 = 0; i5 <= 115; i5++) {
            this.malePortraits[i5] = null;
        }
        for (int i6 = 0; i6 <= 67; i6++) {
            this.femalePortraits[i6] = null;
        }
        this.assetManager.r(Texture.class, "data/graphics/portraits/male/0.png");
        this.assetManager.c();
        this.malePortraits[0] = new TextureRegion((Texture) this.assetManager.d("data/graphics/portraits/male/0.png"));
    }

    public final void x() {
        Music cVar = this.music;
        if (cVar != null) {
            cVar.stop();
        }
    }
}

package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;
import com.badlogic.gdx.audio.Sound;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class GameAssets {
    public static TextureRegion A = null;
    private static long A0 = 0;
    public static TextureRegion B = null;
    public static TextureRegion C = null;
    public static NinePatch D = null;
    public static NinePatch E = null;
    public static NinePatch F = null;
    public static NinePatch G = null;
    public static NinePatch H = null;
    public static NinePatch I = null;
    public static TextureRegion J = null;
    public static TextureRegion K = null;
    public static TextureRegion L = null;
    public static TextureRegion M = null;
    public static TextureRegion N = null;
    public static TextureRegion O = null;
    public static NinePatch P = null;
    public static NinePatch Q = null;
    public static NinePatch R = null;
    public static Label.LabelStyle S = null;
    public static Label.LabelStyle T = null;
    public static Label.LabelStyle U = null;
    public static Label.LabelStyle V = null;
    public static Label.LabelStyle W = null;
    public static Label.LabelStyle X = null;
    public static Label.LabelStyle Y = null;
    public static Label.LabelStyle Z = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a<TextureRegion> f3315a = null;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static Label.LabelStyle f3316a0 = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static float f3317b = 1.0f;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static Label.LabelStyle f3318b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Texture f3319c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static Label.LabelStyle f3320c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static MapParticleEffectPool f3321d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public static BitmapFont f3322d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static MapParticleEffectPool f3323e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static BitmapFont f3324e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static MapParticleEffectPool f3325f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static BitmapFont f3326f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static MapParticleEffectPool f3327g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static BitmapFont f3328g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static MapParticleEffectPool f3329h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static BitmapFont f3330h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static MapParticleEffectPool f3331i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static BitmapFont f3332i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static MapParticleEffectPool f3333j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static BitmapFont f3334j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static MapParticleEffectPool f3335k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static BitmapFont f3336k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static MapParticleEffectPool f3337l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static BitmapFont f3338l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static MapParticleEffectPool f3339m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public static BitmapFont f3340m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static MapParticleEffectPool f3341n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private static ArrayList<GameSound> f3342n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static MapParticleEffectPool f3343o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public static ImageButton.ImageButtonStyle f3344o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static MapParticleEffectPool f3345p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public static ImageButton.ImageButtonStyle f3346p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static MapParticleEffectPool f3347q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public static ImageButton.ImageButtonStyle f3348q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static int f3349r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public static CheckBox.CheckBoxStyle f3350r0;
    public static EffectAnimation[] s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static ImageButton.ImageButtonStyle f3351s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static int f3352t;
    public static ImageButton.ImageButtonStyle t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static TrapAnimation[] f3353u;
    public static ImageButton.ImageButtonStyle u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static SlashAnimation f3354v;
    public static Texture v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static Skin f3355w;
    public static Intro w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static ImageButton.ImageButtonStyle f3356x;
    public static Texture x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static TextureRegion f3357y;
    private static Slider.SliderStyle y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static TextureRegion f3358z;
    private static String z0;

    /* JADX INFO: renamed from: net.fdgames.assets.GameAssets$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3359a;

        static {
            int[] iArr = new int[Tint.values().length];
            f3359a = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3359a[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3359a[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3359a[0] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3359a[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum Tint {
        RED, GREEN, BLUE, BLACK, NONE;
    }

    static {
        new Color(1.0f, 1.0f, 1.0f, 1.0f);
        f3349r = 27;
        s = new EffectAnimation[27];
        f3352t = 2;
        f3353u = new TrapAnimation[2];
        f3342n0 = new ArrayList<>();
    }

    public static ImageButton.ImageButtonStyle a(TextureRegion textureRegion) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle((Button.ButtonStyle) f3355w.get(Button.ButtonStyle.class));
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        imageButtonStyle.imageUp = textureRegionDrawable;
        imageButtonStyle.imageDown = textureRegionDrawable;
        return imageButtonStyle;
    }

    public static ImageButton.ImageButtonStyle b() {
        return f3356x;
    }

    public static ImageButton.ImageButtonStyle c(TextureRegion textureRegion) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle((Button.ButtonStyle) f3355w.get(Button.ButtonStyle.class));
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(textureRegion, 5, 5, 115, 115));
        imageButtonStyle.imageUp = textureRegionDrawable;
        imageButtonStyle.imageDown = textureRegionDrawable;
        return imageButtonStyle;
    }

    public static void d() {
        f3315a = new a<>();
        f3354v = new SlashAnimation();
        l();
        Texture texture = new Texture("data/ui/worldmap.png");
        x0 = texture;
        if (ExiledKingdoms.f3378h) {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        }
        v0 = new Texture(Gdx.files.internal("data/ui/player_mark.png"));
        Texture texture2 = new Texture(Gdx.files.internal("data/sprites/white_dot.png"));
        f3319c = texture2;
        f3357y = new TextureRegion(texture2);
        Texture texture3 = new Texture(Gdx.files.internal("data/ui/transparent.png"));
        f3319c = texture3;
        f3358z = new TextureRegion(texture3);
        Texture texture4 = new Texture(Gdx.files.internal("data/ui/bag_holding.png"));
        f3319c = texture4;
        B = new TextureRegion(texture4);
        Texture texture5 = new Texture(Gdx.files.internal("data/sprites/loot.png"));
        f3319c = texture5;
        if (ExiledKingdoms.f3378h) {
            Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Linear;
            texture5.setFilter(textureFilter2, textureFilter2);
        }
        A = new TextureRegion(f3319c);
        Texture texture6 = new Texture(Gdx.files.internal("data/sprites/goldpile.png"));
        f3319c = texture6;
        C = new TextureRegion(texture6);
        e("data/ui/paperbg.png");
        D = new NinePatch(new Texture(Gdx.files.internal("data/ui/red_round_patch.png")), 5, 5, 4, 4);
        E = new NinePatch(new Texture(Gdx.files.internal("data/ui/yellow_round_patch.png")), 5, 5, 4, 4);
        F = new NinePatch(new Texture(Gdx.files.internal("data/ui/blue_round_patch.png")), 5, 5, 4, 4);
        I = new NinePatch(new Texture(Gdx.files.internal("data/ui/dark_round_patch.png")), 5, 5, 4, 4);
        G = new NinePatch(new Texture(Gdx.files.internal("data/ui/green_round_patch.png")), 5, 5, 4, 4);
        H = new NinePatch(new Texture(Gdx.files.internal("data/ui/orange_round_patch.png")), 5, 5, 4, 4);
        P = new NinePatch(new Texture(Gdx.files.internal("data/ui/smallsquare.png")), 10, 10, 20, 10);
        Q = new NinePatch(new Texture(Gdx.files.internal("data/ui/smallsquare_dark.png")), 10, 10, 20, 10);
        R = new NinePatch(new Texture(Gdx.files.internal("data/ui/smallsquare_red.png")), 10, 10, 20, 10);
        boolean zM = Settings.M();
        if (ExiledKingdoms.f3378h) {
            if (zM) {
                f3324e0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma20bold.fnt"));
            } else {
                f3324e0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal25bold.fnt"));
            }
            f3332i0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/levelFont.fnt"), Gdx.files.internal("data/ui/fonts/levelFont.png"), false);
            f3322d0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
            if (zM) {
                f3338l0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
                f3340m0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
                f3338l0.getData().setScale(0.6f);
                f3340m0.getData().setScale(0.6f);
            } else {
                f3338l0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3340m0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3338l0.getData().setScale(0.5f);
                f3340m0.getData().setScale(0.5f);
            }
            f3338l0.getCache().setColor(Color.RED);
            f3340m0.getCache().setColor(Color.GREEN);
            if (zM) {
                BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma38outline.fnt"));
                f3336k0 = bitmapFont;
                bitmapFont.getData().setScale(1.0f);
            } else {
                BitmapFont bitmapFont2 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3336k0 = bitmapFont2;
                bitmapFont2.getData().setScale(1.0f);
            }
            if (zM) {
                f3334j0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma31outline.fnt"));
            } else {
                f3334j0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal29bold_white_outline.fnt"));
            }
            if (zM) {
                f3326f0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma20white.fnt"));
            } else {
                f3326f0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal24.fnt"));
            }
            f3328g0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma20white.fnt"));
            if (zM) {
                f3330h0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma20white.fnt"));
            } else {
                f3330h0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal26.fnt"));
            }
        } else {
            if (zM) {
                f3324e0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma24bold.fnt"));
            } else {
                f3324e0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal29bold.fnt"));
            }
            f3332i0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/levelFont.fnt"), Gdx.files.internal("data/ui/fonts/levelFont.png"), false);
            f3322d0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
            if (zM) {
                f3338l0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
                f3340m0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma32.fnt"));
                f3338l0.getData().setScale(0.6f);
                f3340m0.getData().setScale(0.6f);
            } else {
                f3338l0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3340m0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3338l0.getData().setScale(0.5f);
                f3340m0.getData().setScale(0.5f);
            }
            f3338l0.getCache().setColor(Color.RED);
            f3340m0.getCache().setColor(Color.GREEN);
            if (zM) {
                BitmapFont bitmapFont3 = new BitmapFont(Gdx.files.internal("data/ui/fonts/arialnarrow40bold.fnt"));
                f3336k0 = bitmapFont3;
                bitmapFont3.getData().setScale(2.0f);
            } else {
                BitmapFont bitmapFont4 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal44_outline.fnt"));
                f3336k0 = bitmapFont4;
                bitmapFont4.getData().setScale(1.0f);
            }
            if (zM) {
                f3334j0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/arialnarrow40bold.fnt"));
            } else {
                f3334j0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal36bold_white_outline.fnt"));
            }
            if (zM) {
                f3326f0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma25white.fnt"));
            } else {
                f3326f0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal30.fnt"));
            }
            f3328g0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma23white.fnt"));
            if (zM) {
                f3330h0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/tahoma27white.fnt"));
            } else {
                f3330h0 = new BitmapFont(Gdx.files.internal("data/ui/fonts/immortal33.fnt"));
            }
        }
        f3324e0.getData().markupEnabled = true;
        f3322d0.getData().markupEnabled = true;
        f3336k0.getData().markupEnabled = true;
        f3334j0.getData().markupEnabled = true;
        f3326f0.getData().markupEnabled = true;
        f3328g0.getData().markupEnabled = true;
        f3330h0.getData().markupEnabled = true;
        BitmapFont bitmapFont5 = f3326f0;
        Color color = Color.WHITE;
        S = new Label.LabelStyle(bitmapFont5, new Color(color));
        T = new Label.LabelStyle(f3330h0, new Color(color));
        U = new Label.LabelStyle(f3330h0, new Color(color));
        V = new Label.LabelStyle(f3330h0, new Color(color));
        W = new Label.LabelStyle(f3330h0, new Color(color));
        X = new Label.LabelStyle(f3330h0, new Color(color));
        f3316a0 = new Label.LabelStyle(f3324e0, new Color(color));
        Y = new Label.LabelStyle(f3334j0, new Color(color));
        f3318b0 = new Label.LabelStyle(f3328g0, new Color(color));
        f3320c0 = new Label.LabelStyle(f3336k0, new Color(color));
        Z = new Label.LabelStyle(f3334j0, new Color(Color.rgb565(110.0f, 160.0f, 255.0f)));
        new Label.LabelStyle(f3334j0, Color.GRAY);
        Skin skinG = Assets.g();
        f3355w = skinG;
        skinG.getFont("menu-button-font").getData().setScale(Gdx.graphics.getWidth() / 1280.0f, Gdx.graphics.getHeight() / 720.0f);
        f3355w.getFont("menu-button-font").getData().markupEnabled = true;
        Texture texture7 = new Texture(Gdx.files.internal("data/ui/attackButton.png"));
        f3319c = texture7;
        TextureRegion textureRegion = new TextureRegion(texture7);
        TextureRegion textureRegion2 = new TextureRegion(textureRegion);
        textureRegion2.flip(true, true);
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle((Button.ButtonStyle) f3355w.get(Button.ButtonStyle.class));
        f3356x = imageButtonStyle;
        imageButtonStyle.imageUp = new TextureRegionDrawable(textureRegion);
        f3356x.imageDown = new TextureRegionDrawable(textureRegion2);
        Texture texture8 = new Texture(Gdx.files.internal("data/ui/mend.png"));
        f3319c = texture8;
        TextureRegion textureRegion3 = new TextureRegion(texture8);
        ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle((Button.ButtonStyle) f3355w.get(Button.ButtonStyle.class));
        u0 = imageButtonStyle2;
        imageButtonStyle2.imageUp = new TextureRegionDrawable(textureRegion3);
        new NinePatch(new Texture(Gdx.files.internal("data/ui/paperbg.png")), 10, 10, 20, 10);
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        y0 = sliderStyle;
        sliderStyle.background = f3355w.getDrawable("default-slider");
        y0.background.setMinWidth(600.0f);
        y0.background.setMinHeight(6.0f);
        y0.knob = f3355w.getDrawable("default-slider-knob");
        y0.knob.setMinHeight(20.0f);
        y0.knob.setMinWidth(15.0f);
        f3344o0 = new ImageButton.ImageButtonStyle();
        Texture texture9 = new Texture(Gdx.files.internal("data/ui/arrow_left.png"));
        f3319c = texture9;
        TextureRegion textureRegion4 = new TextureRegion(texture9);
        textureRegion4.flip(true, false);
        f3344o0.up = new TextureRegionDrawable(new TextureRegion(f3319c));
        Texture texture10 = new Texture(Gdx.files.internal("data/ui/arrow_left_disabled.png"));
        f3319c = texture10;
        Texture.TextureFilter textureFilter3 = Texture.TextureFilter.Linear;
        texture10.setFilter(textureFilter3, textureFilter3);
        TextureRegion textureRegion5 = new TextureRegion(f3319c);
        textureRegion5.flip(true, false);
        f3344o0.disabled = new TextureRegionDrawable(new TextureRegion(f3319c));
        ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
        f3346p0 = imageButtonStyle3;
        imageButtonStyle3.up = new TextureRegionDrawable(textureRegion4);
        f3346p0.disabled = new TextureRegionDrawable(textureRegion5);
        ImageButton.ImageButtonStyle imageButtonStyle4 = new ImageButton.ImageButtonStyle();
        t0 = imageButtonStyle4;
        imageButtonStyle4.up = new NinePatchDrawable(P);
        t0.down = new NinePatchDrawable(Q);
        f3319c = new Texture(Gdx.files.internal("data/ui/arrow_up.png"));
        ImageButton.ImageButtonStyle imageButtonStyle5 = new ImageButton.ImageButtonStyle();
        f3348q0 = imageButtonStyle5;
        imageButtonStyle5.up = new TextureRegionDrawable(new TextureRegion(f3319c));
        Texture texture11 = new Texture(Gdx.files.internal("data/ui/arrow_up_disabled.png"));
        f3319c = texture11;
        f3348q0.disabled = new TextureRegionDrawable(new TextureRegion(texture11));
        Texture texture12 = new Texture(Gdx.files.internal("data/tmx/doors.png"));
        f3319c = texture12;
        J = new TextureRegion(texture12, 0, 0, 64, VertexAttributes.Usage.Tangent);
        K = new TextureRegion(f3319c, 64, 0, 64, VertexAttributes.Usage.Tangent);
        Texture texture13 = new Texture(Gdx.files.internal("data/tmx/door_steel.png"));
        f3319c = texture13;
        L = new TextureRegion(texture13, 0, 0, 64, VertexAttributes.Usage.Tangent);
        M = new TextureRegion(f3319c, 64, 0, 64, VertexAttributes.Usage.Tangent);
        Texture texture14 = new Texture(Gdx.files.internal("data/tmx/black_tile.png"));
        f3319c = texture14;
        new TextureRegion(texture14);
        Texture texture15 = new Texture(Gdx.files.internal("data/sprites/circle_red.png"));
        f3319c = texture15;
        texture15.setFilter(textureFilter3, textureFilter3);
        N = new TextureRegion(f3319c);
        Texture texture16 = new Texture(Gdx.files.internal("data/sprites/circle_red2.png"));
        f3319c = texture16;
        texture16.setFilter(textureFilter3, textureFilter3);
        O = new TextureRegion(f3319c);
        f3351s0 = new ImageButton.ImageButtonStyle();
        Texture texture17 = new Texture(Gdx.files.internal("data/ui/random.png"));
        f3319c = texture17;
        texture17.setFilter(textureFilter3, textureFilter3);
        f3351s0.up = new TextureRegionDrawable(new TextureRegion(f3319c));
        f3350r0 = new CheckBox.CheckBoxStyle();
        Texture texture18 = new Texture(Gdx.files.internal("data/ui/roundcheck_off.png"));
        f3319c = texture18;
        f3350r0.checkboxOff = new TextureRegionDrawable(new TextureRegion(texture18));
        Texture texture19 = new Texture(Gdx.files.internal("data/ui/roundcheck_on.png"));
        f3319c = texture19;
        f3350r0.checkboxOn = new TextureRegionDrawable(new TextureRegion(texture19));
        f3350r0.font = f3324e0;
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("data/particle/blood.p"), Gdx.files.internal("data/particle"));
        f3321d = new MapParticleEffectPool(particleEffect, 1, 3);
        ParticleEffect particleEffect2 = new ParticleEffect();
        particleEffect2.load(Gdx.files.internal("data/particle/flame.p"), Gdx.files.internal("data/particle"));
        f3323e = new MapParticleEffectPool(particleEffect2, 1, 2);
        ParticleEffect particleEffect3 = new ParticleEffect();
        particleEffect3.load(Gdx.files.internal("data/particle/ice.p"), Gdx.files.internal("data/particle"));
        f3325f = new MapParticleEffectPool(particleEffect3, 1, 2);
        ParticleEffect particleEffect4 = new ParticleEffect();
        particleEffect4.load(Gdx.files.internal("data/particle/toxic.p"), Gdx.files.internal("data/particle"));
        f3327g = new MapParticleEffectPool(particleEffect4, 1, 2);
        ParticleEffect particleEffect5 = new ParticleEffect();
        particleEffect5.load(Gdx.files.internal("data/particle/death.p"), Gdx.files.internal("data/particle"));
        f3329h = new MapParticleEffectPool(particleEffect5, 1, 2);
        ParticleEffect particleEffect6 = new ParticleEffect();
        particleEffect6.load(Gdx.files.internal("data/particle/healing.p"), Gdx.files.internal("data/particle"));
        f3331i = new MapParticleEffectPool(particleEffect6, 1, 1);
        ParticleEffect particleEffect7 = new ParticleEffect();
        particleEffect7.load(Gdx.files.internal("data/particle/levelup.p"), Gdx.files.internal("data/particle"));
        f3333j = new MapParticleEffectPool(particleEffect7, 1, 1);
        ParticleEffect particleEffect8 = new ParticleEffect();
        particleEffect8.load(Gdx.files.internal("data/particle/secret.p"), Gdx.files.internal("data/particle"));
        f3335k = new MapParticleEffectPool(particleEffect8, 1, 1);
        ParticleEffect particleEffect9 = new ParticleEffect();
        particleEffect9.load(Gdx.files.internal("data/particle/torch.p"), Gdx.files.internal("data/particle"));
        f3337l = new MapParticleEffectPool(particleEffect9, 5, 60);
        ParticleEffect particleEffect10 = new ParticleEffect();
        particleEffect10.load(Gdx.files.internal("data/particle/bonfire.p"), Gdx.files.internal("data/particle"));
        f3339m = new MapParticleEffectPool(particleEffect10, 2, 30);
        ParticleEffect particleEffect11 = new ParticleEffect();
        particleEffect11.load(Gdx.files.internal("data/particle/speed.p"), Gdx.files.internal("data/particle"));
        f3341n = new MapParticleEffectPool(particleEffect11, 1, 5);
        ParticleEffect particleEffect12 = new ParticleEffect();
        particleEffect12.load(Gdx.files.internal("data/particle/flame_aura.p"), Gdx.files.internal("data/particle"));
        f3343o = new MapParticleEffectPool(particleEffect12, 0, 5);
        ParticleEffect particleEffect13 = new ParticleEffect();
        particleEffect13.load(Gdx.files.internal("data/particle/casting.p"), Gdx.files.internal("data/particle"));
        f3345p = new MapParticleEffectPool(particleEffect13, 0, 3);
        ParticleEffect particleEffect14 = new ParticleEffect();
        particleEffect14.load(Gdx.files.internal("data/particle/spell.p"), Gdx.files.internal("data/particle"));
        f3347q = new MapParticleEffectPool(particleEffect14, 0, 2);
        EffectAnimation effectAnimation = new EffectAnimation("shield");
        EffectAnimation[] effectAnimationArr = s;
        effectAnimationArr[0] = effectAnimation;
        effectAnimationArr[1] = new EffectAnimation("stunned");
        effectAnimationArr[2] = new EffectAnimation("slowed");
        effectAnimationArr[3] = new EffectAnimation("damage");
        effectAnimationArr[4] = new EffectAnimation("evasion");
        effectAnimationArr[5] = new EffectAnimation("resist");
        effectAnimationArr[6] = new EffectAnimation("sacred_fire");
        effectAnimationArr[7] = new EffectAnimation("shock");
        effectAnimationArr[8] = new EffectAnimation("halo");
        effectAnimationArr[9] = new EffectAnimation("fire_1");
        effectAnimationArr[10] = new EffectAnimation("fire_2");
        effectAnimationArr[11] = new EffectAnimation("fire_3");
        effectAnimationArr[12] = new EffectAnimation("fire_small");
        effectAnimationArr[13] = new EffectAnimation("fire_small_2");
        effectAnimationArr[14] = new EffectAnimation("fire_medium");
        effectAnimationArr[15] = new EffectAnimation("gas");
        effectAnimationArr[16] = new EffectAnimation("gas_2");
        effectAnimationArr[17] = new EffectAnimation("gas_weak");
        effectAnimationArr[18] = new EffectAnimation("gas_death");
        effectAnimationArr[19] = new EffectAnimation("bloodlust");
        effectAnimationArr[20] = new EffectAnimation("fire_medium_purple");
        effectAnimationArr[21] = new EffectAnimation("smoke");
        effectAnimationArr[22] = new EffectAnimation("glowing_ashes");
        effectAnimationArr[23] = new EffectAnimation("gate");
        effectAnimationArr[24] = new EffectAnimation("beam");
        effectAnimationArr[25] = new EffectAnimation("beam_blue");
        effectAnimationArr[26] = new EffectAnimation("blizzard");
        TrapAnimation trapAnimation = new TrapAnimation("spikes");
        TrapAnimation[] trapAnimationArr = f3353u;
        trapAnimationArr[0] = trapAnimation;
        trapAnimationArr[1] = new TrapAnimation("beartrap");
    }

    public static TextureRegionDrawable e(String str) {
        return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(str))));
    }

    public static void f() {
        System.out.println("GameAssets.dispose()");
        Texture texture = f3319c;
        if (texture != null) {
            texture.dispose();
            f3319c = null;
        }
        a<AnimationSet> aVar = AnimationLoader.f3300a;
        if (aVar != null && aVar.f1750b > 0) {
            a.b<AnimationSet> it = aVar.iterator();
            while (it.hasNext()) {
                it.next().d();
            }
        }
        AnimationLoader.f3300a = null;
        Intro intro = w0;
        if (intro != null) {
            ArrayList<IntroStage> arrayList = intro.stages;
            if (arrayList != null) {
                Iterator<IntroStage> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    it2.next().a();
                }
            }
            intro.stages = null;
        }
        Texture texture2 = x0;
        if (texture2 != null) {
            texture2.dispose();
            x0 = null;
        }
        SlashAnimation slashAnimation = f3354v;
        if (slashAnimation != null) {
            slashAnimation.a();
        }
        a<TextureRegion> aVar2 = f3315a;
        if (aVar2 != null) {
            a.b<TextureRegion> it3 = aVar2.iterator();
            while (it3.hasNext()) {
                it3.next().getTexture().dispose();
            }
        }
        f3315a = null;
        g();
    }

    public static void g() {
        ArrayList<GameSound> arrayList = f3342n0;
        if (arrayList == null) {
            return;
        }
        Iterator<GameSound> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().sound.dispose();
        }
        f3342n0.clear();
        f3342n0 = null;
    }

    public static int h(String str) {
        int i2 = 0;
        while (true) {
            a<AnimationSet> aVar = AnimationLoader.f3300a;
            if (i2 >= aVar.f1750b) {
                if (!Gdx.files.internal("data/sprites/" + str + ".png").exists()) {
                    return -1;
                }
                AnimationLoader.f3300a.a(new AnimationSet(str));
                return -1;
            }
            if (aVar.get(i2).name.equals(str)) {
                return i2;
            }
            i2++;
        }
    }

    public static AnimationSet i(int i2) {
        a<AnimationSet> aVar = AnimationLoader.f3300a;
        int i3 = aVar.f1750b;
        if (i3 > i2) {
            return aVar.get(i2);
        }
        if (i3 > 0) {
            return aVar.get(0);
        }
        AnimationSet animationSet = new AnimationSet("composite/male_clothes");
        AnimationLoader.f3300a.a(animationSet);
        return animationSet;
    }

    public static Animation j(String str) {
        for (int i2 = 0; i2 < f3349r; i2++) {
            EffectAnimation[] effectAnimationArr = s;
            if (effectAnimationArr[i2].name.equals(str)) {
                return effectAnimationArr[i2].animation;
            }
        }
        return null;
    }

    public static Slider.SliderStyle k() {
        return y0;
    }

    public static void l() {
        m("swing");
        m("coin");
        m("item");
        m("item2");
        m("open1");
        m("close1");
        m("potion");
        m("hit");
        m("sword");
        m("journal");
        m("levelup");
        m("sword");
        m("heal");
        m("male_grunt_1");
        m("male_grunt_2");
        m("male_grunt_3");
        m("male_grunt_4");
        m("female_grunt_1");
        m("female_grunt_2");
        m("female_grunt_3");
        m("male_death");
        m("female_death");
        m("click");
        m("trap");
        m("buff1");
        m("buff2");
        m("buff3");
        m("spell1");
        m("spell2");
        m("spell3");
        m("load");
        m("explo");
    }

    public static void m(String str) {
        String strTrim = str.trim();
        if (strTrim.equals("")) {
            return;
        }
        if (f3342n0 == null) {
            f3342n0 = new ArrayList<>();
        }
        Iterator<GameSound> it = f3342n0.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (it.next().id.equals(strTrim)) {
                z2 = true;
            }
        }
        if (z2) {
            return;
        }
        if (!Gdx.files.internal("data/sounds/" + strTrim + ".mp3").exists()) {
            System.out.println("sound file not found:'data/sounds/" + strTrim + ".mp3");
            return;
        }
        GameSound gameSound = new GameSound();
        Sound dVarNewSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/" + strTrim + ".mp3"));
        gameSound.sound = dVarNewSound;
        dVarNewSound.play(0.0f);
        gameSound.id = strTrim;
        f3342n0.add(gameSound);
    }

    public static void n(String str) {
        Iterator it = Arrays.asList(str.trim().split(";")).iterator();
        while (it.hasNext()) {
            m((String) it.next());
        }
    }

    public static void o(String str) {
        if (Settings.m() == 0.0f || str.equals("")) {
            return;
        }
        String str2 = z0;
        if (str2 == null || !str.equals(str2) || System.currentTimeMillis() >= A0 + 100) {
            for (GameSound gameSound : f3342n0) {
                if (gameSound.id.equals(str)) {
                    gameSound.sound.play(Settings.m());
                    z0 = str;
                    A0 = System.currentTimeMillis();
                    return;
                }
            }
            m(str);
            for (GameSound gameSound2 : f3342n0) {
                if (gameSound2.id.equals(str)) {
                    gameSound2.sound.play(Settings.m());
                    z0 = str;
                    A0 = System.currentTimeMillis();
                    return;
                }
            }
        }
    }

    public static void p(String str) {
        o((String) Arrays.asList(str.trim().split(";")).get(FDUtils.b(0, r2.size() - 1)));
    }
}

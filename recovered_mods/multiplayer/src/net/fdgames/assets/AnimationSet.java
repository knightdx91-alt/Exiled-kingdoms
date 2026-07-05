package net.fdgames.assets;

import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.a;
import java.util.EnumMap;
import java.util.Locale;
import net.fdgames.GameEntities.MapActor;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class AnimationSet {
    public Animation<TextureRegion> animation;
    public String name;
    private EnumMap<MapActor.ActorState, EnumMap<SpriteFacing, Animation>> stateFacingAnimations;
    private Texture texture;
    public Texture texture_ranged;

    /* JADX INFO: renamed from: net.fdgames.assets.AnimationSet$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3522a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f3523b;

        static {
            int[] iArr = new int[SpriteFacing.values().length];
            f3523b = iArr;
            try {
                iArr[4] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3523b[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3523b[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3523b[1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3523b[0] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[MapActor.Facing.values().length];
            f3522a = iArr2;
            try {
                iArr2[4] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3522a[2] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3522a[3] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3522a[1] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3522a[0] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3522a[6] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3522a[5] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3522a[7] = 8;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class SpriteFacing {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final SpriteFacing f3524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final SpriteFacing f3525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final SpriteFacing f3526d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final SpriteFacing f3527e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final SpriteFacing f3528f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static final /* synthetic */ SpriteFacing[] f3529g;

        static {
            SpriteFacing spriteFacing = new SpriteFacing("U", 0);
            f3524b = spriteFacing;
            SpriteFacing spriteFacing2 = new SpriteFacing(JNrsKSCxIEXndG.tyrIhULju, 1);
            f3525c = spriteFacing2;
            SpriteFacing spriteFacing3 = new SpriteFacing("R", 2);
            f3526d = spriteFacing3;
            SpriteFacing spriteFacing4 = new SpriteFacing("RD", 3);
            f3527e = spriteFacing4;
            SpriteFacing spriteFacing5 = new SpriteFacing("D", 4);
            f3528f = spriteFacing5;
            f3529g = new SpriteFacing[]{spriteFacing, spriteFacing2, spriteFacing3, spriteFacing4, spriteFacing5};
        }

        private SpriteFacing() {
            throw null;
        }

        public static SpriteFacing valueOf(String str) {
            return (SpriteFacing) Enum.valueOf(SpriteFacing.class, str);
        }

        public static SpriteFacing[] values() {
            return (SpriteFacing[]) f3529g.clone();
        }
    }

    private class frameInfo {
        Boolean flipped;
        int row;

        public frameInfo(int i2, Boolean bool) {
            this.row = i2;
            this.flipped = bool;
        }
    }

    public AnimationSet(String str) {
        boolean z2;
        int i2;
        int i3;
        int i4;
        char c2;
        int i5;
        char c3;
        int i6;
        boolean zContains = str.contains("_large");
        this.name = str.toLowerCase(Locale.ENGLISH).trim();
        this.stateFacingAnimations = new EnumMap<>(MapActor.ActorState.class);
        this.texture = Assets.q(this.name);
        int i7 = 1;
        if (Gdx.files.internal("data/sprites/" + this.name + "_ranged.png").exists()) {
            Texture textureQ = Assets.q(this.name + "_ranged");
            this.texture_ranged = textureQ;
            textureQ.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
            z2 = true;
        } else {
            z2 = false;
        }
        this.texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        this.stateFacingAnimations.put(MapActor.ActorState.f3287c, c(this.texture, 1, 2, 8, Animation.PlayMode.LOOP, 0.085f, zContains));
        EnumMap<MapActor.ActorState, EnumMap<SpriteFacing, Animation>> enumMap = this.stateFacingAnimations;
        MapActor.ActorState actorState = MapActor.ActorState.f3286b;
        Texture texture = this.texture;
        Animation.PlayMode playMode = Animation.PlayMode.NORMAL;
        enumMap.put(actorState, c(texture, 1, 1, 1, playMode, 1.0f, zContains));
        this.stateFacingAnimations.put(MapActor.ActorState.f3291g, c(this.texture, 1, 1, 1, playMode, 1.0f, zContains));
        this.stateFacingAnimations.put(MapActor.ActorState.f3288d, c(this.texture, 6, 1, 4, playMode, 0.35f, zContains));
        MapActor.ActorState actorState2 = MapActor.ActorState.f3296l;
        if (z2) {
            this.stateFacingAnimations.put(actorState2, c(this.texture_ranged, 1, 1, 4, playMode, 0.35f, zContains));
        } else {
            this.stateFacingAnimations.put(actorState2, c(this.texture, 6, 1, 4, playMode, 0.35f, zContains));
        }
        this.stateFacingAnimations.put(MapActor.ActorState.f3293i, c(this.texture, 6, 1, 4, playMode, 0.175f, zContains));
        this.stateFacingAnimations.put(MapActor.ActorState.f3290f, c(this.texture, 6, 1, 4, Animation.PlayMode.LOOP_PINGPONG, 0.2f, zContains));
        this.stateFacingAnimations.put(MapActor.ActorState.f3295k, c(this.texture, 1, 1, 1, playMode, 1.0f, zContains));
        Texture texture2 = this.texture;
        int i8 = zContains ? 200 : 140;
        this.animation = b(texture2, 11, 1, 4, i8, i8, playMode, 0.1f);
        EnumMap<SpriteFacing, Animation> enumMap2 = new EnumMap<>(SpriteFacing.class);
        for (SpriteFacing spriteFacing : SpriteFacing.values()) {
            enumMap2.put(spriteFacing, this.animation);
        }
        this.stateFacingAnimations.put(MapActor.ActorState.f3289e, enumMap2);
        Texture texture3 = this.texture;
        Animation.PlayMode playMode2 = Animation.PlayMode.NORMAL;
        int i9 = zContains ? 200 : 140;
        this.animation = b(texture3, 11, 4, 1, i9, i9, playMode2, 0.3f);
        EnumMap<SpriteFacing, Animation> enumMap3 = new EnumMap<>(SpriteFacing.class);
        for (SpriteFacing spriteFacing2 : SpriteFacing.values()) {
            enumMap3.put(spriteFacing2, this.animation);
        }
        this.stateFacingAnimations.put(MapActor.ActorState.f3294j, enumMap3);
        EnumMap<MapActor.ActorState, EnumMap<SpriteFacing, Animation>> enumMap4 = this.stateFacingAnimations;
        MapActor.ActorState actorState3 = MapActor.ActorState.f3292h;
        Texture texture4 = this.texture;
        Animation.PlayMode playMode3 = Animation.PlayMode.NORMAL;
        EnumMap<SpriteFacing, Animation> enumMap5 = new EnumMap<>(SpriteFacing.class);
        SpriteFacing[] spriteFacingArrValues = SpriteFacing.values();
        int length = spriteFacingArrValues.length;
        int i10 = 0;
        while (i10 < length) {
            SpriteFacing spriteFacing3 = spriteFacingArrValues[i10];
            a aVar = new a();
            int iOrdinal = spriteFacing3.ordinal();
            int i11 = 8;
            frameInfo frameinfo = iOrdinal != 0 ? iOrdinal != i7 ? iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? new frameInfo(i7, Boolean.FALSE) : new frameInfo(9, Boolean.TRUE) : new frameInfo(10, Boolean.FALSE) : new frameInfo(9, Boolean.FALSE) : new frameInfo(8, Boolean.FALSE) : new frameInfo(7, Boolean.FALSE);
            int i12 = 0;
            while (i12 < i11) {
                if (frameinfo.flipped.booleanValue()) {
                    i3 = -140;
                    i2 = 1;
                } else {
                    i2 = 0;
                    i3 = 140;
                }
                int i13 = i12;
                SpriteFacing[] spriteFacingArr = spriteFacingArrValues;
                Texture texture5 = texture4;
                a aVar2 = aVar;
                Texture texture6 = texture4;
                SpriteFacing spriteFacing4 = spriteFacing3;
                aVar2.a(new TextureRegion(texture5, (i2 + 2) * 140, (frameinfo.row - 1) * 140, i3, 140));
                if (frameinfo.flipped.booleanValue() || (i6 = frameinfo.row) < 6) {
                    i4 = 10;
                } else {
                    i4 = 10;
                    if (i6 < 10) {
                        frameinfo = new frameInfo(i6 + 1, Boolean.FALSE);
                        c3 = 7;
                        c2 = '\t';
                    }
                    i5 = 8;
                    i12 = i13 + 1;
                    aVar = aVar2;
                    i11 = i5;
                    spriteFacingArrValues = spriteFacingArr;
                    spriteFacing3 = spriteFacing4;
                    texture4 = texture6;
                }
                if (frameinfo.row == i4) {
                    c2 = '\t';
                    frameinfo = new frameInfo(9, Boolean.TRUE);
                    c3 = 7;
                    i5 = 8;
                    i12 = i13 + 1;
                    aVar = aVar2;
                    i11 = i5;
                    spriteFacingArrValues = spriteFacingArr;
                    spriteFacing3 = spriteFacing4;
                    texture4 = texture6;
                } else {
                    c2 = '\t';
                    if (frameinfo.flipped.booleanValue() && frameinfo.row == 9) {
                        i5 = 8;
                        frameinfo = new frameInfo(8, Boolean.TRUE);
                        c3 = 7;
                    } else {
                        i5 = 8;
                        if (frameinfo.flipped.booleanValue() && frameinfo.row == 8) {
                            frameinfo = new frameInfo(7, Boolean.TRUE);
                            c3 = 7;
                        } else {
                            c3 = 7;
                            frameinfo = (frameinfo.flipped.booleanValue() && frameinfo.row == 7) ? new frameInfo(6, Boolean.FALSE) : new frameInfo(1, Boolean.FALSE);
                        }
                    }
                    i12 = i13 + 1;
                    aVar = aVar2;
                    i11 = i5;
                    spriteFacingArrValues = spriteFacingArr;
                    spriteFacing3 = spriteFacing4;
                    texture4 = texture6;
                }
            }
            Animation animation = new Animation(0.0625f, aVar);
            animation.setPlayMode(playMode3);
            enumMap5.put(spriteFacing3, animation);
            i10++;
            i7 = 1;
            spriteFacingArrValues = spriteFacingArrValues;
            texture4 = texture4;
        }
        enumMap4.put(actorState3, enumMap5);
    }

    public static Animation b(Texture texture, int i2, int i3, int i4, int i5, int i6, Animation.PlayMode playMode, float f2) {
        a aVar = new a();
        for (int i7 = 0; i7 < i4; i7++) {
            aVar.a(new TextureRegion(texture, ((i3 - 1) + i7) * i5, (i2 - 1) * i6, i5, i6));
        }
        return new Animation(f2, aVar, playMode);
    }

    private static EnumMap c(Texture texture, int i2, int i3, int i4, Animation.PlayMode playMode, float f2, boolean z2) {
        EnumMap enumMap = new EnumMap(SpriteFacing.class);
        int i5 = i2;
        for (SpriteFacing spriteFacing : SpriteFacing.values()) {
            int i6 = z2 ? 200 : 140;
            enumMap.put(spriteFacing, b(texture, i5, i3, i4, i6, i6, playMode, f2));
            i5++;
        }
        return enumMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Animation a(MapActor.ActorState actorState, MapActor.Facing facing) {
        EnumMap<SpriteFacing, Animation> enumMap = this.stateFacingAnimations.get(actorState);
        int iOrdinal = facing.ordinal();
        SpriteFacing spriteFacing = SpriteFacing.f3526d;
        SpriteFacing spriteFacing2 = SpriteFacing.f3527e;
        SpriteFacing spriteFacing3 = SpriteFacing.f3525c;
        SpriteFacing spriteFacing4 = SpriteFacing.f3528f;
        if (iOrdinal == 0) {
            spriteFacing = SpriteFacing.f3524b;
        } else if (iOrdinal == 1) {
            spriteFacing = spriteFacing3;
        } else if (iOrdinal != 2) {
            if (iOrdinal == 3 || iOrdinal == 5) {
                spriteFacing = spriteFacing2;
            } else if (iOrdinal != 6) {
                if (iOrdinal != 7) {
                    spriteFacing = spriteFacing4;
                }
            }
        }
        return enumMap.get(spriteFacing);
    }

    public final void d() {
        this.stateFacingAnimations.clear();
        Assets.y(this.name);
    }
}

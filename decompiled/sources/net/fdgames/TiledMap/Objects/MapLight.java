package net.fdgames.TiledMap.Objects;

import com.badlogic.gdx.graphics.Color;
import m0.b;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;
import q.d;
import q.f;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MapLight {
    private Color color;
    private boolean colorup;
    public boolean destroy;
    float destructionTime;
    private boolean flash;
    float isoX;
    float isoY;
    float lastUpdate;
    public d light;
    public boolean nocturnal;
    MapObject owner;
    int owner_id;
    int radius;
    int targetradius;
    lightType type;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    int f3289x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    int f3290y;

    /* JADX INFO: renamed from: net.fdgames.TiledMap.Objects.MapLight$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3291a;

        static {
            int[] iArr = new int[lightType.values().length];
            f3291a = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3291a[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3291a[5] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3291a[2] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3291a[3] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3291a[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    private static final class lightType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final lightType f3292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final lightType f3293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final lightType f3294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final lightType f3295d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final lightType f3296e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ lightType[] f3297f;

        static {
            lightType lighttype = new lightType("TORCH", 0);
            f3292a = lighttype;
            lightType lighttype2 = new lightType("AURA_YELLOW", 1);
            lightType lighttype3 = new lightType("FLASH_RED", 2);
            f3293b = lighttype3;
            lightType lighttype4 = new lightType("FLASH_WHITE", 3);
            f3294c = lighttype4;
            lightType lighttype5 = new lightType("AURA_BLUE", 4);
            f3295d = lighttype5;
            lightType lighttype6 = new lightType("FLASH_BLUE", 5);
            f3296e = lighttype6;
            f3297f = new lightType[]{lighttype, lighttype2, lighttype3, lighttype4, lighttype5, lighttype6};
        }

        private lightType() {
            throw null;
        }

        public static lightType valueOf(String str) {
            return (lightType) Enum.valueOf(lightType.class, str);
        }

        public static lightType[] values() {
            return (lightType[]) f3297f.clone();
        }
    }

    public MapLight(float f2, int i2, int i3, String str) {
        this(0, 0, str, i3, f2, false);
        this.owner_id = i2;
    }

    public final void a(f fVar) {
        this.light = new d(fVar, this.color, this.radius);
        int i2 = this.owner_id;
        if (i2 == 0) {
            b(this.f3289x, this.f3290y);
        } else {
            MapObject mapObjectH = GameLevel.h(i2);
            this.owner = mapObjectH;
            int i3 = mapObjectH.f3092x;
            this.f3289x = i3;
            int i4 = mapObjectH.f3093y;
            this.f3290y = i4;
            b(i3, i4);
        }
        int iOrdinal = this.type.ordinal();
        if (iOrdinal == 0) {
            this.color = new Color(1.0f, 0.78f, 0.0f, 1.0f);
            return;
        }
        if (iOrdinal == 1) {
            this.color = new Color(1.0f, 1.0f, 0.0f, 1.0f);
            return;
        }
        if (iOrdinal == 2) {
            this.color = new Color(1.0f, 0.2f, 0.0f, 1.0f);
            return;
        }
        if (iOrdinal == 3) {
            this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            return;
        }
        if (iOrdinal == 4) {
            this.color = new Color(0.1f, 0.3f, 1.0f, 1.0f);
        } else if (iOrdinal != 5) {
            this.color = new Color(1.0f, 0.78f, 0.0f, 1.0f);
        } else {
            this.color = new Color(0.3f, 0.5f, 1.0f, 1.0f);
        }
    }

    public final void b(int i2, int i3) {
        float f2 = (i2 + i3) - 64;
        this.isoX = f2;
        float f3 = (i3 - i2) / 2;
        this.isoY = f3;
        this.light.h(f2, f3);
    }

    public final void c() {
        MapObject mapObject = this.owner;
        if (mapObject != null) {
            this.f3289x = mapObject.f3092x;
            this.f3290y = mapObject.f3093y + 64;
        }
        if (this.destructionTime > 0.0f && GameData.v().u() > this.destructionTime) {
            this.destroy = true;
            return;
        }
        if (this.nocturnal && !GameData.v().night) {
            this.light.d(0.0f);
            return;
        }
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        if (GameData.v().player == null || b.r(this.f3289x, this.f3290y, GameData.v().player.f3092x, GameData.v().player.f3093y) >= 480) {
            return;
        }
        if (this.owner != null) {
            b(this.f3289x, this.f3290y);
        }
        if (GameData.v().u() - this.lastUpdate > 0.14f) {
            this.lastUpdate = GameData.v().u();
            int iOrdinal = this.type.ordinal();
            if (iOrdinal == 0) {
                this.color.f1679g = (FDUtils.b(0, 25) * 0.01f) + 0.75f;
                this.light.setColor(this.color);
                this.light.d(FDUtils.b(0, 16) + (this.radius - 8));
                return;
            }
            if (iOrdinal == 1) {
                this.light.setColor(this.color);
                this.light.d(FDUtils.b(0, 16) + (this.radius - 8));
                return;
            }
            if (iOrdinal == 2) {
                this.light.setColor(this.color);
                d dVar = this.light;
                dVar.d(dVar.a() + 16.0f);
                Color color = this.color;
                float f2 = color.f1677a - 0.15f;
                color.f1677a = f2;
                if (f2 < 0.15d) {
                    this.destroy = true;
                    return;
                }
                return;
            }
            if (iOrdinal == 3) {
                this.light.setColor(this.color);
                d dVar2 = this.light;
                dVar2.d(dVar2.a() + 16.0f);
                Color color2 = this.color;
                float f3 = color2.f1677a - 0.15f;
                color2.f1677a = f3;
                if (f3 < 0.15d) {
                    this.destroy = true;
                    return;
                }
                return;
            }
            if (iOrdinal == 4) {
                this.light.setColor(this.color);
                this.light.d(FDUtils.b(0, 16) + (this.radius - 8));
                return;
            }
            if (iOrdinal != 5) {
                return;
            }
            this.light.setColor(this.color);
            d dVar3 = this.light;
            dVar3.d(dVar3.a() + 16.0f);
            Color color3 = this.color;
            float f4 = color3.f1677a - 0.15f;
            color3.f1677a = f4;
            if (f4 < 0.15d) {
                this.destroy = true;
            }
        }
    }

    public MapLight(int i2, int i3, String str, int i4, float f2, boolean z2) {
        this.destructionTime = 0.0f;
        this.destroy = false;
        this.flash = false;
        this.lastUpdate = 0.0f;
        this.color = new Color(1.0f, 0.78f, 0.0f, 1.0f);
        this.colorup = false;
        this.f3289x = i2 + 64;
        this.f3290y = i3 + 64;
        this.owner = null;
        boolean zEquals = str.equals("torch");
        lightType lighttype = lightType.f3292a;
        if (!zEquals && !str.equals("fire") && !str.equals("torch") && !str.equals("aura_yellow")) {
            if (str.equals("flash_red")) {
                lighttype = lightType.f3293b;
            } else if (str.equals("flash_white")) {
                lighttype = lightType.f3294c;
            } else if (str.equals("flash_blue")) {
                lighttype = lightType.f3296e;
            } else if (str.equals("aura_blue")) {
                lighttype = lightType.f3295d;
            }
        }
        this.type = lighttype;
        this.nocturnal = z2;
        if (str.contains("flash")) {
            this.flash = true;
        }
        this.radius = i4;
        if (i4 == 0) {
            this.radius = 300;
        }
        if (f2 > 0.0f) {
            this.destructionTime = GameData.v().u() + f2;
        } else {
            this.destructionTime = 0.0f;
        }
    }
}

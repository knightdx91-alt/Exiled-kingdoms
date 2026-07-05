package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import n0.WOA.IUcMoQOkPHcA;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public interface Lootable {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class LootableType {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final LootableType f3279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final LootableType f3280c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final LootableType f3281d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ LootableType[] f3282e;

        static {
            LootableType lootableType = new LootableType("MAPCONTAINER", 0);
            f3279b = lootableType;
            LootableType lootableType2 = new LootableType(IUcMoQOkPHcA.OhCV, 1);
            f3280c = lootableType2;
            LootableType lootableType3 = new LootableType("SHOP", 2);
            f3281d = lootableType3;
            f3282e = new LootableType[]{lootableType, lootableType2, lootableType3};
        }

        private LootableType() {
            throw null;
        }

        public static LootableType valueOf(String str) {
            return (LootableType) Enum.valueOf(LootableType.class, str);
        }

        public static LootableType[] values() {
            return (LootableType[]) f3282e.clone();
        }
    }

    float a();

    void b();

    void c(int i2);

    void d();

    void e();

    boolean f();

    boolean g();

    int getItem(int i2);

    String getName();

    LootableType getType();

    int h();

    boolean i(int i2);

    Boolean isEmpty();

    Items j();

    boolean k(int i2);

    TextureRegion l();

    void removeItem(int i2);
}

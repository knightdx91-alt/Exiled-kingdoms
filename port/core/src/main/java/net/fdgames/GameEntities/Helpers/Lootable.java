package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public interface Lootable {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class LootableType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final LootableType f3064a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final LootableType f3065b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final LootableType f3066c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ LootableType[] f3067d;

        static {
            LootableType lootableType = new LootableType("MAPCONTAINER", 0);
            f3064a = lootableType;
            LootableType lootableType2 = new LootableType("LOOT", 1);
            f3065b = lootableType2;
            LootableType lootableType3 = new LootableType("SHOP", 2);
            f3066c = lootableType3;
            f3067d = new LootableType[]{lootableType, lootableType2, lootableType3};
        }

        private LootableType() {
            throw null;
        }

        public static LootableType valueOf(String str) {
            return (LootableType) Enum.valueOf(LootableType.class, str);
        }

        public static LootableType[] values() {
            return (LootableType[]) f3067d.clone();
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

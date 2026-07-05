package net.fdgames.GameEntities.CharacterSheet;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SheetEffect {
    public float expiration;
    public int level;
    public SheetEffectType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class SheetEffectType {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final SheetEffectType f3218b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ SheetEffectType[] f3219c;

        static {
            SheetEffectType sheetEffectType = new SheetEffectType("XPBonus", 0);
            f3218b = sheetEffectType;
            f3219c = new SheetEffectType[]{sheetEffectType};
        }

        private SheetEffectType() {
            throw null;
        }

        public static SheetEffectType valueOf(String str) {
            return (SheetEffectType) Enum.valueOf(SheetEffectType.class, str);
        }

        public static SheetEffectType[] values() {
            return (SheetEffectType[]) f3219c.clone();
        }
    }

    public static SheetEffectType a(String str) {
        if (str.toLowerCase(Locale.ENGLISH).equals("xpbonus")) {
            return SheetEffectType.f3218b;
        }
        return null;
    }
}

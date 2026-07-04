package net.fdgames.GameEntities.CharacterSheet;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SheetEffect {
    public float expiration;
    public int level;
    public SheetEffectType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class SheetEffectType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SheetEffectType f3003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final /* synthetic */ SheetEffectType[] f3004b;

        static {
            SheetEffectType sheetEffectType = new SheetEffectType("XPBonus", 0);
            f3003a = sheetEffectType;
            f3004b = new SheetEffectType[]{sheetEffectType};
        }

        private SheetEffectType() {
            throw null;
        }

        public static SheetEffectType valueOf(String str) {
            return (SheetEffectType) Enum.valueOf(SheetEffectType.class, str);
        }

        public static SheetEffectType[] values() {
            return (SheetEffectType[]) f3004b.clone();
        }
    }

    public static SheetEffectType a(String str) {
        if (str.toLowerCase(Locale.ENGLISH).equals("xpbonus")) {
            return SheetEffectType.f3003a;
        }
        return null;
    }
}

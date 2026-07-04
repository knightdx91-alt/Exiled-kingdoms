package net.fdgames.GameEntities.CharacterSheet;

import java.util.Locale;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SheetEffect {
    public float expiration;
    public int level;
    public SheetEffectType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum SheetEffectType {
        XPBonus;
    }

    public static SheetEffectType a(String str) {
        if (str.toLowerCase(Locale.ENGLISH).equals("xpbonus")) {
            return SheetEffectType.XPBonus;
        }
        return null;
    }
}

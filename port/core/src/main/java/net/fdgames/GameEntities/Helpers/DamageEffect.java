package net.fdgames.GameEntities.Helpers;

import a.a;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class DamageEffect {
    private int chance;
    public int level;
    public EffectType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum EffectType {
        STUN, SLOW, CRUSADER, PARALYZE, EMP;
    }

    public DamageEffect(EffectType effectType, int i2, int i3) {
        this.type = effectType;
        this.chance = i3;
        this.level = i2;
    }

    public final boolean a() {
        return FDUtils.b(1, 100) < this.chance;
    }

    public final void b() {
        this.chance = 0;
    }

    public final String toString() {
        EffectType effectType = this.type;
        String strB = "";
        if (effectType == EffectType.EMP) {
            return "";
        }
        int iOrdinal = effectType.ordinal();
        if (iOrdinal == 0) {
            strB = GameString.b("PROC_STUN", false);
        } else if (iOrdinal == 1) {
            strB = GameString.b("PROC_SLOW", false);
        } else if (iOrdinal != 2 && iOrdinal == 3) {
            strB = GameString.b("ITEM_PARALYSIS", false);
        }
        if (strB.length() > 5) {
            strB = strB.substring(0, 5) + ".";
        }
        int i2 = this.chance;
        return (i2 <= 0 || i2 >= 100) ? strB : a.p(a.t(strB, "("), this.chance, "%)");
    }

    public DamageEffect() {
    }
}

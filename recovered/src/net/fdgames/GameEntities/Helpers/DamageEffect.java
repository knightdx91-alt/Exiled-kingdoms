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
    public static final class EffectType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final EffectType f3054a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final EffectType f3055b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final EffectType f3056c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final EffectType f3057d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final EffectType f3058e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ EffectType[] f3059f;

        static {
            EffectType effectType = new EffectType("STUN", 0);
            f3054a = effectType;
            EffectType effectType2 = new EffectType("SLOW", 1);
            f3055b = effectType2;
            EffectType effectType3 = new EffectType("CRUSADER", 2);
            f3056c = effectType3;
            EffectType effectType4 = new EffectType("PARALYZE", 3);
            f3057d = effectType4;
            EffectType effectType5 = new EffectType("EMP", 4);
            f3058e = effectType5;
            f3059f = new EffectType[]{effectType, effectType2, effectType3, effectType4, effectType5};
        }

        private EffectType() {
            throw null;
        }

        public static EffectType valueOf(String str) {
            return (EffectType) Enum.valueOf(EffectType.class, str);
        }

        public static EffectType[] values() {
            return (EffectType[]) f3059f.clone();
        }
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
        if (effectType == EffectType.f3058e) {
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

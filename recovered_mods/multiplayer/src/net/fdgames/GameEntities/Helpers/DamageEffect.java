package net.fdgames.GameEntities.Helpers;

import a.a;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class DamageEffect {
    private int chance;
    public int level;
    public EffectType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class EffectType {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final EffectType f3269b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final EffectType f3270c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final EffectType f3271d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final EffectType f3272e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final EffectType f3273f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static final /* synthetic */ EffectType[] f3274g;

        static {
            EffectType effectType = new EffectType("STUN", 0);
            f3269b = effectType;
            EffectType effectType2 = new EffectType("SLOW", 1);
            f3270c = effectType2;
            EffectType effectType3 = new EffectType("CRUSADER", 2);
            f3271d = effectType3;
            EffectType effectType4 = new EffectType("PARALYZE", 3);
            f3272e = effectType4;
            EffectType effectType5 = new EffectType("EMP", 4);
            f3273f = effectType5;
            f3274g = new EffectType[]{effectType, effectType2, effectType3, effectType4, effectType5};
        }

        private EffectType() {
            throw null;
        }

        public static EffectType valueOf(String str) {
            return (EffectType) Enum.valueOf(EffectType.class, str);
        }

        public static EffectType[] values() {
            return (EffectType[]) f3274g.clone();
        }
    }

    public DamageEffect() {
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
        if (effectType == EffectType.f3273f) {
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
        return (i2 <= 0 || i2 >= 100) ? strB : a.p(a.s(strB, "("), this.chance, "%)");
    }
}

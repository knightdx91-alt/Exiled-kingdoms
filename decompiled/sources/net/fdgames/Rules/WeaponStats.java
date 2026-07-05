package net.fdgames.Rules;

import java.util.Locale;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageEffect;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class WeaponStats {
    public boolean hasProc;
    public String id;
    public boolean light;
    public int procChance;
    public DamageEffect.EffectType procEffect;
    public int procLevel;
    public float ranged_speed;
    public int ranged_type;
    public int secondary_Damage;
    public Damage.DamageType secondary_damageType;
    public String sprite;
    public boolean staff;
    public boolean wand;
    public int minDamage = 1;
    public int maxDamage = 3;
    public int critChance = 1;
    public int speed = 10;
    public int reach = 1;
    public boolean twohanded = false;
    public boolean ranged = false;
    public Damage.DamageType damageType = Damage.DamageType.f3046a;
    public boolean has_secondary_damage = false;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class weaponType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final weaponType f3282a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final weaponType f3283b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final weaponType f3284c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final weaponType f3285d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ weaponType[] f3286e;

        static {
            weaponType weapontype = new weaponType("handweapon", 0);
            f3282a = weapontype;
            weaponType weapontype2 = new weaponType("twohanded", 1);
            f3283b = weapontype2;
            weaponType weapontype3 = new weaponType("ranged", 2);
            f3284c = weapontype3;
            weaponType weapontype4 = new weaponType("light", 3);
            f3285d = weapontype4;
            f3286e = new weaponType[]{weapontype, weapontype2, weapontype3, weapontype4};
        }

        private weaponType() {
            throw null;
        }

        public static weaponType valueOf(String str) {
            return (weaponType) Enum.valueOf(weaponType.class, str);
        }

        public static weaponType[] values() {
            return (weaponType[]) f3286e.clone();
        }
    }

    public static DamageEffect.EffectType d(String str) {
        Locale locale = Locale.ENGLISH;
        if (str.toLowerCase(locale).contains("paralysis")) {
            return DamageEffect.EffectType.f3057d;
        }
        if (str.toLowerCase(locale).equals("stun")) {
            return DamageEffect.EffectType.f3054a;
        }
        if (str.toLowerCase(locale).equals("slow")) {
            return DamageEffect.EffectType.f3055b;
        }
        if (str.toLowerCase(locale).equals("emp")) {
            return DamageEffect.EffectType.f3058e;
        }
        return null;
    }

    public final weaponType a() {
        return this.light ? weaponType.f3285d : this.ranged ? weaponType.f3284c : this.twohanded ? weaponType.f3283b : weaponType.f3282a;
    }

    public final boolean b() {
        return (this.twohanded || this.light || this.ranged) ? false : true;
    }

    public final boolean c() {
        return this.wand || this.staff;
    }
}

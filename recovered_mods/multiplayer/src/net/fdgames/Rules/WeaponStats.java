package net.fdgames.Rules;

import java.util.Locale;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageEffect;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
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
    public Damage.DamageType damageType = Damage.DamageType.f3261b;
    public boolean has_secondary_damage = false;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class weaponType {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final weaponType f3503b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final weaponType f3504c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final weaponType f3505d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final weaponType f3506e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ weaponType[] f3507f;

        static {
            weaponType weapontype = new weaponType("handweapon", 0);
            f3503b = weapontype;
            weaponType weapontype2 = new weaponType("twohanded", 1);
            f3504c = weapontype2;
            weaponType weapontype3 = new weaponType("ranged", 2);
            f3505d = weapontype3;
            weaponType weapontype4 = new weaponType("light", 3);
            f3506e = weapontype4;
            f3507f = new weaponType[]{weapontype, weapontype2, weapontype3, weapontype4};
        }

        private weaponType() {
            throw null;
        }

        public static weaponType valueOf(String str) {
            return (weaponType) Enum.valueOf(weaponType.class, str);
        }

        public static weaponType[] values() {
            return (weaponType[]) f3507f.clone();
        }
    }

    public static DamageEffect.EffectType d(String str) {
        Locale locale = Locale.ENGLISH;
        if (str.toLowerCase(locale).contains("paralysis")) {
            return DamageEffect.EffectType.f3272e;
        }
        if (str.toLowerCase(locale).equals("stun")) {
            return DamageEffect.EffectType.f3269b;
        }
        if (str.toLowerCase(locale).equals("slow")) {
            return DamageEffect.EffectType.f3270c;
        }
        if (str.toLowerCase(locale).equals("emp")) {
            return DamageEffect.EffectType.f3273f;
        }
        return null;
    }

    public final weaponType a() {
        return this.light ? weaponType.f3506e : this.ranged ? weaponType.f3505d : this.twohanded ? weaponType.f3504c : weaponType.f3503b;
    }

    public final boolean b() {
        return (this.twohanded || this.light || this.ranged) ? false : true;
    }

    public final boolean c() {
        return this.wand || this.staff;
    }
}

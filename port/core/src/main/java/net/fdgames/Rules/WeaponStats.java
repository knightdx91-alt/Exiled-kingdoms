package net.fdgames.Rules;

import java.util.Locale;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.DamageEffect;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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
    public Damage.DamageType damageType = Damage.DamageType.Normal;
    public boolean has_secondary_damage = false;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum weaponType {
        handweapon, twohanded, ranged, light;
    }

    public static DamageEffect.EffectType d(String str) {
        Locale locale = Locale.ENGLISH;
        if (str.toLowerCase(locale).contains("paralysis")) {
            return DamageEffect.EffectType.PARALYZE;
        }
        if (str.toLowerCase(locale).equals("stun")) {
            return DamageEffect.EffectType.STUN;
        }
        if (str.toLowerCase(locale).equals("slow")) {
            return DamageEffect.EffectType.SLOW;
        }
        if (str.toLowerCase(locale).equals("emp")) {
            return DamageEffect.EffectType.EMP;
        }
        return null;
    }

    public final weaponType a() {
        return this.light ? weaponType.light : this.ranged ? weaponType.ranged : this.twohanded ? weaponType.twohanded : weaponType.handweapon;
    }

    public final boolean b() {
        return (this.twohanded || this.light || this.ranged) ? false : true;
    }

    public final boolean c() {
        return this.wand || this.staff;
    }
}

package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.CharacterSheet.AttributesSet;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Damage {
    public AttributesSet attackerAttributes;
    public int hp;
    public boolean projectile;
    public DamageType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class DamageType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final DamageType f3046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final DamageType f3047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final DamageType f3048c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final DamageType f3049d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final DamageType f3050e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final DamageType f3051f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final DamageType f3052g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static final /* synthetic */ DamageType[] f3053h;

        static {
            DamageType damageType = new DamageType("Normal", 0);
            f3046a = damageType;
            DamageType damageType2 = new DamageType("Fire", 1);
            f3047b = damageType2;
            DamageType damageType3 = new DamageType("Cold", 2);
            f3048c = damageType3;
            DamageType damageType4 = new DamageType("Shock", 3);
            f3049d = damageType4;
            DamageType damageType5 = new DamageType("Death", 4);
            f3050e = damageType5;
            DamageType damageType6 = new DamageType("Toxic", 5);
            f3051f = damageType6;
            DamageType damageType7 = new DamageType("Spirit", 6);
            f3052g = damageType7;
            f3053h = new DamageType[]{damageType, damageType2, damageType3, damageType4, damageType5, damageType6, damageType7};
        }

        private DamageType() {
            throw null;
        }

        public static DamageType valueOf(String str) {
            return (DamageType) Enum.valueOf(DamageType.class, str);
        }

        public static DamageType[] values() {
            return (DamageType[]) f3053h.clone();
        }
    }

    public Damage(DamageType damageType, int i2, boolean z2) {
        this.type = damageType;
        this.hp = i2;
        this.projectile = z2;
    }

    public static DamageType b(String str) {
        return str.trim().equals("f") ? DamageType.f3047b : str.trim().equals("s") ? DamageType.f3049d : str.trim().equals("c") ? DamageType.f3048c : str.trim().equals("d") ? DamageType.f3050e : str.trim().equals("t") ? DamageType.f3051f : (str.trim().equals("sp") || str.trim().equals("p")) ? DamageType.f3052g : DamageType.f3046a;
    }

    public final TextureRegion a() {
        switch (this.type.ordinal()) {
            case 1:
                return Assets.a("fire");
            case 2:
                return Assets.a("cold");
            case 3:
                return Assets.a("shock");
            case 4:
                return Assets.a("death");
            case 5:
                return Assets.a("poison");
            case 6:
                return Assets.a("holy");
            default:
                return null;
        }
    }

    public final String toString() {
        switch (this.type.ordinal()) {
            case 1:
                return "Fire";
            case 2:
                return "Cold";
            case 3:
                return "Shock";
            case 4:
                return "Death";
            case 5:
                return "Toxic";
            case 6:
                return "Spirit";
            default:
                return "Physical";
        }
    }

    public Damage() {
        this.type = DamageType.f3046a;
    }
}

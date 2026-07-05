package net.fdgames.GameEntities.Helpers;

import android.support.v4.app.mFy.fApIihhYHIP;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameEntities.CharacterSheet.AttributesSet;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Damage {
    public AttributesSet attackerAttributes;
    public int hp;
    public boolean projectile;
    public DamageType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class DamageType {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final DamageType f3261b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final DamageType f3262c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final DamageType f3263d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final DamageType f3264e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final DamageType f3265f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final DamageType f3266g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final DamageType f3267h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static final /* synthetic */ DamageType[] f3268i;

        static {
            DamageType damageType = new DamageType("Normal", 0);
            f3261b = damageType;
            DamageType damageType2 = new DamageType("Fire", 1);
            f3262c = damageType2;
            DamageType damageType3 = new DamageType("Cold", 2);
            f3263d = damageType3;
            DamageType damageType4 = new DamageType("Shock", 3);
            f3264e = damageType4;
            DamageType damageType5 = new DamageType(fApIihhYHIP.UtTkxI, 4);
            f3265f = damageType5;
            DamageType damageType6 = new DamageType("Toxic", 5);
            f3266g = damageType6;
            DamageType damageType7 = new DamageType("Spirit", 6);
            f3267h = damageType7;
            f3268i = new DamageType[]{damageType, damageType2, damageType3, damageType4, damageType5, damageType6, damageType7};
        }

        private DamageType() {
            throw null;
        }

        public static DamageType valueOf(String str) {
            return (DamageType) Enum.valueOf(DamageType.class, str);
        }

        public static DamageType[] values() {
            return (DamageType[]) f3268i.clone();
        }
    }

    public Damage() {
        this.type = DamageType.f3261b;
    }

    public Damage(DamageType damageType, int i2, boolean z2) {
        this.type = damageType;
        this.hp = i2;
        this.projectile = z2;
    }

    public static DamageType b(String str) {
        return str.trim().equals("f") ? DamageType.f3262c : str.trim().equals("s") ? DamageType.f3264e : str.trim().equals("c") ? DamageType.f3263d : str.trim().equals("d") ? DamageType.f3265f : str.trim().equals("t") ? DamageType.f3266g : (str.trim().equals("sp") || str.trim().equals("p")) ? DamageType.f3267h : DamageType.f3261b;
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
}

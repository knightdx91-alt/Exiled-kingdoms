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
    public enum DamageType {
        Normal, Fire, Cold, Shock, Death, Toxic, Spirit;
    }

    public Damage(DamageType damageType, int i2, boolean z2) {
        this.type = damageType;
        this.hp = i2;
        this.projectile = z2;
    }

    public static DamageType b(String str) {
        return str.trim().equals("f") ? DamageType.Fire : str.trim().equals("s") ? DamageType.Shock : str.trim().equals("c") ? DamageType.Cold : str.trim().equals("d") ? DamageType.Death : str.trim().equals("t") ? DamageType.Toxic : (str.trim().equals("sp") || str.trim().equals("p")) ? DamageType.Spirit : DamageType.Normal;
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
        this.type = DamageType.Normal;
    }
}

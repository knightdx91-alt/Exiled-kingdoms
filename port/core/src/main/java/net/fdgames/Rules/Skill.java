package net.fdgames.Rules;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.GameLogic.SkillRequirements;
import net.fdgames.assets.Assets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Skill {
    public boolean NPCSkill;
    public boolean advanced;
    public String baseDescription;
    public String id;
    public ArrayList<SkillLevel> levels;
    public String name;
    public boolean requiresMelee;
    public boolean requiresRanged;
    public ClassRestriction skillClass;
    public SkillRequirements skillsRequired;
    public PlayerRequirements traitsRequired;
    public SKILL_TYPE type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum SKILL_TYPE {
        SKILL_ACTIVE, SKILL_PASSIVE;
    }

    public Skill(String str, String str2, String str3, SKILL_TYPE skill_type, String str4, Boolean bool, boolean z2, boolean z3, boolean z4) {
        this.levels = new ArrayList<>();
        this.id = str.toLowerCase(Locale.ENGLISH).trim().replace(" ", "_").replace("'", "");
        this.name = str2.replace("ecission", "ecision");
        this.type = skill_type;
        this.skillClass = new ClassRestriction(str4);
        this.baseDescription = str3;
        this.NPCSkill = bool.booleanValue();
        this.requiresMelee = z2;
        this.requiresRanged = z3;
        this.advanced = z4;
        this.levels = new ArrayList<>();
    }

    public final void a(int i2, String str, String str2, String str3) {
        if (this.levels.size() == 0) {
            this.levels.add(new SkillLevel(0, this.name, "", "", str3));
        }
        String str4 = this.levels.size() == 1 ? "I" : "";
        if (this.levels.size() == 2) {
            str4 = "II";
        }
        if (this.levels.size() == 3) {
            str4 = "III";
        }
        if (this.levels.size() == 4) {
            str4 = "IV";
        }
        if (this.levels.size() == 5) {
            str4 = "V";
        }
        this.levels.add(new SkillLevel(i2, this.name + " " + str4, str, str2, str3));
    }

    public final boolean b(CharacterSheet characterSheet) {
        if (!this.advanced) {
            return true;
        }
        PlayerRequirements playerRequirements = this.traitsRequired;
        if (playerRequirements != null && !playerRequirements.a(characterSheet, true)) {
            return false;
        }
        SkillRequirements skillRequirements = this.skillsRequired;
        return (skillRequirements == null || skillRequirements.a(characterSheet)) && this.skillClass.c(characterSheet.stats.c()).booleanValue();
    }

    public final String c() {
        return this.baseDescription;
    }

    public final String d() {
        return this.name;
    }

    public final TextureRegion e() {
        return Assets.a(this.id.toLowerCase(Locale.ENGLISH).replace("'", ""));
    }

    public final SkillLevel f(int i2) {
        if (i2 < 0 || i2 > this.levels.size() - 1) {
            return null;
        }
        return this.levels.get(i2);
    }
}

package net.fdgames.Rules;

import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.Rules.Rules;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class Spawn {
    public String AI_type;
    public String attributes;
    public int baseArmor;
    public Rules.CharacterClass characterclass;
    public String conversation_ID;
    public boolean extraSize;
    public String faction;
    public Character.Gender gender;
    public String lootTable;
    public int maxlevel;
    public int minlevel;
    private String name;
    private String name_ES;
    public String onAggroSound;
    public String onDieActions;
    public String onDieConditions;
    public String onDieSound;
    public int portrait;
    public Rules.CharacterRace race;
    public CharacterResistances resistances;
    public Float size;
    public String skills;
    public String spawn_ID;
    public float speedModifier;
    public String spriteName;
    public int wander;
    public WeaponStats weaponStats;

    public Spawn() {
        this.gender = Character.Gender.f2992a;
        this.portrait = 0;
    }

    public final String a() {
        String str;
        return (Settings.h() != 2 || (str = this.name_ES) == null || str.trim().equals("") || this.name_ES.trim().equals("RANDOM")) ? this.name : this.name_ES;
    }

    public final void b(String str) {
        this.name = str;
    }

    public Spawn(Spawn spawn) {
        this.gender = Character.Gender.f2992a;
        this.portrait = 0;
        this.spawn_ID = spawn.spawn_ID;
        this.name = spawn.name;
        this.race = spawn.race;
        this.characterclass = spawn.characterclass;
        this.minlevel = spawn.minlevel;
        this.maxlevel = spawn.maxlevel;
        this.baseArmor = spawn.baseArmor;
        this.weaponStats = spawn.weaponStats;
        this.resistances = spawn.resistances;
        this.lootTable = spawn.lootTable;
        this.spriteName = spawn.spriteName;
        this.size = spawn.size;
        this.AI_type = spawn.AI_type;
        this.speedModifier = spawn.speedModifier;
        this.attributes = spawn.attributes;
        this.skills = spawn.skills;
        this.faction = spawn.faction;
        this.gender = spawn.gender;
        this.portrait = spawn.portrait;
        this.conversation_ID = spawn.conversation_ID;
        this.wander = spawn.wander;
        this.onDieConditions = spawn.onDieConditions;
        this.onDieActions = spawn.onDieActions;
        this.onAggroSound = spawn.onAggroSound;
        this.onDieSound = spawn.onDieSound;
        this.extraSize = spawn.extraSize;
    }
}

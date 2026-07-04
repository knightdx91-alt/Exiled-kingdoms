package net.fdgames.Rules;

import net.fdgames.GameEntities.Character;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class PlayerCreation {
    public Rules.CharacterClass charClass;
    public int difficulty;
    public Character.Gender gender;
    public String name;
    public int portraitIndex;

    public PlayerCreation(String str, int i2) {
        Character.Gender gender = Character.Gender.Male;
        Rules.CharacterClass characterClass = Rules.CharacterClass.f3258a;
        this.name = str;
        this.portraitIndex = i2;
        this.gender = gender;
        this.charClass = characterClass;
        this.difficulty = 0;
    }
}

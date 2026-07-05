package net.fdgames.Rules;

import net.fdgames.GameEntities.Character;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class PlayerCreation {
    public Rules.CharacterClass charClass;
    public int difficulty;
    public Character.Gender gender;
    public String name;
    public int portraitIndex;

    public PlayerCreation(String str, int i2) {
        Character.Gender gender = Character.Gender.f3207b;
        Rules.CharacterClass characterClass = Rules.CharacterClass.f3479b;
        this.name = str;
        this.portraitIndex = i2;
        this.gender = gender;
        this.charClass = characterClass;
        this.difficulty = 0;
    }
}

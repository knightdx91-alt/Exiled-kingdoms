package net.fdgames.GameEntities.CharacterSheet;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class CharacterSkill {
    public int level;
    public String skillID;
    public float timeToCoolDown;

    public CharacterSkill() {
    }

    public CharacterSkill(String str, int i2) {
        this.skillID = str;
        this.level = i2;
        this.timeToCoolDown = 0.0f;
    }
}

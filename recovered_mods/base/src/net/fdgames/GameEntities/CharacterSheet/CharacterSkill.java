package net.fdgames.GameEntities.CharacterSheet;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class CharacterSkill {
    public int level;
    public String skillID;
    public float timeToCoolDown;

    public CharacterSkill(String str, int i2) {
        this.skillID = str;
        this.level = i2;
        this.timeToCoolDown = 0.0f;
    }

    public CharacterSkill() {
    }
}

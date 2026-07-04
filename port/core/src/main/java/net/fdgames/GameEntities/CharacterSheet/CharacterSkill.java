package net.fdgames.GameEntities.CharacterSheet;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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

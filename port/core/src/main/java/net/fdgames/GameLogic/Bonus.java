package net.fdgames.GameLogic;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Bonus {
    private String data;
    private BonusType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum BonusType {
        None, addHP, addHPpercent, addDamage, addArmor, addCritChance, addAction, addPower;
    }
}

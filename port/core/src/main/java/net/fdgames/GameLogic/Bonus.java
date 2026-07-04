package net.fdgames.GameLogic;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Bonus {
    private String data;
    private BonusType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class BonusType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final /* synthetic */ BonusType[] f3107a = {new BonusType("None", 0), new BonusType("addHP", 1), new BonusType("addHPpercent", 2), new BonusType("addDamage", 3), new BonusType("addArmor", 4), new BonusType("addCritChance", 5), new BonusType("addAction", 6), new BonusType("addPower", 7)};

        /* JADX INFO: Fake field, exist only in values array */
        BonusType EF5;

        private BonusType() {
            throw null;
        }

        public static BonusType valueOf(String str) {
            return (BonusType) Enum.valueOf(BonusType.class, str);
        }

        public static BonusType[] values() {
            return (BonusType[]) f3107a.clone();
        }
    }
}

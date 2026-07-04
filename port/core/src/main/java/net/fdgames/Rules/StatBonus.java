package net.fdgames.Rules;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class StatBonus {
    public float modifier;
    public int stat_ID;
    public BonusType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class BonusType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final /* synthetic */ BonusType[] f3281a = {new BonusType("ADDITION", 0), new BonusType("MULTIPLIER", 1), new BonusType("ASSIGN", 2)};

        /* JADX INFO: Fake field, exist only in values array */
        BonusType EF5;

        private BonusType() {
            throw null;
        }

        public static BonusType valueOf(String str) {
            return (BonusType) Enum.valueOf(BonusType.class, str);
        }

        public static BonusType[] values() {
            return (BonusType[]) f3281a.clone();
        }
    }
}

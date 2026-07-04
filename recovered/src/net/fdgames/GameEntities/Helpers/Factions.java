package net.fdgames.GameEntities.Helpers;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Factions {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Faction {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Faction f3060a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Faction f3061b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Faction f3062c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ Faction[] f3063d;

        static {
            Faction faction = new Faction("ENEMY", 0);
            f3060a = faction;
            Faction faction2 = new Faction("NEUTRAL", 1);
            f3061b = faction2;
            Faction faction3 = new Faction("PLAYER", 2);
            f3062c = faction3;
            f3063d = new Faction[]{faction, faction2, faction3, new Faction("ENEMYGUARD", 3)};
        }

        private Faction() {
            throw null;
        }

        public static Faction valueOf(String str) {
            return (Faction) Enum.valueOf(Faction.class, str);
        }

        public static Faction[] values() {
            return (Faction[]) f3063d.clone();
        }
    }
}

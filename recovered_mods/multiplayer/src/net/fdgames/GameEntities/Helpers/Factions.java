package net.fdgames.GameEntities.Helpers;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Factions {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Faction {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Faction f3275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Faction f3276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final Faction f3277d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ Faction[] f3278e;

        static {
            Faction faction = new Faction("ENEMY", 0);
            f3275b = faction;
            Faction faction2 = new Faction("NEUTRAL", 1);
            f3276c = faction2;
            Faction faction3 = new Faction("PLAYER", 2);
            f3277d = faction3;
            f3278e = new Faction[]{faction, faction2, faction3, new Faction("ENEMYGUARD", 3)};
        }

        private Faction() {
            throw null;
        }

        public static Faction valueOf(String str) {
            return (Faction) Enum.valueOf(Faction.class, str);
        }

        public static Faction[] values() {
            return (Faction[]) f3278e.clone();
        }
    }
}

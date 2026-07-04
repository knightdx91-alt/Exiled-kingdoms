package net.fdgames.GameEntities.CharacterSheet;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class CharacterResistances {
    private int all;
    private int cold;
    private int death;
    private int fire;
    private int shock;
    private int spirit;
    private int toxic;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ResistanceType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ResistanceType f2995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ResistanceType f2996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ResistanceType f2997c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ResistanceType f2998d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ResistanceType f2999e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ResistanceType f3000f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static final /* synthetic */ ResistanceType[] f3001g;

        static {
            ResistanceType resistanceType = new ResistanceType("Fire", 0);
            f2995a = resistanceType;
            ResistanceType resistanceType2 = new ResistanceType("Cold", 1);
            f2996b = resistanceType2;
            ResistanceType resistanceType3 = new ResistanceType("Shock", 2);
            f2997c = resistanceType3;
            ResistanceType resistanceType4 = new ResistanceType("Death", 3);
            f2998d = resistanceType4;
            ResistanceType resistanceType5 = new ResistanceType("Toxic", 4);
            f2999e = resistanceType5;
            ResistanceType resistanceType6 = new ResistanceType("Spirit", 5);
            f3000f = resistanceType6;
            f3001g = new ResistanceType[]{resistanceType, resistanceType2, resistanceType3, resistanceType4, resistanceType5, resistanceType6};
        }

        private ResistanceType() {
            throw null;
        }

        public static ResistanceType valueOf(String str) {
            return (ResistanceType) Enum.valueOf(ResistanceType.class, str);
        }

        public static ResistanceType[] values() {
            return (ResistanceType[]) f3001g.clone();
        }
    }

    public CharacterResistances() {
        this.fire = 0;
        this.cold = 0;
        this.shock = 0;
        this.death = 0;
        this.toxic = 0;
        this.spirit = 0;
    }

    public final int a(ResistanceType resistanceType) {
        int i2;
        int i3;
        int iOrdinal = resistanceType.ordinal();
        if (iOrdinal == 0) {
            i2 = this.fire;
            i3 = this.all;
        } else if (iOrdinal == 1) {
            i2 = this.cold;
            i3 = this.all;
        } else if (iOrdinal == 2) {
            i2 = this.shock;
            i3 = this.all;
        } else if (iOrdinal == 3) {
            i2 = this.death;
            i3 = this.all;
        } else if (iOrdinal == 4) {
            i2 = this.toxic;
            i3 = this.all;
        } else {
            if (iOrdinal != 5) {
                return 0;
            }
            i2 = this.spirit;
            i3 = this.all;
        }
        return i2 + i3;
    }

    public final boolean b() {
        return (this.all == 0 && this.fire == 0 && this.cold == 0 && this.shock == 0 && this.death == 0 && this.toxic == 0 && this.spirit == 0) ? false : true;
    }

    public final boolean c() {
        return this.all < 0 || this.fire < 0 || this.cold < 0 || this.shock < 0 || this.death < 0 || this.toxic < 0 || this.spirit < 0;
    }

    public final boolean d() {
        return this.all > 0 || this.fire > 0 || this.cold > 0 || this.shock > 0 || this.death > 0 || this.toxic > 0 || this.spirit > 0;
    }

    public final void e() {
        this.fire = 0;
        this.cold = 0;
        this.shock = 0;
        this.death = 0;
        this.toxic = 0;
        this.spirit = 0;
        this.all = 0;
    }

    public final void f(int i2) {
        this.all = i2;
    }

    public final void g(ResistanceType resistanceType, int i2) {
        int iOrdinal = resistanceType.ordinal();
        if (iOrdinal == 0) {
            this.fire = i2;
            return;
        }
        if (iOrdinal == 1) {
            this.cold = i2;
            return;
        }
        if (iOrdinal == 2) {
            this.shock = i2;
            return;
        }
        if (iOrdinal == 3) {
            this.death = i2;
        } else if (iOrdinal == 4) {
            this.toxic = i2;
        } else {
            if (iOrdinal != 5) {
                return;
            }
            this.spirit = i2;
        }
    }

    public CharacterResistances(String str) {
        this();
        List listAsList = Arrays.asList(str.trim().replace("\"", "").split(","));
        if (listAsList.size() == 6) {
            this.fire = Integer.parseInt((String) listAsList.get(0));
            this.cold = Integer.parseInt((String) listAsList.get(1));
            this.shock = Integer.parseInt((String) listAsList.get(2));
            this.death = Integer.parseInt((String) listAsList.get(3));
            this.toxic = Integer.parseInt((String) listAsList.get(4));
            this.spirit = Integer.parseInt((String) listAsList.get(5));
        }
    }
}

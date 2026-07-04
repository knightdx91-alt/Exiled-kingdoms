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
    public enum ResistanceType {
        Fire, Cold, Shock, Death, Toxic, Spirit;
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

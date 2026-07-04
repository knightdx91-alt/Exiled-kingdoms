package net.fdgames.GameEntities.CharacterSheet;

import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class CharacterStats {
    private int XP;
    private boolean cache_valid;
    private int cached_armorbonus;
    private int cached_damage;
    private int cached_level;
    private int cached_maxhp;
    private int cached_maxmana;
    private boolean cached_monster;
    private Rules.CharacterClass characterClass;
    public Rules.CharacterRace characterRace;
    public int missingHP;
    public int missingMana;

    public CharacterStats() {
        this.XP = 0;
        this.cache_valid = false;
    }

    private int e() {
        if (!j()) {
            return f();
        }
        int iF = f();
        int i2 = iF >= 18 ? (iF - 17) + iF : iF;
        if (iF >= 14) {
            i2 += iF - 13;
        }
        return iF >= 9 ? i2 + (iF - 8) : i2;
    }

    public final void a(int i2) {
        this.XP += i2;
        k();
    }

    public final int b() {
        if (this.cache_valid) {
            return this.cached_armorbonus;
        }
        if (!j()) {
            return 0;
        }
        int iF = GameData.v().E() ? f() / 3 : 0;
        int iOrdinal = this.characterClass.ordinal();
        return (iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? Math.max(f() - 2, 0) : Math.max(f() - 3, 0) : Math.max(f() - 1, 0) : Math.max(f() - 2, 0) : Math.max(f() - 1, 0)) + iF;
    }

    public final Rules.CharacterClass c() {
        return this.characterClass;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0042 A[PHI: r3
      0x0042: PHI (r3v3 int) = (r3v2 int), (r3v5 int) binds: [B:33:0x0056, B:24:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int d() {
        int i2;
        if (this.cache_valid) {
            return this.cached_damage;
        }
        int i3 = 0;
        if (!j()) {
            return 0;
        }
        int iOrdinal = this.characterClass.ordinal();
        float f2 = 0.5f;
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                f2 = 0.75f;
            } else if (iOrdinal == 2) {
                f2 = 0.3f;
            } else if (iOrdinal == 3 || iOrdinal != 4) {
                f2 = 0.0f;
            }
        }
        int iE = e();
        if (GameData.v().E()) {
            i2 = (iE - 1) / 2;
            if (i2 >= 0) {
                i3 = i2;
            }
        } else if (!GameData.v().F()) {
            if (iE < 3) {
                i3 = -1;
            } else {
                i2 = (iE - 3) / 2;
                if (i2 >= 0) {
                }
            }
        }
        return Math.round(f2 * (iE + i3));
    }

    public final int f() {
        if (this.cache_valid) {
            return this.cached_level;
        }
        int i2 = this.XP;
        for (int i3 = 100; i3 > 1; i3--) {
            if (Rules.f3252f[i3] <= i2) {
                return i3;
            }
        }
        Item[] itemArr = Rules.f3247a;
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int g() {
        int i2;
        int i3;
        if (this.cache_valid) {
            return this.cached_maxhp;
        }
        Rules.CharacterRace characterRace = this.characterRace;
        Rules.CharacterRace characterRace2 = Rules.CharacterRace.HUMAN;
        Rules.CharacterRace characterRace3 = Rules.CharacterRace.BOSS;
        Rules.CharacterRace characterRace4 = Rules.CharacterRace.MINIBOSS;
        Rules.CharacterClass characterClass = Rules.CharacterClass.ROGUE;
        Rules.CharacterClass characterClass2 = Rules.CharacterClass.CLERIC;
        Rules.CharacterClass characterClass3 = Rules.CharacterClass.WARRIOR;
        if (characterRace == characterRace2) {
            Rules.CharacterClass characterClass4 = this.characterClass;
            i2 = characterClass4 == characterClass3 ? 45 : characterClass4 == characterClass2 ? 35 : characterClass4 == characterClass ? 30 : 20;
        } else {
            i2 = characterRace == characterRace4 ? 50 : characterRace == characterRace3 ? 100 : 10;
        }
        int i4 = 5;
        if (characterRace != characterRace2) {
            if (characterRace == Rules.CharacterRace.MONSTER) {
                i3 = -1;
            } else if (characterRace != Rules.CharacterRace.MONSTER_WEAK) {
                i3 = characterRace == Rules.CharacterRace.MONSTER_STRONG ? 2 : characterRace == characterRace4 ? 5 : characterRace == characterRace3 ? 12 : characterRace == Rules.CharacterRace.NPC ? -2 : 0;
            }
        }
        Rules.CharacterClass characterClass5 = this.characterClass;
        if (characterClass5 == characterClass3) {
            i4 = 6;
        } else if (characterClass5 != characterClass2) {
            i4 = characterClass5 == characterClass ? 4 : characterClass5 == Rules.CharacterClass.WIZARD ? 3 : 0;
        }
        int iE = ((i3 + i4) * e()) + i2;
        if (this.characterRace == characterRace2) {
            return iE;
        }
        if (GameData.v().E()) {
            iE = (int) (iE * 1.25f);
        }
        return GameData.v().F() ? (int) (iE * 0.75f) : iE;
    }

    public final int h() {
        if (this.cache_valid) {
            return this.cached_maxmana;
        }
        int i2 = j() ? 2 : 0;
        int i3 = this.characterRace == Rules.CharacterRace.HUMAN ? 12 : 0;
        Rules.CharacterClass characterClass = this.characterClass;
        if (characterClass != Rules.CharacterClass.WIZARD && characterClass != Rules.CharacterClass.CLERIC) {
            return 0;
        }
        int iF = f();
        return (iF * i2) + i3;
    }

    public final int i() {
        return this.XP;
    }

    public final boolean j() {
        if (this.cache_valid) {
            return this.cached_monster;
        }
        Rules.CharacterRace characterRace = this.characterRace;
        return characterRace == Rules.CharacterRace.MONSTER || characterRace == Rules.CharacterRace.MONSTER_WEAK || characterRace == Rules.CharacterRace.MONSTER_STRONG || characterRace == Rules.CharacterRace.MINIBOSS || characterRace == Rules.CharacterRace.BOSS;
    }

    final void k() {
        this.cache_valid = false;
        this.cached_level = f();
        this.cached_damage = d();
        this.cached_maxhp = g();
        this.cached_armorbonus = b();
        this.cached_maxmana = h();
        this.cached_monster = j();
        this.cache_valid = true;
    }

    public final void l(int i2) {
        this.XP = Rules.f3252f[i2];
        k();
    }

    public CharacterStats(Rules.CharacterRace characterRace, Rules.CharacterClass characterClass) {
        this.XP = 0;
        this.cache_valid = false;
        this.characterRace = characterRace;
        this.characterClass = characterClass;
        this.missingHP = 0;
        k();
    }
}

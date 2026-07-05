package net.fdgames.GameEntities.CharacterSheet;

import java.util.ArrayList;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.WeaponStats;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class SheetBonus {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f3217a;

    public static ArrayList<DamageEffect> a(CharacterSheet characterSheet, WeaponStats.weaponType weapontype) {
        int iG;
        ArrayList<DamageEffect> arrayList = new ArrayList<>();
        if (characterSheet.N().hasProc) {
            DamageEffect damageEffect = new DamageEffect(characterSheet.N().procEffect, characterSheet.N().procLevel, characterSheet.N().procChance);
            if (damageEffect.type == DamageEffect.EffectType.f3273f && characterSheet.Q(1022) == 0) {
                damageEffect.b();
            }
            arrayList.add(damageEffect);
        }
        CharacterInventory characterInventory = characterSheet.inventory;
        if (characterInventory != null && characterInventory.r()) {
            CharacterInventory characterInventory2 = characterSheet.inventory;
            arrayList.add(new DamageEffect(Rules.f(characterInventory2.slot_offhand).procEffect, Rules.f(characterInventory2.slot_offhand).procLevel, Rules.f(characterInventory2.slot_offhand).procChance));
        }
        int iOrdinal = weapontype.ordinal();
        DamageEffect.EffectType effectType = DamageEffect.EffectType.f3271d;
        if (iOrdinal != 0) {
            DamageEffect.EffectType effectType2 = DamageEffect.EffectType.f3269b;
            if (iOrdinal == 1) {
                int iG2 = characterSheet.skillSet.g("crusader");
                if (iG2 > 0) {
                    arrayList.add(iG2 > 0 ? new DamageEffect(effectType, iG2, 100) : null);
                    arrayList.add(SkillActions.f(iG2));
                }
                int iG3 = characterSheet.skillSet.g("two_handed_expert");
                if (iG3 >= 3) {
                    arrayList.add(new DamageEffect(effectType2, 1, 10));
                } else if (iG3 >= 1) {
                    arrayList.add(new DamageEffect(effectType2, 1, 5));
                }
            } else if (iOrdinal != 2) {
                if (iOrdinal == 3 && (iG = characterSheet.skillSet.g("crusader")) > 0) {
                    arrayList.add(iG > 0 ? new DamageEffect(effectType, iG, 100) : null);
                    arrayList.add(SkillActions.f(iG));
                }
            } else if (!characterSheet.N().c()) {
                int iG4 = characterSheet.skillSet.g("archery");
                if (iG4 == 1) {
                    arrayList.add(new DamageEffect(effectType2, 1, 3));
                } else if (iG4 == 2) {
                    arrayList.add(new DamageEffect(effectType2, 1, 5));
                } else if (iG4 == 3) {
                    arrayList.add(new DamageEffect(effectType2, 1, 7));
                }
            } else if (characterSheet.N().staff) {
                int iG5 = characterSheet.skillSet.g("staff_mastery");
                if (iG5 > 0) {
                    if (iG5 > 0) {
                        DamageEffect.EffectType effectType3 = DamageEffect.EffectType.f3270c;
                        if (iG5 == 1) {
                            damageEffect = new DamageEffect(effectType3, 2, 25);
                        } else if (iG5 == 2) {
                            damageEffect = new DamageEffect(effectType3, 2, 30);
                        } else if (iG5 == 3) {
                            damageEffect = new DamageEffect(effectType3, 2, 35);
                        } else if (iG5 == 4) {
                            damageEffect = new DamageEffect(effectType3, 2, 40);
                        }
                    }
                    arrayList.add(damageEffect);
                }
            } else {
                boolean z2 = characterSheet.N().wand;
            }
        } else {
            int iG6 = characterSheet.skillSet.g("crusader");
            if (iG6 > 0) {
                arrayList.add(iG6 > 0 ? new DamageEffect(effectType, iG6, 100) : null);
                arrayList.add(SkillActions.f(iG6));
            }
        }
        return arrayList;
    }

    public static float b(CharacterSheet characterSheet, WeaponStats.weaponType weapontype) {
        WeaponStats.weaponType weapontype2 = WeaponStats.weaponType.f3505d;
        if (weapontype == weapontype2 && characterSheet.effects.rapid_fire) {
            int iG = characterSheet.skillSet.g("rapid_fire");
            if (iG == 1) {
                return 1.4f;
            }
            if (iG == 2) {
                return 1.7f;
            }
            if (iG == 3) {
                return 2.0f;
            }
        }
        if (weapontype == weapontype2 || !characterSheet.effects.flurry) {
            return 1.0f;
        }
        int iG2 = characterSheet.skillSet.g("flurry");
        if (iG2 == 1) {
            return 1.25f;
        }
        if (iG2 == 2) {
            return 1.5f;
        }
        return iG2 == 3 ? 1.75f : 1.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int c(CharacterSheet characterSheet, WeaponStats.weaponType weapontype) {
        int i2;
        int i3;
        int i4;
        int iG;
        WeaponStats.weaponType weapontype2;
        int i5;
        CharacterEffects characterEffects;
        int i6;
        int i7;
        int i8;
        int iF;
        int iD = d(characterSheet.J(), 0, characterSheet.inventory, characterSheet.effects);
        int iD2 = d(characterSheet.J(), 2, characterSheet.inventory, characterSheet.effects);
        int iD3 = d(characterSheet.J(), 4, characterSheet.inventory, characterSheet.effects);
        int iOrdinal = weapontype.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    if (!characterSheet.N().c()) {
                        iG = characterSheet.skillSet.g("archery");
                        if (iG == 1) {
                            iF = characterSheet.stats.f() / 5;
                        } else if (iG == 2) {
                            iF = characterSheet.stats.f() / 4;
                        } else {
                            if (iG == 3) {
                                iF = characterSheet.stats.f() / 3;
                            }
                            i8 = iD3 / 2;
                        }
                        iG += iF;
                        i8 = iD3 / 2;
                    } else if (characterSheet.N().staff) {
                        int iD4 = d(characterSheet.J(), 3, characterSheet.inventory, characterSheet.effects);
                        int iG2 = characterSheet.skillSet.g("staff_mastery");
                        if (iG2 == 4) {
                            iD4 *= 2;
                        } else if (iG2 == 3) {
                            iD4 += iD4 / 2;
                        } else if (iG2 != 2) {
                            iD4 = iG2 == 1 ? iD4 / 2 : 0;
                        }
                        i3 = iD4 + iD2 + (iD3 / 2);
                        i4 = i3;
                        iG = 0;
                    } else if (characterSheet.N().wand) {
                        int iG3 = characterSheet.skillSet.g("wand_mastery");
                        int i9 = iG3 == 1 ? 1 : 0;
                        if (iG3 == 2) {
                            i9 = 3;
                        }
                        if (iG3 == 3) {
                            i9 = 5;
                        }
                        iG = iG3 == 4 ? 8 : i9;
                        i8 = iD3 / 2;
                    }
                    i4 = iD2 + i8;
                } else if (iOrdinal == 3) {
                    i7 = iD / 2;
                    i3 = i7 + iD2;
                    i4 = i3;
                    iG = 0;
                }
                iG = 0;
                i4 = 0;
            } else if (characterSheet.skillSet.g("two_handed_expert") == 3) {
                iD2 = iD * 2;
                i7 = iD / 2;
                i3 = i7 + iD2;
                i4 = i3;
                iG = 0;
            } else if (characterSheet.skillSet.g("two_handed_expert") == 2) {
                i3 = iD * 2;
                i4 = i3;
                iG = 0;
            } else {
                i2 = iD / 2;
            }
            weapontype2 = WeaponStats.weaponType.f3505d;
            if (!weapontype.equals(weapontype2)) {
                int iG4 = characterSheet.skillSet.g("heavyhand");
                if (iG4 == 1) {
                    iG++;
                }
                if (iG4 == 2) {
                    iG += 3;
                }
                if (iG4 == 3) {
                    iG += 5;
                }
            }
            if (characterSheet.P("giant")) {
                i4 += 2;
            }
            if (characterSheet.effects.fatigued.booleanValue()) {
                iG -= 5;
            }
            if (!characterSheet.stats.j() || weapontype.equals(weapontype2)) {
                i5 = 0;
            } else {
                int iF2 = characterSheet.stats.f();
                int iOrdinal2 = characterSheet.stats.c().ordinal();
                if (iOrdinal2 == 0) {
                    i5 = iF2 / 5;
                } else if (iOrdinal2 == 1) {
                    i5 = (iF2 / 4) + 1;
                } else if (iOrdinal2 == 2) {
                    i5 = iF2 / 7;
                }
            }
            characterEffects = characterSheet.effects;
            i6 = characterEffects.stab.booleanValue() ? characterEffects.stabBonus : 0;
            if (characterEffects.fury.booleanValue()) {
                i6 += characterEffects.furyBonus;
            }
            if (characterEffects.might.booleanValue()) {
                i6 += characterEffects.mightBonus;
            }
            if (characterEffects.might_prayer.booleanValue()) {
                i6 += characterEffects.mightBonus_prayer;
            }
            if (characterEffects.might_arbenos.booleanValue()) {
                i6 += characterEffects.mightBonus_arbenos;
            }
            if (characterEffects.duel.booleanValue()) {
                i6 += characterEffects.duelbonus;
            }
            if (characterEffects.bloodlust.booleanValue()) {
                i6 += characterEffects.bloodlustBonus;
            }
            if (characterSheet.effects.might_arbenos.booleanValue() && weapontype == WeaponStats.weaponType.f3504c) {
                i6 += characterSheet.effects.mightBonus_arbenos;
            }
            return characterSheet.stats.d() + iG + i4 + i5 + i6;
        }
        i2 = iD2 / 2;
        i3 = iD + i2;
        i4 = i3;
        iG = 0;
        weapontype2 = WeaponStats.weaponType.f3505d;
        if (!weapontype.equals(weapontype2)) {
        }
        if (characterSheet.P("giant")) {
        }
        if (characterSheet.effects.fatigued.booleanValue()) {
        }
        if (characterSheet.stats.j()) {
            i5 = 0;
        }
        characterEffects = characterSheet.effects;
        if (characterEffects.stab.booleanValue()) {
        }
        if (characterEffects.fury.booleanValue()) {
        }
        if (characterEffects.might.booleanValue()) {
        }
        if (characterEffects.might_prayer.booleanValue()) {
        }
        if (characterEffects.might_arbenos.booleanValue()) {
        }
        if (characterEffects.duel.booleanValue()) {
        }
        if (characterEffects.bloodlust.booleanValue()) {
        }
        if (characterSheet.effects.might_arbenos.booleanValue()) {
            i6 += characterSheet.effects.mightBonus_arbenos;
        }
        return characterSheet.stats.d() + iG + i4 + i5 + i6;
    }

    public static int d(CharacterTraits characterTraits, int i2, CharacterInventory characterInventory, CharacterEffects characterEffects) {
        if (characterInventory != null) {
            f3217a = characterInventory.n(i2);
        } else {
            f3217a = 0;
        }
        if (characterEffects.rage.booleanValue() && i2 == 0) {
            f3217a += characterEffects.rageStrBonus;
        }
        return characterTraits.d(i2) + f3217a;
    }

    public static int e(CharacterSheet characterSheet) {
        int iO = characterSheet.inventory.o() + (d(characterSheet.J(), 3, characterSheet.inventory, characterSheet.effects) * 2);
        SheetEffects sheetEffects = characterSheet.sheetEffects;
        int i2 = 0;
        if (sheetEffects == null) {
            characterSheet.sheetEffects = new SheetEffects();
        } else {
            SheetEffect sheetEffectB = sheetEffects.b(SheetEffect.a("XPBONUS"));
            if (sheetEffectB != null) {
                i2 = sheetEffectB.level;
            }
        }
        return iO + i2;
    }
}

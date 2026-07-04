package net.fdgames.GameEntities.CharacterSheet;

import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.ItemAttributes;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class CharacterInventory {
    private int XPBonus;
    private int detectionBonus;
    private int devicesBonus;
    private int gossipBonus;
    public boolean shield;
    public boolean stability;
    public boolean stunimmunity;
    private int DefenseBonus = 0;
    private int HPBonus = 0;
    private int ManaBonus = 0;
    private CharacterResistances ResistanceBonus = new CharacterResistances();
    private int[] traits = new int[6];
    private boolean twohander = false;
    public int slot_body = 0;
    public int slot_head = 0;
    public int slot_hands = 0;
    public int slot_legs = 0;
    public int slot_feet = 0;
    public int slot_mainhand = 0;
    public int slot_offhand = 0;
    public int slot_ring = 0;
    public int slot_cloak = 0;
    public int slot_necklace = 0;
    public int slot_ring2 = 0;
    public int slot_belt = 0;
    private boolean antirad = false;

    private void a(Item item) {
        if (item != null) {
            int i2 = this.gossipBonus;
            ItemAttributes itemAttributes = item.attributes;
            int i3 = itemAttributes.gossip;
            this.gossipBonus = i2 + (i3 > 0 ? i3 * 3 : 0);
            int i4 = this.detectionBonus;
            int i5 = itemAttributes.detection;
            this.detectionBonus = i4 + (i5 > 0 ? i5 * 3 : 0);
            int i6 = this.devicesBonus;
            int i7 = itemAttributes.tinkering;
            this.devicesBonus = i6 + (i7 > 0 ? i7 * 5 : 0);
            int i8 = this.XPBonus;
            int i9 = itemAttributes.wisdom;
            this.XPBonus = i8 + (i9 > 0 ? i9 * 2 : 0);
        }
    }

    public final boolean b() {
        return this.antirad;
    }

    public final boolean c(int i2) {
        int i3;
        int i4 = GameData.v().backpack.j(i2).itemID;
        Item itemF = Rules.f(i4);
        int i5 = 0;
        if (i4 == 0 || itemF == null || itemF.stackable) {
            return false;
        }
        Item.ItemType itemType = itemF.type;
        if (itemType == Item.ItemType.f3230b) {
            i3 = this.slot_mainhand;
            this.slot_mainhand = i4;
        } else {
            i3 = 0;
        }
        if (itemType == Item.ItemType.f3232d) {
            i3 = this.slot_head;
            this.slot_head = i4;
        }
        if (itemType == Item.ItemType.f3233e) {
            i3 = this.slot_body;
            this.slot_body = i4;
        }
        if (itemType == Item.ItemType.f3236h) {
            i3 = this.slot_hands;
            this.slot_hands = i4;
        }
        if (itemType == Item.ItemType.f3234f) {
            i3 = this.slot_feet;
            this.slot_feet = i4;
        }
        if (itemType == Item.ItemType.f3235g) {
            i3 = this.slot_legs;
            this.slot_legs = i4;
        }
        if (itemType == Item.ItemType.f3231c) {
            i3 = this.slot_offhand;
            this.slot_offhand = i4;
        }
        if (itemType != Item.ItemType.f3240l) {
            i5 = i3;
        } else {
            i3 = this.slot_ring;
            if (i3 == 0) {
                this.slot_ring = i4;
            } else {
                i5 = this.slot_ring2;
                if (i5 == 0) {
                    this.slot_ring2 = i4;
                } else {
                    this.slot_ring = i4;
                    i5 = i3;
                }
            }
        }
        if (itemType == Item.ItemType.f3241m) {
            i5 = this.slot_belt;
            this.slot_belt = i4;
        }
        if (itemType == Item.ItemType.f3242n) {
            i5 = this.slot_cloak;
            this.slot_cloak = i4;
        }
        if (itemType == Item.ItemType.f3243o) {
            i5 = this.slot_necklace;
            this.slot_necklace = i4;
        }
        GameData.v().backpack.t(i2, i5);
        s();
        return true;
    }

    public final int d() {
        return this.DefenseBonus;
    }

    public final int e() {
        return this.detectionBonus;
    }

    public final int f() {
        return this.devicesBonus;
    }

    public final int g() {
        return this.gossipBonus;
    }

    public final int h() {
        return this.HPBonus;
    }

    public final int i(int i2) {
        switch (i2) {
            case 0:
                return this.slot_head;
            case 1:
                return this.slot_body;
            case 2:
                return this.slot_hands;
            case 3:
                return this.slot_legs;
            case 4:
                return this.slot_feet;
            case 5:
                return this.slot_ring;
            case 6:
                return this.slot_mainhand;
            case 7:
                return this.slot_offhand;
            case 8:
                return this.slot_cloak;
            case 9:
                return this.slot_necklace;
            case 10:
                return this.slot_ring2;
            case 11:
                return this.slot_belt;
            default:
                return 0;
        }
    }

    public final WeaponStats j() {
        int i2 = this.slot_mainhand;
        return i2 == 0 ? new WeaponStats() : Rules.f(i2).weaponStats;
    }

    public final int k() {
        return this.ManaBonus;
    }

    public final int l() {
        if (q()) {
            return Rules.f(this.slot_offhand).damageBonus;
        }
        return 0;
    }

    public final int m(CharacterResistances.ResistanceType resistanceType) {
        return this.ResistanceBonus.a(resistanceType);
    }

    public final int n(int i2) {
        if (this.traits == null) {
            this.traits = new int[6];
        }
        return this.traits[i2];
    }

    public final int o() {
        return this.XPBonus;
    }

    public final boolean p() {
        int i2;
        for (int i3 = 0; i3 < 6; i3++) {
            int i4 = this.slot_body;
            int i5 = (i4 == 0 || Rules.f(i4).f(i3) <= 0) ? 0 : 1;
            int i6 = this.slot_head;
            if (i6 != 0 && Rules.f(i6).f(i3) > 0) {
                i5++;
            }
            int i7 = this.slot_hands;
            if (i7 != 0 && Rules.f(i7).f(i3) > 0) {
                i5++;
            }
            int i8 = this.slot_legs;
            if (i8 != 0 && Rules.f(i8).f(i3) > 0) {
                i5++;
            }
            int i9 = this.slot_feet;
            if (i9 != 0 && Rules.f(i9).f(i3) > 0) {
                i5++;
            }
            int i10 = this.slot_mainhand;
            if (i10 != 0 && Rules.f(i10).f(i3) > 0) {
                i5++;
            }
            if (!this.twohander && (i2 = this.slot_offhand) != 0 && Rules.f(i2).f(i3) > 0) {
                i5++;
            }
            int i11 = this.slot_ring;
            if (i11 != 0 && Rules.f(i11).f(i3) > 0) {
                i5++;
            }
            int i12 = this.slot_ring2;
            if (i12 != 0 && Rules.f(i12).f(i3) > 0) {
                i5++;
            }
            int i13 = this.slot_belt;
            if (i13 != 0 && Rules.f(i13).f(i3) > 0) {
                i5++;
            }
            int i14 = this.slot_cloak;
            if (i14 != 0 && Rules.f(i14).f(i3) > 0) {
                i5++;
            }
            int i15 = this.slot_necklace;
            if (i15 != 0 && Rules.f(i15).f(i3) > 0) {
                i5++;
            }
            if (i5 > 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean q() {
        int i2;
        return (this.twohander || (i2 = this.slot_offhand) == 0 || !Rules.f(i2).hasDamageBonus) ? false : true;
    }

    public final boolean r() {
        int i2;
        return (this.twohander || (i2 = this.slot_offhand) == 0 || !Rules.f(i2).hasProc) ? false : true;
    }

    public final void s() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = this.slot_mainhand;
        if (i9 == 0 || !Rules.f(i9).weaponStats.twohanded) {
            this.twohander = false;
        } else {
            this.twohander = true;
        }
        if (this.twohander || (i8 = this.slot_offhand) == 0 || Rules.f(i8).attributes.shield <= 0) {
            this.shield = false;
        } else {
            this.shield = true;
        }
        this.stability = false;
        int i10 = this.slot_feet;
        if (i10 != 0 && Rules.f(i10).a().stability > 0) {
            this.stability = true;
        }
        int i11 = this.slot_body;
        if (i11 != 0 && Rules.f(i11).a().stability > 0) {
            this.stability = true;
        }
        int i12 = this.slot_offhand;
        if (i12 != 0 && Rules.f(i12).a().stability > 0) {
            this.stability = true;
        }
        this.stunimmunity = false;
        int i13 = this.slot_head;
        if (i13 != 0 && Rules.f(i13).a().stunimmunity > 0) {
            this.stunimmunity = true;
        }
        int i14 = this.slot_necklace;
        if (i14 != 0 && Rules.f(i14).a().stunimmunity > 0) {
            this.stunimmunity = true;
        }
        int i15 = this.slot_body;
        if (i15 != 0 && Rules.f(i15).a().stunimmunity > 0) {
            this.stunimmunity = true;
        }
        int i16 = this.slot_ring;
        if (i16 != 0 && Rules.f(i16).a().stunimmunity > 0) {
            this.stunimmunity = true;
        }
        int i17 = this.slot_ring2;
        if (i17 != 0 && Rules.f(i17).a().stunimmunity > 0) {
            this.stunimmunity = true;
        }
        this.antirad = false;
        int i18 = this.slot_head;
        if (i18 != 0 && Rules.f(i18).a().antirad > 0) {
            this.antirad = true;
        }
        int i19 = this.slot_body;
        if (i19 != 0 && Rules.f(i19).a().antirad > 0) {
            this.antirad = true;
        }
        int i20 = this.slot_body;
        int i21 = i20 != 0 ? Rules.f(i20).armorBonus : 0;
        int i22 = this.slot_head;
        if (i22 != 0) {
            i21 += Rules.f(i22).armorBonus;
        }
        int i23 = this.slot_hands;
        if (i23 != 0) {
            i21 += Rules.f(i23).armorBonus;
        }
        int i24 = this.slot_legs;
        if (i24 != 0) {
            i21 += Rules.f(i24).armorBonus;
        }
        int i25 = this.slot_feet;
        if (i25 != 0) {
            i21 += Rules.f(i25).armorBonus;
        }
        int i26 = this.slot_mainhand;
        if (i26 != 0) {
            i21 += Rules.f(i26).armorBonus;
        }
        if (!this.twohander && (i7 = this.slot_offhand) != 0) {
            i21 += Rules.f(i7).armorBonus;
        }
        int i27 = this.slot_ring;
        if (i27 != 0) {
            i21 += Rules.f(i27).armorBonus;
        }
        int i28 = this.slot_ring2;
        if (i28 != 0) {
            i21 += Rules.f(i28).armorBonus;
        }
        int i29 = this.slot_belt;
        if (i29 != 0) {
            i21 += Rules.f(i29).armorBonus;
        }
        int i30 = this.slot_cloak;
        if (i30 != 0) {
            i21 += Rules.f(i30).armorBonus;
        }
        int i31 = this.slot_necklace;
        if (i31 != 0) {
            i21 += Rules.f(i31).armorBonus;
        }
        this.DefenseBonus = i21;
        int i32 = this.slot_body;
        int i33 = i32 != 0 ? Rules.f(i32).HPBonus : 0;
        int i34 = this.slot_head;
        if (i34 != 0) {
            i33 += Rules.f(i34).HPBonus;
        }
        int i35 = this.slot_hands;
        if (i35 != 0) {
            i33 += Rules.f(i35).HPBonus;
        }
        int i36 = this.slot_legs;
        if (i36 != 0) {
            i33 += Rules.f(i36).HPBonus;
        }
        int i37 = this.slot_feet;
        if (i37 != 0) {
            i33 += Rules.f(i37).HPBonus;
        }
        int i38 = this.slot_mainhand;
        if (i38 != 0) {
            i33 += Rules.f(i38).HPBonus;
        }
        if (!this.twohander && (i6 = this.slot_offhand) != 0) {
            i33 += Rules.f(i6).HPBonus;
        }
        int i39 = this.slot_ring;
        if (i39 != 0) {
            i33 += Rules.f(i39).HPBonus;
        }
        int i40 = this.slot_ring2;
        if (i40 != 0) {
            i33 += Rules.f(i40).HPBonus;
        }
        int i41 = this.slot_belt;
        if (i41 != 0) {
            i33 += Rules.f(i41).HPBonus;
        }
        int i42 = this.slot_cloak;
        if (i42 != 0) {
            i33 += Rules.f(i42).HPBonus;
        }
        int i43 = this.slot_necklace;
        if (i43 != 0) {
            i33 += Rules.f(i43).HPBonus;
        }
        this.HPBonus = i33;
        int i44 = this.slot_body;
        int i45 = i44 != 0 ? Rules.f(i44).ManaBonus : 0;
        int i46 = this.slot_head;
        if (i46 != 0) {
            i45 += Rules.f(i46).ManaBonus;
        }
        int i47 = this.slot_hands;
        if (i47 != 0) {
            i45 += Rules.f(i47).ManaBonus;
        }
        int i48 = this.slot_legs;
        if (i48 != 0) {
            i45 += Rules.f(i48).ManaBonus;
        }
        int i49 = this.slot_feet;
        if (i49 != 0) {
            i45 += Rules.f(i49).ManaBonus;
        }
        int i50 = this.slot_mainhand;
        if (i50 != 0) {
            i45 += Rules.f(i50).ManaBonus;
        }
        if (!this.twohander && (i5 = this.slot_offhand) != 0) {
            i45 += Rules.f(i5).ManaBonus;
        }
        int i51 = this.slot_ring;
        if (i51 != 0) {
            i45 += Rules.f(i51).ManaBonus;
        }
        int i52 = this.slot_ring2;
        if (i52 != 0) {
            i45 += Rules.f(i52).ManaBonus;
        }
        int i53 = this.slot_belt;
        if (i53 != 0) {
            i45 += Rules.f(i53).ManaBonus;
        }
        int i54 = this.slot_cloak;
        if (i54 != 0) {
            i45 += Rules.f(i54).ManaBonus;
        }
        int i55 = this.slot_necklace;
        if (i55 != 0) {
            i45 += Rules.f(i55).ManaBonus;
        }
        this.ManaBonus = i45;
        this.gossipBonus = 0;
        this.detectionBonus = 0;
        this.devicesBonus = 0;
        this.XPBonus = 0;
        int i56 = this.slot_body;
        if (i56 != 0) {
            a(Rules.f(i56));
        }
        int i57 = this.slot_head;
        if (i57 != 0) {
            a(Rules.f(i57));
        }
        int i58 = this.slot_hands;
        if (i58 != 0) {
            a(Rules.f(i58));
        }
        int i59 = this.slot_legs;
        if (i59 != 0) {
            a(Rules.f(i59));
        }
        int i60 = this.slot_feet;
        if (i60 != 0) {
            a(Rules.f(i60));
        }
        int i61 = this.slot_mainhand;
        if (i61 != 0) {
            a(Rules.f(i61));
        }
        if (!this.twohander && (i4 = this.slot_offhand) != 0) {
            a(Rules.f(i4));
        }
        int i62 = this.slot_ring;
        if (i62 != 0) {
            a(Rules.f(i62));
        }
        int i63 = this.slot_ring2;
        if (i63 != 0) {
            a(Rules.f(i63));
        }
        int i64 = this.slot_belt;
        if (i64 != 0) {
            a(Rules.f(i64));
        }
        int i65 = this.slot_cloak;
        if (i65 != 0) {
            a(Rules.f(i65));
        }
        int i66 = this.slot_necklace;
        if (i66 != 0) {
            a(Rules.f(i66));
        }
        this.ResistanceBonus.e();
        for (CharacterResistances.ResistanceType resistanceType : CharacterResistances.ResistanceType.values()) {
            int i67 = this.slot_body;
            int iD = i67 != 0 ? Rules.f(i67).d(resistanceType) : 0;
            int i68 = this.slot_head;
            if (i68 != 0) {
                iD += Rules.f(i68).d(resistanceType);
            }
            int i69 = this.slot_hands;
            if (i69 != 0) {
                iD += Rules.f(i69).d(resistanceType);
            }
            int i70 = this.slot_legs;
            if (i70 != 0) {
                iD += Rules.f(i70).d(resistanceType);
            }
            int i71 = this.slot_feet;
            if (i71 != 0) {
                iD += Rules.f(i71).d(resistanceType);
            }
            int i72 = this.slot_mainhand;
            if (i72 != 0) {
                iD += Rules.f(i72).d(resistanceType);
            }
            if (!this.twohander && (i3 = this.slot_offhand) != 0) {
                iD += Rules.f(i3).d(resistanceType);
            }
            int i73 = this.slot_ring;
            if (i73 != 0) {
                iD += Rules.f(i73).d(resistanceType);
            }
            int i74 = this.slot_ring2;
            if (i74 != 0) {
                iD += Rules.f(i74).d(resistanceType);
            }
            int i75 = this.slot_belt;
            if (i75 != 0) {
                iD += Rules.f(i75).d(resistanceType);
            }
            int i76 = this.slot_cloak;
            if (i76 != 0) {
                iD += Rules.f(i76).d(resistanceType);
            }
            int i77 = this.slot_necklace;
            if (i77 != 0) {
                iD += Rules.f(i77).d(resistanceType);
            }
            this.ResistanceBonus.g(resistanceType, iD);
        }
        this.traits = new int[6];
        for (int i78 = 0; i78 < 6; i78++) {
            int i79 = this.slot_body;
            int iF = (i79 == 0 || Rules.f(i79).f(i78) <= 0) ? 0 : Rules.f(this.slot_body).f(i78);
            int i80 = this.slot_head;
            if (i80 != 0 && Rules.f(i80).f(i78) > iF) {
                iF = Rules.f(this.slot_head).f(i78);
            }
            int i81 = this.slot_hands;
            if (i81 != 0 && Rules.f(i81).f(i78) > iF) {
                iF = Rules.f(this.slot_hands).f(i78);
            }
            int i82 = this.slot_legs;
            if (i82 != 0 && Rules.f(i82).f(i78) > iF) {
                iF = Rules.f(this.slot_legs).f(i78);
            }
            int i83 = this.slot_feet;
            if (i83 != 0 && Rules.f(i83).f(i78) > iF) {
                iF = Rules.f(this.slot_feet).f(i78);
            }
            int i84 = this.slot_mainhand;
            if (i84 != 0 && Rules.f(i84).f(i78) > iF) {
                iF = Rules.f(this.slot_mainhand).f(i78);
            }
            if (!this.twohander && (i2 = this.slot_offhand) != 0 && Rules.f(i2).f(i78) > iF) {
                iF = Rules.f(this.slot_offhand).f(i78);
            }
            int i85 = this.slot_ring;
            if (i85 != 0 && Rules.f(i85).f(i78) > iF) {
                iF = Rules.f(this.slot_ring).f(i78);
            }
            int i86 = this.slot_ring2;
            if (i86 != 0 && Rules.f(i86).f(i78) > iF) {
                iF = Rules.f(this.slot_ring2).f(i78);
            }
            int i87 = this.slot_belt;
            if (i87 != 0 && Rules.f(i87).f(i78) > iF) {
                iF = Rules.f(this.slot_belt).f(i78);
            }
            int i88 = this.slot_cloak;
            if (i88 != 0 && Rules.f(i88).f(i78) > iF) {
                iF = Rules.f(this.slot_cloak).f(i78);
            }
            int i89 = this.slot_necklace;
            if (i89 != 0 && Rules.f(i89).f(i78) > iF) {
                iF = Rules.f(this.slot_necklace).f(i78);
            }
            this.traits[i78] = iF;
        }
        GameLevelData.o().C();
    }
}

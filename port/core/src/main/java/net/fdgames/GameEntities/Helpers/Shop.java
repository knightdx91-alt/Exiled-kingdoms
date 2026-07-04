package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Shop implements Lootable {
    public int owner_ID;
    public Items items = new Items();
    public float modifier = 1.0f;
    public boolean sellsWeapons = false;
    public boolean sellsJewelry = false;
    public boolean sellsArmor = false;
    public boolean sellsMagic = false;
    public boolean sellsGeneral = false;

    public static ArrayList<Integer> t(String str) {
        String[] strArrSplit = str.split(",");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if (strArrSplit[i2].contains("#")) {
                String[] strArrSplit2 = strArrSplit[i2].split("#");
                if (strArrSplit2.length == 2 && FDUtils.b(1, 100) < Integer.parseInt(strArrSplit2[1])) {
                    arrayList.add(Integer.valueOf(Integer.parseInt(strArrSplit2[0])));
                }
            } else {
                arrayList.add(Integer.valueOf(Integer.parseInt(strArrSplit[i2])));
            }
        }
        return arrayList;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final float a() {
        return this.modifier;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void b() {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void c(int i2) {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void d() {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void e() {
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean f() {
        return false;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean g() {
        return false;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final int getItem(int i2) {
        return this.items.h(i2);
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final String getName() {
        return GameLevel.h(this.owner_ID).getName();
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Lootable.LootableType getType() {
        return Lootable.LootableType.SHOP;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final int h() {
        return 0;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean i(int i2) {
        boolean zA = this.items.a(i2, 1);
        for (int i3 : this.items.i()) {
            if (i3 != 0 && Rules.f(i3) != null) {
                switch (Rules.f(i3).type.ordinal()) {
                    case 0:
                        this.sellsGeneral = true;
                        break;
                    case 1:
                        this.sellsWeapons = true;
                        break;
                    case 2:
                        this.sellsArmor = true;
                        break;
                    case 3:
                        this.sellsArmor = true;
                        break;
                    case 4:
                        this.sellsArmor = true;
                        break;
                    case 5:
                        this.sellsArmor = true;
                        break;
                    case 6:
                        this.sellsArmor = true;
                        break;
                    case 7:
                        this.sellsArmor = true;
                        break;
                    case 8:
                        this.sellsMagic = true;
                        break;
                    case 9:
                        this.sellsMagic = true;
                        break;
                    case 11:
                        this.sellsJewelry = true;
                        break;
                    case 12:
                        this.sellsArmor = true;
                        break;
                    case 13:
                        this.sellsArmor = true;
                        break;
                    case 14:
                        this.sellsJewelry = true;
                        break;
                    case 15:
                        this.sellsMagic = true;
                        break;
                }
            }
        }
        return zA;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Boolean isEmpty() {
        return Boolean.FALSE;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final Items j() {
        return this.items;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final boolean k(int i2) {
        return false;
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final TextureRegion l() {
        return n();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0071 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m(int i2) {
        if (i2 != 0 && Rules.f(i2) != null) {
            switch (Rules.f(i2).type.ordinal()) {
                case 0:
                    return this.sellsGeneral || this.sellsMagic;
                case 1:
                    if (this.sellsWeapons) {
                        return true;
                    }
                    break;
                case 2:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 3:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 4:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 5:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 6:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 7:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 8:
                    if (this.sellsMagic) {
                        return true;
                    }
                    break;
                case 9:
                    if (this.sellsMagic) {
                        return true;
                    }
                    break;
                case 11:
                    if (this.sellsJewelry || this.sellsArmor) {
                        return true;
                    }
                    break;
                case 12:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 13:
                    if (this.sellsArmor) {
                        return true;
                    }
                    break;
                case 14:
                    if (this.sellsJewelry || this.sellsArmor) {
                        return true;
                    }
                    break;
                case 15:
                    if (this.sellsMagic) {
                        return true;
                    }
                    break;
            }
        } else {
            return false;
        }
    }

    public final TextureRegion n() {
        return GameLevel.h(this.owner_ID).E();
    }

    public final int o() {
        return this.owner_ID;
    }

    public final int p() {
        return GameLevel.h(this.owner_ID).f3092x;
    }

    public final int q() {
        return GameLevel.h(this.owner_ID).f3093y;
    }

    public final boolean r() {
        return Player.L1(GameLevel.h(this.owner_ID).r());
    }

    @Override // net.fdgames.GameEntities.Helpers.Lootable
    public final void removeItem(int i2) {
    }

    public final boolean s() {
        return GameLevel.h(this.owner_ID) == null || !GameLevel.i(this.owner_ID).A().equals("");
    }

    public final void u(int i2) {
        this.owner_ID = i2;
    }
}

package net.fdgames.GameEntities.Helpers;

import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Items {
    private int[] itemList = new int[20];
    private int[] stackUnits = new int[20];

    public class ItemsSlot {
        public int itemID;
        public int units;

        public ItemsSlot(int i2, int i3) {
            this.itemID = i2;
            this.units = i3;
        }
    }

    private void o() {
        if (this.stackUnits == null) {
            this.stackUnits = new int[20];
            g();
        }
    }

    public final boolean a(int i2, int i3) {
        o();
        if (Rules.n(i2).booleanValue() && k(i2) > -1) {
            this.stackUnits[k(i2)] = this.stackUnits[k(i2)] + i3;
            return true;
        }
        if (Rules.n(i2).booleanValue()) {
            int iF = f();
            if (iF == -1) {
                return false;
            }
            this.itemList[iF] = i2;
            this.stackUnits[iF] = i3;
            return true;
        }
        int iF2 = f();
        if (iF2 == -1) {
            return false;
        }
        this.itemList[iF2] = i2;
        this.stackUnits[iF2] = 1;
        return true;
    }

    public final void b(ArrayList arrayList) {
        o();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            if (!Rules.n(iIntValue).booleanValue() || k(iIntValue) <= -1) {
                int iF = f();
                if (iF == -1) {
                    return;
                }
                this.itemList[iF] = iIntValue;
                this.stackUnits[iF] = 1;
            } else {
                int[] iArr = this.stackUnits;
                int iK = k(iIntValue);
                iArr[iK] = iArr[iK] + 1;
            }
        }
    }

    public final boolean c(int i2) {
        if (f() == -1) {
            return Rules.f(i2).stackable && l(i2) > 0;
        }
        return true;
    }

    public final boolean d(int i2) {
        for (int i3 : this.itemList) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public final void e() {
        this.itemList = new int[20];
        this.stackUnits = new int[20];
    }

    public final int f() {
        for (int i2 = 0; i2 < 20; i2++) {
            if (this.itemList[i2] == 0) {
                return i2;
            }
        }
        return -1;
    }

    public final void g() {
        for (int i2 = 0; i2 < 20; i2++) {
            if (this.itemList[i2] == 0) {
                this.stackUnits[i2] = 0;
            } else {
                int[] iArr = this.stackUnits;
                if (iArr[i2] == 0) {
                    iArr[i2] = 1;
                }
            }
        }
    }

    public final int h(int i2) {
        if (i2 < 20) {
            return this.itemList[i2];
        }
        return 0;
    }

    public final int[] i() {
        return this.itemList;
    }

    public final ItemsSlot j(int i2) {
        return new ItemsSlot(this.itemList[i2], this.stackUnits[i2]);
    }

    public final int k(int i2) {
        for (int i3 = 0; i3 < 20; i3++) {
            if (this.itemList[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    public final int l(int i2) {
        int i3;
        o();
        int i4 = 0;
        for (int i5 = 0; i5 < 20; i5++) {
            int i6 = this.itemList[i5];
            if (i6 == i2) {
                i4 = (!Rules.n(i6).booleanValue() || (i3 = this.stackUnits[i5]) <= 1) ? i4 + 1 : i4 + i3;
            }
        }
        return i4;
    }

    public final boolean m() {
        for (int i2 = 0; i2 < 20; i2++) {
            int i3 = this.itemList[i2];
            if (i3 == 0) {
                Item[] itemArr = Rules.f3247a;
            } else if (Rules.f(i3).value < 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean n(int i2) {
        o();
        if (i2 != 0 && Rules.f(i2) != null) {
            if (f() > -1) {
                return true;
            }
            if (Rules.f(i2).stackable && l(i2) > 0) {
                return true;
            }
        }
        return false;
    }

    public final Boolean p() {
        for (int i2 = 0; i2 < 20; i2++) {
            if (this.itemList[i2] != 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public final void q(int i2) {
        int[] iArr;
        int i3;
        o();
        for (int i4 = 0; i4 < 20; i4++) {
            int i5 = this.itemList[i4];
            if (i5 == i2) {
                if (Rules.n(i5).booleanValue() && (i3 = (iArr = this.stackUnits)[i4]) > 1) {
                    iArr[i4] = i3 - 1;
                    return;
                } else {
                    this.itemList[i4] = 0;
                    this.stackUnits[i4] = 0;
                    return;
                }
            }
        }
    }

    public final void r(int i2, int i3) {
        int[] iArr = this.itemList;
        if (iArr[i3] == 0) {
            iArr[i3] = iArr[i2];
            int[] iArr2 = this.stackUnits;
            iArr2[i3] = iArr2[i2];
            iArr[i2] = 0;
            iArr2[i2] = 0;
        }
    }

    public final void s(int i2) {
        int[] iArr;
        int i3;
        o();
        if (i2 < 20) {
            if (Rules.n(this.itemList[i2]).booleanValue() && (i3 = (iArr = this.stackUnits)[i2]) > 1) {
                iArr[i2] = i3 - 1;
            } else {
                this.itemList[i2] = 0;
                this.stackUnits[i2] = 0;
            }
        }
    }

    public final void t(int i2, int i3) {
        this.itemList[i2] = i3;
    }

    public final String toString() {
        String str = "";
        for (int i2 : this.itemList) {
            if (i2 != 0) {
                str = str + i2 + " ";
            }
        }
        return str;
    }

    public final void u() {
        o();
        for (int i2 = 0; i2 < 20; i2++) {
            if (this.itemList[i2] != 0) {
                int i3 = this.stackUnits[i2];
                if (i3 == 0) {
                    GameData.v().player.A1(this.itemList[i2]);
                }
                while (i3 > 0) {
                    GameData.v().player.A1(this.itemList[i2]);
                    i3--;
                }
                this.itemList[i2] = 0;
                this.stackUnits[i2] = 0;
            }
        }
    }
}

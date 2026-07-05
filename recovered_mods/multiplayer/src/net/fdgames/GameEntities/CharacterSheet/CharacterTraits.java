package net.fdgames.GameEntities.CharacterSheet;

import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.coordinatorlayout.widget.OvMp.SkCylVE;
import com.google.android.gms.games.snapshot.hbR.cxRMW;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class CharacterTraits {
    private Rules.CharacterClass charClass;
    private int extraTraitPoints;
    private int[] spentPoints;
    private int[] value;

    public CharacterTraits() {
    }

    public CharacterTraits(int i2) {
        this.value = new int[6];
        this.spentPoints = new int[6];
        this.extraTraitPoints = 0;
    }

    private void a() {
        if (this.value == null) {
            this.value = new int[6];
        }
        if (this.spentPoints == null) {
            this.spentPoints = new int[6];
        }
    }

    public static String e(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "strenght" : "trait_personality" : "trait_awareness" : "trait_intellect" : "trait_agility" : "trait_endurance" : "trait_strenght";
    }

    public static String f(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : GameString.b("TRAIT_PER", false) : GameString.b("TRAIT_AWA", false) : GameString.b(SkCylVE.IkKTZiLEttT, false) : GameString.b(cxRMW.mxezWNXxjGxUtyd, false) : GameString.b("TRAIT_END", false) : GameString.b("TRAIT_STR", false);
    }

    public static String g(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : GameString.b("T_PER", false) : GameString.b(fApIihhYHIP.CMeZ, false) : GameString.b("T_INT", false) : GameString.b("T_AGI", false) : GameString.b("T_END", false) : GameString.b("T_STR", false);
    }

    public final void b() {
        this.extraTraitPoints++;
    }

    public final int c(int i2, boolean z2) {
        a();
        if (z2) {
            int i3 = (i2 * 2) + 2;
            i2 = i2 > 15 ? (i2 - 15) + i3 : i3;
        }
        int i4 = i2 + this.extraTraitPoints;
        for (int i5 = 0; i5 < 6; i5++) {
            i4 -= this.spentPoints[i5];
        }
        return i4;
    }

    public final int d(int i2) {
        a();
        return this.value[i2];
    }

    public final void h(int i2) {
        a();
        int[] iArr = this.spentPoints;
        if (iArr[i2] == 66) {
            iArr[i2] = 78;
        }
        if (iArr[i2] == 55) {
            iArr[i2] = 66;
        }
        if (iArr[i2] == 45) {
            iArr[i2] = 55;
        }
        if (iArr[i2] == 36) {
            iArr[i2] = 45;
        }
        if (iArr[i2] == 28) {
            iArr[i2] = 36;
        }
        if (iArr[i2] == 21) {
            iArr[i2] = 28;
        }
        if (iArr[i2] == 15) {
            iArr[i2] = 21;
        }
        if (iArr[i2] == 10) {
            iArr[i2] = 15;
        }
        if (iArr[i2] == 6) {
            iArr[i2] = 10;
        }
        if (iArr[i2] == 3) {
            iArr[i2] = 6;
        }
        if (iArr[i2] == 1) {
            iArr[i2] = 3;
        }
        if (iArr[i2] == 0) {
            iArr[i2] = 1;
        }
        i();
    }

    public final void i() {
        a();
        int i2 = 0;
        while (true) {
            int i3 = 6;
            if (i2 >= 6) {
                return;
            }
            int[] iArr = this.value;
            int i4 = this.spentPoints[i2];
            if (i4 >= 78) {
                i3 = 12;
            } else if (i4 >= 66) {
                i3 = 11;
            } else if (i4 >= 55) {
                i3 = 10;
            } else if (i4 >= 45) {
                i3 = 9;
            } else if (i4 >= 36) {
                i3 = 8;
            } else if (i4 >= 28) {
                i3 = 7;
            } else if (i4 < 21) {
                if (i4 >= 15) {
                    i3 = 5;
                } else if (i4 >= 10) {
                    i3 = 4;
                } else if (i4 >= 6) {
                    i3 = 3;
                } else if (i4 >= 3) {
                    i3 = 2;
                } else {
                    i3 = 1;
                    if (i4 < 1) {
                        i3 = 0;
                    }
                }
            }
            iArr[i2] = i3;
            i2++;
        }
    }

    public final void j() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.spentPoints[i2] = 0;
        }
        i();
    }

    public final String toString() {
        return "" + this.value[0] + "," + this.value[1] + "," + this.value[2] + "," + this.value[3] + "," + this.value[4] + "," + this.value[5] + " b:" + this.extraTraitPoints;
    }
}

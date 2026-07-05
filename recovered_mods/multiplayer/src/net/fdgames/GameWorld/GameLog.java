package net.fdgames.GameWorld;

import a.a;
import android.support.v4.app.mFy.fApIihhYHIP;
import java.util.ArrayList;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameLog {
    public String lastLines;
    private ArrayList<LogLine> lines = new ArrayList<>();
    private float lastUpdate = 0.0f;

    public final void a(String str) {
        this.lines.add(new LogLine(str.replace("--", "+")));
        if (this.lines.size() > 50) {
            this.lines.remove(0);
        }
        k();
    }

    public final void b(int i2, int i3) {
        String string = "[YELLOW]" + GameData.v().player.getName() + ":+" + i2 + " XP";
        if (i3 > 0) {
            StringBuilder sbS = a.s(string, ". ");
            sbS.append(GameData.v().party.f().getName());
            sbS.append(":+");
            sbS.append(i3);
            sbS.append(" XP");
            string = sbS.toString();
        }
        a(string + "[]");
    }

    public final void c(int i2) {
        a("[YELLOW]" + GameString.b("GOLD_GAINED", false) + " " + i2 + "[]");
    }

    public final void d(int i2) {
        a("[YELLOW]" + GameString.b("ITEM_GAINED", false) + ": " + Rules.g(i2) + "[]");
    }

    public final void e(int i2, int i3) {
        a("[YELLOW]" + GameString.b("ITEM_GAINED", false) + ": " + Rules.g(i2) + " (" + i3 + fApIihhYHIP.PwhbVAGPpwuWc);
    }

    public final ArrayList<LogLine> f() {
        return this.lines;
    }

    public final float g() {
        return GameData.v().u() - this.lastUpdate;
    }

    public final void h(int i2) {
        a("[YELLOW]" + GameString.b("GOLD_LOST", false) + ": " + i2 + "[]");
    }

    public final void i(int i2) {
        a("[YELLOW]" + GameString.b("ITEM_LOST", false) + ": " + Rules.g(i2) + "[]");
    }

    public final void j(int i2, int i3) {
        a("[YELLOW]" + GameString.b("ITEM_LOST", false) + ": " + Rules.g(i2) + " (" + i3 + ")[]");
    }

    public final void k() {
        this.lastLines = "";
        this.lastUpdate = GameData.v().u();
        if (this.lines.size() >= 1) {
            ArrayList<LogLine> arrayList = this.lines;
            if (!arrayList.get(arrayList.size() - 1).c() || ExiledKingdoms.f3606h) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.lastLines);
                ArrayList<LogLine> arrayList2 = this.lines;
                sb.append(arrayList2.get(arrayList2.size() - 1).b());
                this.lastLines = sb.toString();
            }
        }
        if (this.lines.size() >= 2) {
            ArrayList<LogLine> arrayList3 = this.lines;
            if (!arrayList3.get(arrayList3.size() - 2).c() || ExiledKingdoms.f3606h) {
                StringBuilder sb2 = new StringBuilder();
                ArrayList<LogLine> arrayList4 = this.lines;
                sb2.append(arrayList4.get(arrayList4.size() - 2).b());
                sb2.append("\r\n");
                sb2.append(this.lastLines);
                this.lastLines = sb2.toString();
            }
        }
        if (this.lines.size() >= 3) {
            ArrayList<LogLine> arrayList5 = this.lines;
            if (!arrayList5.get(arrayList5.size() - 3).c() || ExiledKingdoms.f3606h) {
                StringBuilder sb3 = new StringBuilder();
                ArrayList<LogLine> arrayList6 = this.lines;
                sb3.append(arrayList6.get(arrayList6.size() - 3).b());
                sb3.append("\r\n");
                sb3.append(this.lastLines);
                this.lastLines = sb3.toString();
            }
        }
        if (this.lines.size() >= 4) {
            ArrayList<LogLine> arrayList7 = this.lines;
            if (!arrayList7.get(arrayList7.size() - 4).c() || ExiledKingdoms.f3606h) {
                StringBuilder sb4 = new StringBuilder();
                ArrayList<LogLine> arrayList8 = this.lines;
                sb4.append(arrayList8.get(arrayList8.size() - 4).b());
                sb4.append("\r\n");
                sb4.append(this.lastLines);
                this.lastLines = sb4.toString();
            }
        }
        if (this.lines.size() >= 5) {
            ArrayList<LogLine> arrayList9 = this.lines;
            if (!arrayList9.get(arrayList9.size() - 5).c() || ExiledKingdoms.f3606h) {
                StringBuilder sb5 = new StringBuilder();
                ArrayList<LogLine> arrayList10 = this.lines;
                sb5.append(arrayList10.get(arrayList10.size() - 5).b());
                sb5.append("\r\n");
                sb5.append(this.lastLines);
                this.lastLines = sb5.toString();
            }
        }
    }
}

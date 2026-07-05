package net.fdgames.GameWorld;

import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class LogLine {
    private String message;
    public float time;

    public LogLine() {
    }

    public final String a() {
        StringBuilder sb = new StringBuilder("[[");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("D " + (((int) GameLevel.b()) / 1080));
        sb2.append(", ");
        sb2.append(FDUtils.k());
        sb.append(sb2.toString());
        sb.append("] ");
        sb.append(this.message);
        return sb.toString();
    }

    public final String b() {
        return ">" + this.message;
    }

    public final boolean c() {
        return this.time + 30.0f < GameData.v().u();
    }

    public LogLine(String str) {
        this.time = GameData.v().u();
        this.message = str;
    }
}

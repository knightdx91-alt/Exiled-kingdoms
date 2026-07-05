package net.fdgames.ek;

import a.a;
import android.provider.Settings;
import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.g;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.c;
import com.google.android.gms.common.api.Mcc.KOdB;
import h0.LN.EXicjtag;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import l0.KUbz.MbzYTTrg;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameMusic;
import net.fdgames.ek.android.MainActivity;
import x0.b;
import x0.e;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Settings {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ControllerConfig f3648a = new ControllerConfig();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static KeyboardConfig f3649b = new KeyboardConfig();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SettingsData f3650c = new SettingsData();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static GameVariables f3651d = new GameVariables();

    public static void A(boolean z2) {
        Properties propertiesW = Serializer.w();
        if (z2) {
            propertiesW.setProperty("fullscreen", "1");
        } else {
            propertiesW.setProperty("fullscreen", "0");
        }
        Serializer.A(propertiesW);
    }

    public static void B(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.GPGSAutoConnect = z2;
        ExiledKingdoms.f3605g = z2;
        x();
    }

    public static void C(int i2, String str) {
        if (f3651d == null) {
            f3651d = new GameVariables();
        }
        f3651d.a(str);
        f3651d.c(i2, str);
        b.f3909r = true;
    }

    public static void D(int i2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.language = i2;
        x();
    }

    public static void E(float f2) {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return;
        }
        settingsData.MusicVolume = f2;
        Assets.w(f2);
        GameMusic.b(f2);
        x();
    }

    public static void F(Date date) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(EXicjtag.dGbiqSDssxqb));
        f3650c.registrationDate = simpleDateFormat.format(date);
        x();
    }

    public static void G(String str) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.orderID = str;
    }

    public static void H(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.showControls = z2;
        z.f4479w0 = z2;
        x();
    }

    public static void I(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.showNumbersBars = z2;
        x();
    }

    public static void J(float f2) {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return;
        }
        settingsData.SoundVolume = f2;
        x();
    }

    public static void K(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.useOldFonts = z2;
        x();
    }

    public static void L(float f2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.lowMControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.C()) {
                z.X(f2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        x();
    }

    public static void M(float f2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.sideMControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.C()) {
                z.f4478v0 = f2;
                z.Z(z.f4476t0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        x();
    }

    public static void N(float f2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.sizeControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.C()) {
                z.Z(f2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        x();
    }

    public static boolean O() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        SettingsData settingsData = f3650c;
        return settingsData.useOldFonts || settingsData.language == 3 || Gdx.graphics.getHeight() < 720;
    }

    public static void P() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.activationCode = 0;
        x();
    }

    public static boolean Q() {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return true;
        }
        return settingsData.orderID.contains(JNrsKSCxIEXndG.oRYkFNrA);
    }

    public static String a() {
        GameVariables gameVariables = f3651d;
        return gameVariables != null ? gameVariables.toString() : "";
    }

    public static boolean b() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        return f3650c.attackInteracts;
    }

    public static String c(ControllerCommand controllerCommand) {
        ControllerConfig controllerConfig = f3648a;
        if (controllerCommand == controllerConfig.up) {
            return GameString.b("CONTROLLER_PRESS_UP", false);
        }
        if (controllerCommand == controllerConfig.down) {
            return GameString.b("CONTROLLER_PRESS_DOWN", false);
        }
        if (controllerCommand == controllerConfig.left) {
            return GameString.b("CONTROLLER_PRESS_LEFT", false);
        }
        if (controllerCommand == controllerConfig.right) {
            return GameString.b("CONTROLLER_PRESS_RIGHT", false);
        }
        if (controllerCommand == controllerConfig.action) {
            return GameString.b(KOdB.rxdSeVBIj, false);
        }
        if (controllerCommand == controllerConfig.cancel) {
            return GameString.b("CONTROLLER_PRESS_BACK", false);
        }
        if (controllerCommand == controllerConfig.skill1) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 1");
        }
        if (controllerCommand == controllerConfig.skill2) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 2");
        }
        if (controllerCommand == controllerConfig.skill3) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 3");
        }
        if (controllerCommand == controllerConfig.skill4) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 4");
        }
        if (controllerCommand == controllerConfig.skill5) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 5");
        }
        if (controllerCommand == controllerConfig.skill6) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 6");
        }
        if (controllerCommand == controllerConfig.skill7) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 7");
        }
        if (controllerCommand == controllerConfig.skill8) {
            return a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 8");
        }
        if (controllerCommand == controllerConfig.nextItem) {
            return GameString.b("CONTROLLER_PRESS_NEXT_QUICKSLOT", false);
        }
        if (controllerCommand == controllerConfig.prevItem) {
            return GameString.b("CONTROLLER_PRESS_PREV_QUICKSLOT", false);
        }
        if (controllerCommand == controllerConfig.useItem) {
            return GameString.b("CONTROLLER_PRESS_USE_QUICKSLOT", false);
        }
        if (controllerCommand == controllerConfig.recovery) {
            return GameString.b("CONTROLLER_PRESS_RECOVERY", false);
        }
        if (controllerCommand == controllerConfig.invTake) {
            StringBuilder sb = new StringBuilder();
            a.v("TAKE", false, sb, "/");
            a.v("BUY", false, sb, "/");
            return a.n("EQUIP", false, sb);
        }
        if (controllerCommand != controllerConfig.invDrop) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        a.v("DROP", false, sb2, "/");
        return a.n("SELL", false, sb2);
    }

    public static boolean d() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        return f3650c.GPGSAutoConnect;
    }

    public static int e(String str) {
        GameVariables gameVariables = f3651d;
        if (gameVariables == null) {
            return 0;
        }
        return gameVariables.b(str);
    }

    public static String f() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.getClass();
        e.o();
        String strSubstring = Settings.Secure.getString(((MainActivity) ExiledKingdoms.f()).getContext().getContentResolver(), MbzYTTrg.AaHx).substring(r0.length() - 5);
        return !strSubstring.matches("[0-9a-fA-F]+") ? "99999" : strSubstring;
    }

    public static String g(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "-1" : g.a.a(f3649b.quickItem5.id) : g.a.a(f3649b.quickItem4.id) : g.a.a(f3649b.quickItem3.id) : g.a.a(f3649b.quickItem2.id) : g.a.a(f3649b.quickItem1.id);
    }

    public static int h() {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return 1;
        }
        return settingsData.language;
    }

    public static float i() {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return 0.0f;
        }
        return settingsData.MusicVolume;
    }

    public static String j() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        String string = "C:" + Integer.toHexString(f3650c.activationCode);
        if (f3650c.registrationDate != null) {
            StringBuilder sbS = a.s(string, " ");
            sbS.append(f3650c.registrationDate);
            string = sbS.toString();
        }
        if (f3650c.orderID == null) {
            return string;
        }
        StringBuilder sbS2 = a.s(string, " ");
        sbS2.append(f3650c.orderID);
        return sbS2.toString();
    }

    public static boolean k() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        if (ExiledKingdoms.f3606h) {
            return false;
        }
        return f3650c.showControls;
    }

    public static boolean l() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        return f3650c.showNumbersBars;
    }

    public static float m() {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return 0.0f;
        }
        return settingsData.SoundVolume;
    }

    public static boolean n() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        return f3650c.useOldFonts;
    }

    public static boolean o() {
        SettingsData settingsData = f3650c;
        if (settingsData == null) {
            return false;
        }
        return settingsData.combatLog;
    }

    public static float p() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        float f2 = f3650c.lowMControls;
        if (f2 < 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static float q() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        float f2 = f3650c.sideMControls;
        if (f2 < 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static float r() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        if (f3650c.sizeControls == 0.0f) {
            float width = Gdx.graphics.getWidth() / Gdx.graphics.getPpiX();
            float height = Gdx.graphics.getHeight() / Gdx.graphics.getPpiY();
            double dSqrt = Math.sqrt((height * height) + (width * width));
            if (dSqrt <= 5.1d) {
                f3650c.sizeControls = 1.0f;
            }
            if (dSqrt > 5.1d && dSqrt <= 7.0d) {
                f3650c.sizeControls = 0.9f;
            }
            if (dSqrt > 7.0d) {
                f3650c.sizeControls = 0.8f;
            }
        }
        float f2 = f3650c.sizeControls;
        if (f2 < 0.6f || f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static void s(String str) {
        if (f3651d == null) {
            f3651d = new GameVariables();
        }
        f3651d.c(1, str);
    }

    public static Boolean t() {
        if (ExiledKingdoms.f3617s) {
            ExiledKingdoms.f3599a = true;
            return true;
        }
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        ExiledKingdoms.f3599a = true;
        return true;
    }

    public static void u() {
        if (Gdx.files.local("data/config/settings.b").exists()) {
            try {
                f3650c = (SettingsData) new Json().fromJson(SettingsData.class, c.c(Gdx.files.local("data/config/settings.b").readString()));
                Gdx.files.local("data/config/settings.ini").delete();
            } catch (Exception unused) {
                Gdx.files.local("data/config/settings.b").delete();
                f3650c = null;
            }
        } else if (Gdx.files.local("data/config/settings.ini").exists()) {
            f3650c = (SettingsData) new Json().fromJson(SettingsData.class, Gdx.files.local("data/config/settings.ini").readString());
        }
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        if (Gdx.files.local("data/saves/global.sav").exists()) {
            Json json = new Json();
            json.setIgnoreUnknownFields(true);
            try {
                f3651d = (GameVariables) json.fromJson(GameVariables.class, c.c(Gdx.files.local("data/saves/global.sav").readString()));
            } catch (Exception unused2) {
            }
        }
        if (Gdx.files.local("data/config/controls.ini").exists()) {
            Json json2 = new Json();
            json2.setIgnoreUnknownFields(true);
            try {
                f3648a = (ControllerConfig) json2.fromJson(ControllerConfig.class, c.c(Gdx.files.local("data/config/controls.ini").readString()));
            } catch (Exception unused3) {
            }
        }
        if (Gdx.files.local("data/config/keys.ini").exists()) {
            Json json3 = new Json();
            json3.setIgnoreUnknownFields(true);
            try {
                f3649b = (KeyboardConfig) json3.fromJson(KeyboardConfig.class, c.c(Gdx.files.local("data/config/keys.ini").readString()));
            } catch (Exception unused4) {
            }
        }
        if (f3648a == null) {
            f3648a = new ControllerConfig();
        }
        if (f3649b == null) {
            f3649b = new KeyboardConfig();
        }
    }

    public static void v() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.activationCode = Integer.parseInt("AAAAAA", 16);
        f3651d.e(1, "GL_keycode");
        F(new Date());
        ExiledKingdoms.f3599a = t().booleanValue();
    }

    public static void w() {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        if (ExiledKingdoms.f3617s) {
            ExiledKingdoms.f3599a = true;
            return;
        }
        try {
            SettingsData settingsData = f3650c;
            settingsData.getClass();
            e.o();
            String strSubstring = Settings.Secure.getString(((MainActivity) ExiledKingdoms.f()).getContext().getContentResolver(), "android_id").substring(r2.length() - 5);
            if (!strSubstring.matches("[0-9a-fA-F]+")) {
                strSubstring = "99999";
            }
            settingsData.activationCode = 16777213 - Integer.parseInt(strSubstring, 16);
            ExiledKingdoms.f3599a = true;
            x();
        } catch (Exception unused) {
            d1.a.a("License check exception");
            ExiledKingdoms.f3603e = true;
        }
    }

    public static void x() {
        Gdx.files.local("data/config/settings.b").writeString(c.f(new Json().prettyPrint(f3650c)), false);
        Json json = new Json();
        json.setIgnoreUnknownFields(true);
        Gdx.files.local("data/saves/global.sav").writeString(c.f(json.prettyPrint(f3651d)), false);
        Json json2 = new Json();
        json2.setIgnoreUnknownFields(true);
        Gdx.files.local("data/config/controls.ini").writeString(c.f(json2.prettyPrint(f3648a)), false);
        Json json3 = new Json();
        json3.setIgnoreUnknownFields(true);
        Gdx.files.local("data/config/keys.ini").writeString(c.f(json3.prettyPrint(f3649b)), false);
    }

    public static void y(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.attackInteracts = z2;
        x();
    }

    public static void z(boolean z2) {
        if (f3650c == null) {
            f3650c = new SettingsData();
        }
        f3650c.combatLog = z2;
        x();
    }
}

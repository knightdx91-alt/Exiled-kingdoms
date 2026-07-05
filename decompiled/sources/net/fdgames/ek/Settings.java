package net.fdgames.ek;

import a.a;
import android.provider.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.g;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.c;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import l0.b;
import l0.e;
import n0.z;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameMusic;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Settings {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ControllerConfig f3418a = new ControllerConfig();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static KeyboardConfig f3419b = new KeyboardConfig();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SettingsData f3420c = new SettingsData();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static GameVariables f3421d = new GameVariables();

    public static void A(int i2, String str) {
        if (f3421d == null) {
            f3421d = new GameVariables();
        }
        f3421d.e(i2, str);
        b.f2317r = true;
    }

    public static void B(int i2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.language = i2;
        v();
    }

    public static void C(float f2) {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return;
        }
        settingsData.MusicVolume = f2;
        Assets.w(f2);
        GameMusic.b(f2);
        v();
    }

    public static void D(Date date) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        f3420c.registrationDate = simpleDateFormat.format(date);
        v();
    }

    public static void E(String str) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.orderID = str;
    }

    public static void F(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.showControls = z2;
        z.w0 = z2;
        v();
    }

    public static void G(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.showNumbersBars = z2;
        v();
    }

    public static void H(float f2) {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return;
        }
        settingsData.SoundVolume = f2;
        v();
    }

    public static void I(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.useOldFonts = z2;
        v();
    }

    public static void J(float f2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.lowMControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.B()) {
                z.V(f2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        v();
    }

    public static void K(float f2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.sideMControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.B()) {
                z.v0 = f2;
                z.X(z.t0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        v();
    }

    public static void L(float f2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.sizeControls = f2;
        try {
            GameData.v().getClass();
            if (GameData.I() && z.B()) {
                z.X(f2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        v();
    }

    public static boolean M() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        SettingsData settingsData = f3420c;
        return settingsData.useOldFonts || settingsData.language == 3 || Gdx.graphics.getHeight() < 720;
    }

    public static void N() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.activationCode = 0;
        v();
    }

    public static String a() {
        GameVariables gameVariables = f3421d;
        return gameVariables != null ? gameVariables.toString() : "";
    }

    public static boolean b() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        return f3420c.attackInteracts;
    }

    public static String c(ControllerCommand controllerCommand) {
        ControllerConfig controllerConfig = f3418a;
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
            return GameString.b("CONTROLLER_PRESS_ATTACK", false);
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
            a.w("TAKE", false, sb, "/");
            a.w("BUY", false, sb, "/");
            return a.n("EQUIP", false, sb);
        }
        if (controllerCommand != controllerConfig.invDrop) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        a.w("DROP", false, sb2, "/");
        return a.n("SELL", false, sb2);
    }

    public static boolean d() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        return f3420c.GPGSAutoConnect;
    }

    public static int e(String str) {
        GameVariables gameVariables = f3421d;
        if (gameVariables == null) {
            return 0;
        }
        return gameVariables.b(str);
    }

    public static String f() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.getClass();
        e.o();
        String strSubstring = Settings.Secure.getString(((MainActivity) ExiledKingdoms.f()).getContext().getContentResolver(), "android_id").substring(r0.length() - 5);
        return !strSubstring.matches("[0-9a-fA-F]+") ? "99999" : strSubstring;
    }

    public static String g(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "-1" : g.a.a(f3419b.quickItem5.id) : g.a.a(f3419b.quickItem4.id) : g.a.a(f3419b.quickItem3.id) : g.a.a(f3419b.quickItem2.id) : g.a.a(f3419b.quickItem1.id);
    }

    public static int h() {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return 1;
        }
        return settingsData.language;
    }

    public static float i() {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return 0.0f;
        }
        return settingsData.MusicVolume;
    }

    public static String j() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        String string = "C:" + Integer.toHexString(f3420c.activationCode);
        if (f3420c.registrationDate != null) {
            StringBuilder sbT = a.t(string, " ");
            sbT.append(f3420c.registrationDate);
            string = sbT.toString();
        }
        if (f3420c.orderID == null) {
            return string;
        }
        StringBuilder sbT2 = a.t(string, " ");
        sbT2.append(f3420c.orderID);
        return sbT2.toString();
    }

    public static boolean k() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        if (ExiledKingdoms.f3378h) {
            return false;
        }
        return f3420c.showControls;
    }

    public static boolean l() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        return f3420c.showNumbersBars;
    }

    public static float m() {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return 0.0f;
        }
        return settingsData.SoundVolume;
    }

    public static boolean n() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        return f3420c.useOldFonts;
    }

    public static boolean o() {
        SettingsData settingsData = f3420c;
        if (settingsData == null) {
            return false;
        }
        return settingsData.combatLog;
    }

    public static float p() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        float f2 = f3420c.lowMControls;
        if (f2 < 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static float q() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        float f2 = f3420c.sideMControls;
        if (f2 < 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static float r() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        if (f3420c.sizeControls == 0.0f) {
            float width = Gdx.graphics.getWidth() / Gdx.graphics.getPpiX();
            float height = Gdx.graphics.getHeight() / Gdx.graphics.getPpiY();
            double dSqrt = Math.sqrt((height * height) + (width * width));
            if (dSqrt <= 5.1d) {
                f3420c.sizeControls = 1.0f;
            }
            if (dSqrt > 5.1d && dSqrt <= 7.0d) {
                f3420c.sizeControls = 0.9f;
            }
            if (dSqrt > 7.0d) {
                f3420c.sizeControls = 0.8f;
            }
        }
        float f2 = f3420c.sizeControls;
        if (f2 < 0.6f || f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public static void s(String str) {
        if (f3421d == null) {
            f3421d = new GameVariables();
        }
        f3421d.c(1, str);
    }

    public static void t() {
        if (Gdx.files.local("data/config/settings.b").exists()) {
            try {
                f3420c = (SettingsData) new Json().fromJson(SettingsData.class, c.c(Gdx.files.local("data/config/settings.b").readString()));
                Gdx.files.local("data/config/settings.ini").delete();
            } catch (Exception unused) {
                Gdx.files.local("data/config/settings.b").delete();
                f3420c = null;
            }
        } else if (Gdx.files.local("data/config/settings.ini").exists()) {
            f3420c = (SettingsData) new Json().fromJson(SettingsData.class, Gdx.files.local("data/config/settings.ini").readString());
        }
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        if (Gdx.files.local("data/saves/global.sav").exists()) {
            Json json = new Json();
            json.setIgnoreUnknownFields(true);
            try {
                f3421d = (GameVariables) json.fromJson(GameVariables.class, c.c(Gdx.files.local("data/saves/global.sav").readString()));
            } catch (Exception unused2) {
            }
        }
        if (Gdx.files.local("data/config/controls.ini").exists()) {
            Json json2 = new Json();
            json2.setIgnoreUnknownFields(true);
            try {
                f3418a = (ControllerConfig) json2.fromJson(ControllerConfig.class, c.c(Gdx.files.local("data/config/controls.ini").readString()));
            } catch (Exception unused3) {
            }
        }
        if (Gdx.files.local("data/config/keys.ini").exists()) {
            Json json3 = new Json();
            json3.setIgnoreUnknownFields(true);
            try {
                f3419b = (KeyboardConfig) json3.fromJson(KeyboardConfig.class, c.c(Gdx.files.local("data/config/keys.ini").readString()));
            } catch (Exception unused4) {
            }
        }
        if (f3418a == null) {
            f3418a = new ControllerConfig();
        }
        if (f3419b == null) {
            f3419b = new KeyboardConfig();
        }
    }

    public static void u() {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        ExiledKingdoms.f3371a = true;
    }

    public static void v() {
        Gdx.files.local("data/config/settings.b").writeString(c.f(new Json().prettyPrint(f3420c)), false);
        Json json = new Json();
        json.setIgnoreUnknownFields(true);
        Gdx.files.local("data/saves/global.sav").writeString(c.f(json.prettyPrint(f3421d)), false);
        Json json2 = new Json();
        json2.setIgnoreUnknownFields(true);
        Gdx.files.local("data/config/controls.ini").writeString(c.f(json2.prettyPrint(f3418a)), false);
        Json json3 = new Json();
        json3.setIgnoreUnknownFields(true);
        Gdx.files.local("data/config/keys.ini").writeString(c.f(json3.prettyPrint(f3419b)), false);
    }

    public static void w(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.attackInteracts = z2;
        v();
    }

    public static void x(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.combatLog = z2;
        v();
    }

    public static void y(boolean z2) {
        Properties propertiesW = Serializer.w();
        if (z2) {
            propertiesW.setProperty("fullscreen", "1");
        } else {
            propertiesW.setProperty("fullscreen", "0");
        }
        Serializer.A(propertiesW);
    }

    public static void z(boolean z2) {
        if (f3420c == null) {
            f3420c = new SettingsData();
        }
        f3420c.GPGSAutoConnect = z2;
        ExiledKingdoms.f3377g = z2;
        v();
    }
}

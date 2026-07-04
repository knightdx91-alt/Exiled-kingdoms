package net.fdgames.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.GdxNativesLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import m0.b;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.Projectile;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.BasicGameData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Rules.Rules;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Serializer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Integer[] f3224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f3225b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Json f3227d = new Json();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3228e = false;

    public static void A(Properties properties) {
        try {
            properties.store(new FileOutputStream(System.getProperty("user.dir") + "/init.ini"), (String) null);
        } catch (FileNotFoundException e2) {
            l.d("ERROR while writing config file(1): " + e2.getLocalizedMessage());
        } catch (IOException e3) {
            l.d("ERROR while writing config file(2): " + e3.getLocalizedMessage());
        }
    }

    public static void B() {
        int i2 = GameData.v().slot;
        Json json = f3227d;
        json.setIgnoreUnknownFields(true);
        if (i2 == -1 || GameLevelData.o().D()) {
            return;
        }
        String str = GameData.v().CurrentLevel;
        GameLevelData.o().getClass();
        GameLevelData.G();
        String strPrettyPrint = json.prettyPrint(GameLevelData.o());
        Gdx.files.local(e(i2) + str + ".sav").writeString(i(strPrettyPrint), false, "UTF-8");
        l.d("Serializer.saveLevel(" + i2 + "," + str + ")");
        D();
    }

    public static String C(int i2, int i3) {
        if (i3 == 1) {
            return y(i2) + "game.sav";
        }
        if (i3 == 2) {
            return y(i2) + "game2.sav";
        }
        if (i3 == 3) {
            return y(i2) + "game3.sav";
        }
        if (i3 == 4) {
            return y(i2) + "game4.sav";
        }
        if (i3 == 5) {
            return y(i2) + "game5.sav";
        }
        if (i3 == 6) {
            return y(i2) + "game6.sav";
        }
        if (i3 == 7) {
            return y(i2) + "auto2.sav";
        }
        return y(i2) + "auto.sav";
    }

    public static void D() {
        int i2 = GameData.v().slot;
        String str = GameData.v().CurrentLevel;
        if (b.P().f2419d != null) {
            try {
                boolean zExists = Gdx.files.local("data/saves/" + i2 + "/levels/" + str + "_tiles.txt").exists();
                Json json = f3227d;
                if (zExists) {
                    byte[][] bArr = (byte[][]) json.fromJson(byte[][].class, Gdx.files.local("data/saves/" + i2 + "/levels/" + str + "_tiles.txt").readString());
                    byte[][] bArr2 = b.P().f2419d;
                    if (bArr == null || bArr.length < 1) {
                        return;
                    }
                    byte[] bArr3 = bArr[0];
                    if (bArr3.length >= 1 && bArr2 != null && bArr2.length >= 1) {
                        byte[] bArr4 = bArr2[0];
                        if (bArr4.length >= 1 && bArr.length == bArr2.length && bArr3.length == bArr4.length) {
                            int i3 = 0;
                            for (byte[] bArr5 : bArr) {
                                for (int i4 = 0; i4 < bArr[0].length; i4++) {
                                    i3 += bArr5[i4];
                                }
                            }
                            byte[][] bArr6 = b.P().f2419d;
                            int i5 = 0;
                            for (byte[] bArr7 : bArr6) {
                                for (int i6 = 0; i6 < bArr6[0].length; i6++) {
                                    i5 += bArr7[i6];
                                }
                            }
                            if (i3 >= i5) {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                Gdx.files.local("data/saves/" + i2 + "/levels/" + str + "_tiles.txt").writeString(json.prettyPrint(b.P().f2419d), false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean E() {
        for (int i2 = 0; i2 < 10; i2++) {
            if (Gdx.files.local(C(i2, 0)).exists() || Gdx.files.local(C(i2, 1)).exists()) {
                return true;
            }
        }
        return false;
    }

    public static void F(File file, File file2) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    return;
                }
                File file3 = new File(file2, nextEntry.getName());
                if (!file3.getCanonicalPath().startsWith(file2.getCanonicalPath())) {
                    throw new SecurityException("zip file path is incorrect");
                }
                File parentFile = nextEntry.isDirectory() ? file3 : file3.getParentFile();
                if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                    throw new FileNotFoundException("Failed to ensure directory: " + parentFile.getAbsolutePath());
                }
                if (!nextEntry.isDirectory()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file3);
                    while (true) {
                        try {
                            int i2 = zipInputStream.read(bArr);
                            if (i2 == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, i2);
                            }
                        } catch (Throwable th) {
                            fileOutputStream.close();
                            throw th;
                        }
                    }
                    fileOutputStream.close();
                }
            }
        } catch (Throwable th2) {
            zipInputStream.close();
            throw th2;
        }
    }

    public static void G() {
        String strO;
        ExiledKingdoms.f3382l = GameData.GameStatus.STOPPED;
        f3224a = new Integer[10];
        for (int i2 = 0; i2 < 10; i2++) {
            f3224a[i2] = -1;
        }
        Json json = f3227d;
        json.setIgnoreUnknownFields(true);
        for (int i3 = 0; i3 < 10; i3++) {
            float f2 = 0.0f;
            int i4 = 0;
            boolean z2 = true;
            for (int i5 = 0; i5 < 8; i5++) {
                if (Gdx.files.local(C(i3, i5)).exists()) {
                    try {
                        SaveGameData saveGameData = (SaveGameData) json.fromJson(SaveGameData.class, h(Gdx.files.local(C(i3, i5)).readString()));
                        if (saveGameData == null || !q(saveGameData.version)) {
                            strO = "<" + GameString.b("INCOMPATIBLE_FILE", false) + ">";
                        } else {
                            GameData.K(saveGameData.gamedata);
                            strO = l();
                            int i6 = saveGameData.gamedata.player.sheet.stats.i();
                            float fU = saveGameData.gamedata.u();
                            if ((i6 > i4 && fU > f2) || (i6 == i4 && fU > f2)) {
                                try {
                                    f3224a[i3] = Integer.valueOf(i5);
                                    i4 = i6;
                                    f2 = fU;
                                } catch (Exception e2) {
                                    e = e2;
                                    i4 = i6;
                                    f2 = fU;
                                    e.printStackTrace();
                                    strO = a.a.o("INCOMPATIBLE_FILE", false, new StringBuilder("<"), ">");
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                    if (strO != "") {
                        Gdx.files.local(p(i3, i5)).writeString(strO, false, "UTF-8");
                    } else {
                        Gdx.files.local(p(i3, i5)).delete();
                    }
                    z2 = false;
                } else {
                    Gdx.files.local(p(i3, i5)).delete();
                }
            }
            if (z2) {
                Gdx.files.local(y(i3)).deleteDirectory();
                f3224a[i3] = -1;
            }
        }
    }

    public static void a(int i2, int i3) {
        Json json = f3227d;
        json.setIgnoreUnknownFields(true);
        l.d("Serializer.LoadGame(" + i2 + "," + i3 + ")");
        f3228e = true;
        if (i3 != 0) {
            Gdx.files.local(e(i2)).deleteDirectory();
        }
        a aVarLocal = Gdx.files.local(C(i2, i3));
        SaveGameData saveGameData = (SaveGameData) json.fromJson(SaveGameData.class, h(aVarLocal.readString()));
        if (saveGameData.leveldata.D()) {
            l.d("WARNING: saved level in savegamedata is EMPTY! retrying load");
            saveGameData = (SaveGameData) json.fromJson(SaveGameData.class, h(aVarLocal.readString()));
            if (saveGameData.leveldata.D()) {
                l.d("WARNING: on second attempt, saved level in savegamedata is still EMPTY! third attempt");
                saveGameData = (SaveGameData) json.fromJson(SaveGameData.class, h(aVarLocal.readString()));
                if (saveGameData.leveldata.D()) {
                    l.d("WARNING: saved level in savegamedata is EMPTY after three tries.");
                }
            }
        }
        l.d("Serializer.LoadGame() > gamedata loading(" + i2 + "," + i3 + ")");
        GameData.K(saveGameData.gamedata);
        Coords coords = new Coords(GameData.v().player.f3092x, GameData.v().player.f3093y);
        e eVar = (e) Gdx.app.getApplicationListener();
        eVar.c(new l0.b(eVar, new Transition(saveGameData.gamedata.CurrentLevel, 1)));
        GameData.v().player.f3092x = coords.f3287x;
        GameData.v().player.f3093y = coords.f3288y;
        l.d("Serializer.LoadGame() > loading level");
        GameLevelData.E(saveGameData.leveldata);
        if (GameLevelData.f3104c) {
            l.d("Serializer.LoadGame() > wrong level data detected!!!  re-loading map without loading level data");
            l0.b.n();
        }
        GameLevelData.o().C();
        GameLevelData gameLevelDataO = GameLevelData.o();
        gameLevelDataO.getClass();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameData.v().player.x(100);
        Iterator<NPC> it = GameData.v().party.companions.iterator();
        while (it.hasNext()) {
            it.next().x(100);
        }
        Iterator<NPC> it2 = gameLevelDataO.npcs.iterator();
        while (it2.hasNext()) {
            it2.next().W1();
        }
        Iterator<Trap> it3 = gameLevelDataO.traps.iterator();
        while (it3.hasNext()) {
            it3.next().a0();
        }
        Iterator<Projectile> it4 = gameLevelDataO.projectiles.iterator();
        while (it4.hasNext()) {
            it4.next().O();
        }
        Iterator<MonsterSpawn> it5 = gameLevelDataO.spawns.iterator();
        while (it5.hasNext()) {
            it5.next().R();
        }
        WorldFactions.j();
        GameLevelData gameLevelDataO2 = GameLevelData.o();
        gameLevelDataO2.getClass();
        b.P().getClass();
        if (b.e(192.0d, false) == null) {
            for (NPC npc : gameLevelDataO2.npcs) {
                for (MonsterSpawn monsterSpawn : gameLevelDataO2.spawns) {
                    if (npc.L1() == monsterSpawn.q() && !npc.i0() && !npc.k0() && !npc.s() && npc.ai_type.equals("patroller")) {
                        npc.f3092x = monsterSpawn.f3092x;
                        npc.f3093y = monsterSpawn.f3093y;
                    }
                }
            }
        }
        if (GameLevelData.o().mapItems == null) {
            GameLevelData.o().mapItems = new ArrayList<>();
        }
        for (int i4 = 0; i4 < GameLevelData.r().size(); i4++) {
            GameLevelData.r().get(i4).T();
        }
        l.d("Serializer.LoadGame() > loading tiles");
        t(GameData.v().CurrentLevel);
        if (!GameLevelData.f3104c) {
            MessageRouter.d(saveGameData.queue);
        }
        k0.a.l().i();
        GameLevelData.f3104c = false;
        z.v();
        z.v().Y();
        z.v().W();
        Patching.a(n(saveGameData.version));
        String[] strArrSplit = Gdx.files.internal("data/rules/cleanvariables.txt").readString().split("\n");
        for (int i5 = 1; i5 < strArrSplit.length; i5++) {
            String strReplace = strArrSplit[i5].replace("\n", "");
            strArrSplit[i5] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i5] = strReplace2;
            String[] strArrSplit2 = strReplace2.split("\t", -1);
            ConditionsSet conditionsSet = new ConditionsSet(strArrSplit2[0]);
            String[] strArrSplit3 = strArrSplit2[1].replace("\"", "").split(";", -1);
            if (conditionsSet.a().booleanValue()) {
                for (String str : strArrSplit3) {
                    GameData.v().gameVariables.a(str);
                }
            }
        }
        for (NPC npc2 : GameLevelData.o().npcs) {
            if (!npc2.i0()) {
                CharacterSheet characterSheet = npc2.sheet;
                if (characterSheet.stats.missingHP > characterSheet.z() / 2) {
                    CharacterSheet characterSheet2 = npc2.sheet;
                    characterSheet2.stats.missingHP = characterSheet2.z() / 2;
                }
            }
        }
        for (MapEffectEntity mapEffectEntity : GameLevelData.o().mapEffects) {
            mapEffectEntity.Q();
            mapEffectEntity.R();
        }
        f3228e = false;
        l.d("Serializer.LoadGame() > success, v." + saveGameData.version);
        GameLevelData.o().k();
    }

    public static void b(ZipOutputStream zipOutputStream, File file, String str) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        String name = file.getName();
        if (str != null && !str.isEmpty()) {
            StringBuilder sbT = a.a.t(str, "/");
            sbT.append(file.getName());
            name = sbT.toString();
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                b(zipOutputStream, file2, name);
            }
            return;
        }
        byte[] bArr = new byte[GL20.GL_STENCIL_BUFFER_BIT];
        FileInputStream fileInputStream = new FileInputStream(file);
        zipOutputStream.putNextEntry(new ZipEntry(name));
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 <= 0) {
                zipOutputStream.closeEntry();
                fileInputStream.close();
                return;
            }
            zipOutputStream.write(bArr, 0, i2);
        }
    }

    public static void c(boolean z2) {
        String str = z2 ? "EK_GPGS.bak" : "EK.bak";
        try {
            if (Gdx.files.external(str).exists()) {
                Gdx.files.external(str).delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(Gdx.files.getExternalStoragePath() + str);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            b(zipOutputStream, new File(Gdx.files.getLocalStoragePath() + "data/saves"), "");
            zipOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean d() {
        return Gdx.files.external("EK.bak").exists() || Gdx.files.external("download/EK.bak").exists() || Gdx.files.external("Download/EK.bak").exists() || Gdx.files.external("downloads/EK.bak").exists() || Gdx.files.external("Downloads/EK.bak").exists() || Gdx.files.external("sdcard/download/EK.bak").exists() || Gdx.files.external("sdcard/Download/EK.bak").exists();
    }

    public static String e(int i2) {
        return "data/saves/" + i2 + "/cache/";
    }

    public static void f(int i2) {
        l.d("Clearing all data from slot " + i2);
        Gdx.files.local("data/saves/" + i2).deleteDirectory();
    }

    public static void g(int i2, String str) {
        Gdx.files.local(s(i2) + str + ".sav").delete();
        Gdx.files.local(e(i2) + str + ".sav").delete();
    }

    public static String h(String str) {
        return new String(c.a(str), Charset.forName("UTF-8"));
    }

    public static String i(String str) {
        char[] cArrD;
        try {
            cArrD = c.d(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            cArrD = null;
        }
        return new String(cArrD);
    }

    public static boolean j(int i2) {
        return Gdx.files.local(C(i2, 0)).exists() || Gdx.files.local(C(i2, 1)).exists() || Gdx.files.local(C(i2, 2)).exists() || Gdx.files.local(C(i2, 3)).exists() || Gdx.files.local(C(i2, 4)).exists();
    }

    public static String k(int i2, int i3) {
        if (Gdx.files.local(p(i2, i3)).exists()) {
            String string = Gdx.files.local(p(i2, i3)).readString("UTF-8");
            if (string.length() > 5) {
                return string;
            }
        }
        return a.a.o("EMPTY", false, new StringBuilder("<"), ">");
    }

    public static String l() {
        return GameData.v().currentMapName + "\n\r      [GREEN]" + GameString.b("LEVEL", false) + " " + GameData.v().player.sheet.stats.f() + ".[] " + FDUtils.h();
    }

    public static String m() {
        Properties propertiesW = w();
        return propertiesW.getProperty("width") + " x " + propertiesW.getProperty("height");
    }

    public static int n(String str) {
        if (str.trim().equals("")) {
            return 699;
        }
        return Integer.parseInt(str.split("\\.", -1)[2]);
    }

    public static BasicGameData o(int i2) {
        BasicGameData basicGameData = new BasicGameData();
        try {
            Json json = f3227d;
            json.setIgnoreUnknownFields(true);
            Integer num = f3224a[i2];
            if (num != null && num.intValue() != -1) {
                SaveGameData saveGameData = (SaveGameData) json.fromJson(SaveGameData.class, h(Gdx.files.local(C(i2, f3224a[i2].intValue())).readString()));
                if (!q(saveGameData.version)) {
                    basicGameData.slotnumber = i2;
                    basicGameData.corrupted = true;
                    return basicGameData;
                }
                GameData.K(saveGameData.gamedata);
                basicGameData.slotnumber = i2;
                basicGameData.name = GameData.v().player.getName();
                basicGameData.description = Rules.CharacterClass.a(GameData.v().player.sheet.stats.c()) + " (" + GameData.v().player.sheet.stats.f() + ") - [CYAN]" + GameData.v().r() + "[]";
                basicGameData.gender = GameData.v().player.gender;
                basicGameData.portraitIndex = GameData.v().player.portraitIndex;
                return basicGameData;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            basicGameData.slotnumber = i2;
            basicGameData.corrupted = true;
            return basicGameData;
        }
    }

    public static String p(int i2, int i3) {
        return C(i2, i3) + ".nfo";
    }

    public static boolean q(String str) {
        int iN = n("1.3.1217");
        int iN2 = n(str);
        if (iN2 > 1100 && iN2 < 1200) {
            return true;
        }
        if (iN2 < 820) {
            return false;
        }
        return iN2 <= iN || iN2 / 10 <= iN / 10;
    }

    public static Integer r(int i2) {
        Integer[] numArr = f3224a;
        if (numArr == null) {
            return 0;
        }
        return numArr[i2];
    }

    public static String s(int i2) {
        return "data/saves/" + i2 + "/levels/";
    }

    public static void t(String str) {
        int i2 = GameData.v().slot;
        if (i2 != -1) {
            if (Gdx.files.local(s(i2) + str + "_tiles.txt").exists()) {
                try {
                    b.P().f2419d = (byte[][]) f3227d.fromJson(byte[][].class, Gdx.files.local(s(i2) + str + "_tiles.txt").readString());
                } catch (Exception unused) {
                }
                GameConsole.d();
            }
        }
    }

    public static void u(String str) {
        String strH;
        Json json = f3227d;
        json.setIgnoreUnknownFields(true);
        int i2 = GameData.v().slot;
        if (i2 != -1) {
            if (Gdx.files.local(e(i2) + str + ".sav").exists()) {
                strH = h(Gdx.files.local(e(i2) + str + ".sav").readString());
            } else {
                strH = "";
            }
            GameLevelData.E((GameLevelData) json.fromJson(GameLevelData.class, strH));
            GameConsole.d();
            l.d("Serializer.loadLevel(" + i2 + "," + str + ")");
            GameLevelData.o().k();
        }
    }

    public static void v(String str) {
        if (f3226c) {
            if (f3225b == null) {
                f3225b = Gdx.files.local("monsterdump.txt");
            }
            try {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                f3225b.writeString(simpleDateFormat.format(date) + " - " + str + "\r\n", true);
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static Properties w() {
        File file = new File(System.getProperty("user.dir") + "/init.ini");
        Properties properties = new Properties();
        if (file.exists()) {
            try {
                properties.load(new StringReader(new String(Files.readAllBytes(Paths.get(file.toString(), new String[0])))));
            } catch (IOException e2) {
                l.d("ERROR while reading config file: " + e2.getLocalizedMessage());
            }
            if (properties.containsKey("fullscreen") || properties.containsKey("width") || properties.containsKey("height")) {
                return properties;
            }
        }
        properties.setProperty("width", "" + Gdx.graphics.getWidth());
        properties.setProperty("height", "" + Gdx.graphics.getHeight());
        if (Gdx.graphics.isFullscreen()) {
            properties.setProperty("fullscreen", "1");
        } else {
            properties.setProperty("fullscreen", "0");
        }
        return properties;
    }

    public static void x(boolean z2) {
        Gdx.files.local("data/saves.bak").deleteDirectory();
        Gdx.files.local("data/saves/").moveTo(Gdx.files.local("data/saves.bak"));
        StringBuilder sb = new StringBuilder();
        String str = "";
        if (!Gdx.files.external("EK.bak").exists()) {
            if (Gdx.files.external("sdcard/EK.bak").exists()) {
                str = "sdcard/";
            } else if (Gdx.files.external("download/EK.bak").exists()) {
                str = "download/";
            } else if (Gdx.files.external("Download/EK.bak").exists()) {
                str = "Download/";
            } else if (Gdx.files.external("downloads/EK.bak").exists()) {
                str = "downloads/";
            } else if (Gdx.files.external("Downloads/EK.bak").exists()) {
                str = "Downloads/";
            } else if (Gdx.files.external("sdcard/download/EK.bak").exists()) {
                str = "sdcard/download/";
            } else if (Gdx.files.external("sdcard/Download/EK.bak").exists()) {
                str = "sdcard/Download/";
            }
        }
        String strM = a.a.m(str, "EK.bak", sb);
        if (z2) {
            strM = "EK_GPGS.bak";
        }
        try {
            F(new File(Gdx.files.getExternalStoragePath(), strM), new File(Gdx.files.getLocalStoragePath() + "data"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String y(int i2) {
        return "data/saves/" + i2 + "/";
    }

    public static void z(int i2, int i3) {
        l.d("**** Serializer.SaveGame(" + i2 + "," + i3 + ")");
        if (GameData.v() == null) {
            l.d("Save cancelled: null gamedata");
            return;
        }
        GameData.v().getClass();
        if (!GameData.I()) {
            l.d("Save cancelled: game not running");
            return;
        }
        if (GameData.v().player.sheet.o() == 0) {
            l.d("Save cancelled: player is dead");
            return;
        }
        if (i2 != -1) {
            if (i3 == 0) {
                GameData.v().NewArea = null;
                GameData.v().player.tempTransition = null;
            }
            GameData.v().player.conversations = null;
            SaveGameData saveGameData = new SaveGameData();
            saveGameData.gamedata = GameData.v();
            saveGameData.leveldata = GameLevelData.o();
            saveGameData.queue = MessageRouter.b();
            saveGameData.version = "1.3.1217";
            Player player = saveGameData.gamedata.player;
            player.activables = null;
            player.numActivables = 0;
            Json json = f3227d;
            json.setIgnoreUnknownFields(true);
            String strPrettyPrint = json.prettyPrint(saveGameData);
            if (strPrettyPrint.length() > 50) {
                if (i3 == 0) {
                    Gdx.files.local(C(i2, 0)).writeString(i(strPrettyPrint), false, "UTF-8");
                } else {
                    Gdx.files.local(C(i2, 0)).writeString(i(strPrettyPrint), false, "UTF-8");
                    Gdx.files.local(C(i2, i3)).writeString(i(strPrettyPrint), false, "UTF-8");
                }
                Gdx.files.local(p(i2, i3)).writeString(l(), false, "UTF-8");
            } else {
                l.d("WARNING: failed to save game, corrupted data");
            }
            D();
        }
    }
}

package net.fdgames.ek.android.lan;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.Lootable;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.WorldContainer;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.android.MainActivity;

/**
 * The MP mod's non-multiplayer gameplay features 6-11, ported (deobf/MP_FEATURES_SPEC.md).
 * No cheats: the mod's ModMenuDialog toggles are deliberately absent. Every entry point is
 * exception-safe, like EkMp.
 */
public final class EkFeat {
    private EkFeat() {}

    // ================================ 7. equipment upgrades =====================================
    // Level 0..10 per item id, kept in the save as game variable item_upg_<id>.
    static final int UPG_MAX = 10;

    public static int upgLevel(int itemId) {
        try {
            if (itemId <= 0) {
                return 0;
            }
            GameData gd = GameData.O();
            if (gd == null || gd.gameVariables == null) {
                return 0;
            }
            int v = gd.gameVariables.b("item_upg_" + itemId);
            return v < 0 ? 0 : (v > UPG_MAX ? UPG_MAX : v);
        } catch (Throwable e) {
            return 0;
        }
    }

    private static Item item(int id) {
        try {
            return id > 0 ? Rules.c(id) : null;
        } catch (Throwable e) {
            return null;
        }
    }

    /** Theirs Rules.l(I) (ours Rules.e(I), minus its missing null check). */
    static boolean equippable(int id) {
        Item it = item(id);
        if (it == null || it.type == null) {
            return false;
        }
        Item.ItemType t = it.type;
        return t == Item.ItemType.i || t == Item.ItemType.f || t == Item.ItemType.g || t == Item.ItemType.e
                || t == Item.ItemType.h || t == Item.ItemType.m || t == Item.ItemType.n || t == Item.ItemType.c
                || t == Item.ItemType.d || t == Item.ItemType.p || t == Item.ItemType.o;
    }

    static int subtype(int id) {
        Item it = item(id);
        if (it == null) {
            return 0;
        }
        Item.ItemType t = it.type;
        if (t == Item.ItemType.c) {
            WeaponStats w = it.weaponStats;
            if (w == null) {
                return 2;
            }
            if (w.staff || w.wand) {
                return 4;
            }
            if (w.ranged) {
                return 5;
            }
            return w.twohanded ? 3 : 2;
        }
        if (t == Item.ItemType.d) {
            return 0;
        }
        if (t == Item.ItemType.e || t == Item.ItemType.f || t == Item.ItemType.g || t == Item.ItemType.h
                || t == Item.ItemType.i) {
            return 1;
        }
        if (t == Item.ItemType.m || t == Item.ItemType.n) {
            return 6;
        }
        if (t == Item.ItemType.o || t == Item.ItemType.p) {
            return 7;
        }
        return 0;
    }

    static int goldCost(int id, int n) {
        Item it = item(id);
        if (it == null) {
            return 0;
        }
        int base = it.value < 750 ? 750 : it.value;
        int f = n * n * 3 + 15;
        if (n >= 6) {
            f += n * 2;
        }
        if (n >= 9) {
            f += 10;
        }
        int s = subtype(id);
        if (s == 0 || s == 3) {
            f += n;
        } else if (s == 1 || s == 7) {
            f += n / 2;
        } else if (s == 4 || s == 5) {
            f += 1;
        }
        return base * f;
    }

    static int primaryId(int id, int n) {
        int s = subtype(id);
        switch (s) {
            case 2:
                return n <= 3 ? 2001 : (n <= 6 ? 2013 : 2004);
            case 3:
                return n <= 4 ? 2001 : (n <= 8 ? 2013 : 2004);
            case 4:
                return n <= 3 ? 2003 : (n <= 6 ? 2014 : (n <= 8 ? 2015 : 2004));
            case 5:
                return n <= 3 ? 2003 : (n <= 6 ? 2014 : (n <= 8 ? 2004 : 2015));
            case 6:
                return n <= 4 ? 2003 : (n <= 7 ? 2015 : 2004);
            case 7:
                return n <= 4 ? 2002 : (n <= 7 ? 2015 : 2004);
            default: // 0, 1
                return n <= 3 ? 2002 : (n <= 6 ? 2012 : 2004);
        }
    }

    static int primaryQty(int n) {
        if (n <= 3) {
            return n * 2 + 2;
        }
        if (n <= 6) {
            return n * 3 - 1;
        }
        if (n <= 8) {
            return n * 4 - 7;
        }
        return n * 5 - 16;
    }

    static int secondaryId(int id, int n) {
        int s = subtype(id);
        switch (s) {
            case 2:
            case 3:
                return n <= 4 ? 2002 : (n <= 8 ? 2012 : 2015);
            case 4:
                return n <= 4 ? 2004 : (n <= 8 ? 2015 : 2014);
            case 5:
                return n <= 4 ? 2001 : (n <= 8 ? 2013 : 2015);
            case 6:
                return n <= 4 ? 2002 : (n <= 8 ? 2012 : 2015);
            case 7:
                return n <= 4 ? 2003 : (n <= 8 ? 2014 : 2015);
            default: // 0, 1
                return n <= 4 ? 2001 : (n <= 8 ? 2013 : 2015);
        }
    }

    static int secondaryQty(int n) {
        if (n <= 3) {
            return n + 1;
        }
        if (n <= 8) {
            return n * 2 - 2;
        }
        return n * 3 - 10;
    }

    private static String itemName(int id, String fallback) {
        Item it = item(id);
        return it != null && it.name != null ? it.name : fallback;
    }

    // ---- stat effects (hooks installed by tools/patch_mp_features.py) --------------------------

    /** CharacterSheet.c(Z) (their B(Z)): min damage +4L/10, max damage +7L/10 of the main hand. */
    public static int upgMinMax(int mainLevel, boolean max) {
        return max ? mainLevel * 7 / 10 : mainLevel * 4 / 10;
    }

    /** Secondary (elemental) damage of the main hand: +L/3. */
    public static int upgElemental(int base, int mainhandId) {
        return base + upgLevel(mainhandId) / 3;
    }

    // ---- CharacterWindow: UPGRADE button on an equipped item -----------------------------------

    public static void upgButton(TextButton b, int itemId) {
        try {
            if (b == null) {
                return;
            }
            b.setText("UPGRADE");
            b.setVisible(true);
            b.setDisabled(itemId <= 0 || !equippable(itemId) || upgLevel(itemId) >= UPG_MAX);
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Their cdxTryUpgradeEquipped. true = upgraded (the window refreshes itself). */
    public static boolean tryUpgrade(int itemId, Character who) {
        try {
            GameData gd = GameData.O();
            if (gd == null || itemId <= 0) {
                return false;
            }
            int lvl = upgLevel(itemId);
            if (lvl >= UPG_MAX) {
                log("[YELLOW]This item is already at the maximum level.[]");
                return false;
            }
            int n = lvl + 1;
            Item it = item(itemId);
            if (it == null) {
                log("[YELLOW]Could not identify the equipped item.[]");
                return false;
            }
            if (!equippable(itemId)) {
                log("[YELLOW]Only equippable items can be upgraded.[]");
                return false;
            }
            int gold = goldCost(itemId, n);
            int m1 = primaryId(itemId, n);
            int q1 = primaryQty(n);
            int m2 = secondaryId(itemId, n);
            int q2 = secondaryQty(n);
            String n1 = itemName(m1, "material 1");
            String n2 = itemName(m2, "material 2");
            Player p = gd.player;
            Items bag = gd.backpack;
            if (p == null || bag == null || p.g() < gold || bag.g(m1) < q1 || bag.g(m2) < q2) {
                log("[YELLOW]Upgrade needs " + gold + " gold, " + q1 + "x " + n1 + " and " + q2 + "x " + n2 + ".[]");
                return false;
            }
            p.s(-gold);
            for (int k = 0; k < q1; k++) {
                bag.i(m1);
            }
            for (int k = 0; k < q2; k++) {
                bag.i(m2);
            }
            gd.gameVariables.b("item_upg_" + itemId, n);
            if (who != null && who.sheet != null) {
                who.sheet.a0();
            }
            GameAssets.i("item");
            log("[YELLOW]Upgrade complete: " + it.name + " +" + n + " (" + q1 + "x " + n1 + ", " + q2 + "x " + n2 + ").[]");
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    private static void log(String s) {
        try {
            GameData gd = GameData.O();
            if (gd != null && gd.log != null) {
                gd.log.a(s);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Item preview (their cdxUpgradePreview): "Upgrade: +L/10" and the next level's price. */
    public static void upgPreview(StringBuilder sb, int itemId) {
        try {
            if (sb == null || itemId <= 0 || !equippable(itemId)) {
                return;
            }
            int lvl = upgLevel(itemId);
            sb.append("\nUpgrade: +").append(lvl).append("/10");
            if (lvl >= UPG_MAX) {
                sb.append("\nMaximum level reached");
                return;
            }
            int n = lvl + 1;
            sb.append("\nNext: ").append(goldCost(itemId, n)).append(" gold\n")
                    .append(primaryQty(n)).append("x ").append(itemName(primaryId(itemId, n), "Material 1")).append('\n')
                    .append(secondaryQty(n)).append("x ").append(itemName(secondaryId(itemId, n), "Material 2"));
        } catch (Throwable e) {
            // ignore
        }
    }

    /** "+L" badge on an inventory slot (their InventorySlotImage.draw). */
    public static void drawBadge(Actor slot, Batch batch, BitmapFont font, int itemId, float s) {
        try {
            if (slot == null || batch == null || font == null || itemId <= 0 || !equippable(itemId)) {
                return;
            }
            int lvl = upgLevel(itemId);
            if (lvl <= 0) {
                return;
            }
            BitmapFont.BitmapFontData d = font.getData();
            float sx = d.scaleX;
            float sy = d.scaleY;
            float k = ExiledKingdoms.h ? 0.5f : 0.6f;
            d.scaleX = k;
            d.scaleY = k;
            font.setColor(Color.YELLOW);
            font.draw(batch, "+" + lvl, slot.getX() + slot.getWidth() - 20f * s, slot.getY() + 18f * s);
            font.setColor(Color.WHITE);
            d.scaleX = sx;
            d.scaleY = sy;
        } catch (Throwable e) {
            // ignore
        }
    }

    // ================================ 8. storage shortcuts =======================================

    /** Opens a named world container in the character window, like a chest/vault tap. */
    static final class Open extends InputListener {
        private final e.a.d.e.h win;
        private final String id;

        Open(e.a.d.e.h win, String id) {
            this.win = win;
            this.id = id;
        }

        @Override
        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
            try {
                GameData gd = GameData.O();
                String target = id;
                if (target == null) { // the vault button: first owned vault
                    target = gd.hasVault ? "vault" : gd.hasVault2 ? "vault2" : gd.hasVault3 ? "vault3" : gd.hasVault4 ? "vault4" : null;
                }
                if (target != null) {
                    WorldContainer c = gd.d(target);
                    win.a(1, c);
                    GameAssets.i("item");
                }
            } catch (Throwable e) {
                // ignore
            }
            return false;
        }
    }

    /** Vault button in the player's own inventory when a vault is owned. */
    public static void addVaultButton(e.a.d.e.h win, Table row, float p0, float s0) {
        try {
            GameData gd = GameData.O();
            if (gd == null || !(gd.hasVault || gd.hasVault2 || gd.hasVault3 || gd.hasVault4)) {
                return;
            }
            ImageButton b = new ImageButton(GameAssets.a(Assets.b("vault")));
            b.setColor(Color.ORANGE);
            b.addListener(new Open(win, null));
            row.add(b).width(p0 * 60f).height(p0 * 60f).pad(s0);
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Bags 1-5 when a bag of holding is open (vanilla has one bag). */
    public static void addBagTabs(e.a.d.e.h win, Table t, Lootable open, float p0, float s0) {
        try {
            if (open == null) {
                return;
            }
            String name = open.getName();
            if (name == null || !name.contains("bag of holding")) {
                return;
            }
            t.row().expandX().center().pad(s0);
            for (int k = 1; k <= 5; k++) {
                String id = k == 1 ? "bag_of_holding" : "bag_of_holding" + k;
                TextButton b = new e.a.d.u(String.valueOf(k), Assets.e(), "menuButton");
                if (name.equals(k == 1 ? "bag of holding" : "bag of holding" + k)) {
                    b.setColor(Color.ORANGE);
                }
                b.addListener(new Open(win, id));
                t.add(b).width(p0 * 42f).height(p0 * 40f).padRight(s0);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ============ shared world phase A: Options -> MULTIPLAYER (opens the lobby) ==================
    public static TextButton mpOptionsButton() {
        TextButton b = new e.a.d.u("MULTIPLAYER", Assets.e(), "menuButton");
        try {
            b.addListener(EkMp.lobbyListener());
        } catch (Throwable e) {
            // ignore
        }
        return b;
    }

    // ================================ 9. recover ================================================

    /** After Recover / rest: other players see the restored HP at once. */
    /**
     * The vanilla bag-of-holding button (e/a/d/e/k). Never blocked any more: vaults and bags travel in
     * your character block (SHARED_WORLD_SPEC §9), so in someone else's world they are still yours.
     */
    public static boolean bagBlocked() {
        return false;
    }

    public static void afterRecover() {
        try {
            if (LanGameBridge.isSessionRunning()) {
                LanGameBridge.forcePublishLocalState();
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ================================ 11. SAF backup =============================================
    static final int REQ_EXPORT = 0xBACA;
    static final int REQ_IMPORT = 0xBACC;

    private static MainActivity activity() {
        Object a = Gdx.app;
        return a instanceof MainActivity ? (MainActivity) a : null;
    }

    /** true = picker launched (API 19+); false = use the original Download/EK.bak path. */
    public static boolean safExport() {
        try {
            MainActivity a = activity();
            if (a == null || Build.VERSION.SDK_INT < 19) {
                return false;
            }
            Intent i = new Intent("android.intent.action.CREATE_DOCUMENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("application/octet-stream");
            i.putExtra("android.intent.extra.TITLE", "EK.bak");
            a.startActivityForResult(i, REQ_EXPORT);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean safImport() {
        try {
            MainActivity a = activity();
            if (a == null || Build.VERSION.SDK_INT < 19) {
                return false;
            }
            Intent i = new Intent("android.intent.action.OPEN_DOCUMENT");
            i.addCategory("android.intent.category.OPENABLE");
            i.setType("*/*");
            a.startActivityForResult(i, REQ_IMPORT);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    private static void console(final String msg) {
        try {
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    try {
                        GameConsole.a(msg);
                    } catch (Throwable e) {
                        // ignore
                    }
                }
            });
        } catch (Throwable e) {
            // ignore
        }
    }

    private static void deleteTree(File f) {
        File[] kids = f.listFiles();
        if (kids != null) {
            for (File k : kids) {
                deleteTree(k);
            }
        }
        f.delete();
    }

    /** MainActivity.onActivityResult hook. true = one of ours (handled). */
    public static boolean onResult(MainActivity a, int req, int res, Intent data) {
        if (req != REQ_EXPORT && req != REQ_IMPORT) {
            return false;
        }
        if (res != -1 || data == null) {
            return true; // cancelled
        }
        try {
            Uri uri = data.getData();
            if (uri == null) {
                return true;
            }
            final String local = a.getFilesDir().getAbsolutePath() + "/";
            if (req == REQ_EXPORT) {
                OutputStream os = a.getContentResolver().openOutputStream(uri);
                ZipOutputStream z = new ZipOutputStream(os);
                Serializer.a(z, new File(local + "data/saves"), "");
                z.close();
                os.close();
                String where = uri.getLastPathSegment();
                String msg = GameString.a("BACKUP_DONE");
                console(msg != null && msg.contains("##BACKUP##") ? msg.replace("##BACKUP##", where == null ? "EK.bak" : where) : "Backup saved.");
                return true;
            }
            // import: copy to a private file first, then restore on the game thread like ours does
            final File tmp = new File(a.getCacheDir(), "ek_import.bak");
            InputStream in = a.getContentResolver().openInputStream(uri);
            OutputStream out = new FileOutputStream(tmp);
            byte[] buf = new byte[8192];
            int r;
            while ((r = in.read(buf)) > 0) {
                out.write(buf, 0, r);
            }
            out.close();
            in.close();
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    try {
                        File saves = new File(local + "data/saves");
                        File old = new File(local + "data/saves.bak");
                        deleteTree(old);
                        saves.renameTo(old);
                        Serializer.a(tmp, new File(local + "data"));
                        tmp.delete();
                        e.a.b.e.j();
                        String msg = GameString.a("BACKUP_RESTORED");
                        GameConsole.a(msg != null ? msg : "Backup restored.");
                        Serializer.i();
                    } catch (Throwable e) {
                        GameConsole.a("Import failed: " + e);
                    }
                }
            });
        } catch (Throwable e) {
            console((req == REQ_EXPORT ? "Export" : "Import") + " failed: " + e);
        }
        return true;
    }
}

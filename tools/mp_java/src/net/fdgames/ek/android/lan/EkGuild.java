package net.fdgames.ek.android.lan;

import java.lang.reflect.Field;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;

/**
 * Guild ranks: passive perks + Guild Master bookkeeping (deobf/GUILD_EXPANSION_SPEC.md §6-7).
 * Ranks/contracts/story are data (tools/guild_expansion.py); this only turns the ekg_* variables into
 * modest stat bonuses on the player's inventory recompute, and keeps the Guild Master rule consistent.
 * Every entry point is exception-safe.
 */
public final class EkGuild {
    private EkGuild() {}

    /** Guild keys, in ekg_gm numbering order (1..4). */
    static final String[] G = {"warriors", "seventh", "wizards", "three"};
    static final String[] MEMBER_VAR = {"guild_warriors", "guild_seventh", "guild_wizards", "guild_three"};

    private static Field fDef, fHp, fMana, fDev, fDet, fTraits;
    private static boolean fieldsTried;
    private static String lastSig = "";

    private static int var(GameVariables v, String n) {
        try {
            return v.b(n);
        } catch (Throwable e) {
            return 0;
        }
    }

    /** Perk tier of guild i for the current game: 0 = never a member, else rank+1 (6 = its Guild Master). */
    static int tier(GameVariables v, int i) {
        if (var(v, "ekg_mem_" + G[i]) <= 0) {
            return 0;
        }
        int r = var(v, "ekg_rank_" + G[i]);
        if (r < 0) {
            r = 0;
        }
        if (r > 5) {
            r = 5;
        }
        return r + 1;
    }

    private static void fields() {
        if (fieldsTried) {
            return;
        }
        fieldsTried = true;
        try {
            Class<?> c = CharacterInventory.class;
            fDef = c.getDeclaredField("DefenseBonus");
            fHp = c.getDeclaredField("HPBonus");
            fMana = c.getDeclaredField("ManaBonus");
            fDev = c.getDeclaredField("devicesBonus");
            fDet = c.getDeclaredField("detectionBonus");
            fTraits = c.getDeclaredField("traits");
            for (Field f : new Field[]{fDef, fHp, fMana, fDev, fDet, fTraits}) {
                f.setAccessible(true);
            }
        } catch (Throwable e) {
            fDef = null;
        }
    }

    private static void add(Field f, Object o, int n) throws Exception {
        if (f != null && n != 0) {
            f.setInt(o, f.getInt(o) + n);
        }
    }

    /** Tail of CharacterInventory.u(): add the guild perks to the player's own inventory only. */
    public static void apply(Object inv) {
        try {
            GameData gd = GameData.O();
            if (gd == null || gd.player == null || gd.player.sheet == null || gd.player.sheet.inventory != inv
                    || gd.gameVariables == null) {
                return;
            }
            fields();
            if (fDef == null) {
                return;
            }
            GameVariables v = gd.gameVariables;
            int hp = 0, mana = 0, def2 = 0, dev = 0, det2 = 0;
            int[] tr = new int[6];               // STR END AGI INT AWA PER
            int t = tier(v, 0);                  // Warriors' Guild
            hp += 3 * t;
            def2 += t;
            if (t >= 4) tr[0]++;
            if (t >= 6) tr[1]++;
            t = tier(v, 1);                      // Seventh House
            hp += 2 * t;
            dev += 2 * t;                        // item devices points count x5, so this stays small
            det2 += 2 * t;
            if (t >= 4) tr[2]++;
            if (t >= 6) tr[4]++;
            t = tier(v, 2);                      // Wizard's Guild
            mana += 4 * t;
            if (t >= 4) tr[3]++;
            if (t >= 6) tr[4]++;
            t = tier(v, 3);                      // Church of the Three
            hp += 2 * t;
            mana += 2 * t;
            if (t >= 4) tr[5]++;
            if (t >= 6) tr[1]++;
            add(fHp, inv, hp);
            add(fMana, inv, mana);
            add(fDef, inv, def2 / 2);
            add(fDev, inv, dev);
            add(fDet, inv, det2 / 2);
            int[] traits = (int[]) fTraits.get(inv);
            if (traits != null) {
                for (int i = 0; i < 6 && i < traits.length; i++) {
                    traits[i] += tr[i];
                }
            }
        } catch (Throwable e) {
            // ignore: perks are a bonus, never a crash
        }
    }

    /**
     * Every 3 s (EkAuto.tick): keep the Guild Master rule (one seat; the other oaths stay closed) and the
     * "former member" journal state, and re-run the player's inventory recompute when a guild rank changed.
     */
    public static void tick() {
        try {
            GameData gd = GameData.O();
            if (gd == null || gd.player == null || gd.player.sheet == null || gd.gameVariables == null) {
                return;
            }
            GameVariables v = gd.gameVariables;
            int gm = var(v, "ekg_gm");
            StringBuilder sig = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (gm >= 1 && gm <= 4 && gm - 1 != i) {
                    if (var(v, MEMBER_VAR[i]) != 0) {
                        v.b(MEMBER_VAR[i], 0);
                    }
                    if (var(v, "ekg_mem_" + G[i]) > 0 && var(v, "ekg_gq_" + G[i]) < 90) {
                        int r = var(v, "ekg_rank_" + G[i]);
                        v.b("ekg_gq_" + G[i], 90 + Math.max(0, Math.min(4, r)));
                    }
                }
                sig.append(tier(v, i)).append(',');
            }
            String s = sig.toString();
            if (!s.equals(lastSig)) {
                lastSig = s;
                CharacterInventory inv = gd.player.sheet.inventory;
                if (inv != null) {
                    inv.u();
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }
}

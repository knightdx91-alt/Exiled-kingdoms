package net.fdgames.ek.android.lan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Iterator;
import java.util.List;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.DynamicEvent;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.android.MainActivity;

/**
 * Glue between our 4.2.2 game classes and the ported LAN engine (deobf/MULTIPLAYER_PORT_SPEC.md §3).
 * Compiled against tools/mp_java/stubs (our obfuscated names), then spliced into the dex by
 * tools/patch_multiplayer.py. Every entry point is exception-safe: a multiplayer fault must never
 * take the single-player game down with it.
 */
public final class EkMp {
    private EkMp() {}

    // ---- AI: monsters target the nearest hostile actor (the player unless in a session) ----------

    public static Character hostileChar(MapActor a) {
        MapActor t = null;
        try {
            t = LanGameBridge.resolveHostileTargetActor(a);
        } catch (Throwable e) {
            t = null;
        }
        if (t instanceof Character) {
            return (Character) t;
        }
        return GameLevel.h();
    }

    public static int hostileId(MapActor a) {
        Character c = hostileChar(a);
        return c == null ? 1 : c.m();
    }

    public static MapActor companionAnchor(NPC npc) {
        MapActor t = null;
        try {
            t = LanGameBridge.resolveCompanionAnchor(npc);
        } catch (Throwable e) {
            t = null;
        }
        if (t != null) {
            return t;
        }
        GameData gd = GameData.O();
        return gd == null ? null : gd.player;
    }

    // ---- monsters scale with party size: +2 levels per extra player in a session ---------------
    // APPROX vs the MP mod: it also wrote the raised level back into the spawn point, so every
    // respawn stacked another +2x(n-1). Here the base level is never modified.
    public static int scaledSpawnLevel(int lvl, NPC npc) {
        try {
            LanSessionManager m = LanSessionManager.getInstanceIfReady();
            if (m != null && m.isInSession()) {
                int n = m.getPlayerCount();
                if (n > 1) {
                    int base = lvl;
                    if (base <= 0 && npc != null && npc.sheet != null) {
                        base = npc.sheet.z();
                    }
                    if (base > 0) {
                        return base + 2 * (n - 1);
                    }
                }
            }
        } catch (Throwable e) {
            // fall through
        }
        return lvl;
    }

    // ---- peers are never saved: the engine recreates them every tick -------------------------

    public static void stripPeers(GameLevelData d) {
        try {
            if (d == null || d.npcs == null) {
                return;
            }
            Iterator it = d.npcs.iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof NPC && ((NPC) o).lanPeerVisual) {
                    it.remove();
                }
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    public static void stripPeersCurrent() {
        try {
            stripPeers(GameLevelData.s());
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- peer sprite: composite body/offhand/head/mainhand like the player's --------------------
    // APPROX vs the MP mod (NPC.v0LanPeer): it cleared the whole sprite when ANY layer was missing,
    // which would make a peer invisible whenever their weapon sprite is not in our older asset set.
    // Here only a missing body or head clears it; missing weapon layers are simply skipped.
    private static String itemSprite(int id) {
        if (id <= 0) {
            return "";
        }
        Item it = Rules.c(id);
        if (it == null || it.sprite == null) {
            return "";
        }
        return it.sprite;
    }

    private static boolean addLayer(NPC npc, String name) {
        int idx = GameAssets.b(name);
        if (idx == -1) {
            return false;
        }
        npc.spriteIndex.add(Integer.valueOf(idx));
        return true;
    }

    public static void buildPeerSprite(NPC npc) {
        try {
            if (npc.spriteIndex == null) {
                npc.spriteIndex = new com.badlogic.gdx.utils.a();
            }
            npc.spriteIndex.clear();
            String g = npc.gender == Character.Gender.b ? "male" : "female";
            CharacterSheet sh = npc.sheet;
            CharacterInventory inv = sh.inventory;
            String body = itemSprite(inv.slot_body);
            if (body.length() == 0) {
                body = "clothes";
            }
            String head = itemSprite(inv.slot_head);
            head = head.length() == 0 ? "head" : "head_" + head;
            String main = sh.A();
            String off = sh.F();
            boolean acting = npc.J() == MapActor.ActorState.f;
            boolean ok = addLayer(npc, "composite/" + g + "_" + body);
            if (!acting && off != null && off.length() > 0) {
                addLayer(npc, "composite/" + g + "_" + off);
            }
            ok &= addLayer(npc, "composite/" + g + "_" + head);
            if (!acting && main != null && main.length() > 0) {
                addLayer(npc, "composite/" + g + "_" + main);
            }
            if (!ok) {
                npc.spriteIndex.clear();
            }
        } catch (Throwable e) {
            // leave whatever was built
        }
    }

    // ---- UI -------------------------------------------------------------------------------

    public static void openLobby() {
        try {
            Object r = ExiledKingdoms.e();
            if (r instanceof MainActivity) {
                ((MainActivity) r).ekOpenLobby();
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    public static ClickListener lobbyListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openLobby();
            }
        };
    }

    private static TextButton chatBtn;
    private static Stage chatStage;

    /** Called every HUD frame (GameHUD.j, before stage.draw): CHAT button while in a session. */
    public static void hudChat(Stage stage) {
        try {
            if (stage == null) {
                return;
            }
            boolean on = LanGameBridge.isSessionRunning();
            if (chatBtn == null || chatStage != stage) {
                if (!on) {
                    return;
                }
                chatBtn = new TextButton("CHAT", Assets.e(), "menuSmallButton");
                chatBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        LanGameBridge.openChat();
                    }
                });
                stage.addActor(chatBtn);
                chatStage = stage;
            }
            chatBtn.setVisible(on);
            if (!on) {
                return;
            }
            String t = "CHAT";
            LanSessionManager m = LanSessionManager.getInstanceIfReady();
            if (m != null && m.getUnreadChatCount() > 0) {
                t = "CHAT!";
            }
            chatBtn.setText(t);
            chatBtn.setPosition(stage.getWidth() - chatBtn.getWidth() - 10f, stage.getHeight() * 0.62f);
            chatBtn.toFront();
        } catch (Throwable e) {
            // ignore
        }
    }

    /** Player-name labels over peers (GameLevelRenderer, batch already begun). */
    public static void drawPeerNames(Batch batch, GlyphLayout layout) {
        try {
            GameData gd = GameData.O();
            if (gd == null || gd.CurrentLevel == null || batch == null) {
                return;
            }
            List l = LanGameBridge.getPeersForLevel(gd.CurrentLevel);
            if (l == null || l.isEmpty()) {
                return;
            }
            BitmapFont f = GameAssets.l0;
            if (f == null) {
                return;
            }
            GlyphLayout gl = layout != null ? layout : new GlyphLayout();
            for (Object o : l) {
                if (!(o instanceof LanSessionManager.PlayerState)) {
                    continue;
                }
                LanSessionManager.PlayerState ps = (LanSessionManager.PlayerState) o;
                String n = ps.playerName;
                if (n == null) {
                    continue;
                }
                n = n.trim();
                if (n.length() == 0) {
                    continue;
                }
                float fx = ps.x + ps.y + 6;
                float fy = ps.y + 52;
                gl.setText(f, n);
                f.draw(batch, n, fx - gl.width / 2f, fy);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- PvP arena (map H10_pvp_arena, content: pvp_arena_master/exit/chest) ---------------------
    // pvp_arena_won: 2 = fight on, 1 = won, 0 = lost; pvp_fight_active: 1 while a fight runs.
    static final String ARENA = "H10_pvp_arena";

    private static boolean inArena() {
        GameData gd = GameData.O();
        return gd != null && ARENA.equals(gd.CurrentLevel);
    }

    /** Player death (our Player.E, their X): in the arena you are eliminated, not killed —
     *  full HP, idle, the fight is lost and everyone is told. true = death handled. */
    public static boolean arenaPlayerDeath(Player p) {
        try {
            if (p == null || !inArena()) {
                return false;
            }
            GameData gd = GameData.O();
            if (gd.gameVariables != null) {
                gd.gameVariables.b("pvp_arena_won", 0);
                gd.gameVariables.b("pvp_fight_active", 0);
            }
            try {
                LanGameBridge.forcePublishLocalState();
                LanSessionManager m = LanSessionManager.getInstanceIfReady();
                if (m != null) {
                    String n = p.getName();
                    // APPROX: the mod prefixed a crossed-swords emoji the game font cannot draw.
                    m.sendChat("[PVP] " + (n != null ? n : "Player") + " has been eliminated!");
                }
            } catch (Throwable e) {
                // network trouble must not turn an arena knock-out into a game over
            }
            p.sheet.stats.missingHP = 0;
            p.a(MapActor.ActorState.b);
            try {
                LanGameBridge.forcePublishLocalState();
            } catch (Throwable e) {
                // ignore
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /** A peer's puppet dies here (our NPC.E after MapActor.E, their NPC.X): remove it, and when no
     *  other peer is still standing the fight is won. APPROX: the mod tested GameData.currentMapName,
     *  which the game never sets (always ""), so its branch never ran; we test CurrentLevel like the
     *  rest of the engine. */
    public static void arenaPeerDeath(NPC npc) {
        try {
            if (npc == null || !npc.lanPeerVisual || !inArena()) {
                return;
            }
            npc.destroy = true;
            List all = GameLevel.e();
            if (all == null) {
                return;
            }
            int alive = 0;
            for (Object o : all) {
                if (o == npc || !(o instanceof NPC)) {
                    continue;
                }
                NPC n = (NPC) o;
                if (n.lanPeerVisual && n.J() != MapActor.ActorState.j && !n.destroy) {
                    alive++;
                }
            }
            GameData gd = GameData.O();
            if (alive == 0 && gd.gameVariables != null) {
                gd.gameVariables.b("pvp_arena_won", 1);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    // ---- world map (our e/a/d/r1.draw, their z0/q1.draw): peers in this area as coloured markers
    // with their names, drawn right after the red "you are here" corners -----------------------
    private static final Color[] PEER_COLORS = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};

    public static void drawWorldPeers(Actor map, Batch batch, String area, TextureRegion marker, float size) {
        try {
            if (map == null || batch == null || marker == null) {
                return;
            }
            float[] xy = LanGameBridge.getPeerMarkerPairs(area, size);
            if (xy == null || xy.length == 0) {
                return;
            }
            String[] names = LanGameBridge.getPeerMarkerNames(area);
            // A dot in the middle of the peer's area (the marker texture is a 1x1 white pixel: drawn at
            // the full cell size it hid the whole area). Players sharing an area sit side by side.
            float dot = Math.max(6f, size * 0.4f);
            java.util.HashMap<String, Integer> seen = new java.util.HashMap<String, Integer>();
            for (int i = 0; i + 1 < xy.length; i += 2) {
                int k = i >> 1;
                String key = (int) xy[i] + "," + (int) xy[i + 1];
                Integer n = seen.get(key);
                int slot = n == null ? 0 : n.intValue();
                seen.put(key, slot + 1);
                float x = map.getX() + xy[i] + (size - dot) / 2f + slot * dot * 0.6f;
                float y = map.getY() + xy[i + 1] + (size - dot) / 2f - slot * dot * 0.3f;
                batch.setColor(Color.BLACK);
                batch.draw(marker, x - 2f, y - 2f, dot + 4f, dot + 4f);
                batch.setColor(PEER_COLORS[k % PEER_COLORS.length]);
                batch.draw(marker, x, y, dot, dot);
                if (names != null && k < names.length && names[k] != null) {
                    BitmapFont f = GameAssets.d0;
                    if (f != null) {
                        // APPROX: the mod set the shared font's scale to 0.5 and left it there; we
                        // restore it, and scale with the screen like the game's windows do.
                        BitmapFont.BitmapFontData d = f.getData();
                        float sx = d.scaleX;
                        float sy = d.scaleY;
                        float fs = 0.5f * EkUi.uniformScale();
                        f.setColor(Color.WHITE);
                        d.scaleX = fs;
                        d.scaleY = fs;
                        f.draw(batch, names[k], x, y + dot + 4f + fs * 20f);
                        d.scaleX = sx;
                        d.scaleY = sy;
                    }
                }
            }
        } catch (Throwable e) {
            // ignore
        } finally {
            try {
                if (batch != null) {
                    batch.setColor(Color.RED);
                }
            } catch (Throwable e) {
                // ignore
            }
        }
    }

    // ---- world events (our GameData.f(), their Z(F)): a newly started dynamic event is written to
    // the game log and announced to the other players ------------------------------------------
    public static void worldEvent(DynamicEvent ev) {
        try {
            if (ev == null) {
                return;
            }
            String s = ev.e();
            if (s == null) {
                return;
            }
            GameData gd = GameData.O();
            if (gd != null && gd.log != null) {
                gd.log.a(s);
            }
            LanSessionManager m = LanSessionManager.getInstanceIfReady();
            if (m != null && m.isSessionRunning()) {
                String plain = s.replace("[BLUE]", "").replace("[BLACK]", "").replace("[]", "");
                m.sendChatAsync(">>> [World Event] " + plain);
            }
        } catch (Throwable e) {
            // ignore
        }
    }

    /**
     * LanGameBridge.postGameLog, moved onto the game thread (v51). The chat reader thread used to add
     * the line to GameData.log directly (GameLog.a(String) edits an ArrayList and rebuilds the log text
     * the HUD is drawing at that moment), racing the renderer.
     */
    public static void postGameLog(final String s) {
        try {
            if (com.badlogic.gdx.Gdx.app != null) {
                com.badlogic.gdx.Gdx.app.postRunnable(new Runnable() {
                    public void run() {
                        LanGameBridge.ekPostGameLogNow(s);
                    }
                });
                return;
            }
        } catch (Throwable e) {
            // fall through
        }
        LanGameBridge.ekPostGameLogNow(s);
    }
}

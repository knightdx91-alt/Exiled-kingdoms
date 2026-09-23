package net.fdgames.ek.android.lan;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
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
}

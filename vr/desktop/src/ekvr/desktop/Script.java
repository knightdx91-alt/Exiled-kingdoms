package ekvr.desktop;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 * Sequential scripted input for desktop tests (-Dekvr.script=file). One command per line,
 * each runs after the previous one finished:
 *   still            wait until the screen has not changed for 25 frames (animations done)
 *   wait <frames>    wait a fixed number of frames
 *   click <x> <y>    tap at window pixel (x, y)
 *   down/drag/up <x> <y>
 *   key <gdxKeyCode> press + release
 *   hold <gdxKeyCode> <frames>
 *   pad <axis> <value> <frames>   push a virtual gamepad axis (ekvr.Pad) for N frames
 *   shot <name>      save shotDir/<name>.png
 *   quit
 *   actions <EK action list>   run it through the game's ActionsSet (e.g. Travel#H10,1,14)
 * Lines starting with # are comments.
 */
public class Script implements Runnable {
    final List<String[]> cmds = new ArrayList<>();
    int next, waitLeft = 0, stillFrames = 0, holdKey = -1, padAxis = -1;
    long lastHash;
    final String dir;

    public Script(String file, String dir) throws Exception {
        this.dir = dir;
        for (String l : Files.readAllLines(Paths.get(file))) {
            l = l.trim();
            if (l.isEmpty() || l.startsWith("#")) continue;
            cmds.add(l.split("\\s+"));
        }
    }

    long screenHash() {
        int w = DeskBackend.width, h = DeskBackend.height;
        ByteBuffer b = BufferUtils.createByteBuffer(w * h * 4);
        GL11.glReadPixels(0, 0, w, h, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, b);
        long hsh = 1125899906842597L;
        for (int i = 0; i < w * h * 4; i += 4 * 97) hsh = 31 * hsh + b.get(i) + 7 * b.get(i + 1);
        return hsh;
    }

    public void run() {
        if (waitLeft > 0) {
            if (--waitLeft == 0) {
                if (holdKey >= 0) { DeskInput.key(holdKey, false); holdKey = -1; }
                if (padAxis >= 0) { ekvr.Pad.PAD.axes[padAxis] = 0; padAxis = -1; }
            }
            return;
        }
        if (stillFrames > 0) {
            long hh = screenHash();
            if (hh == lastHash) stillFrames++; else stillFrames = 1;
            lastHash = hh;
            if (stillFrames < 25) return;
            stillFrames = 0;
        }
        if (next >= cmds.size()) return;
        String[] c = cmds.get(next++);
        System.out.println("[script] frame " + DeskBackend.frameId + ": " + String.join(" ", c));
        switch (c[0]) {
            case "still": stillFrames = 1; lastHash = 0; break;
            case "wait": waitLeft = Integer.parseInt(c[1]); break;
            case "click": {
                int x = Integer.parseInt(c[1]), y = Integer.parseInt(c[2]);
                if (DeskBackend.vr) { DeskInput.rawPress(x, y); DeskInput.rawRelease(x, y); }   // straight to the game's screen
                else { DeskInput.move(x, y); DeskInput.press(x, y); DeskInput.release(x, y); }
                waitLeft = 2;
                break;
            }
            case "down": DeskInput.press(Integer.parseInt(c[1]), Integer.parseInt(c[2])); break;
            case "drag": DeskInput.move(Integer.parseInt(c[1]), Integer.parseInt(c[2])); break;
            case "up": DeskInput.release(Integer.parseInt(c[1]), Integer.parseInt(c[2])); break;
            case "key": { int k = Integer.parseInt(c[1]); DeskInput.key(k, true); DeskInput.key(k, false); waitLeft = 2; break; }
            case "hold": holdKey = Integer.parseInt(c[1]); DeskInput.key(holdKey, true); waitLeft = Integer.parseInt(c[2]); break;
            case "pad": padAxis = Integer.parseInt(c[1]); ekvr.Pad.PAD.axes[padAxis] = Float.parseFloat(c[2]); waitLeft = Integer.parseInt(c[3]); break;
            case "shot": Shot.save(dir + "/" + c[1] + ".png"); break;
            case "quit": DeskBackend.app().exit(); break;
            // simulated Quest (mode=vr)
            case "aim": SimXr.aimStage = SimXr.aimFrom = null; SimXr.aimX = Integer.parseInt(c[1]); SimXr.aimY = Integer.parseInt(c[2]); break;
            case "aimrel": {      // aim the laser at the ground dx, dz cells from the hero
                float x = SimXr.core.scene.heroX + Float.parseFloat(c[1]), z = SimXr.core.scene.heroZ + Float.parseFloat(c[2]);
                SimXr.aimStage = ekvr.Mat.xform(SimXr.core.world(), x, 0f, z);
                SimXr.aimFrom = c.length > 3 ? ekvr.Mat.xform(SimXr.core.world(), x + 0.05f, 30f, z + 0.05f) : null;
                break;
            }
            case "aimsprite": {   // aim the laser at the nearest map sprite whose class name contains c[1], 0.3 m up
                net.fdgames.GameEntities.MapSprite best = null;
                float hx = SimXr.core.scene.heroX, hz = SimXr.core.scene.heroZ, bd = 1e9f;
                for (net.fdgames.GameEntities.MapSprite sp : net.fdgames.GameLevel.GameLevel.d) {
                    if (!sp.getClass().getSimpleName().contains(c[1])) continue;
                    float x = sp.x / 32f - 1f, z = -(sp.y / 32f - 1f), d = (x - hx) * (x - hx) + (z - hz) * (z - hz);
                    if (d < bd) { bd = d; best = sp; }
                }
                if (best == null) {
                    java.util.TreeMap<String, Integer> kinds = new java.util.TreeMap<>();
                    for (net.fdgames.GameEntities.MapSprite sp : net.fdgames.GameLevel.GameLevel.d) kinds.merge(sp.getClass().getSimpleName(), 1, Integer::sum);
                    System.out.println("[script] no " + c[1] + "; sprites: " + kinds);
                    break;
                }
                float x = best.x / 32f - 1f, z = -(best.y / 32f - 1f);
                SimXr.aimStage = ekvr.Mat.xform(SimXr.core.world(), x, 0.3f, z);
                // "aimsprite NPC top": from straight above it (nothing in between), else from the hand
                SimXr.aimFrom = c.length > 2 ? ekvr.Mat.xform(SimXr.core.world(), x + 0.05f, 30f, z + 0.05f) : null;
                System.out.println("[script] aim " + best.getClass().getSimpleName() + " at " + x + "," + z + " (" + Math.sqrt(bd) + " cells)");
                break;
            }
            case "trig": SimXr.trigger = c[1].equals("1"); waitLeft = 2; break;
            case "drawdist": SimXr.core.binds.drawDist = Integer.parseInt(c[1]); break;
            case "stats": System.out.println("[script] quads=" + SimXr.core.scene.rec.n + " build=" + SimXr.core.scene.buildNs / 1000 + "us zoom=" + SimXr.core.scene.zoom); break;
            case "swing": SimXr.handAt = null; SimXr.swingFrames = 6; break;
            case "handslow": SimXr.handAt = null; SimXr.swingFrames = 30; SimXr.slow = true; break;
            case "mouth": SimXr.handAt = c.length > 1 && c[1].equals("off") ? null
                    : ekvr.Mat.xform(SimXr.headPose, 0, -0.09f, -0.07f); break;
            case "gesture": System.out.println("[script] gesture=" + SimXr.core.lastGesture); SimXr.core.lastGesture = ""; break;
            case "fov": SimXr.fov = Float.parseFloat(c[1]); break;
            case "uiclickat": {  // click the UI where it is seen at window pixel (x, y)
                float[] p = SimXr.uiPointAtPixel(Integer.parseInt(c[1]), Integer.parseInt(c[2]), 1280, 720);
                if (p == null) { System.out.println("[script] uiclickat: no panel there"); break; }
                SimXr.aimFrom = null; SimXr.aimStage = p;
                break;
            }
            case "uiaim": SimXr.aimFrom = null; SimXr.aimStage = SimXr.core.uiPoint(Integer.parseInt(c[1]), Integer.parseInt(c[2])); break;
            case "fakereport":   // (tests) show the last-exit report panel with a sample crash
                SimXr.core.menu.report = "LAST EXIT: JAVA CRASH\nWHEN: Sat Oct 25 15:24:26 GMT 2026\nJAVA EXCEPTION:\njava.lang.NullPointerException: Attempt to invoke virtual method 'int net.fdgames.GameEntities.MapActor.q()' on a null object reference\n\tat net.fdgames.Helpers.Serializer.a(Serializer.java:412)\n\tat l0.b$3.run(b.java:88)";
                break;
            case "menurow": SimXr.aimFrom = null; SimXr.aimStage = SimXr.core.menu.rowPoint(Integer.parseInt(c[1])); break;
            case "opts": try {
                java.lang.reflect.Field f = net.fdgames.ek.Settings.class.getDeclaredField("c"); f.setAccessible(true);
                net.fdgames.ek.SettingsData d = (net.fdgames.ek.SettingsData) f.get(null);
                System.out.println("[script] showControls=" + d.showControls + " attackInteracts=" + d.attackInteracts);
            } catch (Exception e) { System.out.println("[script] opts: " + e); } break;
            case "rstick": SimXr.rstickX = Float.parseFloat(c[1]); SimXr.rstickY = Float.parseFloat(c[2]); break;
            case "yaw": System.out.println("[script] yaw=" + Math.round(Math.toDegrees(SimXr.core.yaw())) ); break;
            case "stick": SimXr.stickX = Float.parseFloat(c[1]); SimXr.stickY = Float.parseFloat(c[2]); break;
            case "btn": {
                boolean on = c.length < 3 || c[2].equals("1");
                switch (c[1]) {
                    case "a": SimXr.a = on; break; case "b": SimXr.b = on; break; case "x": SimXr.x = on; break;
                    case "click": SimXr.click = on; break; case "trig": SimXr.trigger = on; break;
                    default: if (on) SimXr.held.add(c[1]); else SimXr.held.remove(c[1]);
                }
                waitLeft = 2; break;
            }
            case "look": SimXr.headYaw = Float.parseFloat(c[1]); SimXr.headPitch = Float.parseFloat(c[2]); break;
            case "vrmode": SimXr.core.setMode(ekvr.VrCore.Mode.valueOf(c[1].toUpperCase()), SimXr.headPose); break;
            case "actions": {   // run an EK action list through the game's own ActionsSet (e.g. the camp exit)
                String acts = String.join(" ", java.util.Arrays.copyOfRange(c, 1, c.length));
                new net.fdgames.GameLogic.ActionsSet(acts).a();
                break;
            }
            case "hover": System.out.println("[script] hover=" + SimXr.core.hover + ("thing".equals(SimXr.core.hover) ? " " + SimXr.core.hoverAt : "")); break;
            case "hero": System.out.println("[script] hero=" + SimXr.core.scene.heroX + "," + SimXr.core.scene.heroZ); break;
            default: System.out.println("[script] unknown: " + c[0]);
        }
    }
}

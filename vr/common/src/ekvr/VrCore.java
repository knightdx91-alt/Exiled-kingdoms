package ekvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Everything VR that is not platform plumbing (deobf/QUEST_VR_SPEC.md §5): where the map
 * sits in the room, the two view modes, the game's UI panel, the Touch controllers mapped
 * onto the game's own touch input, and drawing one eye. The Quest (ekvr.android.VrEK)
 * feeds it OpenXR poses; the desktop test bed feeds it a simulated head and controllers.
 *
 * Stage space = OpenXR LOCAL_FLOOR (metres, y up, floor at 0).
 * World space = Scene3D units (1 = one map cell; X = column, Z = -row).
 */
public class VrCore {
    public interface Touch {
        void down(int x, int y);
        void drag(int x, int y);
        void up(int x, int y);
        void key(int androidKeyCode);
        /** Hold / release a key (a gamepad button's key event). */
        void keyDown(int androidKeyCode);
        void keyUp(int androidKeyCode);
    }

    /** One controller this frame (OpenXR aim pose, stage space). */
    public static class Hand {
        public boolean active;
        public float[] aim = Mat.id();            // aim pose matrix: origin = laser start, -Z = pointing
        public float trigger, grip, sx, sy;
        public boolean a, b, stick, menu;
        public void set(float[] in, int o) {
            active = in[o] > 0.5f;
            aim = Mat.pose(in[o + 1], in[o + 2], in[o + 3], in[o + 4], in[o + 5], in[o + 6], in[o + 7]);
            trigger = in[o + 8]; grip = in[o + 9]; sx = in[o + 10]; sy = in[o + 11];
            a = in[o + 12] > 0.5f; b = in[o + 13] > 0.5f; stick = in[o + 14] > 0.5f; menu = in[o + 15] > 0.5f;
        }
    }

    /** FIRST: you are the hero; WALK: third person, the hero on the floor ahead; TABLE: a board. */
    public enum Mode { FIRST, WALK, TABLE }
    static final float EYE = 1.55f;                // FIRST: eye height above the map floor (m)

    public final Scene3D scene = new Scene3D();
    public UiLayer ui;
    public final int vw, vh;                       // the game's virtual screen (UI layer pixels)
    public Mode mode = Mode.WALK;
    public final Binds binds = new Binds();
    public final VrMenu menu = new VrMenu(binds);
    boolean bindsLoaded;
    public boolean passthrough;                    // TABLE over the real room when the headset can
    public float tableScale = 0.045f;              // metres per cell on the table
    float yaw;                                     // board / world rotation about Y
    final float[] anchor = {0, 0.75f, -0.6f};      // TABLE: board centre; WALK: hero's spot on the floor
    final float[] head = {0, 1.2f, 0};
    float headYaw;
    float fx, fz;                                  // smoothed hero position (world)
    boolean placed, haveFocus;
    public final Hand left = new Hand(), right = new Hand();
    public Touch touch;
    // UI panel (stage space): centre, right and up half-extent vectors
    final float[] pc = new float[3], pr = new float[3], pu = new float[3];
    // pointer state
    boolean trigHeld, stickHeld, turnLatch, gripWas, menuWas, menuTrigWas, trigDrag, trigThing;
    final boolean[] was = new boolean[Binds.Btn.values().length];   // buttons last frame (edges)
    Binds.Btn clickBtn;                            // the button holding the current laser press
    float prevHX, prevHZ;                          // hero last frame (camera follow)
    int lastX, lastY, downX, downY;
    final float[] grabFrom = new float[3];
    float[] laserEnd;
    public String hover = "";                       // what the laser is on: "ui", "thing", "ground", ""
    public String hoverAt = "";                     // (tests) the thing's cell and the tap it makes

    public VrCore(int vw, int vh) { this.vw = vw; this.vh = vh; }

    /** (tests) the stage point of the game's screen pixel (x, y) on the UI panel. */
    public float[] uiPoint(int x, int y) {
        float u = x / (float) (vw - 1) * 2 - 1, v = 1 - y / (float) (vh - 1) * 2;
        return new float[]{pc[0] + pr[0] * u + pu[0] * v, pc[1] + pr[1] * u + pu[1] * v, pc[2] + pr[2] * u + pu[2] * v};
    }

    /** (tests) where the eye ray (origin o, direction d) meets the UI panel, as a stage point, or null. */
    public float[] uiHitFromEye(float[] o, float[] d) {
        float[] aim = Mat.rigidInverse(Mat.lookAt(o[0], o[1], o[2], o[0] + d[0], o[1] + d[1], o[2] + d[2]));
        float[] end = new float[3];
        return panelHit(aim, pc, pr, pu, end) == null ? null : end;
    }

    /** (tests) the world's yaw in radians. */
    public float yaw() { return yaw; }

    // ---- per frame -------------------------------------------------------------------------
    /** Run the real game for one frame: map recorded for 3D, UI into the layer. */
    public void gameFrame(Runnable renderGame, int defaultFbo) {
        if (ui == null) ui = new UiLayer(vw, vh);
        scene.zoom = Binds.ZOOMS[binds.drawDist];                 // draw distance = EK's map camera zoom
        fitGameMenu();
        scene.beginFrame();
        ui.begin();
        renderGame.run();
        ui.end(defaultFbo);
        scene.endFrame();
        scene.hideHero = mode == Mode.FIRST;
        try { scene.classify(); }                              // also finds where the hero is drawn
        catch (Throwable t) { System.out.println("[ekvr] classify: " + t); }
        if (!haveFocus) { fx = scene.dispX(); fz = scene.dispZ(); haveFocus = true; }
        // follow the hero: exactly when walking (lag reads as the floor sliding / the hero drifting)
        float k = mode == Mode.TABLE ? 0.25f : 1f;
        fx += (scene.dispX() - fx) * k;
        fz += (scene.dispZ() - fz) * k;
    }

    /** World (map units) -> stage (metres). */
    public float[] world() {
        float s = mode == Mode.TABLE ? tableScale : 1f;
        return Mat.mul(Mat.translate(anchor[0], anchor[1], anchor[2]),
                Mat.mul(Mat.rotY(yaw), Mat.mul(Mat.scale(s), Mat.translate(-fx, 0, -fz))));
    }

    /** Place the board / walk spot in front of the user (start, and left-stick click). */
    public void recenter(float[] headPose) {
        head[0] = headPose[12]; head[1] = headPose[13]; head[2] = headPose[14];
        float fx_ = -headPose[8], fz_ = -headPose[10];                      // head forward (−Z axis)
        float l = (float) Math.sqrt(fx_ * fx_ + fz_ * fz_);
        if (l < 1e-3) { fx_ = 0; fz_ = -1; l = 1; }
        fx_ /= l; fz_ /= l;
        float oldYaw = headYaw;
        headYaw = (float) Math.atan2(-fx_, -fz_);
        if (mode == Mode.TABLE) {
            anchor[0] = head[0] + fx_ * 0.6f; anchor[1] = Math.max(0.5f, head[1] - 0.5f); anchor[2] = head[2] + fz_ * 0.6f;
        } else if (mode == Mode.WALK) {
            // third person: the hero 2.4 m ahead, the map floor 2.0 m below your eyes (seated works)
            float dist = Binds.DISTS[binds.camDist], hgt = Binds.HEIGHTS[binds.camHeight];
            anchor[0] = head[0] + fx_ * dist; anchor[1] = head[1] - hgt; anchor[2] = head[2] + fz_ * dist;
        } else {                                                            // FIRST: the hero's spot is yours
            anchor[0] = head[0]; anchor[1] = head[1] - EYE; anchor[2] = head[2];
        }
        // first time: the map's "up the screen" direction (iso view: world -X,-Z) points away from
        // the user; later (recenter, view change): whatever was ahead of you stays ahead
        if (!placed) yaw = headYaw - (float) Math.toRadians(45);
        else yaw += headYaw - oldYaw;
        placed = true;
        placePanel(fx_, fz_);
    }

    void placePanel(float fx_, float fz_) {
        float w, h, dist, y;
        // TABLE: like a screen standing behind the board; WALK: a large screen ahead, a bit low
        if (mode == Mode.TABLE) { w = 1.0f; dist = 1.15f; y = anchor[1] + 0.42f; }
        else if (mode == Mode.WALK) { w = 1.6f; dist = 1.5f; y = head[1] - 0.1f; }
        else { w = 1.5f; dist = 1.3f; y = head[1] - 0.3f; }
        h = w * vh / vw;
        pc[0] = head[0] + fx_ * dist; pc[1] = y; pc[2] = head[2] + fz_ * dist;
        pr[0] = -fz_ * w / 2; pr[1] = 0; pr[2] = fx_ * w / 2;              // right of the user
        pu[0] = 0; pu[1] = h / 2; pu[2] = 0;
    }

    public void setMode(Mode m, float[] headPose) { mode = m; recenter(headPose); }

    /** Change view: third person -> first person -> tabletop -> third person. */
    public void nextMode(float[] headPose) {
        setMode(mode == Mode.WALK ? Mode.FIRST : mode == Mode.FIRST ? Mode.TABLE : Mode.WALK, headPose);
    }

    /**
     * The UI panel follows your gaze lazily (walking views): once you look more than 40° away it
     * glides (up to 120°/s) back in front of you, instead of jumping.
     */
    void panelFollow(float[] headPose, float dt) {
        float hy = headYawOf(headPose);
        float px = pc[0] - head[0], pz = pc[2] - head[2];
        float py = (float) Math.atan2(-px, -pz);
        float d = (float) Math.IEEEremainder(hy - py, 2 * Math.PI);
        if (Math.abs(d) > Math.toRadians(40)) panelGliding = true;
        if (!panelGliding) return;
        if (Math.abs(d) < Math.toRadians(3)) { panelGliding = false; return; }
        float step = Math.signum(d) * Math.min(Math.abs(d), (float) Math.toRadians(120) * dt);
        float ny = py + step;
        placePanel((float) -Math.sin(ny), (float) -Math.cos(ny));
    }

    boolean panelGliding;

    // ---- input -------------------------------------------------------------------------------
    boolean down(Binds.Btn b) {
        switch (b) {
            case A: return right.a;
            case B: return right.b;
            case X: return left.a;
            case Y: return left.b;
            case RTRIG: return right.trigger > 0.6f;
            case LTRIG: return left.trigger > 0.6f;
            case RGRIP: return right.grip > 0.6f;
            case LGRIP: return left.grip > 0.6f;
            case RSTICK: return right.stick;
            case LSTICK: return left.stick;
            case SWING_R: return gestureOn(swingUntil[1]);
            case SWING_L: return gestureOn(swingUntil[0]);
            case MOUTH_R: return gestureOn(mouthUntil[1]);
            case MOUTH_L: return gestureOn(mouthUntil[0]);
            default: return false;
        }
    }

    // ---- gestures (QUEST_VR_SPEC.md §5.2b) ----------------------------------------------------
    float time;                                            // seconds since start (gesture timers)
    final float[] swingUntil = new float[2], mouthUntil = new float[2];   // [left, right]: input held until
    final float[] cooldown = new float[2], mouthSince = {-1, -1};
    final boolean[] mouthArmed = {true, true};
    final float[][] lastPos = new float[2][], vel = new float[2][2];
    public String lastGesture = "";                       // (tests)

    boolean gestureOn(float until) { return binds.gestures && time < until; }

    /** Swing: the controller faster than the swing speed for 2 frames. Mouth: held at the mouth 0.25 s. */
    void gestures(float[] headPose, float dt) {
        time += dt;
        if (!binds.gestures || dt <= 0) return;
        float[] mouth = Mat.xform(headPose, 0, -0.09f, -0.07f);   // below the eyes, a little in front
        for (int h = 0; h < 2; h++) {
            Hand hand = h == 0 ? left : right;
            if (!hand.active || (h == 0 && gripWas)) { lastPos[h] = null; continue; }   // moving the board isn't a swing
            float[] p = {hand.aim[12], hand.aim[13], hand.aim[14]};
            if (lastPos[h] != null) {
                float dx = p[0] - lastPos[h][0], dy = p[1] - lastPos[h][1], dz = p[2] - lastPos[h][2];
                float v = (float) Math.sqrt(dx * dx + dy * dy + dz * dz) / dt;
                vel[h][0] = vel[h][1]; vel[h][1] = v;
                float need = Binds.SWING[binds.swingSens];
                if (time > cooldown[h] && vel[h][0] > need && vel[h][1] > need && v < 30f) {
                    swingUntil[h] = time + 0.3f;
                    cooldown[h] = time + 0.6f;
                    lastGesture = (h == 0 ? "SWING L" : "SWING R") + " " + Math.round(v * 10) / 10f + " m/s";
                }
            }
            lastPos[h] = p;
            float mx = p[0] - mouth[0], my = p[1] - mouth[1], mz = p[2] - mouth[2];
            float d = (float) Math.sqrt(mx * mx + my * my + mz * mz);
            if (d > 0.35f) mouthArmed[h] = true;
            if (d < 0.2f && mouthArmed[h]) {
                if (mouthSince[h] < 0) mouthSince[h] = time;
                if (time - mouthSince[h] >= 0.25f) {
                    mouthUntil[h] = time + 0.3f;
                    mouthArmed[h] = false;
                    mouthSince[h] = -1;
                    lastGesture = h == 0 ? "MOUTH L" : "MOUTH R";
                }
            } else mouthSince[h] = -1;
        }
    }

    static java.lang.reflect.Field fGameMenu;
    public String menuFit = "";                                 // (tests)

    /**
     * EK's game menu (e.a.d.b0, a Window; singleton in its static field i) sizes its buttons from
     * the screen height but its difficulty row and the mod's MULTIPLAYER button are fixed-size, so
     * at 720 px the contents are taller than the window and the top row is cut off (owner's
     * screenshot). Grow the window to its contents and re-centre it (QUEST_VR_SPEC.md V8).
     */
    void fitGameMenu() {
        try {
            if (fGameMenu == null) { fGameMenu = e.a.d.b0.class.getDeclaredField("i"); fGameMenu.setAccessible(true); }
            com.badlogic.gdx.scenes.scene2d.ui.Window w = (com.badlogic.gdx.scenes.scene2d.ui.Window) fGameMenu.get(null);
            if (w == null) return;
            float pref = w.getPrefHeight(), h = w.getHeight();
            menuFit = "pref " + pref + " height " + h;
            if (pref > h + 0.5f) {
                float nh = Math.min(pref, vh);
                w.setHeight(nh);
                w.setY((vh - nh) / 2f);
                w.invalidate();
            }
        } catch (Throwable t) {
            menuFit = t.toString();
        }
    }

    /** Map the controllers onto the game (QUEST_VR_SPEC.md §5). {@code headPose} = the head (centre eye) matrix. */
    public void input(float[] headPose, float dt) {
        if (!bindsLoaded) {
            bindsLoaded = true;
            binds.load();
            mode = Mode.values()[binds.startView];
        }
        if (!placed) recenter(headPose);
        if (!settingsDone) settingsDone = gameSettings();
        head[0] = headPose[12]; head[1] = headPose[13]; head[2] = headPose[14];
        gestures(headPose, dt);
        Binds.Btn[] all = Binds.Btn.values();
        boolean[] now = new boolean[all.length];
        Binds.Btn pressed = null, anyDown = null;
        for (Binds.Btn b : all) {
            now[b.ordinal()] = down(b);
            if (now[b.ordinal()]) anyDown = b;
            if (now[b.ordinal()] && !was[b.ordinal()] && pressed == null) pressed = b;
        }
        // Menu button: the VR controls screen
        if (left.menu && !menuWas) {
            if (menu.open) menu.close();
            else {
                float hy = headYawOf(headPose);
                releaseAll();
                menu.show(head, (float) -Math.sin(hy), (float) -Math.cos(hy));
            }
        }
        menuWas = left.menu;
        if (menu.report != null && !menu.showingReport && !menu.open) {      // why the game closed last time
            float hy = headYawOf(headPose);
            menu.showReport(head, (float) -Math.sin(hy), (float) -Math.cos(hy));
        }
        if (menu.recenter) { menu.recenter = false; recenter(headPose); }
        if (menu.open) {
            menuInput(anyDown, pressed);
            System.arraycopy(now, 0, was, 0, now.length);
            return;
        }
        turning(dt);
        if (mode == Mode.TABLE && Math.abs(turnStick().sy) > 0.4f)
            tableScale = Math.max(0.015f, Math.min(0.2f, tableScale * (1 + turnStick().sy * dt * 1.2f)));
        // left grip: move the board with your hand (tabletop; the grip's binding is ignored meanwhile)
        boolean boardGrab = false;
        if (mode == Mode.TABLE && left.active) {
            float[] p = {left.aim[12], left.aim[13], left.aim[14]};
            if (left.grip > 0.6f) {
                if (!gripWas) { grabFrom[0] = p[0] - anchor[0]; grabFrom[1] = p[1] - anchor[1]; grabFrom[2] = p[2] - anchor[2]; }
                else { anchor[0] = p[0] - grabFrom[0]; anchor[1] = p[1] - grabFrom[1]; anchor[2] = p[2] - grabFrom[2]; }
                gripWas = true; boardGrab = true;
            } else gripWas = false;
        }
        if (mode != Mode.TABLE && !trigHeld) panelFollow(headPose, dt);
        // the laser: where it points this frame (UI, a thing, the ground)
        Hand ptr = pointerHand(now);
        Binds.Btn ptrTrig = ptr == left ? Binds.Btn.LTRIG : Binds.Btn.RTRIG;
        int[] at = ptr.active ? laserTarget(ptr.aim) : null;
        boolean onUi = "ui".equals(hover);
        // one-shot functions on a button's press
        for (Binds.Btn b : all) {
            if (!now[b.ordinal()] || was[b.ordinal()]) continue;
            if (b == Binds.Btn.LGRIP && boardGrab) continue;
            Binds.Fn f = binds.fn(b);
            if (b == ptrTrig && onUi) f = Binds.Fn.CLICK;              // the pointer's trigger always clicks the UI
            switch (f) {
                case VIEW: nextMode(headPose); break;
                case RECENTER: recenter(headPose); break;
                case GAME_MENU: if (touch != null) touch.key(4); break;
                case CLICK:
                    if (touch != null && at != null && !trigHeld) {
                        touch.down(at[0], at[1]);
                        downX = lastX = at[0]; downY = lastY = at[1];
                        trigHeld = true; trigDrag = false; trigThing = "thing".equals(hover); clickBtn = b;
                    }
                    break;
                default: break;
            }
        }
        // the laser press: a tap, a drag once it really moves (hand jitter), released with its button
        if (trigHeld && touch != null) {
            if (clickBtn == null || !now[clickBtn.ordinal()]) { touch.up(lastX, lastY); trigHeld = false; clickBtn = null; }
            else if (at != null && !trigThing) {
                if (!trigDrag && Math.abs(at[0] - downX) + Math.abs(at[1] - downY) > 16) trigDrag = true;
                if (trigDrag && (at[0] != lastX || at[1] != lastY)) { touch.drag(at[0], at[1]); lastX = at[0]; lastY = at[1]; }
            }
        }
        // the game's own controller (§5.1): stick walks, held buttons drive EK commands
        Hand mv = moveStick();
        float m = (float) Math.sqrt(mv.sx * mv.sx + mv.sy * mv.sy);
        float[] wd = m > 0.25f ? stickWorldDir(headPose) : null;
        if (Pad.active()) {
            Pad pad = Pad.PAD;
            pad.clear();
            net.fdgames.ek.ControllerConfig cc = net.fdgames.ek.Settings.a;
            if (cc != null) {
                if (wd != null) {
                    // world direction (X = column, Z = -row) -> world px (dx, dy) = (X, -Z) -> z.D(f2, f3)
                    float dx = wd[0], dy = -wd[1];
                    float f2 = (dx + dy) / 2, f3 = (dy - dx) / 2;
                    float l = Math.abs(f2) + Math.abs(f3);
                    f2 /= l; f3 /= l;                                   // full speed; the game clamps |f2|+|f3| to 1
                    // EK ignores an axis under |0.2|: scale both up so the smaller one passes; z.D
                    // divides by |f2|+|f3| (> 1 now), so the direction stays exact (§5.2)
                    float lo = Math.min(Math.abs(f2), Math.abs(f3));
                    if (lo < 0.02f) { if (Math.abs(f2) < 0.02f) f2 = 0; else f3 = 0; }
                    else if (lo < 0.25f) { float k = 0.25f / lo; f2 *= k; f3 *= k; }
                    pad.set(f2 > 0 ? cc.right : cc.left, Math.abs(f2));
                    pad.set(f3 > 0 ? cc.up : cc.down, Math.abs(f3));
                }
                for (Binds.Btn b : all) {
                    if (!now[b.ordinal()] || (b == Binds.Btn.LGRIP && boardGrab)) continue;
                    // only Attack / act repeats while held; every other command fires once per press
                    // (a grip squeezed just by holding the controller must not keep firing a skill)
                    if (binds.fn(b) != Binds.Fn.ACT && was[b.ordinal()]) continue;
                    if (b == ptrTrig && (onUi || clickBtn == b)) continue;   // that press is a UI click
                    switch (binds.fn(b)) {
                        case ACT: pad.set(cc.action, 1); break;
                        case CANCEL: pad.set(cc.cancel, 1); break;
                        case NEXT_ITEM: pad.set(cc.nextItem, 1); break;
                        case PREV_ITEM: pad.set(cc.prevItem, 1); break;
                        case USE_ITEM: pad.set(cc.useItem, 1); break;
                        case RECOVERY: pad.set(cc.recovery, 1); break;
                        case SKILL1: pad.set(cc.skill1, 1); break;
                        case SKILL2: pad.set(cc.skill2, 1); break;
                        case SKILL3: pad.set(cc.skill3, 1); break;
                        case SKILL4: pad.set(cc.skill4, 1); break;
                        default: break;
                    }
                }
            }
        } else if (touch != null && !trigHeld) {
            // no controller support (shouldn't happen): hold a touch on the ground ahead of the hero
            if (wd != null) {
                int[] p = UiLayer.groundToTouch(scene.heroX + wd[0] * 2.5f, scene.heroZ + wd[1] * 2.5f, vw, vh);
                if (p != null) {
                    if (!stickHeld) { touch.down(p[0], p[1]); stickHeld = true; } else touch.drag(p[0], p[1]);
                    lastX = p[0]; lastY = p[1];
                }
            } else if (stickHeld) { touch.up(lastX, lastY); stickHeld = false; }
        }
        mirrorKeys();
        cameraFollow(dt);
        System.arraycopy(now, 0, was, 0, now.length);
    }

    final boolean[] keyHeld = new boolean[256];

    /**
     * A real Android gamepad sends each button twice: as a controller button and as a key event
     * with the same code (KEYCODE_BUTTON_A = 96 ...). EK relies on the key half: with a controller
     * connected, GameScreen clears the "attacking" flag every frame unless the action's code is
     * held as a key (Gdx.input.isKeyPressed(Settings.a.action.id)), so the attack stopped the
     * frame it started. Send the key events for the pad's buttons too (QUEST_VR_SPEC.md §5.1).
     */
    void mirrorKeys() {
        if (touch == null || !Pad.active()) return;
        boolean[] b = Pad.PAD.buttons;
        for (int i = 0; i < keyHeld.length && i < b.length; i++) {
            if (b[i] && !keyHeld[i]) { touch.keyDown(i); keyHeld[i] = true; }
            else if (!b[i] && keyHeld[i]) { touch.keyUp(i); keyHeld[i] = false; }
        }
    }

    boolean settingsDone;

    /**
     * EK's own options the controllers need (QUEST_VR_SPEC.md §5.1b): no on-screen joystick,
     * and "attack interacts" so the action button opens / talks / loots. Applied every frame
     * until a map is shown (the settings file loads during start-up), then left to the player.
     */
    static boolean gameSettings() {
        try {
            java.lang.reflect.Field f = net.fdgames.ek.Settings.class.getDeclaredField("c");
            f.setAccessible(true);
            net.fdgames.ek.SettingsData d = (net.fdgames.ek.SettingsData) f.get(null);
            if (d != null) { d.showControls = false; d.attackInteracts = true; }
            return d != null && net.fdgames.GameLevel.GameLevel.h() != null;
        } catch (Throwable t) {
            System.out.println("[ekvr] can't set game options: " + t);
            return true;
        }
    }

    /** Let go of everything the game is holding (opening the VR menu). */
    void releaseAll() {
        if (trigHeld && touch != null) touch.up(lastX, lastY);
        if (stickHeld && touch != null) touch.up(lastX, lastY);
        trigHeld = stickHeld = false; clickBtn = null;
        if (Pad.active()) { Pad.PAD.clear(); mirrorKeys(); }
    }

    void menuInput(Binds.Btn anyDown, Binds.Btn pressed) {
        float[] end = new float[3];
        Hand ptr = pointerHand(null);
        int[] p = ptr.active ? menu.hit(ptr.aim, end) : null;
        laserEnd = p != null ? end : ptr.active ? Mat.xform(ptr.aim, 0, 0, -3) : null;
        hover = p != null ? "menu" : "";
        if (menu.waiting != null) {
            // bind the next button that goes down. Only a fresh press counts (the trigger that chose
            // the row is still held; a grip squeezed just by holding the controller never "releases"
            // -- waiting for every button to be let go never happened on the headset)
            if (pressed != null) menu.capture(pressed);
            menuTrigWas = right.trigger > 0.6f || left.trigger > 0.6f;
            return;
        }
        menu.setHover(p);
        boolean trig = right.trigger > 0.6f || left.trigger > 0.6f;
        if (trig && !menuTrigWas) menu.click();
        menuTrigWas = trig;
    }

    /**
     * Right stick X: smooth turn (deg/s from the settings) or 30° snaps; the world turns about its
     * anchor (third person: orbit the hero). Stick right = view turns right: stage heading =
     * world heading + yaw and headings grow to the left, so the world's yaw grows.
     */
    void turning(float dt) {
        float sx = turnStick().sx;
        if (binds.smoothTurn) {
            if (Math.abs(sx) > 0.2f) {
                float k = (Math.abs(sx) - 0.2f) / 0.8f * Math.signum(sx);
                yaw += (float) Math.toRadians(binds.turnSpeed) * k * dt;
            }
        } else if (Math.abs(sx) > 0.7f) {
            if (!turnLatch) { yaw += (float) Math.toRadians(sx > 0 ? 30 : -30); turnLatch = true; }
        } else if (Math.abs(sx) < 0.4f) turnLatch = false;
    }

    /**
     * Third person: while the hero walks, swing the view smoothly until the walking direction
     * points away from you (the camera stays behind the hero). Not for walking back toward you.
     */
    void cameraFollow(float dt) {
        float mx = scene.heroX - prevHX, mz = scene.heroZ - prevHZ;
        prevHX = scene.heroX; prevHZ = scene.heroZ;
        if (mode != Mode.WALK || !binds.follow || dt <= 0) return;
        float sp = (float) Math.sqrt(mx * mx + mz * mz) / dt;
        if (sp < 0.5f || sp > 40f) return;                              // standing / teleported
        // stage heading = world heading + yaw; we want the move to head where you face (headYaw)
        float moveH = (float) Math.atan2(-mx, -mz);
        float diff = (float) Math.IEEEremainder(headYaw - moveH - yaw, 2 * Math.PI);
        if (Math.abs(diff) > Math.toRadians(50)) return;                // walking sideways / back: leave the view alone
        if (Math.abs(turnStick().sx) > 0.2f) return;                    // you are turning yourself
        // gentle (<= 40°/s, slower the closer): holding the stick sideways makes a wide arc, not a spin
        float rate = (float) Math.toRadians(40) * Math.min(1f, Math.abs(diff) / (float) Math.toRadians(90));
        float step = Math.min(Math.abs(diff), rate * dt);
        yaw += Math.signum(diff) * step;
    }

    /** The move stick as a world direction (X, Z), relative to the view's forward (or the head). */
    float[] stickWorldDir(float[] headPose) {
        Hand st = moveStick();
        float hy = binds.moveHead ? headYawOf(headPose) : headYaw;
        float dx = (float) (Math.cos(hy) * st.sx - Math.sin(hy) * st.sy);
        float dz = (float) (-Math.sin(hy) * st.sx - Math.cos(hy) * st.sy);
        // stage direction -> world direction (undo the world yaw)
        float c = (float) Math.cos(-yaw), s = (float) Math.sin(-yaw);
        return new float[]{c * dx + s * dz, -s * dx + c * dz};
    }

    Hand moveStick() { return binds.moveRight ? right : left; }
    Hand turnStick() { return binds.moveRight ? left : right; }

    Hand lastPtr;

    /** The laser hand: fixed by the setting, or (auto) whichever trigger was pulled last. */
    Hand pointerHand(boolean[] now) {
        if (binds.pointer == 1) return right;
        if (binds.pointer == 2) return left;
        if (left.trigger > 0.6f && !(right.trigger > 0.6f) && lastPtr != left) lastPtr = left;
        else if (right.trigger > 0.6f && !(left.trigger > 0.6f) && lastPtr != right) lastPtr = right;
        if (lastPtr == null || !lastPtr.active) lastPtr = right.active || !left.active ? right : left;
        return lastPtr;
    }

    void tap(int x, int y) { if (touch != null) { touch.down(x, y); touch.up(x, y); } }

    /** Yaw of a pose's forward (−Z) direction: forward = (−sin h, −cos h). */
    static float headYawOf(float[] pose) {
        return (float) Math.atan2(pose[8], pose[10]);
    }

    /**
     * Laser vs a panel (centre c, right/up half-extents r/u, stage space): its (u, v) in -1..1
     * (v up), or null; the hit point goes to {@code end}.
     */
    static float[] panelHit(float[] aim, float[] c, float[] r, float[] u, float[] end) {
        float ox = aim[12], oy = aim[13], oz = aim[14];
        float dx = -aim[8], dy = -aim[9], dz = -aim[10];
        float nx = r[1] * u[2] - r[2] * u[1], ny = r[2] * u[0] - r[0] * u[2], nz = r[0] * u[1] - r[1] * u[0];
        float den = nx * dx + ny * dy + nz * dz;
        if (Math.abs(den) < 1e-6) return null;
        float t = (nx * (c[0] - ox) + ny * (c[1] - oy) + nz * (c[2] - oz)) / den;
        if (t <= 0) return null;
        float hx = ox + dx * t - c[0], hy = oy + dy * t - c[1], hz = oz + dz * t - c[2];
        float pu = (hx * r[0] + hy * r[1] + hz * r[2]) / (r[0] * r[0] + r[1] * r[1] + r[2] * r[2]);
        float pv = (hx * u[0] + hy * u[1] + hz * u[2]) / (u[0] * u[0] + u[1] * u[1] + u[2] * u[2]);
        if (Math.abs(pu) > 1 || Math.abs(pv) > 1) return null;
        end[0] = ox + dx * t; end[1] = oy + dy * t; end[2] = oz + dz * t;
        return new float[]{pu, pv};
    }

    /** Where the laser lands, as a pixel of the game's screen (UI or the ground), or null. */
    int[] laserTarget(float[] aim) {
        float ox = aim[12], oy = aim[13], oz = aim[14];
        float dx = -aim[8], dy = -aim[9], dz = -aim[10];
        // 1. the UI panel, where the layer is opaque
        float nx = pr[1] * pu[2] - pr[2] * pu[1], ny = pr[2] * pu[0] - pr[0] * pu[2], nz = pr[0] * pu[1] - pr[1] * pu[0];
        float den = nx * dx + ny * dy + nz * dz;
        if (Math.abs(den) > 1e-6) {
            float t = (nx * (pc[0] - ox) + ny * (pc[1] - oy) + nz * (pc[2] - oz)) / den;
            if (t > 0) {
                float hx = ox + dx * t - pc[0], hy = oy + dy * t - pc[1], hz = oz + dz * t - pc[2];
                float u = (hx * pr[0] + hy * pr[1] + hz * pr[2]) / (pr[0] * pr[0] + pr[1] * pr[1] + pr[2] * pr[2]);
                float v = (hx * pu[0] + hy * pu[1] + hz * pu[2]) / (pu[0] * pu[0] + pu[1] * pu[1] + pu[2] * pu[2]);
                if (Math.abs(u) <= 1 && Math.abs(v) <= 1) {
                    int px = Math.round((u + 1) / 2 * (vw - 1)), py = Math.round((1 - v) / 2 * (vh - 1));
                    if (ui != null && ui.opaqueAt(px, py)) {
                        laserEnd = new float[]{ox + dx * t, oy + dy * t, oz + dz * t};
                        hover = "ui";
                        return new int[]{px, py};
                    }
                }
            }
        }
        // 2. a thing standing on the map (alpha-tested), if nearer than the ground
        float[] w = world();
        float[] inv = invertAffine(w);
        float[] o = Mat.xform(inv, ox, oy, oz);
        float[] o2 = Mat.xform(inv, ox + dx, oy + dy, oz + dz);
        float ddx = o2[0] - o[0], ddy = o2[1] - o[1], ddz = o2[2] - o[2];
        float tg = Math.abs(ddy) > 1e-6 ? -o[1] / ddy : -1;
        float[] eyeW = Mat.xform(inv, head[0], head[1], head[2]);
        float[] hit = scene.pick(o[0], o[1], o[2], ddx, ddy, ddz, tg > 0 ? tg : Float.MAX_VALUE, eyeW[0], eyeW[2]);
        if (hit != null) {
            int[] p = UiLayer.groundToTouch(hit[1], hit[2], vw, vh);         // tap the thing's own cell
            if (p != null) {
                laserEnd = Mat.xform(w, o[0] + ddx * hit[0], o[1] + ddy * hit[0], o[2] + ddz * hit[0]);
                hover = "thing";
                hoverAt = String.format(java.util.Locale.ROOT, "%.1f,%.1f -> %d,%d", hit[1], hit[2], p[0], p[1]);
                return p;
            }
        }
        // 3. the map's ground plane
        if (Math.abs(ddy) > 1e-6) {
            float t = tg;
            if (t > 0) {
                float gx = o[0] + ddx * t, gz = o[2] + ddz * t;
                int[] p = UiLayer.groundToTouch(gx, gz, vw, vh);
                if (p != null) {
                    laserEnd = Mat.xform(w, gx, 0, gz);
                    hover = "ground";
                    return p;
                }
            }
        }
        laserEnd = new float[]{ox + dx * 3, oy + dy * 3, oz + dz * 3};
        hover = "";
        return null;
    }

    static float[] invertAffine(float[] m) {
        // general 3x3 + translation inverse (world() includes uniform scale)
        float a = m[0], b = m[4], c = m[8], d = m[1], e = m[5], f = m[9], g = m[2], h = m[6], i = m[10];
        float det = a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
        float[] r = Mat.id();
        r[0] = (e * i - f * h) / det; r[4] = (c * h - b * i) / det; r[8] = (b * f - c * e) / det;
        r[1] = (f * g - d * i) / det; r[5] = (a * i - c * g) / det; r[9] = (c * d - a * f) / det;
        r[2] = (d * h - e * g) / det; r[6] = (b * g - a * h) / det; r[10] = (a * e - b * d) / det;
        r[12] = -(r[0] * m[12] + r[4] * m[13] + r[8] * m[14]);
        r[13] = -(r[1] * m[12] + r[5] * m[13] + r[9] * m[14]);
        r[14] = -(r[2] * m[12] + r[6] * m[13] + r[10] * m[14]);
        return r;
    }

    // ---- drawing one eye -------------------------------------------------------------------
    int lineProg, lPos, lMvp, lCol;
    final FloatBuffer lineBuf = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();

    /** Draw one eye into the bound framebuffer. {@code view} = stage -> eye; {@code proj} = eye projection. */
    public void drawEye(float[] proj, float[] view, int w, int h) {
        GL20 gl = UiLayer.gl();
        gl.glViewport(0, 0, w, h);
        boolean pt = passthrough && mode == Mode.TABLE;
        float sr = 0.42f * scene.tint[0], sg = 0.55f * scene.tint[1], sbl = 0.72f * scene.tint[2];
        if (pt) gl.glClearColor(0, 0, 0, 0);
        else gl.glClearColor(sr, sg, sbl, 1);
        // fog at the edge of the drawn area (draw distance), in the sky colour (or fading out over passthrough)
        scene.fogC[0] = scene.dispX(); scene.fogC[1] = scene.dispZ();
        scene.fogRange[0] = 8f * scene.zoom; scene.fogRange[1] = 13f * scene.zoom;
        scene.fogCol[0] = sr; scene.fogCol[1] = sg; scene.fogCol[2] = sbl; scene.fogCol[3] = pt ? 1f : 0f;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        float[] pv = Mat.mul(proj, view);
        float[] wm = world();
        float[] inv = invertAffine(wm);
        float[] eye = Mat.xform(Mat.rigidInverse(view), 0, 0, 0);
        float[] eyeW = Mat.xform(inv, eye[0], eye[1], eye[2]);
        scene.viewEyeX = eyeW[0]; scene.viewEyeY = eyeW[1]; scene.viewEyeZ = eyeW[2];
        scene.draw(Mat.mul(pv, wm), eyeW[0], eyeW[2]);
        scene.viewX = eyeW[0]; scene.viewZ = eyeW[2]; scene.haveViewer = true;
        if (ui != null) {
            float[] tl = {pc[0] - pr[0] + pu[0], pc[1] - pr[1] + pu[1], pc[2] - pr[2] + pu[2]};
            float[] tr = {pc[0] + pr[0] + pu[0], pc[1] + pr[1] + pu[1], pc[2] + pr[2] + pu[2]};
            float[] br = {pc[0] + pr[0] - pu[0], pc[1] + pr[1] - pu[1], pc[2] + pr[2] - pu[2]};
            float[] bl = {pc[0] - pr[0] - pu[0], pc[1] - pr[1] - pu[1], pc[2] - pr[2] - pu[2]};
            ui.draw(pv, tl, tr, br, bl);
        }
        if (menu.open) menu.draw(ui, pv);
        Hand ptr = pointerHand(null);
        if (ptr.active && laserEnd != null) line(gl, pv, ptr.aim[12], ptr.aim[13], ptr.aim[14], laserEnd);
    }

    void line(GL20 gl, float[] pv, float x, float y, float z, float[] end) {
        if (lineProg == 0) {
            int vs = Scene3D.shader(gl, GL20.GL_VERTEX_SHADER, "attribute vec3 a_pos; uniform mat4 u_mvp; void main(){ gl_Position=u_mvp*vec4(a_pos,1.0); gl_PointSize=9.0; }");
            int fs = Scene3D.shader(gl, GL20.GL_FRAGMENT_SHADER, "#ifdef GL_ES\nprecision mediump float;\n#endif\nuniform vec4 u_col; void main(){ gl_FragColor=u_col; }");
            lineProg = gl.glCreateProgram();
            gl.glAttachShader(lineProg, vs); gl.glAttachShader(lineProg, fs); gl.glLinkProgram(lineProg);
            lPos = gl.glGetAttribLocation(lineProg, "a_pos");
            lMvp = gl.glGetUniformLocation(lineProg, "u_mvp");
            lCol = gl.glGetUniformLocation(lineProg, "u_col");
        }
        UiLayer.clientArrays(gl);
        gl.glUseProgram(lineProg);
        gl.glUniformMatrix4fv(lMvp, 1, false, pv, 0);
        gl.glUniform4f(lCol, 1f, 0.85f, 0.4f, 1f);
        lineBuf.clear();
        lineBuf.put(x).put(y).put(z).put(end[0]).put(end[1]).put(end[2]);
        lineBuf.position(0);
        gl.glEnableVertexAttribArray(lPos);
        gl.glVertexAttribPointer(lPos, 3, GL20.GL_FLOAT, false, 12, lineBuf);
        gl.glLineWidth(3f);
        gl.glDrawArrays(GL20.GL_LINES, 0, 2);
        gl.glDrawArrays(GL20.GL_POINTS, 1, 1);
    }
}

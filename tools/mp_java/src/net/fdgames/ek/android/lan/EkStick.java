package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.fdgames.assets.Assets;

/**
 * Floating joystick (deobf/FLOATING_JOYSTICK_SPEC.md). Sits in front of the HUD stage in GameScreen's
 * InputMultiplexer and never consumes anything: on a touch in the bottom-left area it just moves the
 * HUD's Touchpad under the finger, so the stage's own hit test hands the touch to the joystick. Quick taps
 * there are replayed to the game world so tapping an enemy or chest still works.
 */
public final class EkStick implements com.badlogic.gdx.j {
    private static final String PREF = "ek_joystick_floating";
    private static Boolean floating;

    private final com.badlogic.gdx.j world;     // GameScreen: world taps
    private int pointer = -1;
    private float homeX;
    private float homeY;
    private int downX;
    private int downY;
    private long downAt;
    private boolean moved;
    private boolean onHome;

    private EkStick(Object world) {
        this.world = world instanceof com.badlogic.gdx.j ? (com.badlogic.gdx.j) world : null;
    }

    /** Hooked in GameScreen.<init>, added to the multiplexer right before the HUD stage. */
    public static com.badlogic.gdx.j install(Object gameScreen) {
        return new EkStick(gameScreen);
    }

    // ---- option ---------------------------------------------------------------------------------

    static boolean floating() {
        if (floating == null) {
            boolean on = true;
            try {
                android.app.Activity a = activity();
                if (a != null) {
                    on = a.getSharedPreferences("ek_lan_prefs", 0).getBoolean(PREF, true);
                }
            } catch (Throwable e) {
                // default on
            }
            floating = on;
        }
        return floating;
    }

    static void setFloating(boolean on) {
        floating = on;
        try {
            android.app.Activity a = activity();
            if (a != null) {
                a.getSharedPreferences("ek_lan_prefs", 0).edit().putBoolean(PREF, on).apply();
            }
        } catch (Throwable e) {
            // kept for this session
        }
    }

    private static android.app.Activity activity() {
        Object a = Gdx.app;
        return a instanceof android.app.Activity ? (android.app.Activity) a : null;
    }

    private static String label() {
        return floating() ? "JOYSTICK: FLOATING" : "JOYSTICK: FIXED";
    }

    /** Options window row, next to MULTIPLAYER. */
    public static TextButton optionsButton() {
        final TextButton b = new e.a.d.u(label(), Assets.e(), "menuButton");
        try {
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setFloating(!floating());
                    b.setText(label());
                }
            });
        } catch (Throwable e) {
            // ignore
        }
        return b;
    }

    // ---- input ----------------------------------------------------------------------------------

    static Touchpad testPad;                     // offline tests only
    static Stage testStage;

    private static Touchpad pad() {
        if (testPad != null) {
            return testPad;
        }
        try {
            return e.a.d.y.ekTouchpad();
        } catch (Throwable e) {
            return null;
        }
    }

    private static Stage stage() {
        if (testStage != null) {
            return testStage;
        }
        try {
            e.a.d.y h = e.a.d.y.J();
            return h == null ? null : h.a();
        } catch (Throwable e) {
            return null;
        }
    }

    public boolean touchDown(int sx, int sy, int p, int button) {
        try {
            if (pointer != -1 || !floating()) {
                return false;
            }
            Touchpad tp = pad();
            Stage st = stage();
            if (tp == null || st == null || !tp.isVisible() || tp.getParent() == null) {
                return false;
            }
            com.badlogic.gdx.math.s v = st.screenToStageCoordinates(new com.badlogic.gdx.math.s(sx, sy));
            float x = v.b;
            float y = v.c;
            if (x > st.getWidth() * 0.45f || y > st.getHeight() * 0.65f) {
                return false;
            }
            Actor hit = st.hit(x, y, true);
            onHome = hit != null && (hit == tp || hit.isDescendantOf(tp));
            if (hit != null && !onHome) {
                return false;                        // a HUD button: leave it alone
            }
            homeX = tp.getX();
            homeY = tp.getY();
            tp.setPosition(x - tp.getWidth() / 2f, y - tp.getHeight() / 2f);
            pointer = p;
            downX = sx;
            downY = sy;
            downAt = System.currentTimeMillis();
            moved = false;
        } catch (Throwable e) {
            pointer = -1;
        }
        return false;                                // the stage now finds the joystick under the finger
    }

    public boolean touchDragged(int sx, int sy, int p) {
        if (p == pointer && !moved) {
            float slop = Gdx.graphics.getHeight() * 0.03f;
            if (Math.abs(sx - downX) > slop || Math.abs(sy - downY) > slop) {
                moved = true;
            }
        }
        return false;
    }

    public boolean touchUp(int sx, int sy, int p, int button) {
        if (p != pointer) {
            return false;
        }
        pointer = -1;
        final boolean tap = !moved && !onHome && System.currentTimeMillis() - downAt < 350L;
        final int tx = downX;
        final int ty = downY;
        final int b = button;
        final int pp = p;
        try {
            final Touchpad tp = pad();
            // let the stage deliver this touchUp to the joystick first (knob reset), then send it home
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    try {
                        if (tp != null) {
                            tp.setPosition(homeX, homeY);
                        }
                        if (tap && world != null) {
                            world.touchDown(tx, ty, pp, b);
                            world.touchUp(tx, ty, pp, b);
                        }
                    } catch (Throwable e) {
                        // ignore
                    }
                }
            });
        } catch (Throwable e) {
            // ignore
        }
        return false;
    }

    public boolean keyDown(int k) {
        return false;
    }

    public boolean keyUp(int k) {
        return false;
    }

    public boolean keyTyped(char c) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(float a, float b) {
        return false;
    }
}

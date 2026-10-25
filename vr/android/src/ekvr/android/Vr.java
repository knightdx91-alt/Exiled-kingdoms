package ekvr.android;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.fdgames.ek.android.MainActivity;

/**
 * Entry point from the patched MainActivity.onCreate (tools/vr/patch_mainactivity.py):
 *   initialize(game, config)  ->  initialize(Vr.wrap(this, game, config), config)
 * The whole libGDX Android backend stays (input, audio, files, saves, network, the MP lobby);
 * only the game's render callback is wrapped so each frame goes to the headset in 3D.
 */
public final class Vr {
    public static com.badlogic.gdx.a wrap(MainActivity activity, com.badlogic.gdx.a game, AndroidApplicationConfiguration config) {
        config.useGL30 = true;          // OpenXR on Quest needs an OpenGL ES 3 context
        config.r = config.g = config.b = config.a = 8;
        config.depth = 16;
        config.useImmersiveMode = true;
        ExitLog.install(activity);      // record a crash's stack trace (shown in VR on the next start)
        return new VrListener(activity, game);
    }
}

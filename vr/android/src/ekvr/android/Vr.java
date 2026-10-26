package ekvr.android;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.fdgames.ek.android.MainActivity;

/**
 * Entry point from the patched MainActivity.onCreate (tools/vr/patch_mainactivity.py):
 *   new ExiledKingdoms(this)  ->  new VrEK(this)        (the game, with VR around its render)
 *   initialize(game, config)  ->  initialize(Vr.wrap(this, game, config), config)
 * wrap only sets up the config and returns the same game object: Gdx.app's listener must be
 * the ExiledKingdoms instance itself (QUEST_VR_SPEC.md §2). The whole libGDX Android backend
 * stays (input, audio, files, saves, network, the MP lobby).
 */
public final class Vr {
    public static com.badlogic.gdx.a wrap(MainActivity activity, com.badlogic.gdx.a game, AndroidApplicationConfiguration config) {
        config.useGL30 = true;          // OpenXR on Quest needs an OpenGL ES 3 context
        config.r = config.g = config.b = config.a = 8;
        config.depth = 16;
        config.useImmersiveMode = true;
        ExitLog.install(activity);      // record a crash's stack trace (shown in VR on the next start)
        return game;
    }
}

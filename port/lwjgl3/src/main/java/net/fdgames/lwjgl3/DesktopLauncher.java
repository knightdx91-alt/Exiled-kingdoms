package net.fdgames.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import net.fdgames.ek.ExiledKingdoms;

/**
 * Desktop entry point — the fast-feedback target for validating the
 * de-obfuscation remap of the recovered core before building the HTML target.
 *
 * ExiledKingdoms currently expects a platform resolver (IPlatformResolver) for
 * IAP / Google Play Games / analytics. A desktop no-op resolver must be wired
 * here in step 2 once the core compiles.
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Exiled Kingdoms");
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new ExiledKingdoms(/* platform resolver: TODO */), config);
    }
}

package net.fdgames.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import net.fdgames.ek.ExiledKingdoms;

/**
 * HTML5 (GWT) entry point. Built only after the desktop target runs clean.
 * GWT forbids reflection, threads, and most java.io — the recovered code's
 * Serializer/reflection usage (see deobf: libgdx-reflect(relocated)) will need
 * GWT-safe replacements here.
 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(1280, 720);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new ExiledKingdoms(/* web platform resolver: TODO */);
    }
}

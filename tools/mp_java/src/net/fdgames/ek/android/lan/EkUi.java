package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * UI scaling fixes for high-resolution screens (deobf/UI_SCALING_SPEC.md). The game lays windows out
 * with E = screen height / 720, but libGDX's CheckBox draws its image with Scaling.none, i.e. at the
 * drawable's min size (the 20x20 png) whatever cell size the window asks for.
 */
public final class EkUi {
    private EkUi() {}

    /** The game's own window scale: screen height / 720, never below 1. */
    static float scale() {
        try {
            float s = Gdx.graphics.getHeight() / 720f;
            return s < 1f ? 1f : s;
        } catch (Throwable e) {
            return 1f;
        }
    }

    /** A drawable that reports its source size x the current UI scale (fold/unfold safe). */
    static final class Scaled extends TextureRegionDrawable {
        private final float w;
        private final float h;

        Scaled(TextureRegionDrawable src) {
            super(src);
            w = src.getMinWidth();
            h = src.getMinHeight();
        }

        @Override
        public float getMinWidth() {
            return w * scale();
        }

        @Override
        public float getMinHeight() {
            return h * scale();
        }
    }

    private static Drawable wrap(Drawable d) {
        return d instanceof TextureRegionDrawable && !(d instanceof Scaled) ? new Scaled((TextureRegionDrawable) d) : d;
    }

    /** Hooked right after GameAssets builds the shared checkbox style (r0). */
    public static void scaleCheckboxes(CheckBox.CheckBoxStyle st) {
        try {
            if (st != null) {
                st.checkboxOff = wrap(st.checkboxOff);
                st.checkboxOn = wrap(st.checkboxOn);
            }
        } catch (Throwable e) {
            // leave the vanilla style
        }
    }
}

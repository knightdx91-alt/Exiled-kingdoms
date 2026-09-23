package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

    /** The uniform scale most windows use: min(width / 1280, height / 720), never below 1. */
    static float uniformScale() {
        try {
            float s = Math.min(Gdx.graphics.getWidth() / 1280f, Gdx.graphics.getHeight() / 720f);
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

    // ---- save slots (SlotDescriptionTable e/a/d/e1/w): name over "class (level) - time" -----------------

    /**
     * Vanilla wraps both lines but never gives them a width in a filled slot, so a wrapped label can't
     * size itself: long names spill onto extra lines and the two lines draw on top of each other.
     * Give both the slot's text width (380 slot - 72 portrait - margins), one line each, a long line
     * shrinks to fit (down to 70%), anything still longer ends in "...". Empty/corrupt slots untouched.
     */
    public static void fixSlot(Label name, Label desc, float l) {
        try {
            if (name == null || desc == null) {
                return;
            }
            desc.setWrap(false);
            if (desc.getPrefWidth() <= 0f) {
                desc.setWrap(true); // empty or incompatible slot: vanilla layout (has a width)
                return;
            }
            float avail = 285f * l;
            fit(name, avail);
            fit(desc, avail);
        } catch (Throwable e) {
            // keep vanilla
        }
    }

    private static void fit(Label lb, float avail) {
        lb.setWrap(false);
        float base = lb.getFontScaleX();
        float w = lb.getPrefWidth();
        if (w > avail && w > 0f) {
            lb.setFontScale(base * Math.max(0.7f, avail / w));
        }
        lb.setEllipsis(true);
        lb.setAlignment(8); // Align.left
        if (lb.getParent() instanceof Table) {
            Cell c = ((Table) lb.getParent()).getCell(lb);
            if (c != null) {
                c.width(avail).left();
            }
        }
        lb.invalidateHierarchy();
    }

    /**
     * Details window name column (240 x height/720 wide, StatsDetailWindow.h): one line, shrunk to fit
     * (not below 60%), "..." beyond that. Wrapping it (v40) made the row shorter than the wrapped
     * text, so it ran into the rows below.
     */
    public static void wrapLabel(Object o) {
        try {
            if (o instanceof Label) {
                Label lb = (Label) o;
                lb.setWrap(false);
                float avail = 240f * (Gdx.graphics.getHeight() / 720f);
                float base = lb.getFontScaleX();
                float w = lb.getPrefWidth();
                if (w > avail && w > 0f) {
                    lb.setFontScale(base * Math.max(0.6f, avail / w));
                }
                lb.setEllipsis(true);
            }
        } catch (Throwable e) {
            // keep vanilla
        }
    }

    /**
     * Details window value column (480 x height/720): a wrapped sentence. The table asked for its height
     * while the label's width was still 0, so the row came out shorter than the wrapped text and the
     * extra lines ran over the rows below. Give it its column width first.
     */
    public static void sizeWrapped(Object o) {
        try {
            if (o instanceof Label) {
                Label lb = (Label) o;
                lb.setWrap(true);
                lb.setWidth(480f * (Gdx.graphics.getHeight() / 720f));
                lb.invalidateHierarchy();
            }
        } catch (Throwable e) {
            // keep vanilla
        }
    }

    /**
     * Right after the value label's cell got its 480xS width: measure the wrapped sentence at that width
     * and pin the row's height to it (v43's early setWidth alone wasn't picked up - owner's screenshot
     * still showed every row one line tall, wrapped lines landing on the next row).
     */
    public static void fitCell(Object o) {
        try {
            if (!(o instanceof Cell)) {
                return;
            }
            Cell c = (Cell) o;
            if (c.getActor() instanceof Label) {
                Label lb = (Label) c.getActor();
                lb.setWrap(true);
                lb.setWidth(480f * (Gdx.graphics.getHeight() / 720f));
                lb.invalidateHierarchy();
                float h = lb.getPrefHeight();
                if (h > 0f) {
                    c.height(h);
                }
            }
        } catch (Throwable e) {
            // keep vanilla
        }
    }
}

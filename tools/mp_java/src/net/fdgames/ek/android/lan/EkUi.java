package net.fdgames.ek.android.lan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
     * Break a label's text into explicit lines that fit {@code width}, using the label's own font at its
     * own font scale, and turn libGDX wrapping off. With explicit lines the height the table reserves and
     * the lines that get drawn come from the same text, so rows can't run into each other. (v43-v46 kept
     * libGDX wrapping and only corrected the width/height it measured; on the owner's Fold the drawn
     * wrap still differed from the measured one - two sentences of identical width, one wrapped and one
     * not - so the rows stayed one line tall while the text drew two.)
     */
    static void wrapTo(Label lb, float width) {
        if (lb == null || width <= 0f || lb.getText() == null || lb.getStyle() == null) {
            return;
        }
        BitmapFont font = lb.getStyle().font;
        if (font == null) {
            return;
        }
        String text = lb.getText().toString();
        BitmapFont.BitmapFontData d = font.getData();
        float osx = d.scaleX;
        float osy = d.scaleY;
        float sx = lb.getFontScaleX();
        float sy = lb.getFontScaleY();
        if (sx != 1f || sy != 1f) {
            d.setScale(sx, sy);
        }
        StringBuilder out = new StringBuilder();
        try {
            GlyphLayout gl = new GlyphLayout();
            float max = width * 0.96f; // a little slack for kerning/markup rounding
            String[] paras = text.split("\n", -1);
            for (int p = 0; p < paras.length; p++) {
                String[] words = paras[p].split(" ", -1);
                String line = "";
                for (int i = 0; i < words.length; i++) {
                    String cand = i == 0 ? words[i] : line + " " + words[i];
                    gl.setText(font, cand);
                    if (i > 0 && gl.width > max && line.trim().length() > 0) {
                        out.append(line).append('\n');
                        line = words[i];
                    } else {
                        line = cand;
                    }
                }
                out.append(line);
                if (p < paras.length - 1) {
                    out.append('\n');
                }
            }
        } finally {
            d.setScale(osx, osy);
        }
        lb.setWrap(false);
        lb.setText(out.toString());
        lb.invalidateHierarchy();
    }

    /**
     * Details window value column (StatsDetailWindow h0, cell width 480 x h): called right after the
     * value label's cell got its width; breaks the sentence into lines of that width.
     */
    public static void prewrap(Object o) {
        try {
            if (!(o instanceof Cell)) {
                return;
            }
            Cell c = (Cell) o;
            if (c.getActor() instanceof Label && c.getPrefWidthValue() != null) {
                wrapTo((Label) c.getActor(), c.getPrefWidthValue().get(c.getActor()));
            }
        } catch (Throwable e) {
            // keep vanilla
        }
    }

    /** SimpleDialog (e/a/d/l1) scale: screen height / 720, as l1.c. */
    private static float dlgScale() {
        return Gdx.graphics.getHeight() / 720f;
    }

    /** Route chooser box width: 660 x c, but never wider than 94% of the screen. */
    private static float dlgWidth() {
        return Math.min(660f * dlgScale(), Gdx.graphics.getWidth() * 0.94f);
    }

    /**
     * Summon route chooser (e/a/d/e/eksp extends SimpleDialog l1). l1 reports a fixed preferred size
     * (430c wide, height from a line count) and Dialog.show() packs to it, which undid v45's resize and
     * left the widened text and buttons clipped on both sides. eksp now overrides getPrefWidth/Height
     * with these; growDialog fits the text and the three buttons inside that width.
     */
    public static void growDialog(Object o) {
        try {
            if (!(o instanceof Dialog)) {
                return;
            }
            Dialog d = (Dialog) o;
            float c = dlgScale();
            float w = dlgWidth();
            float textW = w - 90f * c;
            com.badlogic.gdx.utils.a cells = d.getContentTable().getCells();
            for (int i = 0; i < cells.c; i++) {
                Cell cell = (Cell) cells.get(i);
                cell.width(textW);
                if (cell.getActor() instanceof Label) {
                    wrapTo((Label) cell.getActor(), textW);
                }
            }
            com.badlogic.gdx.utils.a buttons = d.getButtonTable().getCells();
            int n = Math.max(1, buttons.c);
            // l1's button defaults space them 40c apart
            float bw = Math.min(170f * c, (w - 80f * c - 40f * c * (n - 1)) / n);
            for (int i = 0; i < buttons.c; i++) {
                ((Cell) buttons.get(i)).width(bw);
            }
        } catch (Throwable e) {
            // keep vanilla
        }
    }

    public static float dialogPrefWidth(Object o) {
        try {
            return dlgWidth();
        } catch (Throwable e) {
            return 700f;
        }
    }

    public static float dialogPrefHeight(Object o) {
        try {
            Dialog d = (Dialog) o;
            float c = dlgScale();
            float h = d.getContentTable().getPrefHeight() + d.getButtonTable().getPrefHeight() + d.getPadY() + 30f * c;
            return Math.min(h, Gdx.graphics.getHeight() * 0.94f);
        } catch (Throwable e) {
            return 240f * dlgScale();
        }
    }
}

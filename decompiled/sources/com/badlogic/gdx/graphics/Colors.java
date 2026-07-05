package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.y;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Colors {
    private static final y<String, Color> map = new y<>();

    static {
        reset();
    }

    private Colors() {
    }

    public static Color get(String str) {
        return map.d(str);
    }

    public static y<String, Color> getColors() {
        return map;
    }

    public static Color put(String str, Color color) {
        return map.j(str, color);
    }

    public static void reset() {
        y<String, Color> yVar = map;
        yVar.clear();
        yVar.j("CLEAR", Color.CLEAR);
        yVar.j("BLACK", Color.BLACK);
        yVar.j("WHITE", Color.WHITE);
        yVar.j("LIGHT_GRAY", Color.LIGHT_GRAY);
        yVar.j("GRAY", Color.GRAY);
        yVar.j("DARK_GRAY", Color.DARK_GRAY);
        yVar.j("BLUE", Color.BLUE);
        yVar.j("NAVY", Color.NAVY);
        yVar.j("ROYAL", Color.ROYAL);
        yVar.j("SLATE", Color.SLATE);
        yVar.j("SKY", Color.SKY);
        yVar.j("CYAN", Color.CYAN);
        yVar.j("TEAL", Color.TEAL);
        yVar.j("GREEN", Color.GREEN);
        yVar.j("CHARTREUSE", Color.CHARTREUSE);
        yVar.j("LIME", Color.LIME);
        yVar.j("FOREST", Color.FOREST);
        yVar.j("OLIVE", Color.OLIVE);
        yVar.j("YELLOW", Color.YELLOW);
        yVar.j("GOLD", Color.GOLD);
        yVar.j("GOLDENROD", Color.GOLDENROD);
        yVar.j("ORANGE", Color.ORANGE);
        yVar.j("BROWN", Color.BROWN);
        yVar.j("TAN", Color.TAN);
        yVar.j("FIREBRICK", Color.FIREBRICK);
        yVar.j("RED", Color.RED);
        yVar.j("SCARLET", Color.SCARLET);
        yVar.j("CORAL", Color.CORAL);
        yVar.j("SALMON", Color.SALMON);
        yVar.j("PINK", Color.PINK);
        yVar.j("MAGENTA", Color.MAGENTA);
        yVar.j("PURPLE", Color.PURPLE);
        yVar.j("VIOLET", Color.VIOLET);
        yVar.j("MAROON", Color.MAROON);
    }
}

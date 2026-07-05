package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.Gdx;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class UIUtils {
    public static boolean isAndroid;
    public static boolean isIos;
    public static boolean isLinux;
    public static boolean isMac;
    public static boolean isWindows;

    static {
        boolean zContains = System.getProperty("java.runtime.name").contains("Android");
        isAndroid = zContains;
        boolean z2 = false;
        isMac = !zContains && System.getProperty("os.name").contains("Mac");
        isWindows = !isAndroid && System.getProperty("os.name").contains("Windows");
        boolean z3 = !isAndroid && System.getProperty("os.name").contains("Linux");
        isLinux = z3;
        if (!isAndroid && !isWindows && !z3 && !isMac) {
            z2 = true;
        }
        isIos = z2;
    }

    private UIUtils() {
    }

    public static boolean alt(int i2) {
        return i2 == 57 || i2 == 58;
    }

    public static boolean ctrl() {
        return isMac ? Gdx.input.isKeyPressed(63) : Gdx.input.isKeyPressed(129) || Gdx.input.isKeyPressed(130);
    }

    public static boolean left(int i2) {
        return i2 == 0;
    }

    public static boolean middle(int i2) {
        return i2 == 2;
    }

    public static boolean right(int i2) {
        return i2 == 1;
    }

    public static boolean shift(int i2) {
        return i2 == 59 || i2 == 60;
    }

    public static boolean alt() {
        return Gdx.input.isKeyPressed(57) || Gdx.input.isKeyPressed(58);
    }

    public static boolean left() {
        return Gdx.input.isButtonPressed(0);
    }

    public static boolean middle() {
        return Gdx.input.isButtonPressed(2);
    }

    public static boolean right() {
        return Gdx.input.isButtonPressed(1);
    }

    public static boolean shift() {
        return Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
    }

    public static boolean ctrl(int i2) {
        return isMac ? i2 == 63 : i2 == 129 || i2 == 130;
    }
}

package com.badlogic.gdx.controllers;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class PovDirection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PovDirection f1615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PovDirection f1616b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final PovDirection f1617c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PovDirection f1618d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final PovDirection f1619e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final PovDirection f1620f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final PovDirection f1621g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final PovDirection f1622h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final PovDirection f1623i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final /* synthetic */ PovDirection[] f1624j;

    static {
        PovDirection povDirection = new PovDirection("center", 0);
        f1615a = povDirection;
        PovDirection povDirection2 = new PovDirection("north", 1);
        f1616b = povDirection2;
        PovDirection povDirection3 = new PovDirection("south", 2);
        f1617c = povDirection3;
        PovDirection povDirection4 = new PovDirection("east", 3);
        f1618d = povDirection4;
        PovDirection povDirection5 = new PovDirection("west", 4);
        f1619e = povDirection5;
        PovDirection povDirection6 = new PovDirection("northEast", 5);
        f1620f = povDirection6;
        PovDirection povDirection7 = new PovDirection("southEast", 6);
        f1621g = povDirection7;
        PovDirection povDirection8 = new PovDirection("northWest", 7);
        f1622h = povDirection8;
        PovDirection povDirection9 = new PovDirection("southWest", 8);
        f1623i = povDirection9;
        f1624j = new PovDirection[]{povDirection, povDirection2, povDirection3, povDirection4, povDirection5, povDirection6, povDirection7, povDirection8, povDirection9};
    }

    private PovDirection() {
        throw null;
    }

    public static PovDirection valueOf(String str) {
        return (PovDirection) Enum.valueOf(PovDirection.class, str);
    }

    public static PovDirection[] values() {
        return (PovDirection[]) f1624j.clone();
    }
}

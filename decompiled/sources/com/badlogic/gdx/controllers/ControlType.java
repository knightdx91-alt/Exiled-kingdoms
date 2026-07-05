package com.badlogic.gdx.controllers;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ControlType {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final /* synthetic */ ControlType[] f1611a = {new ControlType("button", 0), new ControlType("axis", 1), new ControlType("slider", 2), new ControlType("pov", 3), new ControlType("accelerometer", 4)};

    /* JADX INFO: Fake field, exist only in values array */
    ControlType EF5;

    private ControlType() {
        throw null;
    }

    public static ControlType valueOf(String str) {
        return (ControlType) Enum.valueOf(ControlType.class, str);
    }

    public static ControlType[] values() {
        return (ControlType[]) f1611a.clone();
    }
}

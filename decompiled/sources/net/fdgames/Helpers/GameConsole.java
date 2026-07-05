package net.fdgames.Helpers;

import java.util.Iterator;
import java.util.LinkedList;
import k0.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GameConsole {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static GameConsole f3219a;
    public Boolean initialized = Boolean.FALSE;
    private String currentText = "";
    private float TextAge = 0.0f;
    private float WaitAge = 0.0f;
    private LinkedList<String> messages = new LinkedList<>();

    private GameConsole() {
    }

    public static void a(String str) {
        if (!e().initialized.booleanValue() || str.equals(e().currentText)) {
            return;
        }
        Iterator<String> it = e().messages.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return;
            }
        }
        if (e().messages.size() == 0 && e().currentText.equals("")) {
            e().currentText = str;
        } else {
            e().messages.add(str);
        }
    }

    public static float b() {
        if (e().currentText.equals("") || a.l().f2288o) {
            return 0.0f;
        }
        if (e().TextAge < 1.0500001f) {
            return e().TextAge / 1.0500001f;
        }
        if (e().TextAge > 2.45f) {
            return 1.0f - (e().TextAge - 2.45f);
        }
        return 1.0f;
    }

    public static String c() {
        return e().currentText;
    }

    public static void d() {
        e().messages.clear();
        e().currentText = "";
    }

    public static GameConsole e() {
        if (f3219a == null) {
            f3219a = new GameConsole();
        }
        return f3219a;
    }

    public static void f(float f2) {
        if (!e().currentText.equals("")) {
            e().TextAge += f2;
            if (e().TextAge > 3.5f) {
                e().TextAge = 0.0f;
                e().currentText = "";
            }
        } else if (e().messages.size() > 0) {
            e().WaitAge += f2;
        }
        if (e().WaitAge > 0.3f) {
            e().WaitAge = 0.0f;
            e().currentText = e().messages.getFirst();
            e().messages.removeFirst();
        }
    }
}

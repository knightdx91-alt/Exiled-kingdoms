package net.fdgames.GameWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.ek.android.lan.LanGameBridge;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class MessageRouter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<Message> f3415a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Message f3416b;

    public static void a(String str, int i2, int i3, String str2, float f2, DamageData damageData) {
        if (str.startsWith("UN")) {
            Iterator<Message> it = f3415a.iterator();
            while (it.hasNext()) {
                Message next = it.next();
                if (next.receiver == i3 && next.name.equals(str)) {
                    it.remove();
                }
            }
        }
        if (f2 != 0.0f) {
            Message message = new Message();
            message.name = str;
            message.sender = i2;
            message.receiver = i3;
            message.data = str2;
            message.deliveryTime = GameLevel.b() + f2;
            message.damageData = damageData;
            f3415a.add(message);
            Collections.sort(f3415a);
        } else if (GameLevel.h(i3) != null) {
            GameLevel.h(i3).v(str, i2, str2, damageData);
        }
        LanGameBridge.mirrorCombatAction(str, i2, i3, str2, f2);
    }

    public static ArrayList<Message> b() {
        return f3415a;
    }

    public static void c() {
        Iterator<Message> it = f3415a.iterator();
        while (it.hasNext()) {
            if (it.next().receiver > 1) {
                it.remove();
            }
        }
    }

    public static void d(ArrayList<Message> arrayList) {
        f3415a = arrayList;
    }

    public static void e() {
        while (f3415a.size() > 0 && f3415a.get(0).deliveryTime <= GameLevel.b()) {
            f3416b = f3415a.get(0);
            f3415a.remove(0);
            Collections.sort(f3415a);
            MapObject mapObjectH = GameLevel.h(f3416b.receiver);
            if (mapObjectH != null) {
                Message message = f3416b;
                mapObjectH.v(message.name, message.sender, message.data, message.damageData);
            }
        }
    }
}

package net.fdgames.GameWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MessageRouter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<Message> f3194a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Message f3195b;

    public static void a(String str, int i2, int i3, String str2, float f2, DamageData damageData) {
        if (str.startsWith("UN")) {
            Iterator<Message> it = f3194a.iterator();
            while (it.hasNext()) {
                Message next = it.next();
                if (next.receiver == i3 && next.name.equals(str)) {
                    it.remove();
                }
            }
        }
        if (f2 == 0.0f) {
            if (GameLevel.h(i3) != null) {
                GameLevel.h(i3).v(str, i2, str2, damageData);
                return;
            }
            return;
        }
        Message message = new Message();
        message.name = str;
        message.sender = i2;
        message.receiver = i3;
        message.data = str2;
        message.deliveryTime = GameLevel.b() + f2;
        message.damageData = damageData;
        f3194a.add(message);
        Collections.sort(f3194a);
    }

    public static ArrayList<Message> b() {
        return f3194a;
    }

    public static void c() {
        Iterator<Message> it = f3194a.iterator();
        while (it.hasNext()) {
            if (it.next().receiver > 1) {
                it.remove();
            }
        }
    }

    public static void d(ArrayList<Message> arrayList) {
        f3194a = arrayList;
    }

    public static void e() {
        while (f3194a.size() > 0 && f3194a.get(0).deliveryTime <= GameLevel.b()) {
            f3195b = f3194a.get(0);
            f3194a.remove(0);
            Collections.sort(f3194a);
            MapObject mapObjectH = GameLevel.h(f3195b.receiver);
            if (mapObjectH != null) {
                Message message = f3195b;
                mapObjectH.v(message.name, message.sender, message.data, message.damageData);
            }
        }
    }
}

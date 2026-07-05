package net.fdgames.GameWorld;

import java.util.Iterator;
import java.util.Locale;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.Rules;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DynamicEvent {
    public String event_id;
    public Float expirationDate;
    public Float generationDate;
    public int knowledge;
    public String location_ID;
    private int lootseed;
    public String name;

    public DynamicEvent() {
    }

    public static boolean a(DynamicEvent dynamicEvent) {
        String str;
        if (!b(dynamicEvent.location_ID) && (str = dynamicEvent.event_id) != null && !str.equals("")) {
            Iterator<DynamicEvent> it = GameData.v().t().iterator();
            while (it.hasNext()) {
                String str2 = it.next().event_id;
                Locale locale = Locale.ENGLISH;
                if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean b(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        Iterator<DynamicEvent> it = GameData.v().t().iterator();
        while (it.hasNext()) {
            String str2 = it.next().location_ID;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                return true;
            }
        }
        return false;
    }

    public static DynamicEvent c(String str) {
        for (DynamicEvent dynamicEvent : GameData.v().t()) {
            String str2 = dynamicEvent.location_ID;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                return dynamicEvent;
            }
        }
        return null;
    }

    public final int d() {
        int i2 = this.lootseed;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public final String e() {
        WorldEvents worldEvents = GameWorld.f3193g;
        String str = this.event_id;
        worldEvents.getClass();
        WorldEvent worldEventA = WorldEvents.a(str);
        return "[BLACK]" + FDUtils.e(worldEventA.b().replace("{name}", "[BLUE]" + this.name + "[]").replace("{spawn}", "[BLUE]" + Rules.i(worldEventA.spawn_id).a() + "[]").replace("{location}", "[BLUE]" + WorldEvents.b(this.knowledge, this.location_ID) + "[]")) + "[]";
    }

    public DynamicEvent(int i2) {
        GameWorld.f3193g.getClass();
        WorldEvent worldEventC = WorldEvents.c();
        this.event_id = worldEventC.id;
        this.location_ID = FDUtils.o(worldEventC.locations);
        this.name = WorldRandomNames.b(worldEventC.nameTable);
        this.lootseed = FDUtils.b(1, 999);
        float fU = GameData.v().u();
        this.generationDate = Float.valueOf(fU);
        this.expirationDate = Float.valueOf(fU + (FDUtils.b(4, 8) * 1080));
        this.knowledge = 0;
    }
}

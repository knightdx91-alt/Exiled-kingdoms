package net.fdgames.GameEntities.Helpers;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.Serializer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AreasVisited {
    private ArrayList<AreaVisited> areasVisited = new ArrayList<>();

    public final void a() {
        Gdx.files.local(Serializer.e(GameData.v().slot)).deleteDirectory();
        Iterator<AreaVisited> it = this.areasVisited.iterator();
        while (it.hasNext()) {
            Serializer.g(GameData.v().slot, it.next().area_id);
            it.remove();
        }
    }

    public final void b() {
        Iterator<AreaVisited> it = this.areasVisited.iterator();
        while (it.hasNext()) {
            AreaVisited next = it.next();
            if (GameLevel.b() >= next.exitTime + 540.0f) {
                Serializer.g(GameData.v().slot, next.area_id);
                it.remove();
            }
        }
    }

    public final void c(String str) {
        Iterator<AreaVisited> it = this.areasVisited.iterator();
        while (it.hasNext()) {
            if (it.next().area_id.equals(str)) {
                return;
            }
        }
        ArrayList<AreaVisited> arrayList = this.areasVisited;
        float fB = GameLevel.b();
        AreaVisited areaVisited = new AreaVisited();
        areaVisited.exitTime = fB;
        areaVisited.area_id = str;
        arrayList.add(areaVisited);
    }

    public final boolean d(String str) {
        for (AreaVisited areaVisited : this.areasVisited) {
            if (areaVisited.area_id.equals(str) && GameLevel.b() >= areaVisited.exitTime + 540.0f) {
                return true;
            }
        }
        return false;
    }

    public final boolean e(String str) {
        Iterator<AreaVisited> it = this.areasVisited.iterator();
        while (it.hasNext()) {
            if (it.next().area_id.equals(str)) {
                return true;
            }
        }
        return false;
    }
}

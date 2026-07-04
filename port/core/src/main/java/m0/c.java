package m0;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.lang.reflect.Array;
import java.util.ArrayList;
import net.fdgames.GameEntities.Helpers.MapEffect;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: compiled from: MapStaticEffects.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<MapEffect> f2441a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MapEffect[][] f2442b = (MapEffect[][]) Array.newInstance((Class<?>) MapEffect.class, 96, 96);

    public final void a(int i2, int i3, String str) {
        int iB = FDUtils.b(1, 100);
        String str2 = (!str.equals("lava_edge") || iB >= 50) ? str.equals("lava") ? iB < 5 ? "flames" : iB < 25 ? "medium_flames" : "small_flames" : "" : "small_flames";
        if (str.equals("lava2")) {
            str2 = iB < 10 ? "flames_intense" : iB < 35 ? "medium_flames_intense" : "small_flames_intense";
        }
        if (str.equals("gas_weak")) {
            str2 = "gas_weak";
        }
        String str3 = (!str2.equals("") || str.equals("")) ? str2 : str;
        if (str3.equals("")) {
            return;
        }
        MapEffect mapEffect = new MapEffect(str3, i2 * 32, i3 * 32, 0, true);
        mapEffect.d();
        this.f2441a.add(mapEffect);
        this.f2442b[i2][i3] = mapEffect;
    }

    public final void b(int i2, int i3, SpriteBatch spriteBatch) {
        spriteBatch.draw(this.f2442b[i2][i3].c(), r2.a() - 38, r2.b() - 8);
    }

    public final boolean c(int i2, int i3) {
        return this.f2442b[i2][i3] != null;
    }

    public final void d(float f2) {
        float fU = GameData.v().u();
        int i2 = 0;
        while (true) {
            ArrayList<MapEffect> arrayList = this.f2441a;
            if (i2 >= arrayList.size()) {
                return;
            }
            arrayList.get(i2).g(f2, fU);
            i2++;
        }
    }
}

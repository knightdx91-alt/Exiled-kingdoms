package net.fdgames.Rules;

import com.pairip.licensecheck.wYh.JDxjJEGD;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.assets.GameAssets;
import w0.a;
import y0.b;
import z0.ow.DkgvDLHsdXPkn;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class AreaEffects {
    private static void a(int i2, int i3, int i4, String str, float f2, float f3) {
        if (b.P().y(i2 / 32, i3 / 32)) {
            return;
        }
        MapEffectEntity mapEffectEntity = new MapEffectEntity((float) ((Math.random() * ((double) (f3 - f2))) + ((double) f2)), i2, i3, i4, str);
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        GameLevelData.e(mapEffectEntity);
    }

    public static void b(int i2, int i3, int i4) {
        a(i2, i3, i4, "gas_2", 0.1f, 0.3f);
        int i5 = i2 + 32;
        a(i5, i3, i4, "gas_2", 0.3f, 0.6f);
        int i6 = i2 - 32;
        a(i6, i3, i4, "gas_2", 0.3f, 0.6f);
        int i7 = i3 + 32;
        a(i2, i7, i4, "gas_2", 0.3f, 0.6f);
        int i8 = i3 - 32;
        a(i2, i8, i4, "gas_2", 0.3f, 0.6f);
        a(i5, i7, i4, "gas_2", 0.6f, 1.0f);
        a(i6, i8, i4, "gas_2", 0.6f, 1.0f);
        a(i6, i7, i4, "gas_2", 0.6f, 1.0f);
        a(i5, i8, i4, "gas_2", 0.6f, 1.0f);
    }

    public static void c(int i2, int i3, int i4, int i5) {
        String str = i5 == 2 ? "combustion_weak_2" : DkgvDLHsdXPkn.wXpQat;
        if (i5 == 3) {
            str = JDxjJEGD.iIzowAHAmf;
        }
        a.l().d(0.8f, i2, i3, 0, "fire");
        int i6 = i2 + 32;
        String str2 = str;
        a(i6, i3, i4, str2, 0.0f, 0.0f);
        int i7 = i2 - 32;
        a(i7, i3, i4, str2, 0.0f, 0.0f);
        int i8 = i3 + 32;
        a(i2, i8, i4, str2, 0.0f, 0.0f);
        int i9 = i3 - 32;
        a(i2, i9, i4, str2, 0.0f, 0.0f);
        a(i6, i8, i4, str2, 0.0f, 0.0f);
        a(i7, i9, i4, str2, 0.0f, 0.0f);
        a(i7, i8, i4, str2, 0.0f, 0.0f);
        a(i6, i9, i4, str2, 0.0f, 0.0f);
        int i10 = i2 + 64;
        a(i10, i8, i4, str2, 0.0f, 0.0f);
        a(i10, i3, i4, str2, 0.0f, 0.0f);
        a(i10, i9, i4, str2, 0.0f, 0.0f);
        int i11 = i2 - 64;
        a(i11, i8, i4, str2, 0.0f, 0.0f);
        a(i11, i3, i4, str2, 0.0f, 0.0f);
        a(i11, i9, i4, str2, 0.0f, 0.0f);
        int i12 = i3 + 64;
        a(i7, i12, i4, str2, 0.0f, 0.0f);
        a(i2, i12, i4, str2, 0.0f, 0.0f);
        a(i6, i12, i4, str2, 0.0f, 0.0f);
        int i13 = i3 - 64;
        a(i7, i13, i4, str2, 0.0f, 0.0f);
        a(i2, i13, i4, str2, 0.0f, 0.0f);
        a(i6, i13, i4, str2, 0.0f, 0.0f);
        GameAssets.o("explo");
    }

    public static void d(int i2, int i3, int i4, String str) {
        a.l().d(1.0f, i2, i3, 0, "fire");
        a(i2, i3, i4, str, 0.0f, 0.25f);
        int i5 = i2 + 32;
        a(i5, i3, i4, str, 0.0f, 0.25f);
        int i6 = i2 - 32;
        a(i6, i3, i4, str, 0.0f, 0.25f);
        int i7 = i3 + 32;
        a(i2, i7, i4, str, 0.0f, 0.25f);
        int i8 = i3 - 32;
        a(i2, i8, i4, str, 0.0f, 0.25f);
        a(i5, i7, i4, str, 0.3f, 0.6f);
        a(i6, i8, i4, str, 0.3f, 0.6f);
        a(i6, i7, i4, str, 0.3f, 0.6f);
        a(i5, i8, i4, str, 0.3f, 0.6f);
        GameAssets.o("explo");
    }

    public static void e(int i2, int i3, int i4, int i5) {
        String str = i5 == 2 ? "flames_faith_weak2" : "flames_faith_weak1";
        if (i5 == 3) {
            str = "flames_faith_weak3";
        }
        a.l().d(6.0f, i2, i3, 0, "fire");
        String str2 = str;
        a(i2, i3, i4, str2, 0.0f, 0.0f);
        int i6 = i2 + 32;
        a(i6, i3, i4, str2, 0.0f, 0.0f);
        int i7 = i2 - 32;
        a(i7, i3, i4, str2, 0.0f, 0.0f);
        int i8 = i3 + 32;
        a(i2, i8, i4, str2, 0.0f, 0.0f);
        int i9 = i3 - 32;
        a(i2, i9, i4, str2, 0.0f, 0.0f);
        a(i6, i8, i4, str2, 0.0f, 0.0f);
        a(i7, i9, i4, str2, 0.0f, 0.0f);
        a(i7, i8, i4, str2, 0.0f, 0.0f);
        a(i6, i9, i4, str2, 0.0f, 0.0f);
        int i10 = i2 + 64;
        a(i10, i8, i4, str2, 0.0f, 0.0f);
        a(i10, i3, i4, str2, 0.0f, 0.0f);
        a(i10, i9, i4, str2, 0.0f, 0.0f);
        int i11 = i2 - 64;
        a(i11, i8, i4, str2, 0.0f, 0.0f);
        a(i11, i3, i4, str2, 0.0f, 0.0f);
        a(i11, i9, i4, str2, 0.0f, 0.0f);
        int i12 = i3 + 64;
        a(i7, i12, i4, str2, 0.0f, 0.0f);
        a(i2, i12, i4, str2, 0.0f, 0.0f);
        a(i6, i12, i4, str2, 0.0f, 0.0f);
        int i13 = i3 - 64;
        a(i7, i13, i4, str2, 0.0f, 0.0f);
        a(i2, i13, i4, str2, 0.0f, 0.0f);
        a(i6, i13, i4, str2, 0.0f, 0.0f);
    }

    public static void f(int i2, int i3, int i4) {
        a(i2, i3, i4, "smoke_bomb", 0.1f, 0.3f);
        int i5 = i2 + 16;
        int i6 = i3 + 16;
        a(i5, i6, i4, "smoke_bomb", 0.3f, 0.6f);
        int i7 = i2 - 16;
        a(i7, i6, i4, "smoke_bomb", 0.3f, 0.6f);
        int i8 = i3 - 16;
        a(i5, i8, i4, "smoke_bomb", 0.3f, 0.6f);
        a(i7, i8, i4, "smoke_bomb", 0.3f, 0.6f);
    }
}

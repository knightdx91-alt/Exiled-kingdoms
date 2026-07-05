package net.fdgames.GameEntities.Final;

import androidx.core.content.uvr.JNrsKSCxIEXndG;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Locale;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.assets.GameAssets;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Door extends MapObject {
    public Boolean isLeft;
    private Boolean open;
    public boolean steel;

    public Door() {
    }

    public Door(int i2, int i3, String str) {
        this.f3307x = i2 * 32;
        this.f3308y = i3 * 32;
        if (str.trim().toLowerCase(Locale.ENGLISH).equals(JNrsKSCxIEXndG.OEYgJ)) {
            this.isLeft = Boolean.TRUE;
        } else {
            this.isLeft = Boolean.FALSE;
        }
        this.open = Boolean.FALSE;
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    public final TextureRegion F() {
        return this.steel ? this.open.booleanValue() ? this.isLeft.booleanValue() ? GameAssets.M : GameAssets.L : this.isLeft.booleanValue() ? GameAssets.L : GameAssets.M : this.open.booleanValue() ? this.isLeft.booleanValue() ? GameAssets.K : GameAssets.J : this.isLeft.booleanValue() ? GameAssets.J : GameAssets.K;
    }

    public final boolean G() {
        return this.open.booleanValue();
    }

    public final void H() {
        if (this.steel) {
            GameAssets.o("open2");
        } else {
            GameAssets.o("open1");
        }
        this.open = Boolean.TRUE;
        o("CLOSE", q(), null, 4.0f, null);
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return "Door " + this.f3307x + "," + this.f3308y;
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (str.equals("CLOSE")) {
            for (MapActor mapActor : GameLevel.f3311c) {
                int i3 = this.f3307x;
                int i4 = this.f3308y;
                int i5 = mapActor.f3307x;
                int i6 = mapActor.f3308y;
                int i7 = b.f4007k0;
                int i8 = i3 - i5;
                int i9 = i4 - i6;
                if (Math.sqrt((i9 * i9) + (i8 * i8)) < 96.0d) {
                    m(3.0f, q(), "CLOSE");
                    return;
                }
            }
            this.open = Boolean.FALSE;
            if (this.steel) {
                GameAssets.o("close2");
            } else {
                GameAssets.o("close1");
            }
        }
    }
}

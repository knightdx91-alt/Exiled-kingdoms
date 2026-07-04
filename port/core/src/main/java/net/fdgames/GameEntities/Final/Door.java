package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Locale;
import m0.b;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Door extends MapObject {
    public Boolean isLeft;
    private Boolean open;
    public boolean steel;

    public Door() {
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
        return "Door " + this.f3092x + "," + this.f3093y;
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (str.equals("CLOSE")) {
            for (MapActor mapActor : GameLevel.f3096c) {
                int i3 = this.f3092x;
                int i4 = this.f3093y;
                int i5 = mapActor.f3092x;
                int i6 = mapActor.f3093y;
                int i7 = b.f2414k0;
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

    public Door(int i2, int i3, String str) {
        this.f3092x = i2 * 32;
        this.f3093y = i3 * 32;
        if (str.trim().toLowerCase(Locale.ENGLISH).equals("l")) {
            this.isLeft = Boolean.TRUE;
        } else {
            this.isLeft = Boolean.FALSE;
        }
        this.open = Boolean.FALSE;
    }
}

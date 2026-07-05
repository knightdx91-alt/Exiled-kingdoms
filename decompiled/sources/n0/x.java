package n0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;

/* JADX INFO: compiled from: GameHUD.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class x extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ z f2918b;

    public /* synthetic */ x(z zVar, int i2) {
        this.f2917a = i2;
        this.f2918b = zVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f2917a) {
            case 0:
                if (!GameData.v().G() && !this.f2918b.f2946f.isDisabled() && ((m0.b.P().f2423h || GameData.v().F() || GameData.v().D() || m0.b.P().f2426k) && GameData.v().u() > GameData.v().lastQuickSave + 1.0f && !GameLevel.l())) {
                    GameLevel.n(true);
                    GameData.v().lastQuickSave = GameData.v().u();
                    GameConsole.a(GameString.b("GAME_QUICKSAVED", false));
                    Serializer.z(GameData.v().slot, 2);
                    GameLevel.n(false);
                }
                return true;
            case 1:
                this.f2918b.i();
                return true;
            case 2:
                this.f2918b.g();
                return true;
            case 3:
                this.f2918b.h();
                return false;
            case 4:
                z zVar = this.f2918b;
                if (zVar.f2936a != 2) {
                    zVar.J(2);
                    zVar.f2949i.a(GameData.v().CurrentLevel);
                    GameLevel.n(true);
                } else {
                    zVar.J(0);
                    k0.a.l().f2278e.zoom = 1.0f;
                    GameLevel.n(false);
                    zVar.Y();
                    zVar.W();
                }
                return false;
            case 5:
                z zVar2 = this.f2918b;
                zVar2.J(0);
                k0.a.l().f2278e.zoom = 1.0f;
                GameLevel.n(false);
                zVar2.Y();
                zVar2.W();
                return false;
            default:
                this.f2918b.G();
                return true;
        }
    }
}

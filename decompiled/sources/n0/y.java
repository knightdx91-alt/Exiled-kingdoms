package n0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: compiled from: GameHUD.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class y extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f2920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ z f2921c;

    public /* synthetic */ y(z zVar, int i2, int i3) {
        this.f2919a = i3;
        this.f2921c = zVar;
        this.f2920b = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        switch (this.f2919a) {
            case 0:
                o0.i iVar = this.f2921c.C[this.f2920b];
                o0.i.f3530t = true;
                break;
            default:
                super.enter(inputEvent, f2, f3, i2, actor);
                break;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        switch (this.f2919a) {
            case 0:
                o0.i iVar = this.f2921c.C[this.f2920b];
                o0.i.f3530t = false;
                break;
            default:
                super.exit(inputEvent, f2, f3, i2, actor);
                break;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        int i4 = this.f2920b;
        z zVar = this.f2921c;
        switch (this.f2919a) {
            case 0:
                zVar.b0(i4);
                l0.b.i();
                break;
            default:
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                if (!GameData.v().player.i0() && GameData.v().player.activables != null && GameData.v().player.activables.length > 0) {
                    zVar.E(i4);
                    break;
                }
                break;
        }
        return true;
    }
}

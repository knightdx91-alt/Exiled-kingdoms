package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: GameHUD.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class a0 extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2526a;

    public /* synthetic */ a0(int i2) {
        this.f2526a = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        switch (this.f2526a) {
            case 2:
                if (!Gdx.input.isButtonPressed(0)) {
                    GameData.v().log.k();
                }
                break;
            default:
                super.enter(inputEvent, f2, f3, i2, actor);
                break;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f2526a) {
            case 0:
                ControllerConfig controllerConfig = Settings.f3418a;
                ExiledKingdoms.f3371a = true;
                return true;
            case 1:
                c0.b().c();
                return false;
            case 2:
            default:
                return super.touchDown(inputEvent, f2, f3, i2, i3);
            case 3:
                l0.b.l();
                return true;
            case 4:
                GameLevel.n(true);
                d0.b().c();
                return true;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f2526a) {
            case 3:
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                GameData.v().player.getClass();
                z.f2926j0 = false;
                break;
            default:
                super.touchUp(inputEvent, f2, f3, i2, i3);
                break;
        }
    }
}

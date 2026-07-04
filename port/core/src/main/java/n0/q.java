package n0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: ExtendedLabel.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class q extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Actor f2860b;

    public /* synthetic */ q(int i2, Actor actor) {
        this.f2859a = i2;
        this.f2860b = actor;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        switch (this.f2859a) {
            case 0:
                s sVar = (s) this.f2860b;
                sVar.f2884a = true;
                if (!sVar.f2885b) {
                    GameAssets.o("click_hover");
                    sVar.f2885b = true;
                }
                break;
            default:
                ((v) this.f2860b).f2895a = true;
                break;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
        switch (this.f2859a) {
            case 0:
                s sVar = (s) this.f2860b;
                sVar.f2884a = false;
                sVar.f2885b = false;
                break;
            default:
                ((v) this.f2860b).f2895a = false;
                break;
        }
    }
}

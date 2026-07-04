package n0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ConversationWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class m extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f2786a;

    m(o oVar) {
        this.f2786a = oVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        this.f2786a.f2836k.getStyle().background = new NinePatchDrawable(GameAssets.I);
        if (!ExiledKingdoms.f3378h) {
            return true;
        }
        GameAssets.o("click");
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        o oVar = this.f2786a;
        if (oVar.s != null) {
            oVar.s.a();
        }
        oVar.u(oVar.f2830e);
        oVar.f2836k.getStyle().background = null;
    }
}

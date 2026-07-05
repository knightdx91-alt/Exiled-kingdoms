package n0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ConversationWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f2760a;

    k(o oVar) {
        this.f2760a = oVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        this.f2760a.f2834i.getStyle().background = new NinePatchDrawable(GameAssets.I);
        if (!ExiledKingdoms.f3378h) {
            return true;
        }
        GameAssets.o("click");
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        o oVar = this.f2760a;
        if (oVar.f2842q != null) {
            oVar.f2842q.a();
        }
        oVar.u(oVar.f2828c);
        oVar.f2834i.getStyle().background = null;
    }
}

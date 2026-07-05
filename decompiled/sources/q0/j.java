package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/* JADX INFO: compiled from: CreditsWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class j extends InputListener {
    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        Gdx.f1569net.openURI("http://www.exiledkingdoms.com/ek_credits.html");
        return true;
    }
}

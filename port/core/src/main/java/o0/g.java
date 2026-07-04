package o0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/* JADX INFO: compiled from: CharacterWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class g extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3521a;

    public /* synthetic */ g(int i2) {
        this.f3521a = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f3521a) {
            case 0:
                m.g().getClass();
                if (!m.h()) {
                    l.e().f();
                    break;
                }
                break;
            default:
                m.g().getClass();
                if (!m.h()) {
                    n.b().c();
                    break;
                }
                break;
        }
        return true;
    }
}

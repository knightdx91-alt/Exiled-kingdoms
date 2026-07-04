package a;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import n0.z;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: compiled from: DonateWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class b extends ClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f0a;

    b(d dVar) {
        this.f0a = dVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
    public final void clicked(InputEvent inputEvent, float f2, float f3) {
        this.f0a.setVisible(false);
        int i2 = d.f4d;
        GameData.v().getClass();
        if (!GameData.I() || z.v() == null) {
            return;
        }
        z.v().r();
    }
}

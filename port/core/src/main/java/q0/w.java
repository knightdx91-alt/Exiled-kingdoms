package q0;

import n0.d1;

/* JADX INFO: compiled from: NewGameWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class w extends d1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ v f4015b;

    w(v vVar) {
        this.f4015b = vVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
    protected final void result(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            v vVar = this.f4015b;
            vVar.A.d();
            vVar.A.toFront();
        }
    }
}

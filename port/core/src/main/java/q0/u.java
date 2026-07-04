package q0;

import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: LibraryWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class u extends n0.j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ t f3973d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    u(t tVar, String str, String str2) {
        super(str);
        this.f3973d = tVar;
        this.f3972c = str2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
    protected final void result(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            t.d(this.f3973d, this.f3972c);
            Settings.A(1, "GL_wiki");
            Settings.v();
        }
    }
}

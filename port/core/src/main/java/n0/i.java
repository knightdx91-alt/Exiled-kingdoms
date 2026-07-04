package n0;

/* JADX INFO: compiled from: ChooseSaveWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class i extends j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f2686c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ g f2687d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    i(g gVar, String str, int i2) {
        super(str);
        this.f2687d = gVar;
        this.f2686c = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
    protected final void result(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            g.d(this.f2687d, this.f2686c);
        }
    }
}

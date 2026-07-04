package u;

/* JADX INFO: compiled from: MapGroupLayer.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c extends d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private e f4057f = new e();

    @Override // u.d
    public final void e() {
        int i2 = 0;
        while (true) {
            e eVar = this.f4057f;
            if (i2 >= eVar.size()) {
                return;
            }
            eVar.b(i2).e();
            i2++;
        }
    }

    public final e j() {
        return this.f4057f;
    }
}

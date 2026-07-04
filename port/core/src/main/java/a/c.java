package a;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: DonateWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class c extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ d f2b;

    public /* synthetic */ c(d dVar, int i2) {
        this.f1a = i2;
        this.f2b = dVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f1a) {
            case 0:
                if (ExiledKingdoms.f3388r) {
                    ExiledKingdoms.f3386p.b("ek_donation_con_0");
                } else {
                    ExiledKingdoms.f3386p.b("ek_donation_con_1");
                }
                this.f2b.setVisible(false);
                ExiledKingdoms.f3381k = true;
                break;
            case 1:
                ExiledKingdoms.f3386p.b("ek_donation_con_2");
                this.f2b.setVisible(false);
                ExiledKingdoms.f3381k = true;
                break;
            default:
                ExiledKingdoms.f3386p.b("ek_donation_con_3");
                this.f2b.setVisible(false);
                ExiledKingdoms.f3381k = true;
                break;
        }
        return true;
    }
}

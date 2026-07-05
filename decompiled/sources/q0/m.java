package q0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: DesktopBackupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class m extends ClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f3921a;

    m(n nVar) {
        this.f3921a = nVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
    public final void clicked(InputEvent inputEvent, float f2, float f3) {
        ExiledKingdoms.i();
        this.f3921a.setVisible(false);
    }
}

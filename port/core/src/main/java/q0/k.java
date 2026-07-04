package q0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import n0.k1;
import net.fdgames.Helpers.Serializer;

/* JADX INFO: compiled from: DesktopBackupWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
final class k extends ChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f3917a;

    /* JADX INFO: compiled from: DesktopBackupWindow.java */
    final class a extends n0.j {

        /* JADX INFO: renamed from: q0.k$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DesktopBackupWindow.java */
        final class C0056a extends k1 {
        }

        a(String str) {
            super(str);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                k kVar = k.this;
                kVar.f3917a.f3928g.a();
                n nVar = kVar.f3917a;
                nVar.f3928g.toFront();
                Serializer.c(false);
                new C0056a(n.d("BACKUP_DONE"), 0).show(nVar.f3927f);
                nVar.f3928g.setVisible(false);
            }
        }
    }

    k(n nVar) {
        this.f3917a = nVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
    public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
        a aVar = new a(n.d("BACKUP_INFO"));
        n nVar = this.f3917a;
        aVar.show(nVar.f3927f);
        nVar.setVisible(false);
    }
}

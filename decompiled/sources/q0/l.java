package q0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import n0.k1;
import net.fdgames.Helpers.Serializer;

/* JADX INFO: compiled from: DesktopBackupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class l extends ChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f3919a;

    /* JADX INFO: compiled from: DesktopBackupWindow.java */
    final class a extends n0.j {

        /* JADX INFO: renamed from: q0.l$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DesktopBackupWindow.java */
        final class C0057a extends k1 {
        }

        a(String str) {
            super(str);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
        protected final void result(Object obj) {
            if (((Boolean) obj).booleanValue()) {
                l lVar = l.this;
                lVar.f3919a.f3928g.a();
                n nVar = lVar.f3919a;
                nVar.f3928g.toFront();
                Serializer.x(false);
                l0.e.u();
                new C0057a(n.d("BACKUP_RESTORED"), 0).show(nVar.f3927f);
                Serializer.G();
                nVar.f3928g.setVisible(false);
            }
        }
    }

    /* JADX INFO: compiled from: DesktopBackupWindow.java */
    final class b extends k1 {
    }

    l(n nVar) {
        this.f3919a = nVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
    public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
        boolean zD = Serializer.d();
        n nVar = this.f3919a;
        if (zD) {
            new a(n.d("BACKUP_RESTORE_CONFIRM")).show(nVar.f3927f);
        } else {
            new b(n.d("BACKUP_RESTORE_NOFILE"), 0).show(nVar.f3927f);
        }
        Serializer.G();
        nVar.setVisible(false);
    }
}

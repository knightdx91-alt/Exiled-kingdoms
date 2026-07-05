package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.support.v7.app.c;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.o;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class i implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, o.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private android.support.v7.app.c f950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    f f951d;

    public i(t tVar) {
        this.f949b = tVar;
    }

    public final void a() {
        t tVar = this.f949b;
        c.a aVar = new c.a(tVar.n());
        f fVar = new f(aVar.b(), k.g.abc_list_menu_item_layout);
        this.f951d = fVar;
        fVar.d(this);
        tVar.b(this.f951d);
        aVar.c(this.f951d.a(), this);
        View view = tVar.f941o;
        if (view != null) {
            aVar.d(view);
        } else {
            aVar.e(tVar.f940n);
            aVar.g(tVar.f939m);
        }
        aVar.f(this);
        android.support.v7.app.c cVarA = aVar.a();
        this.f950c = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f950c.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= MetadataChangeSet.INDEXABLE_TEXT_SIZE_LIMIT_BYTES;
        this.f950c.show();
    }

    @Override // android.support.v7.view.menu.o.a
    public final void b(h hVar, boolean z2) {
        android.support.v7.app.c cVar;
        if ((z2 || hVar == this.f949b) && (cVar = this.f950c) != null) {
            cVar.dismiss();
        }
    }

    @Override // android.support.v7.view.menu.o.a
    public final boolean c(t tVar) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        this.f949b.x(((f.a) this.f951d.a()).getItem(i2), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f951d.b(this.f949b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        t tVar = this.f949b;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f950c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f950c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                tVar.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return tVar.performShortcut(i2, keyEvent, 0);
    }
}

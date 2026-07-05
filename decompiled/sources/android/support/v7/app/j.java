package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.support.v7.app.i;
import android.view.ActionMode;
import android.view.Window;
import n.f;

/* JADX INFO: compiled from: AppCompatDelegateImplV23.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class j extends i {
    private final UiModeManager S;

    /* JADX INFO: compiled from: AppCompatDelegateImplV23.java */
    class a extends i.a {
        a(Window.Callback callback) {
            super(callback);
        }

        @Override // android.view.Window.Callback
        public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // n.i, android.view.Window.Callback
        public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            if (!j.this.Q() || i2 != 0) {
                return super.onWindowStartingActionMode(callback, i2);
            }
            i iVar = i.this;
            f.a aVar = new f.a(iVar.f757b, callback);
            n.b bVarN = iVar.N(aVar);
            if (bVarN != null) {
                return aVar.d(bVarN);
            }
            return null;
        }
    }

    j(Context context, Window window, d dVar) {
        super(context, window, dVar);
        this.S = (UiModeManager) context.getSystemService("uimode");
    }

    @Override // android.support.v7.app.i
    final int R(int i2) {
        if (i2 == 0 && this.S.getNightMode() == 0) {
            return -1;
        }
        return super.R(i2);
    }
}

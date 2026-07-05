package android.support.v7.app;

import android.support.v7.app.j;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import java.util.List;

/* JADX INFO: compiled from: AppCompatDelegateImplN.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class g extends j {

    /* JADX INFO: compiled from: AppCompatDelegateImplN.java */
    class a extends j.a {
        a(Window.Callback callback) {
            super(callback);
        }

        @Override // n.i, android.view.Window.Callback
        public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            android.support.v7.view.menu.h hVar = g.this.I(0).f731h;
            if (hVar != null) {
                super.onProvideKeyboardShortcuts(list, hVar, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            }
        }
    }

    @Override // android.support.v7.app.f
    final Window.Callback z(Window.Callback callback) {
        return new a(callback);
    }
}

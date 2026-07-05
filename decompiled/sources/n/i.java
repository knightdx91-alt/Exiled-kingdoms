package n;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* JADX INFO: compiled from: WindowCallbackWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class i implements Window.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Window.Callback f2515b;

    public i(Window.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f2515b = callback;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f2515b.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f2515b.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.f2515b.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f2515b.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f2515b.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f2515b.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        this.f2515b.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        this.f2515b.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.f2515b.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return this.f2515b.onCreatePanelMenu(i2, menu);
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i2) {
        return this.f2515b.onCreatePanelView(i2);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.f2515b.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        return this.f2515b.onMenuItemSelected(i2, menuItem);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        return this.f2515b.onMenuOpened(i2, menu);
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        this.f2515b.onPanelClosed(i2, menu);
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z2) {
        this.f2515b.onPointerCaptureChanged(z2);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        return this.f2515b.onPreparePanel(i2, view, menu);
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
        this.f2515b.onProvideKeyboardShortcuts(list, menu, i2);
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return this.f2515b.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f2515b.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z2) {
        this.f2515b.onWindowFocusChanged(z2);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        return this.f2515b.onWindowStartingActionMode(callback, i2);
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.f2515b.onSearchRequested();
    }
}

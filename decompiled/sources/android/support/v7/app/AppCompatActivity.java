package android.support.v7.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.a0;
import android.support.v4.app.k0;
import android.support.v7.widget.a1;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AppCompatActivity extends FragmentActivity implements d, k0.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f713c = 0;

    @Override // android.support.v4.app.k0.a
    public final Intent a() {
        return a0.a(this);
    }

    @Override // android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        b().c(view, layoutParams);
    }

    public final e b() {
        if (this.f712b == null) {
            this.f712b = new g(this, getWindow(), this);
        }
        return this.f712b;
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ((f) b()).v();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        ((f) b()).v();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public final <T extends View> T findViewById(int i2) {
        return (T) b().e(i2);
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        f fVar = (f) b();
        if (fVar.f762g == null) {
            fVar.v();
            s sVar = fVar.f761f;
            fVar.f762g = new n.g(sVar != null ? sVar.d() : fVar.f757b);
        }
        return fVar.f762g;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i2 = a1.f1182a;
        return super.getResources();
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        b().g();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b().h(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected final void onCreate(Bundle bundle) {
        e eVarB = b();
        eVarB.f();
        eVarB.i(bundle);
        if (eVarB.d() && this.f713c != 0) {
            onApplyThemeResource(getTheme(), this.f713c, false);
        }
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected final void onDestroy() {
        super.onDestroy();
        b().j();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        Intent intentA;
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        f fVar = (f) b();
        fVar.v();
        s sVar = fVar.f761f;
        if (menuItem.getItemId() == 16908332 && sVar != null && (sVar.f811e.n() & 4) != 0 && (intentA = a0.a(this)) != null) {
            if (!shouldUpRecreateTask(intentA)) {
                navigateUpTo(intentA);
                return true;
            }
            k0 k0VarB = k0.b(this);
            k0VarB.a(this);
            k0VarB.c();
            try {
                finishAffinity();
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    protected final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        b().k();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected final void onPostResume() {
        super.onPostResume();
        AppCompatDelegateImplV9 appCompatDelegateImplV9 = (AppCompatDelegateImplV9) b();
        appCompatDelegateImplV9.v();
        s sVar = appCompatDelegateImplV9.f761f;
        if (sVar != null) {
            sVar.l(true);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b().l(bundle);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected final void onStart() {
        super.onStart();
        b().m();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected final void onStop() {
        super.onStop();
        b().n();
    }

    @Override // android.app.Activity
    protected final void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        b().s(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        ((f) b()).v();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i2) {
        b().p(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i2) {
        super.setTheme(i2);
        this.f713c = i2;
    }

    @Override // android.support.v4.app.FragmentActivity
    public final void supportInvalidateOptionsMenu() {
        b().g();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        b().q(view);
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        b().r(view, layoutParams);
    }
}

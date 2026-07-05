package android.support.v7.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/* JADX INFO: compiled from: AlertDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c extends o implements DialogInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AlertController f753c;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AlertController.b f754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f755b;

        public a(Context context) {
            int iB = c.b(context, 0);
            this.f754a = new AlertController.b(new ContextThemeWrapper(context, c.b(context, iB)));
            this.f755b = iB;
        }

        public final c a() {
            AlertController.b bVar = this.f754a;
            c cVar = new c(bVar.f703a, this.f755b);
            View view = bVar.f707e;
            AlertController alertController = cVar.f753c;
            if (view != null) {
                alertController.c(view);
            } else {
                CharSequence charSequence = bVar.f706d;
                if (charSequence != null) {
                    alertController.e(charSequence);
                }
                Drawable drawable = bVar.f705c;
                if (drawable != null) {
                    alertController.d(drawable);
                }
            }
            if (bVar.f709g != null) {
                AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) bVar.f704b.inflate(alertController.f696q, (ViewGroup) null);
                ListAdapter dVar = bVar.f709g;
                if (dVar == null) {
                    dVar = new AlertController.d(bVar.f703a, alertController.f697r, R.id.text1, null);
                }
                alertController.f694o = dVar;
                if (bVar.f710h != null) {
                    recycleListView.setOnItemClickListener(new b(bVar, alertController));
                }
                alertController.f684e = recycleListView;
            }
            cVar.setCancelable(true);
            cVar.setCanceledOnTouchOutside(true);
            cVar.setOnCancelListener(null);
            cVar.setOnDismissListener(null);
            DialogInterface.OnKeyListener onKeyListener = bVar.f708f;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public final Context b() {
            return this.f754a.f703a;
        }

        public final void c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.b bVar = this.f754a;
            bVar.f709g = listAdapter;
            bVar.f710h = onClickListener;
        }

        public final void d(View view) {
            this.f754a.f707e = view;
        }

        public final void e(Drawable drawable) {
            this.f754a.f705c = drawable;
        }

        public final void f(DialogInterface.OnKeyListener onKeyListener) {
            this.f754a.f708f = onKeyListener;
        }

        public final void g(CharSequence charSequence) {
            this.f754a.f706d = charSequence;
        }
    }

    protected c(ContextThemeWrapper contextThemeWrapper, int i2) {
        int iB = b(contextThemeWrapper, i2);
        if (iB == 0) {
            TypedValue typedValue = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(k.a.dialogTheme, typedValue, true);
            iB = typedValue.resourceId;
        }
        super(contextThemeWrapper, iB);
        a().i(null);
        a().d();
        this.f753c = new AlertController(getContext(), this, getWindow());
    }

    static int b(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(k.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.support.v7.app.o, android.app.Dialog
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f753c.a();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f753c.f688i;
        if (nestedScrollView == null || !nestedScrollView.e(keyEvent)) {
            return super.onKeyDown(i2, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f753c.f688i;
        if (nestedScrollView == null || !nestedScrollView.e(keyEvent)) {
            return super.onKeyUp(i2, keyEvent);
        }
        return true;
    }

    @Override // android.support.v7.app.o, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f753c.e(charSequence);
    }
}

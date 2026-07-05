package android.support.v4.app;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.b;
import android.support.v4.app.j.h;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class e extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mViewDestroyed;
    int mStyle = 0;
    int mTheme = 0;
    boolean mCancelable = true;
    boolean mShowsDialog = true;
    int mBackStackId = -1;

    public void dismiss() {
        dismissInternal(false);
    }

    public void dismissAllowingStateLoss() {
        dismissInternal(true);
    }

    void dismissInternal(boolean z2) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mDialog = null;
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            i fragmentManager = getFragmentManager();
            int i2 = this.mBackStackId;
            j jVar = (j) fragmentManager;
            jVar.getClass();
            if (i2 < 0) {
                throw new IllegalArgumentException(a.a.h(i2, "Bad id: "));
            }
            jVar.R(jVar.new h(i2), false);
            this.mBackStackId = -1;
            return;
        }
        j jVar2 = (j) getFragmentManager();
        jVar2.getClass();
        b bVar = new b(jVar2);
        bVar.d(new b.a(3, this));
        if (z2) {
            bVar.f(true);
        } else {
            bVar.f(false);
        }
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Bundle bundle2;
        super.onActivityCreated(bundle);
        if (this.mShowsDialog) {
            View view = getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.mDialog.setContentView(view);
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.mDialog.setOwnerActivity(activity);
            }
            this.mDialog.setCancelable(this.mCancelable);
            this.mDialog.setOnCancelListener(this);
            this.mDialog.setOnDismissListener(this);
            if (bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
                return;
            }
            this.mDialog.onRestoreInstanceState(bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.mShownByMe) {
            return;
        }
        this.mDismissed = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShowsDialog = this.mContainerId == 0;
        if (bundle != null) {
            this.mStyle = bundle.getInt(SAVED_STYLE, 0);
            this.mTheme = bundle.getInt(SAVED_THEME, 0);
            this.mCancelable = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.mShowsDialog = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
            this.mBackStackId = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Dialog(getActivity(), getTheme());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.dismiss();
            this.mDialog = null;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.mShownByMe || this.mDismissed) {
            return;
        }
        this.mDismissed = true;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mViewDestroyed) {
            return;
        }
        dismissInternal(true);
    }

    @Override // android.support.v4.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        if (!this.mShowsDialog) {
            return super.onGetLayoutInflater(bundle);
        }
        Dialog dialogOnCreateDialog = onCreateDialog(bundle);
        this.mDialog = dialogOnCreateDialog;
        if (dialogOnCreateDialog == null) {
            return (LayoutInflater) this.mHost.f219b.getSystemService("layout_inflater");
        }
        setupDialog(dialogOnCreateDialog, this.mStyle);
        return (LayoutInflater) this.mDialog.getContext().getSystemService("layout_inflater");
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.mDialog;
        if (dialog != null && (bundleOnSaveInstanceState = dialog.onSaveInstanceState()) != null) {
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, bundleOnSaveInstanceState);
        }
        int i2 = this.mStyle;
        if (i2 != 0) {
            bundle.putInt(SAVED_STYLE, i2);
        }
        int i3 = this.mTheme;
        if (i3 != 0) {
            bundle.putInt(SAVED_THEME, i3);
        }
        boolean z2 = this.mCancelable;
        if (!z2) {
            bundle.putBoolean(SAVED_CANCELABLE, z2);
        }
        boolean z3 = this.mShowsDialog;
        if (!z3) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, z3);
        }
        int i4 = this.mBackStackId;
        if (i4 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i4);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public void setCancelable(boolean z2) {
        this.mCancelable = z2;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z2);
        }
    }

    public void setShowsDialog(boolean z2) {
        this.mShowsDialog = z2;
    }

    public void setStyle(int i2, int i3) {
        this.mStyle = i2;
        if (i2 == 2 || i2 == 3) {
            this.mTheme = R.style.Theme.Panel;
        }
        if (i3 != 0) {
            this.mTheme = i3;
        }
    }

    public void setupDialog(Dialog dialog, int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void show(i iVar, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        j jVar = (j) iVar;
        jVar.getClass();
        b bVar = new b(jVar);
        bVar.b(this, str);
        bVar.f(false);
    }

    public int show(o oVar, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        oVar.b(this, str);
        this.mViewDestroyed = false;
        int iF = ((b) oVar).f(false);
        this.mBackStackId = iF;
        return iF;
    }
}

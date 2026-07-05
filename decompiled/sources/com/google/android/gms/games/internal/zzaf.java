package com.google.android.gms.games.internal;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@TargetApi(12)
final class zzaf extends zzac implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    private boolean zzfu;
    private WeakReference<View> zzjf;

    protected zzaf(zze zzeVar, int i2) {
        super(zzeVar, i2);
        this.zzfu = false;
    }

    @TargetApi(17)
    private final void zzc(View view) {
        Display display;
        int displayId = -1;
        if (PlatformVersion.isAtLeastJellyBeanMR1() && (display = view.getDisplay()) != null) {
            displayId = display.getDisplayId();
        }
        IBinder windowToken = view.getWindowToken();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        zzae zzaeVar = this.zzjd;
        zzaeVar.zzje = displayId;
        zzaeVar.zzjb = windowToken;
        int i2 = iArr[0];
        zzaeVar.left = i2;
        int i3 = iArr[1];
        zzaeVar.top = i3;
        zzaeVar.right = i2 + width;
        zzaeVar.bottom = i3 + height;
        if (this.zzfu) {
            zzbj();
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        View view;
        WeakReference<View> weakReference = this.zzjf;
        if (weakReference == null || (view = weakReference.get()) == null) {
            return;
        }
        zzc(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        zzc(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.zzjc.zzbd();
        view.removeOnAttachStateChangeListener(this);
    }

    @Override // com.google.android.gms.games.internal.zzac
    @TargetApi(16)
    public final void zzb(View view) {
        this.zzjc.zzbd();
        WeakReference<View> weakReference = this.zzjf;
        if (weakReference != null) {
            View decorView = weakReference.get();
            Context context = this.zzjc.getContext();
            if (decorView == null && (context instanceof Activity)) {
                decorView = ((Activity) context).getWindow().getDecorView();
            }
            if (decorView != null) {
                decorView.removeOnAttachStateChangeListener(this);
                ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                if (PlatformVersion.isAtLeastJellyBean()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
        this.zzjf = null;
        Context context2 = this.zzjc.getContext();
        if (view == null && (context2 instanceof Activity)) {
            Activity activity = (Activity) context2;
            view = activity.findViewById(R.id.content);
            if (view == null) {
                view = activity.getWindow().getDecorView();
            }
            zzh.w("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
        }
        if (view == null) {
            zzh.e("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
            return;
        }
        zzc(view);
        this.zzjf = new WeakReference<>(view);
        view.addOnAttachStateChangeListener(this);
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // com.google.android.gms.games.internal.zzac
    public final void zzbj() {
        boolean z2;
        if (this.zzjd.zzjb != null) {
            super.zzbj();
            z2 = false;
        } else {
            z2 = true;
        }
        this.zzfu = z2;
    }

    @Override // com.google.android.gms.games.internal.zzac
    protected final void zzm(int i2) {
        this.zzjd = new zzae(i2, null);
    }
}

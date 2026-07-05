package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: ActionBarBackgroundDrawableV21.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class b extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ActionBarContainer f1183a;

    public b(ActionBarContainer actionBarContainer) {
        this.f1183a = actionBarContainer;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.f1183a;
        if (actionBarContainer.f1027i) {
            Drawable drawable = actionBarContainer.f1026h;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f1024f;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Drawable drawable3 = actionBarContainer.f1025g;
        if (drawable3 == null || !actionBarContainer.f1028j) {
            return;
        }
        drawable3.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.f1183a;
        if (actionBarContainer.f1027i) {
            Drawable drawable = actionBarContainer.f1026h;
            if (drawable != null) {
                drawable.getOutline(outline);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f1024f;
        if (drawable2 != null) {
            drawable2.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}

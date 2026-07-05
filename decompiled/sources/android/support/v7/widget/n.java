package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppCompatImageView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class n extends ImageView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final m f1311c;

    public n(Context context) {
        this(context, null, 0);
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1310b;
        if (dVar != null) {
            dVar.a();
        }
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1310b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1310b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        m mVar = this.f1311c;
        if (mVar != null) {
            return mVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        m mVar = this.f1311c;
        if (mVar != null) {
            return mVar.c();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return this.f1311c.d() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1310b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1310b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.f(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1310b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1310b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.g(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        m mVar = this.f1311c;
        if (mVar != null) {
            mVar.h(mode);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        u0.a(context);
        d dVar = new d(this);
        this.f1310b = dVar;
        dVar.d(attributeSet, i2);
        m mVar = new m(this);
        this.f1311c = mVar;
        mVar.e(attributeSet, i2);
    }
}

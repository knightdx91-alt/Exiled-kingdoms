package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.dynamic.RemoteCreator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zaas;
    private View.OnClickListener zaat;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View.OnClickListener onClickListener = this.zaat;
        if (onClickListener == null || view != this.zaas) {
            return;
        }
        onClickListener.onClick(this);
    }

    public final void setColorScheme(int i2) {
        setStyle(this.mSize, i2);
    }

    @Override // android.view.View
    public final void setEnabled(boolean z2) {
        super.setEnabled(z2);
        this.zaas.setEnabled(z2);
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zaat = onClickListener;
        View view = this.zaas;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public final void setSize(int i2) {
        setStyle(i2, this.mColor);
    }

    public final void setStyle(int i2, int i3) {
        this.mSize = i2;
        this.mColor = i3;
        Context context = getContext();
        View view = this.zaas;
        if (view != null) {
            removeView(view);
        }
        try {
            this.zaas = SignInButtonCreator.createView(context, this.mSize, this.mColor);
        } catch (RemoteCreator.RemoteCreatorException unused) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i4 = this.mSize;
            int i5 = this.mColor;
            SignInButtonImpl signInButtonImpl = new SignInButtonImpl(context);
            signInButtonImpl.configure(context.getResources(), i4, i5);
            this.zaas = signInButtonImpl;
        }
        addView(this.zaas);
        this.zaas.setEnabled(isEnabled());
        this.zaas.setOnClickListener(this);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.zaat = null;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.google.android.gms.base.R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = typedArrayObtainStyledAttributes.getInt(com.google.android.gms.base.R.styleable.SignInButton_buttonSize, 0);
            this.mColor = typedArrayObtainStyledAttributes.getInt(com.google.android.gms.base.R.styleable.SignInButton_colorScheme, 2);
            typedArrayObtainStyledAttributes.recycle();
            setStyle(this.mSize, this.mColor);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Deprecated
    public final void setStyle(int i2, int i3, Scope[] scopeArr) {
        setStyle(i2, i3);
    }
}

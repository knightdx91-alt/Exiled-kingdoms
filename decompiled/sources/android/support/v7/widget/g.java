package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: compiled from: AppCompatCheckedTextView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g extends CheckedTextView {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f1215c = {R.attr.checkMark};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final z f1216b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        u0.a(context);
        z zVar = new z(this);
        this.f1216b = zVar;
        zVar.l(attributeSet, R.attr.checkedTextViewStyle);
        zVar.c();
        x0 x0VarT = x0.t(getContext(), attributeSet, f1215c, R.attr.checkedTextViewStyle);
        setCheckMarkDrawable(x0VarT.f(0));
        x0VarT.u();
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        z zVar = this.f1216b;
        if (zVar != null) {
            zVar.c();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        k.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i2) {
        setCheckMarkDrawable(l.a.a(getContext(), i2));
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        z zVar = this.f1216b;
        if (zVar != null) {
            zVar.m(context, i2);
        }
    }
}

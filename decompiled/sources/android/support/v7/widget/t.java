package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: compiled from: AppCompatSeekBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class t extends SeekBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final u f1346b;

    /* JADX WARN: Illegal instructions before constructor call */
    public t(Context context, AttributeSet attributeSet) {
        int i2 = k.a.seekBarStyle;
        super(context, attributeSet, i2);
        u uVar = new u(this);
        this.f1346b = uVar;
        uVar.b(attributeSet, i2);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        this.f1346b.f();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f1346b.g();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1346b.e(canvas);
    }
}

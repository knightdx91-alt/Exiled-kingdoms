package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* JADX INFO: compiled from: AppCompatRatingBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class s extends RatingBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final q f1338b;

    /* JADX WARN: Illegal instructions before constructor call */
    public s(Context context, AttributeSet attributeSet) {
        int i2 = k.a.ratingBarStyle;
        super(context, attributeSet, i2);
        q qVar = new q(this);
        this.f1338b = qVar;
        qVar.b(attributeSet, i2);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected final synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmapA = this.f1338b.a();
        if (bitmapA != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmapA.getWidth() * getNumStars(), i2, 0), getMeasuredHeight());
        }
    }
}

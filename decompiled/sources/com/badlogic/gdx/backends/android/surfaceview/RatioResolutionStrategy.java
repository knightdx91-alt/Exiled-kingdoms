package com.badlogic.gdx.backends.android.surfaceview;

import android.view.View;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RatioResolutionStrategy implements ResolutionStrategy {
    private final float ratio;

    public RatioResolutionStrategy(float f2) {
        this.ratio = f2;
    }

    @Override // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public ResolutionStrategy.MeasuredDimension calcMeasures(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        float f2 = this.ratio;
        float f3 = size;
        float f4 = size2;
        if (f3 / f4 < f2) {
            size2 = Math.round(f3 / f2);
        } else {
            size = Math.round(f4 * f2);
        }
        return new ResolutionStrategy.MeasuredDimension(size, size2);
    }

    public RatioResolutionStrategy(float f2, float f3) {
        this.ratio = f2 / f3;
    }
}

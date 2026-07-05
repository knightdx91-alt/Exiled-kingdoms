package com.badlogic.gdx.backends.android.surfaceview;

import android.view.View;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FillResolutionStrategy implements ResolutionStrategy {
    @Override // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public ResolutionStrategy.MeasuredDimension calcMeasures(int i2, int i3) {
        return new ResolutionStrategy.MeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }
}

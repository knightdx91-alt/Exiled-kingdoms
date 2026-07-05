package com.badlogic.gdx.backends.android.surfaceview;

import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FixedResolutionStrategy implements ResolutionStrategy {
    private final int height;
    private final int width;

    public FixedResolutionStrategy(int i2, int i3) {
        this.width = i2;
        this.height = i3;
    }

    @Override // com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy
    public ResolutionStrategy.MeasuredDimension calcMeasures(int i2, int i3) {
        return new ResolutionStrategy.MeasuredDimension(this.width, this.height);
    }
}

package com.badlogic.gdx.backends.android.surfaceview;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ResolutionStrategy {

    public static class MeasuredDimension {
        public final int height;
        public final int width;

        public MeasuredDimension(int i2, int i3) {
            this.width = i2;
            this.height = i3;
        }
    }

    MeasuredDimension calcMeasures(int i2, int i3);
}

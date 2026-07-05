package android.support.v7.widget;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.AbsSeekBar;

/* JADX INFO: compiled from: AppCompatProgressBarHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f1327c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AbsSeekBar f1328a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bitmap f1329b;

    q(AbsSeekBar absSeekBar) {
        this.f1328a = absSeekBar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Drawable c(Drawable drawable, boolean z2) {
        if (drawable instanceof g.a) {
            g.a aVar = (g.a) drawable;
            Drawable drawableB = aVar.b();
            if (drawableB != null) {
                aVar.a(c(drawableB, z2));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    int id = layerDrawable.getId(i2);
                    drawableArr[i2] = c(layerDrawable.getDrawable(i2), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i3 = 0; i3 < numberOfLayers; i3++) {
                    layerDrawable2.setId(i3, layerDrawable.getId(i3));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.f1329b == null) {
                    this.f1329b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z2 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    final Bitmap a() {
        return this.f1329b;
    }

    void b(AttributeSet attributeSet, int i2) {
        AbsSeekBar absSeekBar = this.f1328a;
        x0 x0VarT = x0.t(absSeekBar.getContext(), attributeSet, f1327c, i2);
        Drawable drawableG = x0VarT.g(0);
        if (drawableG != null) {
            if (drawableG instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawableG;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i3 = 0; i3 < numberOfFrames; i3++) {
                    Drawable drawableC = c(animationDrawable.getFrame(i3), true);
                    drawableC.setLevel(10000);
                    animationDrawable2.addFrame(drawableC, animationDrawable.getDuration(i3));
                }
                animationDrawable2.setLevel(10000);
                drawableG = animationDrawable2;
            }
            absSeekBar.setIndeterminateDrawable(drawableG);
        }
        Drawable drawableG2 = x0VarT.g(1);
        if (drawableG2 != null) {
            absSeekBar.setProgressDrawable(c(drawableG2, false));
        }
        x0VarT.u();
    }
}

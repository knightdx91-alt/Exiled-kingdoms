package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

/* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class b0 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final RectF f1184k = new RectF();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static Hashtable<String, Method> f1185l = new Hashtable<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1186a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1187b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1188c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1189d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1190e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f1191f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1192g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextPaint f1193h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final TextView f1194i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Context f1195j;

    b0(TextView textView) {
        this.f1194i = textView;
        this.f1195j = textView.getContext();
    }

    private static int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    private int c(RectF rectF) {
        CharSequence transformation;
        int length = this.f1191f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = length - 1;
        int i3 = 1;
        int i4 = 0;
        while (i3 <= i2) {
            int i5 = (i3 + i2) / 2;
            int i6 = this.f1191f[i5];
            TextView textView = this.f1194i;
            CharSequence text = textView.getText();
            TransformationMethod transformationMethod = textView.getTransformationMethod();
            if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, textView)) != null) {
                text = transformation;
            }
            int maxLines = textView.getMaxLines();
            TextPaint textPaint = this.f1193h;
            if (textPaint == null) {
                this.f1193h = new TextPaint();
            } else {
                textPaint.reset();
            }
            this.f1193h.set(textView.getPaint());
            this.f1193h.setTextSize(i6);
            StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(text, 0, text.length(), this.f1193h, Math.round(rectF.right)).setAlignment((Layout.Alignment) j(textView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL)).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Api.BaseClientBuilder.API_PRIORITY_OTHER : maxLines).setTextDirection((TextDirectionHeuristic) j(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR)).build();
            if ((maxLines == -1 || (staticLayoutBuild.getLineCount() <= maxLines && staticLayoutBuild.getLineEnd(staticLayoutBuild.getLineCount() - 1) == text.length())) && staticLayoutBuild.getHeight() <= rectF.bottom) {
                int i7 = i5 + 1;
                i4 = i3;
                i3 = i7;
            } else {
                i4 = i5 - 1;
                i2 = i4;
            }
        }
        return this.f1191f[i4];
    }

    private static Method i(String str) {
        try {
            Hashtable<String, Method> hashtable = f1185l;
            Method declaredMethod = hashtable.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, null)) != null) {
                declaredMethod.setAccessible(true);
                hashtable.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    private static Object j(Object obj, String str, Object obj2) {
        try {
            return i(str).invoke(obj, null);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return obj2;
        }
    }

    private boolean q() {
        if (s() && this.f1186a == 1) {
            if (!this.f1192g || this.f1191f.length == 0) {
                float fRound = Math.round(this.f1189d);
                int i2 = 1;
                while (Math.round(this.f1188c + fRound) <= Math.round(this.f1190e)) {
                    i2++;
                    fRound += this.f1188c;
                }
                int[] iArr = new int[i2];
                float f2 = this.f1189d;
                for (int i3 = 0; i3 < i2; i3++) {
                    iArr[i3] = Math.round(f2);
                    f2 += this.f1188c;
                }
                this.f1191f = b(iArr);
            }
            this.f1187b = true;
        } else {
            this.f1187b = false;
        }
        return this.f1187b;
    }

    private boolean r() {
        boolean z2 = this.f1191f.length > 0;
        this.f1192g = z2;
        if (z2) {
            this.f1186a = 1;
            this.f1189d = r0[0];
            this.f1190e = r0[r1 - 1];
            this.f1188c = -1.0f;
        }
        return z2;
    }

    private boolean s() {
        return !(this.f1194i instanceof j);
    }

    private void t(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
        this.f1186a = 1;
        this.f1189d = f2;
        this.f1190e = f3;
        this.f1188c = f4;
        this.f1192g = false;
    }

    final void a() {
        if (k()) {
            if (this.f1187b) {
                if (this.f1194i.getMeasuredHeight() <= 0 || this.f1194i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = ((Boolean) j(this.f1194i, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue() ? 1048576 : (this.f1194i.getMeasuredWidth() - this.f1194i.getTotalPaddingLeft()) - this.f1194i.getTotalPaddingRight();
                int height = (this.f1194i.getHeight() - this.f1194i.getCompoundPaddingBottom()) - this.f1194i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1184k;
                synchronized (rectF) {
                    try {
                        rectF.setEmpty();
                        rectF.right = measuredWidth;
                        rectF.bottom = height;
                        float fC = c(rectF);
                        if (fC != this.f1194i.getTextSize()) {
                            p(0, fC);
                        }
                    } finally {
                    }
                }
            }
            this.f1187b = true;
        }
    }

    final int d() {
        return Math.round(this.f1190e);
    }

    final int e() {
        return Math.round(this.f1189d);
    }

    final int f() {
        return Math.round(this.f1188c);
    }

    final int[] g() {
        return this.f1191f;
    }

    final int h() {
        return this.f1186a;
    }

    final boolean k() {
        return s() && this.f1186a != 0;
    }

    final void l(AttributeSet attributeSet, int i2) {
        int resourceId;
        int[] iArr = k.j.AppCompatTextView;
        Context context = this.f1195j;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        if (typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextView_autoSizeTextType)) {
            this.f1186a = typedArrayObtainStyledAttributes.getInt(k.j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextView_autoSizeStepGranularity) ? typedArrayObtainStyledAttributes.getDimension(k.j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextView_autoSizeMinTextSize) ? typedArrayObtainStyledAttributes.getDimension(k.j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextView_autoSizeMaxTextSize) ? typedArrayObtainStyledAttributes.getDimension(k.j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextView_autoSizePresetSizes) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(k.j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr2 = new int[length];
            if (length > 0) {
                for (int i3 = 0; i3 < length; i3++) {
                    iArr2[i3] = typedArrayObtainTypedArray.getDimensionPixelSize(i3, -1);
                }
                this.f1191f = b(iArr2);
                r();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!s()) {
            this.f1186a = 0;
            return;
        }
        if (this.f1186a == 1) {
            if (!this.f1192g) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                t(dimension2, dimension3, dimension);
            }
            q();
        }
    }

    final void m(int i2, int i3, int i4, int i5) {
        if (s()) {
            DisplayMetrics displayMetrics = this.f1195j.getResources().getDisplayMetrics();
            t(TypedValue.applyDimension(i5, i2, displayMetrics), TypedValue.applyDimension(i5, i3, displayMetrics), TypedValue.applyDimension(i5, i4, displayMetrics));
            if (q()) {
                a();
            }
        }
    }

    final void n(int[] iArr, int i2) {
        if (s()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i2 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f1195j.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArrCopyOf[i3] = Math.round(TypedValue.applyDimension(i2, iArr[i3], displayMetrics));
                    }
                }
                this.f1191f = b(iArrCopyOf);
                if (!r()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f1192g = false;
            }
            if (q()) {
                a();
            }
        }
    }

    final void o(int i2) {
        if (s()) {
            if (i2 == 0) {
                this.f1186a = 0;
                this.f1189d = -1.0f;
                this.f1190e = -1.0f;
                this.f1188c = -1.0f;
                this.f1191f = new int[0];
                this.f1187b = false;
                return;
            }
            if (i2 != 1) {
                throw new IllegalArgumentException(a.a.h(i2, "Unknown auto-size text type: "));
            }
            DisplayMetrics displayMetrics = this.f1195j.getResources().getDisplayMetrics();
            t(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (q()) {
                a();
            }
        }
    }

    final void p(int i2, float f2) {
        Context context = this.f1195j;
        float fApplyDimension = TypedValue.applyDimension(i2, f2, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        TextView textView = this.f1194i;
        if (fApplyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(fApplyDimension);
            boolean zIsInLayout = textView.isInLayout();
            if (textView.getLayout() != null) {
                this.f1187b = false;
                try {
                    Method methodI = i("nullLayouts");
                    if (methodI != null) {
                        methodI.invoke(textView, null);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (zIsInLayout) {
                    textView.forceLayout();
                } else {
                    textView.requestLayout();
                }
                textView.invalidate();
            }
        }
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import e.a;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f1376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f1377c;

    private x0(Context context, TypedArray typedArray) {
        this.f1375a = context;
        this.f1376b = typedArray;
    }

    public static x0 r(Context context, int i2, int[] iArr) {
        return new x0(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static x0 s(Context context, AttributeSet attributeSet, int[] iArr) {
        return new x0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static x0 t(Context context, AttributeSet attributeSet, int[] iArr, int i2) {
        return new x0(context, context.obtainStyledAttributes(attributeSet, iArr, i2, 0));
    }

    public final boolean a(int i2, boolean z2) {
        return this.f1376b.getBoolean(i2, z2);
    }

    public final int b(int i2, int i3) {
        return this.f1376b.getColor(i2, i3);
    }

    public final ColorStateList c(int i2) {
        int resourceId;
        TypedArray typedArray = this.f1376b;
        if (typedArray.hasValue(i2) && (resourceId = typedArray.getResourceId(i2, 0)) != 0) {
            int i3 = l.a.f2307b;
            ColorStateList colorStateList = this.f1375a.getColorStateList(resourceId);
            if (colorStateList != null) {
                return colorStateList;
            }
        }
        return typedArray.getColorStateList(i2);
    }

    public final int d(int i2, int i3) {
        return this.f1376b.getDimensionPixelOffset(i2, i3);
    }

    public final int e(int i2, int i3) {
        return this.f1376b.getDimensionPixelSize(i2, i3);
    }

    public final Drawable f(int i2) {
        int resourceId;
        TypedArray typedArray = this.f1376b;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) ? typedArray.getDrawable(i2) : l.a.a(this.f1375a, resourceId);
    }

    public final Drawable g(int i2) {
        int resourceId;
        TypedArray typedArray = this.f1376b;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return i.c().e(this.f1375a, true, resourceId);
    }

    public final float h(int i2) {
        return this.f1376b.getFloat(i2, -1.0f);
    }

    public final Typeface i(int i2, int i3, e.d dVar) {
        int resourceId = this.f1376b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1377c == null) {
            this.f1377c = new TypedValue();
        }
        TypedValue typedValue = this.f1377c;
        Context context = this.f1375a;
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(resourceId, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(resourceId) + "\" (" + Integer.toHexString(resourceId) + ") is not a Font: " + typedValue);
        }
        String string = charSequence.toString();
        if (!string.startsWith("res/")) {
            dVar.a(-3);
            return null;
        }
        Typeface typefaceE = f.b.e(resources, resourceId, i3);
        if (typefaceE != null) {
            dVar.b(typefaceE);
            return typefaceE;
        }
        try {
            if (!string.toLowerCase().endsWith(".xml")) {
                Typeface typefaceC = f.b.c(context, resources, resourceId, string, i3);
                if (typefaceC != null) {
                    dVar.b(typefaceC);
                } else {
                    dVar.a(-3);
                }
                return typefaceC;
            }
            a.InterfaceC0030a interfaceC0030aA = e.a.a(resources.getXml(resourceId), resources);
            if (interfaceC0030aA != null) {
                return f.b.b(context, interfaceC0030aA, resources, resourceId, i3, dVar);
            }
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            dVar.a(-3);
            return null;
        } catch (IOException e2) {
            Log.e("ResourcesCompat", "Failed to read xml resource ".concat(string), e2);
            dVar.a(-3);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(string), e3);
            dVar.a(-3);
            return null;
        }
    }

    public final int j(int i2, int i3) {
        return this.f1376b.getInt(i2, i3);
    }

    public final int k(int i2, int i3) {
        return this.f1376b.getInteger(i2, i3);
    }

    public final int l(int i2, int i3) {
        return this.f1376b.getLayoutDimension(i2, i3);
    }

    public final int m(int i2, int i3) {
        return this.f1376b.getResourceId(i2, i3);
    }

    public final String n(int i2) {
        return this.f1376b.getString(i2);
    }

    public final CharSequence o(int i2) {
        return this.f1376b.getText(i2);
    }

    public final CharSequence[] p(int i2) {
        return this.f1376b.getTextArray(i2);
    }

    public final boolean q(int i2) {
        return this.f1376b.hasValue(i2);
    }

    public final void u() {
        this.f1376b.recycle();
    }
}

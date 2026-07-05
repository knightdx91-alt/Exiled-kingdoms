package android.support.v7.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v7.widget.a0;
import android.support.v7.widget.t;
import android.support.v7.widget.w;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: AppCompatViewInflater.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Class<?>[] f786b = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f787c = {R.attr.onClick};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f788d = {"android.widget.", "android.view.", "android.webkit."};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final android.support.v4.util.b f789e = new android.support.v4.util.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f790a = new Object[2];

    /* JADX INFO: compiled from: AppCompatViewInflater.java */
    private static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f791b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f792c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Method f793d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Context f794e;

        public a(View view, String str) {
            this.f791b = view;
            this.f792c = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            String str;
            Method method;
            if (this.f793d == null) {
                View view2 = this.f791b;
                Context context = view2.getContext();
                while (true) {
                    String str2 = this.f792c;
                    if (context == null) {
                        int id = view2.getId();
                        if (id == -1) {
                            str = "";
                        } else {
                            str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                        }
                        StringBuilder sbU = a.a.u("Could not find method ", str2, "(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
                        sbU.append(view2.getClass());
                        sbU.append(str);
                        throw new IllegalStateException(sbU.toString());
                    }
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                            this.f793d = method;
                            this.f794e = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
                }
            }
            try {
                this.f793d.invoke(this.f794e, view);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    p() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View a(Context context, String str, String str2) {
        android.support.v4.util.b bVar = f789e;
        Constructor constructor = (Constructor) bVar.get(str);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(str2 != null ? str2.concat(str) : str).asSubclass(View.class).getConstructor(f786b);
                bVar.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f790a);
    }

    public final View b(View view, String str, Context context, AttributeSet attributeSet) {
        Context dVar;
        View sVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(k.j.View_theme, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        dVar = (resourceId == 0 || ((context instanceof n.d) && ((n.d) context).a() == resourceId)) ? context : new n.d(context, resourceId);
        str.getClass();
        View view2 = null;
        switch (str) {
            case "RatingBar":
                sVar = new android.support.v7.widget.s(dVar, attributeSet);
                break;
            case "CheckedTextView":
                sVar = new android.support.v7.widget.g(dVar, attributeSet);
                break;
            case "MultiAutoCompleteTextView":
                sVar = new android.support.v7.widget.o(dVar, attributeSet);
                break;
            case "TextView":
                sVar = new a0(dVar, attributeSet);
                break;
            case "ImageButton":
                sVar = new android.support.v7.widget.l(dVar, attributeSet, k.a.imageButtonStyle);
                break;
            case "SeekBar":
                sVar = new t(dVar, attributeSet);
                break;
            case "Spinner":
                sVar = new w(dVar, attributeSet, k.a.spinnerStyle);
                break;
            case "RadioButton":
                sVar = new android.support.v7.widget.r(dVar, attributeSet);
                break;
            case "ImageView":
                sVar = new android.support.v7.widget.n(dVar, attributeSet, 0);
                break;
            case "AutoCompleteTextView":
                sVar = new android.support.v7.widget.c(dVar, attributeSet);
                break;
            case "CheckBox":
                sVar = new android.support.v7.widget.f(dVar, attributeSet);
                break;
            case "EditText":
                sVar = new android.support.v7.widget.j(dVar, attributeSet);
                break;
            case "Button":
                sVar = new android.support.v7.widget.e(dVar, attributeSet);
                break;
            default:
                sVar = null;
                break;
        }
        if (sVar == null && context != dVar) {
            Object[] objArr = this.f790a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = dVar;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = f788d;
                        if (i2 < 3) {
                            View viewA = a(dVar, str, strArr[i2]);
                            if (viewA != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view2 = viewA;
                            } else {
                                i2++;
                            }
                        }
                    }
                } else {
                    View viewA2 = a(dVar, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewA2;
                }
            } catch (Exception unused) {
            } finally {
                objArr[0] = null;
                objArr[1] = null;
            }
            sVar = view2;
        }
        if (sVar != null) {
            Context context2 = sVar.getContext();
            if ((context2 instanceof ContextWrapper) && sVar.hasOnClickListeners()) {
                TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, f787c);
                String string = typedArrayObtainStyledAttributes2.getString(0);
                if (string != null) {
                    sVar.setOnClickListener(new a(sVar, string));
                }
                typedArrayObtainStyledAttributes2.recycle();
            }
        }
        return sVar;
    }
}

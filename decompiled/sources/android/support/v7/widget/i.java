package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static i f1235g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakHashMap<Context, android.support.v4.util.j<ColorStateList>> f1243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f1244b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final WeakHashMap<Context, android.support.v4.util.e<WeakReference<Drawable.ConstantState>>> f1245c = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TypedValue f1246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1247e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final PorterDuff.Mode f1234f = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final a f1236h = new a(6);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int[] f1237i = {k.e.abc_textfield_search_default_mtrl_alpha, k.e.abc_textfield_default_mtrl_alpha, k.e.abc_ab_share_pack_mtrl_alpha};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final int[] f1238j = {k.e.abc_ic_commit_search_api_mtrl_alpha, k.e.abc_seekbar_tick_mark_material, k.e.abc_ic_menu_share_mtrl_alpha, k.e.abc_ic_menu_copy_mtrl_am_alpha, k.e.abc_ic_menu_cut_mtrl_alpha, k.e.abc_ic_menu_selectall_mtrl_alpha, k.e.abc_ic_menu_paste_mtrl_am_alpha};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final int[] f1239k = {k.e.abc_textfield_activated_mtrl_alpha, k.e.abc_textfield_search_activated_mtrl_alpha, k.e.abc_cab_background_top_mtrl_alpha, k.e.abc_text_cursor_material, k.e.abc_text_select_handle_left_mtrl_dark, k.e.abc_text_select_handle_middle_mtrl_dark, k.e.abc_text_select_handle_right_mtrl_dark, k.e.abc_text_select_handle_left_mtrl_light, k.e.abc_text_select_handle_middle_mtrl_light, k.e.abc_text_select_handle_right_mtrl_light};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int[] f1240l = {k.e.abc_popup_background_mtrl_mult, k.e.abc_cab_background_internal_bg, k.e.abc_menu_hardkey_panel_mtrl_mult};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final int[] f1241m = {k.e.abc_tab_indicator_material, k.e.abc_textfield_search_material};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final int[] f1242n = {k.e.abc_btn_check_material, k.e.abc_btn_radio_material};

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    private static class a extends android.support.v4.util.f<Integer, PorterDuffColorFilter> {
    }

    private static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private static ColorStateList b(Context context, int i2) {
        int iB = s0.b(context, k.a.colorControlHighlight);
        int iA = s0.a(context, k.a.colorButtonNormal);
        int[] iArr = s0.f1340b;
        int[] iArr2 = s0.f1342d;
        int iA2 = f.a.a(iB, i2);
        return new ColorStateList(new int[][]{iArr, iArr2, s0.f1341c, s0.f1344f}, new int[]{iA, iA2, f.a.a(iB, i2), i2});
    }

    public static i c() {
        if (f1235g == null) {
            f1235g = new i();
        }
        return f1235g;
    }

    public static PorterDuffColorFilter f(int i2, PorterDuff.Mode mode) {
        a aVar = f1236h;
        aVar.getClass();
        int i3 = (31 + i2) * 31;
        PorterDuffColorFilter porterDuffColorFilter = aVar.get(Integer.valueOf(mode.hashCode() + i3));
        if (porterDuffColorFilter != null) {
            return porterDuffColorFilter;
        }
        PorterDuffColorFilter porterDuffColorFilter2 = new PorterDuffColorFilter(i2, mode);
        aVar.put(Integer.valueOf(mode.hashCode() + i3), porterDuffColorFilter2);
        return porterDuffColorFilter2;
    }

    private static void i(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (e0.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = f1234f;
        }
        drawable.setColorFilter(f(i2, mode));
    }

    static void j(Drawable drawable, v0 v0Var, int[] iArr) {
        if (e0.a(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z2 = v0Var.f1359d;
        if (!z2 && !v0Var.f1358c) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterF = null;
        ColorStateList colorStateList = z2 ? v0Var.f1356a : null;
        PorterDuff.Mode mode = v0Var.f1358c ? v0Var.f1357b : f1234f;
        if (colorStateList != null && mode != null) {
            porterDuffColorFilterF = f(colorStateList.getColorForState(iArr, 0), mode);
        }
        drawable.setColorFilter(porterDuffColorFilterF);
    }

    public final Drawable d(Context context, int i2) {
        return e(context, false, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x019e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x017e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final Drawable e(Context context, boolean z2, int i2) {
        WeakReference<Drawable.ConstantState> weakReferenceD;
        Drawable drawableNewDrawable;
        int i3;
        boolean z3;
        int iRound;
        boolean z4 = true;
        if (!this.f1247e) {
            this.f1247e = true;
            Drawable drawableE = e(context, false, k.e.abc_vector_test);
            if (drawableE == null || (!(drawableE instanceof d.b) && !"android.graphics.drawable.VectorDrawable".equals(drawableE.getClass().getName()))) {
                this.f1247e = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
        if (this.f1246d == null) {
            this.f1246d = new TypedValue();
        }
        TypedValue typedValue = this.f1246d;
        context.getResources().getValue(i2, typedValue, true);
        long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        synchronized (this.f1244b) {
            try {
                android.support.v4.util.e<WeakReference<Drawable.ConstantState>> eVar = this.f1245c.get(context);
                if (eVar != null && (weakReferenceD = eVar.d(j2)) != null) {
                    Drawable.ConstantState constantState = weakReferenceD.get();
                    if (constantState != null) {
                        drawableNewDrawable = constantState.newDrawable(context.getResources());
                    } else {
                        eVar.b(j2);
                    }
                }
                drawableNewDrawable = null;
            } finally {
            }
        }
        if (drawableNewDrawable == null) {
            if (i2 == k.e.abc_cab_background_top_material) {
                drawableNewDrawable = new LayerDrawable(new Drawable[]{e(context, false, k.e.abc_cab_background_internal_bg), e(context, false, k.e.abc_cab_background_top_mtrl_alpha)});
            }
            if (drawableNewDrawable != null) {
                drawableNewDrawable.setChangingConfigurations(typedValue.changingConfigurations);
                Drawable.ConstantState constantState2 = drawableNewDrawable.getConstantState();
                if (constantState2 != null) {
                    synchronized (this.f1244b) {
                        try {
                            android.support.v4.util.e<WeakReference<Drawable.ConstantState>> eVar2 = this.f1245c.get(context);
                            if (eVar2 == null) {
                                eVar2 = new android.support.v4.util.e<>();
                                this.f1245c.put(context, eVar2);
                            }
                            eVar2.e(j2, new WeakReference<>(constantState2));
                        } finally {
                        }
                    }
                }
            }
        }
        if (drawableNewDrawable == null) {
            drawableNewDrawable = context.getDrawable(i2);
        }
        if (drawableNewDrawable != null) {
            ColorStateList colorStateListG = g(context, i2);
            if (colorStateListG != null) {
                if (e0.a(drawableNewDrawable)) {
                    drawableNewDrawable = drawableNewDrawable.mutate();
                }
                drawableNewDrawable.setTintList(colorStateListG);
                mode = i2 == k.e.abc_switch_thumb_material ? PorterDuff.Mode.MULTIPLY : null;
                if (mode != null) {
                    drawableNewDrawable.setTintMode(mode);
                }
            } else {
                int i4 = k.e.abc_seekbar_track_material;
                PorterDuff.Mode mode = f1234f;
                if (i2 == i4) {
                    LayerDrawable layerDrawable = (LayerDrawable) drawableNewDrawable;
                    i(layerDrawable.findDrawableByLayerId(R.id.background), s0.b(context, k.a.colorControlNormal), mode);
                    i(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), s0.b(context, k.a.colorControlNormal), mode);
                    i(layerDrawable.findDrawableByLayerId(R.id.progress), s0.b(context, k.a.colorControlActivated), mode);
                } else if (i2 == k.e.abc_ratingbar_material || i2 == k.e.abc_ratingbar_indicator_material || i2 == k.e.abc_ratingbar_small_material) {
                    LayerDrawable layerDrawable2 = (LayerDrawable) drawableNewDrawable;
                    i(layerDrawable2.findDrawableByLayerId(R.id.background), s0.a(context, k.a.colorControlNormal), mode);
                    i(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), s0.b(context, k.a.colorControlActivated), mode);
                    i(layerDrawable2.findDrawableByLayerId(R.id.progress), s0.b(context, k.a.colorControlActivated), mode);
                } else {
                    boolean zA = a(f1237i, i2);
                    PorterDuff.Mode mode2 = f1234f;
                    if (zA) {
                        i3 = k.a.colorControlNormal;
                    } else if (a(f1239k, i2)) {
                        i3 = k.a.colorControlActivated;
                    } else {
                        if (a(f1240l, i2)) {
                            mode2 = PorterDuff.Mode.MULTIPLY;
                        } else if (i2 == k.e.abc_list_divider_mtrl_alpha) {
                            z3 = true;
                            iRound = Math.round(40.8f);
                            i3 = 16842800;
                            if (z3) {
                                z4 = false;
                            } else {
                                Drawable drawableMutate = e0.a(drawableNewDrawable) ? drawableNewDrawable.mutate() : drawableNewDrawable;
                                drawableMutate.setColorFilter(f(s0.b(context, i3), mode2));
                                if (iRound != -1) {
                                    drawableMutate.setAlpha(iRound);
                                }
                            }
                            if (!z4 || !z2) {
                            }
                            drawableNewDrawable = mode;
                        } else if (i2 != k.e.abc_dialog_material_background) {
                            i3 = 0;
                            z3 = false;
                            iRound = -1;
                            if (z3) {
                            }
                            if (!z4) {
                            }
                        }
                        iRound = -1;
                        i3 = 16842801;
                        z3 = true;
                        if (z3) {
                        }
                        if (!z4) {
                        }
                    }
                    z3 = true;
                    iRound = -1;
                    if (z3) {
                    }
                    if (!z4) {
                    }
                }
            }
            mode = drawableNewDrawable;
            drawableNewDrawable = mode;
        }
        if (drawableNewDrawable != null) {
            int i5 = e0.f1209a;
        }
        return drawableNewDrawable;
    }

    final ColorStateList g(Context context, int i2) {
        android.support.v4.util.j<ColorStateList> jVar;
        WeakHashMap<Context, android.support.v4.util.j<ColorStateList>> weakHashMap = this.f1243a;
        ColorStateList colorStateList = null;
        if (weakHashMap != null && (jVar = weakHashMap.get(context)) != null) {
            colorStateList = jVar.d(i2);
        }
        if (colorStateList == null) {
            if (i2 == k.e.abc_edit_text_material) {
                int i3 = k.c.abc_tint_edittext;
                int i4 = l.a.f2307b;
                colorStateList = context.getColorStateList(i3);
            } else if (i2 == k.e.abc_switch_track_mtrl_alpha) {
                int i5 = k.c.abc_tint_switch_track;
                int i6 = l.a.f2307b;
                colorStateList = context.getColorStateList(i5);
            } else if (i2 == k.e.abc_switch_thumb_material) {
                int[][] iArr = new int[3][];
                int[] iArr2 = new int[3];
                ColorStateList colorStateListC = s0.c(context, k.a.colorSwitchThumbNormal);
                if (colorStateListC == null || !colorStateListC.isStateful()) {
                    iArr[0] = s0.f1340b;
                    iArr2[0] = s0.a(context, k.a.colorSwitchThumbNormal);
                    iArr[1] = s0.f1343e;
                    iArr2[1] = s0.b(context, k.a.colorControlActivated);
                    iArr[2] = s0.f1344f;
                    iArr2[2] = s0.b(context, k.a.colorSwitchThumbNormal);
                } else {
                    int[] iArr3 = s0.f1340b;
                    iArr[0] = iArr3;
                    iArr2[0] = colorStateListC.getColorForState(iArr3, 0);
                    iArr[1] = s0.f1343e;
                    iArr2[1] = s0.b(context, k.a.colorControlActivated);
                    iArr[2] = s0.f1344f;
                    iArr2[2] = colorStateListC.getDefaultColor();
                }
                colorStateList = new ColorStateList(iArr, iArr2);
            } else if (i2 == k.e.abc_btn_default_mtrl_shape) {
                colorStateList = b(context, s0.b(context, k.a.colorButtonNormal));
            } else if (i2 == k.e.abc_btn_borderless_material) {
                colorStateList = b(context, 0);
            } else if (i2 == k.e.abc_btn_colored_material) {
                colorStateList = b(context, s0.b(context, k.a.colorAccent));
            } else if (i2 == k.e.abc_spinner_mtrl_am_alpha || i2 == k.e.abc_spinner_textfield_background_material) {
                int i7 = k.c.abc_tint_spinner;
                int i8 = l.a.f2307b;
                colorStateList = context.getColorStateList(i7);
            } else if (a(f1238j, i2)) {
                colorStateList = s0.c(context, k.a.colorControlNormal);
            } else if (a(f1241m, i2)) {
                int i9 = k.c.abc_tint_default;
                int i10 = l.a.f2307b;
                colorStateList = context.getColorStateList(i9);
            } else if (a(f1242n, i2)) {
                int i11 = k.c.abc_tint_btn_checkable;
                int i12 = l.a.f2307b;
                colorStateList = context.getColorStateList(i11);
            } else if (i2 == k.e.abc_seekbar_thumb_material) {
                int i13 = k.c.abc_tint_seek_thumb;
                int i14 = l.a.f2307b;
                colorStateList = context.getColorStateList(i13);
            }
            if (colorStateList != null) {
                if (this.f1243a == null) {
                    this.f1243a = new WeakHashMap<>();
                }
                android.support.v4.util.j<ColorStateList> jVar2 = this.f1243a.get(context);
                if (jVar2 == null) {
                    jVar2 = new android.support.v4.util.j<>();
                    this.f1243a.put(context, jVar2);
                }
                jVar2.a(i2, colorStateList);
            }
        }
        return colorStateList;
    }

    public final void h(Context context) {
        synchronized (this.f1244b) {
            try {
                android.support.v4.util.e<WeakReference<Drawable.ConstantState>> eVar = this.f1245c.get(context);
                if (eVar != null) {
                    eVar.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

package a;

import android.support.v4.app.Fragment;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.google.android.gms.internal.play_billing.zzbi;
import java.util.Locale;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Skills;
import q0.g;
import q0.h;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final /* synthetic */ class a {
    public static String A(String str, String str2) {
        return str + str2;
    }

    public static float B(float f2, float f3, float f4, float f5) {
        return (f2 * f3) + f4 + f5;
    }

    public static float C(float f2, float f3, float f4, float f5) {
        return ((f2 - f3) * f4) + f5;
    }

    public static float a(float f2, float f3, float f4, float f5) {
        return (f2 * f3 * f4) + f5;
    }

    public static float b(ScaledNumericValue scaledNumericValue, float f2, float f3, float f4) {
        return (scaledNumericValue.getScale(f2) * f3) + f4;
    }

    public static int c(int i2, int i3, int i4) {
        return zzbi.zzx(i2) + i3 + i4;
    }

    public static int d(int i2, int i3, int i4, int i5) {
        return zzbi.zzx(i2) + i3 + i4 + i5;
    }

    public static int e(int i2, String str) {
        return String.valueOf(str).length() + i2;
    }

    public static Cell f(h hVar, g gVar, float f2, float f3) {
        return hVar.add(gVar).height(f2).width(f3);
    }

    public static String g(int i2, int i3, String str) {
        StringBuilder sb = new StringBuilder(i2);
        sb.append(str);
        sb.append(i3);
        return sb.toString();
    }

    public static String h(int i2, String str) {
        return str + i2;
    }

    public static String i(com.badlogic.gdx.files.FileHandle aVar, String str) {
        return str + aVar;
    }

    public static String j(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String k(String str, String str2) {
        return str + str2;
    }

    public static String l(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String m(String str, String str2, StringBuilder sb) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String n(String str, boolean z2, StringBuilder sb) {
        sb.append(GameString.b(str, z2));
        return sb.toString();
    }

    public static String o(String str, boolean z2, StringBuilder sb, String str2) {
        sb.append(GameString.b(str, z2));
        sb.append(str2);
        return sb.toString();
    }

    public static String p(StringBuilder sb, int i2, String str) {
        sb.append(i2);
        sb.append(str);
        return sb.toString();
    }

    public static String q(u.f fVar, String str) {
        return fVar.a().b(str).toString();
    }

    public static StringBuilder r(int i2, String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i2);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder s(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder t(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder u(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void v(String str, String str2, StringBuilder sb) {
        sb.append(Skills.c(str).d());
        sb.append(str2);
    }

    public static void w(String str, boolean z2, StringBuilder sb, String str2) {
        sb.append(GameString.b(str, z2));
        sb.append(str2);
    }

    public static void x(String str, boolean z2, StringBuilder sb, String str2, Label label) {
        sb.append(GameString.b(str, z2));
        sb.append(str2);
        label.setText(sb.toString());
    }

    public static boolean y(String str, Locale locale, String str2) {
        return str.toLowerCase(locale).trim().equals(str2);
    }

    public static float z(float f2, float f3, float f4, float f5) {
        return f5 - ((f2 * f3) * f4);
    }
}

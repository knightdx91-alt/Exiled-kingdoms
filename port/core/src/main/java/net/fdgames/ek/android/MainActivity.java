package net.fdgames.ek.android;
import net.fdgames.ek.IPlatformResolver;
/**
 * Web/desktop STUB of the Android MainActivity.
 * The real class extended AndroidApplication and wired Google Play Games / IAP.
 * On web these platform services are no-ops (e.g. l() "isSignedIn" -> false).
 * Replace bodies with real web implementations as needed.
 */
public class MainActivity implements IPlatformResolver {
    public static final int f3422b = 0;
    public final boolean l() { return false; }
    public final void i() {  }
    public final void k(boolean z2) {  }
    public final void n() {  }
    public final void o() {  }
    public final void p(String str) {  }
    public final void q(int i2, String str) {  }
    public final void r(int i2) {  }
    public final void s(int i2) {  }
    public final void t(String str) {  }
    public Object getContext() { return null; } /* STUB */
    public Object b(Object a0, Object a1) { return null; } /* STUB */
    public Object a(Object a0) { return null; } /* STUB */
    public Object checkSelfPermission(Object a0) { return null; } /* STUB */
    public Object m() { return null; } /* STUB */
    public Object h() { return null; } /* STUB */
}

package i0;

import a0.q;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.g0;

/* JADX INFO: compiled from: ScalingViewport.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a extends b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private g0 f2258i = g0.f1807b;

    public a(float f2, float f3, OrthographicCamera orthographicCamera) {
        m(f2, f3);
        k(orthographicCamera);
    }

    @Override // i0.b
    public final void p(int i2, int i3) {
        q qVarA = this.f2258i.a(i(), h(), i2, i3);
        int iRound = Math.round(qVarA.f91a);
        int iRound2 = Math.round(qVarA.f92b);
        l((i2 - iRound) / 2, (i3 - iRound2) / 2, iRound, iRound2);
        a();
    }
}

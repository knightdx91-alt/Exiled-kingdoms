package i0;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;

/* JADX INFO: compiled from: ScalingViewport.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a extends b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g0 f2258i = g0.f1807b;

    public a(float f2, float f3, OrthographicCamera orthographicCamera) {
        m(f2, f3);
        k(orthographicCamera);
    }

    @Override // i0.b
    public final void p(int i2, int i3) {
        Vector2 qVarA = this.f2258i.a(i(), h(), i2, i3);
        int iRound = Math.round(qVarA.f91a);
        int iRound2 = Math.round(qVarA.f92b);
        l((i2 - iRound) / 2, (i3 - iRound2) / 2, iRound, iRound2);
        a();
    }
}

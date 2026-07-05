package o0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.Rules.Skills;

/* JADX INFO: compiled from: SkillWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class v extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3670a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3671b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ t f3672c;

    v(t tVar, int i2, String str) {
        this.f3672c = tVar;
        this.f3670a = i2;
        this.f3671b = str;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        t tVar = this.f3672c;
        tVar.f3656h = this.f3670a;
        tVar.n(Skills.c(this.f3671b));
        return true;
    }
}

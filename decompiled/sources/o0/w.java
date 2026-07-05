package o0;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.fdgames.Rules.Skills;

/* JADX INFO: compiled from: SkillWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class w extends InputListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ t f3676d;

    public /* synthetic */ w(t tVar, int i2, String str, int i3) {
        this.f3673a = i3;
        this.f3676d = tVar;
        this.f3674b = i2;
        this.f3675c = str;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
        switch (this.f3673a) {
            case 0:
                t tVar = this.f3676d;
                tVar.f3656h = this.f3674b;
                tVar.n(Skills.c(this.f3675c));
                break;
            default:
                t tVar2 = this.f3676d;
                tVar2.f3656h = this.f3674b;
                tVar2.n(Skills.c(this.f3675c));
                break;
        }
        return true;
    }
}

package n0;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: ExtendedLabel.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class r extends ChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ s f2875a;

    r(s sVar) {
        this.f2875a = sVar;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
    public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
        if (this.f2875a.f2885b) {
            return;
        }
        GameAssets.o("click");
    }
}

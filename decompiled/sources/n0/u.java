package n0;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: compiled from: FadingLabelSmall.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class u extends Label {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f2893a;

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Label, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        float fU = GameData.v().u() - this.f2893a;
        float f3 = 0.0f;
        if (fU > 8.0f) {
            setText("");
        } else if (fU != 0.0f) {
            f3 = fU < 1.0f ? 1.0f / fU : fU < 7.0f ? 1.0f : 8.0f - fU;
        }
        setColor(1.0f, 1.0f, 1.0f, f3);
        super.draw(batch, f2);
    }
}

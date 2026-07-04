package n0;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: ExtendedLabel.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class s extends Label {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static NinePatchDrawable f2883c = new NinePatchDrawable(GameAssets.H);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2885b;

    public s(Label.LabelStyle labelStyle) {
        super("", labelStyle);
        this.f2885b = false;
        addListener(new q(0, this));
        addListener(new r(this));
    }

    public final void d() {
        this.f2884a = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Label, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (this.f2884a) {
            getStyle().background = f2883c;
        } else {
            getStyle().background = null;
        }
        super.draw(batch, f2);
    }

    public final void e() {
        this.f2884a = false;
    }
}

package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.android.MainActivity;

/* JADX INFO: compiled from: GPGSConnectButton.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p extends Table {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final float f3937e = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Image f3938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextureRegionDrawable f3939b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextureRegionDrawable f3940c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.badlogic.gdx.e f3941d;

    /* JADX INFO: compiled from: GPGSConnectButton.java */
    final class a extends ClickListener {

        /* JADX INFO: renamed from: q0.p$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GPGSConnectButton.java */
        final class C0058a extends n0.j {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Dialog
            protected final void result(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    l0.e.t();
                }
            }
        }

        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            p pVar = p.this;
            if (((MainActivity) ExiledKingdoms.f()).l()) {
                l0.e.t();
                return true;
            }
            new C0058a(GameString.b("GPLAY_INFO_CONFIRM", false)).show(pVar.getStage());
            return true;
        }
    }

    public p(com.badlogic.gdx.e eVar) {
        Image image = new Image();
        this.f3938a = image;
        this.f3939b = new TextureRegionDrawable(Assets.a("gpgs_off"));
        this.f3940c = new TextureRegionDrawable(Assets.a("gpgs_on"));
        this.f3941d = eVar;
        float f2 = f3937e * 88.0f;
        setWidth(f2);
        setHeight(f2);
        add(image).width(f2).height(f2).expand();
        image.addListener(new a());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        boolean zL = ((MainActivity) ExiledKingdoms.f()).l();
        Image image = this.f3938a;
        if (zL) {
            image.setDrawable(this.f3940c);
        } else {
            image.setDrawable(this.f3939b);
        }
        super.draw(batch, f2);
        validate();
    }
}

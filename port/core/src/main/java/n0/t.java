package n0;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ExtendedTextButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class t extends TextButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static BitmapFont f2886e = GameAssets.f3336k0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ControllerCommand f2890d;

    /* JADX INFO: compiled from: ExtendedTextButton.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            t tVar = t.this;
            tVar.f2887a = true;
            if (tVar.f2888b || tVar.isDisabled()) {
                return;
            }
            GameAssets.o("click_hover");
            tVar.f2888b = true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            t tVar = t.this;
            tVar.f2887a = false;
            tVar.f2888b = false;
        }
    }

    /* JADX INFO: compiled from: ExtendedTextButton.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            if (t.this.isDisabled()) {
                return;
            }
            GameAssets.o("click");
        }
    }

    public t(String str, Skin skin, String str2) {
        super(str, skin, str2);
        this.f2888b = false;
        this.f2889c = false;
        this.f2890d = null;
        d(false);
        if (ExiledKingdoms.f3378h) {
            getLabel().setFontScale(0.99f);
        }
    }

    public final void d(boolean z2) {
        this.f2889c = z2;
        addListener(new a());
        addListener(new b());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        if (ExiledKingdoms.f3378h && this.f2887a && this.f2890d != null) {
            BitmapFont bitmapFont = f2886e;
            Color color = bitmapFont.getCache().getColor();
            float scaleX = bitmapFont.getScaleX();
            float scaleY = bitmapFont.getScaleY();
            if (ExiledKingdoms.f3378h) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(z.f2925i0);
                bitmapFont.draw(batch, "[" + this.f2890d.toString() + "]", ((getWidth() / 2.0f) + getX()) - 16.0f, getY() + 3.0f);
            }
            bitmapFont.getCache().setColor(color);
            bitmapFont.getData().setScale(scaleX, scaleY);
        }
    }

    public final void e(ControllerCommand controllerCommand) {
        this.f2890d = controllerCommand;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public final Color getColor() {
        return (this.f2889c || !this.f2887a || isDisabled()) ? super.getColor() : Color.LIGHT_GRAY;
    }
}

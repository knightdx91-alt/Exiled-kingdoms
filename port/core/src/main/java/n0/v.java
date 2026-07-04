package n0;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: FlashingImageButton.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class v extends ImageButton {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static BitmapFont f2894j = GameAssets.f3336k0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Color f2896b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2898d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Color f2899e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f2900f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f2901g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2902h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f2903i;

    public v(ImageButton.ImageButtonStyle imageButtonStyle, Color color, boolean z2) {
        super(imageButtonStyle);
        this.f2896b = new Color();
        this.f2899e = new Color();
        Color color2 = new Color(color);
        this.f2899e = color2;
        setColor(color2);
        if (z2) {
            reset();
        }
        this.f2900f = (1.0f - color2.f1680r) / 30.0f;
        this.f2901g = (1.0f - color2.f1679g) / 30.0f;
        this.f2902h = (1.0f - color2.f1678b) / 30.0f;
        this.f2903i = (1.0f - color2.f1677a) / 30.0f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void b() {
        int i2;
        if (this.f2898d == 0) {
            getImage().setColor(1.0f, 1.0f, 1.0f, 1.0f);
            return;
        }
        float fB = (int) ((GameLevel.b() - this.f2897c) * 100.0f);
        int i3 = this.f2898d;
        boolean z2 = (i3 == 1) | (i3 == 3) | (i3 == 5);
        float f2 = this.f2903i;
        float f3 = this.f2902h;
        float f4 = this.f2901g;
        float f5 = this.f2900f;
        Color color = this.f2899e;
        if (!z2) {
            i2 = 2;
        } else if (fB < 30.0f) {
            setColor((fB * f5) + color.f1680r, (fB * f4) + color.f1679g, (fB * f3) + color.f1678b, (fB * f2) + color.f1677a);
            i2 = 2;
        } else {
            switch (i3) {
                case 1:
                    i2 = 2;
                    this.f2898d = 2;
                    break;
                case 2:
                    this.f2898d = 3;
                    i2 = 2;
                    break;
                case 3:
                    this.f2898d = 4;
                    i2 = 2;
                    break;
                case 4:
                    this.f2898d = 5;
                    i2 = 2;
                    break;
                case 5:
                    this.f2898d = 6;
                    i2 = 2;
                    break;
                case 6:
                    this.f2898d = 0;
                    i2 = 2;
                    break;
                default:
                    i2 = 2;
                    break;
            }
            this.f2897c = GameLevel.b();
            fB = 0.0f;
        }
        int i4 = this.f2898d;
        if ((i4 == i2) | (i4 == 4) | (i4 == 6)) {
            if (fB < 30.0f) {
                setColor(1.0f - (f5 * fB), 1.0f - (f4 * fB), 1.0f - (f3 * fB), 1.0f - (fB * f2));
            } else {
                switch (i4) {
                    case 1:
                        this.f2898d = 2;
                        break;
                    case 2:
                        this.f2898d = 3;
                        break;
                    case 3:
                        this.f2898d = 4;
                        break;
                    case 4:
                        this.f2898d = 5;
                        break;
                    case 5:
                        this.f2898d = 6;
                        break;
                    case 6:
                        this.f2898d = 0;
                        break;
                }
                setColor(color);
            }
        }
        getImage().setColor(getColor());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ImageButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Color color = getColor();
        Color color2 = this.f2896b;
        color2.set(color);
        if (isDisabled()) {
            setColor(Color.GRAY);
        }
        super.draw(batch, f2);
        setColor(color2);
        if (ExiledKingdoms.f3378h && this.f2895a && !GameLevel.l() && z.v().w() == 0) {
            BitmapFont bitmapFont = f2894j;
            Color color3 = bitmapFont.getCache().getColor();
            float scaleX = bitmapFont.getScaleX();
            float scaleY = bitmapFont.getScaleY();
            if (ExiledKingdoms.f3378h) {
                bitmapFont.getData().setScale(0.5f);
                bitmapFont.getCache().setColor(z.f2925i0);
                bitmapFont.draw(batch, "[" + Settings.f3419b.interact.toString() + "]", getX() + 44.0f, getY() + 3.0f);
            }
            bitmapFont.getCache().setColor(color3);
            bitmapFont.getData().setScale(scaleX, scaleY);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table
    public final void reset() {
        this.f2897c = GameLevel.b();
        this.f2898d = 1;
    }
}

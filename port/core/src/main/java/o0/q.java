package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.Rules.Skill;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;

/* JADX INFO: compiled from: SkillImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class q extends Image {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static NinePatchDrawable f3630e = new NinePatchDrawable(GameAssets.P);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f3631f = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextureRegionDrawable f3632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextureRegionDrawable f3633b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3634c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Color f3635d;

    public q(Skill skill, int i2) {
        this.f3632a = null;
        this.f3633b = null;
        if (skill == null) {
            this.f3632a = new TextureRegionDrawable(Assets.a("unknown"));
            this.f3633b = new TextureRegionDrawable(Assets.e("skill_bg0"));
        } else {
            this.f3632a = new TextureRegionDrawable(skill.e());
            this.f3633b = new TextureRegionDrawable(Assets.e("skill_bg" + i2));
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (this.f3634c) {
            this.f3635d = new Color(batch.getColor().f1680r, batch.getColor().f1679g, batch.getColor().f1678b, batch.getColor().f1677a);
            batch.setColor(Color.ORANGE);
        }
        this.f3633b.draw(batch, getX(), getY(), getWidth(), getHeight());
        f3630e.draw(batch, getX(), getY(), getWidth(), getHeight());
        if (this.f3634c) {
            batch.setColor(this.f3635d);
        }
        TextureRegionDrawable textureRegionDrawable = this.f3632a;
        if (textureRegionDrawable != null) {
            setDrawable(textureRegionDrawable);
            if (getDrawable() != null) {
                Drawable drawable = getDrawable();
                float x2 = getX();
                float f3 = f3631f;
                float f4 = 4.0f * f3;
                float f5 = f3 * 8.0f;
                drawable.draw(batch, x2 + f4, getY() + f4, getWidth() - f5, getHeight() - f5);
            }
        }
        validate();
    }
}

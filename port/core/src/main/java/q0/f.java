package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ClassImage.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f extends Image {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static NinePatchDrawable f3858f = new NinePatchDrawable(GameAssets.P);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static NinePatchDrawable f3859g = new NinePatchDrawable(GameAssets.R);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final float f3860h = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextureRegionDrawable f3861a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextureRegionDrawable f3862b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextureRegionDrawable f3863c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3864d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Rules.CharacterClass f3865e;

    public f(Rules.CharacterClass characterClass, boolean z2) {
        this.f3861a = null;
        this.f3862b = null;
        this.f3863c = null;
        this.f3864d = z2;
        this.f3865e = characterClass;
        if (characterClass == Rules.CharacterClass.f3258a) {
            this.f3861a = new TextureRegionDrawable(Assets.a("class_warrior_on"));
            this.f3862b = new TextureRegionDrawable(Assets.a("class_warrior_off"));
        }
        if (characterClass == Rules.CharacterClass.f3259b) {
            this.f3861a = new TextureRegionDrawable(Assets.a("class_rogue_on"));
            this.f3862b = new TextureRegionDrawable(Assets.a("class_rogue_off"));
        }
        if (characterClass == Rules.CharacterClass.f3260c) {
            this.f3861a = new TextureRegionDrawable(Assets.a("class_cleric_on"));
            this.f3862b = new TextureRegionDrawable(Assets.a("class_cleric_off"));
        }
        if (characterClass == Rules.CharacterClass.f3261d) {
            this.f3861a = new TextureRegionDrawable(Assets.a("class_mage_on"));
            this.f3862b = new TextureRegionDrawable(Assets.a("class_mage_off"));
        }
        this.f3863c = new TextureRegionDrawable(Assets.a("locked_gray"));
    }

    public final void a(Rules.CharacterClass characterClass) {
        if (characterClass == this.f3865e) {
            this.f3864d = true;
        } else {
            this.f3864d = false;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (this.f3864d) {
            f3859g.draw(batch, getX(), getY(), getWidth(), getHeight());
        } else {
            f3858f.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (this.f3864d) {
            setDrawable(this.f3861a);
        } else {
            Rules.CharacterClass characterClass = Rules.CharacterClass.f3260c;
            Rules.CharacterClass characterClass2 = this.f3865e;
            if ((characterClass2 == characterClass || characterClass2 == Rules.CharacterClass.f3261d) && !ExiledKingdoms.f3371a) {
                setDrawable(this.f3863c);
            } else {
                setDrawable(this.f3862b);
            }
        }
        if (getDrawable() != null) {
            Drawable drawable = getDrawable();
            float x2 = getX();
            float f3 = f3860h;
            float f4 = 4.0f * f3;
            float f5 = f3 * 8.0f;
            drawable.draw(batch, x2 + f4, getY() + f4, getWidth() - f5, getHeight() - f5);
        }
        validate();
    }
}

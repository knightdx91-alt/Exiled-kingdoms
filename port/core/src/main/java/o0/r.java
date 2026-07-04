package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.Skills;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: SkillInfoWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class r extends Window {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static float f3636c = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private p f3637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CharacterSheet f3638b;

    /* JADX INFO: compiled from: SkillInfoWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            r.this.a();
            return true;
        }
    }

    public r() {
        super("", Assets.g());
        n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        setVisible(false);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3636c * 750.0f);
        setHeight(f3636c * 680.0f);
        if (getHeight() < 320.0f) {
            setHeight(320.0f);
        }
        if (getWidth() < 476.0f) {
            setWidth(476.0f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        if (ExiledKingdoms.f3378h) {
            f3636c = 0.8f;
            setWidth(600.0f);
            setHeight(544.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        p pVar = new p(null, Skills.c("whirlwind"), Boolean.FALSE);
        this.f3637a = pVar;
        row().colspan(3).left().pad(0.0f).height(2.0f).width(getWidth() - (f3636c * 8.0f));
        add(pVar).height(f3636c * 570.0f);
        row().align(4).padBottom(f3636c * 4.0f).bottom().spaceTop(f3636c * 15.0f);
        add(new Label("", Assets.g(), "menuLabelStyle"));
        add(new Label("", Assets.g(), "menuLabelStyle"));
        add(tVar).bottom().width(f3636c * 250.0f).right().padRight(f3636c * 15.0f).height(f3636c * 50.0f);
        tVar.addListener(new a());
    }

    public final void a() {
        setVisible(false);
        this.f3638b.d0();
        n0.z.v().V.X();
        n0.z.v().Y();
        GameLevel.n(false);
    }

    public final void b(CharacterSheet characterSheet, Skill skill) {
        setVisible(true);
        this.f3638b = characterSheet;
        this.f3637a.b(skill, characterSheet, false, true);
    }
}

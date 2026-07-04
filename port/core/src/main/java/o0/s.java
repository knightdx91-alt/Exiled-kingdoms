package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import k0.a;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.Skills;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: SkillTrainWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class s extends Window {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static float f3640e = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.t f3641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public p f3642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CharacterSheet f3643c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Skill f3644d;

    /* JADX INFO: compiled from: SkillTrainWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            s sVar = s.this;
            if (sVar.f3641a.isDisabled()) {
                return true;
            }
            s.b(sVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillTrainWindow.java */
    final class b extends InputListener {
        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillTrainWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            s.this.c();
            return true;
        }
    }

    public s() {
        super("", Assets.g());
        n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        n0.t tVar2 = new n0.t("", Assets.g(), "menuButton");
        this.f3641a = tVar2;
        TextButton textButton = new TextButton("", Assets.g(), "menuButton");
        setVisible(false);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3640e * 720.0f);
        setHeight(f3640e * 680.0f);
        if (getHeight() < 320.0f) {
            setHeight(320.0f);
        }
        if (getWidth() < 476.0f) {
            setWidth(476.0f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        if (ExiledKingdoms.f3378h) {
            f3640e = 0.8f;
            setWidth(576.0f);
            setHeight(544.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        p pVar = new p(GameData.v().player.sheet, Skills.c("whirlwind"), Boolean.FALSE);
        this.f3642b = pVar;
        row().colspan(3).left().pad(0.0f).height(2.0f).width(getWidth() - (f3640e * 8.0f));
        add(pVar).height(f3640e * 570.0f);
        row().align(4).padBottom(f3640e * 4.0f).bottom().spaceTop(f3640e * 15.0f);
        add(tVar2).bottom().width(f3640e * 250.0f).left().padLeft(f3640e * 15.0f).height(f3640e * 50.0f);
        add(new Label("", Assets.g(), "menuLabelStyle"));
        add(tVar).bottom().width(f3640e * 250.0f).right().padRight(f3640e * 15.0f).height(f3640e * 50.0f);
        textButton.setVisible(false);
        tVar2.setDisabled(false);
        tVar2.setText(GameString.b("LEARN", false) + "(5000gp)");
        tVar2.clearListeners();
        tVar2.addListener(new a());
        textButton.addListener(new b());
        tVar.addListener(new c());
    }

    static void b(s sVar) {
        sVar.getClass();
        GameAssets.o("levelup");
        sVar.f3643c.a0(sVar.f3644d.id);
        if (sVar.f3643c.X()) {
            k0.a aVarL = k0.a.l();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            aVarL.b(GameData.v().player.B(), a.EnumC0031a.LEVELUP, 0.0f);
        }
        GameData.v().player.R1(5000);
        sVar.c();
    }

    public final void c() {
        setVisible(false);
        this.f3643c.d0();
        n0.z.v().V.X();
        n0.z.v().Y();
        GameLevel.n(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void d(CharacterSheet characterSheet, Skill skill) {
        setVisible(true);
        this.f3643c = characterSheet;
        this.f3644d = skill;
        this.f3642b.b(skill, characterSheet, false, true);
        n0.t tVar = this.f3641a;
        tVar.setDisabled(false);
        if (!skill.b(characterSheet) || GameData.v().player.h() < 5000) {
            tVar.setDisabled(true);
        } else {
            if (characterSheet.skillSet == null) {
                characterSheet.skillSet = new SkillSet();
            }
            if (characterSheet.skillSet.i()) {
                String str = skill.id;
                if (characterSheet.skillSet == null) {
                    characterSheet.skillSet = new SkillSet();
                }
                if (characterSheet.skillSet.l(str)) {
                }
            }
        }
        GameLevel.n(true);
    }
}

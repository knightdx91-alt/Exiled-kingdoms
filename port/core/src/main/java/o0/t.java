package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.util.Iterator;
import n0.k1;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.Skills;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: SkillWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class t extends Window {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    static float f3647q = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static float f3648r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n0.t f3649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private n0.t f3650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TextButton f3651c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private n0.t f3652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Label f3653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Label f3654f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private CharacterSheet f3655g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3656h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Table f3657i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private p f3658j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private q[] f3659k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f3660l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Skill f3661m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Stage f3662n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f3663o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Skill f3664p;

    /* JADX INFO: compiled from: SkillWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            t tVar = t.this;
            if (tVar.f3651c.isDisabled()) {
                return true;
            }
            t.b(tVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            t tVar = t.this;
            if (tVar.f3650b.isDisabled()) {
                return true;
            }
            t.e(tVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            t tVar = t.this;
            SkillSet skillSet = tVar.f3655g.skillSet;
            CharacterSheet unused = tVar.f3655g;
            skillSet.r();
            tVar.f3655g.d0();
            tVar.q();
            tVar.f3656h = 0;
            n0.z.v().Y();
            if (tVar.f3655g.X()) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                GameData.v().player.T1();
            }
            tVar.l();
            tVar.n(tVar.f3661m);
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillWindow.java */
    final class d extends InputListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            t.this.m();
            return true;
        }
    }

    /* JADX INFO: compiled from: SkillWindow.java */
    final class e extends InputListener {

        /* JADX INFO: compiled from: SkillWindow.java */
        final class a extends k1 {
        }

        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            t tVar = t.this;
            if (tVar.f3655g.L() != 0) {
                new a(GameString.b("SPEND_ALL_SKILLS", false), 0).show(tVar.f3662n);
                return true;
            }
            tVar.m();
            l0.e.s();
            return true;
        }
    }

    public t() {
        super("", Assets.g());
        n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
        this.f3649a = tVar;
        n0.t tVar2 = new n0.t("", Assets.g(), "menuButton");
        this.f3650b = tVar2;
        TextButton textButton = new TextButton(GameString.b("STAT_DETAILS", false), Assets.g(), "menuSmallButton");
        this.f3651c = textButton;
        n0.t tVar3 = new n0.t("", Assets.g(), "menuButton");
        this.f3652d = tVar3;
        Label label = new Label(a.a.o("AVAILABLE_SKILL_POINTS", false, new StringBuilder(), ":"), Assets.g(), "menuLabelStrongStyle");
        Label label2 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3653e = label2;
        Label label3 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3654f = label3;
        Image image = new Image();
        this.f3656h = 0;
        Table table = new Table();
        this.f3657i = table;
        this.f3659k = new q[20];
        this.f3661m = null;
        setVisible(false);
        this.f3663o = false;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3647q * 1120.0f);
        setHeight(f3647q * 700.0f);
        if (getHeight() < 320.0f) {
            setHeight(320.0f);
        }
        if (getWidth() < 476.0f) {
            setWidth(476.0f);
        }
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3648r = f3647q;
        if (ExiledKingdoms.f3378h) {
            f3647q = 0.8f;
            f3648r = 1.0f;
            setWidth(896.0f);
            setHeight(560.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        this.f3660l = f3647q * 72.0f;
        image.setDrawable(new NinePatchDrawable(GameAssets.P));
        row().expandX().top().padTop(f3647q * 10.0f).align(8).padBottom(10.0f * f3647q).height(25.0f * f3647q);
        add(label).center().padLeft(f3647q * 8.0f);
        add(label2).left().align(8);
        add(label3).right().padRight(f3647q * 8.0f);
        label.setFontScale(f3648r);
        label2.setFontScale(f3648r);
        label3.setFontScale(f3648r);
        row().colspan(3).left().pad(0.0f).height(2.0f).width(getWidth() - (f3647q * 8.0f));
        add(image);
        row().colspan(3).left().pad(0.0f).width(getWidth() - (f3647q * 8.0f)).top();
        this.f3658j = new p(this.f3655g, Skills.c("whirlwind"), Boolean.TRUE);
        Table table2 = new Table();
        table2.add(this.f3658j).width(f3647q * 680.0f).spaceRight(f3647q * 20.0f).top().spaceTop(0.0f).padTop(0.0f);
        table2.add(table);
        add(table2).height(f3647q * 550.0f);
        row().align(4).padBottom(f3647q * 4.0f).bottom();
        add(tVar3).bottom().width(f3647q * 250.0f).left().padLeft(f3647q * 15.0f).height(f3647q * 50.0f);
        add(tVar2).bottom().width(f3647q * 370.0f).left().padLeft(f3647q * 15.0f).height(f3647q * 50.0f);
        add(tVar).bottom().width(f3647q * 250.0f).right().padRight(f3647q * 15.0f).height(f3647q * 50.0f);
        tVar3.setVisible(false);
        this.f3661m = null;
        textButton.getLabel().setFontScale(f3648r);
        textButton.addListener(new a());
        tVar2.addListener(new b());
        tVar3.addListener(new c());
        tVar.addListener(new d());
    }

    static void b(t tVar) {
        if (tVar.f3663o) {
            l0.e.B.b(tVar.f3655g, tVar.f3664p);
            l0.e.B.toFront();
        } else {
            n0.z.v().Y.b(tVar.f3655g, tVar.f3664p);
            n0.z.v().Y.toFront();
        }
    }

    static void e(t tVar) {
        if (!tVar.f3664p.b(tVar.f3655g)) {
            new u(GameString.b("SKILL_REQUISITES_ERROR", false), 0).show(tVar.f3662n);
            return;
        }
        tVar.f3655g.skillSet.j(tVar.f3664p.id);
        GameAssets.o("load");
        if (tVar.f3664p.type == Skill.SKILL_TYPE.SKILL_ACTIVE && tVar.f3655g.X()) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.D1(tVar.f3664p.id);
        }
        tVar.f3655g.skillSet.n();
        n0.z.v().Y();
        tVar.n(tVar.f3664p);
        tVar.l();
        tVar.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        q[] qVarArr;
        Label label;
        Table table = this.f3657i;
        table.clear();
        table.pad(f3647q * 10.0f);
        Rules.CharacterClass characterClassC = this.f3655g.stats.c();
        Iterator<Skill> it = Skills.b(characterClassC).iterator();
        int i2 = 0;
        while (true) {
            boolean zHasNext = it.hasNext();
            qVarArr = this.f3659k;
            if (!zHasNext) {
                break;
            }
            Skill next = it.next();
            if (i2 <= 7 && (this.f3655g.X() || next.NPCSkill)) {
                if (i2 == 0) {
                    this.f3661m = next;
                }
                q qVar = new q(next, this.f3655g.skillSet.g(next.id));
                qVarArr[i2] = qVar;
                qVar.clearListeners();
                qVarArr[i2].addListener(new v(this, i2, next.id));
                i2++;
            }
        }
        while (i2 <= 7) {
            q qVar2 = new q(null, 0);
            qVarArr[i2] = qVar2;
            qVar2.clearListeners();
            i2++;
        }
        int iH = Settings.h();
        Rules.CharacterClass characterClass = Rules.CharacterClass.GENERAL;
        if (iH != 2) {
            label = new Label(" " + Rules.CharacterClass.a(characterClassC) + " " + GameString.b("SKILLS", false), Assets.g(), "menuLabelStrongStyle");
        } else if (characterClassC == characterClass) {
            label = new Label(a.a.o("SKILLS", false, new StringBuilder(), " Generales"), Assets.g(), "menuLabelStrongStyle");
        } else {
            label = new Label(GameString.b("SKILLS_LIST", false) + " " + Rules.CharacterClass.a(characterClassC), Assets.g(), "menuLabelStrongStyle");
        }
        label.setFontScale(f3648r);
        table.row().colspan(4);
        table.add(label).center().expandX();
        table.row().pad(f3647q * 6.0f).spaceBottom(f3647q * 6.0f);
        Cell cellAdd = table.add(qVarArr[0]);
        float f2 = this.f3660l;
        cellAdd.width(f2).height(f2);
        table.add(qVarArr[1]).width(f2).height(f2);
        table.add(qVarArr[2]).width(f2).height(f2);
        table.add(qVarArr[3]).width(f2).height(f2);
        table.row().pad(f3647q * 6.0f).spaceBottom(f3647q * 10.0f);
        table.add(qVarArr[4]).width(f2).height(f2);
        table.add(qVarArr[5]).width(f2).height(f2);
        table.add(qVarArr[6]).width(f2).height(f2);
        table.add(qVarArr[7]).width(f2).height(f2);
        int i3 = 8;
        for (Skill skill : Skills.b(characterClass)) {
            if (i3 <= 11 && ((this.f3655g.X() || skill.NPCSkill) && (!skill.id.equals("beast_master") || (this.f3655g.X() && GameData.v().gameVariables.b("trained_beast") > 0)))) {
                q qVar3 = new q(skill, this.f3655g.skillSet.g(skill.id));
                qVarArr[i3] = qVar3;
                qVar3.clearListeners();
                qVarArr[i3].addListener(new w(this, i3, skill.id, 0));
                i3++;
            }
        }
        while (i3 <= 11) {
            q qVar4 = new q(null, 0);
            qVarArr[i3] = qVar4;
            qVar4.clearListeners();
            i3++;
        }
        Label label2 = Settings.h() == 2 ? new Label(a.a.o("SKILLS", false, new StringBuilder(), " Generales"), Assets.g(), "menuLabelStrongStyle") : new Label(" " + Rules.CharacterClass.a(characterClass) + " " + GameString.b("SKILLS", false), Assets.g(), "menuLabelStrongStyle");
        label2.setFontScale(f3648r);
        table.row().colspan(4);
        table.add(label2).center().expandX();
        table.row().pad(f3647q * 10.0f).spaceBottom(f3647q * 10.0f);
        table.add(qVarArr[8]).width(f2).height(f2);
        table.add(qVarArr[9]).width(f2).height(f2);
        table.add(qVarArr[10]).width(f2).height(f2);
        table.add(qVarArr[11]).width(f2).height(f2);
        int i4 = 12;
        for (Skill skill2 : Skills.a()) {
            if (i4 <= 19) {
                CharacterSheet characterSheet = this.f3655g;
                String str = skill2.id;
                if (characterSheet.skillSet == null) {
                    characterSheet.skillSet = new SkillSet();
                }
                if (characterSheet.skillSet.l(str)) {
                    q qVar5 = new q(skill2, this.f3655g.skillSet.g(skill2.id));
                    qVarArr[i4] = qVar5;
                    qVar5.clearListeners();
                    qVarArr[i4].addListener(new w(this, i4, skill2.id, 1));
                    i4++;
                }
            }
        }
        while (i4 <= 19) {
            q qVar6 = new q(null, 0);
            qVarArr[i4] = qVar6;
            qVar6.clearListeners();
            i4++;
        }
        Label label3 = new Label(GameString.b("SPECIALIST_SKILLS", false), Assets.g(), "menuLabelStrongStyle");
        label3.setFontScale(f3648r);
        table.row().colspan(4);
        table.add(label3).center().expandX();
        table.row().pad(f3647q * 6.0f).spaceBottom(f3647q * 6.0f);
        table.add(qVarArr[12]).width(f2).height(f2);
        table.add(qVarArr[13]).width(f2).height(f2);
        table.add(qVarArr[14]).width(f2).height(f2);
        table.add(qVarArr[15]).width(f2).height(f2);
        table.row().pad(f3647q * 6.0f).spaceBottom(f3647q * 6.0f);
        table.add(qVarArr[16]).width(f2).height(f2);
        table.add(qVarArr[17]).width(f2).height(f2);
        table.add(qVarArr[18]).width(f2).height(f2);
        table.add(qVarArr[19]).width(f2).height(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(Skill skill) {
        p pVar = this.f3658j;
        if (pVar == null) {
            this.f3658j = new p(this.f3655g, skill, Boolean.TRUE);
        } else {
            pVar.b(skill, this.f3655g, !this.f3663o, false);
        }
        this.f3658j.row().width(f3647q * 680.0f).space(f3647q * 12.0f);
        p pVar2 = this.f3658j;
        TextButton textButton = this.f3651c;
        pVar2.add(textButton).center().width(f3647q * 100.0f).height(f3647q * 40.0f);
        textButton.setVisible(true);
        int iG = this.f3655g.skillSet.g(skill.id);
        int size = skill.levels.size() - 1;
        n0.t tVar = this.f3650b;
        if (iG >= size) {
            tVar.setDisabled(true);
            tVar.setText(GameString.b("MAX_LEVEL_REACHED", false));
        } else {
            int i2 = iG + 1;
            if (this.f3655g.L() < skill.f(i2).cost) {
                tVar.setDisabled(true);
                StringBuilder sb = new StringBuilder();
                a.a.w("TRAIN", false, sb, " (");
                sb.append(skill.f(i2).cost);
                sb.append(" SP)");
                tVar.setText(sb.toString());
            } else {
                tVar.setDisabled(false);
                StringBuilder sb2 = new StringBuilder();
                a.a.w("TRAIN", false, sb2, " (");
                sb2.append(skill.f(i2).cost);
                sb2.append(" SP)");
                tVar.setText(sb2.toString());
            }
        }
        this.f3664p = skill;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.f3653e.setText("" + this.f3655g.L());
        StringBuilder sb = new StringBuilder("(");
        a.a.w("YOU_ALREADY_USED", false, sb, " ");
        sb.append(this.f3655g.skillSet.h());
        sb.append(" ");
        sb.append(GameString.b("POINTS", false));
        sb.append(")");
        this.f3654f.setText(sb.toString());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        for (int i2 = 0; i2 < 20; i2++) {
            int i3 = this.f3656h;
            q[] qVarArr = this.f3659k;
            if (i2 == i3) {
                qVarArr[i2].f3634c = true;
            } else {
                qVarArr[i2].f3634c = false;
            }
        }
    }

    public final void m() {
        setVisible(false);
        if (!this.f3663o) {
            this.f3655g.d0();
            n0.z.v().V.X();
            n0.z.v().Y();
        }
        Dialog dialog = this.f3658j.f3629c;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public final void o() {
        n0.t tVar = this.f3649a;
        tVar.setText(GameString.b("START_GAME", false));
        n0.t tVar2 = this.f3652d;
        tVar2.setText(GameString.b("RESET", false));
        tVar2.setVisible(true);
        this.f3663o = true;
        tVar.clearListeners();
        tVar.d(false);
        tVar.addListener(new e());
    }

    public final void p(CharacterSheet characterSheet, Stage stage) {
        setVisible(true);
        this.f3655g = characterSheet;
        this.f3662n = stage;
        this.f3656h = 0;
        l();
        n(this.f3661m);
        q();
        this.f3663o = false;
    }
}

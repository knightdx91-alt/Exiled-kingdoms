package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.GameLogic.SkillRequirements;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Skill;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: SkillDetailTable.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class p extends Table {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static float f3625d = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static float f3626e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f3627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3628b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Dialog f3629c;

    public p(CharacterSheet characterSheet, Skill skill, Boolean bool) {
        float f2 = f3625d;
        this.f3627a = f2 * 72.0f;
        f3626e = f2;
        if (ExiledKingdoms.f3378h) {
            f3625d = 0.8f;
            f3626e = 1.0f;
        }
        this.f3627a = f3625d * 72.0f;
        b(skill, characterSheet, bool.booleanValue(), false);
    }

    private Table a(Skill skill, int i2, CharacterSheet characterSheet) {
        SkillSet skillSet;
        float f2 = Settings.M() ? 0.9f : 1.0f;
        Table table = new Table();
        Label label = new Label("", GameAssets.S);
        label.setWrap(true);
        label.setFontScale(f3626e * f2);
        label.setText("[BLUE]" + GameString.b("LEVEL", false) + " " + i2 + ":[] " + skill.f(i2).a());
        if (skill.type == Skill.SKILL_TYPE.f3277a) {
            label.setText(((Object) label.getText()) + ". Cooldown: " + skill.f(i2).cooldown + " " + GameString.b("SECONDS", false) + ".");
            if (skill.f(1).mana_cost > 0) {
                label.setText(((Object) label.getText()) + " Mana: " + skill.f(i2).mana_cost + ".");
            }
        }
        label.setText("[BLACK]" + ((Object) label.getText()) + " [FIREBRICK]  " + GameString.b("COST", false) + ":" + skill.f(i2).cost + " SP[][]");
        table.add(label).center().width(f3625d * 684.0f).padTop(f3625d * 7.0f);
        Table tablePad = new Table().pad(f3625d * 4.0f);
        tablePad.setBackground(new NinePatchDrawable(GameAssets.P));
        if (characterSheet != null && (skillSet = characterSheet.skillSet) != null && skillSet.g(skill.id) == i2) {
            tablePad.setBackground(new NinePatchDrawable(GameAssets.G));
        }
        tablePad.row().width((f3625d * 700.0f) - (this.f3627a * 1.0f)).left();
        tablePad.add(table).center().padLeft(f3625d * 8.0f);
        return tablePad;
    }

    public final void b(Skill skill, CharacterSheet characterSheet, boolean z2, boolean z3) {
        Label label;
        boolean z4;
        String strK;
        SkillSet skillSet;
        p pVar = this;
        pVar.f3628b = z2;
        clear();
        pVar.align(8).align(2).center().top();
        int iG = (characterSheet == null || (skillSet = characterSheet.skillSet) == null) ? 0 : skillSet.g(skill.id);
        row().top().padTop(f3625d * 10.0f).left();
        Table table = new Table();
        int iG2 = characterSheet != null ? characterSheet.skillSet.g(skill.id) : 0;
        Label label2 = new Label(skill.f(iG2).name, Assets.g(), "menuLabelStrongLargeStyle");
        label2.setWrap(true);
        label2.setFontScale(f3626e);
        Label label3 = new Label("", Assets.g(), "menuLabelGreenStrongStyle");
        label3.setWrap(true);
        label3.setFontScale(f3626e);
        Label label4 = new Label("", GameAssets.S);
        label4.setWrap(true);
        label4.setFontScale(f3626e);
        if (characterSheet != null && skill.advanced) {
            StringBuilder sb = new StringBuilder("[BROWN]");
            a.a.w("REQUIRES", false, sb, ":[][BLACK]");
            PlayerRequirements playerRequirements = skill.traitsRequired;
            String strC = playerRequirements == null ? "" : playerRequirements.c(characterSheet);
            SkillRequirements skillRequirements = skill.skillsRequired;
            String strB = skillRequirements == null ? "" : skillRequirements.b(characterSheet);
            if (strC.equals("") && strB.equals("")) {
                strK = a.a.o("NONE", false, new StringBuilder("[BLACK]"), "[]");
            } else if (strC.equals("") || strB.equals("")) {
                strK = a.a.k(strC, strB);
            } else {
                strK = strC + ", " + strB;
            }
            sb.append(strK);
            sb.append("     [BROWN]");
            sb.append(GameString.b("CLASS", false));
            sb.append(":[] ");
            sb.append(skill.skillClass.b(characterSheet));
            sb.append("[]");
            label4.setText(sb.toString());
        }
        q qVar = new q(skill, iG2);
        boolean z5 = pVar.f3628b;
        Skill.SKILL_TYPE skill_type = Skill.SKILL_TYPE.f3277a;
        boolean z6 = z5 && skill.type == skill_type && characterSheet != null && characterSheet.X() && characterSheet.skillSet.g(skill.id) > 0;
        int i2 = iG;
        n0.t tVar = new n0.t(a.a.n("SLOT_NONE", false, new StringBuilder("Slot:")), Assets.g(), "menuSmallButton");
        tVar.setDisabled(true);
        tVar.getLabel().setFontScale(f3626e);
        if (skill.type == skill_type) {
            label = label4;
            label3.setText(GameString.b("ACTIVE_SKILL", false));
            if (z6) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                int iM1 = GameData.v().player.M1(skill.id);
                String strB2 = GameString.b("SLOT_NONE", false);
                if (iM1 >= 0) {
                    strB2 = "" + (iM1 + 1);
                }
                tVar.setText("Slot: " + strB2);
                z4 = false;
                tVar.setDisabled(false);
                tVar.setVisible(true);
            } else {
                z4 = false;
                tVar.setVisible(false);
            }
        } else {
            label = label4;
            z4 = false;
            label3.setText(GameString.b("PASSIVE_SKILL", false));
        }
        if (skill.type == skill_type) {
            label3.setText(GameString.b("ACTIVE_SKILL", z4));
        } else {
            label3.setText(GameString.b("PASSIVE_SKILL", z4));
        }
        table.left();
        table.row().space(f3625d * 10.0f).left().align(8);
        table.add(label2).left();
        table.row().space(f3625d * 10.0f);
        table.add(label3).left();
        Table tablePad = new Table().pad(f3625d * 4.0f);
        tablePad.columnDefaults(3).align(16);
        tablePad.setBackground(new NinePatchDrawable(GameAssets.H));
        tablePad.row().width(f3625d * 680.0f).left();
        Cell cellAdd = tablePad.add(qVar);
        float f2 = pVar.f3627a;
        cellAdd.width(f2).height(f2).left().spaceRight(f3625d * 10.0f);
        tablePad.add(table).left().padLeft(f3625d * 8.0f).width(f3625d * 350.0f).expandX();
        if (skill.type == skill_type && characterSheet != null && characterSheet.X()) {
            tablePad.add(tVar).left().width(f3625d * 110.0f).padRight(f3625d * 4.0f);
            tVar.addListener(new o(pVar, skill.id, skill, tVar));
        }
        if (skill.advanced) {
            tablePad.row().width((f3625d * 680.0f) - (f2 * 1.0f)).left().colspan(3);
            tablePad.add(label).left().padLeft(f3625d * 5.0f);
        }
        Label label5 = new Label("[BLACK]" + skill.c() + "[]", GameAssets.T);
        label5.setWrap(true);
        label5.setFontScale(f3626e);
        Table table2 = new Table();
        table2.row().width(f3625d * 650.0f).spaceTop(f3625d * 5.0f);
        table2.add(tablePad).width(f3625d * 650.0f);
        table2.row();
        table2.add(label5).width(f3625d * 640.0f).spaceTop(f3625d * 20.0f);
        pVar.add(table2).width(f3625d * 700.0f).space(f3625d * 7.0f).top();
        if (z3) {
            row().width(f3625d * 700.0f).spaceTop(f3625d * 10.0f);
            pVar.add(pVar.a(skill, 1, characterSheet)).left();
            row().width(f3625d * 700.0f).spaceTop(f3625d * 7.0f);
            pVar.add(pVar.a(skill, 2, characterSheet)).left();
            if (skill.levels.size() - 1 > 2) {
                row().width(f3625d * 700.0f).space(f3625d * 7.0f).spaceBottom(f3625d * 5.0f);
                pVar.add(pVar.a(skill, 3, characterSheet)).left();
            }
            if (skill.levels.size() - 1 > 3) {
                row().width(f3625d * 700.0f).space(f3625d * 7.0f).spaceBottom(f3625d * 5.0f);
                pVar.add(pVar.a(skill, 4, characterSheet)).left();
            }
        } else {
            row().width(f3625d * 700.0f).space(f3625d * 7.0f);
            Table table3 = new Table();
            Label label6 = new Label(skill.f(i2).a(), Assets.g(), "menuLabelStyle");
            label6.setWrap(true);
            label6.setFontScale(f3626e);
            Label label7 = new Label(a.a.o("CURRENT_LEVEL", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
            Label label8 = new Label(a.a.o("NEXT_LEVEL", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
            label7.setFontScale(f3626e);
            label8.setFontScale(f3626e);
            Label label9 = new Label("", Assets.g(), "menuLabelStyle");
            label9.setWrap(true);
            label9.setFontScale(f3626e);
            if (skill.type == skill_type && i2 > 0) {
                label6.setText(((Object) label6.getText()) + ". Cooldown: " + skill.f(i2).cooldown + " " + GameString.b("SECONDS", false) + ".");
                if (skill.f(i2).mana_cost > 0) {
                    label6.setText(((Object) label6.getText()) + " Mana: " + skill.f(i2).mana_cost);
                }
            }
            if (i2 < skill.levels.size() - 1) {
                int i3 = 1 + i2;
                label9.setText(skill.f(i3).a());
                if (skill.type == skill_type) {
                    label9.setText(((Object) label9.getText()) + ". Cooldown: " + skill.f(i3).cooldown + " " + GameString.b("SECONDS", false) + ".");
                    if (skill.f(i3).mana_cost > 0) {
                        label9.setText(((Object) label9.getText()) + " Mana: " + skill.f(i3).mana_cost);
                    }
                }
            } else {
                label9.setText(GameString.b("MAX_LEVEL_REACHED", false));
            }
            table3.row().width(f3625d * 690.0f).space(f3625d * 15.0f).spaceBottom(0.0f).padLeft(f3625d * 20.0f);
            table3.add(label7);
            table3.row().width(f3625d * 690.0f).padLeft(f3625d * 20.0f);
            table3.add(label6);
            table3.row().width(f3625d * 690.0f).space(f3625d * 15.0f).spaceBottom(0.0f).padLeft(f3625d * 20.0f);
            table3.add(label8);
            table3.row().width(f3625d * 690.0f).space(f3625d * 5.0f).padLeft(f3625d * 20.0f);
            table3.add(label9);
            float f3 = f3625d;
            table3.setSize(f3 * 700.0f * 0.75f, f3 * 500.0f);
            pVar = this;
            pVar.add(table3).center().pad(f3625d * 8.0f);
        }
        float f4 = f3625d;
        pVar.setSize(f4 * 700.0f, f4 * 500.0f);
    }
}

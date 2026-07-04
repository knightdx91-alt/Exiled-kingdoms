package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ChooseSkillSlotDialog.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class h extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f3522b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f3523a;

    public h() {
        super("", Assets.g());
        Button tVar = new n0.t(GameString.b("SKILL_SLOT_NONE", false), Assets.g(), "menuButton");
        Button tVar2 = new n0.t(a.a.o("CANCEL", false, new StringBuilder("  "), "  "), Assets.g(), "menuSmallButton");
        float height = Gdx.graphics.getHeight() / 720.0f;
        this.f3523a = height;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(140.0f * height);
        setHeight(560.0f * height);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3522b = height;
        if (ExiledKingdoms.f3378h) {
            this.f3523a = 0.8f;
            f3522b = 1.0f;
            setWidth(112.0f);
            setHeight(392.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        n0.t tVar3 = new n0.t("[WHITE]Slot 1[]", Assets.g(), "menuSmallButton");
        n0.t tVar4 = new n0.t("[WHITE]Slot 2[]", Assets.g(), "menuSmallButton");
        n0.t tVar5 = new n0.t("[WHITE]Slot 3[]", Assets.g(), "menuSmallButton");
        n0.t tVar6 = new n0.t("[WHITE]Slot 4[]", Assets.g(), "menuSmallButton");
        n0.t tVar7 = new n0.t("[WHITE]Slot 5[]", Assets.g(), "menuSmallButton");
        n0.t tVar8 = new n0.t("[WHITE]Slot 6[]", Assets.g(), "menuSmallButton");
        n0.t tVar9 = new n0.t("[WHITE]Slot 7[]", Assets.g(), "menuSmallButton");
        n0.t tVar10 = new n0.t("[WHITE]Slot 8[]", Assets.g(), "menuSmallButton");
        tVar3.getLabel().setFontScale(f3522b);
        tVar4.getLabel().setFontScale(f3522b);
        tVar5.getLabel().setFontScale(f3522b);
        tVar6.getLabel().setFontScale(f3522b);
        tVar7.getLabel().setFontScale(f3522b);
        tVar8.getLabel().setFontScale(f3522b);
        tVar9.getLabel().setFontScale(f3522b);
        tVar10.getLabel().setFontScale(f3522b);
        padTop(this.f3523a * 30.0f).padBottom(this.f3523a * 30.0f);
        getContentTable().center();
        getButtonTable().defaults().spaceLeft(this.f3523a * 40.0f).width(this.f3523a * 90.0f);
        getButtonTable().row().height(this.f3523a * 80.0f).width(this.f3523a * 80.0f);
        button((Button) tVar10, (Object) 7);
        button((Button) tVar6, (Object) 3);
        getButtonTable().row().height(this.f3523a * 80.0f).width(this.f3523a * 80.0f);
        button((Button) tVar9, (Object) 6);
        button((Button) tVar5, (Object) 2);
        getButtonTable().row().height(this.f3523a * 80.0f).width(this.f3523a * 80.0f);
        button((Button) tVar8, (Object) 5);
        button((Button) tVar4, (Object) 1);
        getButtonTable().row().height(this.f3523a * 80.0f).width(this.f3523a * 80.0f);
        button((Button) tVar7, (Object) 4);
        button((Button) tVar3, (Object) 0);
        getButtonTable().row().colspan(2).height(this.f3523a * 45.0f);
        button(tVar, (Object) (-1));
        getButtonTable().row().colspan(2).spaceTop(this.f3523a * 20.0f);
        button(tVar2, (Object) (-2));
        tVar2.setWidth(this.f3523a * 120.0f);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefHeight() {
        return this.f3523a * 525.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public final float getPrefWidth() {
        return this.f3523a * 280.0f;
    }
}

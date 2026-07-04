package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import n0.b1;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: KeyboardWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c1 extends Window {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2587a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f2588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ArrayList<b1> f2589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ControllerCommand f2590d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f2591e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f2592f;

    /* JADX INFO: compiled from: KeyboardWindow.java */
    final class a extends ClickListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            c1 c1Var = c1.this;
            c1Var.d();
            c1Var.setVisible(false);
        }
    }

    public c1() {
        super("", Assets.g());
        this.f2587a = false;
        Label label = new Label(GameString.b("KEYBOARD_CONFIGURATION", false), Assets.g(), "menuLabelStrongLargeStyle");
        Label label2 = new Label(a.a.o("CONTROLLER_CHOOSE_COMMAND", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f2588b = label2;
        ArrayList<b1> arrayList = new ArrayList<>();
        this.f2589c = arrayList;
        b1 b1Var = new b1(b1.c.ATTACK, this);
        b1 b1Var2 = new b1(b1.c.INTERACT, this);
        b1 b1Var3 = new b1(b1.c.RECOVERY, this);
        b1 b1Var4 = new b1(b1.c.MAP, this);
        b1 b1Var5 = new b1(b1.c.CHARACTER, this);
        b1 b1Var6 = new b1(b1.c.COMPANION, this);
        b1 b1Var7 = new b1(b1.c.LOG, this);
        b1 b1Var8 = new b1(b1.c.ESCAPE, this);
        b1 b1Var9 = new b1(b1.c.QUICKSAVE, this);
        b1 b1Var10 = new b1(b1.c.UP, this);
        b1 b1Var11 = new b1(b1.c.DOWN, this);
        b1 b1Var12 = new b1(b1.c.LEFT, this);
        b1 b1Var13 = new b1(b1.c.RIGHT, this);
        b1 b1Var14 = new b1(b1.c.SKILL1, this);
        b1 b1Var15 = new b1(b1.c.SKILL2, this);
        b1 b1Var16 = new b1(b1.c.SKILL3, this);
        b1 b1Var17 = new b1(b1.c.SKILL4, this);
        b1 b1Var18 = new b1(b1.c.SKILL5, this);
        b1 b1Var19 = new b1(b1.c.SKILL6, this);
        b1 b1Var20 = new b1(b1.c.SKILL7, this);
        b1 b1Var21 = new b1(b1.c.SKILL8, this);
        b1 b1Var22 = new b1(b1.c.ITEM1, this);
        b1 b1Var23 = new b1(b1.c.ITEM2, this);
        b1 b1Var24 = new b1(b1.c.ITEM3, this);
        b1 b1Var25 = new b1(b1.c.ITEM4, this);
        b1 b1Var26 = new b1(b1.c.s, this);
        b1 b1Var27 = new b1(b1.c.B, this);
        b1 b1Var28 = new b1(b1.c.A, this);
        this.f2590d = null;
        t tVar = new t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        this.f2591e = 1.0f;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(1020.0f);
        setHeight(750.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        this.f2592f = 1.0f;
        if (ExiledKingdoms.f3378h) {
            this.f2591e = 0.8f;
            this.f2592f = 1.0f;
            setWidth(816.0f);
            setHeight(600.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        label.setFontScale(this.f2592f);
        label2.setFontScale(this.f2592f);
        arrayList.add(b1Var);
        arrayList.add(b1Var2);
        arrayList.add(b1Var3);
        arrayList.add(b1Var4);
        arrayList.add(b1Var5);
        arrayList.add(b1Var6);
        arrayList.add(b1Var10);
        arrayList.add(b1Var11);
        arrayList.add(b1Var12);
        arrayList.add(b1Var13);
        arrayList.add(b1Var14);
        arrayList.add(b1Var15);
        arrayList.add(b1Var16);
        arrayList.add(b1Var17);
        arrayList.add(b1Var18);
        arrayList.add(b1Var19);
        arrayList.add(b1Var20);
        arrayList.add(b1Var21);
        arrayList.add(b1Var22);
        arrayList.add(b1Var23);
        arrayList.add(b1Var24);
        arrayList.add(b1Var25);
        arrayList.add(b1Var26);
        arrayList.add(b1Var7);
        arrayList.add(b1Var8);
        arrayList.add(b1Var9);
        arrayList.add(b1Var28);
        arrayList.add(b1Var27);
        center();
        row().height(this.f2591e * 25.0f).space(this.f2591e * 2.0f).colspan(2);
        add(label).center().spaceBottom(this.f2591e * 20.0f);
        row().height(this.f2591e * 25.0f).space(this.f2591e * 2.0f).colspan(2);
        add(label2).center().spaceBottom(this.f2591e * 20.0f);
        b(b1Var, b1Var14);
        b(b1Var2, b1Var15);
        b(b1Var3, b1Var16);
        b(b1Var4, b1Var17);
        b(b1Var5, b1Var18);
        b(b1Var6, b1Var19);
        b(b1Var22, b1Var20);
        b(b1Var23, b1Var21);
        b(b1Var24, b1Var10);
        b(b1Var25, b1Var11);
        b(b1Var26, b1Var12);
        b(b1Var7, b1Var13);
        b(b1Var9, b1Var28);
        b(b1Var8, b1Var27);
        row().colspan(2).spaceTop(this.f2591e * 40.0f);
        add(tVar).width(200.0f);
        tVar.clearListeners();
        tVar.addListener(new a());
        setVisible(false);
    }

    private void b(b1 b1Var, b1 b1Var2) {
        float f2 = this.f2591e;
        float f3 = 400.0f * f2;
        float f4 = 32.0f * f2;
        float f5 = 5.0f * f2;
        float f6 = f2 * 130.0f;
        row().height(f4).spaceTop(f5);
        add(b1Var).width(f3).spaceRight(f6).left().padLeft(f6);
        add(b1Var2).width(f3).padRight(f6).left();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f2590d = null;
        this.f2587a = false;
        a.a.x("CONTROLLER_CHOOSE_COMMAND", false, new StringBuilder("[BLACK]"), "[]", this.f2588b);
        Iterator<b1> it = this.f2589c.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
        Settings.v();
    }

    public final void c() {
        d();
        setVisible(false);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        if (!this.f2587a || this.f2590d == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= 255) {
                i2 = -999;
                break;
            } else if (Gdx.input.isKeyPressed(i2)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -999) {
            ControllerCommand controllerCommand = this.f2590d;
            controllerCommand.type = ControllerCommand.commandType.KEY;
            controllerCommand.id = i2;
            d();
        }
    }

    public final void e(ControllerCommand controllerCommand) {
        this.f2587a = true;
        this.f2590d = controllerCommand;
        Label label = this.f2588b;
        StringBuilder sb = new StringBuilder("[BLACK]");
        a.a.w("PRESS_KEY", false, sb, "[] [BLUE]");
        sb.append(Settings.c(this.f2590d));
        sb.append("[]");
        label.setText(sb.toString());
    }

    public final void f() {
        d();
        setVisible(true);
    }
}

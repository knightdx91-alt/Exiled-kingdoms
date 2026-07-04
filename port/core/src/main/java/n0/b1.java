package n0;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.KeyboardConfig;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: KeyConfigTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b1 extends Table {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static NinePatchDrawable f2539g = new NinePatchDrawable(GameAssets.H);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2540a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2541b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ControllerCommand f2542c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    c1 f2543d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Label f2544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Label f2545f;

    /* JADX INFO: compiled from: KeyConfigTable.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            b1 b1Var = b1.this;
            if (b1Var.f2543d.f2587a) {
                return;
            }
            b1Var.f2540a = true;
            if (b1Var.f2541b) {
                return;
            }
            GameAssets.o("click_hover");
            b1Var.f2541b = true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
            b1 b1Var = b1.this;
            b1Var.f2540a = false;
            b1Var.f2541b = false;
        }
    }

    /* JADX INFO: compiled from: KeyConfigTable.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            b1.d(b1.this);
            return true;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: KeyConfigTable.java */
    public enum c {
        UP, DOWN, LEFT, RIGHT, ATTACK, SKILL1, SKILL2, SKILL3, SKILL4, SKILL5, SKILL6, SKILL7, SKILL8, RECOVERY, ITEM1, ITEM2, ITEM3, ITEM4, ITEM5, MAP, CHARACTER, COMPANION, INTERACT, NONE, LOG, QUICKSAVE, ESCAPE, INVTAKE, INVDROP;
    }

    public b1(c cVar, c1 c1Var) {
        Label label = new Label("", Assets.g(), "menuLabelStrongStyle");
        this.f2544e = label;
        Label label2 = new Label("", GameAssets.S);
        this.f2545f = label2;
        this.f2543d = c1Var;
        switch (cVar.ordinal()) {
            case 0:
                this.f2542c = Settings.f3419b.up;
                break;
            case 1:
                this.f2542c = Settings.f3419b.down;
                break;
            case 2:
                this.f2542c = Settings.f3419b.left;
                break;
            case 3:
                this.f2542c = Settings.f3419b.right;
                break;
            case 4:
                this.f2542c = Settings.f3419b.attack;
                break;
            case 5:
                this.f2542c = Settings.f3419b.skill1;
                break;
            case 6:
                this.f2542c = Settings.f3419b.skill2;
                break;
            case 7:
                this.f2542c = Settings.f3419b.skill3;
                break;
            case 8:
                this.f2542c = Settings.f3419b.skill4;
                break;
            case 9:
                this.f2542c = Settings.f3419b.skill5;
                break;
            case 10:
                this.f2542c = Settings.f3419b.skill6;
                break;
            case 11:
                this.f2542c = Settings.f3419b.skill7;
                break;
            case 12:
                this.f2542c = Settings.f3419b.skill8;
                break;
            case 13:
                this.f2542c = Settings.f3419b.recovery;
                break;
            case 14:
                this.f2542c = Settings.f3419b.quickItem1;
                break;
            case 15:
                this.f2542c = Settings.f3419b.quickItem2;
                break;
            case 16:
                this.f2542c = Settings.f3419b.quickItem3;
                break;
            case 17:
                this.f2542c = Settings.f3419b.quickItem4;
                break;
            case 18:
                this.f2542c = Settings.f3419b.quickItem5;
                break;
            case 19:
                this.f2542c = Settings.f3419b.map;
                break;
            case 20:
                this.f2542c = Settings.f3419b.character;
                break;
            case Decal.C4 /* 21 */:
                this.f2542c = Settings.f3419b.companion;
                break;
            case Decal.U4 /* 22 */:
                this.f2542c = Settings.f3419b.interact;
                break;
            case Decal.SIZE /* 24 */:
                this.f2542c = Settings.f3419b.log;
                break;
            case 25:
                this.f2542c = Settings.f3419b.quicksave;
                break;
            case 26:
                this.f2542c = Settings.f3419b.escape;
                break;
            case 27:
                this.f2542c = Settings.f3419b.invTake;
                break;
            case 28:
                this.f2542c = Settings.f3419b.invDrop;
                break;
        }
        e();
        row().center().bottom();
        add(label).width(220.0f).height(25.0f);
        add(label2).width(70.0f).height(25.0f);
        label2.setAlignment(4);
        addListener(new a());
        addListener(new b());
    }

    static void d(b1 b1Var) {
        if (!b1Var.f2541b) {
            GameAssets.o("click");
        }
        b1Var.f2543d.e(b1Var.f2542c);
        a.a.x("PRESS_KEY", false, new StringBuilder("[RED]"), "[]", b1Var.f2545f);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        boolean z2 = this.f2540a;
        Label label = this.f2545f;
        if (z2) {
            label.getStyle().background = f2539g;
        } else {
            label.getStyle().background = null;
        }
        super.draw(batch, f2);
        if (this.f2540a) {
            label.getStyle().background = null;
        }
    }

    public final void e() {
        String strB;
        KeyboardConfig keyboardConfig = Settings.f3419b;
        ControllerCommand controllerCommand = keyboardConfig.attack;
        ControllerCommand controllerCommand2 = this.f2542c;
        if (controllerCommand2 == controllerCommand) {
            strB = GameString.b("KEYBOARD_ATTACK", false);
        } else if (controllerCommand2 == keyboardConfig.interact) {
            strB = GameString.b("KEYBOARD_INTERACT", false);
        } else if (controllerCommand2 == keyboardConfig.character) {
            strB = GameString.b("KEYBOARD_CHARACTER", false);
        } else if (controllerCommand2 == keyboardConfig.map) {
            strB = GameString.b("KEYBOARD_MAP", false);
        } else if (controllerCommand2 == keyboardConfig.companion) {
            strB = GameString.b("KEYBOARD_COMPANION", false);
        } else if (controllerCommand2 == keyboardConfig.invDrop) {
            StringBuilder sb = new StringBuilder();
            a.a.w("INVENTORY", false, sb, ":");
            a.a.w("DROP", false, sb, "/");
            strB = a.a.n("SELL", false, sb);
        } else if (controllerCommand2 == keyboardConfig.invTake) {
            StringBuilder sb2 = new StringBuilder();
            a.a.w("INVENTORY", false, sb2, ":");
            a.a.w("TAKE", false, sb2, "/");
            a.a.w("BUY", false, sb2, "/");
            strB = a.a.n("EQUIP", false, sb2);
        } else {
            strB = controllerCommand2 == keyboardConfig.quicksave ? GameString.b("QUICKSAVE", false) : controllerCommand2 == keyboardConfig.log ? GameString.b("LOG_COMBAT", false) : controllerCommand2 == keyboardConfig.escape ? GameString.b("CONTROLLER_PRESS_BACK", false) : controllerCommand2 == keyboardConfig.recovery ? GameString.b("CONTROLLER_PRESS_RECOVERY", false) : controllerCommand2 == keyboardConfig.skill1 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 1") : controllerCommand2 == keyboardConfig.skill2 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 2") : controllerCommand2 == keyboardConfig.skill3 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 3") : controllerCommand2 == keyboardConfig.skill4 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 4") : controllerCommand2 == keyboardConfig.skill5 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 5") : controllerCommand2 == keyboardConfig.skill6 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 6") : controllerCommand2 == keyboardConfig.skill7 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 7") : controllerCommand2 == keyboardConfig.skill8 ? a.a.o("CONTROLLER_PRESS_SKILL", false, new StringBuilder(), " 8") : controllerCommand2 == keyboardConfig.quickItem1 ? a.a.o("CONTROLLER_PRESS_USE_QUICKSLOT", false, new StringBuilder(), " 1") : controllerCommand2 == keyboardConfig.quickItem2 ? a.a.o("CONTROLLER_PRESS_USE_QUICKSLOT", false, new StringBuilder(), " 2") : controllerCommand2 == keyboardConfig.quickItem3 ? a.a.o("CONTROLLER_PRESS_USE_QUICKSLOT", false, new StringBuilder(), " 3") : controllerCommand2 == keyboardConfig.quickItem4 ? a.a.o("CONTROLLER_PRESS_USE_QUICKSLOT", false, new StringBuilder(), " 4") : controllerCommand2 == keyboardConfig.quickItem5 ? a.a.o("CONTROLLER_PRESS_USE_QUICKSLOT", false, new StringBuilder(), " 5") : controllerCommand2 == keyboardConfig.up ? GameString.b("CONTROLLER_PRESS_UP", false) : controllerCommand2 == keyboardConfig.down ? GameString.b("CONTROLLER_PRESS_DOWN", false) : controllerCommand2 == keyboardConfig.left ? GameString.b("CONTROLLER_PRESS_LEFT", false) : controllerCommand2 == keyboardConfig.right ? GameString.b("CONTROLLER_PRESS_RIGHT", false) : "None";
        }
        this.f2544e.setText(strB);
        this.f2545f.setText("[BLACK]" + controllerCommand2.toString() + "[]");
    }
}

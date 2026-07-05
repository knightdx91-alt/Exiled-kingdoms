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
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
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
    public static final class c {
        public static final c A;
        public static final c B;
        private static final /* synthetic */ c[] C;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2548a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f2549b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final c f2550c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final c f2551d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final c f2552e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final c f2553f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final c f2554g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final c f2555h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final c f2556i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final c f2557j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final c f2558k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final c f2559l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final c f2560m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final c f2561n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final c f2562o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final c f2563p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final c f2564q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final c f2565r;
        public static final c s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final c f2566t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final c f2567u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final c f2568v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final c f2569w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final c f2570x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final c f2571y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final c f2572z;

        static {
            c cVar = new c("UP", 0);
            f2548a = cVar;
            c cVar2 = new c("DOWN", 1);
            f2549b = cVar2;
            c cVar3 = new c("LEFT", 2);
            f2550c = cVar3;
            c cVar4 = new c("RIGHT", 3);
            f2551d = cVar4;
            c cVar5 = new c("ATTACK", 4);
            f2552e = cVar5;
            c cVar6 = new c("SKILL1", 5);
            f2553f = cVar6;
            c cVar7 = new c("SKILL2", 6);
            f2554g = cVar7;
            c cVar8 = new c("SKILL3", 7);
            f2555h = cVar8;
            c cVar9 = new c("SKILL4", 8);
            f2556i = cVar9;
            c cVar10 = new c("SKILL5", 9);
            f2557j = cVar10;
            c cVar11 = new c("SKILL6", 10);
            f2558k = cVar11;
            c cVar12 = new c("SKILL7", 11);
            f2559l = cVar12;
            c cVar13 = new c("SKILL8", 12);
            f2560m = cVar13;
            c cVar14 = new c("RECOVERY", 13);
            f2561n = cVar14;
            c cVar15 = new c("ITEM1", 14);
            f2562o = cVar15;
            c cVar16 = new c("ITEM2", 15);
            f2563p = cVar16;
            c cVar17 = new c("ITEM3", 16);
            f2564q = cVar17;
            c cVar18 = new c("ITEM4", 17);
            f2565r = cVar18;
            c cVar19 = new c("ITEM5", 18);
            s = cVar19;
            c cVar20 = new c("MAP", 19);
            f2566t = cVar20;
            c cVar21 = new c("CHARACTER", 20);
            f2567u = cVar21;
            c cVar22 = new c("COMPANION", 21);
            f2568v = cVar22;
            c cVar23 = new c("INTERACT", 22);
            f2569w = cVar23;
            c cVar24 = new c("NONE", 23);
            c cVar25 = new c("LOG", 24);
            f2570x = cVar25;
            c cVar26 = new c("QUICKSAVE", 25);
            f2571y = cVar26;
            c cVar27 = new c("ESCAPE", 26);
            f2572z = cVar27;
            c cVar28 = new c("INVTAKE", 27);
            A = cVar28;
            c cVar29 = new c("INVDROP", 28);
            B = cVar29;
            C = new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7, cVar8, cVar9, cVar10, cVar11, cVar12, cVar13, cVar14, cVar15, cVar16, cVar17, cVar18, cVar19, cVar20, cVar21, cVar22, cVar23, cVar24, cVar25, cVar26, cVar27, cVar28, cVar29};
        }

        private c() {
            throw null;
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) C.clone();
        }
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

package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import n0.k1;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameWorld.WorldRandomNames;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.PlayerCreation;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: NewGameWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class v extends Window {
    private static int D = 60;
    private a.f A;
    private int B;
    private Stage C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PlayerCreation f3974a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f3975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f3976c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Label f3977d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextField f3978e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Label f3979f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Image f3980g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Label f3981h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Label f3982i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private q0.f f3983j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private q0.f f3984k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private q0.f f3985l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private q0.f f3986m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Label f3987n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Label f3988o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private q0.o f3989p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private q0.o f3990q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private q0.o f3991r;
    private q0.o s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private q0.o f3992t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private q0.q f3993u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private q0.q f3994v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private n0.t f3995w;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private n0.t f3996z;

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.charClass = Rules.CharacterClass.ROGUE;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            boolean z2 = ExiledKingdoms.f3371a;
            v vVar = v.this;
            if (!z2) {
                v.e(vVar);
                return true;
            }
            GameAssets.o("click");
            vVar.f3974a.charClass = Rules.CharacterClass.CLERIC;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            boolean z2 = ExiledKingdoms.f3371a;
            v vVar = v.this;
            if (!z2) {
                v.e(vVar);
                return true;
            }
            GameAssets.o("click");
            vVar.f3974a.charClass = Rules.CharacterClass.WIZARD;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class d extends InputListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.difficulty = 3;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class e extends InputListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.difficulty = 4;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class f extends InputListener {
        f() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.difficulty = 0;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class g extends InputListener {
        g() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.difficulty = 1;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class h extends InputListener {
        h() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            boolean z2 = ExiledKingdoms.f3371a;
            v vVar = v.this;
            if (!z2) {
                v.e(vVar);
                return true;
            }
            GameAssets.o("click");
            vVar.f3974a.difficulty = 2;
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class i extends InputListener {
        i() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l0.e.C.c(v.this.f3974a.charClass);
            l0.e.C.toFront();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class j implements TextField.TextFieldListener {
        j() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener
        public final void keyTyped(TextField textField, char c2) {
            v vVar = v.this;
            vVar.f3974a.name = vVar.f3978e.getText();
            vVar.h();
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class k extends ChangeListener {
        k() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            v vVar = v.this;
            vVar.f3974a.name = WorldRandomNames.d(vVar.f3974a.gender);
            vVar.h();
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class l extends ChangeListener {
        l() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            GameAssets.o("click");
            v.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class m extends ChangeListener {

        /* JADX INFO: compiled from: NewGameWindow.java */
        final class a extends k1 {
        }

        m() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            v vVar = v.this;
            vVar.f3974a.name = vVar.f3978e.getText();
            GameAssets.o("click");
            if (vVar.f3974a.name.length() > 20 || vVar.f3974a.name.length() < 3) {
                new a(GameString.b("NAME_LENGHT", false), 0).show(vVar.C);
            } else {
                l0.e.q(vVar.f3974a, vVar.B);
                vVar.setVisible(false);
            }
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class n extends ChangeListener {
        n() {
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[PHI: r3
          0x002d: PHI (r3v2 int) = (r3v0 int), (r3v1 int), (r3v3 int), (r3v4 int), (r3v5 int) binds: [B:10:0x002b, B:17:0x003a, B:28:0x004c, B:35:0x005a, B:41:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[PHI: r1
          0x0041: PHI (r1v7 int) = (r1v5 int), (r1v10 int), (r1v11 int) binds: [B:20:0x003f, B:38:0x005f, B:44:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x006f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            int i2;
            int i3;
            GameAssets.o("click");
            v vVar = v.this;
            PlayerCreation playerCreation = vVar.f3974a;
            int i4 = vVar.f3974a.portraitIndex;
            Character.Gender gender = vVar.f3974a.gender;
            Assets assets = Assets.f3309a;
            int iOrdinal = gender.ordinal();
            int i5 = 1;
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    i5 = 0;
                }
                playerCreation.portraitIndex = i5;
                vVar.h();
            }
            if (i4 < 8) {
                i5 = 1 + i4;
            } else {
                i2 = 61;
                if (i4 != 8) {
                    if (i4 < 61 || i4 > 69) {
                        i2 = 106;
                        if (i4 == 70) {
                            i5 = i2;
                        } else {
                            i3 = 107;
                            if (i4 == 106) {
                                i5 = i3;
                            } else if (i4 != 107) {
                            }
                        }
                    }
                }
            }
            playerCreation.portraitIndex = i5;
            vVar.h();
            if (i4 >= 7) {
                i2 = 41;
                if (i4 != 7) {
                    if (i4 < 41 || i4 > 48) {
                        i2 = 62;
                        if (i4 != 49) {
                            i3 = 63;
                            if (i4 != 62) {
                                i2 = 64;
                                if (i4 != 63) {
                                    i3 = 65;
                                    if (i4 != 64) {
                                        if (i4 != 65) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            playerCreation.portraitIndex = i5;
            vVar.h();
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class o extends ChangeListener {
        o() {
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x002e A[PHI: r1
          0x002e: PHI (r1v5 int) = (r1v3 int), (r1v7 int), (r1v8 int) binds: [B:25:0x0049, B:10:0x002c, B:16:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[PHI: r3
          0x0034: PHI (r3v1 int) = (r3v0 int), (r3v2 int), (r3v3 int) binds: [B:28:0x004e, B:13:0x0032, B:19:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            int i2;
            int i3;
            int i4;
            GameAssets.o("click");
            v vVar = v.this;
            PlayerCreation playerCreation = vVar.f3974a;
            int i5 = vVar.f3974a.portraitIndex;
            Character.Gender gender = vVar.f3974a.gender;
            Assets assets = Assets.f3309a;
            int iOrdinal = gender.ordinal();
            if (iOrdinal == 0) {
                i2 = 107;
                if (i5 != 1) {
                    i3 = 106;
                    if (i5 != 107) {
                        if (i5 == 106) {
                            i4 = 70;
                        } else if (i5 == 61) {
                            i4 = 8;
                        }
                    }
                }
            } else if (iOrdinal != 1) {
                i4 = 0;
            } else if (i5 == 41) {
                i4 = 7;
            } else {
                i2 = 65;
                if (i5 == 1) {
                    i4 = i2;
                } else {
                    i3 = 64;
                    if (i5 == 65) {
                        i4 = i3;
                    } else {
                        i2 = 63;
                        if (i5 != 64) {
                            i3 = 62;
                            if (i5 != 63) {
                                i4 = i5 == 62 ? 49 : i5 - 1;
                            }
                        }
                    }
                }
            }
            playerCreation.portraitIndex = i4;
            vVar.h();
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class p extends InputListener {
        p() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            PlayerCreation playerCreation = vVar.f3974a;
            Character.Gender gender = Character.Gender.Male;
            playerCreation.gender = gender;
            vVar.f3974a.portraitIndex = Assets.l(gender);
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class q extends InputListener {
        q() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            PlayerCreation playerCreation = vVar.f3974a;
            Character.Gender gender = Character.Gender.Female;
            playerCreation.gender = gender;
            vVar.f3974a.portraitIndex = Assets.l(gender);
            vVar.h();
            return true;
        }
    }

    /* JADX INFO: compiled from: NewGameWindow.java */
    final class r extends InputListener {
        r() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            GameAssets.o("click");
            v vVar = v.this;
            vVar.f3974a.charClass = Rules.CharacterClass.WARRIOR;
            vVar.h();
            return true;
        }
    }

    public v() {
        float f2;
        super("", Assets.g());
        Label label = new Label(GameString.b("CREATE_YOUR_HERO", false), Assets.g(), "menuLabelStrongVeryLargeStyle");
        this.f3975b = label;
        Label label2 = new Label(a.a.o("NAME", false, new StringBuilder(), ":"), Assets.g(), "menuLabelStrongStyle");
        this.f3976c = label2;
        Label label3 = new Label(a.a.o("CHOOSE_PORTRAIT", false, new StringBuilder(), ":"), Assets.g(), "menuLabelStrongStyle");
        this.f3977d = label3;
        TextField textField = new TextField("", Assets.g());
        this.f3978e = textField;
        ImageButton imageButton = new ImageButton(GameAssets.f3351s0);
        Label label4 = new Label(a.a.o("GENDER", false, new StringBuilder(), ":"), Assets.g(), "menuLabelStrongStyle");
        this.f3979f = label4;
        Image image = new Image();
        this.f3980g = image;
        ImageButton imageButton2 = new ImageButton(GameAssets.f3344o0);
        ImageButton imageButton3 = new ImageButton(GameAssets.f3346p0);
        Label label5 = new Label("", Assets.g(), "menuLabelStrongStyle");
        this.f3981h = label5;
        Label label6 = new Label("", GameAssets.S);
        this.f3982i = label6;
        q0.f fVar = new q0.f(Rules.CharacterClass.WARRIOR, true);
        this.f3983j = fVar;
        q0.f fVar2 = new q0.f(Rules.CharacterClass.ROGUE, false);
        this.f3984k = fVar2;
        q0.f fVar3 = new q0.f(Rules.CharacterClass.CLERIC, false);
        this.f3985l = fVar3;
        q0.f fVar4 = new q0.f(Rules.CharacterClass.WIZARD, false);
        this.f3986m = fVar4;
        Label label7 = new Label("", Assets.g(), "menuLabelStrongStyle");
        this.f3987n = label7;
        Label label8 = new Label("", GameAssets.S);
        this.f3988o = label8;
        q0.o oVar = new q0.o(4, false);
        this.f3989p = oVar;
        q0.o oVar2 = new q0.o(3, false);
        this.f3990q = oVar2;
        q0.o oVar3 = new q0.o(0, true);
        this.f3991r = oVar3;
        q0.o oVar4 = new q0.o(1, false);
        this.s = oVar4;
        q0.o oVar5 = new q0.o(2, false);
        this.f3992t = oVar5;
        Character.Gender gender = Character.Gender.Male;
        q0.q qVar = new q0.q(gender, true);
        this.f3993u = qVar;
        q0.q qVar2 = new q0.q(Character.Gender.Female, false);
        this.f3994v = qVar2;
        n0.t tVar = new n0.t(GameString.b("CANCEL", false), Assets.g(), "menuButton");
        this.f3995w = tVar;
        n0.t tVar2 = new n0.t(GameString.b("START_GAME", false), Assets.g(), "menuButton");
        this.f3996z = tVar2;
        setVisible(false);
        this.f3974a = new PlayerCreation("", Assets.l(gender));
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(1260.0f * fMin);
        setHeight(700.0f * fMin);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        if (ExiledKingdoms.f3378h) {
            setWidth(1112.0f);
            setHeight(560.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
            D = (int) 36.0f;
            fMin = 0.8f;
            f2 = 1.0f;
        } else {
            f2 = fMin;
        }
        label.setFontScale(f2);
        label2.setFontScale(f2);
        label3.setFontScale(f2);
        label4.setFontScale(f2);
        label5.setFontScale(f2);
        label6.setWrap(true);
        label6.setFontScale(f2);
        label7.setFontScale(f2);
        label8.setFontScale(f2);
        label8.setWrap(true);
        this.f3974a.difficulty = 0;
        float f3 = 20.0f * fMin;
        float f4 = 40.0f * fMin;
        row().colspan(2).space(f3).height(f4).space(fMin * 25.0f);
        float f5 = 50.0f * fMin;
        add(label).center().height(f5);
        columnDefaults(0).width(getWidth() / 2.5f).align(1);
        columnDefaults(1).width(getWidth() / 2.5f).align(1);
        row().center();
        Table table = new Table();
        Table table2 = new Table();
        float f6 = fMin * 30.0f;
        add(table).spaceRight(f6);
        add(table2);
        table.top();
        table.row().expandX().left().height(f5).space(f5);
        Table table3 = new Table();
        textField.getStyle().font = GameAssets.f3324e0;
        textField.getStyle().font.getData().setScale(f2);
        table3.row();
        table3.add(label2).width(100.0f * fMin);
        table3.add(textField).width(260.0f * fMin).height(f4).spaceRight(10.0f);
        float f7 = 5.0f * fMin;
        table3.add(imageButton).width(f4).height(f4).padLeft(f7);
        table3.pack();
        Cell cellAdd = table.add(table3);
        float f8 = 80.0f * fMin;
        cellAdd.padRight(f8);
        table.row().expandX().left().height(fMin * 90.0f).space(f5);
        Table table4 = new Table();
        table4.row().colspan(2);
        float f9 = 10.0f * fMin;
        table4.add(label4).space(f9);
        table4.row().center().spaceRight(f6);
        float f10 = fMin * 85.0f;
        table4.add(qVar).width(f10).height(f10).right();
        table4.add(qVar2).width(f10).height(f10).left();
        Cell cellCenter = table.add(table4).center();
        float f11 = 120.0f * fMin;
        cellCenter.padRight(f11);
        float f12 = 200.0f * fMin;
        table.row().expandX().left().height(f12).space(f5);
        Table table5 = new Table();
        table5.columnDefaults(0);
        table5.row().colspan(3);
        table5.add(label3);
        table5.row().center().spaceRight(f3);
        table5.add(imageButton2).width(D * fMin).height(D * fMin);
        float f13 = 150.0f * fMin;
        table5.add(image).width(f13).height(f13);
        table5.add(imageButton3).width(D * fMin).height(D * fMin);
        Cell cellAdd2 = table.add(table5);
        float f14 = 60.0f * fMin;
        cellAdd2.spaceTop(f14).center().padRight(f11);
        table2.row().height(f12).space(f6);
        float f15 = 86.0f * fMin;
        float f16 = 680.0f * fMin;
        Table table6 = new Table();
        table6.row().center().spaceRight(f3);
        table6.add(new Label("  ", Assets.g(), "menuLabelStrongStyle")).pad(f7);
        table6.add(fVar).width(f15).height(f15).center();
        table6.add(fVar2).width(f15).height(f15).center();
        table6.add(fVar3).width(f15).height(f15).center();
        table6.add(fVar4).width(f15).height(f15).center();
        table6.add(new Label("  ", Assets.g(), "menuLabelStrongStyle")).pad(f7);
        table6.row().colspan(6);
        float f17 = 12.0f * fMin;
        table6.add(label5).width(f16).left().spaceTop(f17);
        table6.row().colspan(6);
        table6.add(label6).width(f16).height(f8).spaceBottom(f3).align(2);
        table2.add(table6).top();
        table2.row().height(f12).space(f6).spaceTop(f4);
        Table table7 = new Table();
        table7.row().center().spaceRight(f3);
        float f18 = 110.0f * fMin;
        table7.add(oVar).width(f18).height(f18).right();
        table7.add(oVar2).width(f18).height(f18).right();
        table7.add(oVar3).width(f18).height(f18).right();
        table7.add(oVar4).width(f18).height(f18).center();
        table7.add(oVar5).width(f18).height(f18).left();
        table7.row().colspan(5).spaceTop(f17);
        table7.add(label7).width(f16);
        table7.row().colspan(5).top();
        table7.add(label8).width(f16).height(f8).spaceBottom(f9).align(2);
        table2.add(table7).top();
        row().center().spaceRight(f5).spaceTop(f5);
        float f19 = fMin * 240.0f;
        add(tVar).width(f19).height(f14).right();
        add(tVar2).width(f19).height(f14).left();
        textField.setTextFieldListener(new j());
        imageButton.addListener(new k());
        tVar.addListener(new l());
        tVar2.addListener(new m());
        imageButton3.addListener(new n());
        imageButton2.addListener(new o());
        qVar.addListener(new p());
        qVar2.addListener(new q());
        fVar.addListener(new r());
        fVar2.addListener(new a());
        fVar3.addListener(new b());
        fVar4.addListener(new c());
        oVar2.addListener(new d());
        oVar.addListener(new e());
        oVar3.addListener(new f());
        oVar4.addListener(new g());
        oVar5.addListener(new h());
        label6.addListener(new i());
    }

    static void e(v vVar) {
        vVar.getClass();
        new w(vVar).show(vVar.C);
    }

    public final void g(int i2, a.f fVar, Stage stage) {
        this.C = stage;
        this.A = fVar;
        this.f3975b.setText(GameString.b("CREATE_YOUR_HERO", false));
        this.f3976c.setText(GameString.b("NAME", false));
        this.f3977d.setText(GameString.b("CHOOSE_PORTRAIT", false));
        this.f3979f.setText(GameString.b("GENDER", false));
        this.f3995w.setText(GameString.b("CANCEL", false));
        this.f3996z.setText(GameString.b("NEXT", false));
        this.B = i2;
        Character.Gender gender = Character.Gender.Male;
        this.f3974a = new PlayerCreation(WorldRandomNames.d(gender), Assets.l(gender));
        setVisible(true);
        h();
    }

    public final void h() {
        this.f3978e.setText(this.f3974a.name);
        Image image = this.f3980g;
        PlayerCreation playerCreation = this.f3974a;
        image.setDrawable(new TextureRegionDrawable(Assets.n(playerCreation.gender, playerCreation.portraitIndex)));
        Label label = this.f3981h;
        StringBuilder sb = new StringBuilder();
        a.a.w("CLASS", false, sb, ": ");
        sb.append(Rules.CharacterClass.a(this.f3974a.charClass));
        label.setText(sb.toString());
        Label label2 = this.f3982i;
        StringBuilder sb2 = new StringBuilder("[BLACK]");
        int iOrdinal = this.f3974a.charClass.ordinal();
        sb2.append(iOrdinal != 0 ? iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? "" : GameString.b("MAGE_DESCRIPTION2", false) : GameString.b("CLERIC_DESCRIPTION", false) : GameString.b("ROGUE_DESCRIPTION", false) : GameString.b("WARRIOR_DESCRIPTION", false));
        sb2.append("[]");
        label2.setText(sb2.toString());
        this.f3983j.a(this.f3974a.charClass);
        this.f3984k.a(this.f3974a.charClass);
        this.f3985l.a(this.f3974a.charClass);
        this.f3986m.a(this.f3974a.charClass);
        this.f3993u.a(this.f3974a.gender);
        this.f3994v.a(this.f3974a.gender);
        int i2 = this.f3974a.difficulty;
        Label label3 = this.f3988o;
        Label label4 = this.f3987n;
        if (i2 == 0) {
            StringBuilder sb3 = new StringBuilder();
            a.a.w("DIFFICULTY_LEVEL", false, sb3, ": ");
            sb3.append(GameString.b("DL_NORMAL", false));
            label4.setText(sb3.toString());
            a.a.x("DL_DESC_NORMAL", false, new StringBuilder("[BLACK]"), "[]", label3);
        }
        if (this.f3974a.difficulty == 1) {
            StringBuilder sb4 = new StringBuilder();
            a.a.w("DIFFICULTY_LEVEL", false, sb4, ": ");
            sb4.append(GameString.b("DL_HARD", false));
            label4.setText(sb4.toString());
            a.a.x("DL_DESC_HARD", false, new StringBuilder("[BLACK]"), "[]", label3);
        }
        if (this.f3974a.difficulty == 2) {
            StringBuilder sb5 = new StringBuilder();
            a.a.w("DIFFICULTY_LEVEL", false, sb5, ": ");
            sb5.append(GameString.b("DL_IRONMAN", false));
            label4.setText(sb5.toString());
            a.a.x("DL_DESC_IRONMAN", false, new StringBuilder("[BLACK]"), "[]", label3);
        }
        if (this.f3974a.difficulty == 3) {
            StringBuilder sb6 = new StringBuilder();
            a.a.w("DIFFICULTY_LEVEL", false, sb6, ": ");
            sb6.append(GameString.b("DL_CASUAL", false));
            label4.setText(sb6.toString());
            a.a.x("DL_DESC_CASUAL", false, new StringBuilder("[BLACK]"), "[]", label3);
        }
        if (this.f3974a.difficulty == 4) {
            StringBuilder sb7 = new StringBuilder();
            a.a.w("DIFFICULTY_LEVEL", false, sb7, ": ");
            sb7.append(GameString.b("DL_STORY", false));
            label4.setText(sb7.toString());
            a.a.x("DL_DESC_STORY", false, new StringBuilder("[BLACK]"), "[]", label3);
        }
        this.f3989p.a(this.f3974a.difficulty);
        this.f3990q.a(this.f3974a.difficulty);
        this.f3991r.a(this.f3974a.difficulty);
        this.s.a(this.f3974a.difficulty);
        this.f3992t.a(this.f3974a.difficulty);
    }
}

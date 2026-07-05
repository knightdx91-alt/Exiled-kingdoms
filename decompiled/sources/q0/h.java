package q0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;
import q0.g;

/* JADX INFO: compiled from: ControllerSettingsWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h extends Window {
    private ArrayList<g> A;
    private n0.t B;
    private float C;
    private float D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f3888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f3889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private g f3890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private g f3891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private g f3892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private g f3893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private g f3894h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private g f3895i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private g f3896j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private g f3897k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private g f3898l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private g f3899m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private g f3900n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private g f3901o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private g f3902p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private g f3903q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private g f3904r;
    private g s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private g f3905t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private g f3906u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private g f3907v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private g f3908w;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private ControllerCommand f3909z;

    /* JADX INFO: compiled from: ControllerSettingsWindow.java */
    final class a extends ClickListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            h hVar = h.this;
            hVar.c();
            hVar.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: ControllerSettingsWindow.java */
    final class b extends ClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ g f3911a;

        b(g gVar) {
            this.f3911a = gVar;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public final void clicked(InputEvent inputEvent, float f2, float f3) {
            g gVar = this.f3911a;
            if (gVar.isDisabled()) {
                return;
            }
            h.b(h.this, gVar.f3866a);
        }
    }

    public h() {
        super("", Assets.g());
        this.f3887a = false;
        Label label = new Label(GameString.b("CONTROLLER_SETUP", false), Assets.g(), "menuLabelStrongLargeStyle");
        this.f3888b = label;
        Label label2 = new Label(a.a.o("CONTROLLER_CHOOSE_COMMAND", false, new StringBuilder("[BLACK]"), "[]"), GameAssets.S);
        this.f3889c = label2;
        g gVar = new g(g.a.f3867a);
        this.f3890d = gVar;
        g gVar2 = new g(g.a.f3868b);
        this.f3891e = gVar2;
        g gVar3 = new g(g.a.f3869c);
        this.f3892f = gVar3;
        g gVar4 = new g(g.a.f3870d);
        this.f3893g = gVar4;
        g gVar5 = new g(g.a.f3871e);
        this.f3894h = gVar5;
        g gVar6 = new g(g.a.f3872f);
        this.f3895i = gVar6;
        g gVar7 = new g(g.a.s);
        this.f3896j = gVar7;
        g gVar8 = new g(g.a.f3885t);
        this.f3897k = gVar8;
        g gVar9 = new g(g.a.f3873g);
        this.f3898l = gVar9;
        g gVar10 = new g(g.a.f3874h);
        this.f3899m = gVar10;
        g gVar11 = new g(g.a.f3875i);
        this.f3900n = gVar11;
        g gVar12 = new g(g.a.f3876j);
        this.f3901o = gVar12;
        g gVar13 = new g(g.a.f3877k);
        this.f3902p = gVar13;
        g gVar14 = new g(g.a.f3878l);
        this.f3903q = gVar14;
        g gVar15 = new g(g.a.f3879m);
        this.f3904r = gVar15;
        g gVar16 = new g(g.a.f3880n);
        this.s = gVar16;
        g gVar17 = new g(g.a.f3881o);
        this.f3905t = gVar17;
        g gVar18 = new g(g.a.f3882p);
        this.f3906u = gVar18;
        g gVar19 = new g(g.a.f3883q);
        this.f3907v = gVar19;
        g gVar20 = new g(g.a.f3884r);
        this.f3908w = gVar20;
        this.f3909z = null;
        ArrayList<g> arrayList = new ArrayList<>();
        this.A = arrayList;
        this.B = new n0.t(GameString.b("EXIT", false), Assets.g(), "menuButton");
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        this.C = fMin;
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(850.0f * fMin);
        setHeight(720.0f * fMin);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        this.D = fMin;
        if (ExiledKingdoms.f3378h) {
            this.C = 0.8f;
            this.D = 1.0f;
            setWidth(680.0f);
            setHeight(576.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        label.setFontScale(this.D);
        label2.setFontScale(this.D);
        float f2 = this.D * 0.75f;
        arrayList.add(gVar);
        arrayList.add(gVar2);
        arrayList.add(gVar3);
        arrayList.add(gVar4);
        arrayList.add(gVar5);
        arrayList.add(gVar6);
        arrayList.add(gVar9);
        arrayList.add(gVar10);
        arrayList.add(gVar11);
        arrayList.add(gVar12);
        arrayList.add(gVar13);
        arrayList.add(gVar14);
        arrayList.add(gVar15);
        arrayList.add(gVar16);
        arrayList.add(gVar17);
        arrayList.add(gVar18);
        arrayList.add(gVar19);
        arrayList.add(gVar20);
        arrayList.add(gVar7);
        arrayList.add(gVar8);
        Iterator<g> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().getLabel().setFontScale(f2);
        }
        center();
        row().height(this.C * 25.0f).space(this.C * 2.0f).colspan(4);
        add(this.f3888b).center().spaceBottom(this.C * 20.0f);
        row().height(this.C * 25.0f).space(this.C * 2.0f).colspan(4);
        add(this.f3889c).center().spaceBottom(this.C * 20.0f);
        float f3 = this.C;
        float f4 = 190.0f * f3;
        float f5 = f3 * 90.0f;
        Iterator<g> it2 = this.A.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
        row().height(f5).space(this.C * 2.0f).spaceBottom(this.C * 10.0f);
        a.a.f(this, this.f3890d, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3891e, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3892f, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3893g, f5, f4).spaceRight(this.C * 10.0f);
        row().height(f5).space(this.C * 2.0f).spaceBottom(this.C * 10.0f);
        a.a.f(this, this.f3898l, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3899m, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3900n, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3901o, f5, f4).spaceRight(this.C * 10.0f);
        row().height(f5).space(this.C * 2.0f).spaceBottom(this.C * 10.0f);
        a.a.f(this, this.f3902p, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3903q, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3904r, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.s, f5, f4).spaceRight(this.C * 10.0f);
        row().height(f5).space(this.C * 2.0f).spaceBottom(this.C * 10.0f);
        a.a.f(this, this.f3894h, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3895i, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3908w, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3907v, f5, f4).spaceRight(this.C * 10.0f);
        row().height(f5).space(this.C * 2.0f).spaceBottom(this.C * 10.0f);
        a.a.f(this, this.f3905t, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3906u, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3896j, f5, f4).spaceRight(this.C * 10.0f);
        a.a.f(this, this.f3897k, f5, f4).spaceRight(this.C * 10.0f);
        row().colspan(4).spaceTop(this.C * 40.0f);
        add(this.B).width(200.0f);
        this.B.clearListeners();
        this.B.addListener(new a());
        for (g gVar21 : this.A) {
            gVar21.addListener(new b(gVar21));
        }
        setVisible(false);
    }

    static void b(h hVar, ControllerCommand controllerCommand) {
        hVar.f3887a = true;
        hVar.f3909z = controllerCommand;
        Iterator<g> it = hVar.A.iterator();
        while (it.hasNext()) {
            it.next().setDisabled(true);
        }
        Label label = hVar.f3889c;
        StringBuilder sb = new StringBuilder("[BLACK]");
        a.a.w("CONTROLLER_PRESS", false, sb, "[] [BLUE]");
        sb.append(Settings.c(hVar.f3909z));
        sb.append("[]");
        label.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f3909z = null;
        this.f3887a = false;
        Iterator<g> it = this.A.iterator();
        while (it.hasNext()) {
            it.next().setDisabled(false);
        }
        a.a.x("CONTROLLER_CHOOSE_COMMAND", false, new StringBuilder("[BLACK]"), "[]", this.f3889c);
        Iterator<g> it2 = this.A.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
        Settings.v();
    }

    public final void d() {
        this.f3888b.setText(GameString.b("CONTROLLER_SETUP", false));
        this.B.setText(GameString.b("EXIT", false));
        a.a.x("CONTROLLER_CHOOSE_COMMAND", false, new StringBuilder("[BLACK]"), "[]", this.f3889c);
        setVisible(true);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        boolean z2;
        int i2;
        int i3;
        super.draw(batch, f2);
        if (!this.f3887a || this.f3909z == null) {
            return;
        }
        int i4 = 0;
        if (ExiledKingdoms.g()) {
            i2 = 0;
            while (true) {
                if (i2 >= 255) {
                    i2 = -999;
                    break;
                } else if (ExiledKingdoms.f3384n.b(i2)) {
                    break;
                } else {
                    i2++;
                }
            }
            i3 = 0;
            while (true) {
                if (i3 >= 255) {
                    z2 = false;
                    i3 = -999;
                    break;
                }
                if (ExiledKingdoms.f3384n.c(i3) > 0.2f) {
                    z2 = false;
                    break;
                }
                z2 = true;
                if (ExiledKingdoms.f3384n.c(i3) < -0.2f) {
                    break;
                }
                PovDirection povDirectionA = ExiledKingdoms.f3384n.a(i3);
                PovDirection povDirection = PovDirection.f1617c;
                ControllerCommand.commandType commandtype = ControllerCommand.commandType.f3369e;
                if (povDirectionA == povDirection) {
                    ControllerCommand controllerCommand = this.f3909z;
                    controllerCommand.type = commandtype;
                    controllerCommand.id = 2;
                    c();
                    return;
                }
                if (ExiledKingdoms.f3384n.a(i3) == PovDirection.f1616b) {
                    ControllerCommand controllerCommand2 = this.f3909z;
                    controllerCommand2.type = commandtype;
                    controllerCommand2.id = 1;
                    c();
                    return;
                }
                if (ExiledKingdoms.f3384n.a(i3) == PovDirection.f1618d) {
                    ControllerCommand controllerCommand3 = this.f3909z;
                    controllerCommand3.type = commandtype;
                    controllerCommand3.id = 3;
                    c();
                    return;
                }
                if (ExiledKingdoms.f3384n.a(i3) == PovDirection.f1619e) {
                    ControllerCommand controllerCommand4 = this.f3909z;
                    controllerCommand4.type = commandtype;
                    controllerCommand4.id = 4;
                    c();
                    return;
                }
                i3++;
            }
        } else {
            z2 = false;
            i2 = -999;
            i3 = -999;
        }
        while (true) {
            if (i4 >= 255) {
                i4 = -999;
                break;
            } else if (Gdx.input.isKeyPressed(i4)) {
                break;
            } else {
                i4++;
            }
        }
        if (i2 != -999) {
            ControllerCommand controllerCommand5 = this.f3909z;
            controllerCommand5.type = ControllerCommand.commandType.f3367c;
            controllerCommand5.id = i2;
            c();
            return;
        }
        if (i3 == -999) {
            if (i4 != -999) {
                ControllerCommand controllerCommand6 = this.f3909z;
                controllerCommand6.type = ControllerCommand.commandType.f3368d;
                controllerCommand6.id = i4;
                c();
                return;
            }
            return;
        }
        if (z2) {
            ControllerCommand controllerCommand7 = this.f3909z;
            controllerCommand7.type = ControllerCommand.commandType.f3365a;
            controllerCommand7.id = i3;
        } else {
            ControllerCommand controllerCommand8 = this.f3909z;
            controllerCommand8.type = ControllerCommand.commandType.f3366b;
            controllerCommand8.id = i3;
        }
        c();
    }
}

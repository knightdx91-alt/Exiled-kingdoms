package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: TeleportWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m1 extends Window {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    t f2787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    TextButton f2788b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    TextButton f2789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    TextButton f2790d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    TextButton f2791e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    TextButton f2792f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    TextButton f2793g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    TextButton f2794h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    TextButton f2795i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    TextButton f2796j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    TextButton f2797k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    TextButton f2798l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    TextButton f2799m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    TextButton f2800n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    TextButton f2801o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    TextButton f2802p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    TextButton f2803q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f2804r;

    /* JADX INFO: compiled from: TeleportWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            m1.this.setVisible(false);
            GameLevel.n(false);
            GameData.v().player.O1();
        }
    }

    /* JADX INFO: compiled from: TeleportWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            m1 m1Var = m1.this;
            m1Var.setVisible(false);
            GameLevel.n(false);
            GameLevel.n(false);
            m1Var.setVisible(false);
            GameData.v().player.Z1("E10_tower");
        }
    }

    /* JADX INFO: compiled from: TeleportWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            m1.this.setVisible(false);
            GameLevel.n(false);
        }
    }

    /* JADX INFO: compiled from: TeleportWindow.java */
    final class d extends ChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2808a;

        d(String str) {
            this.f2808a = str;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            String str = this.f2808a;
            m1 m1Var = m1.this;
            m1Var.getClass();
            GameLevel.n(false);
            m1Var.setVisible(false);
            GameData.v().player.Z1(str);
        }
    }

    public m1() {
        super("", Assets.g());
        Label label = new Label("", Assets.g(), "menuLabelStrongLargeStyle");
        t tVar = new t("", Assets.g(), "menuButton");
        t tVar2 = new t("", Assets.g(), "menuButton");
        this.f2787a = tVar2;
        TextButton textButton = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2788b = textButton;
        TextButton textButton2 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2789c = textButton2;
        TextButton textButton3 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2790d = textButton3;
        TextButton textButton4 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2791e = textButton4;
        TextButton textButton5 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2792f = textButton5;
        TextButton textButton6 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2793g = textButton6;
        TextButton textButton7 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2794h = textButton7;
        TextButton textButton8 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2795i = textButton8;
        TextButton textButton9 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2796j = textButton9;
        TextButton textButton10 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2797k = textButton10;
        TextButton textButton11 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2798l = textButton11;
        TextButton textButton12 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2799m = textButton12;
        TextButton textButton13 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2800n = textButton13;
        TextButton textButton14 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2801o = textButton14;
        TextButton textButton15 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2802p = textButton15;
        TextButton textButton16 = new TextButton("", Assets.g(), "menuSmallButton");
        this.f2803q = textButton16;
        t tVar3 = new t(GameString.b("CANCEL", false), Assets.g(), "menuButton");
        this.f2804r = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        if (ExiledKingdoms.f3378h) {
            this.f2804r = 1.0f;
        }
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(this.f2804r * 838.0f);
        setHeight(this.f2804r * 590.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        setVisible(false);
        a(textButton, "NG", "NEW_GARAND");
        a(textButton2, "FT", "FREETOWN");
        a(textButton3, "NI", "NIVARIAN");
        a(textButton4, "IM", "ICEMIST");
        a(textButton5, "H10", "LANNEGAR");
        a(textButton6, "G9", "KINGSBRIDGE");
        a(textButton7, "J10", "NEW_ANTHUR");
        a(textButton9, "E10", "JABAL");
        a(textButton10, "F8", "SYDARUN");
        a(textButton13, "F10", "RHONEIS");
        a(textButton14, "D11", "FOGAS");
        a(textButton16, "H6", "FRIGULD");
        a(textButton11, "H12", "WHITETOWER");
        a(textButton15, "C13", "LAMIS");
        a(textButton8, "J7", "PORT_MALAN");
        a(textButton12, "K11", "SOLLIGA");
        label.setFontScale(this.f2804r);
        label.setText(GameString.b("CHOOSE_DESTINATION", false));
        tVar.setText(GameString.b("RECALL", false));
        tVar.getLabel().setFontScale(this.f2804r);
        if (ExiledKingdoms.f3378h) {
            tVar.getLabel().setFontScale(0.99f);
        }
        Color color = Color.GREEN;
        tVar.setColor(color);
        tVar.addListener(new a());
        tVar2.getLabel().setFontScale(this.f2804r);
        tVar2.getLabel().setWrap(true);
        if (ExiledKingdoms.f3378h) {
            tVar2.getLabel().setFontScale(0.99f);
        }
        tVar2.setColor(color);
        tVar2.addListener(new b());
        tVar3.setText(GameString.b("CANCEL", false));
        if (ExiledKingdoms.f3378h) {
            tVar3.getLabel().setFontScale(0.99f);
        }
        tVar3.addListener(new c());
        center();
        float f2 = this.f2804r;
        float f3 = 165.0f * f2;
        float f4 = f2 * 48.0f;
        row().colspan(4);
        add(label).height(this.f2804r * 60.0f).top();
        row().colspan(4).spaceTop(this.f2804r * 15.0f);
        add(tVar).height(f4).width(f3);
        row().colspan(4).spaceTop(this.f2804r * 15.0f);
        add(tVar2).height(f4).width(f3 * 2.0f);
        row().spaceTop(this.f2804r * 15.0f);
        add(textButton).height(f4).width(f3).spaceRight(5.0f);
        add(textButton2).height(f4).width(f3).spaceRight(5.0f);
        add(textButton3).height(f4).width(f3).spaceRight(5.0f);
        add(textButton4).height(f4).width(f3);
        row().spaceTop(this.f2804r * 15.0f);
        add(textButton5).height(f4).width(f3).spaceRight(5.0f);
        add(textButton6).height(f4).width(f3).spaceRight(5.0f);
        add(textButton7).height(f4).width(f3).spaceRight(5.0f);
        add(textButton8).height(f4).width(f3);
        row().spaceTop(this.f2804r * 15.0f);
        add(textButton9).height(f4).width(f3).spaceRight(5.0f);
        add(textButton10).height(f4).width(f3).spaceRight(5.0f);
        add(textButton11).height(f4).width(f3).spaceRight(5.0f);
        add(textButton12).height(f4).width(f3);
        row().spaceTop(this.f2804r * 15.0f);
        add(textButton16).height(f4).width(f3).spaceRight(5.0f);
        add(textButton14).height(f4).width(f3).spaceRight(5.0f);
        add(textButton13).height(f4).width(f3).spaceRight(5.0f);
        add(textButton15).height(f4).width(f3);
        row().colspan(4).spaceTop(this.f2804r * 25.0f);
        add(tVar3).height(f4).width(f3).bottom();
    }

    public final void a(TextButton textButton, String str, String str2) {
        textButton.getLabel().setFontScale(this.f2804r);
        textButton.getLabel().setWrap(true);
        textButton.setText(GameString.b(str2, false));
        textButton.addListener(new d(str));
    }
}

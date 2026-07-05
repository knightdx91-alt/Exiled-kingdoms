package p0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import n0.t;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ClassesHelpWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a extends Window {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static float f3709g = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static float f3710h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Table f3711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ScrollPane f3712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Label f3713c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Label f3714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Label f3715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Label f3716f;

    /* JADX INFO: renamed from: p0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesHelpWindow.java */
    final class C0045a extends InputListener {
        C0045a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a.this.setVisible(false);
            return true;
        }
    }

    /* JADX INFO: compiled from: ClassesHelpWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a.this.f3712b.setScrollPercentY(0.02f);
            return true;
        }
    }

    /* JADX INFO: compiled from: ClassesHelpWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a.this.f3712b.setScrollPercentY(0.29f);
            return true;
        }
    }

    /* JADX INFO: compiled from: ClassesHelpWindow.java */
    final class d extends InputListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a.this.f3712b.setScrollPercentY(0.6f);
            return true;
        }
    }

    /* JADX INFO: compiled from: ClassesHelpWindow.java */
    final class e extends InputListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            a.this.f3712b.setScrollPercentY(0.89f);
            return true;
        }
    }

    public a() {
        super("", Assets.g());
        t tVar = new t(GameString.b("BACK", false), Assets.g(), "menuButton");
        Table table = new Table();
        this.f3711a = table;
        ScrollPane scrollPane = new ScrollPane(table);
        this.f3712b = scrollPane;
        Image image = new Image();
        setVisible(false);
        setBackground(Assets.g().getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(f3709g * 820.0f);
        setHeight(f3709g * 600.0f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        f3710h = f3709g;
        if (ExiledKingdoms.f3378h) {
            f3709g = 0.8f;
            f3710h = 1.0f;
            setWidth(680.0f);
            setHeight(480.0f);
            setPosition((Gdx.graphics.getWidth() - getWidth()) / 2.0f, (Gdx.graphics.getHeight() - getHeight()) / 2.0f);
        }
        scrollPane.setForceScroll(false, true);
        scrollPane.setScrollbarsOnTop(true);
        if (ExiledKingdoms.f3378h) {
            ScrollPane scrollPane2 = new ScrollPane(table, Assets.g());
            this.f3712b = scrollPane2;
            scrollPane2.setFadeScrollBars(false);
        }
        image.setDrawable(new NinePatchDrawable(GameAssets.P));
        row();
        Label label = new Label(GameString.b("WARRIOR", false), GameAssets.Z);
        this.f3713c = label;
        Label label2 = new Label(GameString.b("ROGUE", false), GameAssets.Z);
        this.f3714d = label2;
        Label label3 = new Label(GameString.b("CLERIC", false), GameAssets.Z);
        this.f3715e = label3;
        Label label4 = new Label(GameString.b("MAGE", false), GameAssets.Z);
        this.f3716f = label4;
        label.setFontScale(f3710h);
        label2.setFontScale(f3710h);
        label3.setFontScale(f3710h);
        label4.setFontScale(f3710h);
        add(label, label2, label3, label4);
        row().colspan(4);
        if (ExiledKingdoms.f3378h) {
            add(image).width(f3709g * 830.0f);
        } else {
            add(image).width(f3709g * 810.0f);
        }
        row().colspan(4);
        add(this.f3712b).fill().expand().top().left();
        row().align(4).center().colspan(4);
        add(tVar).bottom().width(f3709g * 200.0f);
        tVar.clearListeners();
        tVar.addListener(new C0045a());
        label.clearListeners();
        label.addListener(new b());
        label2.clearListeners();
        label2.addListener(new c());
        label3.clearListeners();
        label3.addListener(new d());
        label4.clearListeners();
        label4.addListener(new e());
        setVisible(false);
    }

    private static Table b(String str, String str2, TextureRegion textureRegion) {
        Table table = new Table();
        table.pad(f3709g * 5.0f);
        Label label = new Label(str, Assets.g(), "menuLabelStrongStyle");
        label.setFontScale(f3710h);
        Label label2 = new Label("[BLACK]" + str2.replace("\"", "") + "[]", GameAssets.S);
        label2.setWrap(true);
        label2.setFontScale(f3710h);
        Image image = new Image(textureRegion);
        table.row().width(f3709g * 760.0f).colspan(2).space(f3709g * 12.0f).center().pad(f3709g * 8.0f);
        table.add(label).center().align(1);
        table.row().expandX().top().width(f3709g * 790.0f);
        table.add(label2).width(f3709g * 620.0f);
        table.add(image).width(f3709g * 150.0f).height(f3709g * 150.0f);
        return table;
    }

    public final void c(Rules.CharacterClass characterClass) {
        setVisible(true);
        this.f3713c.setText(GameString.b("WARRIOR", false));
        this.f3714d.setText(GameString.b("ROGUE", false));
        this.f3715e.setText(GameString.b("CLERIC", false));
        this.f3716f.setText(GameString.b("MAGE", false));
        Table table = this.f3711a;
        table.clearChildren();
        table.align(8).align(2).left().padLeft(f3709g * 5.0f);
        table.row().top().pad(f3709g * 5.0f).space(f3709g * 10.0f).center();
        Label label = new Label(GameString.b("CHARACTER_CLASSES", false), Assets.g(), "menuLabelStrongLargeStyle");
        label.setFontScale(f3710h);
        table.add(label);
        table.row();
        table.add(b(GameString.b("WARRIOR", false), GameString.b("CLASS_DESC_WARRIOR", false), Assets.f("warrior")));
        table.row();
        table.add(b(GameString.b("ROGUE", false), GameString.b("CLASS_DESC_ROGUE", false), Assets.f("rogue")));
        table.row();
        table.add(b(GameString.b("CLERIC", false), GameString.b("CLASS_DESC_CLERIC", false), Assets.f("cleric")));
        table.row();
        table.add(b(GameString.b("MAGE", false), GameString.b("CLASS_DESC_MAGE", false), Assets.f("mage")));
        ScrollPane scrollPane = this.f3712b;
        scrollPane.validate();
        scrollPane.layout();
        int iOrdinal = characterClass.ordinal();
        if (iOrdinal == 0) {
            scrollPane.setScrollPercentY(0.02f);
            return;
        }
        if (iOrdinal == 1) {
            scrollPane.setScrollPercentY(0.29f);
        } else if (iOrdinal == 2) {
            scrollPane.setScrollPercentY(0.6f);
        } else {
            if (iOrdinal != 3) {
                return;
            }
            scrollPane.setScrollPercentY(0.8f);
        }
    }
}

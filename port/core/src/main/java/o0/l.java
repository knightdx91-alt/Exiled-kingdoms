package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.io.PrintStream;
import java.util.Iterator;
import net.fdgames.GameWorld.DynamicEvent;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Quest;
import net.fdgames.GameWorld.Quests;
import net.fdgames.GameWorld.Rumor;
import net.fdgames.GameWorld.WorldEvents;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: JournalWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class l extends Window {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static l f3577l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static float f3578m = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static float f3579n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.t f3580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Table f3581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ScrollPane f3582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CheckBox f3583d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CheckBox f3584e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CheckBox f3585f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CheckBox f3586g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f3587h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3588i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3589j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3590k;

    /* JADX INFO: compiled from: JournalWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l.this.setVisible(false);
            return true;
        }
    }

    /* JADX INFO: compiled from: JournalWindow.java */
    final class b extends InputListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l lVar = l.this;
            lVar.f3588i = false;
            lVar.f3587h = false;
            lVar.f();
            return true;
        }
    }

    /* JADX INFO: compiled from: JournalWindow.java */
    final class c extends InputListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l lVar = l.this;
            lVar.f3588i = true;
            lVar.f3587h = false;
            lVar.f();
            return true;
        }
    }

    /* JADX INFO: compiled from: JournalWindow.java */
    final class d extends InputListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l lVar = l.this;
            lVar.f3587h = true;
            lVar.f();
            return true;
        }
    }

    /* JADX INFO: compiled from: JournalWindow.java */
    final class e extends InputListener {
        e() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            l lVar = l.this;
            lVar.f3589j = !lVar.f3589j;
            lVar.f();
            return true;
        }
    }

    public static void d() {
        if (f3577l != null) {
            f3577l = null;
        }
    }

    public static l e() {
        if (f3577l == null) {
            l lVar = new l("", Assets.g());
            n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
            lVar.f3580a = tVar;
            Table table = new Table();
            lVar.f3581b = table;
            ScrollPane scrollPane = new ScrollPane(table);
            lVar.f3582c = scrollPane;
            CheckBox checkBox = new CheckBox(a.a.o("IN_PROGRESS", false, new StringBuilder(" [BLACK]"), "[]"), GameAssets.f3350r0);
            lVar.f3583d = checkBox;
            CheckBox checkBox2 = new CheckBox(a.a.o("COMPLETED", false, new StringBuilder(" [BLACK]"), "[]"), GameAssets.f3350r0);
            lVar.f3584e = checkBox2;
            CheckBox checkBox3 = new CheckBox(a.a.o("RUMORS", false, new StringBuilder(" [BLACK]"), "[]"), GameAssets.f3350r0);
            lVar.f3585f = checkBox3;
            CheckBox checkBox4 = new CheckBox(a.a.o("SHOW_TOWN_HALL", false, new StringBuilder(" [BLACK]"), "[]"), GameAssets.f3350r0);
            lVar.f3586g = checkBox4;
            Image image = new Image();
            lVar.f3587h = false;
            lVar.f3588i = false;
            lVar.f3589j = true;
            lVar.f3590k = 900;
            lVar.setBackground(Assets.g().getDrawable("windowbg"));
            lVar.setMovable(false);
            lVar.setModal(true);
            lVar.setWidth(Gdx.graphics.getWidth() * 0.88f);
            lVar.setHeight(Gdx.graphics.getHeight() * 0.75f);
            lVar.setPosition((Gdx.graphics.getWidth() - lVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - lVar.getHeight()) / 2.0f);
            f3579n = f3578m;
            if (ExiledKingdoms.f3378h) {
                f3578m = 0.8f;
                f3579n = 1.0f;
                lVar.setWidth(901.12006f);
                lVar.setHeight(432.0f);
                lVar.setPosition((Gdx.graphics.getWidth() - lVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - lVar.getHeight()) / 2.0f);
            }
            checkBox.getLabel().setFontScale(f3579n);
            checkBox2.getLabel().setFontScale(f3579n);
            checkBox4.getLabel().setFontScale(f3579n);
            checkBox3.getLabel().setFontScale(f3579n);
            image.setDrawable(new NinePatchDrawable(GameAssets.P));
            table.align(8).align(2);
            table.columnDefaults(0).width(f3578m * 320.0f);
            table.columnDefaults(1).width(f3578m * 650.0f);
            scrollPane.setForceScroll(false, true);
            scrollPane.setScrollbarsOnTop(true);
            if (ExiledKingdoms.f3378h) {
                ScrollPane scrollPane2 = new ScrollPane(table, Assets.g());
                lVar.f3582c = scrollPane2;
                scrollPane2.setFadeScrollBars(false);
            }
            lVar.row().expandX().top().padTop(f3578m * 10.0f).align(8).padBottom(f3578m * 10.0f);
            lVar.add(checkBox).center();
            lVar.add(checkBox2).left().align(8);
            lVar.add(checkBox3).left().align(8);
            lVar.add(checkBox4).left().align(8);
            Cell cell = checkBox.getCells().get(0);
            float f2 = f3578m * 20.0f;
            cell.size(f2, f2);
            Cell cell2 = checkBox2.getCells().get(0);
            float f3 = f3578m * 20.0f;
            cell2.size(f3, f3);
            Cell cell3 = checkBox3.getCells().get(0);
            float f4 = f3578m * 20.0f;
            cell3.size(f4, f4);
            Cell cell4 = checkBox4.getCells().get(0);
            float f5 = f3578m * 20.0f;
            cell4.size(f5, f5);
            checkBox4.setChecked(true);
            lVar.row().colspan(4).left().pad(0.0f).height(2.0f).width(lVar.getWidth() - (f3578m * 15.0f));
            lVar.add(image);
            lVar.row().height(lVar.getHeight() * 0.74f).colspan(4).padTop(f3578m * 15.0f);
            lVar.add(lVar.f3582c).fill().expand();
            lVar.row().align(4).colspan(4);
            lVar.add(tVar).bottom().width(f3578m * 200.0f);
            if (ExiledKingdoms.f3378h) {
                tVar.getLabel().setFontScale(0.99f);
            }
            tVar.addListener(lVar.new a());
            lVar.f3588i = false;
            checkBox.addListener(lVar.new b());
            checkBox2.addListener(lVar.new c());
            checkBox3.addListener(lVar.new d());
            checkBox4.addListener(lVar.new e());
            f3577l = lVar;
        }
        return f3577l;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Window, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        super.draw(batch, f2);
        boolean z2 = this.f3587h;
        CheckBox checkBox = this.f3585f;
        CheckBox checkBox2 = this.f3584e;
        CheckBox checkBox3 = this.f3583d;
        if (z2) {
            checkBox3.setChecked(false);
            checkBox2.setChecked(false);
            checkBox.setChecked(true);
            return;
        }
        checkBox.setChecked(false);
        if (this.f3588i) {
            checkBox3.setChecked(false);
            checkBox2.setChecked(true);
        } else {
            checkBox3.setChecked(true);
            checkBox2.setChecked(false);
        }
    }

    public final void f() {
        Iterator<DynamicQuest> it;
        String str;
        Iterator<DynamicEvent> it2;
        this.f3580a.setText(GameString.b("BACK", false));
        String str2 = " [BLACK]";
        this.f3583d.setText(a.a.o("IN_PROGRESS", false, new StringBuilder(" [BLACK]"), "[]"));
        this.f3584e.setText(a.a.o("COMPLETED", false, new StringBuilder(" [BLACK]"), "[]"));
        this.f3585f.setText(a.a.o("RUMORS", false, new StringBuilder(" [BLACK]"), "[]"));
        this.f3586g.setText(a.a.o("SHOW_TOWN_HALL", false, new StringBuilder(" [BLACK]"), "[]"));
        Table table = this.f3581b;
        table.clearChildren();
        table.padTop(f3578m * 15.0f);
        setVisible(true);
        boolean z2 = this.f3587h;
        int i2 = this.f3590k;
        if (z2) {
            Iterator<DynamicEvent> it3 = GameData.v().t().iterator();
            boolean z3 = true;
            while (it3.hasNext()) {
                DynamicEvent next = it3.next();
                if (next.knowledge > 0) {
                    Image image = new Image();
                    image.setDrawable(Assets.g().getDrawable("default-rect-down"));
                    WorldEvents worldEvents = GameWorld.f3193g;
                    String str3 = next.event_id;
                    worldEvents.getClass();
                    it2 = it3;
                    Label label = new Label(WorldEvents.a(str3).a().replace("{name}", next.name), Assets.g(), "menuLabelStrongLargeStyle");
                    Label label2 = new Label(next.e(), GameAssets.S);
                    if (next.knowledge < 3) {
                        StringBuilder sb = new StringBuilder();
                        sb.append((Object) label2.getText());
                        sb.append(str2);
                        str = str2;
                        sb.append(GameString.b("LEARN_MORE", false));
                        sb.append("[]");
                        label2.setText(sb.toString());
                    } else {
                        str = str2;
                    }
                    label2.setText(FDUtils.e(((Object) label2.getText()) + "\r\n\r\n[BLACK]" + GameString.b("RUMOR_TIME", false) + " [BLUE]" + FDUtils.g(next.expirationDate.floatValue() - GameData.v().u()) + "[]"));
                    label.setFontScale(f3579n);
                    label2.setFontScale(f3579n);
                    label2.setWrap(true);
                    table.row().space(f3578m * 10.0f).padTop(f3578m * 15.0f);
                    table.add(image).top().width(f3578m * 12.0f).height(f3578m * 12.0f).left().center();
                    Cell pVar = table.add(label).top();
                    float f2 = (float) i2;
                    pVar.width(f3578m * f2).height(f3578m * 12.0f).left();
                    table.row().top().left().spaceBottom(f3578m * 40.0f).align(8).colspan(2);
                    table.add(label2).top().left().width(f2 * f3578m);
                    z3 = false;
                } else {
                    str = str2;
                    it2 = it3;
                    PrintStream printStream = System.out;
                    WorldEvents worldEvents2 = GameWorld.f3193g;
                    String str4 = next.event_id;
                    worldEvents2.getClass();
                    printStream.println(WorldEvents.a(str4).a().replace("{name}", next.name));
                }
                it3 = it2;
                str2 = str;
            }
            for (Rumor rumor : GameWorld.f3190d.a()) {
                Image image2 = new Image();
                image2.setDrawable(Assets.g().getDrawable("default-rect-down"));
                Label label3 = new Label(GameString.b("TAVERN_RUMOR", false), Assets.g(), "menuLabelStrongLargeStyle");
                Label label4 = new Label("[BLACK]" + rumor.a() + "[]", GameAssets.S);
                label3.setFontScale(f3579n);
                label4.setFontScale(f3579n);
                label4.setWrap(true);
                table.row().space(f3578m * 10.0f).padTop(f3578m * 15.0f);
                table.add(image2).top().width(f3578m * 12.0f).height(f3578m * 12.0f).left().center();
                float f3 = i2;
                table.add(label3).top().width(f3578m * f3).height(f3578m * 12.0f).left();
                table.row().top().left().spaceBottom(f3578m * 40.0f).align(8).colspan(2);
                table.add(label4).top().left().width(f3 * f3578m);
                z3 = false;
            }
            if (z3) {
                Label label5 = new Label(GameString.b("NO_RUMORS", false), Assets.g(), "menuLabelStrongStyle");
                table.row().space(f3578m * 30.0f).padTop(f3578m * 39.0f).center();
                table.add(label5).top().center().width(getWidth() * 0.9f);
                return;
            }
            return;
        }
        int i3 = 99;
        int i4 = 100;
        if (this.f3589j) {
            Iterator<DynamicQuest> it4 = GameData.v().dynamicQuests.iterator();
            while (it4.hasNext()) {
                DynamicQuest next2 = it4.next();
                int iB = GameData.v().gameVariables.b("DQ_" + next2.DQ_id);
                boolean z4 = this.f3588i;
                if ((z4 || iB <= 0 || iB >= i4) && (!z4 || iB <= i3)) {
                    it = it4;
                } else {
                    Image image3 = new Image();
                    image3.setDrawable(Assets.g().getDrawable("default-rect-down"));
                    Label label6 = new Label(next2.f(), Assets.g(), "menuLabelStrongLargeStyle");
                    it = it4;
                    Label label7 = new Label(next2.g(iB), GameAssets.S);
                    if (!this.f3588i) {
                        if (next2.knowledge < 3 && iB < 50) {
                            label7.setText(((Object) label7.getText()) + " [BLACK]" + GameString.b("LEARN_MORE", false) + "[]");
                        }
                        label7.setText(((Object) label7.getText()) + "\r\n\r\n[BLACK]" + GameString.b("QUEST_TIME", false) + " [BLUE]" + FDUtils.g(next2.expirationDate.floatValue() - GameData.v().u()) + "[]");
                    }
                    label6.setFontScale(f3579n);
                    label7.setFontScale(f3579n);
                    label7.setWrap(true);
                    table.row().space(f3578m * 10.0f).padTop(f3578m * 15.0f);
                    table.add(image3).top().width(f3578m * 12.0f).height(f3578m * 12.0f).left().center();
                    float f4 = i2;
                    table.add(label6).top().width(f3578m * f4).height(f3578m * 12.0f).left();
                    table.row().top().left().spaceBottom(f3578m * 40.0f).align(8).colspan(2);
                    table.add(label7).top().left().width(f4 * f3578m);
                }
                it4 = it;
                i3 = 99;
                i4 = 100;
            }
        }
        for (Quest quest : Quests.f3196a) {
            int iB2 = GameData.v().gameVariables.b(quest.ID);
            boolean z5 = this.f3588i;
            if (!z5 && iB2 > 0) {
                if (iB2 >= 100) {
                }
                Image image4 = new Image();
                image4.setDrawable(Assets.g().getDrawable("default-rect-down"));
                Label label8 = new Label(quest.a(), Assets.g(), "menuLabelStrongLargeStyle");
                Label label9 = new Label(quest.c(GameData.v().gameVariables.b(quest.ID)), GameAssets.S);
                label8.setFontScale(f3579n);
                label9.setFontScale(f3579n);
                label9.setWrap(true);
                table.row().space(f3578m * 10.0f).padTop(f3578m * 15.0f);
                table.add(image4).top().width(f3578m * 12.0f).height(f3578m * 12.0f).left().center();
                Cell pVar2 = table.add(label8).top();
                float f5 = i2;
                pVar2.width(f3578m * f5).height(f3578m * 12.0f).left();
                table.row().top().left().spaceBottom(f3578m * 40.0f).align(8).colspan(2);
                table.add(label9).top().left().width(f5 * f3578m);
            }
            if (z5 && iB2 > 99) {
                Image image42 = new Image();
                image42.setDrawable(Assets.g().getDrawable("default-rect-down"));
                Label label82 = new Label(quest.a(), Assets.g(), "menuLabelStrongLargeStyle");
                Label label92 = new Label(quest.c(GameData.v().gameVariables.b(quest.ID)), GameAssets.S);
                label82.setFontScale(f3579n);
                label92.setFontScale(f3579n);
                label92.setWrap(true);
                table.row().space(f3578m * 10.0f).padTop(f3578m * 15.0f);
                table.add(image42).top().width(f3578m * 12.0f).height(f3578m * 12.0f).left().center();
                Cell pVar22 = table.add(label82).top();
                float f52 = i2;
                pVar22.width(f3578m * f52).height(f3578m * 12.0f).left();
                table.row().top().left().spaceBottom(f3578m * 40.0f).align(8).colspan(2);
                table.add(label92).top().left().width(f52 * f3578m);
            }
        }
    }
}

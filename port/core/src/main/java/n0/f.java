package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.MapContainer;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ChooseQuestWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class f extends Window {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static float f2634i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Label f2636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Label f2637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Label f2638c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageButton f2639d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageButton f2640e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<DynamicQuest> f2641f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2642g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static float f2633h = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static f f2635j = null;

    /* JADX INFO: compiled from: ChooseQuestWindow.java */
    final class a extends ChangeListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            f fVar = f.this;
            f.b(fVar);
            fVar.h();
        }
    }

    /* JADX INFO: compiled from: ChooseQuestWindow.java */
    final class b extends ChangeListener {
        b() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            f fVar = f.this;
            f.c(fVar);
            fVar.h();
        }
    }

    /* JADX INFO: compiled from: ChooseQuestWindow.java */
    final class c extends ChangeListener {
        c() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            f.this.setVisible(false);
        }
    }

    /* JADX INFO: compiled from: ChooseQuestWindow.java */
    final class d extends ChangeListener {
        d() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
        public final void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
            f fVar = f.this;
            int i2 = ((DynamicQuest) fVar.f2641f.get(fVar.f2642g - 1)).DQ_id;
            GameData.v().gameVariables.e(10, "DQ_" + i2);
            DynamicQuest dynamicQuestE = DynamicQuest.e(i2);
            dynamicQuestE.getClass();
            float fU = GameData.v().u();
            dynamicQuestE.generationDate = Float.valueOf(fU);
            dynamicQuestE.expirationDate = Float.valueOf(fU + (dynamicQuestE.duration * 1080));
            m0.b.P().getClass();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            for (MonsterSpawn monsterSpawn : GameLevelData.o().spawns) {
                if (!monsterSpawn.questLocation.equals("") && DynamicQuest.j(monsterSpawn.questLocation) > 0) {
                    for (NPC npc : GameLevelData.t()) {
                        if (npc.L1() == monsterSpawn.q()) {
                            npc.destroy = true;
                        }
                    }
                    monsterSpawn.o("SPAWN", monsterSpawn.q(), null, 0.1f, null);
                }
            }
            m0.b.P().getClass();
            Iterator<MapContainer> it = GameLevelData.m().iterator();
            while (it.hasNext()) {
                it.next().F();
            }
            fVar.h();
            fVar.setVisible(false);
            n0.d.f().d();
        }
    }

    static /* synthetic */ void b(f fVar) {
        fVar.f2642g++;
    }

    static /* synthetic */ void c(f fVar) {
        fVar.f2642g--;
    }

    public static void e() {
        if (f2635j != null) {
            f2635j = null;
        }
    }

    public static f f() {
        if (f2635j == null) {
            f fVar = new f("", Assets.g());
            Label label = new Label("", Assets.g(), "menuLabelStrongLargeStyle");
            fVar.f2636a = label;
            Label label2 = new Label("", Assets.g(), "menuLabelStrongStyle");
            fVar.f2637b = label2;
            Label label3 = new Label("", GameAssets.S);
            fVar.f2638c = label3;
            ImageButton imageButton = new ImageButton(GameAssets.f3344o0);
            fVar.f2639d = imageButton;
            ImageButton imageButton2 = new ImageButton(GameAssets.f3346p0);
            fVar.f2640e = imageButton2;
            t tVar = new t(GameString.b("EXIT", false), Assets.g(), "menuButton");
            t tVar2 = new t(GameString.b("ACCEPT_QUEST", false), Assets.g(), "menuButton");
            fVar.setVisible(false);
            fVar.setBackground(Assets.g().getDrawable("windowbg"));
            fVar.setMovable(false);
            fVar.setModal(true);
            fVar.setWidth(f2633h * 735.0f);
            fVar.setHeight(f2633h * 630.0f);
            fVar.setPosition((Gdx.graphics.getWidth() - fVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - fVar.getHeight()) / 2.0f);
            f2634i = f2633h;
            if (ExiledKingdoms.f3378h) {
                f2633h = 0.8f;
                f2634i = 1.0f;
                fVar.setWidth(588.0f);
                fVar.setHeight(504.0f);
                fVar.setPosition((Gdx.graphics.getWidth() - fVar.getWidth()) / 2.0f, (Gdx.graphics.getHeight() - fVar.getHeight()) / 2.0f);
            }
            label.setFontScale(f2634i);
            label3.setWrap(true);
            label3.setFontScale(f2634i);
            label2.setWrap(true);
            label2.setAlignment(8);
            label2.setFontScale(f2634i);
            fVar.top();
            fVar.row().center().colspan(2).padTop(f2633h * 20.0f);
            fVar.add(label).center().height(f2633h * 40.0f);
            fVar.row().center().colspan(2).spaceTop(f2633h * 20.0f);
            Table table = new Table();
            table.row().center().spaceRight(f2633h * 20.0f).height(f2633h * 420.0f);
            float f2 = 52;
            table.add(imageButton).width(f2633h * f2).height(f2633h * f2);
            Table table2 = new Table();
            table2.top();
            table2.setBackground(new NinePatchDrawable(GameAssets.P));
            table2.row().center();
            table2.add(label2).left();
            table2.row().spaceRight(f2633h * 20.0f).width(f2633h * 500.0f);
            table2.add(label3).pad(f2633h * 10.0f);
            float f3 = f2633h;
            table2.setSize(520.0f * f3, f3 * 440.0f);
            table.add(table2);
            table.add(imageButton2).width(f2633h * f2).height(f2 * f2633h);
            fVar.add(table);
            fVar.row().center().spaceRight(f2633h * 40.0f).spaceTop(f2633h * 15.0f);
            fVar.add(tVar).width(f2633h * 240.0f).right();
            fVar.add(tVar2).width(f2633h * 240.0f).left();
            imageButton2.addListener(fVar.new a());
            imageButton.addListener(fVar.new b());
            tVar.addListener(fVar.new c());
            tVar2.addListener(fVar.new d());
            f2635j = fVar;
        }
        return f2635j;
    }

    public final void g(String str) {
        this.f2642g = 1;
        ArrayList<DynamicQuest> arrayListH = DynamicQuest.h(str);
        this.f2641f = arrayListH;
        if (arrayListH.size() <= 0) {
            setVisible(false);
            return;
        }
        h();
        setVisible(true);
        toFront();
        h();
    }

    public final void h() {
        int i2 = this.f2642g;
        ImageButton imageButton = this.f2639d;
        if (i2 == 1) {
            imageButton.setDisabled(true);
        } else {
            imageButton.setDisabled(false);
        }
        int i3 = this.f2642g;
        int size = this.f2641f.size();
        ImageButton imageButton2 = this.f2640e;
        if (i3 == size) {
            imageButton2.setDisabled(true);
        } else {
            imageButton2.setDisabled(false);
        }
        Label label = this.f2636a;
        StringBuilder sb = new StringBuilder();
        a.a.w("AVAILABLE_QUESTS", false, sb, " (");
        sb.append(this.f2642g);
        sb.append("/");
        sb.append(this.f2641f.size());
        sb.append(")");
        label.setText(sb.toString());
        this.f2637b.setText(this.f2641f.get(this.f2642g - 1).f());
        Label label2 = this.f2638c;
        label2.setText(this.f2641f.get(this.f2642g - 1).g(-5));
        label2.setText(((Object) label2.getText()) + "\r\n\r\n[BLACK]" + GameString.b("QUEST_TIME", false) + "  [BLUE]" + this.f2641f.get(this.f2642g - 1).duration + " " + GameString.b("DAYS", false) + "[]");
    }
}

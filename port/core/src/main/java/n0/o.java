package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.StaticNPC;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.Conversation;
import net.fdgames.GameLogic.ConversationAnswers;
import net.fdgames.GameLogic.ConversationQuestion;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.MapConversation;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ConversationWindow.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class o extends Window {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static float f2825w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Conversation f2826a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2827b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2828c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2829d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2831f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Label f2832g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Label f2833h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public s f2834i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public s f2835j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public s f2836k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public s f2837l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Label f2838m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Image f2839n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f2840o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MapConversation f2841p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ActionsSet f2842q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ActionsSet f2843r;
    public ActionsSet s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ActionsSet f2844t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Coords f2845u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2846v;

    public o(Skin skin, Stage stage) {
        super("", skin);
        this.f2827b = 0;
        Label label = new Label("", Assets.g(), "menuLabelStyle");
        this.f2832g = label;
        Label label2 = new Label("", GameAssets.T);
        this.f2833h = label2;
        s sVar = new s(GameAssets.U);
        this.f2834i = sVar;
        s sVar2 = new s(GameAssets.V);
        this.f2835j = sVar2;
        s sVar3 = new s(GameAssets.W);
        this.f2836k = sVar3;
        s sVar4 = new s(GameAssets.X);
        this.f2837l = sVar4;
        Label label3 = new Label("", Assets.g(), "menuLabelStyle");
        this.f2838m = label3;
        Image image = new Image();
        this.f2839n = image;
        Image image2 = new Image();
        Image image3 = new Image();
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        this.f2840o = fMin;
        this.f2846v = 0;
        setBackground(skin.getDrawable("windowbg"));
        setMovable(false);
        setModal(true);
        setWidth(stage.getWidth() / 1.6f);
        setHeight(stage.getHeight());
        setPosition(stage.getWidth() / 2.0f, 0.0f);
        f2825w = fMin;
        if (ExiledKingdoms.f3378h) {
            this.f2840o = 0.8f;
            f2825w = 1.0f;
            setWidth(640.0f);
            setHeight(576.0f);
            setPosition(stage.getWidth() - getWidth(), stage.getHeight() - getHeight());
        }
        float f2 = this.f2840o;
        float f3 = 4.0f * f2;
        float f4 = f2 * 120.0f;
        label.setFontScale(f2825w);
        label.setWrap(true);
        label2.setFontScale(f2825w);
        label2.setWrap(true);
        sVar.setFontScale(f2825w);
        sVar.setWrap(true);
        sVar2.setFontScale(f2825w);
        sVar2.setWrap(true);
        sVar3.setFontScale(f2825w);
        sVar3.setWrap(true);
        sVar4.setFontScale(f2825w);
        sVar4.setWrap(true);
        label3.setFontScale(f2825w);
        image2.setDrawable(new NinePatchDrawable(GameAssets.P));
        image3.setDrawable(new NinePatchDrawable(GameAssets.P));
        pad(f3);
        left();
        top();
        row().left().pad(f3).padTop(this.f2840o * 26.0f).padBottom(0.0f).align(8);
        Table table = new Table();
        table.setBackground(new NinePatchDrawable(GameAssets.P));
        table.row().width((this.f2840o * 28.0f) + f4);
        table.add(label).align(1).bottom();
        table.row().width(f4);
        table.add(image).height(f4).width(f4).bottom();
        add(table).top();
        Table table2 = new Table();
        Cell cellAdd = table2.add(label2);
        float f5 = this.f2840o;
        cellAdd.width(595.0f * f5).left().top().padLeft(f5 * (-25.0f)).padTop(0.0f).padRight(f3);
        add(table2).top();
        float f6 = this.f2840o * 765.0f;
        row().colspan(2).left().pad(this.f2840o * 2.0f).height(2.0f).width(this.f2840o * 808.0f);
        add(image2);
        float f7 = 3.0f * f3;
        row().colspan(2).left().pad(f3).minHeight(this.f2840o * 80.0f).width(f6).spaceBottom(this.f2840o * 14.0f).padLeft(f7);
        add(sVar).left();
        row().colspan(2).left().pad(f3).minHeight(this.f2840o * 80.0f).width(f6).spaceBottom(this.f2840o * 14.0f).padLeft(f7);
        add(sVar2).left();
        row().colspan(2).left().pad(f3).minHeight(this.f2840o * 80.0f).width(f6).spaceBottom(this.f2840o * 14.0f).padLeft(f7);
        add(sVar3).left();
        row().colspan(2).left().pad(f3).minHeight(this.f2840o * 80.0f).width(f6).padLeft(f7);
        add(sVar4).left();
        row().bottom().right().colspan(2).expand().pad(f3);
        add(label3).center().bottom();
        sVar.addListener(new k(this));
        sVar2.addListener(new l(this));
        sVar3.addListener(new m(this));
        sVar4.addListener(new n(this));
    }

    /* JADX INFO: Access modifiers changed from: public */
    public void u(int i2) {
        if (i2 == 0) {
            setVisible(false);
            if (!z.v().f2939b0.isVisible()) {
                z.v();
                if (!z.y().booleanValue() && z.v().f2945e0 == 0) {
                    GameLevel.n(false);
                }
            }
            z.v().J(z.v().w());
            return;
        }
        if (ExiledKingdoms.g()) {
            this.f2846v = 0;
        } else {
            this.f2846v = -1;
        }
        ConversationQuestion conversationQuestionB = this.f2826a.b(i2);
        String str = conversationQuestionB.newOwner;
        Locale locale = Locale.ENGLISH;
        boolean zEquals = str.toLowerCase(locale).equals("r");
        Image image = this.f2839n;
        Label label = this.f2832g;
        if (zEquals) {
            this.f2827b = this.f2841p.a();
            label.setText(this.f2841p.getName());
            image.setDrawable(new TextureRegionDrawable(this.f2841p.c()));
            this.f2845u = this.f2841p.getPosition();
        } else {
            String lowerCase = conversationQuestionB.newOwner.toLowerCase(locale);
            if (!lowerCase.equals("")) {
                GameLevelData.o();
                Iterator<NPC> it = GameLevelData.t().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        GameLevelData.o();
                        Iterator<StaticNPC> it2 = GameLevelData.y().iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            StaticNPC next = it2.next();
                            if (next.tag != null && lowerCase.trim().equals(next.tag.trim())) {
                                this.f2827b = next.q();
                                label.setText(next.getName());
                                image.setDrawable(new TextureRegionDrawable(next.E()));
                                this.f2845u = next.B();
                                break;
                            }
                        }
                    } else {
                        NPC next2 = it.next();
                        if (next2.tag != null && lowerCase.trim().equals(next2.tag.trim())) {
                            this.f2827b = next2.q();
                            label.setText(next2.getName());
                            image.setDrawable(new TextureRegionDrawable(next2.E()));
                            this.f2845u = next2.B();
                            break;
                        }
                    }
                }
            }
        }
        this.f2833h.setText("[#601805]" + conversationQuestionB.text + "[]");
        s sVar = this.f2834i;
        sVar.setText("");
        s sVar2 = this.f2835j;
        sVar2.setText("");
        s sVar3 = this.f2836k;
        sVar3.setText("");
        s sVar4 = this.f2837l;
        sVar4.setText("");
        sVar.setVisible(false);
        sVar2.setVisible(false);
        sVar3.setVisible(false);
        sVar4.setVisible(false);
        this.f2828c = 0;
        this.f2829d = 0;
        this.f2830e = 0;
        this.f2831f = 0;
        sVar.getStyle().background = null;
        sVar2.getStyle().background = null;
        sVar3.getStyle().background = null;
        sVar4.getStyle().background = null;
        this.f2842q = null;
        this.f2843r = null;
        this.s = null;
        this.f2844t = null;
        int i3 = conversationQuestionB.destination;
        if (i3 == 0) {
            sVar.setVisible(true);
            sVar.setText("[BLUE]1. (" + GameString.b("LEAVE", false) + ")[]");
            this.f2828c = 0;
        } else if (this.f2826a.c(i3)) {
            sVar.setVisible(true);
            sVar.setText("[BLUE]1. (" + GameString.b("CONTINUE", false) + ")[]");
            this.f2828c = conversationQuestionB.destination;
        } else {
            ConversationAnswers conversationAnswersA = this.f2826a.a(conversationQuestionB.destination);
            if (conversationAnswersA.length > 0) {
                sVar.setVisible(true);
                sVar.setText("[BLUE]1. [][BLACK] " + conversationAnswersA.text[0] + "[]");
                this.f2828c = conversationAnswersA.destination[0];
                this.f2842q = conversationAnswersA.actions[0];
            }
            if (conversationAnswersA.length > 1) {
                sVar2.setVisible(true);
                sVar2.setText("[BLUE]2. [][BLACK] " + conversationAnswersA.text[1] + "[]");
                this.f2829d = conversationAnswersA.destination[1];
                this.f2843r = conversationAnswersA.actions[1];
            }
            if (conversationAnswersA.length > 2) {
                sVar3.setVisible(true);
                sVar3.setText("[BLUE]3. [][BLACK] " + conversationAnswersA.text[2] + "[]");
                this.f2830e = conversationAnswersA.destination[2];
                this.s = conversationAnswersA.actions[2];
            }
            if (conversationAnswersA.length > 3) {
                sVar4.setVisible(true);
                sVar4.setText("[BLUE]4. [BLACK] " + conversationAnswersA.text[3] + "[]");
                this.f2831f = conversationAnswersA.destination[3];
                this.f2844t = conversationAnswersA.actions[3];
            }
        }
        v();
        ActionsSet actionsSet = conversationQuestionB.actions;
        if (actionsSet != null) {
            actionsSet.a();
        }
    }

    public final void a() {
        int i2;
        if (!ExiledKingdoms.g() || (i2 = this.f2846v) == -1 || i2 >= 3) {
            return;
        }
        if (i2 != 0 || this.f2835j.isVisible()) {
            if (this.f2846v != 1 || this.f2836k.isVisible()) {
                if (this.f2846v != 2 || this.f2837l.isVisible()) {
                    this.f2846v++;
                    v();
                }
            }
        }
    }

    public final void b() {
        int i2;
        if (!ExiledKingdoms.g() || (i2 = this.f2846v) == -1 || i2 <= 0) {
            return;
        }
        this.f2846v = i2 - 1;
        v();
    }

    public final void p(int i2) {
        if (ExiledKingdoms.f3378h) {
            GameAssets.o("click");
        }
        this.f2846v = 0;
        if (i2 == 1) {
            if (this.f2828c >= 0) {
                this.f2834i.getStyle().background = new NinePatchDrawable(GameAssets.I);
                ActionsSet actionsSet = this.f2842q;
                if (actionsSet != null) {
                    actionsSet.a();
                }
                u(this.f2828c);
                return;
            }
            return;
        }
        if (i2 == 2) {
            s sVar = this.f2835j;
            if (sVar.isVisible()) {
                sVar.getStyle().background = new NinePatchDrawable(GameAssets.I);
                ActionsSet actionsSet2 = this.f2843r;
                if (actionsSet2 != null) {
                    actionsSet2.a();
                }
                u(this.f2829d);
                return;
            }
            return;
        }
        if (i2 == 3) {
            s sVar2 = this.f2836k;
            if (sVar2.isVisible()) {
                sVar2.getStyle().background = new NinePatchDrawable(GameAssets.I);
                ActionsSet actionsSet3 = this.s;
                if (actionsSet3 != null) {
                    actionsSet3.a();
                }
                u(this.f2830e);
                return;
            }
            return;
        }
        if (i2 != 4) {
            return;
        }
        s sVar3 = this.f2837l;
        if (sVar3.isVisible()) {
            sVar3.getStyle().background = new NinePatchDrawable(GameAssets.I);
            ActionsSet actionsSet4 = this.f2844t;
            if (actionsSet4 != null) {
                actionsSet4.a();
            }
            u(this.f2831f);
        }
    }

    public final void q() {
        int i2;
        if (!ExiledKingdoms.g() || (i2 = this.f2846v) == -1) {
            return;
        }
        p(i2 + 1);
    }

    public final void r() {
        u(0);
    }

    public final Coords s() {
        return this.f2845u;
    }

    public final void t(MapConversation mapConversation) {
        if (ExiledKingdoms.g()) {
            this.f2846v = 0;
        } else {
            this.f2846v = -1;
        }
        GameData.v().TIP_INTERACTION = false;
        setVisible(true);
        GameLevel.n(true);
        this.f2838m.setText("(" + GameString.b("CHOOSE_OPTION_CONTINUE", false) + ")");
        this.f2826a = new Conversation(mapConversation.b());
        this.f2827b = mapConversation.a();
        this.f2832g.setText(mapConversation.getName());
        u(1);
        this.f2839n.setDrawable(new TextureRegionDrawable(mapConversation.c()));
        this.f2845u = mapConversation.getPosition();
        this.f2841p = mapConversation;
        if (GameData.v().player.sheet.effects.stealth.booleanValue()) {
            GameData.v().player.sheet.effects.stealth = Boolean.FALSE;
        }
        z.v().J(z.v().w());
        v();
        System.gc();
    }

    public final void v() {
        s sVar = this.f2834i;
        sVar.e();
        s sVar2 = this.f2835j;
        sVar2.e();
        s sVar3 = this.f2836k;
        sVar3.e();
        s sVar4 = this.f2837l;
        sVar4.e();
        int i2 = this.f2846v;
        if (i2 == 0) {
            sVar.d();
            return;
        }
        if (i2 == 1) {
            sVar2.d();
        } else if (i2 == 2) {
            sVar3.d();
        } else {
            if (i2 != 3) {
                return;
            }
            sVar4.d();
        }
    }
}

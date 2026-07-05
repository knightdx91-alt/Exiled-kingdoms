package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.Locale;
import net.fdgames.GameWorld.Castle;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.WorldFaction;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CastleDescriptionTable.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c extends Table {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static float f2573g = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static float f2574h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f2575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f2576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f2577c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Image f2578d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Image f2579e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2580f;

    public c() {
        super(Assets.g());
        Label label = new Label("", Assets.g(), "menuLabelStrongVeryLargeStyle");
        this.f2575a = label;
        Label label2 = new Label(a.a.o("REPUTATION", false, new StringBuilder(), ": "), Assets.g(), "menuLabelStrongStyle");
        Label label3 = new Label("", GameAssets.S);
        this.f2576b = label3;
        Label label4 = new Label("", GameAssets.f3318b0);
        this.f2577c = label4;
        Image image = new Image();
        this.f2578d = image;
        Image image2 = new Image();
        this.f2579e = image2;
        setBackground(Assets.g().getDrawable("windowbg"));
        setWidth(f2573g * 600.0f);
        setHeight(f2573g * 350.0f);
        f2574h = f2573g;
        if (ExiledKingdoms.f3378h) {
            f2573g = 0.8f;
            f2574h = 1.0f;
            setWidth(480.0f);
            setHeight(280.0f);
        }
        label.setFontScale(f2574h);
        label2.setFontScale(f2574h);
        label3.setFontScale(f2574h);
        label4.setFontScale(f2574h * 0.75f);
        label.setWrap(true);
        label2.setWrap(true);
        label3.setWrap(true);
        label4.setWrap(true);
        Table table = new Table();
        table.setWidth(f2573g * 450.0f * 1.0f);
        table.setHeight(f2573g * 150.0f * 1.0f);
        table.center();
        table.row().pad(f2573g * 8.0f * 1.0f);
        table.add(image).width(f2573g * 120.0f * 1.0f).height(f2573g * 108.0f * 1.0f);
        table.add(image2).width(f2573g * 162.0f * 1.0f).height(f2573g * 108.0f * 1.0f);
        row().width(f2573g * 560.0f);
        add(label).padLeft(f2573g * 10.0f).padTop(f2573g * 15.0f).center().width(f2573g * 560.0f).align(1);
        row().space(f2573g * 10.0f).height(f2573g * 108.0f * 1.0f).expandY();
        add(table);
        row();
        add(label2).left().padLeft(f2573g * 10.0f).expandX();
        row();
        add(label3).left().padLeft(f2573g * 10.0f).width(f2573g * 560.0f).spaceTop(f2573g * 8.0f);
        row();
        add(label4).left().padLeft(f2573g * 10.0f).width(f2573g * 560.0f);
    }

    public final void a(String str) {
        this.f2580f = str;
        this.f2575a.setText(GameWorld.f3188b.a(str).c());
        this.f2578d.setDrawable(new TextureRegionDrawable(GameWorld.f3189c.c(GameWorld.f3188b.a(this.f2580f).faction_id).b()));
        Image image = this.f2579e;
        Castle castleA = GameWorld.f3188b.a(this.f2580f);
        image.setDrawable(new TextureRegionDrawable(GameWorld.f3189c.c(castleA.faction_id2) != null ? GameWorld.f3189c.c(castleA.faction_id2).b() : GameAssets.f3358z));
        Label label = this.f2576b;
        StringBuilder sb = new StringBuilder("[BLACK]");
        Castle castleA2 = GameWorld.f3188b.a(this.f2580f);
        String str2 = "";
        int iE = castleA2.faction_id2.equals("") ? GameWorld.f3189c.c(castleA2.faction_id).e() : (GameWorld.f3189c.c(castleA2.faction_id2).e() + GameWorld.f3189c.c(castleA2.faction_id).e()) / 2;
        if (iE == -255) {
            iE = 0;
        }
        sb.append(FDUtils.e((iE <= -80 ? GameString.b("ARCH-ENEMY_DESC", false) : iE <= -60 ? GameString.b("ENEMY_DESC", false) : iE <= -50 ? GameString.b("CRIMINAL_DESC", false) : iE <= -20 ? GameString.b("BANDIT_DESC", false) : iE <= -5 ? GameString.b("RASCAL_DESC", false) : iE <= 9 ? GameString.b("UNKNOWN_DESC", false) : iE <= 24 ? GameString.b("FRIENDLY_DESC", false) : iE <= 39 ? GameString.b("TRUSTED_DESC", false) : iE <= 59 ? GameString.b("HERO_DESC", false) : iE <= 79 ? GameString.b("GREAT_HERO_DESC", false) : iE <= 100 ? GameString.b("LEGENDARY_HERO_DESC", false) : "").replace("#REPUTATION#", "[BLUE]" + WorldFaction.d(iE).toLowerCase(Locale.ENGLISH) + "(" + iE + ")[]")));
        sb.append("[]");
        label.setText(sb.toString());
        Label label2 = this.f2577c;
        Castle castleA3 = GameWorld.f3188b.a(this.f2580f);
        if (!castleA3.faction_id2.equals("")) {
            str2 = "[BLACK]" + GameString.b("TH_LABEL_AVERAGE", false).replace("{1}", GameWorld.f3189c.c(castleA3.faction_id).c()).replace("{2}", GameWorld.f3189c.c(castleA3.faction_id2).c()) + "[]";
        }
        label2.setText(str2);
        setVisible(true);
    }
}

package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.Party;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: InnDescriptionTable.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a1 extends Table {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static float f2527e = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static float f2528f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Label f2529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Label f2530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Label f2531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Label f2532d;

    public a1() {
        super(Assets.g());
        Label label = new Label("", Assets.g(), "menuLabelStrongVeryLargeStyle");
        this.f2529a = label;
        Label label2 = new Label("", GameAssets.S);
        this.f2530b = label2;
        Label label3 = new Label("", GameAssets.S);
        this.f2531c = label3;
        Label label4 = new Label("", GameAssets.S);
        this.f2532d = label4;
        setBackground(Assets.g().getDrawable("windowbg"));
        setWidth(f2527e * 586.0f);
        setHeight(f2527e * 290.0f);
        f2528f = f2527e;
        if (ExiledKingdoms.f3378h) {
            f2527e = 0.8f;
            f2528f = 1.0f;
            setWidth(468.80002f);
            setHeight(232.0f);
        }
        label.setWrap(true);
        label2.setWrap(true);
        label3.setWrap(true);
        label4.setWrap(true);
        label.setFontScale(f2528f);
        label2.setFontScale(f2528f);
        label3.setFontScale(f2528f);
        label4.setFontScale(f2528f);
        row().width(f2527e * 560.0f);
        add(label).padLeft(f2527e * 10.0f).center();
        row().top().spaceTop(f2527e * 25.0f);
        add(label3).left().padLeft(f2527e * 10.0f).width(f2527e * 560.0f);
        row().top().spaceTop(f2527e * 10.0f);
        add(label4).left().padLeft(f2527e * 10.0f).width(f2527e * 560.0f);
        row().top().spaceTop(f2527e * 15.0f);
        add(label2).left().padLeft(f2527e * 10.0f).width(f2527e * 560.0f);
    }

    public final void a(String str, boolean z2) {
        String name;
        Label label = this.f2529a;
        StringBuilder sb = new StringBuilder();
        a.a.w("INN_TITLE", false, sb, " ");
        sb.append(GameString.b(str, true));
        label.setText(sb.toString());
        Label label2 = this.f2530b;
        if (z2) {
            a.a.x("INN_GOSSIP_POSSIBLE", false, new StringBuilder("[BLACK]"), "[]", label2);
        } else {
            a.a.x("INN_GOSSIP_IMPOSSIBLE", false, new StringBuilder("[BLACK]"), "[]", label2);
        }
        this.f2531c.setText("[TEAL]" + FDUtils.h() + "[]");
        Label label3 = this.f2532d;
        StringBuilder sb2 = new StringBuilder("[NAVY]");
        sb2.append(GameString.b("GOSSIP_BONUS", false));
        sb2.append(":[] [BLACK]");
        sb2.append(GameData.v().party.h());
        sb2.append("%, ");
        Party party = GameData.v().party;
        if (party.j()) {
            int iU = party.f().sheet.u();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            name = iU >= GameData.v().player.sheet.u() ? party.f().getName() : GameData.v().player.getName();
        } else {
            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
            name = GameData.v().player.getName();
        }
        sb2.append(name);
        sb2.append("[]");
        label3.setText(sb2.toString());
        setVisible(true);
    }
}

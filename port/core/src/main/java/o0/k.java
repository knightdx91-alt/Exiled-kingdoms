package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import n0.k1;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.ItemAttributes;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: ItemPreviewTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class k extends Table {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static float f3566g = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static float f3567h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Label f3568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Label f3569b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Label f3570c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Label f3571d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f3572e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f3573f;

    /* JADX INFO: compiled from: ItemPreviewTable.java */
    final class a extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3574a;

        /* JADX INFO: renamed from: o0.k$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ItemPreviewTable.java */
        final class C0043a extends k1 {
        }

        a(int i2) {
            this.f3574a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            String strB = Rules.f(this.f3574a).b();
            if (strB.equals("")) {
                return true;
            }
            new C0043a(strB, 0).show(n0.z.v().a());
            return true;
        }
    }

    /* JADX INFO: compiled from: ItemPreviewTable.java */
    final class b extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3575a;

        /* JADX INFO: compiled from: ItemPreviewTable.java */
        final class a extends k1 {
        }

        b(int i2) {
            this.f3575a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            String strB = Rules.f(this.f3575a).b();
            if (strB.equals("")) {
                return true;
            }
            new a(strB, 0).show(n0.z.v().a());
            return true;
        }
    }

    /* JADX INFO: compiled from: ItemPreviewTable.java */
    final class c extends InputListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3576a;

        /* JADX INFO: compiled from: ItemPreviewTable.java */
        final class a extends k1 {
        }

        c(int i2) {
            this.f3576a = i2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            String strB = Rules.f(this.f3576a).b();
            if (strB.equals("")) {
                return true;
            }
            new a(strB, 0).show(n0.z.v().a());
            return true;
        }
    }

    public k() {
        Label label = new Label("", Assets.g(), "menuLabelStrongMidLargeStyle");
        this.f3568a = label;
        Label label2 = new Label("", Assets.g(), "menuLabelBlueStrongStyle");
        this.f3569b = label2;
        Label label3 = new Label("", Assets.g(), "menuLabelStyle");
        this.f3570c = label3;
        Label label4 = new Label("", GameAssets.S);
        this.f3571d = label4;
        j jVar = new j();
        this.f3572e = jVar;
        this.f3573f = new i();
        f3567h = f3566g;
        if (ExiledKingdoms.f3378h) {
            f3566g = 0.8f;
            f3567h = 1.0f;
        }
        float f2 = f3566g;
        float f3 = 4.0f * f2;
        float f4 = 82.0f * f2;
        setWidth(f2 * 412.0f);
        setHeight(f3566g * 400.0f);
        label.setFontScale(f3567h);
        label4.setFontScale(f3567h);
        if (Gdx.graphics.getHeight() * 4.1f > Gdx.graphics.getWidth() * 3.0f) {
            label4.setFontScale(f3567h);
        }
        label3.setFontScale(f3567h);
        label2.setFontScale(f3567h);
        label2.setWrap(true);
        label2.setFontScale(f3567h);
        label4.setWrap(true);
        label.setWrap(true);
        i iVar = new i();
        this.f3573f = iVar;
        padTop(1.5f * f3);
        setBackground(new NinePatchDrawable(GameAssets.P));
        center().top();
        row().width(f4).top().left();
        add(iVar).height(f4).width(f4).top().left();
        Table table = new Table();
        table.top().left();
        int i2 = Settings.M() ? -4 : 0;
        table.add(label).padLeft(f3).left().align(8).width(f3566g * 315.0f).spaceBottom(1.0f * f3).spaceTop(f3566g * 20.0f);
        table.row();
        table.add(label3).padLeft(f3).left().align(8).bottom().padTop(i2);
        table.row();
        table.add(label2).padLeft(f3).left().align(8).align(4).top().width(f3566g * 320.0f).spaceTop(f3566g * 5.0f);
        label2.clearListeners();
        add(table).left().expandX();
        row().colspan(2).left();
        add(label4).width(getWidth() - f3).spaceTop(f3566g * 5.0f);
        row().colspan(2).left();
        add(jVar).width(getWidth() - (f3 * 2.0f)).spaceTop(f3566g * 5.0f).padBottom(f3566g * 0.0f);
    }

    public final void a(int i2) {
        String str;
        String string;
        String strB;
        String strK;
        WeaponStats weaponStats;
        this.f3568a.setText(Rules.g(i2));
        Label label = this.f3571d;
        if (i2 != 0) {
            str = "[#333333]" + Rules.f(i2).c() + "[]";
        } else {
            str = "";
        }
        label.setText(str);
        label.clearListeners();
        label.addListener(new a(i2));
        Label label2 = this.f3570c;
        StringBuilder sb = new StringBuilder();
        a.a.w("CLASS", false, sb, ": ");
        sb.append(i2 != 0 ? Rules.f(i2).classes.a() : "");
        label2.setText(sb.toString());
        label2.clearListeners();
        label2.addListener(new b(i2));
        this.f3572e.c(i2);
        this.f3573f.a(i2);
        Label label3 = this.f3569b;
        Item itemF = Rules.f(i2);
        ItemAttributes itemAttributes = itemF.attributes;
        if (itemAttributes.orc_slayer > 0) {
            StringBuilder sb2 = new StringBuilder("");
            a.a.w("ITEM_ORCSLAYER", false, sb2, " ");
            sb2.append(FDUtils.a(itemAttributes.orc_slayer));
            string = sb2.toString();
        } else {
            string = "";
        }
        if (itemAttributes.arcane > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS = a.a.s(string);
            a.a.w("ITEM_ARCANE", false, sbS, " ");
            sbS.append(FDUtils.a(itemAttributes.arcane));
            string = sbS.toString();
        }
        if (itemAttributes.shield > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_SHIELD", false, a.a.s(string));
        }
        if (itemAttributes.offhand > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_OFFHAND", false, a.a.s(string));
        }
        if (itemAttributes.orb > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_OFFHAND", false, a.a.s(string));
        }
        if (itemAttributes.holy > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS2 = a.a.s(string);
            a.a.w("ITEM_HOLY", false, sbS2, " ");
            sbS2.append(FDUtils.a(itemAttributes.holy));
            string = sbS2.toString();
        }
        if (itemAttributes.emp > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS3 = a.a.s(string);
            a.a.w("ITEM_EMP", false, sbS3, " ");
            sbS3.append(FDUtils.a(itemAttributes.emp));
            string = sbS3.toString();
        }
        if (itemAttributes.antirad > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_RAD", false, a.a.s(string));
        }
        if (itemAttributes.banishing > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS4 = a.a.s(string);
            a.a.w("ITEM_BANISHING", false, sbS4, " ");
            sbS4.append(FDUtils.a(itemAttributes.banishing));
            string = sbS4.toString();
        }
        if (itemAttributes.beast_slayer > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS5 = a.a.s(string);
            a.a.w("ITEM_BEASTSLAYER", false, sbS5, " ");
            sbS5.append(FDUtils.a(itemAttributes.beast_slayer));
            string = sbS5.toString();
        }
        if (itemAttributes.slow > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS6 = a.a.s(string);
            a.a.w("ITEM_SLOW", false, sbS6, " ");
            sbS6.append(FDUtils.a(itemAttributes.slow));
            string = sbS6.toString();
        }
        if (itemAttributes.stun > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS7 = a.a.s(string);
            a.a.w("ITEM_STUN", false, sbS7, " ");
            sbS7.append(FDUtils.a(itemAttributes.stun));
            string = sbS7.toString();
        }
        if (itemAttributes.paralysis > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS8 = a.a.s(string);
            a.a.w("ITEM_PARALYSIS", false, sbS8, " ");
            sbS8.append(FDUtils.a(itemAttributes.paralysis));
            string = sbS8.toString();
        }
        if (itemAttributes.vorpal > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS9 = a.a.s(string);
            a.a.w("ITEM_VORPAL", false, sbS9, " ");
            sbS9.append(FDUtils.a(itemAttributes.vorpal));
            string = sbS9.toString();
        }
        if (itemAttributes.silver > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_SILVER", false, a.a.s(string));
        }
        if (itemAttributes.stability > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_STABILITY", false, a.a.s(string));
        }
        if (itemAttributes.stunimmunity > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            string = a.a.n("ITEM_STUNIMMUNITY", false, a.a.s(string));
        }
        if (itemAttributes.vicious > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS10 = a.a.s(string);
            a.a.w("ITEM_VICIOUS", false, sbS10, " ");
            sbS10.append(FDUtils.a(itemAttributes.vicious));
            string = sbS10.toString();
        }
        if (itemAttributes.detection > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS11 = a.a.s(string);
            a.a.w("ITEM_DETECTION", false, sbS11, " ");
            sbS11.append(FDUtils.a(itemAttributes.detection));
            string = sbS11.toString();
        }
        if (itemAttributes.gossip > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS12 = a.a.s(string);
            a.a.w("ITEM_GOSSIP", false, sbS12, " ");
            sbS12.append(FDUtils.a(itemAttributes.gossip));
            string = sbS12.toString();
        }
        if (itemAttributes.tinkering > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS13 = a.a.s(string);
            a.a.w("ITEM_TINKERING", false, sbS13, " ");
            sbS13.append(FDUtils.a(itemAttributes.tinkering));
            string = sbS13.toString();
        }
        if (itemAttributes.wisdom > 0) {
            if (string != "") {
                string = a.a.k(string, ", ");
            }
            StringBuilder sbS14 = a.a.s(string);
            a.a.w("ITEM_WISDOM", false, sbS14, " ");
            sbS14.append(FDUtils.a(itemAttributes.wisdom));
            string = sbS14.toString();
        }
        if (itemF.type != Item.ItemType.WEAPON || (weaponStats = itemF.weaponStats) == null) {
            strB = "";
        } else {
            strB = (!weaponStats.twohanded || weaponStats.ranged) ? (!weaponStats.ranged || weaponStats.c()) ? itemF.weaponStats.light ? GameString.b("WEAPON_LIGHT", false) : "" : GameString.b("WEAPON_RANGED", false) : GameString.b("WEAPON_2HANDED", false);
            if (itemF.weaponStats.wand) {
                strB = GameString.b("WEAPON_RANGED", false);
            }
            if (itemF.weaponStats.staff) {
                StringBuilder sb3 = new StringBuilder();
                a.a.w("WEAPON_RANGED", false, sb3, ", ");
                strB = a.a.n("WEAPON_2HANDED", false, sb3);
            }
        }
        if (string.equals("") || strB.equals("")) {
            strK = a.a.k(string, strB);
        } else {
            strK = string + ", " + strB;
        }
        label3.setText(strK);
        label3.clearListeners();
        label3.addListener(new c(i2));
    }
}

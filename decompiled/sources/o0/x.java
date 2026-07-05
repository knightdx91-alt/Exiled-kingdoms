package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import java.text.DecimalFormat;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.SheetBonus;
import net.fdgames.GameEntities.CharacterSheet.SheetEffect;
import net.fdgames.GameEntities.CharacterSheet.SheetEffects;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.Quests;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.ItemAttributes;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;

/* JADX INFO: compiled from: StatsDetailWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class x extends Window {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static x f3677d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static float f3678e = Gdx.graphics.getHeight() / 720.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static float f3679f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    CharacterSheet f3680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Table f3681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ScrollPane f3682c;

    /* JADX INFO: compiled from: StatsDetailWindow.java */
    final class a extends InputListener {
        a() {
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public final boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            x.this.setVisible(false);
            return true;
        }
    }

    public static void a() {
        if (f3677d != null) {
            f3677d = null;
        }
    }

    public static x b() {
        if (f3677d == null) {
            x xVar = new x("", Assets.g());
            n0.t tVar = new n0.t(GameString.b("BACK", false), Assets.g(), "menuButton");
            Table table = new Table();
            xVar.f3681b = table;
            ScrollPane scrollPane = new ScrollPane(table);
            xVar.f3682c = scrollPane;
            Image image = new Image();
            xVar.setBackground(Assets.g().getDrawable("windowbg"));
            xVar.setMovable(false);
            xVar.setModal(true);
            xVar.setWidth(f3678e * 750.0f);
            xVar.setHeight(f3678e * 600.0f);
            xVar.setPosition(Gdx.graphics.getWidth() - (xVar.getWidth() - (f3678e * 50.0f)), (Gdx.graphics.getHeight() - xVar.getHeight()) / 2.0f);
            f3679f = f3678e;
            if (ExiledKingdoms.f3378h) {
                f3678e = 0.8f;
                f3679f = 1.0f;
                xVar.setWidth(624.0f);
                xVar.setHeight(f3678e * 660.0f);
                xVar.setPosition(Gdx.graphics.getWidth() - (xVar.getWidth() - (f3678e * 100.0f)), (Gdx.graphics.getHeight() - xVar.getHeight()) / 2.0f);
            }
            scrollPane.setForceScroll(false, true);
            scrollPane.setScrollbarsOnTop(true);
            if (ExiledKingdoms.f3378h) {
                ScrollPane scrollPane2 = new ScrollPane(table, Assets.g());
                xVar.f3682c = scrollPane2;
                scrollPane2.setFadeScrollBars(false);
            }
            image.setDrawable(new NinePatchDrawable(GameAssets.P));
            xVar.add(xVar.f3682c).fill().expand().top().left();
            xVar.row().align(4).center();
            xVar.add(tVar).bottom().width(f3678e * 200.0f);
            tVar.addListener(xVar.new a());
            f3677d = xVar;
        }
        return f3677d;
    }

    private static Table c(String str, boolean z2, String str2) {
        Table table = new Table();
        table.pad(f3678e * 5.0f);
        String str3 = z2 ? "    -" : "";
        Label label = !z2 ? new Label(a.a.k(str3, str), Assets.g(), "menuLabelStrongStyle") : new Label(a.a.k(str3, str), Assets.g(), "menuLabelSubStrongStyle");
        label.setFontScale(f3679f);
        Label label2 = new Label(a.a.l("[BLACK]", str2, "[]"), GameAssets.S);
        label2.setWrap(true);
        if (z2) {
            label2.setFontScale(f3679f);
        } else {
            label2.setFontScale(f3679f);
        }
        table.row().expandX().top().width(f3678e * 730.0f);
        table.add(label).width(f3678e * 240.0f).left().center().top();
        table.add(label2).width(f3678e * 480.0f);
        return table;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x02e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void d(CharacterSheet characterSheet) {
        float f2;
        float f3;
        String string;
        String str;
        String strSubstring;
        SheetEffect sheetEffectB;
        Item itemF;
        setVisible(true);
        this.f3680a = characterSheet;
        Table table = this.f3681b;
        table.clearChildren();
        table.align(8).align(2).left();
        table.row().top().pad(f3678e * 5.0f).space(f3678e * 10.0f).center();
        Label label = new Label(GameString.b("HELP_STATS", false), Assets.g(), "menuLabelStrongLargeStyle");
        label.setFontScale(f3679f);
        table.add(label);
        table.row();
        String strO = a.a.o("ARMOR", false, new StringBuilder(), ":");
        String strB = GameString.b("HELP_ARMOR", false);
        StringBuilder sb = new StringBuilder(" ");
        if (this.f3680a.j() < 2) {
            string = "[BLUE]" + this.f3680a.j() + "[]";
        } else {
            StringBuilder sb2 = new StringBuilder("[BLUE]");
            int iJ = this.f3680a.j();
            if (this.f3680a.Z()) {
                f2 = iJ;
                f3 = 0.4f;
            } else {
                f2 = iJ;
                f3 = 0.2f;
            }
            sb2.append((int) (f2 * f3));
            sb2.append("-");
            sb2.append(this.f3680a.j());
            sb2.append("[]");
            string = sb2.toString();
        }
        sb.append(string);
        table.add(c(strO, false, strB.replace("{ARMOR-VALUE}", sb.toString())));
        table.row();
        String strB2 = GameString.b("HELP_ARMOR_ARROWS", false);
        StringBuilder sb3 = new StringBuilder(" ");
        if (this.f3680a.k() < 2) {
            str = "[BLUE]" + this.f3680a.k() + "[]";
        } else {
            str = "[BLUE]" + (this.f3680a.k() / 3) + "-" + this.f3680a.k() + "[]";
        }
        sb3.append(str);
        table.add(c("", false, strB2.replace("{ARMOR-VALUE}", sb3.toString())));
        table.row();
        String strB3 = GameString.b("HELP_DAMAGE", false);
        StringBuilder sb4 = new StringBuilder("");
        CharacterSheet characterSheet2 = this.f3680a;
        WeaponStats.weaponType weapontype = WeaponStats.weaponType.f3282a;
        CharacterEffects characterEffects = characterSheet2.effects;
        sb4.append(SheetBonus.c(characterSheet2, weapontype));
        String strReplace = strB3.replace("{1h}", sb4.toString());
        StringBuilder sb5 = new StringBuilder("");
        CharacterSheet characterSheet3 = this.f3680a;
        WeaponStats.weaponType weapontype2 = WeaponStats.weaponType.f3283b;
        CharacterEffects characterEffects2 = characterSheet3.effects;
        sb5.append(SheetBonus.c(characterSheet3, weapontype2));
        String strReplace2 = strReplace.replace("{2h}", sb5.toString());
        StringBuilder sb6 = new StringBuilder("");
        CharacterSheet characterSheet4 = this.f3680a;
        WeaponStats.weaponType weapontype3 = WeaponStats.weaponType.f3285d;
        CharacterEffects characterEffects3 = characterSheet4.effects;
        sb6.append(SheetBonus.c(characterSheet4, weapontype3));
        String strReplace3 = strReplace2.replace("{l}", sb6.toString());
        StringBuilder sb7 = new StringBuilder("");
        CharacterSheet characterSheet5 = this.f3680a;
        WeaponStats.weaponType weapontype4 = WeaponStats.weaponType.f3284c;
        CharacterEffects characterEffects4 = characterSheet5.effects;
        sb7.append(SheetBonus.c(characterSheet5, weapontype4));
        table.add(c(a.a.o("DAMAGE_BONUS", false, new StringBuilder(), ":"), false, strReplace3.replace("{r}", sb7.toString())));
        table.row();
        c cVar = new c();
        if (cVar.a(this.f3680a, true, true) > 0) {
            String strO2 = a.a.o("EXTRA_DAMAGE", false, new StringBuilder(), ":");
            Table table2 = new Table();
            table2.pad(f3678e * 5.0f);
            Label label2 = new Label(strO2, Assets.g(), "menuLabelStrongStyle");
            label2.setFontScale(f3679f);
            table2.row().expandX().top().width(f3678e * 730.0f);
            table2.add(label2).width(f3678e * 240.0f).left().center().top();
            table2.add(cVar).width(f3678e * 480.0f);
            table.add(table2);
            table.row();
        }
        int i2 = this.f3680a.inventory.slot_mainhand;
        if (i2 <= 0 || (itemF = Rules.f(i2)) == null) {
            strSubstring = "";
        } else {
            ItemAttributes itemAttributes = itemF.attributes;
            if (itemAttributes.hasAttributes) {
                if (itemAttributes.orc_slayer > 0) {
                    StringBuilder sb8 = new StringBuilder("[BLUE]");
                    sb8.append(f.Q(itemAttributes.orc_slayer * 2));
                    sb8.append("[]");
                    strSubstring = a.a.o("DAMAGE_ORCSLAYER", false, sb8, "\r\n");
                } else {
                    strSubstring = "";
                }
                if (itemAttributes.holy > 0) {
                    StringBuilder sbT = a.a.t(strSubstring, "[BLUE]");
                    sbT.append(f.Q(itemAttributes.holy * 2));
                    sbT.append("[]");
                    strSubstring = a.a.o("DAMAGE_HOLY", false, sbT, "\r\n");
                }
                if (itemAttributes.banishing > 0) {
                    StringBuilder sbT2 = a.a.t(strSubstring, "[BLUE]");
                    sbT2.append(f.Q(itemAttributes.banishing * 2));
                    sbT2.append("[]");
                    strSubstring = a.a.o("DAMAGE_BANISHING", false, sbT2, "\r\n");
                }
                if (itemAttributes.beast_slayer > 0) {
                    StringBuilder sbT3 = a.a.t(strSubstring, "[BLUE]");
                    sbT3.append(f.Q(itemAttributes.beast_slayer * 2));
                    sbT3.append("[]");
                    strSubstring = a.a.o("DAMAGE_BEASTSLAYER", false, sbT3, "\r\n");
                }
                if (itemAttributes.vicious > 0) {
                    StringBuilder sbT4 = a.a.t(strSubstring, "[BLUE]");
                    sbT4.append(f.Q(itemAttributes.vicious * 2));
                    sbT4.append("[]");
                    strSubstring = a.a.o("DAMAGE_VICIOUS", false, sbT4, "\r\n");
                }
            }
        }
        if (this.f3680a.X() && GameData.v().player.z0() > 0) {
            StringBuilder sbT5 = a.a.t(strSubstring, "[BLUE]");
            sbT5.append(f.Q(GameData.v().player.z0() + 1));
            sbT5.append("[]");
            strSubstring = a.a.o("DAMAGE_BEASTSLAYER", false, sbT5, "\r\n");
        }
        if (strSubstring != "") {
            if (strSubstring.substring(strSubstring.length() - 1).equals("\n")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 2);
            }
            table.add(c(a.a.o("SPECIAL_DAMAGE", false, new StringBuilder(), ":"), false, strSubstring));
            table.row();
        }
        table.add(c(a.a.o("PERCEPTION", false, new StringBuilder(), ":"), false, GameString.b("HELP_PERCEPTION", false).replace("{per}", "" + this.f3680a.s())));
        table.row();
        table.add(c(a.a.o("DEVICES", false, new StringBuilder(), ":"), false, GameString.b("HELP_DEVICES", false).replace("{dev}", "" + this.f3680a.t())));
        table.row();
        table.add(c(a.a.o("TIT_GOSSIP", false, new StringBuilder(), ":"), false, GameString.b("HELP_GOSSIP", false).replace("{gos}", "" + this.f3680a.u())));
        table.row();
        StringBuilder sb9 = new StringBuilder("[BLUE]+");
        CharacterSheet characterSheet6 = this.f3680a;
        characterSheet6.getClass();
        sb9.append(SheetBonus.e(characterSheet6));
        sb9.append("%[]");
        String string2 = sb9.toString();
        CharacterSheet characterSheet7 = this.f3680a;
        SheetEffects sheetEffects = characterSheet7.sheetEffects;
        if (sheetEffects == null) {
            characterSheet7.sheetEffects = new SheetEffects();
            sheetEffectB = null;
        } else {
            sheetEffectB = sheetEffects.b(SheetEffect.a("XPBONUS"));
        }
        if (sheetEffectB != null) {
            string2 = a.a.k(string2, GameString.b("XP_BONUS_EFFECT_DESC", false).replace("{1}", "" + sheetEffectB.level).replace("{2}", FDUtils.g(sheetEffectB.expiration - GameData.v().u())));
        }
        table.add(c(a.a.o("XP_BONUS", false, new StringBuilder(), ":"), false, string2));
        table.row();
        table.add(c("XP:", false, new DecimalFormat("###,###").format(n0.z.v().V.O().sheet.stats.i())));
        table.row();
        Label label3 = new Label(GameString.b("STATS_TITLE", false), Assets.g(), "menuLabelStrongLargeStyle");
        label3.setFontScale(f3679f);
        table.add(label3).spaceTop(f3678e * 30.0f);
        table.row();
        int i3 = (int) GameData.v().realTime;
        int i4 = i3 / 3600;
        table.add(c(a.a.o("TIME_PLAYED", false, new StringBuilder(), ":"), false, String.format("%dh %dm", Integer.valueOf(i4), Integer.valueOf((i3 - (i4 * 3600)) / 60))));
        table.row();
        table.add(c(a.a.o("QUESTS_COMPLETED", false, new StringBuilder(), ":"), false, "" + GameData.v().i() + "/" + (Quests.f3196a.size() - 8)));
        table.row();
        table.add(c(a.a.o("QUESTS_TH", false, new StringBuilder(), ":"), false, "" + GameData.v().h()));
        table.row();
        table.add(c(a.a.o("SECRETS_FOUND", false, new StringBuilder(), ":"), false, "" + GameData.v().T()));
        table.row();
        table.add(c(a.a.o("STATS_ENEMIES", false, new StringBuilder(), ":"), false, "" + GameData.v().y().killed));
        table.row();
        table.add(c(a.a.o("STATS_GOBLINS", false, new StringBuilder(), ":"), true, "" + GameData.v().y().killed_goblins));
        table.row();
        table.add(c(a.a.o("STATS_ORCS", false, new StringBuilder(), ":"), true, "" + GameData.v().y().killed_orcs));
        table.row();
        table.add(c(a.a.o("STATS_UNDEAD", false, new StringBuilder(), ":"), true, "" + GameData.v().y().killed_undead));
        table.row();
        GPGSUpdate.c(true);
        table.add(c(a.a.o("SCORE", false, new StringBuilder(), ":"), false, "" + GPGSUpdate.W));
        table.row();
        table.add(c(a.a.m(GameString.b("SCORE_XP", false), ":", new StringBuilder()), true, "" + GPGSUpdate.R));
        table.row();
        table.add(c(a.a.o("SCORE_QUESTS", false, new StringBuilder(), ":"), true, "" + GPGSUpdate.U));
        table.row();
        table.add(c(a.a.o("SCORE_TH", false, new StringBuilder(), ":"), true, "" + GPGSUpdate.V));
        table.row();
        table.add(c(a.a.o("SCORE_ACHIEVEMENTS", false, new StringBuilder(), ":"), true, "" + GPGSUpdate.Q));
        table.row();
        table.add(c(a.a.o("SCORE_REP", false, new StringBuilder(), ":"), true, "" + GPGSUpdate.S));
        table.row();
        table.add(c(a.a.o("SCORE_SECRETS", false, new StringBuilder(), ":"), true, "" + GPGSUpdate.T));
        table.row();
        table.add(c(a.a.o("SCORE_DIFFICULTY", false, new StringBuilder(), ":"), true, "" + GameData.v().q()));
        table.row();
    }
}

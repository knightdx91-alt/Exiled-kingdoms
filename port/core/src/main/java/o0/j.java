package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterTraits;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameLogic.PlayerRequirements;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: ItemDescriptionTable.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class j extends Table {
    private static Label L = new Label(" ", Assets.g(), "menuLabelStyle");
    private static float M;
    private static float N;
    private static float O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CharacterResistances f3543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3545c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3546d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3548f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Damage.DamageType f3549g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private WeaponStats f3550h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int[] f3551i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private PlayerRequirements f3552j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f3553k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f3554l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f3555m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Image f3556n = new Image(new TextureRegionDrawable(Assets.a("shield_small")));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Image f3557o = new Image(new TextureRegionDrawable(Assets.a("heart")));

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Image f3558p = new Image(new TextureRegionDrawable(Assets.a("mana")));

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Image f3559q = new Image(new TextureRegionDrawable(Assets.a("fire")));

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Image f3560r = new Image(new TextureRegionDrawable(Assets.a("cold")));
    private Image s = new Image(new TextureRegionDrawable(Assets.a("shock")));

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Image f3561t = new Image(new TextureRegionDrawable(Assets.a("death")));

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Image f3562u = new Image(new TextureRegionDrawable(Assets.a("poison")));

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Image f3563v = new Image(new TextureRegionDrawable(Assets.a("holy")));

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private Image f3564w = new Image(new TextureRegionDrawable(Assets.a("fire")));

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private Image f3565z = new Image(new TextureRegionDrawable(Assets.a("cold")));
    private Image A = new Image(new TextureRegionDrawable(Assets.a("shock")));
    private Image B = new Image(new TextureRegionDrawable(Assets.a("death")));
    private Image C = new Image(new TextureRegionDrawable(Assets.a("poison")));
    private Image D = new Image(new TextureRegionDrawable(Assets.a("holy")));
    private Label E = new Label("Bonus: ", Assets.g(), "menuLabelSmallStyle");
    private Label F = new Label(a.a.m(GameString.b("DAMAGE", false), ": ", new StringBuilder()), Assets.g(), "menuLabelSubStrongStyle");
    private Label G = new Label("", Assets.g(), "menuLabelStyle");
    private Label H = new Label(a.a.o("SPEED", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
    private Label I = new Label("", Assets.g(), "menuLabelStyle");
    private Label J = new Label(a.a.o("CRITICAL", false, new StringBuilder(), ": "), Assets.g(), "menuLabelSubStrongStyle");
    private Label K = new Label("", Assets.g(), "menuLabelStyle");

    static {
        float fMin = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);
        M = fMin;
        O = fMin * 11.0f;
    }

    private static String a(int i2) {
        String str = i2 < 0 ? "-" : "+";
        if (i2 <= -10 || i2 >= 10) {
            return str + i2 + " ";
        }
        return str + i2 + "   ";
    }

    private static float b(int i2) {
        float f2;
        float f3;
        if (i2 > 9 || i2 < -99) {
            f2 = 40.0f;
            f3 = M;
        } else {
            f2 = 30.0f;
            f3 = M;
        }
        return f3 * f2;
    }

    public final void c(int i2) {
        CharacterResistances characterResistancesE;
        boolean z2;
        Image image;
        Image image2;
        Image image3;
        Image image4;
        Image image5;
        Image image6;
        Image image7;
        Image image8;
        Image image9;
        Image image10;
        Image image11;
        Image image12 = this.f3563v;
        Image image13 = this.f3562u;
        Image image14 = this.f3561t;
        Image image15 = this.s;
        Image image16 = this.f3560r;
        clearChildren();
        align(8);
        if (N == 0.0f) {
            N = 1.0f;
        }
        Label label = L;
        label.setFontScale(N);
        label.setWrap(true);
        Label label2 = this.F;
        label2.setFontScale(N);
        Label label3 = this.G;
        label3.setFontScale(N);
        Label label4 = this.H;
        label4.setFontScale(N);
        Label label5 = this.I;
        label5.setFontScale(N);
        Label label6 = this.J;
        label6.setFontScale(N);
        Label label7 = this.K;
        label7.setFontScale(N);
        Label label8 = this.E;
        label8.setFontScale(N);
        left().align(8).pad(0.0f);
        if (i2 > 0) {
            if (i2 != 0) {
                characterResistancesE = Rules.f(i2).e();
            } else {
                Item[] itemArr = Rules.f3247a;
                characterResistancesE = null;
            }
            this.f3543a = characterResistancesE;
            this.f3544b = Rules.f(i2).armorBonus;
            this.f3545c = Rules.f(i2).HPBonus;
            this.f3546d = Rules.f(i2).ManaBonus;
            this.f3551i = Rules.f(i2).traitBonus;
            Item itemF = Rules.f(i2);
            if (itemF.traitBonus == null) {
                z2 = false;
            } else {
                int i3 = 0;
                for (int i4 = 6; i3 < i4; i4 = 6) {
                    if (itemF.traitBonus[i3] != 0) {
                        z2 = true;
                        break;
                    }
                    i3++;
                }
                z2 = false;
            }
            this.f3553k = z2;
            PlayerRequirements playerRequirements = Rules.f(i2).requisites;
            this.f3554l = playerRequirements == null ? false : playerRequirements.d();
            this.f3552j = Rules.f(i2).requisites;
            int i5 = Rules.f(i2).manaCost;
            this.f3547e = Rules.f(i2).manaCost;
            int i6 = Rules.f(i2).damageBonus;
            this.f3548f = i6;
            boolean z3 = i6 > 0;
            this.f3555m = z3;
            if (z3) {
                this.f3549g = Rules.f(i2).damageBonusType;
            }
            row();
            Table table = new Table();
            N = M;
            if (ExiledKingdoms.f3378h) {
                M = 0.8f;
                N = 1.0f;
            }
            O = M * 8.0f;
            table.pad(0.0f).left().align(8);
            table.add(label8);
            boolean z4 = this.f3555m;
            Image image17 = this.f3565z;
            Image image18 = this.B;
            Image image19 = this.f3564w;
            Image image20 = this.A;
            Image image21 = this.D;
            Image image22 = this.C;
            Damage.DamageType damageType = Damage.DamageType.Normal;
            if (!z4 || this.f3548f <= 0) {
                image = image13;
                image2 = image14;
            } else {
                image = image13;
                image2 = image14;
                Label label9 = new Label(a(this.f3548f), Assets.g(), "menuLabelStyle");
                label9.setFontScale(N);
                table.add(label9).width(b(this.f3548f) - (M * 4.0f));
                Damage.DamageType damageType2 = this.f3549g;
                if (damageType2 != damageType) {
                    switch (damageType2.ordinal()) {
                        case 1:
                            table.add(image19).width(M * 24.0f).height(M * 24.0f).left();
                            break;
                        case 2:
                            table.add(image17).width(M * 24.0f);
                            break;
                        case 3:
                            table.add(image20).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 4:
                            table.add(image18).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 5:
                            table.add(image22).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 6:
                            table.add(image21).width(M * 24.0f).height(M * 24.0f);
                            break;
                    }
                }
                Label label10 = new Label(GameString.b("DMG", false), Assets.g(), "menuLabelSubStrongStyle");
                label10.setFontScale(N * 0.8f);
                table.add(label10).center().padRight((M * 4.0f) + O);
            }
            if (this.f3553k) {
                int i7 = 0;
                while (i7 < 6) {
                    if (this.f3551i[i7] != 0) {
                        image11 = image19;
                        Label label11 = new Label(a(this.f3551i[i7]), Assets.g(), "menuLabelStyle");
                        label11.setFontScale(N);
                        table.add(label11).width(b(this.f3551i[i7]));
                        Label label12 = new Label(CharacterTraits.g(i7), Assets.g(), "menuLabelSubStrongStyle");
                        label12.setFontScale(N * 0.8f);
                        table.add(label12).center().padRight((M * 4.0f) + O);
                    } else {
                        image11 = image19;
                    }
                    i7++;
                    image19 = image11;
                }
            }
            Image image23 = image19;
            if (this.f3544b != 0) {
                Label label13 = new Label(f.Q(this.f3544b), Assets.g(), "menuLabelStyle");
                label13.setFontScale(N);
                table.add(label13).width(b(this.f3544b));
                table.add(this.f3556n).width(M * 26.0f).height(M * 32.0f).padRight(O);
            }
            if (this.f3545c != 0) {
                Label label14 = new Label(f.Q(this.f3545c), Assets.g(), "menuLabelStyle");
                label14.setFontScale(N);
                table.add(label14).width(b(this.f3545c));
                table.add(this.f3557o).width(M * 26.0f).height(M * 26.0f).padRight(O);
            }
            int i8 = this.f3546d;
            Image image24 = this.f3558p;
            if (i8 != 0) {
                Label label15 = new Label(f.Q(this.f3546d), Assets.g(), "menuLabelStyle");
                label15.setFontScale(N);
                table.add(label15).width(b(this.f3546d));
                table.add(image24).width(M * 26.0f).height(M * 26.0f).padRight(O);
            }
            CharacterResistances characterResistances = this.f3543a;
            CharacterResistances.ResistanceType resistanceType = CharacterResistances.ResistanceType.Fire;
            int iA = characterResistances.a(resistanceType);
            Image image25 = this.f3559q;
            if (iA != 0) {
                image3 = image17;
                Label label16 = new Label(f.Q(this.f3543a.a(resistanceType)), Assets.g(), "menuLabelStyle");
                label16.setFontScale(N);
                table.add(label16).width(b(this.f3543a.a(resistanceType)));
                table.add(image25).width(M * 32.0f).height(M * 32.0f).padRight(O);
            } else {
                image3 = image17;
            }
            CharacterResistances characterResistances2 = this.f3543a;
            CharacterResistances.ResistanceType resistanceType2 = CharacterResistances.ResistanceType.Cold;
            if (characterResistances2.a(resistanceType2) != 0) {
                Label label17 = new Label(f.Q(this.f3543a.a(resistanceType2)), Assets.g(), "menuLabelStyle");
                label17.setFontScale(N);
                table.add(label17).width(b(this.f3543a.a(resistanceType2)));
                table.add(image16).width(M * 32.0f).height(M * 32.0f).padRight(O);
            }
            CharacterResistances characterResistances3 = this.f3543a;
            CharacterResistances.ResistanceType resistanceType3 = CharacterResistances.ResistanceType.Shock;
            if (characterResistances3.a(resistanceType3) != 0) {
                Label label18 = new Label(f.Q(this.f3543a.a(resistanceType3)), Assets.g(), "menuLabelStyle");
                label18.setFontScale(N);
                table.add(label18).width(b(this.f3543a.a(resistanceType3)));
                table.add(image15).width(M * 32.0f).height(M * 32.0f).padRight(O);
            }
            CharacterResistances characterResistances4 = this.f3543a;
            CharacterResistances.ResistanceType resistanceType4 = CharacterResistances.ResistanceType.Death;
            if (characterResistances4.a(resistanceType4) != 0) {
                Label label19 = new Label(f.Q(this.f3543a.a(resistanceType4)), Assets.g(), "menuLabelStyle");
                label19.setFontScale(N);
                table.add(label19).width(b(this.f3543a.a(resistanceType4)));
                image4 = image2;
                table.add(image4).width(M * 32.0f).height(M * 32.0f).padRight(O);
            } else {
                image4 = image2;
            }
            CharacterResistances characterResistances5 = this.f3543a;
            CharacterResistances.ResistanceType resistanceType5 = CharacterResistances.ResistanceType.Toxic;
            if (characterResistances5.a(resistanceType5) != 0) {
                image5 = image20;
                Label label20 = new Label(f.Q(this.f3543a.a(resistanceType5)), Assets.g(), "menuLabelStyle");
                label20.setFontScale(N);
                table.add(label20).width(b(this.f3543a.a(resistanceType5)));
                image6 = image;
                table.add(image6).width(M * 32.0f).height(M * 32.0f).padRight(O);
            } else {
                image5 = image20;
                image6 = image;
            }
            CharacterResistances characterResistances6 = this.f3543a;
            CharacterResistances.ResistanceType resistanceType6 = CharacterResistances.ResistanceType.Spirit;
            if (characterResistances6.a(resistanceType6) != 0) {
                image7 = image18;
                Label label21 = new Label(f.Q(this.f3543a.a(resistanceType6)), Assets.g(), "menuLabelStyle");
                label21.setFontScale(N);
                table.add(label21).width(b(this.f3543a.a(resistanceType6)));
                image8 = image12;
                table.add(image8).width(M * 32.0f).height(M * 32.0f).padRight(O);
            } else {
                image7 = image18;
                image8 = image12;
            }
            if (!this.f3553k && this.f3544b == 0 && this.f3545c == 0 && this.f3546d == 0 && !this.f3543a.b() && !this.f3555m) {
                table.clear();
            }
            if (this.f3554l) {
                StringBuilder sb = new StringBuilder("[BROWN]");
                image9 = image22;
                image10 = image21;
                a.a.w("REQ", false, sb, ":[] [BLACK]");
                sb.append(this.f3552j.c(n0.z.v().V.O().sheet));
                sb.append("[]     ");
                Label label22 = new Label(sb.toString(), GameAssets.S);
                label22.setFontScale(N);
                table.add(label22);
            } else {
                image9 = image22;
                image10 = image21;
            }
            if (this.f3547e > 0) {
                StringBuilder sb2 = new StringBuilder("[BROWN]");
                a.a.w("CASTING_COST", false, sb2, ":[] [BLACK]");
                Label label23 = new Label(a.a.p(sb2, this.f3547e, "[]  "), GameAssets.S);
                label23.setFontScale(N);
                table.add(label23).width(M * 120.0f);
                table.add(image24).width(M * 26.0f).height(M * 26.0f).padRight(O);
            }
            add(table).left();
            if (Rules.f(i2).type == Item.ItemType.WEAPON) {
                this.f3550h = Rules.f(i2).weaponStats;
                row();
                Table table2 = new Table();
                table2.pad(0.0f).left().align(8);
                label3.setText("" + this.f3550h.minDamage + "-" + this.f3550h.maxDamage);
                table2.add(label2);
                table2.add(label3);
                Damage.DamageType damageType3 = this.f3550h.damageType;
                if (damageType3 != damageType) {
                    switch (damageType3.ordinal()) {
                        case 1:
                            table2.add(image25).width(M * 24.0f).height(M * 24.0f).left();
                            break;
                        case 2:
                            table2.add(image16).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 3:
                            table2.add(image15).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 4:
                            table2.add(image4).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 5:
                            table2.add(image6).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 6:
                            table2.add(image8).width(M * 24.0f).height(M * 24.0f);
                            break;
                    }
                }
                if (this.f3550h.has_secondary_damage) {
                    label3.setText(((Object) label3.getText()) + ", +" + this.f3550h.secondary_Damage);
                    switch (this.f3550h.secondary_damageType.ordinal()) {
                        case 1:
                            table2.add(image23).width(M * 24.0f).height(M * 24.0f).left();
                            break;
                        case 2:
                            table2.add(image3).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 3:
                            table2.add(image5).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 4:
                            table2.add(image7).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 5:
                            table2.add(image9).width(M * 24.0f).height(M * 24.0f);
                            break;
                        case 6:
                            table2.add(image10).width(M * 24.0f).height(M * 24.0f);
                            break;
                    }
                }
                add(table2).left();
                row().spaceTop(M * 4.0f);
                Table table3 = new Table();
                table3.pad(0.0f).left().align(8);
                label5.setText("" + this.f3550h.speed);
                label7.setText(this.f3550h.critChance + "% ");
                table3.add(label4);
                table3.add(label5).padRight(O);
                table3.add(label6);
                table3.add(label7).padRight(O);
                add(table3);
            }
        }
    }
}

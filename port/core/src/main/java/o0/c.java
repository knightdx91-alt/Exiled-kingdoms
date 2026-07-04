package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.Rules.Rules;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CharacterDamageDescription.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class c extends Table {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f3464a = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static float f3465b;

    public c() {
        new Image(new TextureRegionDrawable(Assets.a("fire")));
        new Image(new TextureRegionDrawable(Assets.a("cold")));
        new Image(new TextureRegionDrawable(Assets.a("shock")));
        new Image(new TextureRegionDrawable(Assets.a("death")));
        new Image(new TextureRegionDrawable(Assets.a("poison")));
        new Image(new TextureRegionDrawable(Assets.a("holy")));
    }

    public final int a(CharacterSheet characterSheet, boolean z2, boolean z3) {
        Label label;
        int i2;
        int iG;
        float f2;
        float f3;
        float f4;
        Damage.DamageType damageType;
        Label label2;
        float f5;
        clear();
        f3465b = f3464a;
        if (ExiledKingdoms.f3378h) {
            f3464a = 0.8f;
            f3465b = 1.0f;
        }
        ArrayList<Damage> arrayList = characterSheet.h().damages;
        pad(0.0f).center().align(8);
        float fMax = characterSheet.effects.fury.booleanValue() ? Math.max(characterSheet.effects.furyMultiplier, 1.0f) : 1.0f;
        boolean zQ = characterSheet.inventory.q();
        Damage.DamageType damageType2 = Damage.DamageType.Normal;
        int iL = (zQ && Rules.f(characterSheet.inventory.slot_offhand).damageBonusType == damageType2) ? characterSheet.inventory.l() : 0;
        String str = "";
        if (z2 || z3) {
            label = new Label("", GameAssets.S);
        } else {
            StringBuilder sb = new StringBuilder("");
            float f6 = iL;
            sb.append((int) ((characterSheet.B(false) * fMax) + f6));
            sb.append("-");
            sb.append((int) ((characterSheet.y(false) * fMax) + f6));
            label = new Label(sb.toString(), Assets.g(), "menuLabelStyle");
        }
        label.setFontScale(f3465b);
        add(label);
        if (arrayList.get(0).type != damageType2) {
            add(new Image(arrayList.get(0).a())).width(f3464a * 24.0f).height(f3464a * 24.0f).left();
        }
        if (arrayList.size() > 1) {
            i2 = 0;
            for (int i3 = 1; i3 < arrayList.size(); i3++) {
                Damage damage = arrayList.get(i3);
                if (damage != null && (damageType = damage.type) != null && damageType != damageType2) {
                    i2++;
                    if (z2) {
                        label2 = new Label("[BLUE]" + f.Q(damage.hp) + "[]", GameAssets.S);
                        f5 = 0.7f;
                    } else {
                        label2 = new Label(", " + f.Q(damage.hp), Assets.g(), "menuLabelStyle");
                        f5 = 1.0f;
                    }
                    label2.setFontScale(f3465b);
                    Cell cellAdd = add(label2);
                    int i4 = damage.hp;
                    cellAdd.width(((i4 > 9 || i4 < -9) ? f3464a * 58.0f : f3464a * 44.0f) * f5);
                    add(new Image(damage.a())).width(f3464a * 24.0f).height(f3464a * 24.0f).left();
                    if (z2) {
                        add(new Label(" ", GameAssets.S));
                    }
                }
            }
        } else {
            i2 = 0;
        }
        if (z3 && (iG = characterSheet.skillSet.g("crusader")) > 0) {
            if (i2 > 0) {
                str = "[BLACK], []";
                f2 = 1.0f;
            } else {
                f2 = 0.7f;
            }
            i2++;
            StringBuilder sbT = a.a.t(str, "[BLUE]");
            int i5 = iG * 2;
            sbT.append(f.Q(i5));
            sbT.append("[]");
            Label label3 = new Label(sbT.toString(), GameAssets.S);
            label3.setFontScale(f3465b);
            Cell cellAdd2 = add(label3);
            if (i5 > 9 || i5 < -9) {
                f3 = f3464a;
                f4 = 58.0f;
            } else {
                f3 = f3464a;
                f4 = 44.0f;
            }
            cellAdd2.width(f3 * f4 * f2);
            add(new Image(Assets.a("holy"))).width(f3464a * 24.0f).height(f3464a * 24.0f).left();
            Label label4 = new Label(a.a.o("DAMAGE_HOLY", false, new StringBuilder(" [BLACK]"), "[]"), GameAssets.S);
            label4.setFontScale(f3465b);
            add(label4);
        }
        return i2;
    }
}

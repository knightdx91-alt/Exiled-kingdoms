package net.fdgames.GameEntities.Helpers;

import a.a;
import android.app.cYK.BQmoQ;
import androidx.drawerlayout.widget.Qm.ReTXwDyZpZSd;
import com.badlogic.gdx.Gdx;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import java.util.ArrayList;
import n0.WOA.jzidqMPaLNVH;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.BestiaryEntry;
import net.fdgames.Helpers.Serializer;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class BestiaryData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<BestiaryEntry> f3259a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static CharacterSheet f3260b;

    public static void a(NPC npc) {
        if (ExiledKingdoms.f3606h) {
            CharacterSheet characterSheet = npc.sheet;
            f3260b = characterSheet;
            DamageData damageDataH = characterSheet.h();
            ArrayList<BestiaryEntry> arrayList = f3259a;
            for (BestiaryEntry bestiaryEntry : arrayList) {
                if (bestiaryEntry.id.equals(npc.getName()) && bestiaryEntry.level == f3260b.stats.f() && bestiaryEntry.skills.equals(f3260b.skillSet.toString())) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder(ReTXwDyZpZSd.JuRZzk);
            sb.append(GameData.v().r());
            sb.append("] ; Name: ");
            sb.append(npc.getName());
            sb.append(" ; Spawn: ");
            sb.append(npc.spawn_id);
            sb.append(" ; Level: ");
            sb.append(f3260b.stats.f());
            sb.append(" ; HP: ");
            sb.append(f3260b.z());
            sb.append(" ; Mana: ");
            sb.append(f3260b.A());
            sb.append(" ; Armor: ");
            sb.append(f3260b.j());
            sb.append(" ; Resistances: ");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3210b));
            sb.append(",");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3211c));
            sb.append(",");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3212d));
            sb.append(",");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3213e));
            sb.append(",");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3214f));
            sb.append(",");
            sb.append(f3260b.F(CharacterResistances.ResistanceType.f3215g));
            sb.append(" ; Movement: ");
            sb.append(npc.speedModifier);
            sb.append(" ; DPS: ");
            sb.append(((int) (f3260b.q() * 100.0f)) / 100.0f);
            sb.append(" ; Attack Speed: ");
            sb.append(f3260b.H());
            sb.append(" ; Range: ");
            sb.append((int) (f3260b.a() / 32.0f));
            sb.append(" ; Base damage: (");
            sb.append(damageDataH.damages.get(0).type.toString());
            sb.append(BQmoQ.FYXeHFrOtU);
            sb.append(f3260b.B(false));
            sb.append("-");
            sb.append(f3260b.y(false));
            sb.append(" ; Critical: ");
            sb.append(f3260b.n());
            sb.append("% (x");
            int i2 = f3260b.skillSet.bonusSet.critDamageModifier;
            sb.append((i2 > 150 ? i2 : 150.0f) / 100.0f);
            sb.append(") ; ");
            String string = sb.toString();
            if (damageDataH.damages.size() > 1) {
                StringBuilder sbS = a.s(string, jzidqMPaLNVH.ydgQadTxp);
                sbS.append(damageDataH.damages.get(1).type.toString());
                sbS.append(")");
                string = a.p(sbS, damageDataH.damages.get(1).hp, " ; ");
            }
            StringBuilder sbS2 = a.s(string, "Attributes: ");
            sbS2.append(f3260b.attributes.toString());
            sbS2.append(" ; ");
            StringBuilder sbS3 = a.s(sbS2.toString(), "Skills: ");
            sbS3.append(f3260b.skillSet.toString());
            sbS3.append(" ; ");
            String string2 = sbS3.toString();
            BestiaryEntry bestiaryEntry2 = new BestiaryEntry();
            bestiaryEntry2.id = npc.getName();
            bestiaryEntry2.level = f3260b.stats.f();
            bestiaryEntry2.data = string2;
            bestiaryEntry2.skills = f3260b.skillSet.toString();
            arrayList.add(bestiaryEntry2);
            String str = string2 + qJDUJ.INUwj;
            if (Serializer.f3447c) {
                if (Serializer.f3446b == null) {
                    Serializer.f3446b = Gdx.files.local("monsterdump.txt");
                }
                Serializer.f3446b.writeString(str, true);
            }
        }
    }
}

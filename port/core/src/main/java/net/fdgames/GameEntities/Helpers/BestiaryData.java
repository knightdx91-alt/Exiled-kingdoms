package net.fdgames.GameEntities.Helpers;

import a.a;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.BestiaryEntry;
import net.fdgames.Helpers.Serializer;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class BestiaryData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<BestiaryEntry> f3044a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static CharacterSheet f3045b;

    public static void a(NPC npc) {
        if (ExiledKingdoms.f3378h) {
            CharacterSheet characterSheet = npc.sheet;
            f3045b = characterSheet;
            DamageData damageDataH = characterSheet.h();
            ArrayList<BestiaryEntry> arrayList = f3044a;
            for (BestiaryEntry bestiaryEntry : arrayList) {
                if (bestiaryEntry.id.equals(npc.getName()) && bestiaryEntry.level == f3045b.stats.f() && bestiaryEntry.skills.equals(f3045b.skillSet.toString())) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder("[DIFF.:");
            sb.append(GameData.v().r());
            sb.append("] ; Name: ");
            sb.append(npc.getName());
            sb.append(" ; Spawn: ");
            sb.append(npc.spawn_id);
            sb.append(" ; Level: ");
            sb.append(f3045b.stats.f());
            sb.append(" ; HP: ");
            sb.append(f3045b.z());
            sb.append(" ; Mana: ");
            sb.append(f3045b.A());
            sb.append(" ; Armor: ");
            sb.append(f3045b.j());
            sb.append(" ; Resistances: ");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f2995a));
            sb.append(",");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f2996b));
            sb.append(",");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f2997c));
            sb.append(",");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f2998d));
            sb.append(",");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f2999e));
            sb.append(",");
            sb.append(f3045b.F(CharacterResistances.ResistanceType.f3000f));
            sb.append(" ; Movement: ");
            sb.append(npc.speedModifier);
            sb.append(" ; DPS: ");
            sb.append(((int) (f3045b.q() * 100.0f)) / 100.0f);
            sb.append(" ; Attack Speed: ");
            sb.append(f3045b.H());
            sb.append(" ; Range: ");
            sb.append((int) (f3045b.a() / 32.0f));
            sb.append(" ; Base damage: (");
            sb.append(damageDataH.damages.get(0).type.toString());
            sb.append(") ");
            sb.append(f3045b.B(false));
            sb.append("-");
            sb.append(f3045b.y(false));
            sb.append(" ; Critical: ");
            sb.append(f3045b.n());
            sb.append("% (x");
            int i2 = f3045b.skillSet.bonusSet.critDamageModifier;
            sb.append((i2 > 150 ? i2 : 150.0f) / 100.0f);
            sb.append(") ; ");
            String string = sb.toString();
            if (damageDataH.damages.size() > 1) {
                StringBuilder sbT = a.t(string, "Extra damage: (");
                sbT.append(damageDataH.damages.get(1).type.toString());
                sbT.append(")");
                string = a.p(sbT, damageDataH.damages.get(1).hp, " ; ");
            }
            StringBuilder sbT2 = a.t(string, "Attributes: ");
            sbT2.append(f3045b.attributes.toString());
            sbT2.append(" ; ");
            StringBuilder sbT3 = a.t(sbT2.toString(), "Skills: ");
            sbT3.append(f3045b.skillSet.toString());
            sbT3.append(" ; ");
            String string2 = sbT3.toString();
            BestiaryEntry bestiaryEntry2 = new BestiaryEntry();
            bestiaryEntry2.id = npc.getName();
            bestiaryEntry2.level = f3045b.stats.f();
            bestiaryEntry2.data = string2;
            bestiaryEntry2.skills = f3045b.skillSet.toString();
            arrayList.add(bestiaryEntry2);
            String str = string2 + "\r\n";
            if (Serializer.f3226c) {
                if (Serializer.f3225b == null) {
                    Serializer.f3225b = Gdx.files.local("monsterdump.txt");
                }
                Serializer.f3225b.writeString(str, true);
            }
        }
    }
}

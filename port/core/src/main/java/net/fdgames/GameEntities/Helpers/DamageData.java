package net.fdgames.GameEntities.Helpers;

import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.Helpers.Damage;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class DamageData extends MessageData {
    public boolean critical;
    public ArrayList<Damage> damages;
    public ArrayList<DamageEffect> procs;
    public boolean surprised;
    public int weapon_item_id;

    public DamageData(Damage.DamageType damageType, int i2, boolean z2) {
        this.damages = new ArrayList<>();
        this.procs = new ArrayList<>();
        this.damages = new ArrayList<>();
        a(damageType, i2, z2);
    }

    public final void a(Damage.DamageType damageType, int i2, boolean z2) {
        this.damages.add(new Damage(damageType, i2, z2));
    }

    public final boolean b() {
        ArrayList<Damage> arrayList = this.damages;
        if (arrayList == null) {
            return false;
        }
        Iterator<Damage> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().type.equals(Damage.DamageType.Fire)) {
                return true;
            }
        }
        return false;
    }

    public DamageData() {
        this.damages = new ArrayList<>();
        this.procs = new ArrayList<>();
    }
}

package net.fdgames.GameWorld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Rules;
import net.fdgames.ek.GPGSUpdate;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Party {
    public ArrayList<Follower> followers = new ArrayList<>();
    public ArrayList<NPC> companions = new ArrayList<>();
    private String activeCompanion = "";
    private boolean hiddenFollowers = false;
    private String hiddenCompanion = "";

    public static void q() {
        for (NPC npc : GameLevelData.o().npcs) {
            if (npc.k0()) {
                npc.f3307x = GameData.v().player.f3307x;
                npc.f3308y = GameData.v().player.f3308y;
            }
        }
    }

    public final void a(NPC npc) {
        if (this.companions == null) {
            this.companions = new ArrayList<>();
        }
        if (npc == null) {
            return;
        }
        this.activeCompanion = npc.spawn_id;
        Iterator<NPC> it = this.companions.iterator();
        while (it.hasNext()) {
            if (it.next().spawn_id.equals(npc.spawn_id)) {
                r();
                z.w().V();
                return;
            }
        }
        this.companions.add(npc);
        r();
        z.w().V();
        GPGSUpdate.c(false);
        if (npc.gender == Character.Gender.f3208c) {
            z.P(GameString.b("RECRUITED_COMPANION_TITLE_FEMALE", false), GameString.b("RECRUITED_COMPANION_FEMALE", false));
        } else {
            z.P(GameString.b("RECRUITED_COMPANION_TITLE_MALE", false), GameString.b("RECRUITED_COMPANION_MALE", false));
        }
    }

    public final void b(String str, String str2, String str3) {
        if (j()) {
            f().Q1();
        }
        if (str.equals(this.activeCompanion)) {
            this.activeCompanion = "";
            return;
        }
        Iterator<Follower> it = this.followers.iterator();
        while (it.hasNext()) {
            Follower next = it.next();
            if (next == null || next.spawn_id == null || next.a() == null || next.a().tag == null || next.a().name == null || (next.spawn_id.equals(str) && next.a().tag.equals(str2) && next.a().name.equals(str3))) {
                it.remove();
            }
        }
    }

    public final void c() {
        if (j()) {
            f().destroy = true;
        }
        this.activeCompanion = "";
        for (NPC npc : GameLevelData.o().npcs) {
            if (npc.k0()) {
                npc.destroy = true;
            }
        }
        this.followers.clear();
    }

    public final void d(String str) {
        Iterator<Follower> it = this.followers.iterator();
        while (it.hasNext()) {
            if (it.next().a().tag.equals(str)) {
                it.remove();
            }
        }
    }

    public final void e() {
        Iterator<Follower> it = this.followers.iterator();
        while (it.hasNext()) {
            Follower next = it.next();
            if (next.a().tag.contains("summon")) {
                it.remove();
                NPC npcJ = GameLevel.j(next.a().tag);
                if (npcJ != null) {
                    npcJ.J1();
                }
            }
        }
    }

    public final NPC f() {
        for (int i2 = 0; i2 < this.companions.size(); i2++) {
            if (this.companions.get(i2).spawn_id.equals(this.activeCompanion)) {
                return this.companions.get(i2);
            }
        }
        return null;
    }

    public final String g() {
        return this.activeCompanion;
    }

    public final int h() {
        if (!j()) {
            AStarPathFinder aStarPathFinder = GameLevel.f3309a;
            return GameData.v().player.sheet.u();
        }
        int iU = f().sheet.u();
        AStarPathFinder aStarPathFinder2 = GameLevel.f3309a;
        return Math.max(iU, GameData.v().player.sheet.u());
    }

    public final NPC i() {
        for (Follower follower : this.followers) {
            if (follower.a().tag.contains("summon")) {
                return GameLevel.j(follower.a().tag);
            }
        }
        return null;
    }

    public final boolean j() {
        ArrayList<NPC> arrayList;
        if (this.activeCompanion.equals("") || (arrayList = this.companions) == null || arrayList.size() == 0) {
            return false;
        }
        return !this.activeCompanion.equals("");
    }

    public final Boolean k() {
        return Boolean.valueOf(this.followers.size() > 0);
    }

    public final void l() {
        this.hiddenFollowers = true;
        if (j()) {
            this.hiddenCompanion = this.activeCompanion;
        } else {
            this.hiddenCompanion = "";
        }
        this.activeCompanion = "";
    }

    public final boolean m(String str) {
        Iterator<NPC> it = this.companions.iterator();
        while (it.hasNext()) {
            if (it.next().spawn_id.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final boolean n(int i2) {
        NPC npcF;
        if (!this.activeCompanion.equals("") && (npcF = f()) != null && npcF.q() == i2) {
            AStarPathFinder aStarPathFinder = GameLevel.f3309a;
            int i3 = GameData.v().player.f3307x;
            int i4 = GameData.v().player.f3308y;
            if (Math.abs(i3 - npcF.f3307x) < 18 && Math.abs(i4 - npcF.f3308y) < 18) {
                return true;
            }
        }
        return false;
    }

    public final boolean o() {
        return this.hiddenFollowers;
    }

    public final boolean p(String str, String str2) {
        if (str.equals(this.activeCompanion) || str.equals(this.hiddenCompanion)) {
            return true;
        }
        for (Follower follower : this.followers) {
            if (follower.a().tag != null) {
                String str3 = follower.spawn_id;
                Locale locale = Locale.ENGLISH;
                if (str3.toLowerCase(locale).equals(str.toLowerCase(locale)) && follower.a().tag.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void r() {
        if (j()) {
            NPC npcF = f();
            int i2 = GameData.v().player.sheet.stats.i() / 2;
            if (i2 > npcF.sheet.stats.i()) {
                npcF.O0(i2 - npcF.sheet.stats.i());
            }
        }
    }

    public final void s() {
        if (j()) {
            f().r1(Rules.i(this.activeCompanion).a());
        }
    }

    public final void t() {
        this.hiddenFollowers = false;
        String str = this.hiddenCompanion;
        if (str != null && !str.equals("")) {
            this.activeCompanion = this.hiddenCompanion;
        }
        this.hiddenCompanion = "";
    }
}

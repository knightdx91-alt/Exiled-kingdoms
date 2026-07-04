package net.fdgames.GameLogic;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameLogic.ScriptedAction;
import net.fdgames.Helpers.FDUtils;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class ActionsSet {
    public ArrayList<ScriptedAction> actions = new ArrayList<>();

    public ActionsSet() {
    }

    public final void a() {
        Iterator<ScriptedAction> it = this.actions.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public final String toString() {
        String string = "";
        for (ScriptedAction scriptedAction : this.actions) {
            if (!string.equals("")) {
                string = string.concat(";");
            }
            StringBuilder sbS = a.s(string);
            sbS.append(scriptedAction.toString());
            string = sbS.toString();
        }
        return string;
    }

    public ActionsSet(String str) {
        ScriptedAction.ActionType actionType;
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            ArrayList<ScriptedAction> arrayList = this.actions;
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            Locale locale = Locale.ENGLISH;
            if (a.y(str2, locale, "setvariable")) {
                actionType = ScriptedAction.ActionType.f3140b;
            } else if (a.y(str2, locale, "incvariable")) {
                actionType = ScriptedAction.ActionType.f3142c;
            } else if (a.y(str2, locale, "decvariable")) {
                actionType = ScriptedAction.ActionType.f3144d;
            } else if (a.y(str2, locale, "gainitem")) {
                actionType = ScriptedAction.ActionType.f3146e;
            } else if (a.y(str2, locale, "gainitems")) {
                actionType = ScriptedAction.ActionType.f3152h;
            } else if (a.y(str2, locale, "loseitem")) {
                actionType = ScriptedAction.ActionType.f3148f;
            } else if (a.y(str2, locale, "loseitems")) {
                actionType = ScriptedAction.ActionType.f3150g;
            } else if (a.y(str2, locale, "gaingold")) {
                actionType = ScriptedAction.ActionType.f3160l;
            } else if (a.y(str2, locale, "losegold")) {
                actionType = ScriptedAction.ActionType.f3162m;
            } else if (a.y(str2, locale, "gainxp")) {
                actionType = ScriptedAction.ActionType.f3154i;
            } else if (a.y(str2, locale, "gainhp")) {
                actionType = ScriptedAction.ActionType.f3156j;
            } else if (a.y(str2, locale, "gainrandomhp")) {
                actionType = ScriptedAction.ActionType.D0;
            } else if (a.y(str2, locale, "gainmana")) {
                actionType = ScriptedAction.ActionType.f3158k;
            } else if (a.y(str2, locale, "gainhpfull")) {
                actionType = ScriptedAction.ActionType.f3164n;
            } else if (a.y(str2, locale, "lesserrestoration")) {
                actionType = ScriptedAction.ActionType.f3166o;
            } else if (a.y(str2, locale, "circlerestoration")) {
                actionType = ScriptedAction.ActionType.f3168p;
            } else if (a.y(str2, locale, "losehp")) {
                actionType = ScriptedAction.ActionType.f3170q;
            } else if (a.y(str2, locale, "travel")) {
                actionType = ScriptedAction.ActionType.f3178w;
            } else if (a.y(str2, locale, "npcgoto")) {
                actionType = ScriptedAction.ActionType.f3179x;
            } else if (a.y(str2, locale, "npcdespawn")) {
                actionType = ScriptedAction.ActionType.f3181z;
            } else if (a.y(str2, locale, "npchostile")) {
                actionType = ScriptedAction.ActionType.A;
            } else if (a.y(str2, locale, "npcfollow")) {
                actionType = ScriptedAction.ActionType.B;
            } else if (a.y(str2, locale, "npcwait")) {
                actionType = ScriptedAction.ActionType.D;
            } else if (a.y(str2, locale, "npcdontwait")) {
                actionType = ScriptedAction.ActionType.E;
            } else if (a.y(str2, locale, "npcattack")) {
                actionType = ScriptedAction.ActionType.C;
            } else if (a.y(str2, locale, "npcstopfollowing")) {
                actionType = ScriptedAction.ActionType.F;
            } else if (a.y(str2, locale, "npcmoverandom")) {
                actionType = ScriptedAction.ActionType.G;
            } else if (a.y(str2, locale, "gainshield")) {
                actionType = ScriptedAction.ActionType.N;
            } else if (a.y(str2, locale, "gainmight")) {
                actionType = ScriptedAction.ActionType.O;
            } else if (a.y(str2, locale, "gaininvis")) {
                actionType = ScriptedAction.ActionType.P;
            } else if (a.y(str2, locale, "gainspeed")) {
                actionType = ScriptedAction.ActionType.Q;
            } else if (a.y(str2, locale, "resistfire")) {
                actionType = ScriptedAction.ActionType.T;
            } else if (a.y(str2, locale, "resistcold")) {
                actionType = ScriptedAction.ActionType.U;
            } else if (a.y(str2, locale, "resistdeath")) {
                actionType = ScriptedAction.ActionType.S;
            } else if (a.y(str2, locale, "resistshock")) {
                actionType = ScriptedAction.ActionType.W;
            } else if (a.y(str2, locale, "resistspirit")) {
                actionType = ScriptedAction.ActionType.X;
            } else if (a.y(str2, locale, "resisttoxic")) {
                actionType = ScriptedAction.ActionType.V;
            } else if (a.y(str2, locale, "startconversation")) {
                actionType = ScriptedAction.ActionType.Z;
            } else if (a.y(str2, locale, "startitemconversation")) {
                actionType = ScriptedAction.ActionType.f3139a0;
            } else if (a.y(str2, locale, "disbandparty")) {
                actionType = ScriptedAction.ActionType.I;
            } else if (a.y(str2, locale, "hideparty")) {
                actionType = ScriptedAction.ActionType.J;
            } else if (a.y(str2, locale, "showparty")) {
                actionType = ScriptedAction.ActionType.K;
            } else if (a.y(str2, locale, "loseinventory")) {
                actionType = ScriptedAction.ActionType.L;
            } else if (a.y(str2, locale, "recoverinventory")) {
                actionType = ScriptedAction.ActionType.M;
            } else if (a.y(str2, locale, "sleep")) {
                actionType = ScriptedAction.ActionType.f3141b0;
            } else if (a.y(str2, locale, "savegame")) {
                actionType = ScriptedAction.ActionType.f3143c0;
            } else if (a.y(str2, locale, "particle")) {
                actionType = ScriptedAction.ActionType.f3145d0;
            } else if (a.y(str2, locale, "disband")) {
                actionType = ScriptedAction.ActionType.H;
            } else if (a.y(str2, locale, "disbandall")) {
                actionType = ScriptedAction.ActionType.O0;
            } else if (a.y(str2, locale, "recall")) {
                actionType = ScriptedAction.ActionType.f3147e0;
            } else if (a.y(str2, locale, "teleport")) {
                actionType = ScriptedAction.ActionType.f3149f0;
            } else if (a.y(str2, locale, "npcspawn")) {
                actionType = ScriptedAction.ActionType.f3180y;
            } else if (a.y(str2, locale, "npctrytodespawn")) {
                actionType = ScriptedAction.ActionType.f3153h0;
            } else if (a.y(str2, locale, "openshop")) {
                actionType = ScriptedAction.ActionType.f3155i0;
            } else if (a.y(str2, locale, "gaintrait")) {
                actionType = ScriptedAction.ActionType.f3157j0;
            } else if (a.y(str2, locale, "gainskillpoint")) {
                actionType = ScriptedAction.ActionType.f3159k0;
            } else if (a.y(str2, locale, "detect")) {
                actionType = ScriptedAction.ActionType.f3151g0;
            } else if (a.y(str2, locale, "poisonweapon")) {
                actionType = ScriptedAction.ActionType.Y;
            } else if (a.y(str2, locale, "itemactivate")) {
                actionType = ScriptedAction.ActionType.f3161l0;
            } else if (a.y(str2, locale, "itemdeactivate")) {
                actionType = ScriptedAction.ActionType.f3163m0;
            } else if (a.y(str2, locale, "itemhide")) {
                actionType = ScriptedAction.ActionType.f3165n0;
            } else if (a.y(str2, locale, "itemshow")) {
                actionType = ScriptedAction.ActionType.f3167o0;
            } else if (a.y(str2, locale, "itemblockview")) {
                actionType = ScriptedAction.ActionType.f3169p0;
            } else if (a.y(str2, locale, "itemunblockview")) {
                actionType = ScriptedAction.ActionType.f3171q0;
            } else if (a.y(str2, locale, "itemwalkable")) {
                actionType = ScriptedAction.ActionType.f3173r0;
            } else if (a.y(str2, locale, "itemunwalkable")) {
                actionType = ScriptedAction.ActionType.f3174s0;
            } else if (a.y(str2, locale, "playerrobbed")) {
                actionType = ScriptedAction.ActionType.t0;
            } else if (a.y(str2, locale, "stoprender")) {
                actionType = ScriptedAction.ActionType.u0;
            } else if (a.y(str2, locale, "startrender")) {
                actionType = ScriptedAction.ActionType.v0;
            } else if (a.y(str2, locale, "losehptoxic")) {
                actionType = ScriptedAction.ActionType.s;
            } else if (a.y(str2, locale, "losehpshock")) {
                actionType = ScriptedAction.ActionType.f3172r;
            } else if (a.y(str2, locale, "losehpfire")) {
                actionType = ScriptedAction.ActionType.f3175t;
            } else if (a.y(str2, locale, "losehpcold")) {
                actionType = ScriptedAction.ActionType.f3176u;
            } else if (a.y(str2, locale, "losehpdeath")) {
                actionType = ScriptedAction.ActionType.f3177v;
            } else if (a.y(str2, locale, "openworldcontainer")) {
                actionType = ScriptedAction.ActionType.w0;
            } else if (a.y(str2, locale, "resetplayerskills")) {
                actionType = ScriptedAction.ActionType.x0;
            } else if (a.y(str2, locale, "resetplayertraits")) {
                actionType = ScriptedAction.ActionType.y0;
            } else if (a.y(str2, locale, "resetcompanion")) {
                actionType = ScriptedAction.ActionType.z0;
            } else if (a.y(str2, locale, "trainskill")) {
                actionType = ScriptedAction.ActionType.A0;
            } else if (a.y(str2, locale, "forgetplayeradvancedskills")) {
                actionType = ScriptedAction.ActionType.B0;
            } else if (a.y(str2, locale, "fullrest")) {
                actionType = ScriptedAction.ActionType.C0;
            } else if (a.y(str2, locale, "gaineffect")) {
                actionType = ScriptedAction.ActionType.R;
            } else if (a.y(str2, locale, "summon")) {
                actionType = ScriptedAction.ActionType.E0;
            } else if (a.y(str2, locale, "turnundead")) {
                actionType = ScriptedAction.ActionType.F0;
            } else if (a.y(str2, locale, "destroyshards")) {
                actionType = ScriptedAction.ActionType.G0;
            } else if (a.y(str2, locale, "hurtnpc")) {
                actionType = ScriptedAction.ActionType.H0;
            } else if (a.y(str2, locale, "openvault")) {
                actionType = ScriptedAction.ActionType.I0;
            } else if (a.y(str2, locale, "tolcurse")) {
                actionType = ScriptedAction.ActionType.J0;
            } else if (a.y(str2, locale, "endtolcurse")) {
                actionType = ScriptedAction.ActionType.K0;
            } else if (a.y(str2, locale, "halfrecovery")) {
                actionType = ScriptedAction.ActionType.L0;
            } else if (a.y(str2, locale, "fullrecovery")) {
                actionType = ScriptedAction.ActionType.N0;
            } else if (a.y(str2, locale, "gainbagholding")) {
                actionType = ScriptedAction.ActionType.M0;
            } else if (a.y(str2, locale, "planarbinding")) {
                actionType = ScriptedAction.ActionType.P0;
            } else if (a.y(str2, locale, "endgame")) {
                actionType = ScriptedAction.ActionType.Q0;
            } else if (a.y(str2, locale, "losearenaitems")) {
                actionType = ScriptedAction.ActionType.R0;
            } else if (a.y(str2, locale, "upgradecompanion")) {
                actionType = ScriptedAction.ActionType.S0;
            } else {
                actionType = ScriptedAction.ActionType.f3138a;
            }
            arrayList.add(new ScriptedAction(actionType, str3));
        }
    }
}

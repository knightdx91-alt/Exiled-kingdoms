package net.fdgames.GameLogic;

import a.a;
import android.app.cYK.BQmoQ;
import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.appcompat.view.menu.dC.pheteKhVjcmzvb;
import i.LXkY.rFUF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameLogic.ScriptedAction;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;
import q0.FCji.sPtsahwU;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class ActionsSet {
    public ArrayList<ScriptedAction> actions = new ArrayList<>();

    public ActionsSet() {
    }

    public ActionsSet(String str) {
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            ArrayList<ScriptedAction> arrayList = this.actions;
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            Locale locale = Locale.ENGLISH;
            arrayList.add(new ScriptedAction(a.x(str2, locale, "setvariable") ? ScriptedAction.ActionType.f3356c : a.x(str2, locale, "incvariable") ? ScriptedAction.ActionType.f3358d : a.x(str2, locale, "decvariable") ? ScriptedAction.ActionType.f3360e : a.x(str2, locale, "gainitem") ? ScriptedAction.ActionType.f3361f : a.x(str2, locale, "gainitems") ? ScriptedAction.ActionType.f3367i : a.x(str2, locale, "loseitem") ? ScriptedAction.ActionType.f3363g : a.x(str2, locale, "loseitems") ? ScriptedAction.ActionType.f3365h : a.x(str2, locale, "gaingold") ? ScriptedAction.ActionType.f3375m : a.x(str2, locale, "losegold") ? ScriptedAction.ActionType.f3377n : a.x(str2, locale, "gainxp") ? ScriptedAction.ActionType.f3369j : a.x(str2, locale, "gainhp") ? ScriptedAction.ActionType.f3371k : a.x(str2, locale, "gainrandomhp") ? ScriptedAction.ActionType.E0 : a.x(str2, locale, "gainmana") ? ScriptedAction.ActionType.f3373l : a.x(str2, locale, "gainhpfull") ? ScriptedAction.ActionType.f3379o : a.x(str2, locale, "lesserrestoration") ? ScriptedAction.ActionType.f3381p : a.x(str2, locale, "circlerestoration") ? ScriptedAction.ActionType.f3383q : a.x(str2, locale, "losehp") ? ScriptedAction.ActionType.f3385r : a.x(str2, locale, "travel") ? ScriptedAction.ActionType.f3397x : a.x(str2, locale, "npcgoto") ? ScriptedAction.ActionType.f3399y : a.x(str2, locale, "npcdespawn") ? ScriptedAction.ActionType.A : a.x(str2, locale, "npchostile") ? ScriptedAction.ActionType.B : a.x(str2, locale, "npcfollow") ? ScriptedAction.ActionType.C : a.x(str2, locale, "npcwait") ? ScriptedAction.ActionType.E : a.x(str2, locale, fApIihhYHIP.olAcY) ? ScriptedAction.ActionType.F : a.x(str2, locale, "npcattack") ? ScriptedAction.ActionType.D : a.x(str2, locale, "npcstopfollowing") ? ScriptedAction.ActionType.G : a.x(str2, locale, "npcmoverandom") ? ScriptedAction.ActionType.H : a.x(str2, locale, "gainshield") ? ScriptedAction.ActionType.O : a.x(str2, locale, sPtsahwU.FXhLlQdqqTSB) ? ScriptedAction.ActionType.P : a.x(str2, locale, "gaininvis") ? ScriptedAction.ActionType.Q : a.x(str2, locale, "gainspeed") ? ScriptedAction.ActionType.R : a.x(str2, locale, "resistfire") ? ScriptedAction.ActionType.U : a.x(str2, locale, "resistcold") ? ScriptedAction.ActionType.V : a.x(str2, locale, "resistdeath") ? ScriptedAction.ActionType.T : a.x(str2, locale, "resistshock") ? ScriptedAction.ActionType.X : a.x(str2, locale, "resistspirit") ? ScriptedAction.ActionType.Y : a.x(str2, locale, "resisttoxic") ? ScriptedAction.ActionType.W : a.x(str2, locale, "startconversation") ? ScriptedAction.ActionType.f3353a0 : a.x(str2, locale, "startitemconversation") ? ScriptedAction.ActionType.f3355b0 : a.x(str2, locale, "disbandparty") ? ScriptedAction.ActionType.J : a.x(str2, locale, "hideparty") ? ScriptedAction.ActionType.K : a.x(str2, locale, BQmoQ.bOanf) ? ScriptedAction.ActionType.L : a.x(str2, locale, "loseinventory") ? ScriptedAction.ActionType.M : a.x(str2, locale, "recoverinventory") ? ScriptedAction.ActionType.N : a.x(str2, locale, "sleep") ? ScriptedAction.ActionType.f3357c0 : a.x(str2, locale, pheteKhVjcmzvb.YMtHtyLaE) ? ScriptedAction.ActionType.f3359d0 : a.x(str2, locale, "particle") ? ScriptedAction.ActionType.e0 : a.x(str2, locale, "disband") ? ScriptedAction.ActionType.I : a.x(str2, locale, "disbandall") ? ScriptedAction.ActionType.P0 : a.x(str2, locale, fApIihhYHIP.kXHNsDEOL) ? ScriptedAction.ActionType.f3362f0 : a.x(str2, locale, "teleport") ? ScriptedAction.ActionType.f3364g0 : a.x(str2, locale, "npcspawn") ? ScriptedAction.ActionType.f3401z : a.x(str2, locale, "npctrytodespawn") ? ScriptedAction.ActionType.f3368i0 : a.x(str2, locale, "openshop") ? ScriptedAction.ActionType.f3370j0 : a.x(str2, locale, "gaintrait") ? ScriptedAction.ActionType.f3372k0 : a.x(str2, locale, "gainskillpoint") ? ScriptedAction.ActionType.f3374l0 : a.x(str2, locale, "detect") ? ScriptedAction.ActionType.f3366h0 : a.x(str2, locale, "poisonweapon") ? ScriptedAction.ActionType.Z : a.x(str2, locale, "itemactivate") ? ScriptedAction.ActionType.f3376m0 : a.x(str2, locale, "itemdeactivate") ? ScriptedAction.ActionType.f3378n0 : a.x(str2, locale, "itemhide") ? ScriptedAction.ActionType.f3380o0 : a.x(str2, locale, "itemshow") ? ScriptedAction.ActionType.f3382p0 : a.x(str2, locale, "itemblockview") ? ScriptedAction.ActionType.f3384q0 : a.x(str2, locale, pgtXvpMCFu.STDnLTTfCRmamk) ? ScriptedAction.ActionType.f3386r0 : a.x(str2, locale, "itemwalkable") ? ScriptedAction.ActionType.f3388s0 : a.x(str2, locale, "itemunwalkable") ? ScriptedAction.ActionType.f3390t0 : a.x(str2, locale, "playerrobbed") ? ScriptedAction.ActionType.f3392u0 : a.x(str2, locale, "stoprender") ? ScriptedAction.ActionType.f3394v0 : a.x(str2, locale, "startrender") ? ScriptedAction.ActionType.f3396w0 : a.x(str2, locale, "losehptoxic") ? ScriptedAction.ActionType.f3389t : a.x(str2, locale, "losehpshock") ? ScriptedAction.ActionType.f3387s : a.x(str2, locale, "losehpfire") ? ScriptedAction.ActionType.f3391u : a.x(str2, locale, "losehpcold") ? ScriptedAction.ActionType.f3393v : a.x(str2, locale, "losehpdeath") ? ScriptedAction.ActionType.f3395w : a.x(str2, locale, "openworldcontainer") ? ScriptedAction.ActionType.f3398x0 : a.x(str2, locale, rFUF.BBFxocmuNOK) ? ScriptedAction.ActionType.f3400y0 : a.x(str2, locale, "resetplayertraits") ? ScriptedAction.ActionType.f3402z0 : a.x(str2, locale, "resetcompanion") ? ScriptedAction.ActionType.A0 : a.x(str2, locale, "trainskill") ? ScriptedAction.ActionType.B0 : a.x(str2, locale, "forgetplayeradvancedskills") ? ScriptedAction.ActionType.C0 : a.x(str2, locale, "fullrest") ? ScriptedAction.ActionType.D0 : a.x(str2, locale, "gaineffect") ? ScriptedAction.ActionType.S : a.x(str2, locale, "summon") ? ScriptedAction.ActionType.F0 : a.x(str2, locale, "turnundead") ? ScriptedAction.ActionType.G0 : a.x(str2, locale, "destroyshards") ? ScriptedAction.ActionType.H0 : a.x(str2, locale, "hurtnpc") ? ScriptedAction.ActionType.I0 : a.x(str2, locale, "openvault") ? ScriptedAction.ActionType.J0 : a.x(str2, locale, "tolcurse") ? ScriptedAction.ActionType.K0 : a.x(str2, locale, "endtolcurse") ? ScriptedAction.ActionType.L0 : a.x(str2, locale, "halfrecovery") ? ScriptedAction.ActionType.M0 : a.x(str2, locale, "fullrecovery") ? ScriptedAction.ActionType.O0 : a.x(str2, locale, "gainbagholding") ? ScriptedAction.ActionType.N0 : a.x(str2, locale, "planarbinding") ? ScriptedAction.ActionType.Q0 : a.x(str2, locale, "endgame") ? ScriptedAction.ActionType.R0 : a.x(str2, locale, "losearenaitems") ? ScriptedAction.ActionType.S0 : a.x(str2, locale, "upgradecompanion") ? ScriptedAction.ActionType.T0 : ScriptedAction.ActionType.f3354b, str3));
        }
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
            StringBuilder sbR = a.r(string);
            sbR.append(scriptedAction.toString());
            string = sbR.toString();
        }
        return string;
    }
}

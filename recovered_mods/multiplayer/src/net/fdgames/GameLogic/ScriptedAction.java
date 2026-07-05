package net.fdgames.GameLogic;

import a1.s;
import android.app.cYK.BQmoQ;
import android.app.cYK.lRWXeT;
import android.support.v4.media.session.bbR.CrhMs;
import androidx.coordinatorlayout.widget.OvMp.SkCylVE;
import androidx.drawerlayout.widget.Qm.ReTXwDyZpZSd;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.loader.rr.fIsXh;
import com.badlogic.gdx.scenes.scene2d.utils.WHW.gNaSiQJmMEn;
import com.google.android.datatransport.backend.cct.CSLH.VEZT;
import com.google.android.datatransport.fJ.lxYOBQSyWPCj;
import com.google.android.gms.common.api.Mcc.KOdB;
import com.google.android.gms.common.internal.safeparcel.YT.EGEEZWr;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.internal.p000authapi.KJ.ZeMa;
import com.google.android.gms.tasks.bnw.jUhYGTigo;
import com.pairip.licensecheck.wYh.JDxjJEGD;
import i.LXkY.rFUF;
import java.util.Iterator;
import java.util.Locale;
import l0.KUbz.MbzYTTrg;
import n0.WOA.IUcMoQOkPHcA;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.MapItem;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.StaticNPC;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Party;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Skills;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.ItemConversation;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.GameAssets;
import o.Oeoo.vIBRkbZbNjpf;
import w0.a;
import y0.b;
import y0.d;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class ScriptedAction {
    private String data;
    private ActionType type;

    /* JADX INFO: renamed from: net.fdgames.GameLogic.ScriptedAction$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3352a;

        static {
            int[] iArr = new int[ActionType.values().length];
            f3352a = iArr;
            try {
                ActionType actionType = ActionType.f3354b;
                iArr[9] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f3352a;
                ActionType actionType2 = ActionType.f3354b;
                iArr2[81] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = f3352a;
                ActionType actionType3 = ActionType.f3354b;
                iArr3[10] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = f3352a;
                ActionType actionType4 = ActionType.f3354b;
                iArr4[13] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = f3352a;
                ActionType actionType5 = ActionType.f3354b;
                iArr5[14] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = f3352a;
                ActionType actionType6 = ActionType.f3354b;
                iArr6[15] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = f3352a;
                ActionType actionType7 = ActionType.f3354b;
                iArr7[16] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = f3352a;
                ActionType actionType8 = ActionType.f3354b;
                iArr8[18] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = f3352a;
                ActionType actionType9 = ActionType.f3354b;
                iArr9[17] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = f3352a;
                ActionType actionType10 = ActionType.f3354b;
                iArr10[19] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = f3352a;
                ActionType actionType11 = ActionType.f3354b;
                iArr11[20] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = f3352a;
                ActionType actionType12 = ActionType.f3354b;
                iArr12[21] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr13 = f3352a;
                ActionType actionType13 = ActionType.f3354b;
                iArr13[8] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr14 = f3352a;
                ActionType actionType14 = ActionType.f3354b;
                iArr14[11] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr15 = f3352a;
                ActionType actionType15 = ActionType.f3354b;
                iArr15[12] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr16 = f3352a;
                ActionType actionType16 = ActionType.f3354b;
                iArr16[4] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                int[] iArr17 = f3352a;
                ActionType actionType17 = ActionType.f3354b;
                iArr17[7] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                int[] iArr18 = f3352a;
                ActionType actionType18 = ActionType.f3354b;
                iArr18[5] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                int[] iArr19 = f3352a;
                ActionType actionType19 = ActionType.f3354b;
                iArr19[6] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                int[] iArr20 = f3352a;
                ActionType actionType20 = ActionType.f3354b;
                iArr20[1] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                int[] iArr21 = f3352a;
                ActionType actionType21 = ActionType.f3354b;
                iArr21[3] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                int[] iArr22 = f3352a;
                ActionType actionType22 = ActionType.f3354b;
                iArr22[2] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                int[] iArr23 = f3352a;
                ActionType actionType23 = ActionType.f3354b;
                iArr23[22] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                int[] iArr24 = f3352a;
                ActionType actionType24 = ActionType.f3354b;
                iArr24[0] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                int[] iArr25 = f3352a;
                ActionType actionType25 = ActionType.f3354b;
                iArr25[23] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                int[] iArr26 = f3352a;
                ActionType actionType26 = ActionType.f3354b;
                iArr26[25] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                int[] iArr27 = f3352a;
                ActionType actionType27 = ActionType.f3354b;
                iArr27[26] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                int[] iArr28 = f3352a;
                ActionType actionType28 = ActionType.f3354b;
                iArr28[27] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                int[] iArr29 = f3352a;
                ActionType actionType29 = ActionType.f3354b;
                iArr29[29] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                int[] iArr30 = f3352a;
                ActionType actionType30 = ActionType.f3354b;
                iArr30[30] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                int[] iArr31 = f3352a;
                ActionType actionType31 = ActionType.f3354b;
                iArr31[28] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                int[] iArr32 = f3352a;
                ActionType actionType32 = ActionType.f3354b;
                iArr32[31] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                int[] iArr33 = f3352a;
                ActionType actionType33 = ActionType.f3354b;
                iArr33[41] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                int[] iArr34 = f3352a;
                ActionType actionType34 = ActionType.f3354b;
                iArr34[40] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                int[] iArr35 = f3352a;
                ActionType actionType35 = ActionType.f3354b;
                iArr35[39] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                int[] iArr36 = f3352a;
                ActionType actionType36 = ActionType.f3354b;
                iArr36[42] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                int[] iArr37 = f3352a;
                ActionType actionType37 = ActionType.f3354b;
                iArr37[46] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                int[] iArr38 = f3352a;
                ActionType actionType38 = ActionType.f3354b;
                iArr38[45] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                int[] iArr39 = f3352a;
                ActionType actionType39 = ActionType.f3354b;
                iArr39[44] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                int[] iArr40 = f3352a;
                ActionType actionType40 = ActionType.f3354b;
                iArr40[47] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                int[] iArr41 = f3352a;
                ActionType actionType41 = ActionType.f3354b;
                iArr41[48] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                int[] iArr42 = f3352a;
                ActionType actionType42 = ActionType.f3354b;
                iArr42[49] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                int[] iArr43 = f3352a;
                ActionType actionType43 = ActionType.f3354b;
                iArr43[43] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                int[] iArr44 = f3352a;
                ActionType actionType44 = ActionType.f3354b;
                iArr44[32] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                int[] iArr45 = f3352a;
                ActionType actionType45 = ActionType.f3354b;
                iArr45[51] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                int[] iArr46 = f3352a;
                ActionType actionType46 = ActionType.f3354b;
                iArr46[52] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                int[] iArr47 = f3352a;
                ActionType actionType47 = ActionType.f3354b;
                iArr47[34] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                int[] iArr48 = f3352a;
                ActionType actionType48 = ActionType.f3354b;
                iArr48[35] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                int[] iArr49 = f3352a;
                ActionType actionType49 = ActionType.f3354b;
                iArr49[36] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                int[] iArr50 = f3352a;
                ActionType actionType50 = ActionType.f3354b;
                iArr50[37] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                int[] iArr51 = f3352a;
                ActionType actionType51 = ActionType.f3354b;
                iArr51[38] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                int[] iArr52 = f3352a;
                ActionType actionType52 = ActionType.f3354b;
                iArr52[53] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                int[] iArr53 = f3352a;
                ActionType actionType53 = ActionType.f3354b;
                iArr53[54] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                int[] iArr54 = f3352a;
                ActionType actionType54 = ActionType.f3354b;
                iArr54[55] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                int[] iArr55 = f3352a;
                ActionType actionType55 = ActionType.f3354b;
                iArr55[33] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                int[] iArr56 = f3352a;
                ActionType actionType56 = ActionType.f3354b;
                iArr56[92] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                int[] iArr57 = f3352a;
                ActionType actionType57 = ActionType.f3354b;
                iArr57[56] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                int[] iArr58 = f3352a;
                ActionType actionType58 = ActionType.f3354b;
                iArr58[57] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                int[] iArr59 = f3352a;
                ActionType actionType59 = ActionType.f3354b;
                iArr59[58] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                int[] iArr60 = f3352a;
                ActionType actionType60 = ActionType.f3354b;
                iArr60[24] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                int[] iArr61 = f3352a;
                ActionType actionType61 = ActionType.f3354b;
                iArr61[60] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                int[] iArr62 = f3352a;
                ActionType actionType62 = ActionType.f3354b;
                iArr62[61] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                int[] iArr63 = f3352a;
                ActionType actionType63 = ActionType.f3354b;
                iArr63[62] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                int[] iArr64 = f3352a;
                ActionType actionType64 = ActionType.f3354b;
                iArr64[63] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                int[] iArr65 = f3352a;
                ActionType actionType65 = ActionType.f3354b;
                iArr65[67] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                int[] iArr66 = f3352a;
                ActionType actionType66 = ActionType.f3354b;
                iArr66[64] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                int[] iArr67 = f3352a;
                ActionType actionType67 = ActionType.f3354b;
                iArr67[65] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                int[] iArr68 = f3352a;
                ActionType actionType68 = ActionType.f3354b;
                iArr68[66] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                int[] iArr69 = f3352a;
                ActionType actionType69 = ActionType.f3354b;
                iArr69[68] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                int[] iArr70 = f3352a;
                ActionType actionType70 = ActionType.f3354b;
                iArr70[70] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                int[] iArr71 = f3352a;
                ActionType actionType71 = ActionType.f3354b;
                iArr71[69] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                int[] iArr72 = f3352a;
                ActionType actionType72 = ActionType.f3354b;
                iArr72[59] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                int[] iArr73 = f3352a;
                ActionType actionType73 = ActionType.f3354b;
                iArr73[50] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                int[] iArr74 = f3352a;
                ActionType actionType74 = ActionType.f3354b;
                iArr74[71] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                int[] iArr75 = f3352a;
                ActionType actionType75 = ActionType.f3354b;
                iArr75[73] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                int[] iArr76 = f3352a;
                ActionType actionType76 = ActionType.f3354b;
                iArr76[72] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                int[] iArr77 = f3352a;
                ActionType actionType77 = ActionType.f3354b;
                iArr77[74] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                int[] iArr78 = f3352a;
                ActionType actionType78 = ActionType.f3354b;
                iArr78[77] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                int[] iArr79 = f3352a;
                ActionType actionType79 = ActionType.f3354b;
                iArr79[75] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                int[] iArr80 = f3352a;
                ActionType actionType80 = ActionType.f3354b;
                iArr80[76] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                int[] iArr81 = f3352a;
                ActionType actionType81 = ActionType.f3354b;
                iArr81[78] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                int[] iArr82 = f3352a;
                ActionType actionType82 = ActionType.f3354b;
                iArr82[79] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                int[] iArr83 = f3352a;
                ActionType actionType83 = ActionType.f3354b;
                iArr83[80] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                int[] iArr84 = f3352a;
                ActionType actionType84 = ActionType.f3354b;
                iArr84[82] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                int[] iArr85 = f3352a;
                ActionType actionType85 = ActionType.f3354b;
                iArr85[83] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                int[] iArr86 = f3352a;
                ActionType actionType86 = ActionType.f3354b;
                iArr86[84] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                int[] iArr87 = f3352a;
                ActionType actionType87 = ActionType.f3354b;
                iArr87[85] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                int[] iArr88 = f3352a;
                ActionType actionType88 = ActionType.f3354b;
                iArr88[86] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                int[] iArr89 = f3352a;
                ActionType actionType89 = ActionType.f3354b;
                iArr89[87] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                int[] iArr90 = f3352a;
                ActionType actionType90 = ActionType.f3354b;
                iArr90[88] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                int[] iArr91 = f3352a;
                ActionType actionType91 = ActionType.f3354b;
                iArr91[89] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                int[] iArr92 = f3352a;
                ActionType actionType92 = ActionType.f3354b;
                iArr92[91] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                int[] iArr93 = f3352a;
                ActionType actionType93 = ActionType.f3354b;
                iArr93[90] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                int[] iArr94 = f3352a;
                ActionType actionType94 = ActionType.f3354b;
                iArr94[93] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                int[] iArr95 = f3352a;
                ActionType actionType95 = ActionType.f3354b;
                iArr95[94] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                int[] iArr96 = f3352a;
                ActionType actionType96 = ActionType.f3354b;
                iArr96[95] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                int[] iArr97 = f3352a;
                ActionType actionType97 = ActionType.f3354b;
                iArr97[96] = 97;
            } catch (NoSuchFieldError unused97) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ActionType {
        public static final ActionType S0;
        public static final ActionType T0;
        private static final /* synthetic */ ActionType[] U0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ActionType f3354b = new ActionType("None", 0);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ActionType f3356c = new ActionType("SetVariable", 1);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ActionType f3358d = new ActionType("IncVariable", 2);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ActionType f3360e = new ActionType("DecVariable", 3);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ActionType f3361f = new ActionType("GainItem", 4);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final ActionType f3363g = new ActionType("LoseItem", 5);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final ActionType f3365h = new ActionType("LoseItems", 6);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final ActionType f3367i = new ActionType("GainItems", 7);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final ActionType f3369j = new ActionType("GainXP", 8);

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final ActionType f3371k = new ActionType(SkCylVE.sKTViLUSWrHRZo, 9);

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final ActionType f3373l = new ActionType("GainMana", 10);

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final ActionType f3375m = new ActionType("GainGold", 11);

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final ActionType f3377n = new ActionType(IUcMoQOkPHcA.qOgiFjIruH, 12);

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final ActionType f3379o = new ActionType(fIsXh.SwjsLxmsZtzGS, 13);

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final ActionType f3381p = new ActionType("LesserRestoration", 14);

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final ActionType f3383q = new ActionType("CircleRestoration", 15);

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final ActionType f3385r = new ActionType("LoseHP", 16);

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static final ActionType f3387s = new ActionType("LoseHPShock", 17);

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final ActionType f3389t = new ActionType("LoseHPToxic", 18);

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final ActionType f3391u = new ActionType("LoseHPFire", 19);

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final ActionType f3393v = new ActionType("LoseHPCold", 20);

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final ActionType f3395w = new ActionType("LoseHPDeath", 21);

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final ActionType f3397x = new ActionType("Travel", 22);

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final ActionType f3399y = new ActionType("NPCGoTo", 23);

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final ActionType f3401z = new ActionType("NPCSpawn", 24);
        public static final ActionType A = new ActionType("NPCDespawn", 25);
        public static final ActionType B = new ActionType("NPCHostile", 26);
        public static final ActionType C = new ActionType("NPCFollow", 27);
        public static final ActionType D = new ActionType("NPCAttack", 28);
        public static final ActionType E = new ActionType("NPCWait", 29);
        public static final ActionType F = new ActionType("NPCDontWait", 30);
        public static final ActionType G = new ActionType("NPCStopFollowing", 31);
        public static final ActionType H = new ActionType("NPCMoveRandom", 32);
        public static final ActionType I = new ActionType("Disband", 33);
        public static final ActionType J = new ActionType("DisbandParty", 34);
        public static final ActionType K = new ActionType("HideParty", 35);
        public static final ActionType L = new ActionType("ShowParty", 36);
        public static final ActionType M = new ActionType("LoseInventory", 37);
        public static final ActionType N = new ActionType(ReTXwDyZpZSd.EWrrESihvidR, 38);
        public static final ActionType O = new ActionType("GainShield", 39);
        public static final ActionType P = new ActionType("GainMight", 40);
        public static final ActionType Q = new ActionType(VEZT.IcbXj, 41);
        public static final ActionType R = new ActionType(KOdB.wTdxYlYQs, 42);
        public static final ActionType S = new ActionType("GainEffect", 43);
        public static final ActionType T = new ActionType("GainResistDeath", 44);
        public static final ActionType U = new ActionType("GainResistFire", 45);
        public static final ActionType V = new ActionType("GainResistCold", 46);
        public static final ActionType W = new ActionType("GainResistToxic", 47);
        public static final ActionType X = new ActionType("GainResistShock", 48);
        public static final ActionType Y = new ActionType(pgtXvpMCFu.sFSzqGbGYOARBtO, 49);
        public static final ActionType Z = new ActionType("PoisonWeapon", 50);

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public static final ActionType f3353a0 = new ActionType(BQmoQ.DjyeUxbe, 51);

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public static final ActionType f3355b0 = new ActionType("StartItemConversation", 52);

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public static final ActionType f3357c0 = new ActionType("Sleep", 53);

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public static final ActionType f3359d0 = new ActionType("SaveGame", 54);
        public static final ActionType e0 = new ActionType("Particle", 55);

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public static final ActionType f3362f0 = new ActionType("Recall", 56);

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public static final ActionType f3364g0 = new ActionType("Teleport", 57);

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public static final ActionType f3366h0 = new ActionType("Detect", 58);

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public static final ActionType f3368i0 = new ActionType("NPCTryToDespawn", 59);

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public static final ActionType f3370j0 = new ActionType("OpenShop", 60);

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public static final ActionType f3372k0 = new ActionType("GainTrait", 61);

        /* JADX INFO: renamed from: l0, reason: collision with root package name */
        public static final ActionType f3374l0 = new ActionType("GainSkillPoint", 62);

        /* JADX INFO: renamed from: m0, reason: collision with root package name */
        public static final ActionType f3376m0 = new ActionType(MbzYTTrg.ZxLrVh, 63);

        /* JADX INFO: renamed from: n0, reason: collision with root package name */
        public static final ActionType f3378n0 = new ActionType("ItemDeactivate", 64);

        /* JADX INFO: renamed from: o0, reason: collision with root package name */
        public static final ActionType f3380o0 = new ActionType("ItemHide", 65);

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        public static final ActionType f3382p0 = new ActionType(ZeMa.bzy, 66);

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        public static final ActionType f3384q0 = new ActionType("ItemBlockView", 67);

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        public static final ActionType f3386r0 = new ActionType("ItemUnblockView", 68);

        /* JADX INFO: renamed from: s0, reason: collision with root package name */
        public static final ActionType f3388s0 = new ActionType("ItemWalkable", 69);

        /* JADX INFO: renamed from: t0, reason: collision with root package name */
        public static final ActionType f3390t0 = new ActionType("ItemUnwalkable", 70);

        /* JADX INFO: renamed from: u0, reason: collision with root package name */
        public static final ActionType f3392u0 = new ActionType("PlayerRobbed", 71);

        /* JADX INFO: renamed from: v0, reason: collision with root package name */
        public static final ActionType f3394v0 = new ActionType("StopRender", 72);

        /* JADX INFO: renamed from: w0, reason: collision with root package name */
        public static final ActionType f3396w0 = new ActionType("StartRender", 73);

        /* JADX INFO: renamed from: x0, reason: collision with root package name */
        public static final ActionType f3398x0 = new ActionType("OpenWorldContainer", 74);

        /* JADX INFO: renamed from: y0, reason: collision with root package name */
        public static final ActionType f3400y0 = new ActionType("ResetPlayerSkills", 75);

        /* JADX INFO: renamed from: z0, reason: collision with root package name */
        public static final ActionType f3402z0 = new ActionType("ResetPlayerTraits", 76);
        public static final ActionType A0 = new ActionType("ResetCompanion", 77);
        public static final ActionType B0 = new ActionType("TrainSkill", 78);
        public static final ActionType C0 = new ActionType("ForgetPlayerAdvancedSkills", 79);
        public static final ActionType D0 = new ActionType(SkCylVE.eYiGgCIx, 80);
        public static final ActionType E0 = new ActionType("GainRandomHP", 81);
        public static final ActionType F0 = new ActionType("Summon", 82);
        public static final ActionType G0 = new ActionType("TurnUndead", 83);
        public static final ActionType H0 = new ActionType("DestroyShards", 84);
        public static final ActionType I0 = new ActionType("HurtNPC", 85);
        public static final ActionType J0 = new ActionType("OpenVault", 86);
        public static final ActionType K0 = new ActionType(SkCylVE.QhhuCuBZmLrpUZC, 87);
        public static final ActionType L0 = new ActionType("EndTolCurse", 88);
        public static final ActionType M0 = new ActionType("HalfRecovery", 89);
        public static final ActionType N0 = new ActionType("GainBagHolding", 90);
        public static final ActionType O0 = new ActionType("FullRecovery", 91);
        public static final ActionType P0 = new ActionType("DisbandAll", 92);
        public static final ActionType Q0 = new ActionType("PlanarBinding", 93);
        public static final ActionType R0 = new ActionType("EndGame", 94);

        static {
            ActionType actionType = new ActionType("LoseArenaItems", 95);
            S0 = actionType;
            ActionType actionType2 = new ActionType("UpgradeCompanion", 96);
            T0 = actionType2;
            U0 = new ActionType[]{f3354b, f3356c, f3358d, f3360e, f3361f, f3363g, f3365h, f3367i, f3369j, f3371k, f3373l, f3375m, f3377n, f3379o, f3381p, f3383q, f3385r, f3387s, f3389t, f3391u, f3393v, f3395w, f3397x, f3399y, f3401z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, f3353a0, f3355b0, f3357c0, f3359d0, e0, f3362f0, f3364g0, f3366h0, f3368i0, f3370j0, f3372k0, f3374l0, f3376m0, f3378n0, f3380o0, f3382p0, f3384q0, f3386r0, f3388s0, f3390t0, f3392u0, f3394v0, f3396w0, f3398x0, f3400y0, f3402z0, A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, actionType, actionType2};
        }

        private ActionType() {
            throw null;
        }

        public static ActionType valueOf(String str) {
            return (ActionType) Enum.valueOf(ActionType.class, str);
        }

        public static ActionType[] values() {
            return (ActionType[]) U0.clone();
        }
    }

    public ScriptedAction() {
    }

    public ScriptedAction(ActionType actionType, String str) {
        this.type = actionType;
        this.data = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void a() {
        Shop shopW;
        NPC npcF;
        String strR = FDUtils.r(this.data);
        int iOrdinal = this.type.ordinal();
        Damage.DamageType damageType = Damage.DamageType.f3261b;
        a.EnumC0056a enumC0056a = a.EnumC0056a.f3897p;
        String str = CrhMs.TpGpsLX;
        int i2 = 0;
        switch (iOrdinal) {
            case 0:
                d1.a.a("ERROR 4.2 ;" + GameData.v().CurrentLevel + " - " + this.data);
                break;
            case 1:
                String[] strArrSplit = strR.split(str);
                GameData.v().gameVariables.e(Integer.parseInt(strArrSplit[1]), strArrSplit[0]);
                break;
            case 2:
                String[] strArrSplit2 = strR.split(str);
                if (strArrSplit2[0].contains("REP_")) {
                    GameWorld.f3410c.a(Integer.parseInt(strArrSplit2[1]), 255, strArrSplit2[0].substring(4));
                } else {
                    GameData.v().gameVariables.e(Integer.parseInt(strArrSplit2[1]) + GameData.v().gameVariables.b(strArrSplit2[0]), strArrSplit2[0]);
                }
                break;
            case 3:
                String[] strArrSplit3 = strR.split(str);
                if (strArrSplit3[0].contains("REP_")) {
                    GameWorld.f3410c.a(-Integer.parseInt(strArrSplit3[1]), 255, strArrSplit3[0].substring(4));
                } else {
                    GameData.v().gameVariables.e(GameData.v().gameVariables.b(strArrSplit3[0]) - Integer.parseInt(strArrSplit3[1]), strArrSplit3[0]);
                }
                break;
            case 4:
                GameData.v().log.d(Integer.parseInt(strR));
                CharacterSheet characterSheet = GameData.v().player.sheet;
                int i3 = Integer.parseInt(strR);
                characterSheet.getClass();
                CharacterSheet.b(i3);
                break;
            case 5:
                GameData.v().player.sheet.b0(Integer.parseInt(strR));
                GameData.v().log.i(Integer.parseInt(strR));
                break;
            case 6:
                String[] strArrSplit4 = strR.split(str);
                for (int i4 = 1; i4 <= Integer.parseInt(strArrSplit4[1]); i4++) {
                    GameData.v().player.sheet.b0(Integer.parseInt(strArrSplit4[0]));
                }
                GameData.v().log.j(Integer.parseInt(strArrSplit4[0]), Integer.parseInt(strArrSplit4[1]));
                break;
            case 7:
                String[] strArrSplit5 = strR.split(str);
                for (int i5 = 1; i5 <= Integer.parseInt(strArrSplit5[1]); i5++) {
                    CharacterSheet characterSheet2 = GameData.v().player.sheet;
                    int i6 = Integer.parseInt(strArrSplit5[0]);
                    characterSheet2.getClass();
                    CharacterSheet.b(i6);
                }
                GameData.v().log.e(Integer.parseInt(strArrSplit5[0]), Integer.parseInt(strArrSplit5[1]));
                break;
            case 8:
                GameData.v().player.O0(Integer.parseInt(strR));
                break;
            case 9:
                z.w().t().V0(Integer.parseInt(strR));
                break;
            case 10:
                z.w().t().o1(Integer.parseInt(strR));
                break;
            case 11:
                GameData.v().player.B1(Integer.parseInt(strR));
                GameData.v().log.c(Integer.parseInt(strR));
                break;
            case 12:
                GameData.v().player.S1(Integer.parseInt(strR));
                GameData.v().log.h(Integer.parseInt(strR));
                break;
            case 13:
                GameData.v().player.V0(1000);
                break;
            case 14:
                CharacterSheet characterSheet3 = z.w().t().sheet;
                int i7 = characterSheet3.stats.missingHP / 2;
                int iC = characterSheet3.C() / 2;
                z.w().t().V0(i7);
                z.w().t().o1(iC);
                break;
            case 15:
                Player playerK = GameLevel.k();
                playerK.V0(playerK.sheet.stats.missingHP / 2);
                for (NPC npc : GameLevelData.t()) {
                    if (npc.k0()) {
                        npc.V0(npc.sheet.stats.missingHP / 2);
                    }
                }
                if (GameData.v().party.j()) {
                    NPC npcF2 = GameData.v().party.f();
                    if (npcF2.i0()) {
                        npcF2.q0(MapActor.ActorState.f3286b);
                        npcF2.Q1();
                        npcF2.t0();
                        SkillActions.d(npcF2);
                    }
                }
                break;
            case 16:
                z.w().t().R0(new Damage(damageType, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 17:
                z.w().t().R0(new Damage(Damage.DamageType.f3264e, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 18:
                z.w().t().R0(new Damage(Damage.DamageType.f3266g, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 19:
                z.w().t().R0(new Damage(Damage.DamageType.f3262c, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 20:
                z.w().t().R0(new Damage(Damage.DamageType.f3263d, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 21:
                z.w().t().R0(new Damage(Damage.DamageType.f3265f, Integer.parseInt(strR), false), z.w().t().q(), false, 0);
                break;
            case 22:
                String[] strArrSplit6 = strR.split(str);
                if (strArrSplit6[0].equals(GameData.v().CurrentLevel)) {
                    new Coords();
                    Coords coordsM = b.P().M(Integer.parseInt(strArrSplit6[1]));
                    a.l().i();
                    GameData.v().player.f3307x = coordsM.f3508x;
                    GameData.v().player.f3308y = coordsM.f3509y;
                    GameData.v().player.C0();
                } else {
                    GameLevel.k().Z1(new Transition(strArrSplit6[0], Integer.parseInt(strArrSplit6[1])));
                }
                if (strArrSplit6.length == 3) {
                    GameData.v().M(Integer.parseInt(strArrSplit6[2]));
                }
                GameData.v().party.getClass();
                Party.q();
                break;
            case 23:
                String[] strArrSplit7 = strR.split(str);
                for (MapActor mapActor : GameLevel.e()) {
                    if (mapActor.tag != null && strArrSplit7[0].trim().equals(mapActor.tag.trim())) {
                        mapActor.r0(b.P().W(strArrSplit7[1].trim()));
                        mapActor.o0(b.P().W(strArrSplit7[1].trim()));
                    }
                }
                break;
            case 24:
                for (MonsterSpawn monsterSpawn : GameLevelData.o().spawns) {
                    if (monsterSpawn.tag != null && strR.trim().equals(monsterSpawn.tag.trim())) {
                        monsterSpawn.Q();
                    }
                }
                break;
            case 25:
                for (MapActor mapActor2 : GameLevel.e()) {
                    if (mapActor2.tag != null && strR.trim().equals(mapActor2.tag.trim())) {
                        mapActor2.destroy = true;
                    }
                }
                for (StaticNPC staticNPC : GameLevelData.y()) {
                    if (staticNPC.tag != null && strR.trim().equals(staticNPC.tag.trim())) {
                        staticNPC.destroy = true;
                    }
                }
                for (StaticNPC staticNPC2 : GameLevelData.y()) {
                    if (staticNPC2.tag != null && strR.trim().equals(staticNPC2.tag.trim())) {
                        staticNPC2.destroy = true;
                    }
                }
                GameData.v().player.numActivables = 0;
                break;
            case 26:
                for (MapActor mapActor3 : GameLevel.e()) {
                    if (mapActor3.tag != null && strR.trim().equals(mapActor3.tag.trim())) {
                        ((NPC) mapActor3).G1();
                    }
                }
                break;
            case 27:
                if (strR.equals("")) {
                    z.w().getClass();
                    if (z.n() > 0) {
                        z.w().getClass();
                        ((NPC) GameLevel.g(z.n())).C1();
                    }
                    z.w().getClass();
                    ((NPC) GameLevel.g(z.n())).f3307x = GameLevel.k().f3307x;
                    z.w().getClass();
                    ((NPC) GameLevel.g(z.n())).f3308y = GameLevel.k().f3308y;
                } else {
                    for (MapActor mapActor4 : GameLevel.e()) {
                        if (mapActor4.tag != null && strR.trim().equals(mapActor4.tag.trim())) {
                            NPC npc2 = (NPC) mapActor4;
                            npc2.C1();
                            npc2.f3307x = GameLevel.k().f3307x;
                            npc2.f3308y = GameLevel.k().f3308y;
                        }
                    }
                }
                break;
            case 28:
                if (GameData.v().party.j()) {
                    String strTrim = strR.trim();
                    Locale locale = Locale.ENGLISH;
                    if (strTrim.toLowerCase(locale).equals("never")) {
                        NPC npcF3 = GameData.v().party.f();
                        int i8 = NPC.f3238c;
                        npcF3.attackStrategy = 3;
                    }
                    if (strR.trim().toLowerCase(locale).equals("defend")) {
                        NPC npcF4 = GameData.v().party.f();
                        int i9 = NPC.f3238c;
                        npcF4.attackStrategy = 2;
                    }
                    if (strR.trim().toLowerCase(locale).equals("close")) {
                        NPC npcF5 = GameData.v().party.f();
                        int i10 = NPC.f3238c;
                        npcF5.attackStrategy = 1;
                    }
                    if (strR.trim().toLowerCase(locale).equals("all")) {
                        NPC npcF6 = GameData.v().party.f();
                        int i11 = NPC.f3238c;
                        npcF6.attackStrategy = 0;
                    }
                }
                break;
            case 29:
                if (GameData.v().party.j()) {
                    GameData.v().party.f().wait = true;
                }
                break;
            case 30:
                if (GameData.v().party.j()) {
                    GameData.v().party.f().wait = false;
                }
                break;
            case 31:
                z.w().getClass();
                if (z.n() > 0) {
                    z.w().getClass();
                    ((NPC) GameLevel.g(z.n())).V1();
                }
                break;
            case 32:
                z.w().getClass();
                if (z.n() > 0) {
                    z.w().getClass();
                    ((NPC) GameLevel.g(z.n())).O1();
                }
                break;
            case 33:
                if (GameLevelData.o().s(strR) > 0) {
                    ((NPC) GameLevel.g(GameLevelData.o().s(strR))).V1();
                }
                break;
            case 35:
                GameData.v().party.l();
                break;
            case 36:
                if (GameData.v().party.o()) {
                    GameData.v().party.t();
                    GameLevelData.o().getClass();
                    GameLevelData.I();
                }
                break;
            case 37:
                GameData.v().player.N1();
                break;
            case 38:
                GameData.v().player.R1();
                break;
            case 39:
                z.w().t().t1(Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 40:
                z.w().t().j1(Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 41:
                Iterator<NPC> it = GameLevelData.o().npcs.iterator();
                while (it.hasNext()) {
                    it.next().P1();
                }
                GameData.v().player.f1(Integer.parseInt(strR));
                break;
            case 42:
                z.w().t().v1(Integer.parseInt(strR));
                break;
            case 43:
                String[] strArrSplit8 = strR.split(str);
                z.w().t().sheet.c(Integer.parseInt(strArrSplit8[1]), Integer.parseInt(strArrSplit8[2]), strArrSplit8[0]);
                break;
            case 44:
                z.w().t().q1(CharacterResistances.ResistanceType.f3213e, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 45:
                z.w().t().q1(CharacterResistances.ResistanceType.f3210b, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 46:
                z.w().t().q1(CharacterResistances.ResistanceType.f3211c, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 47:
                z.w().t().q1(CharacterResistances.ResistanceType.f3214f, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 48:
                z.w().t().q1(CharacterResistances.ResistanceType.f3212d, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 49:
                z.w().t().q1(CharacterResistances.ResistanceType.f3215g, Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 50:
                z.w().t().n1(Integer.parseInt(strR.split(str, -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 51:
                String[] strArrSplit9 = strR.split(str, -1);
                String str2 = strArrSplit9[0];
                String str3 = strArrSplit9[1];
                int iS = GameLevelData.o().s(str2);
                if (iS == 0) {
                    iS = GameLevelData.o().s(str2);
                }
                if (iS > 0 && !str3.equals("")) {
                    if (z.A().booleanValue()) {
                        z.w().getClass();
                        z.v().setVisible(false);
                        GameLevel.n(false);
                    }
                    d dVar = new d(iS, str3);
                    dVar.e();
                    z.w().getClass();
                    z.v().t(dVar);
                    break;
                }
                break;
            case 52:
                ItemConversation itemConversationQ = b.P().Q(strR);
                if (itemConversationQ != null) {
                    z.w().getClass();
                    z.v().t(itemConversationQ);
                }
                break;
            case 53:
                GameLevel.k().q0(MapActor.ActorState.f3294j);
                break;
            case 54:
                GameLevel.k().V0(1000);
                GameLevel.k().sheet.stats.missingMana = 0;
                if (GameData.v().party != null && GameData.v().party.j()) {
                    GameData.v().party.f().V0(1000);
                }
                GameLevel.k().T1();
                if (GameData.v().CurrentLevel.toLowerCase(Locale.ENGLISH).equals("i3_ark2")) {
                    NPC npcJ = GameLevel.j("I3_amarisa");
                    if (npcJ != null) {
                        npcJ.V0(npcJ.sheet.stats.missingHP);
                    }
                } else {
                    Serializer.z(GameData.v().slot, 1);
                    GameConsole.a(GameString.a("GAME_SAVED"));
                }
                break;
            case 55:
                boolean zEquals = this.data.trim().equals("");
                a.EnumC0056a enumC0056a2 = a.EnumC0056a.f3885d;
                if (zEquals) {
                    a.l().b(GameLevel.k().B(), enumC0056a2, 0.0f);
                } else {
                    for (MapActor mapActor5 : GameLevel.e()) {
                        if (mapActor5.tag != null && strR.trim().equals(mapActor5.tag.trim())) {
                            a.l().b(mapActor5.B(), enumC0056a2, 0.0f);
                        }
                    }
                }
                break;
            case 56:
                if (GameData.v().player.G0(3, GameString.a("RECALL"))) {
                    GameData.v().player.m(3.0f, 1, "RECALL");
                }
                break;
            case 57:
                if (GameData.v().player.G0(3, GameString.a("TELEPORT"))) {
                    GameData.v().player.m(3.0f, 1, "TELEPORT");
                }
                break;
            case 58:
                if (GameData.v().player.G0(1, GameString.a("DETECTION"))) {
                    GameData.v().player.spell_id = "detect";
                    GameData.v().player.m(1.0f, 1, "CAST");
                }
                break;
            case 59:
                for (MapActor mapActor6 : GameLevel.e()) {
                    if (mapActor6.tag != null && strR.trim().equals(mapActor6.tag.trim())) {
                        mapActor6.L();
                    }
                }
                for (StaticNPC staticNPC3 : GameLevelData.y()) {
                    if (staticNPC3.tag != null) {
                        strR.trim().equals(staticNPC3.tag.trim());
                    }
                }
                for (StaticNPC staticNPC4 : GameLevelData.y()) {
                    if (staticNPC4.tag != null && strR.trim().equals(staticNPC4.tag.trim())) {
                        staticNPC4.destroy = true;
                    }
                }
                GameData.v().player.numActivables = 0;
                break;
            case 60:
                if (z.A().booleanValue()) {
                    z.w().getClass();
                    int iN = z.n();
                    z.w().getClass();
                    z.v().setVisible(false);
                    GameLevel.n(false);
                    i2 = iN;
                }
                if (i2 > 0 && (shopW = GameLevelData.o().w(i2)) != null) {
                    z.w().H(shopW);
                    break;
                }
                break;
            case 61:
                z.w().t().N0();
                break;
            case 62:
                z.w().t().M0();
                break;
            case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                for (int i12 = 0; i12 < GameLevelData.r().size(); i12++) {
                    if (GameLevelData.r().get(i12) != null && GameLevelData.r().get(i12).tag != null && GameLevelData.r().get(i12).tag.equals(strR)) {
                        GameLevelData.r().get(i12).N(MapItem.MapItemAction.f3222c);
                    }
                }
                while (i2 < GameLevelData.q().size()) {
                    if (GameLevelData.q().get(i2) != null && GameLevelData.q().get(i2).tag != null && GameLevelData.q().get(i2).tag.equals(strR)) {
                        GameLevelData.q().get(i2).N();
                    }
                    i2++;
                }
                break;
            case VertexAttributes.Usage.BoneWeight /* 64 */:
                for (int i13 = 0; i13 < GameLevelData.r().size(); i13++) {
                    if (GameLevelData.r().get(i13).tag != null && GameLevelData.r().get(i13).tag.equals(strR)) {
                        GameLevelData.r().get(i13).N(MapItem.MapItemAction.f3223d);
                    }
                }
                while (i2 < GameLevelData.q().size()) {
                    if (GameLevelData.q().get(i2).tag != null && GameLevelData.q().get(i2).tag.equals(strR)) {
                        GameLevelData.q().get(i2).O();
                    }
                    i2++;
                }
                break;
            case 65:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3221b);
                    }
                    i2++;
                }
                break;
            case 66:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3228i);
                    }
                    i2++;
                }
                break;
            case 67:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3226g);
                    }
                    i2++;
                }
                break;
            case 68:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3227h);
                    }
                    i2++;
                }
                break;
            case 69:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3224e);
                    }
                    i2++;
                }
                break;
            case 70:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3225f);
                    }
                    i2++;
                }
                break;
            case 71:
                GameLevel.k().C1();
                GameData.v().introMessageShown = false;
                GameLevel.k().b2();
                break;
            case 72:
                a.l().f3882o = true;
                break;
            case 73:
                a.l().f3882o = false;
                break;
            case 74:
                z.w().V.T(1, GameData.v().z(strR));
                GameAssets.o("item");
                GameLevel.n(true);
                break;
            case 75:
                GameData.v().Q();
                break;
            case 76:
                GameData.v().R();
                break;
            case 77:
                GameData.v().P();
                break;
            case 78:
                Skill skillC = Skills.c(strR);
                if (strR == "beast_master") {
                    GameLog gameLog = GameData.v().log;
                    String str4 = rFUF.IRS;
                    gameLog.a(GameString.a(str4));
                    GameConsole.a(GameString.a(str4));
                } else if (skillC != null) {
                    if (z.A().booleanValue()) {
                        z.w().getClass();
                        z.v().setVisible(false);
                    }
                    s sVar = z.w().f4483b0;
                    CharacterSheet characterSheet4 = GameLevel.k().sheet;
                    z.w().getClass();
                    sVar.d(characterSheet4, skillC);
                }
                break;
            case 79:
                GameData.v().a();
                break;
            case 80:
                AStarPathFinder aStarPathFinder = GameLevel.f3309a;
                GameData.v().V(true);
                System.gc();
                a.l().i();
                x0.b.n();
                GameData.v().player.areasVisited.b();
                x0.b.h();
                GameLevel.k().V1(true);
                break;
            case 81:
                z.w().t().V0(FDUtils.b(1, Integer.parseInt(strR)));
                if (FDUtils.b(1, 100) < 40) {
                    z.w().t().z1(3.0f);
                }
                break;
            case 82:
                String[] strArrSplit10 = strR.split(str, -1);
                SkillActions.l(z.w().t(), strArrSplit10[0], Integer.parseInt(strArrSplit10[1]), Integer.parseInt(strArrSplit10[2]));
                break;
            case 83:
                z.w().t().G0(1, "");
                SkillActions.m(z.w().t());
                break;
            case 84:
                GameData.v().N();
                break;
            case 85:
                String[] strArrSplit11 = strR.split(str);
                for (NPC npc3 : GameLevelData.o().npcs) {
                    if (npc3.tag != null && strArrSplit11[0].trim().equals(npc3.tag.trim())) {
                        npc3.R0(new Damage(damageType, Integer.parseInt(strArrSplit11[1].trim()), false), 1, false, 0);
                    }
                }
                break;
            case 86:
                if (z.A().booleanValue()) {
                    z.w().getClass();
                    z.v().setVisible(false);
                    GameLevel.n(false);
                }
                z.w().f4481a0.f();
                break;
            case 87:
                GameData.v().tolCurse = true;
                break;
            case 88:
                GameData.v().tolCurse = false;
                break;
            case 89:
                Player playerK2 = GameLevel.k();
                a aVarL = a.l();
                aVarL.getClass();
                aVarL.b(playerK2.B(), enumC0056a, 1.0f).owner = playerK2;
                playerK2.V0(playerK2.sheet.z() / 2);
                CharacterSheet characterSheet5 = playerK2.sheet;
                int iA = characterSheet5.A() / 2;
                CharacterStats characterStats = characterSheet5.stats;
                int i14 = characterStats.missingMana - iA;
                characterStats.missingMana = i14;
                if (i14 < 0) {
                    characterStats.missingMana = 0;
                }
                GameLevel.k().usedRecover = Math.max(0, GameLevel.k().usedRecover - ((GameLevel.k().sheet.skillSet.g("extra_recovery") + 2) / 2));
                break;
            case 90:
                GameData.v().m();
                break;
            case 91:
                Player playerK3 = GameLevel.k();
                a aVarL2 = a.l();
                aVarL2.getClass();
                aVarL2.b(playerK3.B(), enumC0056a, 1.0f).owner = playerK3;
                playerK3.V0(playerK3.sheet.z());
                CharacterSheet characterSheet6 = playerK3.sheet;
                int iA2 = characterSheet6.A();
                CharacterStats characterStats2 = characterSheet6.stats;
                int i15 = characterStats2.missingMana - iA2;
                characterStats2.missingMana = i15;
                if (i15 < 0) {
                    characterStats2.missingMana = 0;
                }
                GameLevel.k().usedRecover = 0;
                break;
            case 92:
                GameData.v().party.c();
                break;
            case 93:
                NPC npcI = GameData.v().party.i();
                if (npcI != null && !npcI.i0()) {
                    npcI.V0(80);
                    break;
                }
                break;
            case 94:
                z.w().e0 = Integer.parseInt(strR);
                z.w().O();
                break;
            case 95:
                GameData.v().player.sheet.b0(522);
                GameData.v().player.sheet.b0(523);
                GameData.v().player.sheet.b0(525);
                GameData.v().player.sheet.b0(526);
                GameData.v().player.sheet.b0(527);
                GameData.v().player.sheet.b0(529);
                GameData.v().player.sheet.b0(370);
                GameData.v().player.sheet.b0(380);
                GameData.v().player.sheet.b0(240);
                GameData.v().player.sheet.b0(241);
                GameData.v().player.sheet.b0(242);
                GameData.v().player.sheet.b0(243);
                GameData.v().player.sheet.b0(244);
                GameData.v().player.sheet.b0(245);
                GameData.v().player.sheet.b0(246);
                GameData.v().player.sheet.b0(759);
                GameData.v().player.sheet.b0(115);
                GameData.v().player.sheet.b0(107);
                GameData.v().player.sheet.b0(112);
                GameLevel.k().v0();
                break;
            case 96:
                if (GameData.v().party.j() && (npcF = GameData.v().party.f()) != null) {
                    npcF.O0(40000);
                    GameAssets.o(lxYOBQSyWPCj.zYKeGamlFCg);
                    if (strR.trim().toLowerCase().equals("hirge")) {
                        npcF.sheet.a0("retribution");
                        npcF.sheet.a0("duel");
                        npcF.sheet.a0("death_ward");
                        npcF.sheet.a0(JDxjJEGD.nqoWmFvH);
                        npcF.sheet.a0("heavyhand");
                    }
                    if (strR.trim().toLowerCase().equals("grissenda")) {
                        npcF.sheet.a0("body_development");
                        npcF.sheet.a0("massive_criticals");
                        npcF.sheet.a0("infantry_training");
                        npcF.sheet.a0("precission_strikes");
                        npcF.sheet.a0("heavyhand");
                    }
                    if (strR.trim().toLowerCase().equals(lRWXeT.zYBNT)) {
                        npcF.sheet.a0("flurry");
                        npcF.sheet.a0(jUhYGTigo.TgEVdpOnk);
                        npcF.sheet.a0("massive_criticals");
                        npcF.sheet.a0("precission_strikes");
                        npcF.sheet.a0("precission_shots");
                        npcF.sheet.a0("assassinate");
                    }
                    npcF.sheet.f();
                    npcF.sheet.f();
                    npcF.N0();
                    npcF.N0();
                    npcF.N0();
                    GameData.v().log.a(GameString.a("COMPANION_ADVANCEMENT"));
                    GameConsole.a(GameString.a("COMPANION_ADVANCEMENT"));
                }
                break;
        }
    }

    public final String toString() {
        switch (this.type.ordinal()) {
            case 0:
                return "None#" + this.data;
            case 1:
                return "SetVariable#" + this.data;
            case 2:
                return "IncVariable#" + this.data;
            case 3:
                return "DecVariable#" + this.data;
            case 4:
                return gNaSiQJmMEn.WFSDNIHCSyY + this.data;
            case 5:
                return "LoseItem#" + this.data;
            case 6:
                return "LoseItems#" + this.data;
            case 7:
                return "GainItems#" + this.data;
            case 8:
                return "GainXP#" + this.data;
            case 9:
                return "GainHP#" + this.data;
            case 10:
                return "GainMana#" + this.data;
            case 11:
                return "GainGold#" + this.data;
            case 12:
                return "LoseGold#" + this.data;
            case 13:
                return "GetHPFull#" + this.data;
            case 14:
                return "LesserRestoration#" + this.data;
            case 15:
                return "CircleRestoration#" + this.data;
            case 16:
                return "LoseHP#" + this.data;
            case 17:
                return "LoseHPshock#" + this.data;
            case 18:
                return VEZT.LbLDwOVTC + this.data;
            case 19:
                return "LoseHPfire#" + this.data;
            case 20:
                return "LoseHPCold#" + this.data;
            case 21:
                return "LoseHPDeath#" + this.data;
            case 22:
                return EGEEZWr.XjNXmtKaaq + this.data;
            case 23:
                return "NPCGoTo#" + this.data;
            case 24:
                return "NPCSpawn#" + this.data;
            case 25:
                return "NPCDespawn#" + this.data;
            case 26:
                return "NPCHostile#" + this.data;
            case 27:
                return "NPCFollow#" + this.data;
            case 28:
                return "NPCAttack#" + this.data;
            case 29:
                return "NPCWait#" + this.data;
            case 30:
                return "NPCdontWait#" + this.data;
            case 31:
                return "NPCStopFollowing#" + this.data;
            case 32:
                return "NPCMoveRandom#" + this.data;
            case 33:
                return "Disband#" + this.data;
            case 34:
                return "DisbandParty#" + this.data;
            case 35:
                return "HideParty#" + this.data;
            case 36:
                return "ShowParty#" + this.data;
            case 37:
                return "LoseInventory#" + this.data;
            case 38:
                return "RecoverInventory#" + this.data;
            case 39:
                return CrhMs.ODi + this.data;
            case 40:
                return "GainMight#" + this.data;
            case 41:
                return "GainShield#" + this.data;
            case 42:
                return "GainSpeed#" + this.data;
            case 43:
                return "GainEffect#" + this.data;
            case 44:
                return "GainResistDeath#" + this.data;
            case 45:
                return "GainResistFire#" + this.data;
            case 46:
                return "GainResistCold#" + this.data;
            case 47:
                return "GainResistToxic#" + this.data;
            case 48:
                return "GainResistShock#" + this.data;
            case 49:
                return MbzYTTrg.MlxCYx + this.data;
            case 50:
                return "PoisonWeapon#" + this.data;
            case 51:
                return "StartConversation#" + this.data;
            case 52:
                return "StartItemConversation#" + this.data;
            case 53:
                return "Sleep#" + this.data;
            case 54:
                return "SaveGame#" + this.data;
            case 55:
                return "Particle#" + this.data;
            case 56:
                return "Recall#" + this.data;
            case 57:
                return "Teleport#" + this.data;
            case 58:
                return "Detect#" + this.data;
            case 59:
                return "NPCTryToDespawn#" + this.data;
            case 60:
                return "OpenShop#" + this.data;
            case 61:
                return "GainTrait#" + this.data;
            case 62:
                return "GainSkillPoint#" + this.data;
            case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                return "ItemActivate#" + this.data;
            case VertexAttributes.Usage.BoneWeight /* 64 */:
                return "ItemDeactivate#" + this.data;
            case 65:
                return "ItemHide#" + this.data;
            case 66:
                return "ItemShow#" + this.data;
            case 67:
                return "ItemBlockView#" + this.data;
            case 68:
                return "ItemUnblockView#" + this.data;
            case 69:
                return "ItemWalkable#" + this.data;
            case 70:
                return "ItemUnwalkable#" + this.data;
            case 71:
                return "PlayerRobbed#" + this.data;
            case 72:
                return "StopRender#" + this.data;
            case 73:
                return "StartRender#" + this.data;
            case 74:
                return "OpenWorldContainer#" + this.data;
            case 75:
                return "ResetPlayerSkills#" + this.data;
            case 76:
                return "ResetPlayerTraits#" + this.data;
            case 77:
                return "ResetCompanion#" + this.data;
            case 78:
                return "TrainSkill#" + this.data;
            case 79:
                return "ForgetPlayerAdvancedSkills#" + this.data;
            case 80:
                return "FullRest#" + this.data;
            case 81:
                return "GainRandomHP#" + this.data;
            case 82:
                return "Summon#" + this.data;
            case 83:
                return "TurnUndead#" + this.data;
            case 84:
                return "DestroyShards#" + this.data;
            case 85:
                return "HurtNPC#" + this.data;
            case 86:
                return "OpenVault#" + this.data;
            case 87:
                return "TolCurse#" + this.data;
            case 88:
                return "EndTolCurse#" + this.data;
            case 89:
                return "HalfRecovery#" + this.data;
            case 90:
                return "GainBagHolding#" + this.data;
            case 91:
                return "FullRecovery#" + this.data;
            case 92:
                return vIBRkbZbNjpf.GgowAhj + this.data;
            case 93:
                return "PlanarBinding#" + this.data;
            case 94:
                return MbzYTTrg.CIGWOMVTd + this.data;
            case 95:
                return "LoseArenaItems#" + this.data;
            case 96:
                return "UpgradeCompanion#" + this.data;
            default:
                return "";
        }
    }
}

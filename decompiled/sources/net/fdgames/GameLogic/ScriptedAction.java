package net.fdgames.GameLogic;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.games.Notifications;
import java.util.Iterator;
import java.util.Locale;
import k0.a;
import m0.b;
import m0.d;
import n0.z;
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
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Party;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Skill;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Skills;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.ItemConversation;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.GameAssets;
import o0.s;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScriptedAction {
    private String data;
    private ActionType type;

    /* JADX INFO: renamed from: net.fdgames.GameLogic.ScriptedAction$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3137a;

        static {
            int[] iArr = new int[ActionType.values().length];
            f3137a = iArr;
            try {
                ActionType actionType = ActionType.f3138a;
                iArr[9] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f3137a;
                ActionType actionType2 = ActionType.f3138a;
                iArr2[81] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = f3137a;
                ActionType actionType3 = ActionType.f3138a;
                iArr3[10] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = f3137a;
                ActionType actionType4 = ActionType.f3138a;
                iArr4[13] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = f3137a;
                ActionType actionType5 = ActionType.f3138a;
                iArr5[14] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = f3137a;
                ActionType actionType6 = ActionType.f3138a;
                iArr6[15] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = f3137a;
                ActionType actionType7 = ActionType.f3138a;
                iArr7[16] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = f3137a;
                ActionType actionType8 = ActionType.f3138a;
                iArr8[18] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = f3137a;
                ActionType actionType9 = ActionType.f3138a;
                iArr9[17] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = f3137a;
                ActionType actionType10 = ActionType.f3138a;
                iArr10[19] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = f3137a;
                ActionType actionType11 = ActionType.f3138a;
                iArr11[20] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = f3137a;
                ActionType actionType12 = ActionType.f3138a;
                iArr12[21] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr13 = f3137a;
                ActionType actionType13 = ActionType.f3138a;
                iArr13[8] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr14 = f3137a;
                ActionType actionType14 = ActionType.f3138a;
                iArr14[11] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr15 = f3137a;
                ActionType actionType15 = ActionType.f3138a;
                iArr15[12] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr16 = f3137a;
                ActionType actionType16 = ActionType.f3138a;
                iArr16[4] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                int[] iArr17 = f3137a;
                ActionType actionType17 = ActionType.f3138a;
                iArr17[7] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                int[] iArr18 = f3137a;
                ActionType actionType18 = ActionType.f3138a;
                iArr18[5] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                int[] iArr19 = f3137a;
                ActionType actionType19 = ActionType.f3138a;
                iArr19[6] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                int[] iArr20 = f3137a;
                ActionType actionType20 = ActionType.f3138a;
                iArr20[1] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                int[] iArr21 = f3137a;
                ActionType actionType21 = ActionType.f3138a;
                iArr21[3] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                int[] iArr22 = f3137a;
                ActionType actionType22 = ActionType.f3138a;
                iArr22[2] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                int[] iArr23 = f3137a;
                ActionType actionType23 = ActionType.f3138a;
                iArr23[22] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                int[] iArr24 = f3137a;
                ActionType actionType24 = ActionType.f3138a;
                iArr24[0] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                int[] iArr25 = f3137a;
                ActionType actionType25 = ActionType.f3138a;
                iArr25[23] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                int[] iArr26 = f3137a;
                ActionType actionType26 = ActionType.f3138a;
                iArr26[25] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                int[] iArr27 = f3137a;
                ActionType actionType27 = ActionType.f3138a;
                iArr27[26] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                int[] iArr28 = f3137a;
                ActionType actionType28 = ActionType.f3138a;
                iArr28[27] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                int[] iArr29 = f3137a;
                ActionType actionType29 = ActionType.f3138a;
                iArr29[29] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                int[] iArr30 = f3137a;
                ActionType actionType30 = ActionType.f3138a;
                iArr30[30] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                int[] iArr31 = f3137a;
                ActionType actionType31 = ActionType.f3138a;
                iArr31[28] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                int[] iArr32 = f3137a;
                ActionType actionType32 = ActionType.f3138a;
                iArr32[31] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                int[] iArr33 = f3137a;
                ActionType actionType33 = ActionType.f3138a;
                iArr33[41] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                int[] iArr34 = f3137a;
                ActionType actionType34 = ActionType.f3138a;
                iArr34[40] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                int[] iArr35 = f3137a;
                ActionType actionType35 = ActionType.f3138a;
                iArr35[39] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                int[] iArr36 = f3137a;
                ActionType actionType36 = ActionType.f3138a;
                iArr36[42] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                int[] iArr37 = f3137a;
                ActionType actionType37 = ActionType.f3138a;
                iArr37[46] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                int[] iArr38 = f3137a;
                ActionType actionType38 = ActionType.f3138a;
                iArr38[45] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                int[] iArr39 = f3137a;
                ActionType actionType39 = ActionType.f3138a;
                iArr39[44] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                int[] iArr40 = f3137a;
                ActionType actionType40 = ActionType.f3138a;
                iArr40[47] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                int[] iArr41 = f3137a;
                ActionType actionType41 = ActionType.f3138a;
                iArr41[48] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                int[] iArr42 = f3137a;
                ActionType actionType42 = ActionType.f3138a;
                iArr42[49] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                int[] iArr43 = f3137a;
                ActionType actionType43 = ActionType.f3138a;
                iArr43[43] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                int[] iArr44 = f3137a;
                ActionType actionType44 = ActionType.f3138a;
                iArr44[32] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                int[] iArr45 = f3137a;
                ActionType actionType45 = ActionType.f3138a;
                iArr45[51] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                int[] iArr46 = f3137a;
                ActionType actionType46 = ActionType.f3138a;
                iArr46[52] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                int[] iArr47 = f3137a;
                ActionType actionType47 = ActionType.f3138a;
                iArr47[34] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                int[] iArr48 = f3137a;
                ActionType actionType48 = ActionType.f3138a;
                iArr48[35] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                int[] iArr49 = f3137a;
                ActionType actionType49 = ActionType.f3138a;
                iArr49[36] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                int[] iArr50 = f3137a;
                ActionType actionType50 = ActionType.f3138a;
                iArr50[37] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                int[] iArr51 = f3137a;
                ActionType actionType51 = ActionType.f3138a;
                iArr51[38] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                int[] iArr52 = f3137a;
                ActionType actionType52 = ActionType.f3138a;
                iArr52[53] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                int[] iArr53 = f3137a;
                ActionType actionType53 = ActionType.f3138a;
                iArr53[54] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                int[] iArr54 = f3137a;
                ActionType actionType54 = ActionType.f3138a;
                iArr54[55] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                int[] iArr55 = f3137a;
                ActionType actionType55 = ActionType.f3138a;
                iArr55[33] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                int[] iArr56 = f3137a;
                ActionType actionType56 = ActionType.f3138a;
                iArr56[92] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                int[] iArr57 = f3137a;
                ActionType actionType57 = ActionType.f3138a;
                iArr57[56] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                int[] iArr58 = f3137a;
                ActionType actionType58 = ActionType.f3138a;
                iArr58[57] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                int[] iArr59 = f3137a;
                ActionType actionType59 = ActionType.f3138a;
                iArr59[58] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                int[] iArr60 = f3137a;
                ActionType actionType60 = ActionType.f3138a;
                iArr60[24] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                int[] iArr61 = f3137a;
                ActionType actionType61 = ActionType.f3138a;
                iArr61[60] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                int[] iArr62 = f3137a;
                ActionType actionType62 = ActionType.f3138a;
                iArr62[61] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                int[] iArr63 = f3137a;
                ActionType actionType63 = ActionType.f3138a;
                iArr63[62] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                int[] iArr64 = f3137a;
                ActionType actionType64 = ActionType.f3138a;
                iArr64[63] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                int[] iArr65 = f3137a;
                ActionType actionType65 = ActionType.f3138a;
                iArr65[67] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                int[] iArr66 = f3137a;
                ActionType actionType66 = ActionType.f3138a;
                iArr66[64] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                int[] iArr67 = f3137a;
                ActionType actionType67 = ActionType.f3138a;
                iArr67[65] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                int[] iArr68 = f3137a;
                ActionType actionType68 = ActionType.f3138a;
                iArr68[66] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                int[] iArr69 = f3137a;
                ActionType actionType69 = ActionType.f3138a;
                iArr69[68] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                int[] iArr70 = f3137a;
                ActionType actionType70 = ActionType.f3138a;
                iArr70[70] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                int[] iArr71 = f3137a;
                ActionType actionType71 = ActionType.f3138a;
                iArr71[69] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                int[] iArr72 = f3137a;
                ActionType actionType72 = ActionType.f3138a;
                iArr72[59] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                int[] iArr73 = f3137a;
                ActionType actionType73 = ActionType.f3138a;
                iArr73[50] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                int[] iArr74 = f3137a;
                ActionType actionType74 = ActionType.f3138a;
                iArr74[71] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                int[] iArr75 = f3137a;
                ActionType actionType75 = ActionType.f3138a;
                iArr75[73] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                int[] iArr76 = f3137a;
                ActionType actionType76 = ActionType.f3138a;
                iArr76[72] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                int[] iArr77 = f3137a;
                ActionType actionType77 = ActionType.f3138a;
                iArr77[74] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                int[] iArr78 = f3137a;
                ActionType actionType78 = ActionType.f3138a;
                iArr78[77] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                int[] iArr79 = f3137a;
                ActionType actionType79 = ActionType.f3138a;
                iArr79[75] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                int[] iArr80 = f3137a;
                ActionType actionType80 = ActionType.f3138a;
                iArr80[76] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                int[] iArr81 = f3137a;
                ActionType actionType81 = ActionType.f3138a;
                iArr81[78] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                int[] iArr82 = f3137a;
                ActionType actionType82 = ActionType.f3138a;
                iArr82[79] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                int[] iArr83 = f3137a;
                ActionType actionType83 = ActionType.f3138a;
                iArr83[80] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                int[] iArr84 = f3137a;
                ActionType actionType84 = ActionType.f3138a;
                iArr84[82] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                int[] iArr85 = f3137a;
                ActionType actionType85 = ActionType.f3138a;
                iArr85[83] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                int[] iArr86 = f3137a;
                ActionType actionType86 = ActionType.f3138a;
                iArr86[84] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                int[] iArr87 = f3137a;
                ActionType actionType87 = ActionType.f3138a;
                iArr87[85] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                int[] iArr88 = f3137a;
                ActionType actionType88 = ActionType.f3138a;
                iArr88[86] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                int[] iArr89 = f3137a;
                ActionType actionType89 = ActionType.f3138a;
                iArr89[87] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                int[] iArr90 = f3137a;
                ActionType actionType90 = ActionType.f3138a;
                iArr90[88] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                int[] iArr91 = f3137a;
                ActionType actionType91 = ActionType.f3138a;
                iArr91[89] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                int[] iArr92 = f3137a;
                ActionType actionType92 = ActionType.f3138a;
                iArr92[91] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                int[] iArr93 = f3137a;
                ActionType actionType93 = ActionType.f3138a;
                iArr93[90] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                int[] iArr94 = f3137a;
                ActionType actionType94 = ActionType.f3138a;
                iArr94[93] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                int[] iArr95 = f3137a;
                ActionType actionType95 = ActionType.f3138a;
                iArr95[94] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                int[] iArr96 = f3137a;
                ActionType actionType96 = ActionType.f3138a;
                iArr96[95] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                int[] iArr97 = f3137a;
                ActionType actionType97 = ActionType.f3138a;
                iArr97[96] = 97;
            } catch (NoSuchFieldError unused97) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ActionType {
        public static final ActionType R0;
        public static final ActionType S0;
        private static final /* synthetic */ ActionType[] T0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ActionType f3138a = new ActionType("None", 0);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ActionType f3140b = new ActionType("SetVariable", 1);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ActionType f3142c = new ActionType("IncVariable", 2);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ActionType f3144d = new ActionType("DecVariable", 3);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ActionType f3146e = new ActionType("GainItem", 4);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ActionType f3148f = new ActionType("LoseItem", 5);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final ActionType f3150g = new ActionType("LoseItems", 6);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final ActionType f3152h = new ActionType("GainItems", 7);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final ActionType f3154i = new ActionType("GainXP", 8);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final ActionType f3156j = new ActionType("GainHP", 9);

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final ActionType f3158k = new ActionType("GainMana", 10);

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final ActionType f3160l = new ActionType("GainGold", 11);

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final ActionType f3162m = new ActionType("LoseGold", 12);

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final ActionType f3164n = new ActionType("GetHPFull", 13);

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final ActionType f3166o = new ActionType("LesserRestoration", 14);

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final ActionType f3168p = new ActionType("CircleRestoration", 15);

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final ActionType f3170q = new ActionType("LoseHP", 16);

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final ActionType f3172r = new ActionType("LoseHPShock", 17);
        public static final ActionType s = new ActionType("LoseHPToxic", 18);

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final ActionType f3175t = new ActionType("LoseHPFire", 19);

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final ActionType f3176u = new ActionType("LoseHPCold", 20);

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final ActionType f3177v = new ActionType("LoseHPDeath", 21);

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final ActionType f3178w = new ActionType("Travel", 22);

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final ActionType f3179x = new ActionType("NPCGoTo", 23);

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final ActionType f3180y = new ActionType("NPCSpawn", 24);

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final ActionType f3181z = new ActionType("NPCDespawn", 25);
        public static final ActionType A = new ActionType("NPCHostile", 26);
        public static final ActionType B = new ActionType("NPCFollow", 27);
        public static final ActionType C = new ActionType("NPCAttack", 28);
        public static final ActionType D = new ActionType("NPCWait", 29);
        public static final ActionType E = new ActionType("NPCDontWait", 30);
        public static final ActionType F = new ActionType("NPCStopFollowing", 31);
        public static final ActionType G = new ActionType("NPCMoveRandom", 32);
        public static final ActionType H = new ActionType("Disband", 33);
        public static final ActionType I = new ActionType("DisbandParty", 34);
        public static final ActionType J = new ActionType("HideParty", 35);
        public static final ActionType K = new ActionType("ShowParty", 36);
        public static final ActionType L = new ActionType("LoseInventory", 37);
        public static final ActionType M = new ActionType("RecoverInventory", 38);
        public static final ActionType N = new ActionType("GainShield", 39);
        public static final ActionType O = new ActionType("GainMight", 40);
        public static final ActionType P = new ActionType("GainInvis", 41);
        public static final ActionType Q = new ActionType("GainSpeed", 42);
        public static final ActionType R = new ActionType("GainEffect", 43);
        public static final ActionType S = new ActionType("GainResistDeath", 44);
        public static final ActionType T = new ActionType("GainResistFire", 45);
        public static final ActionType U = new ActionType("GainResistCold", 46);
        public static final ActionType V = new ActionType("GainResistToxic", 47);
        public static final ActionType W = new ActionType("GainResistShock", 48);
        public static final ActionType X = new ActionType("GainResistSpirit", 49);
        public static final ActionType Y = new ActionType("PoisonWeapon", 50);
        public static final ActionType Z = new ActionType("StartConversation", 51);

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public static final ActionType f3139a0 = new ActionType("StartItemConversation", 52);

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public static final ActionType f3141b0 = new ActionType("Sleep", 53);

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public static final ActionType f3143c0 = new ActionType("SaveGame", 54);

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public static final ActionType f3145d0 = new ActionType("Particle", 55);

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public static final ActionType f3147e0 = new ActionType("Recall", 56);

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public static final ActionType f3149f0 = new ActionType("Teleport", 57);

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public static final ActionType f3151g0 = new ActionType("Detect", 58);

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public static final ActionType f3153h0 = new ActionType("NPCTryToDespawn", 59);

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public static final ActionType f3155i0 = new ActionType("OpenShop", 60);

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public static final ActionType f3157j0 = new ActionType("GainTrait", 61);

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public static final ActionType f3159k0 = new ActionType("GainSkillPoint", 62);

        /* JADX INFO: renamed from: l0, reason: collision with root package name */
        public static final ActionType f3161l0 = new ActionType("ItemActivate", 63);

        /* JADX INFO: renamed from: m0, reason: collision with root package name */
        public static final ActionType f3163m0 = new ActionType("ItemDeactivate", 64);

        /* JADX INFO: renamed from: n0, reason: collision with root package name */
        public static final ActionType f3165n0 = new ActionType("ItemHide", 65);

        /* JADX INFO: renamed from: o0, reason: collision with root package name */
        public static final ActionType f3167o0 = new ActionType("ItemShow", 66);

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        public static final ActionType f3169p0 = new ActionType("ItemBlockView", 67);

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        public static final ActionType f3171q0 = new ActionType("ItemUnblockView", 68);

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        public static final ActionType f3173r0 = new ActionType("ItemWalkable", 69);

        /* JADX INFO: renamed from: s0, reason: collision with root package name */
        public static final ActionType f3174s0 = new ActionType("ItemUnwalkable", 70);
        public static final ActionType t0 = new ActionType("PlayerRobbed", 71);
        public static final ActionType u0 = new ActionType("StopRender", 72);
        public static final ActionType v0 = new ActionType("StartRender", 73);
        public static final ActionType w0 = new ActionType("OpenWorldContainer", 74);
        public static final ActionType x0 = new ActionType("ResetPlayerSkills", 75);
        public static final ActionType y0 = new ActionType("ResetPlayerTraits", 76);
        public static final ActionType z0 = new ActionType("ResetCompanion", 77);
        public static final ActionType A0 = new ActionType("TrainSkill", 78);
        public static final ActionType B0 = new ActionType("ForgetPlayerAdvancedSkills", 79);
        public static final ActionType C0 = new ActionType("FullRest", 80);
        public static final ActionType D0 = new ActionType("GainRandomHP", 81);
        public static final ActionType E0 = new ActionType("Summon", 82);
        public static final ActionType F0 = new ActionType("TurnUndead", 83);
        public static final ActionType G0 = new ActionType("DestroyShards", 84);
        public static final ActionType H0 = new ActionType("HurtNPC", 85);
        public static final ActionType I0 = new ActionType("OpenVault", 86);
        public static final ActionType J0 = new ActionType("TolCurse", 87);
        public static final ActionType K0 = new ActionType("EndTolCurse", 88);
        public static final ActionType L0 = new ActionType("HalfRecovery", 89);
        public static final ActionType M0 = new ActionType("GainBagHolding", 90);
        public static final ActionType N0 = new ActionType("FullRecovery", 91);
        public static final ActionType O0 = new ActionType("DisbandAll", 92);
        public static final ActionType P0 = new ActionType("PlanarBinding", 93);
        public static final ActionType Q0 = new ActionType("EndGame", 94);

        static {
            ActionType actionType = new ActionType("LoseArenaItems", 95);
            R0 = actionType;
            ActionType actionType2 = new ActionType("UpgradeCompanion", 96);
            S0 = actionType2;
            T0 = new ActionType[]{f3138a, f3140b, f3142c, f3144d, f3146e, f3148f, f3150g, f3152h, f3154i, f3156j, f3158k, f3160l, f3162m, f3164n, f3166o, f3168p, f3170q, f3172r, s, f3175t, f3176u, f3177v, f3178w, f3179x, f3180y, f3181z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, f3139a0, f3141b0, f3143c0, f3145d0, f3147e0, f3149f0, f3151g0, f3153h0, f3155i0, f3157j0, f3159k0, f3161l0, f3163m0, f3165n0, f3167o0, f3169p0, f3171q0, f3173r0, f3174s0, t0, u0, v0, w0, x0, y0, z0, A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, actionType, actionType2};
        }

        private ActionType() {
            throw null;
        }

        public static ActionType valueOf(String str) {
            return (ActionType) Enum.valueOf(ActionType.class, str);
        }

        public static ActionType[] values() {
            return (ActionType[]) T0.clone();
        }
    }

    public ScriptedAction() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void a() {
        Shop shopW;
        NPC npcF;
        String strR = FDUtils.r(this.data);
        int iOrdinal = this.type.ordinal();
        Damage.DamageType damageType = Damage.DamageType.f3046a;
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2303o;
        int i2 = 0;
        switch (iOrdinal) {
            case 0:
                r0.a.a("ERROR 4.2 ;" + GameData.v().CurrentLevel + " - " + this.data);
                break;
            case 1:
                String[] strArrSplit = strR.split(",");
                GameData.v().gameVariables.e(Integer.parseInt(strArrSplit[1]), strArrSplit[0]);
                break;
            case 2:
                String[] strArrSplit2 = strR.split(",");
                if (strArrSplit2[0].contains("REP_")) {
                    GameWorld.f3189c.a(Integer.parseInt(strArrSplit2[1]), 255, strArrSplit2[0].substring(4));
                } else {
                    GameData.v().gameVariables.e(Integer.parseInt(strArrSplit2[1]) + GameData.v().gameVariables.b(strArrSplit2[0]), strArrSplit2[0]);
                }
                break;
            case 3:
                String[] strArrSplit3 = strR.split(",");
                if (strArrSplit3[0].contains("REP_")) {
                    GameWorld.f3189c.a(-Integer.parseInt(strArrSplit3[1]), 255, strArrSplit3[0].substring(4));
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
                String[] strArrSplit4 = strR.split(",");
                for (int i4 = 1; i4 <= Integer.parseInt(strArrSplit4[1]); i4++) {
                    GameData.v().player.sheet.b0(Integer.parseInt(strArrSplit4[0]));
                }
                GameData.v().log.j(Integer.parseInt(strArrSplit4[0]), Integer.parseInt(strArrSplit4[1]));
                break;
            case 7:
                String[] strArrSplit5 = strR.split(",");
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
                z.v().s().V0(Integer.parseInt(strR));
                break;
            case 10:
                z.v().s().o1(Integer.parseInt(strR));
                break;
            case 11:
                GameData.v().player.B1(Integer.parseInt(strR));
                GameData.v().log.c(Integer.parseInt(strR));
                break;
            case 12:
                GameData.v().player.R1(Integer.parseInt(strR));
                GameData.v().log.h(Integer.parseInt(strR));
                break;
            case 13:
                GameData.v().player.V0(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                break;
            case 14:
                CharacterSheet characterSheet3 = z.v().s().sheet;
                int i7 = characterSheet3.stats.missingHP / 2;
                int iC = characterSheet3.C() / 2;
                z.v().s().V0(i7);
                z.v().s().o1(iC);
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
                        npcF2.q0(MapActor.ActorState.f3071a);
                        npcF2.Q1();
                        npcF2.t0();
                        SkillActions.d(npcF2);
                    }
                }
                break;
            case 16:
                z.v().s().R0(new Damage(damageType, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case 17:
                z.v().s().R0(new Damage(Damage.DamageType.f3049d, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case 18:
                z.v().s().R0(new Damage(Damage.DamageType.f3051f, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case 19:
                z.v().s().R0(new Damage(Damage.DamageType.f3047b, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case 20:
                z.v().s().R0(new Damage(Damage.DamageType.f3048c, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case Decal.C4 /* 21 */:
                z.v().s().R0(new Damage(Damage.DamageType.f3050e, Integer.parseInt(strR), false), z.v().s().q(), false, 0);
                break;
            case Decal.U4 /* 22 */:
                String[] strArrSplit6 = strR.split(",");
                if (strArrSplit6[0].equals(GameData.v().CurrentLevel)) {
                    new Coords();
                    Coords coordsM = b.P().M(Integer.parseInt(strArrSplit6[1]));
                    a.l().i();
                    GameData.v().player.f3092x = coordsM.f3287x;
                    GameData.v().player.f3093y = coordsM.f3288y;
                    GameData.v().player.C0();
                } else {
                    GameLevel.k().Y1(new Transition(strArrSplit6[0], Integer.parseInt(strArrSplit6[1])));
                }
                if (strArrSplit6.length == 3) {
                    GameData.v().M(Integer.parseInt(strArrSplit6[2]));
                }
                GameData.v().party.getClass();
                Party.q();
                break;
            case Decal.V4 /* 23 */:
                String[] strArrSplit7 = strR.split(",");
                for (MapActor mapActor : GameLevel.e()) {
                    if (mapActor.tag != null && strArrSplit7[0].trim().equals(mapActor.tag.trim())) {
                        mapActor.r0(b.P().W(strArrSplit7[1].trim()));
                        mapActor.o0(b.P().W(strArrSplit7[1].trim()));
                    }
                }
                break;
            case Decal.SIZE /* 24 */:
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
                    z.v().getClass();
                    if (z.m() > 0) {
                        z.v().getClass();
                        ((NPC) GameLevel.g(z.m())).C1();
                    }
                    z.v().getClass();
                    ((NPC) GameLevel.g(z.m())).f3092x = GameLevel.k().f3092x;
                    z.v().getClass();
                    ((NPC) GameLevel.g(z.m())).f3093y = GameLevel.k().f3093y;
                } else {
                    for (MapActor mapActor4 : GameLevel.e()) {
                        if (mapActor4.tag != null && strR.trim().equals(mapActor4.tag.trim())) {
                            NPC npc2 = (NPC) mapActor4;
                            npc2.C1();
                            npc2.f3092x = GameLevel.k().f3092x;
                            npc2.f3093y = GameLevel.k().f3093y;
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
                        int i8 = NPC.f3023b;
                        npcF3.attackStrategy = 3;
                    }
                    if (strR.trim().toLowerCase(locale).equals("defend")) {
                        NPC npcF4 = GameData.v().party.f();
                        int i9 = NPC.f3023b;
                        npcF4.attackStrategy = 2;
                    }
                    if (strR.trim().toLowerCase(locale).equals("close")) {
                        NPC npcF5 = GameData.v().party.f();
                        int i10 = NPC.f3023b;
                        npcF5.attackStrategy = 1;
                    }
                    if (strR.trim().toLowerCase(locale).equals("all")) {
                        NPC npcF6 = GameData.v().party.f();
                        int i11 = NPC.f3023b;
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
                z.v().getClass();
                if (z.m() > 0) {
                    z.v().getClass();
                    ((NPC) GameLevel.g(z.m())).V1();
                }
                break;
            case 32:
                z.v().getClass();
                if (z.m() > 0) {
                    z.v().getClass();
                    ((NPC) GameLevel.g(z.m())).O1();
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
                GameData.v().player.Q1();
                break;
            case 39:
                z.v().s().t1(Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 40:
                z.v().s().j1(Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 41:
                Iterator<NPC> it = GameLevelData.o().npcs.iterator();
                while (it.hasNext()) {
                    it.next().P1();
                }
                GameData.v().player.f1(Integer.parseInt(strR));
                break;
            case 42:
                z.v().s().v1(Integer.parseInt(strR));
                break;
            case 43:
                String[] strArrSplit8 = strR.split(",");
                z.v().s().sheet.c(Integer.parseInt(strArrSplit8[1]), Integer.parseInt(strArrSplit8[2]), strArrSplit8[0]);
                break;
            case 44:
                z.v().s().q1(CharacterResistances.ResistanceType.f2998d, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 45:
                z.v().s().q1(CharacterResistances.ResistanceType.f2995a, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 46:
                z.v().s().q1(CharacterResistances.ResistanceType.f2996b, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 47:
                z.v().s().q1(CharacterResistances.ResistanceType.f2999e, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 48:
                z.v().s().q1(CharacterResistances.ResistanceType.f2997c, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 49:
                z.v().s().q1(CharacterResistances.ResistanceType.f3000f, Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 50:
                z.v().s().n1(Integer.parseInt(strR.split(",", -1)[0]), Integer.parseInt(r1[1]));
                break;
            case 51:
                String[] strArrSplit9 = strR.split(",", -1);
                String str = strArrSplit9[0];
                String str2 = strArrSplit9[1];
                int iS = GameLevelData.o().s(str);
                if (iS == 0) {
                    iS = GameLevelData.o().s(str);
                }
                if (iS > 0 && !str2.equals("")) {
                    if (z.z().booleanValue()) {
                        z.v().getClass();
                        z.u().setVisible(false);
                        GameLevel.n(false);
                    }
                    d dVar = new d(iS, str2);
                    dVar.e();
                    z.v().getClass();
                    z.u().t(dVar);
                    break;
                }
                break;
            case 52:
                ItemConversation itemConversationQ = b.P().Q(strR);
                if (itemConversationQ != null) {
                    z.v().getClass();
                    z.u().t(itemConversationQ);
                }
                break;
            case 53:
                GameLevel.k().q0(MapActor.ActorState.f3079i);
                break;
            case 54:
                GameLevel.k().V0(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                GameLevel.k().sheet.stats.missingMana = 0;
                if (GameData.v().party != null && GameData.v().party.j()) {
                    GameData.v().party.f().V0(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                }
                GameLevel.k().S1();
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
                a.EnumC0031a enumC0031a2 = a.EnumC0031a.f2291c;
                if (zEquals) {
                    a.l().b(GameLevel.k().B(), enumC0031a2, 0.0f);
                } else {
                    for (MapActor mapActor5 : GameLevel.e()) {
                        if (mapActor5.tag != null && strR.trim().equals(mapActor5.tag.trim())) {
                            a.l().b(mapActor5.B(), enumC0031a2, 0.0f);
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
                if (z.z().booleanValue()) {
                    z.v().getClass();
                    int iM = z.m();
                    z.v().getClass();
                    z.u().setVisible(false);
                    GameLevel.n(false);
                    i2 = iM;
                }
                if (i2 > 0 && (shopW = GameLevelData.o().w(i2)) != null) {
                    z.v().F(shopW);
                    break;
                }
                break;
            case 61:
                z.v().s().N0();
                break;
            case 62:
                z.v().s().M0();
                break;
            case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                for (int i12 = 0; i12 < GameLevelData.r().size(); i12++) {
                    if (GameLevelData.r().get(i12) != null && GameLevelData.r().get(i12).tag != null && GameLevelData.r().get(i12).tag.equals(strR)) {
                        GameLevelData.r().get(i12).N(MapItem.MapItemAction.f3007b);
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
                        GameLevelData.r().get(i13).N(MapItem.MapItemAction.f3008c);
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
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3006a);
                    }
                    i2++;
                }
                break;
            case 66:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3013h);
                    }
                    i2++;
                }
                break;
            case 67:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3011f);
                    }
                    i2++;
                }
                break;
            case 68:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3012g);
                    }
                    i2++;
                }
                break;
            case 69:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3009d);
                    }
                    i2++;
                }
                break;
            case 70:
                while (i2 < GameLevelData.r().size()) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        GameLevelData.r().get(i2).N(MapItem.MapItemAction.f3010e);
                    }
                    i2++;
                }
                break;
            case 71:
                GameLevel.k().C1();
                GameData.v().introMessageShown = false;
                GameLevel.k().a2();
                break;
            case 72:
                a.l().f2288o = true;
                break;
            case 73:
                a.l().f2288o = false;
                break;
            case 74:
                z.v().V.T(1, GameData.v().z(strR));
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
                    GameData.v().log.a(GameString.a("LEARNED_BEAST_MASTERY"));
                    GameConsole.a(GameString.a("LEARNED_BEAST_MASTERY"));
                } else if (skillC != null) {
                    if (z.z().booleanValue()) {
                        z.v().getClass();
                        z.u().setVisible(false);
                    }
                    s sVar = z.v().f2939b0;
                    CharacterSheet characterSheet4 = GameLevel.k().sheet;
                    z.v().getClass();
                    sVar.d(characterSheet4, skillC);
                }
                break;
            case 79:
                GameData.v().a();
                break;
            case 80:
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                GameData.v().V(true);
                System.gc();
                a.l().i();
                l0.b.n();
                GameData.v().player.areasVisited.b();
                l0.b.h();
                GameLevel.k().U1(true);
                break;
            case 81:
                z.v().s().V0(FDUtils.b(1, Integer.parseInt(strR)));
                if (FDUtils.b(1, 100) < 40) {
                    z.v().s().z1(3.0f);
                }
                break;
            case 82:
                String[] strArrSplit10 = strR.split(",", -1);
                SkillActions.l(z.v().s(), strArrSplit10[0], Integer.parseInt(strArrSplit10[1]), Integer.parseInt(strArrSplit10[2]));
                break;
            case 83:
                z.v().s().G0(1, "");
                SkillActions.m(z.v().s());
                break;
            case 84:
                GameData.v().N();
                break;
            case 85:
                String[] strArrSplit11 = strR.split(",");
                for (NPC npc3 : GameLevelData.o().npcs) {
                    if (npc3.tag != null && strArrSplit11[0].trim().equals(npc3.tag.trim())) {
                        npc3.R0(new Damage(damageType, Integer.parseInt(strArrSplit11[1].trim()), false), 1, false, 0);
                    }
                }
                break;
            case 86:
                if (z.z().booleanValue()) {
                    z.v().getClass();
                    z.u().setVisible(false);
                    GameLevel.n(false);
                }
                z.v().f2937a0.f();
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
                aVarL.b(playerK2.B(), enumC0031a, 1.0f).owner = playerK2;
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
                aVarL2.b(playerK3.B(), enumC0031a, 1.0f).owner = playerK3;
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
                z.v().f2945e0 = Integer.parseInt(strR);
                z.v().M();
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
                    GameAssets.o("levelup");
                    if (strR.trim().toLowerCase().equals("hirge")) {
                        npcF.sheet.a0("retribution");
                        npcF.sheet.a0("duel");
                        npcF.sheet.a0("death_ward");
                        npcF.sheet.a0("spiritual_ward");
                        npcF.sheet.a0("heavyhand");
                    }
                    if (strR.trim().toLowerCase().equals("grissenda")) {
                        npcF.sheet.a0("body_development");
                        npcF.sheet.a0("massive_criticals");
                        npcF.sheet.a0("infantry_training");
                        npcF.sheet.a0("precission_strikes");
                        npcF.sheet.a0("heavyhand");
                    }
                    if (strR.trim().toLowerCase().equals("adaon")) {
                        npcF.sheet.a0("flurry");
                        npcF.sheet.a0("rapid_fire");
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
                return "GainItem#" + this.data;
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
                return "LoseHPToxic#" + this.data;
            case 19:
                return "LoseHPfire#" + this.data;
            case 20:
                return "LoseHPCold#" + this.data;
            case Decal.C4 /* 21 */:
                return "LoseHPDeath#" + this.data;
            case Decal.U4 /* 22 */:
                return "Travel#" + this.data;
            case Decal.V4 /* 23 */:
                return "NPCGoTo#" + this.data;
            case Decal.SIZE /* 24 */:
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
                return "GainInvis#" + this.data;
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
                return "GainResistSpirit#" + this.data;
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
                return "DisbandAll#" + this.data;
            case 93:
                return "PlanarBinding#" + this.data;
            case 94:
                return "EndGame#" + this.data;
            case 95:
                return "LoseArenaItems#" + this.data;
            case 96:
                return "UpgradeCompanion#" + this.data;
            default:
                return "";
        }
    }

    public ScriptedAction(ActionType actionType, String str) {
        this.type = actionType;
        this.data = str;
    }
}

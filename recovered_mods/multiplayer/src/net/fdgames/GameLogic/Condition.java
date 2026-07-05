package net.fdgames.GameLogic;

import a.a;
import android.app.cYK.BQmoQ;
import android.app.cYK.lRWXeT;
import android.support.v4.app.mFy.fApIihhYHIP;
import androidx.appcompat.widget.LW.RCsZWh;
import androidx.coordinatorlayout.widget.OvMp.SkCylVE;
import com.badlogic.gdx.scenes.scene2d.utils.WHW.gNaSiQJmMEn;
import com.google.android.datatransport.backend.cct.CSLH.qJDUJ;
import com.google.android.gms.internal.p000authapi.KJ.ZeMa;
import com.google.android.gms.tasks.bnw.jUhYGTigo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import n0.WOA.IUcMoQOkPHcA;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Party;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.Rules;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.ek.Settings;
import y0.b;
import z0.ow.DkgvDLHsdXPkn;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Condition {
    private String data;
    private ConditionType type;

    /* JADX INFO: renamed from: net.fdgames.GameLogic.Condition$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3323a;

        static {
            int[] iArr = new int[ConditionType.values().length];
            f3323a = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3323a[32] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3323a[36] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3323a[37] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3323a[35] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3323a[31] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3323a[29] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3323a[30] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3323a[1] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3323a[2] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3323a[3] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3323a[4] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3323a[5] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f3323a[15] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f3323a[16] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f3323a[17] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f3323a[24] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f3323a[25] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f3323a[26] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f3323a[27] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f3323a[28] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f3323a[6] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f3323a[14] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f3323a[13] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f3323a[9] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f3323a[7] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f3323a[8] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f3323a[10] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f3323a[11] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f3323a[12] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f3323a[20] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f3323a[21] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                f3323a[22] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f3323a[40] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                f3323a[18] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                f3323a[23] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                f3323a[19] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                f3323a[33] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                f3323a[34] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                f3323a[38] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                f3323a[39] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                f3323a[41] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                f3323a[42] = 43;
            } catch (NoSuchFieldError unused43) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ConditionType {
        public static final ConditionType A;
        public static final ConditionType B;
        public static final ConditionType C;
        public static final ConditionType D;
        public static final ConditionType E;
        public static final ConditionType F;
        public static final ConditionType G;
        public static final ConditionType H;
        public static final ConditionType I;
        public static final ConditionType J;
        public static final ConditionType K;
        public static final ConditionType L;
        public static final ConditionType M;
        public static final ConditionType N;
        public static final ConditionType O;
        public static final ConditionType P;
        public static final ConditionType Q;
        public static final ConditionType R;
        private static final /* synthetic */ ConditionType[] S;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final ConditionType f3324b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final ConditionType f3325c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final ConditionType f3326d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final ConditionType f3327e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final ConditionType f3328f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final ConditionType f3329g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final ConditionType f3330h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final ConditionType f3331i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final ConditionType f3332j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final ConditionType f3333k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final ConditionType f3334l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final ConditionType f3335m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final ConditionType f3336n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final ConditionType f3337o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final ConditionType f3338p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final ConditionType f3339q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final ConditionType f3340r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static final ConditionType f3341s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final ConditionType f3342t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final ConditionType f3343u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final ConditionType f3344v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final ConditionType f3345w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final ConditionType f3346x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final ConditionType f3347y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final ConditionType f3348z;

        static {
            ConditionType conditionType = new ConditionType("None", 0);
            f3324b = conditionType;
            ConditionType conditionType2 = new ConditionType("PlayerHasItem", 1);
            f3325c = conditionType2;
            ConditionType conditionType3 = new ConditionType("PlayerHasntItem", 2);
            f3326d = conditionType3;
            ConditionType conditionType4 = new ConditionType("PlayerHasItems", 3);
            f3327e = conditionType4;
            ConditionType conditionType5 = new ConditionType("PlayerHasGold", 4);
            f3328f = conditionType5;
            ConditionType conditionType6 = new ConditionType("PlayerHasntGold", 5);
            f3329g = conditionType6;
            ConditionType conditionType7 = new ConditionType("NPCisFollower", 6);
            f3330h = conditionType7;
            ConditionType conditionType8 = new ConditionType("NPCisInParty", 7);
            f3331i = conditionType8;
            ConditionType conditionType9 = new ConditionType("NPCisntInParty", 8);
            f3332j = conditionType9;
            ConditionType conditionType10 = new ConditionType("IsInParty", 9);
            f3333k = conditionType10;
            ConditionType conditionType11 = new ConditionType("NPCinArea", 10);
            f3334l = conditionType11;
            ConditionType conditionType12 = new ConditionType("NPCNotinArea", 11);
            f3335m = conditionType12;
            ConditionType conditionType13 = new ConditionType("NPCisNotInParty", 12);
            f3336n = conditionType13;
            ConditionType conditionType14 = new ConditionType("NPCIsntWaiting", 13);
            f3337o = conditionType14;
            ConditionType conditionType15 = new ConditionType("NPCIsWaiting", 14);
            f3338p = conditionType15;
            ConditionType conditionType16 = new ConditionType("PlayerIsClass", 15);
            f3339q = conditionType16;
            ConditionType conditionType17 = new ConditionType("PlayerIsntClass", 16);
            f3340r = conditionType17;
            ConditionType conditionType18 = new ConditionType("PlayerIsLevel", 17);
            f3341s = conditionType18;
            ConditionType conditionType19 = new ConditionType(lRWXeT.egOYfWGjeJ, 18);
            f3342t = conditionType19;
            ConditionType conditionType20 = new ConditionType("HasNoFollower", 19);
            f3343u = conditionType20;
            ConditionType conditionType21 = new ConditionType("AreaIs", 20);
            f3344v = conditionType21;
            ConditionType conditionType22 = new ConditionType("AreaIsnt", 21);
            f3345w = conditionType22;
            ConditionType conditionType23 = new ConditionType("HasCompanion", 22);
            f3346x = conditionType23;
            ConditionType conditionType24 = new ConditionType("HasNoCompanion", 23);
            f3347y = conditionType24;
            ConditionType conditionType25 = new ConditionType("PlayerIsntLevel", 24);
            f3348z = conditionType25;
            ConditionType conditionType26 = new ConditionType("PlayerIsWounded", 25);
            A = conditionType26;
            ConditionType conditionType27 = new ConditionType("VariableGreater", 26);
            B = conditionType27;
            ConditionType conditionType28 = new ConditionType("VariableEqual", 27);
            C = conditionType28;
            ConditionType conditionType29 = new ConditionType("VariableLower", 28);
            D = conditionType29;
            ConditionType conditionType30 = new ConditionType("IsItemActive", 29);
            E = conditionType30;
            ConditionType conditionType31 = new ConditionType("IsItemInactive", 30);
            F = conditionType31;
            ConditionType conditionType32 = new ConditionType("isItemHidden", 31);
            G = conditionType32;
            ConditionType conditionType33 = new ConditionType("isRegistered", 32);
            H = conditionType33;
            ConditionType conditionType34 = new ConditionType("NPCIsDead", 33);
            I = conditionType34;
            ConditionType conditionType35 = new ConditionType("NPCIsntDead", 34);
            J = conditionType35;
            ConditionType conditionType36 = new ConditionType("PlayerHasGuild", 35);
            K = conditionType36;
            ConditionType conditionType37 = new ConditionType(BQmoQ.YiLVlSLWefSU, 36);
            L = conditionType37;
            ConditionType conditionType38 = new ConditionType("PlayerIsFemale", 37);
            M = conditionType38;
            ConditionType conditionType39 = new ConditionType("isNight", 38);
            N = conditionType39;
            ConditionType conditionType40 = new ConditionType("isDay", 39);
            O = conditionType40;
            ConditionType conditionType41 = new ConditionType("HasParty", 40);
            P = conditionType41;
            ConditionType conditionType42 = new ConditionType("Hostiles", 41);
            Q = conditionType42;
            ConditionType conditionType43 = new ConditionType("FadedIn", 42);
            R = conditionType43;
            S = new ConditionType[]{conditionType, conditionType2, conditionType3, conditionType4, conditionType5, conditionType6, conditionType7, conditionType8, conditionType9, conditionType10, conditionType11, conditionType12, conditionType13, conditionType14, conditionType15, conditionType16, conditionType17, conditionType18, conditionType19, conditionType20, conditionType21, conditionType22, conditionType23, conditionType24, conditionType25, conditionType26, conditionType27, conditionType28, conditionType29, conditionType30, conditionType31, conditionType32, conditionType33, conditionType34, conditionType35, conditionType36, conditionType37, conditionType38, conditionType39, conditionType40, conditionType41, conditionType42, conditionType43};
        }

        private ConditionType() {
            throw null;
        }

        public static ConditionType valueOf(String str) {
            return (ConditionType) Enum.valueOf(ConditionType.class, str);
        }

        public static ConditionType[] values() {
            return (ConditionType[]) S.clone();
        }
    }

    public Condition() {
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Condition(String str, String str2) {
        Locale locale = Locale.ENGLISH;
        boolean zX = a.x(str, locale, "none");
        ConditionType conditionType = ConditionType.f3324b;
        if (!zX) {
            if (a.x(str, locale, "playerhasitem")) {
                conditionType = ConditionType.f3325c;
            } else if (a.x(str, locale, "playerhasntitem")) {
                conditionType = ConditionType.f3326d;
            } else if (a.x(str, locale, "playerhasitems")) {
                conditionType = ConditionType.f3327e;
            } else if (a.x(str, locale, "playerhasgold")) {
                conditionType = ConditionType.f3328f;
            } else if (a.x(str, locale, IUcMoQOkPHcA.foUziwB)) {
                conditionType = ConditionType.f3329g;
            } else if (a.x(str, locale, "playerisclass")) {
                conditionType = ConditionType.f3339q;
            } else if (a.x(str, locale, "playerisntclass")) {
                conditionType = ConditionType.f3340r;
            } else if (a.x(str, locale, "playerislevel")) {
                conditionType = ConditionType.f3341s;
            } else if (a.x(str, locale, gNaSiQJmMEn.PZiYx)) {
                conditionType = ConditionType.A;
            } else if (a.x(str, locale, "variablegreater")) {
                conditionType = ConditionType.B;
            } else if (a.x(str, locale, "variableequal")) {
                conditionType = ConditionType.C;
            } else if (a.x(str, locale, "variablelower")) {
                conditionType = ConditionType.D;
            } else if (a.x(str, locale, "npcisfollower")) {
                conditionType = ConditionType.f3330h;
            } else if (a.x(str, locale, BQmoQ.MUXUOVNNcUNAAu)) {
                conditionType = ConditionType.f3348z;
            } else if (a.x(str, locale, "hasfollower")) {
                conditionType = ConditionType.f3342t;
            } else if (a.x(str, locale, "hasparty")) {
                conditionType = ConditionType.P;
            } else if (a.x(str, locale, "hasnofollower")) {
                conditionType = ConditionType.f3343u;
            } else if (a.x(str, locale, "hascompanion")) {
                conditionType = ConditionType.f3346x;
            } else if (a.x(str, locale, "hasnocompanion")) {
                conditionType = ConditionType.f3347y;
            } else if (a.x(str, locale, "npcisinparty")) {
                conditionType = ConditionType.f3331i;
            } else if (a.x(str, locale, "npcisntinparty")) {
                conditionType = ConditionType.f3332j;
            } else if (a.x(str, locale, "npcisnotinparty")) {
                conditionType = ConditionType.f3336n;
            } else if (a.x(str, locale, "npciswaiting")) {
                conditionType = ConditionType.f3338p;
            } else if (a.x(str, locale, "npcisntwaiting")) {
                conditionType = ConditionType.f3337o;
            } else if (a.x(str, locale, "areais")) {
                conditionType = ConditionType.f3344v;
            } else if (a.x(str, locale, "areaisnt")) {
                conditionType = ConditionType.f3345w;
            } else {
                boolean zX2 = a.x(str, locale, "isitemactive");
                ConditionType conditionType2 = ConditionType.E;
                if (zX2) {
                    conditionType = conditionType2;
                } else {
                    boolean zX3 = a.x(str, locale, "isiteminactive");
                    ConditionType conditionType3 = ConditionType.F;
                    if (!zX3) {
                        if (!a.x(str, locale, "active")) {
                            if (a.x(str, locale, jUhYGTigo.TNiptTUYr)) {
                                conditionType = conditionType3;
                            } else if (a.x(str, locale, RCsZWh.dNo)) {
                                conditionType = ConditionType.G;
                            } else if (a.x(str, locale, "isregistered")) {
                                conditionType = ConditionType.H;
                            } else if (a.x(str, locale, "npcisdead")) {
                                conditionType = ConditionType.I;
                            } else if (a.x(str, locale, "npcisntdead") || a.x(str, locale, "npcisnotdead")) {
                                conditionType = ConditionType.J;
                            } else if (a.x(str, locale, "npcinarea")) {
                                conditionType = ConditionType.f3334l;
                            } else if (a.x(str, locale, SkCylVE.xQhntHKMkJeuA)) {
                                conditionType = ConditionType.f3335m;
                            } else if (a.x(str, locale, "playerhasguild")) {
                                conditionType = ConditionType.K;
                            } else if (a.x(str, locale, "playerismale")) {
                                conditionType = ConditionType.L;
                            } else if (a.x(str, locale, qJDUJ.SmqykFDOdgbR)) {
                                conditionType = ConditionType.M;
                            } else if (a.x(str, locale, "isnight")) {
                                conditionType = ConditionType.N;
                            } else if (a.x(str, locale, "isday")) {
                                conditionType = ConditionType.O;
                            } else if (a.x(str, locale, "hostiles")) {
                                conditionType = ConditionType.Q;
                            } else if (a.x(str, locale, "fadedin")) {
                                conditionType = ConditionType.R;
                            } else if (a.x(str, locale, "isinparty")) {
                                conditionType = ConditionType.f3333k;
                            } else {
                                d1.a.a(DkgvDLHsdXPkn.mFbtLBcBdbdDi + GameData.v().CurrentLevel + " - " + str);
                            }
                        }
                    }
                }
            }
        }
        this.type = conditionType;
        this.data = str2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final Boolean a() {
        String strR = FDUtils.r(this.data);
        switch (this.type.ordinal()) {
            case 0:
                return Boolean.TRUE;
            case 1:
                AStarPathFinder aStarPathFinder = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.sheet.Q(Integer.parseInt(this.data)) > 0);
            case 2:
                AStarPathFinder aStarPathFinder2 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.sheet.Q(Integer.parseInt(this.data)) == 0);
            case 3:
                String[] strArrSplit = this.data.split(",");
                AStarPathFinder aStarPathFinder3 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.sheet.Q(Integer.parseInt(strArrSplit[0])) >= Integer.parseInt(strArrSplit[1]));
            case 4:
                AStarPathFinder aStarPathFinder4 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.h() >= Integer.parseInt(strR));
            case 5:
                AStarPathFinder aStarPathFinder5 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.h() < Integer.parseInt(strR));
            case 6:
                z.w().getClass();
                if (z.n() > 0) {
                    z.w().getClass();
                    if (((NPC) GameLevel.g(z.n())).k0()) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            case 7:
                String[] strArrSplit2 = this.data.split(",");
                return GameData.v().party.p(strArrSplit2[0], strArrSplit2[1]) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                String[] strArrSplit3 = this.data.split(",");
                return GameData.v().party.p(strArrSplit3[0], strArrSplit3[1]) ? Boolean.FALSE : Boolean.TRUE;
            case 9:
                Party party = GameData.v().party;
                if (!party.j() || !party.f().tag.equals(strR)) {
                    for (Follower follower : party.followers) {
                        if (follower.a().tag == null || !follower.a().tag.equals(strR)) {
                        }
                    }
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            case 10:
                return GameLevel.d(this.data);
            case 11:
                return Boolean.valueOf(!GameLevel.d(this.data).booleanValue());
            case 12:
                String[] strArrSplit4 = this.data.split(",");
                return GameData.v().party.p(strArrSplit4[0], strArrSplit4[1]) ? Boolean.FALSE : Boolean.TRUE;
            case 13:
                z.w().getClass();
                if (z.n() > 0) {
                    z.w().getClass();
                    if (((NPC) GameLevel.g(z.n())).wait) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            case 14:
                z.w().getClass();
                if (z.n() > 0) {
                    z.w().getClass();
                    if (((NPC) GameLevel.g(z.n())).wait) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            case 15:
                Rules.CharacterClass characterClassC = Rules.c(this.data);
                AStarPathFinder aStarPathFinder6 = GameLevel.f3309a;
                return Boolean.valueOf(characterClassC == GameData.v().player.sheet.stats.c());
            case 16:
                Rules.CharacterClass characterClassC2 = Rules.c(this.data);
                AStarPathFinder aStarPathFinder7 = GameLevel.f3309a;
                return Boolean.valueOf(characterClassC2 != GameData.v().player.sheet.stats.c());
            case 17:
                AStarPathFinder aStarPathFinder8 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.sheet.stats.f() >= Integer.parseInt(this.data));
            case 18:
                return GameData.v().party.k();
            case 19:
                return Boolean.valueOf(!GameData.v().party.k().booleanValue());
            case 20:
                return Boolean.valueOf(GameData.v().CurrentLevel.equals(this.data));
            case 21:
                return Boolean.valueOf(!GameData.v().CurrentLevel.equals(this.data));
            case 22:
                return Boolean.valueOf(GameData.v().party.j());
            case 23:
                return Boolean.valueOf(!GameData.v().party.j());
            case 24:
                AStarPathFinder aStarPathFinder9 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.sheet.stats.f() < Integer.parseInt(this.data));
            case 25:
                return Boolean.FALSE;
            case 26:
                String[] strArrSplit5 = this.data.split(",");
                return Boolean.valueOf(GameData.v().gameVariables.b(strArrSplit5[0]) > Integer.parseInt(strArrSplit5[1]));
            case 27:
                String[] strArrSplit6 = this.data.split(",");
                return Boolean.valueOf(GameData.v().gameVariables.b(strArrSplit6[0]) == Integer.parseInt(strArrSplit6[1]));
            case 28:
                String[] strArrSplit7 = this.data.split(",");
                return Boolean.valueOf(GameData.v().gameVariables.b(strArrSplit7[0]) < Integer.parseInt(strArrSplit7[1]));
            case 29:
                for (int i2 = 0; i2 < GameLevelData.r().size(); i2++) {
                    if (GameLevelData.r().get(i2).tag != null && GameLevelData.r().get(i2).tag.equals(strR)) {
                        return GameLevelData.r().get(i2).O();
                    }
                }
                return Boolean.FALSE;
            case 30:
                for (int i3 = 0; i3 < GameLevelData.r().size(); i3++) {
                    if (GameLevelData.r().get(i3).tag.equals(strR)) {
                        return Boolean.valueOf(!GameLevelData.r().get(i3).O().booleanValue());
                    }
                }
                return Boolean.TRUE;
            case 31:
                for (int i4 = 0; i4 < GameLevelData.r().size(); i4++) {
                    if (GameLevelData.r().get(i4).tag.equals(strR)) {
                        return GameLevelData.r().get(i4).P();
                    }
                }
                return Boolean.FALSE;
            case 32:
                return Settings.t();
            case 33:
                return Boolean.valueOf(GameData.v().H(this.data));
            case 34:
                return Boolean.valueOf(!GameData.v().H(this.data));
            case 35:
                if (GameData.v().gameVariables.b("guild_seventh") <= 0 && GameData.v().gameVariables.b("guild_warriors") <= 0 && GameData.v().gameVariables.b("guild_golden") <= 0 && GameData.v().gameVariables.b("guild_loreseekers") <= 0 && GameData.v().gameVariables.b("guild_wizards") <= 0 && GameData.v().gameVariables.b("guild_three") <= 0) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            case 36:
                AStarPathFinder aStarPathFinder10 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.gender == Character.Gender.f3207b);
            case 37:
                AStarPathFinder aStarPathFinder11 = GameLevel.f3309a;
                return Boolean.valueOf(GameData.v().player.gender == Character.Gender.f3208c);
            case 38:
                return Boolean.valueOf(GameData.v().night);
            case 39:
                return Boolean.valueOf(!GameData.v().night);
            case 40:
                return Boolean.valueOf(GameData.v().party.j() || GameData.v().party.k().booleanValue());
            case 41:
                if (!GameData.v().CurrentLevel.contains("arena")) {
                    Player player = GameData.v().player;
                    return Boolean.valueOf(Player.f3243g);
                }
                int size = GameLevel.f3311c.size();
                for (int i5 = 0; i5 < size; i5++) {
                    WorldFactions worldFactions = GameWorld.f3410c;
                    int[] iArrR = GameData.v().player.r();
                    ArrayList<MapActor> arrayList = GameLevel.f3311c;
                    if (worldFactions.g(iArrR, arrayList.get(i5).r()) && !arrayList.get(i5).d0().equals(MapActor.ActorState.f3289e)) {
                        b bVarP = b.P();
                        int iQ = arrayList.get(i5).q();
                        Coords coordsB = GameData.v().player.B();
                        bVarP.getClass();
                        if (b.F(iQ, coordsB) < 768.0d) {
                            return Boolean.TRUE;
                        }
                    }
                }
                return Boolean.FALSE;
            case 42:
                return w0.a.l().n();
            default:
                PrintStream printStream = System.out;
                printStream.println("WARNING: condition not recognized:" + toString());
                printStream.println("WARNING: unknown condition: " + this.type + "-" + this.data);
                return Boolean.TRUE;
        }
    }

    public final String toString() {
        switch (this.type.ordinal()) {
            case 0:
                return "condition:None";
            case 1:
                return "condition:PlayerHasItem#" + this.data;
            case 2:
                return "condition:PlayerHasntItem#" + this.data;
            case 3:
                return "condition:PlayerHasItems#" + this.data;
            case 4:
                return "condition:PlayerHasGold#" + this.data;
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 30:
            case 36:
            case 37:
            default:
                return "condition:";
            case 6:
                return "condition:NPCisFollower#" + this.data;
            case 7:
                return "condition:NPCisInParty#" + this.data;
            case 12:
                return "condition:NPCisNotInParty#" + this.data;
            case 15:
                return "condition:PlayerIsClass#" + this.data;
            case 16:
                return "condition:PlayerIsntClass#" + this.data;
            case 17:
                return "condition:PlayerIsLevel#" + this.data;
            case 18:
                return "condition:HasFollower#" + this.data;
            case 19:
                return "condition:HasNoFollower#" + this.data;
            case 20:
                return "condition:AreaIs#" + this.data;
            case 21:
                return "condition:AreaIsnt#" + this.data;
            case 22:
                return "condition:HasCompanion#" + this.data;
            case 23:
                return "condition:HasNoCompanion#" + this.data;
            case 24:
                return "condition:PlayerIsntLevel#" + this.data;
            case 25:
                return ZeMa.tGCQJzYWmcyaa + this.data;
            case 26:
                return "condition:VariableGreater#" + this.data;
            case 27:
                return "condition:VariableEqual#" + this.data;
            case 28:
                return "condition:VariableLower#" + this.data;
            case 29:
                return "condition:IsItemActive#" + this.data;
            case 31:
                return "condition:isItemHidden#" + this.data;
            case 32:
                return "condition:isRegistered#" + this.data;
            case 33:
                return "condition:NPCIsDead#" + this.data;
            case 34:
                return "condition:NPCIsntDead#" + this.data;
            case 35:
                return "condition:PlayerHasGuild#" + this.data;
            case 38:
                return "condition:isNight#" + this.data;
            case 39:
                return "condition:isDay#" + this.data;
            case 40:
                return "condition:HasParty#" + this.data;
            case 41:
                return fApIihhYHIP.hIZXvmkFNythh + this.data;
        }
    }
}

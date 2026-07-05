package q0;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ControllerCommand;
import net.fdgames.ek.Settings;

/* JADX INFO: compiled from: ControllerSettingButton.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g extends TextButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ControllerCommand f3866a;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ControllerSettingButton.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f3867a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f3868b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f3869c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f3870d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f3871e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f3872f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f3873g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f3874h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f3875i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final a f3876j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final a f3877k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final a f3878l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final a f3879m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final a f3880n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final a f3881o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final a f3882p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final a f3883q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final a f3884r;
        public static final a s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final a f3885t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private static final /* synthetic */ a[] f3886u;

        static {
            a aVar = new a("UP", 0);
            f3867a = aVar;
            a aVar2 = new a("DOWN", 1);
            f3868b = aVar2;
            a aVar3 = new a("LEFT", 2);
            f3869c = aVar3;
            a aVar4 = new a("RIGHT", 3);
            f3870d = aVar4;
            a aVar5 = new a("ATTACK", 4);
            f3871e = aVar5;
            a aVar6 = new a("CANCEL", 5);
            f3872f = aVar6;
            a aVar7 = new a("SKILL1", 6);
            f3873g = aVar7;
            a aVar8 = new a("SKILL2", 7);
            f3874h = aVar8;
            a aVar9 = new a("SKILL3", 8);
            f3875i = aVar9;
            a aVar10 = new a("SKILL4", 9);
            f3876j = aVar10;
            a aVar11 = new a("SKILL5", 10);
            f3877k = aVar11;
            a aVar12 = new a("SKILL6", 11);
            f3878l = aVar12;
            a aVar13 = new a("SKILL7", 12);
            f3879m = aVar13;
            a aVar14 = new a("SKILL8", 13);
            f3880n = aVar14;
            a aVar15 = new a("NEXTITEM", 14);
            f3881o = aVar15;
            a aVar16 = new a("PREVITEM", 15);
            f3882p = aVar16;
            a aVar17 = new a("USEITEM", 16);
            f3883q = aVar17;
            a aVar18 = new a("RECOVERY", 17);
            f3884r = aVar18;
            a aVar19 = new a("TAKE", 18);
            s = aVar19;
            a aVar20 = new a("DROP", 19);
            f3885t = aVar20;
            f3886u = new a[]{aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20};
        }

        private a() {
            throw null;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) f3886u.clone();
        }
    }

    public g(a aVar) {
        super("", Assets.g(), "menuButton");
        switch (aVar.ordinal()) {
            case 0:
                this.f3866a = Settings.f3418a.up;
                break;
            case 1:
                this.f3866a = Settings.f3418a.down;
                break;
            case 2:
                this.f3866a = Settings.f3418a.left;
                break;
            case 3:
                this.f3866a = Settings.f3418a.right;
                break;
            case 4:
                this.f3866a = Settings.f3418a.action;
                break;
            case 5:
                this.f3866a = Settings.f3418a.cancel;
                break;
            case 6:
                this.f3866a = Settings.f3418a.skill1;
                break;
            case 7:
                this.f3866a = Settings.f3418a.skill2;
                break;
            case 8:
                this.f3866a = Settings.f3418a.skill3;
                break;
            case 9:
                this.f3866a = Settings.f3418a.skill4;
                break;
            case 10:
                this.f3866a = Settings.f3418a.skill5;
                break;
            case 11:
                this.f3866a = Settings.f3418a.skill6;
                break;
            case 12:
                this.f3866a = Settings.f3418a.skill7;
                break;
            case 13:
                this.f3866a = Settings.f3418a.skill8;
                break;
            case 14:
                this.f3866a = Settings.f3418a.nextItem;
                break;
            case 15:
                this.f3866a = Settings.f3418a.prevItem;
                break;
            case 16:
                this.f3866a = Settings.f3418a.useItem;
                break;
            case 17:
                this.f3866a = Settings.f3418a.recovery;
                break;
            case 18:
                this.f3866a = Settings.f3418a.invTake;
                break;
            case 19:
                this.f3866a = Settings.f3418a.invDrop;
                break;
        }
        a();
    }

    public final void a() {
        StringBuilder sb = new StringBuilder("[BLACK]");
        ControllerCommand controllerCommand = this.f3866a;
        sb.append(Settings.c(controllerCommand));
        sb.append("[]");
        sb.append(System.getProperty("line.separator"));
        sb.append("[BLUE]");
        sb.append(controllerCommand.toString());
        sb.append("[]");
        setText(sb.toString());
    }
}

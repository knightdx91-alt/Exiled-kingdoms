package net.fdgames.ek;

import com.badlogic.gdx.g;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class ControllerCommand {
    public int id;
    public int povIndex;
    public commandType type;

    /* JADX INFO: renamed from: net.fdgames.ek.ControllerCommand$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3364a;

        static {
            int[] iArr = new int[commandType.values().length];
            f3364a = iArr;
            try {
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3364a[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3364a[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3364a[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3364a[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class commandType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final commandType f3365a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final commandType f3366b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final commandType f3367c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final commandType f3368d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final commandType f3369e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static final /* synthetic */ commandType[] f3370f;

        static {
            commandType commandtype = new commandType("NEGATIVE_AXIS", 0);
            f3365a = commandtype;
            commandType commandtype2 = new commandType("POSITIVE_AXIS", 1);
            f3366b = commandtype2;
            commandType commandtype3 = new commandType("BUTTON", 2);
            f3367c = commandtype3;
            commandType commandtype4 = new commandType("KEY", 3);
            f3368d = commandtype4;
            commandType commandtype5 = new commandType("POV", 4);
            f3369e = commandtype5;
            f3370f = new commandType[]{commandtype, commandtype2, commandtype3, commandtype4, commandtype5};
        }

        private commandType() {
            throw null;
        }

        public static commandType valueOf(String str) {
            return (commandType) Enum.valueOf(commandType.class, str);
        }

        public static commandType[] values() {
            return (commandType[]) f3370f.clone();
        }
    }

    public ControllerCommand(commandType commandtype, int i2) {
        this.type = commandtype;
        this.id = i2;
    }

    public final String toString() {
        int iOrdinal = this.type.ordinal();
        if (iOrdinal == 0) {
            return "-AX " + this.id;
        }
        if (iOrdinal == 1) {
            return "+AX " + this.id;
        }
        if (iOrdinal == 2) {
            return "BTN " + this.id;
        }
        if (iOrdinal == 3) {
            return "" + g.a.a(this.id);
        }
        if (iOrdinal != 4) {
            return "";
        }
        return "DPAD " + this.id;
    }

    public ControllerCommand() {
    }
}

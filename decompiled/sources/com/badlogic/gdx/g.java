package com.badlogic.gdx;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: compiled from: Input.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface g {

    /* JADX INFO: compiled from: Input.java */
    public static class a {
        public static String a(int i2) {
            if (i2 < 0) {
                throw new IllegalArgumentException(a.a.h(i2, "keycode cannot be negative, keycode: "));
            }
            if (i2 > 255) {
                throw new IllegalArgumentException(a.a.h(i2, "keycode cannot be greater than 255, keycode: "));
            }
            if (i2 == 112) {
                return "Forward Delete";
            }
            switch (i2) {
                case 0:
                    return "Unknown";
                case 1:
                    return "Soft Left";
                case 2:
                    return "Soft Right";
                case 3:
                    return "Home";
                case 4:
                    return "Back";
                case 5:
                    return "Call";
                case 6:
                    return "End Call";
                case 7:
                    return "0";
                case 8:
                    return "1";
                case 9:
                    return "2";
                case 10:
                    return "3";
                case 11:
                    return "4";
                case 12:
                    return "5";
                case 13:
                    return "6";
                case 14:
                    return "7";
                case 15:
                    return "8";
                case 16:
                    return "9";
                case 17:
                    return "*";
                case 18:
                    return "#";
                case 19:
                    return "Up";
                case 20:
                    return "Down";
                case Decal.C4 /* 21 */:
                    return "Left";
                case Decal.U4 /* 22 */:
                    return "Right";
                case Decal.V4 /* 23 */:
                    return "Center";
                case Decal.SIZE /* 24 */:
                    return "Volume Up";
                case 25:
                    return "Volume Down";
                case 26:
                    return "Power";
                case 27:
                    return "Camera";
                case 28:
                    return "Clear";
                case 29:
                    return "A";
                case 30:
                    return "B";
                case 31:
                    return "C";
                case 32:
                    return "D";
                case 33:
                    return "E";
                case 34:
                    return "F";
                case 35:
                    return "G";
                case 36:
                    return "H";
                case 37:
                    return "I";
                case 38:
                    return "J";
                case 39:
                    return "K";
                case 40:
                    return "L";
                case 41:
                    return "M";
                case 42:
                    return "N";
                case 43:
                    return "O";
                case 44:
                    return "P";
                case 45:
                    return "Q";
                case 46:
                    return "R";
                case 47:
                    return "S";
                case 48:
                    return "T";
                case 49:
                    return "U";
                case 50:
                    return "V";
                case 51:
                    return "W";
                case 52:
                    return "X";
                case 53:
                    return "Y";
                case 54:
                    return "Z";
                case 55:
                    return ",";
                case 56:
                    return ".";
                case 57:
                    return "L-Alt";
                case 58:
                    return "R-Alt";
                case 59:
                    return "L-Shift";
                case 60:
                    return "R-Shift";
                case 61:
                    return "Tab";
                case 62:
                    return "Space";
                case Notifications.NOTIFICATION_TYPES_ALL /* 63 */:
                    return "SYM";
                case VertexAttributes.Usage.BoneWeight /* 64 */:
                    return "Explorer";
                case 65:
                    return "Envelope";
                case 66:
                    return "Enter";
                case 67:
                    return "Delete";
                case 68:
                    return "`";
                case 69:
                    return "-";
                case 70:
                    return "=";
                case 71:
                    return "[";
                case 72:
                    return "]";
                case 73:
                    return "\\";
                case 74:
                    return ";";
                case 75:
                    return "'";
                case 76:
                    return "/";
                case 77:
                    return "@";
                case 78:
                    return "Num";
                case 79:
                    return "Headset Hook";
                case 80:
                    return "Focus";
                case 81:
                    return "Plus";
                case 82:
                    return "Menu";
                case 83:
                    return "Notification";
                case 84:
                    return "Search";
                case 85:
                    return "Play/Pause";
                case 86:
                    return "Stop Media";
                case 87:
                    return "Next Media";
                case 88:
                    return "Prev Media";
                case 89:
                    return "Rewind";
                case 90:
                    return "Fast Forward";
                case 91:
                    return "Mute";
                case 92:
                    return "Page Up";
                case 93:
                    return "Page Down";
                case 94:
                    return "PICTSYMBOLS";
                case 95:
                    return "SWITCH_CHARSET";
                case 96:
                    return "A Button";
                case 97:
                    return "B Button";
                case 98:
                    return "C Button";
                case 99:
                    return "X Button";
                case MetadataChangeSet.MAX_TOTAL_PROPERTIES_PER_RESOURCE /* 100 */:
                    return "Y Button";
                case Quests.SELECT_COMPLETED_UNCLAIMED /* 101 */:
                    return "Z Button";
                case Quests.SELECT_ENDING_SOON /* 102 */:
                    return "L1 Button";
                case Quests.SELECT_RECENTLY_FAILED /* 103 */:
                    return "R1 Button";
                case 104:
                    return "L2 Button";
                case 105:
                    return "R2 Button";
                case 106:
                    return "Left Thumb";
                case 107:
                    return "Right Thumb";
                case 108:
                    return "Start";
                case 109:
                    return "Select";
                case 110:
                    return "Button Mode";
                default:
                    switch (i2) {
                        case 129:
                            return "L-Ctrl";
                        case 130:
                            return "R-Ctrl";
                        case 131:
                            return "Escape";
                        case 132:
                            return "End";
                        case 133:
                            return "Insert";
                        default:
                            switch (i2) {
                                case 144:
                                    return "Numpad 0";
                                case 145:
                                    return "Numpad 1";
                                case 146:
                                    return "Numpad 2";
                                case 147:
                                    return "Numpad 3";
                                case 148:
                                    return "Numpad 4";
                                case 149:
                                    return "Numpad 5";
                                case 150:
                                    return "Numpad 6";
                                case 151:
                                    return "Numpad 7";
                                case 152:
                                    return "Numpad 8";
                                case 153:
                                    return "Numpad 9";
                                default:
                                    switch (i2) {
                                        case 243:
                                            return ":";
                                        case 244:
                                            return "F1";
                                        case 245:
                                            return "F2";
                                        case 246:
                                            return "F3";
                                        case 247:
                                            return "F4";
                                        case 248:
                                            return "F5";
                                        case 249:
                                            return "F6";
                                        case 250:
                                            return "F7";
                                        case 251:
                                            return "F8";
                                        case 252:
                                            return "F9";
                                        case 253:
                                            return "F10";
                                        case 254:
                                            return "F11";
                                        case 255:
                                            return "F12";
                                        default:
                                            return null;
                                    }
                            }
                    }
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Input.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f1662b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ b[] f1663c;

        static {
            b bVar = new b("Default", 0);
            f1661a = bVar;
            b bVar2 = new b("NumberPad", 1);
            b bVar3 = new b("PhonePad", 2);
            b bVar4 = new b("Email", 3);
            b bVar5 = new b("Password", 4);
            f1662b = bVar5;
            f1663c = new b[]{bVar, bVar2, bVar3, bVar4, bVar5, new b("URI", 5)};
        }

        private b() {
            throw null;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f1663c.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Input.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1664a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f1665b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ c[] f1666c;

        static {
            c cVar = new c("Landscape", 0);
            f1664a = cVar;
            c cVar2 = new c("Portrait", 1);
            f1665b = cVar2;
            f1666c = new c[]{cVar, cVar2};
        }

        private c() {
            throw null;
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) f1666c.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Input.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f1667a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final d f1668b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final d f1669c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final d f1670d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final d f1671e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final d f1672f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final d f1673g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final d f1674h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final d f1675i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private static final /* synthetic */ d[] f1676j;

        static {
            d dVar = new d("HardwareKeyboard", 0);
            f1667a = dVar;
            d dVar2 = new d("OnscreenKeyboard", 1);
            f1668b = dVar2;
            d dVar3 = new d("MultitouchScreen", 2);
            f1669c = dVar3;
            d dVar4 = new d("Accelerometer", 3);
            f1670d = dVar4;
            d dVar5 = new d("Compass", 4);
            f1671e = dVar5;
            d dVar6 = new d("Vibrator", 5);
            f1672f = dVar6;
            d dVar7 = new d("Gyroscope", 6);
            f1673g = dVar7;
            d dVar8 = new d("RotationVector", 7);
            f1674h = dVar8;
            d dVar9 = new d("Pressure", 8);
            f1675i = dVar9;
            f1676j = new d[]{dVar, dVar2, dVar3, dVar4, dVar5, dVar6, dVar7, dVar8, dVar9};
        }

        private d() {
            throw null;
        }

        public static d valueOf(String str) {
            return (d) Enum.valueOf(d.class, str);
        }

        public static d[] values() {
            return (d[]) f1676j.clone();
        }
    }

    /* JADX INFO: compiled from: Input.java */
    public interface e {
    }

    long getCurrentEventTime();

    int getDeltaX();

    int getDeltaY();

    int getX();

    int getY();

    boolean isButtonPressed(int i2);

    @Deprecated
    boolean isCatchBackKey();

    boolean isKeyJustPressed(int i2);

    boolean isKeyPressed(int i2);

    boolean isTouched();

    boolean isTouched(int i2);

    @Deprecated
    void setCatchBackKey(boolean z2);

    void setInputProcessor(j jVar);

    void setOnscreenKeyboardVisible(boolean z2);
}

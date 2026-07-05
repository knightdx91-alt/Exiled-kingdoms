package net.fdgames.GameEntities.Helpers;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MessageData {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class dataType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final /* synthetic */ dataType[] f3070a = {new dataType("damage", 0)};

        /* JADX INFO: Fake field, exist only in values array */
        dataType EF5;

        private dataType() {
            throw null;
        }

        public static dataType valueOf(String str) {
            return (dataType) Enum.valueOf(dataType.class, str);
        }

        public static dataType[] values() {
            return (dataType[]) f3070a.clone();
        }
    }
}

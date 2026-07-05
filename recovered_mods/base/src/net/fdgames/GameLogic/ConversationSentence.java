package net.fdgames.GameLogic;

import a.a;
import java.util.Locale;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class ConversationSentence {
    public int ID;
    public ActionsSet actions;
    public ConditionsSet conditions;
    public String newQuestionOwner;
    public int next;
    private PlayerRequirements requirements;
    private String text;
    public SentenceType type;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class SentenceType {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SentenceType f3134a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final SentenceType f3135b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ SentenceType[] f3136c;

        static {
            SentenceType sentenceType = new SentenceType("Question", 0);
            f3134a = sentenceType;
            SentenceType sentenceType2 = new SentenceType("Answer", 1);
            f3135b = sentenceType2;
            f3136c = new SentenceType[]{sentenceType, sentenceType2};
        }

        private SentenceType() {
            throw null;
        }

        public static SentenceType valueOf(String str) {
            return (SentenceType) Enum.valueOf(SentenceType.class, str);
        }

        public static SentenceType[] values() {
            return (SentenceType[]) f3136c.clone();
        }
    }

    public ConversationSentence() {
    }

    public final Boolean a() {
        PlayerRequirements playerRequirements = this.requirements;
        if (playerRequirements == null) {
            return this.conditions.a();
        }
        playerRequirements.getClass();
        boolean z2 = false;
        if (playerRequirements.a(GameData.v().player.sheet, false) && this.conditions.a().booleanValue()) {
            z2 = true;
        }
        return Boolean.valueOf(z2);
    }

    public final String b() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.requirements == null) {
            str = "";
        } else {
            str = this.requirements.toString() + " ";
        }
        sb.append(str);
        sb.append(this.text);
        return sb.toString();
    }

    public ConversationSentence(String str, String str2, String str3, String str4, String str5) {
        if (str.contains(",")) {
            String[] strArrSplit = str.split(",", 2);
            this.ID = Integer.parseInt(strArrSplit[0]);
            this.newQuestionOwner = strArrSplit[1];
        } else {
            this.ID = Integer.parseInt(str);
            this.newQuestionOwner = "";
        }
        Locale locale = Locale.ENGLISH;
        this.type = a.y(str2, locale, "q") ? SentenceType.f3134a : a.y(str2, locale, "a") ? SentenceType.f3135b : null;
        this.text = str3;
        if (str4.equals("")) {
            this.next = 0;
        } else {
            this.next = Integer.parseInt(str4);
        }
        if (str5.equals("")) {
            this.requirements = null;
        } else {
            this.requirements = new PlayerRequirements(str5);
        }
    }
}

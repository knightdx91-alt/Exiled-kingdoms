package net.fdgames.GameLogic;

import a.a;
import java.util.Locale;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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
    public enum SentenceType {
        Question, Answer;
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
        this.type = a.y(str2, locale, "q") ? SentenceType.Question : a.y(str2, locale, "a") ? SentenceType.Answer : null;
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

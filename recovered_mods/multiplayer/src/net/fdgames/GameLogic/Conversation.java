package net.fdgames.GameLogic;

import a.a;
import androidx.appcompat.widget.LW.RCsZWh;
import com.badlogic.gdx.Gdx;
import com.google.android.gms.common.api.Mcc.KOdB;
import com.google.android.gms.common.internal.safeparcel.YT.EGEEZWr;
import i.LXkY.rFUF;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.GameLogic.ConversationSentence;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.ek.Settings;
import z0.ow.DkgvDLHsdXPkn;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Conversation {
    private ArrayList<ConversationSentence> conversationSentences;

    /* JADX WARN: Removed duplicated region for block: B:35:0x0137 A[EDGE_INSN: B:61:0x0137->B:35:0x0137 BREAK  A[LOOP:1: B:28:0x011a->B:39:0x0140]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Conversation(String str) {
        String[] strArr;
        this.conversationSentences = new ArrayList<>();
        String str2 = str.equals("Hirge") ? "hirge" : str;
        this.conversationSentences = new ArrayList<>();
        String[] strArrSplit = Gdx.files.internal("data/conversations/" + str2 + ".txt").readString("UTF-8").replace("[GREEN]", "[FOREST]").split("\n");
        String strL = FDUtils.l();
        boolean zEquals = strL.equals("");
        int i2 = -1;
        String str3 = RCsZWh.MSfjYFICEHe;
        if (!zEquals) {
            if (Gdx.files.internal("data/conversations/" + strL + str2 + ".txt").exists()) {
                String[] strArrSplit2 = Gdx.files.internal("data/conversations/" + strL + str2 + ".txt").readString(KOdB.Jtxdfph).split(str3);
                if (strArrSplit2 != null && strArrSplit2.length < strArrSplit.length) {
                    PrintStream printStream = System.out;
                    StringBuilder sbT = a.t("WARNING: conversation localized file length mismatch, ", str2, " ; ");
                    sbT.append(strArrSplit2.length);
                    sbT.append(EGEEZWr.QtQh);
                    sbT.append(strArrSplit.length);
                    printStream.println(sbT.toString());
                }
                if (strArrSplit2 != null && !strArrSplit2[0].toLowerCase(Locale.ENGLISH).contains("trans")) {
                    PrintStream printStream2 = System.out;
                    StringBuilder sbT2 = a.t(rFUF.NwmXPFYBPnz, str2, " ; first text=");
                    sbT2.append(strArrSplit2[0]);
                    printStream2.println(sbT2.toString());
                    strArrSplit2 = null;
                }
                if (strArrSplit2 != null) {
                    int length = strArrSplit.length;
                    int i3 = 1;
                    while (true) {
                        if (i3 >= strArrSplit.length) {
                            break;
                        }
                        if (strArrSplit[i3].replace("\n", "").replace(str3, "").split("\t", -1)[2].trim().equals("")) {
                            length = i3;
                            break;
                        }
                        i3++;
                    }
                    int i4 = 1;
                    while (true) {
                        if (i4 >= strArrSplit2.length || i4 >= 100) {
                            break;
                        }
                        String strTrim = strArrSplit2[i4].replace("\n", "").replace(str3, "").trim();
                        if (strTrim.equals("")) {
                            if (length != i4) {
                                break;
                            }
                        } else if (strTrim.contains("\t")) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                }
                strArr = strArrSplit2;
            } else {
                strArr = null;
            }
        }
        int i5 = 1;
        while (i5 < strArrSplit.length) {
            String strReplace = strArrSplit[i5].replace("\n", "");
            strArrSplit[i5] = strReplace;
            String strReplace2 = strReplace.replace(str3, "");
            strArrSplit[i5] = strReplace2;
            String[] strArrSplit3 = strReplace2.split("\t", i2);
            String strTrim2 = strArrSplit3.length >= 8 ? strArrSplit3[7].trim() : "";
            String strReplace3 = strArrSplit3[2];
            if (Settings.h() == 2) {
                strReplace3 = strArrSplit3[3];
            } else if (strArr != null) {
                strReplace3 = strArr[i5].replace("\n", "").replace(str3, "");
                if (strReplace3.trim().equals("")) {
                    System.out.println("WARNING: conversation localized file blank line, " + str2 + DkgvDLHsdXPkn.zKeeNrdhIMumFY + i5);
                    strReplace3 = strArrSplit3[2];
                }
            }
            ConversationSentence conversationSentence = new ConversationSentence(strArrSplit3[0].trim(), strArrSplit3[1], strReplace3, strArrSplit3[4].trim(), strTrim2);
            conversationSentence.conditions = new ConditionsSet(strArrSplit3[5]);
            conversationSentence.actions = new ActionsSet(strArrSplit3[6]);
            this.conversationSentences.add(conversationSentence);
            i5++;
            i2 = -1;
        }
    }

    public final ConversationAnswers a(int i2) {
        ConversationAnswers conversationAnswers = new ConversationAnswers();
        for (ConversationSentence conversationSentence : this.conversationSentences) {
            if (conversationSentence.ID == i2 && conversationSentence.type == ConversationSentence.SentenceType.f3350c && conversationSentence.a().booleanValue()) {
                conversationAnswers.text[conversationAnswers.length] = FDUtils.f(conversationSentence.b());
                int[] iArr = conversationAnswers.destination;
                int i3 = conversationAnswers.length;
                int i4 = conversationSentence.next;
                iArr[i3] = i4;
                conversationAnswers.actions[i3] = conversationSentence.actions;
                if (i4 == 0) {
                    conversationAnswers.text[i3] = conversationAnswers.text[conversationAnswers.length] + " [BLUE](" + GameString.b("LEAVE", false) + ")[BLACK]";
                }
                int i5 = conversationAnswers.length + 1;
                conversationAnswers.length = i5;
                if (i5 == 4) {
                    break;
                }
            }
        }
        return conversationAnswers;
    }

    public final ConversationQuestion b(int i2) {
        for (ConversationSentence conversationSentence : this.conversationSentences) {
            if (conversationSentence.ID == i2 && conversationSentence.type == ConversationSentence.SentenceType.f3349b && conversationSentence.a().booleanValue()) {
                ConversationQuestion conversationQuestion = new ConversationQuestion();
                conversationQuestion.text = FDUtils.f(conversationSentence.b());
                conversationQuestion.destination = conversationSentence.next;
                conversationQuestion.newOwner = conversationSentence.newQuestionOwner;
                conversationQuestion.actions = conversationSentence.actions;
                return conversationQuestion;
            }
        }
        return null;
    }

    public final boolean c(int i2) {
        for (ConversationSentence conversationSentence : this.conversationSentences) {
            if (conversationSentence.ID == i2 && conversationSentence.type == ConversationSentence.SentenceType.f3349b) {
                return true;
            }
        }
        return false;
    }
}

package net.fdgames.Rules;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Locale;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class RewardTable {
    String id;
    public ArrayList<Reward> rewards = new ArrayList<>();

    public RewardTable(String str) {
        this.id = str;
        String[] strArrSplit = Gdx.files.internal("data/rules/rewards/" + str + ".txt").readString().split("\n");
        for (int i2 = 1; i2 < strArrSplit.length; i2++) {
            String strReplace = strArrSplit[i2].replace("\n", "");
            strArrSplit[i2] = strReplace;
            String strReplace2 = strReplace.replace("\r", "");
            strArrSplit[i2] = strReplace2;
            String[] strArrSplit2 = strReplace2.split("\t", -1);
            Reward reward = new Reward();
            String str2 = strArrSplit2[0];
            Locale locale = Locale.ENGLISH;
            reward.level = Integer.parseInt(str2.toLowerCase(locale).trim());
            reward.item_id = Integer.parseInt(strArrSplit2[1].toLowerCase(locale).trim());
            this.rewards.add(reward);
        }
    }

    public final String a(int i2) {
        for (Reward reward : this.rewards) {
            if (reward.level == i2) {
                int i3 = reward.item_id;
                if (i3 > 0) {
                    return Rules.g(i3);
                }
                if (i3 >= 0) {
                    return "";
                }
                return "" + (-reward.item_id) + " " + GameString.b("GOLD_COINS", false);
            }
        }
        return "";
    }

    public final void b(int i2) {
        for (Reward reward : this.rewards) {
            if (reward.level == i2) {
                if (reward.item_id > 0) {
                    GameData.v().backpack.a(reward.item_id, 1);
                }
                if (reward.item_id < 0) {
                    GameData.v().player.B1(-reward.item_id);
                    GameData.v().log.c(-reward.item_id);
                    return;
                }
                return;
            }
        }
    }
}

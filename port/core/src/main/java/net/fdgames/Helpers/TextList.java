package net.fdgames.Helpers;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class TextList {
    public static ArrayList a() {
        ArrayList arrayList = new ArrayList();
        for (String str : Gdx.files.internal("data/sprites/staticNPC/list.txt").readString().split("\n")) {
            String strTrim = str.trim();
            if (!strTrim.equals("")) {
                arrayList.add(strTrim);
            }
        }
        return arrayList;
    }
}

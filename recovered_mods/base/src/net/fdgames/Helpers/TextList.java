package net.fdgames.Helpers;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
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

package net.fdgames.assets;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Intro {
    public ArrayList<IntroStage> stages;

    public final void a(String str, String str2, float f2, Color color) {
        this.stages.add(new IntroStage(str, str2, f2, color));
    }
}

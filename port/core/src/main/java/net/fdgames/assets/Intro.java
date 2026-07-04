package net.fdgames.assets;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Intro {
    public ArrayList<IntroStage> stages;

    public final void a(String str, String str2, float f2, Color color) {
        this.stages.add(new IntroStage(str, str2, f2, color));
    }
}

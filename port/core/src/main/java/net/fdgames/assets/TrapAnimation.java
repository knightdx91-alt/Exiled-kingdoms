package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Animation;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class TrapAnimation {
    public Animation animation;
    public String name;

    public TrapAnimation(String str) {
        this.name = str;
        this.animation = AnimationSet.b(new Texture(Gdx.files.internal("data/sprites/traps/" + str + ".png")), 1, 1, 4, 64, VertexAttributes.Usage.Tangent, Animation.PlayMode.NORMAL, 0.1f);
    }
}

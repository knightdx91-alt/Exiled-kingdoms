package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class EffectAnimation {
    public Animation animation;
    public String name;

    public EffectAnimation(String str) {
        this.name = str;
        this.animation = AnimationSet.b(new Texture(Gdx.files.internal("data/sprites/effects/" + str + ".png")), 1, 1, 12, 140, 140, Animation.PlayMode.LOOP, 0.1f);
    }
}

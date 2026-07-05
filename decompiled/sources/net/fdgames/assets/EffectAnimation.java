package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class EffectAnimation {
    public Animation animation;
    public String name;

    public EffectAnimation(String str) {
        this.name = str;
        this.animation = AnimationSet.b(new Texture(Gdx.files.internal("data/sprites/effects/" + str + ".png")), 1, 1, 12, 140, 140, Animation.PlayMode.LOOP, 0.1f);
    }
}

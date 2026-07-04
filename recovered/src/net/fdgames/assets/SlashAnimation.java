package net.fdgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.EnumMap;
import net.fdgames.GameEntities.MapActor;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SlashAnimation {
    private EnumMap<MapActor.Facing, Animation> facingAnimations;
    public String name = "slash";
    private Texture texture = new Texture(Gdx.files.internal("data/sprites/composite/slash/slash.png"));

    public SlashAnimation() {
        EnumMap<MapActor.Facing, Animation> enumMap = new EnumMap<>(MapActor.Facing.class);
        this.facingAnimations = enumMap;
        MapActor.Facing facing = MapActor.Facing.f3085c;
        Texture texture = this.texture;
        Animation.PlayMode playMode = Animation.PlayMode.NORMAL;
        enumMap.put(facing, AnimationSet.b(texture, 1, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3089g, AnimationSet.b(this.texture, 1, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3084b, AnimationSet.b(this.texture, 2, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3090h, AnimationSet.b(this.texture, 2, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3086d, AnimationSet.b(this.texture, 3, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3088f, AnimationSet.b(this.texture, 3, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3087e, AnimationSet.b(this.texture, 4, 1, 5, 140, 140, playMode, 0.35f));
        this.facingAnimations.put(MapActor.Facing.f3083a, AnimationSet.b(this.texture, 5, 1, 5, 140, 140, playMode, 0.35f));
    }

    public final void a() {
        Texture texture = this.texture;
        if (texture != null) {
            texture.dispose();
        }
        this.texture = null;
    }

    public final Animation b(MapActor.Facing facing) {
        return this.facingAnimations.get(facing);
    }
}

package net.fdgames.GameLevel;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.fdgames.GameWorld.GameData;
import net.fdgames.assets.GameAssets;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SpriteEffect {
    public float creationTime;
    public float destructionTime;
    public String effectName;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3105x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3106y;

    public SpriteEffect() {
    }

    public final TextureRegion a() {
        return (TextureRegion) GameAssets.j(this.effectName).getKeyFrame(Math.max(GameLevel.b() - this.creationTime, 0.0f));
    }

    public SpriteEffect(float f2, int i2, int i3, String str) {
        this.effectName = str;
        this.f3105x = i2;
        this.f3106y = i3;
        float fU = GameData.v().u();
        this.creationTime = fU;
        this.destructionTime = fU + f2;
    }
}

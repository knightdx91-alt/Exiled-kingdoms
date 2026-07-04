package net.fdgames.assets;

import a.a;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.fdgames.Helpers.GameString;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class IntroStage {
    public Color color;
    public Direction direction;
    public float duration;
    public String textTag;
    public float text_right;
    public float text_up;
    public Texture texture;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public IntroStage(String str, String str2, float f2, Color color) {
        Direction direction = Direction.RIGHT;
        Texture texture = new Texture(a.l("data/ui/intro/", str, ".png"));
        this.texture = texture;
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        texture.setFilter(textureFilter, textureFilter);
        this.textTag = str2;
        this.direction = direction;
        this.duration = f2;
        this.color = new Color(color);
    }

    public final void a() {
        this.texture.dispose();
    }

    public final TextureRegionDrawable b() {
        return new TextureRegionDrawable(new TextureRegion(this.texture));
    }

    public final String c() {
        return GameString.b(this.textTag, false);
    }
}

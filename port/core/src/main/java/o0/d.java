package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CharacterPreview.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class d extends Image {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f3466b = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Character f3467a;

    public d(Character character) {
        this.f3467a = character;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Image, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (ExiledKingdoms.f3378h) {
            f3466b = 0.7f;
        }
        Character character = this.f3467a;
        MapActor.ActorState actorStateD0 = character.d0();
        MapActor.Facing facing = character.facing;
        float f3 = character.stateRelativeTime;
        character.q0(MapActor.ActorState.IDLE);
        character.facing = MapActor.Facing.LD;
        character.stateRelativeTime = 0.0f;
        a.b<TextureRegion> it = character.Q().iterator();
        while (it.hasNext()) {
            TextureRegion next = it.next();
            float x2 = getX() - (f3466b * 90.0f);
            float y2 = getY();
            float f4 = f3466b * 360.0f;
            batch.draw(next, x2, y2, f4, f4);
        }
        character.q0(actorStateD0);
        character.facing = facing;
        character.stateRelativeTime = f3;
        validate();
    }
}

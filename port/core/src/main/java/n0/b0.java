package n0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameWorld.GameData;

/* JADX INFO: compiled from: GameLogConsole.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b0 extends Table implements a.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static float f2536b = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Color f2537c = Color.WHITE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Label f2538a;

    private static float a() {
        if (GameData.v().log == null) {
            return 0.0f;
        }
        float fG = 1.0f / (GameData.v().log.g() / 2.2f);
        if (fG < 0.35f) {
            return 0.0f;
        }
        return fG;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public final void draw(Batch batch, float f2) {
        if (GameData.v().log == null || a() <= 0.0f || GameData.v().log.lastLines.equals("")) {
            return;
        }
        Label label = this.f2538a;
        label.setText(GameData.v().log.lastLines);
        Color color = f2537c;
        label.setColor(color.f1680r, color.f1679g, color.f1678b, a());
        super.draw(batch, f2);
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean fling(float f2, float f3, int i2) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean longPress(float f2, float f3) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean pan(float f2, float f3, float f4, float f5) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean panStop(float f2, float f3, int i2, int i3) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean pinch(a0.q qVar, a0.q qVar2, a0.q qVar3, a0.q qVar4) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final void pinchStop() {
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean tap(float f2, float f3, int i2, int i3) {
        if (i2 != 2 || f2 <= getX() || f2 >= getX() + 400.0f || f3 >= 100.0f || GameLevel.l()) {
            return false;
        }
        GameData.v().player.V1(-1, 1);
        c0.b().c();
        return true;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean touchDown(float f2, float f3, int i2, int i3) {
        return false;
    }

    @Override // com.badlogic.gdx.input.GestureDetector.c
    public final boolean zoom(float f2, float f3) {
        return false;
    }
}

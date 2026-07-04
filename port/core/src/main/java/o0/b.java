package o0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.assets.Assets;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: CharacterCriticalDamageDescription.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b extends Table {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f3463a = Math.min(Gdx.graphics.getHeight() / 720.0f, Gdx.graphics.getWidth() / 1280.0f);

    public final void a(CharacterSheet characterSheet) {
        clear();
        if (ExiledKingdoms.f3378h) {
            f3463a = 1.0f;
        }
        pad(0.0f).center().align(8);
        float fMax = characterSheet.effects.fury.booleanValue() ? Math.max(characterSheet.effects.furyMultiplier, 1.0f) : 1.0f;
        StringBuilder sb = new StringBuilder("");
        sb.append(characterSheet.n());
        sb.append("% (");
        sb.append((int) ((characterSheet.v() + characterSheet.B(true)) * fMax));
        sb.append("-");
        Label label = new Label(a.a.p(sb, (int) ((characterSheet.v() + characterSheet.y(true)) * fMax), ")"), Assets.g(), "menuLabelStyle");
        label.setFontScale(f3463a);
        add(label);
    }
}

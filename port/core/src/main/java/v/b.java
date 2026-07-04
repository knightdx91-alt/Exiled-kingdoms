package v;

import com.badlogic.gdx.math.Rectangle;
import u.f;

/* JADX INFO: compiled from: RectangleMapObject.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Rectangle f4067b;

    public b() {
        this(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public final Rectangle b() {
        return this.f4067b;
    }

    public b(float f2, float f3, float f4, float f5) {
        this.f4067b = new Rectangle (f2, f3, f4, f5);
    }
}

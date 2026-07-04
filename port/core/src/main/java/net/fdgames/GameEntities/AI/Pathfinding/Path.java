package net.fdgames.GameEntities.AI.Pathfinding;

import com.badlogic.gdx.utils.Array;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Path {
    private Array<Step> steps = new Array <>();

    public final void a(int i2, int i3) {
        for (int i4 = this.steps.f1750b - 1; i4 >= 0; i4--) {
            if (this.steps.get(i4).f2989x == i2 && this.steps.get(i4).f2990y == i3) {
                this.steps.get(i4).b();
            }
        }
    }

    public final void b(int i2, int i3) {
        this.steps.a(new Step(i2, i3));
    }

    public final void c() {
        this.steps.clear();
    }

    public final Coords d() {
        for (int i2 = this.steps.f1750b - 1; i2 >= 0; i2--) {
            if (!this.steps.get(i2).a()) {
                return new Coords(this.steps.get(i2).f2989x, this.steps.get(i2).f2990y);
            }
        }
        return null;
    }

    public final int e() {
        return this.steps.f1750b;
    }

    public final Step f(int i2) {
        return this.steps.get(i2);
    }

    public final void g(int i2, int i3) {
        this.steps.a(new Step(i2, i3));
    }

    public final void h(int i2) {
        this.steps.o(i2);
    }

    public final void i() {
        Array<Step> aVar = new Array <>();
        int i2 = 0;
        while (true) {
            Array<Step> aVar2 = this.steps;
            if (i2 >= aVar2.f1750b) {
                this.steps = aVar;
                return;
            } else {
                if (!aVar2.get(i2).a()) {
                    aVar.a(this.steps.get(i2));
                }
                i2++;
            }
        }
    }
}

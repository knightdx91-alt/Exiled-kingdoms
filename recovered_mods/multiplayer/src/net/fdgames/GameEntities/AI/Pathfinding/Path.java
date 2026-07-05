package net.fdgames.GameEntities.AI.Pathfinding;

import com.badlogic.gdx.utils.a;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Path {
    private a<Step> steps = new a<>();

    public final void a(int i2, int i3) {
        for (int i4 = this.steps.f2517c - 1; i4 >= 0; i4--) {
            if (this.steps.get(i4).f3204x == i2 && this.steps.get(i4).f3205y == i3) {
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
        for (int i2 = this.steps.f2517c - 1; i2 >= 0; i2--) {
            if (!this.steps.get(i2).a()) {
                return new Coords(this.steps.get(i2).f3204x, this.steps.get(i2).f3205y);
            }
        }
        return null;
    }

    public final int e() {
        return this.steps.f2517c;
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
        a<Step> aVar = new a<>();
        int i2 = 0;
        while (true) {
            a<Step> aVar2 = this.steps;
            if (i2 >= aVar2.f2517c) {
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

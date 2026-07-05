package net.fdgames.GameEntities.AI.Pathfinding;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Step {
    private boolean visited = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3204x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3205y;

    public Step() {
    }

    public Step(int i2, int i3) {
        this.f3204x = i2;
        this.f3205y = i3;
    }

    public final boolean a() {
        return this.visited;
    }

    public final void b() {
        this.visited = true;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Step)) {
            return false;
        }
        Step step = (Step) obj;
        return step.f3204x == this.f3204x && step.f3205y == this.f3205y;
    }

    public final int hashCode() {
        return this.f3204x * this.f3205y;
    }
}

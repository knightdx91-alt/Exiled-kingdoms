package net.fdgames.GameEntities.AI.Pathfinding;

/* JADX INFO: loaded from: /tmp/tmp.I6QUuWp8g5/classes.dex */
public class Step {
    private boolean visited = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2989x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2990y;

    public Step(int i2, int i3) {
        this.f2989x = i2;
        this.f2990y = i3;
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
        return step.f2989x == this.f2989x && step.f2990y == this.f2990y;
    }

    public final int hashCode() {
        return this.f2989x * this.f2990y;
    }

    public Step() {
    }
}

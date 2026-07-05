package b0;

import java.io.Serializable;

/* JADX INFO: compiled from: Ray.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class b implements Serializable {
    private static final long serialVersionUID = -620692054835390878L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.badlogic.gdx.math.a f1416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.badlogic.gdx.math.a f1417b;

    static {
        new com.badlogic.gdx.math.a();
    }

    public b() {
        this.f1416a = new com.badlogic.gdx.math.a();
        this.f1417b = new com.badlogic.gdx.math.a();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != b.class) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1417b.equals(bVar.f1417b) && this.f1416a.equals(bVar.f1416a);
    }

    public final int hashCode() {
        return this.f1416a.hashCode() + ((this.f1417b.hashCode() + 73) * 73);
    }

    public final String toString() {
        return "ray [" + this.f1416a + ":" + this.f1417b + "]";
    }

    public b(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        com.badlogic.gdx.math.a aVar3 = new com.badlogic.gdx.math.a();
        this.f1416a = aVar3;
        com.badlogic.gdx.math.a aVar4 = new com.badlogic.gdx.math.a();
        this.f1417b = aVar4;
        aVar3.u(aVar);
        aVar4.u(aVar2);
        aVar4.n();
    }
}

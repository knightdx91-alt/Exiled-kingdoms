package a0;

import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;

/* JADX INFO: compiled from: CumulativeDistribution.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.badlogic.gdx.utils.a<c<T>.a> f34a = new com.badlogic.gdx.utils.a<>(false, 10, a.class);

    /* JADX INFO: compiled from: CumulativeDistribution.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MeshSpawnShapeValue.Triangle f35a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f36b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f37c;

        public a() {
            throw null;
        }
    }

    public final void a(MeshSpawnShapeValue.Triangle triangle, float f2) {
        com.badlogic.gdx.utils.a<c<T>.a> aVar = this.f34a;
        c<T>.a aVar2 = new a();
        aVar2.f35a = triangle;
        aVar2.f36b = 0.0f;
        aVar2.f37c = f2;
        aVar.a(aVar2);
    }

    public final void b() {
        this.f34a.clear();
    }

    public final void c() {
        com.badlogic.gdx.utils.a<c<T>.a> aVar;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = 0;
        while (true) {
            aVar = this.f34a;
            if (i2 >= aVar.f1750b) {
                break;
            }
            f3 += aVar.f1749a[i2].f37c;
            i2++;
        }
        for (int i3 = 0; i3 < aVar.f1750b; i3++) {
            c<T>.a[] aVarArr = aVar.f1749a;
            f2 += aVarArr[i3].f37c / f3;
            aVarArr[i3].f36b = f2;
        }
    }

    public final T d() {
        float fNextFloat = j.f69a.nextFloat();
        com.badlogic.gdx.utils.a<c<T>.a> aVar = this.f34a;
        int i2 = aVar.f1750b - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = ((i2 - i3) / 2) + i3;
            float f2 = aVar.f1749a[i4].f36b;
            if (fNextFloat >= f2) {
                if (fNextFloat <= f2) {
                    break;
                }
                i3 = i4 + 1;
            } else {
                i2 = i4 - 1;
            }
        }
        return (T) aVar.f1749a[i3].f35a;
    }
}

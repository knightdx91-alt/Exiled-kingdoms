package net.fdgames.GameEntities.AI.Pathfinding;

import com.badlogic.gdx.utils.a;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class AStarPathFinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int[][] f3196a;
    private com.badlogic.gdx.utils.a<a> closed = new com.badlogic.gdx.utils.a<>();
    private b open = new b();
    final Coords originTile = new Coords();
    final Coords targetTile = new Coords();
    private int maxSearchDistance = 12;
    private a[][] nodes = (a[][]) Array.newInstance((Class<?>) a.class, y0.b.S(), y0.b.R());

    private class a implements Comparable<a> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3197b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3198c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f3199d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private a f3200e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f3201f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f3202g;

        public a(int i2, int i3) {
            this.f3197b = i2;
            this.f3198c = i3;
        }

        @Override // java.lang.Comparable
        public final int compareTo(a aVar) {
            a aVar2 = aVar;
            float f2 = this.f3201f + this.f3199d;
            float f3 = aVar2.f3201f + aVar2.f3199d;
            if (f2 < f3) {
                return -1;
            }
            return f2 > f3 ? 1 : 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return aVar.f3197b == this.f3197b && aVar.f3198c == this.f3198c;
        }

        public final int hashCode() {
            return this.f3197b * this.f3198c;
        }

        public final int i(a aVar) {
            int i2 = aVar.f3202g + 1;
            this.f3202g = i2;
            this.f3200e = aVar;
            return i2;
        }
    }

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LinkedList<a> f3203a = new LinkedList<>();

        b() {
        }

        public final void a(a aVar) {
            LinkedList<a> linkedList = this.f3203a;
            linkedList.add(aVar);
            Collections.sort(linkedList);
        }

        public final void b() {
            this.f3203a.clear();
        }

        public final boolean c(Object obj) {
            return this.f3203a.contains(obj);
        }

        public final Object d() {
            return this.f3203a.get(0);
        }

        public final void e(Object obj) {
            this.f3203a.remove(obj);
        }

        public final int f() {
            return this.f3203a.size();
        }
    }

    public AStarPathFinder() {
        for (int i2 = 0; i2 < y0.b.S(); i2++) {
            for (int i3 = 0; i3 < y0.b.R(); i3++) {
                this.nodes[i2][i3] = new a(i2, i3);
            }
        }
    }

    public final Path a(int i2, int i3, int i4, int i5) {
        a aVar;
        Path path = new Path();
        f3196a = y0.b.P().o();
        Coords coords = this.originTile;
        coords.f3508x = i2 / 32;
        coords.f3509y = i3 / 32;
        Coords coords2 = this.targetTile;
        int i6 = i4 / 32;
        int i7 = i5 / 32;
        coords2.f3508x = i6;
        coords2.f3509y = i7;
        int i8 = coords.f3508x;
        int i9 = coords.f3509y;
        a.b<Coords> it = y0.b.P().T(i6, i7).iterator();
        int i10 = 999;
        Coords coords3 = null;
        while (it.hasNext()) {
            Coords next = it.next();
            int iR = y0.b.r(i8, i9, next.f3508x, next.f3509y);
            if (f3196a[next.f3508x][next.f3509y] > 0) {
                iR += 10;
            }
            if (y0.b.P().K(next.f3508x, next.f3509y)) {
                iR += 20;
            }
            if (iR < i10) {
                coords3 = next;
                i10 = iR;
            }
        }
        if (coords3 == null) {
            return null;
        }
        Coords coords4 = this.originTile;
        int i11 = coords4.f3508x;
        int i12 = coords4.f3509y;
        int i13 = coords3.f3508x;
        int i14 = coords3.f3509y;
        this.nodes[i11][i12].f3199d = 0.0f;
        this.nodes[i11][i12].f3202g = 0;
        this.closed.clear();
        this.open.b();
        this.open.a(this.nodes[i11][i12]);
        this.nodes[i13][i14].f3200e = null;
        int iMax = 0;
        while (iMax < this.maxSearchDistance && this.open.f() != 0 && (aVar = (a) this.open.d()) != this.nodes[i13][i14]) {
            this.open.e(aVar);
            this.closed.a(aVar);
            a.b<Coords> it2 = y0.b.P().T(aVar.f3197b, aVar.f3198c).iterator();
            while (it2.hasNext()) {
                Coords next2 = it2.next();
                int i15 = next2.f3508x;
                int i16 = next2.f3509y;
                float f2 = aVar.f3199d;
                int unused = aVar.f3197b;
                float f3 = f2 + (f3196a[i15][i16] == 0 ? 1.0f : y0.b.P().K(i15, i16) ? 20.0f : 10.0f);
                a aVar2 = this.nodes[i15][i16];
                if (f3 < aVar2.f3199d) {
                    if (this.open.c(aVar2)) {
                        this.open.e(aVar2);
                    }
                    if (this.closed.e(aVar2, false)) {
                        this.closed.q(aVar2, false);
                    }
                }
                if (!this.open.c(aVar2) && !this.closed.e(aVar2, false)) {
                    aVar2.f3199d = f3;
                    aVar2.f3201f = y0.b.r(i15, i16, i13, i14);
                    iMax = Math.max(iMax, aVar2.i(aVar));
                    this.open.a(aVar2);
                }
            }
        }
        if (this.nodes[i13][i14].f3200e == null) {
            return null;
        }
        for (a aVar3 = this.nodes[i13][i14]; aVar3 != this.nodes[i11][i12]; aVar3 = aVar3.f3200e) {
            Coords coordsU = y0.b.u(aVar3.f3197b, aVar3.f3198c);
            path.g(coordsU.f3508x, coordsU.f3509y);
        }
        return path;
    }
}

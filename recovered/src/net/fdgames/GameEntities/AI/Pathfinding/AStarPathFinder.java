package net.fdgames.GameEntities.AI.Pathfinding;

import com.badlogic.gdx.utils.a;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import net.fdgames.TiledMap.Objects.Coords;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class AStarPathFinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int[][] f2981a;
    private com.badlogic.gdx.utils.a<a> closed = new com.badlogic.gdx.utils.a<>();
    private b open = new b();
    final Coords originTile = new Coords();
    final Coords targetTile = new Coords();
    private int maxSearchDistance = 12;
    private a[][] nodes = (a[][]) Array.newInstance((Class<?>) a.class, m0.b.S(), m0.b.R());

    private class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2982a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2983b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f2984c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private a f2985d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f2986e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f2987f;

        public a(int i2, int i3) {
            this.f2982a = i2;
            this.f2983b = i3;
        }

        @Override // java.lang.Comparable
        public final int compareTo(a aVar) {
            a aVar2 = aVar;
            float f2 = this.f2986e + this.f2984c;
            float f3 = aVar2.f2986e + aVar2.f2984c;
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
            return aVar.f2982a == this.f2982a && aVar.f2983b == this.f2983b;
        }

        public final int hashCode() {
            return this.f2982a * this.f2983b;
        }

        public final int i(a aVar) {
            int i2 = aVar.f2987f + 1;
            this.f2987f = i2;
            this.f2985d = aVar;
            return i2;
        }
    }

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LinkedList<a> f2988a = new LinkedList<>();

        b() {
        }

        public final void a(a aVar) {
            LinkedList<a> linkedList = this.f2988a;
            linkedList.add(aVar);
            Collections.sort(linkedList);
        }

        public final void b() {
            this.f2988a.clear();
        }

        public final boolean c(Object obj) {
            return this.f2988a.contains(obj);
        }

        public final Object d() {
            return this.f2988a.get(0);
        }

        public final void e(Object obj) {
            this.f2988a.remove(obj);
        }

        public final int f() {
            return this.f2988a.size();
        }
    }

    public AStarPathFinder() {
        for (int i2 = 0; i2 < m0.b.S(); i2++) {
            for (int i3 = 0; i3 < m0.b.R(); i3++) {
                this.nodes[i2][i3] = new a(i2, i3);
            }
        }
    }

    public final Path a(int i2, int i3, int i4, int i5) {
        a aVar;
        Path path = new Path();
        f2981a = m0.b.P().o();
        Coords coords = this.originTile;
        coords.f3287x = i2 / 32;
        coords.f3288y = i3 / 32;
        Coords coords2 = this.targetTile;
        int i6 = i4 / 32;
        int i7 = i5 / 32;
        coords2.f3287x = i6;
        coords2.f3288y = i7;
        int i8 = coords.f3287x;
        int i9 = coords.f3288y;
        a.b<Coords> it = m0.b.P().T(i6, i7).iterator();
        int i10 = 999;
        Coords coords3 = null;
        while (it.hasNext()) {
            Coords next = it.next();
            int iR = m0.b.r(i8, i9, next.f3287x, next.f3288y);
            if (f2981a[next.f3287x][next.f3288y] > 0) {
                iR += 10;
            }
            if (m0.b.P().K(next.f3287x, next.f3288y)) {
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
        int i11 = coords4.f3287x;
        int i12 = coords4.f3288y;
        int i13 = coords3.f3287x;
        int i14 = coords3.f3288y;
        this.nodes[i11][i12].f2984c = 0.0f;
        this.nodes[i11][i12].f2987f = 0;
        this.closed.clear();
        this.open.b();
        this.open.a(this.nodes[i11][i12]);
        this.nodes[i13][i14].f2985d = null;
        int iMax = 0;
        while (iMax < this.maxSearchDistance && this.open.f() != 0 && (aVar = (a) this.open.d()) != this.nodes[i13][i14]) {
            this.open.e(aVar);
            this.closed.a(aVar);
            a.b<Coords> it2 = m0.b.P().T(aVar.f2982a, aVar.f2983b).iterator();
            while (it2.hasNext()) {
                Coords next2 = it2.next();
                int i15 = next2.f3287x;
                int i16 = next2.f3288y;
                float f2 = aVar.f2984c;
                int unused = aVar.f2982a;
                float f3 = f2 + (f2981a[i15][i16] == 0 ? 1.0f : m0.b.P().K(i15, i16) ? 20.0f : 10.0f);
                a aVar2 = this.nodes[i15][i16];
                if (f3 < aVar2.f2984c) {
                    if (this.open.c(aVar2)) {
                        this.open.e(aVar2);
                    }
                    if (this.closed.e(aVar2, false)) {
                        this.closed.q(aVar2, false);
                    }
                }
                if (!this.open.c(aVar2) && !this.closed.e(aVar2, false)) {
                    aVar2.f2984c = f3;
                    aVar2.f2986e = m0.b.r(i15, i16, i13, i14);
                    iMax = Math.max(iMax, aVar2.i(aVar));
                    this.open.a(aVar2);
                }
            }
        }
        if (this.nodes[i13][i14].f2985d == null) {
            return null;
        }
        for (a aVar3 = this.nodes[i13][i14]; aVar3 != this.nodes[i11][i12]; aVar3 = aVar3.f2985d) {
            Coords coordsU = m0.b.u(aVar3.f2982a, aVar3.f2983b);
            path.g(coordsU.f3287x, coordsU.f3288y);
        }
        return path;
    }
}

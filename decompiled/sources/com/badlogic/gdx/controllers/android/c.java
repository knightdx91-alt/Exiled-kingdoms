package com.badlogic.gdx.controllers.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.p;

/* JADX INFO: compiled from: AndroidControllers.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class c implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ AndroidControllers f1641b;

    c(AndroidControllers androidControllers) {
        this.f1641b = androidControllers;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.f1641b.f1628e) {
            try {
                a.b it = this.f1641b.f1628e.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    int i2 = bVar.f1638b;
                    if (i2 == 0) {
                        p pVar = bVar.f1637a.f1631b;
                        int i3 = bVar.f1639c;
                        pVar.e(i3, i3);
                        a.b it2 = this.f1641b.f1627d.iterator();
                        while (it2.hasNext()) {
                            ((ControllerListener) it2.next()).getClass();
                        }
                        a.b<ControllerListener> it3 = bVar.f1637a.d().iterator();
                        while (it3.hasNext()) {
                            it3.next().getClass();
                        }
                    } else if (i2 == 1) {
                        bVar.f1637a.f1631b.f(bVar.f1639c);
                        a.b it4 = this.f1641b.f1627d.iterator();
                        while (it4.hasNext()) {
                            ((ControllerListener) it4.next()).getClass();
                        }
                        a.b<ControllerListener> it5 = bVar.f1637a.d().iterator();
                        while (it5.hasNext()) {
                            it5.next().getClass();
                        }
                    } else if (i2 == 2) {
                        bVar.f1637a.f1632c[bVar.f1639c] = bVar.f1640d;
                        a.b it6 = this.f1641b.f1627d.iterator();
                        while (it6.hasNext()) {
                            ((ControllerListener) it6.next()).getClass();
                        }
                        a.b<ControllerListener> it7 = bVar.f1637a.d().iterator();
                        while (it7.hasNext()) {
                            it7.next().getClass();
                        }
                    } else if (i2 == 3) {
                        a.b it8 = this.f1641b.f1627d.iterator();
                        while (it8.hasNext()) {
                            ((ControllerListener) it8.next()).getClass();
                        }
                        a.b<ControllerListener> it9 = bVar.f1637a.d().iterator();
                        while (it9.hasNext()) {
                            it9.next().getClass();
                        }
                    } else if (i2 == 4) {
                        this.f1641b.f1626c.a(bVar.f1637a);
                        a.b it10 = this.f1641b.f1627d.iterator();
                        while (it10.hasNext()) {
                            ((ControllerListener) it10.next()).getClass();
                        }
                    } else if (i2 == 5) {
                        this.f1641b.f1626c.q(bVar.f1637a, true);
                        a.b it11 = this.f1641b.f1627d.iterator();
                        while (it11.hasNext()) {
                            ((ControllerListener) it11.next()).getClass();
                        }
                        a.b<ControllerListener> it12 = bVar.f1637a.d().iterator();
                        while (it12.hasNext()) {
                            it12.next().getClass();
                        }
                    }
                }
                this.f1641b.f1629f.freeAll(this.f1641b.f1628e);
                this.f1641b.f1628e.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        Gdx.app.postRunnable(this);
    }
}

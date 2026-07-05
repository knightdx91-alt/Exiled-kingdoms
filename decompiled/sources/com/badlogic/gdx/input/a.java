package com.badlogic.gdx.input;

import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.h;
import com.badlogic.gdx.utils.q0;

/* JADX INFO: compiled from: GestureDetector.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class a extends h {
    private boolean inTapRectangle;
    private final q initialPointer1;
    private final q initialPointer2;
    private int lastTapButton;
    private int lastTapPointer;
    private long lastTapTime;
    private float lastTapX;
    private float lastTapY;
    final c listener;
    boolean longPressFired;
    private float longPressSeconds;
    private final q0.a longPressTask;
    private long maxFlingDelay;
    private boolean panning;
    private boolean pinching;
    q pointer1;
    private final q pointer2;
    private int tapCount;
    private long tapCountInterval;
    private float tapRectangleCenterX;
    private float tapRectangleCenterY;
    private float tapRectangleHeight;
    private float tapRectangleWidth;
    private long touchDownTime;
    private final d tracker;

    /* JADX INFO: renamed from: com.badlogic.gdx.input.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GestureDetector.java */
    final class C0026a extends q0.a {
        C0026a() {
        }

        @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            if (aVar.longPressFired) {
                return;
            }
            c cVar = aVar.listener;
            q qVar = aVar.pointer1;
            aVar.longPressFired = cVar.longPress(qVar.f91a, qVar.f92b);
        }
    }

    /* JADX INFO: compiled from: GestureDetector.java */
    public static class b implements c {
        @Override // com.badlogic.gdx.input.a.c
        public boolean fling(float f2, float f3, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean longPress(float f2, float f3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean pan(float f2, float f3, float f4, float f5) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean panStop(float f2, float f3, int i2, int i3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean pinch(q qVar, q qVar2, q qVar3, q qVar4) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public void pinchStop() {
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean tap(float f2, float f3, int i2, int i3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean touchDown(float f2, float f3, int i2, int i3) {
            return false;
        }

        @Override // com.badlogic.gdx.input.a.c
        public boolean zoom(float f2, float f3) {
            return false;
        }
    }

    /* JADX INFO: compiled from: GestureDetector.java */
    public interface c {
        boolean fling(float f2, float f3, int i2);

        boolean longPress(float f2, float f3);

        boolean pan(float f2, float f3, float f4, float f5);

        boolean panStop(float f2, float f3, int i2, int i3);

        boolean pinch(q qVar, q qVar2, q qVar3, q qVar4);

        void pinchStop();

        boolean tap(float f2, float f3, int i2, int i3);

        boolean touchDown(float f2, float f3, int i2, int i3);

        boolean zoom(float f2, float f3);
    }

    /* JADX INFO: compiled from: GestureDetector.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1705b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1706c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1707d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f1708e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1709f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float[] f1710g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float[] f1711h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        long[] f1712i;

        public final void a(long j2, float f2, float f3) {
            this.f1704a = f2;
            this.f1705b = f3;
            this.f1706c = 0.0f;
            this.f1707d = 0.0f;
            this.f1709f = 0;
            for (int i2 = 0; i2 < 10; i2++) {
                this.f1710g[i2] = 0.0f;
                this.f1711h[i2] = 0.0f;
                this.f1712i[i2] = 0;
            }
            this.f1708e = j2;
        }

        public final void b(long j2, float f2, float f3) {
            float f4 = f2 - this.f1704a;
            this.f1706c = f4;
            float f5 = f3 - this.f1705b;
            this.f1707d = f5;
            this.f1704a = f2;
            this.f1705b = f3;
            long j3 = j2 - this.f1708e;
            this.f1708e = j2;
            int i2 = this.f1709f;
            int i3 = i2 % 10;
            this.f1710g[i3] = f4;
            this.f1711h[i3] = f5;
            this.f1712i[i3] = j3;
            this.f1709f = i2 + 1;
        }
    }

    public a(c cVar) {
        this(20.0f, 0.4f, 1.1f, 2.1474836E9f, cVar);
    }

    private boolean isWithinTapRectangle(float f2, float f3, float f4, float f5) {
        return Math.abs(f2 - f4) < this.tapRectangleWidth && Math.abs(f3 - f5) < this.tapRectangleHeight;
    }

    public void cancel() {
        this.longPressTask.cancel();
        this.longPressFired = true;
    }

    public void invalidateTapSquare() {
        this.inTapRectangle = false;
    }

    public boolean isLongPressed() {
        return isLongPressed(this.longPressSeconds);
    }

    public boolean isPanning() {
        return this.panning;
    }

    public void reset() {
        this.touchDownTime = 0L;
        this.panning = false;
        this.inTapRectangle = false;
        this.tracker.f1708e = 0L;
    }

    public void setLongPressSeconds(float f2) {
        this.longPressSeconds = f2;
    }

    public void setMaxFlingDelay(long j2) {
        this.maxFlingDelay = j2;
    }

    public void setTapCountInterval(float f2) {
        this.tapCountInterval = (long) (f2 * 1.0E9f);
    }

    public void setTapRectangleSize(float f2, float f3) {
        this.tapRectangleWidth = f2;
        this.tapRectangleHeight = f3;
    }

    public void setTapSquareSize(float f2) {
        setTapRectangleSize(f2, f2);
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDown(int i2, int i3, int i4, int i5) {
        return touchDown(i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDragged(int i2, int i3, int i4) {
        return touchDragged(i2, i3, i4);
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchUp(int i2, int i3, int i4, int i5) {
        return touchUp(i2, i3, i4, i5);
    }

    public a(float f2, float f3, float f4, float f5, c cVar) {
        this(f2, f2, f3, f4, f5, cVar);
    }

    public boolean isLongPressed(float f2) {
        return this.touchDownTime != 0 && System.nanoTime() - this.touchDownTime > ((long) (f2 * 1.0E9f));
    }

    public boolean touchDown(float f2, float f3, int i2, int i3) {
        if (i2 > 1) {
            return false;
        }
        if (i2 == 0) {
            q qVar = this.pointer1;
            qVar.f91a = f2;
            qVar.f92b = f3;
            long currentEventTime = Gdx.input.getCurrentEventTime();
            this.touchDownTime = currentEventTime;
            this.tracker.a(currentEventTime, f2, f3);
            if (Gdx.input.isTouched(1)) {
                this.inTapRectangle = false;
                this.pinching = true;
                this.initialPointer1.b(this.pointer1);
                this.initialPointer2.b(this.pointer2);
                this.longPressTask.cancel();
            } else {
                this.inTapRectangle = true;
                this.pinching = false;
                this.longPressFired = false;
                this.tapRectangleCenterX = f2;
                this.tapRectangleCenterY = f3;
                if (!this.longPressTask.isScheduled()) {
                    q0.b().d(this.longPressTask, this.longPressSeconds, 0.0f, 0);
                }
            }
        } else {
            q qVar2 = this.pointer2;
            qVar2.f91a = f2;
            qVar2.f92b = f3;
            this.inTapRectangle = false;
            this.pinching = true;
            this.initialPointer1.b(this.pointer1);
            this.initialPointer2.b(this.pointer2);
            this.longPressTask.cancel();
        }
        return this.listener.touchDown(f2, f3, i2, i3);
    }

    public boolean touchDragged(float f2, float f3, int i2) {
        if (i2 > 1 || this.longPressFired) {
            return false;
        }
        if (i2 == 0) {
            q qVar = this.pointer1;
            qVar.f91a = f2;
            qVar.f92b = f3;
        } else {
            q qVar2 = this.pointer2;
            qVar2.f91a = f2;
            qVar2.f92b = f3;
        }
        if (!this.pinching) {
            this.tracker.b(Gdx.input.getCurrentEventTime(), f2, f3);
            if (this.inTapRectangle && !isWithinTapRectangle(f2, f3, this.tapRectangleCenterX, this.tapRectangleCenterY)) {
                this.longPressTask.cancel();
                this.inTapRectangle = false;
            }
            if (this.inTapRectangle) {
                return false;
            }
            this.panning = true;
            c cVar = this.listener;
            d dVar = this.tracker;
            return cVar.pan(f2, f3, dVar.f1706c, dVar.f1707d);
        }
        c cVar2 = this.listener;
        if (cVar2 == null) {
            return false;
        }
        boolean zPinch = cVar2.pinch(this.initialPointer1, this.initialPointer2, this.pointer1, this.pointer2);
        c cVar3 = this.listener;
        q qVar3 = this.initialPointer1;
        q qVar4 = this.initialPointer2;
        qVar3.getClass();
        float f4 = qVar4.f91a - qVar3.f91a;
        float f5 = qVar4.f92b - qVar3.f92b;
        float fSqrt = (float) Math.sqrt((f5 * f5) + (f4 * f4));
        q qVar5 = this.pointer1;
        q qVar6 = this.pointer2;
        qVar5.getClass();
        float f6 = qVar6.f91a - qVar5.f91a;
        float f7 = qVar6.f92b - qVar5.f92b;
        return cVar3.zoom(fSqrt, (float) Math.sqrt((double) ((f7 * f7) + (f6 * f6)))) || zPinch;
    }

    public boolean touchUp(float f2, float f3, int i2, int i3) {
        if (i2 > 1) {
            return false;
        }
        if (this.inTapRectangle && !isWithinTapRectangle(f2, f3, this.tapRectangleCenterX, this.tapRectangleCenterY)) {
            this.inTapRectangle = false;
        }
        boolean z2 = this.panning;
        this.panning = false;
        this.longPressTask.cancel();
        if (this.longPressFired) {
            return false;
        }
        if (this.inTapRectangle) {
            if (this.lastTapButton != i3 || this.lastTapPointer != i2 || System.nanoTime() - this.lastTapTime > this.tapCountInterval || !isWithinTapRectangle(f2, f3, this.lastTapX, this.lastTapY)) {
                this.tapCount = 0;
            }
            this.tapCount++;
            this.lastTapTime = System.nanoTime();
            this.lastTapX = f2;
            this.lastTapY = f3;
            this.lastTapButton = i3;
            this.lastTapPointer = i2;
            this.touchDownTime = 0L;
            return this.listener.tap(f2, f3, this.tapCount, i3);
        }
        if (this.pinching) {
            this.pinching = false;
            this.listener.pinchStop();
            this.panning = true;
            if (i2 == 0) {
                d dVar = this.tracker;
                q qVar = this.pointer2;
                dVar.a(Gdx.input.getCurrentEventTime(), qVar.f91a, qVar.f92b);
            } else {
                d dVar2 = this.tracker;
                q qVar2 = this.pointer1;
                dVar2.a(Gdx.input.getCurrentEventTime(), qVar2.f91a, qVar2.f92b);
            }
            return false;
        }
        boolean zPanStop = (!z2 || this.panning) ? false : this.listener.panStop(f2, f3, i2, i3);
        long currentEventTime = Gdx.input.getCurrentEventTime();
        if (currentEventTime - this.touchDownTime <= this.maxFlingDelay) {
            this.tracker.b(currentEventTime, f2, f3);
            c cVar = this.listener;
            d dVar3 = this.tracker;
            float[] fArr = dVar3.f1710g;
            int iMin = Math.min(10, dVar3.f1709f);
            float f4 = 0.0f;
            for (int i4 = 0; i4 < iMin; i4++) {
                f4 += fArr[i4];
            }
            float f5 = f4 / iMin;
            long[] jArr = dVar3.f1712i;
            int iMin2 = Math.min(10, dVar3.f1709f);
            long j2 = 0;
            for (int i5 = 0; i5 < iMin2; i5++) {
                j2 += jArr[i5];
            }
            float f6 = (iMin2 != 0 ? j2 / ((long) iMin2) : 0L) / 1.0E9f;
            float f7 = f6 != 0.0f ? f5 / f6 : 0.0f;
            d dVar4 = this.tracker;
            float[] fArr2 = dVar4.f1711h;
            int iMin3 = Math.min(10, dVar4.f1709f);
            float f8 = 0.0f;
            for (int i6 = 0; i6 < iMin3; i6++) {
                f8 += fArr2[i6];
            }
            float f9 = f8 / iMin3;
            long[] jArr2 = dVar4.f1712i;
            int iMin4 = Math.min(10, dVar4.f1709f);
            long j3 = 0;
            for (int i7 = 0; i7 < iMin4; i7++) {
                j3 += jArr2[i7];
            }
            float f10 = (iMin4 != 0 ? j3 / ((long) iMin4) : 0L) / 1.0E9f;
            zPanStop = cVar.fling(f7, f10 != 0.0f ? f9 / f10 : 0.0f, i3) || zPanStop;
        }
        this.touchDownTime = 0L;
        return zPanStop;
    }

    public a(float f2, float f3, float f4, float f5, float f6, c cVar) {
        d dVar = new d();
        dVar.f1710g = new float[10];
        dVar.f1711h = new float[10];
        dVar.f1712i = new long[10];
        this.tracker = dVar;
        this.pointer1 = new q();
        this.pointer2 = new q();
        this.initialPointer1 = new q();
        this.initialPointer2 = new q();
        this.longPressTask = new C0026a();
        if (cVar != null) {
            this.tapRectangleWidth = f2;
            this.tapRectangleHeight = f3;
            this.tapCountInterval = (long) (f4 * 1.0E9f);
            this.longPressSeconds = f5;
            this.maxFlingDelay = (long) (f6 * 1.0E9f);
            this.listener = cVar;
            return;
        }
        throw new IllegalArgumentException("listener cannot be null.");
    }
}

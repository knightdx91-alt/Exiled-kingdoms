package com.badlogic.gdx.graphics.g2d;

import a0.j;
import com.badlogic.gdx.utils.a;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Animation<T> {
    private float animationDuration;
    private float frameDuration;
    T[] keyFrames;
    private int lastFrameNumber;
    private float lastStateTime;
    private PlayMode playMode;

    /* JADX INFO: renamed from: com.badlogic.gdx.graphics.g2d.Animation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode;

        static {
            int[] iArr = new int[PlayMode.values().length];
            $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode = iArr;
            try {
                iArr[PlayMode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[PlayMode.LOOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[PlayMode.LOOP_PINGPONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[PlayMode.LOOP_RANDOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[PlayMode.REVERSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[PlayMode.LOOP_REVERSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Animation(float f2, a<? extends T> aVar) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f2;
        Object[] objArr = (Object[]) Array.newInstance(aVar.f1749a.getClass().getComponentType(), aVar.f1750b);
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = aVar.get(i3);
        }
        setKeyFrames(objArr);
    }

    public float getAnimationDuration() {
        return this.animationDuration;
    }

    public float getFrameDuration() {
        return this.frameDuration;
    }

    public T getKeyFrame(float f2, boolean z2) {
        PlayMode playMode;
        PlayMode playMode2;
        PlayMode playMode3 = this.playMode;
        if (z2 && (playMode3 == (playMode2 = PlayMode.NORMAL) || playMode3 == PlayMode.REVERSED)) {
            if (playMode3 == playMode2) {
                this.playMode = PlayMode.LOOP;
            } else {
                this.playMode = PlayMode.LOOP_REVERSED;
            }
        } else if (!z2 && playMode3 != PlayMode.NORMAL && playMode3 != (playMode = PlayMode.REVERSED)) {
            if (playMode3 == PlayMode.LOOP_REVERSED) {
                this.playMode = playMode;
            } else {
                this.playMode = PlayMode.LOOP;
            }
        }
        T keyFrame = getKeyFrame(f2);
        this.playMode = playMode3;
        return keyFrame;
    }

    public int getKeyFrameIndex(float f2) {
        if (this.keyFrames.length == 1) {
            return 0;
        }
        int iMin = (int) (f2 / this.frameDuration);
        switch (AnonymousClass1.$SwitchMap$com$badlogic$gdx$graphics$g2d$Animation$PlayMode[this.playMode.ordinal()]) {
            case 1:
                iMin = Math.min(this.keyFrames.length - 1, iMin);
                break;
            case 2:
                iMin %= this.keyFrames.length;
                break;
            case 3:
                T[] tArr = this.keyFrames;
                iMin %= (tArr.length * 2) - 2;
                if (iMin >= tArr.length) {
                    iMin = (tArr.length - 2) - (iMin - tArr.length);
                }
                break;
            case 4:
                iMin = ((int) (this.lastStateTime / this.frameDuration)) == iMin ? this.lastFrameNumber : j.i(this.keyFrames.length - 1);
                break;
            case 5:
                iMin = Math.max((this.keyFrames.length - iMin) - 1, 0);
                break;
            case 6:
                T[] tArr2 = this.keyFrames;
                iMin = (tArr2.length - (iMin % tArr2.length)) - 1;
                break;
        }
        this.lastFrameNumber = iMin;
        this.lastStateTime = f2;
        return iMin;
    }

    public T[] getKeyFrames() {
        return this.keyFrames;
    }

    public PlayMode getPlayMode() {
        return this.playMode;
    }

    public boolean isAnimationFinished(float f2) {
        return this.keyFrames.length - 1 < ((int) (f2 / this.frameDuration));
    }

    public void setFrameDuration(float f2) {
        this.frameDuration = f2;
        this.animationDuration = this.keyFrames.length * f2;
    }

    protected void setKeyFrames(T... tArr) {
        this.keyFrames = tArr;
        this.animationDuration = tArr.length * this.frameDuration;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public Animation(float f2, a<? extends T> aVar, PlayMode playMode) {
        this(f2, aVar);
        setPlayMode(playMode);
    }

    public T getKeyFrame(float f2) {
        return this.keyFrames[getKeyFrameIndex(f2)];
    }

    public Animation(float f2, T... tArr) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f2;
        setKeyFrames(tArr);
    }
}

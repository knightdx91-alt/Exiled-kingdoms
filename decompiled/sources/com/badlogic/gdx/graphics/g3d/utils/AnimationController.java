package com.badlogic.gdx.graphics.g3d.utils;

import a0.j;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AnimationController extends BaseAnimationController {
    public boolean allowSameAnimation;
    protected final c0<AnimationDesc> animationPool;
    public AnimationDesc current;
    public boolean inAction;
    private boolean justChangedAnimation;
    public boolean paused;
    public AnimationDesc previous;
    public AnimationDesc queued;
    public float queuedTransitionTime;
    public float transitionCurrentTime;
    public float transitionTargetTime;

    public static class AnimationDesc {
        public Animation animation;
        public float duration;
        public AnimationListener listener;
        public int loopCount;
        public float offset;
        public float speed;
        public float time;

        protected AnimationDesc() {
        }

        protected float update(float f2) {
            int iAbs;
            AnimationListener animationListener;
            if (this.loopCount == 0 || this.animation == null) {
                return f2;
            }
            float f3 = this.speed * f2;
            if (j.f(this.duration)) {
                iAbs = 1;
            } else {
                float f4 = this.time + f3;
                this.time = f4;
                iAbs = (int) Math.abs(f4 / this.duration);
                if (this.time < 0.0f) {
                    iAbs++;
                    while (true) {
                        float f5 = this.time;
                        if (f5 >= 0.0f) {
                            break;
                        }
                        this.time = f5 + this.duration;
                    }
                }
                this.time = Math.abs(this.time % this.duration);
            }
            for (int i2 = 0; i2 < iAbs; i2++) {
                int i3 = this.loopCount;
                if (i3 > 0) {
                    this.loopCount = i3 - 1;
                }
                if (this.loopCount != 0 && (animationListener = this.listener) != null) {
                    animationListener.onLoop(this);
                }
                if (this.loopCount == 0) {
                    float f6 = this.duration;
                    float f7 = ((iAbs - 1) - i2) * f6;
                    float f8 = this.time;
                    if (f3 < 0.0f) {
                        f8 = f6 - f8;
                    }
                    float f9 = f7 + f8;
                    this.time = f3 >= 0.0f ? f6 : 0.0f;
                    AnimationListener animationListener2 = this.listener;
                    if (animationListener2 != null) {
                        animationListener2.onEnd(this);
                    }
                    return f9;
                }
            }
            return 0.0f;
        }
    }

    public interface AnimationListener {
        void onEnd(AnimationDesc animationDesc);

        void onLoop(AnimationDesc animationDesc);
    }

    public AnimationController(ModelInstance modelInstance) {
        super(modelInstance);
        this.animationPool = new c0<AnimationDesc>() { // from class: com.badlogic.gdx.graphics.g3d.utils.AnimationController.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public AnimationDesc newObject() {
                return new AnimationDesc();
            }
        };
        this.justChangedAnimation = false;
    }

    private AnimationDesc obtain(Animation animation, float f2, float f3, int i2, float f4, AnimationListener animationListener) {
        if (animation == null) {
            return null;
        }
        AnimationDesc animationDescObtain = this.animationPool.obtain();
        animationDescObtain.animation = animation;
        animationDescObtain.listener = animationListener;
        animationDescObtain.loopCount = i2;
        animationDescObtain.speed = f4;
        animationDescObtain.offset = f2;
        if (f3 < 0.0f) {
            f3 = animation.duration - f2;
        }
        animationDescObtain.duration = f3;
        animationDescObtain.time = f4 < 0.0f ? f3 : 0.0f;
        return animationDescObtain;
    }

    public AnimationDesc action(String str, int i2, float f2, AnimationListener animationListener, float f3) {
        return action(str, 0.0f, -1.0f, i2, f2, animationListener, f3);
    }

    public AnimationDesc animate(String str, float f2) {
        return animate(str, 1, 1.0f, null, f2);
    }

    public AnimationDesc queue(String str, int i2, float f2, AnimationListener animationListener, float f3) {
        return queue(str, 0.0f, -1.0f, i2, f2, animationListener, f3);
    }

    public AnimationDesc setAnimation(String str) {
        return setAnimation(str, 1, 1.0f, null);
    }

    public void update(float f2) {
        if (this.paused) {
            return;
        }
        AnimationDesc animationDesc = this.previous;
        if (animationDesc != null) {
            float f3 = this.transitionCurrentTime + f2;
            this.transitionCurrentTime = f3;
            if (f3 >= this.transitionTargetTime) {
                removeAnimation(animationDesc.animation);
                this.justChangedAnimation = true;
                this.animationPool.free(this.previous);
                this.previous = null;
            }
        }
        if (this.justChangedAnimation) {
            this.target.calculateTransforms();
            this.justChangedAnimation = false;
        }
        AnimationDesc animationDesc2 = this.current;
        if (animationDesc2 == null || animationDesc2.loopCount == 0 || animationDesc2.animation == null) {
            return;
        }
        float fUpdate = animationDesc2.update(f2);
        AnimationDesc animationDesc3 = this.queued;
        if (animationDesc3 != null) {
            this.inAction = false;
            animate(animationDesc3, this.queuedTransitionTime);
            this.queued = null;
            if (fUpdate != 0.0f) {
                update(fUpdate);
                return;
            }
            return;
        }
        AnimationDesc animationDesc4 = this.previous;
        if (animationDesc4 == null) {
            AnimationDesc animationDesc5 = this.current;
            applyAnimation(animationDesc5.animation, animationDesc5.offset + animationDesc5.time);
        } else {
            Animation animation = animationDesc4.animation;
            float f4 = animationDesc4.offset + animationDesc4.time;
            AnimationDesc animationDesc6 = this.current;
            applyAnimations(animation, f4, animationDesc6.animation, animationDesc6.offset + animationDesc6.time, this.transitionCurrentTime / this.transitionTargetTime);
        }
    }

    public AnimationDesc action(String str, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return action(obtain(str, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc animate(String str, AnimationListener animationListener, float f2) {
        return animate(str, 1, 1.0f, animationListener, f2);
    }

    public AnimationDesc queue(String str, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return queue(obtain(str, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc setAnimation(String str, int i2) {
        return setAnimation(str, i2, 1.0f, null);
    }

    protected AnimationDesc action(Animation animation, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return action(obtain(animation, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc animate(String str, int i2, AnimationListener animationListener, float f2) {
        return animate(str, i2, 1.0f, animationListener, f2);
    }

    protected AnimationDesc queue(Animation animation, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return queue(obtain(animation, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc setAnimation(String str, AnimationListener animationListener) {
        return setAnimation(str, 1, 1.0f, animationListener);
    }

    protected AnimationDesc action(AnimationDesc animationDesc, float f2) {
        if (animationDesc.loopCount >= 0) {
            AnimationDesc animationDesc2 = this.current;
            if (animationDesc2 != null && animationDesc2.loopCount != 0) {
                AnimationDesc animationDescObtain = this.inAction ? null : obtain(animationDesc2);
                this.inAction = false;
                animate(animationDesc, f2);
                this.inAction = true;
                if (animationDescObtain != null) {
                    queue(animationDescObtain, f2);
                }
            } else {
                animate(animationDesc, f2);
            }
            return animationDesc;
        }
        throw new m("An action cannot be continuous");
    }

    public AnimationDesc animate(String str, int i2, float f2, AnimationListener animationListener, float f3) {
        return animate(str, 0.0f, -1.0f, i2, f2, animationListener, f3);
    }

    protected AnimationDesc queue(AnimationDesc animationDesc, float f2) {
        AnimationDesc animationDesc2 = this.current;
        if (animationDesc2 != null && animationDesc2.loopCount != 0) {
            AnimationDesc animationDesc3 = this.queued;
            if (animationDesc3 != null) {
                this.animationPool.free(animationDesc3);
            }
            this.queued = animationDesc;
            this.queuedTransitionTime = f2;
            AnimationDesc animationDesc4 = this.current;
            if (animationDesc4.loopCount < 0) {
                animationDesc4.loopCount = 1;
            }
        } else {
            animate(animationDesc, f2);
        }
        return animationDesc;
    }

    public AnimationDesc setAnimation(String str, int i2, AnimationListener animationListener) {
        return setAnimation(str, i2, 1.0f, animationListener);
    }

    public AnimationDesc animate(String str, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return animate(obtain(str, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc setAnimation(String str, int i2, float f2, AnimationListener animationListener) {
        return setAnimation(str, 0.0f, -1.0f, i2, f2, animationListener);
    }

    protected AnimationDesc animate(Animation animation, float f2, float f3, int i2, float f4, AnimationListener animationListener, float f5) {
        return animate(obtain(animation, f2, f3, i2, f4, animationListener), f5);
    }

    public AnimationDesc setAnimation(String str, float f2, float f3, int i2, float f4, AnimationListener animationListener) {
        return setAnimation(obtain(str, f2, f3, i2, f4, animationListener));
    }

    protected AnimationDesc animate(AnimationDesc animationDesc, float f2) {
        AnimationDesc animationDesc2 = this.current;
        if (animationDesc2 != null && animationDesc2.loopCount != 0) {
            if (this.inAction) {
                queue(animationDesc, f2);
            } else if (!this.allowSameAnimation && animationDesc != null && animationDesc2.animation == animationDesc.animation) {
                animationDesc.time = animationDesc2.time;
                this.animationPool.free(animationDesc2);
                this.current = animationDesc;
            } else {
                AnimationDesc animationDesc3 = this.previous;
                if (animationDesc3 != null) {
                    removeAnimation(animationDesc3.animation);
                    this.animationPool.free(this.previous);
                }
                this.previous = this.current;
                this.current = animationDesc;
                this.transitionCurrentTime = 0.0f;
                this.transitionTargetTime = f2;
            }
        } else {
            this.current = animationDesc;
        }
        return animationDesc;
    }

    protected AnimationDesc setAnimation(Animation animation, float f2, float f3, int i2, float f4, AnimationListener animationListener) {
        return setAnimation(obtain(animation, f2, f3, i2, f4, animationListener));
    }

    protected AnimationDesc setAnimation(AnimationDesc animationDesc) {
        AnimationDesc animationDesc2 = this.current;
        if (animationDesc2 == null) {
            this.current = animationDesc;
        } else {
            if (!this.allowSameAnimation && animationDesc != null && animationDesc2.animation == animationDesc.animation) {
                animationDesc.time = animationDesc2.time;
            } else {
                removeAnimation(animationDesc2.animation);
            }
            this.animationPool.free(this.current);
            this.current = animationDesc;
        }
        this.justChangedAnimation = true;
        return animationDesc;
    }

    private AnimationDesc obtain(String str, float f2, float f3, int i2, float f4, AnimationListener animationListener) {
        if (str == null) {
            return null;
        }
        Animation animation = this.target.getAnimation(str);
        if (animation != null) {
            return obtain(animation, f2, f3, i2, f4, animationListener);
        }
        throw new m("Unknown animation: ".concat(str));
    }

    private AnimationDesc obtain(AnimationDesc animationDesc) {
        return obtain(animationDesc.animation, animationDesc.offset, animationDesc.duration, animationDesc.loopCount, animationDesc.speed, animationDesc.listener);
    }
}

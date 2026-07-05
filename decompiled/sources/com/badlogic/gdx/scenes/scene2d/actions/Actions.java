package com.badlogic.gdx.scenes.scene2d.actions;

import a0.h;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Actions {
    public static <T extends Action> T action(Class<T> cls) {
        c0 c0VarC = d0.c(cls);
        T t2 = (T) c0VarC.obtain();
        t2.setPool(c0VarC);
        return t2;
    }

    public static AddAction addAction(Action action) {
        AddAction addAction = (AddAction) action(AddAction.class);
        addAction.setAction(action);
        return addAction;
    }

    public static AddListenerAction addListener(EventListener eventListener, boolean z2) {
        AddListenerAction addListenerAction = (AddListenerAction) action(AddListenerAction.class);
        addListenerAction.setListener(eventListener);
        addListenerAction.setCapture(z2);
        return addListenerAction;
    }

    public static AfterAction after(Action action) {
        AfterAction afterAction = (AfterAction) action(AfterAction.class);
        afterAction.setAction(action);
        return afterAction;
    }

    public static AlphaAction alpha(float f2) {
        return alpha(f2, 0.0f, null);
    }

    public static ColorAction color(Color color) {
        return color(color, 0.0f, null);
    }

    public static DelayAction delay(float f2) {
        DelayAction delayAction = (DelayAction) action(DelayAction.class);
        delayAction.setDuration(f2);
        return delayAction;
    }

    public static AlphaAction fadeIn(float f2) {
        return alpha(1.0f, f2, null);
    }

    public static AlphaAction fadeOut(float f2) {
        return alpha(0.0f, f2, null);
    }

    public static RepeatAction forever(Action action) {
        RepeatAction repeatAction = (RepeatAction) action(RepeatAction.class);
        repeatAction.setCount(-1);
        repeatAction.setAction(action);
        return repeatAction;
    }

    public static VisibleAction hide() {
        return visible(false);
    }

    public static LayoutAction layout(boolean z2) {
        LayoutAction layoutAction = (LayoutAction) action(LayoutAction.class);
        layoutAction.setLayoutEnabled(z2);
        return layoutAction;
    }

    public static MoveByAction moveBy(float f2, float f3) {
        return moveBy(f2, f3, 0.0f, null);
    }

    public static MoveToAction moveTo(float f2, float f3) {
        return moveTo(f2, f3, 0.0f, null);
    }

    public static MoveToAction moveToAligned(float f2, float f3, int i2) {
        return moveToAligned(f2, f3, i2, 0.0f, null);
    }

    public static ParallelAction parallel(Action action) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        parallelAction.addAction(action);
        return parallelAction;
    }

    public static RemoveAction removeAction(Action action) {
        RemoveAction removeAction = (RemoveAction) action(RemoveAction.class);
        removeAction.setAction(action);
        return removeAction;
    }

    public static RemoveActorAction removeActor() {
        return (RemoveActorAction) action(RemoveActorAction.class);
    }

    public static RemoveListenerAction removeListener(EventListener eventListener, boolean z2) {
        RemoveListenerAction removeListenerAction = (RemoveListenerAction) action(RemoveListenerAction.class);
        removeListenerAction.setListener(eventListener);
        removeListenerAction.setCapture(z2);
        return removeListenerAction;
    }

    public static RepeatAction repeat(int i2, Action action) {
        RepeatAction repeatAction = (RepeatAction) action(RepeatAction.class);
        repeatAction.setCount(i2);
        repeatAction.setAction(action);
        return repeatAction;
    }

    public static RotateByAction rotateBy(float f2) {
        return rotateBy(f2, 0.0f, null);
    }

    public static RotateToAction rotateTo(float f2) {
        return rotateTo(f2, 0.0f, null);
    }

    public static RunnableAction run(Runnable runnable) {
        RunnableAction runnableAction = (RunnableAction) action(RunnableAction.class);
        runnableAction.setRunnable(runnable);
        return runnableAction;
    }

    public static ScaleByAction scaleBy(float f2, float f3) {
        return scaleBy(f2, f3, 0.0f, null);
    }

    public static ScaleToAction scaleTo(float f2, float f3) {
        return scaleTo(f2, f3, 0.0f, null);
    }

    public static SequenceAction sequence(Action action) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        sequenceAction.addAction(action);
        return sequenceAction;
    }

    public static VisibleAction show() {
        return visible(true);
    }

    public static SizeByAction sizeBy(float f2, float f3) {
        return sizeBy(f2, f3, 0.0f, null);
    }

    public static SizeToAction sizeTo(float f2, float f3) {
        return sizeTo(f2, f3, 0.0f, null);
    }

    public static Action targeting(Actor actor, Action action) {
        action.setTarget(actor);
        return action;
    }

    public static TimeScaleAction timeScale(float f2, Action action) {
        TimeScaleAction timeScaleAction = (TimeScaleAction) action(TimeScaleAction.class);
        timeScaleAction.setScale(f2);
        timeScaleAction.setAction(action);
        return timeScaleAction;
    }

    public static TouchableAction touchable(Touchable touchable) {
        TouchableAction touchableAction = (TouchableAction) action(TouchableAction.class);
        touchableAction.setTouchable(touchable);
        return touchableAction;
    }

    public static VisibleAction visible(boolean z2) {
        VisibleAction visibleAction = (VisibleAction) action(VisibleAction.class);
        visibleAction.setVisible(z2);
        return visibleAction;
    }

    public static AlphaAction alpha(float f2, float f3) {
        return alpha(f2, f3, null);
    }

    public static ColorAction color(Color color, float f2) {
        return color(color, f2, null);
    }

    public static AlphaAction fadeIn(float f2, h hVar) {
        AlphaAction alphaAction = (AlphaAction) action(AlphaAction.class);
        alphaAction.setAlpha(1.0f);
        alphaAction.setDuration(f2);
        alphaAction.setInterpolation(hVar);
        return alphaAction;
    }

    public static AlphaAction fadeOut(float f2, h hVar) {
        AlphaAction alphaAction = (AlphaAction) action(AlphaAction.class);
        alphaAction.setAlpha(0.0f);
        alphaAction.setDuration(f2);
        alphaAction.setInterpolation(hVar);
        return alphaAction;
    }

    public static MoveByAction moveBy(float f2, float f3, float f4) {
        return moveBy(f2, f3, f4, null);
    }

    public static MoveToAction moveTo(float f2, float f3, float f4) {
        return moveTo(f2, f3, f4, null);
    }

    public static MoveToAction moveToAligned(float f2, float f3, int i2, float f4) {
        return moveToAligned(f2, f3, i2, f4, null);
    }

    public static RemoveActorAction removeActor(Actor actor) {
        RemoveActorAction removeActorAction = (RemoveActorAction) action(RemoveActorAction.class);
        removeActorAction.setTarget(actor);
        return removeActorAction;
    }

    public static RotateByAction rotateBy(float f2, float f3) {
        return rotateBy(f2, f3, null);
    }

    public static RotateToAction rotateTo(float f2, float f3) {
        return rotateTo(f2, f3, null);
    }

    public static ScaleByAction scaleBy(float f2, float f3, float f4) {
        return scaleBy(f2, f3, f4, null);
    }

    public static ScaleToAction scaleTo(float f2, float f3, float f4) {
        return scaleTo(f2, f3, f4, null);
    }

    public static SizeByAction sizeBy(float f2, float f3, float f4) {
        return sizeBy(f2, f3, f4, null);
    }

    public static SizeToAction sizeTo(float f2, float f3, float f4) {
        return sizeTo(f2, f3, f4, null);
    }

    public static AddAction addAction(Action action, Actor actor) {
        AddAction addAction = (AddAction) action(AddAction.class);
        addAction.setTarget(actor);
        addAction.setAction(action);
        return addAction;
    }

    public static AlphaAction alpha(float f2, float f3, h hVar) {
        AlphaAction alphaAction = (AlphaAction) action(AlphaAction.class);
        alphaAction.setAlpha(f2);
        alphaAction.setDuration(f3);
        alphaAction.setInterpolation(hVar);
        return alphaAction;
    }

    public static ColorAction color(Color color, float f2, h hVar) {
        ColorAction colorAction = (ColorAction) action(ColorAction.class);
        colorAction.setEndColor(color);
        colorAction.setDuration(f2);
        colorAction.setInterpolation(hVar);
        return colorAction;
    }

    public static DelayAction delay(float f2, Action action) {
        DelayAction delayAction = (DelayAction) action(DelayAction.class);
        delayAction.setDuration(f2);
        delayAction.setAction(action);
        return delayAction;
    }

    public static MoveByAction moveBy(float f2, float f3, float f4, h hVar) {
        MoveByAction moveByAction = (MoveByAction) action(MoveByAction.class);
        moveByAction.setAmount(f2, f3);
        moveByAction.setDuration(f4);
        moveByAction.setInterpolation(hVar);
        return moveByAction;
    }

    public static MoveToAction moveTo(float f2, float f3, float f4, h hVar) {
        MoveToAction moveToAction = (MoveToAction) action(MoveToAction.class);
        moveToAction.setPosition(f2, f3);
        moveToAction.setDuration(f4);
        moveToAction.setInterpolation(hVar);
        return moveToAction;
    }

    public static MoveToAction moveToAligned(float f2, float f3, int i2, float f4, h hVar) {
        MoveToAction moveToAction = (MoveToAction) action(MoveToAction.class);
        moveToAction.setPosition(f2, f3, i2);
        moveToAction.setDuration(f4);
        moveToAction.setInterpolation(hVar);
        return moveToAction;
    }

    public static ParallelAction parallel(Action action, Action action2) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        parallelAction.addAction(action);
        parallelAction.addAction(action2);
        return parallelAction;
    }

    public static RemoveAction removeAction(Action action, Actor actor) {
        RemoveAction removeAction = (RemoveAction) action(RemoveAction.class);
        removeAction.setTarget(actor);
        removeAction.setAction(action);
        return removeAction;
    }

    public static RotateByAction rotateBy(float f2, float f3, h hVar) {
        RotateByAction rotateByAction = (RotateByAction) action(RotateByAction.class);
        rotateByAction.setAmount(f2);
        rotateByAction.setDuration(f3);
        rotateByAction.setInterpolation(hVar);
        return rotateByAction;
    }

    public static RotateToAction rotateTo(float f2, float f3, h hVar) {
        RotateToAction rotateToAction = (RotateToAction) action(RotateToAction.class);
        rotateToAction.setRotation(f2);
        rotateToAction.setDuration(f3);
        rotateToAction.setInterpolation(hVar);
        return rotateToAction;
    }

    public static ScaleByAction scaleBy(float f2, float f3, float f4, h hVar) {
        ScaleByAction scaleByAction = (ScaleByAction) action(ScaleByAction.class);
        scaleByAction.setAmount(f2, f3);
        scaleByAction.setDuration(f4);
        scaleByAction.setInterpolation(hVar);
        return scaleByAction;
    }

    public static ScaleToAction scaleTo(float f2, float f3, float f4, h hVar) {
        ScaleToAction scaleToAction = (ScaleToAction) action(ScaleToAction.class);
        scaleToAction.setScale(f2, f3);
        scaleToAction.setDuration(f4);
        scaleToAction.setInterpolation(hVar);
        return scaleToAction;
    }

    public static SequenceAction sequence(Action action, Action action2) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        sequenceAction.addAction(action);
        sequenceAction.addAction(action2);
        return sequenceAction;
    }

    public static SizeByAction sizeBy(float f2, float f3, float f4, h hVar) {
        SizeByAction sizeByAction = (SizeByAction) action(SizeByAction.class);
        sizeByAction.setAmount(f2, f3);
        sizeByAction.setDuration(f4);
        sizeByAction.setInterpolation(hVar);
        return sizeByAction;
    }

    public static SizeToAction sizeTo(float f2, float f3, float f4, h hVar) {
        SizeToAction sizeToAction = (SizeToAction) action(SizeToAction.class);
        sizeToAction.setSize(f2, f3);
        sizeToAction.setDuration(f4);
        sizeToAction.setInterpolation(hVar);
        return sizeToAction;
    }

    public static AddListenerAction addListener(EventListener eventListener, boolean z2, Actor actor) {
        AddListenerAction addListenerAction = (AddListenerAction) action(AddListenerAction.class);
        addListenerAction.setTarget(actor);
        addListenerAction.setListener(eventListener);
        addListenerAction.setCapture(z2);
        return addListenerAction;
    }

    public static RemoveListenerAction removeListener(EventListener eventListener, boolean z2, Actor actor) {
        RemoveListenerAction removeListenerAction = (RemoveListenerAction) action(RemoveListenerAction.class);
        removeListenerAction.setTarget(actor);
        removeListenerAction.setListener(eventListener);
        removeListenerAction.setCapture(z2);
        return removeListenerAction;
    }

    public static ParallelAction parallel(Action action, Action action2, Action action3) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        parallelAction.addAction(action);
        parallelAction.addAction(action2);
        parallelAction.addAction(action3);
        return parallelAction;
    }

    public static SequenceAction sequence(Action action, Action action2, Action action3) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        sequenceAction.addAction(action);
        sequenceAction.addAction(action2);
        sequenceAction.addAction(action3);
        return sequenceAction;
    }

    public static ParallelAction parallel(Action action, Action action2, Action action3, Action action4) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        parallelAction.addAction(action);
        parallelAction.addAction(action2);
        parallelAction.addAction(action3);
        parallelAction.addAction(action4);
        return parallelAction;
    }

    public static SequenceAction sequence(Action action, Action action2, Action action3, Action action4) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        sequenceAction.addAction(action);
        sequenceAction.addAction(action2);
        sequenceAction.addAction(action3);
        sequenceAction.addAction(action4);
        return sequenceAction;
    }

    public static ParallelAction parallel(Action action, Action action2, Action action3, Action action4, Action action5) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        parallelAction.addAction(action);
        parallelAction.addAction(action2);
        parallelAction.addAction(action3);
        parallelAction.addAction(action4);
        parallelAction.addAction(action5);
        return parallelAction;
    }

    public static SequenceAction sequence(Action action, Action action2, Action action3, Action action4, Action action5) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        sequenceAction.addAction(action);
        sequenceAction.addAction(action2);
        sequenceAction.addAction(action3);
        sequenceAction.addAction(action4);
        sequenceAction.addAction(action5);
        return sequenceAction;
    }

    public static ParallelAction parallel(Action... actionArr) {
        ParallelAction parallelAction = (ParallelAction) action(ParallelAction.class);
        for (Action action : actionArr) {
            parallelAction.addAction(action);
        }
        return parallelAction;
    }

    public static SequenceAction sequence(Action... actionArr) {
        SequenceAction sequenceAction = (SequenceAction) action(SequenceAction.class);
        for (Action action : actionArr) {
            sequenceAction.addAction(action);
        }
        return sequenceAction;
    }

    public static ParallelAction parallel() {
        return (ParallelAction) action(ParallelAction.class);
    }

    public static SequenceAction sequence() {
        return (SequenceAction) action(SequenceAction.class);
    }
}

package com.badlogic.gdx.scenes.scene2d;

import a0.a;
import a0.j;
import a0.p;
import a0.q;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Group extends Actor implements Cullable {
    private static final q tmp = new q();
    private p cullingArea;
    final k0<Actor> children = new k0<>(true, 4, Actor.class);
    private final a worldTransform = new a();
    private final Matrix4 computedTransform = new Matrix4();
    private final Matrix4 oldTransform = new Matrix4();
    boolean transform = true;

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f2) {
        super.act(f2);
        Actor[] actorArrW = this.children.w();
        int i2 = this.children.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            actorArrW[i3].act(f2);
        }
        this.children.x();
    }

    public void addActor(Actor actor) {
        Group group = actor.parent;
        if (group != null) {
            if (group == this) {
                return;
            } else {
                group.removeActor(actor, false);
            }
        }
        this.children.a(actor);
        actor.setParent(this);
        actor.setStage(getStage());
        childrenChanged();
    }

    public void addActorAfter(Actor actor, Actor actor2) {
        Group group = actor2.parent;
        if (group != null) {
            if (group == this) {
                return;
            } else {
                group.removeActor(actor2, false);
            }
        }
        int iH = this.children.h(actor, true);
        k0<Actor> k0Var = this.children;
        if (iH == k0Var.f1750b || iH == -1) {
            k0Var.a(actor2);
        } else {
            k0Var.i(iH + 1, actor2);
        }
        actor2.setParent(this);
        actor2.setStage(getStage());
        childrenChanged();
    }

    public void addActorAt(int i2, Actor actor) {
        Group group = actor.parent;
        if (group != null) {
            if (group == this) {
                return;
            } else {
                group.removeActor(actor, false);
            }
        }
        k0<Actor> k0Var = this.children;
        if (i2 >= k0Var.f1750b) {
            k0Var.a(actor);
        } else {
            k0Var.i(i2, actor);
        }
        actor.setParent(this);
        actor.setStage(getStage());
        childrenChanged();
    }

    public void addActorBefore(Actor actor, Actor actor2) {
        Group group = actor2.parent;
        if (group != null) {
            if (group == this) {
                return;
            } else {
                group.removeActor(actor2, false);
            }
        }
        this.children.i(this.children.h(actor, true), actor2);
        actor2.setParent(this);
        actor2.setStage(getStage());
        childrenChanged();
    }

    protected void applyTransform(Batch batch, Matrix4 matrix4) {
        this.oldTransform.o(batch.getTransformMatrix());
        batch.setTransformMatrix(matrix4);
    }

    protected void childrenChanged() {
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void clear() {
        super.clear();
        clearChildren();
    }

    public void clearChildren() {
        Actor[] actorArrW = this.children.w();
        int i2 = this.children.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Actor actor = actorArrW[i3];
            actor.setStage(null);
            actor.setParent(null);
        }
        this.children.x();
        this.children.clear();
        childrenChanged();
    }

    protected Matrix4 computeTransform() {
        a aVar = this.worldTransform;
        float f2 = this.originX;
        float f3 = this.originY;
        float f4 = this.f1742x + f2;
        float f5 = this.f1743y + f3;
        float f6 = this.rotation;
        float f7 = this.scaleX;
        float f8 = this.scaleY;
        aVar.f27c = f4;
        aVar.f30f = f5;
        if (f6 == 0.0f) {
            aVar.f25a = f7;
            aVar.f26b = 0.0f;
            aVar.f28d = 0.0f;
            aVar.f29e = f8;
        } else {
            float fK = j.k(f6);
            float fC = j.c(f6);
            aVar.f25a = fC * f7;
            aVar.f26b = (-fK) * f8;
            aVar.f28d = fK * f7;
            aVar.f29e = fC * f8;
        }
        if (f2 != 0.0f || f3 != 0.0f) {
            float f9 = -f2;
            float f10 = -f3;
            float f11 = aVar.f27c;
            aVar.f27c = a.a.B(aVar.f26b, f10, aVar.f25a * f9, f11);
            float f12 = aVar.f30f;
            aVar.f30f = a.a.B(aVar.f29e, f10, aVar.f28d * f9, f12);
        }
        Group group = this.parent;
        while (group != null && !group.transform) {
            group = group.parent;
        }
        if (group != null) {
            a aVar2 = group.worldTransform;
            float f13 = aVar2.f25a;
            float f14 = aVar.f25a;
            float f15 = aVar2.f26b;
            float f16 = aVar.f28d;
            float f17 = (f15 * f16) + (f13 * f14);
            float f18 = aVar.f26b;
            float f19 = aVar.f29e;
            float f20 = (f15 * f19) + (f13 * f18);
            float f21 = aVar.f27c;
            float f22 = aVar.f30f;
            float f23 = (f15 * f22) + (f13 * f21) + aVar2.f27c;
            float f24 = aVar2.f28d;
            float f25 = aVar2.f29e;
            float f26 = (f16 * f25) + (f14 * f24);
            float f27 = (f19 * f25) + (f18 * f24);
            float f28 = (f25 * f22) + (f24 * f21) + aVar2.f30f;
            aVar.f25a = f17;
            aVar.f26b = f20;
            aVar.f27c = f23;
            aVar.f28d = f26;
            aVar.f29e = f27;
            aVar.f30f = f28;
        }
        Matrix4 matrix4 = this.computedTransform;
        matrix4.getClass();
        float f29 = aVar.f25a;
        float[] fArr = matrix4.f1724a;
        fArr[0] = f29;
        fArr[1] = aVar.f28d;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = aVar.f26b;
        fArr[5] = aVar.f29e;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = aVar.f27c;
        fArr[13] = aVar.f30f;
        fArr[14] = 0.0f;
        fArr[15] = 1.0f;
        return this.computedTransform;
    }

    public Group debugAll() {
        setDebug(true, true);
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        if (this.transform) {
            applyTransform(batch, computeTransform());
        }
        drawChildren(batch, f2);
        if (this.transform) {
            resetTransform(batch);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void drawChildren(Batch batch, float f2) {
        float f3;
        float f4 = this.color.f1677a * f2;
        k0<Actor> k0Var = this.children;
        Actor[] actorArrW = k0Var.w();
        p pVar = this.cullingArea;
        int i2 = 0;
        if (pVar != null) {
            float f5 = pVar.f89x;
            float f6 = pVar.width + f5;
            float f7 = pVar.f90y;
            float f8 = pVar.height + f7;
            if (this.transform) {
                int i3 = k0Var.f1750b;
                while (i2 < i3) {
                    Actor actor = actorArrW[i2];
                    if (actor.isVisible()) {
                        float f9 = actor.f1742x;
                        float f10 = actor.f1743y;
                        if (f9 <= f6 && f10 <= f8 && f9 + actor.width >= f5 && f10 + actor.height >= f7) {
                            actor.draw(batch, f4);
                        }
                    }
                    i2++;
                }
            } else {
                float f11 = this.f1742x;
                float f12 = this.f1743y;
                this.f1742x = 0.0f;
                this.f1743y = 0.0f;
                int i4 = k0Var.f1750b;
                while (i2 < i4) {
                    Actor actor2 = actorArrW[i2];
                    if (actor2.isVisible()) {
                        float f13 = actor2.f1742x;
                        float f14 = actor2.f1743y;
                        if (f13 <= f6 && f14 <= f8) {
                            f3 = f8;
                            if (actor2.width + f13 >= f5 && actor2.height + f14 >= f7) {
                                actor2.f1742x = f13 + f11;
                                actor2.f1743y = f14 + f12;
                                actor2.draw(batch, f4);
                                actor2.f1742x = f13;
                                actor2.f1743y = f14;
                            }
                        }
                    } else {
                        f3 = f8;
                    }
                    i2++;
                    f8 = f3;
                }
                this.f1742x = f11;
                this.f1743y = f12;
            }
        } else if (this.transform) {
            int i5 = k0Var.f1750b;
            while (i2 < i5) {
                Actor actor3 = actorArrW[i2];
                if (actor3.isVisible()) {
                    actor3.draw(batch, f4);
                }
                i2++;
            }
        } else {
            float f15 = this.f1742x;
            float f16 = this.f1743y;
            this.f1742x = 0.0f;
            this.f1743y = 0.0f;
            int i6 = k0Var.f1750b;
            while (i2 < i6) {
                Actor actor4 = actorArrW[i2];
                if (actor4.isVisible()) {
                    float f17 = actor4.f1742x;
                    float f18 = actor4.f1743y;
                    actor4.f1742x = f17 + f15;
                    actor4.f1743y = f18 + f16;
                    actor4.draw(batch, f4);
                    actor4.f1742x = f17;
                    actor4.f1743y = f18;
                }
                i2++;
            }
            this.f1742x = f15;
            this.f1743y = f16;
        }
        k0Var.x();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        drawDebugBounds(shapeRenderer);
        if (this.transform) {
            applyTransform(shapeRenderer, computeTransform());
        }
        drawDebugChildren(shapeRenderer);
        if (this.transform) {
            resetTransform(shapeRenderer);
        }
    }

    protected void drawDebugChildren(ShapeRenderer shapeRenderer) {
        k0<Actor> k0Var = this.children;
        Actor[] actorArrW = k0Var.w();
        int i2 = 0;
        if (this.transform) {
            int i3 = k0Var.f1750b;
            while (i2 < i3) {
                Actor actor = actorArrW[i2];
                if (actor.isVisible() && (actor.getDebug() || (actor instanceof Group))) {
                    actor.drawDebug(shapeRenderer);
                }
                i2++;
            }
            shapeRenderer.flush();
        } else {
            float f2 = this.f1742x;
            float f3 = this.f1743y;
            this.f1742x = 0.0f;
            this.f1743y = 0.0f;
            int i4 = k0Var.f1750b;
            while (i2 < i4) {
                Actor actor2 = actorArrW[i2];
                if (actor2.isVisible() && (actor2.getDebug() || (actor2 instanceof Group))) {
                    float f4 = actor2.f1742x;
                    float f5 = actor2.f1743y;
                    actor2.f1742x = f4 + f2;
                    actor2.f1743y = f5 + f3;
                    actor2.drawDebug(shapeRenderer);
                    actor2.f1742x = f4;
                    actor2.f1743y = f5;
                }
                i2++;
            }
            this.f1742x = f2;
            this.f1743y = f3;
        }
        k0Var.x();
    }

    public <T extends Actor> T findActor(String str) {
        T t2;
        k0<Actor> k0Var = this.children;
        int i2 = k0Var.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (str.equals(k0Var.get(i3).getName())) {
                return (T) k0Var.get(i3);
            }
        }
        int i4 = k0Var.f1750b;
        for (int i5 = 0; i5 < i4; i5++) {
            Actor actor = k0Var.get(i5);
            if ((actor instanceof Group) && (t2 = (T) ((Group) actor).findActor(str)) != null) {
                return t2;
            }
        }
        return null;
    }

    public Actor getChild(int i2) {
        return this.children.get(i2);
    }

    public k0<Actor> getChildren() {
        return this.children;
    }

    public p getCullingArea() {
        return this.cullingArea;
    }

    public boolean hasChildren() {
        return this.children.f1750b > 0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if ((z2 && getTouchable() == Touchable.disabled) || !isVisible()) {
            return null;
        }
        q qVar = tmp;
        k0<Actor> k0Var = this.children;
        Actor[] actorArr = k0Var.f1749a;
        for (int i2 = k0Var.f1750b - 1; i2 >= 0; i2--) {
            Actor actor = actorArr[i2];
            qVar.f91a = f2;
            qVar.f92b = f3;
            actor.parentToLocalCoordinates(qVar);
            Actor actorHit = actor.hit(qVar.f91a, qVar.f92b, z2);
            if (actorHit != null) {
                return actorHit;
            }
        }
        return super.hit(f2, f3, z2);
    }

    public boolean isTransform() {
        return this.transform;
    }

    public q localToDescendantCoordinates(Actor actor, q qVar) {
        Group group = actor.parent;
        if (group == null) {
            throw new IllegalArgumentException("Child is not a descendant: " + actor);
        }
        if (group != this) {
            localToDescendantCoordinates(group, qVar);
        }
        actor.parentToLocalCoordinates(qVar);
        return qVar;
    }

    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    public Actor removeActorAt(int i2, boolean z2) {
        Stage stage;
        Actor actorO = this.children.o(i2);
        if (z2 && (stage = getStage()) != null) {
            stage.unfocus(actorO);
        }
        actorO.setParent(null);
        actorO.setStage(null);
        childrenChanged();
        return actorO;
    }

    protected void resetTransform(Batch batch) {
        batch.setTransformMatrix(this.oldTransform);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(p pVar) {
        this.cullingArea = pVar;
    }

    public void setDebug(boolean z2, boolean z3) {
        setDebug(z2);
        if (z3) {
            a.b<Actor> it = this.children.iterator();
            while (it.hasNext()) {
                Actor next = it.next();
                if (next instanceof Group) {
                    ((Group) next).setDebug(z2, z3);
                } else {
                    next.setDebug(z2);
                }
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setStage(Stage stage) {
        super.setStage(stage);
        k0<Actor> k0Var = this.children;
        Actor[] actorArr = k0Var.f1749a;
        int i2 = k0Var.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            actorArr[i3].setStage(stage);
        }
    }

    public void setTransform(boolean z2) {
        this.transform = z2;
    }

    public boolean swapActor(int i2, int i3) {
        k0<Actor> k0Var = this.children;
        int i4 = k0Var.f1750b;
        if (i2 < 0 || i2 >= i4 || i3 < 0 || i3 >= i4) {
            return false;
        }
        k0Var.z(i2, i3);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        toString(sb, 1);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public boolean removeActor(Actor actor, boolean z2) {
        int iH = this.children.h(actor, true);
        if (iH == -1) {
            return false;
        }
        removeActorAt(iH, z2);
        return true;
    }

    protected void resetTransform(ShapeRenderer shapeRenderer) {
        shapeRenderer.setTransformMatrix(this.oldTransform);
    }

    protected void applyTransform(ShapeRenderer shapeRenderer, Matrix4 matrix4) {
        this.oldTransform.o(shapeRenderer.getTransformMatrix());
        shapeRenderer.setTransformMatrix(matrix4);
        shapeRenderer.flush();
    }

    public boolean swapActor(Actor actor, Actor actor2) {
        int iH = this.children.h(actor, true);
        int iH2 = this.children.h(actor2, true);
        if (iH == -1 || iH2 == -1) {
            return false;
        }
        this.children.z(iH, iH2);
        return true;
    }

    void toString(StringBuilder sb, int i2) {
        sb.append(super.toString());
        sb.append('\n');
        Actor[] actorArrW = this.children.w();
        int i3 = this.children.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                sb.append("|  ");
            }
            Actor actor = actorArrW[i4];
            if (actor instanceof Group) {
                ((Group) actor).toString(sb, i2 + 1);
            } else {
                sb.append(actor);
                sb.append('\n');
            }
        }
        this.children.x();
    }
}

package com.badlogic.gdx.graphics.glutils;

import a0.j;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.i;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ShapeRenderer implements i {
    private boolean autoShapeType;
    private final Color color;
    private final Matrix4 combinedMatrix;
    private float defaultRectLineWidth;
    private boolean matrixDirty;
    private final Matrix4 projectionMatrix;
    private final ImmediateModeRenderer renderer;
    private ShapeType shapeType;
    private final q tmp;
    private final Matrix4 transformMatrix;

    public enum ShapeType {
        Point(0),
        Line(1),
        Filled(4);

        private final int glType;

        ShapeType(int i2) {
            this.glType = i2;
        }

        public int getGlType() {
            return this.glType;
        }
    }

    public ShapeRenderer() {
        this(5000);
    }

    private void check(ShapeType shapeType, ShapeType shapeType2, int i2) {
        ShapeType shapeType3 = this.shapeType;
        if (shapeType3 == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if (shapeType3 == shapeType || shapeType3 == shapeType2) {
            if (this.matrixDirty) {
                end();
                begin(shapeType3);
                return;
            } else {
                if (this.renderer.getMaxVertices() - this.renderer.getNumVertices() < i2) {
                    ShapeType shapeType4 = this.shapeType;
                    end();
                    begin(shapeType4);
                    return;
                }
                return;
            }
        }
        if (this.autoShapeType) {
            end();
            begin(shapeType);
        } else {
            if (shapeType2 == null) {
                throw new IllegalStateException("Must call begin(ShapeType." + shapeType + ").");
            }
            throw new IllegalStateException("Must call begin(ShapeType." + shapeType + ") or begin(ShapeType." + shapeType2 + ").");
        }
    }

    public void arc(float f2, float f3, float f4, float f5, float f6) {
        arc(f2, f3, f4, f5, f6, Math.max(1, (int) ((f6 / 360.0f) * ((float) Math.cbrt(f4)) * 6.0f)));
    }

    public void begin() {
        if (!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be true to use this method.");
        }
        begin(ShapeType.Line);
    }

    public void box(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = -f7;
        float floatBits = this.color.toFloatBits();
        ShapeType shapeType = this.shapeType;
        ShapeType shapeType2 = ShapeType.Line;
        if (shapeType == shapeType2) {
            check(shapeType2, ShapeType.Filled, 24);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f4);
            this.renderer.color(floatBits);
            float f9 = f5 + f2;
            this.renderer.vertex(f9, f3, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f3, f4);
            this.renderer.color(floatBits);
            float f10 = f8 + f4;
            this.renderer.vertex(f9, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f4);
            this.renderer.color(floatBits);
            float f11 = f6 + f3;
            this.renderer.vertex(f2, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f11, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f11, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f3, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9, f11, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f10);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f11, f10);
            return;
        }
        check(shapeType2, ShapeType.Filled, 36);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f4);
        this.renderer.color(floatBits);
        float f12 = f5 + f2;
        this.renderer.vertex(f12, f3, f4);
        this.renderer.color(floatBits);
        float f13 = f6 + f3;
        this.renderer.vertex(f12, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f4);
        this.renderer.color(floatBits);
        float f14 = f8 + f4;
        this.renderer.vertex(f12, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f13, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f14);
        this.renderer.color(floatBits);
        this.renderer.vertex(f12, f3, f4);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, f4);
    }

    public void circle(float f2, float f3, float f4) {
        circle(f2, f3, f4, Math.max(1, (int) (((float) Math.cbrt(f4)) * 6.0f)));
    }

    public void cone(float f2, float f3, float f4, float f5, float f6) {
        cone(f2, f3, f4, f5, f6, Math.max(1, (int) (((float) Math.sqrt(f5)) * 4.0f)));
    }

    public void curve(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i2) {
        check(ShapeType.Line, null, (i2 * 2) + 2);
        float floatBits = this.color.toFloatBits();
        float f10 = 1.0f / i2;
        float f11 = f10 * f10;
        float f12 = f11 * f10;
        float f13 = f10 * 3.0f;
        float f14 = f11 * 3.0f;
        float f15 = f11 * 6.0f;
        float f16 = 6.0f * f12;
        float f17 = (f2 - (f4 * 2.0f)) + f6;
        float f18 = (f3 - (2.0f * f5)) + f7;
        float f19 = (((f4 - f6) * 3.0f) - f2) + f8;
        float f20 = (((f5 - f7) * 3.0f) - f3) + f9;
        float f21 = (f19 * f12) + (f17 * f14) + ((f4 - f2) * f13);
        float f22 = (f12 * f20) + (f14 * f18) + ((f5 - f3) * f13);
        float f23 = f19 * f16;
        float f24 = (f17 * f15) + f23;
        float f25 = f20 * f16;
        float f26 = (f18 * f15) + f25;
        float f27 = f3;
        int i3 = i2;
        float f28 = f2;
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f28, f27, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f8, f9, 0.0f);
                return;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f28, f27, 0.0f);
            f28 += f21;
            f27 += f22;
            f21 += f24;
            f22 += f26;
            f24 += f23;
            f26 += f25;
            this.renderer.color(floatBits);
            this.renderer.vertex(f28, f27, 0.0f);
            i3 = i4;
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        this.renderer.dispose();
    }

    public void ellipse(float f2, float f3, float f4, float f5) {
        ellipse(f2, f3, f4, f5, Math.max(1, (int) (((float) Math.cbrt(Math.max(f4 * 0.5f, 0.5f * f5))) * 12.0f)));
    }

    public void end() {
        this.renderer.end();
        this.shapeType = null;
    }

    public void flush() {
        ShapeType shapeType = this.shapeType;
        if (shapeType == null) {
            return;
        }
        end();
        begin(shapeType);
    }

    public Color getColor() {
        return this.color;
    }

    public ShapeType getCurrentType() {
        return this.shapeType;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public ImmediateModeRenderer getRenderer() {
        return this.renderer;
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public void identity() {
        this.transformMatrix.d();
        this.matrixDirty = true;
    }

    public boolean isDrawing() {
        return this.shapeType != null;
    }

    public final void line(float f2, float f3, float f4, float f5, float f6, float f7) {
        Color color = this.color;
        line(f2, f3, f4, f5, f6, f7, color, color);
    }

    public void point(float f2, float f3, float f4) {
        ShapeType shapeType = this.shapeType;
        if (shapeType == ShapeType.Line) {
            float f5 = this.defaultRectLineWidth * 0.5f;
            line(f2 - f5, f3 - f5, f4, f2 + f5, f3 + f5, f4);
        } else if (shapeType == ShapeType.Filled) {
            float f6 = this.defaultRectLineWidth;
            float f7 = 0.5f * f6;
            box(f2 - f7, f3 - f7, f4 - f7, f6, f6, f6);
        } else {
            check(ShapeType.Point, null, 1);
            this.renderer.color(this.color);
            this.renderer.vertex(f2, f3, f4);
        }
    }

    public void polygon(float[] fArr, int i2, int i3) {
        float f2;
        float f3;
        if (i3 < 6) {
            throw new IllegalArgumentException("Polygons must contain at least 3 points.");
        }
        if (i3 % 2 != 0) {
            throw new IllegalArgumentException("Polygons must have an even number of vertices.");
        }
        check(ShapeType.Line, null, i3);
        float floatBits = this.color.toFloatBits();
        float f4 = fArr[0];
        float f5 = fArr[1];
        int i4 = i2 + i3;
        while (i2 < i4) {
            float f6 = fArr[i2];
            float f7 = fArr[i2 + 1];
            int i5 = i2 + 2;
            if (i5 >= i3) {
                f2 = f4;
                f3 = f5;
            } else {
                f2 = fArr[i5];
                f3 = fArr[i2 + 3];
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            i2 = i5;
        }
    }

    public void polyline(float[] fArr, int i2, int i3) {
        if (i3 < 4) {
            throw new IllegalArgumentException("Polylines must contain at least 2 points.");
        }
        if (i3 % 2 != 0) {
            throw new IllegalArgumentException("Polylines must have an even number of vertices.");
        }
        check(ShapeType.Line, null, i3);
        float floatBits = this.color.toFloatBits();
        int i4 = (i3 + i2) - 2;
        while (i2 < i4) {
            float f2 = fArr[i2];
            float f3 = fArr[i2 + 1];
            int i5 = i2 + 2;
            float f4 = fArr[i5];
            float f5 = fArr[i2 + 3];
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f4, f5, 0.0f);
            i2 = i5;
        }
    }

    public void rect(float f2, float f3, float f4, float f5) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 8);
        float floatBits = this.color.toFloatBits();
        if (this.shapeType != shapeType) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            float f6 = f4 + f2;
            this.renderer.vertex(f6, f3, 0.0f);
            this.renderer.color(floatBits);
            float f7 = f5 + f3;
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f7, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(floatBits);
        float f8 = f4 + f2;
        this.renderer.vertex(f8, f3, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f8, f3, 0.0f);
        this.renderer.color(floatBits);
        float f9 = f5 + f3;
        this.renderer.vertex(f8, f9, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f8, f9, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f9, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f9, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, 0.0f);
    }

    public void rectLine(float f2, float f3, float f4, float f5, float f6) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 8);
        float floatBits = this.color.toFloatBits();
        q qVar = this.tmp;
        qVar.f91a = f5 - f3;
        qVar.f92b = f2 - f4;
        qVar.a();
        float f7 = f6 * 0.5f;
        float f8 = qVar.f91a * f7;
        float f9 = qVar.f92b * f7;
        if (this.shapeType != shapeType) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f2 + f8, f3 + f9, 0.0f);
            this.renderer.color(floatBits);
            float f10 = f2 - f8;
            float f11 = f3 - f9;
            this.renderer.vertex(f10, f11, 0.0f);
            this.renderer.color(floatBits);
            float f12 = f4 + f8;
            float f13 = f5 + f9;
            this.renderer.vertex(f12, f13, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f4 - f8, f5 - f9, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f12, f13, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f10, f11, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        float f14 = f2 + f8;
        float f15 = f3 + f9;
        this.renderer.vertex(f14, f15, 0.0f);
        this.renderer.color(floatBits);
        float f16 = f2 - f8;
        float f17 = f3 - f9;
        this.renderer.vertex(f16, f17, 0.0f);
        this.renderer.color(floatBits);
        float f18 = f4 + f8;
        float f19 = f5 + f9;
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(floatBits);
        float f20 = f4 - f8;
        float f21 = f5 - f9;
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f14, f15, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f16, f17, 0.0f);
    }

    public void rotate(float f2, float f3, float f4, float f5) {
        this.transformMatrix.i(f2, f3, f4, f5);
        this.matrixDirty = true;
    }

    public void scale(float f2, float f3, float f4) {
        this.transformMatrix.l(f2, f3, f4);
        this.matrixDirty = true;
    }

    public void set(ShapeType shapeType) {
        ShapeType shapeType2 = this.shapeType;
        if (shapeType2 == shapeType) {
            return;
        }
        if (shapeType2 == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if (!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be enabled.");
        }
        end();
        begin(shapeType);
    }

    public void setAutoShapeType(boolean z2) {
        this.autoShapeType = z2;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setProjectionMatrix(Matrix4 matrix4) {
        this.projectionMatrix.o(matrix4);
        this.matrixDirty = true;
    }

    public void setTransformMatrix(Matrix4 matrix4) {
        this.transformMatrix.o(matrix4);
        this.matrixDirty = true;
    }

    public void translate(float f2, float f3, float f4) {
        this.transformMatrix.v(f2, f3, f4);
        this.matrixDirty = true;
    }

    public void triangle(float f2, float f3, float f4, float f5, float f6, float f7) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 6);
        float floatBits = this.color.toFloatBits();
        if (this.shapeType != shapeType) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f6, f7, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f4, f5, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f4, f5, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f6, f7, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f6, f7, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f2, f3, 0.0f);
    }

    public void updateMatrices() {
        this.matrixDirty = true;
    }

    public void x(float f2, float f3, float f4) {
        float f5 = f2 - f4;
        float f6 = f3 - f4;
        float f7 = f2 + f4;
        float f8 = f3 + f4;
        line(f5, f6, f7, f8);
        line(f5, f8, f7, f6);
    }

    public ShapeRenderer(int i2) {
        this(i2, null);
    }

    public void arc(float f2, float f3, float f4, float f5, float f6, int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float floatBits = this.color.toFloatBits();
        float f7 = ((f6 / 360.0f) * 6.2831855f) / i2;
        float fB = j.b(f7);
        float fJ = j.j(f7);
        float f8 = f5 * 0.017453292f;
        float fB2 = j.b(f8) * f4;
        float fJ2 = j.j(f8) * f4;
        ShapeType shapeType = this.shapeType;
        ShapeType shapeType2 = ShapeType.Line;
        int i3 = 0;
        if (shapeType == shapeType2) {
            check(shapeType2, ShapeType.Filled, (i2 * 2) + 2);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2 + fB2, f3 + fJ2, 0.0f);
            while (i3 < i2) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + fB2, f3 + fJ2, 0.0f);
                float f9 = (fB * fB2) - (fJ * fJ2);
                fJ2 = (fJ2 * fB) + (fB2 * fJ);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f9, f3 + fJ2, 0.0f);
                i3++;
                fB2 = f9;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(fB2 + f2, fJ2 + f3, 0.0f);
        } else {
            check(shapeType2, ShapeType.Filled, (i2 * 3) + 3);
            while (i3 < i2) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f2, f3, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + fB2, f3 + fJ2, 0.0f);
                float f10 = (fB * fB2) - (fJ * fJ2);
                fJ2 = (fJ2 * fB) + (fB2 * fJ);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f10, f3 + fJ2, 0.0f);
                i3++;
                fB2 = f10;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(fB2 + f2, fJ2 + f3, 0.0f);
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f2 + 0.0f, f3 + 0.0f, 0.0f);
    }

    public void circle(float f2, float f3, float f4, int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float floatBits = this.color.toFloatBits();
        float f5 = 6.2831855f / i2;
        float fB = j.b(f5);
        float fJ = j.j(f5);
        ShapeType shapeType = this.shapeType;
        ShapeType shapeType2 = ShapeType.Line;
        int i3 = 0;
        if (shapeType == shapeType2) {
            check(shapeType2, ShapeType.Filled, (i2 * 2) + 2);
            float f6 = f4;
            float f7 = 0.0f;
            while (i3 < i2) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f6, f3 + f7, 0.0f);
                float f8 = (fB * f6) - (fJ * f7);
                f7 = (f7 * fB) + (f6 * fJ);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f8, f3 + f7, 0.0f);
                i3++;
                f6 = f8;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f6 + f2, f7 + f3, 0.0f);
        } else {
            check(shapeType2, ShapeType.Filled, (i2 * 3) + 3);
            int i4 = i2 - 1;
            float f9 = f4;
            float f10 = 0.0f;
            while (i3 < i4) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f2, f3, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f9, f3 + f10, 0.0f);
                float f11 = (fB * f9) - (fJ * f10);
                f10 = (f10 * fB) + (f9 * fJ);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f11, f3 + f10, 0.0f);
                i3++;
                f9 = f11;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f9 + f2, f10 + f3, 0.0f);
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f2 + f4, f3 + 0.0f, 0.0f);
    }

    public void cone(float f2, float f3, float f4, float f5, float f6, int i2) {
        float f7;
        float f8;
        if (i2 <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, (i2 * 4) + 2);
        float floatBits = this.color.toFloatBits();
        float f9 = 6.2831855f / i2;
        float fB = j.b(f9);
        float fJ = j.j(f9);
        int i3 = 0;
        if (this.shapeType == shapeType) {
            f7 = f5;
            f8 = 0.0f;
            while (i3 < i2) {
                this.renderer.color(floatBits);
                float f10 = f2 + f7;
                float f11 = f3 + f8;
                this.renderer.vertex(f10, f11, f4);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2, f3, f4 + f6);
                this.renderer.color(floatBits);
                this.renderer.vertex(f10, f11, f4);
                float f12 = (fB * f7) - (fJ * f8);
                f8 = (f8 * fB) + (f7 * fJ);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2 + f12, f3 + f8, f4);
                i3++;
                f7 = f12;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f2 + f7, f3 + f8, f4);
        } else {
            int i4 = i2 - 1;
            f7 = f5;
            f8 = 0.0f;
            while (i3 < i4) {
                this.renderer.color(floatBits);
                this.renderer.vertex(f2, f3, f4);
                this.renderer.color(floatBits);
                float f13 = f2 + f7;
                float f14 = f3 + f8;
                this.renderer.vertex(f13, f14, f4);
                float f15 = (fB * f7) - (fJ * f8);
                f8 = (f8 * fB) + (f7 * fJ);
                this.renderer.color(floatBits);
                float f16 = f2 + f15;
                float f17 = f3 + f8;
                this.renderer.vertex(f16, f17, f4);
                this.renderer.color(floatBits);
                this.renderer.vertex(f13, f14, f4);
                this.renderer.color(floatBits);
                this.renderer.vertex(f16, f17, f4);
                this.renderer.color(floatBits);
                this.renderer.vertex(f2, f3, f4 + f6);
                i3++;
                f7 = f15;
            }
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2 + f7, f3 + f8, f4);
        }
        this.renderer.color(floatBits);
        float f18 = f2 + f5;
        float f19 = 0.0f + f3;
        this.renderer.vertex(f18, f19, f4);
        if (this.shapeType != ShapeType.Line) {
            this.renderer.color(floatBits);
            this.renderer.vertex(f7 + f2, f8 + f3, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f18, f19, f4);
            this.renderer.color(floatBits);
            this.renderer.vertex(f2, f3, f4 + f6);
        }
    }

    public void ellipse(float f2, float f3, float f4, float f5, int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, i2 * 3);
        float floatBits = this.color.toFloatBits();
        float f6 = 6.2831855f / i2;
        float f7 = (f4 / 2.0f) + f2;
        float f8 = (f5 / 2.0f) + f3;
        int i3 = 0;
        if (this.shapeType == shapeType) {
            while (i3 < i2) {
                this.renderer.color(floatBits);
                float f9 = f4 * 0.5f;
                float f10 = i3 * f6;
                float f11 = f5 * 0.5f;
                this.renderer.vertex((j.b(f10) * f9) + f7, (j.j(f10) * f11) + f8, 0.0f);
                this.renderer.color(floatBits);
                i3++;
                float f12 = i3 * f6;
                this.renderer.vertex((j.b(f12) * f9) + f7, (j.j(f12) * f11) + f8, 0.0f);
            }
            return;
        }
        while (i3 < i2) {
            this.renderer.color(floatBits);
            float f13 = f4 * 0.5f;
            float f14 = i3 * f6;
            float f15 = f5 * 0.5f;
            this.renderer.vertex((j.b(f14) * f13) + f7, (j.j(f14) * f15) + f8, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f7, f8, 0.0f);
            this.renderer.color(floatBits);
            i3++;
            float f16 = i3 * f6;
            this.renderer.vertex((j.b(f16) * f13) + f7, (j.j(f16) * f15) + f8, 0.0f);
        }
    }

    public final void line(a aVar, a aVar2) {
        float f2 = aVar.f1729a;
        float f3 = aVar.f1730b;
        float f4 = aVar.f1731c;
        float f5 = aVar2.f1729a;
        float f6 = aVar2.f1730b;
        float f7 = aVar2.f1731c;
        Color color = this.color;
        line(f2, f3, f4, f5, f6, f7, color, color);
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
    }

    public ShapeRenderer(int i2, ShaderProgram shaderProgram) {
        this.matrixDirty = false;
        Matrix4 matrix4 = new Matrix4();
        this.projectionMatrix = matrix4;
        this.transformMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.tmp = new q();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.defaultRectLineWidth = 0.75f;
        if (shaderProgram == null) {
            this.renderer = new ImmediateModeRenderer20(i2, false, true, 0);
        } else {
            this.renderer = new ImmediateModeRenderer20(i2, false, true, 0, shaderProgram);
        }
        matrix4.s(0.0f, 0.0f + Gdx.graphics.getWidth(), 0.0f, 0.0f + Gdx.graphics.getHeight(), 0.0f, 1.0f);
        this.matrixDirty = true;
    }

    public final void line(float f2, float f3, float f4, float f5) {
        Color color = this.color;
        line(f2, f3, 0.0f, f4, f5, 0.0f, color, color);
    }

    public void x(q qVar, float f2) {
        x(qVar.f91a, qVar.f92b, f2);
    }

    public void begin(ShapeType shapeType) {
        if (this.shapeType == null) {
            this.shapeType = shapeType;
            if (this.matrixDirty) {
                this.combinedMatrix.o(this.projectionMatrix);
                Matrix4.h(this.combinedMatrix.f1724a, this.transformMatrix.f1724a);
                this.matrixDirty = false;
            }
            this.renderer.begin(this.combinedMatrix, this.shapeType.getGlType());
            return;
        }
        throw new IllegalStateException("Call end() before beginning a new shape batch.");
    }

    public final void line(q qVar, q qVar2) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        float f4 = qVar2.f91a;
        float f5 = qVar2.f92b;
        Color color = this.color;
        line(f2, f3, 0.0f, f4, f5, 0.0f, color, color);
    }

    public final void line(float f2, float f3, float f4, float f5, Color color, Color color2) {
        line(f2, f3, 0.0f, f4, f5, 0.0f, color, color2);
    }

    public void line(float f2, float f3, float f4, float f5, float f6, float f7, Color color, Color color2) {
        if (this.shapeType == ShapeType.Filled) {
            rectLine(f2, f3, f5, f6, this.defaultRectLineWidth, color, color2);
            return;
        }
        check(ShapeType.Line, null, 2);
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f2, f3, f4);
        this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
        this.renderer.vertex(f5, f6, f7);
    }

    public void polyline(float[] fArr) {
        polyline(fArr, 0, fArr.length);
    }

    public void polygon(float[] fArr) {
        polygon(fArr, 0, fArr.length);
    }

    public void ellipse(float f2, float f3, float f4, float f5, float f6) {
        ellipse(f2, f3, f4, f5, f6, Math.max(1, (int) (((float) Math.cbrt(Math.max(f4 * 0.5f, 0.5f * f5))) * 12.0f)));
    }

    public void ellipse(float f2, float f3, float f4, float f5, float f6, int i2) {
        if (i2 > 0) {
            ShapeType shapeType = ShapeType.Line;
            check(shapeType, ShapeType.Filled, i2 * 3);
            float floatBits = this.color.toFloatBits();
            float f7 = 6.2831855f / i2;
            float f8 = (3.1415927f * f6) / 180.0f;
            float fJ = j.j(f8);
            float fB = j.b(f8);
            float f9 = (f4 / 2.0f) + f2;
            float f10 = (f5 / 2.0f) + f3;
            float f11 = 0.5f;
            float f12 = f4 * 0.5f;
            int i3 = 0;
            if (this.shapeType == shapeType) {
                float f13 = f12;
                float f14 = 0.0f;
                while (i3 < i2) {
                    this.renderer.color(floatBits);
                    this.renderer.vertex(((fB * f13) + f9) - (fJ * f14), (f14 * fB) + (f13 * fJ) + f10, 0.0f);
                    i3++;
                    float f15 = i3 * f7;
                    float fB2 = j.b(f15) * f12;
                    float fJ2 = j.j(f15) * f5 * f11;
                    this.renderer.color(floatBits);
                    this.renderer.vertex(((fB * fB2) + f9) - (fJ * fJ2), (fB * fJ2) + (fJ * fB2) + f10, 0.0f);
                    f11 = 0.5f;
                    f14 = fJ2;
                    f13 = fB2;
                }
                return;
            }
            float f16 = f12;
            float f17 = 0.0f;
            while (i3 < i2) {
                this.renderer.color(floatBits);
                this.renderer.vertex(((fB * f16) + f9) - (fJ * f17), (f17 * fB) + (f16 * fJ) + f10, 0.0f);
                this.renderer.color(floatBits);
                this.renderer.vertex(f9, f10, 0.0f);
                i3++;
                float f18 = i3 * f7;
                float fB3 = j.b(f18) * f12;
                float fJ3 = j.j(f18) * f5 * 0.5f;
                this.renderer.color(floatBits);
                this.renderer.vertex(((fB * fB3) + f9) - (fJ * fJ3), (fB * fJ3) + (fJ * fB3) + f10, 0.0f);
                f17 = fJ3;
                f16 = fB3;
            }
            return;
        }
        throw new IllegalArgumentException("segments must be > 0.");
    }

    public void triangle(float f2, float f3, float f4, float f5, float f6, float f7, Color color, Color color2, Color color3) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 6);
        if (this.shapeType == shapeType) {
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f2, f3, 0.0f);
            return;
        }
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
        this.renderer.vertex(f4, f5, 0.0f);
        this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
        this.renderer.vertex(f6, f7, 0.0f);
    }

    public void rect(float f2, float f3, float f4, float f5, Color color, Color color2, Color color3, Color color4) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 8);
        if (this.shapeType == shapeType) {
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            float f6 = f4 + f2;
            this.renderer.vertex(f6, f3, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            this.renderer.vertex(f6, f3, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            float f7 = f5 + f3;
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            this.renderer.vertex(f6, f7, 0.0f);
            this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
            this.renderer.vertex(f2, f7, 0.0f);
            this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
            this.renderer.vertex(f2, f7, 0.0f);
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f2, f3, 0.0f);
            return;
        }
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
        float f8 = f4 + f2;
        this.renderer.vertex(f8, f3, 0.0f);
        this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
        float f9 = f5 + f3;
        this.renderer.vertex(f8, f9, 0.0f);
        this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
        this.renderer.vertex(f8, f9, 0.0f);
        this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
        this.renderer.vertex(f2, f9, 0.0f);
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f2, f3, 0.0f);
    }

    public void rectLine(float f2, float f3, float f4, float f5, float f6, Color color, Color color2) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 8);
        float floatBits = color.toFloatBits();
        float floatBits2 = color2.toFloatBits();
        q qVar = this.tmp;
        qVar.f91a = f5 - f3;
        qVar.f92b = f2 - f4;
        qVar.a();
        float f7 = f6 * 0.5f;
        float f8 = qVar.f91a * f7;
        float f9 = qVar.f92b * f7;
        if (this.shapeType == shapeType) {
            this.renderer.color(floatBits);
            float f10 = f2 + f8;
            float f11 = f3 + f9;
            this.renderer.vertex(f10, f11, 0.0f);
            this.renderer.color(floatBits);
            float f12 = f2 - f8;
            float f13 = f3 - f9;
            this.renderer.vertex(f12, f13, 0.0f);
            this.renderer.color(floatBits2);
            float f14 = f4 + f8;
            float f15 = f5 + f9;
            this.renderer.vertex(f14, f15, 0.0f);
            this.renderer.color(floatBits2);
            float f16 = f4 - f8;
            float f17 = f5 - f9;
            this.renderer.vertex(f16, f17, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f14, f15, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f10, f11, 0.0f);
            this.renderer.color(floatBits2);
            this.renderer.vertex(f16, f17, 0.0f);
            this.renderer.color(floatBits);
            this.renderer.vertex(f12, f13, 0.0f);
            return;
        }
        this.renderer.color(floatBits);
        this.renderer.vertex(f2 + f8, f3 + f9, 0.0f);
        this.renderer.color(floatBits);
        float f18 = f2 - f8;
        float f19 = f3 - f9;
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(floatBits2);
        float f20 = f4 + f8;
        float f21 = f5 + f9;
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(floatBits2);
        this.renderer.vertex(f4 - f8, f5 - f9, 0.0f);
        this.renderer.color(floatBits2);
        this.renderer.vertex(f20, f21, 0.0f);
        this.renderer.color(floatBits);
        this.renderer.vertex(f18, f19, 0.0f);
    }

    public void rect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        Color color = this.color;
        rect(f2, f3, f4, f5, f6, f7, f8, f9, f10, color, color, color, color);
    }

    public void rect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, Color color, Color color2, Color color3, Color color4) {
        ShapeType shapeType = ShapeType.Line;
        check(shapeType, ShapeType.Filled, 8);
        float fC = j.c(f10);
        float fK = j.k(f10);
        float f11 = -f4;
        float f12 = -f5;
        float f13 = f6 - f4;
        float f14 = f7 - f5;
        if (f8 != 1.0f || f9 != 1.0f) {
            f11 *= f8;
            f12 *= f9;
            f13 *= f8;
            f14 *= f9;
        }
        float f15 = f2 + f4;
        float f16 = f3 + f5;
        float f17 = fK * f12;
        float f18 = ((fC * f11) - f17) + f15;
        float f19 = f12 * fC;
        float f20 = (f11 * fK) + f19 + f16;
        float f21 = fC * f13;
        float f22 = (f21 - f17) + f15;
        float f23 = f13 * fK;
        float f24 = f19 + f23 + f16;
        float f25 = (f21 - (fK * f14)) + f15;
        float fB = a.a.B(fC, f14, f23, f16);
        float f26 = (f25 - f22) + f18;
        float f27 = fB - (f24 - f20);
        if (this.shapeType == shapeType) {
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f18, f20, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            this.renderer.vertex(f22, f24, 0.0f);
            this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
            this.renderer.vertex(f22, f24, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            this.renderer.vertex(f25, fB, 0.0f);
            this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
            this.renderer.vertex(f25, fB, 0.0f);
            this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
            this.renderer.vertex(f26, f27, 0.0f);
            this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
            this.renderer.vertex(f26, f27, 0.0f);
            this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
            this.renderer.vertex(f18, f20, 0.0f);
            return;
        }
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f18, f20, 0.0f);
        this.renderer.color(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a);
        this.renderer.vertex(f22, f24, 0.0f);
        this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
        this.renderer.vertex(f25, fB, 0.0f);
        this.renderer.color(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a);
        this.renderer.vertex(f25, fB, 0.0f);
        this.renderer.color(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a);
        this.renderer.vertex(f26, f27, 0.0f);
        this.renderer.color(color.f1680r, color.f1679g, color.f1678b, color.f1677a);
        this.renderer.vertex(f18, f20, 0.0f);
    }

    public void rectLine(q qVar, q qVar2, float f2) {
        rectLine(qVar.f91a, qVar.f92b, qVar2.f91a, qVar2.f92b, f2);
    }
}

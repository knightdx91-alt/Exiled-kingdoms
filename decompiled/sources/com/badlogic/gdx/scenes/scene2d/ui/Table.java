package com.badlogic.gdx.scenes.scene2d.ui;

import a0.p;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.k0;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Table extends WidgetGroup {
    private static float[] columnWeightedWidth;
    private static float[] rowWeightedHeight;
    int align;
    Drawable background;
    private final Cell cellDefaults;
    private final a<Cell> cells;
    private boolean clip;
    private final a<Cell> columnDefaults;
    private float[] columnMinWidth;
    private float[] columnPrefWidth;
    private float[] columnWidth;
    private int columns;
    Debug debug;
    a<DebugRect> debugRects;
    private float[] expandHeight;
    private float[] expandWidth;
    private boolean implicitEndRow;
    Value padBottom;
    Value padLeft;
    Value padRight;
    Value padTop;
    boolean round;
    private Cell rowDefaults;
    private float[] rowHeight;
    private float[] rowMinHeight;
    private float[] rowPrefHeight;
    private int rows;
    private boolean sizeInvalid;
    private Skin skin;
    private float tableMinHeight;
    private float tableMinWidth;
    private float tablePrefHeight;
    private float tablePrefWidth;
    public static Color debugTableColor = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    public static Color debugCellColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static Color debugActorColor = new Color(0.0f, 1.0f, 0.0f, 1.0f);
    static final c0<Cell> cellPool = new c0<Cell>() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.c0
        public Cell newObject() {
            return new Cell();
        }
    };
    public static Value backgroundTop = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.2
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getTopHeight();
        }
    };
    public static Value backgroundLeft = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.3
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getLeftWidth();
        }
    };
    public static Value backgroundBottom = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.4
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getBottomHeight();
        }
    };
    public static Value backgroundRight = new Value() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Table.5
        @Override // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(Actor actor) {
            Drawable drawable = ((Table) actor).background;
            if (drawable == null) {
                return 0.0f;
            }
            return drawable.getRightWidth();
        }
    };

    public enum Debug {
        none,
        all,
        table,
        cell,
        actor
    }

    public static class DebugRect extends p {
        static c0<DebugRect> pool = d0.c(DebugRect.class);
        Color color;
    }

    public Table() {
        this(null);
    }

    private void addDebugRect(float f2, float f3, float f4, float f5, Color color) {
        DebugRect debugRectObtain = DebugRect.pool.obtain();
        debugRectObtain.color = color;
        debugRectObtain.set(f2, (getHeight() - f3) - f5, f4, f5);
        this.debugRects.a(debugRectObtain);
    }

    private void addDebugRects(float f2, float f3, float f4, float f5) {
        clearDebugRects();
        Debug debug = this.debug;
        if (debug == Debug.table || debug == Debug.all) {
            addDebugRect(0.0f, 0.0f, getWidth(), getHeight(), debugTableColor);
            addDebugRect(f2, f3, f4, f5, debugTableColor);
        }
        int i2 = this.cells.f1750b;
        float f6 = f2;
        float f7 = f3;
        for (int i3 = 0; i3 < i2; i3++) {
            Cell cell = this.cells.get(i3);
            Debug debug2 = this.debug;
            if (debug2 == Debug.actor || debug2 == Debug.all) {
                addDebugRect(cell.actorX, cell.actorY, cell.actorWidth, cell.actorHeight, debugActorColor);
            }
            int i4 = cell.column;
            int iIntValue = cell.colspan.intValue() + i4;
            float f8 = 0.0f;
            while (i4 < iIntValue) {
                f8 += this.columnWidth[i4];
                i4++;
            }
            float f9 = cell.computedPadLeft;
            float f10 = f8 - (cell.computedPadRight + f9);
            float f11 = f6 + f9;
            Debug debug3 = this.debug;
            if (debug3 == Debug.cell || debug3 == Debug.all) {
                float f12 = cell.computedPadTop;
                addDebugRect(f11, f7 + f12, f10, (this.rowHeight[cell.row] - f12) - cell.computedPadBottom, debugCellColor);
            }
            if (cell.endRow) {
                f7 += this.rowHeight[cell.row];
                f6 = f2;
            } else {
                f6 = f10 + cell.computedPadRight + f11;
            }
        }
    }

    private void clearDebugRects() {
        if (this.debugRects == null) {
            this.debugRects = new a<>();
        }
        DebugRect.pool.freeAll(this.debugRects);
        this.debugRects.clear();
    }

    private void computeSize() {
        this.sizeInvalid = false;
        a<Cell> aVar = this.cells;
        Cell[] cellArr = aVar.f1749a;
        int i2 = aVar.f1750b;
        if (i2 > 0 && !cellArr[i2 - 1].endRow) {
            endRow();
            this.implicitEndRow = true;
        }
        int i3 = this.columns;
        int i4 = this.rows;
        float[] fArrEnsureSize = ensureSize(this.columnMinWidth, i3);
        this.columnMinWidth = fArrEnsureSize;
        float[] fArrEnsureSize2 = ensureSize(this.rowMinHeight, i4);
        this.rowMinHeight = fArrEnsureSize2;
        float[] fArrEnsureSize3 = ensureSize(this.columnPrefWidth, i3);
        this.columnPrefWidth = fArrEnsureSize3;
        float[] fArrEnsureSize4 = ensureSize(this.rowPrefHeight, i4);
        this.rowPrefHeight = fArrEnsureSize4;
        this.columnWidth = ensureSize(this.columnWidth, i3);
        this.rowHeight = ensureSize(this.rowHeight, i4);
        float[] fArrEnsureSize5 = ensureSize(this.expandWidth, i3);
        this.expandWidth = fArrEnsureSize5;
        float[] fArrEnsureSize6 = ensureSize(this.expandHeight, i4);
        this.expandHeight = fArrEnsureSize6;
        int i5 = 0;
        float f2 = 0.0f;
        while (i5 < i2) {
            Cell cell = cellArr[i5];
            int i6 = cell.column;
            int i7 = cell.row;
            int i8 = i2;
            int iIntValue = cell.colspan.intValue();
            int i9 = i5;
            Actor actor = cell.actor;
            float[] fArr = fArrEnsureSize2;
            if (cell.expandY.intValue() != 0 && fArrEnsureSize6[i7] == 0.0f) {
                fArrEnsureSize6[i7] = cell.expandY.intValue();
            }
            if (iIntValue == 1 && cell.expandX.intValue() != 0 && fArrEnsureSize5[i6] == 0.0f) {
                fArrEnsureSize5[i6] = cell.expandX.intValue();
            }
            float[] fArr2 = fArrEnsureSize6;
            cell.computedPadLeft = cell.padLeft.get(actor) + (i6 == 0 ? 0.0f : Math.max(0.0f, cell.spaceLeft.get(actor) - f2));
            float f3 = cell.padTop.get(actor);
            cell.computedPadTop = f3;
            int i10 = cell.cellAboveIndex;
            if (i10 != -1) {
                cell.computedPadTop = Math.max(0.0f, cell.spaceTop.get(actor) - cellArr[i10].spaceBottom.get(actor)) + f3;
            }
            float f4 = cell.spaceRight.get(actor);
            cell.computedPadRight = cell.padRight.get(actor) + (i6 + iIntValue == i3 ? 0.0f : f4);
            cell.computedPadBottom = cell.padBottom.get(actor) + (i7 == i4 + (-1) ? 0.0f : cell.spaceBottom.get(actor));
            float f5 = cell.prefWidth.get(actor);
            float f6 = cell.prefHeight.get(actor);
            float f7 = cell.minWidth.get(actor);
            int i11 = i4;
            float fCeil = cell.minHeight.get(actor);
            int i12 = i3;
            float fCeil2 = cell.maxWidth.get(actor);
            float[] fArr3 = fArrEnsureSize5;
            float fCeil3 = cell.maxHeight.get(actor);
            if (f5 < f7) {
                f5 = f7;
            }
            if (f6 < fCeil) {
                f6 = fCeil;
            }
            if (fCeil2 <= 0.0f || f5 <= fCeil2) {
                fCeil2 = f5;
            }
            if (fCeil3 <= 0.0f || f6 <= fCeil3) {
                fCeil3 = f6;
            }
            if (this.round) {
                float fCeil4 = (float) Math.ceil(f7);
                fCeil = (float) Math.ceil(fCeil);
                fCeil2 = (float) Math.ceil(fCeil2);
                fCeil3 = (float) Math.ceil(fCeil3);
                f7 = fCeil4;
            }
            if (iIntValue == 1) {
                float f8 = cell.computedPadLeft + cell.computedPadRight;
                fArrEnsureSize3[i6] = Math.max(fArrEnsureSize3[i6], fCeil2 + f8);
                fArrEnsureSize[i6] = Math.max(fArrEnsureSize[i6], f7 + f8);
            }
            float f9 = cell.computedPadTop + cell.computedPadBottom;
            fArrEnsureSize4[i7] = Math.max(fArrEnsureSize4[i7], fCeil3 + f9);
            fArr[i7] = Math.max(fArr[i7], fCeil + f9);
            i5 = i9 + 1;
            i2 = i8;
            fArrEnsureSize2 = fArr;
            fArrEnsureSize6 = fArr2;
            f2 = f4;
            i4 = i11;
            i3 = i12;
            fArrEnsureSize5 = fArr3;
        }
        int i13 = i3;
        int i14 = i4;
        float[] fArr4 = fArrEnsureSize2;
        float[] fArr5 = fArrEnsureSize5;
        int i15 = i2;
        float fMax = 0.0f;
        float fMax2 = 0.0f;
        float fMax3 = 0.0f;
        float fMax4 = 0.0f;
        for (int i16 = 0; i16 < i15; i16++) {
            Cell cell2 = cellArr[i16];
            int i17 = cell2.column;
            int iIntValue2 = cell2.expandX.intValue();
            if (iIntValue2 != 0) {
                int iIntValue3 = cell2.colspan.intValue() + i17;
                int i18 = i17;
                while (true) {
                    if (i18 >= iIntValue3) {
                        int i19 = i17;
                        while (i19 < iIntValue3) {
                            fArr5[i19] = iIntValue2;
                            i19++;
                            iIntValue3 = iIntValue3;
                        }
                    } else if (fArr5[i18] != 0.0f) {
                        break;
                    } else {
                        i18++;
                    }
                }
            }
            Boolean bool = cell2.uniformX;
            Boolean bool2 = Boolean.TRUE;
            if (bool == bool2 && cell2.colspan.intValue() == 1) {
                float f10 = cell2.computedPadLeft + cell2.computedPadRight;
                fMax3 = Math.max(fMax3, fArrEnsureSize[i17] - f10);
                fMax = Math.max(fMax, fArrEnsureSize3[i17] - f10);
            }
            if (cell2.uniformY == bool2) {
                float f11 = cell2.computedPadTop + cell2.computedPadBottom;
                fMax4 = Math.max(fMax4, fArr4[cell2.row] - f11);
                fMax2 = Math.max(fMax2, fArrEnsureSize4[cell2.row] - f11);
            }
        }
        if (fMax > 0.0f || fMax2 > 0.0f) {
            for (int i20 = 0; i20 < i15; i20++) {
                Cell cell3 = cellArr[i20];
                if (fMax > 0.0f && cell3.uniformX == Boolean.TRUE && cell3.colspan.intValue() == 1) {
                    float f12 = cell3.computedPadLeft + cell3.computedPadRight;
                    int i21 = cell3.column;
                    fArrEnsureSize[i21] = fMax3 + f12;
                    fArrEnsureSize3[i21] = f12 + fMax;
                }
                if (fMax2 > 0.0f && cell3.uniformY == Boolean.TRUE) {
                    float f13 = cell3.computedPadTop + cell3.computedPadBottom;
                    int i22 = cell3.row;
                    fArr4[i22] = fMax4 + f13;
                    fArrEnsureSize4[i22] = f13 + fMax2;
                }
            }
        }
        for (int i23 = 0; i23 < i15; i23++) {
            Cell cell4 = cellArr[i23];
            int iIntValue4 = cell4.colspan.intValue();
            if (iIntValue4 != 1) {
                int i24 = cell4.column;
                Actor actor2 = cell4.actor;
                float fCeil5 = cell4.minWidth.get(actor2);
                float f14 = cell4.prefWidth.get(actor2);
                float fCeil6 = cell4.maxWidth.get(actor2);
                if (f14 < fCeil5) {
                    f14 = fCeil5;
                }
                if (fCeil6 <= 0.0f || f14 <= fCeil6) {
                    fCeil6 = f14;
                }
                if (this.round) {
                    fCeil5 = (float) Math.ceil(fCeil5);
                    fCeil6 = (float) Math.ceil(fCeil6);
                }
                float f15 = -(cell4.computedPadLeft + cell4.computedPadRight);
                int i25 = i24 + iIntValue4;
                float f16 = f15;
                float f17 = 0.0f;
                for (int i26 = i24; i26 < i25; i26++) {
                    f15 += fArrEnsureSize[i26];
                    f16 += fArrEnsureSize3[i26];
                    f17 += fArr5[i26];
                }
                float fMax5 = Math.max(0.0f, fCeil5 - f15);
                float fMax6 = Math.max(0.0f, fCeil6 - f16);
                while (i24 < i25) {
                    float f18 = f17 == 0.0f ? 1.0f / iIntValue4 : fArr5[i24] / f17;
                    fArrEnsureSize[i24] = (fMax5 * f18) + fArrEnsureSize[i24];
                    fArrEnsureSize3[i24] = (f18 * fMax6) + fArrEnsureSize3[i24];
                    i24++;
                }
            }
        }
        float f19 = this.padRight.get(this) + this.padLeft.get(this);
        float f20 = this.padBottom.get(this) + this.padTop.get(this);
        this.tableMinWidth = f19;
        this.tablePrefWidth = f19;
        for (int i27 = 0; i27 < i13; i27++) {
            this.tableMinWidth += fArrEnsureSize[i27];
            this.tablePrefWidth += fArrEnsureSize3[i27];
        }
        this.tableMinHeight = f20;
        this.tablePrefHeight = f20;
        for (int i28 = 0; i28 < i14; i28++) {
            float f21 = this.tableMinHeight;
            float f22 = fArr4[i28];
            this.tableMinHeight = f21 + f22;
            this.tablePrefHeight = Math.max(f22, fArrEnsureSize4[i28]) + this.tablePrefHeight;
        }
        this.tablePrefWidth = Math.max(this.tableMinWidth, this.tablePrefWidth);
        this.tablePrefHeight = Math.max(this.tableMinHeight, this.tablePrefHeight);
    }

    private void drawDebugRects(ShapeRenderer shapeRenderer) {
        float x2;
        float y2;
        if (this.debugRects == null || !getDebug()) {
            return;
        }
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        if (getStage() != null) {
            shapeRenderer.setColor(getStage().getDebugColor());
        }
        if (isTransform()) {
            x2 = 0.0f;
            y2 = 0.0f;
        } else {
            x2 = getX();
            y2 = getY();
        }
        int i2 = this.debugRects.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            DebugRect debugRect = this.debugRects.get(i3);
            shapeRenderer.setColor(debugRect.color);
            shapeRenderer.rect(debugRect.f89x + x2, debugRect.f90y + y2, debugRect.width, debugRect.height);
        }
    }

    private void endRow() {
        a<Cell> aVar = this.cells;
        Cell[] cellArr = aVar.f1749a;
        int iIntValue = 0;
        for (int i2 = aVar.f1750b - 1; i2 >= 0; i2--) {
            Cell cell = cellArr[i2];
            if (cell.endRow) {
                break;
            }
            iIntValue += cell.colspan.intValue();
        }
        this.columns = Math.max(this.columns, iIntValue);
        this.rows++;
        this.cells.k().endRow = true;
    }

    private float[] ensureSize(float[] fArr, int i2) {
        if (fArr == null || fArr.length < i2) {
            return new float[i2];
        }
        Arrays.fill(fArr, 0, i2, 0.0f);
        return fArr;
    }

    private Cell obtainCell() {
        Cell cellObtain = cellPool.obtain();
        cellObtain.setTable(this);
        return cellObtain;
    }

    public <T extends Actor> Cell<T> add(T t2) {
        Cell<T> cellObtainCell = obtainCell();
        cellObtainCell.actor = t2;
        if (this.implicitEndRow) {
            this.implicitEndRow = false;
            this.rows--;
            this.cells.k().endRow = false;
        }
        a<Cell> aVar = this.cells;
        int i2 = aVar.f1750b;
        if (i2 > 0) {
            Cell cellK = aVar.k();
            if (cellK.endRow) {
                cellObtainCell.column = 0;
                cellObtainCell.row = cellK.row + 1;
            } else {
                cellObtainCell.column = cellK.colspan.intValue() + cellK.column;
                cellObtainCell.row = cellK.row;
            }
            if (cellObtainCell.row > 0) {
                Cell[] cellArr = this.cells.f1749a;
                int i3 = i2 - 1;
                loop0: while (true) {
                    if (i3 < 0) {
                        break;
                    }
                    Cell cell = cellArr[i3];
                    int i4 = cell.column;
                    int iIntValue = cell.colspan.intValue() + i4;
                    while (i4 < iIntValue) {
                        if (i4 == cellObtainCell.column) {
                            cellObtainCell.cellAboveIndex = i3;
                            break loop0;
                        }
                        i4++;
                    }
                    i3--;
                }
            }
        } else {
            cellObtainCell.column = 0;
            cellObtainCell.row = 0;
        }
        this.cells.a(cellObtainCell);
        cellObtainCell.set(this.cellDefaults);
        int i5 = cellObtainCell.column;
        a<Cell> aVar2 = this.columnDefaults;
        if (i5 < aVar2.f1750b) {
            cellObtainCell.merge(aVar2.get(i5));
        }
        cellObtainCell.merge(this.rowDefaults);
        if (t2 != null) {
            addActor(t2);
        }
        return cellObtainCell;
    }

    public Table align(int i2) {
        this.align = i2;
        return this;
    }

    public Table background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    public Table bottom() {
        this.align = (this.align | 4) & (-3);
        return this;
    }

    public Table center() {
        this.align = 1;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren() {
        a<Cell> aVar = this.cells;
        Cell[] cellArr = aVar.f1749a;
        for (int i2 = aVar.f1750b - 1; i2 >= 0; i2--) {
            Actor actor = cellArr[i2].actor;
            if (actor != null) {
                actor.remove();
            }
        }
        c0<Cell> c0Var = cellPool;
        c0Var.freeAll(this.cells);
        this.cells.clear();
        this.rows = 0;
        this.columns = 0;
        Cell cell = this.rowDefaults;
        if (cell != null) {
            c0Var.free(cell);
        }
        this.rowDefaults = null;
        this.implicitEndRow = false;
        super.clearChildren();
    }

    public Table clip() {
        setClip(true);
        return this;
    }

    public Cell columnDefaults(int i2) {
        a<Cell> aVar = this.columnDefaults;
        Cell cellObtainCell = aVar.f1750b > i2 ? aVar.get(i2) : null;
        if (cellObtainCell == null) {
            cellObtainCell = obtainCell();
            cellObtainCell.clear();
            a<Cell> aVar2 = this.columnDefaults;
            int i3 = aVar2.f1750b;
            if (i2 >= i3) {
                while (i3 < i2) {
                    this.columnDefaults.a(null);
                    i3++;
                }
                this.columnDefaults.a(cellObtainCell);
            } else {
                aVar2.t(i2, cellObtainCell);
            }
        }
        return cellObtainCell;
    }

    public Table debugActor() {
        super.setDebug(true);
        Debug debug = this.debug;
        Debug debug2 = Debug.actor;
        if (debug != debug2) {
            this.debug = debug2;
            invalidate();
        }
        return this;
    }

    public Table debugCell() {
        super.setDebug(true);
        Debug debug = this.debug;
        Debug debug2 = Debug.cell;
        if (debug != debug2) {
            this.debug = debug2;
            invalidate();
        }
        return this;
    }

    public Table debugTable() {
        super.setDebug(true);
        Debug debug = this.debug;
        Debug debug2 = Debug.table;
        if (debug != debug2) {
            this.debug = debug2;
            invalidate();
        }
        return this;
    }

    public Cell defaults() {
        return this.cellDefaults;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        validate();
        if (!isTransform()) {
            drawBackground(batch, f2, getX(), getY());
            super.draw(batch, f2);
            return;
        }
        applyTransform(batch, computeTransform());
        drawBackground(batch, f2, 0.0f, 0.0f);
        if (this.clip) {
            batch.flush();
            float f3 = this.padLeft.get(this);
            float f4 = this.padBottom.get(this);
            if (clipBegin(f3, f4, (getWidth() - f3) - this.padRight.get(this), (getHeight() - f4) - this.padTop.get(this))) {
                drawChildren(batch, f2);
                batch.flush();
                clipEnd();
            }
        } else {
            drawChildren(batch, f2);
        }
        resetTransform(batch);
    }

    protected void drawBackground(Batch batch, float f2, float f3, float f4) {
        if (this.background == null) {
            return;
        }
        Color color = getColor();
        batch.setColor(color.f1680r, color.f1679g, color.f1678b, color.f1677a * f2);
        this.background.draw(batch, f3, f4, getWidth(), getHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        float f2;
        float f3;
        if (!isTransform()) {
            drawDebugRects(shapeRenderer);
            super.drawDebug(shapeRenderer);
            return;
        }
        applyTransform(shapeRenderer, computeTransform());
        drawDebugRects(shapeRenderer);
        if (this.clip) {
            shapeRenderer.flush();
            float width = getWidth();
            float height = getHeight();
            if (this.background != null) {
                f2 = this.padLeft.get(this);
                f3 = this.padBottom.get(this);
                width -= this.padRight.get(this) + f2;
                height -= this.padTop.get(this) + f3;
            } else {
                f2 = 0.0f;
                f3 = 0.0f;
            }
            if (clipBegin(f2, f3, width, height)) {
                drawDebugChildren(shapeRenderer);
                clipEnd();
            }
        } else {
            drawDebugChildren(shapeRenderer);
        }
        resetTransform(shapeRenderer);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    protected void drawDebugBounds(ShapeRenderer shapeRenderer) {
    }

    public int getAlign() {
        return this.align;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public <T extends Actor> Cell<T> getCell(T t2) {
        if (t2 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        a<Cell> aVar = this.cells;
        Cell<T>[] cellArr = aVar.f1749a;
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Cell<T> cell = cellArr[i3];
            if (cell.actor == t2) {
                return cell;
            }
        }
        return null;
    }

    public a<Cell> getCells() {
        return this.cells;
    }

    public boolean getClip() {
        return this.clip;
    }

    public float getColumnMinWidth(int i2) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.columnMinWidth[i2];
    }

    public float getColumnPrefWidth(int i2) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.columnPrefWidth[i2];
    }

    public float getColumnWidth(int i2) {
        float[] fArr = this.columnWidth;
        if (fArr == null) {
            return 0.0f;
        }
        return fArr[i2];
    }

    public int getColumns() {
        return this.columns;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.tableMinHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.tableMinWidth;
    }

    public float getPadBottom() {
        return this.padBottom.get(this);
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft.get(this);
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight.get(this);
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop.get(this);
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadX() {
        return this.padRight.get(this) + this.padLeft.get(this);
    }

    public float getPadY() {
        return this.padBottom.get(this) + this.padTop.get(this);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        float f2 = this.tablePrefHeight;
        Drawable drawable = this.background;
        return drawable != null ? Math.max(f2, drawable.getMinHeight()) : f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        float f2 = this.tablePrefWidth;
        Drawable drawable = this.background;
        return drawable != null ? Math.max(f2, drawable.getMinWidth()) : f2;
    }

    public int getRow(float f2) {
        int i2 = this.cells.f1750b;
        if (i2 == 0) {
            return -1;
        }
        float padTop = getPadTop() + f2;
        Cell[] cellArr = this.cells.f1749a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i3 + 1;
            Cell cell = cellArr[i3];
            if (cell.actorY + cell.computedPadTop < padTop) {
                return i4;
            }
            if (cell.endRow) {
                i4++;
            }
            i3 = i5;
        }
        return -1;
    }

    public float getRowHeight(int i2) {
        float[] fArr = this.rowHeight;
        if (fArr == null) {
            return 0.0f;
        }
        return fArr[i2];
    }

    public float getRowMinHeight(int i2) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.rowMinHeight[i2];
    }

    public float getRowPrefHeight(int i2) {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.rowPrefHeight[i2];
    }

    public int getRows() {
        return this.rows;
    }

    public Skin getSkin() {
        return this.skin;
    }

    public Debug getTableDebug() {
        return this.debug;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f2, float f3, boolean z2) {
        if (!this.clip || (!(z2 && getTouchable() == Touchable.disabled) && f2 >= 0.0f && f2 < getWidth() && f3 >= 0.0f && f3 < getHeight())) {
            return super.hit(f2, f3, z2);
        }
        return null;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        this.sizeInvalid = true;
        super.invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:202:? A[RETURN, SYNTHETIC] */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        float f2;
        float[] fArr;
        float f3;
        float[] fArr2;
        float f4;
        float f5;
        float f6;
        float f7;
        int i2;
        int i3;
        int i4;
        int i5;
        Actor actor;
        float[] fArr3;
        Table table = this;
        if (table.sizeInvalid) {
            computeSize();
        }
        float width = getWidth();
        float height = getHeight();
        int i6 = table.columns;
        int i7 = table.rows;
        float[] fArr4 = table.columnWidth;
        float[] fArr5 = table.rowHeight;
        float f8 = table.padLeft.get(table);
        float f9 = table.padRight.get(table) + f8;
        float f10 = table.padTop.get(table);
        float f11 = table.padBottom.get(table) + f10;
        float f12 = table.tablePrefWidth;
        float f13 = table.tableMinWidth;
        float f14 = f12 - f13;
        if (f14 == 0.0f) {
            fArr = table.columnMinWidth;
            f2 = f10;
        } else {
            float fMin = Math.min(f14, Math.max(0.0f, width - f13));
            float[] fArrEnsureSize = table.ensureSize(columnWeightedWidth, i6);
            columnWeightedWidth = fArrEnsureSize;
            float[] fArr6 = table.columnMinWidth;
            float[] fArr7 = table.columnPrefWidth;
            f2 = f10;
            for (int i8 = 0; i8 < i6; i8++) {
                float f15 = fArr7[i8];
                float f16 = fArr6[i8];
                fArrEnsureSize[i8] = (((f15 - f16) / f14) * fMin) + f16;
            }
            fArr = fArrEnsureSize;
        }
        float f17 = table.tablePrefHeight - table.tableMinHeight;
        if (f17 == 0.0f) {
            fArr2 = table.rowMinHeight;
            f3 = f8;
        } else {
            float[] fArrEnsureSize2 = table.ensureSize(rowWeightedHeight, i7);
            rowWeightedHeight = fArrEnsureSize2;
            float fMin2 = Math.min(f17, Math.max(0.0f, height - table.tableMinHeight));
            float[] fArr8 = table.rowMinHeight;
            float[] fArr9 = table.rowPrefHeight;
            f3 = f8;
            for (int i9 = 0; i9 < i7; i9++) {
                float f18 = fArr9[i9];
                float f19 = fArr8[i9];
                fArrEnsureSize2[i9] = (((f18 - f19) / f17) * fMin2) + f19;
            }
            fArr2 = fArrEnsureSize2;
        }
        a<Cell> aVar = table.cells;
        Cell[] cellArr = aVar.f1749a;
        int i10 = aVar.f1750b;
        int i11 = 0;
        while (i11 < i10) {
            Cell cell = cellArr[i11];
            int i12 = cell.column;
            Cell[] cellArr2 = cellArr;
            int i13 = cell.row;
            int i14 = i10;
            Actor actor2 = cell.actor;
            float f20 = height;
            int iIntValue = cell.colspan.intValue();
            float f21 = f11;
            int i15 = i12 + iIntValue;
            float f22 = width;
            int i16 = i7;
            float f23 = 0.0f;
            for (int i17 = i12; i17 < i15; i17++) {
                f23 += fArr[i17];
            }
            float f24 = fArr2[i13];
            float f25 = cell.prefWidth.get(actor2);
            float[] fArr10 = fArr2;
            float f26 = cell.prefHeight.get(actor2);
            float[] fArr11 = fArr;
            float f27 = cell.minWidth.get(actor2);
            float f28 = f9;
            float f29 = cell.minHeight.get(actor2);
            int i18 = i6;
            float f30 = cell.maxWidth.get(actor2);
            float f31 = cell.maxHeight.get(actor2);
            if (f25 < f27) {
                f25 = f27;
            }
            if (f26 < f29) {
                f26 = f29;
            }
            if (f30 <= 0.0f || f25 <= f30) {
                f30 = f25;
            }
            if (f31 <= 0.0f || f26 <= f31) {
                f31 = f26;
            }
            cell.actorWidth = Math.min((f23 - cell.computedPadLeft) - cell.computedPadRight, f30);
            cell.actorHeight = Math.min((f24 - cell.computedPadTop) - cell.computedPadBottom, f31);
            if (iIntValue == 1) {
                fArr4[i12] = Math.max(fArr4[i12], f23);
            }
            fArr5[i13] = Math.max(fArr5[i13], f24);
            i11++;
            table = this;
            cellArr = cellArr2;
            i10 = i14;
            height = f20;
            f11 = f21;
            i7 = i16;
            width = f22;
            fArr2 = fArr10;
            fArr = fArr11;
            f9 = f28;
            i6 = i18;
        }
        float f32 = width;
        float f33 = height;
        int i19 = i6;
        int i20 = i7;
        int i21 = i10;
        float f34 = f9;
        float f35 = f11;
        float[] fArr12 = fArr;
        Cell[] cellArr3 = cellArr;
        float[] fArr13 = table.expandWidth;
        float[] fArr14 = table.expandHeight;
        float f36 = 0.0f;
        for (int i22 = 0; i22 < i19; i22++) {
            f36 += fArr13[i22];
        }
        float f37 = 0.0f;
        if (f36 > 0.0f) {
            float f38 = f32 - f34;
            for (int i23 = 0; i23 < i19; i23++) {
                f38 -= fArr4[i23];
            }
            if (f38 > 0.0f) {
                float f39 = 0.0f;
                int i24 = 0;
                int i25 = 0;
                while (i24 < i19) {
                    float f40 = fArr13[i24];
                    if (f40 != f37) {
                        float f41 = (f40 * f38) / f36;
                        fArr4[i24] = fArr4[i24] + f41;
                        f39 += f41;
                        i25 = i24;
                    }
                    i24++;
                    f37 = 0.0f;
                }
                fArr4[i25] = (f38 - f39) + fArr4[i25];
            }
        }
        float f42 = 0.0f;
        for (int i26 = 0; i26 < i20; i26++) {
            f42 += fArr14[i26];
        }
        float f43 = 0.0f;
        if (f42 > 0.0f) {
            float f44 = f33 - f35;
            for (int i27 = 0; i27 < i20; i27++) {
                f44 -= fArr5[i27];
            }
            if (f44 > 0.0f) {
                float f45 = 0.0f;
                int i28 = 0;
                int i29 = 0;
                while (i28 < i20) {
                    float f46 = fArr14[i28];
                    if (f46 != f43) {
                        float f47 = (f46 * f44) / f42;
                        fArr5[i28] = fArr5[i28] + f47;
                        f45 += f47;
                        i29 = i28;
                    }
                    i28++;
                    f43 = 0.0f;
                }
                fArr5[i29] = (f44 - f45) + fArr5[i29];
            }
        }
        int i30 = i21;
        for (int i31 = 0; i31 < i30; i31++) {
            Cell cell2 = cellArr3[i31];
            int iIntValue2 = cell2.colspan.intValue();
            if (iIntValue2 != 1) {
                int i32 = cell2.column;
                int i33 = i32 + iIntValue2;
                float f48 = 0.0f;
                while (i32 < i33) {
                    f48 += fArr12[i32] - fArr4[i32];
                    i32++;
                }
                float fMax = (f48 - Math.max(0.0f, cell2.computedPadLeft + cell2.computedPadRight)) / iIntValue2;
                if (fMax > 0.0f) {
                    int i34 = cell2.column;
                    int i35 = iIntValue2 + i34;
                    while (i34 < i35) {
                        fArr4[i34] = fArr4[i34] + fMax;
                        i34++;
                    }
                }
            }
        }
        float f49 = f34;
        for (int i36 = 0; i36 < i19; i36++) {
            f49 += fArr4[i36];
        }
        float f50 = f35;
        for (int i37 = 0; i37 < i20; i37++) {
            f50 += fArr5[i37];
        }
        int i38 = table.align;
        if ((i38 & 16) != 0) {
            f5 = f32 - f49;
        } else if ((i38 & 8) == 0) {
            f5 = (f32 - f49) / 2.0f;
        } else {
            f4 = f3;
            if ((i38 & 4) == 0) {
                f7 = f33 - f50;
            } else {
                if ((i38 & 2) != 0) {
                    f6 = f2;
                    float f51 = f4;
                    float f52 = f6;
                    i2 = 0;
                    while (i2 < i30) {
                        Cell cell3 = cellArr3[i2];
                        int i39 = cell3.column;
                        int iIntValue3 = cell3.colspan.intValue() + i39;
                        float f53 = 0.0f;
                        while (i39 < iIntValue3) {
                            f53 += fArr4[i39];
                            i39++;
                        }
                        float f54 = cell3.computedPadLeft;
                        float f55 = f53 - (cell3.computedPadRight + f54);
                        float f56 = f51 + f54;
                        float fFloatValue = cell3.fillX.floatValue();
                        float fFloatValue2 = cell3.fillY.floatValue();
                        if (fFloatValue > 0.0f) {
                            i5 = i30;
                            cell3.actorWidth = Math.max(fFloatValue * f55, cell3.minWidth.get(cell3.actor));
                            float f57 = cell3.maxWidth.get(cell3.actor);
                            if (f57 > 0.0f) {
                                cell3.actorWidth = Math.min(cell3.actorWidth, f57);
                            }
                        } else {
                            i5 = i30;
                        }
                        if (fFloatValue2 > 0.0f) {
                            cell3.actorHeight = Math.max(((fArr5[cell3.row] * fFloatValue2) - cell3.computedPadTop) - cell3.computedPadBottom, cell3.minHeight.get(cell3.actor));
                            float f58 = cell3.maxHeight.get(cell3.actor);
                            if (f58 > 0.0f) {
                                cell3.actorHeight = Math.min(cell3.actorHeight, f58);
                            }
                        }
                        int iIntValue4 = cell3.align.intValue();
                        if ((iIntValue4 & 8) != 0) {
                            cell3.actorX = f56;
                        } else if ((iIntValue4 & 16) != 0) {
                            cell3.actorX = (f56 + f55) - cell3.actorWidth;
                        } else {
                            cell3.actorX = ((f55 - cell3.actorWidth) / 2.0f) + f56;
                        }
                        if ((iIntValue4 & 2) != 0) {
                            cell3.actorY = cell3.computedPadTop;
                        } else if ((iIntValue4 & 4) != 0) {
                            cell3.actorY = (fArr5[cell3.row] - cell3.actorHeight) - cell3.computedPadBottom;
                        } else {
                            cell3.actorY = (((fArr5[cell3.row] - cell3.actorHeight) + cell3.computedPadTop) - cell3.computedPadBottom) / 2.0f;
                            cell3.actorY = ((f33 - f52) - cell3.actorY) - cell3.actorHeight;
                            if (table.round) {
                                cell3.actorWidth = (float) Math.ceil(cell3.actorWidth);
                                cell3.actorHeight = (float) Math.ceil(cell3.actorHeight);
                                cell3.actorX = (float) Math.floor(cell3.actorX);
                                cell3.actorY = (float) Math.floor(cell3.actorY);
                            }
                            actor = cell3.actor;
                            if (actor == null) {
                                fArr3 = fArr4;
                                actor.setBounds(cell3.actorX, cell3.actorY, cell3.actorWidth, cell3.actorHeight);
                            } else {
                                fArr3 = fArr4;
                            }
                            if (cell3.endRow) {
                                f51 = f55 + cell3.computedPadRight + f56;
                            } else {
                                f52 += fArr5[cell3.row];
                                f51 = f4;
                            }
                            i2++;
                            fArr4 = fArr3;
                            i30 = i5;
                        }
                        cell3.actorY = ((f33 - f52) - cell3.actorY) - cell3.actorHeight;
                        if (table.round) {
                        }
                        actor = cell3.actor;
                        if (actor == null) {
                        }
                        if (cell3.endRow) {
                        }
                        i2++;
                        fArr4 = fArr3;
                        i30 = i5;
                    }
                    k0<Actor> children = getChildren();
                    Actor[] actorArr = children.f1749a;
                    i3 = children.f1750b;
                    for (i4 = 0; i4 < i3; i4++) {
                        Object obj = actorArr[i4];
                        if (obj instanceof Layout) {
                            ((Layout) obj).validate();
                        }
                    }
                    if (table.debug != Debug.none) {
                        table.addDebugRects(f4, f6, f49 - f34, f50 - f35);
                        return;
                    }
                    return;
                }
                f7 = (f33 - f50) / 2.0f;
            }
            f6 = f7 + f2;
            float f512 = f4;
            float f522 = f6;
            i2 = 0;
            while (i2 < i30) {
            }
            k0<Actor> children2 = getChildren();
            Actor[] actorArr2 = children2.f1749a;
            i3 = children2.f1750b;
            while (i4 < i3) {
            }
            if (table.debug != Debug.none) {
            }
        }
        f4 = f5 + f3;
        if ((i38 & 4) == 0) {
        }
        f6 = f7 + f2;
        float f5122 = f4;
        float f5222 = f6;
        i2 = 0;
        while (i2 < i30) {
        }
        k0<Actor> children22 = getChildren();
        Actor[] actorArr22 = children22.f1749a;
        i3 = children22.f1750b;
        while (i4 < i3) {
        }
        if (table.debug != Debug.none) {
        }
    }

    public Table left() {
        this.align = (this.align | 8) & (-17);
        return this;
    }

    public Table pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value;
        this.padLeft = value;
        this.padBottom = value;
        this.padRight = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value;
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value;
        this.sizeInvalid = true;
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i2, boolean z2) {
        Actor actorRemoveActorAt = super.removeActorAt(i2, z2);
        Cell cell = getCell(actorRemoveActorAt);
        if (cell != null) {
            cell.actor = null;
        }
        return actorRemoveActorAt;
    }

    public void reset() {
        clearChildren();
        this.padTop = backgroundTop;
        this.padLeft = backgroundLeft;
        this.padBottom = backgroundBottom;
        this.padRight = backgroundRight;
        this.align = 1;
        debug(Debug.none);
        this.cellDefaults.reset();
        int i2 = this.columnDefaults.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Cell cell = this.columnDefaults.get(i3);
            if (cell != null) {
                cellPool.free(cell);
            }
        }
        this.columnDefaults.clear();
    }

    public Table right() {
        this.align = (this.align | 16) & (-9);
        return this;
    }

    public Cell row() {
        a<Cell> aVar = this.cells;
        if (aVar.f1750b > 0) {
            if (!this.implicitEndRow) {
                if (aVar.k().endRow) {
                    return this.rowDefaults;
                }
                endRow();
            }
            invalidate();
        }
        this.implicitEndRow = false;
        Cell cell = this.rowDefaults;
        if (cell != null) {
            cellPool.free(cell);
        }
        Cell cellObtainCell = obtainCell();
        this.rowDefaults = cellObtainCell;
        cellObtainCell.clear();
        return this.rowDefaults;
    }

    public void setBackground(String str) {
        Skin skin = this.skin;
        if (skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        setBackground(skin.getDrawable(str));
    }

    public void setClip(boolean z2) {
        this.clip = z2;
        setTransform(z2);
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void setDebug(boolean z2) {
        debug(z2 ? Debug.all : Debug.none);
    }

    public void setRound(boolean z2) {
        this.round = z2;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Cell<Stack> stack(Actor... actorArr) {
        Stack stack = new Stack();
        if (actorArr != null) {
            for (Actor actor : actorArr) {
                stack.addActor(actor);
            }
        }
        return add(stack);
    }

    public Table top() {
        this.align = (this.align | 2) & (-5);
        return this;
    }

    public Table(Skin skin) {
        this.cells = new a<>(true, 4);
        this.columnDefaults = new a<>(true, 2);
        this.sizeInvalid = true;
        this.padTop = backgroundTop;
        this.padLeft = backgroundLeft;
        this.padBottom = backgroundBottom;
        this.padRight = backgroundRight;
        this.align = 1;
        this.debug = Debug.none;
        this.round = true;
        this.skin = skin;
        this.cellDefaults = obtainCell();
        setTransform(false);
        setTouchable(Touchable.childrenOnly);
    }

    public Table background(String str) {
        setBackground(str);
        return this;
    }

    public Table clip(boolean z2) {
        setClip(z2);
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public Table debug() {
        super.debug();
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Table debugAll() {
        super.debugAll();
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z2) {
        if (!super.removeActor(actor, z2)) {
            return false;
        }
        Cell cell = getCell(actor);
        if (cell == null) {
            return true;
        }
        cell.actor = null;
        return true;
    }

    public Table debug(Debug debug) {
        Debug debug2 = Debug.none;
        super.setDebug(debug != debug2);
        if (this.debug != debug) {
            this.debug = debug;
            if (debug == debug2) {
                clearDebugRects();
            } else {
                invalidate();
            }
        }
        return this;
    }

    public Table padBottom(float f2) {
        this.padBottom = Value.Fixed.valueOf(f2);
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(float f2) {
        this.padLeft = Value.Fixed.valueOf(f2);
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(float f2) {
        this.padRight = Value.Fixed.valueOf(f2);
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(float f2) {
        this.padTop = Value.Fixed.valueOf(f2);
        this.sizeInvalid = true;
        return this;
    }

    public void setBackground(Drawable drawable) {
        if (this.background == drawable) {
            return;
        }
        float padTop = getPadTop();
        float padLeft = getPadLeft();
        float padBottom = getPadBottom();
        float padRight = getPadRight();
        this.background = drawable;
        float padTop2 = getPadTop();
        float padLeft2 = getPadLeft();
        float padBottom2 = getPadBottom();
        float padRight2 = getPadRight();
        if (padTop + padBottom != padTop2 + padBottom2 || padLeft + padRight != padLeft2 + padRight2) {
            invalidateHierarchy();
        } else {
            if (padTop == padTop2 && padLeft == padLeft2 && padBottom == padBottom2 && padRight == padRight2) {
                return;
            }
            invalidate();
        }
    }

    public Table pad(Value value, Value value2, Value value3, Value value4) {
        if (value == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if (value3 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if (value4 != null) {
            this.padTop = value;
            this.padLeft = value2;
            this.padBottom = value3;
            this.padRight = value4;
            this.sizeInvalid = true;
            return this;
        }
        throw new IllegalArgumentException("right cannot be null.");
    }

    public Table pad(float f2) {
        pad(Value.Fixed.valueOf(f2));
        return this;
    }

    public Table pad(float f2, float f3, float f4, float f5) {
        this.padTop = Value.Fixed.valueOf(f2);
        this.padLeft = Value.Fixed.valueOf(f3);
        this.padBottom = Value.Fixed.valueOf(f4);
        this.padRight = Value.Fixed.valueOf(f5);
        this.sizeInvalid = true;
        return this;
    }

    public Table add(Actor... actorArr) {
        for (Actor actor : actorArr) {
            add(actor);
        }
        return this;
    }

    public Cell<Label> add(CharSequence charSequence) {
        if (this.skin != null) {
            return add(new Label(charSequence, this.skin));
        }
        throw new IllegalStateException("Table must have a skin set to use this method.");
    }

    public Cell<Label> add(CharSequence charSequence, String str) {
        if (this.skin != null) {
            return add(new Label(charSequence, (Label.LabelStyle) this.skin.get(str, Label.LabelStyle.class)));
        }
        throw new IllegalStateException("Table must have a skin set to use this method.");
    }

    public Cell<Label> add(CharSequence charSequence, String str, Color color) {
        if (this.skin != null) {
            return add(new Label(charSequence, new Label.LabelStyle(this.skin.getFont(str), color)));
        }
        throw new IllegalStateException("Table must have a skin set to use this method.");
    }

    public Cell<Label> add(CharSequence charSequence, String str, String str2) {
        if (this.skin != null) {
            return add(new Label(charSequence, new Label.LabelStyle(this.skin.getFont(str), this.skin.getColor(str2))));
        }
        throw new IllegalStateException("Table must have a skin set to use this method.");
    }

    public Cell add() {
        return add((Actor) null);
    }
}

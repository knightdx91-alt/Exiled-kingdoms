package com.badlogic.gdx.scenes.scene2d.ui;

import a0.j;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.o;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextArea extends TextField {
    int cursorLine;
    int firstLineShowing;
    private String lastText;
    o linesBreak;
    private int linesShowing;
    float moveOffset;
    private float prefRows;

    public class TextAreaListener extends TextField.TextFieldClickListener {
        public TextAreaListener() {
            super();
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected boolean checkFocusTraversal(char c2) {
            return TextArea.this.focusTraversal && c2 == '\t';
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected void goEnd(boolean z2) {
            if (!z2) {
                TextArea textArea = TextArea.this;
                if (textArea.cursorLine < textArea.getLines()) {
                    TextArea textArea2 = TextArea.this;
                    int i2 = textArea2.cursorLine;
                    int i3 = (i2 * 2) + 1;
                    o oVar = textArea2.linesBreak;
                    if (i3 < oVar.f1850b) {
                        textArea2.cursor = oVar.d((i2 * 2) + 1);
                        return;
                    }
                    return;
                }
            }
            TextArea textArea3 = TextArea.this;
            textArea3.cursor = textArea3.text.length();
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected void goHome(boolean z2) {
            if (z2) {
                TextArea.this.cursor = 0;
                return;
            }
            TextArea textArea = TextArea.this;
            int i2 = textArea.cursorLine;
            int i3 = i2 * 2;
            o oVar = textArea.linesBreak;
            if (i3 < oVar.f1850b) {
                textArea.cursor = oVar.d(i2 * 2);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i2) {
            boolean zKeyDown = super.keyDown(inputEvent, i2);
            if (!TextArea.this.hasKeyboardFocus()) {
                return zKeyDown;
            }
            boolean z2 = Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
            if (i2 == 20) {
                if (z2) {
                    TextArea textArea = TextArea.this;
                    if (!textArea.hasSelection) {
                        textArea.selectionStart = textArea.cursor;
                        textArea.hasSelection = true;
                    }
                } else {
                    TextArea.this.clearSelection();
                }
                TextArea textArea2 = TextArea.this;
                textArea2.moveCursorLine(textArea2.cursorLine + 1);
            } else {
                if (i2 != 19) {
                    TextArea.this.moveOffset = -1.0f;
                    TextArea.this.showCursor();
                    return true;
                }
                if (z2) {
                    TextArea textArea3 = TextArea.this;
                    if (!textArea3.hasSelection) {
                        textArea3.selectionStart = textArea3.cursor;
                        textArea3.hasSelection = true;
                    }
                } else {
                    TextArea.this.clearSelection();
                }
                TextArea textArea4 = TextArea.this;
                textArea4.moveCursorLine(textArea4.cursorLine - 1);
            }
            scheduleKeyRepeatTask(i2);
            TextArea.this.showCursor();
            return true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c2) {
            boolean zKeyTyped = super.keyTyped(inputEvent, c2);
            TextArea.this.showCursor();
            return zKeyTyped;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldClickListener
        protected void setCursorPosition(float f2, float f3) {
            TextArea textArea = TextArea.this;
            textArea.moveOffset = -1.0f;
            TextField.TextFieldStyle textFieldStyle = textArea.style;
            Drawable drawable = textFieldStyle.background;
            BitmapFont bitmapFont = textFieldStyle.font;
            float height = textArea.getHeight();
            if (drawable != null) {
                height -= drawable.getTopHeight();
                f2 -= drawable.getLeftWidth();
            }
            float fMax = Math.max(0.0f, f2);
            if (drawable != null) {
                f3 -= drawable.getTopHeight();
            }
            TextArea textArea2 = TextArea.this;
            int iFloor = (int) Math.floor((height - f3) / bitmapFont.getLineHeight());
            TextArea textArea3 = TextArea.this;
            textArea2.cursorLine = iFloor + textArea3.firstLineShowing;
            textArea3.cursorLine = Math.max(0, Math.min(textArea3.cursorLine, textArea3.getLines() - 1));
            super.setCursorPosition(fMax, f3);
            TextArea.this.updateCurrentLine();
        }
    }

    public TextArea(String str, Skin skin) {
        super(str, skin);
    }

    private int calculateCurrentLineIndex(int i2) {
        int i3 = 0;
        while (true) {
            o oVar = this.linesBreak;
            if (i3 >= oVar.f1850b || i2 <= oVar.f1849a[i3]) {
                break;
            }
            i3++;
        }
        return i3;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void calculateOffsets() {
        super.calculateOffsets();
        if (this.text.equals(this.lastText)) {
            return;
        }
        this.lastText = this.text;
        BitmapFont bitmapFont = this.style.font;
        float width = getWidth();
        Drawable drawable = this.style.background;
        float rightWidth = width - (drawable != null ? this.style.background.getRightWidth() + drawable.getLeftWidth() : 0.0f);
        this.linesBreak.f1850b = 0;
        c0 c0VarC = d0.c(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) c0VarC.obtain();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.text.length(); i4++) {
            char cCharAt = this.text.charAt(i4);
            if (cCharAt == '\r' || cCharAt == '\n') {
                this.linesBreak.a(i2);
                this.linesBreak.a(i4);
                i2 = i4 + 1;
            } else {
                if (!continueCursor(i4, 0)) {
                    i3 = i4;
                }
                glyphLayout.setText(bitmapFont, this.text.subSequence(i2, i4 + 1));
                if (glyphLayout.width > rightWidth) {
                    if (i2 >= i3) {
                        i3 = i4 - 1;
                    }
                    this.linesBreak.a(i2);
                    i3++;
                    this.linesBreak.a(i3);
                    i2 = i3;
                }
            }
        }
        c0VarC.free(glyphLayout);
        if (i2 < this.text.length()) {
            this.linesBreak.a(i2);
            this.linesBreak.a(this.text.length());
        }
        showCursor();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected boolean continueCursor(int i2, int i3) {
        int[] iArr;
        int i4;
        int iCalculateCurrentLineIndex = calculateCurrentLineIndex(i2 + i3);
        if (super.continueCursor(i2, i3)) {
            if (iCalculateCurrentLineIndex >= 0) {
                o oVar = this.linesBreak;
                if (iCalculateCurrentLineIndex >= oVar.f1850b - 2 || (i4 = (iArr = oVar.f1849a)[iCalculateCurrentLineIndex + 1]) != i2 || i4 == iArr[iCalculateCurrentLineIndex + 2]) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected InputListener createInputListener() {
        return new TextAreaListener();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawCursor(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        drawable.draw(batch, getCursorX() + f2, getCursorY() + f3, drawable.getMinWidth(), bitmapFont.getLineHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawSelection(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        float f4;
        float f5;
        int i2 = this.firstLineShowing * 2;
        int iMin = Math.min(this.cursor, this.selectionStart);
        int iMax = Math.max(this.cursor, this.selectionStart);
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        float lineHeight = this.style.font.getLineHeight();
        float lineHeight2 = 0.0f;
        while (true) {
            int i3 = i2 + 1;
            o oVar = this.linesBreak;
            if (i3 >= oVar.f1850b || i2 >= (this.firstLineShowing + this.linesShowing) * 2) {
                return;
            }
            int iD = oVar.d(i2);
            int iD2 = this.linesBreak.d(i3);
            if ((iMin >= iD || iMin >= iD2 || iMax >= iD || iMax >= iD2) && (iMin <= iD || iMin <= iD2 || iMax <= iD || iMax <= iD2)) {
                int iMax2 = Math.max(iD, iMin);
                int iMin2 = Math.min(iD2, iMax);
                BitmapFont.Glyph glyph = data.getGlyph(this.displayText.charAt(iD));
                if (glyph == null) {
                    f4 = 0.0f;
                    f5 = 0.0f;
                } else if (iMax2 == iD) {
                    f5 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    f4 = 0.0f;
                } else {
                    f4 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    f5 = 0.0f;
                }
                drawable.draw(batch, f2 + (this.glyphPositions.e(iMax2) - this.glyphPositions.e(iD)) + f4, (f3 - lineHeight) - lineHeight2, (this.glyphPositions.e(iMin2) - this.glyphPositions.e(iMax2)) + f5, bitmapFont.getLineHeight());
            }
            lineHeight2 += bitmapFont.getLineHeight();
            i2 += 2;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawText(Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        float lineHeight = (-(this.style.font.getLineHeight() - this.textHeight)) / 2.0f;
        for (int i2 = this.firstLineShowing * 2; i2 < (this.firstLineShowing + this.linesShowing) * 2; i2 += 2) {
            o oVar = this.linesBreak;
            if (i2 >= oVar.f1850b) {
                return;
            }
            int[] iArr = oVar.f1849a;
            bitmapFont.draw(batch, this.displayText, f2, f3 + lineHeight, iArr[i2], iArr[i2 + 1], 0.0f, 8, false);
            lineHeight -= bitmapFont.getLineHeight();
        }
    }

    public int getCursorLine() {
        return this.cursorLine;
    }

    public float getCursorX() {
        BitmapFont.BitmapFontData data = this.style.font.getData();
        float fE = 0.0f;
        if (this.cursor < this.glyphPositions.f1824b) {
            int i2 = this.cursorLine;
            int i3 = i2 * 2;
            o oVar = this.linesBreak;
            if (i3 < oVar.f1850b) {
                int i4 = oVar.f1849a[i2 * 2];
                BitmapFont.Glyph glyph = data.getGlyph(this.displayText.charAt(i4));
                if (glyph != null && !glyph.fixedWidth) {
                    fE = ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                }
                fE += this.glyphPositions.e(this.cursor) - this.glyphPositions.e(i4);
            }
        }
        return fE + data.cursorX;
    }

    public float getCursorY() {
        return this.style.font.getLineHeight() * (-((this.cursorLine - this.firstLineShowing) + 1));
    }

    public int getFirstLineShowing() {
        return this.firstLineShowing;
    }

    public int getLines() {
        return (newLineAtEnd() ? 1 : 0) + (this.linesBreak.f1850b / 2);
    }

    public int getLinesShowing() {
        return this.linesShowing;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField, com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.prefRows <= 0.0f) {
            return super.getPrefHeight();
        }
        float lineHeight = this.style.font.getLineHeight() * this.prefRows;
        a0.o oVar = j.f69a;
        float f2 = 16384 - ((int) (16384.0d - ((double) lineHeight)));
        Drawable drawable = this.style.background;
        if (drawable == null) {
            return f2;
        }
        return Math.max(this.style.background.getTopHeight() + drawable.getBottomHeight() + f2, this.style.background.getMinHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected float getTextY(BitmapFont bitmapFont, Drawable drawable) {
        float height = getHeight();
        if (drawable != null) {
            height -= drawable.getTopHeight();
        }
        return bitmapFont.usesIntegerPositions() ? (int) height : height;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void initialize() {
        super.initialize();
        this.writeEnters = true;
        this.linesBreak = new o();
        this.cursorLine = 0;
        this.firstLineShowing = 0;
        this.moveOffset = -1.0f;
        this.linesShowing = 0;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected int letterUnderCursor(float f2) {
        o oVar = this.linesBreak;
        int i2 = oVar.f1850b;
        if (i2 <= 0) {
            return 0;
        }
        int i3 = this.cursorLine;
        if (i3 * 2 >= i2) {
            return this.text.length();
        }
        float[] fArr = this.glyphPositions.f1823a;
        int[] iArr = oVar.f1849a;
        int i4 = iArr[i3 * 2];
        float f3 = f2 + fArr[i4];
        int i5 = iArr[(i3 * 2) + 1];
        while (i4 < i5 && fArr[i4] <= f3) {
            i4++;
        }
        return (i4 <= 0 || fArr[i4] - f3 > f3 - fArr[i4 + (-1)]) ? Math.max(0, i4 - 1) : i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void moveCursor(boolean z2, boolean z3) {
        int i2 = z2 ? 1 : -1;
        int i3 = this.cursorLine;
        int i4 = (i3 * 2) + i2;
        if (i4 >= 0) {
            int i5 = i4 + 1;
            o oVar = this.linesBreak;
            if (i5 < oVar.f1850b) {
                int[] iArr = oVar.f1849a;
                int i6 = iArr[i4];
                int i7 = this.cursor;
                if (i6 == i7 && iArr[i5] == i7) {
                    this.cursorLine = i3 + i2;
                    if (z3) {
                        super.moveCursor(z2, z3);
                    }
                    showCursor();
                } else {
                    super.moveCursor(z2, z3);
                }
            }
        }
        updateCurrentLine();
    }

    public void moveCursorLine(int i2) {
        if (i2 < 0) {
            this.cursorLine = 0;
            this.cursor = 0;
            this.moveOffset = -1.0f;
            return;
        }
        if (i2 >= getLines()) {
            int lines = getLines() - 1;
            this.cursor = this.text.length();
            if (i2 > getLines() || lines == this.cursorLine) {
                this.moveOffset = -1.0f;
            }
            this.cursorLine = lines;
            return;
        }
        int i3 = this.cursorLine;
        if (i2 != i3) {
            if (this.moveOffset < 0.0f) {
                this.moveOffset = this.linesBreak.f1850b > i3 * 2 ? this.glyphPositions.e(this.cursor) - this.glyphPositions.e(this.linesBreak.d(this.cursorLine * 2)) : 0.0f;
            }
            this.cursorLine = i2;
            int i4 = i2 * 2;
            o oVar = this.linesBreak;
            this.cursor = i4 >= oVar.f1850b ? this.text.length() : oVar.d(i2 * 2);
            while (this.cursor < this.text.length() && this.cursor <= this.linesBreak.d((this.cursorLine * 2) + 1) - 1 && this.glyphPositions.e(this.cursor) - this.glyphPositions.e(this.linesBreak.d(this.cursorLine * 2)) < this.moveOffset) {
                this.cursor++;
            }
            showCursor();
        }
    }

    public boolean newLineAtEnd() {
        if (this.text.length() != 0) {
            String str = this.text;
            if (str.charAt(str.length() - 1) == '\n') {
                return true;
            }
            String str2 = this.text;
            if (str2.charAt(str2.length() - 1) == '\r') {
                return true;
            }
        }
        return false;
    }

    public void setPrefRows(float f2) {
        this.prefRows = f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setSelection(int i2, int i3) {
        super.setSelection(i2, i3);
        updateCurrentLine();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setStyle(TextField.TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textFieldStyle;
        this.textHeight = textFieldStyle.font.getCapHeight() - textFieldStyle.font.getDescent();
        if (this.text != null) {
            updateDisplayText();
        }
        invalidateHierarchy();
    }

    void showCursor() {
        updateCurrentLine();
        updateFirstLineShowing();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        float topHeight;
        this.lastText = null;
        TextField.TextFieldStyle textFieldStyle = this.style;
        BitmapFont bitmapFont = textFieldStyle.font;
        Drawable drawable = textFieldStyle.background;
        float height = getHeight();
        if (drawable == null) {
            topHeight = 0.0f;
        } else {
            topHeight = drawable.getTopHeight() + drawable.getBottomHeight();
        }
        this.linesShowing = (int) Math.floor((height - topHeight) / bitmapFont.getLineHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void updateCurrentLine() {
        int iCalculateCurrentLineIndex = calculateCurrentLineIndex(this.cursor);
        int i2 = iCalculateCurrentLineIndex / 2;
        if (iCalculateCurrentLineIndex % 2 != 0) {
            int i3 = iCalculateCurrentLineIndex + 1;
            o oVar = this.linesBreak;
            if (i3 < oVar.f1850b) {
                int i4 = this.cursor;
                int[] iArr = oVar.f1849a;
                int i5 = iArr[iCalculateCurrentLineIndex];
                if (i4 != i5 || iArr[i3] != i5) {
                    if (i2 < this.linesBreak.f1850b / 2 || this.text.length() == 0) {
                        this.cursorLine = i2;
                    } else {
                        if (this.text.charAt(r0.length() - 1) != '\n') {
                            if (this.text.charAt(r0.length() - 1) == '\r') {
                            }
                        }
                    }
                }
            }
        }
        updateFirstLineShowing();
    }

    void updateFirstLineShowing() {
        int i2 = this.cursorLine;
        int i3 = this.firstLineShowing;
        if (i2 == i3) {
            return;
        }
        int i4 = i2 >= i3 ? 1 : -1;
        while (true) {
            int i5 = this.firstLineShowing;
            int i6 = this.cursorLine;
            if (i5 <= i6 && (this.linesShowing + i5) - 1 >= i6) {
                return;
            } else {
                this.firstLineShowing = i5 + i4;
            }
        }
    }

    public TextArea(String str, Skin skin, String str2) {
        super(str, skin, str2);
    }

    public TextArea(String str, TextField.TextFieldStyle textFieldStyle) {
        super(str, textFieldStyle);
    }
}

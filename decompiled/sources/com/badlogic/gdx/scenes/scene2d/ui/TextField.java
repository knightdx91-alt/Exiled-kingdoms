package com.badlogic.gdx.scenes.scene2d.ui;

import a0.o;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.f;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.q0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextField extends Widget implements Disableable {
    protected static final char BACKSPACE = '\b';
    protected static final char BULLET = 149;
    protected static final char CARRIAGE_RETURN = '\r';
    protected static final char DELETE = 127;
    protected static final char NEWLINE = '\n';
    protected static final char TAB = '\t';
    final q0.a blinkTask;
    float blinkTime;
    f clipboard;
    protected int cursor;
    boolean cursorOn;
    boolean disabled;
    protected CharSequence displayText;
    TextFieldFilter filter;
    boolean focusTraversal;
    boolean focused;
    protected float fontOffset;
    protected final j glyphPositions;
    protected boolean hasSelection;
    InputListener inputListener;
    final KeyRepeatTask keyRepeatTask;
    OnscreenKeyboard keyboard;
    long lastChangeTime;
    protected final GlyphLayout layout;
    TextFieldListener listener;
    private int maxLength;
    private String messageText;
    boolean onlyFontChars;
    private StringBuilder passwordBuffer;
    private char passwordCharacter;
    boolean passwordMode;
    boolean programmaticChangeEvents;
    float renderOffset;
    protected int selectionStart;
    private float selectionWidth;
    private float selectionX;
    TextFieldStyle style;
    protected String text;
    private int textHAlign;
    protected float textHeight;
    protected float textOffset;
    String undoText;
    private int visibleTextEnd;
    private int visibleTextStart;
    protected boolean writeEnters;
    private static final q tmp1 = new q();
    private static final q tmp2 = new q();
    private static final q tmp3 = new q();
    public static float keyRepeatInitialTime = 0.4f;
    public static float keyRepeatTime = 0.1f;

    public static class DefaultOnscreenKeyboard implements OnscreenKeyboard {
        @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard
        public void show(boolean z2) {
            Gdx.input.setOnscreenKeyboardVisible(z2);
        }
    }

    class KeyRepeatTask extends q0.a {
        int keycode;

        KeyRepeatTask() {
        }

        @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
        public void run() {
            if (TextField.this.getStage() == null) {
                cancel();
            } else {
                TextField.this.inputListener.keyDown(null, this.keycode);
            }
        }
    }

    public interface OnscreenKeyboard {
        void show(boolean z2);
    }

    public class TextFieldClickListener extends ClickListener {
        public TextFieldClickListener() {
        }

        protected boolean checkFocusTraversal(char c2) {
            return TextField.this.focusTraversal && (c2 == '\t' || ((c2 == '\r' || c2 == '\n') && (UIUtils.isAndroid || UIUtils.isIos)));
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent, float f2, float f3) {
            int tapCount = getTapCount() % 4;
            if (tapCount == 0) {
                TextField.this.clearSelection();
            }
            if (tapCount == 2) {
                int[] iArrWordUnderCursor = TextField.this.wordUnderCursor(f2);
                TextField.this.setSelection(iArrWordUnderCursor[0], iArrWordUnderCursor[1]);
            }
            if (tapCount == 3) {
                TextField.this.selectAll();
            }
        }

        protected void goEnd(boolean z2) {
            TextField textField = TextField.this;
            textField.cursor = textField.text.length();
        }

        protected void goHome(boolean z2) {
            TextField.this.cursor = 0;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i2) {
            boolean z2;
            boolean z3;
            TextField textField = TextField.this;
            if (textField.disabled) {
                return false;
            }
            textField.cursorOn = textField.focused;
            textField.blinkTask.cancel();
            TextField textField2 = TextField.this;
            if (textField2.focused) {
                q0.a aVar = textField2.blinkTask;
                float f2 = textField2.blinkTime;
                q0.c(aVar, f2, f2);
            }
            if (!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            boolean zCtrl = UIUtils.ctrl();
            boolean z4 = true;
            boolean z5 = zCtrl && !TextField.this.passwordMode;
            if (zCtrl) {
                if (i2 == 29) {
                    TextField.this.selectAll();
                    return true;
                }
                if (i2 != 31) {
                    if (i2 == 50) {
                        TextField textField3 = TextField.this;
                        textField3.paste(textField3.clipboard.getContents(), true);
                        z3 = true;
                    } else {
                        if (i2 == 52) {
                            TextField.this.cut(true);
                            return true;
                        }
                        if (i2 == 54) {
                            TextField textField4 = TextField.this;
                            String str = textField4.text;
                            textField4.setText(textField4.undoText);
                            TextField textField5 = TextField.this;
                            textField5.undoText = str;
                            textField5.updateDisplayText();
                            return true;
                        }
                        if (i2 != 133) {
                            z3 = false;
                        }
                    }
                    z2 = z3;
                }
                TextField.this.copy();
                return true;
            }
            z2 = true;
            z3 = false;
            if (UIUtils.shift()) {
                if (i2 == 112) {
                    TextField.this.cut(true);
                } else if (i2 == 133) {
                    TextField textField6 = TextField.this;
                    textField6.paste(textField6.clipboard.getContents(), true);
                }
                TextField textField7 = TextField.this;
                int i3 = textField7.cursor;
                if (i2 == 3) {
                    goHome(z5);
                } else if (i2 != 132) {
                    if (i2 != 21) {
                        if (i2 == 22) {
                            textField7.moveCursor(true, z5);
                        }
                        z4 = z3;
                    } else {
                        textField7.moveCursor(false, z5);
                    }
                    z3 = true;
                } else {
                    goEnd(z5);
                }
                TextField textField8 = TextField.this;
                if (!textField8.hasSelection) {
                    textField8.selectionStart = i3;
                    textField8.hasSelection = true;
                }
                z2 = true;
                z4 = z3;
            } else {
                if (i2 == 3) {
                    goHome(z5);
                    TextField.this.clearSelection();
                } else if (i2 != 132) {
                    if (i2 != 21) {
                        if (i2 == 22) {
                            TextField.this.moveCursor(true, z5);
                            TextField.this.clearSelection();
                        }
                        z4 = z3;
                    } else {
                        TextField.this.moveCursor(false, z5);
                        TextField.this.clearSelection();
                    }
                    z2 = true;
                } else {
                    goEnd(z5);
                    TextField.this.clearSelection();
                }
                z2 = true;
                z4 = z3;
            }
            TextField textField9 = TextField.this;
            int i4 = textField9.cursor;
            int length = textField9.text.length();
            o oVar = a0.j.f69a;
            textField9.cursor = i4 >= 0 ? i4 > length ? length : i4 : 0;
            if (z4) {
                scheduleKeyRepeatTask(i2);
            }
            return z2;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c2) {
            TextField textField;
            TextFieldFilter textFieldFilter;
            TextField textField2 = TextField.this;
            if (textField2.disabled) {
                return false;
            }
            if (c2 != '\r') {
                switch (c2) {
                    case '\b':
                    case '\t':
                    case '\n':
                        break;
                    default:
                        if (c2 < ' ') {
                            return false;
                        }
                        break;
                }
            }
            if (!textField2.hasKeyboardFocus()) {
                return false;
            }
            if (UIUtils.isMac && Gdx.input.isKeyPressed(63)) {
                return true;
            }
            if (checkFocusTraversal(c2)) {
                TextField.this.next(UIUtils.shift());
            } else {
                boolean z2 = c2 == '\r' || c2 == '\n';
                boolean z3 = c2 == 127;
                boolean z4 = c2 == '\b';
                TextField textField3 = TextField.this;
                boolean z5 = z2 ? textField3.writeEnters : !textField3.onlyFontChars || textField3.style.font.getData().hasGlyph(c2);
                boolean z6 = z4 || z3;
                if (z5 || z6) {
                    TextField textField4 = TextField.this;
                    String str = textField4.text;
                    int i2 = textField4.cursor;
                    if (z6) {
                        if (textField4.hasSelection) {
                            textField4.cursor = textField4.delete(false);
                        } else {
                            if (z4 && i2 > 0) {
                                StringBuilder sb = new StringBuilder();
                                TextField textField5 = TextField.this;
                                sb.append(textField5.text.substring(0, textField5.cursor - 1));
                                TextField textField6 = TextField.this;
                                String str2 = textField6.text;
                                int i3 = textField6.cursor;
                                textField6.cursor = i3 - 1;
                                sb.append(str2.substring(i3));
                                textField4.text = sb.toString();
                                TextField.this.renderOffset = 0.0f;
                            }
                            if (z3) {
                                TextField textField7 = TextField.this;
                                if (textField7.cursor < textField7.text.length()) {
                                    TextField textField8 = TextField.this;
                                    StringBuilder sb2 = new StringBuilder();
                                    TextField textField9 = TextField.this;
                                    sb2.append(textField9.text.substring(0, textField9.cursor));
                                    TextField textField10 = TextField.this;
                                    sb2.append(textField10.text.substring(textField10.cursor + 1));
                                    textField8.text = sb2.toString();
                                }
                            }
                        }
                    }
                    if (z5 && !z6) {
                        if (!z2 && (textFieldFilter = (textField = TextField.this).filter) != null && !textFieldFilter.acceptChar(textField, c2)) {
                            return true;
                        }
                        TextField textField11 = TextField.this;
                        int length = textField11.text.length();
                        TextField textField12 = TextField.this;
                        if (!textField11.withinMaxLength(length - (textField12.hasSelection ? Math.abs(textField12.cursor - textField12.selectionStart) : 0))) {
                            return true;
                        }
                        TextField textField13 = TextField.this;
                        if (textField13.hasSelection) {
                            textField13.cursor = textField13.delete(false);
                        }
                        String strValueOf = z2 ? "\n" : String.valueOf(c2);
                        TextField textField14 = TextField.this;
                        int i4 = textField14.cursor;
                        textField14.cursor = i4 + 1;
                        textField14.text = textField14.insert(i4, strValueOf, textField14.text);
                    }
                    TextField textField15 = TextField.this;
                    String str3 = textField15.undoText;
                    if (textField15.changeText(str, textField15.text)) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        long j2 = jCurrentTimeMillis - 750;
                        TextField textField16 = TextField.this;
                        if (j2 > textField16.lastChangeTime) {
                            textField16.undoText = str;
                        }
                        textField16.lastChangeTime = jCurrentTimeMillis;
                        textField16.updateDisplayText();
                    } else {
                        TextField.this.cursor = i2;
                    }
                }
            }
            TextField textField17 = TextField.this;
            TextFieldListener textFieldListener = textField17.listener;
            if (textFieldListener != null) {
                textFieldListener.keyTyped(textField17, c2);
            }
            return true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyUp(InputEvent inputEvent, int i2) {
            TextField textField = TextField.this;
            if (textField.disabled) {
                return false;
            }
            textField.keyRepeatTask.cancel();
            return true;
        }

        protected void scheduleKeyRepeatTask(int i2) {
            if (TextField.this.keyRepeatTask.isScheduled() && TextField.this.keyRepeatTask.keycode == i2) {
                return;
            }
            KeyRepeatTask keyRepeatTask = TextField.this.keyRepeatTask;
            keyRepeatTask.keycode = i2;
            keyRepeatTask.cancel();
            q0.c(TextField.this.keyRepeatTask, TextField.keyRepeatInitialTime, TextField.keyRepeatTime);
        }

        protected void setCursorPosition(float f2, float f3) {
            TextField textField = TextField.this;
            textField.cursor = textField.letterUnderCursor(f2);
            TextField textField2 = TextField.this;
            textField2.cursorOn = textField2.focused;
            textField2.blinkTask.cancel();
            TextField textField3 = TextField.this;
            if (textField3.focused) {
                q0.a aVar = textField3.blinkTask;
                float f4 = textField3.blinkTime;
                q0.c(aVar, f4, f4);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            if (!super.touchDown(inputEvent, f2, f3, i2, i3)) {
                return false;
            }
            if (i2 == 0 && i3 != 0) {
                return false;
            }
            if (TextField.this.disabled) {
                return true;
            }
            setCursorPosition(f2, f3);
            TextField textField = TextField.this;
            textField.selectionStart = textField.cursor;
            Stage stage = textField.getStage();
            if (stage != null) {
                stage.setKeyboardFocus(TextField.this);
            }
            TextField.this.keyboard.show(true);
            TextField.this.hasSelection = true;
            return true;
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public void touchDragged(InputEvent inputEvent, float f2, float f3, int i2) {
            super.touchDragged(inputEvent, f2, f3, i2);
            setCursorPosition(f2, f3);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
        public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
            TextField textField = TextField.this;
            if (textField.selectionStart == textField.cursor) {
                textField.hasSelection = false;
            }
            super.touchUp(inputEvent, f2, f3, i2, i3);
        }
    }

    public interface TextFieldFilter {

        public static class DigitsOnlyFilter implements TextFieldFilter {
            @Override // com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter
            public boolean acceptChar(TextField textField, char c2) {
                return Character.isDigit(c2);
            }
        }

        boolean acceptChar(TextField textField, char c2);
    }

    public interface TextFieldListener {
        void keyTyped(TextField textField, char c2);
    }

    public static class TextFieldStyle {
        public Drawable background;
        public Drawable cursor;
        public Drawable disabledBackground;
        public Color disabledFontColor;
        public Drawable focusedBackground;
        public Color focusedFontColor;
        public BitmapFont font;
        public Color fontColor;
        public BitmapFont messageFont;
        public Color messageFontColor;
        public Drawable selection;

        public TextFieldStyle() {
        }

        public TextFieldStyle(BitmapFont bitmapFont, Color color, Drawable drawable, Drawable drawable2, Drawable drawable3) {
            this.font = bitmapFont;
            this.fontColor = color;
            this.cursor = drawable;
            this.selection = drawable2;
            this.background = drawable3;
        }

        public TextFieldStyle(TextFieldStyle textFieldStyle) {
            this.font = textFieldStyle.font;
            if (textFieldStyle.fontColor != null) {
                this.fontColor = new Color(textFieldStyle.fontColor);
            }
            if (textFieldStyle.focusedFontColor != null) {
                this.focusedFontColor = new Color(textFieldStyle.focusedFontColor);
            }
            if (textFieldStyle.disabledFontColor != null) {
                this.disabledFontColor = new Color(textFieldStyle.disabledFontColor);
            }
            this.background = textFieldStyle.background;
            this.focusedBackground = textFieldStyle.focusedBackground;
            this.disabledBackground = textFieldStyle.disabledBackground;
            this.cursor = textFieldStyle.cursor;
            this.selection = textFieldStyle.selection;
            this.messageFont = textFieldStyle.messageFont;
            if (textFieldStyle.messageFontColor != null) {
                this.messageFontColor = new Color(textFieldStyle.messageFontColor);
            }
        }
    }

    public TextField(String str, Skin skin) {
        this(str, (TextFieldStyle) skin.get(TextFieldStyle.class));
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private TextField findNextTextField(a<Actor> aVar, TextField textField, q qVar, q qVar2, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        int i2 = aVar.f1750b;
        TextField textFieldFindNextTextField = textField;
        for (int i3 = 0; i3 < i2; i3++) {
            Actor actor = aVar.get(i3);
            if (actor instanceof TextField) {
                if (actor != this) {
                    TextField textField2 = (TextField) actor;
                    if (!textField2.isDisabled() && textField2.focusTraversal && textField2.ascendantsVisible()) {
                        Group parent = actor.getParent();
                        q qVar3 = tmp3;
                        float x2 = actor.getX();
                        float y2 = actor.getY();
                        qVar3.f91a = x2;
                        qVar3.f92b = y2;
                        q qVarLocalToStageCoordinates = parent.localToStageCoordinates(qVar3);
                        float f2 = qVarLocalToStageCoordinates.f92b;
                        float f3 = qVar2.f92b;
                        boolean z6 = true;
                        if (f2 == f3) {
                            z3 = false;
                            if (f2 != f3) {
                                z4 = false;
                                if (!z3 || z4) {
                                    if (textFieldFindNextTextField == null) {
                                        float f4 = qVar.f92b;
                                        if (f2 != f4) {
                                            z5 = (f2 > f4) ^ z2;
                                            if (!z5) {
                                                if (f2 != qVar.f92b) {
                                                    z6 = false;
                                                    z5 = z6;
                                                } else {
                                                    if (!((qVarLocalToStageCoordinates.f91a < qVar.f91a) ^ z2)) {
                                                    }
                                                    z5 = z6;
                                                }
                                            }
                                            if (!z5) {
                                                qVar.getClass();
                                                qVar.f91a = qVarLocalToStageCoordinates.f91a;
                                                qVar.f92b = qVarLocalToStageCoordinates.f92b;
                                                textFieldFindNextTextField = textField2;
                                            }
                                        }
                                        if (!z5) {
                                        }
                                        if (!z5) {
                                        }
                                    }
                                }
                            } else {
                                if ((qVarLocalToStageCoordinates.f91a > qVar2.f91a) ^ z2) {
                                    z4 = true;
                                }
                                if (!z3) {
                                    if (textFieldFindNextTextField == null) {
                                    }
                                }
                            }
                        } else {
                            if ((f2 < f3) ^ z2) {
                                z3 = true;
                            }
                            if (f2 != f3) {
                            }
                        }
                    }
                }
            } else if (actor instanceof Group) {
                textFieldFindNextTextField = findNextTextField(((Group) actor).getChildren(), textFieldFindNextTextField, qVar, qVar2, z2);
            }
        }
        return textFieldFindNextTextField;
    }

    public void appendText(String str) {
        if (str == null) {
            str = "";
        }
        clearSelection();
        this.cursor = this.text.length();
        paste(str, this.programmaticChangeEvents);
    }

    protected void calculateOffsets() {
        float width = getWidth();
        Drawable backgroundDrawable = getBackgroundDrawable();
        if (backgroundDrawable != null) {
            width -= backgroundDrawable.getRightWidth() + backgroundDrawable.getLeftWidth();
        }
        j jVar = this.glyphPositions;
        int i2 = jVar.f1824b;
        float[] fArr = jVar.f1823a;
        float f2 = fArr[Math.max(0, this.cursor - 1)];
        float f3 = this.renderOffset;
        float f4 = f2 + f3;
        float f5 = 0.0f;
        if (f4 <= 0.0f) {
            this.renderOffset = f3 - f4;
        } else {
            float f6 = fArr[Math.min(i2 - 1, this.cursor + 1)] - width;
            if ((-this.renderOffset) < f6) {
                this.renderOffset = -f6;
            }
        }
        float f7 = fArr[i2 - 1];
        int i3 = i2 - 2;
        float f8 = 0.0f;
        while (i3 >= 0) {
            float f9 = fArr[i3];
            if (f7 - f9 > width) {
                break;
            }
            i3--;
            f8 = f9;
        }
        if ((-this.renderOffset) > f8) {
            this.renderOffset = -f8;
        }
        this.visibleTextStart = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                break;
            }
            float f10 = fArr[i4];
            if (f10 >= (-this.renderOffset)) {
                this.visibleTextStart = i4;
                f5 = f10;
                break;
            }
            i4++;
        }
        int i5 = this.visibleTextStart + 1;
        float f11 = width - this.renderOffset;
        int iMin = Math.min(this.displayText.length(), i2);
        while (i5 <= iMin && fArr[i5] <= f11) {
            i5++;
        }
        int iMax = Math.max(0, i5 - 1);
        this.visibleTextEnd = iMax;
        int i6 = this.textHAlign;
        if ((i6 & 8) == 0) {
            this.textOffset = ((width - fArr[iMax]) - this.fontOffset) + f5;
            if ((i6 & 1) != 0) {
                this.textOffset = Math.round(r2 * 0.5f);
            }
        } else {
            this.textOffset = f5 + this.renderOffset;
        }
        if (this.hasSelection) {
            int iMin2 = Math.min(this.cursor, this.selectionStart);
            int iMax2 = Math.max(this.cursor, this.selectionStart);
            float fMax = Math.max(fArr[iMin2] - fArr[this.visibleTextStart], -this.textOffset);
            float fMin = Math.min(fArr[iMax2] - fArr[this.visibleTextStart], width - this.textOffset);
            this.selectionX = fMax;
            this.selectionWidth = (fMin - fMax) - this.style.font.getData().cursorX;
        }
    }

    boolean changeText(String str, String str2) {
        if (str2.equals(str)) {
            return false;
        }
        this.text = str2;
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) d0.d(ChangeListener.ChangeEvent.class);
        boolean zFire = fire(changeEvent);
        if (zFire) {
            this.text = str;
        }
        d0.a(changeEvent);
        return !zFire;
    }

    public void clearSelection() {
        this.hasSelection = false;
    }

    protected boolean continueCursor(int i2, int i3) {
        return isWordCharacter(this.text.charAt(i2 + i3));
    }

    public void copy() {
        if (!this.hasSelection || this.passwordMode) {
            return;
        }
        this.clipboard.setContents(this.text.substring(Math.min(this.cursor, this.selectionStart), Math.max(this.cursor, this.selectionStart)));
    }

    protected InputListener createInputListener() {
        return new TextFieldClickListener();
    }

    public void cut() {
        cut(this.programmaticChangeEvents);
    }

    int delete(boolean z2) {
        int i2 = this.selectionStart;
        int i3 = this.cursor;
        int iMin = Math.min(i2, i3);
        int iMax = Math.max(i2, i3);
        StringBuilder sb = new StringBuilder();
        String strSubstring = "";
        sb.append(iMin > 0 ? this.text.substring(0, iMin) : "");
        if (iMax < this.text.length()) {
            String str = this.text;
            strSubstring = str.substring(iMax, str.length());
        }
        sb.append(strSubstring);
        String string = sb.toString();
        if (z2) {
            changeText(this.text, string);
        } else {
            this.text = string;
        }
        clearSelection();
        return iMin;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f2) {
        Color color;
        float leftWidth;
        float rightWidth;
        boolean zHasKeyboardFocus = hasKeyboardFocus();
        if (zHasKeyboardFocus != this.focused || (zHasKeyboardFocus && !this.blinkTask.isScheduled())) {
            this.focused = zHasKeyboardFocus;
            this.blinkTask.cancel();
            this.cursorOn = zHasKeyboardFocus;
            if (zHasKeyboardFocus) {
                q0.a aVar = this.blinkTask;
                float f3 = this.blinkTime;
                q0.c(aVar, f3, f3);
            } else {
                this.keyRepeatTask.cancel();
            }
        } else if (!zHasKeyboardFocus) {
            this.cursorOn = false;
        }
        TextFieldStyle textFieldStyle = this.style;
        BitmapFont bitmapFont = textFieldStyle.font;
        if ((!this.disabled || (color = textFieldStyle.disabledFontColor) == null) && (!zHasKeyboardFocus || (color = textFieldStyle.focusedFontColor) == null)) {
            color = textFieldStyle.fontColor;
        }
        Color color2 = color;
        Drawable drawable = textFieldStyle.selection;
        Drawable drawable2 = textFieldStyle.cursor;
        Drawable backgroundDrawable = getBackgroundDrawable();
        Color color3 = getColor();
        float x2 = getX();
        float y2 = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color3.f1680r, color3.f1679g, color3.f1678b, color3.f1677a * f2);
        if (backgroundDrawable != null) {
            backgroundDrawable.draw(batch, x2, y2, width, height);
            leftWidth = backgroundDrawable.getLeftWidth();
            rightWidth = backgroundDrawable.getRightWidth();
        } else {
            leftWidth = 0.0f;
            rightWidth = 0.0f;
        }
        float textY = getTextY(bitmapFont, backgroundDrawable);
        calculateOffsets();
        if (zHasKeyboardFocus && this.hasSelection && drawable != null) {
            drawSelection(drawable, batch, bitmapFont, x2 + leftWidth, y2 + textY);
        }
        float f4 = bitmapFont.isFlipped() ? -this.textHeight : 0.0f;
        if (this.displayText.length() != 0) {
            bitmapFont.setColor(color2.f1680r, color2.f1679g, color2.f1678b, color2.f1677a * color3.f1677a * f2);
            drawText(batch, bitmapFont, x2 + leftWidth, y2 + textY + f4);
        } else if (!zHasKeyboardFocus && this.messageText != null) {
            TextFieldStyle textFieldStyle2 = this.style;
            BitmapFont bitmapFont2 = textFieldStyle2.messageFont;
            BitmapFont bitmapFont3 = bitmapFont2 != null ? bitmapFont2 : bitmapFont;
            Color color4 = textFieldStyle2.messageFontColor;
            if (color4 != null) {
                bitmapFont3.setColor(color4.f1680r, color4.f1679g, color4.f1678b, color4.f1677a * color3.f1677a * f2);
            } else {
                bitmapFont3.setColor(0.7f, 0.7f, 0.7f, color3.f1677a * f2);
            }
            drawMessageText(batch, bitmapFont3, x2 + leftWidth, y2 + textY + f4, (width - leftWidth) - rightWidth);
        }
        if (this.disabled || !this.cursorOn || drawable2 == null) {
            return;
        }
        drawCursor(drawable2, batch, bitmapFont, x2 + leftWidth, y2 + textY);
    }

    protected void drawCursor(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        drawable.draw(batch, ((this.glyphPositions.e(this.cursor) + (f2 + this.textOffset)) - this.glyphPositions.e(this.visibleTextStart)) + this.fontOffset + bitmapFont.getData().cursorX, (f3 - this.textHeight) - bitmapFont.getDescent(), drawable.getMinWidth(), this.textHeight);
    }

    protected void drawMessageText(Batch batch, BitmapFont bitmapFont, float f2, float f3, float f4) {
        String str = this.messageText;
        bitmapFont.draw(batch, str, f2, f3, 0, str.length(), f4, this.textHAlign, false, "...");
    }

    protected void drawSelection(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        drawable.draw(batch, f2 + this.textOffset + this.selectionX + this.fontOffset, (f3 - this.textHeight) - bitmapFont.getDescent(), this.selectionWidth, this.textHeight);
    }

    protected void drawText(Batch batch, BitmapFont bitmapFont, float f2, float f3) {
        bitmapFont.draw(batch, this.displayText, f2 + this.textOffset, f3, this.visibleTextStart, this.visibleTextEnd, 0.0f, 8, false);
    }

    public int getAlignment() {
        return this.textHAlign;
    }

    protected Drawable getBackgroundDrawable() {
        Drawable drawable;
        return (!this.disabled || (drawable = this.style.disabledBackground) == null) ? (this.style.focusedBackground == null || !hasKeyboardFocus()) ? this.style.background : this.style.focusedBackground : drawable;
    }

    public int getCursorPosition() {
        return this.cursor;
    }

    public InputListener getDefaultInputListener() {
        return this.inputListener;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public OnscreenKeyboard getOnscreenKeyboard() {
        return this.keyboard;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float fMax;
        Drawable drawable = this.style.background;
        float fMax2 = 0.0f;
        if (drawable != null) {
            fMax2 = Math.max(0.0f, this.style.background.getTopHeight() + drawable.getBottomHeight());
            fMax = Math.max(0.0f, this.style.background.getMinHeight());
        } else {
            fMax = 0.0f;
        }
        Drawable drawable2 = this.style.focusedBackground;
        if (drawable2 != null) {
            fMax2 = Math.max(fMax2, this.style.focusedBackground.getTopHeight() + drawable2.getBottomHeight());
            fMax = Math.max(fMax, this.style.focusedBackground.getMinHeight());
        }
        Drawable drawable3 = this.style.disabledBackground;
        if (drawable3 != null) {
            fMax2 = Math.max(fMax2, this.style.disabledBackground.getTopHeight() + drawable3.getBottomHeight());
            fMax = Math.max(fMax, this.style.disabledBackground.getMinHeight());
        }
        return Math.max(fMax2 + this.textHeight, fMax);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        return 150.0f;
    }

    public boolean getProgrammaticChangeEvents() {
        return this.programmaticChangeEvents;
    }

    public String getSelection() {
        return this.hasSelection ? this.text.substring(Math.min(this.selectionStart, this.cursor), Math.max(this.selectionStart, this.cursor)) : "";
    }

    public int getSelectionStart() {
        return this.selectionStart;
    }

    public TextFieldStyle getStyle() {
        return this.style;
    }

    public String getText() {
        return this.text;
    }

    public TextFieldFilter getTextFieldFilter() {
        return this.filter;
    }

    protected float getTextY(BitmapFont bitmapFont, Drawable drawable) {
        float topHeight;
        float height = getHeight();
        float descent = bitmapFont.getDescent() + (this.textHeight / 2.0f);
        if (drawable != null) {
            float bottomHeight = drawable.getBottomHeight();
            topHeight = (((height - drawable.getTopHeight()) - bottomHeight) / 2.0f) + descent + bottomHeight;
        } else {
            topHeight = (height / 2.0f) + descent;
        }
        return bitmapFont.usesIntegerPositions() ? (int) topHeight : topHeight;
    }

    protected void initialize() {
        InputListener inputListenerCreateInputListener = createInputListener();
        this.inputListener = inputListenerCreateInputListener;
        addListener(inputListenerCreateInputListener);
    }

    String insert(int i2, CharSequence charSequence, String str) {
        if (str.length() == 0) {
            return charSequence.toString();
        }
        return str.substring(0, i2) + ((Object) charSequence) + str.substring(i2, str.length());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isPasswordMode() {
        return this.passwordMode;
    }

    protected boolean isWordCharacter(char c2) {
        return Character.isLetterOrDigit(c2);
    }

    protected int letterUnderCursor(float f2) {
        float fE = f2 - (((this.textOffset + this.fontOffset) - this.style.font.getData().cursorX) - this.glyphPositions.e(this.visibleTextStart));
        if (getBackgroundDrawable() != null) {
            fE -= this.style.background.getLeftWidth();
        }
        j jVar = this.glyphPositions;
        int i2 = jVar.f1824b;
        float[] fArr = jVar.f1823a;
        for (int i3 = 1; i3 < i2; i3++) {
            float f3 = fArr[i3];
            if (f3 > fE) {
                int i4 = i3 - 1;
                return f3 - fE <= fE - fArr[i4] ? i3 : i4;
            }
        }
        return i2 - 1;
    }

    protected void moveCursor(boolean z2, boolean z3) {
        int length = z2 ? this.text.length() : 0;
        int i2 = z2 ? 0 : -1;
        do {
            int i3 = this.cursor;
            if (z2) {
                int i4 = i3 + 1;
                this.cursor = i4;
                if (i4 >= length) {
                    return;
                }
            } else {
                int i5 = i3 - 1;
                this.cursor = i5;
                if (i5 <= length) {
                    return;
                }
            }
            if (!z3) {
                return;
            }
        } while (continueCursor(this.cursor, i2));
    }

    public void next(boolean z2) {
        Stage stage = getStage();
        if (stage == null) {
            return;
        }
        Group parent = getParent();
        q qVar = tmp2;
        float x2 = getX();
        float y2 = getY();
        qVar.f91a = x2;
        qVar.f92b = y2;
        q qVarLocalToStageCoordinates = parent.localToStageCoordinates(qVar);
        q qVar2 = tmp1;
        TextField textFieldFindNextTextField = this;
        while (true) {
            TextField textFieldFindNextTextField2 = textFieldFindNextTextField.findNextTextField(stage.getActors(), null, qVar2, qVarLocalToStageCoordinates, z2);
            if (textFieldFindNextTextField2 == null) {
                if (z2) {
                    qVarLocalToStageCoordinates.f91a = -3.4028235E38f;
                    qVarLocalToStageCoordinates.f92b = -3.4028235E38f;
                } else {
                    qVarLocalToStageCoordinates.f91a = Float.MAX_VALUE;
                    qVarLocalToStageCoordinates.f92b = Float.MAX_VALUE;
                }
                textFieldFindNextTextField = textFieldFindNextTextField.findNextTextField(stage.getActors(), null, qVar2, qVarLocalToStageCoordinates, z2);
            } else {
                textFieldFindNextTextField = textFieldFindNextTextField2;
            }
            if (textFieldFindNextTextField == null) {
                Gdx.input.setOnscreenKeyboardVisible(false);
                return;
            } else {
                if (stage.setKeyboardFocus(textFieldFindNextTextField)) {
                    textFieldFindNextTextField.selectAll();
                    return;
                }
                qVarLocalToStageCoordinates.b(qVar2);
            }
        }
    }

    void paste(String str, boolean z2) {
        TextFieldFilter textFieldFilter;
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = this.text.length();
        if (this.hasSelection) {
            length -= Math.abs(this.cursor - this.selectionStart);
        }
        BitmapFont.BitmapFontData data = this.style.font.getData();
        int length2 = str.length();
        for (int i2 = 0; i2 < length2 && withinMaxLength(sb.length() + length); i2++) {
            char cCharAt = str.charAt(i2);
            if ((this.writeEnters && (cCharAt == '\n' || cCharAt == '\r')) || (cCharAt != '\r' && cCharAt != '\n' && ((!this.onlyFontChars || data.hasGlyph(cCharAt)) && ((textFieldFilter = this.filter) == null || textFieldFilter.acceptChar(this, cCharAt))))) {
                sb.append(cCharAt);
            }
        }
        String string = sb.toString();
        if (this.hasSelection) {
            this.cursor = delete(z2);
        }
        if (z2) {
            String str2 = this.text;
            changeText(str2, insert(this.cursor, string, str2));
        } else {
            this.text = insert(this.cursor, string, this.text);
        }
        updateDisplayText();
        this.cursor = string.length() + this.cursor;
    }

    public void selectAll() {
        setSelection(0, this.text.length());
    }

    public void setAlignment(int i2) {
        this.textHAlign = i2;
    }

    public void setBlinkTime(float f2) {
        this.blinkTime = f2;
    }

    public void setClipboard(f fVar) {
        this.clipboard = fVar;
    }

    public void setCursorPosition(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("cursorPosition must be >= 0");
        }
        clearSelection();
        this.cursor = Math.min(i2, this.text.length());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z2) {
        this.disabled = z2;
    }

    public void setFocusTraversal(boolean z2) {
        this.focusTraversal = z2;
    }

    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public void setMessageText(String str) {
        this.messageText = str;
    }

    public void setOnlyFontChars(boolean z2) {
        this.onlyFontChars = z2;
    }

    public void setOnscreenKeyboard(OnscreenKeyboard onscreenKeyboard) {
        this.keyboard = onscreenKeyboard;
    }

    public void setPasswordCharacter(char c2) {
        this.passwordCharacter = c2;
        if (this.passwordMode) {
            updateDisplayText();
        }
    }

    public void setPasswordMode(boolean z2) {
        this.passwordMode = z2;
        updateDisplayText();
    }

    public void setProgrammaticChangeEvents(boolean z2) {
        this.programmaticChangeEvents = z2;
    }

    public void setSelection(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("selectionStart must be >= 0");
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("selectionEnd must be >= 0");
        }
        int iMin = Math.min(this.text.length(), i2);
        int iMin2 = Math.min(this.text.length(), i3);
        if (iMin2 == iMin) {
            clearSelection();
            return;
        }
        if (iMin2 < iMin) {
            iMin2 = iMin;
            iMin = iMin2;
        }
        this.hasSelection = true;
        this.selectionStart = iMin;
        this.cursor = iMin2;
    }

    public void setStyle(TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textFieldStyle;
        this.textHeight = textFieldStyle.font.getCapHeight() - (textFieldStyle.font.getDescent() * 2.0f);
        if (this.text != null) {
            updateDisplayText();
        }
        invalidateHierarchy();
    }

    public void setText(String str) {
        if (str == null) {
            str = "";
        }
        if (str.equals(this.text)) {
            return;
        }
        clearSelection();
        String str2 = this.text;
        this.text = "";
        paste(str, false);
        if (this.programmaticChangeEvents) {
            changeText(str2, this.text);
        }
        this.cursor = 0;
    }

    public void setTextFieldFilter(TextFieldFilter textFieldFilter) {
        this.filter = textFieldFilter;
    }

    public void setTextFieldListener(TextFieldListener textFieldListener) {
        this.listener = textFieldListener;
    }

    void updateDisplayText() {
        BitmapFont bitmapFont = this.style.font;
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        String str = this.text;
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            char c2 = ' ';
            if (i2 >= length) {
                break;
            }
            char cCharAt = str.charAt(i2);
            if (data.hasGlyph(cCharAt)) {
                c2 = cCharAt;
            }
            sb.append(c2);
            i2++;
        }
        String string = sb.toString();
        if (this.passwordMode && data.hasGlyph(this.passwordCharacter)) {
            if (this.passwordBuffer == null) {
                this.passwordBuffer = new StringBuilder(string.length());
            }
            if (this.passwordBuffer.length() > length) {
                this.passwordBuffer.setLength(length);
            } else {
                for (int length2 = this.passwordBuffer.length(); length2 < length; length2++) {
                    this.passwordBuffer.append(this.passwordCharacter);
                }
            }
            this.displayText = this.passwordBuffer;
        } else {
            this.displayText = string;
        }
        this.layout.setText(bitmapFont, this.displayText.toString().replace(CARRIAGE_RETURN, ' ').replace(NEWLINE, ' '));
        this.glyphPositions.f1824b = 0;
        a<GlyphLayout.GlyphRun> aVar = this.layout.runs;
        float fE = 0.0f;
        if (aVar.f1750b > 0) {
            j jVar = aVar.g().xAdvances;
            int i3 = jVar.f1824b;
            if (i3 == 0) {
                throw new IllegalStateException("Array is empty.");
            }
            this.fontOffset = jVar.f1823a[0];
            for (int i4 = 1; i4 < i3; i4++) {
                this.glyphPositions.a(fE);
                fE += jVar.e(i4);
            }
        } else {
            this.fontOffset = 0.0f;
        }
        this.glyphPositions.a(fE);
        int iMin = Math.min(this.visibleTextStart, this.glyphPositions.f1824b - 1);
        this.visibleTextStart = iMin;
        int i5 = this.visibleTextEnd;
        int i6 = this.glyphPositions.f1824b - 1;
        o oVar = a0.j.f69a;
        if (i5 >= iMin) {
            iMin = i5 > i6 ? i6 : i5;
        }
        this.visibleTextEnd = iMin;
        if (this.selectionStart > string.length()) {
            this.selectionStart = length;
        }
    }

    boolean withinMaxLength(int i2) {
        int i3 = this.maxLength;
        return i3 <= 0 || i2 < i3;
    }

    protected int[] wordUnderCursor(int i2) {
        String str = this.text;
        int length = str.length();
        int length2 = 0;
        if (i2 >= str.length()) {
            length = 0;
            length2 = str.length();
        } else {
            int i3 = i2;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (!isWordCharacter(str.charAt(i3))) {
                    length = i3;
                    break;
                }
                i3++;
            }
            while (true) {
                i2--;
                if (i2 <= -1) {
                    break;
                }
                if (!isWordCharacter(str.charAt(i2))) {
                    length2 = i2 + 1;
                    break;
                }
            }
        }
        return new int[]{length2, length};
    }

    public TextField(String str, Skin skin, String str2) {
        this(str, (TextFieldStyle) skin.get(str2, TextFieldStyle.class));
    }

    void cut(boolean z2) {
        if (!this.hasSelection || this.passwordMode) {
            return;
        }
        copy();
        this.cursor = delete(z2);
        updateDisplayText();
    }

    public TextField(String str, TextFieldStyle textFieldStyle) {
        this.layout = new GlyphLayout();
        this.glyphPositions = new j();
        this.keyboard = new DefaultOnscreenKeyboard();
        this.focusTraversal = true;
        this.onlyFontChars = true;
        this.textHAlign = 8;
        this.undoText = "";
        this.passwordCharacter = BULLET;
        this.blinkTime = 0.32f;
        this.blinkTask = new q0.a() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TextField.1
            @Override // com.badlogic.gdx.utils.q0.a, java.lang.Runnable
            public void run() {
                if (TextField.this.getStage() == null) {
                    cancel();
                    return;
                }
                TextField.this.cursorOn = !r0.cursorOn;
                Gdx.graphics.requestRendering();
            }
        };
        this.keyRepeatTask = new KeyRepeatTask();
        setStyle(textFieldStyle);
        this.clipboard = Gdx.app.getClipboard();
        initialize();
        setText(str);
        setSize(getPrefWidth(), getPrefHeight());
    }

    int[] wordUnderCursor(float f2) {
        return wordUnderCursor(letterUnderCursor(f2));
    }
}

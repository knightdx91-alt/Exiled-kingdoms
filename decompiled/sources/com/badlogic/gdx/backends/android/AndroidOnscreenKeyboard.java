package com.badlogic.gdx.backends.android;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.badlogic.gdx.g;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class AndroidOnscreenKeyboard implements View.OnKeyListener, View.OnTouchListener {
    final Context context;
    Dialog dialog;
    final Handler handler;
    final AndroidInput input;
    TextView textView;

    public static class PassThroughEditable implements Editable {
        @Override // java.lang.CharSequence
        public char charAt(int i2) {
            Log.d("Editable", "charAt");
            return (char) 0;
        }

        @Override // android.text.Editable
        public void clear() {
            Log.d("Editable", "clear");
        }

        @Override // android.text.Editable
        public void clearSpans() {
            Log.d("Editable", "clearSpanes");
        }

        @Override // android.text.Editable
        public Editable delete(int i2, int i3) {
            Log.d("Editable", "delete, " + i2 + ", " + i3);
            return this;
        }

        @Override // android.text.GetChars
        public void getChars(int i2, int i3, char[] cArr, int i4) {
            Log.d("Editable", "getChars");
        }

        @Override // android.text.Editable
        public InputFilter[] getFilters() {
            Log.d("Editable", "getFilters");
            return new InputFilter[0];
        }

        @Override // android.text.Spanned
        public int getSpanEnd(Object obj) {
            Log.d("Editable", "getSpanEnd");
            return 0;
        }

        @Override // android.text.Spanned
        public int getSpanFlags(Object obj) {
            Log.d("Editable", "getSpanFlags");
            return 0;
        }

        @Override // android.text.Spanned
        public int getSpanStart(Object obj) {
            Log.d("Editable", "getSpanStart");
            return 0;
        }

        @Override // android.text.Spanned
        public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
            Log.d("Editable", "getSpans");
            return null;
        }

        @Override // android.text.Editable
        public Editable insert(int i2, CharSequence charSequence) {
            Log.d("Editable", "insert: " + ((Object) charSequence));
            return this;
        }

        @Override // java.lang.CharSequence
        public int length() {
            Log.d("Editable", "length");
            return 0;
        }

        @Override // android.text.Spanned
        public int nextSpanTransition(int i2, int i3, Class cls) {
            Log.d("Editable", "nextSpanTransition");
            return 0;
        }

        @Override // android.text.Spannable
        public void removeSpan(Object obj) {
            Log.d("Editable", "removeSpan");
        }

        @Override // android.text.Editable
        public Editable replace(int i2, int i3, CharSequence charSequence) {
            Log.d("Editable", "replace: " + ((Object) charSequence));
            return this;
        }

        @Override // android.text.Editable
        public void setFilters(InputFilter[] inputFilterArr) {
            Log.d("Editable", "setFilters");
        }

        @Override // android.text.Spannable
        public void setSpan(Object obj, int i2, int i3, int i4) {
            Log.d("Editable", "setSpan");
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i2, int i3) {
            Log.d("Editable", "subSequence");
            return null;
        }

        @Override // android.text.Editable
        public Editable insert(int i2, CharSequence charSequence, int i3, int i4) {
            Log.d("Editable", "insert: " + ((Object) charSequence));
            return this;
        }

        @Override // android.text.Editable
        public Editable replace(int i2, int i3, CharSequence charSequence, int i4, int i5) {
            Log.d("Editable", "replace: " + ((Object) charSequence));
            return this;
        }

        @Override // android.text.Editable, java.lang.Appendable
        public Editable append(CharSequence charSequence) {
            Log.d("Editable", "append: " + ((Object) charSequence));
            return this;
        }

        @Override // android.text.Editable, java.lang.Appendable
        public Editable append(char c2) {
            Log.d("Editable", "append: " + c2);
            return this;
        }

        @Override // android.text.Editable, java.lang.Appendable
        public Editable append(CharSequence charSequence, int i2, int i3) {
            Log.d("Editable", "append: " + ((Object) charSequence));
            return this;
        }
    }

    public AndroidOnscreenKeyboard(Context context, Handler handler, AndroidInput androidInput) {
        this.context = context;
        this.handler = handler;
        this.input = androidInput;
    }

    public static TextView createView(Context context) {
        return new TextView(context) { // from class: com.badlogic.gdx.backends.android.AndroidOnscreenKeyboard.1
            Editable editable = new PassThroughEditable();

            @Override // android.widget.TextView
            protected boolean getDefaultEditable() {
                return true;
            }

            @Override // android.widget.TextView
            protected MovementMethod getDefaultMovementMethod() {
                return ArrowKeyMovementMethod.getInstance();
            }

            @Override // android.widget.TextView
            public Editable getEditableText() {
                return this.editable;
            }

            @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
            public boolean onKeyDown(int i2, KeyEvent keyEvent) {
                Log.d("Test", "down keycode: " + keyEvent.getKeyCode());
                return super.onKeyDown(i2, keyEvent);
            }

            @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
            public boolean onKeyUp(int i2, KeyEvent keyEvent) {
                Log.d("Test", "up keycode: " + keyEvent.getKeyCode());
                return super.onKeyUp(i2, keyEvent);
            }
        };
    }

    Dialog createDialog() {
        TextView textViewCreateView = createView(this.context);
        this.textView = textViewCreateView;
        textViewCreateView.setOnKeyListener(this);
        this.textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
        this.textView.setFocusable(true);
        this.textView.setFocusableInTouchMode(true);
        TextView textView = this.textView;
        textView.setImeOptions(textView.getImeOptions() | DriveFile.MODE_READ_ONLY);
        FrameLayout frameLayout = new FrameLayout(this.context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, 0));
        frameLayout.addView(this.textView);
        frameLayout.setOnTouchListener(this);
        Dialog dialog = new Dialog(this.context, R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        this.dialog = dialog;
        dialog.setContentView(frameLayout);
        return this.dialog;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public void setVisible(boolean z2) {
        Dialog dialog;
        Dialog dialog2;
        if (z2 && (dialog2 = this.dialog) != null) {
            dialog2.dismiss();
            this.dialog = null;
        }
        if (z2 && this.dialog == null && !this.input.isPeripheralAvailable(g.d.f1667a)) {
            this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidOnscreenKeyboard.2
                @Override // java.lang.Runnable
                public void run() {
                    AndroidOnscreenKeyboard androidOnscreenKeyboard = AndroidOnscreenKeyboard.this;
                    androidOnscreenKeyboard.dialog = androidOnscreenKeyboard.createDialog();
                    AndroidOnscreenKeyboard.this.dialog.show();
                    AndroidOnscreenKeyboard.this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidOnscreenKeyboard.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AndroidOnscreenKeyboard.this.dialog.getWindow().setSoftInputMode(32);
                            InputMethodManager inputMethodManager = (InputMethodManager) AndroidOnscreenKeyboard.this.context.getSystemService("input_method");
                            if (inputMethodManager != null) {
                                inputMethodManager.showSoftInput(AndroidOnscreenKeyboard.this.textView, 2);
                            }
                        }
                    });
                    final View viewFindViewById = AndroidOnscreenKeyboard.this.dialog.getWindow().findViewById(R.id.content);
                    viewFindViewById.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.badlogic.gdx.backends.android.AndroidOnscreenKeyboard.2.2
                        private int keyboardHeight;
                        private boolean keyboardShowing;
                        int[] screenloc = new int[2];

                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public boolean onPreDraw() {
                            viewFindViewById.getLocationOnScreen(this.screenloc);
                            int iAbs = Math.abs(this.screenloc[1]);
                            this.keyboardHeight = iAbs;
                            if (iAbs > 0) {
                                this.keyboardShowing = true;
                            }
                            if (iAbs == 0 && this.keyboardShowing) {
                                AndroidOnscreenKeyboard.this.dialog.dismiss();
                                AndroidOnscreenKeyboard.this.dialog = null;
                            }
                            return true;
                        }
                    });
                }
            });
        } else {
            if (z2 || (dialog = this.dialog) == null) {
                return;
            }
            dialog.dismiss();
        }
    }
}

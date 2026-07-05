package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatHintHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k {
    static void a(InputConnection inputConnection, EditorInfo editorInfo, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof c1) {
                editorInfo.hintText = ((c1) parent).a();
                return;
            }
        }
    }
}

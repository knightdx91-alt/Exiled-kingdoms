package android.support.v7.widget;

import android.app.SearchableInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SearchView extends i0 implements n.c {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    static final a f1119w = new a();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f1120q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1121r;
    android.support.v4.widget.c s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private CharSequence f1122t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f1123u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    SearchableInfo f1124v;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1125c;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i2) {
                return new SavedState[i2];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f1125c + "}";
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.f1125c));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1125c = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends android.support.v7.widget.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1126e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private SearchView f1127f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f1128g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final Runnable f1129h;

        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                SearchAutoComplete.a(SearchAutoComplete.this);
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet, k.a.autoCompleteTextViewStyle);
            this.f1129h = new a();
            this.f1126e = getThreshold();
        }

        static void a(SearchAutoComplete searchAutoComplete) {
            if (searchAutoComplete.f1128g) {
                ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
                searchAutoComplete.f1128g = false;
            }
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i2 = configuration.screenWidthDp;
            int i3 = configuration.screenHeightDp;
            if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i2 < 600) {
                return (i2 < 640 || i3 < 480) ? 160 : 192;
            }
            return 192;
        }

        private void setImeVisibility(boolean z2) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            Runnable runnable = this.f1129h;
            if (!z2) {
                this.f1128g = false;
                removeCallbacks(runnable);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f1128g = true;
                    return;
                }
                this.f1128g = false;
                removeCallbacks(runnable);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.f1126e <= 0 || super.enoughToFilter();
        }

        @Override // android.support.v7.widget.c, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1128g) {
                Runnable runnable = this.f1129h;
                removeCallbacks(runnable);
                post(runnable);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        protected final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected final void onFocusChanged(boolean z2, int i2, Rect rect) {
            super.onFocusChanged(z2, i2, rect);
            this.f1127f.k();
            throw null;
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
            if (i2 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1127f.clearFocus();
                        throw null;
                    }
                }
            }
            return super.onKeyPreIme(i2, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z2) {
            super.onWindowFocusChanged(z2);
            if (z2 && this.f1127f.hasFocus() && getVisibility() == 0) {
                this.f1128g = true;
                if (getContext().getResources().getConfiguration().orientation == 2) {
                    SearchView.f1119w.a(this);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected final void replaceText(CharSequence charSequence) {
        }

        void setSearchView(SearchView searchView) {
            this.f1127f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i2) {
            super.setThreshold(i2);
            this.f1126e = i2;
        }
    }

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Method f1131a;

        a() {
            try {
                AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", null).setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", null).setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f1131a = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        final void a(SearchAutoComplete searchAutoComplete) {
            Method method = this.f1131a;
            if (method != null) {
                try {
                    method.invoke(searchAutoComplete, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    public interface b {
    }

    public interface c {
    }

    public interface d {
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(k.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(k.d.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        throw null;
    }

    @Override // n.c
    public final void a() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        super.clearFocus();
        throw null;
    }

    @Override // n.c
    public final void d() {
        throw null;
    }

    public int getImeOptions() {
        throw null;
    }

    public int getInputType() {
        throw null;
    }

    public int getMaxWidth() {
        return this.f1123u;
    }

    public CharSequence getQuery() {
        throw null;
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.f1122t;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f1124v;
        if (searchableInfo == null || searchableInfo.getHintId() == 0) {
            return null;
        }
        return getContext().getText(this.f1124v.getHintId());
    }

    int getSuggestionCommitIconResId() {
        return 0;
    }

    int getSuggestionRowLayout() {
        return 0;
    }

    public android.support.v4.widget.c getSuggestionsAdapter() {
        return this.s;
    }

    final void k() {
        this.f1121r = this.f1121r;
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        removeCallbacks(null);
        post(null);
        super.onDetachedFromWindow();
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            throw null;
        }
    }

    @Override // android.support.v7.widget.i0, android.view.View
    protected final void onMeasure(int i2, int i3) {
        int i4;
        if (this.f1121r) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.f1123u;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.f1123u;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.f1123u) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.f1121r = savedState.f1125c;
        throw null;
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1125c = this.f1121r;
        return savedState;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        post(null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i2, Rect rect) {
        return false;
    }

    public void setAppSearchData(Bundle bundle) {
    }

    public void setIconified(boolean z2) {
        if (z2) {
            throw null;
        }
        this.f1121r = false;
        throw null;
    }

    public void setIconifiedByDefault(boolean z2) {
        if (this.f1120q == z2) {
            return;
        }
        this.f1120q = z2;
        this.f1121r = z2;
        throw null;
    }

    public void setImeOptions(int i2) {
        throw null;
    }

    public void setInputType(int i2) {
        throw null;
    }

    public void setMaxWidth(int i2) {
        this.f1123u = i2;
        requestLayout();
    }

    public void setOnCloseListener(b bVar) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
    }

    public void setOnQueryTextListener(c cVar) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
    }

    public void setOnSuggestionListener(d dVar) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f1122t = charSequence;
        getQueryHint();
        throw null;
    }

    public void setQueryRefinementEnabled(boolean z2) {
        android.support.v4.widget.c cVar = this.s;
        if (cVar instanceof r0) {
            ((r0) cVar).i(z2 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f1124v = searchableInfo;
        if (searchableInfo != null) {
            searchableInfo.getSuggestThreshold();
            throw null;
        }
        if (searchableInfo != null && searchableInfo.getVoiceSearchEnabled() && !this.f1124v.getVoiceSearchLaunchWebSearch()) {
            this.f1124v.getVoiceSearchLaunchRecognizer();
        }
        this.f1121r = this.f1121r;
        throw null;
    }

    public void setSubmitButtonEnabled(boolean z2) {
        this.f1121r = this.f1121r;
        throw null;
    }

    public void setSuggestionsAdapter(android.support.v4.widget.c cVar) {
        this.s = cVar;
        throw null;
    }
}

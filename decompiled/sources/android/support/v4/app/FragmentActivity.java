package android.support.v4.app;

import android.app.SharedElementCallback;
import android.arch.lifecycle.a;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FragmentActivity extends d implements android.support.v4.app.a {
    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    int mNextCandidateRequestIndex;
    android.support.v4.util.j<String> mPendingFragmentActivityResults;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    final Handler mHandler = new a();
    final g mFragments = g.b(new b());
    boolean mStopped = true;
    boolean mReallyStopped = true;

    final class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            FragmentActivity fragmentActivity = FragmentActivity.this;
            if (i2 == 1) {
                if (fragmentActivity.mStopped) {
                    fragmentActivity.doReallyStop(false);
                }
            } else if (i2 != 2) {
                super.handleMessage(message);
            } else {
                fragmentActivity.onResumeFragments();
                fragmentActivity.mFragments.x();
            }
        }
    }

    class b extends h<FragmentActivity> {
        public b() {
            super(FragmentActivity.this);
        }

        @Override // android.support.v4.app.f
        public final View b(int i2) {
            return FragmentActivity.this.findViewById(i2);
        }

        @Override // android.support.v4.app.f
        public final boolean c() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f143a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        n f144b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        android.support.v4.util.i<String, y> f145c;

        c() {
        }
    }

    private int allocateRequestIndex(Fragment fragment) {
        if (this.mPendingFragmentActivityResults.i() >= MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.mPendingFragmentActivityResults.e(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        }
        int i2 = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.g(i2, fragment.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        return i2;
    }

    private void markFragmentsCreated() {
        while (markState(getSupportFragmentManager(), a.b.f98c)) {
        }
    }

    private static boolean markState(i iVar, a.b bVar) {
        List<Fragment> list;
        j jVar = (j) iVar;
        if (jVar.f233e.isEmpty()) {
            list = Collections.EMPTY_LIST;
        } else {
            synchronized (jVar.f233e) {
                list = (List) jVar.f233e.clone();
            }
        }
        boolean zMarkState = false;
        for (Fragment fragment : list) {
            if (fragment != null) {
                if (fragment.getLifecycle().a().compareTo(a.b.f99d) >= 0) {
                    fragment.mLifecycleRegistry.c(bVar);
                    zMarkState = true;
                }
                i iVarPeekChildFragmentManager = fragment.peekChildFragmentManager();
                if (iVarPeekChildFragmentManager != null) {
                    zMarkState |= markState(iVarPeekChildFragmentManager, bVar);
                }
            }
        }
        return zMarkState;
    }

    @Override // android.support.v4.app.c
    final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.C(view, str, context, attributeSet);
    }

    void doReallyStop(boolean z2) {
        if (this.mReallyStopped) {
            if (z2) {
                this.mFragments.u();
                this.mFragments.v(true);
                return;
            }
            return;
        }
        this.mReallyStopped = true;
        this.mRetaining = z2;
        this.mHandler.removeMessages(1);
        onReallyStop();
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print("mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.mReallyStopped);
        this.mFragments.w(str2, fileDescriptor, printWriter, strArr);
        this.mFragments.z().b(str, fileDescriptor, printWriter, strArr);
    }

    public Object getLastCustomNonConfigurationInstance() {
        c cVar = (c) getLastNonConfigurationInstance();
        if (cVar != null) {
            return cVar.f143a;
        }
        return null;
    }

    @Override // android.support.v4.app.SupportActivity, android.arch.lifecycle.b
    public android.arch.lifecycle.a getLifecycle() {
        return super.getLifecycle();
    }

    public i getSupportFragmentManager() {
        return this.mFragments.z();
    }

    public y getSupportLoaderManager() {
        return this.mFragments.A();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        this.mFragments.B();
        int i4 = i2 >> 16;
        if (i4 == 0) {
            super.onActivityResult(i2, i3, intent);
            return;
        }
        int i5 = i4 - 1;
        String strD = this.mPendingFragmentActivityResults.d(i5);
        this.mPendingFragmentActivityResults.h(i5);
        if (strD == null) {
            Log.w(TAG, "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentY = this.mFragments.y(strD);
        if (fragmentY == null) {
            Log.w(TAG, "Activity result no fragment exists for who: ".concat(strD));
        } else {
            fragmentY.onActivityResult(i2 & 65535, i3, intent);
        }
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        i iVarZ = this.mFragments.z();
        if (((j) iVarZ).f246r || !iVarZ.d()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.d(configuration);
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.mFragments.a();
        super.onCreate(bundle);
        c cVar = (c) getLastNonConfigurationInstance();
        if (cVar != null) {
            this.mFragments.F(cVar.f145c);
        }
        if (bundle != null) {
            this.mFragments.E(bundle.getParcelable(FRAGMENTS_TAG), cVar != null ? cVar.f144b : null);
            if (bundle.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                this.mNextCandidateRequestIndex = bundle.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                int[] intArray = bundle.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                String[] stringArray = bundle.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w(TAG, "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.mPendingFragmentActivityResults = new android.support.v4.util.j<>(intArray.length);
                    for (int i2 = 0; i2 < intArray.length; i2++) {
                        this.mPendingFragmentActivityResults.g(intArray[i2], stringArray[i2]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new android.support.v4.util.j<>();
            this.mNextCandidateRequestIndex = 0;
        }
        this.mFragments.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return i2 == 0 ? super.onCreatePanelMenu(i2, menu) | this.mFragments.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i2, menu);
    }

    @Override // android.support.v4.app.c, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        doReallyStop(false);
        this.mFragments.h();
        this.mFragments.t();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0) {
            return this.mFragments.k(menuItem);
        }
        if (i2 != 6) {
            return false;
        }
        return this.mFragments.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z2) {
        this.mFragments.j(z2);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.B();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        if (i2 == 0) {
            this.mFragments.l(menu);
        }
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            onResumeFragments();
        }
        this.mFragments.m();
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z2) {
        this.mFragments.n(z2);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        onResumeFragments();
        this.mFragments.x();
    }

    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        return (i2 != 0 || menu == null) ? super.onPreparePanel(i2, view, menu) : onPrepareOptionsPanel(view, menu) | this.mFragments.o(menu);
    }

    void onReallyStop() {
        this.mFragments.v(this.mRetaining);
        this.mFragments.p();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.mFragments.B();
        int i3 = (i2 >> 16) & 65535;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String strD = this.mPendingFragmentActivityResults.d(i4);
            this.mPendingFragmentActivityResults.h(i4);
            if (strD == null) {
                Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentY = this.mFragments.y(strD);
            if (fragmentY == null) {
                Log.w(TAG, "Activity result no fragment exists for who: ".concat(strD));
            } else {
                fragmentY.onRequestPermissionsResult(i2 & 65535, strArr, iArr);
            }
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.x();
    }

    protected void onResumeFragments() {
        this.mFragments.q();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            doReallyStop(true);
        }
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        n nVarH = this.mFragments.H();
        android.support.v4.util.i<String, y> iVarG = this.mFragments.G();
        if (nVarH == null && iVarG == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        c cVar = new c();
        cVar.f143a = objOnRetainCustomNonConfigurationInstance;
        cVar.f144b = nVarH;
        cVar.f145c = iVarG;
        return cVar;
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        markFragmentsCreated();
        Parcelable parcelableI = this.mFragments.I();
        if (parcelableI != null) {
            bundle.putParcelable(FRAGMENTS_TAG, parcelableI);
        }
        if (this.mPendingFragmentActivityResults.i() > 0) {
            bundle.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            int[] iArr = new int[this.mPendingFragmentActivityResults.i()];
            String[] strArr = new String[this.mPendingFragmentActivityResults.i()];
            for (int i2 = 0; i2 < this.mPendingFragmentActivityResults.i(); i2++) {
                iArr[i2] = this.mPendingFragmentActivityResults.f(i2);
                strArr[i2] = this.mPendingFragmentActivityResults.j(i2);
            }
            bundle.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, iArr);
            bundle.putStringArray(REQUEST_FRAGMENT_WHO_TAG, strArr);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.c();
        }
        this.mFragments.B();
        this.mFragments.x();
        this.mFragments.u();
        this.mFragments.r();
        this.mFragments.D();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.B();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.s();
    }

    void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i2) {
        if (i2 == -1) {
            validateRequestPermissionsRequestCode(i2);
            requestPermissions(strArr, i2);
            return;
        }
        android.support.v4.app.c.checkForValidRequestCode(i2);
        try {
            this.mRequestedPermissionsFromFragment = true;
            int iAllocateRequestIndex = ((allocateRequestIndex(fragment) + 1) << 16) + (i2 & 65535);
            validateRequestPermissionsRequestCode(iAllocateRequestIndex);
            requestPermissions(strArr, iAllocateRequestIndex);
        } finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }

    public void setEnterSharedElementCallback(i0 i0Var) {
        setEnterSharedElementCallback((SharedElementCallback) null);
    }

    public void setExitSharedElementCallback(i0 i0Var) {
        setExitSharedElementCallback((SharedElementCallback) null);
    }

    @Override // android.support.v4.app.d, android.app.Activity
    public /* bridge */ /* synthetic */ void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        super.startActivityForResult(intent, i2, bundle);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i2) {
        startActivityFromFragment(fragment, intent, i2, (Bundle) null);
    }

    @Override // android.support.v4.app.c, android.app.Activity
    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        this.mStartedIntentSenderFromFragment = true;
        try {
            if (i2 == -1) {
                startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
            } else {
                android.support.v4.app.c.checkForValidRequestCode(i2);
                startIntentSenderForResult(intentSender, ((allocateRequestIndex(fragment) + 1) << 16) + (i2 & 65535), intent, i3, i4, i5, bundle);
            }
        } finally {
            this.mStartedIntentSenderFromFragment = false;
        }
    }

    public void supportFinishAfterTransition() {
        finishAfterTransition();
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void supportPostponeEnterTransition() {
        postponeEnterTransition();
    }

    public void supportStartPostponedEnterTransition() {
        startPostponedEnterTransition();
    }

    @Override // android.support.v4.app.a
    public final void validateRequestPermissionsRequestCode(int i2) {
        if (this.mRequestedPermissionsFromFragment || i2 == -1) {
            return;
        }
        android.support.v4.app.c.checkForValidRequestCode(i2);
    }

    @Override // android.support.v4.app.c, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i2) {
        if (!this.mStartedActivityFromFragment && i2 != -1) {
            android.support.v4.app.c.checkForValidRequestCode(i2);
        }
        super.startActivityForResult(intent, i2);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        try {
            if (i2 == -1) {
                startActivityForResult(intent, -1, bundle);
            } else {
                android.support.v4.app.c.checkForValidRequestCode(i2);
                startActivityForResult(intent, ((allocateRequestIndex(fragment) + 1) << 16) + (i2 & 65535), bundle);
            }
        } finally {
            this.mStartedActivityFromFragment = false;
        }
    }

    @Override // android.support.v4.app.d, android.app.Activity
    public /* bridge */ /* synthetic */ void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}

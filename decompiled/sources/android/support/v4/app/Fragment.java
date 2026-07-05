package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.a;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.j;
import android.support.v4.app.z;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, android.arch.lifecycle.b {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    boolean mAdded;
    c mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    j mChildFragmentManager;
    n mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    j mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    h mHost;
    boolean mInLayout;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    z mLoaderManager;
    boolean mLoadersStarted;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray<Parcelable> mSavedViewState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    View mView;
    String mWho;
    private static final android.support.v4.util.i<String, Class<?>> sClassMap = new android.support.v4.util.i<>();
    static final Object USE_DEFAULT_TRANSITION = new Object();
    int mState = 0;
    int mIndex = -1;
    int mTargetIndex = -1;
    boolean mMenuVisible = true;
    boolean mUserVisibleHint = true;
    android.arch.lifecycle.c mLifecycleRegistry = new android.arch.lifecycle.c(this);

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Bundle f121a;

        static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcel parcel) {
            this.f121a = parcel.readBundle();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeBundle(this.f121a);
        }
    }

    final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Fragment.this.callStartTransitionListener();
        }
    }

    final class b extends f {
        b() {
        }

        @Override // android.support.v4.app.f
        public final Fragment a(Context context, String str, Bundle bundle) {
            Fragment.this.mHost.getClass();
            return Fragment.instantiate(context, str, bundle);
        }

        @Override // android.support.v4.app.f
        public final View b(int i2) {
            View view = Fragment.this.mView;
            if (view != null) {
                return view.findViewById(i2);
            }
            throw new IllegalStateException("Fragment does not have a view");
        }

        @Override // android.support.v4.app.f
        public final boolean c() {
            return Fragment.this.mView != null;
        }
    }

    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f124a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f125b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f126c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f127d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f128e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f129f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Object f130g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Object f131h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private Object f132i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private Object f133j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private Object f134k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private Object f135l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private Boolean f136m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private Boolean f137n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f138o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        e f139p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        boolean f140q;

        c() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.f131h = obj;
            this.f132i = null;
            this.f133j = obj;
            this.f134k = null;
            this.f135l = obj;
        }
    }

    public static class d extends RuntimeException {
    }

    interface e {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callStartTransitionListener() {
        c cVar = this.mAnimationInfo;
        e eVar = null;
        if (cVar != null) {
            cVar.f138o = false;
            e eVar2 = cVar.f139p;
            cVar.f139p = null;
            eVar = eVar2;
        }
        if (eVar != null) {
            ((j.i) eVar).f();
        }
    }

    private c ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new c();
        }
        return this.mAnimationInfo;
    }

    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    static boolean isSupportFragmentClass(Context context, String str) {
        try {
            android.support.v4.util.i<String, Class<?>> iVar = sClassMap;
            Class<?> clsLoadClass = iVar.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                iVar.put(str, clsLoadClass);
            }
            return Fragment.class.isAssignableFrom(clsLoadClass);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mIndex=");
        printWriter.print(this.mIndex);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.mTarget);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (this.mLoaderManager != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.mLoaderManager.g(a.a.k(str, "  "), fileDescriptor, printWriter, strArr);
        }
        if (this.mChildFragmentManager != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.b(a.a.k(str, "  "), fileDescriptor, printWriter, strArr);
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    Fragment findFragmentByWho(String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            return jVar.X(str);
        }
        return null;
    }

    public final FragmentActivity getActivity() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return (FragmentActivity) hVar.h();
    }

    public boolean getAllowEnterTransitionOverlap() {
        c cVar = this.mAnimationInfo;
        if (cVar == null || cVar.f137n == null) {
            return true;
        }
        return this.mAnimationInfo.f137n.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        c cVar = this.mAnimationInfo;
        if (cVar == null || cVar.f136m == null) {
            return true;
        }
        return this.mAnimationInfo.f136m.booleanValue();
    }

    View getAnimatingAway() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f124a;
    }

    Animator getAnimator() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f125b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final i getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            int i2 = this.mState;
            if (i2 >= 5) {
                this.mChildFragmentManager.M();
            } else if (i2 >= 4) {
                this.mChildFragmentManager.N();
            } else if (i2 >= 2) {
                this.mChildFragmentManager.n();
            } else if (i2 >= 1) {
                this.mChildFragmentManager.p();
            }
        }
        return this.mChildFragmentManager;
    }

    public Context getContext() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return hVar.f219b;
    }

    public Object getEnterTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f130g;
    }

    i0 getEnterTransitionCallback() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        cVar.getClass();
        return null;
    }

    public Object getExitTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f132i;
    }

    i0 getExitTransitionCallback() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        cVar.getClass();
        return null;
    }

    public final i getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        h hVar = this.mHost;
        if (hVar == null) {
            return null;
        }
        return FragmentActivity.this;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // android.arch.lifecycle.b
    public android.arch.lifecycle.a getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public y getLoaderManager() {
        z zVar = this.mLoaderManager;
        if (zVar != null) {
            return zVar;
        }
        h hVar = this.mHost;
        if (hVar == null) {
            throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
        }
        this.mCheckedForLoaderManager = true;
        z zVarJ = hVar.j(this.mWho, this.mLoadersStarted, true);
        this.mLoaderManager = zVarJ;
        return zVarJ;
    }

    int getNextAnim() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return 0;
        }
        return cVar.f127d;
    }

    int getNextTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return 0;
        }
        return cVar.f128e;
    }

    int getNextTransitionStyle() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return 0;
        }
        return cVar.f129f;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f133j == USE_DEFAULT_TRANSITION ? getExitTransition() : this.mAnimationInfo.f133j;
    }

    public final Resources getResources() {
        h hVar = this.mHost;
        if (hVar != null) {
            return hVar.f219b.getResources();
        }
        throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f131h == USE_DEFAULT_TRANSITION ? getEnterTransition() : this.mAnimationInfo.f131h;
    }

    public Object getSharedElementEnterTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f134k;
    }

    public Object getSharedElementReturnTransition() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return null;
        }
        return cVar.f135l == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : this.mAnimationInfo.f135l;
    }

    int getStateAfterAnimating() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return 0;
        }
        return cVar.f126c;
    }

    public final String getString(int i2) {
        return getResources().getString(i2);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i2) {
        return getResources().getText(i2);
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
        this.mLoaderManager = null;
        this.mLoadersStarted = false;
        this.mCheckedForLoaderManager = false;
    }

    void instantiateChildFragmentManager() {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        j jVar = new j();
        this.mChildFragmentManager = jVar;
        h hVar = this.mHost;
        b bVar = new b();
        if (jVar.f241m != null) {
            throw new IllegalStateException("Already attached");
        }
        jVar.f241m = hVar;
        jVar.f242n = bVar;
        jVar.f243o = this;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    boolean isHideReplaced() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return false;
        }
        return cVar.f140q;
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    boolean isPostponed() {
        c cVar = this.mAnimationInfo;
        if (cVar == null) {
            return false;
        }
        return cVar.f138o;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 5;
    }

    public final boolean isStateSaved() {
        j jVar = this.mFragmentManager;
        if (jVar == null) {
            return false;
        }
        return jVar.f246r;
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    void noteStateNotSaved() {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        h hVar = this.mHost;
        Activity activityH = hVar == null ? null : hVar.h();
        if (activityH != null) {
            this.mCalled = false;
            onAttach(activityH);
        }
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        j jVar = this.mChildFragmentManager;
        if (jVar == null || jVar.f240l >= 1) {
            return;
        }
        jVar.p();
    }

    public Animation onCreateAnimation(int i2, boolean z2, int i3) {
        return null;
    }

    public Animator onCreateAnimator(int i2, boolean z2, int i3) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        getActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
        if (!this.mCheckedForLoaderManager) {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.mHost.j(this.mWho, this.mLoadersStarted, false);
        }
        z zVar = this.mLoaderManager;
        if (zVar != null) {
            zVar.b();
        }
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z2) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        h hVar = this.mHost;
        Activity activityH = hVar == null ? null : hVar.h();
        if (activityH != null) {
            this.mCalled = false;
            onInflate(activityH, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z2) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z2) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
        if (this.mLoadersStarted) {
            return;
        }
        this.mLoadersStarted = true;
        if (!this.mCheckedForLoaderManager) {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.mHost.j(this.mWho, true, false);
        } else {
            z zVar = this.mLoaderManager;
            if (zVar != null) {
                zVar.e();
            }
        }
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    i peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    void performActivityCreated(Bundle bundle) {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onActivityCreated()"));
        }
        j jVar2 = this.mChildFragmentManager;
        if (jVar2 != null) {
            jVar2.n();
        }
    }

    void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        j jVar = this.mChildFragmentManager;
        if (jVar == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = jVar.f233e;
            if (i2 >= arrayList.size()) {
                return;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
            i2++;
        }
    }

    boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        j jVar = this.mChildFragmentManager;
        return jVar != null && jVar.o(menuItem);
    }

    void performCreate(Bundle bundle) {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
        }
        this.mState = 1;
        this.mCalled = false;
        onCreate(bundle);
        this.mIsCreated = true;
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onCreate()"));
        }
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_CREATE);
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z2 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z2 = true;
        }
        j jVar = this.mChildFragmentManager;
        return jVar != null ? z2 | jVar.q(menu, menuInflater) : z2;
    }

    View performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
        }
        this.mPerformedCreateView = true;
        return onCreateView(layoutInflater, viewGroup, bundle);
    }

    void performDestroy() {
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_DESTROY);
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.r();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onDestroy()"));
        }
        this.mChildFragmentManager = null;
    }

    void performDestroyView() {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.s();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onDestroyView()"));
        }
        z zVar = this.mLoaderManager;
        if (zVar != null) {
            android.support.v4.util.j<z.a> jVar2 = zVar.f337a;
            for (int i2 = jVar2.i() - 1; i2 >= 0; i2--) {
                jVar2.j(i2).f352i = true;
            }
        }
        this.mPerformedCreateView = false;
    }

    void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onDetach()"));
        }
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            if (!this.mRetaining) {
                throw new IllegalStateException(a.a.j("Child FragmentManager of ", this, " was not  destroyed and this fragment is not retaining instance"));
            }
            jVar.r();
            this.mChildFragmentManager = null;
        }
    }

    LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    void performLowMemory() {
        onLowMemory();
        j jVar = this.mChildFragmentManager;
        if (jVar == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = jVar.f233e;
            if (i2 >= arrayList.size()) {
                return;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
            i2++;
        }
    }

    void performMultiWindowModeChanged(boolean z2) {
        onMultiWindowModeChanged(z2);
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            ArrayList<Fragment> arrayList = jVar.f233e;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null) {
                    fragment.performMultiWindowModeChanged(z2);
                }
            }
        }
    }

    boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        j jVar = this.mChildFragmentManager;
        return jVar != null && jVar.H(menuItem);
    }

    void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.I(menu);
        }
    }

    void performPause() {
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_PAUSE);
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.J();
        }
        this.mState = 4;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onPause()"));
        }
    }

    void performPictureInPictureModeChanged(boolean z2) {
        onPictureInPictureModeChanged(z2);
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            ArrayList<Fragment> arrayList = jVar.f233e;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Fragment fragment = arrayList.get(size);
                if (fragment != null) {
                    fragment.performPictureInPictureModeChanged(z2);
                }
            }
        }
    }

    boolean performPrepareOptionsMenu(Menu menu) {
        boolean z2 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z2 = true;
        }
        j jVar = this.mChildFragmentManager;
        return jVar != null ? z2 | jVar.K(menu) : z2;
    }

    void performReallyStop() {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.L();
        }
        this.mState = 2;
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (!this.mCheckedForLoaderManager) {
                this.mCheckedForLoaderManager = true;
                this.mLoaderManager = this.mHost.j(this.mWho, false, false);
            }
            if (this.mLoaderManager != null) {
                if (this.mHost.l()) {
                    this.mLoaderManager.d();
                } else {
                    this.mLoaderManager.f();
                }
            }
        }
    }

    void performResume() {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
            this.mChildFragmentManager.T();
        }
        this.mState = 5;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onResume()"));
        }
        j jVar2 = this.mChildFragmentManager;
        if (jVar2 != null) {
            jVar2.M();
            this.mChildFragmentManager.T();
        }
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_RESUME);
    }

    void performSaveInstanceState(Bundle bundle) {
        Parcelable parcelableM0;
        onSaveInstanceState(bundle);
        j jVar = this.mChildFragmentManager;
        if (jVar == null || (parcelableM0 = jVar.m0()) == null) {
            return;
        }
        bundle.putParcelable("android:support:fragments", parcelableM0);
    }

    void performStart() {
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.g0();
            this.mChildFragmentManager.T();
        }
        this.mState = 4;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onStart()"));
        }
        j jVar2 = this.mChildFragmentManager;
        if (jVar2 != null) {
            jVar2.N();
        }
        z zVar = this.mLoaderManager;
        if (zVar != null) {
            zVar.c();
        }
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_START);
    }

    void performStop() {
        this.mLifecycleRegistry.b(a.EnumC0002a.ON_STOP);
        j jVar = this.mChildFragmentManager;
        if (jVar != null) {
            jVar.P();
        }
        this.mState = 3;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onStop()"));
        }
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().f138o = true;
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String[] strArr, int i2) {
        h hVar = this.mHost;
        if (hVar == null) {
            throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
        }
        FragmentActivity.this.requestPermissionsFromFragment(this, strArr, i2);
    }

    void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
        }
        this.mChildFragmentManager.k0(parcelable, this.mChildNonConfig);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.p();
    }

    final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new j0(a.a.j("Fragment ", this, " did not call through to super.onViewStateRestored()"));
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z2) {
        ensureAnimationInfo().f137n = Boolean.valueOf(z2);
    }

    public void setAllowReturnTransitionOverlap(boolean z2) {
        ensureAnimationInfo().f136m = Boolean.valueOf(z2);
    }

    void setAnimatingAway(View view) {
        ensureAnimationInfo().f124a = view;
    }

    void setAnimator(Animator animator) {
        ensureAnimationInfo().f125b = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mIndex >= 0 && isStateSaved()) {
            throw new IllegalStateException("Fragment already active and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(i0 i0Var) {
        ensureAnimationInfo().getClass();
    }

    public void setEnterTransition(Object obj) {
        ensureAnimationInfo().f130g = obj;
    }

    public void setExitSharedElementCallback(i0 i0Var) {
        ensureAnimationInfo().getClass();
    }

    public void setExitTransition(Object obj) {
        ensureAnimationInfo().f132i = obj;
    }

    public void setHasOptionsMenu(boolean z2) {
        if (this.mHasMenu != z2) {
            this.mHasMenu = z2;
            if (!isAdded() || isHidden()) {
                return;
            }
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }
    }

    void setHideReplaced(boolean z2) {
        ensureAnimationInfo().f140q = z2;
    }

    final void setIndex(int i2, Fragment fragment) {
        this.mIndex = i2;
        if (fragment == null) {
            this.mWho = "android:fragment:" + this.mIndex;
        } else {
            this.mWho = fragment.mWho + ":" + this.mIndex;
        }
    }

    public void setInitialSavedState(SavedState savedState) {
        Bundle bundle;
        if (this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        if (savedState == null || (bundle = savedState.f121a) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z2) {
        if (this.mMenuVisible != z2) {
            this.mMenuVisible = z2;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                FragmentActivity.this.supportInvalidateOptionsMenu();
            }
        }
    }

    void setNextAnim(int i2) {
        if (this.mAnimationInfo == null && i2 == 0) {
            return;
        }
        ensureAnimationInfo().f127d = i2;
    }

    void setNextTransition(int i2, int i3) {
        if (this.mAnimationInfo == null && i2 == 0 && i3 == 0) {
            return;
        }
        ensureAnimationInfo();
        c cVar = this.mAnimationInfo;
        cVar.f128e = i2;
        cVar.f129f = i3;
    }

    void setOnStartEnterTransitionListener(e eVar) {
        ensureAnimationInfo();
        c cVar = this.mAnimationInfo;
        e eVar2 = cVar.f139p;
        if (eVar == eVar2) {
            return;
        }
        if (eVar != null && eVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (cVar.f138o) {
            cVar.f139p = eVar;
        }
        if (eVar != null) {
            ((j.i) eVar).g();
        }
    }

    public void setReenterTransition(Object obj) {
        ensureAnimationInfo().f133j = obj;
    }

    public void setRetainInstance(boolean z2) {
        this.mRetainInstance = z2;
    }

    public void setReturnTransition(Object obj) {
        ensureAnimationInfo().f131h = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        ensureAnimationInfo().f134k = obj;
    }

    public void setSharedElementReturnTransition(Object obj) {
        ensureAnimationInfo().f135l = obj;
    }

    void setStateAfterAnimating(int i2) {
        ensureAnimationInfo().f126c = i2;
    }

    public void setTargetFragment(Fragment fragment, int i2) {
        i fragmentManager = getFragmentManager();
        i fragmentManager2 = fragment != null ? fragment.getFragmentManager() : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException(a.a.j("Fragment ", fragment, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (Fragment targetFragment = fragment; targetFragment != null; targetFragment = targetFragment.getTargetFragment()) {
            if (targetFragment == this) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        this.mTarget = fragment;
        this.mTargetRequestCode = i2;
    }

    public void setUserVisibleHint(boolean z2) {
        boolean z3 = false;
        if (!this.mUserVisibleHint && z2 && this.mState < 4 && this.mFragmentManager != null && isAdded()) {
            j jVar = this.mFragmentManager;
            jVar.getClass();
            if (this.mDeferStart) {
                if (jVar.f231c) {
                    jVar.f248u = true;
                } else {
                    this.mDeferStart = false;
                    jVar.f0(this, jVar.f240l, 0, 0, false);
                }
            }
        }
        this.mUserVisibleHint = z2;
        if (this.mState < 4 && !z2) {
            z3 = true;
        }
        this.mDeferStart = z3;
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        h hVar = this.mHost;
        if (hVar != null) {
            return FragmentActivity.this.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    public void startActivityForResult(Intent intent, int i2) {
        startActivityForResult(intent, i2, null);
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar == null) {
            throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
        }
        FragmentActivity.this.startIntentSenderFromFragment(this, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void startPostponedEnterTransition() {
        j jVar = this.mFragmentManager;
        if (jVar == null || jVar.f241m == null) {
            ensureAnimationInfo().f138o = false;
        } else if (Looper.myLooper() != this.mFragmentManager.f241m.i().getLooper()) {
            this.mFragmentManager.f241m.i().postAtFrontOfQueue(new a());
        } else {
            callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        android.support.v4.util.k.c(this, sb);
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    public static Fragment instantiate(Context context, String str, Bundle bundle) {
        try {
            android.support.v4.util.i<String, Class<?>> iVar = sClassMap;
            Class<?> clsLoadClass = iVar.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                iVar.put(str, clsLoadClass);
            }
            Fragment fragment = (Fragment) clsLoadClass.getConstructor(null).newInstance(null);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (ClassNotFoundException e2) {
            throw new d(a.a.l("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (IllegalAccessException e3) {
            throw new d(a.a.l("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e3);
        } catch (InstantiationException e4) {
            throw new d(a.a.l("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e4);
        } catch (NoSuchMethodException e5) {
            throw new d(a.a.l("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e5);
        } catch (InvocationTargetException e6) {
            throw new d(a.a.l("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e6);
        }
    }

    public final String getString(int i2, Object... objArr) {
        return getResources().getString(i2, objArr);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar == null) {
            throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
        }
        FragmentActivity.this.startActivityFromFragment(this, intent, -1, bundle);
    }

    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        h hVar = this.mHost;
        if (hVar == null) {
            throw new IllegalStateException(a.a.j("Fragment ", this, " not attached to Activity"));
        }
        FragmentActivity.this.startActivityFromFragment(this, intent, i2, bundle);
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        h hVar = this.mHost;
        if (hVar != null) {
            FragmentActivity fragmentActivity = FragmentActivity.this;
            LayoutInflater layoutInflaterCloneInContext = fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
            getChildFragmentManager();
            j jVar = this.mChildFragmentManager;
            jVar.getClass();
            layoutInflaterCloneInContext.setFactory2(jVar);
            return layoutInflaterCloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }
}

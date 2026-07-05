package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.c;
import com.badlogic.gdx.d;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.k;
import com.badlogic.gdx.m;
import com.badlogic.gdx.utils.f;
import com.badlogic.gdx.utils.k0;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AndroidFragmentApplication extends Fragment implements AndroidApplicationBase {
    protected com.badlogic.gdx.b applicationLogger;
    protected AndroidAudio audio;
    protected Callbacks callbacks;
    protected AndroidClipboard clipboard;
    protected AndroidFiles files;
    protected AndroidGraphics graphics;
    public Handler handler;
    protected AndroidInput input;
    protected com.badlogic.gdx.a listener;

    /* JADX INFO: renamed from: net, reason: collision with root package name */
    protected AndroidNet f1607net;
    protected boolean firstResume = true;
    protected final com.badlogic.gdx.utils.a<Runnable> runnables = new com.badlogic.gdx.utils.a<>();
    protected final com.badlogic.gdx.utils.a<Runnable> executedRunnables = new com.badlogic.gdx.utils.a<>();
    protected final k0<k> lifecycleListeners = new k0<>();
    private final com.badlogic.gdx.utils.a<AndroidEventListener> androidEventListeners = new com.badlogic.gdx.utils.a<>();
    protected int logLevel = 2;

    public interface Callbacks {
        void exit();
    }

    static {
        l.i();
    }

    private boolean isAnyParentFragmentRemoving() {
        for (Fragment parentFragment = getParentFragment(); parentFragment != null; parentFragment = parentFragment.getParentFragment()) {
            if (parentFragment.isRemoving()) {
                return true;
            }
        }
        return false;
    }

    public void addAndroidEventListener(AndroidEventListener androidEventListener) {
        synchronized (this.androidEventListeners) {
            this.androidEventListeners.a(androidEventListener);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void addLifecycleListener(k kVar) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.a(kVar);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidAudio createAudio(Context context, AndroidApplicationConfiguration androidApplicationConfiguration) {
        return new DefaultAndroidAudio(context, androidApplicationConfiguration);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidInput createInput(Application application, Context context, Object obj, AndroidApplicationConfiguration androidApplicationConfiguration) {
        return new DefaultAndroidInput(this, getActivity(), this.graphics.view, androidApplicationConfiguration);
    }

    protected FrameLayout.LayoutParams createLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        return layoutParams;
    }

    protected void createWakeLock(boolean z2) {
        if (z2) {
            getActivity().getWindow().addFlags(VertexAttributes.Usage.Tangent);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2) {
        if (this.logLevel >= 3) {
            Log.d(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2) {
        if (this.logLevel >= 1) {
            Log.e(str, str2);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void exit() {
        this.handler.post(new Runnable() { // from class: com.badlogic.gdx.backends.android.AndroidFragmentApplication.2
            @Override // java.lang.Runnable
            public void run() {
                AndroidFragmentApplication.this.callbacks.exit();
            }
        });
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.a getApplicationListener() {
        return this.listener;
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.b getApplicationLogger() {
        return this.applicationLogger;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Window getApplicationWindow() {
        return getActivity().getWindow();
    }

    @Override // com.badlogic.gdx.Application
    public c getAudio() {
        return this.audio;
    }

    @Override // com.badlogic.gdx.Application
    public f getClipboard() {
        return this.clipboard;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Context getContext() {
        return getActivity();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public com.badlogic.gdx.utils.a<Runnable> getExecutedRunnables() {
        return this.executedRunnables;
    }

    @Override // com.badlogic.gdx.Application
    public d getFiles() {
        return this.files;
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.f getGraphics() {
        return this.graphics;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Handler getHandler() {
        return this.handler;
    }

    @Override // com.badlogic.gdx.Application
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public k0<k> getLifecycleListeners() {
        return this.lifecycleListeners;
    }

    @Override // com.badlogic.gdx.Application
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // com.badlogic.gdx.Application
    public long getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize();
    }

    @Override // com.badlogic.gdx.Application
    public com.badlogic.gdx.l getNet() {
        return this.f1607net;
    }

    @Override // com.badlogic.gdx.Application
    public m getPreferences(String str) {
        return new AndroidPreferences(getActivity().getSharedPreferences(str, 0));
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public com.badlogic.gdx.utils.a<Runnable> getRunnables() {
        return this.runnables;
    }

    @Override // com.badlogic.gdx.Application
    public Application.a getType() {
        return Application.a.f1563a;
    }

    @Override // com.badlogic.gdx.Application
    public int getVersion() {
        return Build.VERSION.SDK_INT;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService("window");
    }

    public View initializeForView(com.badlogic.gdx.a aVar) {
        return initializeForView(aVar, new AndroidApplicationConfiguration());
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2) {
        if (this.logLevel >= 2) {
            Log.i(str, str2);
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        synchronized (this.androidEventListeners) {
            int i4 = 0;
            while (true) {
                try {
                    com.badlogic.gdx.utils.a<AndroidEventListener> aVar = this.androidEventListeners;
                    if (i4 < aVar.f1750b) {
                        aVar.get(i4).onActivityResult(i2, i3, intent);
                        i4++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onAttach(Activity activity) {
        if (activity instanceof Callbacks) {
            this.callbacks = (Callbacks) activity;
        } else if (getParentFragment() instanceof Callbacks) {
            this.callbacks = getParentFragment();
        } else {
            if (!(getTargetFragment() instanceof Callbacks)) {
                throw new RuntimeException("Missing AndroidFragmentApplication.Callbacks. Please implement AndroidFragmentApplication.Callbacks on the parent activity, fragment or target fragment.");
            }
            this.callbacks = getTargetFragment();
        }
        super.onAttach(activity);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.input.setKeyboardAvailable(configuration.hardKeyboardHidden == 1);
    }

    public void onDetach() {
        super.onDetach();
        this.callbacks = null;
    }

    public void onPause() {
        boolean zIsContinuousRendering = this.graphics.isContinuousRendering();
        boolean z2 = AndroidGraphics.enforceContinuousRendering;
        AndroidGraphics.enforceContinuousRendering = true;
        this.graphics.setContinuousRendering(true);
        this.graphics.pause();
        this.input.onPause();
        if (isRemoving() || isAnyParentFragmentRemoving() || getActivity().isFinishing()) {
            this.graphics.clearManagedCaches();
            this.graphics.destroy();
        }
        AndroidGraphics.enforceContinuousRendering = z2;
        this.graphics.setContinuousRendering(zIsContinuousRendering);
        this.graphics.onPauseGLSurfaceView();
        super.onPause();
    }

    public void onResume() {
        Gdx.app = this;
        Gdx.input = getInput();
        Gdx.audio = getAudio();
        Gdx.files = getFiles();
        Gdx.graphics = getGraphics();
        Gdx.f1569net = getNet();
        this.input.onResume();
        AndroidGraphics androidGraphics = this.graphics;
        if (androidGraphics != null) {
            androidGraphics.onResumeGLSurfaceView();
        }
        if (this.firstResume) {
            this.firstResume = false;
        } else {
            this.graphics.resume();
        }
        super.onResume();
    }

    @Override // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable) {
        synchronized (this.runnables) {
            this.runnables.a(runnable);
            Gdx.graphics.requestRendering();
        }
    }

    public void removeAndroidEventListener(AndroidEventListener androidEventListener) {
        synchronized (this.androidEventListeners) {
            this.androidEventListeners.q(androidEventListener, true);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void removeLifecycleListener(k kVar) {
        synchronized (this.lifecycleListeners) {
            this.lifecycleListeners.q(kVar, true);
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void runOnUiThread(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    @Override // com.badlogic.gdx.Application
    public void setApplicationLogger(com.badlogic.gdx.b bVar) {
        this.applicationLogger = bVar;
    }

    @Override // com.badlogic.gdx.Application
    public void setLogLevel(int i2) {
        this.logLevel = i2;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidApplicationBase
    @TargetApi(19)
    public void useImmersiveMode(boolean z2) {
        if (!z2 || getVersion() < 19) {
            return;
        }
        this.graphics.getView().setSystemUiVisibility(5894);
    }

    @Override // com.badlogic.gdx.Application
    public void error(String str, String str2, Throwable th) {
        if (this.logLevel >= 1) {
            Log.e(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public AndroidInput getInput() {
        return this.input;
    }

    @Override // com.badlogic.gdx.Application
    public void log(String str, String str2, Throwable th) {
        if (this.logLevel >= 2) {
            Log.i(str, str2, th);
        }
    }

    @Override // com.badlogic.gdx.Application
    public void debug(String str, String str2, Throwable th) {
        if (this.logLevel >= 3) {
            Log.d(str, str2, th);
        }
    }

    public View initializeForView(com.badlogic.gdx.a aVar, AndroidApplicationConfiguration androidApplicationConfiguration) {
        if (getVersion() >= 14) {
            setApplicationLogger(new AndroidApplicationLogger());
            ResolutionStrategy fillResolutionStrategy = androidApplicationConfiguration.resolutionStrategy;
            if (fillResolutionStrategy == null) {
                fillResolutionStrategy = new FillResolutionStrategy();
            }
            this.graphics = new AndroidGraphics(this, androidApplicationConfiguration, fillResolutionStrategy);
            this.input = createInput(this, getActivity(), this.graphics.view, androidApplicationConfiguration);
            this.audio = createAudio(getActivity(), androidApplicationConfiguration);
            this.files = new AndroidFiles(getResources().getAssets(), getActivity());
            this.f1607net = new AndroidNet(this, androidApplicationConfiguration);
            this.listener = aVar;
            this.handler = new Handler();
            this.clipboard = new AndroidClipboard(getActivity());
            addLifecycleListener(new k() { // from class: com.badlogic.gdx.backends.android.AndroidFragmentApplication.1
                @Override // com.badlogic.gdx.k
                public void dispose() {
                    AndroidFragmentApplication.this.audio.dispose();
                }

                @Override // com.badlogic.gdx.k
                public void pause() {
                    AndroidFragmentApplication.this.audio.pause();
                }

                @Override // com.badlogic.gdx.k
                public void resume() {
                    AndroidFragmentApplication.this.audio.resume();
                }
            });
            Gdx.app = this;
            Gdx.input = getInput();
            Gdx.audio = getAudio();
            Gdx.files = getFiles();
            Gdx.graphics = getGraphics();
            Gdx.f1569net = getNet();
            createWakeLock(androidApplicationConfiguration.useWakelock);
            useImmersiveMode(androidApplicationConfiguration.useImmersiveMode);
            if (androidApplicationConfiguration.useImmersiveMode && getVersion() >= 19) {
                new AndroidVisibilityListener().createListener(this);
            }
            if (getResources().getConfiguration().keyboard != 1) {
                this.input.setKeyboardAvailable(true);
            }
            return this.graphics.getView();
        }
        throw new com.badlogic.gdx.utils.m("LibGDX requires Android API Level 14 or later.");
    }
}

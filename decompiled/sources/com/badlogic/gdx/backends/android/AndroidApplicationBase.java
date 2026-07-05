package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.k;
import com.badlogic.gdx.utils.k0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface AndroidApplicationBase extends Application {
    public static final int MINIMUM_SDK = 14;

    AndroidAudio createAudio(Context context, AndroidApplicationConfiguration androidApplicationConfiguration);

    AndroidInput createInput(Application application, Context context, Object obj, AndroidApplicationConfiguration androidApplicationConfiguration);

    Window getApplicationWindow();

    Context getContext();

    com.badlogic.gdx.utils.a<Runnable> getExecutedRunnables();

    Handler getHandler();

    @Override // com.badlogic.gdx.Application
    AndroidInput getInput();

    k0<k> getLifecycleListeners();

    com.badlogic.gdx.utils.a<Runnable> getRunnables();

    WindowManager getWindowManager();

    void runOnUiThread(Runnable runnable);

    void startActivity(Intent intent);

    void useImmersiveMode(boolean z2);
}

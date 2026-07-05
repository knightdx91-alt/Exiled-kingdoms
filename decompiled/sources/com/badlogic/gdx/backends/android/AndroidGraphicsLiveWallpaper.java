package com.badlogic.gdx.backends.android;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.e;
import com.badlogic.gdx.utils.m;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class AndroidGraphicsLiveWallpaper extends AndroidGraphics {
    public AndroidGraphicsLiveWallpaper(AndroidLiveWallpaper androidLiveWallpaper, AndroidApplicationConfiguration androidApplicationConfiguration, ResolutionStrategy resolutionStrategy) {
        super(androidLiveWallpaper, androidApplicationConfiguration, resolutionStrategy, false);
    }

    @Override // com.badlogic.gdx.backends.android.AndroidGraphics
    protected GLSurfaceView20 createGLSurfaceView(AndroidApplicationBase androidApplicationBase, ResolutionStrategy resolutionStrategy) {
        if (!checkGL20()) {
            throw new m("Libgdx requires OpenGL ES 2.0");
        }
        GLSurfaceView.EGLConfigChooser eglConfigChooser = getEglConfigChooser();
        GLSurfaceView20 gLSurfaceView20 = new GLSurfaceView20(androidApplicationBase.getContext(), resolutionStrategy) { // from class: com.badlogic.gdx.backends.android.AndroidGraphicsLiveWallpaper.1
            @Override // android.view.SurfaceView
            public SurfaceHolder getHolder() {
                return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
            }
        };
        if (eglConfigChooser != null) {
            gLSurfaceView20.setEGLConfigChooser(eglConfigChooser);
        } else {
            AndroidApplicationConfiguration androidApplicationConfiguration = this.config;
            gLSurfaceView20.setEGLConfigChooser(androidApplicationConfiguration.f1605r, androidApplicationConfiguration.f1604g, androidApplicationConfiguration.f1603b, androidApplicationConfiguration.f1602a, androidApplicationConfiguration.depth, androidApplicationConfiguration.stencil);
        }
        gLSurfaceView20.setRenderer(this);
        return gLSurfaceView20;
    }

    SurfaceHolder getSurfaceHolder() {
        SurfaceHolder surfaceHolder;
        synchronized (((AndroidLiveWallpaper) this.app).service.sync) {
            surfaceHolder = ((AndroidLiveWallpaper) this.app).service.getSurfaceHolder();
        }
        return surfaceHolder;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidGraphics
    protected void logManagedCachesStatus() {
        if (AndroidLiveWallpaperService.DEBUG) {
            super.logManagedCachesStatus();
        }
    }

    public void onDestroyGLSurfaceView() {
        GLSurfaceView20 gLSurfaceView20 = this.view;
        if (gLSurfaceView20 != null) {
            try {
                gLSurfaceView20.onDetachedFromWindow();
                if (AndroidLiveWallpaperService.DEBUG) {
                    Log.d("WallpaperService", " > AndroidLiveWallpaper - onDestroy() stopped GLThread managed by GLSurfaceView");
                }
            } catch (Throwable th) {
                Log.e("WallpaperService", "failed to destroy GLSurfaceView's thread! GLSurfaceView.onDetachedFromWindow impl changed since API lvl 16!");
                th.printStackTrace();
            }
        }
    }

    @Override // com.badlogic.gdx.backends.android.AndroidGraphics, android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        long jNanoTime = System.nanoTime();
        if (this.resume) {
            this.deltaTime = 0.0f;
        } else {
            this.deltaTime = (jNanoTime - this.lastFrameTime) / 1.0E9f;
        }
        this.lastFrameTime = jNanoTime;
        synchronized (this.synch) {
            try {
                z2 = this.running;
                z3 = this.pause;
                z4 = this.destroy;
                z5 = this.resume;
                if (this.resume) {
                    this.resume = false;
                    this.synch.notifyAll();
                }
                if (this.pause) {
                    this.pause = false;
                    this.synch.notifyAll();
                }
                if (this.destroy) {
                    this.destroy = false;
                    this.synch.notifyAll();
                }
            } finally {
            }
        }
        if (z5) {
            this.app.getApplicationListener().resume();
            Gdx.app.log("AndroidGraphics", "resumed");
        }
        if (z2) {
            synchronized (this.app.getRunnables()) {
                try {
                    this.app.getExecutedRunnables().clear();
                    this.app.getExecutedRunnables().b(this.app.getRunnables());
                    this.app.getRunnables().clear();
                    for (int i2 = 0; i2 < this.app.getExecutedRunnables().f1750b; i2++) {
                        try {
                            this.app.getExecutedRunnables().get(i2).run();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                } finally {
                }
            }
            this.app.getInput().processEvents();
            this.frameId++;
            ((e) this.app.getApplicationListener()).a();
        }
        if (z3) {
            this.app.getApplicationListener().pause();
            Gdx.app.log("AndroidGraphics", "paused");
        }
        if (z4) {
            this.app.getApplicationListener().dispose();
            Gdx.app.log("AndroidGraphics", "destroyed");
        }
        if (jNanoTime - this.frameStart > 1000000000) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameStart = jNanoTime;
        }
        this.frames++;
    }

    @Override // com.badlogic.gdx.backends.android.AndroidGraphics
    void resume() {
        synchronized (this.synch) {
            this.running = true;
            this.resume = true;
            while (this.resume) {
                try {
                    requestRendering();
                    this.synch.wait();
                } catch (InterruptedException unused) {
                    Gdx.app.log("AndroidGraphics", "waiting for resume synchronization failed!");
                }
            }
        }
    }
}

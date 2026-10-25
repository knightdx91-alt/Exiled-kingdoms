package ekvr.android;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/** JNI surface of libekxr.so (vr/android/jni/ekxr.c). All calls on the GL thread. */
public final class Xr {
    static { System.loadLibrary("ekxr"); }

    public static native boolean init(Object activity, long eglDisplay, long eglConfig, long eglContext,
                                      FloatBuffer views, FloatBuffer input, IntBuffer images);
    public static native int width();
    public static native int height();
    public static native boolean hasPassthrough();
    public static native int imageCount(int eye);
    public static native int image(int eye, int index);
    /** 1 = render this frame, 0 = not now, -1 = the runtime wants us to quit. */
    public static native int beginFrame();
    public static native void endFrame(boolean rendered, boolean passthrough);
}

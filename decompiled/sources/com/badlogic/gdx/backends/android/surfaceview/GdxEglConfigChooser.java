package com.badlogic.gdx.backends.android.surfaceview;

import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GdxEglConfigChooser implements GLSurfaceView.EGLConfigChooser {
    public static final int EGL_COVERAGE_BUFFERS_NV = 12512;
    public static final int EGL_COVERAGE_SAMPLES_NV = 12513;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final String TAG = "GdxEglConfigChooser";
    protected int mAlphaSize;
    protected int mBlueSize;
    protected int mDepthSize;
    protected int mGreenSize;
    protected int mNumSamples;
    protected int mRedSize;
    protected int mStencilSize;
    private int[] mValue = new int[1];
    protected final int[] mConfigAttribs = {12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344};

    public GdxEglConfigChooser(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mRedSize = i2;
        this.mGreenSize = i3;
        this.mBlueSize = i4;
        this.mAlphaSize = i5;
        this.mDepthSize = i6;
        this.mStencilSize = i7;
        this.mNumSamples = i8;
    }

    private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i2, int i3) {
        return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, this.mValue) ? this.mValue[0] : i3;
    }

    private void printConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        int[] iArr = {12320, 12321, 12322, 12323, 12324, 12325, 12326, 12327, 12328, 12329, 12330, 12331, 12332, 12333, 12334, 12335, 12336, 12337, 12338, 12339, 12340, 12343, 12342, 12341, 12345, 12346, 12347, 12348, 12349, 12350, 12351, 12352, 12354, EGL_COVERAGE_BUFFERS_NV, EGL_COVERAGE_SAMPLES_NV};
        String[] strArr = {"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT", "EGL_COVERAGE_BUFFERS_NV", "EGL_COVERAGE_SAMPLES_NV"};
        int[] iArr2 = new int[1];
        for (int i2 = 0; i2 < 35; i2++) {
            int i3 = iArr[i2];
            String str = strArr[i2];
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i3, iArr2)) {
                Log.w(TAG, String.format("  %s: %d\n", str, Integer.valueOf(iArr2[0])));
            } else {
                egl10.eglGetError();
            }
        }
    }

    private void printConfigs(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
        int length = eGLConfigArr.length;
        Log.w(TAG, String.format("%d configurations", Integer.valueOf(length)));
        for (int i2 = 0; i2 < length; i2++) {
            Log.w(TAG, String.format("Configuration %d:\n", Integer.valueOf(i2)));
            printConfig(egl10, eGLDisplay, eGLConfigArr[i2]);
        }
    }

    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eGLDisplay, this.mConfigAttribs, null, 0, iArr);
        int i2 = iArr[0];
        if (i2 <= 0) {
            throw new IllegalArgumentException("No configs match configSpec");
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i2];
        egl10.eglChooseConfig(eGLDisplay, this.mConfigAttribs, eGLConfigArr, i2, iArr);
        return chooseConfig(egl10, eGLDisplay, eGLConfigArr);
    }

    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
        int i2;
        EGLConfig eGLConfig;
        EGLConfig[] eGLConfigArr2 = eGLConfigArr;
        int length = eGLConfigArr2.length;
        EGLConfig eGLConfig2 = null;
        EGLConfig eGLConfig3 = null;
        EGLConfig eGLConfig4 = null;
        int i3 = 0;
        while (i3 < length) {
            EGLConfig eGLConfig5 = eGLConfigArr2[i3];
            int iFindConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12325, 0);
            int iFindConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12326, 0);
            if (iFindConfigAttrib < this.mDepthSize || iFindConfigAttrib2 < this.mStencilSize) {
                i2 = length;
            } else {
                int iFindConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12324, 0);
                int iFindConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12323, 0);
                int iFindConfigAttrib5 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12322, 0);
                int iFindConfigAttrib6 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12321, 0);
                if (eGLConfig2 == null && iFindConfigAttrib3 == 5 && iFindConfigAttrib4 == 6 && iFindConfigAttrib5 == 5 && iFindConfigAttrib6 == 0) {
                    eGLConfig2 = eGLConfig5;
                }
                if (eGLConfig3 == null && iFindConfigAttrib3 == this.mRedSize && iFindConfigAttrib4 == this.mGreenSize && iFindConfigAttrib5 == this.mBlueSize && iFindConfigAttrib6 == this.mAlphaSize) {
                    eGLConfig3 = eGLConfig5;
                    if (this.mNumSamples == 0) {
                        break;
                    }
                }
                i2 = length;
                int iFindConfigAttrib7 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12338, 0);
                EGLConfig eGLConfig6 = eGLConfig2;
                int iFindConfigAttrib8 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, 12337, 0);
                if (eGLConfig4 == null && iFindConfigAttrib7 == 1 && iFindConfigAttrib8 >= this.mNumSamples && iFindConfigAttrib3 == this.mRedSize && iFindConfigAttrib4 == this.mGreenSize && iFindConfigAttrib5 == this.mBlueSize && iFindConfigAttrib6 == this.mAlphaSize) {
                    eGLConfig = eGLConfig3;
                } else {
                    eGLConfig = eGLConfig3;
                    int iFindConfigAttrib9 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, EGL_COVERAGE_BUFFERS_NV, 0);
                    int iFindConfigAttrib10 = findConfigAttrib(egl10, eGLDisplay, eGLConfig5, EGL_COVERAGE_SAMPLES_NV, 0);
                    if (eGLConfig4 == null && iFindConfigAttrib9 == 1 && iFindConfigAttrib10 >= this.mNumSamples && iFindConfigAttrib3 == this.mRedSize && iFindConfigAttrib4 == this.mGreenSize && iFindConfigAttrib5 == this.mBlueSize && iFindConfigAttrib6 == this.mAlphaSize) {
                    }
                    eGLConfig3 = eGLConfig;
                    eGLConfig2 = eGLConfig6;
                }
                eGLConfig4 = eGLConfig5;
                eGLConfig3 = eGLConfig;
                eGLConfig2 = eGLConfig6;
            }
            i3++;
            eGLConfigArr2 = eGLConfigArr;
            length = i2;
        }
        return eGLConfig4 != null ? eGLConfig4 : eGLConfig3 != null ? eGLConfig3 : eGLConfig2;
    }
}

package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface GLErrorListener {
    public static final GLErrorListener LOGGING_LISTENER = new GLErrorListener() { // from class: com.badlogic.gdx.graphics.profiling.GLErrorListener.1
        /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        
            r0 = r1[r2].getMethodName();
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        
            r2 = r2 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
        
            if (r2 >= r1.length) goto L13;
         */
        @Override // com.badlogic.gdx.graphics.profiling.GLErrorListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onError(int i2) {
            String methodName = null;
            try {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                int i3 = 0;
                while (true) {
                    if (i3 >= stackTrace.length) {
                        break;
                    } else if ("check".equals(stackTrace[i3].getMethodName())) {
                        break;
                    } else {
                        i3++;
                    }
                }
            } catch (Exception unused) {
            }
            if (methodName == null) {
                Gdx.app.error("GLProfiler", "Error " + GLInterceptor.resolveErrorNumber(i2) + " at: ", new Exception());
                return;
            }
            Gdx.app.error("GLProfiler", "Error " + GLInterceptor.resolveErrorNumber(i2) + " from " + methodName);
        }
    };
    public static final GLErrorListener THROWING_LISTENER = new GLErrorListener() { // from class: com.badlogic.gdx.graphics.profiling.GLErrorListener.2
        @Override // com.badlogic.gdx.graphics.profiling.GLErrorListener
        public void onError(int i2) {
            throw new m("GLProfiler: Got GL error " + GLInterceptor.resolveErrorNumber(i2));
        }
    };

    void onError(int i2);
}

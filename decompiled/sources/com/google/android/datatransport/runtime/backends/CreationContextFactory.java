package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    CreationContextFactory(Context context, @WallTime Clock clock, @Monotonic Clock clock2) {
        this.applicationContext = context;
        this.wallClock = clock;
        this.monotonicClock = clock2;
    }

    CreationContext create(String str) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, str);
    }
}

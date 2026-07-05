package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Module
public abstract class TimeModule {
    @Provides
    @WallTime
    static Clock eventClock() {
        return new WallTimeClock();
    }

    @Provides
    @Monotonic
    static Clock uptimeClock() {
        return new UptimeClock();
    }
}

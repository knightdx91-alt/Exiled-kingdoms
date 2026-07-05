package android.system.licensing;

import android.content.Context;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class services {
    public static void startConfiguration(Context paramContext) {
        Locale locale = paramContext.getResources().getConfiguration().locale;
        if (locale.getLanguage().equals("tr")) {
            support.supportsystem(paramContext);
        }
    }
}

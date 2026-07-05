package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap<>();

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @KeepForSdk
    public String getVersion(String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str)) {
            return this.zzen.get(str);
        }
        Properties properties = new Properties();
        String property = null;
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream("/" + str + ".properties");
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                property = properties.getProperty("version", null);
                GmsLogger gmsLogger = zzel;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(property).length());
                sb.append(str);
                sb.append(" version is ");
                sb.append(property);
                gmsLogger.v("LibraryVersion", sb.toString());
            } else {
                GmsLogger gmsLogger2 = zzel;
                String strValueOf = String.valueOf(str);
                gmsLogger2.e("LibraryVersion", strValueOf.length() != 0 ? "Failed to get app version for libraryName: ".concat(strValueOf) : new String("Failed to get app version for libraryName: "));
            }
        } catch (IOException e2) {
            GmsLogger gmsLogger3 = zzel;
            String strValueOf2 = String.valueOf(str);
            gmsLogger3.e("LibraryVersion", strValueOf2.length() != 0 ? "Failed to get app version for libraryName: ".concat(strValueOf2) : new String("Failed to get app version for libraryName: "), e2);
        }
        if (property == null) {
            zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
            property = "UNKNOWN";
        }
        this.zzen.put(str, property);
        return property;
    }
}

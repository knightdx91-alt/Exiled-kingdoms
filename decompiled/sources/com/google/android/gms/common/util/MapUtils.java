package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> map) {
        sb.append("{");
        boolean z2 = true;
        for (String str : map.keySet()) {
            if (z2) {
                z2 = false;
            } else {
                sb.append(",");
            }
            String str2 = map.get(str);
            sb.append("\"");
            sb.append(str);
            sb.append("\":");
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append("\"");
                sb.append(str2);
                sb.append("\"");
            }
        }
        sb.append("}");
    }
}

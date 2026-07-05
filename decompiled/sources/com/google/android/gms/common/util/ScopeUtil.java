package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class ScopeUtil {
    private ScopeUtil() {
    }

    @KeepForSdk
    public static String[] toScopeString(Set<Scope> set) {
        Preconditions.checkNotNull(set, "scopes can't be null.");
        Scope[] scopeArr = (Scope[]) set.toArray(new Scope[set.size()]);
        Preconditions.checkNotNull(scopeArr, "scopes can't be null.");
        String[] strArr = new String[scopeArr.length];
        for (int i2 = 0; i2 < scopeArr.length; i2++) {
            strArr[i2] = scopeArr[i2].getScopeUri();
        }
        return strArr;
    }
}

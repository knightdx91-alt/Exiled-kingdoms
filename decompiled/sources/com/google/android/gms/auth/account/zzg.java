package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg implements PendingResultUtil.ResultConverter<WorkAccountApi.AddAccountResult, Account> {
    zzg(WorkAccountClient workAccountClient) {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Account convert(Result result) {
        return ((WorkAccountApi.AddAccountResult) result).getAccount();
    }
}
